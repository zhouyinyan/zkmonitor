package com.zbj.study.curator.masterleader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/5/5.
 */
public class MasterLeaderTest {

    static final String MASTERPATH = "/master_path";

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

        LeaderSelector leaderSelector = new LeaderSelector(client, MASTERPATH, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                String name =  ManagementFactory.getRuntimeMXBean().getName();
                String pid = name.split("@")[0];
                System.out.println(System.currentTimeMillis() + "  pid:"+pid+", to be master ");
                TimeUnit.SECONDS.sleep(3);//for do something.
                System.out.println(System.currentTimeMillis() +  "  pid:"+pid+", to give up master ");
            }
        });


        leaderSelector.autoRequeue();
        leaderSelector.start();


        TimeUnit.SECONDS.sleep(20000);

        client.close();


    }
}
