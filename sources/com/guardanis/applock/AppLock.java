package com.guardanis.applock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationResult;
import androidx.core.p003os.CancellationSignal;
import com.guardanis.applock.activities.UnlockActivity;
import com.guardanis.applock.services.FingerprintLockService;
import com.guardanis.applock.services.FingerprintLockService.AuthenticationDelegate;
import com.guardanis.applock.services.LockService;
import com.guardanis.applock.services.PINLockService;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AppLock {
    public static final int ERROR_CODE_FINGERPRINTS_EMPTY = 3;
    public static final int ERROR_CODE_FINGERPRINTS_MISSING_HARDWARE = 1;
    public static final int ERROR_CODE_FINGERPRINTS_NOT_LOCALLY_ENROLLED = 4;
    public static final int ERROR_CODE_FINGERPRINTS_PERMISSION_REQUIRED = 2;
    public static final int ERROR_CODE_SDK_VERSION_MINIMUM = 5;
    private static final String PREFS = "pin__preferences";
    private static final String PREF_UNLOCK_FAILURE_TIME = "pin__unlock_failure_time";
    private static final String PREF_UNLOCK_SUCCESS_TIME = "pin__unlock_success_time";
    public static final int REQUEST_CODE_FINGERPRINT_PERMISSION = 9373;
    public static final int REQUEST_CODE_LOCK_CREATION = 9372;
    public static final int REQUEST_CODE_UNLOCK = 9371;
    private static AppLock instance;
    protected Context context;
    protected HashMap<Class, LockService> lockServices = new HashMap<>();
    protected int unlockAttemptsCount = 1;

    public interface UnlockDelegate {
        void onAuthenticationHelp(int i, String str);

        void onFailureLimitExceeded(String str);

        void onResolutionRequired(int i);

        void onUnlockSuccessful();
    }

    public static AppLock getInstance(Context context2) {
        if (instance == null) {
            instance = new AppLock(context2);
        }
        return instance;
    }

    protected AppLock(Context context2) {
        this.context = context2.getApplicationContext();
        this.lockServices.put(PINLockService.class, new PINLockService());
        this.lockServices.put(FingerprintLockService.class, new FingerprintLockService());
    }

    public static boolean isEnrolled(Context context2) {
        for (LockService isEnrolled : getInstance(context2).lockServices.values()) {
            if (isEnrolled.isEnrolled(context2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUnlockRequired(Context context2) {
        return isUnlockRequired(context2, TimeUnit.MINUTES.toMillis((long) context2.getResources().getInteger(C2245R.integer.applock__activity_lock_reenable_minutes)));
    }

    public static boolean isUnlockRequired(Context context2, long j) {
        return isEnrolled(context2) && j < System.currentTimeMillis() - getUnlockSuccessTime(context2);
    }

    public void attemptFingerprintUnlock(final UnlockDelegate unlockDelegate) {
        if (!handleFailureBlocking(unlockDelegate)) {
            ((FingerprintLockService) getLockService(FingerprintLockService.class)).authenticate(this.context, new AuthenticationDelegate() {
                public void onAuthenticating(CancellationSignal cancellationSignal) {
                }

                public void onResolutionRequired(int i) {
                    unlockDelegate.onResolutionRequired(i);
                }

                public void onAuthenticationHelp(int i, CharSequence charSequence) {
                    unlockDelegate.onAuthenticationHelp(i, String.valueOf(charSequence));
                }

                public void onAuthenticationSuccess(AuthenticationResult authenticationResult) {
                    AppLock.this.onUnlockSuccessful(unlockDelegate);
                }

                public void onAuthenticationFailed(String str) {
                    AppLock.this.handleUnlockFailure(str, unlockDelegate);
                }
            });
        }
    }

    public void attemptPINUnlock(String str, final UnlockDelegate unlockDelegate) {
        if (!handleFailureBlocking(unlockDelegate)) {
            ((PINLockService) getLockService(PINLockService.class)).authenticate(this.context, str, new PINLockService.AuthenticationDelegate() {
                public void onNoPIN() {
                    onUnlockFailed(AppLock.this.context.getString(C2245R.string.applock__unlock_error_no_matching_pin_found));
                }

                public void onPINDoesNotMatch() {
                    onUnlockFailed(AppLock.this.context.getString(C2245R.string.applock__unlock_error_match_failed));
                }

                public void onPINMatches() {
                    AppLock.this.onUnlockSuccessful(unlockDelegate);
                }

                private void onUnlockFailed(String str) {
                    AppLock.this.handleUnlockFailure(str, unlockDelegate);
                }
            });
        }
    }

    private boolean handleFailureBlocking(UnlockDelegate unlockDelegate) {
        if (isUnlockFailureBlockEnabled()) {
            this.unlockAttemptsCount++;
            if (getFailureDelayMs() < System.currentTimeMillis() - getUnlockFailureBlockStart()) {
                resetUnlockFailure();
            } else {
                String format = String.format(this.context.getString(C2245R.string.applock__unlock_error_retry_limit_exceeded), new Object[]{formatTimeRemaining()});
                if (unlockDelegate != null) {
                    unlockDelegate.onFailureLimitExceeded(format);
                }
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void handleUnlockFailure(String str, UnlockDelegate unlockDelegate) {
        this.unlockAttemptsCount++;
        if (unlockDelegate != null) {
            unlockDelegate.onFailureLimitExceeded(str);
        }
        if (this.context.getResources().getInteger(C2245R.integer.applock__max_retry_count) < this.unlockAttemptsCount) {
            onFailureExceedsLimit();
        }
    }

    public SharedPreferences getPreferences() {
        return this.context.getSharedPreferences(PREFS, 0);
    }

    /* access modifiers changed from: protected */
    public void onFailureExceedsLimit() {
        getPreferences().edit().putLong(PREF_UNLOCK_FAILURE_TIME, System.currentTimeMillis()).commit();
    }

    public boolean isUnlockFailureBlockEnabled() {
        return this.context.getResources().getInteger(C2245R.integer.applock__max_retry_count) < this.unlockAttemptsCount || System.currentTimeMillis() - getUnlockFailureBlockStart() < getFailureDelayMs();
    }

    /* access modifiers changed from: protected */
    public long getUnlockFailureBlockStart() {
        return getPreferences().getLong(PREF_UNLOCK_FAILURE_TIME, 0);
    }

    /* access modifiers changed from: protected */
    public void onUnlockSuccessful(UnlockDelegate unlockDelegate) {
        getPreferences().edit().putLong(PREF_UNLOCK_SUCCESS_TIME, System.currentTimeMillis()).commit();
        resetUnlockFailure();
        if (unlockDelegate != null) {
            unlockDelegate.onUnlockSuccessful();
        }
    }

    protected static long getUnlockSuccessTime(Context context2) {
        return getInstance(context2).getPreferences().getLong(PREF_UNLOCK_SUCCESS_TIME, 0);
    }

    public void setAuthenticationRequired() {
        getPreferences().edit().putLong(PREF_UNLOCK_SUCCESS_TIME, 0).commit();
    }

    /* access modifiers changed from: protected */
    public void resetUnlockFailure() {
        this.unlockAttemptsCount = 1;
        getPreferences().edit().putLong(PREF_UNLOCK_FAILURE_TIME, 0).commit();
    }

    /* access modifiers changed from: protected */
    public String formatTimeRemaining() {
        long failureDelayMs = getFailureDelayMs() - (System.currentTimeMillis() - getUnlockFailureBlockStart());
        long seconds = TimeUnit.MILLISECONDS.toSeconds(failureDelayMs) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(failureDelayMs));
        if (TimeUnit.MILLISECONDS.toMinutes(failureDelayMs) < 1) {
            return String.format("%d seconds", new Object[]{Long.valueOf(seconds)});
        }
        return String.format("%d minutes, %d seconds", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(failureDelayMs)), Long.valueOf(seconds)});
    }

    /* access modifiers changed from: protected */
    public long getFailureDelayMs() {
        return TimeUnit.MINUTES.toMillis((long) this.context.getResources().getInteger(C2245R.integer.applock__failure_retry_delay));
    }

    public void invalidateEnrollments() {
        resetUnlockFailure();
        setAuthenticationRequired();
        for (LockService invalidateEnrollments : this.lockServices.values()) {
            invalidateEnrollments.invalidateEnrollments(this.context);
        }
    }

    public void cancelPendingAuthentications() {
        for (LockService cancelPendingAuthentications : this.lockServices.values()) {
            cancelPendingAuthentications.cancelPendingAuthentications(this.context);
        }
    }

    public <T extends LockService> T getLockService(Class<T> cls) {
        return (LockService) this.lockServices.get(cls);
    }

    public static void onActivityResumed(Activity activity) {
        if (isUnlockRequired(activity)) {
            activity.startActivityForResult(new Intent(activity, UnlockActivity.class).putExtra(UnlockActivity.INTENT_ALLOW_UNLOCKED_EXIT, activity.getResources().getBoolean(C2245R.bool.applock__unlock_activity_return_allowed)), REQUEST_CODE_UNLOCK);
        }
    }

    public static boolean unlockIfRequired(Activity activity) {
        if (!isUnlockRequired(activity)) {
            return false;
        }
        activity.startActivityForResult(new Intent(activity, UnlockActivity.class).putExtra(UnlockActivity.INTENT_ALLOW_UNLOCKED_EXIT, true), REQUEST_CODE_UNLOCK);
        return true;
    }
}
