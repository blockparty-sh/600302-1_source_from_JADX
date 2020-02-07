package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexed$1", mo38000f = "Channels.common.kt", mo38001i = {0, 1, 1, 2, 2}, mo38002l = {730, 731, 731}, mo38003m = "invokeSuspend", mo38004n = {"index", "index", "e", "index", "e"}, mo38005s = {"I$0", "I$0", "L$1", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$filterIndexed$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3 $predicate;
    final /* synthetic */ ReceiveChannel $this_filterIndexed;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f743p$;

    ChannelsKt__Channels_commonKt$filterIndexed$1(ReceiveChannel receiveChannel, Function3 function3, Continuation continuation) {
        this.$this_filterIndexed = receiveChannel;
        this.$predicate = function3;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$filterIndexed$1 channelsKt__Channels_commonKt$filterIndexed$1 = new ChannelsKt__Channels_commonKt$filterIndexed$1(this.$this_filterIndexed, this.$predicate, continuation);
        channelsKt__Channels_commonKt$filterIndexed$1.f743p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$filterIndexed$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$filterIndexed$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x006c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a0  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x004e
            if (r1 == r4) goto L_0x003e
            if (r1 == r3) goto L_0x0029
            if (r1 != r2) goto L_0x0021
            java.lang.Object r1 = r11.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r11.L$1
            int r5 = r11.I$0
            java.lang.Object r6 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x005d
        L_0x0021:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0029:
            java.lang.Object r1 = r11.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r11.L$1
            int r6 = r11.I$0
            java.lang.Object r7 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            kotlin.ResultKt.throwOnFailure(r12)
            r8 = r5
            r5 = r6
            r6 = r7
            r7 = r0
            r0 = r11
            goto L_0x0098
        L_0x003e:
            java.lang.Object r1 = r11.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r5 = r11.I$0
            java.lang.Object r6 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.throwOnFailure(r12)
            r7 = r0
            r0 = r11
            goto L_0x0071
        L_0x004e:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.channels.ProducerScope r12 = r11.f743p$
            r1 = 0
            kotlinx.coroutines.channels.ReceiveChannel r5 = r11.$this_filterIndexed
            kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
            r6 = r12
            r1 = r5
            r5 = 0
        L_0x005d:
            r12 = r11
        L_0x005e:
            r12.L$0 = r6
            r12.I$0 = r5
            r12.L$1 = r1
            r12.label = r4
            java.lang.Object r7 = r1.hasNext(r12)
            if (r7 != r0) goto L_0x006d
            return r0
        L_0x006d:
            r10 = r0
            r0 = r12
            r12 = r7
            r7 = r10
        L_0x0071:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x00b4
            java.lang.Object r12 = r1.next()
            kotlin.jvm.functions.Function3 r8 = r0.$predicate
            java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            int r5 = r5 + r4
            r0.L$0 = r6
            r0.I$0 = r5
            r0.L$1 = r12
            r0.L$2 = r1
            r0.label = r3
            java.lang.Object r8 = r8.invoke(r9, r12, r0)
            if (r8 != r7) goto L_0x0095
            return r7
        L_0x0095:
            r10 = r8
            r8 = r12
            r12 = r10
        L_0x0098:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x00b1
            r0.L$0 = r6
            r0.I$0 = r5
            r0.L$1 = r8
            r0.L$2 = r1
            r0.label = r2
            java.lang.Object r12 = r6.send(r8, r0)
            if (r12 != r7) goto L_0x00b1
            return r7
        L_0x00b1:
            r12 = r0
            r0 = r7
            goto L_0x005e
        L_0x00b4:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filterIndexed$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
