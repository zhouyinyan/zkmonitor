package com.zbj.monitor.collect;

import com.zbj.monitor.collect.zookeeper.ZKConfInfoCollector;
import com.zbj.monitor.model.ZKConfInfo;
import com.zbj.monitor.network.ssh.SSHSessionFactory;
import org.junit.Test;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class ZKConfInfoCollectorTest {


    @Test
    public void testCollect(){
        ZKConfInfoCollector collector = new ZKConfInfoCollector(new SSHSessionFactory());
        ZKConfInfo zkConfInfo = collector.collect("");
        System.out.println("theadname:"+ Thread.currentThread().getName()+"; collect zk conf: "+ zkConfInfo.toString());
    }
}
