package p015io.grpc;

import com.google.common.base.Preconditions;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.ThreadSafe;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/4984")
@ThreadSafe
/* renamed from: io.grpc.SynchronizationContext */
public final class SynchronizationContext implements Executor {
    private final AtomicReference<Thread> drainingThread = new AtomicReference<>();
    private final Queue<Runnable> queue = new ConcurrentLinkedQueue();
    private final UncaughtExceptionHandler uncaughtExceptionHandler;

    /* renamed from: io.grpc.SynchronizationContext$ManagedRunnable */
    private static class ManagedRunnable implements Runnable {
        boolean hasStarted;
        boolean isCancelled;
        final Runnable task;

        ManagedRunnable(Runnable runnable) {
            this.task = (Runnable) Preconditions.checkNotNull(runnable, "task");
        }

        public void run() {
            if (!this.isCancelled) {
                this.hasStarted = true;
                this.task.run();
            }
        }
    }

    /* renamed from: io.grpc.SynchronizationContext$ScheduledHandle */
    public static final class ScheduledHandle {
        private final ScheduledFuture<?> future;
        private final ManagedRunnable runnable;

        private ScheduledHandle(ManagedRunnable managedRunnable, ScheduledFuture<?> scheduledFuture) {
            this.runnable = (ManagedRunnable) Preconditions.checkNotNull(managedRunnable, "runnable");
            this.future = (ScheduledFuture) Preconditions.checkNotNull(scheduledFuture, "future");
        }

        public void cancel() {
            this.runnable.isCancelled = true;
            this.future.cancel(false);
        }

        public boolean isPending() {
            return !this.runnable.hasStarted && !this.runnable.isCancelled;
        }
    }

    public SynchronizationContext(UncaughtExceptionHandler uncaughtExceptionHandler2) {
        this.uncaughtExceptionHandler = (UncaughtExceptionHandler) Preconditions.checkNotNull(uncaughtExceptionHandler2, "uncaughtExceptionHandler");
    }

    public final void drain() {
        while (this.drainingThread.compareAndSet(null, Thread.currentThread())) {
            while (true) {
                try {
                    Runnable runnable = (Runnable) this.queue.poll();
                    if (runnable != null) {
                        runnable.run();
                    } else {
                        this.drainingThread.set(null);
                        if (this.queue.isEmpty()) {
                            return;
                        }
                    }
                } catch (Throwable th) {
                    this.drainingThread.set(null);
                    throw th;
                }
            }
        }
    }

    public final void executeLater(Runnable runnable) {
        this.queue.add(Preconditions.checkNotNull(runnable, "runnable is null"));
    }

    public final void execute(Runnable runnable) {
        executeLater(runnable);
        drain();
    }

    public void throwIfNotInThisSynchronizationContext() {
        Preconditions.checkState(Thread.currentThread() == this.drainingThread.get(), "Not called from the SynchronizationContext");
    }

    public final ScheduledHandle schedule(final Runnable runnable, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        final ManagedRunnable managedRunnable = new ManagedRunnable(runnable);
        return new ScheduledHandle(managedRunnable, scheduledExecutorService.schedule(new Runnable() {
            public void run() {
                SynchronizationContext.this.execute(managedRunnable);
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(runnable.toString());
                sb.append("(scheduled in SynchronizationContext)");
                return sb.toString();
            }
        }, j, timeUnit));
    }
}
