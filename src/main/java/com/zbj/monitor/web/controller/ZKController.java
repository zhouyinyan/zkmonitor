package com.zbj.monitor.web.controller;

import com.zbj.monitor.model.ZKCluster;
import com.zbj.monitor.model.ZKConfInfo;
import com.zbj.monitor.model.ZKStatMetric;
import com.zbj.monitor.repository.ZkClusterRepository;
import com.zbj.monitor.repository.ZkStatMetricRepository;
import com.zbj.monitor.web.InfoCaches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhouyinyan on 17/5/3.
 */
@RestController
public class ZKController {

    @Autowired
    InfoCaches infoCaches;

    @Autowired
    ZkStatMetricRepository zkStatMetricRepository;

    @Autowired
    ZkClusterRepository zkClusterRepository;

    @RequestMapping("/zk/conf")
    public ZKConfInfo conf(){

        return infoCaches.getZkConfInfo();
    }

    @RequestMapping("/zk/stat")
    public Page<ZKStatMetric> stat(int page, int size){
        PageRequest pageRequest = new PageRequest(1, 10);
        return zkStatMetricRepository.findAll(pageRequest);
    }

    @RequestMapping(value = "/zkcluster/find", method = RequestMethod.GET)
    public List<ZKCluster> zkClusts(){
        List<ZKCluster> zkClusters = zkClusterRepository.findAll();
        return  zkClusters;
    }

    @RequestMapping("/zkcluster/save")
    public ZKCluster zkClustsSave(ZKCluster cluster){
        Assert.notNull(cluster, "cluster cant be null while save it");
        cluster = zkClusterRepository.save(cluster);
        return  cluster;
    }
}
