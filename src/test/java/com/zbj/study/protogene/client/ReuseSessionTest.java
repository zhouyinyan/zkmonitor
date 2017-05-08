package com.zbj.study.protogene.client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/4/18.
 */
public class ReuseSessionTest implements Watcher{

    private static CountDownLatch countDownLatch  = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {

        ZooKeeper zooKeeper1 = new ZooKeeper("127.0.0.1:2181",
                                        5000,
                                        new ReuseSessionTest());

        System.out.println("zookeeper.state:" + zooKeeper1.getState());
        countDownLatch.await();

        long sessionId = zooKeeper1.getSessionId();
        byte[] sessionPassword = zooKeeper1.getSessionPasswd();

        ZooKeeper zooKeeper2 = new ZooKeeper("127.0.0.1:2181",
                                        5000,
                                        new ReuseSessionTest(),
                                        1l,
                                        "test".getBytes());


        //重用会话的场景是什么？客户端断线重连？
        //复用会话为什么会一直断线重连？
        /**
         * receive watched event:WatchedEvent state:SyncConnected type:None path:null
         * receive watched event:WatchedEvent state:Disconnected type:None path:null
         */
        ZooKeeper zooKeeper3 = new ZooKeeper("127.0.0.1:2181",
                                            5000,
                                            new ReuseSessionTest(),
                                            sessionId,
                                            sessionPassword);


        TimeUnit.SECONDS.sleep(60);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("receive watched event:"+event);
        if(event.getState() == Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }
}
