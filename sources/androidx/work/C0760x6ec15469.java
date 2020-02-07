package androidx.work;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Result.Companion;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, mo37405d2 = {"<anonymous>", "", "R", "run", "androidx/work/ListenableFutureKt$await$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* renamed from: androidx.work.ListenableFutureKt$await$$inlined$suspendCancellableCoroutine$lambda$2 */
/* compiled from: ListenableFuture.kt */
public final class C0760x6ec15469 implements Runnable {
    final /* synthetic */ CancellableContinuation $cancellableContinuation;
    final /* synthetic */ ListenableFuture $this_await$inlined;

    public C0760x6ec15469(CancellableContinuation cancellableContinuation, ListenableFuture listenableFuture) {
        this.$cancellableContinuation = cancellableContinuation;
        this.$this_await$inlined = listenableFuture;
    }

    public final void run() {
        try {
            Continuation continuation = this.$cancellableContinuation;
            Object obj = this.$this_await$inlined.get();
            Companion companion = Result.Companion;
            continuation.resumeWith(Result.m479constructorimpl(obj));
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause == null) {
                cause = th;
            }
            if (th instanceof CancellationException) {
                this.$cancellableContinuation.cancel(cause);
                return;
            }
            Continuation continuation2 = this.$cancellableContinuation;
            Companion companion2 = Result.Companion;
            continuation2.resumeWith(Result.m479constructorimpl(ResultKt.createFailure(cause)));
        }
    }
}
