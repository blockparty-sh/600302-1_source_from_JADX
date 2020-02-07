package org.koin.core.logger;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.PrintStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016¨\u0006\n"}, mo37405d2 = {"Lorg/koin/core/logger/PrintLogger;", "Lorg/koin/core/logger/Logger;", "level", "Lorg/koin/core/logger/Level;", "(Lorg/koin/core/logger/Level;)V", "log", "", "msg", "", "Lorg/koin/core/logger/MESSAGE;", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: PrintLogger.kt */
public final class PrintLogger extends Logger {
    public PrintLogger() {
        this(null, 1, null);
    }

    public PrintLogger(@NotNull Level level) {
        Intrinsics.checkParameterIsNotNull(level, Param.LEVEL);
        super(level);
    }

    public /* synthetic */ PrintLogger(Level level, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            level = Level.INFO;
        }
        this(level);
    }

    public void log(@NotNull Level level, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(level, Param.LEVEL);
        Intrinsics.checkParameterIsNotNull(str, NotificationCompat.CATEGORY_MESSAGE);
        PrintStream printStream = level.compareTo(Level.ERROR) >= 0 ? System.err : System.out;
        if (getLevel().compareTo(level) <= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            sb.append(level);
            sb.append("] [Koin] ");
            sb.append(str);
            printStream.println(sb.toString());
        }
    }
}
