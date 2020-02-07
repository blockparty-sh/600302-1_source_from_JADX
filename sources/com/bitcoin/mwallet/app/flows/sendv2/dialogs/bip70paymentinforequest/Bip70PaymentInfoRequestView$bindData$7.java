package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.preferences.WalletPreference;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInfoRequestView.kt */
final class Bip70PaymentInfoRequestView$bindData$7<T> implements Observer<WalletId> {
    final /* synthetic */ Bip70PaymentInfoRequestView this$0;

    Bip70PaymentInfoRequestView$bindData$7(Bip70PaymentInfoRequestView bip70PaymentInfoRequestView) {
        this.this$0 = bip70PaymentInfoRequestView;
    }

    public final void onChanged(@Nullable WalletId walletId) {
        if (walletId != null) {
            C1261Wallet wallet = this.this$0.getPresenter().getWallet(walletId);
            View view = this.this$0.getView();
            String str = null;
            if (view != null) {
                TextView textView = (TextView) view.findViewById(C1018R.C1021id.sendingWalletNameTextView);
                if (textView != null) {
                    textView.setText(wallet != null ? wallet.getName() : null);
                }
            }
            View view2 = this.this$0.getView();
            if (view2 != null) {
                ImageView imageView = (ImageView) view2.findViewById(C1018R.C1021id.walletColorDot);
                if (imageView != null) {
                    if (wallet != null) {
                        WalletPreference preference = wallet.preference();
                        if (preference != null) {
                            str = preference.getColor();
                        }
                    }
                    imageView.setColorFilter(Color.parseColor(str), Mode.MULTIPLY);
                }
            }
            this.this$0.getPresenter().setWalletData(walletId);
        }
    }
}
