package com.zbj.monitor.collect;

import com.zbj.monitor.collect.hostinfo.HostNameCollector;
import com.zbj.monitor.network.ssh.SSHSessionFactory;
import org.junit.Test;

import java.util.Date;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class HostNameCollectorTest {


    @Test
    public void testCollect(){
        HostNameCollector collector = new HostNameCollector(new SSHSessionFactory());
        String hostname = collector.collect("");
        System.out.println("theadname:"+ Thread.currentThread().getName()+"; collect host name: "+ hostname);
    }
}
