package com.microsoft.appcenter.utils;

import android.os.Process;
import androidx.annotation.VisibleForTesting;

public class ShutdownHelper {
    @VisibleForTesting
    ShutdownHelper() {
    }

    public static void shutdown(int i) {
        Process.killProcess(Process.myPid());
        System.exit(i);
    }
}
