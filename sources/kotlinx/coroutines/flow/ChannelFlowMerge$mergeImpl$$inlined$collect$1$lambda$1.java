package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, mo37405d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/ChannelFlowMerge$mergeImpl$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Merge.kt */
final class ChannelFlowMerge$mergeImpl$$inlined$collect$1$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow $inner;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f753p$;
    final /* synthetic */ ChannelFlowMerge$mergeImpl$$inlined$collect$1 this$0;

    ChannelFlowMerge$mergeImpl$$inlined$collect$1$lambda$1(Flow flow, Continuation continuation, ChannelFlowMerge$mergeImpl$$inlined$collect$1 channelFlowMerge$mergeImpl$$inlined$collect$1) {
        this.$inner = flow;
        this.this$0 = channelFlowMerge$mergeImpl$$inlined$collect$1;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelFlowMerge$mergeImpl$$inlined$collect$1$lambda$1 channelFlowMerge$mergeImpl$$inlined$collect$1$lambda$1 = new ChannelFlowMerge$mergeImpl$$inlined$collect$1$lambda$1(this.$inner, continuation, this.this$0);
        channelFlowMerge$mergeImpl$$inlined$collect$1$lambda$1.f753p$ = (CoroutineScope) obj;
        return channelFlowMerge$mergeImpl$$inlined$collect$1$lambda$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelFlowMerge$mergeImpl$$inlined$collect$1$lambda$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f753p$;
            Flow flow = this.$inner;
            FlowCollector flowCollector = this.this$0.$collector$inlined;
            this.label = 1;
            if (flow.collect(flowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                this.this$0.$semaphore$inlined.release();
                throw th;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.$semaphore$inlined.release();
        return Unit.INSTANCE;
    }
}
