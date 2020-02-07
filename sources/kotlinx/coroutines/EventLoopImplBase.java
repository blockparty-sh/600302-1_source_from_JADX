package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.Delay.DefaultImpls;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\b \u0018\u00002\u00020\u00012\u00020\u0002:\u0003678B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0006\u0010\u0004J\u0017\u0010\t\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bH\u0002¢\u0006\u0004\b\t\u0010\nJ!\u0010\u000e\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u0007j\u0002`\b¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0011\u001a\u00020\u00052\n\u0010\u0010\u001a\u00060\u0007j\u0002`\b¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0014\u001a\u00020\u00132\n\u0010\u0010\u001a\u00060\u0007j\u0002`\bH\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u0019\u0010\u0004J\u000f\u0010\u001a\u001a\u00020\u0005H\u0004¢\u0006\u0004\b\u001a\u0010\u0004J\u0017\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001bH\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010!\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002¢\u0006\u0004\b!\u0010\"J%\u0010&\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00162\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050$H\u0016¢\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u001bH\u0002¢\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020\u0005H\u0014¢\u0006\u0004\b*\u0010\u0004J\u000f\u0010+\u001a\u00020\u0005H\u0002¢\u0006\u0004\b+\u0010\u0004R\u0016\u0010,\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020\u00138T@\u0014X\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0016\u00101\u001a\u00020\u00168T@\u0014X\u0004¢\u0006\u0006\u001a\u0004\b0\u0010\u0018R\u0016\u00105\u001a\u0002028$@$X¤\u0004¢\u0006\u0006\u001a\u0004\b3\u00104¨\u00069"}, mo37405d2 = {"Lkotlinx/coroutines/EventLoopImplBase;", "Lkotlinx/coroutines/EventLoop;", "Lkotlinx/coroutines/Delay;", "<init>", "()V", "", "closeQueue", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dequeue", "()Ljava/lang/Runnable;", "Lkotlin/coroutines/CoroutineContext;", "context", "block", "dispatch", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "task", "enqueue", "(Ljava/lang/Runnable;)V", "", "enqueueImpl", "(Ljava/lang/Runnable;)Z", "", "processNextEvent", "()J", "rescheduleAllDelayed", "resetAll", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "delayedTask", "schedule$kotlinx_coroutines_core", "(Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;)V", "schedule", "", "scheduleImpl", "(Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;)I", "timeMillis", "Lkotlinx/coroutines/CancellableContinuation;", "continuation", "scheduleResumeAfterDelay", "(JLkotlinx/coroutines/CancellableContinuation;)V", "shouldUnpark", "(Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;)Z", "shutdown", "unpark", "isCompleted", "Z", "isEmpty", "()Z", "getNextTime", "nextTime", "Ljava/lang/Thread;", "getThread", "()Ljava/lang/Thread;", "thread", "DelayedResumeTask", "DelayedRunnableTask", "DelayedTask", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: EventLoop.kt */
public abstract class EventLoopImplBase extends EventLoop implements Delay {
    private static final AtomicReferenceFieldUpdater _delayed$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");
    private static final AtomicReferenceFieldUpdater _queue$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");
    private volatile Object _delayed = null;
    private volatile Object _queue = null;
    /* access modifiers changed from: private */
    public volatile boolean isCompleted;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0006H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, mo37405d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedResumeTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "timeMillis", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "", "(Lkotlinx/coroutines/EventLoopImplBase;JLkotlinx/coroutines/CancellableContinuation;)V", "run", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: EventLoop.kt */
    private final class DelayedResumeTask extends DelayedTask {
        private final CancellableContinuation<Unit> cont;
        final /* synthetic */ EventLoopImplBase this$0;

        public DelayedResumeTask(EventLoopImplBase eventLoopImplBase, @NotNull long j, CancellableContinuation<? super Unit> cancellableContinuation) {
            Intrinsics.checkParameterIsNotNull(cancellableContinuation, "cont");
            this.this$0 = eventLoopImplBase;
            super(j);
            this.cont = cancellableContinuation;
            CancellableContinuationKt.disposeOnCancellation(this.cont, this);
        }

        public void run() {
            this.cont.resumeUndispatched(this.this$0, Unit.INSTANCE);
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, mo37405d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedRunnableTask;", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "time", "", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "(JLjava/lang/Runnable;)V", "run", "", "toString", "", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: EventLoop.kt */
    public static final class DelayedRunnableTask extends DelayedTask {
        private final Runnable block;

        public DelayedRunnableTask(long j, @NotNull Runnable runnable) {
            Intrinsics.checkParameterIsNotNull(runnable, "block");
            super(j);
            this.block = runnable;
        }

        public void run() {
            this.block.run();
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append(this.block.toString());
            return sb.toString();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b \u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u00032\u00020\u00042\u00020\u0005B\r\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0000H\u0002J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ\u001c\u0010\u001e\u001a\u00020\u00132\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00000\f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0007J\b\u0010%\u001a\u00020&H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R0\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f2\f\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\f8V@VX\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, mo37405d2 = {"Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "", "Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "timeMillis", "", "(J)V", "_heap", "", "value", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "heap", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "nanoTime", "compareTo", "other", "dispose", "", "rescheduleOnShutdown", "schedule", "delayed", "eventLoop", "Lkotlinx/coroutines/EventLoopImplBase;", "timeToExecute", "", "now", "toString", "", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: EventLoop.kt */
    public static abstract class DelayedTask implements Runnable, Comparable<DelayedTask>, DisposableHandle, ThreadSafeHeapNode {
        private Object _heap;
        private int index = -1;
        @JvmField
        public final long nanoTime;

        public DelayedTask(long j) {
            this.nanoTime = TimeSourceKt.getTimeSource().nanoTime() + EventLoopKt.delayToNanos(j);
        }

        @Nullable
        public ThreadSafeHeap<?> getHeap() {
            Object obj = this._heap;
            if (!(obj instanceof ThreadSafeHeap)) {
                obj = null;
            }
            return (ThreadSafeHeap) obj;
        }

        public void setHeap(@Nullable ThreadSafeHeap<?> threadSafeHeap) {
            if (this._heap != EventLoopKt.DISPOSED_TASK) {
                this._heap = threadSafeHeap;
                return;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int i) {
            this.index = i;
        }

        public int compareTo(@NotNull DelayedTask delayedTask) {
            Intrinsics.checkParameterIsNotNull(delayedTask, "other");
            int i = ((this.nanoTime - delayedTask.nanoTime) > 0 ? 1 : ((this.nanoTime - delayedTask.nanoTime) == 0 ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            return i < 0 ? -1 : 0;
        }

        public final boolean timeToExecute(long j) {
            return j - this.nanoTime >= 0;
        }

        public final synchronized int schedule(@NotNull ThreadSafeHeap<DelayedTask> threadSafeHeap, @NotNull EventLoopImplBase eventLoopImplBase) {
            int i;
            Intrinsics.checkParameterIsNotNull(threadSafeHeap, "delayed");
            Intrinsics.checkParameterIsNotNull(eventLoopImplBase, "eventLoop");
            if (this._heap == EventLoopKt.DISPOSED_TASK) {
                return 2;
            }
            ThreadSafeHeapNode threadSafeHeapNode = this;
            synchronized (threadSafeHeap) {
                if (!eventLoopImplBase.isCompleted) {
                    threadSafeHeap.addImpl(threadSafeHeapNode);
                    i = 1;
                } else {
                    i = 0;
                }
            }
            return i ^ 1;
        }

        public final void rescheduleOnShutdown() {
            DefaultExecutor.INSTANCE.schedule$kotlinx_coroutines_core(this);
        }

        public final synchronized void dispose() {
            Object obj = this._heap;
            if (obj != EventLoopKt.DISPOSED_TASK) {
                if (!(obj instanceof ThreadSafeHeap)) {
                    obj = null;
                }
                ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) obj;
                if (threadSafeHeap != null) {
                    threadSafeHeap.remove(this);
                }
                this._heap = EventLoopKt.DISPOSED_TASK;
            }
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Delayed[nanos=");
            sb.append(this.nanoTime);
            sb.append(']');
            return sb.toString();
        }
    }

    /* access modifiers changed from: protected */
    @NotNull
    public abstract Thread getThread();

    @Nullable
    public Object delay(long j, @NotNull Continuation<? super Unit> continuation) {
        return DefaultImpls.delay(this, j, continuation);
    }

    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        return DefaultImpls.invokeOnTimeout(this, j, runnable);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r0 == kotlinx.coroutines.EventLoopKt.access$getCLOSED_EMPTY$p()) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isEmpty() {
        /*
            r4 = this;
            boolean r0 = r4.isUnconfinedQueueEmpty()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.Object r0 = r4._delayed
            kotlinx.coroutines.internal.ThreadSafeHeap r0 = (kotlinx.coroutines.internal.ThreadSafeHeap) r0
            if (r0 == 0) goto L_0x0015
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0015
            return r1
        L_0x0015:
            java.lang.Object r0 = r4._queue
            r2 = 1
            if (r0 != 0) goto L_0x001c
        L_0x001a:
            r1 = 1
            goto L_0x002e
        L_0x001c:
            boolean r3 = r0 instanceof kotlinx.coroutines.internal.LockFreeTaskQueueCore
            if (r3 == 0) goto L_0x0027
            kotlinx.coroutines.internal.LockFreeTaskQueueCore r0 = (kotlinx.coroutines.internal.LockFreeTaskQueueCore) r0
            boolean r1 = r0.isEmpty()
            goto L_0x002e
        L_0x0027:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.EventLoopKt.CLOSED_EMPTY
            if (r0 != r3) goto L_0x002e
            goto L_0x001a
        L_0x002e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.EventLoopImplBase.isEmpty():boolean");
    }

    /* access modifiers changed from: protected */
    public long getNextTime() {
        if (super.getNextTime() == 0) {
            return 0;
        }
        Object obj = this._queue;
        if (obj != null) {
            if (obj instanceof LockFreeTaskQueueCore) {
                if (!((LockFreeTaskQueueCore) obj).isEmpty()) {
                    return 0;
                }
            } else if (obj == EventLoopKt.CLOSED_EMPTY) {
                return Long.MAX_VALUE;
            } else {
                return 0;
            }
        }
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        if (threadSafeHeap != null) {
            DelayedTask delayedTask = (DelayedTask) threadSafeHeap.peek();
            if (delayedTask != null) {
                return RangesKt.coerceAtLeast(delayedTask.nanoTime - TimeSourceKt.getTimeSource().nanoTime(), 0);
            }
        }
        return Long.MAX_VALUE;
    }

    private final void unpark() {
        Thread thread = getThread();
        if (Thread.currentThread() != thread) {
            TimeSourceKt.getTimeSource().unpark(thread);
        }
    }

    /* access modifiers changed from: protected */
    public void shutdown() {
        ThreadLocalEventLoop.INSTANCE.resetEventLoop$kotlinx_coroutines_core();
        this.isCompleted = true;
        closeQueue();
        do {
        } while (processNextEvent() <= 0);
        rescheduleAllDelayed();
    }

    public void scheduleResumeAfterDelay(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        Intrinsics.checkParameterIsNotNull(cancellableContinuation, "continuation");
        schedule$kotlinx_coroutines_core(new DelayedResumeTask(this, j, cancellableContinuation));
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long processNextEvent() {
        /*
            r7 = this;
            boolean r0 = r7.processUnconfinedEvent()
            if (r0 == 0) goto L_0x000b
            long r0 = r7.getNextTime()
            return r0
        L_0x000b:
            java.lang.Object r0 = r7._delayed
            kotlinx.coroutines.internal.ThreadSafeHeap r0 = (kotlinx.coroutines.internal.ThreadSafeHeap) r0
            if (r0 == 0) goto L_0x004a
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x004a
            kotlinx.coroutines.TimeSource r1 = kotlinx.coroutines.TimeSourceKt.getTimeSource()
            long r1 = r1.nanoTime()
        L_0x001f:
            monitor-enter(r0)
            kotlinx.coroutines.internal.ThreadSafeHeapNode r3 = r0.firstImpl()     // Catch:{ all -> 0x0047 }
            r4 = 0
            if (r3 == 0) goto L_0x0041
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r3 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r3     // Catch:{ all -> 0x0047 }
            boolean r5 = r3.timeToExecute(r1)     // Catch:{ all -> 0x0047 }
            r6 = 0
            if (r5 == 0) goto L_0x0037
            java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch:{ all -> 0x0047 }
            boolean r3 = r7.enqueueImpl(r3)     // Catch:{ all -> 0x0047 }
            goto L_0x0038
        L_0x0037:
            r3 = 0
        L_0x0038:
            if (r3 == 0) goto L_0x003f
            kotlinx.coroutines.internal.ThreadSafeHeapNode r3 = r0.removeAtImpl(r6)     // Catch:{ all -> 0x0047 }
            r4 = r3
        L_0x003f:
            monitor-exit(r0)
            goto L_0x0042
        L_0x0041:
            monitor-exit(r0)
        L_0x0042:
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r4 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r4
            if (r4 == 0) goto L_0x004a
            goto L_0x001f
        L_0x0047:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x004a:
            java.lang.Runnable r0 = r7.dequeue()
            if (r0 == 0) goto L_0x0053
            r0.run()
        L_0x0053:
            long r0 = r7.getNextTime()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.EventLoopImplBase.processNextEvent():long");
    }

    public final void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(runnable, "block");
        enqueue(runnable);
    }

    public final void enqueue(@NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "task");
        if (enqueueImpl(runnable)) {
            unpark();
        } else {
            DefaultExecutor.INSTANCE.enqueue(runnable);
        }
    }

    private final void closeQueue() {
        boolean z = this.isCompleted;
        if (!_Assertions.ENABLED || z) {
            while (true) {
                Object obj = this._queue;
                if (obj == null) {
                    if (_queue$FU.compareAndSet(this, null, EventLoopKt.CLOSED_EMPTY)) {
                        return;
                    }
                } else if (obj instanceof LockFreeTaskQueueCore) {
                    ((LockFreeTaskQueueCore) obj).close();
                    return;
                } else if (obj != EventLoopKt.CLOSED_EMPTY) {
                    LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                    if (obj != null) {
                        lockFreeTaskQueueCore.addLast((Runnable) obj);
                        if (_queue$FU.compareAndSet(this, obj, lockFreeTaskQueueCore)) {
                            return;
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Runnable /* = java.lang.Runnable */");
                    }
                } else {
                    return;
                }
            }
        } else {
            throw new AssertionError("Assertion failed");
        }
    }

    public final void schedule$kotlinx_coroutines_core(@NotNull DelayedTask delayedTask) {
        Intrinsics.checkParameterIsNotNull(delayedTask, "delayedTask");
        int scheduleImpl = scheduleImpl(delayedTask);
        if (scheduleImpl != 0) {
            if (scheduleImpl == 1) {
                DefaultExecutor.INSTANCE.schedule$kotlinx_coroutines_core(delayedTask);
            } else if (scheduleImpl != 2) {
                throw new IllegalStateException("unexpected result".toString());
            }
        } else if (shouldUnpark(delayedTask)) {
            unpark();
        }
    }

    private final boolean shouldUnpark(DelayedTask delayedTask) {
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        return (threadSafeHeap != null ? (DelayedTask) threadSafeHeap.peek() : null) == delayedTask;
    }

    private final int scheduleImpl(DelayedTask delayedTask) {
        if (this.isCompleted) {
            return 1;
        }
        ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
        if (threadSafeHeap == null) {
            EventLoopImplBase eventLoopImplBase = this;
            _delayed$FU.compareAndSet(eventLoopImplBase, null, new ThreadSafeHeap());
            Object obj = eventLoopImplBase._delayed;
            if (obj == null) {
                Intrinsics.throwNpe();
            }
            threadSafeHeap = (ThreadSafeHeap) obj;
        }
        return delayedTask.schedule(threadSafeHeap, this);
    }

    /* access modifiers changed from: protected */
    public final void resetAll() {
        this._queue = null;
        this._delayed = null;
    }

    private final void rescheduleAllDelayed() {
        while (true) {
            ThreadSafeHeap threadSafeHeap = (ThreadSafeHeap) this._delayed;
            if (threadSafeHeap != null) {
                DelayedTask delayedTask = (DelayedTask) threadSafeHeap.removeFirstOrNull();
                if (delayedTask != null) {
                    delayedTask.rescheduleOnShutdown();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private final boolean enqueueImpl(Runnable runnable) {
        while (true) {
            Object obj = this._queue;
            if (this.isCompleted) {
                return false;
            }
            if (obj == null) {
                if (_queue$FU.compareAndSet(this, null, runnable)) {
                    return true;
                }
            } else if (obj instanceof LockFreeTaskQueueCore) {
                if (obj != null) {
                    LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                    int addLast = lockFreeTaskQueueCore.addLast(runnable);
                    if (addLast == 0) {
                        return true;
                    }
                    if (addLast == 1) {
                        _queue$FU.compareAndSet(this, obj, lockFreeTaskQueueCore.next());
                    } else if (addLast == 2) {
                        return false;
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Queue<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> /* = kotlinx.coroutines.internal.LockFreeTaskQueueCore<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> */");
                }
            } else if (obj == EventLoopKt.CLOSED_EMPTY) {
                return false;
            } else {
                LockFreeTaskQueueCore lockFreeTaskQueueCore2 = new LockFreeTaskQueueCore(8, true);
                if (obj != null) {
                    lockFreeTaskQueueCore2.addLast((Runnable) obj);
                    lockFreeTaskQueueCore2.addLast(runnable);
                    if (_queue$FU.compareAndSet(this, obj, lockFreeTaskQueueCore2)) {
                        return true;
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Runnable /* = java.lang.Runnable */");
                }
            }
        }
    }

    private final Runnable dequeue() {
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                return null;
            }
            if (obj instanceof LockFreeTaskQueueCore) {
                if (obj != null) {
                    LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                    Object removeFirstOrNull = lockFreeTaskQueueCore.removeFirstOrNull();
                    if (removeFirstOrNull != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                        return (Runnable) removeFirstOrNull;
                    }
                    _queue$FU.compareAndSet(this, obj, lockFreeTaskQueueCore.next());
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Queue<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> /* = kotlinx.coroutines.internal.LockFreeTaskQueueCore<kotlinx.coroutines.Runnable /* = java.lang.Runnable */> */");
                }
            } else if (obj == EventLoopKt.CLOSED_EMPTY) {
                return null;
            } else {
                if (_queue$FU.compareAndSet(this, obj, null)) {
                    if (obj != null) {
                        return (Runnable) obj;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.Runnable /* = java.lang.Runnable */");
                }
            }
        }
    }
}
