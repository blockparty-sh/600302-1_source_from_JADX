package com.bitcoin.mwallet.core.services.eventstream;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.bitcoin.mwallet.GlobalTxDetailsActivity;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: EventStreamHandler.kt */
final class EventStreamHandler$showNotification$1 implements OnClickListener {
    final /* synthetic */ Activity $currentActivity;
    final /* synthetic */ HistoricTransaction $tx;

    EventStreamHandler$showNotification$1(Activity activity, HistoricTransaction historicTransaction) {
        this.$currentActivity = activity;
        this.$tx = historicTransaction;
    }

    public final void onClick(View view) {
        Intent intent = new Intent(this.$currentActivity, GlobalTxDetailsActivity.class);
        intent.putExtra("txid", this.$tx.getTxId().getId());
        intent.putExtra("walletId", this.$tx.getWalletId());
        Activity activity = this.$currentActivity;
        if (activity != null) {
            activity.startActivity(intent);
        }
    }
}
