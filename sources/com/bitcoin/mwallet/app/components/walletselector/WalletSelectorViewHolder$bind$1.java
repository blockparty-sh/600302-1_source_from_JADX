package com.bitcoin.mwallet.app.components.walletselector;

import android.view.View;
import android.view.View.OnClickListener;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.views.walletsummary.WalletSummaryView;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorViewHolder.kt */
final class WalletSelectorViewHolder$bind$1 implements OnClickListener {
    final /* synthetic */ WalletSummaryView $item;
    final /* synthetic */ WalletSelectorViewHolder this$0;

    WalletSelectorViewHolder$bind$1(WalletSelectorViewHolder walletSelectorViewHolder, WalletSummaryView walletSummaryView) {
        this.this$0 = walletSelectorViewHolder;
        this.$item = walletSummaryView;
    }

    public final void onClick(View view) {
        WalletSelectorListener walletSelectorListener = this.this$0.getWalletSelectorListener();
        WalletId walletId = this.$item.getWalletId();
        WalletType walletType = this.$item.getWalletType();
        if (walletType == null) {
            walletType = WalletType.BCH;
        }
        walletSelectorListener.onWalletSelected(walletId, walletType);
    }
}
