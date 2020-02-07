package com.bitcoin.mwallet.app.flows.receive.receiveamountselection;

import com.bitcoin.mwallet.app.components.amountselection.AmountSelectionRouterBase;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionRouter;", "Lcom/bitcoin/mwallet/app/components/amountselection/AmountSelectionRouterBase;", "()V", "backToQrCode", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveAmountSelectionRouter.kt */
public final class ReceiveAmountSelectionRouter extends AmountSelectionRouterBase {
    public final void backToQrCode() {
        getNavController().popBackStack();
    }
}
