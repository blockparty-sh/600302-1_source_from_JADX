package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.channels.Channel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"<anonymous>", "", "T", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__DelayKt$debounce$2$1$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$1 */
/* compiled from: Delay.kt */
final class C3179x5d4af17d extends SuspendLambda implements Function2<Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Deferred $collector$inlined;
    final /* synthetic */ FlowCollector $downstream$inlined;
    final /* synthetic */ BooleanRef $isDone$inlined;
    final /* synthetic */ ObjectRef $lastValue$inlined;
    final /* synthetic */ Channel $values$inlined;
    int label;
    private Object p$0;
    final /* synthetic */ FlowKt__DelayKt$debounce$2 this$0;

    C3179x5d4af17d(Continuation continuation, FlowKt__DelayKt$debounce$2 flowKt__DelayKt$debounce$2, Channel channel, ObjectRef objectRef, FlowCollector flowCollector, Deferred deferred, BooleanRef booleanRef) {
        this.this$0 = flowKt__DelayKt$debounce$2;
        this.$values$inlined = channel;
        this.$lastValue$inlined = objectRef;
        this.$downstream$inlined = flowCollector;
        this.$collector$inlined = deferred;
        this.$isDone$inlined = booleanRef;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        C3179x5d4af17d flowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$1 = new C3179x5d4af17d(continuation, this.this$0, this.$values$inlined, this.$lastValue$inlined, this.$downstream$inlined, this.$collector$inlined, this.$isDone$inlined);
        flowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$1.p$0 = obj;
        return flowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((C3179x5d4af17d) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$lastValue$inlined.element = this.p$0;
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
