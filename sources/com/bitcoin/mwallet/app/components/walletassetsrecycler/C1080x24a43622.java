package com.bitcoin.mwallet.app.components.walletassetsrecycler;

import android.view.View;
import android.view.View.OnClickListener;
import com.bitcoin.mwallet.app.components.walletassetsrecycler.WalletAssetsRecyclerAdapter.MultipleWalletsAssetsViewHolder;
import com.bitcoin.mwallet.core.models.CoinBalance;
import kotlin.Metadata;
import kotlin.Pair;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.app.components.walletassetsrecycler.WalletAssetsRecyclerAdapter$MultipleWalletsAssetsViewHolder$bind$3 */
/* compiled from: WalletAssetsRecyclerAdapter.kt */
final class C1080x24a43622 implements OnClickListener {
    final /* synthetic */ Pair $chainAndBalances;
    final /* synthetic */ MultipleWalletsAssetsViewHolder this$0;

    C1080x24a43622(MultipleWalletsAssetsViewHolder multipleWalletsAssetsViewHolder, Pair pair) {
        this.this$0 = multipleWalletsAssetsViewHolder;
        this.$chainAndBalances = pair;
    }

    public final void onClick(View view) {
        this.this$0.collapseExpandDropdown(((CoinBalance) this.$chainAndBalances.getFirst()).getCoin());
    }
}
