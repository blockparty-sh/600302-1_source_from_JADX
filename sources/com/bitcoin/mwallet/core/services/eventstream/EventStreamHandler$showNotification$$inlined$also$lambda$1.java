package com.bitcoin.mwallet.core.services.eventstream;

import android.app.Activity;
import android.content.res.Resources;
import android.widget.ImageView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, mo37405d2 = {"<anonymous>", "", "run", "com/bitcoin/mwallet/core/services/eventstream/EventStreamHandler$showNotification$2$handler$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: EventStreamHandler.kt */
final class EventStreamHandler$showNotification$$inlined$also$lambda$1 implements Runnable {
    final /* synthetic */ String $amount$inlined;
    final /* synthetic */ Activity $currentActivity$inlined;
    final /* synthetic */ ImageView $notificationIcon;
    final /* synthetic */ HistoricTransaction $tx$inlined;
    final /* synthetic */ EventStreamHandler this$0;

    EventStreamHandler$showNotification$$inlined$also$lambda$1(ImageView imageView, EventStreamHandler eventStreamHandler, String str, HistoricTransaction historicTransaction, Activity activity) {
        this.$notificationIcon = imageView;
        this.this$0 = eventStreamHandler;
        this.$amount$inlined = str;
        this.$tx$inlined = historicTransaction;
        this.$currentActivity$inlined = activity;
    }

    public final void run() {
        VerifiedTokenInteractor access$getVerifiedTokenInteractor$p = this.this$0.verifiedTokenInteractor;
        Activity activity = this.$currentActivity$inlined;
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Resources resources = activity.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "currentActivity!!.resources");
        ImageView imageView = this.$notificationIcon;
        if (imageView == null) {
            Intrinsics.throwNpe();
        }
        if (!access$getVerifiedTokenInteractor$p.trySetVerifiedTokenImage(resources, imageView, this.$tx$inlined.getTokenId())) {
            this.$notificationIcon.setImageResource(C1018R.C1020drawable.ic_slp_notification);
        }
    }
}
