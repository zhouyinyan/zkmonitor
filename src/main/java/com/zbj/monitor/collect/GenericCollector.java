package com.zbj.monitor.collect;

import com.zbj.monitor.network.Session;
import com.zbj.monitor.network.SessionFactory;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public abstract class GenericCollector<R, A> implements collector<R, A> {

    SessionFactory sessionFactory;

    public GenericCollector(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public R collect(A arg) throws CollectException {
        Session<?> session = sessionFactory.createSession();

        R r =  doCollect(arg, session);

        sessionFactory.recycleSession(session);

        return r;
    }

    protected abstract R doCollect(A arg, Session<?> session) throws CollectException;
}
