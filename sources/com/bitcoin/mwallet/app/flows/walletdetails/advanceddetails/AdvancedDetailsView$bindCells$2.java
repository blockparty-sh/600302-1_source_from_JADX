package com.bitcoin.mwallet.app.flows.walletdetails.advanceddetails;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: AdvancedDetailsView.kt */
final class AdvancedDetailsView$bindCells$2 implements OnClickListener {
    final /* synthetic */ AdvancedDetailsView this$0;

    AdvancedDetailsView$bindCells$2(AdvancedDetailsView advancedDetailsView) {
        this.this$0 = advancedDetailsView;
    }

    public final void onClick(View view) {
        CharSequence charSequence;
        AdvancedDetailsPresenter advancedDetailsPresenter = (AdvancedDetailsPresenter) this.this$0.getPresenter();
        LinearLayout pubkeyCell = this.this$0.getPubkeyCell();
        if (pubkeyCell != null) {
            TextView textView = (TextView) pubkeyCell.findViewById(C1018R.C1021id.advancedWalletSubtitle);
            if (textView != null) {
                charSequence = textView.getText();
                advancedDetailsPresenter.addToClipboard(String.valueOf(charSequence));
            }
        }
        charSequence = null;
        advancedDetailsPresenter.addToClipboard(String.valueOf(charSequence));
    }
}
