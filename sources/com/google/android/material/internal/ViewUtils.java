package com.google.android.material.internal;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.Dimension;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.core.view.ViewCompat;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ViewUtils {
    public static Mode parseTintMode(int i, Mode mode) {
        if (i == 3) {
            return Mode.SRC_OVER;
        }
        if (i == 5) {
            return Mode.SRC_IN;
        }
        if (i == 9) {
            return Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return Mode.MULTIPLY;
            case 15:
                return Mode.SCREEN;
            case 16:
                return Mode.ADD;
            default:
                return mode;
        }
    }

    public static boolean isLayoutRtl(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }

    public static float dpToPx(Context context, @Dimension(unit = 0) int i) {
        return TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }
}