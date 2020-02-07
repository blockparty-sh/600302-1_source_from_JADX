package org.koin.core.logger;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\fJ\u0012\u0010\r\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\fJ\u0012\u0010\u000e\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\fJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003J\u001c\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\n\u001a\u00060\u000bj\u0002`\fH&R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0013"}, mo37405d2 = {"Lorg/koin/core/logger/Logger;", "", "level", "Lorg/koin/core/logger/Level;", "(Lorg/koin/core/logger/Level;)V", "getLevel", "()Lorg/koin/core/logger/Level;", "setLevel", "debug", "", "msg", "", "Lorg/koin/core/logger/MESSAGE;", "error", "info", "isAt", "", "lvl", "log", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Logger.kt */
public abstract class Logger {
    @NotNull
    private Level level;

    public Logger() {
        this(null, 1, null);
    }

    public abstract void log(@NotNull Level level2, @NotNull String str);

    public Logger(@NotNull Level level2) {
        Intrinsics.checkParameterIsNotNull(level2, Param.LEVEL);
        this.level = level2;
    }

    public /* synthetic */ Logger(Level level2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            level2 = Level.INFO;
        }
        this(level2);
    }

    @NotNull
    public final Level getLevel() {
        return this.level;
    }

    public final void setLevel(@NotNull Level level2) {
        Intrinsics.checkParameterIsNotNull(level2, "<set-?>");
        this.level = level2;
    }

    public final void debug(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, NotificationCompat.CATEGORY_MESSAGE);
        log(Level.DEBUG, str);
    }

    public final void info(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, NotificationCompat.CATEGORY_MESSAGE);
        log(Level.INFO, str);
    }

    public final void error(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, NotificationCompat.CATEGORY_MESSAGE);
        log(Level.ERROR, str);
    }

    public final boolean isAt(@NotNull Level level2) {
        Intrinsics.checkParameterIsNotNull(level2, "lvl");
        return this.level.compareTo(level2) <= 0;
    }
}
