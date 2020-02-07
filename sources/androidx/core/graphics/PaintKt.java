package androidx.core.graphics;

import android.graphics.Paint;
import androidx.annotation.ColorInt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\b\u001a!\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\b¨\u0006\b"}, mo37405d2 = {"setBlendMode", "", "Landroid/graphics/Paint;", "blendModeCompat", "Landroidx/core/graphics/BlendModeCompat;", "setBlendModeColorFilter", "color", "", "core-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Paint.kt */
public final class PaintKt {
    public static final boolean setBlendMode(@NotNull Paint paint, @Nullable BlendModeCompat blendModeCompat) {
        Intrinsics.checkParameterIsNotNull(paint, "$this$setBlendMode");
        return PaintCompat.setBlendMode(paint, blendModeCompat);
    }

    public static final boolean setBlendModeColorFilter(@NotNull Paint paint, @ColorInt int i, @Nullable BlendModeCompat blendModeCompat) {
        Intrinsics.checkParameterIsNotNull(paint, "$this$setBlendModeColorFilter");
        return PaintCompat.setBlendModeColorFilter(paint, i, blendModeCompat);
    }
}
