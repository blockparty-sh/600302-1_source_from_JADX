package com.bitcoin.mwallet.app.flows.receive.receiveamountselection;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "inputAmount", "", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveAmountSelectionView.kt */
final class ReceiveAmountSelectionView$bindDataObservers$1<T> implements Observer<String> {
    final /* synthetic */ ReceiveAmountSelectionView this$0;

    ReceiveAmountSelectionView$bindDataObservers$1(ReceiveAmountSelectionView receiveAmountSelectionView) {
        this.this$0 = receiveAmountSelectionView;
    }

    public final void onChanged(String str) {
        View view = this.this$0.getView();
        String str2 = "inputAmount";
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.primaryAmountTextView);
            if (textView != null) {
                ReceiveAmountSelectionPresenter receiveAmountSelectionPresenter = (ReceiveAmountSelectionPresenter) this.this$0.getPresenter();
                Intrinsics.checkExpressionValueIsNotNull(str, str2);
                textView.setText(receiveAmountSelectionPresenter.formatPrimaryDisplayValue(str));
            }
        }
        ((ReceiveAmountSelectionPresenter) this.this$0.getPresenter()).updateSecondaryAmount();
        ((ReceiveAmountSelectionPresenter) this.this$0.getPresenter()).storeBalance();
        View view2 = this.this$0.getView();
        if (view2 != null) {
            Button button = (Button) view2.findViewById(C1018R.C1021id.continueButton);
            if (button != null) {
                ReceiveAmountSelectionPresenter receiveAmountSelectionPresenter2 = (ReceiveAmountSelectionPresenter) this.this$0.getPresenter();
                Intrinsics.checkExpressionValueIsNotNull(str, str2);
                button.setEnabled(receiveAmountSelectionPresenter2.convertToBigDecimal(str).compareTo(BigDecimal.ZERO) > 0);
            }
        }
    }
}
