package com.zbj.monitor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouyinyan on 17/5/6.
 */
@Document
public class ZKCluster implements Serializable{

    @Id
    private String id;
    private String clusterName;
    @Field
    private List<ZKNode> nodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public List<ZKNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<ZKNode> nodes) {
        this.nodes = nodes;
    }
}
