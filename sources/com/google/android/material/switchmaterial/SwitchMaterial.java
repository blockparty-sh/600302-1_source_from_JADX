package com.google.android.material.switchmaterial;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.C1435R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ThemeEnforcement;

public class SwitchMaterial extends SwitchCompat {
    private static final int DEF_STYLE_RES = C1435R.C1440style.Widget_MaterialComponents_CompoundButton_Switch;
    private static final int[][] ENABLED_CHECKED_STATES = {new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};
    private final ElevationOverlayProvider elevationOverlayProvider;
    @Nullable
    private ColorStateList materialThemeColorsThumbTintList;
    @Nullable
    private ColorStateList materialThemeColorsTrackTintList;

    public SwitchMaterial(Context context) {
        this(context, null);
    }

    public SwitchMaterial(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, C1435R.attr.switchStyle);
    }

    public SwitchMaterial(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(ThemeEnforcement.createThemedContext(context, attributeSet, i, DEF_STYLE_RES), attributeSet, i);
        Context context2 = getContext();
        this.elevationOverlayProvider = new ElevationOverlayProvider(context2);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, C1435R.styleable.SwitchMaterial, i, DEF_STYLE_RES, new int[0]);
        boolean z = obtainStyledAttributes.getBoolean(C1435R.styleable.SwitchMaterial_useMaterialThemeColors, false);
        obtainStyledAttributes.recycle();
        if (z && getThumbTintList() == null) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
        }
        if (z && getTrackTintList() == null) {
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        if (z) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
            setTrackTintList(getMaterialThemeColorsTrackTintList());
            return;
        }
        setThumbTintList(null);
        setTrackTintList(null);
    }

    public boolean isUseMaterialThemeColors() {
        ColorStateList colorStateList = this.materialThemeColorsThumbTintList;
        if (colorStateList == null || this.materialThemeColorsTrackTintList == null || !colorStateList.equals(getThumbTintList()) || !this.materialThemeColorsTrackTintList.equals(getTrackTintList())) {
            return false;
        }
        return true;
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.materialThemeColorsThumbTintList == null) {
            int color = MaterialColors.getColor(this, C1435R.attr.colorSurface);
            int color2 = MaterialColors.getColor(this, C1435R.attr.colorControlActivated);
            int layerOverlayIfNeeded = this.elevationOverlayProvider.layerOverlayIfNeeded(color, (float) getResources().getDimensionPixelSize(C1435R.dimen.mtrl_switch_thumb_elevation));
            int[] iArr = new int[ENABLED_CHECKED_STATES.length];
            iArr[0] = MaterialColors.layer(color, color2, 1.0f);
            iArr[1] = layerOverlayIfNeeded;
            iArr[2] = MaterialColors.layer(color, color2, 0.38f);
            iArr[3] = layerOverlayIfNeeded;
            this.materialThemeColorsThumbTintList = new ColorStateList(ENABLED_CHECKED_STATES, iArr);
        }
        return this.materialThemeColorsThumbTintList;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.materialThemeColorsTrackTintList == null) {
            int[] iArr = new int[ENABLED_CHECKED_STATES.length];
            int color = MaterialColors.getColor(this, C1435R.attr.colorSurface);
            int color2 = MaterialColors.getColor(this, C1435R.attr.colorControlActivated);
            int color3 = MaterialColors.getColor(this, C1435R.attr.colorOnSurface);
            iArr[0] = MaterialColors.layer(color, color2, 0.54f);
            iArr[1] = MaterialColors.layer(color, color3, 0.32f);
            iArr[2] = MaterialColors.layer(color, color2, 0.12f);
            iArr[3] = MaterialColors.layer(color, color3, 0.12f);
            this.materialThemeColorsTrackTintList = new ColorStateList(ENABLED_CHECKED_STATES, iArr);
        }
        return this.materialThemeColorsTrackTintList;
    }
}
