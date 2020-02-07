package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__MergeKt$switchMap$1$1$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1$lambda$1 */
/* compiled from: Merge.kt */
final class C3217xaad28035 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $value;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f765p$;
    final /* synthetic */ FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1 this$0;

    C3217xaad28035(Object obj, Continuation continuation, FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1 flowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1) {
        this.$value = obj;
        this.this$0 = flowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        C3217xaad28035 flowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1$lambda$1 = new C3217xaad28035(this.$value, continuation, this.this$0);
        flowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1$lambda$1.f765p$ = (CoroutineScope) obj;
        return flowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1$lambda$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((C3217xaad28035) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        FlowCollector flowCollector;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f765p$;
            flowCollector = this.this$0.$downstream$inlined;
            Function2 function2 = this.this$0.this$0.$transform;
            Object obj2 = this.$value;
            this.L$0 = flowCollector;
            this.label = 1;
            obj = function2.invoke(obj2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            Flow flow = (Flow) this.L$1;
            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Flow flow2 = (Flow) obj;
        this.L$0 = flowCollector;
        this.L$1 = flow2;
        this.label = 2;
        if (flow2.collect(flowCollector, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
