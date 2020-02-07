package com.bitcoin.mwallet.app.flows.sendv2.review;

import android.app.Activity;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel;
import com.bitcoin.mwallet.app.viper.RouterBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0004J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/review/ReviewRouter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "onBackPressed", "", "onClosePressed", "activity", "Landroid/app/Activity;", "toSendingWalletSelect", "toSuccess", "txid", "Lcom/bitcoin/bitcoink/tx/TxId;", "sendWhatData", "Lcom/bitcoin/mwallet/app/flows/sendv2/sendamountselection/SendWhatModel;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReviewRouter.kt */
public final class ReviewRouter extends RouterBase {
    public final void toSendingWalletSelect() {
    }

    public final void toSuccess(@NotNull TxId txId, @NotNull SendWhatModel sendWhatModel) {
        Intrinsics.checkParameterIsNotNull(txId, "txid");
        Intrinsics.checkParameterIsNotNull(sendWhatModel, "sendWhatData");
        getNavController().navigate(ReviewViewDirections.Companion.actionSendReviewToSendSuccess(txId, sendWhatModel));
    }

    public final void onBackPressed() {
        getNavController().popBackStack();
    }

    public final void onClosePressed(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        activity.finish();
    }
}
