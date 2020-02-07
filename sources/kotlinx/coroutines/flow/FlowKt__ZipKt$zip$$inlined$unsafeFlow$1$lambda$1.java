package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo37405d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$zip$1$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Zip.kt */
final class FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector $this_unsafeFlow;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
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
    private CoroutineScope f770p$;
    final /* synthetic */ FlowKt__ZipKt$zip$$inlined$unsafeFlow$1 this$0;

    FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, Continuation continuation, FlowKt__ZipKt$zip$$inlined$unsafeFlow$1 flowKt__ZipKt$zip$$inlined$unsafeFlow$1) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = flowKt__ZipKt$zip$$inlined$unsafeFlow$1;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1 flowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1 = new FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1(this.$this_unsafeFlow, continuation, this.this$0);
        flowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1.f770p$ = (CoroutineScope) obj;
        return flowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x017c A[Catch:{ Throwable -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01bc A[Catch:{ Throwable -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01be A[Catch:{ Throwable -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01cb A[Catch:{ Throwable -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01ce A[Catch:{ Throwable -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01d4 A[Catch:{ Throwable -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01d6 A[Catch:{ Throwable -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0201 A[Catch:{ Throwable -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x022e A[Catch:{ Throwable -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x024b  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r22) {
        /*
            r21 = this;
            r1 = r21
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L_0x0118
            if (r2 == r6) goto L_0x00de
            if (r2 == r5) goto L_0x00a2
            if (r2 == r4) goto L_0x004e
            if (r2 != r3) goto L_0x0046
            java.lang.Object r2 = r1.L$11
            java.lang.Object r2 = r1.L$10
            java.lang.Object r2 = r1.L$9
            java.lang.Object r2 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r8 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r1.L$6
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r10 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r11 = r1.L$4
            kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1 r11 = (kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1) r11
            java.lang.Object r12 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r1.L$2
            kotlinx.coroutines.channels.ChannelIterator r13 = (kotlinx.coroutines.channels.ChannelIterator) r13
            java.lang.Object r14 = r1.L$1
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r15 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r15 = (kotlinx.coroutines.channels.ReceiveChannel) r15
            kotlin.ResultKt.throwOnFailure(r22)     // Catch:{ Throwable -> 0x0114 }
            goto L_0x0230
        L_0x0046:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x004e:
            java.lang.Object r2 = r1.L$12
            kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
            java.lang.Object r8 = r1.L$11
            java.lang.Object r9 = r1.L$10
            java.lang.Object r10 = r1.L$9
            java.lang.Object r11 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            java.lang.Object r13 = r1.L$6
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            java.lang.Object r14 = r1.L$5
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r15 = r1.L$4
            kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1 r15 = (kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1) r15
            java.lang.Object r3 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3
            java.lang.Object r4 = r1.L$2
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r5 = r1.L$1
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            kotlin.ResultKt.throwOnFailure(r22)     // Catch:{ Throwable -> 0x009c, all -> 0x0096 }
            r7 = r8
            r8 = r12
            r12 = r3
            r3 = r10
            r10 = r14
            r14 = r5
            r5 = r0
            r0 = r22
            r19 = r15
            r15 = r2
            r2 = r11
            r11 = r19
            r20 = r13
            r13 = r4
            r4 = r9
            r9 = r20
            goto L_0x020c
        L_0x0096:
            r0 = move-exception
            r9 = r13
            r10 = r14
            r14 = r5
            goto L_0x025d
        L_0x009c:
            r0 = move-exception
            r9 = r0
            r10 = r14
            r14 = r5
            goto L_0x025c
        L_0x00a2:
            java.lang.Object r2 = r1.L$10
            java.lang.Object r3 = r1.L$9
            java.lang.Object r4 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r5 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r1.L$6
            r9 = r6
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r6 = r1.L$5
            r10 = r6
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r6 = r1.L$4
            kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1 r6 = (kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1) r6
            java.lang.Object r8 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r11 = r1.L$2
            kotlinx.coroutines.channels.ChannelIterator r11 = (kotlinx.coroutines.channels.ChannelIterator) r11
            java.lang.Object r12 = r1.L$1
            r14 = r12
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r12 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r12 = (kotlinx.coroutines.channels.ReceiveChannel) r12
            kotlin.ResultKt.throwOnFailure(r22)     // Catch:{ Throwable -> 0x0114 }
            r7 = r22
            r13 = r2
            r15 = 2
            r2 = r1
            r19 = r12
            r12 = r3
            r3 = r5
            r5 = r8
            r8 = r19
            goto L_0x01a1
        L_0x00de:
            java.lang.Object r2 = r1.L$8
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r3 = r1.L$7
            kotlinx.coroutines.channels.ReceiveChannel r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3
            java.lang.Object r4 = r1.L$6
            r9 = r4
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            java.lang.Object r4 = r1.L$5
            r10 = r4
            kotlinx.coroutines.channels.ReceiveChannel r10 = (kotlinx.coroutines.channels.ReceiveChannel) r10
            java.lang.Object r4 = r1.L$4
            kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1 r4 = (kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1) r4
            java.lang.Object r5 = r1.L$3
            kotlinx.coroutines.channels.ReceiveChannel r5 = (kotlinx.coroutines.channels.ReceiveChannel) r5
            java.lang.Object r6 = r1.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r8 = r1.L$1
            r14 = r8
            kotlinx.coroutines.channels.ReceiveChannel r14 = (kotlinx.coroutines.channels.ReceiveChannel) r14
            java.lang.Object r8 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r22)     // Catch:{ Throwable -> 0x0114 }
            r13 = r22
            r11 = r6
            r12 = 1
            r6 = r4
            r4 = r2
            r2 = r1
            goto L_0x0174
        L_0x0111:
            r0 = move-exception
            goto L_0x025d
        L_0x0114:
            r0 = move-exception
            r9 = r0
            goto L_0x025c
        L_0x0118:
            kotlin.ResultKt.throwOnFailure(r22)
            kotlinx.coroutines.CoroutineScope r2 = r1.f770p$
            kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1 r3 = r1.this$0
            kotlinx.coroutines.flow.Flow r3 = r3.$this_zip$inlined
            kotlinx.coroutines.channels.ReceiveChannel r10 = kotlinx.coroutines.flow.FlowKt__ZipKt.asChannel$FlowKt__ZipKt(r2, r3)
            kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1 r3 = r1.this$0
            kotlinx.coroutines.flow.Flow r3 = r3.$other$inlined
            kotlinx.coroutines.channels.ReceiveChannel r14 = kotlinx.coroutines.flow.FlowKt__ZipKt.asChannel$FlowKt__ZipKt(r2, r3)
            if (r14 == 0) goto L_0x0283
            r2 = r14
            kotlinx.coroutines.channels.SendChannel r2 = (kotlinx.coroutines.channels.SendChannel) r2
            kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1$1 r3 = new kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1$1
            r3.<init>(r10)
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
            r2.invokeOnClose(r3)
            kotlinx.coroutines.channels.ChannelIterator r2 = r14.iterator()
            r9 = r7
            java.lang.Throwable r9 = (java.lang.Throwable) r9     // Catch:{ AbortFlowException -> 0x0273, all -> 0x0261 }
            kotlinx.coroutines.channels.ChannelIterator r3 = r10.iterator()     // Catch:{ Throwable -> 0x0114 }
            r11 = r0
            r0 = r1
            r4 = r2
            r5 = r10
            r6 = r5
            r8 = r6
            r2 = r0
        L_0x014e:
            r2.L$0 = r10     // Catch:{ Throwable -> 0x0259, all -> 0x0256 }
            r2.L$1 = r14     // Catch:{ Throwable -> 0x0259, all -> 0x0256 }
            r2.L$2 = r4     // Catch:{ Throwable -> 0x0259, all -> 0x0256 }
            r2.L$3 = r5     // Catch:{ Throwable -> 0x0259, all -> 0x0256 }
            r2.L$4 = r0     // Catch:{ Throwable -> 0x0259, all -> 0x0256 }
            r2.L$5 = r6     // Catch:{ Throwable -> 0x0259, all -> 0x0256 }
            r2.L$6 = r9     // Catch:{ Throwable -> 0x0259, all -> 0x0256 }
            r2.L$7 = r8     // Catch:{ Throwable -> 0x0259, all -> 0x0256 }
            r2.L$8 = r3     // Catch:{ Throwable -> 0x0259, all -> 0x0256 }
            r12 = 1
            r2.label = r12     // Catch:{ Throwable -> 0x0259, all -> 0x0256 }
            java.lang.Object r13 = r3.hasNext(r0)     // Catch:{ Throwable -> 0x0259, all -> 0x0256 }
            if (r13 != r11) goto L_0x016a
            return r11
        L_0x016a:
            r19 = r6
            r6 = r0
            r0 = r11
            r11 = r4
            r4 = r3
            r3 = r8
            r8 = r10
            r10 = r19
        L_0x0174:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ Throwable -> 0x0114 }
            boolean r13 = r13.booleanValue()     // Catch:{ Throwable -> 0x0114 }
            if (r13 == 0) goto L_0x0240
            java.lang.Object r13 = r4.next()     // Catch:{ Throwable -> 0x0114 }
            r2.L$0 = r8     // Catch:{ Throwable -> 0x0114 }
            r2.L$1 = r14     // Catch:{ Throwable -> 0x0114 }
            r2.L$2 = r11     // Catch:{ Throwable -> 0x0114 }
            r2.L$3 = r5     // Catch:{ Throwable -> 0x0114 }
            r2.L$4 = r6     // Catch:{ Throwable -> 0x0114 }
            r2.L$5 = r10     // Catch:{ Throwable -> 0x0114 }
            r2.L$6 = r9     // Catch:{ Throwable -> 0x0114 }
            r2.L$7 = r3     // Catch:{ Throwable -> 0x0114 }
            r2.L$8 = r4     // Catch:{ Throwable -> 0x0114 }
            r2.L$9 = r13     // Catch:{ Throwable -> 0x0114 }
            r2.L$10 = r13     // Catch:{ Throwable -> 0x0114 }
            r15 = 2
            r2.label = r15     // Catch:{ Throwable -> 0x0114 }
            java.lang.Object r7 = r11.hasNext(r2)     // Catch:{ Throwable -> 0x0114 }
            if (r7 != r0) goto L_0x01a0
            return r0
        L_0x01a0:
            r12 = r13
        L_0x01a1:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ Throwable -> 0x0114 }
            boolean r7 = r7.booleanValue()     // Catch:{ Throwable -> 0x0114 }
            if (r7 != 0) goto L_0x01b4
            r1 = r2
            r2 = r4
            r4 = r11
            r11 = r0
            r0 = r6
            r6 = r10
            r10 = r8
            r8 = r3
            r3 = 4
            goto L_0x0239
        L_0x01b4:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL     // Catch:{ Throwable -> 0x0114 }
            java.lang.Object r15 = r11.next()     // Catch:{ Throwable -> 0x0114 }
            if (r15 != r7) goto L_0x01be
            r7 = 0
            goto L_0x01bf
        L_0x01be:
            r7 = r15
        L_0x01bf:
            kotlinx.coroutines.flow.FlowCollector r15 = r2.$this_unsafeFlow     // Catch:{ Throwable -> 0x0114 }
            kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1 r1 = r2.this$0     // Catch:{ Throwable -> 0x0114 }
            kotlin.jvm.functions.Function3 r1 = r1.$transform$inlined     // Catch:{ Throwable -> 0x0114 }
            r16 = r0
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL     // Catch:{ Throwable -> 0x0114 }
            if (r13 != r0) goto L_0x01ce
            r17 = 0
            goto L_0x01d0
        L_0x01ce:
            r17 = r13
        L_0x01d0:
            kotlinx.coroutines.internal.Symbol r0 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL     // Catch:{ Throwable -> 0x0114 }
            if (r7 != r0) goto L_0x01d6
            r0 = 0
            goto L_0x01d7
        L_0x01d6:
            r0 = r7
        L_0x01d7:
            r2.L$0 = r8     // Catch:{ Throwable -> 0x0114 }
            r2.L$1 = r14     // Catch:{ Throwable -> 0x0114 }
            r2.L$2 = r11     // Catch:{ Throwable -> 0x0114 }
            r2.L$3 = r5     // Catch:{ Throwable -> 0x0114 }
            r2.L$4 = r6     // Catch:{ Throwable -> 0x0114 }
            r2.L$5 = r10     // Catch:{ Throwable -> 0x0114 }
            r2.L$6 = r9     // Catch:{ Throwable -> 0x0114 }
            r2.L$7 = r3     // Catch:{ Throwable -> 0x0114 }
            r2.L$8 = r4     // Catch:{ Throwable -> 0x0114 }
            r2.L$9 = r12     // Catch:{ Throwable -> 0x0114 }
            r2.L$10 = r13     // Catch:{ Throwable -> 0x0114 }
            r2.L$11 = r7     // Catch:{ Throwable -> 0x0114 }
            r2.L$12 = r15     // Catch:{ Throwable -> 0x0114 }
            r18 = r3
            r3 = 3
            r2.label = r3     // Catch:{ Throwable -> 0x0114 }
            r3 = r17
            java.lang.Object r0 = r1.invoke(r3, r0, r2)     // Catch:{ Throwable -> 0x0114 }
            r1 = r16
            if (r0 != r1) goto L_0x0201
            return r1
        L_0x0201:
            r3 = r12
            r12 = r5
            r5 = r1
            r1 = r2
            r2 = r4
            r4 = r13
            r13 = r11
            r11 = r6
            r6 = r8
            r8 = r18
        L_0x020c:
            r1.L$0 = r6     // Catch:{ Throwable -> 0x0114 }
            r1.L$1 = r14     // Catch:{ Throwable -> 0x0114 }
            r1.L$2 = r13     // Catch:{ Throwable -> 0x0114 }
            r1.L$3 = r12     // Catch:{ Throwable -> 0x0114 }
            r1.L$4 = r11     // Catch:{ Throwable -> 0x0114 }
            r1.L$5 = r10     // Catch:{ Throwable -> 0x0114 }
            r1.L$6 = r9     // Catch:{ Throwable -> 0x0114 }
            r1.L$7 = r8     // Catch:{ Throwable -> 0x0114 }
            r1.L$8 = r2     // Catch:{ Throwable -> 0x0114 }
            r1.L$9 = r3     // Catch:{ Throwable -> 0x0114 }
            r1.L$10 = r4     // Catch:{ Throwable -> 0x0114 }
            r1.L$11 = r7     // Catch:{ Throwable -> 0x0114 }
            r3 = 4
            r1.label = r3     // Catch:{ Throwable -> 0x0114 }
            java.lang.Object r0 = r15.emit(r0, r1)     // Catch:{ Throwable -> 0x0114 }
            if (r0 != r5) goto L_0x022e
            return r5
        L_0x022e:
            r0 = r5
            r15 = r6
        L_0x0230:
            r6 = r10
            r5 = r12
            r4 = r13
            r10 = r15
            r19 = r11
            r11 = r0
            r0 = r19
        L_0x0239:
            r7 = 0
            r3 = r2
            r2 = r1
            r1 = r21
            goto L_0x014e
        L_0x0240:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Throwable -> 0x0114 }
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r10, r9)     // Catch:{ AbortFlowException -> 0x0273, all -> 0x0261 }
            boolean r0 = r14.isClosedForReceive()
            if (r0 != 0) goto L_0x0280
            kotlinx.coroutines.flow.internal.AbortFlowException r0 = new kotlinx.coroutines.flow.internal.AbortFlowException
            r0.<init>()
        L_0x0250:
            java.util.concurrent.CancellationException r0 = (java.util.concurrent.CancellationException) r0
            r14.cancel(r0)
            goto L_0x0280
        L_0x0256:
            r0 = move-exception
            r10 = r6
            goto L_0x025d
        L_0x0259:
            r0 = move-exception
            r9 = r0
            r10 = r6
        L_0x025c:
            throw r9     // Catch:{ all -> 0x0111 }
        L_0x025d:
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r10, r9)     // Catch:{ AbortFlowException -> 0x0273, all -> 0x0261 }
            throw r0     // Catch:{ AbortFlowException -> 0x0273, all -> 0x0261 }
        L_0x0261:
            r0 = move-exception
            boolean r1 = r14.isClosedForReceive()
            if (r1 != 0) goto L_0x0272
            kotlinx.coroutines.flow.internal.AbortFlowException r1 = new kotlinx.coroutines.flow.internal.AbortFlowException
            r1.<init>()
            java.util.concurrent.CancellationException r1 = (java.util.concurrent.CancellationException) r1
            r14.cancel(r1)
        L_0x0272:
            throw r0
        L_0x0273:
            boolean r0 = r14.isClosedForReceive()
            if (r0 != 0) goto L_0x0280
            kotlinx.coroutines.flow.internal.AbortFlowException r0 = new kotlinx.coroutines.flow.internal.AbortFlowException
            r0.<init>()
            goto L_0x0250
        L_0x0280:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0283:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type kotlinx.coroutines.channels.SendChannel<*>"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$zip$$inlined$unsafeFlow$1$lambda$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
