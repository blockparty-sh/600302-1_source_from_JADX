package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$2", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Collect.kt */
public final class FlowKt__CollectKt$collect$2 implements FlowCollector<T> {
    final /* synthetic */ Function2 $action;

    public FlowKt__CollectKt$collect$2(Function2 function2) {
        this.$action = function2;
    }

    @Nullable
    public Object emit(T t, @NotNull Continuation<? super Unit> continuation) {
        return this.$action.invoke(t, continuation);
    }

    @Nullable
    public Object emit$$forInline(Object obj, @NotNull Continuation continuation) {
        InlineMarker.mark(4);
        new FlowKt__CollectKt$collect$2$emit$1(this, continuation);
        InlineMarker.mark(5);
        return this.$action.invoke(obj, continuation);
    }
}
