package com.zbj.study.protogene.client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/4/18.
 */
public class GetDataAsyncTest implements Watcher{
    private static CountDownLatch countDownLatch  = new CountDownLatch(1);
    static ZooKeeper zooKeeper = null;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = connect();

       String path1 =  zooKeeper.create("/zk-test", "data".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT);

       System.out.println("success createSession znode : "+path1);

       Stat stat = new Stat();
       zooKeeper.getData("/zk-test", true, new AsyncCallback.DataCallback() {
           @Override
           public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
               System.out.println("createSession data result : [ rc = " + rc + ", path = " + path + ", ctx = " + ctx + ", data = " + new String(data) + ", stat : "+stat);
           }
       }, "ctx");

       zooKeeper.setData("/zk-test", "new data".getBytes(), stat.getVersion());

        TimeUnit.SECONDS.sleep(10);

    }


    public static ZooKeeper connect() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",
                            5000,
                            new GetDataAsyncTest());

        System.out.println("zookeeper.state:" + zooKeeper.getState());
        countDownLatch.await();
        System.out.println("zookeeper session established.");

        return zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getState() == Event.KeeperState.SyncConnected){
            if(Event.EventType.None == event.getType() && null == event.getPath()) {
                countDownLatch.countDown();
            }else if(event.getType() == Event.EventType.NodeChildrenChanged){
                //
            }else if(event.getType() == Event.EventType.NodeDataChanged){
                try {
                    System.out.println("receive watch event : "+ event + ", reget data："+ new String(zooKeeper.getData("/zk-test", true, new Stat())));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
