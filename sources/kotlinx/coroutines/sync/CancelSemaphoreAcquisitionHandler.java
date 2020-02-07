package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancelHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo37405d2 = {"Lkotlinx/coroutines/sync/CancelSemaphoreAcquisitionHandler;", "Lkotlinx/coroutines/CancelHandler;", "semaphore", "Lkotlinx/coroutines/sync/Semaphore;", "segment", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "index", "", "(Lkotlinx/coroutines/sync/Semaphore;Lkotlinx/coroutines/sync/SemaphoreSegment;I)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Semaphore.kt */
final class CancelSemaphoreAcquisitionHandler extends CancelHandler {
    private final int index;
    private final SemaphoreSegment segment;
    private final Semaphore semaphore;

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public CancelSemaphoreAcquisitionHandler(@NotNull Semaphore semaphore2, @NotNull SemaphoreSegment semaphoreSegment, int i) {
        Intrinsics.checkParameterIsNotNull(semaphore2, "semaphore");
        Intrinsics.checkParameterIsNotNull(semaphoreSegment, "segment");
        this.semaphore = semaphore2;
        this.segment = semaphoreSegment;
        this.index = i;
    }

    public void invoke(@Nullable Throwable th) {
        this.segment.cancel(this.index);
        this.semaphore.release();
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CancelSemaphoreAcquisitionHandler[");
        sb.append(this.semaphore);
        String str = ", ";
        sb.append(str);
        sb.append(this.segment);
        sb.append(str);
        sb.append(this.index);
        sb.append(']');
        return sb.toString();
    }
}
