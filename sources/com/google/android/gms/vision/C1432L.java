package com.google.android.gms.vision;

import android.util.Log;
import androidx.annotation.Keep;

@Keep
/* renamed from: com.google.android.gms.vision.L */
public class C1432L {
    public static boolean isLoggable(int i) {
        return 4 <= i;
    }

    public static int zza(String str, Object... objArr) {
        if (!isLoggable(2)) {
            return 0;
        }
        return Log.v("Vision", String.format(str, objArr));
    }

    public static int zzb(String str, Object... objArr) {
        if (!isLoggable(3)) {
            return 0;
        }
        return Log.d("Vision", String.format(str, objArr));
    }

    public static int zzc(String str, Object... objArr) {
        if (!isLoggable(6)) {
            return 0;
        }
        return Log.e("Vision", String.format(str, objArr));
    }

    public static int zza(Throwable th, String str, Object... objArr) {
        if (!isLoggable(6)) {
            return 0;
        }
        String str2 = "Vision";
        if (isLoggable(3)) {
            return Log.e(str2, String.format(str, objArr), th);
        }
        String format = String.format(str, objArr);
        String valueOf = String.valueOf(th);
        StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 2 + String.valueOf(valueOf).length());
        sb.append(format);
        sb.append(": ");
        sb.append(valueOf);
        return Log.e(str2, sb.toString());
    }
}
