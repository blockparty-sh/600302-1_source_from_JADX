package com.htc.htcwalletsdk.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import com.htc.htcwalletsdk.C2271R;
import com.leanplum.core.BuildConfig;

public class DebugMode extends Activity {
    public static int DebugFunc1 = 1;
    public static int DebugFunc2 = 2;
    public static int DebugFunc3 = 4;
    public static int DebugFunc4 = 8;
    public static int DebugFunc5 = 16;
    public static int DebugFunc6 = 32;
    public static int NoDebug = 0;
    static final String TAG = "DebugMode";
    public static int sDebugMode = NoDebug;
    EditText etDebugNumber;

    public static void CheckDebugMode() {
        sDebugMode = PropertiesWrapper.getInt("htcwalletsdk_DebugMode", BuildConfig.BUILD_NUMBER);
        StringBuilder sb = new StringBuilder();
        sb.append("sDebugMode=");
        sb.append(sDebugMode);
        ZKMALog.m272d(TAG, sb.toString());
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2271R.layout.debugmode_activity);
        this.etDebugNumber = (EditText) findViewById(C2271R.C2274id.etDebugNumber);
    }

    public void onClickDebugNumber(View view) {
        int parseInt = Integer.parseInt(view.getTag().toString());
        String str = TAG;
        if (parseInt != 999) {
            ZKMALog.m272d(str, "Not support this number");
            return;
        }
        int parseInt2 = Integer.parseInt(this.etDebugNumber.getText().toString());
        StringBuilder sb = new StringBuilder();
        sb.append("number=");
        sb.append(parseInt2);
        ZKMALog.m272d(str, sb.toString());
    }
}
