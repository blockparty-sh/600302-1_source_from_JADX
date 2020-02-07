package com.htc.htcwalletsdk.Act;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.htc.htcwalletsdk.Export.RESULT;
import com.htc.htcwalletsdk.Protect.ScreenProtector;
import com.htc.htcwalletsdk.Utils.ResultCallback;

public class BaseResultCallbackAct extends AppCompatActivity {
    public static ResultCallback sResultCallback;

    public int ScreenProtector(int i, int i2, String str) {
        ScreenProtector.forbidScreenCaptureOnRelease(this);
        ScreenProtector.ActivityPortraitModeOnly(this);
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ScreenProtector(RESULT.UNKNOWN, 1234, "TEST ISdkProtector callback");
    }
}
