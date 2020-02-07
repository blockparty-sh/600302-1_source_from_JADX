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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__ZipKt$combineLatest$7$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Zip.kt */
final class FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector $this_unsafeFlow;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f768p$;
    final /* synthetic */ FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2 this$0;

    FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1(FlowCollector flowCollector, Continuation continuation, FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2 flowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = flowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1 flowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1 = new FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1(this.$this_unsafeFlow, continuation, this.this$0);
        flowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1.f768p$ = (CoroutineScope) obj;
        return flowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x011e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x009b A[SYNTHETIC] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r24) {
        /*
            r23 = this;
            r1 = r23
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r4) goto L_0x002a
            java.lang.Object r2 = r1.L$3
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1 r2 = (kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1) r2
            java.lang.Object r2 = r1.L$2
            java.lang.Boolean[] r2 = (java.lang.Boolean[]) r2
            java.lang.Object r5 = r1.L$1
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            java.lang.Object r6 = r1.L$0
            kotlinx.coroutines.channels.ReceiveChannel[] r6 = (kotlinx.coroutines.channels.ReceiveChannel[]) r6
            int r7 = r1.I$0
            kotlin.ResultKt.throwOnFailure(r24)
            r9 = r0
            r8 = r5
            r5 = r7
            r7 = r2
            r2 = r1
            goto L_0x011f
        L_0x002a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r24)
            kotlinx.coroutines.CoroutineScope r2 = r1.f768p$
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2 r5 = r1.this$0
            kotlinx.coroutines.flow.Flow[] r5 = r5.$others$inlined
            int r5 = r5.length
            int r5 = r5 + r4
            kotlinx.coroutines.channels.ReceiveChannel[] r6 = new kotlinx.coroutines.channels.ReceiveChannel[r5]
            int r7 = r6.length
            r8 = 0
        L_0x0041:
            if (r8 >= r7) goto L_0x0065
            java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            if (r9 != 0) goto L_0x0054
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2 r9 = r1.this$0
            kotlinx.coroutines.flow.Flow r9 = r9.$this_combineLatest$inlined
            goto L_0x005c
        L_0x0054:
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2 r10 = r1.this$0
            kotlinx.coroutines.flow.Flow[] r10 = r10.$others$inlined
            int r9 = r9 + -1
            r9 = r10[r9]
        L_0x005c:
            kotlinx.coroutines.channels.ReceiveChannel r9 = kotlinx.coroutines.flow.FlowKt__ZipKt.asFairChannel$FlowKt__ZipKt(r2, r9)
            r6[r8] = r9
            int r8 = r8 + 1
            goto L_0x0041
        L_0x0065:
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.Boolean[] r7 = new java.lang.Boolean[r5]
            int r8 = r7.length
            r9 = 0
        L_0x006b:
            if (r9 >= r8) goto L_0x007f
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            java.lang.Number r10 = (java.lang.Number) r10
            r10.intValue()
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            r7[r9] = r10
            int r9 = r9 + 1
            goto L_0x006b
        L_0x007f:
            r9 = r0
            r8 = r2
            r2 = r1
        L_0x0082:
            int r0 = r7.length
            r10 = 0
        L_0x0084:
            if (r10 >= r0) goto L_0x009b
            r11 = r7[r10]
            boolean r11 = r11.booleanValue()
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)
            boolean r11 = r11.booleanValue()
            if (r11 != 0) goto L_0x0098
            r0 = 0
            goto L_0x009c
        L_0x0098:
            int r10 = r10 + 1
            goto L_0x0084
        L_0x009b:
            r0 = 1
        L_0x009c:
            if (r0 != 0) goto L_0x0123
            r2.I$0 = r5
            r2.L$0 = r6
            r2.L$1 = r8
            r2.L$2 = r7
            r2.L$3 = r2
            r2.label = r4
            r15 = r2
            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
            kotlinx.coroutines.selects.SelectBuilderImpl r14 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r14.<init>(r15)
            r0 = r14
            kotlinx.coroutines.selects.SelectBuilder r0 = (kotlinx.coroutines.selects.SelectBuilder) r0     // Catch:{ Throwable -> 0x0108 }
            r13 = 0
        L_0x00b6:
            if (r13 >= r5) goto L_0x0104
            r10 = r7[r13]     // Catch:{ Throwable -> 0x0108 }
            boolean r18 = r10.booleanValue()     // Catch:{ Throwable -> 0x0108 }
            r19 = r6[r13]     // Catch:{ Throwable -> 0x0108 }
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1$1 r20 = new kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1$1     // Catch:{ Throwable -> 0x0108 }
            r12 = 0
            r10 = r20
            r11 = r13
            r21 = r13
            r13 = r2
            r3 = r14
            r14 = r5
            r22 = r15
            r15 = r7
            r16 = r6
            r17 = r8
            r10.<init>(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ Throwable -> 0x0102 }
            r11 = r20
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11     // Catch:{ Throwable -> 0x0102 }
            if (r18 == 0) goto L_0x00dc
            goto L_0x00fa
        L_0x00dc:
            kotlinx.coroutines.selects.SelectClause1 r15 = r19.getOnReceiveOrNull()     // Catch:{ Throwable -> 0x0102 }
            kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1$2 r19 = new kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1$2     // Catch:{ Throwable -> 0x0102 }
            r12 = 0
            r10 = r19
            r13 = r21
            r14 = r2
            r4 = r15
            r15 = r5
            r16 = r7
            r17 = r6
            r18 = r8
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ Throwable -> 0x0102 }
            r10 = r19
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10     // Catch:{ Throwable -> 0x0102 }
            r0.invoke(r4, r10)     // Catch:{ Throwable -> 0x0102 }
        L_0x00fa:
            int r13 = r21 + 1
            r14 = r3
            r15 = r22
            r3 = 0
            r4 = 1
            goto L_0x00b6
        L_0x0102:
            r0 = move-exception
            goto L_0x010c
        L_0x0104:
            r3 = r14
            r22 = r15
            goto L_0x010f
        L_0x0108:
            r0 = move-exception
            r3 = r14
            r22 = r15
        L_0x010c:
            r3.handleBuilderException(r0)
        L_0x010f:
            java.lang.Object r0 = r3.getResult()
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r0 != r3) goto L_0x011c
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r22)
        L_0x011c:
            if (r0 != r9) goto L_0x011f
            return r9
        L_0x011f:
            r3 = 0
            r4 = 1
            goto L_0x0082
        L_0x0123:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combineLatest$$inlined$unsafeFlow$2$lambda$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
