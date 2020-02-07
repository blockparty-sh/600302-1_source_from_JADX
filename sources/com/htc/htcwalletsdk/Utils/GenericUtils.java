package com.htc.htcwalletsdk.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.FeatureInfo;
import android.preference.PreferenceManager;
import com.google.common.base.Ascii;

public class GenericUtils {
    static final String TAG = "GenericUtils";
    public static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static final boolean isDeviceSupportHardwareWallet(Context context) {
        FeatureInfo[] systemAvailableFeatures = context.getPackageManager().getSystemAvailableFeatures();
        int length = systemAvailableFeatures.length;
        int i = 0;
        while (i < length) {
            FeatureInfo featureInfo = systemAvailableFeatures[i];
            if (featureInfo.name == null || !featureInfo.name.equals("com.htc.hardware.wallet")) {
                i++;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("isDeviceSupportHardwareWallet  true , ");
                sb.append(featureInfo.name);
                ZKMALog.m275i(TAG, sb.toString());
                return true;
            }
        }
        return false;
    }

    public static String byteArrayToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte valueOf : bArr) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(valueOf)}));
        }
        return sb.toString();
    }

    public static String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte valueOf : bArr) {
            sb.append(String.format("%02x,", new Object[]{Byte.valueOf(valueOf)}));
        }
        return sb.toString();
    }

    public static boolean IsAppLaunched(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String str = "bIsAppLaunched";
        boolean z = defaultSharedPreferences.getBoolean(str, false);
        String str2 = TAG;
        if (!z) {
            ZKMALog.m275i(str2, "bIsAppLaunched == false");
            Editor edit = defaultSharedPreferences.edit();
            edit.putBoolean(str, true);
            edit.apply();
            return false;
        }
        ZKMALog.m275i(str2, "bIsAppLaunched == true");
        return true;
    }

    public static int bytesToInt(byte[] bArr) {
        return (bArr[0] & 255) | ((bArr[3] << Ascii.CAN) & -16777216) | ((bArr[2] << 16) & 16711680) | ((bArr[1] << 8) & 65280);
    }

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i2] = cArr2[b >>> 4];
            cArr[i2 + 1] = cArr2[b & Ascii.f528SI];
        }
        return new String(cArr);
    }
}
