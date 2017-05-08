package com.zbj.monitor.collect.hostinfo;

import com.zbj.monitor.collect.GenericCollector;
import com.zbj.monitor.utils.SSHUtils;
import com.zbj.monitor.network.Session;
import com.zbj.monitor.network.ssh.SSHSessionFactory;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class HostNameCollector extends GenericCollector<String, String> {

    private static final String HOSTNAME_CMD = "hostname";

    public HostNameCollector(SSHSessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected String doCollect(String arg, Session<?> session) {
        ch.ethz.ssh2.Session  sess= (ch.ethz.ssh2.Session) session.getProto();
        String res = SSHUtils.execCmd(HOSTNAME_CMD, sess);
        return res;
    }

}
