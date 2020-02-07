package androidx.work;

import androidx.annotation.RequiresApi;
import androidx.exifinterface.media.ExifInterface;
import androidx.work.PeriodicWorkRequest.Builder;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\b\u001a%\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\b\u001a%\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\b\u001a5\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\b¨\u0006\u000b"}, mo37405d2 = {"PeriodicWorkRequestBuilder", "Landroidx/work/PeriodicWorkRequest$Builder;", "W", "Landroidx/work/ListenableWorker;", "repeatInterval", "Ljava/time/Duration;", "flexTimeInterval", "", "repeatIntervalTimeUnit", "Ljava/util/concurrent/TimeUnit;", "flexTimeIntervalUnit", "work-runtime-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: PeriodicWorkRequest.kt */
public final class PeriodicWorkRequestKt {
    @NotNull
    public static final /* synthetic */ <W extends ListenableWorker> Builder PeriodicWorkRequestBuilder(long j, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "repeatIntervalTimeUnit");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_WEST);
        return new Builder(ListenableWorker.class, j, timeUnit);
    }

    @RequiresApi(26)
    @NotNull
    public static final /* synthetic */ <W extends ListenableWorker> Builder PeriodicWorkRequestBuilder(@NotNull Duration duration) {
        Intrinsics.checkParameterIsNotNull(duration, "repeatInterval");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_WEST);
        return new Builder(ListenableWorker.class, duration);
    }

    @NotNull
    public static final /* synthetic */ <W extends ListenableWorker> Builder PeriodicWorkRequestBuilder(long j, @NotNull TimeUnit timeUnit, long j2, @NotNull TimeUnit timeUnit2) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "repeatIntervalTimeUnit");
        Intrinsics.checkParameterIsNotNull(timeUnit2, "flexTimeIntervalUnit");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_WEST);
        Builder builder = new Builder(ListenableWorker.class, j, timeUnit, j2, timeUnit2);
        return builder;
    }

    @RequiresApi(26)
    @NotNull
    public static final /* synthetic */ <W extends ListenableWorker> Builder PeriodicWorkRequestBuilder(@NotNull Duration duration, @NotNull Duration duration2) {
        Intrinsics.checkParameterIsNotNull(duration, "repeatInterval");
        Intrinsics.checkParameterIsNotNull(duration2, "flexTimeInterval");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_WEST);
        return new Builder(ListenableWorker.class, duration, duration2);
    }
}
