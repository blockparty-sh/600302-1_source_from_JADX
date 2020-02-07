package com.guardanis.applock.dialogs;

import android.app.Activity;
import android.view.View;
import com.guardanis.applock.C2245R;
import com.guardanis.applock.views.LockCreationViewController;
import com.guardanis.applock.views.LockCreationViewController.Delegate;

public class LockCreationDialogBuilder extends AppLockDialogBuilder<LockCreationViewController> implements Delegate {
    protected Runnable canceledCallback;
    protected Runnable lockCreatedCallback;

    public LockCreationDialogBuilder(Activity activity) {
        super(activity, C2245R.layout.applock__lock_creation);
    }

    public LockCreationDialogBuilder onLockCreated(Runnable runnable) {
        this.lockCreatedCallback = runnable;
        return this;
    }

    public LockCreationDialogBuilder onCanceled(Runnable runnable) {
        this.canceledCallback = runnable;
        return this;
    }

    /* access modifiers changed from: protected */
    public LockCreationViewController buildViewControllerInstance(View view) {
        LockCreationViewController lockCreationViewController = new LockCreationViewController((Activity) this.activity.get(), view);
        lockCreationViewController.setDelegate(this);
        return lockCreationViewController;
    }

    public void onLockCreated() {
        dismissDialog();
        Runnable runnable = this.lockCreatedCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: protected */
    public void handleCanceled() {
        super.handleCanceled();
        Runnable runnable = this.canceledCallback;
        if (runnable != null) {
            runnable.run();
        }
    }
}
