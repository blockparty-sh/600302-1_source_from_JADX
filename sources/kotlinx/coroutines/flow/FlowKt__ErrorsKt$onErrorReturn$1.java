package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007"}, mo37405d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "e", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$onErrorReturn$1", mo38000f = "Errors.kt", mo38001i = {}, mo38002l = {41}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: Errors.kt */
final class FlowKt__ErrorsKt$onErrorReturn$1 extends SuspendLambda implements Function3<FlowCollector<? super T>, Throwable, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $fallback;
    final /* synthetic */ Function1 $predicate;
    int label;

    /* renamed from: p$ */
    private FlowCollector f764p$;
    private Throwable p$0;

    FlowKt__ErrorsKt$onErrorReturn$1(Function1 function1, Object obj, Continuation continuation) {
        this.$predicate = function1;
        this.$fallback = obj;
        super(3, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull FlowCollector<? super T> flowCollector, @NotNull Throwable th, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(flowCollector, "$this$create");
        Intrinsics.checkParameterIsNotNull(th, "e");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        FlowKt__ErrorsKt$onErrorReturn$1 flowKt__ErrorsKt$onErrorReturn$1 = new FlowKt__ErrorsKt$onErrorReturn$1(this.$predicate, this.$fallback, continuation);
        flowKt__ErrorsKt$onErrorReturn$1.f764p$ = flowCollector;
        flowKt__ErrorsKt$onErrorReturn$1.p$0 = th;
        return flowKt__ErrorsKt$onErrorReturn$1;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((FlowKt__ErrorsKt$onErrorReturn$1) create((FlowCollector) obj, (Throwable) obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = this.f764p$;
            Throwable th = this.p$0;
            if (((Boolean) this.$predicate.invoke(th)).booleanValue()) {
                Object obj2 = this.$fallback;
                this.label = 1;
                if (flowCollector.emit(obj2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                throw th;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
