package androidx.biometric;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.fragment.app.FragmentActivity;
import org.spongycastle.i18n.MessageBundle;

@RestrictTo({Scope.LIBRARY})
/* renamed from: androidx.biometric.Utils */
class C0179Utils {
    static boolean isUnknownError(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return false;
            default:
                return true;
        }
    }

    private C0179Utils() {
    }

    @RequiresApi(21)
    static void launchDeviceCredentialConfirmation(@NonNull String str, @Nullable FragmentActivity fragmentActivity, @Nullable Bundle bundle, @Nullable Runnable runnable) {
        KeyguardManager keyguardManager;
        CharSequence charSequence;
        if (!(fragmentActivity instanceof DeviceCredentialHandlerActivity)) {
            Log.e(str, "Failed to check device credential. Parent handler not found.");
            return;
        }
        DeviceCredentialHandlerActivity deviceCredentialHandlerActivity = (DeviceCredentialHandlerActivity) fragmentActivity;
        if (VERSION.SDK_INT >= 23) {
            keyguardManager = (KeyguardManager) deviceCredentialHandlerActivity.getSystemService(KeyguardManager.class);
        } else {
            Object systemService = deviceCredentialHandlerActivity.getSystemService("keyguard");
            if (!(systemService instanceof KeyguardManager)) {
                Log.e(str, "Failed to check device credential. KeyguardManager not found.");
                deviceCredentialHandlerActivity.handleDeviceCredentialResult(0);
                return;
            }
            keyguardManager = (KeyguardManager) systemService;
        }
        if (keyguardManager == null) {
            Log.e(str, "Failed to check device credential. KeyguardManager was null.");
            deviceCredentialHandlerActivity.handleDeviceCredentialResult(0);
            return;
        }
        CharSequence charSequence2 = null;
        if (bundle != null) {
            charSequence2 = bundle.getCharSequence(MessageBundle.TITLE_ENTRY);
            charSequence = bundle.getCharSequence("subtitle");
        } else {
            charSequence = null;
        }
        Intent createConfirmDeviceCredentialIntent = keyguardManager.createConfirmDeviceCredentialIntent(charSequence2, charSequence);
        if (createConfirmDeviceCredentialIntent == null) {
            Log.e(str, "Failed to check device credential. Got null intent from Keyguard.");
            deviceCredentialHandlerActivity.handleDeviceCredentialResult(0);
            return;
        }
        DeviceCredentialHandlerBridge instance = DeviceCredentialHandlerBridge.getInstance();
        instance.setConfirmingDeviceCredential(true);
        instance.startIgnoringReset();
        if (runnable != null) {
            runnable.run();
        }
        createConfirmDeviceCredentialIntent.setFlags(134742016);
        deviceCredentialHandlerActivity.startActivityForResult(createConfirmDeviceCredentialIntent, 0);
    }

    static void maybeFinishHandler(@Nullable FragmentActivity fragmentActivity) {
        if ((fragmentActivity instanceof DeviceCredentialHandlerActivity) && !fragmentActivity.isFinishing()) {
            fragmentActivity.finish();
        }
    }

    static boolean shouldUseFingerprintForCrypto(@NonNull Context context, String str, String str2) {
        boolean z = false;
        if (VERSION.SDK_INT != 28) {
            return false;
        }
        if (isVendorInList(context, str, C0173R.array.crypto_fingerprint_fallback_vendors) || isModelInPrefixList(context, str2, C0173R.array.crypto_fingerprint_fallback_prefixes)) {
            z = true;
        }
        return z;
    }

    static boolean shouldHideFingerprintDialog(@NonNull Context context, String str) {
        if (VERSION.SDK_INT != 28) {
            return false;
        }
        return isModelInPrefixList(context, str, C0173R.array.hide_fingerprint_instantly_prefixes);
    }

    private static boolean isVendorInList(@NonNull Context context, String str, int i) {
        if (str == null) {
            return false;
        }
        for (String equalsIgnoreCase : context.getResources().getStringArray(i)) {
            if (str.equalsIgnoreCase(equalsIgnoreCase)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isModelInPrefixList(@NonNull Context context, String str, int i) {
        if (str == null) {
            return false;
        }
        for (String startsWith : context.getResources().getStringArray(i)) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    static boolean isConfirmingDeviceCredential() {
        DeviceCredentialHandlerBridge instanceIfNotNull = DeviceCredentialHandlerBridge.getInstanceIfNotNull();
        return instanceIfNotNull != null && instanceIfNotNull.isConfirmingDeviceCredential();
    }
}
