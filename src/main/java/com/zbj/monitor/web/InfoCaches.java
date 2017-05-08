package com.zbj.monitor.web;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.zbj.monitor.collect.zookeeper.ZKConfInfoCollector;
import com.zbj.monitor.model.ZKConfInfo;
import com.zbj.monitor.network.ssh.SSHSessionFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyinyan on 17/5/3.
 */
@Component
public class InfoCaches {

    static final String ZK_CONF_KEY = "zkconfkey";

    LoadingCache<String, ZKConfInfo> zkConfCache = CacheBuilder.newBuilder()
        .maximumSize(1)
        .expireAfterWrite(30, TimeUnit.MINUTES)
        .build(
            new CacheLoader<String, ZKConfInfo>() {
                public ZKConfInfo load(String key) {
                    if(ZK_CONF_KEY.equals(key)) {
                        ZKConfInfoCollector collector = new ZKConfInfoCollector(new SSHSessionFactory());
                        ZKConfInfo zkConfInfo = collector.collect("");
                        return zkConfInfo;
                    }else{
                        return new ZKConfInfo();
                    }
                }
    });

    public ZKConfInfo getZkConfInfo(){
        return zkConfCache.getUnchecked(ZK_CONF_KEY);
    }

}
