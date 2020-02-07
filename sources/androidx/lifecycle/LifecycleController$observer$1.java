package androidx.lifecycle;

import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle.State;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "", "source", "Landroidx/lifecycle/LifecycleOwner;", "<anonymous parameter 1>", "Landroidx/lifecycle/Lifecycle$Event;", "onStateChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: LifecycleController.kt */
final class LifecycleController$observer$1 implements LifecycleEventObserver {
    final /* synthetic */ Job $parentJob;
    final /* synthetic */ LifecycleController this$0;

    LifecycleController$observer$1(LifecycleController lifecycleController, Job job) {
        this.this$0 = lifecycleController;
        this.$parentJob = job;
    }

    public final void onStateChanged(@NotNull LifecycleOwner lifecycleOwner, @NotNull Event event) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, Param.SOURCE);
        Intrinsics.checkParameterIsNotNull(event, "<anonymous parameter 1>");
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        String str = "source.lifecycle";
        Intrinsics.checkExpressionValueIsNotNull(lifecycle, str);
        if (lifecycle.getCurrentState() == State.DESTROYED) {
            LifecycleController lifecycleController = this.this$0;
            this.$parentJob.cancel();
            lifecycleController.finish();
            return;
        }
        Lifecycle lifecycle2 = lifecycleOwner.getLifecycle();
        Intrinsics.checkExpressionValueIsNotNull(lifecycle2, str);
        if (lifecycle2.getCurrentState().compareTo(this.this$0.minState) < 0) {
            this.this$0.dispatchQueue.pause();
        } else {
            this.this$0.dispatchQueue.resume();
        }
    }
}
