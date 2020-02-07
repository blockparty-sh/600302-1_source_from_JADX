package org.koin.core.logger;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016¨\u0006\n"}, mo37405d2 = {"Lorg/koin/core/logger/EmptyLogger;", "Lorg/koin/core/logger/Logger;", "()V", "log", "", "level", "Lorg/koin/core/logger/Level;", "msg", "", "Lorg/koin/core/logger/MESSAGE;", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: EmptyLogger.kt */
public final class EmptyLogger extends Logger {
    public void log(@NotNull Level level, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(level, Param.LEVEL);
        Intrinsics.checkParameterIsNotNull(str, NotificationCompat.CATEGORY_MESSAGE);
    }

    public EmptyLogger() {
        super(Level.ERROR);
    }
}
