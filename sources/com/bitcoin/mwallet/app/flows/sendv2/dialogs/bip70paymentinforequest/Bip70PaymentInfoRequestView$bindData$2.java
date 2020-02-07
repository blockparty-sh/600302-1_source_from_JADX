package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import android.view.View;
import android.widget.ProgressBar;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.Bip70PaymentErrorStatus;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "progress", "", "onChanged", "(Ljava/lang/Integer;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInfoRequestView.kt */
final class Bip70PaymentInfoRequestView$bindData$2<T> implements Observer<Integer> {
    final /* synthetic */ Bip70PaymentInfoRequestView this$0;

    Bip70PaymentInfoRequestView$bindData$2(Bip70PaymentInfoRequestView bip70PaymentInfoRequestView) {
        this.this$0 = bip70PaymentInfoRequestView;
    }

    public final void onChanged(@Nullable Integer num) {
        if (num == null) {
            View view = this.this$0.getView();
            if (view != null) {
                ProgressBar progressBar = (ProgressBar) view.findViewById(C1018R.C1021id.expiryTimer);
                if (progressBar != null) {
                    progressBar.setVisibility(4);
                }
            }
        } else if (num.intValue() == 0) {
            this.this$0.getPresenter().get_errorState().postValue(Bip70PaymentErrorStatus.EXPIRED_PAYMENT);
        } else {
            View view2 = this.this$0.getView();
            if (view2 != null) {
                ProgressBar progressBar2 = (ProgressBar) view2.findViewById(C1018R.C1021id.expiryTimer);
                if (progressBar2 != null) {
                    progressBar2.setVisibility(0);
                }
            }
            View view3 = this.this$0.getView();
            if (view3 != null) {
                ProgressBar progressBar3 = (ProgressBar) view3.findViewById(C1018R.C1021id.expiryTimer);
                if (progressBar3 != null) {
                    progressBar3.setProgress(num.intValue());
                }
            }
        }
    }
}
