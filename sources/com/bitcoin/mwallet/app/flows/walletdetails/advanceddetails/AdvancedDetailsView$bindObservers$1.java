package com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails;

import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: AdvancedDetailsView.kt */
final class AdvancedDetailsView$bindObservers$1<T> implements Observer<C1261Wallet> {
    final /* synthetic */ AdvancedDetailsView this$0;

    AdvancedDetailsView$bindObservers$1(AdvancedDetailsView advancedDetailsView) {
        this.this$0 = advancedDetailsView;
    }

    public final void onChanged(C1261Wallet wallet) {
        LinearLayout coinTypeCell = this.this$0.getCoinTypeCell();
        if (coinTypeCell != null) {
            TextView textView = (TextView) coinTypeCell.findViewById(C1018R.C1021id.advancedWalletSubtitle);
            if (textView != null) {
                textView.setText(wallet.getCoin().getTicker());
            }
        }
        LinearLayout walletIdCell = this.this$0.getWalletIdCell();
        if (walletIdCell != null) {
            TextView textView2 = (TextView) walletIdCell.findViewById(C1018R.C1021id.advancedWalletSubtitle);
            if (textView2 != null) {
                textView2.setText(wallet.getId().getId());
            }
        }
        LinearLayout configurationCell = this.this$0.getConfigurationCell();
        if (configurationCell != null) {
            TextView textView3 = (TextView) configurationCell.findViewById(C1018R.C1021id.advancedWalletSubtitle);
            if (textView3 != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(wallet.getCopayers().getRequiredSignatures());
                sb.append('-');
                sb.append(wallet.getCopayers().getNumCopayers());
                textView3.setText(sb.toString());
            }
        }
        LinearLayout networkCell = this.this$0.getNetworkCell();
        if (networkCell != null) {
            TextView textView4 = (TextView) networkCell.findViewById(C1018R.C1021id.advancedWalletSubtitle);
            if (textView4 != null) {
                textView4.setText(wallet.getNetwork().name());
            }
        }
        LinearLayout derivationCell = this.this$0.getDerivationCell();
        if (derivationCell != null) {
            TextView textView5 = (TextView) derivationCell.findViewById(C1018R.C1021id.advancedWalletSubtitle);
            if (textView5 != null) {
                textView5.setText(DerivationPath.asString$default(wallet.getPath(), false, 1, null));
            }
        }
        LinearLayout pubkeyCell = this.this$0.getPubkeyCell();
        if (pubkeyCell != null) {
            TextView textView6 = (TextView) pubkeyCell.findViewById(C1018R.C1021id.advancedWalletSubtitle);
            if (textView6 != null) {
                textView6.setText(wallet.getPublicKey().base58(wallet.getNetwork()));
            }
        }
    }
}
