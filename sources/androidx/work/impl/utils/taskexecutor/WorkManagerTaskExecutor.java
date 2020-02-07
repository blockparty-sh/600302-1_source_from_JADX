package androidx.work.impl.utils.taskexecutor;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.work.impl.utils.SerialExecutor;
import java.util.concurrent.Executor;

@RestrictTo({Scope.LIBRARY_GROUP})
public class WorkManagerTaskExecutor implements TaskExecutor {
    private final Executor mBackgroundExecutor;
    private final Executor mMainThreadExecutor = new Executor() {
        public void execute(@NonNull Runnable runnable) {
            WorkManagerTaskExecutor.this.postToMainThread(runnable);
        }
    };
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());

    public WorkManagerTaskExecutor(@NonNull Executor executor) {
        this.mBackgroundExecutor = new SerialExecutor(executor);
    }

    public void postToMainThread(Runnable runnable) {
        this.mMainThreadHandler.post(runnable);
    }

    public Executor getMainThreadExecutor() {
        return this.mMainThreadExecutor;
    }

    public void executeOnBackgroundThread(Runnable runnable) {
        this.mBackgroundExecutor.execute(runnable);
    }

    public Executor getBackgroundExecutor() {
        return this.mBackgroundExecutor;
    }
}
