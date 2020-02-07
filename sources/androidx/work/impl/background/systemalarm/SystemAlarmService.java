package androidx.work.impl.background.systemalarm;

import android.content.Intent;
import androidx.annotation.MainThread;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.lifecycle.LifecycleService;
import androidx.work.Logger;
import androidx.work.impl.utils.WakeLocks;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SystemAlarmService extends LifecycleService implements CommandsCompletedListener {
    private static final String TAG = Logger.tagWithPrefix("SystemAlarmService");
    private SystemAlarmDispatcher mDispatcher;
    private boolean mIsShutdown;

    public void onCreate() {
        super.onCreate();
        initializeDispatcher();
        this.mIsShutdown = false;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mIsShutdown = true;
        this.mDispatcher.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (this.mIsShutdown) {
            Logger.get().info(TAG, "Re-initializing SystemAlarmDispatcher after a request to shut-down.", new Throwable[0]);
            this.mDispatcher.onDestroy();
            initializeDispatcher();
            this.mIsShutdown = false;
        }
        if (intent != null) {
            this.mDispatcher.add(intent, i2);
        }
        return 3;
    }

    @MainThread
    public void onAllCommandsCompleted() {
        this.mIsShutdown = true;
        Logger.get().debug(TAG, "All commands completed in dispatcher", new Throwable[0]);
        WakeLocks.checkWakeLocks();
        stopSelf();
    }

    @MainThread
    private void initializeDispatcher() {
        this.mDispatcher = new SystemAlarmDispatcher(this);
        this.mDispatcher.setCompletedListener(this);
    }
}
