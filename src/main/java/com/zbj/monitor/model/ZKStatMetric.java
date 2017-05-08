package com.zbj.monitor.model;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhouyinyan on 17/5/4.
 */
@Document(collection = "zk_stat_metric")
public class ZKStatMetric implements Serializable {

    @Id
    private String id;

    private String latencyString;
    private long received;
    private long send;
    private long connnections;
    private long outstanding;
    private String zxid;
    private String mode;
    private long nodeCount;
    private Date collectTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatencyString() {
        return latencyString;
    }

    public void setLatencyString(String latencyString) {
        this.latencyString = latencyString;
    }

    public long getReceived() {
        return received;
    }

    public void setReceived(long received) {
        this.received = received;
    }

    public long getSend() {
        return send;
    }

    public void setSend(long send) {
        this.send = send;
    }

    public long getConnnections() {
        return connnections;
    }

    public void setConnnections(long connnections) {
        this.connnections = connnections;
    }

    public long getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(long outstanding) {
        this.outstanding = outstanding;
    }

    public String getZxid() {
        return zxid;
    }

    public void setZxid(String zxid) {
        this.zxid = zxid;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public long getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(long nodeCount) {
        this.nodeCount = nodeCount;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("latencyString", latencyString)
                .add("received", received)
                .add("send", send)
                .add("connnections", connnections)
                .add("outstanding", outstanding)
                .add("zxid", zxid)
                .add("mode", mode)
                .add("nodeCount", nodeCount)
                .add("collectTime", collectTime)
                .toString();
    }
}
