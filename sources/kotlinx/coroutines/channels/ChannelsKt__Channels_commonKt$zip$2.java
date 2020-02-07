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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, mo37405d2 = {"<anonymous>", "", "E", "R", "V", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2", mo38000f = "Channels.common.kt", mo38001i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2}, mo38002l = {2157, 2147, 2149}, mo38003m = "invokeSuspend", mo38004n = {"otherIterator", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "otherIterator", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "e$iv", "element1", "otherIterator", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "e$iv", "element1", "element2"}, mo38005s = {"L$1", "L$2", "L$4", "L$5", "L$6", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$10"})
/* compiled from: Channels.common.kt */
final class ChannelsKt__Channels_commonKt$zip$2 extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $other;
    final /* synthetic */ ReceiveChannel $this_zip;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;

    /* renamed from: p$ */
    private ProducerScope f751p$;

    ChannelsKt__Channels_commonKt$zip$2(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, Function2 function2, Continuation continuation) {
        this.$this_zip = receiveChannel;
        this.$other = receiveChannel2;
        this.$transform = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelsKt__Channels_commonKt$zip$2 channelsKt__Channels_commonKt$zip$2 = new ChannelsKt__Channels_commonKt$zip$2(this.$this_zip, this.$other, this.$transform, continuation);
        channelsKt__Channels_commonKt$zip$2.f751p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$zip$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelsKt__Channels_commonKt$zip$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ee A[Catch:{ Throwable -> 0x014f }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0118 A[Catch:{ Throwable -> 0x014f }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0119 A[Catch:{ Throwable -> 0x014f }] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r17) {
        /*
            r16 = this;
            r1 = r16
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x00b1
            if (r2 == r5) goto L_0x0089
            if (r2 == r4) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            java.lang.Object r2 = r1.L$10
            java.lang.Object r2 = r1.L$9
            java.lang.Object r2 = r1.L$8
            java.lang.Object r2 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2 r9 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r9
            java.lang.Object r10 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r12 = (kotlinx.coroutines.channels.ProducerScope) r12
            kotlin.ResultKt.throwOnFailure(r17)     // Catch:{ Throwable -> 0x014f }
            r13 = r0
            r0 = r1
            goto L_0x0142
        L_0x0040:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0048:
            java.lang.Object r2 = r1.L$9
            java.lang.Object r6 = r1.L$8
            java.lang.Object r7 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r8 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$5
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r10 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$3
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2 r11 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r11
            java.lang.Object r12 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r14 = (kotlinx.coroutines.channels.ProducerScope) r14
            kotlin.ResultKt.throwOnFailure(r17)     // Catch:{ Throwable -> 0x0084, all -> 0x007f }
            r15 = r17
            r4 = r6
            r6 = r8
            r8 = r10
            r10 = r12
            r12 = r14
            r14 = r2
            r2 = r7
            r7 = r9
            r9 = r11
            r11 = r13
            r13 = r0
            r0 = r1
            goto L_0x0110
        L_0x007f:
            r0 = move-exception
            r7 = r9
            r8 = r10
            goto L_0x0152
        L_0x0084:
            r0 = move-exception
            r7 = r0
            r8 = r10
            goto L_0x0151
        L_0x0089:
            java.lang.Object r2 = r1.L$7
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r6 = r1.L$6
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$5
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r8 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$3
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2 r9 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2) r9
            java.lang.Object r10 = r1.L$2
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r12 = (kotlinx.coroutines.channels.ProducerScope) r12
            kotlin.ResultKt.throwOnFailure(r17)     // Catch:{ Throwable -> 0x014f }
            r14 = r17
            r13 = r0
            r0 = r1
            goto L_0x00e6
        L_0x00b1:
            kotlin.ResultKt.throwOnFailure(r17)
            kotlinx.coroutines.channels.ProducerScope r2 = r1.f751p$
            kotlinx.coroutines.channels.ReceiveChannel r6 = r1.$other
            kotlinx.coroutines.channels.ChannelIterator r6 = r6.iterator()
            kotlinx.coroutines.channels.ReceiveChannel r8 = r1.$this_zip
            r7 = 0
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            kotlinx.coroutines.channels.ChannelIterator r9 = r8.iterator()     // Catch:{ Throwable -> 0x014f }
            r13 = r0
            r0 = r1
            r12 = r2
            r11 = r6
            r6 = r8
            r10 = r6
            r2 = r9
            r9 = r0
        L_0x00cd:
            r0.L$0 = r12     // Catch:{ Throwable -> 0x014f }
            r0.L$1 = r11     // Catch:{ Throwable -> 0x014f }
            r0.L$2 = r10     // Catch:{ Throwable -> 0x014f }
            r0.L$3 = r9     // Catch:{ Throwable -> 0x014f }
            r0.L$4 = r8     // Catch:{ Throwable -> 0x014f }
            r0.L$5 = r7     // Catch:{ Throwable -> 0x014f }
            r0.L$6 = r6     // Catch:{ Throwable -> 0x014f }
            r0.L$7 = r2     // Catch:{ Throwable -> 0x014f }
            r0.label = r5     // Catch:{ Throwable -> 0x014f }
            java.lang.Object r14 = r2.hasNext(r9)     // Catch:{ Throwable -> 0x014f }
            if (r14 != r13) goto L_0x00e6
            return r13
        L_0x00e6:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x014f }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x014f }
            if (r14 == 0) goto L_0x0145
            java.lang.Object r14 = r2.next()     // Catch:{ Throwable -> 0x014f }
            r0.L$0 = r12     // Catch:{ Throwable -> 0x014f }
            r0.L$1 = r11     // Catch:{ Throwable -> 0x014f }
            r0.L$2 = r10     // Catch:{ Throwable -> 0x014f }
            r0.L$3 = r9     // Catch:{ Throwable -> 0x014f }
            r0.L$4 = r8     // Catch:{ Throwable -> 0x014f }
            r0.L$5 = r7     // Catch:{ Throwable -> 0x014f }
            r0.L$6 = r6     // Catch:{ Throwable -> 0x014f }
            r0.L$7 = r2     // Catch:{ Throwable -> 0x014f }
            r0.L$8 = r14     // Catch:{ Throwable -> 0x014f }
            r0.L$9 = r14     // Catch:{ Throwable -> 0x014f }
            r0.label = r4     // Catch:{ Throwable -> 0x014f }
            java.lang.Object r15 = r11.hasNext(r0)     // Catch:{ Throwable -> 0x014f }
            if (r15 != r13) goto L_0x010f
            return r13
        L_0x010f:
            r4 = r14
        L_0x0110:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x014f }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x014f }
            if (r15 != 0) goto L_0x0119
            goto L_0x0142
        L_0x0119:
            java.lang.Object r15 = r11.next()     // Catch:{ Throwable -> 0x014f }
            kotlin.jvm.functions.Function2 r5 = r0.$transform     // Catch:{ Throwable -> 0x014f }
            java.lang.Object r5 = r5.invoke(r14, r15)     // Catch:{ Throwable -> 0x014f }
            r0.L$0 = r12     // Catch:{ Throwable -> 0x014f }
            r0.L$1 = r11     // Catch:{ Throwable -> 0x014f }
            r0.L$2 = r10     // Catch:{ Throwable -> 0x014f }
            r0.L$3 = r9     // Catch:{ Throwable -> 0x014f }
            r0.L$4 = r8     // Catch:{ Throwable -> 0x014f }
            r0.L$5 = r7     // Catch:{ Throwable -> 0x014f }
            r0.L$6 = r6     // Catch:{ Throwable -> 0x014f }
            r0.L$7 = r2     // Catch:{ Throwable -> 0x014f }
            r0.L$8 = r4     // Catch:{ Throwable -> 0x014f }
            r0.L$9 = r14     // Catch:{ Throwable -> 0x014f }
            r0.L$10 = r15     // Catch:{ Throwable -> 0x014f }
            r0.label = r3     // Catch:{ Throwable -> 0x014f }
            java.lang.Object r4 = r12.send(r5, r0)     // Catch:{ Throwable -> 0x014f }
            if (r4 != r13) goto L_0x0142
            return r13
        L_0x0142:
            r4 = 2
            r5 = 1
            goto L_0x00cd
        L_0x0145:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x014f }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x014d:
            r0 = move-exception
            goto L_0x0152
        L_0x014f:
            r0 = move-exception
            r7 = r0
        L_0x0151:
            throw r7     // Catch:{ all -> 0x014d }
        L_0x0152:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r8, r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
