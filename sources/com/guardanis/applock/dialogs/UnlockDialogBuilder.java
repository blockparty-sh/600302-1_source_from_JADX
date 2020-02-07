package com.guardanis.applock.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import com.guardanis.applock.AppLock;
import com.guardanis.applock.C2245R;
import com.guardanis.applock.views.UnlockViewController;
import com.guardanis.applock.views.UnlockViewController.Delegate;

public class UnlockDialogBuilder extends AppLockDialogBuilder<UnlockViewController> implements Delegate {
    protected Runnable canceledCallback;
    protected Runnable unlockCallback;

    public UnlockDialogBuilder(Activity activity) {
        super(activity, C2245R.layout.applock__unlock);
    }

    public UnlockDialogBuilder onUnlocked(Runnable runnable) {
        this.unlockCallback = runnable;
        return this;
    }

    public UnlockDialogBuilder onCanceled(Runnable runnable) {
        this.canceledCallback = runnable;
        return this;
    }

    /* access modifiers changed from: protected */
    public UnlockViewController buildViewControllerInstance(View view) {
        UnlockViewController unlockViewController = new UnlockViewController((Activity) this.activity.get(), view);
        unlockViewController.setDelegate(this);
        return unlockViewController;
    }

    public void onUnlockSuccessful() {
        dismissDialog();
        triggerUnlockCallback();
    }

    /* access modifiers changed from: protected */
    public void handleCanceled() {
        super.handleCanceled();
        Runnable runnable = this.canceledCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: protected */
    public void triggerUnlockCallback() {
        Runnable runnable = this.unlockCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    public Dialog showIfEnrolledOrSuccess() {
        Activity activity = (Activity) this.activity.get();
        if (activity == null) {
            return null;
        }
        if (AppLock.isEnrolled(activity)) {
            return show();
        }
        triggerUnlockCallback();
        return null;
    }

    public Dialog showIfRequiredOrSuccess(long j) {
        Activity activity = (Activity) this.activity.get();
        if (activity == null) {
            return null;
        }
        if (AppLock.isUnlockRequired(activity, j)) {
            return show();
        }
        triggerUnlockCallback();
        return null;
    }
}
