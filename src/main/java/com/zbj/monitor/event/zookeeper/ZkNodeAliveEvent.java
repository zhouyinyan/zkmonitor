package com.zbj.monitor.event.zookeeper;

import com.google.common.base.Objects;
import com.zbj.monitor.event.Event;

/**
 * Created by zhouyinyan on 17/2/7.
 */
public class ZkNodeAliveEvent extends Event {

    private String zkAddresss;

    private Boolean status;


    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ZkNodeAliveEvent(Object source) {
        super(source);
    }

    public ZkNodeAliveEvent(Object source, String zkAddresss, Boolean status) {
        super(source);
        this.zkAddresss = zkAddresss;
        this.status = status;
    }

    public String getZkAddresss() {
        return zkAddresss;
    }

    public void setZkAddresss(String zkAddresss) {
        this.zkAddresss = zkAddresss;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("zkAddresss", zkAddresss)
                .add("status", status)
                .toString();
    }
}
