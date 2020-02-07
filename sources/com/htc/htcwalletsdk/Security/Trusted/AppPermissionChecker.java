package com.htc.htcwalletsdk.Security.Trusted;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.Log;
import com.htc.htcwalletsdk.CONSTANT;
import com.htc.htcwalletsdk.Export.RESULT;
import com.htc.htcwalletsdk.Utils.ZKMALog;

public final class AppPermissionChecker {
    private static final String TAG = "AppPermissionChecker";
    private final PackageManager packageManager;

    public AppPermissionChecker(Context context) {
        this.packageManager = context.getPackageManager();
    }

    public int isPermissionGranted(String str) {
        String str2 = "); do not trust";
        boolean isEmpty = TextUtils.isEmpty(str);
        String str3 = TAG;
        if (isEmpty) {
            if (ZKMALog.isLoggable(str3, 5)) {
                ZKMALog.m277w(str3, "null or empty package name; do not trust");
            }
            return RESULT.E_ZKMS_PACKAGE_NOT_FOUND;
        }
        try {
            PackageInfo packageInfo = this.packageManager.getPackageInfo(str, 4096);
            if (packageInfo.requestedPermissions == null || packageInfo.requestedPermissions.length <= 1) {
                if (ZKMALog.isLoggable(str3, 5)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Permission com.htc.wallet.permission.ACCESS_ZION not found in package (");
                    sb.append(str);
                    sb.append(str2);
                    ZKMALog.m277w(str3, sb.toString());
                }
                return RESULT.E_ZKMS_PERMISSION_NOT_GRANTED;
            }
            for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                if (packageInfo.requestedPermissions[i].equals(CONSTANT.PERMISSION_ACCESS_ZION)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Found ");
                    sb2.append(packageInfo.requestedPermissions[i]);
                    sb2.append(" permission !!!");
                    ZKMALog.m272d(str3, sb2.toString());
                    String str4 = packageInfo.requestedPermissions[i];
                    try {
                        int i2 = this.packageManager.getPermissionInfo(str4, 0).protectionLevel;
                        if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0) {
                            ZKMALog.m275i(str3, "Permission com.htc.wallet.permission.ACCESS_ZION has been GRANTED !!!");
                            return 0;
                        }
                    } catch (NameNotFoundException e) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Unknown permission: ");
                        sb3.append(str4);
                        Log.w(str3, sb3.toString(), e);
                    }
                }
            }
            return RESULT.E_ZKMS_PERMISSION_NOT_GRANTED;
        } catch (NameNotFoundException unused) {
            if (ZKMALog.isLoggable(str3, 5)) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("package not found (");
                sb4.append(str);
                sb4.append(str2);
                ZKMALog.m277w(str3, sb4.toString());
            }
            return RESULT.E_ZKMS_PACKAGE_NOT_FOUND;
        }
    }
}
