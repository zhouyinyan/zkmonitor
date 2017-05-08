package com.zbj.monitor.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by zhouyinyan on 17/2/7.
 */
public abstract class Event extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public Event(Object source) {
        super(source);
    }
}
