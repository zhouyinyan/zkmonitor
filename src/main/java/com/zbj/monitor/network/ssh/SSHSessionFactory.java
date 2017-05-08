package com.zbj.monitor.network.ssh;

import ch.ethz.ssh2.Connection;
import com.zbj.monitor.network.CreateSessionException;
import com.zbj.monitor.network.RecycleSessionException;
import com.zbj.monitor.network.Session;
import com.zbj.monitor.network.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Created by zhouyinyan on 17/5/3.
 */
@Component
public class SSHSessionFactory implements SessionFactory {

    private String hostname = "127.0.0.1";
    private String username = "zhouyinyan";
    private String password = "zhouyinyan";

    public SSHSessionFactory(){

    }

    public SSHSessionFactory(String hostname, String username, String password) {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
    }

    @Override
    public Session createSession() {
        ch.ethz.ssh2.Session sess = null;
        Connection conn = null;
        try {
            /* Create a connection instance */
            conn = new Connection(hostname);
            /* Now network */
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false) {
                throw new CreateSessionException("Authentication failed.");
            }
		    /* Create a session */
            sess = conn.openSession();
        } catch (IOException e) {
            e.printStackTrace();
            //todo
        } catch (Exception e){
            e.printStackTrace();
            //todo
        }
        finally {
        }
        return new SSHSession(sess, conn);
    }

    @Override
    public void recycleSession(Session session) throws RecycleSessionException {
        Assert.notNull(session, "recycle session , session cant be null");
        if(!(session instanceof SSHSession)){
            throw new RecycleSessionException("xxx");//todo
        }
        try {
            ((ch.ethz.ssh2.Session)session.getProto()).close();
            (((SSHSession) session).getConnection()).close();
        } catch (Exception e) {
            e.printStackTrace();//todo
        } finally {
        }
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
