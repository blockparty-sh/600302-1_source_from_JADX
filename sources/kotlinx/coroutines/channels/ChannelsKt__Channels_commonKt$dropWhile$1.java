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
@DebugMetadata(mo37999c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$dropWhile$1", mo38000f = "Channels.common.kt", mo38001i = {1, 2, 4}, mo38002l = {681, 682, 683, 687, 688}, mo38003m = "invokeSuspend", mo38004n = {"e", "e", "e"}, mo38005s = {"L$1", "L$1", "L$1"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$dropWhile$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $predicate;
    final /* synthetic */ ReceiveChannel $this_dropWhile;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f741p$;

    ChannelsKt__Channels_commonKt$dropWhile$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        this.$this_dropWhile = receiveChannel;
        this.$predicate = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$dropWhile$1 channelsKt__Channels_commonKt$dropWhile$1 = new ChannelsKt__Channels_commonKt$dropWhile$1(this.$this_dropWhile, this.$predicate, continuation);
        channelsKt__Channels_commonKt$dropWhile$1.f741p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$dropWhile$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$dropWhile$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c1 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d7 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e4  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == 0) goto L_0x006f
            if (r1 == r6) goto L_0x0061
            if (r1 == r5) goto L_0x004f
            if (r1 == r4) goto L_0x0041
            if (r1 == r3) goto L_0x0032
            if (r1 != r2) goto L_0x002a
            java.lang.Object r1 = r11.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r11.L$1
            java.lang.Object r4 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r12)
            r8 = r0
            r12 = r1
            r7 = r4
            r0 = r11
            goto L_0x00cb
        L_0x002a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0032:
            java.lang.Object r1 = r11.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r12)
            r8 = r0
            r0 = r11
            goto L_0x00dc
        L_0x0041:
            java.lang.Object r1 = r11.L$1
            java.lang.Object r1 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            kotlin.ResultKt.throwOnFailure(r12)
            r8 = r0
            r7 = r1
            r0 = r11
            goto L_0x00c5
        L_0x004f:
            java.lang.Object r1 = r11.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r7 = r11.L$1
            java.lang.Object r8 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            kotlin.ResultKt.throwOnFailure(r12)
            r9 = r7
            r7 = r8
            r8 = r0
            r0 = r11
            goto L_0x00ad
        L_0x0061:
            java.lang.Object r1 = r11.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r7 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            kotlin.ResultKt.throwOnFailure(r12)
            r8 = r0
            r0 = r11
            goto L_0x008d
        L_0x006f:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.channels.ProducerScope r12 = r11.f741p$
            kotlinx.coroutines.channels.ReceiveChannel r1 = r11.$this_dropWhile
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            r7 = r12
            r12 = r11
        L_0x007c:
            r12.L$0 = r7
            r12.L$1 = r1
            r12.label = r6
            java.lang.Object r8 = r1.hasNext(r12)
            if (r8 != r0) goto L_0x0089
            return r0
        L_0x0089:
            r10 = r0
            r0 = r12
            r12 = r8
            r8 = r10
        L_0x008d:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x00c5
            java.lang.Object r12 = r1.next()
            kotlin.jvm.functions.Function2 r9 = r0.$predicate
            r0.L$0 = r7
            r0.L$1 = r12
            r0.L$2 = r1
            r0.label = r5
            java.lang.Object r9 = r9.invoke(r12, r0)
            if (r9 != r8) goto L_0x00aa
            return r8
        L_0x00aa:
            r10 = r9
            r9 = r12
            r12 = r10
        L_0x00ad:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 != 0) goto L_0x00c2
            r0.L$0 = r7
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r12 = r7.send(r9, r0)
            if (r12 != r8) goto L_0x00c5
            return r8
        L_0x00c2:
            r12 = r0
            r0 = r8
            goto L_0x007c
        L_0x00c5:
            kotlinx.coroutines.channels.ReceiveChannel r12 = r0.$this_dropWhile
            kotlinx.coroutines.channels.ChannelIterator r12 = r12.iterator()
        L_0x00cb:
            r0.L$0 = r7
            r0.L$1 = r12
            r0.label = r3
            java.lang.Object r1 = r12.hasNext(r0)
            if (r1 != r8) goto L_0x00d8
            return r8
        L_0x00d8:
            r4 = r7
            r10 = r1
            r1 = r12
            r12 = r10
        L_0x00dc:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x00fa
            java.lang.Object r12 = r1.next()
            r0.L$0 = r4
            r0.L$1 = r12
            r0.L$2 = r1
            r0.label = r2
            java.lang.Object r12 = r4.send(r12, r0)
            if (r12 != r8) goto L_0x00f7
            return r8
        L_0x00f7:
            r12 = r1
            r7 = r4
            goto L_0x00cb
        L_0x00fa:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$dropWhile$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
