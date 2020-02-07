package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "it", "", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionView.kt */
final class SendAmountSelectionView$bindDataObservers$10<T> implements Observer<String> {
    final /* synthetic */ SendAmountSelectionView this$0;

    SendAmountSelectionView$bindDataObservers$10(SendAmountSelectionView sendAmountSelectionView) {
        this.this$0 = sendAmountSelectionView;
    }

    public final void onChanged(@Nullable String str) {
        View view = this.this$0.getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.errorMessage);
            if (textView != null) {
                textView.setText(str);
            }
        }
    }
}
