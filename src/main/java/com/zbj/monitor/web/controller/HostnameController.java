package com.zbj.monitor.web.controller;

import com.zbj.monitor.collect.hostinfo.HostNameCollector;
import com.zbj.monitor.network.ssh.SSHSessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhouyinyan on 17/5/3.
 */
@RestController
public class HostnameController {

    @RequestMapping("/hostname")
    public String hostname(){
        HostNameCollector collector = new HostNameCollector(new SSHSessionFactory());
        String hostname = collector.collect(null);
        return hostname;
    }
}
