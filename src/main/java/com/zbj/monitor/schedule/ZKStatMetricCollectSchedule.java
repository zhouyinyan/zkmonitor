package com.zbj.monitor.schedule;

import com.zbj.monitor.collect.zookeeper.ZKStatMetricCollector;
import com.zbj.monitor.event.EventPublisher;
import com.zbj.monitor.event.zookeeper.ZkStatMetricEvent;
import com.zbj.monitor.model.ZKStatMetric;
import com.zbj.monitor.network.ssh.SSHSessionFactory;
import com.zbj.monitor.schedule.leaderselect.LeaderSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhouyinyan on 17/5/3.
 */
@Service
public class ZKStatMetricCollectSchedule extends LeaderBaseSchedule{

    @Resource
    EventPublisher eventPublisher;

    @Autowired
    SSHSessionFactory sshSessionFactory;

    @Scheduled(fixedRate = 100000)
    public void zKStatMetricCollect(){
        schedule();
    }

    @Override
    protected void doSchedule() {
        ZKStatMetricCollector collector = new ZKStatMetricCollector(sshSessionFactory);
        ZKStatMetric statMetric = collector.collect("");
        System.out.println("theadname:"+ Thread.currentThread().getName()+"; collect zk stat metric: "+ statMetric);

        eventPublisher.publish(new ZkStatMetricEvent(this, sshSessionFactory.getHostname(), statMetric));
    }
}
