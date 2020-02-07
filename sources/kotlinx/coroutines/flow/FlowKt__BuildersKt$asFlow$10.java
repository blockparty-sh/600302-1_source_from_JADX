package kotlinx.coroutines.flow;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$10", mo38000f = "Builders.kt", mo38001i = {0, 0, 0}, mo38002l = {198}, mo38003m = "invokeSuspend", mo38004n = {"$this$forEach$iv", "element$iv", "value"}, mo38005s = {"L$1", "L$3", "J$0"})
/* compiled from: Builders.kt */
final class FlowKt__BuildersKt$asFlow$10 extends SuspendLambda implements Function2<FlowCollector<? super Long>, Continuation<? super Unit>, Object> {
    final /* synthetic */ LongRange $this_asFlow;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private FlowCollector f755p$;

    FlowKt__BuildersKt$asFlow$10(LongRange longRange, Continuation continuation) {
        this.$this_asFlow = longRange;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        FlowKt__BuildersKt$asFlow$10 flowKt__BuildersKt$asFlow$10 = new FlowKt__BuildersKt$asFlow$10(this.$this_asFlow, continuation);
        flowKt__BuildersKt$asFlow$10.f755p$ = (FlowCollector) obj;
        return flowKt__BuildersKt$asFlow$10;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__BuildersKt$asFlow$10) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        FlowKt__BuildersKt$asFlow$10 flowKt__BuildersKt$asFlow$10;
        FlowCollector flowCollector;
        Iterable iterable;
        Iterator it;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector2 = this.f755p$;
            Iterable iterable2 = this.$this_asFlow;
            flowCollector = flowCollector2;
            flowKt__BuildersKt$asFlow$10 = this;
            iterable = iterable2;
            it = iterable2.iterator();
        } else if (i == 1) {
            long j = this.J$0;
            Object obj2 = this.L$3;
            it = (Iterator) this.L$2;
            iterable = (Iterable) this.L$1;
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowKt__BuildersKt$asFlow$10 = this;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            Object next = it.next();
            long longValue = ((Number) next).longValue();
            Long boxLong = Boxing.boxLong(longValue);
            flowKt__BuildersKt$asFlow$10.L$0 = flowCollector;
            flowKt__BuildersKt$asFlow$10.L$1 = iterable;
            flowKt__BuildersKt$asFlow$10.L$2 = it;
            flowKt__BuildersKt$asFlow$10.L$3 = next;
            flowKt__BuildersKt$asFlow$10.J$0 = longValue;
            flowKt__BuildersKt$asFlow$10.label = 1;
            if (flowCollector.emit(boxLong, flowKt__BuildersKt$asFlow$10) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
