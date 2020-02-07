package com.htc.htcwalletsdk.Act;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.core.content.ContextCompat;
import com.htc.htcwalletsdk.C2271R;
import com.htc.htcwalletsdk.Utils.ZKMALog;

public class UIErrorDialogAct extends BaseResultCallbackAct {
    private static final String TAG = "UIErrorDialogAct";

    /* access modifiers changed from: protected */
    @SuppressLint({"StringFormatMatches"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        setContentView(C2271R.layout.activity_default_error_dialog);
        if (VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(ContextCompat.getColor(this, 17170445));
        } else if (VERSION.SDK_INT >= 19) {
            window.setFlags(67108864, 67108864);
        }
        Bundle bundleExtra = getIntent().getBundleExtra("input");
        if (bundleExtra != null) {
            final int i = bundleExtra.getInt("errorCode");
            String string = bundleExtra.getString("errorMessage");
            Builder builder = new Builder(this);
            if (string != null) {
                builder.setMessage((CharSequence) string);
            } else {
                builder.setMessage((CharSequence) String.format(getString(C2271R.string.text_generic_error_description), new Object[]{Integer.valueOf(i)}));
            }
            builder.setPositiveButton((CharSequence) "Ok", (OnClickListener) new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (BaseResultCallbackAct.sResultCallback != null) {
                        BaseResultCallbackAct.sResultCallback.makeSuccess();
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("sResultCallback is null, An Error has occurred. Error code=");
                        sb.append(i);
                        ZKMALog.m273e(UIErrorDialogAct.TAG, sb.toString());
                    }
                    UIErrorDialogAct.this.finish();
                }
            });
            builder.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    if (BaseResultCallbackAct.sResultCallback != null) {
                        BaseResultCallbackAct.sResultCallback.makeSuccess();
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("sResultCallback is null, An Error has occurred. Error code=");
                        sb.append(i);
                        ZKMALog.m273e(UIErrorDialogAct.TAG, sb.toString());
                    }
                    UIErrorDialogAct.this.finish();
                }
            });
            builder.show();
        }
    }
}
