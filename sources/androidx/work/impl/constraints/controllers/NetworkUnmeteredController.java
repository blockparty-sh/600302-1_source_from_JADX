package androidx.work.impl.constraints.controllers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;

public class NetworkUnmeteredController extends ConstraintController<NetworkState> {
    public NetworkUnmeteredController(@NonNull Context context, @NonNull TaskExecutor taskExecutor) {
        super(Trackers.getInstance(context, taskExecutor).getNetworkStateTracker());
    }

    /* access modifiers changed from: 0000 */
    public boolean hasConstraint(@NonNull WorkSpec workSpec) {
        return workSpec.constraints.getRequiredNetworkType() == NetworkType.UNMETERED;
    }

    /* access modifiers changed from: 0000 */
    public boolean isConstrained(@NonNull NetworkState networkState) {
        return !networkState.isConnected() || networkState.isMetered();
    }
}
