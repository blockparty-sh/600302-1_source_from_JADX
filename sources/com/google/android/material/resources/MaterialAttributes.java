package com.google.android.material.resources;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.AttrRes;
import androidx.annotation.C0045Px;
import androidx.annotation.DimenRes;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import com.google.android.material.C1435R;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MaterialAttributes {
    @Nullable
    public static TypedValue resolve(Context context, @AttrRes int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static int resolveOrThrow(Context context, @AttrRes int i, String str) {
        TypedValue resolve = resolve(context, i);
        if (resolve != null) {
            return resolve.data;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", new Object[]{str, context.getResources().getResourceName(i)}));
    }

    public static int resolveOrThrow(View view, @AttrRes int i) {
        return resolveOrThrow(view.getContext(), i, view.getClass().getCanonicalName());
    }

    public static boolean resolveBooleanOrThrow(Context context, @AttrRes int i, String str) {
        return resolveOrThrow(context, i, str) != 0;
    }

    public static boolean resolveBoolean(Context context, @AttrRes int i, boolean z) {
        TypedValue resolve = resolve(context, i);
        if (resolve == null || resolve.type != 18) {
            return z;
        }
        return resolve.data != 0;
    }

    @C0045Px
    public static int resolveMinimumAccessibleTouchTarget(Context context) {
        return resolveDimension(context, C1435R.attr.minTouchTargetSize, C1435R.dimen.mtrl_min_touch_target_size);
    }

    @C0045Px
    public static int resolveDimension(Context context, @AttrRes int i, @DimenRes int i2) {
        float dimension;
        TypedValue resolve = resolve(context, i);
        if (resolve == null || resolve.type != 5) {
            dimension = context.getResources().getDimension(i2);
        } else {
            dimension = resolve.getDimension(context.getResources().getDisplayMetrics());
        }
        return (int) dimension;
    }
}
