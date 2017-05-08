package com.zbj.monitor.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Created by zhouyinyan on 17/2/7.
 */
@Component
public class EventPublisher implements ApplicationEventPublisherAware {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void publish(Event event){
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
