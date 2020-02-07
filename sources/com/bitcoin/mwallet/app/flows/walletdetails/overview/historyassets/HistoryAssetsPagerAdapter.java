package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView;
import com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory.TransactionHistoryView;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0005H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0016\u001a\u00020\u0005H\u0016R\u001a\u0010\u0007\u001a\u00020\bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/HistoryAssetsPagerAdapter;", "Landroidx/fragment/app/FragmentPagerAdapter;", "fm", "Landroidx/fragment/app/FragmentManager;", "behavior", "", "(Landroidx/fragment/app/FragmentManager;I)V", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "setCoin", "(Lcom/bitcoin/mwallet/core/models/Coin;)V", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "getCount", "getItem", "Landroidx/fragment/app/Fragment;", "position", "getPageTitle", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: HistoryAssetsPagerAdapter.kt */
public final class HistoryAssetsPagerAdapter extends FragmentPagerAdapter {
    @NotNull
    public Coin coin;
    @NotNull
    public WalletId walletId;

    public HistoryAssetsPagerAdapter(@NotNull FragmentManager fragmentManager, int i) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        super(fragmentManager, i);
    }

    @NotNull
    public final WalletId getWalletId() {
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return walletId2;
    }

    public final void setWalletId(@NotNull WalletId walletId2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "<set-?>");
        this.walletId = walletId2;
    }

    @NotNull
    public final Coin getCoin() {
        Coin coin2 = this.coin;
        if (coin2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coin");
        }
        return coin2;
    }

    public final void setCoin(@NotNull Coin coin2) {
        Intrinsics.checkParameterIsNotNull(coin2, "<set-?>");
        this.coin = coin2;
    }

    public int getCount() {
        Coin coin2 = this.coin;
        if (coin2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coin");
        }
        return coin2 == Coin.BCH ? 2 : 1;
    }

    @NotNull
    public Fragment getItem(int i) {
        String str = "walletId";
        if (i == 0) {
            TransactionHistoryView transactionHistoryView = new TransactionHistoryView();
            WalletId walletId2 = this.walletId;
            if (walletId2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str);
            }
            transactionHistoryView.setWalletId(walletId2);
            return transactionHistoryView;
        }
        SLPAssetsView sLPAssetsView = new SLPAssetsView();
        WalletId walletId3 = this.walletId;
        if (walletId3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        sLPAssetsView.setWalletId(walletId3);
        return sLPAssetsView;
    }

    @Nullable
    public CharSequence getPageTitle(int i) {
        return i == 0 ? "Transaction History" : "SLP Tokens";
    }
}
