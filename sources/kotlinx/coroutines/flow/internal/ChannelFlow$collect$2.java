package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.internal.ChannelFlow$collect$2", mo38000f = "ChannelFlow.kt", mo38001i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, mo38002l = {180, 71}, mo38003m = "invokeSuspend", mo38004n = {"channel", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "channel", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "e$iv", "it"}, mo38005s = {"L$0", "L$1", "L$3", "L$4", "L$5", "L$0", "L$1", "L$3", "L$4", "L$5", "L$7", "L$8"})
/* compiled from: ChannelFlow.kt */
final class ChannelFlow$collect$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector $collector;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f773p$;
    final /* synthetic */ ChannelFlow this$0;

    ChannelFlow$collect$2(ChannelFlow channelFlow, FlowCollector flowCollector, Continuation continuation) {
        this.this$0 = channelFlow;
        this.$collector = flowCollector;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ChannelFlow$collect$2 channelFlow$collect$2 = new ChannelFlow$collect$2(this.this$0, this.$collector, continuation);
        channelFlow$collect$2.f773p$ = (CoroutineScope) obj;
        return channelFlow$collect$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ChannelFlow$collect$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a3 A[Catch:{ Throwable -> 0x00dc }] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0063
            if (r1 == r3) goto L_0x0041
            if (r1 != r2) goto L_0x0039
            java.lang.Object r1 = r13.L$8
            java.lang.Object r1 = r13.L$7
            java.lang.Object r1 = r13.L$6
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r13.L$5
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r13.L$4
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r13.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r13.L$2
            kotlinx.coroutines.flow.internal.ChannelFlow$collect$2 r7 = (kotlinx.coroutines.flow.internal.ChannelFlow$collect$2) r7
            java.lang.Object r8 = r13.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r13.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x00dc }
            r14 = r1
            r1 = r8
            r8 = r4
            r4 = r6
            r6 = r9
            r9 = r0
            r0 = r13
            goto L_0x007c
        L_0x0039:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0041:
            java.lang.Object r1 = r13.L$6
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r13.L$5
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
            java.lang.Object r5 = r13.L$4
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            java.lang.Object r6 = r13.L$3
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r13.L$2
            kotlinx.coroutines.flow.internal.ChannelFlow$collect$2 r7 = (kotlinx.coroutines.flow.internal.ChannelFlow$collect$2) r7
            java.lang.Object r8 = r13.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r13.L$0
            kotlinx.coroutines.channels.ReceiveChannel r9 = (kotlinx.coroutines.channels.ReceiveChannel) r9
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Throwable -> 0x00dc }
            r10 = r0
            r0 = r13
            goto L_0x009b
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlinx.coroutines.CoroutineScope r14 = r13.f773p$
            kotlinx.coroutines.flow.internal.ChannelFlow r1 = r13.this$0
            kotlinx.coroutines.channels.ReceiveChannel r6 = r1.produceImpl(r14)
            r14 = 0
            r5 = r14
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            kotlinx.coroutines.channels.ChannelIterator r14 = r6.iterator()     // Catch:{ Throwable -> 0x00dc }
            r7 = r13
            r9 = r0
            r1 = r6
            r4 = r1
            r8 = r4
            r0 = r7
        L_0x007c:
            r0.L$0 = r6     // Catch:{ Throwable -> 0x00d6, all -> 0x00d3 }
            r0.L$1 = r1     // Catch:{ Throwable -> 0x00d6, all -> 0x00d3 }
            r0.L$2 = r7     // Catch:{ Throwable -> 0x00d6, all -> 0x00d3 }
            r0.L$3 = r4     // Catch:{ Throwable -> 0x00d6, all -> 0x00d3 }
            r0.L$4 = r5     // Catch:{ Throwable -> 0x00d6, all -> 0x00d3 }
            r0.L$5 = r8     // Catch:{ Throwable -> 0x00d6, all -> 0x00d3 }
            r0.L$6 = r14     // Catch:{ Throwable -> 0x00d6, all -> 0x00d3 }
            r0.label = r3     // Catch:{ Throwable -> 0x00d6, all -> 0x00d3 }
            java.lang.Object r10 = r14.hasNext(r7)     // Catch:{ Throwable -> 0x00d6, all -> 0x00d3 }
            if (r10 != r9) goto L_0x0093
            return r9
        L_0x0093:
            r12 = r1
            r1 = r14
            r14 = r10
            r10 = r9
            r9 = r6
            r6 = r4
            r4 = r8
            r8 = r12
        L_0x009b:
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x00dc }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x00dc }
            if (r14 == 0) goto L_0x00cb
            java.lang.Object r14 = r1.next()     // Catch:{ Throwable -> 0x00dc }
            kotlinx.coroutines.flow.FlowCollector r11 = r0.$collector     // Catch:{ Throwable -> 0x00dc }
            r0.L$0 = r9     // Catch:{ Throwable -> 0x00dc }
            r0.L$1 = r8     // Catch:{ Throwable -> 0x00dc }
            r0.L$2 = r7     // Catch:{ Throwable -> 0x00dc }
            r0.L$3 = r6     // Catch:{ Throwable -> 0x00dc }
            r0.L$4 = r5     // Catch:{ Throwable -> 0x00dc }
            r0.L$5 = r4     // Catch:{ Throwable -> 0x00dc }
            r0.L$6 = r1     // Catch:{ Throwable -> 0x00dc }
            r0.L$7 = r14     // Catch:{ Throwable -> 0x00dc }
            r0.L$8 = r14     // Catch:{ Throwable -> 0x00dc }
            r0.label = r2     // Catch:{ Throwable -> 0x00dc }
            java.lang.Object r14 = r11.emit(r14, r0)     // Catch:{ Throwable -> 0x00dc }
            if (r14 != r10) goto L_0x00c4
            return r10
        L_0x00c4:
            r14 = r1
            r1 = r8
            r8 = r4
            r4 = r6
            r6 = r9
            r9 = r10
            goto L_0x007c
        L_0x00cb:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x00dc }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r5)
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x00d3:
            r14 = move-exception
            r6 = r4
            goto L_0x00df
        L_0x00d6:
            r14 = move-exception
            r5 = r14
            r6 = r4
            goto L_0x00de
        L_0x00da:
            r14 = move-exception
            goto L_0x00df
        L_0x00dc:
            r14 = move-exception
            r5 = r14
        L_0x00de:
            throw r5     // Catch:{ all -> 0x00da }
        L_0x00df:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r6, r5)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.ChannelFlow$collect$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
