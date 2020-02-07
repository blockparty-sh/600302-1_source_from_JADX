package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo37405d2 = {"<anonymous>", "", "run", "kotlinx/coroutines/RunnableKt$Runnable$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* renamed from: kotlinx.coroutines.test.TestCoroutineContext$Dispatcher$scheduleResumeAfterDelay$$inlined$Runnable$1 */
/* compiled from: Runnable.kt */
public final class C3273xe7664c50 implements Runnable {
    final /* synthetic */ CancellableContinuation $continuation$inlined;
    final /* synthetic */ Dispatcher this$0;

    public C3273xe7664c50(Dispatcher dispatcher, CancellableContinuation cancellableContinuation) {
        this.this$0 = dispatcher;
        this.$continuation$inlined = cancellableContinuation;
    }

    public final void run() {
        this.$continuation$inlined.resumeUndispatched(this.this$0, Unit.INSTANCE);
    }
}
