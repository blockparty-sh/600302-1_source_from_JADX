package androidx.biometric;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt.PromptInfo;
import androidx.fragment.app.FragmentActivity;

@SuppressLint({"SyntheticAccessor"})
@RestrictTo({Scope.LIBRARY})
public class DeviceCredentialHandlerActivity extends AppCompatActivity {
    static final String EXTRA_PROMPT_INFO_BUNDLE = "prompt_info_bundle";
    private static final String KEY_DID_CHANGE_CONFIGURATION = "did_change_configuration";
    private static final String TAG = "DeviceCredentialHandler";
    private boolean mDidChangeConfiguration;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        DeviceCredentialHandlerBridge instance = DeviceCredentialHandlerBridge.getInstance();
        boolean z = true;
        if (instance.getClientThemeResId() != 0) {
            setTheme(instance.getClientThemeResId());
            getTheme().applyStyle(C0173R.C0178style.TransparentStyle, true);
        }
        super.onCreate(bundle);
        if (bundle == null || !bundle.getBoolean(KEY_DID_CHANGE_CONFIGURATION, false)) {
            z = false;
        }
        this.mDidChangeConfiguration = z;
        if (!this.mDidChangeConfiguration) {
            instance.stopIgnoringReset();
        } else {
            this.mDidChangeConfiguration = false;
        }
        setTitle(null);
        setContentView(C0173R.layout.device_credential_handler_activity);
        if (instance.getExecutor() == null || instance.getAuthenticationCallback() == null) {
            Log.e(TAG, "onCreate: Executor and/or callback was null!");
            finish();
            return;
        }
        new BiometricPrompt((FragmentActivity) this, instance.getExecutor(), instance.getAuthenticationCallback()).authenticate(new PromptInfo(getIntent().getBundleExtra(EXTRA_PROMPT_INFO_BUNDLE)));
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        DeviceCredentialHandlerBridge instanceIfNotNull = DeviceCredentialHandlerBridge.getInstanceIfNotNull();
        if (isChangingConfigurations() && instanceIfNotNull != null) {
            instanceIfNotNull.ignoreNextReset();
            this.mDidChangeConfiguration = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(KEY_DID_CHANGE_CONFIGURATION, this.mDidChangeConfiguration);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        handleDeviceCredentialResult(i2);
    }

    /* access modifiers changed from: 0000 */
    public void handleDeviceCredentialResult(int i) {
        DeviceCredentialHandlerBridge instanceIfNotNull = DeviceCredentialHandlerBridge.getInstanceIfNotNull();
        if (instanceIfNotNull == null) {
            Log.e(TAG, "onActivityResult: Bridge or callback was null!");
        } else if (i == -1) {
            instanceIfNotNull.setDeviceCredentialResult(1);
            instanceIfNotNull.setConfirmingDeviceCredential(false);
            instanceIfNotNull.startIgnoringReset();
        } else {
            instanceIfNotNull.setDeviceCredentialResult(2);
            instanceIfNotNull.setConfirmingDeviceCredential(false);
            instanceIfNotNull.startIgnoringReset();
        }
        finish();
    }
}
