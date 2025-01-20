package com.lingolearn.common.infra;

import com.lingolearn.common.domain.logging.MyLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLoggerImpl implements MyLogger {
    private final Logger logger;

    public MyLoggerImpl(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public MyLoggerImpl(String name) {
        this.logger = LoggerFactory.getLogger(name);
    }

    @Override
    public void trace(String message) {
        logger.trace(message);
    }

    @Override
    public void trace(String message, Object... args) {
        logger.trace(message, args);
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void debug(String message, Object... args) {
        logger.debug(message, args);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void info(String message, Object... args) {
        logger.info(message, args);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

    @Override
    public void warn(String message, Object... args) {
        logger.warn(message, args);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void error(String message, Object... args) {
        logger.error(message, args);
    }

    @Override
    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
