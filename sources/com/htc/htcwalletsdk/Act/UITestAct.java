package com.htc.htcwalletsdk.Act;

import android.os.Bundle;
import android.view.View;
import com.htc.htcwalletsdk.C2271R;
import org.bitcoinj.core.NetworkParameters;

public class UITestAct extends BaseResultCallbackAct {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2271R.layout.activity_ui_test);
        sResultCallback.getOutput().putString(NetworkParameters.PAYMENT_PROTOCOL_ID_TESTNET, "output data ready");
    }

    public void onSuccess(View view) {
        if (sResultCallback != null) {
            sResultCallback.makeSuccess();
            sResultCallback = null;
            finish();
        }
    }

    public void onFailed(View view) {
        if (sResultCallback != null) {
            sResultCallback.makeFailure(8533254);
            sResultCallback = null;
            finish();
        }
    }
}
