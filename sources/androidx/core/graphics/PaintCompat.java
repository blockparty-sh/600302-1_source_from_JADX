package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build.VERSION;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;

public final class PaintCompat {
    private static final String EM_STRING = "m";
    private static final String TOFU_STRING = "󟿽";
    private static final ThreadLocal<Pair<Rect, Rect>> sRectThreadLocal = new ThreadLocal<>();

    public static boolean hasGlyph(@NonNull Paint paint, @NonNull String str) {
        if (VERSION.SDK_INT >= 23) {
            return paint.hasGlyph(str);
        }
        int length = str.length();
        if (length == 1 && Character.isWhitespace(str.charAt(0))) {
            return true;
        }
        String str2 = TOFU_STRING;
        float measureText = paint.measureText(str2);
        float measureText2 = paint.measureText(EM_STRING);
        float measureText3 = paint.measureText(str);
        float f = 0.0f;
        if (measureText3 == 0.0f) {
            return false;
        }
        if (str.codePointCount(0, str.length()) > 1) {
            if (measureText3 > measureText2 * 2.0f) {
                return false;
            }
            int i = 0;
            while (i < length) {
                int charCount = Character.charCount(str.codePointAt(i)) + i;
                f += paint.measureText(str, i, charCount);
                i = charCount;
            }
            if (measureText3 >= f) {
                return false;
            }
        }
        if (measureText3 != measureText) {
            return true;
        }
        Pair obtainEmptyRects = obtainEmptyRects();
        paint.getTextBounds(str2, 0, 2, (Rect) obtainEmptyRects.first);
        paint.getTextBounds(str, 0, length, (Rect) obtainEmptyRects.second);
        return !((Rect) obtainEmptyRects.first).equals(obtainEmptyRects.second);
    }

    public static boolean setBlendMode(@NonNull Paint paint, @Nullable BlendModeCompat blendModeCompat) {
        boolean z = true;
        BlendMode blendMode = null;
        if (VERSION.SDK_INT >= 29) {
            if (blendModeCompat != null) {
                blendMode = obtainBlendModeFromCompat(blendModeCompat);
            }
            paint.setBlendMode(blendMode);
            return true;
        } else if (blendModeCompat != null) {
            Mode obtainPorterDuffFromCompat = obtainPorterDuffFromCompat(blendModeCompat);
            if (obtainPorterDuffFromCompat != null) {
                blendMode = new PorterDuffXfermode(obtainPorterDuffFromCompat);
            }
            paint.setXfermode(blendMode);
            if (obtainPorterDuffFromCompat == null) {
                z = false;
            }
            return z;
        } else {
            paint.setXfermode(null);
            return true;
        }
    }

    public static boolean setBlendModeColorFilter(@NonNull Paint paint, @ColorInt int i, @Nullable BlendModeCompat blendModeCompat) {
        boolean z = true;
        PorterDuffColorFilter porterDuffColorFilter = null;
        if (VERSION.SDK_INT >= 29) {
            BlendMode obtainBlendModeFromCompat = obtainBlendModeFromCompat(blendModeCompat);
            if (obtainBlendModeFromCompat != null) {
                paint.setColorFilter(new BlendModeColorFilter(i, obtainBlendModeFromCompat));
            } else {
                paint.setColorFilter(null);
            }
            return true;
        } else if (blendModeCompat != null) {
            Mode obtainPorterDuffFromCompat = obtainPorterDuffFromCompat(blendModeCompat);
            if (obtainPorterDuffFromCompat != null) {
                porterDuffColorFilter = new PorterDuffColorFilter(i, obtainPorterDuffFromCompat);
            }
            paint.setColorFilter(porterDuffColorFilter);
            if (obtainPorterDuffFromCompat == null) {
                z = false;
            }
            return z;
        } else {
            paint.setColorFilter(null);
            return true;
        }
    }

