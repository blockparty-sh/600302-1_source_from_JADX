package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.StyleableRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TintTypedArray;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MaterialResources {
    private MaterialResources() {
    }

    @Nullable
    public static ColorStateList getColorStateList(Context context, TypedArray typedArray, @StyleableRes int i) {
        if (typedArray.hasValue(i)) {
            int resourceId = typedArray.getResourceId(i, 0);
            if (resourceId != 0) {
                ColorStateList colorStateList = AppCompatResources.getColorStateList(context, resourceId);
                if (colorStateList != null) {
                    return colorStateList;
                }
            }
        }
        if (VERSION.SDK_INT <= 15) {
            int color = typedArray.getColor(i, -1);
            if (color != -1) {
                return ColorStateList.valueOf(color);
            }
        }
        return typedArray.getColorStateList(i);
    }

    @Nullable
    public static ColorStateList getColorStateList(Context context, TintTypedArray tintTypedArray, @StyleableRes int i) {
        if (tintTypedArray.hasValue(i)) {
            int resourceId = tintTypedArray.getResourceId(i, 0);
            if (resourceId != 0) {
                ColorStateList colorStateList = AppCompatResources.getColorStateList(context, resourceId);
                if (colorStateList != null) {
                    return colorStateList;
                }
            }
        }
        if (VERSION.SDK_INT <= 15) {
            int color = tintTypedArray.getColor(i, -1);
            if (color != -1) {
                return ColorStateList.valueOf(color);
            }
        }
        return tintTypedArray.getColorStateList(i);
    }

    @Nullable
    public static Drawable getDrawable(Context context, TypedArray typedArray, @StyleableRes int i) {
        if (typedArray.hasValue(i)) {
            int resourceId = typedArray.getResourceId(i, 0);
            if (resourceId != 0) {
                Drawable drawable = AppCompatResources.getDrawable(context, resourceId);
                if (drawable != null) {
                    return drawable;
                }
            }
        }
        return typedArray.getDrawable(i);
    }

    @Nullable
    public static TextAppearance getTextAppearance(Context context, TypedArray typedArray, @StyleableRes int i) {
        if (typedArray.hasValue(i)) {
            int resourceId = typedArray.getResourceId(i, 0);
            if (resourceId != 0) {
                return new TextAppearance(context, resourceId);
            }
        }
        return null;
    }

    @StyleableRes
    static int getIndexWithValue(TypedArray typedArray, @StyleableRes int i, @StyleableRes int i2) {
        return typedArray.hasValue(i) ? i : i2;
    }
}
