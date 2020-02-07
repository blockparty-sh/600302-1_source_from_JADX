package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Builders.kt */
public final class FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1 implements Flow<T> {
    final /* synthetic */ Flow $this_delayFlow$inlined;
    final /* synthetic */ long $timeMillis$inlined;

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0086 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0087 A[PHI: r9 
      PHI: (r9v2 java.lang.Object) = (r9v4 java.lang.Object), (r9v1 java.lang.Object) binds: [B:19:0x0084, B:10:0x0029] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.FlowCollector r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1.C31841
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1.C31841) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0059
            if (r2 == r4) goto L_0x0045
            if (r2 != r3) goto L_0x003d
            java.lang.Object r8 = r0.L$3
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            java.lang.Object r8 = r0.L$2
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1 r8 = (kotlinx.coroutines.flow.FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0087
        L_0x003d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0045:
            java.lang.Object r8 = r0.L$3
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            java.lang.Object r2 = r0.L$2
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            java.lang.Object r4 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r4 = (kotlinx.coroutines.flow.FlowCollector) r4
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1 r5 = (kotlinx.coroutines.flow.FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1) r5
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0074
        L_0x0059:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r0
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            long r5 = r7.$timeMillis$inlined
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r2
            r0.L$3 = r8
            r0.label = r4
            java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r5, r0)
            if (r9 != r1) goto L_0x0072
            return r1
        L_0x0072:
            r5 = r7
            r4 = r8
        L_0x0074:
            kotlinx.coroutines.flow.Flow r9 = r5.$this_delayFlow$inlined
            r0.L$0 = r5
            r0.L$1 = r4
            r0.L$2 = r2
            r0.L$3 = r8
            r0.label = r3
            java.lang.Object r9 = r9.collect(r8, r0)
            if (r9 != r1) goto L_0x0087
            return r1
        L_0x0087:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public FlowKt__DelayKt$delayFlow$$inlined$unsafeFlow$1(Flow flow, long j) {
        this.$this_delayFlow$inlined = flow;
        this.$timeMillis$inlined = j;
    }
}
