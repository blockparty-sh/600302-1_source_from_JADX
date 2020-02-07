package androidx.work;

import android.content.Context;
import androidx.work.ListenableWorker.Result;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import com.leanplum.internal.Constants.Params;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u0016\u001a\u00020\u000fH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0006\u0010\u0018\u001a\u00020\u0019J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001bR\u001c\u0010\u0007\u001a\u00020\b8\u0016X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, mo37405d2 = {"Landroidx/work/CoroutineWorker;", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "coroutineContext", "Lkotlinx/coroutines/CoroutineDispatcher;", "coroutineContext$annotations", "()V", "getCoroutineContext", "()Lkotlinx/coroutines/CoroutineDispatcher;", "future", "Landroidx/work/impl/utils/futures/SettableFuture;", "Landroidx/work/ListenableWorker$Result;", "getFuture$work_runtime_ktx_release", "()Landroidx/work/impl/utils/futures/SettableFuture;", "job", "Lkotlinx/coroutines/Job;", "getJob$work_runtime_ktx_release", "()Lkotlinx/coroutines/Job;", "doWork", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onStopped", "", "startWork", "Lcom/google/common/util/concurrent/ListenableFuture;", "work-runtime-ktx_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CoroutineWorker.kt */
public abstract class CoroutineWorker extends ListenableWorker {
    @NotNull
    private final CoroutineDispatcher coroutineContext;
    @NotNull
    private final SettableFuture<Result> future;
    @NotNull
    private final Job job = JobKt.Job$default((Job) null, 1, (Object) null);

    @Deprecated(message = "use withContext(...) inside doWork() instead.")
    public static /* synthetic */ void coroutineContext$annotations() {
    }

    @Nullable
    public abstract Object doWork(@NotNull Continuation<? super Result> continuation);

    public CoroutineWorker(@NotNull Context context, @NotNull WorkerParameters workerParameters) {
        Intrinsics.checkParameterIsNotNull(context, "appContext");
        Intrinsics.checkParameterIsNotNull(workerParameters, Params.PARAMS);
        super(context, workerParameters);
        SettableFuture<Result> create = SettableFuture.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "SettableFuture.create()");
        this.future = create;
        SettableFuture<Result> settableFuture = this.future;
        Runnable r4 = new Runnable(this) {
            final /* synthetic */ CoroutineWorker this$0;

            {
                this.this$0 = r1;
            }

            public final void run() {
                if (this.this$0.getFuture$work_runtime_ktx_release().isCancelled()) {
                    this.this$0.getJob$work_runtime_ktx_release().cancel();
                }
            }
        };
        TaskExecutor taskExecutor = getTaskExecutor();
        Intrinsics.checkExpressionValueIsNotNull(taskExecutor, "taskExecutor");
        settableFuture.addListener(r4, taskExecutor.getBackgroundExecutor());
        this.coroutineContext = Dispatchers.getDefault();
    }

    @NotNull
    public final Job getJob$work_runtime_ktx_release() {
        return this.job;
    }

    @NotNull
    public final SettableFuture<Result> getFuture$work_runtime_ktx_release() {
        return this.future;
    }

    @NotNull
    public CoroutineDispatcher getCoroutineContext() {
        return this.coroutineContext;
    }

    @NotNull
    public final ListenableFuture<Result> startWork() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(getCoroutineContext().plus(this.job)), null, null, new CoroutineWorker$startWork$1(this, null), 3, null);
        return this.future;
    }

    public final void onStopped() {
        super.onStopped();
        this.future.cancel(false);
    }
}
