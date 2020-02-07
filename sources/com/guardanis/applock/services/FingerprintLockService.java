package com.guardanis.applock.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import androidx.core.content.ContextCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationResult;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.CryptoObject;
import androidx.core.p003os.CancellationSignal;
import com.guardanis.applock.AppLock;
import com.guardanis.applock.C2245R;
import com.guardanis.applock.utils.CipherGenerator;
import javax.crypto.Cipher;

public class FingerprintLockService extends LockService {
    private static final String PREF_ENROLLMENT_ALLOWED = "pin__fingerprint_enrollment_allowed";
    protected CancellationSignal fingerprintCancellationSignal;

    public interface AuthenticationDelegate {
        void onAuthenticating(CancellationSignal cancellationSignal);

        void onAuthenticationFailed(String str);

        void onAuthenticationHelp(int i, CharSequence charSequence);

        void onAuthenticationSuccess(AuthenticationResult authenticationResult);

        void onResolutionRequired(int i);
    }

    public boolean isEnrollmentEligible(Context context) {
        return 23 <= VERSION.SDK_INT && context.getResources().getBoolean(C2245R.bool.applock__fingerprint_service_enabled) && isHardwarePresent(context);
    }

    public void enroll(Context context, AuthenticationDelegate authenticationDelegate) {
        authenticate(context, false, authenticationDelegate);
    }

    public void authenticate(Context context, AuthenticationDelegate authenticationDelegate) {
        authenticate(context, true, authenticationDelegate);
    }

    /* access modifiers changed from: protected */
    public void authenticate(Context context, boolean z, AuthenticationDelegate authenticationDelegate) {
        int requiredResolutionErrorCode = getRequiredResolutionErrorCode(context, z);
        if (-1 < requiredResolutionErrorCode) {
            authenticationDelegate.onResolutionRequired(requiredResolutionErrorCode);
        } else if (VERSION.SDK_INT >= 23) {
            attemptFingerprintManagerAuthentication(context, authenticationDelegate);
        }
    }

    /* access modifiers changed from: protected */
    public int getRequiredResolutionErrorCode(Context context, boolean z) {
        FingerprintManagerCompat from = FingerprintManagerCompat.from(context);
        if (VERSION.SDK_INT < 23) {
            return 5;
        }
        if (z && !isEnrolled(context)) {
            return 4;
        }
        if (!isHardwarePresent(context)) {
            return 1;
        }
        if (!from.hasEnrolledFingerprints()) {
            return 3;
        }
        return ContextCompat.checkSelfPermission(context, "android.permission.USE_FINGERPRINT") != 0 ? 2 : -1;
    }

    /* access modifiers changed from: protected */
    public void attemptFingerprintManagerAuthentication(final Context context, final AuthenticationDelegate authenticationDelegate) {
        this.fingerprintCancellationSignal = new CancellationSignal();
        C22541 r5 = new AuthenticationCallback() {
            public void onAuthenticationError(int i, CharSequence charSequence) {
                super.onAuthenticationError(i, charSequence);
                authenticationDelegate.onResolutionRequired(C2245R.string.applock__fingerprint_error_unknown);
            }

            public void onAuthenticationHelp(int i, CharSequence charSequence) {
                super.onAuthenticationHelp(i, charSequence);
                authenticationDelegate.onAuthenticationHelp(i, charSequence);
            }

            public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
                super.onAuthenticationSucceeded(authenticationResult);
                FingerprintLockService.this.notifyEnrolled(context);
                authenticationDelegate.onAuthenticationSuccess(authenticationResult);
            }

            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                authenticationDelegate.onAuthenticationFailed(context.getString(C2245R.string.applock__fingerprint_error_unrecognized));
            }
        };
        authenticationDelegate.onAuthenticating(this.fingerprintCancellationSignal);
        try {
            FingerprintManagerCompat.from(context).authenticate(new CryptoObject(generateAuthCipher(context, false, 0)), 0, this.fingerprintCancellationSignal, r5, null);
        } catch (Exception e) {
            e.printStackTrace();
            authenticationDelegate.onResolutionRequired(1);
        }
    }

    public boolean isHardwarePresent(Context context) {
        return FingerprintManagerCompat.from(context).isHardwareDetected();
    }

    public boolean isEnrolled(Context context) {
        return AppLock.getInstance(context).getPreferences().getBoolean(PREF_ENROLLMENT_ALLOWED, false);
    }

    /* access modifiers changed from: protected */
    public void notifyEnrolled(Context context) {
        AppLock.getInstance(context).getPreferences().edit().putBoolean(PREF_ENROLLMENT_ALLOWED, true).commit();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public Cipher generateAuthCipher(Context context, boolean z, int i) throws Exception {
        if (VERSION.SDK_INT >= 23) {
            return new CipherGenerator().generateAuthCipher(context, z, i);
        }
        throw new RuntimeException("generateAuthCipher() not supported before Api 23");
    }

    public void invalidateEnrollments(Context context) {
        AppLock.getInstance(context).getPreferences().edit().putBoolean(PREF_ENROLLMENT_ALLOWED, false).commit();
    }

    public void cancelPendingAuthentications(Context context) {
        CancellationSignal cancellationSignal = this.fingerprintCancellationSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
            this.fingerprintCancellationSignal = null;
        }
    }
}
