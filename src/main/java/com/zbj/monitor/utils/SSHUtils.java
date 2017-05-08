package com.zbj.monitor.utils;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.zbj.monitor.collect.CollectException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.zbj.monitor.utils.Constants.LF;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class SSHUtils {

    public static String execCmd(String cmd, Session sess) throws CollectException {
        StringBuffer stringBuffer = null;
        try {
            sess.execCommand(cmd);

            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));

            stringBuffer = new StringBuffer();
            while (true) {
                String line = stdoutReader.readLine();
                if (line == null) {
                    break;
                }
                stringBuffer.append(line)
                            .append(LF);
            }

            /* close the buffer reader */
            stdoutReader.close();
            stdout.close();
        } catch (IOException e) {
            e.printStackTrace(); //TODO
        } finally {
        }

        return stringBuffer.toString();
    }
}
