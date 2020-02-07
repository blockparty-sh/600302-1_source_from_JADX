package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory.TransactionHistoryView.WhenMappings;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: TransactionHistoryView.kt */
final class TransactionHistoryView$onActivityCreated$3 implements OnClickListener {
    final /* synthetic */ TransactionHistoryView this$0;

    TransactionHistoryView$onActivityCreated$3(TransactionHistoryView transactionHistoryView) {
        this.this$0 = transactionHistoryView;
    }

    public final void onClick(View view) {
        C1261Wallet wallet = TransactionHistoryView.access$getPresenter$p(this.this$0).getWallet();
        Coin coin = wallet != null ? wallet.getCoin() : null;
        String str = (coin != null && WhenMappings.$EnumSwitchMapping$0[coin.ordinal()] == 1) ? "btc" : "bch";
        Coin.BCH.name();
        StringBuilder sb = new StringBuilder();
        sb.append("https://buy.bitcoin.com/wallet/");
        sb.append(str);
        this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sb.toString())));
    }
}
