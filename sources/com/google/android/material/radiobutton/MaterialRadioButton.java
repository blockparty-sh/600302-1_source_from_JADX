package com.google.android.material.radiobutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.widget.CompoundButtonCompat;
import com.google.android.material.C1435R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;

public class MaterialRadioButton extends AppCompatRadioButton {
    private static final int DEF_STYLE_RES = C1435R.C1440style.Widget_MaterialComponents_CompoundButton_RadioButton;
    private static final int[][] ENABLED_CHECKED_STATES = {new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};
    @Nullable
    private ColorStateList materialThemeColorsTintList;

    public MaterialRadioButton(Context context) {
        this(context, null);
    }

    public MaterialRadioButton(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, C1435R.attr.radioButtonStyle);
    }

    public MaterialRadioButton(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(ThemeEnforcement.createThemedContext(context, attributeSet, i, DEF_STYLE_RES), attributeSet, i);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(getContext(), attributeSet, C1435R.styleable.MaterialRadioButton, i, DEF_STYLE_RES, new int[0]);
        boolean z = obtainStyledAttributes.getBoolean(C1435R.styleable.MaterialRadioButton_useMaterialThemeColors, false);
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
            int color = MaterialColors.getColor(this, C1435R.attr.colorControlActivated);
            int color2 = MaterialColors.getColor(this, C1435R.attr.colorOnSurface);
            int color3 = MaterialColors.getColor(this, C1435R.attr.colorSurface);
            int[] iArr = new int[ENABLED_CHECKED_STATES.length];
            iArr[0] = MaterialColors.layer(color3, color, 1.0f);
            iArr[1] = MaterialColors.layer(color3, color2, 0.54f);
            iArr[2] = MaterialColors.layer(color3, color2, 0.38f);
            iArr[3] = MaterialColors.layer(color3, color2, 0.38f);
            this.materialThemeColorsTintList = new ColorStateList(ENABLED_CHECKED_STATES, iArr);
        }
        return this.materialThemeColorsTintList;
    }
}
