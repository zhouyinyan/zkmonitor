package com.zbj.study.protogene.client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhouyinyan on 17/4/18.
 */
public class CreateSessionTest implements Watcher{

    private static CountDownLatch countDownLatch  = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {

        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",
                                        5000,
                                        new CreateSessionTest());

        System.out.println("zookeeper.state:" + zooKeeper.getState());
        countDownLatch.await();

        System.out.println("zookeeper session established.");
        System.out.println("zookeeper.state:" + zooKeeper.getState());


    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("receive watched event:"+event);
        if(event.getState() == Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }
}
