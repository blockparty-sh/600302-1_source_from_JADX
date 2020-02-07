package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$2", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__TransformKt$$special$$inlined$collect$4"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Collect.kt */
public final class FlowKt__TransformKt$filter$$inlined$unsafeFlow$2$lambda$1 implements FlowCollector<T> {
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ FlowKt__TransformKt$filter$$inlined$unsafeFlow$2 this$0;

    public FlowKt__TransformKt$filter$$inlined$unsafeFlow$2$lambda$1(FlowCollector flowCollector, FlowKt__TransformKt$filter$$inlined$unsafeFlow$2 flowKt__TransformKt$filter$$inlined$unsafeFlow$2) {
        this.$this_unsafeFlow$inlined = flowCollector;
        this.this$0 = flowKt__TransformKt$filter$$inlined$unsafeFlow$2;
    }

    @Nullable
    public Object emit(Object obj, @NotNull Continuation continuation) {
        if (((Boolean) this.this$0.$predicate$inlined.invoke(obj, continuation)).booleanValue()) {
            return this.$this_unsafeFlow$inlined.emit(obj, continuation);
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public Object emit$$forInline(Object obj, @NotNull Continuation continuation) {
        InlineMarker.mark(4);
        new ContinuationImpl(this, continuation) {
            int label;
            /* synthetic */ Object result;
            final /* synthetic */ FlowKt__TransformKt$filter$$inlined$unsafeFlow$2$lambda$1 this$0;

            {
                this.this$0 = r1;
            }

            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return this.this$0.emit(null, this);
            }
        };
        InlineMarker.mark(5);
        if (!((Boolean) this.this$0.$predicate$inlined.invoke(obj, continuation)).booleanValue()) {
            return Unit.INSTANCE;
        }
        FlowCollector flowCollector = this.$this_unsafeFlow$inlined;
        InlineMarker.mark(0);
        Object emit = flowCollector.emit(obj, continuation);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        return emit;
    }
}
