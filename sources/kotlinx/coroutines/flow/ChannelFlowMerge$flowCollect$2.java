package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.internal.ConcurrentFlowCollector;
import kotlinx.coroutines.flow.internal.ConcurrentKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.ChannelFlowMerge$flowCollect$2", mo38000f = "Merge.kt", mo38001i = {}, mo38002l = {172}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: Merge.kt */
final class ChannelFlowMerge$flowCollect$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector $collector;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f754p$;
    final /* synthetic */ ChannelFlowMerge this$0;

    ChannelFlowMerge$flowCollect$2(ChannelFlowMerge channelFlowMerge, FlowCollector flowCollector, Continuation continuation) {
        this.this$0 = channelFlowMerge;
        this.$collector = flowCollector;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelFlowMerge$flowCollect$2 channelFlowMerge$flowCollect$2 = new ChannelFlowMerge$flowCollect$2(this.this$0, this.$collector, continuation);
        channelFlowMerge$flowCollect$2.f754p$ = (CoroutineScope) obj;
        return channelFlowMerge$flowCollect$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelFlowMerge$flowCollect$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f754p$;
            ChannelFlowMerge channelFlowMerge = this.this$0;
            ConcurrentFlowCollector asConcurrentFlowCollector = ConcurrentKt.asConcurrentFlowCollector(this.$collector);
            this.label = 1;
            if (channelFlowMerge.mergeImpl(coroutineScope, asConcurrentFlowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
