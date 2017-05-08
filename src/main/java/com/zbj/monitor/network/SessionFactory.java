package com.zbj.monitor.network;

/**
 * Created by zhouyinyan on 17/5/3.
 *
 *
 *
 */
public interface SessionFactory {

    Session createSession() throws CreateSessionException;

    void recycleSession(Session session) throws RecycleSessionException;
}
