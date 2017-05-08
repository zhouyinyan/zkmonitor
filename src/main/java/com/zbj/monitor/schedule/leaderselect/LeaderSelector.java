package com.zbj.monitor.schedule.leaderselect;

import com.zbj.monitor.network.zk.CuratorSessionFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/5/5.
 */
@Component
public class LeaderSelector implements InitializingBean {

    static final String LEADERLATCHPATH = "/LEADERLATCH";

    LeaderLatch leaderLatch ;

    @Autowired
    CuratorSessionFactory curatorSessionFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        CuratorFramework client = curatorSessionFactory.getCuratorSession().getProto();
        leaderLatch = new LeaderLatch(client, LEADERLATCHPATH);
        leaderLatch.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                System.out.println(Thread.currentThread().getName() + " -- , im leader");
            }

            @Override
            public void notLeader() {
                System.out.println(Thread.currentThread().getName() + " -- , im not leader");
            }
        });

        leaderLatch.start();
    }

    public boolean hasLeadership(){
        return leaderLatch.hasLeadership();
    }

}