    @RequiresApi(29)
    @VisibleForTesting
    @Nullable
    static BlendMode obtainBlendModeFromCompat(@NonNull BlendModeCompat blendModeCompat) {
        switch (blendModeCompat) {
            case CLEAR:
                return BlendMode.CLEAR;
            case SRC:
                return BlendMode.SRC;
            case DST:
                return BlendMode.DST;
            case SRC_OVER:
                return BlendMode.SRC_OVER;
            case DST_OVER:
                return BlendMode.SRC_OVER;
            case SRC_IN:
                return BlendMode.SRC_IN;
            case DST_IN:
                return BlendMode.DST_IN;
            case SRC_OUT:
                return BlendMode.SRC_OUT;
            case DST_OUT:
                return BlendMode.DST_OUT;
            case SRC_ATOP:
                return BlendMode.SRC_ATOP;
            case DST_ATOP:
                return BlendMode.DST_ATOP;
            case XOR:
                return BlendMode.XOR;
            case PLUS:
                return BlendMode.PLUS;
            case MODULATE:
                return BlendMode.MODULATE;
            case SCREEN:
                return BlendMode.SCREEN;
            case OVERLAY:
                return BlendMode.OVERLAY;
            case DARKEN:
                return BlendMode.DARKEN;
            case LIGHTEN:
                return BlendMode.LIGHTEN;
            case COLOR_DODGE:
                return BlendMode.COLOR_DODGE;
            case COLOR_BURN:
                return BlendMode.COLOR_BURN;
            case HARD_LIGHT:
                return BlendMode.HARD_LIGHT;
            case SOFT_LIGHT:
                return BlendMode.SOFT_LIGHT;
            case DIFFERENCE:
                return BlendMode.DIFFERENCE;
            case EXCLUSION:
                return BlendMode.EXCLUSION;
            case MULTIPLY:
                return BlendMode.MULTIPLY;
            case HUE:
                return BlendMode.HUE;
            case SATURATION:
                return BlendMode.SATURATION;
            case COLOR:
                return BlendMode.COLOR;
            case LUMINOSITY:
                return BlendMode.LUMINOSITY;
            default:
                return null;
        }
    }

    @VisibleForTesting
    @Nullable
    static Mode obtainPorterDuffFromCompat(@Nullable BlendModeCompat blendModeCompat) {
        if (blendModeCompat == null) {
            return null;
        }
        switch (blendModeCompat) {
            case CLEAR:
                return Mode.CLEAR;
            case SRC:
                return Mode.SRC;
            case DST:
                return Mode.DST;
            case SRC_OVER:
                return Mode.SRC_OVER;
            case DST_OVER:
                return Mode.DST_OVER;
            case SRC_IN:
                return Mode.SRC_IN;
            case DST_IN:
                return Mode.DST_IN;
            case SRC_OUT:
                return Mode.SRC_OUT;
            case DST_OUT:
                return Mode.DST_OUT;
            case SRC_ATOP:
                return Mode.SRC_ATOP;
            case DST_ATOP:
                return Mode.DST_ATOP;
            case XOR:
                return Mode.XOR;
            case PLUS:
                return Mode.ADD;
            case MODULATE:
                return Mode.MULTIPLY;
            case SCREEN:
                return Mode.SCREEN;
            case OVERLAY:
                return Mode.OVERLAY;
            case DARKEN:
                return Mode.DARKEN;
            case LIGHTEN:
                return Mode.LIGHTEN;
            default:
                return null;
        }
    }

    private static Pair<Rect, Rect> obtainEmptyRects() {
        Pair<Rect, Rect> pair = (Pair) sRectThreadLocal.get();
        if (pair == null) {
            Pair<Rect, Rect> pair2 = new Pair<>(new Rect(), new Rect());
            sRectThreadLocal.set(pair2);
            return pair2;
        }
        ((Rect) pair.first).setEmpty();
        ((Rect) pair.second).setEmpty();
        return pair;
    }

    private PaintCompat() {
    }
}
