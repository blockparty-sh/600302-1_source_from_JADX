package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref.BooleanRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Builders.kt */
public final class FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1 implements Flow<T> {
    final /* synthetic */ Function2 $predicate$inlined;
    final /* synthetic */ Flow $this_dropWhile$inlined;

    public FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1(Flow flow, Function2 function2) {
        this.$this_dropWhile$inlined = flow;
        this.$predicate$inlined = function2;
    }

    @Nullable
    public Object collect(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
        BooleanRef booleanRef = new BooleanRef();
        booleanRef.element = false;
        return this.$this_dropWhile$inlined.collect(new FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1(flowCollector, booleanRef, this), continuation);
    }
}
