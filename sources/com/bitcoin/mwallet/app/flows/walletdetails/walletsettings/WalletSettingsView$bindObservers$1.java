package com.bitcoin.mwallet.app.flows.walletdetails.walletsettings;

import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.cottacush.android.currencyedittext.CurrencyEditText;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletSettingsView.kt */
final class WalletSettingsView$bindObservers$1<T> implements Observer<C1261Wallet> {
    final /* synthetic */ BooleanRef $isInitial;
    final /* synthetic */ WalletSettingsView this$0;

    WalletSettingsView$bindObservers$1(WalletSettingsView walletSettingsView, BooleanRef booleanRef) {
        this.this$0 = walletSettingsView;
        this.$isInitial = booleanRef;
    }

    public final void onChanged(C1261Wallet wallet) {
        if (wallet != null) {
            if (this.$isInitial.element) {
                View view = this.this$0.getView();
                if (view != null) {
                    EditText editText = (EditText) view.findViewById(C1018R.C1021id.walletNameEditText);
                    if (editText != null) {
                        editText.setText(new SpannableStringBuilder(wallet.getName()));
                    }
                }
                int indexOf = this.this$0.getColors().indexOf(Integer.valueOf(Color.parseColor(wallet.preference().getColor())));
                ImageView imageView = (ImageView) this.this$0.getDots().get(indexOf);
                if (imageView != null) {
                    imageView.setImageResource(C1018R.C1020drawable.ic_colorselected);
                }
                ImageView imageView2 = (ImageView) this.this$0.getDots().get(indexOf);
                if (imageView2 != null) {
                    imageView2.setColorFilter(((Number) this.this$0.getColors().get(indexOf)).intValue(), Mode.MULTIPLY);
                }
            }
            this.$isInitial.element = false;
            View view2 = this.this$0.getView();
            if (view2 != null) {
                TextView textView = (TextView) view2.findViewById(C1018R.C1021id.walletTitleTextView);
                if (textView != null) {
                    textView.setText(wallet.getName());
                }
            }
            View view3 = this.this$0.getView();
            String str = null;
            CurrencyEditText currencyEditText = view3 != null ? (CurrencyEditText) view3.findViewById(C1018R.C1021id.autoSpendThreshold) : null;
            if (currencyEditText != null) {
                String currencyCode = ((WalletSettingsPresenter) this.this$0.getPresenter()).getDefaultCurrency().getCurrencyCode();
                Intrinsics.checkExpressionValueIsNotNull(currencyCode, "presenter.getDefaultCurrency().currencyCode");
                currencyEditText.setLocale(currencyCode);
            }
            if (currencyEditText != null) {
                String symbol = ((WalletSettingsPresenter) this.this$0.getPresenter()).getDefaultCurrency().getSymbol();
                Intrinsics.checkExpressionValueIsNotNull(symbol, "presenter.getDefaultCurrency().symbol");
                currencyEditText.setCurrencySymbol(symbol, true);
            }
            if (currencyEditText != null) {
                BigDecimal walletSpendingThreshold = ((WalletSettingsPresenter) this.this$0.getPresenter()).getWalletSpendingThreshold();
                if (walletSpendingThreshold != null) {
                    str = BigDecimalKt.toPlainFiatString(walletSpendingThreshold);
                }
                currencyEditText.setText(str);
            }
            View view4 = this.this$0.getView();
            if (view4 != null) {
                ImageView imageView3 = (ImageView) view4.findViewById(C1018R.C1021id.walletHeaderColorDot);
                if (imageView3 != null) {
                    imageView3.setColorFilter(Color.parseColor(wallet.preference().getColor()), Mode.MULTIPLY);
                }
            }
            View view5 = this.this$0.getView();
            if (view5 != null) {
                Switch switchR = (Switch) view5.findViewById(C1018R.C1021id.hideBalancesSwitch);
                if (switchR != null) {
                    switchR.setChecked(wallet.preference().getHideBalance());
                }
            }
            View view6 = this.this$0.getView();
            if (view6 != null) {
                Switch switchR2 = (Switch) view6.findViewById(C1018R.C1021id.requireSpendingAuthSwitch);
                if (switchR2 != null) {
                    switchR2.setChecked(wallet.preference().getHasSpendingAuth());
                }
            }
        }
    }
}
