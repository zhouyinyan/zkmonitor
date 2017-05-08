package com.zbj.monitor.event.zookeeper;

import com.zbj.monitor.model.ZKStatMetric;
import com.zbj.monitor.repository.ZkStatMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhouyinyan on 17/2/7.
 */
@Component
public class ZkStatMetricEventListener implements ApplicationListener<ZkStatMetricEvent> {

    @Autowired
    ZkStatMetricRepository zkStatMetricRepository;

    @Override
    public void onApplicationEvent(ZkStatMetricEvent event) {

        ZKStatMetric stat = event.getStatMetric();
        stat = zkStatMetricRepository.save(stat);

        System.out.println("repository ===== theadname:"+ Thread.currentThread().getName()+", save stat : "+stat);
    }
}
