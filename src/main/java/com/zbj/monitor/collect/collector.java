package com.zbj.monitor.collect;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public interface collector<R, A> {

    R collect(A arg) throws CollectException;
}
