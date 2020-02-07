package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0000¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"T", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlinx/coroutines/flow/internal/ConcurrentFlowCollector;", "asConcurrentFlowCollector", "(Lkotlinx/coroutines/flow/FlowCollector;)Lkotlinx/coroutines/flow/internal/ConcurrentFlowCollector;", "kotlinx-coroutines-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Concurrent.kt */
public final class ConcurrentKt {
    @NotNull
    public static final <T> ConcurrentFlowCollector<T> asConcurrentFlowCollector(@NotNull FlowCollector<? super T> flowCollector) {
        Intrinsics.checkParameterIsNotNull(flowCollector, "$this$asConcurrentFlowCollector");
        ConcurrentFlowCollector<T> concurrentFlowCollector = (ConcurrentFlowCollector) (!(flowCollector instanceof ConcurrentFlowCollector) ? null : flowCollector);
        return concurrentFlowCollector != null ? concurrentFlowCollector : new SerializingCollector<>(flowCollector);
    }
}
