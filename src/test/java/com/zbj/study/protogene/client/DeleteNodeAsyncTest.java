package com.zbj.study.protogene.client;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/4/18.
 */
public class DeleteNodeAsyncTest implements Watcher{
    private static CountDownLatch countDownLatch  = new CountDownLatch(1);


    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = connect();

       zooKeeper.create("/zk-test", "data".getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL,
                        new CreateNodeStringCallback(),
                        "I am ctx");

       zooKeeper.delete("/zk-test", -1, new AsyncCallback.VoidCallback() {
           @Override
           public void processResult(int rc, String path, Object ctx) {
               System.out.println("delete result: rc = " + rc + ", path = "+ path + ", context = " + ctx );
           }
       }, "im context!");


        TimeUnit.SECONDS.sleep(30);
    }


    public static ZooKeeper connect() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",
                            5000,
                            new DeleteNodeAsyncTest());

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


    static class CreateNodeStringCallback implements AsyncCallback.StringCallback{

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("async createSession node result ：[ rc = " + rc + ", path = " + path + ", name = " + name + ", ctx = " + ctx);

        }
    }
}
