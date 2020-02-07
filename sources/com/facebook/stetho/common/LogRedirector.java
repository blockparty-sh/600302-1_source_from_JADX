package com.facebook.stetho.common;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogRedirector {
    private static volatile Logger sLogger;

    public interface Logger {
        boolean isLoggable(String str, int i);

        void log(int i, String str, String str2);
    }

    public static void setLogger(Logger logger) {
        Util.throwIfNull(logger);
        Util.throwIfNotNull(sLogger);
        sLogger = logger;
    }

    /* renamed from: e */
    public static void m27e(String str, String str2, Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("\n");
        sb.append(formatThrowable(th));
        m26e(str, sb.toString());
    }

    /* renamed from: e */
    public static void m26e(String str, String str2) {
        log(6, str, str2);
    }

    /* renamed from: w */
    public static void m33w(String str, String str2, Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("\n");
        sb.append(formatThrowable(th));
        m32w(str, sb.toString());
    }

    /* renamed from: w */
    public static void m32w(String str, String str2) {
        log(5, str, str2);
    }

    /* renamed from: i */
    public static void m29i(String str, String str2, Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("\n");
        sb.append(formatThrowable(th));
        m28i(str, sb.toString());
    }

    /* renamed from: i */
    public static void m28i(String str, String str2) {
        log(4, str, str2);
    }

    /* renamed from: d */
    public static void m25d(String str, String str2, Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("\n");
        sb.append(formatThrowable(th));
        m24d(str, sb.toString());
    }

    /* renamed from: d */
    public static void m24d(String str, String str2) {
        log(3, str, str2);
    }

    /* renamed from: v */
    public static void m31v(String str, String str2, Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("\n");
        sb.append(formatThrowable(th));
        m30v(str, sb.toString());
    }

    /* renamed from: v */
    public static void m30v(String str, String str2) {
        log(2, str, str2);
    }

    private static String formatThrowable(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace();
        printWriter.flush();
        return stringWriter.toString();
    }

    private static void log(int i, String str, String str2) {
        Logger logger = sLogger;
        if (logger != null) {
            logger.log(i, str, str2);
        } else {
            Log.println(i, str, str2);
        }
    }

    public static boolean isLoggable(String str, int i) {
        Logger logger = sLogger;
        if (logger != null) {
            return logger.isLoggable(str, i);
        }
        return Log.isLoggable(str, i);
    }
}
