package com.zbj.study.curator.masterleader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/5/5.
 */
public class LeaderLatchTest{

    static final String LEADERLATCHPATH = "/LEADERLATCH";

    public static void main(String[] args) throws Exception {

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .connectionTimeoutMs(5000)
                .sessionTimeoutMs(30000)
                .canBeReadOnly(true)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("zk")
                .build();


        client.start();



        LeaderLatch leaderLatch = new LeaderLatch(client, LEADERLATCHPATH);


        String name =  ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];

        leaderLatch.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                System.out.println(pid + " -- , im leader");
            }

            @Override
            public void notLeader() {
                System.out.println(pid + " -- , im not leader");
            }
        });

        leaderLatch.start();


        while (true) {
            System.out.println(pid + " -- , has leadship : " + leaderLatch.hasLeadership());
            TimeUnit.SECONDS.sleep(3);
        }


//        client.close();


    }
}
