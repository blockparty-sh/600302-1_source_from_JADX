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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo37405d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combineLatest$1$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Zip.kt */
final class FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector $this_unsafeFlow;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f767p$;
    final /* synthetic */ FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1 this$0;

    FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, Continuation continuation, FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1 flowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = flowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1 flowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1 = new FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1(this.$this_unsafeFlow, continuation, this.this$0);
        flowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1.f767p$ = (CoroutineScope) obj;
        return flowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(19:0|(1:(2:3|37)(2:4|5))(1:6)|7|(14:13|14|15|16|17|18|(1:20)(1:21)|22|(1:24)(1:25)|30|(1:32)|33|(1:35)(17:36|37|7|(1:9)|13|14|15|16|17|18|(0)(0)|22|(0)(0)|30|(0)|33|(0))|35)(2:11|12)|9|13|14|15|16|17|18|(0)(0)|22|(0)(0)|30|(0)|33|(0)(0)|35) */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0124, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0126, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0127, code lost:
        r3 = r13;
        r21 = r14;
        r22 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x012c, code lost:
        r3.handleBuilderException(r0);
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00c1 A[Catch:{ Throwable -> 0x0124 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00c2 A[Catch:{ Throwable -> 0x0124 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0100 A[Catch:{ Throwable -> 0x0124 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0101 A[Catch:{ Throwable -> 0x0124 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0140 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0141  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r27) {
        /*
            r26 = this;
            r1 = r26
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r2 = r1.L$6
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1 r2 = (kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1) r2
            java.lang.Object r2 = r1.L$5
            kotlin.jvm.internal.Ref$BooleanRef r2 = (kotlin.jvm.internal.Ref.BooleanRef) r2
            java.lang.Object r4 = r1.L$4
            kotlin.jvm.internal.Ref$BooleanRef r4 = (kotlin.jvm.internal.Ref.BooleanRef) r4
            java.lang.Object r5 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r5 = (kotlin.jvm.internal.Ref.ObjectRef) r5
            java.lang.Object r6 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r6 = (kotlin.jvm.internal.Ref.ObjectRef) r6
            java.lang.Object r7 = r1.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            kotlin.ResultKt.throwOnFailure(r27)
            r15 = r0
            r9 = r2
            r2 = r1
            r25 = r6
            r6 = r4
            r4 = r8
            r8 = r5
            r5 = r7
            r7 = r25
            goto L_0x0142
        L_0x0039:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r27)
            kotlinx.coroutines.CoroutineScope r2 = r1.f767p$
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1 r4 = r1.this$0
            kotlinx.coroutines.flow.Flow r4 = r4.$this_combineLatest$inlined
            kotlinx.coroutines.channels.ReceiveChannel r4 = kotlinx.coroutines.flow.FlowKt__ZipKt.asFairChannel$FlowKt__ZipKt(r2, r4)
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1 r5 = r1.this$0
            kotlinx.coroutines.flow.Flow r5 = r5.$other$inlined
            kotlinx.coroutines.channels.ReceiveChannel r2 = kotlinx.coroutines.flow.FlowKt__ZipKt.asFairChannel$FlowKt__ZipKt(r2, r5)
            kotlin.jvm.internal.Ref$ObjectRef r5 = new kotlin.jvm.internal.Ref$ObjectRef
            r5.<init>()
            r6 = 0
            r5.element = r6
            kotlin.jvm.internal.Ref$ObjectRef r7 = new kotlin.jvm.internal.Ref$ObjectRef
            r7.<init>()
            r7.element = r6
            kotlin.jvm.internal.Ref$BooleanRef r6 = new kotlin.jvm.internal.Ref$BooleanRef
            r6.<init>()
            r8 = 0
            r6.element = r8
            kotlin.jvm.internal.Ref$BooleanRef r9 = new kotlin.jvm.internal.Ref$BooleanRef
            r9.<init>()
            r9.element = r8
            r15 = r0
            r8 = r7
            r7 = r5
            r5 = r2
            r2 = r1
        L_0x0079:
            boolean r0 = r6.element
            if (r0 == 0) goto L_0x0085
            boolean r0 = r9.element
            if (r0 != 0) goto L_0x0082
            goto L_0x0085
        L_0x0082:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0085:
            r2.L$0 = r4
            r2.L$1 = r5
            r2.L$2 = r7
            r2.L$3 = r8
            r2.L$4 = r6
            r2.L$5 = r9
            r2.L$6 = r2
            r2.label = r3
            r14 = r2
            kotlin.coroutines.Continuation r14 = (kotlin.coroutines.Continuation) r14
            kotlinx.coroutines.selects.SelectBuilderImpl r13 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r13.<init>(r14)
            r0 = r13
            kotlinx.coroutines.selects.SelectBuilder r0 = (kotlinx.coroutines.selects.SelectBuilder) r0     // Catch:{ Throwable -> 0x0126 }
            boolean r12 = r6.element     // Catch:{ Throwable -> 0x0126 }
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1$1 r19 = new kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1$1     // Catch:{ Throwable -> 0x0126 }
            r11 = 0
            r10 = r19
            r20 = r12
            r12 = r2
            r3 = r13
            r13 = r6
            r21 = r14
            r14 = r4
            r22 = r15
            r15 = r7
            r16 = r8
            r17 = r9
            r18 = r5
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ Throwable -> 0x0124 }
            r11 = r19
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11     // Catch:{ Throwable -> 0x0124 }
            if (r20 == 0) goto L_0x00c2
            goto L_0x00e4
        L_0x00c2:
            kotlinx.coroutines.selects.SelectClause1 r15 = r4.getOnReceiveOrNull()     // Catch:{ Throwable -> 0x0124 }
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1$2 r20 = new kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1$2     // Catch:{ Throwable -> 0x0124 }
            r12 = 0
            r10 = r20
            r13 = r2
            r14 = r6
            r23 = r15
            r15 = r4
            r16 = r7
            r17 = r8
            r18 = r9
            r19 = r5
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ Throwable -> 0x0124 }
            r10 = r20
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10     // Catch:{ Throwable -> 0x0124 }
            r11 = r23
            r0.invoke(r11, r10)     // Catch:{ Throwable -> 0x0124 }
        L_0x00e4:
            boolean r15 = r9.element     // Catch:{ Throwable -> 0x0124 }
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1$3 r19 = new kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1$3     // Catch:{ Throwable -> 0x0124 }
            r11 = 0
            r10 = r19
            r12 = r2
            r13 = r6
            r14 = r4
            r20 = r15
            r15 = r7
            r16 = r8
            r17 = r9
            r18 = r5
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ Throwable -> 0x0124 }
            r11 = r19
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11     // Catch:{ Throwable -> 0x0124 }
            if (r20 == 0) goto L_0x0101
            goto L_0x012f
        L_0x0101:
            kotlinx.coroutines.selects.SelectClause1 r15 = r5.getOnReceiveOrNull()     // Catch:{ Throwable -> 0x0124 }
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1$4 r20 = new kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1$4     // Catch:{ Throwable -> 0x0124 }
            r12 = 0
            r10 = r20
            r13 = r2
            r14 = r6
            r24 = r15
            r15 = r4
            r16 = r7
            r17 = r8
            r18 = r9
            r19 = r5
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ Throwable -> 0x0124 }
            r10 = r20
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10     // Catch:{ Throwable -> 0x0124 }
            r11 = r24
            r0.invoke(r11, r10)     // Catch:{ Throwable -> 0x0124 }
            goto L_0x012f
        L_0x0124:
            r0 = move-exception
            goto L_0x012c
        L_0x0126:
            r0 = move-exception
            r3 = r13
            r21 = r14
            r22 = r15
        L_0x012c:
            r3.handleBuilderException(r0)
        L_0x012f:
            java.lang.Object r0 = r3.getResult()
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r0 != r3) goto L_0x013c
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r21)
        L_0x013c:
            r3 = r22
            if (r0 != r3) goto L_0x0141
            return r3
        L_0x0141:
            r15 = r3
        L_0x0142:
            r3 = 1
            goto L_0x0079
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$1$lambda$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
