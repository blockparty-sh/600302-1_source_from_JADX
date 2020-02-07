package com.bitcoin.mwallet.app.components.walletselector;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.views.walletsummary.WalletSummaryView;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "", "summaries", "", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorDialogView.kt */
final class WalletSelectorDialogView$bindObservers$2<T> implements Observer<List<? extends WalletSummaryView>> {
    final /* synthetic */ WalletSelectorAdapter $adapter;

    WalletSelectorDialogView$bindObservers$2(WalletSelectorAdapter walletSelectorAdapter) {
        this.$adapter = walletSelectorAdapter;
    }

    public final void onChanged(List<WalletSummaryView> list) {
        WalletSelectorAdapter walletSelectorAdapter = this.$adapter;
        Intrinsics.checkExpressionValueIsNotNull(list, "summaries");
        walletSelectorAdapter.submitList(CollectionsKt.sortedWith(list, new C1091x52dce5a4()));
    }
}
