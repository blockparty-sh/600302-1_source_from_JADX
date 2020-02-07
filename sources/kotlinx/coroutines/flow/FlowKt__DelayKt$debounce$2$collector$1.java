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
import kotlinx.coroutines.channels.Channel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$collector$1", mo38000f = "Delay.kt", mo38001i = {0}, mo38002l = {162}, mo38003m = "invokeSuspend", mo38004n = {"$this$collect$iv"}, mo38005s = {"L$0"})
/* compiled from: Delay.kt */
final class FlowKt__DelayKt$debounce$2$collector$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Channel $values;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f759p$;
    final /* synthetic */ FlowKt__DelayKt$debounce$2 this$0;

    FlowKt__DelayKt$debounce$2$collector$1(FlowKt__DelayKt$debounce$2 flowKt__DelayKt$debounce$2, Channel channel, Continuation continuation) {
        this.this$0 = flowKt__DelayKt$debounce$2;
        this.$values = channel;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        FlowKt__DelayKt$debounce$2$collector$1 flowKt__DelayKt$debounce$2$collector$1 = new FlowKt__DelayKt$debounce$2$collector$1(this.this$0, this.$values, continuation);
        flowKt__DelayKt$debounce$2$collector$1.f759p$ = (CoroutineScope) obj;
        return flowKt__DelayKt$debounce$2$collector$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__DelayKt$debounce$2$collector$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f759p$;
            Flow flow = this.this$0.$this_debounce;
            FlowCollector flowKt__DelayKt$debounce$2$collector$1$invokeSuspend$$inlined$collect$1 = new C3177x947368c2(this);
            this.L$0 = flow;
            this.label = 1;
            if (flow.collect(flowKt__DelayKt$debounce$2$collector$1$invokeSuspend$$inlined$collect$1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            Flow flow2 = (Flow) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
