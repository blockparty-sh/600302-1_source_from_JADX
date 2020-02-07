package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a\u0006\u0010\t\u001a\u00020\u0002\u001a@\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2'\u0010\f\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\r¢\u0006\u0002\b\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0012\u001a\u00020\u0013*\u00020\u00022\u0010\b\u0002\u0010\u0014\u001a\n\u0018\u00010\u0015j\u0004\u0018\u0001`\u0016\u001a\n\u0010\u0017\u001a\u00020\u0013*\u00020\u0002\u001a\u0015\u0010\u0018\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0002\"\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0000\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, mo37405d2 = {"isActive", "", "Lkotlinx/coroutines/CoroutineScope;", "isActive$annotations", "(Lkotlinx/coroutines/CoroutineScope;)V", "(Lkotlinx/coroutines/CoroutineScope;)Z", "CoroutineScope", "context", "Lkotlin/coroutines/CoroutineContext;", "MainScope", "coroutineScope", "R", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancel", "", "cause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "ensureActive", "plus", "kotlinx-coroutines-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: CoroutineScope.kt */
public final class CoroutineScopeKt {
    public static /* synthetic */ void isActive$annotations(CoroutineScope coroutineScope) {
    }

    @NotNull
    public static final CoroutineScope plus(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "$this$plus");
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return new ContextScope(coroutineScope.getCoroutineContext().plus(coroutineContext));
    }

    @NotNull
    public static final CoroutineScope MainScope() {
        return new ContextScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain()));
    }

    public static final boolean isActive(@NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "$this$isActive");
        Job job = (Job) coroutineScope.getCoroutineContext().get(Job.Key);
        if (job != null) {
            return job.isActive();
        }
        return true;
    }

    @Nullable
    public static final <R> Object coroutineScope(@NotNull Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> function2, @NotNull Continuation<? super R> continuation) {
        ScopeCoroutine scopeCoroutine = new ScopeCoroutine(continuation.getContext(), continuation);
        Object startUndispatchedOrReturn = UndispatchedKt.startUndispatchedOrReturn(scopeCoroutine, scopeCoroutine, function2);
        if (startUndispatchedOrReturn == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return startUndispatchedOrReturn;
    }

    @NotNull
    public static final CoroutineScope CoroutineScope(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        if (coroutineContext.get(Job.Key) == null) {
            coroutineContext = coroutineContext.plus(JobKt.Job$default((Job) null, 1, (Object) null));
        }
        return new ContextScope(coroutineContext);
    }

    public static /* synthetic */ void cancel$default(CoroutineScope coroutineScope, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = null;
        }
        cancel(coroutineScope, cancellationException);
    }

    public static final void cancel(@NotNull CoroutineScope coroutineScope, @Nullable CancellationException cancellationException) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "$this$cancel");
        Job job = (Job) coroutineScope.getCoroutineContext().get(Job.Key);
        if (job != null) {
            job.cancel(cancellationException);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Scope cannot be cancelled because it does not have a job: ");
        sb.append(coroutineScope);
        throw new IllegalStateException(sb.toString().toString());
    }

    public static final void ensureActive(@NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "$this$ensureActive");
        JobKt.ensureActive(coroutineScope.getCoroutineContext());
    }
}
