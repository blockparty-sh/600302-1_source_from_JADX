package kotlinx.coroutines;

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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, mo37405d2 = {"Lkotlinx/coroutines/CompletableJob;", "Lkotlinx/coroutines/Job;", "complete", "", "completeExceptionally", "exception", "", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CompletableJob.kt */
public interface CompletableJob extends Job {

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    /* compiled from: CompletableJob.kt */
    public static final class DefaultImpls {
        public static <R> R fold(CompletableJob completableJob, R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
            Intrinsics.checkParameterIsNotNull(function2, "operation");
            return kotlinx.coroutines.Job.DefaultImpls.fold(completableJob, r, function2);
        }

        @Nullable
        public static <E extends Element> E get(CompletableJob completableJob, @NotNull C2916Key<E> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlinx.coroutines.Job.DefaultImpls.get(completableJob, key);
        }

        @NotNull
        public static CoroutineContext minusKey(CompletableJob completableJob, @NotNull C2916Key<?> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlinx.coroutines.Job.DefaultImpls.minusKey(completableJob, key);
        }

        @NotNull
        public static CoroutineContext plus(CompletableJob completableJob, @NotNull CoroutineContext coroutineContext) {
            Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
            return kotlinx.coroutines.Job.DefaultImpls.plus((Job) completableJob, coroutineContext);
        }

        @NotNull
        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        public static Job plus(CompletableJob completableJob, @NotNull Job job) {
            Intrinsics.checkParameterIsNotNull(job, "other");
            return kotlinx.coroutines.Job.DefaultImpls.plus((Job) completableJob, job);
        }
    }

    boolean complete();

    boolean completeExceptionally(@NotNull Throwable th);
}
