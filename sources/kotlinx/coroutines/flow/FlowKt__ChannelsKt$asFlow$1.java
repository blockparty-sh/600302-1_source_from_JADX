package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BroadcastChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.FlowKt__ChannelsKt$asFlow$1", mo38000f = "Channels.kt", mo38001i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, mo38002l = {75, 32}, mo38003m = "invokeSuspend", mo38004n = {"subscription", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "subscription", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "e$iv", "value"}, mo38005s = {"L$1", "L$2", "L$4", "L$5", "L$6", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9"})
/* compiled from: Channels.kt */
final class FlowKt__ChannelsKt$asFlow$1 extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ BroadcastChannel $this_asFlow;
    Object L$0;
    Object L$1;
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
    private FlowCollector f757p$;

    FlowKt__ChannelsKt$asFlow$1(BroadcastChannel broadcastChannel, Continuation continuation) {
        this.$this_asFlow = broadcastChannel;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        FlowKt__ChannelsKt$asFlow$1 flowKt__ChannelsKt$asFlow$1 = new FlowKt__ChannelsKt$asFlow$1(this.$this_asFlow, continuation);
        flowKt__ChannelsKt$asFlow$1.f757p$ = (FlowCollector) obj;
        return flowKt__ChannelsKt$asFlow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__ChannelsKt$asFlow$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b5 A[Catch:{ Throwable -> 0x00f1 }] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x006e
            if (r1 == r3) goto L_0x0048
            if (r1 != r2) goto L_0x0040
            java.lang.Object r1 = r14.L$9
            java.lang.Object r1 = r14.L$8
            java.lang.Object r1 = r14.L$7
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r14.L$6
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r14.L$5
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r14.L$4
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r14.L$3
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$asFlow$1 r7 = (kotlinx.coroutines.flow.FlowKt__ChannelsKt$asFlow$1) r7
            java.lang.Object r8 = r14.L$2
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r14.L$1
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r14.L$0
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x00f1 }
            r15 = r14
            r12 = r10
            r10 = r0
            r0 = r12
            r13 = r9
            r9 = r4
            r4 = r8
            r8 = r5
            r5 = r6
            r6 = r13
            goto L_0x0089
        L_0x0040:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x0048:
            java.lang.Object r1 = r14.L$7
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r14.L$6
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r14.L$5
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r14.L$4
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r14.L$3
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$asFlow$1 r7 = (kotlinx.coroutines.flow.FlowKt__ChannelsKt$asFlow$1) r7
            java.lang.Object r8 = r14.L$2
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r14.L$1
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            java.lang.Object r10 = r14.L$0
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ Throwable -> 0x00f1 }
            r11 = r0
            r0 = r14
            goto L_0x00ad
        L_0x006e:
            kotlin.ResultKt.throwOnFailure(r15)
            kotlinx.coroutines.flow.FlowCollector r15 = r14.f757p$
            kotlinx.coroutines.channels.BroadcastChannel r1 = r14.$this_asFlow
            kotlinx.coroutines.channels.ReceiveChannel r6 = r1.openSubscription()
            r1 = 0
            r5 = r1
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            kotlinx.coroutines.channels.ChannelIterator r1 = r6.iterator()     // Catch:{ Throwable -> 0x00f1 }
            r7 = r14
            r10 = r0
            r8 = r5
            r4 = r6
            r5 = r4
            r9 = r5
            r0 = r15
            r15 = r7
        L_0x0089:
            r15.L$0 = r0     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            r15.L$1 = r6     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            r15.L$2 = r4     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            r15.L$3 = r7     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            r15.L$4 = r5     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            r15.L$5 = r8     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            r15.L$6 = r9     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            r15.L$7 = r1     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            r15.label = r3     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            java.lang.Object r11 = r1.hasNext(r7)     // Catch:{ Throwable -> 0x00ec, all -> 0x00e8 }
            if (r11 != r10) goto L_0x00a2
            return r10
        L_0x00a2:
            r12 = r0
            r0 = r15
            r15 = r11
            r11 = r10
            r10 = r12
            r13 = r8
            r8 = r4
            r4 = r9
            r9 = r6
            r6 = r5
            r5 = r13
        L_0x00ad:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ Throwable -> 0x00f1 }
            boolean r15 = r15.booleanValue()     // Catch:{ Throwable -> 0x00f1 }
            if (r15 == 0) goto L_0x00e0
            java.lang.Object r15 = r1.next()     // Catch:{ Throwable -> 0x00f1 }
            r0.L$0 = r10     // Catch:{ Throwable -> 0x00f1 }
            r0.L$1 = r9     // Catch:{ Throwable -> 0x00f1 }
            r0.L$2 = r8     // Catch:{ Throwable -> 0x00f1 }
            r0.L$3 = r7     // Catch:{ Throwable -> 0x00f1 }
            r0.L$4 = r6     // Catch:{ Throwable -> 0x00f1 }
            r0.L$5 = r5     // Catch:{ Throwable -> 0x00f1 }
            r0.L$6 = r4     // Catch:{ Throwable -> 0x00f1 }
            r0.L$7 = r1     // Catch:{ Throwable -> 0x00f1 }
            r0.L$8 = r15     // Catch:{ Throwable -> 0x00f1 }
            r0.L$9 = r15     // Catch:{ Throwable -> 0x00f1 }
            r0.label = r2     // Catch:{ Throwable -> 0x00f1 }
            java.lang.Object r15 = r10.emit(r15, r0)     // Catch:{ Throwable -> 0x00f1 }
            if (r15 != r11) goto L_0x00d6
            return r11
        L_0x00d6:
            r15 = r0
            r0 = r10
            r10 = r11
            r12 = r9
            r9 = r4
            r4 = r8
            r8 = r5
            r5 = r6
            r6 = r12
            goto L_0x0089
        L_0x00e0:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00f1 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r5)
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        L_0x00e8:
            r15 = move-exception
            r6 = r5
            r5 = r8
            goto L_0x00f4
        L_0x00ec:
            r15 = move-exception
            r6 = r5
            goto L_0x00f2
        L_0x00ef:
            r15 = move-exception
            goto L_0x00f4
        L_0x00f1:
            r15 = move-exception
        L_0x00f2:
            r5 = r15
            throw r5     // Catch:{ all -> 0x00ef }
        L_0x00f4:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r5)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ChannelsKt$asFlow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
