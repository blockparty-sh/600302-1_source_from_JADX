package com.guardanis.applock.services;

import android.content.Context;
import com.guardanis.applock.AppLock;
import com.guardanis.applock.utils.CryptoUtils;

public class PINLockService extends LockService {
    private static final String PREF_SAVED_LOCKED_PASSWORD = "pin__saved_locked_password";

    public interface AuthenticationDelegate {
        void onNoPIN();

        void onPINDoesNotMatch();

        void onPINMatches();
    }

    public void cancelPendingAuthentications(Context context) {
    }

    public boolean isEnrollmentEligible(Context context) {
        return true;
    }

    public void authenticate(Context context, String str, AuthenticationDelegate authenticationDelegate) {
        if (!isEnrolled(context)) {
            authenticationDelegate.onNoPIN();
        } else if (!getEnrolledPIN(context).equals(CryptoUtils.encryptSha1(str))) {
            authenticationDelegate.onPINDoesNotMatch();
        } else {
            authenticationDelegate.onPINMatches();
        }
    }

    public boolean isEnrolled(Context context) {
        return AppLock.getInstance(context).getPreferences().getString(PREF_SAVED_LOCKED_PASSWORD, null) != null;
    }

    private String getEnrolledPIN(Context context) {
        return AppLock.getInstance(context).getPreferences().getString(PREF_SAVED_LOCKED_PASSWORD, null);
    }

    public void enroll(Context context, String str) {
        AppLock.getInstance(context).getPreferences().edit().putString(PREF_SAVED_LOCKED_PASSWORD, CryptoUtils.encryptSha1(str)).commit();
    }

    public void invalidateEnrollments(Context context) {
        AppLock.getInstance(context).getPreferences().edit().remove(PREF_SAVED_LOCKED_PASSWORD).commit();
    }
}
