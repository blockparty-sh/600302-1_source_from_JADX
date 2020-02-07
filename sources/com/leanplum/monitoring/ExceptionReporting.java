package com.leanplum.monitoring;

import android.content.Context;

public interface ExceptionReporting {
    void reportException(Throwable th);

    void setContext(Context context);
}
