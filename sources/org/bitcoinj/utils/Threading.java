package org.bitcoinj.utils;

import com.google.common.util.concurrent.CycleDetectingLockFactory;
import com.google.common.util.concurrent.CycleDetectingLockFactory.Policies;
import com.google.common.util.concurrent.CycleDetectingLockFactory.Policy;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Uninterruptibles;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bitcoinj.core.C3447Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Threading {
    public static final Executor SAME_THREAD = new Executor() {
        public void execute(@Nonnull Runnable runnable) {
            runnable.run();
        }
    };
    public static ListeningExecutorService THREAD_POOL = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool(new ThreadFactory() {
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("Threading.THREAD_POOL worker");
            thread.setDaemon(true);
            return thread;
        }
    }));
    public static Executor USER_THREAD = new UserThread();
    public static CycleDetectingLockFactory factory;
    private static Policy policy;
    @Nullable
    public static volatile UncaughtExceptionHandler uncaughtExceptionHandler;

    public static class UserThread extends Thread implements Executor {
        public static int WARNING_THRESHOLD = 10000;
        private static final Logger log = LoggerFactory.getLogger(UserThread.class);
        private LinkedBlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

        public UserThread() {
            super("bitcoinj user thread");
            setDaemon(true);
            start();
        }

        public void run() {
            while (true) {
                try {
                    ((Runnable) Uninterruptibles.takeUninterruptibly(this.tasks)).run();
                } catch (Throwable th) {
                    log.warn("Exception in user thread", th);
                    UncaughtExceptionHandler uncaughtExceptionHandler = Threading.uncaughtExceptionHandler;
                    if (uncaughtExceptionHandler != null) {
                        uncaughtExceptionHandler.uncaughtException(this, th);
                    }
                }
            }
        }

        public void execute(Runnable runnable) {
            int size = this.tasks.size();
            if (size == WARNING_THRESHOLD) {
                log.warn("User thread has {} pending tasks, memory exhaustion may occur.\nIf you see this message, check your memory consumption and see if it's problematic or excessively spikey.\nIf it is, check for deadlocked or slow event handlers. If it isn't, try adjusting the constant \nThreading.UserThread.WARNING_THRESHOLD upwards until it's a suitable level for your app, or Integer.MAX_VALUE to disable.", (Object) Integer.valueOf(size));
            }
            Uninterruptibles.putUninterruptibly(this.tasks, runnable);
        }
    }

    public static void waitForUserCode() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        USER_THREAD.execute(new Runnable() {
            public void run() {
                countDownLatch.countDown();
            }
        });
        Uninterruptibles.awaitUninterruptibly(countDownLatch);
    }

    static {
        throwOnLockCycles();
    }

    public static ReentrantLock lock(String str) {
        if (C3447Utils.isAndroidRuntime()) {
            return new ReentrantLock(true);
        }
        return factory.newReentrantLock(str);
    }

    public static void warnOnLockCycles() {
        setPolicy(Policies.WARN);
    }

    public static void throwOnLockCycles() {
        setPolicy(Policies.THROW);
    }

    public static void ignoreLockCycles() {
        setPolicy(Policies.DISABLED);
    }

    public static void setPolicy(Policy policy2) {
        policy = policy2;
        factory = CycleDetectingLockFactory.newInstance(policy2);
    }

    public static Policy getPolicy() {
        return policy;
    }
}
