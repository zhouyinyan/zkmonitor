package com.zbj.study.protogene.client;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/4/18.
 */
public class GetChildernASyncTest implements Watcher{
    private static CountDownLatch countDownLatch  = new CountDownLatch(1);
    static ZooKeeper zooKeeper = null;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = connect();

       String path1 =  zooKeeper.create("/zk-test", "data".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT);

       zooKeeper.create("/zk-test/c1", "data".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);

        zooKeeper.getChildren(path1, true, (int rc, String path, Object ctx, List<String> children) ->
                                        System.out.println("childrens : " + children), "ctx");

        zooKeeper.create("/zk-test/c2", "data".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);

        TimeUnit.SECONDS.sleep(10);

    }


    public static ZooKeeper connect() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",
                            5000,
                            new GetChildernASyncTest());

        System.out.println("zookeeper.state:" + zooKeeper.getState());
        countDownLatch.await();
        System.out.println("zookeeper session established.");

        return zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("receive watched event : " + event);
        if(event.getState() == Event.KeeperState.SyncConnected){
            if(Event.EventType.None == event.getType() && null == event.getPath()) {
                countDownLatch.countDown();
            }else if(event.getType() == Event.EventType.NodeChildrenChanged){
                try {
                    System.out.println("ReGet Child:" + zooKeeper.getChildren("/zk-test", true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
