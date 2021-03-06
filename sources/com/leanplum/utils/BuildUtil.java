package com.leanplum.utils;

import android.content.Context;
import android.os.Build.VERSION;

public class BuildUtil {
    private static int targetSdk = -1;

    public static boolean isNotificationChannelSupported(Context context) {
        return VERSION.SDK_INT >= 26 && getTargetSdkVersion(context) >= 26;
    }

    private static int getTargetSdkVersion(Context context) {
        if (targetSdk == -1 && context != null) {
            targetSdk = context.getApplicationInfo().targetSdkVersion;
        }
        return targetSdk;
    }
}
