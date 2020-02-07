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
@DebugMetadata(mo37999c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filter$1", mo38000f = "Channels.common.kt", mo38001i = {1, 2}, mo38002l = {707, 708, 708}, mo38003m = "invokeSuspend", mo38004n = {"e", "e"}, mo38005s = {"L$1", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$filter$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $predicate;
    final /* synthetic */ ReceiveChannel $this_filter;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f742p$;

    ChannelsKt__Channels_commonKt$filter$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        this.$this_filter = receiveChannel;
        this.$predicate = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$filter$1 channelsKt__Channels_commonKt$filter$1 = new ChannelsKt__Channels_commonKt$filter$1(this.$this_filter, this.$predicate, continuation);
        channelsKt__Channels_commonKt$filter$1.f742p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$filter$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$filter$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0060 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008d  */
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
            if (r1 == 0) goto L_0x0047
            if (r1 == r4) goto L_0x0039
            if (r1 == r3) goto L_0x0027
            if (r1 != r2) goto L_0x001f
            java.lang.Object r1 = r9.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r9.L$1
            java.lang.Object r5 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0053
        L_0x001f:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0027:
            java.lang.Object r1 = r9.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r9.L$1
            java.lang.Object r6 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.throwOnFailure(r10)
            r7 = r5
            r5 = r6
            r6 = r0
            r0 = r9
            goto L_0x0085
        L_0x0039:
            java.lang.Object r1 = r9.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r10)
            r6 = r0
            r0 = r9
            goto L_0x0065
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.channels.ProducerScope r10 = r9.f742p$
            kotlinx.coroutines.channels.ReceiveChannel r1 = r9.$this_filter
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            r5 = r10
        L_0x0053:
            r10 = r9
        L_0x0054:
            r10.L$0 = r5
            r10.L$1 = r1
            r10.label = r4
            java.lang.Object r6 = r1.hasNext(r10)
            if (r6 != r0) goto L_0x0061
            return r0
        L_0x0061:
            r8 = r0
            r0 = r10
            r10 = r6
            r6 = r8
        L_0x0065:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x009f
            java.lang.Object r10 = r1.next()
            kotlin.jvm.functions.Function2 r7 = r0.$predicate
            r0.L$0 = r5
            r0.L$1 = r10
            r0.L$2 = r1
            r0.label = r3
            java.lang.Object r7 = r7.invoke(r10, r0)
            if (r7 != r6) goto L_0x0082
            return r6
        L_0x0082:
            r8 = r7
            r7 = r10
            r10 = r8
        L_0x0085:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x009c
            r0.L$0 = r5
            r0.L$1 = r7
            r0.L$2 = r1
            r0.label = r2
            java.lang.Object r10 = r5.send(r7, r0)
            if (r10 != r6) goto L_0x009c
            return r6
        L_0x009c:
            r10 = r0
            r0 = r6
            goto L_0x0054
        L_0x009f:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$filter$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
