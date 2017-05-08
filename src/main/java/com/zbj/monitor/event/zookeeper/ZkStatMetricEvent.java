package com.zbj.monitor.event.zookeeper;

import com.google.common.base.Objects;
import com.zbj.monitor.event.Event;
import com.zbj.monitor.model.ZKStatMetric;

/**
 * Created by zhouyinyan on 17/2/7.
 */
public class ZkStatMetricEvent extends Event {

    private String zkAddresss;

    private ZKStatMetric statMetric;


    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ZkStatMetricEvent(Object source) {
        super(source);
    }

    public ZkStatMetricEvent(Object source, String zkAddresss, ZKStatMetric statMetric) {
        super(source);
        this.zkAddresss = zkAddresss;
        this.statMetric = statMetric;
    }

    public String getZkAddresss() {
        return zkAddresss;
    }

    public void setZkAddresss(String zkAddresss) {
        this.zkAddresss = zkAddresss;
    }

    public ZKStatMetric getStatMetric() {
        return statMetric;
    }

    public void setStatMetric(ZKStatMetric statMetric) {
        this.statMetric = statMetric;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("zkAddresss", zkAddresss)
                .add("statMetric", statMetric)
                .toString();
    }
}
