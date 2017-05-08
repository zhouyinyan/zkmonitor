package com.zbj.monitor.network;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class CreateSessionException extends RuntimeException {
    public CreateSessionException() {
    }

    public CreateSessionException(String message) {
        super(message);
    }

    public CreateSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateSessionException(Throwable cause) {
        super(cause);
    }

    public CreateSessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
