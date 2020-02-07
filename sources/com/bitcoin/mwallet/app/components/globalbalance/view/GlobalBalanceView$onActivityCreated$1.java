package com.bitcoin.mwallet.app.components.globalbalance.view;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: GlobalBalanceView.kt */
final class GlobalBalanceView$onActivityCreated$1 implements OnClickListener {
    final /* synthetic */ GlobalBalanceView this$0;

    GlobalBalanceView$onActivityCreated$1(GlobalBalanceView globalBalanceView) {
        this.this$0 = globalBalanceView;
    }

    public final void onClick(View view) {
        GlobalBalanceView.access$getPresenter$p(this.this$0).addBuyAnalytics();
        this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://buy.bitcoin.com/wallet/")));
    }
}
