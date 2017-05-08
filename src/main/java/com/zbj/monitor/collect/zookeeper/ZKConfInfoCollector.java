package com.zbj.monitor.collect.zookeeper;

import com.zbj.monitor.collect.GenericCollector;
import com.zbj.monitor.utils.SSHUtils;
import com.zbj.monitor.collect.Parser;
import com.zbj.monitor.model.ZKConfInfo;
import com.zbj.monitor.network.Session;
import com.zbj.monitor.network.ssh.SSHSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static com.zbj.monitor.utils.Constants.LF;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class ZKConfInfoCollector extends GenericCollector<ZKConfInfo, String>{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String ZK_CONF_CMD = "echo conf | nc 127.0.0.1 2181";

    public ZKConfInfoCollector(SSHSessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected ZKConfInfo doCollect(String arg, Session<?> session) {
        ch.ethz.ssh2.Session  sess= (ch.ethz.ssh2.Session) session.getProto();
        String res = SSHUtils.execCmd(ZK_CONF_CMD, sess);

        logger.info("collect zkConfInfo ....");

        // result like this .
        //    clientPort=2181
        //    dataDir=/tmp/zookeeper/version-2
        //    dataLogDir=/tmp/zookeeper/version-2
        //    tickTime=2000
        //    maxClientCnxns=60
        //    minSessionTimeout=4000
        //    maxSessionTimeout=40000
        //    serverId=0

        ZKConfInfo zkConfInfo = new ZKConfInfo();
        Arrays.stream(res.split(LF)).forEach(e -> {
                                               String[] pair =  e.split("=");
                                               switch (pair[0]){
                                                   case "clientPort":
                                                       zkConfInfo.setClientPort(Integer.parseInt(pair[1]));
                                                       break;
                                                   case "dataDir":
                                                       zkConfInfo.setDataDir(pair[1]);
                                                       break;
                                                   case "dataLogDir":
                                                       zkConfInfo.setDataLogDir(pair[1]);
                                                       break;
                                                   case "tickTime":
                                                       zkConfInfo.setTickTime(Long.parseLong(pair[1]));
                                                       break;
                                                   case "maxClientCnxns":
                                                       zkConfInfo.setMaxClientCnxns(Long.parseLong(pair[1]));
                                                       break;
                                                   case "minSessionTimeout":
                                                       zkConfInfo.setMinSessionTimeout(Long.parseLong(pair[1]));
                                                       break;
                                                   case "maxSessionTimeout":
                                                       zkConfInfo.setMaxSessionTimeout(Long.parseLong(pair[1]));
                                                       break;
                                                   case "serverId":
                                                       zkConfInfo.setServerId(Integer.parseInt(pair[1]));
                                                       break;
                                                   default:
                                                       break;
                                               }
                                            });

        return zkConfInfo;

    }



}
