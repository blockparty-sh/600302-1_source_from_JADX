package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import java.math.BigDecimal;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "walletInfoBalance", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInfoRequestView.kt */
final class Bip70PaymentInfoRequestView$bindData$4<T> implements Observer<WalletInfoBalance> {
    final /* synthetic */ Bip70PaymentInfoRequestView this$0;

    Bip70PaymentInfoRequestView$bindData$4(Bip70PaymentInfoRequestView bip70PaymentInfoRequestView) {
        this.this$0 = bip70PaymentInfoRequestView;
    }

    public final void onChanged(@Nullable WalletInfoBalance walletInfoBalance) {
        Object obj = null;
        if ((walletInfoBalance != null ? walletInfoBalance.getFiatBalance() : null) != null) {
            View view = this.this$0.getView();
            TextView textView = view != null ? (TextView) view.findViewById(C1018R.C1021id.walletBip70Balance) : null;
            BigDecimal fiatBalance = walletInfoBalance.getFiatBalance();
            if (fiatBalance == null) {
                Intrinsics.throwNpe();
            }
            if (fiatBalance.compareTo(this.this$0.getPresenter().getInvoiceFiatAmount()) < 0) {
                if (textView != null) {
                    textView.setTextColor(ContextCompat.getColor(this.this$0.requireContext(), C1018R.C1019color.warningRed));
                }
                Object value = this.this$0.getPresenter().getValidationError().getValue();
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                if (!((ArrayList) value).contains(Integer.valueOf(C1018R.string.send_error_insufficient_funds))) {
                    Object value2 = this.this$0.getPresenter().getValidationError().getValue();
                    if (value2 == null) {
                        Intrinsics.throwNpe();
                    }
                    ((ArrayList) value2).add(0, Integer.valueOf(C1018R.string.send_error_insufficient_funds));
                    this.this$0.getPresenter().getValidationError().postValue(this.this$0.getPresenter().getValidationError().getValue());
                }
            } else if (this.this$0.getPresenter().isInsufficientFee()) {
                if (textView != null) {
                    textView.setTextColor(ContextCompat.getColor(this.this$0.requireContext(), C1018R.C1019color.warningRed));
                }
                Object value3 = this.this$0.getPresenter().getValidationError().getValue();
                if (value3 == null) {
                    Intrinsics.throwNpe();
                }
                if (!((ArrayList) value3).contains(Integer.valueOf(C1018R.string.send_amount_error_insufficient_fee))) {
                    Object value4 = this.this$0.getPresenter().getValidationError().getValue();
                    if (value4 == null) {
                        Intrinsics.throwNpe();
                    }
                    ((ArrayList) value4).add(Integer.valueOf(C1018R.string.send_amount_error_insufficient_fee));
                    this.this$0.getPresenter().getValidationError().postValue(this.this$0.getPresenter().getValidationError().getValue());
                }
            } else {
                if (textView != null) {
                    textView.setTextColor(ContextCompat.getColor(this.this$0.requireContext(), C1018R.C1019color.lightGray));
                }
                Object value5 = this.this$0.getPresenter().getValidationError().getValue();
                if (value5 == null) {
                    Intrinsics.throwNpe();
                }
                ((ArrayList) value5).clear();
                this.this$0.getPresenter().getValidationError().setValue(this.this$0.getPresenter().getValidationError().getValue());
            }
            if (textView != null) {
                Context context = this.this$0.getContext();
                if (context != null) {
                    Object[] objArr = new Object[2];
                    objArr[0] = walletInfoBalance.getFiatCurrency().getSymbol();
                    BigDecimal fiatBalance2 = walletInfoBalance.getFiatBalance();
                    if (fiatBalance2 != null) {
                        obj = fiatBalance2.setScale(2, 4);
                    }
                    objArr[1] = obj;
                    obj = context.getString(C1018R.string.send_amount_wallet_balance, objArr);
                }
                textView.setText((CharSequence) obj);
            }
        }
    }
}
