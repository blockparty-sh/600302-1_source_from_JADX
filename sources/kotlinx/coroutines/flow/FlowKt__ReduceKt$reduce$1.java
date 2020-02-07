package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u0002H\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00042F\u0010\u0005\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000bH@ø\u0001\u0000"}, mo37405d2 = {"reduce", "", "S", "T", "Lkotlinx/coroutines/flow/Flow;", "operation", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "accumulator", "value", "Lkotlin/coroutines/Continuation;", "continuation"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", mo38000f = "Reduce.kt", mo38001i = {0, 0, 0, 0}, mo38002l = {128}, mo38003m = "reduce", mo38004n = {"$this$reduce", "operation", "accumulator", "$this$collect$iv"}, mo38005s = {"L$0", "L$1", "L$2", "L$3"})
/* compiled from: Reduce.kt */
final class FlowKt__ReduceKt$reduce$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    FlowKt__ReduceKt$reduce$1(Continuation continuation) {
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt.reduce(null, null, this);
    }
}
