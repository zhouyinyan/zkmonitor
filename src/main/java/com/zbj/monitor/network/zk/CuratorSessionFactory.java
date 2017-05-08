package com.zbj.monitor.network.zk;

import com.zbj.monitor.network.RecycleSessionException;
import com.zbj.monitor.network.Session;
import com.zbj.monitor.network.SessionFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Created by zhouyinyan on 17/5/3.
 */
@Component
public class CuratorSessionFactory implements SessionFactory {

    static final String DEFAULT_ZKADDRESS =  "127.0.0.1:2181";

    String zkAddress ;

    CuratorSession curatorSession;

    public CuratorSessionFactory() {
        this(DEFAULT_ZKADDRESS);
    }

    public CuratorSessionFactory(String zkAddresss){
        if(!StringUtils.isEmpty(zkAddresss)){
            this.zkAddress = DEFAULT_ZKADDRESS;
        }

        CuratorFramework client =  CuratorFrameworkFactory.builder()
                .connectString(this.zkAddress)
                .connectionTimeoutMs(5000)
                .sessionTimeoutMs(30000)
                .canBeReadOnly(true)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("zkmonitor")
                .build();

        client.start();

        curatorSession = new CuratorSession(client);
    }


    @Override
    public Session createSession() {
        return curatorSession;
    }

    @Override
    public void recycleSession(Session session) throws RecycleSessionException {
        Assert.notNull(session, "recycle session , session cant be null");
        //DO NOTHING
    }

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    public CuratorSession getCuratorSession() {
        return curatorSession;
    }

    public void setCuratorSession(CuratorSession curatorSession) {
        this.curatorSession = curatorSession;
    }
}
