package androidx.work;

import androidx.work.Operation.State.SUCCESS;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003HHø\u0001\u0000¢\u0006\u0002\u0010\u0004\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, mo37405d2 = {"await", "Landroidx/work/Operation$State$SUCCESS;", "kotlin.jvm.PlatformType", "Landroidx/work/Operation;", "(Landroidx/work/Operation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "work-runtime-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Operation.kt */
public final class OperationKt {
    @Nullable
    public static final Object await(@NotNull Operation operation, @NotNull Continuation<? super SUCCESS> continuation) {
        ListenableFuture result = operation.getResult();
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        if (result.isDone()) {
            try {
                return result.get();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    cause = e;
                }
                throw cause;
            }
        } else {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            result.addListener(new OperationKt$await$$inlined$suspendCancellableCoroutine$lambda$1(cancellableContinuationImpl, result), DirectExecutor.INSTANCE);
            Object result2 = cancellableContinuationImpl.getResult();
            if (result2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return result2;
            }
            DebugProbesKt.probeCoroutineSuspended(continuation);
            return result2;
        }
    }

    @Nullable
    private static final Object await$$forInline(@NotNull Operation operation, @NotNull Continuation continuation) {
        ListenableFuture result = operation.getResult();
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        if (result.isDone()) {
            try {
                return result.get();
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    cause = e;
                }
                throw cause;
            }
        } else {
            InlineMarker.mark(0);
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            result.addListener(new OperationKt$await$$inlined$suspendCancellableCoroutine$lambda$1(cancellableContinuationImpl, result), DirectExecutor.INSTANCE);
            Object result2 = cancellableContinuationImpl.getResult();
            if (result2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            InlineMarker.mark(1);
            return result2;
        }
    }
}
