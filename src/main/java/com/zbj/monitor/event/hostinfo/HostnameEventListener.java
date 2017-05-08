package com.zbj.monitor.event.hostinfo;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhouyinyan on 17/2/7.
 */
@Component
public class HostnameEventListener implements ApplicationListener<HostnameEvent> {

    @Override
    public void onApplicationEvent(HostnameEvent event) {

        System.out.println("theadname:"+ Thread.currentThread().getName()+", receive event : "+event);
    }
}
