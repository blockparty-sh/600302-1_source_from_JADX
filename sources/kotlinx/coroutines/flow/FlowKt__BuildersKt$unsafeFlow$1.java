package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__BuildersKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Builders.kt */
public final class FlowKt__BuildersKt$unsafeFlow$1 implements Flow<T> {
    final /* synthetic */ Function2 $block;

    public FlowKt__BuildersKt$unsafeFlow$1(Function2 function2) {
        this.$block = function2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.FlowCollector<? super T> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.flow.FlowKt__BuildersKt$unsafeFlow$1$collect$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            kotlinx.coroutines.flow.FlowKt__BuildersKt$unsafeFlow$1$collect$1 r0 = (kotlinx.coroutines.flow.FlowKt__BuildersKt$unsafeFlow$1$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__BuildersKt$unsafeFlow$1$collect$1 r0 = new kotlinx.coroutines.flow.FlowKt__BuildersKt$unsafeFlow$1$collect$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__BuildersKt$unsafeFlow$1 r5 = (kotlinx.coroutines.flow.FlowKt__BuildersKt$unsafeFlow$1) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004c
        L_0x0032:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.jvm.functions.Function2 r6 = r4.$block
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r5 = r6.invoke(r5, r0)
            if (r5 != r1) goto L_0x004c
            return r1
        L_0x004c:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__BuildersKt$unsafeFlow$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public Object collect$$forInline(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
        InlineMarker.mark(4);
        new FlowKt__BuildersKt$unsafeFlow$1$collect$1(this, continuation);
        InlineMarker.mark(5);
        this.$block.invoke(flowCollector, continuation);
        return Unit.INSTANCE;
    }
}
