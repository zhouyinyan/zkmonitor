package com.zbj.monitor.event.hostinfo;

import com.zbj.monitor.event.Event;
import org.springframework.context.ApplicationEvent;

/**
 * Created by zhouyinyan on 17/2/7.
 */
public class HostnameEvent extends Event {

    private String hostname;


    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public HostnameEvent(Object source, String hostname) {
        super(source);
        this.hostname = hostname;
    }

    public String getHostname() {
        return hostname;
    }

    @Override
    public String toString() {
        return "ZkNodeAliveEvent{" +
                "hostname='" + hostname + '\'' +
                '}';
    }
}
