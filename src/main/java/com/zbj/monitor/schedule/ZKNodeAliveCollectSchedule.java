package com.zbj.monitor.schedule;

import com.zbj.monitor.collect.zookeeper.ZKNodeAliveCollector;
import com.zbj.monitor.event.EventPublisher;
import com.zbj.monitor.event.zookeeper.ZkNodeAliveEvent;
import com.zbj.monitor.network.zk.CuratorSessionFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/5/3.
 */
@Service
public class ZKNodeAliveCollectSchedule extends LeaderBaseSchedule{

    @Resource
    EventPublisher eventPublisher;

    @Autowired
    CuratorSessionFactory curatorSessionFactory;

    @Scheduled(fixedRate = 100000)
    private void zKNodeAliveCollect(){
       schedule();
    }

    @Override
    protected void doSchedule() {
        ZKNodeAliveCollector collector = new ZKNodeAliveCollector(curatorSessionFactory);
        Boolean status = collector.collect("");
        System.out.println(new Date() + " ------ theadname:"+ Thread.currentThread().getName()+"; collect zk node alive: "+ status);

        eventPublisher.publish(new ZkNodeAliveEvent(this, curatorSessionFactory.getZkAddress(), status));

    }
}
