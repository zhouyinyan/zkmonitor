package com.zbj.monitor.collect.zookeeper;

import com.zbj.monitor.collect.CollectException;
import com.zbj.monitor.collect.GenericCollector;
import com.zbj.monitor.network.Session;
import com.zbj.monitor.network.zk.CuratorSessionFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class ZKNodeAliveCollector extends GenericCollector<Boolean, String>{

    static final String CHECK_PATH = "";

    public ZKNodeAliveCollector(CuratorSessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Boolean doCollect(String ags, Session<?> session) {
        try {
            CuratorFramework client = (CuratorFramework) session.getProto();

            String createResult =  client.create().withMode(CreateMode.PERSISTENT)
                    .forPath("/NodeAliveTest","NodeAliveTestData".getBytes());

            String nodeData =  new String(client.getData().forPath("/NodeAliveTest"));

            client.delete().deletingChildrenIfNeeded().forPath("/NodeAliveTest");

            if("NodeAliveTestData".equals(nodeData)){
                return true;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CollectException();
        } finally {
        }

    }
}
