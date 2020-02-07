package com.cottacush.android.currencyedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import androidx.annotation.RequiresApi;
import com.google.android.material.textfield.TextInputEditText;
import com.leanplum.internal.Constants.Keys;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\r\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\"\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0018\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\b2\b\b\u0002\u0010\u001c\u001a\u00020\u0013J\u000e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\bH\u0007R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, mo37405d2 = {"Lcom/cottacush/android/currencyedittext/CurrencyEditText;", "Lcom/google/android/material/textfield/TextInputEditText;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "currencySymbolPrefix", "", "locale", "Ljava/util/Locale;", "textWatcher", "Lcom/cottacush/android/currencyedittext/CurrencyInputWatcher;", "getNumericValue", "", "()Ljava/lang/Double;", "invalidateTextWatcher", "", "isLollipopAndAbove", "", "onFocusChanged", "focused", "direction", "", "previouslyFocusedRect", "Landroid/graphics/Rect;", "setCurrencySymbol", "currencySymbol", "useCurrencySymbolAsHint", "setLocale", "localeTag", "com.cottacush.CurrencyEditText"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CurrencyEditText.kt */
public final class CurrencyEditText extends TextInputEditText {
    private String currencySymbolPrefix = "";
    private Locale locale;
    private CurrencyInputWatcher textWatcher;

    /* JADX INFO: finally extract failed */
    public CurrencyEditText(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attributeSet);
        Locale locale2 = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale2, "Locale.getDefault()");
        this.locale = locale2;
        setInputType(8194);
        ObjectRef objectRef = new ObjectRef();
        boolean z = false;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C1289R.styleable.CurrencyEditText, 0, 0);
        try {
            String string = obtainStyledAttributes.getString(C1289R.styleable.CurrencyEditText_currencySymbol);
            objectRef.element = obtainStyledAttributes.getString(C1289R.styleable.CurrencyEditText_localeTag);
            boolean z2 = obtainStyledAttributes.getBoolean(C1289R.styleable.CurrencyEditText_useCurrencySymbolAsHint, false);
            obtainStyledAttributes.recycle();
            CharSequence charSequence = string;
            if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
                StringBuilder sb = new StringBuilder();
                sb.append(string);
                sb.append(' ');
                this.currencySymbolPrefix = sb.toString();
            }
            if (z2) {
                setHint(this.currencySymbolPrefix);
            }
            if (isLollipopAndAbove()) {
                CharSequence charSequence2 = (String) objectRef.element;
                if (charSequence2 == null || StringsKt.isBlank(charSequence2)) {
                    z = true;
                }
                if (!z) {
                    Locale forLanguageTag = Locale.forLanguageTag((String) objectRef.element);
                    Intrinsics.checkExpressionValueIsNotNull(forLanguageTag, "Locale.forLanguageTag(localeTag)");
                    this.locale = forLanguageTag;
                }
            }
            this.textWatcher = new CurrencyInputWatcher(this, this.currencySymbolPrefix, this.locale);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void setLocale(@NotNull Locale locale2) {
        Intrinsics.checkParameterIsNotNull(locale2, Keys.LOCALE);
        this.locale = locale2;
        invalidateTextWatcher();
    }

    @RequiresApi(21)
    public final void setLocale(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "localeTag");
        Locale forLanguageTag = Locale.forLanguageTag(str);
        Intrinsics.checkExpressionValueIsNotNull(forLanguageTag, "Locale.forLanguageTag(localeTag)");
        this.locale = forLanguageTag;
        invalidateTextWatcher();
    }

    public static /* synthetic */ void setCurrencySymbol$default(CurrencyEditText currencyEditText, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        currencyEditText.setCurrencySymbol(str, z);
    }

    public final void setCurrencySymbol(@NotNull String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "currencySymbol");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(' ');
        this.currencySymbolPrefix = sb.toString();
        if (z) {
            setHint(this.currencySymbolPrefix);
        }
        invalidateTextWatcher();
    }

    private final void invalidateTextWatcher() {
        removeTextChangedListener(this.textWatcher);
        this.textWatcher = new CurrencyInputWatcher(this, this.currencySymbolPrefix, this.locale);
        addTextChangedListener(this.textWatcher);
    }

    @Nullable
    public final Double getNumericValue() {
        return StringsKt.toDoubleOrNull(UtilsKt.parseMoneyValue(String.valueOf(getText()), String.valueOf(this.textWatcher.getDecimalFormatSymbols().getGroupingSeparator()), this.currencySymbolPrefix));
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, @Nullable Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z) {
            removeTextChangedListener(this.textWatcher);
            addTextChangedListener(this.textWatcher);
            if (String.valueOf(getText()).length() == 0) {
                setText(this.currencySymbolPrefix);
                return;
            }
            return;
        }
        removeTextChangedListener(this.textWatcher);
        if (Intrinsics.areEqual((Object) String.valueOf(getText()), (Object) this.currencySymbolPrefix)) {
            setText("");
        }
    }

    private final boolean isLollipopAndAbove() {
        return VERSION.SDK_INT >= 21;
    }
}
