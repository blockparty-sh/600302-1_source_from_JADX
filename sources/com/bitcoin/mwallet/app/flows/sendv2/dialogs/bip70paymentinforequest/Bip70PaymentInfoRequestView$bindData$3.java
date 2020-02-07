package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter.PaymentState;
import com.leanplum.internal.Constants.Params;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "state", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestPresenter$PaymentState;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInfoRequestView.kt */
final class Bip70PaymentInfoRequestView$bindData$3<T> implements Observer<PaymentState> {
    final /* synthetic */ Bip70PaymentInfoRequestView this$0;

    Bip70PaymentInfoRequestView$bindData$3(Bip70PaymentInfoRequestView bip70PaymentInfoRequestView) {
        this.this$0 = bip70PaymentInfoRequestView;
    }

    public final void onChanged(PaymentState paymentState) {
        Bip70PaymentInfoRequestView bip70PaymentInfoRequestView = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(paymentState, Params.STATE);
        bip70PaymentInfoRequestView.setPaymentStateView(paymentState);
        if (paymentState == PaymentState.PAYMENT) {
            this.this$0.setMerchantInformation();
        }
    }
}
