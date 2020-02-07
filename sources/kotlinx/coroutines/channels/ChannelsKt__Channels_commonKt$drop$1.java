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
@DebugMetadata(mo37999c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$drop$1", mo38000f = "Channels.common.kt", mo38001i = {0, 1, 2, 2}, mo38002l = {656, 661, 662}, mo38003m = "invokeSuspend", mo38004n = {"remaining", "remaining", "remaining", "e"}, mo38005s = {"I$0", "I$0", "I$0", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$drop$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {

    /* renamed from: $n */
    final /* synthetic */ int f739$n;
    final /* synthetic */ ReceiveChannel $this_drop;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f740p$;

    ChannelsKt__Channels_commonKt$drop$1(ReceiveChannel receiveChannel, int i, Continuation continuation) {
        this.$this_drop = receiveChannel;
        this.f739$n = i;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$drop$1 channelsKt__Channels_commonKt$drop$1 = new ChannelsKt__Channels_commonKt$drop$1(this.$this_drop, this.f739$n, continuation);
        channelsKt__Channels_commonKt$drop$1.f740p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$drop$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$drop$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ab A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b9  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0050
            if (r1 == r4) goto L_0x0040
            if (r1 == r3) goto L_0x002f
            if (r1 != r2) goto L_0x0027
            java.lang.Object r1 = r9.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r9.L$1
            int r4 = r9.I$0
            java.lang.Object r5 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r10)
            r7 = r0
            r10 = r1
            r6 = r5
            r0 = r9
        L_0x0024:
            r5 = r4
            goto L_0x009d
        L_0x0027:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x002f:
            java.lang.Object r1 = r9.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r4 = r9.I$0
            java.lang.Object r5 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r10)
            r7 = r0
            r0 = r9
            goto L_0x00b1
        L_0x0040:
            java.lang.Object r1 = r9.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            int r5 = r9.I$0
            java.lang.Object r6 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.throwOnFailure(r10)
            r7 = r0
            r0 = r9
            goto L_0x0080
        L_0x0050:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.channels.ProducerScope r10 = r9.f740p$
            int r1 = r9.f739$n
            if (r1 < 0) goto L_0x005b
            r1 = 1
            goto L_0x005c
        L_0x005b:
            r1 = 0
        L_0x005c:
            if (r1 == 0) goto L_0x00d5
            int r1 = r9.f739$n
            if (r1 <= 0) goto L_0x0093
            kotlinx.coroutines.channels.ReceiveChannel r5 = r9.$this_drop
            kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
            r6 = r10
            r10 = r9
            r8 = r5
            r5 = r1
            r1 = r8
        L_0x006d:
            r10.L$0 = r6
            r10.I$0 = r5
            r10.L$1 = r1
            r10.label = r4
            java.lang.Object r7 = r1.hasNext(r10)
            if (r7 != r0) goto L_0x007c
            return r0
        L_0x007c:
            r8 = r0
            r0 = r10
            r10 = r7
            r7 = r8
        L_0x0080:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x0097
            r1.next()
            int r5 = r5 + -1
            if (r5 != 0) goto L_0x0090
            goto L_0x0097
        L_0x0090:
            r10 = r0
            r0 = r7
            goto L_0x006d
        L_0x0093:
            r6 = r10
            r7 = r0
            r5 = r1
            r0 = r9
        L_0x0097:
            kotlinx.coroutines.channels.ReceiveChannel r10 = r0.$this_drop
            kotlinx.coroutines.channels.ChannelIterator r10 = r10.iterator()
        L_0x009d:
            r0.L$0 = r6
            r0.I$0 = r5
            r0.L$1 = r10
            r0.label = r3
            java.lang.Object r1 = r10.hasNext(r0)
            if (r1 != r7) goto L_0x00ac
            return r7
        L_0x00ac:
            r4 = r5
            r5 = r6
            r8 = r1
            r1 = r10
            r10 = r8
        L_0x00b1:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x00d2
            java.lang.Object r10 = r1.next()
            r0.L$0 = r5
            r0.I$0 = r4
            r0.L$1 = r10
            r0.L$2 = r1
            r0.label = r2
            java.lang.Object r10 = r5.send(r10, r0)
            if (r10 != r7) goto L_0x00ce
            return r7
        L_0x00ce:
            r10 = r1
            r6 = r5
            goto L_0x0024
        L_0x00d2:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x00d5:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "Requested element count "
            r10.append(r0)
            int r0 = r9.f739$n
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
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$drop$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
