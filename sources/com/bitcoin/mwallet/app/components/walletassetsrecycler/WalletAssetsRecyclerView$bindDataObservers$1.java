package com.bitcoin.mwallet.app.components.walletassetsrecycler;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import com.bitcoin.mwallet.core.models.CoinBalance;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012>\u0010\u0002\u001a:\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004 \b*\u001c\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\t"}, mo37405d2 = {"<anonymous>", "", "balances", "", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/CoinBalance;", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletAssetsRecyclerView.kt */
final class WalletAssetsRecyclerView$bindDataObservers$1<T> implements Observer<List<Pair<? extends CoinBalance, ? extends List<? extends WalletInfoBalance>>>> {
    final /* synthetic */ RecyclerView $recycler;

    WalletAssetsRecyclerView$bindDataObservers$1(RecyclerView recyclerView) {
        this.$recycler = recyclerView;
    }

    public final void onChanged(List<Pair<CoinBalance, List<WalletInfoBalance>>> list) {
        RecyclerView recyclerView = this.$recycler;
        Adapter adapter = recyclerView != null ? recyclerView.getAdapter() : null;
        if (adapter != null) {
            WalletAssetsRecyclerAdapter walletAssetsRecyclerAdapter = (WalletAssetsRecyclerAdapter) adapter;
            Intrinsics.checkExpressionValueIsNotNull(list, "balances");
            walletAssetsRecyclerAdapter.submitNewData(list);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.app.components.walletassetsrecycler.WalletAssetsRecyclerAdapter");
    }
}
