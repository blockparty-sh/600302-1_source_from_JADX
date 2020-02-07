package org.slf4j.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

class TimberAndroidLoggerFactory implements ILoggerFactory {
    static final String ANONYMOUS_TAG = "null";
    private final ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap();

    TimberAndroidLoggerFactory() {
    }

    public Logger getLogger(String str) {
        if (str == null) {
            str = ANONYMOUS_TAG;
        }
        Logger logger = (Logger) this.loggerMap.get(str);
        if (logger != null) {
            return logger;
        }
        TimberAndroidLoggerAdapter timberAndroidLoggerAdapter = new TimberAndroidLoggerAdapter(str);
        Logger logger2 = (Logger) this.loggerMap.putIfAbsent(str, timberAndroidLoggerAdapter);
        return logger2 == null ? timberAndroidLoggerAdapter : logger2;
    }
}
