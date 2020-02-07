package com.bitcoin.mwallet.app.flows.walletdetails.overview;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.TypeCastException;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: OverviewView.kt */
final class OverviewView$bindActions$2 implements OnClickListener {
    final /* synthetic */ OverviewView this$0;

    OverviewView$bindActions$2(OverviewView overviewView) {
        this.this$0 = overviewView;
    }

    public final void onClick(View view) {
        OverviewPresenter overviewPresenter = (OverviewPresenter) this.this$0.getPresenter();
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            overviewPresenter.toReceive(activity);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }
}
