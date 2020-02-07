package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ChannelCoroutine;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.FlowKt__ZipKt$asFairChannel$1", mo38000f = "Zip.kt", mo38001i = {0, 0}, mo38002l = {256}, mo38003m = "invokeSuspend", mo38004n = {"channel", "$this$collect$iv"}, mo38005s = {"L$0", "L$1"})
/* compiled from: Zip.kt */
final class FlowKt__ZipKt$asFairChannel$1 extends SuspendLambda implements Function2<ProducerScope<? super Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow $flow;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private ProducerScope f772p$;

    FlowKt__ZipKt$asFairChannel$1(Flow flow, Continuation continuation) {
        this.$flow = flow;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        FlowKt__ZipKt$asFairChannel$1 flowKt__ZipKt$asFairChannel$1 = new FlowKt__ZipKt$asFairChannel$1(this.$flow, continuation);
        flowKt__ZipKt$asFairChannel$1.f772p$ = (ProducerScope) obj;
        return flowKt__ZipKt$asFairChannel$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__ZipKt$asFairChannel$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SendChannel channel = this.f772p$.getChannel();
            if (channel != null) {
                ChannelCoroutine channelCoroutine = (ChannelCoroutine) channel;
                Flow flow = this.$flow;
                FlowCollector flowKt__ZipKt$asFairChannel$1$invokeSuspend$$inlined$collect$1 = new FlowKt__ZipKt$asFairChannel$1$invokeSuspend$$inlined$collect$1(channelCoroutine);
                this.L$0 = channelCoroutine;
                this.L$1 = flow;
                this.label = 1;
                if (flow.collect(flowKt__ZipKt$asFairChannel$1$invokeSuspend$$inlined$collect$1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelCoroutine<kotlin.Any>");
            }
        } else if (i == 1) {
            Flow flow2 = (Flow) this.L$1;
            ChannelCoroutine channelCoroutine2 = (ChannelCoroutine) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
