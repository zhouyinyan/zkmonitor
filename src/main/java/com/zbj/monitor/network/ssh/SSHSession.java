package com.zbj.monitor.network.ssh;

import ch.ethz.ssh2.Connection;
import com.sun.org.apache.regexp.internal.RE;
import com.zbj.monitor.network.Session;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class SSHSession implements Session<ch.ethz.ssh2.Session> {

    ch.ethz.ssh2.Session proto;

    Connection connection;

    public SSHSession(ch.ethz.ssh2.Session proto, Connection connection) {
        this.proto = proto;
        this.connection = connection;
    }

    @Override
    public ch.ethz.ssh2.Session getProto() {
        return this.proto;
    }

    public Connection getConnection(){
        return this.connection;
    }
}
