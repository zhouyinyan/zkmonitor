package com.zbj.study.protogene.client;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhouyinyan on 17/4/18.
 */
public class CreateNodeSyncTest implements Watcher{
    private static CountDownLatch countDownLatch  = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = connect();

       String path1 =  zooKeeper.create("/zk-test", "data".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL);

       System.out.println("success createSession znode : "+path1);

       String path2 =  zooKeeper.create("/zk-test", "data".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);

       System.out.println("success createSession znode : "+path2);

    }


    public static ZooKeeper connect() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",
                            5000,
                            new CreateNodeSyncTest());

        System.out.println("zookeeper.state:" + zooKeeper.getState());
        countDownLatch.await();
        System.out.println("zookeeper session established.");

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
