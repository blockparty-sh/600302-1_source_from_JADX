package com.bitcoin.mwallet.app.components.contacts.router;

import android.app.Activity;
import com.bitcoin.mwallet.app.viper.RouterBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/contacts/router/ContactsRouter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "onBackPressed", "", "onClosePressed", "activity", "Landroid/app/Activity;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ContactsRouter.kt */
public class ContactsRouter extends RouterBase {
    public final void onBackPressed() {
        getNavController().popBackStack();
    }

    public final void onClosePressed(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        activity.finish();
    }
}
