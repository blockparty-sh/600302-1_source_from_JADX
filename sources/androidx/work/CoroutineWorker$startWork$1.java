package androidx.work;

import androidx.work.ListenableWorker.Result;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "androidx.work.CoroutineWorker$startWork$1", mo38000f = "CoroutineWorker.kt", mo38001i = {0}, mo38002l = {68}, mo38003m = "invokeSuspend", mo38004n = {"$this$launch"}, mo38005s = {"L$0"})
/* compiled from: CoroutineWorker.kt */
final class CoroutineWorker$startWork$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f86p$;
    final /* synthetic */ CoroutineWorker this$0;

    CoroutineWorker$startWork$1(CoroutineWorker coroutineWorker, Continuation continuation) {
        this.this$0 = coroutineWorker;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        CoroutineWorker$startWork$1 coroutineWorker$startWork$1 = new CoroutineWorker$startWork$1(this.this$0, continuation);
        coroutineWorker$startWork$1.f86p$ = (CoroutineScope) obj;
        return coroutineWorker$startWork$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((CoroutineWorker$startWork$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f86p$;
            CoroutineWorker coroutineWorker = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = coroutineWorker.doWork(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                this.this$0.getFuture$work_runtime_ktx_release().setException(th);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.getFuture$work_runtime_ktx_release().set((Result) obj);
        return Unit.INSTANCE;
    }
}
