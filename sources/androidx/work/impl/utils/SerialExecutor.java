package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;

public class SerialExecutor implements Executor {
    private volatile Runnable mActive;
    private final Executor mExecutor;
    private final Object mLock = new Object();
    private final ArrayDeque<Task> mTasks = new ArrayDeque<>();

    static class Task implements Runnable {
        final Runnable mRunnable;
        final SerialExecutor mSerialExecutor;

        Task(@NonNull SerialExecutor serialExecutor, @NonNull Runnable runnable) {
            this.mSerialExecutor = serialExecutor;
            this.mRunnable = runnable;
        }

        public void run() {
            try {
                this.mRunnable.run();
            } finally {
                this.mSerialExecutor.scheduleNext();
            }
        }
    }

    public SerialExecutor(@NonNull Executor executor) {
        this.mExecutor = executor;
    }

    public void execute(@NonNull Runnable runnable) {
        synchronized (this.mLock) {
            this.mTasks.add(new Task(this, runnable));
            if (this.mActive == null) {
                scheduleNext();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void scheduleNext() {
        synchronized (this.mLock) {
            Runnable runnable = (Runnable) this.mTasks.poll();
            this.mActive = runnable;
            if (runnable != null) {
                this.mExecutor.execute(this.mActive);
            }
        }
    }
}
