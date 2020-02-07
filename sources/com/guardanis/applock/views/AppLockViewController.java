package com.guardanis.applock.views;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import com.guardanis.applock.AppLock;
import com.guardanis.applock.C2245R;
import com.guardanis.applock.pin.PINInputController;
import com.guardanis.applock.pin.PINInputView;
import com.guardanis.applock.utils.LifeCycleUtils;
import com.guardanis.applock.utils.LifeCycleUtils.AppLockActivityLifeCycleCallbacks.Delegate;
import java.lang.ref.WeakReference;

public abstract class AppLockViewController implements Delegate {
    protected WeakReference<View> actionSettings;
    protected WeakReference<Activity> activity;
    protected ActivityLifecycleCallbacks activityLifecycleCallbacks;
    protected boolean autoAuthorizationEnabled = true;
    protected WeakReference<TextView> descriptionView;
    protected WeakReference<AppCompatImageView> fingerprintAuthImageView;
    protected WeakReference<View> parent;
    protected PINInputController pinInputController;
    protected WeakReference<PINInputView> pinInputView;

    /* access modifiers changed from: protected */
    public abstract void handleActionSettingsClicked(int i);

    public void onActivityPaused() {
    }

    public void onActivityResumed() {
    }

    public abstract void setupRootFlow();

    public AppLockViewController(Activity activity2, View view) {
        this.activity = new WeakReference<>(activity2);
        this.parent = new WeakReference<>(view);
        this.descriptionView = new WeakReference<>((TextView) view.findViewById(C2245R.C2248id.pin__description));
        this.actionSettings = new WeakReference<>(view.findViewById(C2245R.C2248id.pin__action_settings));
        this.pinInputView = new WeakReference<>((PINInputView) view.findViewById(C2245R.C2248id.pin__input_view));
        this.fingerprintAuthImageView = new WeakReference<>(view.findViewById(C2245R.C2248id.pin__fingerprint_image));
        int integer = view.getResources().getInteger(C2245R.integer.applock__input_pin_item_count);
        this.pinInputController = new PINInputController((PINInputView) this.pinInputView.get()).setInputNumbersCount(integer).setPasswordCharactersEnabled(view.getResources().getBoolean(C2245R.bool.applock__item_password_chars_enabled));
        this.activityLifecycleCallbacks = LifeCycleUtils.attach(activity2, this);
    }

    public void setDescription(int i) {
        TextView textView = (TextView) this.descriptionView.get();
        if (textView != null) {
            textView.setText(i);
        }
    }

    public void setDescription(String str) {
        TextView textView = (TextView) this.descriptionView.get();
        if (textView != null) {
            textView.setText(str);
        }
    }

    /* access modifiers changed from: protected */
    public <T extends View> void hide(WeakReference<T> weakReference) {
        View view = (View) weakReference.get();
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public <T extends View> void show(WeakReference<T> weakReference) {
        View view = (View) weakReference.get();
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public PINInputController getPINInputController() {
        return this.pinInputController;
    }

    public View getParent() {
        return (View) this.parent.get();
    }

    public void setAutoAuthorizationEnabled(boolean z) {
        this.autoAuthorizationEnabled = z;
    }

    public void updateActionSettings(final int i) {
        View view = (View) this.actionSettings.get();
        if (view != null) {
            if (getSettingsIntent(i) == null) {
                view.setVisibility(8);
                return;
            }
            view.setVisibility(0);
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AppLockViewController.this.handleActionSettingsClicked(i);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void handleInitialErrorPrompt(int i) {
        Activity activity2 = (Activity) this.activity.get();
        if (activity2 != null && i == 2) {
            ActivityCompat.requestPermissions(activity2, new String[]{"android.permission.USE_FINGERPRINT"}, AppLock.REQUEST_CODE_FINGERPRINT_PERMISSION);
        }
    }

    /* access modifiers changed from: protected */
    public int getDescriptionResIdForError(int i) {
        if (i == 1) {
            return C2245R.string.applock__fingerprint_error_hardware;
        }
        if (i == 2) {
            return C2245R.string.applock__fingerprint_error_permission;
        }
        if (i == 3) {
            return C2245R.string.applock__fingerprint_error_none;
        }
        if (i != 4) {
            return C2245R.string.applock__fingerprint_error_unknown;
        }
        return C2245R.string.applock__fingerprint_error_not_enrolled;
    }

    public Intent getSettingsIntent(int i) {
        if (i == 1 || i == 2 || i == 3) {
            return new Intent("android.settings.SECURITY_SETTINGS");
        }
        return null;
    }

    public void unregisterReceivers() {
        Activity activity2 = (Activity) this.activity.get();
        if (activity2 != null) {
            activity2.getApplication().unregisterActivityLifecycleCallbacks(this.activityLifecycleCallbacks);
        }
    }
}
