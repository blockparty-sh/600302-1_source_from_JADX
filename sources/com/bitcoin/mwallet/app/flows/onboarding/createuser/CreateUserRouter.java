package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.FragmentManager;
import com.bitcoin.mwallet.MainTabbedActivity;
import com.bitcoin.mwallet.app.components.walletmanagement.createwallet.view.CreateWalletDialogView;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view.ImportWalletDialogView;
import com.bitcoin.mwallet.app.viper.RouterBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u0004¨\u0006\u000e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserRouter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "toCreateWallet", "", "fm", "Landroidx/fragment/app/FragmentManager;", "toHome", "context", "Landroid/content/Context;", "toImportExistingWallet", "isZion", "", "toMigration", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CreateUserRouter.kt */
public final class CreateUserRouter extends RouterBase {
    public final void toHome(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intent intent = new Intent(context.getApplicationContext(), MainTabbedActivity.class);
        intent.addFlags(32768);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public final void toMigration() {
        getNavController().navigate(CreateUserViewDirections.Companion.actionCreateUserViewToMigrationView());
    }

    public final void toCreateWallet(@NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        new CreateWalletDialogView(null, 1, null).show(fragmentManager, "");
    }

    public final void toImportExistingWallet(@NotNull FragmentManager fragmentManager, boolean z) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        new ImportWalletDialogView(null).show(fragmentManager, "import_existing_wallet");
    }
}
