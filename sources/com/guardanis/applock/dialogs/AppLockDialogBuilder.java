package com.guardanis.applock.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Build.VERSION;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatDialog;
import com.guardanis.applock.AppLock;
import com.guardanis.applock.views.AppLockViewController;
import java.lang.ref.WeakReference;

public abstract class AppLockDialogBuilder<ALVC extends AppLockViewController> {
    protected WeakReference<Activity> activity;
    protected WeakReference<AppCompatDialog> dialog = new WeakReference<>(null);
    protected int layoutResId;
    protected ALVC viewController;

    /* access modifiers changed from: protected */
    public abstract ALVC buildViewControllerInstance(View view);

    public AppLockDialogBuilder(Activity activity2, int i) {
        this.activity = new WeakReference<>(activity2);
        this.layoutResId = i;
    }

    public Dialog show() {
        if (this.viewController == null) {
            Activity activity2 = (Activity) this.activity.get();
            if (activity2 == null) {
                return null;
            }
            View inflate = activity2.getLayoutInflater().inflate(this.layoutResId, null, false);
            Builder builder = new Builder(activity2);
            builder.setView(inflate);
            builder.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    AppLockDialogBuilder.this.handleCanceled();
                }
            });
            builder.setOnDismissListener(new OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    AppLockDialogBuilder.this.handleDismissed();
                }
            });
            this.viewController = buildViewControllerInstance(inflate);
            this.viewController.setupRootFlow();
            AlertDialog show = builder.show();
            this.dialog = new WeakReference<>(show);
            return show;
        }
        throw new RuntimeException("You can only call show() once per AppLockDialogBuilder instance.");
    }

    /* access modifiers changed from: protected */
    public void handleCanceled() {
        dismissDialog();
    }

    /* access modifiers changed from: protected */
    public void handleDismissed() {
        this.dialog = new WeakReference<>(null);
        this.viewController.unregisterReceivers();
        Activity activity2 = (Activity) this.activity.get();
        if (activity2 != null) {
            AppLock.getInstance(activity2).cancelPendingAuthentications();
        }
    }

    /* access modifiers changed from: protected */
    public void dismissDialog() {
        try {
            AppCompatDialog appCompatDialog = (AppCompatDialog) this.dialog.get();
            if (appCompatDialog != null) {
                Context baseContext = ((ContextWrapper) appCompatDialog.getContext()).getBaseContext();
                if (!(baseContext instanceof Activity)) {
                    appCompatDialog.dismiss();
                } else if (!((Activity) baseContext).isFinishing()) {
                    if (VERSION.SDK_INT < 17) {
                        appCompatDialog.dismiss();
                    } else if (!((Activity) baseContext).isDestroyed()) {
                        appCompatDialog.dismiss();
                    }
                }
                this.dialog = new WeakReference<>(null);
            }
        } catch (Throwable unused) {
        }
    }

    public ALVC getViewController() {
        return this.viewController;
    }
}
