package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007¢\u0006\u0002\b\u0003\u001a\u0011\u0010\u0000\u001a\u00020\u0004*\u00020\u0005H\u0007¢\u0006\u0002\b\u0003¨\u0006\u0006"}, mo37405d2 = {"asCoroutineDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "Ljava/util/concurrent/Executor;", "from", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Ljava/util/concurrent/ExecutorService;", "kotlinx-coroutines-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Executors.kt */
public final class ExecutorsKt {
    @NotNull
    @JvmName(name = "from")
    public static final ExecutorCoroutineDispatcher from(@NotNull ExecutorService executorService) {
        Intrinsics.checkParameterIsNotNull(executorService, "$this$asCoroutineDispatcher");
        CoroutineDispatcher from = from((Executor) executorService);
        if (from != null) {
            return (ExecutorCoroutineDispatcher) from;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.ExecutorCoroutineDispatcher");
    }

    @NotNull
    @JvmName(name = "from")
    public static final CoroutineDispatcher from(@NotNull Executor executor) {
        Intrinsics.checkParameterIsNotNull(executor, "$this$asCoroutineDispatcher");
        return new ExecutorCoroutineDispatcherImpl(executor);
    }
}
