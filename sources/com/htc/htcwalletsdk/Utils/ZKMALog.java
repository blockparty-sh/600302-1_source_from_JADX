package com.htc.htcwalletsdk.Utils;

import android.util.Log;

public final class ZKMALog {
    private static final int CHUNKMAX = 1024;
    private static final int MSG_OFFSET = 3;
    public static final String TAG = "ZKMALog";
    public static final boolean bDEBUG = false;

    public static void byteArray(String str, byte[] bArr) {
    }

    /* renamed from: c */
    public static void m271c(String str, String str2) {
    }

    /* renamed from: d */
    public static void m272d(String str, String str2) {
    }

    public static boolean isLoggable(String str, int i) {
        return Log.isLoggable(TAG, i);
    }

    public static void partial(String str, String str2, int i) {
        int length = str2.length();
        String str3 = TAG;
        if (i > length) {
            Log.v(str3, str2);
        } else {
            Log.v(str3, str2.substring(0, i));
        }
    }

    /* renamed from: v */
    public static void m276v(String str, String str2) {
        Log.v(TAG, str2);
    }

    /* renamed from: i */
    public static void m275i(String str, String str2) {
        Log.i(TAG, str2);
    }

    /* renamed from: w */
    public static void m277w(String str, String str2) {
        Log.w(TAG, str2);
    }

    /* renamed from: e */
    public static void m273e(String str, String str2) {
        Log.e(TAG, str2);
    }

    /* renamed from: e */
    public static void m274e(String str, String str2, RuntimeException runtimeException) throws RuntimeException {
        Log.e(TAG, str2);
    }

    public static void wtf(String str, String str2, RuntimeException runtimeException) throws RuntimeException {
        Log.wtf(TAG, str2, runtimeException);
    }
}
