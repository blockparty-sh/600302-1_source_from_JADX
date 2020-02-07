package com.bitcoin.mwallet.app.flows.settings.settings.listeners;

import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "run"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: PreferenceListenerAppLockSwitch.kt */
final class PreferenceListenerAppLockSwitch$onPreferenceChange$3 implements Runnable {
    final /* synthetic */ PreferenceListenerAppLockSwitch this$0;

    PreferenceListenerAppLockSwitch$onPreferenceChange$3(PreferenceListenerAppLockSwitch preferenceListenerAppLockSwitch) {
        this.this$0 = preferenceListenerAppLockSwitch;
    }

    public final void run() {
        this.this$0.presenter.enableAppLock();
    }
}
