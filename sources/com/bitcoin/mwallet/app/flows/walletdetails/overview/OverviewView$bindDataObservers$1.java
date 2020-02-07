package com.bitcoin.mwallet.app.flows.walletdetails.overview;

import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.lijiankun24.shadowlayout.ShadowLayout;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: OverviewView.kt */
final class OverviewView$bindDataObservers$1<T> implements Observer<C1261Wallet> {
    final /* synthetic */ OverviewView this$0;

    OverviewView$bindDataObservers$1(OverviewView overviewView) {
        this.this$0 = overviewView;
    }

    public final void onChanged(C1261Wallet wallet) {
        if (wallet != null) {
            View view = this.this$0.getView();
            if (view != null) {
                ImageView imageView = (ImageView) view.findViewById(C1018R.C1021id.walletHeaderColorDot);
                if (imageView != null) {
                    imageView.setColorFilter(Color.parseColor(wallet.preference().getColor()), Mode.MULTIPLY);
                }
            }
            if (wallet.preference().getHasBackedUp()) {
                View view2 = this.this$0.getView();
                if (view2 != null) {
                    ShadowLayout shadowLayout = (ShadowLayout) view2.findViewById(C1018R.C1021id.backupNotice);
                    if (shadowLayout != null) {
                        shadowLayout.setVisibility(8);
                    }
                }
            } else {
                View view3 = this.this$0.getView();
                if (view3 != null) {
                    ShadowLayout shadowLayout2 = (ShadowLayout) view3.findViewById(C1018R.C1021id.backupNotice);
                    if (shadowLayout2 != null) {
                        shadowLayout2.setVisibility(0);
                    }
                }
            }
            if (wallet.preference().getHideBalance()) {
                View view4 = this.this$0.getView();
                if (view4 != null) {
                    TextView textView = (TextView) view4.findViewById(C1018R.C1021id.walletSummaryTextView);
                    if (textView != null) {
                        textView.setVisibility(8);
                    }
                }
                View view5 = this.this$0.getView();
                if (view5 != null) {
                    TextView textView2 = (TextView) view5.findViewById(C1018R.C1021id.walletValueTextView);
                    if (textView2 != null) {
                        textView2.setVisibility(8);
                        return;
                    }
                    return;
                }
                return;
            }
            View view6 = this.this$0.getView();
            if (view6 != null) {
                TextView textView3 = (TextView) view6.findViewById(C1018R.C1021id.walletValueTextView);
                if (textView3 != null) {
                    textView3.setVisibility(0);
                }
            }
            View view7 = this.this$0.getView();
            if (view7 != null) {
                TextView textView4 = (TextView) view7.findViewById(C1018R.C1021id.walletSummaryTextView);
                if (textView4 != null) {
                    textView4.setVisibility(0);
                }
            }
        }
    }
}
