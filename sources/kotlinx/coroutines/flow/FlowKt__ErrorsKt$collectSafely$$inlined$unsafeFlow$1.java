package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Builders.kt */
public final class FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1 implements Flow<T> {
    final /* synthetic */ Function3 $onException$inlined;
    final /* synthetic */ Flow $this_collectSafely$inlined;

    public FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1(Flow flow, Function3 function3) {
        this.$this_collectSafely$inlined = flow;
        this.$onException$inlined = function3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f7 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.FlowCollector r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1.C31901
            if (r0 == 0) goto L_0x0014
            r0 = r13
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1.C31901) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$1
            r0.<init>(r11, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0093
            if (r2 == r5) goto L_0x006e
            if (r2 == r4) goto L_0x0051
            if (r2 != r3) goto L_0x0049
            java.lang.Object r12 = r0.L$5
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            java.lang.Object r12 = r0.L$4
            kotlin.jvm.internal.Ref$BooleanRef r12 = (kotlin.jvm.internal.Ref.BooleanRef) r12
            java.lang.Object r12 = r0.L$3
            kotlinx.coroutines.flow.FlowCollector r12 = (kotlinx.coroutines.flow.FlowCollector) r12
            java.lang.Object r12 = r0.L$2
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            java.lang.Object r12 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r12 = (kotlinx.coroutines.flow.FlowCollector) r12
            java.lang.Object r12 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1 r12 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1) r12
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00f8
        L_0x0049:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0051:
            java.lang.Object r12 = r0.L$5
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            java.lang.Object r12 = r0.L$4
            kotlin.jvm.internal.Ref$BooleanRef r12 = (kotlin.jvm.internal.Ref.BooleanRef) r12
            java.lang.Object r12 = r0.L$3
            kotlinx.coroutines.flow.FlowCollector r12 = (kotlinx.coroutines.flow.FlowCollector) r12
            java.lang.Object r12 = r0.L$2
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            java.lang.Object r12 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r12 = (kotlinx.coroutines.flow.FlowCollector) r12
            java.lang.Object r12 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1 r12 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1) r12
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00f5
        L_0x006e:
            java.lang.Object r12 = r0.L$5
            kotlinx.coroutines.flow.Flow r12 = (kotlinx.coroutines.flow.Flow) r12
            java.lang.Object r12 = r0.L$4
            kotlin.jvm.internal.Ref$BooleanRef r12 = (kotlin.jvm.internal.Ref.BooleanRef) r12
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
            java.lang.Object r5 = r0.L$2
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1 r7 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1) r7
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ Throwable -> 0x008b }
            goto L_0x00f8
        L_0x008b:
            r13 = move-exception
            r9 = r2
            r2 = r12
            r12 = r9
            r10 = r5
            r5 = r13
            r13 = r10
            goto L_0x00c2
        L_0x0093:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r0
            kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
            kotlin.jvm.internal.Ref$BooleanRef r2 = new kotlin.jvm.internal.Ref$BooleanRef
            r2.<init>()
            r6 = 0
            r2.element = r6
            kotlinx.coroutines.flow.Flow r6 = r11.$this_collectSafely$inlined     // Catch:{ Throwable -> 0x00bf }
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$lambda$1 r7 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$lambda$1     // Catch:{ Throwable -> 0x00bf }
            r7.<init>(r12, r2)     // Catch:{ Throwable -> 0x00bf }
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7     // Catch:{ Throwable -> 0x00bf }
            r0.L$0 = r11     // Catch:{ Throwable -> 0x00bf }
            r0.L$1 = r12     // Catch:{ Throwable -> 0x00bf }
            r0.L$2 = r13     // Catch:{ Throwable -> 0x00bf }
            r0.L$3 = r12     // Catch:{ Throwable -> 0x00bf }
            r0.L$4 = r2     // Catch:{ Throwable -> 0x00bf }
            r0.L$5 = r6     // Catch:{ Throwable -> 0x00bf }
            r0.label = r5     // Catch:{ Throwable -> 0x00bf }
            java.lang.Object r12 = r6.collect(r7, r0)     // Catch:{ Throwable -> 0x00bf }
            if (r12 != r1) goto L_0x00f8
            return r1
        L_0x00bf:
            r5 = move-exception
            r7 = r11
            r6 = r12
        L_0x00c2:
            boolean r8 = r2.element
            if (r8 != 0) goto L_0x00fb
            kotlin.coroutines.CoroutineContext r8 = r0.getContext()
            boolean r8 = kotlinx.coroutines.flow.FlowKt__ErrorsKt.isCancellationCause$FlowKt__ErrorsKt(r5, r8)
            if (r8 != 0) goto L_0x00fb
            kotlin.jvm.functions.Function3 r8 = r7.$onException$inlined
            r0.L$0 = r7
            r0.L$1 = r6
            r0.L$2 = r13
            r0.L$3 = r12
            r0.L$4 = r2
            r0.L$5 = r5
            r0.label = r3
            r0.L$0 = r7
            r0.L$1 = r6
            r0.L$2 = r13
            r0.L$3 = r12
            r0.L$4 = r2
            r0.L$5 = r5
            r0.label = r4
            java.lang.Object r13 = r8.invoke(r12, r5, r0)
            if (r13 != r1) goto L_0x00f5
            return r1
        L_0x00f5:
            if (r13 != r1) goto L_0x00f8
            return r1
        L_0x00f8:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x00fb:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
