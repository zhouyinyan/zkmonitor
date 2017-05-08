package com.zbj.monitor.event.zookeeper;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhouyinyan on 17/2/7.
 */
@Component
public class ZkNodeAliveEventListener implements ApplicationListener<ZkNodeAliveEvent> {

    @Override
    public void onApplicationEvent(ZkNodeAliveEvent event) {

        System.out.println("theadname:"+ Thread.currentThread().getName()+", receive event : "+event);
    }
}
