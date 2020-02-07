package com.bitcoin.mwallet.app.flows.sendv2.review;

import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReviewView.kt */
final class ReviewView$bindObservers$2<T> implements Observer<C1261Wallet> {
    final /* synthetic */ ReviewView this$0;

    ReviewView$bindObservers$2(ReviewView reviewView) {
        this.this$0 = reviewView;
    }

    public final void onChanged(C1261Wallet wallet) {
        if (wallet != null) {
            View view = this.this$0.getView();
            if (view != null) {
                ImageView imageView = (ImageView) view.findViewById(C1018R.C1021id.walletColorDot);
                if (imageView != null) {
                    imageView.setColorFilter(Color.parseColor(wallet.preference().getColor()), Mode.MULTIPLY);
                }
            }
            View view2 = this.this$0.getView();
            if (view2 != null) {
                TextView textView = (TextView) view2.findViewById(C1018R.C1021id.reviewWalletName);
                if (textView != null) {
                    textView.setText(wallet.getName());
                }
            }
        }
    }
}
