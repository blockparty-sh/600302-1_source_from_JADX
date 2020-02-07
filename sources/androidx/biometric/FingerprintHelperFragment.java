package androidx.biometric;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.VisibleForTesting;
import androidx.biometric.BiometricPrompt.CryptoObject;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationResult;
import androidx.core.p003os.CancellationSignal;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.util.concurrent.Executor;

@SuppressLint({"SyntheticAccessor"})
@RestrictTo({Scope.LIBRARY})
public class FingerprintHelperFragment extends Fragment {
    private static final String TAG = "FingerprintHelperFrag";
    static final int USER_CANCELED_FROM_NEGATIVE_BUTTON = 2;
    static final int USER_CANCELED_FROM_NONE = 0;
    static final int USER_CANCELED_FROM_USER = 1;
    @VisibleForTesting
    final AuthenticationCallback mAuthenticationCallback = new AuthenticationCallback() {
        /* access modifiers changed from: private */
        public void dismissAndForwardResult(final int i, final CharSequence charSequence) {
            FingerprintHelperFragment.this.mMessageRouter.sendMessage(3);
            if (!C0179Utils.isConfirmingDeviceCredential()) {
                FingerprintHelperFragment.this.mExecutor.execute(new Runnable() {
                    public void run() {
                        FingerprintHelperFragment.this.mClientAuthenticationCallback.onAuthenticationError(i, charSequence);
                    }
                });
            }
        }

        public void onAuthenticationError(final int i, final CharSequence charSequence) {
            if (i == 5) {
                if (FingerprintHelperFragment.this.mCanceledFrom == 0) {
                    dismissAndForwardResult(i, charSequence);
                }
                FingerprintHelperFragment.this.cleanup();
            } else if (i == 7 || i == 9) {
                dismissAndForwardResult(i, charSequence);
                FingerprintHelperFragment.this.cleanup();
            } else {
                if (charSequence == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Got null string for error message: ");
                    sb.append(i);
                    Log.e(FingerprintHelperFragment.TAG, sb.toString());
                    charSequence = FingerprintHelperFragment.this.mContext.getResources().getString(C0173R.string.default_error_msg);
                }
                if (C0179Utils.isUnknownError(i)) {
                    i = 8;
                }
                FingerprintHelperFragment.this.mMessageRouter.sendMessage(2, i, 0, charSequence);
                FingerprintHelperFragment.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        C01681.this.dismissAndForwardResult(i, charSequence);
                        FingerprintHelperFragment.this.cleanup();
                    }
                }, (long) FingerprintDialogFragment.getHideDialogDelay(FingerprintHelperFragment.this.getContext()));
            }
        }

        public void onAuthenticationHelp(int i, CharSequence charSequence) {
            FingerprintHelperFragment.this.mMessageRouter.sendMessage(1, charSequence);
        }

        public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
            FingerprintHelperFragment.this.mMessageRouter.sendMessage(5);
            final BiometricPrompt.AuthenticationResult authenticationResult2 = authenticationResult != null ? new BiometricPrompt.AuthenticationResult(FingerprintHelperFragment.unwrapCryptoObject(authenticationResult.getCryptoObject())) : new BiometricPrompt.AuthenticationResult(null);
            FingerprintHelperFragment.this.mExecutor.execute(new Runnable() {
                public void run() {
                    FingerprintHelperFragment.this.mClientAuthenticationCallback.onAuthenticationSucceeded(authenticationResult2);
                }
            });
            FingerprintHelperFragment.this.cleanup();
        }

        public void onAuthenticationFailed() {
            FingerprintHelperFragment.this.mMessageRouter.sendMessage(1, FingerprintHelperFragment.this.mContext.getResources().getString(C0173R.string.fingerprint_not_recognized));
            FingerprintHelperFragment.this.mExecutor.execute(new Runnable() {
                public void run() {
                    FingerprintHelperFragment.this.mClientAuthenticationCallback.onAuthenticationFailed();
                }
            });
        }
    };
    /* access modifiers changed from: private */
    public int mCanceledFrom;
    private CancellationSignal mCancellationSignal;
    @VisibleForTesting
    BiometricPrompt.AuthenticationCallback mClientAuthenticationCallback;
    /* access modifiers changed from: private */
    public Context mContext;
    private CryptoObject mCryptoObject;
    @VisibleForTesting
    Executor mExecutor;
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public MessageRouter mMessageRouter;
    private boolean mShowing;

    @VisibleForTesting
    static class MessageRouter {
        private final Handler mHandler;

        @VisibleForTesting
        MessageRouter(Handler handler) {
            this.mHandler = handler;
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public void sendMessage(int i) {
            this.mHandler.obtainMessage(i).sendToTarget();
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public void sendMessage(int i, Object obj) {
            this.mHandler.obtainMessage(i, obj).sendToTarget();
        }

        /* access modifiers changed from: 0000 */
        @VisibleForTesting
        public void sendMessage(int i, int i2, int i3, Object obj) {
            this.mHandler.obtainMessage(i, i2, i3, obj).sendToTarget();
        }
    }

    static FingerprintHelperFragment newInstance() {
        return new FingerprintHelperFragment();
    }

    /* access modifiers changed from: 0000 */
    public void setCryptoObject(CryptoObject cryptoObject) {
        this.mCryptoObject = cryptoObject;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        this.mContext = getContext();
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (!this.mShowing) {
            this.mCancellationSignal = new CancellationSignal();
            this.mCanceledFrom = 0;
            FingerprintManagerCompat from = FingerprintManagerCompat.from(this.mContext);
            if (handlePreAuthenticationErrors(from)) {
                this.mMessageRouter.sendMessage(3);
                cleanup();
            } else {
                from.authenticate(wrapCryptoObject(this.mCryptoObject), 0, this.mCancellationSignal, this.mAuthenticationCallback, null);
                this.mShowing = true;
            }
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    /* access modifiers changed from: 0000 */
    public void setCallback(Executor executor, BiometricPrompt.AuthenticationCallback authenticationCallback) {
        this.mExecutor = executor;
        this.mClientAuthenticationCallback = authenticationCallback;
    }

    /* access modifiers changed from: 0000 */
    public void setHandler(Handler handler) {
        this.mHandler = handler;
        this.mMessageRouter = new MessageRouter(this.mHandler);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void setMessageRouter(MessageRouter messageRouter) {
        this.mMessageRouter = messageRouter;
    }

    /* access modifiers changed from: 0000 */
    public void cancel(int i) {
        this.mCanceledFrom = i;
        if (i == 1) {
            sendErrorToClient(10);
        }
        CancellationSignal cancellationSignal = this.mCancellationSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        cleanup();
    }

    /* access modifiers changed from: private */
    public void cleanup() {
        this.mShowing = false;
        FragmentActivity activity = getActivity();
        if (getFragmentManager() != null) {
            getFragmentManager().beginTransaction().detach(this).commitAllowingStateLoss();
        }
        if (!C0179Utils.isConfirmingDeviceCredential()) {
            C0179Utils.maybeFinishHandler(activity);
        }
    }

    private boolean handlePreAuthenticationErrors(FingerprintManagerCompat fingerprintManagerCompat) {
        if (!fingerprintManagerCompat.isHardwareDetected()) {
            sendErrorToClient(12);
            return true;
        } else if (fingerprintManagerCompat.hasEnrolledFingerprints()) {
            return false;
        } else {
            sendErrorToClient(11);
            return true;
        }
    }

    private void sendErrorToClient(int i) {
        if (!C0179Utils.isConfirmingDeviceCredential()) {
            this.mClientAuthenticationCallback.onAuthenticationError(i, getErrorString(this.mContext, i));
        }
    }

    private String getErrorString(Context context, int i) {
        if (i == 1) {
            return context.getString(C0173R.string.fingerprint_error_hw_not_available);
        }
        switch (i) {
            case 10:
                return context.getString(C0173R.string.fingerprint_error_user_canceled);
            case 11:
                return context.getString(C0173R.string.fingerprint_error_no_fingerprints);
            case 12:
                return context.getString(C0173R.string.fingerprint_error_hw_not_present);
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown error code: ");
                sb.append(i);
                Log.e(TAG, sb.toString());
                return context.getString(C0173R.string.default_error_msg);
        }
    }

    /* access modifiers changed from: private */
    public static CryptoObject unwrapCryptoObject(FingerprintManagerCompat.CryptoObject cryptoObject) {
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

    private static FingerprintManagerCompat.CryptoObject wrapCryptoObject(CryptoObject cryptoObject) {
        FingerprintManagerCompat.CryptoObject cryptoObject2 = null;
        if (cryptoObject == null) {
            return null;
        }
        if (cryptoObject.getCipher() != null) {
            return new FingerprintManagerCompat.CryptoObject(cryptoObject.getCipher());
        }
        if (cryptoObject.getSignature() != null) {
            return new FingerprintManagerCompat.CryptoObject(cryptoObject.getSignature());
        }
        if (cryptoObject.getMac() != null) {
            cryptoObject2 = new FingerprintManagerCompat.CryptoObject(cryptoObject.getMac());
        }
        return cryptoObject2;
    }
}
