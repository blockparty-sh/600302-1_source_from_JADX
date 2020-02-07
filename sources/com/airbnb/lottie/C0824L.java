package com.airbnb.lottie;

import android.util.Log;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.core.p003os.TraceCompat;
import java.util.HashSet;
import java.util.Set;

@RestrictTo({Scope.LIBRARY})
/* renamed from: com.airbnb.lottie.L */
public class C0824L {
    public static boolean DBG = false;
    private static final int MAX_DEPTH = 20;
    public static final String TAG = "LOTTIE";
    private static int depthPastMaxDepth = 0;
    private static final Set<String> loggedMessages = new HashSet();
    private static String[] sections;
    private static long[] startTimeNs;
    private static int traceDepth = 0;
    private static boolean traceEnabled = false;

    public static void debug(String str) {
        if (DBG) {
            Log.d(TAG, str);
        }
    }

    public static void warn(String str) {
        if (!loggedMessages.contains(str)) {
            Log.w(TAG, str);
            loggedMessages.add(str);
        }
    }

    public static void setTraceEnabled(boolean z) {
        if (traceEnabled != z) {
            traceEnabled = z;
            if (traceEnabled) {
                sections = new String[20];
                startTimeNs = new long[20];
            }
        }
    }

    public static void beginSection(String str) {
        if (traceEnabled) {
            int i = traceDepth;
            if (i == 20) {
                depthPastMaxDepth++;
                return;
            }
            sections[i] = str;
            startTimeNs[i] = System.nanoTime();
            TraceCompat.beginSection(str);
            traceDepth++;
        }
    }

    public static float endSection(String str) {
        int i = depthPastMaxDepth;
        if (i > 0) {
            depthPastMaxDepth = i - 1;
            return 0.0f;
        } else if (!traceEnabled) {
            return 0.0f;
        } else {
            traceDepth--;
            int i2 = traceDepth;
            if (i2 == -1) {
                throw new IllegalStateException("Can't end trace section. There are none.");
            } else if (str.equals(sections[i2])) {
                TraceCompat.endSection();
                return ((float) (System.nanoTime() - startTimeNs[traceDepth])) / 1000000.0f;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unbalanced trace call ");
                sb.append(str);
                sb.append(". Expected ");
                sb.append(sections[traceDepth]);
                sb.append(".");
                throw new IllegalStateException(sb.toString());
            }
        }
    }
}
