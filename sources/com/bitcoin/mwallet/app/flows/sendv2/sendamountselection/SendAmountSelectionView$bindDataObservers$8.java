package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "", "enoughBalance", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionView.kt */
final class SendAmountSelectionView$bindDataObservers$8<T> implements Observer<Boolean> {
    final /* synthetic */ SendAmountSelectionView this$0;

    SendAmountSelectionView$bindDataObservers$8(SendAmountSelectionView sendAmountSelectionView) {
        this.this$0 = sendAmountSelectionView;
    }

    public final void onChanged(Boolean bool) {
        View view = this.this$0.getView();
        TextView textView = null;
        TextView textView2 = view != null ? (TextView) view.findViewById(C1018R.C1021id.sendingWalletPrimaryBalance) : null;
        View view2 = this.this$0.getView();
        if (view2 != null) {
            textView = (TextView) view2.findViewById(C1018R.C1021id.sendingWalletSecondaryBalance);
        }
        Intrinsics.checkExpressionValueIsNotNull(bool, "enoughBalance");
        if (bool.booleanValue()) {
            if (textView2 != null) {
                textView2.setTextColor(ContextCompat.getColor(this.this$0.requireContext(), C1018R.C1019color.pitchBlack));
            }
            if (textView != null) {
                textView.setTextColor(ContextCompat.getColor(this.this$0.requireContext(), C1018R.C1019color.pitchBlack));
                return;
            }
            return;
        }
        if (textView2 != null) {
            textView2.setTextColor(ContextCompat.getColor(this.this$0.requireContext(), C1018R.C1019color.warningRed));
        }
        if (textView != null) {
            textView.setTextColor(ContextCompat.getColor(this.this$0.requireContext(), C1018R.C1019color.warningRed));
        }
    }
}
