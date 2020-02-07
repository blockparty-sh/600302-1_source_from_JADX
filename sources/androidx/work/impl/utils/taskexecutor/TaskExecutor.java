package androidx.work.impl.utils.taskexecutor;

import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import java.util.concurrent.Executor;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface TaskExecutor {
    void executeOnBackgroundThread(Runnable runnable);

    Executor getBackgroundExecutor();

    Executor getMainThreadExecutor();

    void postToMainThread(Runnable runnable);
}
