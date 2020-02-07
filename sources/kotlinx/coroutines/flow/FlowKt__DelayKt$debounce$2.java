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
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2", mo38000f = "Delay.kt", mo38001i = {0, 0, 0, 0}, mo38002l = {162}, mo38003m = "invokeSuspend", mo38004n = {"values", "collector", "isDone", "lastValue"}, mo38005s = {"L$1", "L$2", "L$3", "L$4"})
/* compiled from: Delay.kt */
final class FlowKt__DelayKt$debounce$2 extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow $this_debounce;
    final /* synthetic */ long $timeoutMillis;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f758p$;
    private FlowCollector p$0;

    FlowKt__DelayKt$debounce$2(Flow flow, long j, Continuation continuation) {
        this.$this_debounce = flow;
        this.$timeoutMillis = j;
        super(3, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull CoroutineScope coroutineScope, @NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "$this$create");
        Intrinsics.checkParameterIsNotNull(flowCollector, "downstream");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        FlowKt__DelayKt$debounce$2 flowKt__DelayKt$debounce$2 = new FlowKt__DelayKt$debounce$2(this.$this_debounce, this.$timeoutMillis, continuation);
        flowKt__DelayKt$debounce$2.f758p$ = coroutineScope;
        flowKt__DelayKt$debounce$2.p$0 = flowCollector;
        return flowKt__DelayKt$debounce$2;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((FlowKt__DelayKt$debounce$2) create((CoroutineScope) obj, (FlowCollector) obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:13|14|15|16) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00eb, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00f2, code lost:
        r11.handleBuilderException(r0);
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x006d  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r24) {
        /*
            r23 = this;
            r1 = r23
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r2 = r1.L$5
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2 r2 = (kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2) r2
            java.lang.Object r2 = r1.L$4
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            java.lang.Object r4 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r4 = (kotlin.jvm.internal.Ref.BooleanRef) r4
            java.lang.Object r5 = r1.L$2
            kotlinx.coroutines.Deferred r5 = (kotlinx.coroutines.Deferred) r5
            java.lang.Object r6 = r1.L$1
            kotlinx.coroutines.channels.Channel r6 = (kotlinx.coroutines.channels.Channel) r6
            java.lang.Object r7 = r1.L$0
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            kotlin.ResultKt.throwOnFailure(r24)
            r8 = r0
            r10 = r6
            r6 = r5
            r5 = r4
            r4 = r7
            r7 = r2
            r2 = r1
            goto L_0x0108
        L_0x0031:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r24)
            kotlinx.coroutines.CoroutineScope r4 = r1.f758p$
            kotlinx.coroutines.flow.FlowCollector r2 = r1.p$0
            r5 = -1
            kotlinx.coroutines.channels.Channel r10 = kotlinx.coroutines.channels.ChannelKt.Channel(r5)
            r5 = 0
            r6 = 0
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$collector$1 r7 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$collector$1
            r11 = 0
            r7.<init>(r1, r10, r11)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 3
            r9 = 0
            kotlinx.coroutines.Deferred r4 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(r4, r5, r6, r7, r8, r9)
            kotlin.jvm.internal.Ref$BooleanRef r5 = new kotlin.jvm.internal.Ref$BooleanRef
            r5.<init>()
            r6 = 0
            r5.element = r6
            kotlin.jvm.internal.Ref$ObjectRef r6 = new kotlin.jvm.internal.Ref$ObjectRef
            r6.<init>()
            r6.element = r11
            r8 = r0
            r7 = r6
            r6 = r4
            r4 = r2
            r2 = r1
        L_0x0069:
            boolean r0 = r5.element
            if (r0 != 0) goto L_0x010b
            r2.L$0 = r4
            r2.L$1 = r10
            r2.L$2 = r6
            r2.L$3 = r5
            r2.L$4 = r7
            r2.L$5 = r2
            r2.label = r3
            r9 = r2
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            kotlinx.coroutines.selects.SelectBuilderImpl r11 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r11.<init>(r9)
            r0 = r11
            kotlinx.coroutines.selects.SelectBuilder r0 = (kotlinx.coroutines.selects.SelectBuilder) r0     // Catch:{ Throwable -> 0x00ed }
            kotlinx.coroutines.selects.SelectClause1 r15 = r10.getOnReceive()     // Catch:{ Throwable -> 0x00ed }
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$1 r20 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$1     // Catch:{ Throwable -> 0x00ed }
            r13 = 0
            r12 = r20
            r14 = r2
            r3 = r15
            r15 = r10
            r16 = r7
            r17 = r4
            r18 = r6
            r19 = r5
            r12.<init>(r13, r14, r15, r16, r17, r18, r19)     // Catch:{ Throwable -> 0x00ed }
            r12 = r20
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12     // Catch:{ Throwable -> 0x00ed }
            r0.invoke(r3, r12)     // Catch:{ Throwable -> 0x00ed }
            T r13 = r7.element     // Catch:{ Throwable -> 0x00ed }
            if (r13 == 0) goto L_0x00cc
            long r14 = r2.$timeoutMillis     // Catch:{ Throwable -> 0x00ed }
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$2 r3 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$2     // Catch:{ Throwable -> 0x00ed }
            r16 = 0
            r12 = r3
            r24 = r8
            r22 = r9
            r8 = r14
            r14 = r16
            r15 = r0
            r16 = r2
            r17 = r10
            r18 = r7
            r19 = r4
            r20 = r6
            r21 = r5
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ Throwable -> 0x00eb }
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3     // Catch:{ Throwable -> 0x00eb }
            r0.onTimeout(r8, r3)     // Catch:{ Throwable -> 0x00eb }
            goto L_0x00d0
        L_0x00cc:
            r24 = r8
            r22 = r9
        L_0x00d0:
            kotlinx.coroutines.selects.SelectClause1 r3 = r6.getOnAwait()     // Catch:{ Throwable -> 0x00eb }
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$3 r8 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$3     // Catch:{ Throwable -> 0x00eb }
            r13 = 0
            r12 = r8
            r14 = r2
            r15 = r10
            r16 = r7
            r17 = r4
            r18 = r6
            r19 = r5
            r12.<init>(r13, r14, r15, r16, r17, r18, r19)     // Catch:{ Throwable -> 0x00eb }
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8     // Catch:{ Throwable -> 0x00eb }
            r0.invoke(r3, r8)     // Catch:{ Throwable -> 0x00eb }
            goto L_0x00f5
        L_0x00eb:
            r0 = move-exception
            goto L_0x00f2
        L_0x00ed:
            r0 = move-exception
            r24 = r8
            r22 = r9
        L_0x00f2:
            r11.handleBuilderException(r0)
        L_0x00f5:
            java.lang.Object r0 = r11.getResult()
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r0 != r3) goto L_0x0102
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r22)
        L_0x0102:
            r3 = r24
            if (r0 != r3) goto L_0x0107
            return r3
        L_0x0107:
            r8 = r3
        L_0x0108:
            r3 = 1
            goto L_0x0069
        L_0x010b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
