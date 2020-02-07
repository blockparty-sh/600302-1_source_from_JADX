package com.bitcoin.mwallet.app.flows.home.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.FragmentManager;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.OnBoardingActivity;
import com.bitcoin.mwallet.ReceiveActivity;
import com.bitcoin.mwallet.SendV2Activity;
import com.bitcoin.mwallet.WalletDetailsActivity;
import com.bitcoin.mwallet.app.components.buybitcoinbuttons.view.BuyBitcoinProvider;
import com.bitcoin.mwallet.app.components.walletmanagement.addwallet.view.AddWalletView;
import com.bitcoin.mwallet.app.viper.RouterBase;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u0004J\u0006\u0010\u000f\u001a\u00020\u0004J\u0006\u0010\u0010\u001a\u00020\u0004J\u0006\u0010\u0011\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\rJ\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\rJ\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\rJ\u000e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u001d"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/home/home/HomeRouter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "toAddWallet", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "fm", "Landroidx/fragment/app/FragmentManager;", "toBuyBitcoin", "provider", "Lcom/bitcoin/mwallet/app/components/buybitcoinbuttons/view/BuyBitcoinProvider;", "activityContext", "Landroid/content/Context;", "toContactAdd", "toContactViewAll", "toCreateNewWalletOptions", "toMultiSigDisabled", "toOnBoarding", "context", "toReceive", "toSend", "toTransaction", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "toWallet", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "toWallets", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: HomeRouter.kt */
public final class HomeRouter extends RouterBase {

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[BuyBitcoinProvider.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 1;
            $EnumSwitchMapping$1[BuyBitcoinProvider.WEBSITE.ordinal()] = 1;
            $EnumSwitchMapping$1[BuyBitcoinProvider.WEBVIEW.ordinal()] = 2;
        }
    }

    public final void toContactAdd() {
    }

    public final void toContactViewAll() {
    }

    public final void toCreateNewWalletOptions() {
    }

    public final void toMultiSigDisabled() {
    }

    public final void toTransaction(@NotNull TxId txId) {
        Intrinsics.checkParameterIsNotNull(txId, "txId");
    }

    public final void toWallets(@NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(coin, "coin");
    }

    public final void toBuyBitcoin(@NotNull BuyBitcoinProvider buyBitcoinProvider, @NotNull Coin coin, @NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(buyBitcoinProvider, "provider");
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        Intrinsics.checkParameterIsNotNull(context, "activityContext");
        if (WhenMappings.$EnumSwitchMapping$1[buyBitcoinProvider.ordinal()] == 1) {
            String str = WhenMappings.$EnumSwitchMapping$0[coin.ordinal()] != 1 ? "bch" : "btc";
            StringBuilder sb = new StringBuilder();
            sb.append("https://buy.bitcoin.com/wallet/");
            sb.append(str);
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sb.toString())));
        }
    }

    public final void toReceive(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intent intent = new Intent(context, ReceiveActivity.class);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final void toSend(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intent intent = new Intent(context, SendV2Activity.class);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final void toOnBoarding(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intent intent = new Intent(context.getApplicationContext(), OnBoardingActivity.class);
        intent.addFlags(32768);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final void toWallet(@NotNull Context context, @NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(walletId, "walletId");
        Intent intent = new Intent(context, WalletDetailsActivity.class);
        intent.putExtra("wallet_id", walletId);
        context.startActivity(intent);
    }

    public final void toAddWallet(@NotNull Coin coin, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        new AddWalletView(coin).show(fragmentManager, "add_wallet");
    }
}
