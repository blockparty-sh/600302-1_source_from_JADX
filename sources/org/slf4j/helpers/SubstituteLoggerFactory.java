package org.slf4j.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.event.SubstituteLoggingEvent;

public class SubstituteLoggerFactory implements ILoggerFactory {
    final LinkedBlockingQueue<SubstituteLoggingEvent> eventQueue = new LinkedBlockingQueue<>();
    final ConcurrentMap<String, SubstituteLogger> loggers = new ConcurrentHashMap();

    public Logger getLogger(String str) {
        SubstituteLogger substituteLogger = (SubstituteLogger) this.loggers.get(str);
        if (substituteLogger != null) {
            return substituteLogger;
        }
        SubstituteLogger substituteLogger2 = new SubstituteLogger(str, this.eventQueue);
        SubstituteLogger substituteLogger3 = (SubstituteLogger) this.loggers.putIfAbsent(str, substituteLogger2);
        return substituteLogger3 != null ? substituteLogger3 : substituteLogger2;
    }

    public List<String> getLoggerNames() {
        return new ArrayList(this.loggers.keySet());
    }

    public List<SubstituteLogger> getLoggers() {
        return new ArrayList(this.loggers.values());
    }

    public LinkedBlockingQueue<SubstituteLoggingEvent> getEventQueue() {
        return this.eventQueue;
    }

    public void clear() {
        this.loggers.clear();
        this.eventQueue.clear();
    }
}
