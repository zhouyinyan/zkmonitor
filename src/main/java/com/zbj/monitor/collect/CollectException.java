package com.zbj.monitor.collect;

/**
 * Created by zhouyinyan on 17/5/3.
 */
public class CollectException extends RuntimeException {
    public CollectException() {
    }

    public CollectException(String message) {
        super(message);
    }

    public CollectException(String message, Throwable cause) {
        super(message, cause);
    }

    public CollectException(Throwable cause) {
        super(cause);
    }

    public CollectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
