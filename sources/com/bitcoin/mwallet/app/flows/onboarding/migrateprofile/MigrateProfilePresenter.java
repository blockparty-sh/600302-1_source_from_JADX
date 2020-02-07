package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.MigrationStatus.Migration;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet.OldWalletsJsonSource;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWalletsFileSystemSource;
import com.bitcoin.mwallet.app.viper.PresenterCloseOnBackHandler;
import com.bitcoin.mwallet.app.viper.PresenterWithCloseOnBackHandler;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B%\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0002\u0010\u000bJ\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020%R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\r¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u0006'"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfilePresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileRouter;", "Lcom/bitcoin/mwallet/app/viper/PresenterWithCloseOnBackHandler;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "interactor", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileInteractor;", "router", "(Lkotlinx/coroutines/CoroutineScope;Landroid/content/Context;Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileInteractor;Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileRouter;)V", "_migrationStatus", "Landroidx/lifecycle/MutableLiveData;", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/MigrationStatus;", "backHandler", "Lcom/bitcoin/mwallet/app/viper/PresenterCloseOnBackHandler;", "getBackHandler", "()Lcom/bitcoin/mwallet/app/viper/PresenterCloseOnBackHandler;", "getInteractor", "()Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/MigrateProfileInteractor;", "migrationComplete", "", "getMigrationComplete", "()Landroidx/lifecycle/MutableLiveData;", "migrationState", "migrationStatus", "Landroidx/lifecycle/LiveData;", "getMigrationStatus", "()Landroidx/lifecycle/LiveData;", "oldWallet", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$OldWalletsJsonSource;", "getOldWallet", "()Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$OldWalletsJsonSource;", "getViewModelScope", "()Lkotlinx/coroutines/CoroutineScope;", "beginMigration", "", "goToHome", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: MigrateProfilePresenter.kt */
public final class MigrateProfilePresenter extends ScreenPresenter<MigrateProfileRouter> implements PresenterWithCloseOnBackHandler {
    /* access modifiers changed from: private */
    public final MutableLiveData<MigrationStatus> _migrationStatus;
    @NotNull
    private final PresenterCloseOnBackHandler backHandler = new PresenterCloseOnBackHandler();
    /* access modifiers changed from: private */
    public final Context context;
    @NotNull
    private final MigrateProfileInteractor interactor;
    @NotNull
    private final MutableLiveData<Boolean> migrationComplete = new MutableLiveData<>(Boolean.valueOf(false));
    /* access modifiers changed from: private */
    public final MigrationStatus migrationState;
    @NotNull
    private final LiveData<MigrationStatus> migrationStatus;
    @NotNull
    private final OldWalletsJsonSource oldWallet = new OldWalletsFileSystemSource(this.context);
    @NotNull
    private final CoroutineScope viewModelScope;

    public static final /* synthetic */ MigrateProfileRouter access$getRouter$p(MigrateProfilePresenter migrateProfilePresenter) {
        return (MigrateProfileRouter) migrateProfilePresenter.getRouter();
    }

    @NotNull
    public final CoroutineScope getViewModelScope() {
        return this.viewModelScope;
    }

    @NotNull
    public final MigrateProfileInteractor getInteractor() {
        return this.interactor;
    }

    public MigrateProfilePresenter(@NotNull CoroutineScope coroutineScope, @NotNull Context context2, @NotNull MigrateProfileInteractor migrateProfileInteractor, @NotNull MigrateProfileRouter migrateProfileRouter) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(migrateProfileInteractor, "interactor");
        Intrinsics.checkParameterIsNotNull(migrateProfileRouter, "router");
        super(context2, migrateProfileRouter);
        this.viewModelScope = coroutineScope;
        this.context = context2;
        this.interactor = migrateProfileInteractor;
        Migration migration = Migration.NOT_STARTED;
        String string = this.context.getString(C1018R.string.migration_migrating);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.migration_migrating)");
        this.migrationState = new MigrationStatus(migration, string);
        this._migrationStatus = new MutableLiveData<>(this.migrationState);
        this.migrationStatus = this._migrationStatus;
    }

    @NotNull
    public PresenterCloseOnBackHandler getBackHandler() {
        return this.backHandler;
    }

    @NotNull
    public final OldWalletsJsonSource getOldWallet() {
        return this.oldWallet;
    }

    @NotNull
    public final MutableLiveData<Boolean> getMigrationComplete() {
        return this.migrationComplete;
    }

    @NotNull
    public final LiveData<MigrationStatus> getMigrationStatus() {
        return this.migrationStatus;
    }

    public final void beginMigration() {
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new MigrateProfilePresenter$beginMigration$1(this, null), 3, null);
    }

    public final void goToHome() {
        ((MigrateProfileRouter) getRouter()).toHome(this.context);
    }
}
