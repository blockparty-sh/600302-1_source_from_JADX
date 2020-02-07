package com.bitcoin.mwallet.app.flows.receive.receive;

import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.flows.receive.receive.ReceiveView.WhenMappings;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveView.kt */
final class ReceiveView$bindDataObservers$4<T> implements Observer<WalletId> {
    final /* synthetic */ ReceiveView this$0;

    ReceiveView$bindDataObservers$4(ReceiveView receiveView) {
        this.this$0 = receiveView;
    }

    public final void onChanged(@Nullable WalletId walletId) {
        if (walletId != null) {
            LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(C1018R.C1021id.selectedWalletLayout);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "selectedWalletLayout");
            linearLayout.setVisibility(0);
            C1261Wallet wallet = ((ReceivePresenter) this.this$0.getPresenter()).getWallet(walletId);
            if (wallet != null) {
                int i = WhenMappings.$EnumSwitchMapping$1[wallet.getCoin().ordinal()];
                if (i == 1) {
                    ((ReceivePresenter) this.this$0.getPresenter()).setPrevCashId(walletId);
                } else if (i == 2) {
                    ((ReceivePresenter) this.this$0.getPresenter()).setPrevCoreId(walletId);
                }
                View view = this.this$0.getView();
                if (view != null) {
                    TextView textView = (TextView) view.findViewById(C1018R.C1021id.walletNameTextView);
                    if (textView != null) {
                        textView.setText(wallet.getName());
                    }
                }
                View view2 = this.this$0.getView();
                if (view2 != null) {
                    ImageView imageView = (ImageView) view2.findViewById(C1018R.C1021id.walletColorImageView);
                    if (imageView != null) {
                        imageView.setColorFilter(Color.parseColor(wallet.preference().getColor()), Mode.MULTIPLY);
                    }
                }
                ((ReceivePresenter) this.this$0.getPresenter()).setAddress();
            }
        }
    }
}
