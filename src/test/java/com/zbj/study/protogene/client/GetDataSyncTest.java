package com.zbj.study.protogene.client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/4/18.
 */
public class GetDataSyncTest implements Watcher{
    private static CountDownLatch countDownLatch  = new CountDownLatch(1);
    static ZooKeeper zooKeeper = null;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = connect();

       String path1 =  zooKeeper.create("/zk-test", "data".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL);

       System.out.println("success createSession znode : "+path1);

       Stat stat = new Stat();
       byte[] dataByte = zooKeeper.getData("/zk-test", true, stat);

       System.out.println("data : " + new String(dataByte) + ", stat : "+stat);


       zooKeeper.setData("/zk-test", "new data".getBytes(), stat.getVersion());


        TimeUnit.SECONDS.sleep(10);

    }


    public static ZooKeeper connect() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",
                            5000,
                            new GetDataSyncTest());

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
