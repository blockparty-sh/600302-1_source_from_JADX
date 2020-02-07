package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Lkotlinx/coroutines/ChildJob;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.JobSupport$children$1", mo38000f = "JobSupport.kt", mo38001i = {0, 1, 1, 1, 1, 1}, mo38002l = {899, 901}, mo38003m = "invokeSuspend", mo38004n = {"state", "state", "list", "this_$iv", "cur$iv", "it"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* compiled from: JobSupport.kt */
final class JobSupport$children$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super ChildJob>, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* renamed from: p$ */
    private SequenceScope f735p$;
    final /* synthetic */ JobSupport this$0;

    JobSupport$children$1(JobSupport jobSupport, Continuation continuation) {
        this.this$0 = jobSupport;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        JobSupport$children$1 jobSupport$children$1 = new JobSupport$children$1(this.this$0, continuation);
        jobSupport$children$1.f735p$ = (SequenceScope) obj;
        return jobSupport$children$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((JobSupport$children$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x007c  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0039
            if (r1 == r3) goto L_0x0032
            if (r1 != r2) goto L_0x002a
            java.lang.Object r1 = r10.L$5
            kotlinx.coroutines.ChildHandleNode r1 = (kotlinx.coroutines.ChildHandleNode) r1
            java.lang.Object r1 = r10.L$4
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r1
            java.lang.Object r4 = r10.L$3
            kotlinx.coroutines.NodeList r4 = (kotlinx.coroutines.NodeList) r4
            java.lang.Object r5 = r10.L$2
            kotlinx.coroutines.NodeList r5 = (kotlinx.coroutines.NodeList) r5
            java.lang.Object r6 = r10.L$1
            java.lang.Object r7 = r10.L$0
            kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r10
            goto L_0x009a
        L_0x002a:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0032:
            java.lang.Object r0 = r10.L$0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00a7
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlin.sequences.SequenceScope r11 = r10.f735p$
            kotlinx.coroutines.JobSupport r1 = r10.this$0
            java.lang.Object r1 = r1.getState$kotlinx_coroutines_core()
            boolean r4 = r1 instanceof kotlinx.coroutines.ChildHandleNode
            if (r4 == 0) goto L_0x0058
            r2 = r1
            kotlinx.coroutines.ChildHandleNode r2 = (kotlinx.coroutines.ChildHandleNode) r2
            kotlinx.coroutines.ChildJob r2 = r2.childJob
            r10.L$0 = r1
            r10.label = r3
            java.lang.Object r11 = r11.yield(r2, r10)
            if (r11 != r0) goto L_0x00a7
            return r0
        L_0x0058:
            boolean r4 = r1 instanceof kotlinx.coroutines.Incomplete
            if (r4 == 0) goto L_0x00a7
            r4 = r1
            kotlinx.coroutines.Incomplete r4 = (kotlinx.coroutines.Incomplete) r4
            kotlinx.coroutines.NodeList r4 = r4.getList()
            if (r4 == 0) goto L_0x00a7
            java.lang.Object r5 = r4.getNext()
            if (r5 == 0) goto L_0x009f
            kotlinx.coroutines.internal.LockFreeLinkedListNode r5 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r5
            r7 = r11
            r6 = r1
            r1 = r5
            r11 = r10
            r5 = r4
        L_0x0072:
            r8 = r4
            kotlinx.coroutines.internal.LockFreeLinkedListHead r8 = (kotlinx.coroutines.internal.LockFreeLinkedListHead) r8
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r8)
            r8 = r8 ^ r3
            if (r8 == 0) goto L_0x00a7
            boolean r8 = r1 instanceof kotlinx.coroutines.ChildHandleNode
            if (r8 == 0) goto L_0x009a
            r8 = r1
            kotlinx.coroutines.ChildHandleNode r8 = (kotlinx.coroutines.ChildHandleNode) r8
            kotlinx.coroutines.ChildJob r9 = r8.childJob
            r11.L$0 = r7
            r11.L$1 = r6
            r11.L$2 = r5
            r11.L$3 = r4
            r11.L$4 = r1
            r11.L$5 = r8
            r11.label = r2
            java.lang.Object r8 = r7.yield(r9, r11)
            if (r8 != r0) goto L_0x009a
            return r0
        L_0x009a:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r1 = r1.getNextNode()
            goto L_0x0072
        L_0x009f:
            kotlin.TypeCastException r11 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
            r11.<init>(r0)
            throw r11
        L_0x00a7:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport$children$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
