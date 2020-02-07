package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0006H@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b"}, mo37405d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.FlowKt__MergeKt$switchMap$1", mo38000f = "Merge.kt", mo38001i = {0, 0}, mo38002l = {185}, mo38003m = "invokeSuspend", mo38004n = {"previousFlow", "$this$collect$iv"}, mo38005s = {"L$0", "L$1"})
/* compiled from: Merge.kt */
final class FlowKt__MergeKt$switchMap$1 extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow $this_switchMap;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f766p$;
    private FlowCollector p$0;

    FlowKt__MergeKt$switchMap$1(Flow flow, Function2 function2, Continuation continuation) {
        this.$this_switchMap = flow;
        this.$transform = function2;
        super(3, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull CoroutineScope coroutineScope, @NotNull FlowCollector<? super R> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "$this$create");
        Intrinsics.checkParameterIsNotNull(flowCollector, "downstream");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        FlowKt__MergeKt$switchMap$1 flowKt__MergeKt$switchMap$1 = new FlowKt__MergeKt$switchMap$1(this.$this_switchMap, this.$transform, continuation);
        flowKt__MergeKt$switchMap$1.f766p$ = coroutineScope;
        flowKt__MergeKt$switchMap$1.p$0 = flowCollector;
        return flowKt__MergeKt$switchMap$1;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((FlowKt__MergeKt$switchMap$1) create((CoroutineScope) obj, (FlowCollector) obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f766p$;
            FlowCollector flowCollector = this.p$0;
            ObjectRef objectRef = new ObjectRef();
            objectRef.element = (Job) null;
            Flow flow = this.$this_switchMap;
            FlowCollector flowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1 = new FlowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1(this, coroutineScope, objectRef, flowCollector);
            this.L$0 = objectRef;
            this.L$1 = flow;
            this.label = 1;
            if (flow.collect(flowKt__MergeKt$switchMap$1$invokeSuspend$$inlined$collect$1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            Flow flow2 = (Flow) this.L$1;
            ObjectRef objectRef2 = (ObjectRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
