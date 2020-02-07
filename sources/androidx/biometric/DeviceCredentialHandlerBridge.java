package androidx.biometric;

import android.annotation.SuppressLint;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.biometric.BiometricPrompt.AuthenticationCallback;
import java.util.concurrent.Executor;

@RestrictTo({Scope.LIBRARY})
public class DeviceCredentialHandlerBridge {
    private static final int IGNORING_NEXT_RESET = 1;
    private static final int IGNORING_RESET = 2;
    private static final int NOT_IGNORING_RESET = 0;
    static final int RESULT_ERROR = 2;
    static final int RESULT_NONE = 0;
    static final int RESULT_SUCCESS = 1;
    @Nullable
    private static DeviceCredentialHandlerBridge sInstance;
    @Nullable
    private AuthenticationCallback mAuthenticationCallback;
    @Nullable
    private BiometricFragment mBiometricFragment;
    private int mClientThemeResId;
    private boolean mConfirmingDeviceCredential;
    private int mDeviceCredentialResult = 0;
    @Nullable
    private Executor mExecutor;
    @Nullable
    private FingerprintDialogFragment mFingerprintDialogFragment;
    @Nullable
    private FingerprintHelperFragment mFingerprintHelperFragment;
    private int mIgnoreResetState = 0;
    @Nullable
    private OnClickListener mOnClickListener;

    private DeviceCredentialHandlerBridge() {
    }

    @NonNull
    static DeviceCredentialHandlerBridge getInstance() {
        if (sInstance == null) {
            sInstance = new DeviceCredentialHandlerBridge();
        }
        return sInstance;
    }

    @Nullable
    static DeviceCredentialHandlerBridge getInstanceIfNotNull() {
        return sInstance;
    }

    /* access modifiers changed from: 0000 */
    public void setClientThemeResId(int i) {
        this.mClientThemeResId = i;
    }

    /* access modifiers changed from: 0000 */
    public int getClientThemeResId() {
        return this.mClientThemeResId;
    }

    /* access modifiers changed from: 0000 */
    public void setBiometricFragment(@Nullable BiometricFragment biometricFragment) {
        this.mBiometricFragment = biometricFragment;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public BiometricFragment getBiometricFragment() {
        return this.mBiometricFragment;
    }

    /* access modifiers changed from: 0000 */
    public void setFingerprintFragments(@Nullable FingerprintDialogFragment fingerprintDialogFragment, @Nullable FingerprintHelperFragment fingerprintHelperFragment) {
        this.mFingerprintDialogFragment = fingerprintDialogFragment;
        this.mFingerprintHelperFragment = fingerprintHelperFragment;
    }

    @Nullable
    public FingerprintDialogFragment getFingerprintDialogFragment() {
        return this.mFingerprintDialogFragment;
    }

    @Nullable
    public FingerprintHelperFragment getFingerprintHelperFragment() {
        return this.mFingerprintHelperFragment;
    }

    /* access modifiers changed from: 0000 */
    @SuppressLint({"LambdaLast"})
    public void setCallbacks(@NonNull Executor executor, @NonNull OnClickListener onClickListener, @NonNull AuthenticationCallback authenticationCallback) {
        this.mExecutor = executor;
        this.mOnClickListener = onClickListener;
        this.mAuthenticationCallback = authenticationCallback;
        if (this.mBiometricFragment == null || VERSION.SDK_INT < 28) {
            FingerprintDialogFragment fingerprintDialogFragment = this.mFingerprintDialogFragment;
            if (fingerprintDialogFragment != null && this.mFingerprintHelperFragment != null) {
                fingerprintDialogFragment.setNegativeButtonListener(onClickListener);
                this.mFingerprintHelperFragment.setCallback(executor, authenticationCallback);
                this.mFingerprintHelperFragment.setHandler(this.mFingerprintDialogFragment.getHandler());
                return;
            }
            return;
        }
        this.mBiometricFragment.setCallbacks(executor, onClickListener, authenticationCallback);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Executor getExecutor() {
        return this.mExecutor;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public OnClickListener getOnClickListener() {
        return this.mOnClickListener;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public AuthenticationCallback getAuthenticationCallback() {
        return this.mAuthenticationCallback;
    }

    /* access modifiers changed from: 0000 */
    public void setDeviceCredentialResult(int i) {
        this.mDeviceCredentialResult = i;
    }

    /* access modifiers changed from: 0000 */
    public int getDeviceCredentialResult() {
        return this.mDeviceCredentialResult;
    }

    /* access modifiers changed from: 0000 */
    public void setConfirmingDeviceCredential(boolean z) {
        this.mConfirmingDeviceCredential = z;
    }

    /* access modifiers changed from: 0000 */
    public boolean isConfirmingDeviceCredential() {
        return this.mConfirmingDeviceCredential;
    }

    /* access modifiers changed from: 0000 */
    public void ignoreNextReset() {
        if (this.mIgnoreResetState == 0) {
            this.mIgnoreResetState = 1;
        }
    }

    /* access modifiers changed from: 0000 */
    public void startIgnoringReset() {
        this.mIgnoreResetState = 2;
    }

    /* access modifiers changed from: 0000 */
    public void stopIgnoringReset() {
        this.mIgnoreResetState = 0;
    }

    /* access modifiers changed from: 0000 */
    public void reset() {
        int i = this.mIgnoreResetState;
        if (i != 2) {
            if (i == 1) {
                stopIgnoringReset();
                return;
            }
            this.mClientThemeResId = 0;
            this.mBiometricFragment = null;
            this.mFingerprintDialogFragment = null;
            this.mFingerprintHelperFragment = null;
            this.mExecutor = null;
            this.mOnClickListener = null;
            this.mAuthenticationCallback = null;
            this.mDeviceCredentialResult = 0;
            this.mConfirmingDeviceCredential = false;
            sInstance = null;
        }
    }
}
