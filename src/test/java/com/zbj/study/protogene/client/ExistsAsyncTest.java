package com.zbj.study.protogene.client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/4/18.
 */
public class ExistsAsyncTest implements Watcher{
    private static CountDownLatch countDownLatch  = new CountDownLatch(1);
    static ZooKeeper zooKeeper = null;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = connect();

       zooKeeper.exists("/zk-test", true,
                (int rc, String path, Object ctx, Stat stat) -> System.out.println("rc = " + rc + ", stat = " + stat),
                null);

       String path1 =  zooKeeper.create("/zk-test", "data".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL);

       System.out.println("success createSession znode : "+path1);

       zooKeeper.exists("/zk-test", true,
               (int rc, String path, Object ctx, Stat stat) -> System.out.println("rc = " + rc + ", stat = " + stat),
               null);

       zooKeeper.setData("/zk-test", "new data22".getBytes(), -1);
       zooKeeper.exists("/zk-test", true);
       zooKeeper.delete("/zk-test", -1);

        TimeUnit.SECONDS.sleep(10);

    }


    public static ZooKeeper connect() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",
                            5000,
                            new ExistsAsyncTest());

        System.out.println("zookeeper.state:" + zooKeeper.getState());
        countDownLatch.await();
        System.out.println("zookeeper session established.");

        return zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getState() == Event.KeeperState.SyncConnected) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                countDownLatch.countDown();
            } else if (event.getType() == Event.EventType.NodeCreated
                    || event.getType() == Event.EventType.NodeDataChanged
                    || event.getType() == Event.EventType.NodeDeleted) {
                System.out.println("receive watch event : " + event);
            }
        }
    }
}
