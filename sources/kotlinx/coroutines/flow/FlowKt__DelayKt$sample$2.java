package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, mo37405d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2", mo38000f = "Delay.kt", mo38001i = {0, 0, 0, 0}, mo38002l = {162}, mo38003m = "invokeSuspend", mo38004n = {"values", "isDone", "lastValue", "ticker"}, mo38005s = {"L$1", "L$2", "L$3", "L$4"})
/* compiled from: Delay.kt */
final class FlowKt__DelayKt$sample$2 extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $periodMillis;
    final /* synthetic */ Flow $this_sample;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f761p$;
    private FlowCollector p$0;

    FlowKt__DelayKt$sample$2(Flow flow, long j, Continuation continuation) {
        this.$this_sample = flow;
        this.$periodMillis = j;
        super(3, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull CoroutineScope coroutineScope, @NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "$this$create");
        Intrinsics.checkParameterIsNotNull(flowCollector, "downstream");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        FlowKt__DelayKt$sample$2 flowKt__DelayKt$sample$2 = new FlowKt__DelayKt$sample$2(this.$this_sample, this.$periodMillis, continuation);
        flowKt__DelayKt$sample$2.f761p$ = coroutineScope;
        flowKt__DelayKt$sample$2.p$0 = flowCollector;
        return flowKt__DelayKt$sample$2;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((FlowKt__DelayKt$sample$2) create((CoroutineScope) obj, (FlowCollector) obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:9|(2:10|11)|12|13|14|15|21|(1:23)|(1:25)(4:26|27|7|(1:28)(0))|25) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00c4, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00c5, code lost:
        r3 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00cd, code lost:
        r3.handleBuilderException(r0);
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0072  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r20) {
        /*
            r19 = this;
            r1 = r19
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r2 = r1.L$5
            kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2 r2 = (kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2) r2
            java.lang.Object r2 = r1.L$4
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r4 = (kotlin.jvm.internal.Ref.ObjectRef) r4
            java.lang.Object r5 = r1.L$2
            kotlin.jvm.internal.Ref$BooleanRef r5 = (kotlin.jvm.internal.Ref.BooleanRef) r5
            java.lang.Object r6 = r1.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r7 = r1.L$0
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            kotlin.ResultKt.throwOnFailure(r20)
            r10 = r0
            r15 = r2
            r14 = r4
            r13 = r5
            r12 = r6
            r11 = r7
            r2 = r1
            goto L_0x00e1
        L_0x0031:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r20)
            kotlinx.coroutines.CoroutineScope r2 = r1.f761p$
            kotlinx.coroutines.flow.FlowCollector r11 = r1.p$0
            r5 = 0
            r6 = -1
            kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$values$1 r4 = new kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$values$1
            r10 = 0
            r4.<init>(r1, r10)
            r7 = r4
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 1
            r9 = 0
            r4 = r2
            kotlinx.coroutines.channels.ReceiveChannel r12 = kotlinx.coroutines.channels.ProduceKt.produce$default(r4, r5, r6, r7, r8, r9)
            kotlin.jvm.internal.Ref$BooleanRef r13 = new kotlin.jvm.internal.Ref$BooleanRef
            r13.<init>()
            r4 = 0
            r13.element = r4
            kotlin.jvm.internal.Ref$ObjectRef r14 = new kotlin.jvm.internal.Ref$ObjectRef
            r14.<init>()
            r14.element = r10
            long r5 = r1.$periodMillis
            r7 = 0
            r9 = 2
            r4 = r2
            kotlinx.coroutines.channels.ReceiveChannel r2 = kotlinx.coroutines.flow.FlowKt__DelayKt.fixedPeriodTicker$default(r4, r5, r7, r9, r10)
            r10 = r0
            r15 = r2
            r2 = r1
        L_0x006e:
            boolean r0 = r13.element
            if (r0 != 0) goto L_0x00e5
            r2.L$0 = r11
            r2.L$1 = r12
            r2.L$2 = r13
            r2.L$3 = r14
            r2.L$4 = r15
            r2.L$5 = r2
            r2.label = r3
            r9 = r2
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            kotlinx.coroutines.selects.SelectBuilderImpl r8 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r8.<init>(r9)
            r0 = r8
            kotlinx.coroutines.selects.SelectBuilder r0 = (kotlinx.coroutines.selects.SelectBuilder) r0     // Catch:{ Throwable -> 0x00c8 }
            kotlinx.coroutines.selects.SelectClause1 r7 = r12.getOnReceiveOrNull()     // Catch:{ Throwable -> 0x00c8 }
            kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$1 r16 = new kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$1     // Catch:{ Throwable -> 0x00c8 }
            r5 = 0
            r4 = r16
            r6 = r12
            r3 = r7
            r7 = r15
            r17 = r8
            r8 = r13
            r18 = r9
            r9 = r14
            r1 = r10
            r10 = r11
            r4.<init>(r5, r6, r7, r8, r9, r10)     // Catch:{ Throwable -> 0x00c4 }
            r4 = r16
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4     // Catch:{ Throwable -> 0x00c4 }
            r0.invoke(r3, r4)     // Catch:{ Throwable -> 0x00c4 }
            kotlinx.coroutines.selects.SelectClause1 r3 = r15.getOnReceive()     // Catch:{ Throwable -> 0x00c4 }
            kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$2 r16 = new kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$invokeSuspend$$inlined$select$lambda$2     // Catch:{ Throwable -> 0x00c4 }
            r5 = 0
            r4 = r16
            r6 = r12
            r7 = r15
            r8 = r13
            r9 = r14
            r10 = r11
            r4.<init>(r5, r6, r7, r8, r9, r10)     // Catch:{ Throwable -> 0x00c4 }
            r4 = r16
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4     // Catch:{ Throwable -> 0x00c4 }
            r0.invoke(r3, r4)     // Catch:{ Throwable -> 0x00c4 }
            r3 = r17
            goto L_0x00d0
        L_0x00c4:
            r0 = move-exception
            r3 = r17
            goto L_0x00cd
        L_0x00c8:
            r0 = move-exception
            r18 = r9
            r1 = r10
            r3 = r8
        L_0x00cd:
            r3.handleBuilderException(r0)
        L_0x00d0:
            java.lang.Object r0 = r3.getResult()
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r0 != r3) goto L_0x00dd
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r18)
        L_0x00dd:
            if (r0 != r1) goto L_0x00e0
            return r1
        L_0x00e0:
            r10 = r1
        L_0x00e1:
            r3 = 1
            r1 = r19
            goto L_0x006e
        L_0x00e5:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
