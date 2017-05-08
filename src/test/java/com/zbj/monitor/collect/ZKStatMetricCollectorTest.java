package com.zbj.monitor.collect;

import com.zbj.monitor.collect.zookeeper.ZKStatMetricCollector;
import com.zbj.monitor.model.ZKStatMetric;
import com.zbj.monitor.network.ssh.SSHSessionFactory;
import org.junit.Test;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class ZKStatMetricCollectorTest {


    @Test
    public void testCollect(){
        ZKStatMetricCollector collector = new ZKStatMetricCollector(new SSHSessionFactory());
        ZKStatMetric statMetric = collector.collect("");
        System.out.println("theadname:"+ Thread.currentThread().getName()+"; collect zk stat metric: "+ statMetric.toString());
    }
}
