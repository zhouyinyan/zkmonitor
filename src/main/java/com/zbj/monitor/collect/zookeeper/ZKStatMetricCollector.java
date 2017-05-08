package com.zbj.monitor.collect.zookeeper;

import com.zbj.monitor.collect.CollectException;
import com.zbj.monitor.collect.GenericCollector;
import com.zbj.monitor.model.ZKConfInfo;
import com.zbj.monitor.model.ZKStatMetric;
import com.zbj.monitor.network.Session;
import com.zbj.monitor.network.ssh.SSHSessionFactory;
import com.zbj.monitor.network.zk.CuratorSessionFactory;
import com.zbj.monitor.utils.SSHUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.util.StringUtils;

import java.util.Arrays;

import static com.zbj.monitor.utils.Constants.LF;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class ZKStatMetricCollector extends GenericCollector<ZKStatMetric, String>{

    private static final String ZK_STAT_CMD = "echo stat | nc 127.0.0.1 2181";

    public ZKStatMetricCollector(SSHSessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected ZKStatMetric doCollect(String ags, Session<?> session) {
        try {
            ch.ethz.ssh2.Session sess = (ch.ethz.ssh2.Session) session.getProto();
            String res = SSHUtils.execCmd(ZK_STAT_CMD, sess);

            //    Latency min/avg/max: 0/0/71
            //    Received: 364
            //    Sent: 363
            //    Connections: 2
            //    Outstanding: 0
            //    Zxid: 0xb6
            //    Mode: standalone
            //    Node count: 5

            ZKStatMetric zkStatMetric = new ZKStatMetric();
            Arrays.stream(res.split(LF)).forEach(e -> {
                String[] pair =  e.split(":");
                switch (pair[0]){
                    case "Latency min/avg/max":
                        zkStatMetric.setLatencyString(pair[1]);
                        break;
                    case "Received":
                        zkStatMetric.setReceived(Long.parseLong(StringUtils.trimWhitespace(pair[1])));
                        break;
                    case "Sent":
                        zkStatMetric.setSend(Long.parseLong(StringUtils.trimWhitespace(pair[1])));
                        break;
                    case "Connections":
                        zkStatMetric.setConnnections(Long.parseLong(StringUtils.trimWhitespace(pair[1])));
                        break;
                    case "Outstanding":
                        zkStatMetric.setOutstanding(Long.parseLong(StringUtils.trimWhitespace(pair[1])));
                        break;
                    case "Zxid":
                        zkStatMetric.setZxid(pair[1]);
                        break;
                    case "Mode":
                        zkStatMetric.setMode(pair[1]);
                        break;
                    case "Node count":
                        zkStatMetric.setNodeCount(Long.parseLong(StringUtils.trimWhitespace(pair[1])));
                        break;
                    default:
                        break;
                }
            });

            return zkStatMetric;

        } catch (Exception e) {
            e.printStackTrace();
            throw new CollectException();
        } finally {
        }

    }
}
