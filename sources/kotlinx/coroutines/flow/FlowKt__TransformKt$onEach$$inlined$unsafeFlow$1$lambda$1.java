package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$2", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__TransformKt$$special$$inlined$collect$8"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Collect.kt */
public final class FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1 implements FlowCollector<T> {
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1 this$0;

    public FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1 flowKt__TransformKt$onEach$$inlined$unsafeFlow$1) {
        this.$this_unsafeFlow$inlined = flowCollector;
        this.this$0 = flowKt__TransformKt$onEach$$inlined$unsafeFlow$1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0090 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1.C32451
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1.C32451) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0064
            if (r2 == r5) goto L_0x0054
            if (r2 == r4) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r8 = r0.L$3
            java.lang.Object r8 = r0.L$2
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            java.lang.Object r8 = r0.L$1
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1 r8 = (kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00a4
        L_0x003c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0044:
            java.lang.Object r8 = r0.L$3
            java.lang.Object r2 = r0.L$2
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            java.lang.Object r4 = r0.L$1
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1 r5 = (kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1) r5
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0091
        L_0x0054:
            java.lang.Object r8 = r0.L$3
            java.lang.Object r2 = r0.L$2
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            java.lang.Object r4 = r0.L$1
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1 r5 = (kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1) r5
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x008e
        L_0x0064:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1 r2 = r7.this$0
            kotlin.jvm.functions.Function2 r2 = r2.$action$inlined
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r8
            r0.label = r4
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r8
            r0.label = r5
            java.lang.Object r2 = r2.invoke(r8, r0)
            if (r2 != r1) goto L_0x0089
            return r1
        L_0x0089:
            r5 = r7
            r4 = r8
            r6 = r2
            r2 = r9
            r9 = r6
        L_0x008e:
            if (r9 != r1) goto L_0x0091
            return r1
        L_0x0091:
            kotlinx.coroutines.flow.FlowCollector r9 = r5.$this_unsafeFlow$inlined
            r0.L$0 = r5
            r0.L$1 = r4
            r0.L$2 = r2
            r0.L$3 = r8
            r0.label = r3
            java.lang.Object r8 = r9.emit(r8, r0)
            if (r8 != r1) goto L_0x00a4
            return r1
        L_0x00a4:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeFlow$1$lambda$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
