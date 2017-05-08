package com.zbj.study.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhouyinyan on 17/4/19.
 */
public class CuratorTest {

    static Logger  logger = LoggerFactory.getLogger("test");

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


        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath("/test2/c1");

        client.close();


    }
}
