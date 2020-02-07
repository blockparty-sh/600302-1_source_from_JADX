package com.leanplum.internal;

import android.os.Handler;
import android.os.Looper;

public class OsHandler {
    public static OsHandler instance;
    final Handler handler = new Handler(Looper.getMainLooper());

    public Boolean post(Runnable runnable) {
        return Boolean.valueOf(this.handler.post(runnable));
    }

    public Boolean postDelayed(Runnable runnable, long j) {
        return Boolean.valueOf(this.handler.postDelayed(runnable, j));
    }

    public static OsHandler getInstance() {
        if (instance == null) {
            instance = new OsHandler();
        }
        return instance;
    }
}
