package com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails;

import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "walletUtxos", "Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: AdvancedDetailsView.kt */
final class AdvancedDetailsView$bindObservers$2<T> implements Observer<WalletUtxos> {
    final /* synthetic */ AdvancedDetailsView this$0;

    AdvancedDetailsView$bindObservers$2(AdvancedDetailsView advancedDetailsView) {
        this.this$0 = advancedDetailsView;
    }

    public final void onChanged(WalletUtxos walletUtxos) {
        LinearLayout walletInputsCell = this.this$0.getWalletInputsCell();
        if (walletInputsCell != null) {
            TextView textView = (TextView) walletInputsCell.findViewById(C1018R.C1021id.advancedWalletSubtitle);
            if (textView != null) {
                textView.setText(String.valueOf(walletUtxos.getUtxos().size()));
            }
        }
    }
}
