package kotlin.concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001aJ\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001aL\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a\u001a\u0010\u0010\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001aJ\u0010\u0010\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001aL\u0010\u0010\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a$\u0010\u0011\u001a\u00020\f2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a0\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a8\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a0\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a8\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a8\u0010\u0015\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a8\u0010\u0015\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b¨\u0006\u0016"}, mo37405d2 = {"fixedRateTimer", "Ljava/util/Timer;", "name", "", "daemon", "", "startAt", "Ljava/util/Date;", "period", "", "action", "Lkotlin/Function1;", "Ljava/util/TimerTask;", "", "Lkotlin/ExtensionFunctionType;", "initialDelay", "timer", "timerTask", "schedule", "time", "delay", "scheduleAtFixedRate", "kotlin-stdlib"}, mo37406k = 2, mo37407mv = {1, 1, 15})
@JvmName(name = "TimersKt")
/* compiled from: Timer.kt */
public final class TimersKt {
    @InlineOnly
    private static final TimerTask schedule(@NotNull Timer timer, long j, Function1<? super TimerTask, Unit> function1) {
        TimerTask timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.schedule(timersKt$timerTask$1, j);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask schedule(@NotNull Timer timer, Date date, Function1<? super TimerTask, Unit> function1) {
        TimerTask timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.schedule(timersKt$timerTask$1, date);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask schedule(@NotNull Timer timer, long j, long j2, Function1<? super TimerTask, Unit> function1) {
        TimerTask timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.schedule(timersKt$timerTask$1, j, j2);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask schedule(@NotNull Timer timer, Date date, long j, Function1<? super TimerTask, Unit> function1) {
        TimerTask timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.schedule(timersKt$timerTask$1, date, j);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask scheduleAtFixedRate(@NotNull Timer timer, long j, long j2, Function1<? super TimerTask, Unit> function1) {
        TimerTask timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.scheduleAtFixedRate(timersKt$timerTask$1, j, j2);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask scheduleAtFixedRate(@NotNull Timer timer, Date date, long j, Function1<? super TimerTask, Unit> function1) {
        TimerTask timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.scheduleAtFixedRate(timersKt$timerTask$1, date, j);
        return timersKt$timerTask$1;
    }

    @NotNull
    @PublishedApi
    public static final Timer timer(@Nullable String str, boolean z) {
        return str == null ? new Timer(z) : new Timer(str, z);
    }

    static /* synthetic */ Timer timer$default(String str, boolean z, long j, long j2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            j = 0;
        }
        long j3 = j;
        Timer timer = timer(str, z);
        timer.schedule(new TimersKt$timerTask$1(function1), j3, j2);
        return timer;
    }

    @InlineOnly
    private static final Timer timer(String str, boolean z, long j, long j2, Function1<? super TimerTask, Unit> function1) {
        Timer timer = timer(str, z);
        timer.schedule(new TimersKt$timerTask$1(function1), j, j2);
        return timer;
    }

    static /* synthetic */ Timer timer$default(String str, boolean z, Date date, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        Timer timer = timer(str, z);
        timer.schedule(new TimersKt$timerTask$1(function1), date, j);
        return timer;
    }

    @InlineOnly
    private static final Timer timer(String str, boolean z, Date date, long j, Function1<? super TimerTask, Unit> function1) {
        Timer timer = timer(str, z);
        timer.schedule(new TimersKt$timerTask$1(function1), date, j);
        return timer;
    }

    static /* synthetic */ Timer fixedRateTimer$default(String str, boolean z, long j, long j2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            j = 0;
        }
        long j3 = j;
        Timer timer = timer(str, z);
        timer.scheduleAtFixedRate(new TimersKt$timerTask$1(function1), j3, j2);
        return timer;
    }

    @InlineOnly
    private static final Timer fixedRateTimer(String str, boolean z, long j, long j2, Function1<? super TimerTask, Unit> function1) {
        Timer timer = timer(str, z);
        timer.scheduleAtFixedRate(new TimersKt$timerTask$1(function1), j, j2);
        return timer;
    }

    static /* synthetic */ Timer fixedRateTimer$default(String str, boolean z, Date date, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        Timer timer = timer(str, z);
        timer.scheduleAtFixedRate(new TimersKt$timerTask$1(function1), date, j);
        return timer;
    }

    @InlineOnly
    private static final Timer fixedRateTimer(String str, boolean z, Date date, long j, Function1<? super TimerTask, Unit> function1) {
        Timer timer = timer(str, z);
        timer.scheduleAtFixedRate(new TimersKt$timerTask$1(function1), date, j);
        return timer;
    }

    @InlineOnly
    private static final TimerTask timerTask(Function1<? super TimerTask, Unit> function1) {
        return new TimersKt$timerTask$1(function1);
    }
}