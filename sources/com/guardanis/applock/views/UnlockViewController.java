package com.guardanis.applock.views;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.guardanis.applock.AppLock;
import com.guardanis.applock.AppLock.UnlockDelegate;
import com.guardanis.applock.C2245R;
import com.guardanis.applock.pin.PINInputController.InputEventListener;
import com.guardanis.applock.services.FingerprintLockService;
import java.lang.ref.WeakReference;

public class UnlockViewController extends AppLockViewController implements UnlockDelegate, InputEventListener {
    protected WeakReference<Delegate> delegate;
    protected DisplayVariant displayVariant = DisplayVariant.PIN_UNLOCK;

    public interface Delegate {
        void onUnlockSuccessful();
    }

    public enum DisplayVariant {
        NONE,
        PIN_UNLOCK,
        FINGERPRINT_AUTHENTICATION
    }

    public UnlockViewController(Activity activity, View view) {
        super(activity, view);
    }

    public UnlockViewController setDelegate(Delegate delegate2) {
        this.delegate = new WeakReference<>(delegate2);
        return this;
    }

    public void setupRootFlow() {
        View view = (View) this.parent.get();
        if (view != null) {
            if (((FingerprintLockService) AppLock.getInstance(view.getContext()).getLockService(FingerprintLockService.class)).isEnrolled(view.getContext())) {
                setupFingerprintUnlock();
            } else {
                setupPINUnlock();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setupPINUnlock() {
        this.displayVariant = DisplayVariant.PIN_UNLOCK;
        hide(this.fingerprintAuthImageView);
        hide(this.actionSettings);
        show(this.pinInputView);
        setDescription(C2245R.string.applock__description_unlock_pin);
        this.pinInputController.ensureKeyboardVisible();
        this.pinInputController.setInputEventListener(this);
    }

    public void onInputEntered(String str) {
        if (!this.pinInputController.matchesRequiredPINLength(str)) {
            setDescription(C2245R.string.applock__unlock_error_insufficient_selection);
        } else {
            attemptPINUnlock(str);
        }
    }

    /* access modifiers changed from: protected */
    public void attemptPINUnlock(String str) {
        Activity activity = (Activity) this.activity.get();
        if (activity != null) {
            AppLock.getInstance(activity).attemptPINUnlock(str, this);
        }
    }

    /* access modifiers changed from: protected */
    public void setupFingerprintUnlock() {
        this.displayVariant = DisplayVariant.FINGERPRINT_AUTHENTICATION;
        hide(this.pinInputView);
        hide(this.actionSettings);
        show(this.fingerprintAuthImageView);
        setDescription(C2245R.string.applock__description_unlock_fingerprint);
        if (this.autoAuthorizationEnabled) {
            attemptFingerprintAuthentication();
        }
    }

    /* access modifiers changed from: protected */
    public void attemptFingerprintAuthentication() {
        Activity activity = (Activity) this.activity.get();
        if (activity != null) {
            AppLock.getInstance(activity).attemptFingerprintUnlock(this);
        }
    }

    public void onUnlockSuccessful() {
        this.displayVariant = DisplayVariant.NONE;
        Delegate delegate2 = (Delegate) this.delegate.get();
        if (delegate2 != null) {
            delegate2.onUnlockSuccessful();
        }
    }

    public void onResolutionRequired(int i) {
        setDescription(getDescriptionResIdForError(i));
        updateActionSettings(i);
        handleInitialErrorPrompt(i);
    }

    public void onAuthenticationHelp(int i, String str) {
        Activity activity = (Activity) this.activity.get();
        if (activity != null) {
            setDescription(String.format(activity.getString(C2245R.string.applock__description_unlock_fingerprint_help), new Object[]{str}));
        }
    }

    public void onFailureLimitExceeded(String str) {
        setDescription(str);
    }

    public void onActivityPaused() {
        Activity activity = (Activity) this.activity.get();
        if (activity != null) {
            AppLock.getInstance(activity).cancelPendingAuthentications();
            if (this.displayVariant == DisplayVariant.FINGERPRINT_AUTHENTICATION) {
                setDescription(C2245R.string.applock__description_create_fingerprint_paused);
            }
        }
    }

    public void onActivityResumed() {
        Activity activity = (Activity) this.activity.get();
        if (activity != null && this.displayVariant == DisplayVariant.FINGERPRINT_AUTHENTICATION) {
            if (ContextCompat.checkSelfPermission(activity, "android.permission.USE_FINGERPRINT") != 0) {
                setDescription(C2245R.string.applock__fingerprint_error_permission_multiple);
                updateActionSettings(2);
                return;
            }
            setDescription(C2245R.string.applock__description_unlock_fingerprint);
            hide(this.actionSettings);
            attemptFingerprintAuthentication();
        }
    }

    /* access modifiers changed from: protected */
    public void handleActionSettingsClicked(int i) {
        Activity activity = (Activity) this.activity.get();
        Intent settingsIntent = getSettingsIntent(i);
        if (activity != null && settingsIntent != null) {
            activity.startActivity(settingsIntent);
        }
    }
}
