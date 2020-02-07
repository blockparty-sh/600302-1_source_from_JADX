package com.google.android.material.checkbox;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.widget.CompoundButtonCompat;
import com.google.android.material.C1435R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;

public class MaterialCheckBox extends AppCompatCheckBox {
    private static final int DEF_STYLE_RES = C1435R.C1440style.Widget_MaterialComponents_CompoundButton_CheckBox;
    private static final int[][] ENABLED_CHECKED_STATES = {new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};
    @Nullable
    private ColorStateList materialThemeColorsTintList;

    public MaterialCheckBox(Context context) {
        this(context, null);
    }

    public MaterialCheckBox(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, C1435R.attr.checkboxStyle);
    }

    public MaterialCheckBox(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(ThemeEnforcement.createThemedContext(context, attributeSet, i, DEF_STYLE_RES), attributeSet, i);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(getContext(), attributeSet, C1435R.styleable.MaterialCheckBox, i, DEF_STYLE_RES, new int[0]);
        boolean z = obtainStyledAttributes.getBoolean(C1435R.styleable.MaterialCheckBox_useMaterialThemeColors, false);
        obtainStyledAttributes.recycle();
        if (z && CompoundButtonCompat.getButtonTintList(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        if (z) {
            CompoundButtonCompat.setButtonTintList(this, getMaterialThemeColorsTintList());
        } else {
            CompoundButtonCompat.setButtonTintList(this, null);
        }
    }

    public boolean isUseMaterialThemeColors() {
        ColorStateList colorStateList = this.materialThemeColorsTintList;
        return colorStateList != null && colorStateList.equals(CompoundButtonCompat.getButtonTintList(this));
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.materialThemeColorsTintList == null) {
            int[] iArr = new int[ENABLED_CHECKED_STATES.length];
            int color = MaterialColors.getColor(this, C1435R.attr.colorControlActivated);
            int color2 = MaterialColors.getColor(this, C1435R.attr.colorSurface);
            int color3 = MaterialColors.getColor(this, C1435R.attr.colorOnSurface);
            iArr[0] = MaterialColors.layer(color2, color, 1.0f);
            iArr[1] = MaterialColors.layer(color2, color3, 0.54f);
            iArr[2] = MaterialColors.layer(color2, color3, 0.38f);
            iArr[3] = MaterialColors.layer(color2, color3, 0.38f);
            this.materialThemeColorsTintList = new ColorStateList(ENABLED_CHECKED_STATES, iArr);
        }
        return this.materialThemeColorsTintList;
    }
}
