package com.bitcoin.mwallet.app.flows.receive.receive;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.flows.receive.receive.entity.ReceiveAmount;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "amount", "Lcom/bitcoin/mwallet/app/flows/receive/receive/entity/ReceiveAmount;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveView.kt */
final class ReceiveView$bindDataObservers$1<T> implements Observer<ReceiveAmount> {
    final /* synthetic */ ReceiveView this$0;

    ReceiveView$bindDataObservers$1(ReceiveView receiveView) {
        this.this$0 = receiveView;
    }

    public final void onChanged(ReceiveAmount receiveAmount) {
        if (receiveAmount != null) {
            View view = this.this$0.getView();
            if (view != null) {
                LinearLayout linearLayout = (LinearLayout) view.findViewById(C1018R.C1021id.amountContainer);
                if (linearLayout != null) {
                    linearLayout.setVisibility(0);
                }
            }
            View view2 = this.this$0.getView();
            String str = null;
            if (view2 != null) {
                TextView textView = (TextView) view2.findViewById(C1018R.C1021id.primaryText);
                if (textView != null) {
                    BigDecimal fiat = receiveAmount.getFiat();
                    textView.setText(fiat != null ? BigDecimalKt.toFiatString$default(fiat, receiveAmount.getCurrency(), false, 2, null) : null);
                }
            }
            View view3 = this.this$0.getView();
            if (view3 != null) {
                TextView textView2 = (TextView) view3.findViewById(C1018R.C1021id.secondaryText);
                if (textView2 != null) {
                    BigDecimal crypto = receiveAmount.getCrypto();
                    if (crypto != null) {
                        str = BigDecimalKt.toCoinString(crypto, receiveAmount.getCryptoTicker());
                    }
                    textView2.setText(str);
                    return;
                }
                return;
            }
            return;
        }
        View view4 = this.this$0.getView();
        if (view4 == null) {
            Intrinsics.throwNpe();
        }
        View findViewById = view4.findViewById(C1018R.C1021id.amountContainer);
        if (findViewById == null) {
            Intrinsics.throwNpe();
        }
        ((LinearLayout) findViewById).setVisibility(8);
    }
}
