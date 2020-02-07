package com.yakivmospan.scytale;

import android.os.Build.VERSION;

/* renamed from: com.yakivmospan.scytale.Utils */
final class C2652Utils {
    static String TAG = "Scytale";
    static final int VERSION = VERSION.SDK_INT;

    private C2652Utils() {
    }

    static boolean lowerThenJellyBean() {
        return VERSION < 18;
    }

    static boolean lowerThenMarshmallow() {
        return VERSION < 23;
    }

    static boolean biggerThenJellyBean() {
        return VERSION > 18;
    }

    static boolean isJellyBean() {
        return VERSION == 18;
    }
}
