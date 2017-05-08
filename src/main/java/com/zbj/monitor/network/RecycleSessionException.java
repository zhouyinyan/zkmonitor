package com.zbj.monitor.network;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class RecycleSessionException extends RuntimeException {
    public RecycleSessionException() {
    }

    public RecycleSessionException(String message) {
        super(message);
    }

    public RecycleSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecycleSessionException(Throwable cause) {
        super(cause);
    }

    public RecycleSessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
