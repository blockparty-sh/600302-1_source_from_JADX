package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo37405d2 = {"Lkotlinx/coroutines/DisposeOnCompletion;", "Lkotlinx/coroutines/JobNode;", "Lkotlinx/coroutines/Job;", "job", "handle", "Lkotlinx/coroutines/DisposableHandle;", "(Lkotlinx/coroutines/Job;Lkotlinx/coroutines/DisposableHandle;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: JobSupport.kt */
public final class DisposeOnCompletion extends JobNode<Job> {
    private final DisposableHandle handle;

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public DisposeOnCompletion(@NotNull Job job, @NotNull DisposableHandle disposableHandle) {
        Intrinsics.checkParameterIsNotNull(job, "job");
        Intrinsics.checkParameterIsNotNull(disposableHandle, "handle");
        super(job);
        this.handle = disposableHandle;
    }

    public void invoke(@Nullable Throwable th) {
        this.handle.dispose();
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DisposeOnCompletion[");
        sb.append(this.handle);
        sb.append(']');
        return sb.toString();
    }
}
