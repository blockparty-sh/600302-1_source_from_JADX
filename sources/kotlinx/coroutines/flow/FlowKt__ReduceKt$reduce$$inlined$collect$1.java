package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref.ObjectRef;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$2", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Collect.kt */
public final class FlowKt__ReduceKt$reduce$$inlined$collect$1 implements FlowCollector<T> {
    final /* synthetic */ ObjectRef $accumulator$inlined;
    final /* synthetic */ Function3 $operation$inlined;

    /* JADX WARNING: Removed duplicated region for block: B:15:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0091 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1.C32251
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1.C32251) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x005b
            if (r2 == r4) goto L_0x0047
            if (r2 != r3) goto L_0x003f
            java.lang.Object r8 = r0.L$4
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r1 = r0.L$3
            java.lang.Object r1 = r0.L$2
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            java.lang.Object r1 = r0.L$1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1) r0
            kotlin.ResultKt.throwOnFailure(r9)
        L_0x003c:
            r2 = r8
            r8 = r9
            goto L_0x0092
        L_0x003f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0047:
            java.lang.Object r8 = r0.L$4
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r2 = r0.L$3
            java.lang.Object r2 = r0.L$2
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            java.lang.Object r2 = r0.L$1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x008f
        L_0x005b:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            kotlin.jvm.internal.Ref$ObjectRef r2 = r7.$accumulator$inlined
            T r5 = r2.element
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r5 == r6) goto L_0x0092
            kotlin.jvm.functions.Function3 r5 = r7.$operation$inlined
            kotlin.jvm.internal.Ref$ObjectRef r6 = r7.$accumulator$inlined
            T r6 = r6.element
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r8
            r0.L$4 = r2
            r0.label = r3
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r8
            r0.L$4 = r2
            r0.label = r4
            java.lang.Object r9 = r5.invoke(r6, r8, r0)
            if (r9 != r1) goto L_0x008e
            return r1
        L_0x008e:
            r8 = r2
        L_0x008f:
            if (r9 != r1) goto L_0x003c
            return r1
        L_0x0092:
            r2.element = r8
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public FlowKt__ReduceKt$reduce$$inlined$collect$1(ObjectRef objectRef, Function3 function3) {
        this.$accumulator$inlined = objectRef;
        this.$operation$inlined = function3;
    }
}
