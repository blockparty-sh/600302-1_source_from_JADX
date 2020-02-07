package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$flatMap$1", mo38000f = "Channels.common.kt", mo38001i = {1, 2}, mo38002l = {1247, 1248, 1248}, mo38003m = "invokeSuspend", mo38004n = {"e", "e"}, mo38005s = {"L$1", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$flatMap$1 extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_flatMap;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f744p$;

    ChannelsKt__Channels_commonKt$flatMap$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        this.$this_flatMap = receiveChannel;
        this.$transform = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$flatMap$1 channelsKt__Channels_commonKt$flatMap$1 = new ChannelsKt__Channels_commonKt$flatMap$1(this.$this_flatMap, this.$transform, continuation);
        channelsKt__Channels_commonKt$flatMap$1.f744p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$flatMap$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$flatMap$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x009d  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0049
            if (r1 == r4) goto L_0x003b
            if (r1 == r3) goto L_0x0027
            if (r1 != r2) goto L_0x001f
            java.lang.Object r1 = r10.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r10.L$1
            java.lang.Object r5 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0055
        L_0x001f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0027:
            java.lang.Object r1 = r10.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r10.L$1
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r0
            r0 = r10
            r9 = r6
            r6 = r1
            r1 = r5
            r5 = r9
            goto L_0x0089
        L_0x003b:
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r11)
            r6 = r0
            r0 = r10
            goto L_0x0067
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlinx.coroutines.channels.ProducerScope r11 = r10.f744p$
            kotlinx.coroutines.channels.ReceiveChannel r1 = r10.$this_flatMap
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            r5 = r11
        L_0x0055:
            r11 = r10
        L_0x0056:
            r11.L$0 = r5
            r11.L$1 = r1
            r11.label = r4
            java.lang.Object r6 = r1.hasNext(r11)
            if (r6 != r0) goto L_0x0063
            return r0
        L_0x0063:
            r9 = r0
            r0 = r11
            r11 = r6
            r6 = r9
        L_0x0067:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00a1
            java.lang.Object r11 = r1.next()
            kotlin.jvm.functions.Function2 r7 = r0.$transform
            r0.L$0 = r5
            r0.L$1 = r11
            r0.L$2 = r1
            r0.label = r3
            java.lang.Object r7 = r7.invoke(r11, r0)
            if (r7 != r6) goto L_0x0084
            return r6
        L_0x0084:
            r9 = r1
            r1 = r11
            r11 = r7
            r7 = r6
            r6 = r9
        L_0x0089:
            kotlinx.coroutines.channels.ReceiveChannel r11 = (kotlinx.coroutines.channels.ReceiveChannel) r11
            r8 = r5
            kotlinx.coroutines.channels.SendChannel r8 = (kotlinx.coroutines.channels.SendChannel) r8
            r0.L$0 = r5
            r0.L$1 = r1
            r0.L$2 = r6
            r0.label = r2
            java.lang.Object r11 = kotlinx.coroutines.channels.ChannelsKt.toChannel(r11, r8, r0)
            if (r11 != r7) goto L_0x009d
            return r7
        L_0x009d:
            r11 = r0
            r1 = r6
            r0 = r7
            goto L_0x0056
        L_0x00a1:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$flatMap$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
