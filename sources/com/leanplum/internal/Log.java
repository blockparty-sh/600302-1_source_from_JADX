package com.leanplum.internal;

import com.leanplum.internal.Constants.Methods;
import com.leanplum.internal.Constants.Values;
import java.util.HashMap;

public class Log {
    private static final ThreadLocal<Boolean> isLogging = new ThreadLocal<Boolean>() {
        /* access modifiers changed from: protected */
        public Boolean initialValue() {
            return Boolean.valueOf(false);
        }
    };

    /* renamed from: com.leanplum.internal.Log$2 */
    static /* synthetic */ class C23752 {
        static final /* synthetic */ int[] $SwitchMap$com$leanplum$internal$Log$LeanplumLogType = new int[LeanplumLogType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.leanplum.internal.Log$LeanplumLogType[] r0 = com.leanplum.internal.Log.LeanplumLogType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$leanplum$internal$Log$LeanplumLogType = r0
                int[] r0 = $SwitchMap$com$leanplum$internal$Log$LeanplumLogType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.leanplum.internal.Log$LeanplumLogType r1 = com.leanplum.internal.Log.LeanplumLogType.ERROR     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$leanplum$internal$Log$LeanplumLogType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.leanplum.internal.Log$LeanplumLogType r1 = com.leanplum.internal.Log.LeanplumLogType.WARNING     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$leanplum$internal$Log$LeanplumLogType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.leanplum.internal.Log$LeanplumLogType r1 = com.leanplum.internal.Log.LeanplumLogType.INFO     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$leanplum$internal$Log$LeanplumLogType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.leanplum.internal.Log$LeanplumLogType r1 = com.leanplum.internal.Log.LeanplumLogType.VERBOSE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$com$leanplum$internal$Log$LeanplumLogType     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.leanplum.internal.Log$LeanplumLogType r1 = com.leanplum.internal.Log.LeanplumLogType.PRIVATE     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.Log.C23752.<clinit>():void");
        }
    }

    public enum LeanplumLogType {
        ERROR,
        WARNING,
        INFO,
        VERBOSE,
        PRIVATE,
        DEBUG
    }

    /* renamed from: e */
    public static void m280e(Object... objArr) {
        log(LeanplumLogType.ERROR, CollectionUtil.concatenateArray(objArr, ", "));
    }

    /* renamed from: w */
    public static void m284w(Object... objArr) {
        log(LeanplumLogType.WARNING, CollectionUtil.concatenateArray(objArr, ", "));
    }

    /* renamed from: i */
    public static void m281i(Object... objArr) {
        log(LeanplumLogType.INFO, CollectionUtil.concatenateArray(objArr, ", "));
    }

    /* renamed from: v */
    public static void m283v(Object... objArr) {
        log(LeanplumLogType.VERBOSE, CollectionUtil.concatenateArray(objArr, ", "));
    }

    /* renamed from: p */
    public static void m282p(Object... objArr) {
        log(LeanplumLogType.PRIVATE, CollectionUtil.concatenateArray(objArr, ", "));
    }

    /* renamed from: d */
    public static void m279d(Object... objArr) {
        log(LeanplumLogType.DEBUG, CollectionUtil.concatenateArray(objArr, ", "));
    }

    public static void log(LeanplumLogType leanplumLogType, String str) {
        String generateTag = generateTag(leanplumLogType);
        String generateMessagePrefix = generateMessagePrefix();
        int i = C23752.$SwitchMap$com$leanplum$internal$Log$LeanplumLogType[leanplumLogType.ordinal()];
        if (i == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(generateMessagePrefix);
            sb.append(str);
            android.util.Log.e(generateTag, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(generateTag);
            sb2.append(generateMessagePrefix);
            sb2.append(str);
            maybeSendLog(sb2.toString());
        } else if (i == 2) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(generateMessagePrefix);
            sb3.append(str);
            android.util.Log.w(generateTag, sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            sb4.append(generateTag);
            sb4.append(generateMessagePrefix);
            sb4.append(str);
            maybeSendLog(sb4.toString());
        } else if (i == 3) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(generateMessagePrefix);
            sb5.append(str);
            android.util.Log.i(generateTag, sb5.toString());
            StringBuilder sb6 = new StringBuilder();
            sb6.append(generateTag);
            sb6.append(generateMessagePrefix);
            sb6.append(str);
            maybeSendLog(sb6.toString());
        } else if (i == 4) {
            if (Constants.isDevelopmentModeEnabled && Constants.enableVerboseLoggingInDevelopmentMode) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append(generateMessagePrefix);
                sb7.append(str);
                android.util.Log.v(generateTag, sb7.toString());
                StringBuilder sb8 = new StringBuilder();
                sb8.append(generateTag);
                sb8.append(generateMessagePrefix);
                sb8.append(str);
                maybeSendLog(sb8.toString());
            }
        } else if (i == 5) {
            StringBuilder sb9 = new StringBuilder();
            sb9.append(generateTag);
            sb9.append(generateMessagePrefix);
            sb9.append(str);
            maybeSendLog(sb9.toString());
        }
    }

    private static String generateTag(LeanplumLogType leanplumLogType) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(leanplumLogType.name());
        sb.append("][Leanplum]");
        return sb.toString();
    }

    private static String generateMessagePrefix() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length < 5) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(stackTrace[5].getClassName());
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        String str = "::";
        sb3.append(str);
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(sb4);
        sb5.append(stackTrace[5].getMethodName());
        String sb6 = sb5.toString();
        StringBuilder sb7 = new StringBuilder();
        sb7.append(sb6);
        sb7.append(str);
        String sb8 = sb7.toString();
        StringBuilder sb9 = new StringBuilder();
        sb9.append(sb8);
        sb9.append(stackTrace[5].getLineNumber());
        String sb10 = sb9.toString();
        StringBuilder sb11 = new StringBuilder();
        sb11.append(sb10);
        sb11.append("]: ");
        return sb11.toString();
    }

    private static void maybeSendLog(String str) {
        if (Constants.loggingEnabled && !((Boolean) isLogging.get()).booleanValue()) {
            isLogging.set(Boolean.valueOf(true));
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("type", Values.SDK_LOG);
                hashMap.put("message", str);
                RequestOld.post(Methods.LOG, hashMap).sendEventually();
            } catch (Throwable th) {
                isLogging.remove();
                throw th;
            }
            isLogging.remove();
        }
    }

    public static String getStackTraceString(Throwable th) {
        return android.util.Log.getStackTraceString(th);
    }
}
