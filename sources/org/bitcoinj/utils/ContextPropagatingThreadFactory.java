package org.bitcoinj.utils;

import com.google.common.base.Throwables;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;
import org.bitcoinj.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextPropagatingThreadFactory implements ThreadFactory {
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(ContextPropagatingThreadFactory.class);
    private final String name;
    private final int priority;

    public ContextPropagatingThreadFactory(String str, int i) {
        this.name = str;
        this.priority = i;
    }

    public ContextPropagatingThreadFactory(String str) {
        this(str, 5);
    }

    public Thread newThread(final Runnable runnable) {
        final Context context = Context.get();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Context.propagate(context);
                    runnable.run();
                } catch (Exception e) {
                    ContextPropagatingThreadFactory.log.error("Exception in thread", (Throwable) e);
                    Throwables.propagate(e);
                }
            }
        }, this.name);
        thread.setPriority(this.priority);
        thread.setDaemon(true);
        UncaughtExceptionHandler uncaughtExceptionHandler = Threading.uncaughtExceptionHandler;
        if (uncaughtExceptionHandler != null) {
            thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
        return thread;
    }
}
