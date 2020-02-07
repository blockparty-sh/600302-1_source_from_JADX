package com.bitcoin.mwallet.app.flows.walletdetails.walletsettings;

import android.text.Editable;
import android.text.TextWatcher;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000;\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J*\u0010\r\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016¨\u0006\u000f¸\u0006\u0000"}, mo37405d2 = {"androidx/core/widget/TextViewKt$addTextChangedListener$textWatcher$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "text", "", "start", "", "count", "after", "onTextChanged", "before", "core-ktx_release"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TextView.kt */
public final class WalletSettingsView$bindActions$$inlined$addTextChangedListener$2 implements TextWatcher {
    final /* synthetic */ WalletSettingsView this$0;

    public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
    }

    public WalletSettingsView$bindActions$$inlined$addTextChangedListener$2(WalletSettingsView walletSettingsView) {
        this.this$0 = walletSettingsView;
    }

    public void afterTextChanged(@Nullable Editable editable) {
        Regex regex = new Regex("[A-Za-z]");
        String valueOf = String.valueOf(editable);
        String symbol = ((WalletSettingsPresenter) this.this$0.getPresenter()).getDefaultCurrency().getSymbol();
        Intrinsics.checkExpressionValueIsNotNull(symbol, "presenter.getDefaultCurrency().symbol");
        String replace = regex.replace((CharSequence) StringsKt.replace$default(valueOf, symbol, "", false, 4, (Object) null), "");
        NumberFormat instance = NumberFormat.getInstance();
        if (instance != null) {
            DecimalFormat decimalFormat = (DecimalFormat) instance;
            boolean z = true;
            decimalFormat.setParseBigDecimal(true);
            String str = "null cannot be cast to non-null type kotlin.CharSequence";
            if (replace != null) {
                CharSequence charSequence = replace;
                if (StringsKt.trim(charSequence).toString().length() != 0) {
                    z = false;
                }
                if (z) {
                    WalletSettingsPresenter walletSettingsPresenter = (WalletSettingsPresenter) this.this$0.getPresenter();
                    BigDecimal bigDecimal = BigDecimal.ZERO;
                    Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "BigDecimal.ZERO");
                    walletSettingsPresenter.updateWalletSpendingThreshold(bigDecimal);
                } else if (replace != null) {
                    Number parse = decimalFormat.parse(StringsKt.trim(charSequence).toString());
                    if (parse != null) {
                        ((WalletSettingsPresenter) this.this$0.getPresenter()).updateWalletSpendingThreshold((BigDecimal) parse);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type java.math.BigDecimal");
                } else {
                    throw new TypeCastException(str);
                }
            } else {
                throw new TypeCastException(str);
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type java.text.DecimalFormat");
        }
    }
}
