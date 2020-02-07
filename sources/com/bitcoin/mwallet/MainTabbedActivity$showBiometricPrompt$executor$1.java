package com.bitcoin.mwallet;

import android.os.Handler;
import java.util.concurrent.Executor;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "command", "Ljava/lang/Runnable;", "kotlin.jvm.PlatformType", "execute"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MainTabbedActivity.kt */
final class MainTabbedActivity$showBiometricPrompt$executor$1 implements Executor {
    public static final MainTabbedActivity$showBiometricPrompt$executor$1 INSTANCE = new MainTabbedActivity$showBiometricPrompt$executor$1();

    MainTabbedActivity$showBiometricPrompt$executor$1() {
    }

    public final void execute(Runnable runnable) {
        new Handler().post(runnable);
    }
}
