package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.builder;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter;
import com.bitcoin.mwallet.app.viper.ComponentBuilderBase;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor;
import com.bitcoin.mwallet.core.repositories.UserRepository;
import com.bitcoin.mwallet.zion.ZionRepository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.koin.core.qualifier.Qualifier;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0002¢\u0006\f\n\u0004\b\u0018\u0010\n\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/builder/ImportWalletBuilder;", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "createWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;", "getCreateWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;", "createWalletInteractor$delegate", "Lkotlin/Lazy;", "presenter", "Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/presenter/ImportWalletPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/presenter/ImportWalletPresenter;", "userService", "Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "getUserService", "()Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "userService$delegate", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "getZionRepository", "()Lcom/bitcoin/mwallet/zion/ZionRepository;", "zionRepository$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ImportWalletBuilder.kt */
public final class ImportWalletBuilder extends ComponentBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ImportWalletBuilder.class), "userService", "getUserService()Lcom/bitcoin/mwallet/core/repositories/UserRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ImportWalletBuilder.class), "createWalletInteractor", "getCreateWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ImportWalletBuilder.class), "zionRepository", "getZionRepository()Lcom/bitcoin/mwallet/zion/ZionRepository;"))};
    private final Lazy createWalletInteractor$delegate;
    @NotNull
    private final ImportWalletPresenter presenter;
    private final Lazy userService$delegate;
    private final Lazy zionRepository$delegate;

    private final CreateWalletInteractor getCreateWalletInteractor() {
        Lazy lazy = this.createWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (CreateWalletInteractor) lazy.getValue();
    }

    private final UserRepository getUserService() {
        Lazy lazy = this.userService$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (UserRepository) lazy.getValue();
    }

    private final ZionRepository getZionRepository() {
        Lazy lazy = this.zionRepository$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (ZionRepository) lazy.getValue();
    }

    public ImportWalletBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.userService$delegate = LazyKt.lazy(new ImportWalletBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.createWalletInteractor$delegate = LazyKt.lazy(new ImportWalletBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.zionRepository$delegate = LazyKt.lazy(new ImportWalletBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        ImportWalletPresenter importWalletPresenter = new ImportWalletPresenter(application, ViewModelKt.getViewModelScope(this), getCreateWalletInteractor(), getUserService(), getZionRepository());
        this.presenter = importWalletPresenter;
    }

    @NotNull
    public ImportWalletPresenter getPresenter() {
        return this.presenter;
    }
}
