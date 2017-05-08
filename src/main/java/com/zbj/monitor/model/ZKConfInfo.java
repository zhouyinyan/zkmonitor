package com.zbj.monitor.model;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class ZKConfInfo implements Serializable {

    private int clientPort;
    private String dataDir;
    private String dataLogDir;
    private long tickTime;
    private long maxClientCnxns;
    private long minSessionTimeout;
    private long maxSessionTimeout;
    private int serverId;

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public String getDataDir() {
        return dataDir;
    }

    public void setDataDir(String dataDir) {
        this.dataDir = dataDir;
    }

    public String getDataLogDir() {
        return dataLogDir;
    }

    public void setDataLogDir(String dataLogDir) {
        this.dataLogDir = dataLogDir;
    }

    public long getTickTime() {
        return tickTime;
    }

    public void setTickTime(long tickTime) {
        this.tickTime = tickTime;
    }

    public long getMaxClientCnxns() {
        return maxClientCnxns;
    }

    public void setMaxClientCnxns(long maxClientCnxns) {
        this.maxClientCnxns = maxClientCnxns;
    }

    public long getMinSessionTimeout() {
        return minSessionTimeout;
    }

    public void setMinSessionTimeout(long minSessionTimeout) {
        this.minSessionTimeout = minSessionTimeout;
    }

    public long getMaxSessionTimeout() {
        return maxSessionTimeout;
    }

    public void setMaxSessionTimeout(long maxSessionTimeout) {
        this.maxSessionTimeout = maxSessionTimeout;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("clientPort", clientPort)
                .add("dataDir", dataDir)
                .add("dataLogDir", dataLogDir)
                .add("tickTime", tickTime)
                .add("maxClientCnxns", maxClientCnxns)
                .add("minSessionTimeout", minSessionTimeout)
                .add("maxSessionTimeout", maxSessionTimeout)
                .add("serverId", serverId)
                .toString();
    }
}
