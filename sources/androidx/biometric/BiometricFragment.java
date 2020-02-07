package androidx.biometric;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.biometrics.BiometricPrompt.AuthenticationCallback;
import android.hardware.biometrics.BiometricPrompt.AuthenticationResult;
import android.hardware.biometrics.BiometricPrompt.Builder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.VisibleForTesting;
import androidx.biometric.BiometricPrompt.CryptoObject;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.util.concurrent.Executor;
import org.spongycastle.i18n.MessageBundle;

@RequiresApi(28)
@SuppressLint({"SyntheticAccessor"})
@RestrictTo({Scope.LIBRARY})
public class BiometricFragment extends Fragment {
    private static final String TAG = "BiometricFragment";
    @VisibleForTesting
    final AuthenticationCallback mAuthenticationCallback = new AuthenticationCallback() {
        public void onAuthenticationHelp(int i, CharSequence charSequence) {
        }

        public void onAuthenticationError(final int i, final CharSequence charSequence) {
            if (!C0179Utils.isConfirmingDeviceCredential()) {
                BiometricFragment.this.mClientExecutor.execute(new Runnable() {
                    public void run() {
                        CharSequence charSequence = charSequence;
                        if (charSequence == null) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(BiometricFragment.this.mContext.getString(C0173R.string.default_error_msg));
                            sb.append(" ");
                            sb.append(i);
                            charSequence = sb.toString();
                        }
                        BiometricFragment.this.mClientAuthenticationCallback.onAuthenticationError(C0179Utils.isUnknownError(i) ? 8 : i, charSequence);
                    }
                });
                BiometricFragment.this.cleanup();
            }
        }

