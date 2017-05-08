package com.zbj.monitor.schedule;

import com.zbj.monitor.collect.hostinfo.HostNameCollector;
import com.zbj.monitor.event.EventPublisher;
import com.zbj.monitor.event.hostinfo.HostnameEvent;
import com.zbj.monitor.network.ssh.SSHSessionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhouyinyan on 17/5/3.
 */
@Service
public class HostNameCollectSchedule {

    @Resource
    EventPublisher eventPublisher;

//    @Scheduled(fixedRate = 3000)
    public void hostnameCollect(){
        HostNameCollector collector = new HostNameCollector(new SSHSessionFactory());
        String hostname = collector.collect(null);
        System.out.println("theadname:"+ Thread.currentThread().getName()+"; collect host name: "+ hostname);

        eventPublisher.publish(new HostnameEvent(this, hostname));

    }
}
