package com.leanplum.monitoring;

import com.leanplum.Leanplum;
import com.leanplum.internal.Log;

public class ExceptionHandler {
    private static final String LEANPLUM_CRASH_REPORTER_CLASS = "com.leanplum.monitoring.internal.LeanplumExceptionReporter";
    private static final ExceptionHandler instance = new ExceptionHandler();
    public ExceptionReporting exceptionReporter = null;

    private ExceptionHandler() {
    }

    public static ExceptionHandler getInstance() {
        return instance;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        com.leanplum.internal.Log.m281i("LeanplumExceptionHandler could not initialize Exception Reporting.This is expected if you have not included the leanplum-monitoring module");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        com.leanplum.internal.Log.m280e(r0, r6);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[ExcHandler: ClassNotFoundException (unused java.lang.ClassNotFoundException), SYNTHETIC, Splitter:B:1:0x0007] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setContext(android.content.Context r6) {
        /*
            r5 = this;
            java.lang.String r0 = "LeanplumExceptionHandler"
            r1 = 2
            r2 = 0
            r3 = 1
            java.lang.String r4 = "com.leanplum.monitoring.internal.LeanplumExceptionReporter"
            java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x002a, Throwable -> 0x001f }
            com.leanplum.monitoring.ExceptionReporting r4 = r5.exceptionReporter     // Catch:{ ClassNotFoundException -> 0x002a, Throwable -> 0x001f }
            if (r4 == 0) goto L_0x0033
            com.leanplum.monitoring.ExceptionReporting r4 = r5.exceptionReporter     // Catch:{ Throwable -> 0x0014, ClassNotFoundException -> 0x002a }
            r4.setContext(r6)     // Catch:{ Throwable -> 0x0014, ClassNotFoundException -> 0x002a }
            goto L_0x0033
        L_0x0014:
            r6 = move-exception
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ ClassNotFoundException -> 0x002a, Throwable -> 0x001f }
            r4[r2] = r0     // Catch:{ ClassNotFoundException -> 0x002a, Throwable -> 0x001f }
            r4[r3] = r6     // Catch:{ ClassNotFoundException -> 0x002a, Throwable -> 0x001f }
            com.leanplum.internal.Log.m280e(r4)     // Catch:{ ClassNotFoundException -> 0x002a, Throwable -> 0x001f }
            goto L_0x0033
        L_0x001f:
            r6 = move-exception
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r2] = r0
            r1[r3] = r6
            com.leanplum.internal.Log.m280e(r1)
            goto L_0x0033
        L_0x002a:
            java.lang.Object[] r6 = new java.lang.Object[r3]
            java.lang.String r0 = "LeanplumExceptionHandler could not initialize Exception Reporting.This is expected if you have not included the leanplum-monitoring module"
            r6[r2] = r0
            com.leanplum.internal.Log.m281i(r6)
        L_0x0033:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.monitoring.ExceptionHandler.setContext(android.content.Context):void");
    }

    public void reportException(Throwable th) {
        ExceptionReporting exceptionReporting = this.exceptionReporter;
        if (exceptionReporting != null) {
            try {
                exceptionReporting.reportException(th);
            } catch (Throwable th2) {
                Log.m280e("LeanplumExceptionHandler", th2);
            }
            Leanplum.countAggregator().incrementCount("report_exception");
        }
    }
}
