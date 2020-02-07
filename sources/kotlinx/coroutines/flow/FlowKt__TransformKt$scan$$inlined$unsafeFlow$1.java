package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Builders.kt */
public final class FlowKt__TransformKt$scan$$inlined$unsafeFlow$1 implements Flow<R> {
    final /* synthetic */ Object $initial$inlined;
    final /* synthetic */ Function3 $operation$inlined;
    final /* synthetic */ Flow $this_scan$inlined;

    public FlowKt__TransformKt$scan$$inlined$unsafeFlow$1(Flow flow, Object obj, Function3 function3) {
        this.$this_scan$inlined = flow;
        this.$initial$inlined = obj;
        this.$operation$inlined = function3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00ac A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00ad A[PHI: r11 
      PHI: (r11v2 java.lang.Object) = (r11v4 java.lang.Object), (r11v1 java.lang.Object) binds: [B:19:0x00aa, B:10:0x0029] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.FlowCollector r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1.C32461
            if (r0 == 0) goto L_0x0014
            r0 = r11
            kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1.C32461) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$1
            r0.<init>(r9, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0069
            if (r2 == r4) goto L_0x004d
            if (r2 != r3) goto L_0x0045
            java.lang.Object r10 = r0.L$5
            kotlinx.coroutines.flow.Flow r10 = (kotlinx.coroutines.flow.Flow) r10
            java.lang.Object r10 = r0.L$4
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r10 = r0.L$3
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            java.lang.Object r10 = r0.L$2
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            java.lang.Object r10 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
            java.lang.Object r10 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1 r10 = (kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00ad
        L_0x0045:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x004d:
            java.lang.Object r10 = r0.L$4
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
            java.lang.Object r4 = r0.L$2
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1 r6 = (kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1) r6
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r4
            r8 = r2
            r2 = r10
            r10 = r8
            goto L_0x008f
        L_0x0069:
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r0
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            kotlin.jvm.internal.Ref$ObjectRef r2 = new kotlin.jvm.internal.Ref$ObjectRef
            r2.<init>()
            java.lang.Object r5 = r9.$initial$inlined
            r2.element = r5
            T r5 = r2.element
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r11
            r0.L$3 = r10
            r0.L$4 = r2
            r0.label = r4
            java.lang.Object r4 = r10.emit(r5, r0)
            if (r4 != r1) goto L_0x008d
            return r1
        L_0x008d:
            r6 = r9
            r5 = r10
        L_0x008f:
            kotlinx.coroutines.flow.Flow r4 = r6.$this_scan$inlined
            kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1 r7 = new kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1$lambda$1
            r7.<init>(r10, r2, r6)
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r11
            r0.L$3 = r10
            r0.L$4 = r2
            r0.L$5 = r4
            r0.label = r3
            java.lang.Object r11 = r4.collect(r7, r0)
            if (r11 != r1) goto L_0x00ad
            return r1
        L_0x00ad:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$scan$$inlined$unsafeFlow$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
