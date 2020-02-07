package com.bitcoin.mwallet.app.flows.receive.receive;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "receiveAddress", "", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveView.kt */
final class ReceiveView$bindDataObservers$6<T> implements Observer<String> {
    final /* synthetic */ ReceiveView this$0;

    ReceiveView$bindDataObservers$6(ReceiveView receiveView) {
        this.this$0 = receiveView;
    }

    public final void onChanged(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "receiveAddress");
        View view = this.this$0.getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.receiveAddress);
            if (textView != null) {
                textView.setText(str);
            }
        }
    }
}
