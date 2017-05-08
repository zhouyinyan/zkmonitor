package com.zbj.monitor.collect;

/**
 * Created by zhouyinyan on 17/5/3.
 */
@FunctionalInterface
public interface Parser<T> {

    T parse(String result);
}
