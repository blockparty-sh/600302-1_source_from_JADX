package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import android.view.View;
import android.widget.Button;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionView.kt */
final class SendAmountSelectionView$bindDataObservers$9<T> implements Observer<Boolean> {
    final /* synthetic */ SendAmountSelectionView this$0;

    SendAmountSelectionView$bindDataObservers$9(SendAmountSelectionView sendAmountSelectionView) {
        this.this$0 = sendAmountSelectionView;
    }

    public final void onChanged(Boolean bool) {
        SendAmountSelectionPresenter sendAmountSelectionPresenter = (SendAmountSelectionPresenter) this.this$0.getPresenter();
        Object value = ((SendAmountSelectionPresenter) this.this$0.getPresenter()).getPrimaryAmount().getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "presenter.primaryAmount.value!!");
        if (sendAmountSelectionPresenter.convertToBigDecimal((String) value).doubleValue() == 0.0d || ((SendAmountSelectionPresenter) this.this$0.getPresenter()).getSelectedWalletId().getValue() == null) {
            View view = this.this$0.getView();
            if (view != null) {
                Button button = (Button) view.findViewById(C1018R.C1021id.continueButton);
                if (button != null) {
                    button.setEnabled(false);
                    return;
                }
                return;
            }
            return;
        }
        View view2 = this.this$0.getView();
        if (view2 != null) {
            Button button2 = (Button) view2.findViewById(C1018R.C1021id.continueButton);
            if (button2 != null) {
                Intrinsics.checkExpressionValueIsNotNull(bool, "it");
                button2.setEnabled(bool.booleanValue());
            }
        }
    }
}
