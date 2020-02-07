package com.htc.htcwalletsdk.Act;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.htc.htcwalletsdk.C2271R;
import com.htc.htcwalletsdk.CONSTANT.FLAG_UI_TRY_OFTEN;
import com.htc.htcwalletsdk.CONSTANT.UI_IN_KEY;
import com.htc.htcwalletsdk.Export.RESULT;
import com.htc.htcwalletsdk.Protect.ISdkProtector.NotifyType;
import com.htc.htcwalletsdk.Protect.ResultChecker;
import com.htc.htcwalletsdk.Utils.ZKMALog;

public class UITryOftenAct extends BaseResultCallbackAct {
    private static final String TAG = "WalletSecure_TryOftenAct";
    int activityResultValue = -1;
    /* access modifiers changed from: private */
    public boolean bTryTooOftenActivity = false;

    /* access modifiers changed from: protected */
    @SuppressLint({"ClickableViewAccessibility"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2271R.layout.activity_ui_try_often);
        Window window = getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        if (VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(ContextCompat.getColor(this, 17170445));
        } else if (VERSION.SDK_INT >= 19) {
            window.setFlags(67108864, 67108864);
        }
        window.getDecorView().setSystemUiVisibility(1280);
        TextView textView = (TextView) findViewById(C2271R.C2274id.try_often_title);
        TextView textView2 = (TextView) findViewById(C2271R.C2274id.try_often_description);
        Button button = (Button) findViewById(C2271R.C2274id.check_button);
        Bundle bundleExtra = getIntent().getBundleExtra("input");
        String string = bundleExtra.getString(UI_IN_KEY.FLAG);
        String string2 = bundleExtra.getString(UI_IN_KEY.MINS);
        boolean equals = FLAG_UI_TRY_OFTEN.TRY_LATER.equals(string);
        String str = TAG;
        if (equals) {
            textView.setText(getResources().getText(C2271R.string.text_try_often_title1));
            if (string2 != null) {
                textView2.setText(String.format(getString(C2271R.string.text_try_often_description1), new Object[]{string2}));
            } else {
                ZKMALog.m275i(str, "Not get lock-mins input, use 10 mins to instead");
                textView2.setText(String.format(getString(C2271R.string.text_try_often_description1), new Object[]{"10"}));
            }
            button.setText(getResources().getText(C2271R.string.text_try_often_button1));
        } else if (FLAG_UI_TRY_OFTEN.TRY_TOO_OFTEN.equals(string)) {
            textView.setText(getResources().getText(C2271R.string.text_try_often_title2));
            textView2.setText(getResources().getText(C2271R.string.text_try_often_description2));
            button.setText(getResources().getText(C2271R.string.text_try_often_button2));
            if (ResultChecker.mSdkProtectorListener != null) {
                this.bTryTooOftenActivity = true;
                ResultChecker.mSdkProtectorListener.onNotify(NotifyType.UIEVENT, RESULT.E_TEEKM_RETRY_RESET, "UITryOftenAct.Activity.onCreate", null);
            }
        } else {
            ZKMALog.m273e(str, "no flag");
            finish(3);
            return;
        }
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UITryOftenAct.this.finish(0);
                if (ResultChecker.mSdkProtectorListener != null && UITryOftenAct.this.bTryTooOftenActivity) {
                    ResultChecker.mSdkProtectorListener.onNotify(NotifyType.UIEVENT, RESULT.E_TEEKM_RETRY_RESET, "UITryOftenAct.Button.onClick", null);
                }
            }
        });
    }

    public void finish(int i) {
        this.activityResultValue = i;
        finish();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        ZKMALog.m272d(TAG, "onPause()");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.bTryTooOftenActivity = false;
        if (sResultCallback != null) {
            if (this.activityResultValue == 0) {
                sResultCallback.makeSuccess();
                sResultCallback = null;
            } else {
                sResultCallback.makeFailure(this.activityResultValue);
                sResultCallback = null;
            }
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        if (ResultChecker.mSdkProtectorListener != null && this.bTryTooOftenActivity) {
            ResultChecker.mSdkProtectorListener.onNotify(NotifyType.UIEVENT, RESULT.E_TEEKM_RETRY_RESET, "UITryOftenAct.Button.onBackPressed", null);
        }
        finish(1);
    }
}
