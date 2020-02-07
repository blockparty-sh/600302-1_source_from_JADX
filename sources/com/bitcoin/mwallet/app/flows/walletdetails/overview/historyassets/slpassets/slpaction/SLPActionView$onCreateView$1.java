package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.slpaction;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.fragment.app.FragmentActivity;
import com.bitcoin.mwallet.SendV2Activity;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SLPActionView.kt */
final class SLPActionView$onCreateView$1 implements OnClickListener {
    final /* synthetic */ SLPActionView this$0;

    SLPActionView$onCreateView$1(SLPActionView sLPActionView) {
        this.this$0 = sLPActionView;
    }

    public final void onClick(View view) {
        Intent intent = new Intent(this.this$0.getActivity(), SendV2Activity.class);
        intent.putExtra("wallet_id", this.this$0.getWalletId());
        intent.putExtra("token_id", this.this$0.getTokenId());
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            activity.startActivity(intent);
        }
        this.this$0.dismiss();
    }
}
