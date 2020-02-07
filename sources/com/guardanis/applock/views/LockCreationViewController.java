package com.guardanis.applock.views;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.core.content.ContextCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationResult;
import androidx.core.p003os.CancellationSignal;
import com.guardanis.applock.AppLock;
import com.guardanis.applock.C2245R;
import com.guardanis.applock.pin.PINInputController.InputEventListener;
import com.guardanis.applock.services.FingerprintLockService;
import com.guardanis.applock.services.FingerprintLockService.AuthenticationDelegate;
import com.guardanis.applock.services.PINLockService;
import java.lang.ref.WeakReference;

public class LockCreationViewController extends AppLockViewController implements InputEventListener, AuthenticationDelegate {
    protected WeakReference<View> chooserParent;
    protected WeakReference<Delegate> delegate;
    protected DisplayVariant displayVariant = DisplayVariant.NONE;
    protected String pinFirst;

    /* renamed from: com.guardanis.applock.views.LockCreationViewController$3 */
    static /* synthetic */ class C22583 {

        /* renamed from: $SwitchMap$com$guardanis$applock$views$LockCreationViewController$DisplayVariant */
        static final /* synthetic */ int[] f637x285d9f03 = new int[DisplayVariant.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.guardanis.applock.views.LockCreationViewController$DisplayVariant[] r0 = com.guardanis.applock.views.LockCreationViewController.DisplayVariant.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f637x285d9f03 = r0
                int[] r0 = f637x285d9f03     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.guardanis.applock.views.LockCreationViewController$DisplayVariant r1 = com.guardanis.applock.views.LockCreationViewController.DisplayVariant.PIN_CREATION     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f637x285d9f03     // Catch:{ NoSuchFieldError -> 0x001f }
                com.guardanis.applock.views.LockCreationViewController$DisplayVariant r1 = com.guardanis.applock.views.LockCreationViewController.DisplayVariant.PIN_CONFIRMATION     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.guardanis.applock.views.LockCreationViewController.C22583.<clinit>():void");
        }
    }

    public interface Delegate {
        void onLockCreated();
    }

    public enum DisplayVariant {
        NONE,
        CHOOSER,
        PIN_CREATION,
        PIN_CONFIRMATION,
        FINGERPRINT_AUTHENTICATION
    }

    public void onAuthenticating(CancellationSignal cancellationSignal) {
    }

    public LockCreationViewController(Activity activity, View view) {
        super(activity, view);
        this.chooserParent = new WeakReference<>(view.findViewById(C2245R.C2248id.pin__create_chooser_items));
    }

    public LockCreationViewController setDelegate(Delegate delegate2) {
        this.delegate = new WeakReference<>(delegate2);
        return this;
    }

    public void setupRootFlow() {
        Activity activity = (Activity) this.activity.get();
        if (activity != null) {
            if (!((FingerprintLockService) AppLock.getInstance(activity).getLockService(FingerprintLockService.class)).isEnrollmentEligible(activity)) {
                setupPINCreation();
            } else {
                setupCreationChooser();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setupCreationChooser() {
        this.displayVariant = DisplayVariant.CHOOSER;
        hide(this.fingerprintAuthImageView);
        hide(this.pinInputView);
        hide(this.actionSettings);
        show(this.chooserParent);
        setDescription(C2245R.string.applock__description_chooser);
        View view = (View) this.parent.get();
        if (view != null) {
            view.findViewById(C2245R.C2248id.pin__create_option_pin).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    LockCreationViewController.this.setupPINCreation();
                }
            });
            view.findViewById(C2245R.C2248id.pin__create_option_fingerprint).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    LockCreationViewController.this.setupFingerprintAuthentication();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void setupPINCreation() {
        this.displayVariant = DisplayVariant.PIN_CREATION;
        hide(this.fingerprintAuthImageView);
        hide(this.chooserParent);
        hide(this.actionSettings);
        show(this.pinInputView);
        setDescription(C2245R.string.applock__description_create_pin);
        this.pinInputController.ensureKeyboardVisible();
        this.pinInputController.setInputEventListener(this);
    }

    /* access modifiers changed from: protected */
    public void setupPINConfirmation() {
        this.displayVariant = DisplayVariant.PIN_CONFIRMATION;
        hide(this.fingerprintAuthImageView);
        hide(this.chooserParent);
        hide(this.actionSettings);
        show(this.pinInputView);
        setDescription(C2245R.string.applock__description_confirm);
        this.pinInputController.ensureKeyboardVisible();
        this.pinInputController.setInputEventListener(this);
    }

    public void onInputEntered(String str) {
        int i = C22583.f637x285d9f03[this.displayVariant.ordinal()];
        if (i != 1) {
            if (i == 2) {
                if (!this.pinInputController.matchesRequiredPINLength(str)) {
                    setDescription(C2245R.string.applock__unlock_error_insufficient_selection);
                } else if (!str.equals(this.pinFirst)) {
                    this.pinFirst = null;
                    setupPINCreation();
                    setDescription(C2245R.string.applock__description_create_pin_reattempt);
                } else {
                    createPINLock(str);
                }
            }
        } else if (!this.pinInputController.matchesRequiredPINLength(str)) {
            setDescription(C2245R.string.applock__unlock_error_insufficient_selection);
        } else {
            this.pinFirst = str;
            setupPINConfirmation();
        }
    }

    /* access modifiers changed from: protected */
    public void createPINLock(String str) {
        Activity activity = (Activity) this.activity.get();
        if (activity != null) {
            ((PINLockService) AppLock.getInstance(activity).getLockService(PINLockService.class)).enroll(activity, str);
            handleLockCreated();
        }
    }

    /* access modifiers changed from: protected */
    public void setupFingerprintAuthentication() {
        this.displayVariant = DisplayVariant.FINGERPRINT_AUTHENTICATION;
        hide(this.pinInputView);
        hide(this.chooserParent);
        hide(this.actionSettings);
        show(this.fingerprintAuthImageView);
        setDescription(C2245R.string.applock__description_create_fingerprint);
        if (this.autoAuthorizationEnabled) {
            attemptFingerprintAuthentication();
        }
    }

    /* access modifiers changed from: protected */
    public void attemptFingerprintAuthentication() {
        Activity activity = (Activity) this.activity.get();
        if (activity != null) {
            ((FingerprintLockService) AppLock.getInstance(activity).getLockService(FingerprintLockService.class)).enroll(activity, this);
        }
    }

    public void onResolutionRequired(int i) {
        setDescription(getDescriptionResIdForError(i));
        updateActionSettings(i);
        handleInitialErrorPrompt(i);
    }

    public void onAuthenticationHelp(int i, CharSequence charSequence) {
        Activity activity = (Activity) this.activity.get();
        if (activity != null) {
            setDescription(String.format(activity.getString(C2245R.string.applock__description_unlock_fingerprint_help), new Object[]{charSequence}));
        }
    }

    public void onAuthenticationSuccess(AuthenticationResult authenticationResult) {
        handleLockCreated();
    }

    public void onAuthenticationFailed(String str) {
        setDescription(str);
    }

    /* access modifiers changed from: protected */
    public void handleLockCreated() {
        this.displayVariant = DisplayVariant.NONE;
        Delegate delegate2 = (Delegate) this.delegate.get();
        if (delegate2 != null) {
            delegate2.onLockCreated();
        }
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
            setDescription(C2245R.string.applock__description_create_fingerprint);
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
