package com.lingolearn.common.domain.logging;

public interface MyLogger {
    void trace(String message);
    void trace(String message, Object... args);
    void debug(String message);
    void debug(String message, Object... args);
    void info(String message);
    void info(String message, Object... args);
    void warn(String message);
    void warn(String message, Object... args);
    void error(String message);
    void error(String message, Object... args);
    void error(String message, Throwable throwable);
}
