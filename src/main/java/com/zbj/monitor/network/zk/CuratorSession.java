package com.zbj.monitor.network.zk;

import com.zbj.monitor.network.Session;
import org.apache.curator.framework.CuratorFramework;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class CuratorSession implements Session<CuratorFramework> {

    CuratorFramework proto;

    public CuratorSession(CuratorFramework proto) {
        this.proto = proto;
    }

    @Override
    public CuratorFramework getProto() {
        return proto;
    }
}
