package com.google.android.material.picker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.widget.TextView;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.StyleRes;
import androidx.core.view.ViewCompat;
import com.google.android.material.C1435R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

@RestrictTo({Scope.LIBRARY_GROUP})
class CalendarGridSelectors {
    private CalendarGridSelectors() {
    }

    static void colorCell(TextView textView, @StyleRes int i) {
        Context context = textView.getContext();
        if (i != 0) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, C1435R.styleable.MaterialCalendarDay);
            ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, C1435R.styleable.MaterialCalendarDay_itemFillColor);
            ColorStateList colorStateList2 = MaterialResources.getColorStateList(context, obtainStyledAttributes, C1435R.styleable.MaterialCalendarDay_itemTextColor);
            ColorStateList colorStateList3 = MaterialResources.getColorStateList(context, obtainStyledAttributes, C1435R.styleable.MaterialCalendarDay_itemStrokeColor);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C1435R.styleable.MaterialCalendarDay_itemStrokeWidth, 0);
            ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel(context, obtainStyledAttributes.getResourceId(C1435R.styleable.MaterialCalendarDay_itemShapeAppearance, 0), obtainStyledAttributes.getResourceId(C1435R.styleable.MaterialCalendarDay_itemShapeAppearanceOverlay, 0));
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
            materialShapeDrawable2.setShapeAppearanceModel(shapeAppearanceModel);
            obtainStyledAttributes.recycle();
            textView.setTextColor(colorStateList2);
            materialShapeDrawable.setFillColor(colorStateList);
            materialShapeDrawable.setStroke((float) dimensionPixelSize, colorStateList3);
            if (VERSION.SDK_INT >= 21) {
                ViewCompat.setBackground(textView, new RippleDrawable(colorStateList2.withAlpha(30), materialShapeDrawable, materialShapeDrawable2));
            } else {
                ViewCompat.setBackground(textView, materialShapeDrawable);
            }
        }
    }
}
