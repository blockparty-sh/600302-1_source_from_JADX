package org.bitcoinj.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DaemonThreadFactory implements ThreadFactory {
    @Nullable
    private final String name;

    public DaemonThreadFactory(@Nullable String str) {
        this.name = str;
    }

    public DaemonThreadFactory() {
        this(null);
    }

    public Thread newThread(@Nonnull Runnable runnable) {
        Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
        newThread.setDaemon(true);
        String str = this.name;
        if (str != null) {
            newThread.setName(str);
        }
        return newThread;
    }
}
