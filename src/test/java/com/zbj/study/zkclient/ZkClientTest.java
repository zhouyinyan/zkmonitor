package com.zbj.study.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/4/19.
 */
public class ZkClientTest {

    public static void main(String[] args) throws InterruptedException {

        //createSession session
        ZkClient client = new ZkClient("localhost:2181", 30000, 1000);

        System.out.println("session established!");

        //createSession znode
        String path = "/zkclient/c1";
        client.createPersistent(path, true);
        System.out.println("createSession node : "+ path);

        //delete znode
        boolean deleteRes = client.deleteRecursive("/zkclient");
        System.out.println("delete node : /zkclient : res: " + deleteRes);

        //createSession children
        client.createPersistent(path, true);
        List<String> childrens = client.getChildren("/zkclient");
        System.out.println("createSession childrens : " + childrens);

        //set data
        client.writeData(path, "data v1");
        String data = client.readData(path);
        System.out.println(path + "'s data is "+data);

        //subscribe
        client.subscribeChildChanges("/zkclient", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("child change.  parentPath : " + parentPath + ", currentChilds : " + currentChilds);
            }
        });


        client.createEphemeral("/zkclient/e1");

        TimeUnit.SECONDS.sleep(1);

        client.delete("/zkclient/e1");

        TimeUnit.SECONDS.sleep(1);

        client.deleteRecursive("/zkclient");


        TimeUnit.SECONDS.sleep(1);


        //another subscribe
        client.subscribeDataChanges("/zkclient", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("node data change, data path = " + dataPath + ", data = " + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("node delete, data path = " + dataPath);
            }
        });




        client.createEphemeral("/zkclient", "zkclient data");
        TimeUnit.SECONDS.sleep(1);

        client.writeData("/zkclient", "new zkclient data");
        TimeUnit.SECONDS.sleep(1);
        client.delete("/zkclient");
        TimeUnit.SECONDS.sleep(1);


        //exists
        boolean zkClientNodeExists = client.exists("/zkclient");
        System.out.println("zkClientNodeExists = " + zkClientNodeExists);


        client.createEphemeral("/zkclient", "zkclient data");
        zkClientNodeExists = client.exists("/zkclient");
        System.out.println("zkClientNodeExists = " + zkClientNodeExists);

        TimeUnit.SECONDS.sleep(5);



    }

}
