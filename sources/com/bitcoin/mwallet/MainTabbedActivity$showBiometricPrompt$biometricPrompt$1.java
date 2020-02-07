package com.bitcoin.mwallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt.AuthenticationCallback;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo37405d2 = {"com/bitcoin/mwallet/MainTabbedActivity$showBiometricPrompt$biometricPrompt$1", "Landroidx/biometric/BiometricPrompt$AuthenticationCallback;", "onAuthenticationFailed", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MainTabbedActivity.kt */
public final class MainTabbedActivity$showBiometricPrompt$biometricPrompt$1 extends AuthenticationCallback {
    final /* synthetic */ AppCompatActivity $activity;

    MainTabbedActivity$showBiometricPrompt$biometricPrompt$1(AppCompatActivity appCompatActivity) {
        this.$activity = appCompatActivity;
    }

    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        this.$activity.finish();
    }
}
