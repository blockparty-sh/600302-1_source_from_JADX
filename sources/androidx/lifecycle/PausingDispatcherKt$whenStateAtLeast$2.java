package androidx.lifecycle;

import androidx.lifecycle.Lifecycle.State;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "androidx.lifecycle.PausingDispatcherKt$whenStateAtLeast$2", mo38000f = "PausingDispatcher.kt", mo38001i = {0, 0, 0, 0}, mo38002l = {163}, mo38003m = "invokeSuspend", mo38004n = {"$this$withContext", "job", "dispatcher", "controller"}, mo38005s = {"L$0", "L$1", "L$2", "L$3"})
/* compiled from: PausingDispatcher.kt */
final class PausingDispatcherKt$whenStateAtLeast$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ Function2 $block;
    final /* synthetic */ State $minState;
    final /* synthetic */ Lifecycle $this_whenStateAtLeast;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f62p$;

    PausingDispatcherKt$whenStateAtLeast$2(Lifecycle lifecycle, State state, Function2 function2, Continuation continuation) {
        this.$this_whenStateAtLeast = lifecycle;
        this.$minState = state;
        this.$block = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        PausingDispatcherKt$whenStateAtLeast$2 pausingDispatcherKt$whenStateAtLeast$2 = new PausingDispatcherKt$whenStateAtLeast$2(this.$this_whenStateAtLeast, this.$minState, this.$block, continuation);
        pausingDispatcherKt$whenStateAtLeast$2.f62p$ = (CoroutineScope) obj;
        return pausingDispatcherKt$whenStateAtLeast$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PausingDispatcherKt$whenStateAtLeast$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        LifecycleController lifecycleController;
        LifecycleController lifecycleController2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f62p$;
            Job job = (Job) coroutineScope.getCoroutineContext().get(Job.Key);
            if (job != null) {
                PausingDispatcher pausingDispatcher = new PausingDispatcher();
                lifecycleController = new LifecycleController(this.$this_whenStateAtLeast, this.$minState, pausingDispatcher.dispatchQueue, job);
                try {
                    CoroutineContext coroutineContext = pausingDispatcher;
                    Function2 function2 = this.$block;
                    this.L$0 = coroutineScope;
                    this.L$1 = job;
                    this.L$2 = pausingDispatcher;
                    this.L$3 = lifecycleController;
                    this.label = 1;
                    obj = BuildersKt.withContext(coroutineContext, function2, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    lifecycleController2 = lifecycleController;
                } catch (Throwable th) {
                    th = th;
                    lifecycleController.finish();
                    throw th;
                }
            } else {
                throw new IllegalStateException("when[State] methods should have a parent job".toString());
            }
        } else if (i == 1) {
            lifecycleController2 = (LifecycleController) this.L$3;
            PausingDispatcher pausingDispatcher2 = (PausingDispatcher) this.L$2;
            Job job2 = (Job) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                th = th2;
                lifecycleController = lifecycleController2;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        lifecycleController2.finish();
        return obj;
    }
}
