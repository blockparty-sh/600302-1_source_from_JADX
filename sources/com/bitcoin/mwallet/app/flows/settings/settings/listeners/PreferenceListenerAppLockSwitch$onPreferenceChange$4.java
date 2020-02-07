package com.bitcoin.mwallet.app.flows.settings.settings.listeners;

import android.app.Activity;
import com.guardanis.applock.AppLock;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "run"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: PreferenceListenerAppLockSwitch.kt */
final class PreferenceListenerAppLockSwitch$onPreferenceChange$4 implements Runnable {
    final /* synthetic */ Activity $currentActivity;

    PreferenceListenerAppLockSwitch$onPreferenceChange$4(Activity activity) {
        this.$currentActivity = activity;
    }

    public final void run() {
        AppLock.getInstance(this.$currentActivity).invalidateEnrollments();
    }
}
