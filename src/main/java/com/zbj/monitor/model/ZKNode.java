package com.zbj.monitor.model;

import java.io.Serializable;

/**
 * Created by zhouyinyan on 17/5/6.
 */
public class ZKNode implements Serializable{

    private String address;//ip:port

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
