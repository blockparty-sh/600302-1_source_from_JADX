package androidx.lifecycle;

import android.annotation.SuppressLint;
import androidx.annotation.AnyThread;
import androidx.annotation.MainThread;
import java.util.ArrayDeque;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u0006H\u0003J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0003J\b\u0010\u000e\u001a\u00020\fH\u0007J\b\u0010\u000f\u001a\u00020\fH\u0007J\b\u0010\u0010\u001a\u00020\fH\u0007J\b\u0010\u0011\u001a\u00020\fH\u0007J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, mo37405d2 = {"Landroidx/lifecycle/DispatchQueue;", "", "()V", "consumer", "Ljava/lang/Runnable;", "finished", "", "paused", "queue", "Ljava/util/Queue;", "canRun", "enqueue", "", "runnable", "finish", "maybeEnqueueConsumer", "pause", "resume", "runOrEnqueue", "lifecycle-runtime-ktx_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DispatchQueue.kt */
public final class DispatchQueue {
    private final Runnable consumer = new DispatchQueue$consumer$1(this);
    private boolean finished;
    private boolean paused = true;
    /* access modifiers changed from: private */
    public final Queue<Runnable> queue = new ArrayDeque();

    @MainThread
    public final void pause() {
        this.paused = true;
    }

    @MainThread
    public final void resume() {
        if (this.paused) {
            if (!this.finished) {
                this.paused = false;
                maybeEnqueueConsumer();
                return;
            }
            throw new IllegalStateException("Cannot resume a finished dispatcher".toString());
        }
    }

    @MainThread
    public final void finish() {
        this.finished = true;
        maybeEnqueueConsumer();
    }

    @MainThread
    public final void maybeEnqueueConsumer() {
        if (!this.queue.isEmpty()) {
            Dispatchers.getMain().dispatch(EmptyCoroutineContext.INSTANCE, this.consumer);
        }
    }

    /* access modifiers changed from: private */
    @MainThread
    public final boolean canRun() {
        return this.finished || !this.paused;
    }

    @SuppressLint({"WrongThread"})
    @AnyThread
    @ExperimentalCoroutinesApi
    public final void runOrEnqueue(@NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "runnable");
        Dispatchers.getMain().getImmediate().dispatch(EmptyCoroutineContext.INSTANCE, new DispatchQueue$runOrEnqueue$1(this, runnable));
    }

    /* access modifiers changed from: private */
    @MainThread
    public final void enqueue(Runnable runnable) {
        if (this.queue.offer(runnable)) {
            maybeEnqueueConsumer();
            return;
        }
        throw new IllegalStateException("cannot enqueue any more runnables".toString());
    }
}
