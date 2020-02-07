package com.htc.htcwalletsdk.Protect;

import android.app.Activity;

public class ScreenProtector {
    public static void forbidScreenCaptureOnRelease(Activity activity) {
        activity.getWindow().setFlags(8192, 8192);
    }

    public static void ActivityPortraitModeOnly(Activity activity) {
        activity.setRequestedOrientation(1);
    }
}
