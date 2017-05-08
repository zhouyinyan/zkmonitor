package com.zbj.study.protogene.client;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/4/18.
 */
public class AuthAclTest implements Watcher{
    private static CountDownLatch countDownLatch  = new CountDownLatch(1);
    private static CountDownLatch countDownLatch1  = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = connectWithAuth();

       String path1 =  zooKeeper.create("/zk-test", "data".getBytes(),
                        ZooDefs.Ids.CREATOR_ALL_ACL,
                        CreateMode.EPHEMERAL);

       System.out.println("success createSession znode : "+path1);

        ZooKeeper zooKeeper1 = connect();
        System.out.println(new String(zooKeeper1.getData("/zk-test", false ,null)));

        TimeUnit.SECONDS.sleep(5);
    }


    public static ZooKeeper connect() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",
                            5000,
                            new AuthAclTest());

        System.out.println("zookeeper.state:" + zooKeeper.getState());
        countDownLatch.await();
        System.out.println("zookeeper session established.");

        return zooKeeper;
    }

    public static ZooKeeper connectWithAuth() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",
                5000,
                (WatchedEvent event) -> {
                    System.out.println("receive watched event:"+event);
                    if(event.getState() == Event.KeeperState.SyncConnected){
                        countDownLatch1.countDown();
                    }
                });

        countDownLatch1.await();
        zooKeeper.addAuthInfo("digest", "foo:true".getBytes());
        return zooKeeper;
    }


    @Override
    public void process(WatchedEvent event) {
        System.out.println("receive watched event:"+event);
        if(event.getState() == Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }
}
