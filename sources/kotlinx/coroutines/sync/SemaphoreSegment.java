package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Segment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ,\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\b¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\u0007H\b¢\u0006\u0004\b\u0012\u0010\u0013J2\u0010\u0016\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\u00072\u0016\u0010\u0015\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0014H\b¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001d\u001a\u00020\u000f8V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, mo37405d2 = {"Lkotlinx/coroutines/sync/SemaphoreSegment;", "Lkotlinx/coroutines/internal/Segment;", "", "id", "prev", "<init>", "(JLkotlinx/coroutines/sync/SemaphoreSegment;)V", "", "index", "", "cancel", "(I)V", "", "expected", "value", "", "cas", "(ILjava/lang/Object;Ljava/lang/Object;)Z", "get", "(I)Ljava/lang/Object;", "Lkotlin/Function1;", "function", "getAndUpdate", "(ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "", "toString", "()Ljava/lang/String;", "getRemoved", "()Z", "removed", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Semaphore.kt */
final class SemaphoreSegment extends Segment<SemaphoreSegment> {
    private static final AtomicIntegerFieldUpdater cancelledSlots$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreSegment.class, "cancelledSlots");
    AtomicReferenceArray acquirers = new AtomicReferenceArray(SemaphoreKt.SEGMENT_SIZE);
    private volatile int cancelledSlots = 0;

    public SemaphoreSegment(long j, @Nullable SemaphoreSegment semaphoreSegment) {
        super(j, semaphoreSegment);
    }

    @Nullable
    public final Object get(int i) {
        return this.acquirers.get(i);
    }

    public final boolean cas(int i, @Nullable Object obj, @Nullable Object obj2) {
        return this.acquirers.compareAndSet(i, obj, obj2);
    }

    @Nullable
    public final Object getAndUpdate(int i, @NotNull Function1<Object, ? extends Object> function1) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(function1, "function");
        do {
            obj = this.acquirers.get(i);
        } while (!this.acquirers.compareAndSet(i, obj, function1.invoke(obj)));
        return obj;
    }

    public boolean getRemoved() {
        return this.cancelledSlots == SemaphoreKt.SEGMENT_SIZE;
    }

    public final void cancel(int i) {
        this.acquirers.set(i, SemaphoreKt.CANCELLED);
        if (cancelledSlots$FU.incrementAndGet(this) == SemaphoreKt.SEGMENT_SIZE) {
            remove();
        }
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SemaphoreSegment[id=");
        sb.append(getId());
        sb.append(", hashCode=");
        sb.append(hashCode());
        sb.append(']');
        return sb.toString();
    }
}
