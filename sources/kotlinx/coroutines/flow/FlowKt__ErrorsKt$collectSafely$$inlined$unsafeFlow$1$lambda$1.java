package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.internal.Ref.BooleanRef;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0007"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$2", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__ErrorsKt$$special$$inlined$collect$2"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Collect.kt */
public final class FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$lambda$1 implements FlowCollector<T> {
    final /* synthetic */ BooleanRef $fromDownstream$inlined;
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;

    public FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, BooleanRef booleanRef) {
        this.$this_unsafeFlow$inlined = flowCollector;
        this.$fromDownstream$inlined = booleanRef;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$lambda$1.C31911
            if (r0 == 0) goto L_0x0014
            r0 = r6
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$lambda$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$lambda$1.C31911) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$lambda$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$lambda$1$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r5 = r0.L$3
            java.lang.Object r5 = r0.L$2
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            java.lang.Object r5 = r0.L$1
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$lambda$1 r5 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$lambda$1) r5
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ Throwable -> 0x0036 }
            goto L_0x0059
        L_0x0036:
            r6 = move-exception
            goto L_0x005e
        L_0x0038:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r6)
            r6 = r0
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            kotlinx.coroutines.flow.FlowCollector r2 = r4.$this_unsafeFlow$inlined     // Catch:{ Throwable -> 0x005c }
            r0.L$0 = r4     // Catch:{ Throwable -> 0x005c }
            r0.L$1 = r5     // Catch:{ Throwable -> 0x005c }
            r0.L$2 = r6     // Catch:{ Throwable -> 0x005c }
            r0.L$3 = r5     // Catch:{ Throwable -> 0x005c }
            r0.label = r3     // Catch:{ Throwable -> 0x005c }
            java.lang.Object r5 = r2.emit(r5, r0)     // Catch:{ Throwable -> 0x005c }
            if (r5 != r1) goto L_0x0059
            return r1
        L_0x0059:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x005c:
            r6 = move-exception
            r5 = r4
        L_0x005e:
            kotlin.jvm.internal.Ref$BooleanRef r5 = r5.$fromDownstream$inlined
            r5.element = r3
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt$collectSafely$$inlined$unsafeFlow$1$lambda$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
