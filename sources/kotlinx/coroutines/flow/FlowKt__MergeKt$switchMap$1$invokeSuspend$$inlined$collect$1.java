package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo37405d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$2", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Collect.kt */
public final class FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1 implements FlowCollector<T> {
    final /* synthetic */ FlowCollector $downstream$inlined;
    final /* synthetic */ ObjectRef $previousFlow$inlined;
    final /* synthetic */ CoroutineScope $receiver$0$inlined;
    final /* synthetic */ FlowKt__MergeKt$switchMap$1 this$0;

    public FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1(FlowKt__MergeKt$switchMap$1 flowKt__MergeKt$switchMap$1, CoroutineScope coroutineScope, ObjectRef objectRef, FlowCollector flowCollector) {
        this.this$0 = flowKt__MergeKt$switchMap$1;
        this.$receiver$0$inlined = coroutineScope;
        this.$previousFlow$inlined = objectRef;
        this.$downstream$inlined = flowCollector;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object emit(java.lang.Object r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1.C32161
            if (r0 == 0) goto L_0x0014
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1.C32161) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            java.lang.Object r8 = r0.L$3
            java.lang.Object r1 = r0.L$2
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            java.lang.Object r1 = r0.L$1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1 r0 = (kotlinx.coroutines.flow.FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0070
        L_0x0036:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            kotlin.jvm.internal.Ref$ObjectRef r2 = r7.$previousFlow$inlined
            T r2 = r2.element
            kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2
            if (r2 == 0) goto L_0x0056
            kotlinx.coroutines.flow.internal.ChildCancelledException r4 = new kotlinx.coroutines.flow.internal.ChildCancelledException
            r4.<init>()
            java.util.concurrent.CancellationException r4 = (java.util.concurrent.CancellationException) r4
            r2.cancel(r4)
        L_0x0056:
            kotlin.jvm.internal.Ref$ObjectRef r2 = r7.$previousFlow$inlined
            T r2 = r2.element
            kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2
            if (r2 == 0) goto L_0x0073
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r8
            r0.label = r3
            java.lang.Object r9 = r2.join(r0)
            if (r9 != r1) goto L_0x006f
            return r1
        L_0x006f:
            r0 = r7
        L_0x0070:
            kotlin.Unit r9 = (kotlin.Unit) r9
            goto L_0x0074
        L_0x0073:
            r0 = r7
        L_0x0074:
            kotlin.jvm.internal.Ref$ObjectRef r9 = r0.$previousFlow$inlined
            kotlinx.coroutines.CoroutineScope r1 = r0.$receiver$0$inlined
            r2 = 0
            kotlinx.coroutines.CoroutineStart r3 = kotlinx.coroutines.CoroutineStart.UNDISPATCHED
            kotlinx.coroutines.flow.FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1$lambda$1 r4 = new kotlinx.coroutines.flow.FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1$lambda$1
            r5 = 0
            r4.<init>(r8, r5, r0)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r5 = 1
            r6 = 0
            kotlinx.coroutines.Job r8 = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r1, r2, r3, r4, r5, r6)
            r9.element = r8
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
