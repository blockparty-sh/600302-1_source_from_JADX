package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.C2916Key;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandler;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006\u0000"}, mo37405d2 = {"kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: kotlinx.coroutines.test.TestCoroutineContext$$special$$inlined$CoroutineExceptionHandler$1 */
/* compiled from: CoroutineExceptionHandler.kt */
public final class C3272x96bc1e17 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    final /* synthetic */ TestCoroutineContext this$0;

    public C3272x96bc1e17(C2916Key key, TestCoroutineContext testCoroutineContext) {
        this.this$0 = testCoroutineContext;
        super(key);
    }

    public void handleException(@NotNull CoroutineContext coroutineContext, @NotNull Throwable th) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(th, "exception");
        this.this$0.uncaughtExceptions.add(th);
    }
}
