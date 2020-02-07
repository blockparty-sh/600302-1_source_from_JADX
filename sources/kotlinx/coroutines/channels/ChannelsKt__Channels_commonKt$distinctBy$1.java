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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "", "E", "K", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$distinctBy$1", mo38000f = "Channels.common.kt", mo38001i = {0, 1, 1, 2, 2, 2}, mo38002l = {1656, 1657, 1659}, mo38003m = "invokeSuspend", mo38004n = {"keys", "keys", "e", "keys", "e", "k"}, mo38005s = {"L$1", "L$1", "L$2", "L$1", "L$2", "L$4"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$distinctBy$1 extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $selector;
    final /* synthetic */ ReceiveChannel $this_distinctBy;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* renamed from: p$ */
    private ProducerScope f738p$;

    ChannelsKt__Channels_commonKt$distinctBy$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        this.$this_distinctBy = receiveChannel;
        this.$selector = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$distinctBy$1 channelsKt__Channels_commonKt$distinctBy$1 = new ChannelsKt__Channels_commonKt$distinctBy$1(this.$this_distinctBy, this.$selector, continuation);
        channelsKt__Channels_commonKt$distinctBy$1.f738p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$distinctBy$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$distinctBy$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ae  */
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
            if (r1 == 0) goto L_0x005a
            if (r1 == r4) goto L_0x0048
            if (r1 == r3) goto L_0x0030
            if (r1 != r2) goto L_0x0028
            java.lang.Object r1 = r11.L$4
            java.lang.Object r5 = r11.L$3
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r11.L$2
            java.lang.Object r6 = r11.L$1
            java.util.HashSet r6 = (java.util.HashSet) r6
            java.lang.Object r7 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r0
            r0 = r11
            goto L_0x00c1
        L_0x0028:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0030:
            java.lang.Object r1 = r11.L$3
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r11.L$2
            java.lang.Object r6 = r11.L$1
            java.util.HashSet r6 = (java.util.HashSet) r6
            java.lang.Object r7 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            kotlin.ResultKt.throwOnFailure(r12)
            r8 = r5
            r5 = r1
            r1 = r12
            r12 = r0
            r0 = r11
            goto L_0x00a8
        L_0x0048:
            java.lang.Object r1 = r11.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r11.L$1
            java.util.HashSet r5 = (java.util.HashSet) r5
            java.lang.Object r6 = r11.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.throwOnFailure(r12)
            r7 = r0
            r0 = r11
            goto L_0x0082
        L_0x005a:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.channels.ProducerScope r12 = r11.f738p$
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            kotlinx.coroutines.channels.ReceiveChannel r5 = r11.$this_distinctBy
            kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
            r6 = r12
            r12 = r11
            r10 = r5
            r5 = r1
            r1 = r10
        L_0x006f:
            r12.L$0 = r6
            r12.L$1 = r5
            r12.L$2 = r1
            r12.label = r4
            java.lang.Object r7 = r1.hasNext(r12)
            if (r7 != r0) goto L_0x007e
            return r0
        L_0x007e:
            r10 = r0
            r0 = r12
            r12 = r7
            r7 = r10
        L_0x0082:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x00ce
            java.lang.Object r12 = r1.next()
            kotlin.jvm.functions.Function2 r8 = r0.$selector
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r12
            r0.L$3 = r1
            r0.label = r3
            java.lang.Object r8 = r8.invoke(r12, r0)
            if (r8 != r7) goto L_0x00a1
            return r7
        L_0x00a1:
            r10 = r8
            r8 = r12
            r12 = r7
            r7 = r6
            r6 = r5
            r5 = r1
            r1 = r10
        L_0x00a8:
            boolean r9 = r6.contains(r1)
            if (r9 != 0) goto L_0x00c7
            r0.L$0 = r7
            r0.L$1 = r6
            r0.L$2 = r8
            r0.L$3 = r5
            r0.L$4 = r1
            r0.label = r2
            java.lang.Object r8 = r7.send(r8, r0)
            if (r8 != r12) goto L_0x00c1
            return r12
        L_0x00c1:
            r8 = r6
            java.util.Collection r8 = (java.util.Collection) r8
            r8.add(r1)
        L_0x00c7:
            r1 = r5
            r5 = r6
            r6 = r7
            r10 = r0
            r0 = r12
            r12 = r10
            goto L_0x006f
        L_0x00ce:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$distinctBy$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
