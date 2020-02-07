package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.internal.SegmentQueue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\n\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000bJ!\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0014\u0010\u0013J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0016\u0010\u001a\u001a\u00020\u00048V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001b\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, mo37405d2 = {"Lkotlinx/coroutines/sync/SemaphoreImpl;", "Lkotlinx/coroutines/sync/Semaphore;", "Lkotlinx/coroutines/internal/SegmentQueue;", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "", "permits", "acquiredPermits", "<init>", "(II)V", "", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addToQueueAndSuspend", "", "id", "prev", "newSegment", "(JLkotlinx/coroutines/sync/SemaphoreSegment;)Lkotlinx/coroutines/sync/SemaphoreSegment;", "release", "()V", "resumeNextFromQueue", "", "tryAcquire", "()Z", "getAvailablePermits", "()I", "availablePermits", "I", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Semaphore.kt */
final class SemaphoreImpl extends SegmentQueue<SemaphoreSegment> implements Semaphore {
    private static final AtomicIntegerFieldUpdater _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");
    private static final AtomicLongFieldUpdater deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");
    static final AtomicLongFieldUpdater enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");
    private volatile int _availablePermits;
    private volatile long deqIdx;
    volatile long enqIdx;
    private final int permits;

    public static final /* synthetic */ SemaphoreSegment access$getSegment(SemaphoreImpl semaphoreImpl, SemaphoreSegment semaphoreSegment, long j) {
        return (SemaphoreSegment) semaphoreImpl.getSegment(semaphoreSegment, j);
    }

    public static final /* synthetic */ SemaphoreSegment access$getTail$p(SemaphoreImpl semaphoreImpl) {
        return (SemaphoreSegment) semaphoreImpl.getTail();
    }

    public SemaphoreImpl(int i, int i2) {
        this.permits = i;
        boolean z = true;
        if (this.permits > 0) {
            int i3 = this.permits;
            if (i2 < 0 || i3 < i2) {
                z = false;
            }
            if (z) {
                this._availablePermits = this.permits;
                this.enqIdx = 0;
                this.deqIdx = 0;
                return;
            }
            throw new IllegalArgumentException("The number of acquired permits should be in 0..permits".toString());
        }
        throw new IllegalArgumentException("Semaphore should have at least 1 permit".toString());
    }

    @NotNull
    public SemaphoreSegment newSegment(long j, @Nullable SemaphoreSegment semaphoreSegment) {
        return new SemaphoreSegment(j, semaphoreSegment);
    }

    public int getAvailablePermits() {
        return Math.max(this._availablePermits, 0);
    }

    @Nullable
    public Object acquire(@NotNull Continuation<? super Unit> continuation) {
        if (_availablePermits$FU.getAndDecrement(this) > 0) {
            return Unit.INSTANCE;
        }
        return addToQueueAndSuspend(continuation);
    }

    private final void resumeNextFromQueue() {
        Object obj;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) getHead();
        long andIncrement = deqIdx$FU.getAndIncrement(this);
        SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) getSegmentAndMoveHead(semaphoreSegment, andIncrement / ((long) SemaphoreKt.SEGMENT_SIZE));
        if (semaphoreSegment2 != null) {
            int access$getSEGMENT_SIZE$p = (int) (andIncrement % ((long) SemaphoreKt.SEGMENT_SIZE));
            do {
                obj = semaphoreSegment2.acquirers.get(access$getSEGMENT_SIZE$p);
                if (obj != SemaphoreKt.CANCELLED) {
                } else {
                    return;
                }
            } while (!semaphoreSegment2.acquirers.compareAndSet(access$getSEGMENT_SIZE$p, obj, SemaphoreKt.RESUMED));
            if (obj != null) {
                Continuation continuation = (CancellableContinuation) obj;
                Unit unit = Unit.INSTANCE;
                Companion companion = Result.Companion;
                continuation.resumeWith(Result.m479constructorimpl(unit));
            }
        }
    }

    public boolean tryAcquire() {
        int i;
        do {
            i = this._availablePermits;
            if (i <= 0) {
                return false;
            }
        } while (!_availablePermits$FU.compareAndSet(this, i, i - 1));
        return true;
    }

    public void release() {
        int i;
        do {
            i = this._availablePermits;
            if (!(i < this.permits)) {
                throw new IllegalStateException("The number of acquired permits cannot be greater than `permits`".toString());
            }
        } while (!_availablePermits$FU.compareAndSet(this, i, i + 1));
        if (i < 0) {
            resumeNextFromQueue();
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final /* synthetic */ Object addToQueueAndSuspend(@NotNull Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 0);
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        SemaphoreSegment access$getTail$p = access$getTail$p(this);
        long andIncrement = enqIdx$FU.getAndIncrement(this);
        SemaphoreSegment access$getSegment = access$getSegment(this, access$getTail$p, andIncrement / ((long) SemaphoreKt.SEGMENT_SIZE));
        int access$getSEGMENT_SIZE$p = (int) (andIncrement % ((long) SemaphoreKt.SEGMENT_SIZE));
        if (access$getSegment == null || access$getSegment.acquirers.get(access$getSEGMENT_SIZE$p) == SemaphoreKt.RESUMED || !access$getSegment.acquirers.compareAndSet(access$getSEGMENT_SIZE$p, null, cancellableContinuation)) {
            Continuation continuation2 = cancellableContinuation;
            Unit unit = Unit.INSTANCE;
            Companion companion = Result.Companion;
            continuation2.resumeWith(Result.m479constructorimpl(unit));
        } else {
            cancellableContinuation.invokeOnCancellation(new CancelSemaphoreAcquisitionHandler(this, access$getSegment, access$getSEGMENT_SIZE$p));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
