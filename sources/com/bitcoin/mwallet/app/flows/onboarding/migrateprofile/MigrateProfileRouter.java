package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import android.content.Context;
import android.content.Intent;
import com.bitcoin.mwallet.MainTabbedActivity;
import com.bitcoin.mwallet.app.viper.RouterBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileRouter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "toCreateUser", "", "toHome", "context", "Landroid/content/Context;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MigrateProfileRouter.kt */
public final class MigrateProfileRouter extends RouterBase {
    public final void toCreateUser() {
    }

    public final void toHome(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intent intent = new Intent(context.getApplicationContext(), MainTabbedActivity.class);
        intent.addFlags(32768);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }
}
