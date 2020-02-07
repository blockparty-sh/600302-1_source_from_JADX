package com.bitcoin.mwallet.app.components.buybitcoinbuttons.view;

import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: BuyBitcoinButtonsView.kt */
final class BuyBitcoinButtonsView$setButtonActions$1 implements OnClickListener {
    final /* synthetic */ BuyBitcoinButtonsView this$0;

    BuyBitcoinButtonsView$setButtonActions$1(BuyBitcoinButtonsView buyBitcoinButtonsView) {
        this.this$0 = buyBitcoinButtonsView;
    }

    public final void onClick(View view) {
        BuyBitcoinButtonsView.access$getPresenter$p(this.this$0).onBuyBchClicked();
    }
}
