package com.lingolearn.common.infra;

import com.lingolearn.common.domain.logging.MyLogger;

public class MyLoggerFactory {
    public static MyLogger getLogger(Class<?> clazz) {
        return new MyLoggerImpl(clazz);
    }

    public static MyLogger getLogger(String name) {
        return new MyLoggerImpl(name);
    }
}
