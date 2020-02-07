package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, mo37405d2 = {"Lkotlinx/coroutines/flow/internal/ConcurrentUndispatchedContextCollector;", "T", "Lkotlinx/coroutines/flow/internal/UndispatchedContextCollector;", "Lkotlinx/coroutines/flow/internal/ConcurrentFlowCollector;", "downstream", "emitContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/flow/internal/ConcurrentFlowCollector;Lkotlin/coroutines/CoroutineContext;)V", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ChannelFlow.kt */
final class ConcurrentUndispatchedContextCollector<T> extends UndispatchedContextCollector<T> implements ConcurrentFlowCollector<T> {
    public ConcurrentUndispatchedContextCollector(@NotNull ConcurrentFlowCollector<T> concurrentFlowCollector, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(concurrentFlowCollector, "downstream");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "emitContext");
        super(concurrentFlowCollector, coroutineContext);
    }
}
