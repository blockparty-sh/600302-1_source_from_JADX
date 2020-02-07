package com.google.android.material.resources;

import android.graphics.Typeface;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public abstract class TextAppearanceFontCallback {
    public abstract void onFontRetrievalFailed(int i);

    public abstract void onFontRetrieved(Typeface typeface, boolean z);
}
