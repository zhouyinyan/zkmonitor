package com.zbj.monitor.collect;

import com.zbj.monitor.collect.zookeeper.ZKNodeAliveCollector;
import com.zbj.monitor.network.zk.CuratorSessionFactory;
import org.junit.Test;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class ZKNodeAlievCollectorTest {


    @Test
    public void testCollect(){
        ZKNodeAliveCollector collector = new ZKNodeAliveCollector(new CuratorSessionFactory("127.0.0.1:2181"));
        Boolean staus = collector.collect("");
        System.out.println("theadname:"+ Thread.currentThread().getName()+"; collect zk node alive: "+ staus);
    }
}
