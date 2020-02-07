package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.internal.ConcurrentFlowCollector;
import kotlinx.coroutines.sync.Semaphore;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$2", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Collect.kt */
public final class ChannelFlowMerge$mergeImpl$$inlined$collect$1 implements FlowCollector<Flow<? extends T>> {
    final /* synthetic */ ConcurrentFlowCollector $collector$inlined;
    final /* synthetic */ CoroutineScope $scope$inlined;
    final /* synthetic */ Semaphore $semaphore$inlined;

    public ChannelFlowMerge$mergeImpl$$inlined$collect$1(Semaphore semaphore, CoroutineScope coroutineScope, ConcurrentFlowCollector concurrentFlowCollector) {
        this.$semaphore$inlined = semaphore;
        this.$scope$inlined = coroutineScope;
        this.$collector$inlined = concurrentFlowCollector;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.ChannelFlowMerge$mergeImpl$$inlined$collect$1.C31611
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.flow.ChannelFlowMerge$mergeImpl$$inlined$collect$1$1 r0 = (kotlinx.coroutines.flow.ChannelFlowMerge$mergeImpl$$inlined$collect$1.C31611) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.ChannelFlowMerge$mergeImpl$$inlined$collect$1$1 r0 = new kotlinx.coroutines.flow.ChannelFlowMerge$mergeImpl$$inlined$collect$1$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r8 = r0.L$3
            kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
            java.lang.Object r1 = r0.L$2
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            java.lang.Object r1 = r0.L$1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.ChannelFlowMerge$mergeImpl$$inlined$collect$1 r0 = (kotlinx.coroutines.flow.ChannelFlowMerge$mergeImpl$$inlined$collect$1) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x005e
        L_0x0038:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r2 = r8
            kotlinx.coroutines.flow.Flow r2 = (kotlinx.coroutines.flow.Flow) r2
            kotlinx.coroutines.sync.Semaphore r4 = r7.$semaphore$inlined
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r2
            r0.label = r3
            java.lang.Object r8 = r4.acquire(r0)
            if (r8 != r1) goto L_0x005c
            return r1
        L_0x005c:
            r0 = r7
            r8 = r2
        L_0x005e:
            kotlinx.coroutines.CoroutineScope r1 = r0.$scope$inlined
            r2 = 0
            r3 = 0
            kotlinx.coroutines.flow.ChannelFlowMerge$mergeImpl$$inlined$collect$1$lambda$1 r9 = new kotlinx.coroutines.flow.ChannelFlowMerge$mergeImpl$$inlined$collect$1$lambda$1
            r4 = 0
            r9.<init>(r8, r4, r0)
            r4 = r9
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r5 = 3
            r6 = 0
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r1, r2, r3, r4, r5, r6)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.ChannelFlowMerge$mergeImpl$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
