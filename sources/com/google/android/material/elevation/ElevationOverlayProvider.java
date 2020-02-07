package com.google.android.material.elevation;

import android.content.Context;
import androidx.annotation.ColorInt;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.C1435R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialAttributes;

public class ElevationOverlayProvider {
    private static final float FORMULA_MULTIPLIER = 4.5f;
    private static final float FORMULA_OFFSET = 2.0f;
    private final int colorSurface;
    private final float displayDensity;
    private final int elevationOverlaysColor;
    private final boolean elevationOverlaysEnabled;

    public ElevationOverlayProvider(Context context) {
        this.elevationOverlaysEnabled = MaterialAttributes.resolveBoolean(context, C1435R.attr.elevationOverlaysEnabled, false);
        this.elevationOverlaysColor = MaterialColors.getColor(context, C1435R.attr.elevationOverlaysColor, 0);
        this.colorSurface = MaterialColors.getColor(context, C1435R.attr.colorSurface, 0);
        this.displayDensity = context.getResources().getDisplayMetrics().density;
    }

    @ColorInt
    public int layerOverlayIfNeeded(@ColorInt int i, float f) {
        return (!this.elevationOverlaysEnabled || !isSurfaceColor(i)) ? i : layerOverlay(i, f);
    }

    @ColorInt
    public int layerOverlay(@ColorInt int i, float f) {
        return MaterialColors.layer(i, this.elevationOverlaysColor, calculateOverlayAlphaFraction(f));
    }

    public int calculateOverlayAlpha(float f) {
        return Math.round(calculateOverlayAlphaFraction(f) * 255.0f);
    }

    public float calculateOverlayAlphaFraction(float f) {
        float f2 = this.displayDensity;
        if (f2 <= 0.0f || f <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p((double) (f / f2))) * FORMULA_MULTIPLIER) + FORMULA_OFFSET) / 100.0f, 1.0f);
    }

    public boolean isOverlaysEnabled() {
        return this.elevationOverlaysEnabled;
    }

    @ColorInt
    public int getOverlaysColor() {
        return this.elevationOverlaysColor;
    }

    @ColorInt
    public int getSurfaceColor() {
        return this.colorSurface;
    }

    @ColorInt
    public int getSurfaceColorWithOverlayIfNeeded(float f) {
        return layerOverlayIfNeeded(this.colorSurface, f);
    }

    private boolean isSurfaceColor(@ColorInt int i) {
        return ColorUtils.setAlphaComponent(i, 255) == this.colorSurface;
    }
}
