package com.zbj.monitor.repository;

import com.zbj.monitor.model.ZKStatMetric;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zhouyinyan on 17/5/4.
 */
public interface ZkStatMetricRepository extends MongoRepository<ZKStatMetric, String> {
}
