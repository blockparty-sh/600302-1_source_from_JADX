package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00009\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__MergeKt$transform$$inlined$unsafeFlow$1", "kotlinx/coroutines/flow/FlowKt__MergeKt$map$$inlined$transform$1"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Builders.kt */
public final class FlowKt__MergeKt$flatMapConcat$$inlined$map$1 implements Flow<Flow<? extends R>> {
    final /* synthetic */ Flow $this_transform$inlined;
    final /* synthetic */ Function2 $transform$inlined$1;

    public FlowKt__MergeKt$flatMapConcat$$inlined$map$1(Flow flow, Function2 function2) {
        this.$this_transform$inlined = flow;
        this.$transform$inlined$1 = function2;
    }

    @Nullable
    public Object collect(@NotNull final FlowCollector flowCollector, @NotNull Continuation continuation) {
        return this.$this_transform$inlined.collect(new FlowCollector<T>() {
            /* JADX WARNING: Removed duplicated region for block: B:14:0x006a  */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x00ab A[RETURN] */
            /* JADX WARNING: Removed duplicated region for block: B:21:0x00ac A[PHI: r12 
              PHI: (r12v2 java.lang.Object) = (r12v4 java.lang.Object), (r12v1 java.lang.Object) binds: [B:19:0x00a9, B:10:0x0029] A[DONT_GENERATE, DONT_INLINE], RETURN] */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
            @org.jetbrains.annotations.Nullable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object emit(java.lang.Object r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r12) {
                /*
                    r10 = this;
                    boolean r0 = r12 instanceof kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.C32032.C32041
                    if (r0 == 0) goto L_0x0014
                    r0 = r12
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.C32032.C32041) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L_0x0014
                    int r12 = r0.label
                    int r12 = r12 - r2
                    r0.label = r12
                    goto L_0x0019
                L_0x0014:
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1
                    r0.<init>(r10, r12)
                L_0x0019:
                    java.lang.Object r12 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L_0x006a
                    if (r2 == r4) goto L_0x004c
                    if (r2 != r3) goto L_0x0044
                    java.lang.Object r11 = r0.L$6
                    kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                    java.lang.Object r11 = r0.L$5
                    java.lang.Object r11 = r0.L$4
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r11 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.C32032.C32041) r11
                    java.lang.Object r11 = r0.L$3
                    java.lang.Object r11 = r0.L$2
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r11 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.C32032.C32041) r11
                    java.lang.Object r11 = r0.L$1
                    java.lang.Object r11 = r0.L$0
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2 r11 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.C32032) r11
                    kotlin.ResultKt.throwOnFailure(r12)
                    goto L_0x00ac
                L_0x0044:
                    java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                    java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                    r11.<init>(r12)
                    throw r11
                L_0x004c:
                    java.lang.Object r11 = r0.L$7
                    kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                    java.lang.Object r2 = r0.L$6
                    kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                    java.lang.Object r4 = r0.L$5
                    java.lang.Object r5 = r0.L$4
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r5 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.C32032.C32041) r5
                    java.lang.Object r6 = r0.L$3
                    java.lang.Object r7 = r0.L$2
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r7 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.C32032.C32041) r7
                    java.lang.Object r8 = r0.L$1
                    java.lang.Object r9 = r0.L$0
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2 r9 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.C32032) r9
                    kotlin.ResultKt.throwOnFailure(r12)
                    goto L_0x0095
                L_0x006a:
                    kotlin.ResultKt.throwOnFailure(r12)
                    kotlinx.coroutines.flow.FlowCollector r12 = r3
                    kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1 r2 = r2
                    kotlin.jvm.functions.Function2 r2 = r2.$transform$inlined$1
                    r0.L$0 = r10
                    r0.L$1 = r11
                    r0.L$2 = r0
                    r0.L$3 = r11
                    r0.L$4 = r0
                    r0.L$5 = r11
                    r0.L$6 = r12
                    r0.L$7 = r12
                    r0.label = r4
                    java.lang.Object r2 = r2.invoke(r11, r0)
                    if (r2 != r1) goto L_0x008c
                    return r1
                L_0x008c:
                    r9 = r10
                    r4 = r11
                    r6 = r4
                    r8 = r6
                    r11 = r12
                    r5 = r0
                    r7 = r5
                    r12 = r2
                    r2 = r11
                L_0x0095:
                    r0.L$0 = r9
                    r0.L$1 = r8
                    r0.L$2 = r7
                    r0.L$3 = r6
                    r0.L$4 = r5
                    r0.L$5 = r4
                    r0.L$6 = r2
                    r0.label = r3
                    java.lang.Object r12 = r11.emit(r12, r0)
                    if (r12 != r1) goto L_0x00ac
                    return r1
                L_0x00ac:
                    return r12
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.C32032.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
    }
}
