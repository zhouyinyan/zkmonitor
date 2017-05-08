package com.zbj.study.protogene.client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/4/18.
 */
public class SetDataAsyncTest implements Watcher{
    private static CountDownLatch countDownLatch  = new CountDownLatch(1);
    static ZooKeeper zooKeeper = null;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = connect();

       String path1 =  zooKeeper.create("/zk-test", "data".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL);

       System.out.println("success createSession znode : "+path1);

       byte[] dataByte = zooKeeper.getData("/zk-test", true, null);

       System.out.println("data : " + new String(dataByte) );
        
       zooKeeper.setData("/zk-test", "new data22".getBytes(), -1,
               (int rc, String path, Object ctx, Stat stat) -> {
                   System.out.println("set data result : [ rc" + rc + ", stat : "+stat);
                   System.out.println("path" + path + ", ctx : "+ctx);
               }
          , null);

        TimeUnit.SECONDS.sleep(10);

    }


    public static ZooKeeper connect() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",
                            5000,
                            new SetDataAsyncTest());

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
            }else if(event.getType() == Event.EventType.NodeDataChanged ){
                try {
                    System.out.println("receive watch event : "+ event + ", reget data："+ new String(zooKeeper.getData("/zk-test", true, new Stat())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
