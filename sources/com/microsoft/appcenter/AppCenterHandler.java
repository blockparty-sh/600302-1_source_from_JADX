package com.microsoft.appcenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface AppCenterHandler {
    void post(@NonNull Runnable runnable, @Nullable Runnable runnable2);
}
