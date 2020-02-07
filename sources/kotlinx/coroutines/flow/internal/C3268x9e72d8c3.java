package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000©\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\nR\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b¸\u0006\r"}, mo37405d2 = {"kotlin/coroutines/ContinuationKt$Continuation$1", "Lkotlin/coroutines/Continuation;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "resumeWith", "", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "kotlin-stdlib", "kotlinx/coroutines/flow/internal/ChannelFlowKt$$special$$inlined$Continuation$1", "kotlinx/coroutines/flow/internal/ChannelFlowKt$$special$$inlined$withCoroutineContext$lambda$1"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowKt$withContextUndispatched$$inlined$suspendCoroutineUninterceptedOrReturn$lambda$1 */
/* compiled from: Continuation.kt */
public final class C3268x9e72d8c3 implements Continuation<T> {
    final /* synthetic */ Function2 $block$inlined;
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ Object $countOrElement$inlined;
    final /* synthetic */ CoroutineContext $newContext$inlined;
    final /* synthetic */ Continuation $uCont$inlined;
    final /* synthetic */ Object $value$inlined;

    public C3268x9e72d8c3(CoroutineContext coroutineContext, Continuation continuation, CoroutineContext coroutineContext2, Object obj, Function2 function2, Object obj2) {
        this.$context = coroutineContext;
        this.$uCont$inlined = continuation;
        this.$newContext$inlined = coroutineContext2;
        this.$countOrElement$inlined = obj;
        this.$block$inlined = function2;
        this.$value$inlined = obj2;
    }

    @NotNull
    public CoroutineContext getContext() {
        return this.$context;
    }

    public void resumeWith(@NotNull Object obj) {
        this.$uCont$inlined.resumeWith(obj);
    }
}