        public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
            final BiometricPrompt.AuthenticationResult authenticationResult2 = authenticationResult != null ? new BiometricPrompt.AuthenticationResult(BiometricFragment.unwrapCryptoObject(authenticationResult.getCryptoObject())) : new BiometricPrompt.AuthenticationResult(null);
            BiometricFragment.this.mClientExecutor.execute(new Runnable() {
                public void run() {
                    BiometricFragment.this.mClientAuthenticationCallback.onAuthenticationSucceeded(authenticationResult2);
                }
            });
            BiometricFragment.this.cleanup();
        }

        public void onAuthenticationFailed() {
            BiometricFragment.this.mClientExecutor.execute(new Runnable() {
                public void run() {
                    BiometricFragment.this.mClientAuthenticationCallback.onAuthenticationFailed();
                }
            });
        }
    };
    private BiometricPrompt mBiometricPrompt;
    /* access modifiers changed from: private */
    public Bundle mBundle;
    private CancellationSignal mCancellationSignal;
    @VisibleForTesting
    BiometricPrompt.AuthenticationCallback mClientAuthenticationCallback;
    @VisibleForTesting
    Executor mClientExecutor;
    @VisibleForTesting
    OnClickListener mClientNegativeButtonListener;
    /* access modifiers changed from: private */
    public Context mContext;
    private CryptoObject mCryptoObject;
    private final OnClickListener mDeviceCredentialButtonListener = new OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == -2) {
                C0179Utils.launchDeviceCredentialConfirmation(BiometricFragment.TAG, BiometricFragment.this.getActivity(), BiometricFragment.this.mBundle, null);
            }
        }
    };
    private final Executor mExecutor = new Executor() {
        public void execute(@NonNull Runnable runnable) {
            BiometricFragment.this.mHandler.post(runnable);
        }
    };
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    private final OnClickListener mNegativeButtonListener = new OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            BiometricFragment.this.mClientNegativeButtonListener.onClick(dialogInterface, i);
        }
    };
    private CharSequence mNegativeButtonText;
    private boolean mShowing;
    /* access modifiers changed from: private */
    public boolean mStartRespectingCancel;

    static BiometricFragment newInstance() {
        return new BiometricFragment();
    }

    /* access modifiers changed from: 0000 */
    public void setCallbacks(Executor executor, OnClickListener onClickListener, BiometricPrompt.AuthenticationCallback authenticationCallback) {
        this.mClientExecutor = executor;
        this.mClientNegativeButtonListener = onClickListener;
        this.mClientAuthenticationCallback = authenticationCallback;
    }

    /* access modifiers changed from: 0000 */
    public void setCryptoObject(CryptoObject cryptoObject) {
        this.mCryptoObject = cryptoObject;
    }

    /* access modifiers changed from: 0000 */
    public void cancel() {
        if (VERSION.SDK_INT < 29 || !isDeviceCredentialAllowed() || this.mStartRespectingCancel) {
            CancellationSignal cancellationSignal = this.mCancellationSignal;
            if (cancellationSignal != null) {
                cancellationSignal.cancel();
            }
            cleanup();
            return;
        }
        Log.w(TAG, "Ignoring fast cancel signal");
    }

    /* access modifiers changed from: 0000 */
    public void cleanup() {
        this.mShowing = false;
        FragmentActivity activity = getActivity();
        if (getFragmentManager() != null) {
            getFragmentManager().beginTransaction().detach(this).commitAllowingStateLoss();
        }
        C0179Utils.maybeFinishHandler(activity);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public CharSequence getNegativeButtonText() {
        return this.mNegativeButtonText;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    /* access modifiers changed from: 0000 */
    public void setBundle(@Nullable Bundle bundle) {
        this.mBundle = bundle;
    }

    /* access modifiers changed from: 0000 */
    public boolean isDeviceCredentialAllowed() {
        Bundle bundle = this.mBundle;
        if (bundle == null || !bundle.getBoolean("allow_device_credential", false)) {
            return false;
        }
        return true;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (!this.mShowing) {
            Bundle bundle2 = this.mBundle;
            if (bundle2 != null) {
                this.mNegativeButtonText = bundle2.getCharSequence("negative_text");
                Builder builder = new Builder(getContext());
                builder.setTitle(this.mBundle.getCharSequence(MessageBundle.TITLE_ENTRY)).setSubtitle(this.mBundle.getCharSequence("subtitle")).setDescription(this.mBundle.getCharSequence("description"));
                boolean z = this.mBundle.getBoolean("allow_device_credential");
                if (z && VERSION.SDK_INT <= 28) {
                    this.mNegativeButtonText = getString(C0173R.string.confirm_device_credential_password);
                    builder.setNegativeButton(this.mNegativeButtonText, this.mClientExecutor, this.mDeviceCredentialButtonListener);
                } else if (!TextUtils.isEmpty(this.mNegativeButtonText)) {
                    builder.setNegativeButton(this.mNegativeButtonText, this.mClientExecutor, this.mNegativeButtonListener);
                }
                if (VERSION.SDK_INT >= 29) {
                    builder.setConfirmationRequired(this.mBundle.getBoolean("require_confirmation", true));
                    builder.setDeviceCredentialAllowed(z);
                }
                if (z) {
                    this.mStartRespectingCancel = false;
                    this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            BiometricFragment.this.mStartRespectingCancel = true;
                        }
                    }, 250);
                }
                this.mBiometricPrompt = builder.build();
                this.mCancellationSignal = new CancellationSignal();
                CryptoObject cryptoObject = this.mCryptoObject;
                if (cryptoObject == null) {
                    this.mBiometricPrompt.authenticate(this.mCancellationSignal, this.mExecutor, this.mAuthenticationCallback);
                } else {
                    this.mBiometricPrompt.authenticate(wrapCryptoObject(cryptoObject), this.mCancellationSignal, this.mExecutor, this.mAuthenticationCallback);
                }
            }
        }
        this.mShowing = true;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    /* access modifiers changed from: private */
    public static CryptoObject unwrapCryptoObject(BiometricPrompt.CryptoObject cryptoObject) {
        CryptoObject cryptoObject2 = null;
        if (cryptoObject == null) {
            return null;
        }
        if (cryptoObject.getCipher() != null) {
            return new CryptoObject(cryptoObject.getCipher());
        }
        if (cryptoObject.getSignature() != null) {
            return new CryptoObject(cryptoObject.getSignature());
        }
        if (cryptoObject.getMac() != null) {
            cryptoObject2 = new CryptoObject(cryptoObject.getMac());
        }
        return cryptoObject2;
    }

    private static BiometricPrompt.CryptoObject wrapCryptoObject(CryptoObject cryptoObject) {
        BiometricPrompt.CryptoObject cryptoObject2 = null;
        if (cryptoObject == null) {
            return null;
        }
        if (cryptoObject.getCipher() != null) {
            return new BiometricPrompt.CryptoObject(cryptoObject.getCipher());
        }
        if (cryptoObject.getSignature() != null) {
            return new BiometricPrompt.CryptoObject(cryptoObject.getSignature());
        }
        if (cryptoObject.getMac() != null) {
            cryptoObject2 = new BiometricPrompt.CryptoObject(cryptoObject.getMac());
        }
        return cryptoObject2;
    }
}
