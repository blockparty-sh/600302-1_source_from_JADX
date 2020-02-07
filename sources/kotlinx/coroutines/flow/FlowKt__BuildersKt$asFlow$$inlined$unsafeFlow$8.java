package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Builders.kt */
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8 implements Flow<Long> {
    final /* synthetic */ long[] $this_asFlow$inlined;

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8(long[] jArr) {
        this.$this_asFlow$inlined = jArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.FlowCollector r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            boolean r2 = r1 instanceof kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8.C31691
            if (r2 == 0) goto L_0x0018
            r2 = r1
            kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$1 r2 = (kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8.C31691) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$1 r2 = new kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L_0x005c
            if (r4 != r5) goto L_0x0054
            long r6 = r2.J$1
            long r6 = r2.J$0
            int r4 = r2.I$1
            int r6 = r2.I$0
            java.lang.Object r7 = r2.L$5
            long[] r7 = (long[]) r7
            java.lang.Object r8 = r2.L$4
            long[] r8 = (long[]) r8
            java.lang.Object r9 = r2.L$3
            kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
            java.lang.Object r10 = r2.L$2
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            java.lang.Object r11 = r2.L$1
            kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
            java.lang.Object r12 = r2.L$0
            kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8 r12 = (kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8) r12
            kotlin.ResultKt.throwOnFailure(r1)
            r1 = r9
            r9 = r8
            r8 = r11
            r11 = r3
            r3 = r12
            r12 = 1
            goto L_0x009f
        L_0x0054:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x005c:
            kotlin.ResultKt.throwOnFailure(r1)
            r1 = r2
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            long[] r4 = r0.$this_asFlow$inlined
            int r6 = r4.length
            r7 = 0
            r8 = r17
            r10 = r1
            r11 = r3
            r7 = r4
            r9 = r7
            r4 = 0
            r1 = r8
            r3 = r0
        L_0x006f:
            if (r4 >= r6) goto L_0x00a2
            r12 = r7[r4]
            java.lang.Long r14 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r12)
            java.lang.Number r14 = (java.lang.Number) r14
            long r14 = r14.longValue()
            java.lang.Long r5 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r14)
            r2.L$0 = r3
            r2.L$1 = r8
            r2.L$2 = r10
            r2.L$3 = r1
            r2.L$4 = r9
            r2.L$5 = r7
            r2.I$0 = r6
            r2.I$1 = r4
            r2.J$0 = r12
            r2.J$1 = r14
            r12 = 1
            r2.label = r12
            java.lang.Object r5 = r1.emit(r5, r2)
            if (r5 != r11) goto L_0x009f
            return r11
        L_0x009f:
            int r4 = r4 + r12
            r5 = 1
            goto L_0x006f
        L_0x00a2:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
