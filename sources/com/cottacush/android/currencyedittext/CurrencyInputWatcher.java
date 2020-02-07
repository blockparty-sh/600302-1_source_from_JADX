package com.cottacush.android.currencyedittext;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.leanplum.core.BuildConfig;
import com.leanplum.internal.Constants.Keys;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000  2\u00020\u0001:\u0001 B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0017J(\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0016J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0002J(\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, mo37405d2 = {"Lcom/cottacush/android/currencyedittext/CurrencyInputWatcher;", "Landroid/text/TextWatcher;", "editText", "Landroid/widget/EditText;", "currencySymbol", "", "locale", "Ljava/util/Locale;", "(Landroid/widget/EditText;Ljava/lang/String;Ljava/util/Locale;)V", "decimalFormatSymbols", "Ljava/text/DecimalFormatSymbols;", "getDecimalFormatSymbols", "()Ljava/text/DecimalFormatSymbols;", "fractionDecimalFormat", "Ljava/text/DecimalFormat;", "hasDecimalPoint", "", "wholeNumberDecimalFormat", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "getFormatSequenceAfterDecimalSeparator", "number", "onTextChanged", "before", "Companion", "com.cottacush.CurrencyEditText"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CurrencyInputWatcher.kt */
public final class CurrencyInputWatcher implements TextWatcher {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String FRACTION_FORMAT_PATTERN_PREFIX = "#,##0.";
    public static final int MAX_NO_OF_DECIMAL_PLACES = 2;
    private final String currencySymbol;
    private final EditText editText;
    private final DecimalFormat fractionDecimalFormat;
    private boolean hasDecimalPoint;
    private final DecimalFormat wholeNumberDecimalFormat;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, mo37405d2 = {"Lcom/cottacush/android/currencyedittext/CurrencyInputWatcher$Companion;", "", "()V", "FRACTION_FORMAT_PATTERN_PREFIX", "", "MAX_NO_OF_DECIMAL_PLACES", "", "com.cottacush.CurrencyEditText"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: CurrencyInputWatcher.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CurrencyInputWatcher(@NotNull EditText editText2, @NotNull String str, @NotNull Locale locale) {
        Intrinsics.checkParameterIsNotNull(editText2, "editText");
        Intrinsics.checkParameterIsNotNull(str, "currencySymbol");
        Intrinsics.checkParameterIsNotNull(locale, Keys.LOCALE);
        this.editText = editText2;
        this.currencySymbol = str;
        NumberFormat numberInstance = NumberFormat.getNumberInstance(locale);
        String str2 = "null cannot be cast to non-null type java.text.DecimalFormat";
        if (numberInstance != null) {
            DecimalFormat decimalFormat = (DecimalFormat) numberInstance;
            decimalFormat.applyPattern("#,##0");
            this.wholeNumberDecimalFormat = decimalFormat;
            NumberFormat numberInstance2 = NumberFormat.getNumberInstance(locale);
            if (numberInstance2 != null) {
                this.fractionDecimalFormat = (DecimalFormat) numberInstance2;
                return;
            }
            throw new TypeCastException(str2);
        }
        throw new TypeCastException(str2);
    }

    @NotNull
    public final DecimalFormatSymbols getDecimalFormatSymbols() {
        DecimalFormatSymbols decimalFormatSymbols = this.wholeNumberDecimalFormat.getDecimalFormatSymbols();
        Intrinsics.checkExpressionValueIsNotNull(decimalFormatSymbols, "wholeNumberDecimalFormat.decimalFormatSymbols");
        return decimalFormatSymbols;
    }

    public void beforeTextChanged(@NotNull CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(charSequence, BuildConfig.LEANPLUM_PACKAGE_IDENTIFIER);
        this.fractionDecimalFormat.setDecimalSeparatorAlwaysShown(true);
    }

    public void onTextChanged(@NotNull CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(charSequence, BuildConfig.LEANPLUM_PACKAGE_IDENTIFIER);
        this.hasDecimalPoint = StringsKt.contains$default((CharSequence) charSequence.toString(), (CharSequence) String.valueOf(getDecimalFormatSymbols().getDecimalSeparator()), false, 2, (Object) null);
    }

    @SuppressLint({"SetTextI18n"})
    public void afterTextChanged(@NotNull Editable editable) {
        Intrinsics.checkParameterIsNotNull(editable, BuildConfig.LEANPLUM_PACKAGE_IDENTIFIER);
        String obj = editable.toString();
        if (obj.length() < this.currencySymbol.length()) {
            this.editText.setText(this.currencySymbol);
            this.editText.setSelection(this.currencySymbol.length());
        } else if (Intrinsics.areEqual((Object) obj, (Object) this.currencySymbol)) {
            this.editText.setSelection(this.currencySymbol.length());
        } else {
            TextWatcher textWatcher = this;
            this.editText.removeTextChangedListener(textWatcher);
            int length = this.editText.getText().length();
            try {
                String parseMoneyValue = UtilsKt.parseMoneyValue(obj, String.valueOf(getDecimalFormatSymbols().getGroupingSeparator()), this.currencySymbol);
                Number parse = this.fractionDecimalFormat.parse(parseMoneyValue);
                if (parse == null) {
                    Intrinsics.throwNpe();
                }
                int selectionStart = this.editText.getSelectionStart();
                if (this.hasDecimalPoint) {
                    DecimalFormat decimalFormat = this.fractionDecimalFormat;
                    StringBuilder sb = new StringBuilder();
                    sb.append(FRACTION_FORMAT_PATTERN_PREFIX);
                    sb.append(getFormatSequenceAfterDecimalSeparator(parseMoneyValue));
                    decimalFormat.applyPattern(sb.toString());
                    EditText editText2 = this.editText;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(this.currencySymbol);
                    sb2.append(this.fractionDecimalFormat.format(parse));
                    editText2.setText(sb2.toString());
                } else {
                    EditText editText3 = this.editText;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(this.currencySymbol);
                    sb3.append(this.wholeNumberDecimalFormat.format(parse));
                    editText3.setText(sb3.toString());
                }
                int length2 = selectionStart + (this.editText.getText().length() - length);
                if (length2 <= 0 || length2 > this.editText.getText().length()) {
                    this.editText.setSelection(this.editText.getText().length() - 1);
                } else {
                    this.editText.setSelection(length2);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.editText.addTextChangedListener(textWatcher);
        }
    }

    private final String getFormatSequenceAfterDecimalSeparator(String str) {
        return StringsKt.repeat(BuildConfig.BUILD_NUMBER, Math.min((str.length() - StringsKt.indexOf$default((CharSequence) str, getDecimalFormatSymbols().getDecimalSeparator(), 0, false, 6, (Object) null)) - 1, 2));
    }
}
