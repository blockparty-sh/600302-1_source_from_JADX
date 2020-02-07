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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$take$1", mo38000f = "Channels.common.kt", mo38001i = {0, 1, 1}, mo38002l = {950, 951}, mo38003m = "invokeSuspend", mo38004n = {"remaining", "remaining", "e"}, mo38005s = {"I$0", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$take$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {

    /* renamed from: $n */
    final /* synthetic */ int f747$n;
    final /* synthetic */ ReceiveChannel $this_take;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f748p$;

    ChannelsKt__Channels_commonKt$take$1(ReceiveChannel receiveChannel, int i, Continuation continuation) {
        this.$this_take = receiveChannel;
        this.f747$n = i;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$take$1 channelsKt__Channels_commonKt$take$1 = new ChannelsKt__Channels_commonKt$take$1(this.$this_take, this.f747$n, continuation);
        channelsKt__Channels_commonKt$take$1.f748p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$take$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$take$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0097  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x003b
            if (r1 == r3) goto L_0x002b
            if (r1 != r2) goto L_0x0023
            java.lang.Object r1 = r9.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r9.L$1
            int r4 = r9.I$0
            java.lang.Object r5 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r9
            r7 = r5
            r5 = r0
            r0 = r7
            goto L_0x0090
        L_0x0023:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x002b:
            java.lang.Object r1 = r9.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r4 = r9.I$0
            java.lang.Object r5 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r10)
            r6 = r0
            r0 = r9
            goto L_0x0070
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.channels.ProducerScope r10 = r9.f748p$
            int r1 = r9.f747$n
            if (r1 != 0) goto L_0x0047
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x0047:
            if (r1 < 0) goto L_0x004b
            r1 = 1
            goto L_0x004c
        L_0x004b:
            r1 = 0
        L_0x004c:
            if (r1 == 0) goto L_0x009e
            int r1 = r9.f747$n
            kotlinx.coroutines.channels.ReceiveChannel r4 = r9.$this_take
            kotlinx.coroutines.channels.ChannelIterator r4 = r4.iterator()
            r5 = r0
            r0 = r10
            r10 = r9
        L_0x0059:
            r10.L$0 = r0
            r10.I$0 = r1
            r10.L$1 = r4
            r10.label = r3
            java.lang.Object r6 = r4.hasNext(r10)
            if (r6 != r5) goto L_0x0068
            return r5
        L_0x0068:
            r7 = r0
            r0 = r10
            r10 = r6
            r6 = r5
            r5 = r7
            r8 = r4
            r4 = r1
            r1 = r8
        L_0x0070:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x009b
            java.lang.Object r10 = r1.next()
            r0.L$0 = r5
            r0.I$0 = r4
            r0.L$1 = r10
            r0.L$2 = r1
            r0.label = r2
            java.lang.Object r10 = r5.send(r10, r0)
            if (r10 != r6) goto L_0x008d
            return r6
        L_0x008d:
            r10 = r0
            r0 = r5
            r5 = r6
        L_0x0090:
            int r4 = r4 + -1
            if (r4 != 0) goto L_0x0097
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x0097:
            r7 = r4
            r4 = r1
            r1 = r7
            goto L_0x0059
        L_0x009b:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x009e:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "Requested element count "
            r10.append(r0)
            int r0 = r9.f747$n
            r10.append(r0)
            java.lang.String r0 = " is less than zero."
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r10 = r10.toString()
            r0.<init>(r10)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$take$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
