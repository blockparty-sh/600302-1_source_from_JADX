package com.facebook.stetho.common;

import java.util.Locale;

public class LogUtil {
    private static final String TAG = "stetho";

    /* renamed from: e */
    public static void m39e(String str, Object... objArr) {
        m38e(format(str, objArr));
    }

    /* renamed from: e */
    public static void m41e(Throwable th, String str, Object... objArr) {
        m40e(th, format(str, objArr));
    }

    /* renamed from: e */
    public static void m38e(String str) {
        if (isLoggable(6)) {
            LogRedirector.m26e(TAG, str);
        }
    }

    /* renamed from: e */
    public static void m40e(Throwable th, String str) {
        if (isLoggable(6)) {
            LogRedirector.m27e(TAG, str, th);
        }
    }

    /* renamed from: w */
    public static void m51w(String str, Object... objArr) {
        m50w(format(str, objArr));
    }

    /* renamed from: w */
    public static void m53w(Throwable th, String str, Object... objArr) {
        m52w(th, format(str, objArr));
    }

    /* renamed from: w */
    public static void m50w(String str) {
        if (isLoggable(5)) {
            LogRedirector.m32w(TAG, str);
        }
    }

    /* renamed from: w */
    public static void m52w(Throwable th, String str) {
        if (isLoggable(5)) {
            LogRedirector.m33w(TAG, str, th);
        }
    }

    /* renamed from: i */
    public static void m43i(String str, Object... objArr) {
        m42i(format(str, objArr));
    }

    /* renamed from: i */
    public static void m45i(Throwable th, String str, Object... objArr) {
        m44i(th, format(str, objArr));
    }

    /* renamed from: i */
    public static void m42i(String str) {
        if (isLoggable(4)) {
            LogRedirector.m28i(TAG, str);
        }
    }

    /* renamed from: i */
    public static void m44i(Throwable th, String str) {
        if (isLoggable(4)) {
            LogRedirector.m29i(TAG, str, th);
        }
    }

    /* renamed from: d */
    public static void m35d(String str, Object... objArr) {
        m34d(format(str, objArr));
    }

    /* renamed from: d */
    public static void m37d(Throwable th, String str, Object... objArr) {
        m36d(th, format(str, objArr));
    }

    /* renamed from: d */
    public static void m34d(String str) {
        if (isLoggable(3)) {
            LogRedirector.m24d(TAG, str);
        }
    }

    /* renamed from: d */
    public static void m36d(Throwable th, String str) {
        if (isLoggable(3)) {
            LogRedirector.m25d(TAG, str, th);
        }
    }

    /* renamed from: v */
    public static void m47v(String str, Object... objArr) {
        m46v(format(str, objArr));
    }

    /* renamed from: v */
    public static void m49v(Throwable th, String str, Object... objArr) {
        m48v(th, format(str, objArr));
    }

    /* renamed from: v */
    public static void m46v(String str) {
        if (isLoggable(2)) {
            LogRedirector.m30v(TAG, str);
        }
    }

    /* renamed from: v */
    public static void m48v(Throwable th, String str) {
        if (isLoggable(2)) {
            LogRedirector.m31v(TAG, str, th);
        }
    }

    private static String format(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    public static boolean isLoggable(int i) {
        if (i == 5 || i == 6) {
            return true;
        }
        return LogRedirector.isLoggable(TAG, i);
    }
}
