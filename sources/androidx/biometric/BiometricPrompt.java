package androidx.biometric;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import java.security.Signature;
import java.util.concurrent.Executor;
import javax.crypto.Cipher;
import javax.crypto.Mac;

@SuppressLint({"SyntheticAccessor"})
public class BiometricPrompt implements BiometricConstants {
    static final String BIOMETRIC_FRAGMENT_TAG = "BiometricFragment";
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_FORCE_FINGERPRINT = false;
    private static final int DELAY_MILLIS = 500;
    static final String DIALOG_FRAGMENT_TAG = "FingerprintDialogFragment";
    static final String FINGERPRINT_HELPER_FRAGMENT_TAG = "FingerprintHelperFragment";
    static final String KEY_ALLOW_DEVICE_CREDENTIAL = "allow_device_credential";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_HANDLING_DEVICE_CREDENTIAL_RESULT = "handling_device_credential_result";
    static final String KEY_NEGATIVE_TEXT = "negative_text";
    static final String KEY_REQUIRE_CONFIRMATION = "require_confirmation";
    static final String KEY_SUBTITLE = "subtitle";
    static final String KEY_TITLE = "title";
    private static final String TAG = "BiometricPromptCompat";
    /* access modifiers changed from: private */
    public final AuthenticationCallback mAuthenticationCallback;
    /* access modifiers changed from: private */
    public BiometricFragment mBiometricFragment;
    /* access modifiers changed from: private */
    public final Executor mExecutor;
    /* access modifiers changed from: private */
    public FingerprintDialogFragment mFingerprintDialogFragment;
    /* access modifiers changed from: private */
    public FingerprintHelperFragment mFingerprintHelperFragment;
    private Fragment mFragment;
    private FragmentActivity mFragmentActivity;
    private boolean mIsHandlingDeviceCredential;
    private final LifecycleObserver mLifecycleObserver = new LifecycleObserver() {
        /* access modifiers changed from: 0000 */
        @OnLifecycleEvent(Event.ON_PAUSE)
        public void onPause() {
            if (!BiometricPrompt.this.isChangingConfigurations()) {
                if (!BiometricPrompt.canUseBiometricFragment() || BiometricPrompt.this.mBiometricFragment == null) {
                    if (!(BiometricPrompt.this.mFingerprintDialogFragment == null || BiometricPrompt.this.mFingerprintHelperFragment == null)) {
                        BiometricPrompt.dismissFingerprintFragments(BiometricPrompt.this.mFingerprintDialogFragment, BiometricPrompt.this.mFingerprintHelperFragment);
                    }
                } else if (!BiometricPrompt.this.mBiometricFragment.isDeviceCredentialAllowed()) {
                    BiometricPrompt.this.mBiometricFragment.cancel();
                } else if (!BiometricPrompt.this.mPausedOnce) {
                    BiometricPrompt.this.mPausedOnce = true;
                } else {
                    BiometricPrompt.this.mBiometricFragment.cancel();
                }
                BiometricPrompt.this.maybeResetHandlerBridge();
            }
        }

        /* access modifiers changed from: 0000 */
        @OnLifecycleEvent(Event.ON_RESUME)
        public void onResume() {
            BiometricPrompt.this.mBiometricFragment = BiometricPrompt.canUseBiometricFragment() ? (BiometricFragment) BiometricPrompt.this.getFragmentManager().findFragmentByTag(BiometricPrompt.BIOMETRIC_FRAGMENT_TAG) : null;
            if (!BiometricPrompt.canUseBiometricFragment() || BiometricPrompt.this.mBiometricFragment == null) {
                BiometricPrompt biometricPrompt = BiometricPrompt.this;
                biometricPrompt.mFingerprintDialogFragment = (FingerprintDialogFragment) biometricPrompt.getFragmentManager().findFragmentByTag(BiometricPrompt.DIALOG_FRAGMENT_TAG);
                BiometricPrompt biometricPrompt2 = BiometricPrompt.this;
                biometricPrompt2.mFingerprintHelperFragment = (FingerprintHelperFragment) biometricPrompt2.getFragmentManager().findFragmentByTag(BiometricPrompt.FINGERPRINT_HELPER_FRAGMENT_TAG);
                if (BiometricPrompt.this.mFingerprintDialogFragment != null) {
                    BiometricPrompt.this.mFingerprintDialogFragment.setNegativeButtonListener(BiometricPrompt.this.mNegativeButtonListener);
                }
                if (BiometricPrompt.this.mFingerprintHelperFragment != null) {
                    BiometricPrompt.this.mFingerprintHelperFragment.setCallback(BiometricPrompt.this.mExecutor, BiometricPrompt.this.mAuthenticationCallback);
                    if (BiometricPrompt.this.mFingerprintDialogFragment != null) {
                        BiometricPrompt.this.mFingerprintHelperFragment.setHandler(BiometricPrompt.this.mFingerprintDialogFragment.getHandler());
                    }
                }
            } else {
                BiometricPrompt.this.mBiometricFragment.setCallbacks(BiometricPrompt.this.mExecutor, BiometricPrompt.this.mNegativeButtonListener, BiometricPrompt.this.mAuthenticationCallback);
            }
            BiometricPrompt.this.maybeHandleDeviceCredentialResult();
            BiometricPrompt.this.maybeInitHandlerBridge(false);
        }
    };
    /* access modifiers changed from: private */
    public final OnClickListener mNegativeButtonListener = new OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            BiometricPrompt.this.mExecutor.execute(new Runnable() {
                public void run() {
                    CharSequence charSequence = "";
                    if (BiometricPrompt.canUseBiometricFragment() && BiometricPrompt.this.mBiometricFragment != null) {
                        CharSequence negativeButtonText = BiometricPrompt.this.mBiometricFragment.getNegativeButtonText();
                        AuthenticationCallback access$200 = BiometricPrompt.this.mAuthenticationCallback;
                        if (negativeButtonText == null) {
                            negativeButtonText = charSequence;
                        }
                        access$200.onAuthenticationError(13, negativeButtonText);
                        BiometricPrompt.this.mBiometricFragment.cleanup();
                    } else if (BiometricPrompt.this.mFingerprintDialogFragment == null || BiometricPrompt.this.mFingerprintHelperFragment == null) {
                        Log.e(BiometricPrompt.TAG, "Negative button callback not run. Fragment was null.");
                    } else {
                        CharSequence negativeButtonText2 = BiometricPrompt.this.mFingerprintDialogFragment.getNegativeButtonText();
                        AuthenticationCallback access$2002 = BiometricPrompt.this.mAuthenticationCallback;
                        if (negativeButtonText2 == null) {
                            negativeButtonText2 = charSequence;
                        }
                        access$2002.onAuthenticationError(13, negativeButtonText2);
                        BiometricPrompt.this.mFingerprintHelperFragment.cancel(2);
                    }
                }
            });
        }
    };
    /* access modifiers changed from: private */
    public boolean mPausedOnce;

    public static abstract class AuthenticationCallback {
        public void onAuthenticationError(int i, @NonNull CharSequence charSequence) {
        }

        public void onAuthenticationFailed() {
        }

        public void onAuthenticationSucceeded(@NonNull AuthenticationResult authenticationResult) {
        }
    }

    public static class AuthenticationResult {
        private final CryptoObject mCryptoObject;

        AuthenticationResult(CryptoObject cryptoObject) {
            this.mCryptoObject = cryptoObject;
        }

        @Nullable
        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }
    }

    public static class CryptoObject {
        private final Cipher mCipher;
        private final Mac mMac;
        private final Signature mSignature;

        public CryptoObject(@NonNull Signature signature) {
            this.mSignature = signature;
            this.mCipher = null;
            this.mMac = null;
        }

        public CryptoObject(@NonNull Cipher cipher) {
            this.mCipher = cipher;
            this.mSignature = null;
            this.mMac = null;
        }

        public CryptoObject(@NonNull Mac mac) {
            this.mMac = mac;
            this.mCipher = null;
            this.mSignature = null;
        }

        @Nullable
        public Signature getSignature() {
            return this.mSignature;
        }

        @Nullable
        public Cipher getCipher() {
            return this.mCipher;
        }

        @Nullable
        public Mac getMac() {
            return this.mMac;
        }
    }

    public static class PromptInfo {
        private Bundle mBundle;

        public static class Builder {
            private final Bundle mBundle = new Bundle();

            @NonNull
            public Builder setTitle(@NonNull CharSequence charSequence) {
                this.mBundle.putCharSequence("title", charSequence);
                return this;
            }

            @NonNull
            public Builder setSubtitle(@Nullable CharSequence charSequence) {
                this.mBundle.putCharSequence(BiometricPrompt.KEY_SUBTITLE, charSequence);
                return this;
            }

            @NonNull
            public Builder setDescription(@Nullable CharSequence charSequence) {
                this.mBundle.putCharSequence(BiometricPrompt.KEY_DESCRIPTION, charSequence);
                return this;
            }

            @NonNull
            public Builder setNegativeButtonText(@NonNull CharSequence charSequence) {
                this.mBundle.putCharSequence(BiometricPrompt.KEY_NEGATIVE_TEXT, charSequence);
                return this;
            }

            @NonNull
            public Builder setConfirmationRequired(boolean z) {
                this.mBundle.putBoolean(BiometricPrompt.KEY_REQUIRE_CONFIRMATION, z);
                return this;
            }

            @NonNull
            public Builder setDeviceCredentialAllowed(boolean z) {
                this.mBundle.putBoolean(BiometricPrompt.KEY_ALLOW_DEVICE_CREDENTIAL, z);
                return this;
            }

            /* access modifiers changed from: 0000 */
            @NonNull
            @RestrictTo({Scope.LIBRARY})
            public Builder setHandlingDeviceCredentialResult(boolean z) {
                this.mBundle.putBoolean(BiometricPrompt.KEY_HANDLING_DEVICE_CREDENTIAL_RESULT, z);
                return this;
            }

            @NonNull
            public PromptInfo build() {
                CharSequence charSequence = this.mBundle.getCharSequence("title");
                CharSequence charSequence2 = this.mBundle.getCharSequence(BiometricPrompt.KEY_NEGATIVE_TEXT);
                boolean z = this.mBundle.getBoolean(BiometricPrompt.KEY_ALLOW_DEVICE_CREDENTIAL);
                boolean z2 = this.mBundle.getBoolean(BiometricPrompt.KEY_HANDLING_DEVICE_CREDENTIAL_RESULT);
                if (TextUtils.isEmpty(charSequence)) {
                    throw new IllegalArgumentException("Title must be set and non-empty");
                } else if (TextUtils.isEmpty(charSequence2) && !z) {
                    throw new IllegalArgumentException("Negative text must be set and non-empty");
                } else if (!TextUtils.isEmpty(charSequence2) && z) {
                    throw new IllegalArgumentException("Can't have both negative button behavior and device credential enabled");
                } else if (!z2 || z) {
                    return new PromptInfo(this.mBundle);
                } else {
                    throw new IllegalArgumentException("Can't be handling device credential result without device credential enabled");
                }
            }
        }

        PromptInfo(Bundle bundle) {
            this.mBundle = bundle;
        }

        /* access modifiers changed from: 0000 */
        public Bundle getBundle() {
            return this.mBundle;
        }

        @NonNull
        public CharSequence getTitle() {
            return this.mBundle.getCharSequence("title");
        }

        @Nullable
        public CharSequence getSubtitle() {
            return this.mBundle.getCharSequence(BiometricPrompt.KEY_SUBTITLE);
        }

        @Nullable
        public CharSequence getDescription() {
            return this.mBundle.getCharSequence(BiometricPrompt.KEY_DESCRIPTION);
        }

        @NonNull
        public CharSequence getNegativeButtonText() {
            return this.mBundle.getCharSequence(BiometricPrompt.KEY_NEGATIVE_TEXT);
        }

        public boolean isConfirmationRequired() {
            return this.mBundle.getBoolean(BiometricPrompt.KEY_REQUIRE_CONFIRMATION);
        }

        public boolean isDeviceCredentialAllowed() {
            return this.mBundle.getBoolean(BiometricPrompt.KEY_ALLOW_DEVICE_CREDENTIAL);
        }

        /* access modifiers changed from: 0000 */
        @RestrictTo({Scope.LIBRARY})
        public boolean isHandlingDeviceCredentialResult() {
            return this.mBundle.getBoolean(BiometricPrompt.KEY_HANDLING_DEVICE_CREDENTIAL_RESULT);
        }
    }

    @SuppressLint({"LambdaLast"})
    public BiometricPrompt(@NonNull FragmentActivity fragmentActivity, @NonNull Executor executor, @NonNull AuthenticationCallback authenticationCallback) {
        if (fragmentActivity == null) {
            throw new IllegalArgumentException("FragmentActivity must not be null");
        } else if (executor == null) {
            throw new IllegalArgumentException("Executor must not be null");
        } else if (authenticationCallback != null) {
            this.mFragmentActivity = fragmentActivity;
            this.mAuthenticationCallback = authenticationCallback;
            this.mExecutor = executor;
            this.mFragmentActivity.getLifecycle().addObserver(this.mLifecycleObserver);
        } else {
            throw new IllegalArgumentException("AuthenticationCallback must not be null");
        }
    }

    @SuppressLint({"LambdaLast"})
    public BiometricPrompt(@NonNull Fragment fragment, @NonNull Executor executor, @NonNull AuthenticationCallback authenticationCallback) {
        if (fragment == null) {
            throw new IllegalArgumentException("FragmentActivity must not be null");
        } else if (executor == null) {
            throw new IllegalArgumentException("Executor must not be null");
        } else if (authenticationCallback != null) {
            this.mFragment = fragment;
            this.mAuthenticationCallback = authenticationCallback;
            this.mExecutor = executor;
            this.mFragment.getLifecycle().addObserver(this.mLifecycleObserver);
        } else {
            throw new IllegalArgumentException("AuthenticationCallback must not be null");
        }
    }

    public void authenticate(@NonNull PromptInfo promptInfo, @NonNull CryptoObject cryptoObject) {
        if (promptInfo == null) {
            throw new IllegalArgumentException("PromptInfo can not be null");
        } else if (cryptoObject == null) {
            throw new IllegalArgumentException("CryptoObject can not be null");
        } else if (!promptInfo.getBundle().getBoolean(KEY_ALLOW_DEVICE_CREDENTIAL)) {
            authenticateInternal(promptInfo, cryptoObject);
        } else {
            throw new IllegalArgumentException("Device credential not supported with crypto");
        }
    }

    public void authenticate(@NonNull PromptInfo promptInfo) {
        if (promptInfo != null) {
            authenticateInternal(promptInfo, null);
            return;
        }
        throw new IllegalArgumentException("PromptInfo can not be null");
    }

    private void authenticateInternal(@NonNull PromptInfo promptInfo, @Nullable CryptoObject cryptoObject) {
        this.mIsHandlingDeviceCredential = promptInfo.isHandlingDeviceCredentialResult();
        FragmentActivity activity = getActivity();
        boolean isDeviceCredentialAllowed = promptInfo.isDeviceCredentialAllowed();
        String str = TAG;
        if (isDeviceCredentialAllowed && VERSION.SDK_INT <= 28) {
            if (!this.mIsHandlingDeviceCredential) {
                launchDeviceCredentialHandler(promptInfo);
                return;
            } else if (VERSION.SDK_INT >= 21) {
                if (activity == null) {
                    Log.e(str, "Failed to authenticate with device credential. Activity was null.");
                    return;
                }
                DeviceCredentialHandlerBridge instanceIfNotNull = DeviceCredentialHandlerBridge.getInstanceIfNotNull();
                if (instanceIfNotNull == null) {
                    Log.e(str, "Failed to authenticate with device credential. Bridge was null.");
                    return;
                } else if (!instanceIfNotNull.isConfirmingDeviceCredential() && BiometricManager.from(activity).canAuthenticate() != 0) {
                    C0179Utils.launchDeviceCredentialConfirmation(str, activity, promptInfo.getBundle(), null);
                    return;
                }
            }
        }
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.isStateSaved()) {
            Log.w(str, "Not launching prompt. authenticate() called after onSaveInstanceState()");
            return;
        }
        Bundle bundle = promptInfo.getBundle();
        boolean z = false;
        this.mPausedOnce = false;
        if (!(activity == null || cryptoObject == null || !C0179Utils.shouldUseFingerprintForCrypto(activity, Build.MANUFACTURER, Build.MODEL))) {
            z = true;
        }
        if (z || !canUseBiometricFragment()) {
            String str2 = DIALOG_FRAGMENT_TAG;
            FingerprintDialogFragment fingerprintDialogFragment = (FingerprintDialogFragment) fragmentManager.findFragmentByTag(str2);
            if (fingerprintDialogFragment != null) {
                this.mFingerprintDialogFragment = fingerprintDialogFragment;
            } else {
                this.mFingerprintDialogFragment = FingerprintDialogFragment.newInstance();
            }
            this.mFingerprintDialogFragment.setNegativeButtonListener(this.mNegativeButtonListener);
            this.mFingerprintDialogFragment.setBundle(bundle);
            if (activity != null && !C0179Utils.shouldHideFingerprintDialog(activity, Build.MODEL)) {
                if (fingerprintDialogFragment == null) {
                    this.mFingerprintDialogFragment.show(fragmentManager, str2);
                } else if (this.mFingerprintDialogFragment.isDetached()) {
                    fragmentManager.beginTransaction().attach(this.mFingerprintDialogFragment).commitAllowingStateLoss();
                }
            }
            String str3 = FINGERPRINT_HELPER_FRAGMENT_TAG;
            FingerprintHelperFragment fingerprintHelperFragment = (FingerprintHelperFragment) fragmentManager.findFragmentByTag(str3);
            if (fingerprintHelperFragment != null) {
                this.mFingerprintHelperFragment = fingerprintHelperFragment;
            } else {
                this.mFingerprintHelperFragment = FingerprintHelperFragment.newInstance();
            }
            this.mFingerprintHelperFragment.setCallback(this.mExecutor, this.mAuthenticationCallback);
            Handler handler = this.mFingerprintDialogFragment.getHandler();
            this.mFingerprintHelperFragment.setHandler(handler);
            this.mFingerprintHelperFragment.setCryptoObject(cryptoObject);
            handler.sendMessageDelayed(handler.obtainMessage(6), 500);
            if (fingerprintHelperFragment == null) {
                fragmentManager.beginTransaction().add((Fragment) this.mFingerprintHelperFragment, str3).commitAllowingStateLoss();
            } else if (this.mFingerprintHelperFragment.isDetached()) {
                fragmentManager.beginTransaction().attach(this.mFingerprintHelperFragment).commitAllowingStateLoss();
            }
        } else {
            String str4 = BIOMETRIC_FRAGMENT_TAG;
            BiometricFragment biometricFragment = (BiometricFragment) fragmentManager.findFragmentByTag(str4);
            if (biometricFragment != null) {
                this.mBiometricFragment = biometricFragment;
            } else {
                this.mBiometricFragment = BiometricFragment.newInstance();
            }
            this.mBiometricFragment.setCallbacks(this.mExecutor, this.mNegativeButtonListener, this.mAuthenticationCallback);
            this.mBiometricFragment.setCryptoObject(cryptoObject);
            this.mBiometricFragment.setBundle(bundle);
            if (biometricFragment == null) {
                fragmentManager.beginTransaction().add((Fragment) this.mBiometricFragment, str4).commitAllowingStateLoss();
            } else if (this.mBiometricFragment.isDetached()) {
                fragmentManager.beginTransaction().attach(this.mBiometricFragment).commitAllowingStateLoss();
            }
        }
        fragmentManager.executePendingTransactions();
    }

    public void cancelAuthentication() {
        if (canUseBiometricFragment()) {
            BiometricFragment biometricFragment = this.mBiometricFragment;
            if (biometricFragment != null) {
                biometricFragment.cancel();
                if (!this.mIsHandlingDeviceCredential) {
                    DeviceCredentialHandlerBridge instanceIfNotNull = DeviceCredentialHandlerBridge.getInstanceIfNotNull();
                    if (instanceIfNotNull != null && instanceIfNotNull.getBiometricFragment() != null) {
                        instanceIfNotNull.getBiometricFragment().cancel();
                        return;
                    }
                    return;
                }
                return;
            }
        }
        FingerprintHelperFragment fingerprintHelperFragment = this.mFingerprintHelperFragment;
        if (fingerprintHelperFragment != null) {
            FingerprintDialogFragment fingerprintDialogFragment = this.mFingerprintDialogFragment;
            if (fingerprintDialogFragment != null) {
                dismissFingerprintFragments(fingerprintDialogFragment, fingerprintHelperFragment);
            }
        }
        if (!this.mIsHandlingDeviceCredential) {
            DeviceCredentialHandlerBridge instanceIfNotNull2 = DeviceCredentialHandlerBridge.getInstanceIfNotNull();
            if (instanceIfNotNull2 != null && instanceIfNotNull2.getFingerprintDialogFragment() != null && instanceIfNotNull2.getFingerprintHelperFragment() != null) {
                dismissFingerprintFragments(instanceIfNotNull2.getFingerprintDialogFragment(), instanceIfNotNull2.getFingerprintHelperFragment());
            }
        }
    }

    private void launchDeviceCredentialHandler(PromptInfo promptInfo) {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            Log.w(TAG, "Failed to start handler activity. Parent activity was null or finishing.");
            return;
        }
        maybeInitHandlerBridge(true);
        Bundle bundle = promptInfo.getBundle();
        bundle.putBoolean(KEY_HANDLING_DEVICE_CREDENTIAL_RESULT, true);
        Intent intent = new Intent(activity, DeviceCredentialHandlerActivity.class);
        intent.putExtra("prompt_info_bundle", bundle);
        activity.startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void maybeInitHandlerBridge(boolean z) {
        if (VERSION.SDK_INT < 29) {
            DeviceCredentialHandlerBridge instance = DeviceCredentialHandlerBridge.getInstance();
            if (this.mIsHandlingDeviceCredential) {
                if (canUseBiometricFragment()) {
                    BiometricFragment biometricFragment = this.mBiometricFragment;
                    if (biometricFragment != null) {
                        instance.setBiometricFragment(biometricFragment);
                    }
                }
                FingerprintDialogFragment fingerprintDialogFragment = this.mFingerprintDialogFragment;
                if (fingerprintDialogFragment != null) {
                    FingerprintHelperFragment fingerprintHelperFragment = this.mFingerprintHelperFragment;
                    if (fingerprintHelperFragment != null) {
                        instance.setFingerprintFragments(fingerprintDialogFragment, fingerprintHelperFragment);
                    }
                }
            } else {
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    try {
                        instance.setClientThemeResId(activity.getPackageManager().getActivityInfo(activity.getComponentName(), 0).getThemeResource());
                    } catch (NameNotFoundException e) {
                        Log.e(TAG, "Failed to register client theme to bridge", e);
                    }
                }
            }
            instance.setCallbacks(this.mExecutor, this.mNegativeButtonListener, this.mAuthenticationCallback);
            if (z) {
                instance.startIgnoringReset();
            }
        }
    }

    /* access modifiers changed from: private */
    public void maybeHandleDeviceCredentialResult() {
        if (!this.mIsHandlingDeviceCredential) {
            DeviceCredentialHandlerBridge instanceIfNotNull = DeviceCredentialHandlerBridge.getInstanceIfNotNull();
            if (instanceIfNotNull != null) {
                int deviceCredentialResult = instanceIfNotNull.getDeviceCredentialResult();
                if (deviceCredentialResult == 1) {
                    this.mAuthenticationCallback.onAuthenticationSucceeded(new AuthenticationResult(null));
                    instanceIfNotNull.stopIgnoringReset();
                    instanceIfNotNull.reset();
                } else if (deviceCredentialResult == 2) {
                    this.mAuthenticationCallback.onAuthenticationError(10, getActivity() != null ? getActivity().getString(C0173R.string.generic_error_user_canceled) : "");
                    instanceIfNotNull.stopIgnoringReset();
                    instanceIfNotNull.reset();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void maybeResetHandlerBridge() {
        DeviceCredentialHandlerBridge instanceIfNotNull = DeviceCredentialHandlerBridge.getInstanceIfNotNull();
        if (instanceIfNotNull != null) {
            instanceIfNotNull.reset();
        }
    }

    /* access modifiers changed from: private */
    public boolean isChangingConfigurations() {
        return getActivity() != null && getActivity().isChangingConfigurations();
    }

    @Nullable
    private FragmentActivity getActivity() {
        FragmentActivity fragmentActivity = this.mFragmentActivity;
        return fragmentActivity != null ? fragmentActivity : this.mFragment.getActivity();
    }

    /* access modifiers changed from: private */
    public FragmentManager getFragmentManager() {
        FragmentActivity fragmentActivity = this.mFragmentActivity;
        if (fragmentActivity != null) {
            return fragmentActivity.getSupportFragmentManager();
        }
        return this.mFragment.getChildFragmentManager();
    }

    /* access modifiers changed from: private */
    public static boolean canUseBiometricFragment() {
        return VERSION.SDK_INT >= 28;
    }

    /* access modifiers changed from: private */
    public static void dismissFingerprintFragments(@NonNull FingerprintDialogFragment fingerprintDialogFragment, @NonNull FingerprintHelperFragment fingerprintHelperFragment) {
        fingerprintDialogFragment.dismissSafely();
        fingerprintHelperFragment.cancel(0);
    }
}
