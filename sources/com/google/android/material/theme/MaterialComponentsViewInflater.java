package com.google.android.material.theme;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.radiobutton.MaterialRadioButton;

@Keep
public class MaterialComponentsViewInflater extends AppCompatViewInflater {
    private static int floatingToolbarItemBackgroundResId = -1;

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        if (shouldInflateAppCompatButton(context, attributeSet)) {
            return new AppCompatButton(context, attributeSet);
        }
        return new MaterialButton(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public boolean shouldInflateAppCompatButton(Context context, AttributeSet attributeSet) {
        if (VERSION.SDK_INT != 23 && VERSION.SDK_INT != 24 && VERSION.SDK_INT != 25) {
            return false;
        }
        if (floatingToolbarItemBackgroundResId == -1) {
            floatingToolbarItemBackgroundResId = context.getResources().getIdentifier("floatingToolbarItemBackgroundDrawable", "^attr-private", "android");
        }
        int i = floatingToolbarItemBackgroundResId;
        if (!(i == 0 || i == -1)) {
            for (int i2 = 0; i2 < attributeSet.getAttributeCount(); i2++) {
                if (attributeSet.getAttributeNameResource(i2) == 16842964 && floatingToolbarItemBackgroundResId == attributeSet.getAttributeListValue(i2, null, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatCheckBox createCheckBox(Context context, AttributeSet attributeSet) {
        return new MaterialCheckBox(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public AppCompatRadioButton createRadioButton(Context context, AttributeSet attributeSet) {
        return new MaterialRadioButton(context, attributeSet);
    }
}
