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
public class ZkStatMetricAlarmEventListener implements ApplicationListener<ZkStatMetricEvent> {

    @Override
    public void onApplicationEvent(ZkStatMetricEvent event) {

        System.out.println("alarm ===== theadname:"+ Thread.currentThread().getName()+", alarm stat : "+event);
    }
}
