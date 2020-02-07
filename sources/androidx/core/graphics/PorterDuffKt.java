package androidx.core.graphics;

import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\r\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\b¨\u0006\u0007"}, mo37405d2 = {"toColorFilter", "Landroid/graphics/PorterDuffColorFilter;", "Landroid/graphics/PorterDuff$Mode;", "color", "", "toXfermode", "Landroid/graphics/PorterDuffXfermode;", "core-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: PorterDuff.kt */
public final class PorterDuffKt {
    @NotNull
    public static final PorterDuffXfermode toXfermode(@NotNull Mode mode) {
        Intrinsics.checkParameterIsNotNull(mode, "$this$toXfermode");
        return new PorterDuffXfermode(mode);
    }

    @NotNull
    public static final PorterDuffColorFilter toColorFilter(@NotNull Mode mode, int i) {
        Intrinsics.checkParameterIsNotNull(mode, "$this$toColorFilter");
        return new PorterDuffColorFilter(i, mode);
    }
}
