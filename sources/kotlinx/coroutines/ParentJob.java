package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext.C2916Key;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\f\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H'¨\u0006\u0005"}, mo37405d2 = {"Lkotlinx/coroutines/ParentJob;", "Lkotlinx/coroutines/Job;", "getChildJobCancellationCause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
@InternalCoroutinesApi
/* compiled from: Job.kt */
public interface ParentJob extends Job {

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    /* compiled from: Job.kt */
    public static final class DefaultImpls {
        public static <R> R fold(ParentJob parentJob, R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
            Intrinsics.checkParameterIsNotNull(function2, "operation");
            return kotlinx.coroutines.Job.DefaultImpls.fold(parentJob, r, function2);
        }

        @Nullable
        public static <E extends Element> E get(ParentJob parentJob, @NotNull C2916Key<E> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlinx.coroutines.Job.DefaultImpls.get(parentJob, key);
        }

        @NotNull
        public static CoroutineContext minusKey(ParentJob parentJob, @NotNull C2916Key<?> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlinx.coroutines.Job.DefaultImpls.minusKey(parentJob, key);
        }

        @NotNull
        public static CoroutineContext plus(ParentJob parentJob, @NotNull CoroutineContext coroutineContext) {
            Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
            return kotlinx.coroutines.Job.DefaultImpls.plus((Job) parentJob, coroutineContext);
        }

        @NotNull
        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        public static Job plus(ParentJob parentJob, @NotNull Job job) {
            Intrinsics.checkParameterIsNotNull(job, "other");
            return kotlinx.coroutines.Job.DefaultImpls.plus((Job) parentJob, job);
        }
    }

    @NotNull
    @InternalCoroutinesApi
    CancellationException getChildJobCancellationCause();
}
