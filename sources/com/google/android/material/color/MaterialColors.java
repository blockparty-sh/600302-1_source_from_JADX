package com.google.android.material.color;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.resources.MaterialAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MaterialColors {
    public static final float ALPHA_DISABLED = 0.38f;
    public static final float ALPHA_DISABLED_LOW = 0.12f;
    public static final float ALPHA_FULL = 1.0f;
    public static final float ALPHA_LOW = 0.32f;
    public static final float ALPHA_MEDIUM = 0.54f;

    @ColorInt
    public static int getColor(View view, @AttrRes int i) {
        return MaterialAttributes.resolveOrThrow(view, i);
    }

    @ColorInt
    public static int getColor(Context context, @AttrRes int i, String str) {
        return MaterialAttributes.resolveOrThrow(context, i, str);
    }

    @ColorInt
    public static int getColor(View view, @AttrRes int i, @ColorInt int i2) {
        return getColor(view.getContext(), i, i2);
    }

    @ColorInt
    public static int getColor(Context context, @AttrRes int i, @ColorInt int i2) {
        TypedValue resolve = MaterialAttributes.resolve(context, i);
        return resolve != null ? resolve.data : i2;
    }

    @ColorInt
    public static int layer(View view, @AttrRes int i, @AttrRes int i2) {
        return layer(view, i, i2, 1.0f);
    }

    @ColorInt
    public static int layer(View view, @AttrRes int i, @AttrRes int i2, @FloatRange(from = 0.0d, mo626to = 1.0d) float f) {
        return layer(getColor(view, i), getColor(view, i2), f);
    }

    @ColorInt
    public static int layer(@ColorInt int i, @ColorInt int i2, @FloatRange(from = 0.0d, mo626to = 1.0d) float f) {
        return layer(i, ColorUtils.setAlphaComponent(i2, Math.round(((float) Color.alpha(i2)) * f)));
    }

    @ColorInt
    public static int layer(@ColorInt int i, @ColorInt int i2) {
        return ColorUtils.compositeColors(i2, i);
    }

    public static ColorStateList layer(ColorStateList colorStateList, @ColorInt int i, ColorStateList colorStateList2, @ColorInt int i2, int[][] iArr) {
        int i3;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int length = iArr.length;
        while (true) {
            length--;
            if (length < 0) {
                break;
            }
            int[] iArr2 = iArr[length];
            int layer = layer(colorStateList.getColorForState(iArr2, i), colorStateList2.getColorForState(iArr2, i2));
            if (shouldAddColorForState(arrayList, layer, arrayList2, iArr2)) {
                arrayList.add(0, Integer.valueOf(layer));
                arrayList2.add(0, iArr2);
            }
        }
        int size = arrayList.size();
        int[] iArr3 = new int[size];
        int[][] iArr4 = new int[size][];
        for (i3 = 0; i3 < size; i3++) {
            iArr3[i3] = ((Integer) arrayList.get(i3)).intValue();
            iArr4[i3] = (int[]) arrayList2.get(i3);
        }
        return new ColorStateList(iArr4, iArr3);
    }

    private static boolean shouldAddColorForState(List<Integer> list, @ColorInt int i, List<int[]> list2, @Nullable int[] iArr) {
        new HashSet(list);
        boolean z = true;
        if (!list.contains(Integer.valueOf(i))) {
            return true;
        }
        Iterator it = list2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            int[] iArr2 = (int[]) it.next();
            if (StateSet.stateSetMatches(iArr2, iArr)) {
                if (((Integer) list.get(list2.indexOf(iArr2))).intValue() == i) {
                    z = false;
                }
            }
        }
        return z;
    }
}
