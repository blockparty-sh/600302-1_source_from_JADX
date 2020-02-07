package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWalletsFileSystemSource;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor;
import com.bitcoin.mwallet.core.repositories.UserRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.notification.NotificationService;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\n\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001f\u001a\u00020 8BX\u0002¢\u0006\f\n\u0004\b#\u0010\n\u001a\u0004\b!\u0010\"R\u001b\u0010$\u001a\u00020%8BX\u0002¢\u0006\f\n\u0004\b(\u0010\n\u001a\u0004\b&\u0010'¨\u0006)"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "createWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;", "getCreateWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;", "createWalletInteractor$delegate", "Lkotlin/Lazy;", "interactor", "Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserInteractor;", "notificationService", "Lcom/bitcoin/mwallet/core/services/notification/NotificationService;", "getNotificationService", "()Lcom/bitcoin/mwallet/core/services/notification/NotificationService;", "notificationService$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserRouter;", "userService", "Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "getUserService", "()Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "userService$delegate", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "getWalletRepository", "()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "walletRepository$delegate", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "getZionRepository", "()Lcom/bitcoin/mwallet/zion/ZionRepository;", "zionRepository$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CreateUserBuilder.kt */
public final class CreateUserBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CreateUserBuilder.class), "userService", "getUserService()Lcom/bitcoin/mwallet/core/repositories/UserRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CreateUserBuilder.class), "notificationService", "getNotificationService()Lcom/bitcoin/mwallet/core/services/notification/NotificationService;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CreateUserBuilder.class), "createWalletInteractor", "getCreateWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CreateUserBuilder.class), "zionRepository", "getZionRepository()Lcom/bitcoin/mwallet/zion/ZionRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CreateUserBuilder.class), "walletRepository", "getWalletRepository()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;"))};
    private final Lazy createWalletInteractor$delegate;
    private final CreateUserInteractor interactor;
    private final Lazy notificationService$delegate;
    @NotNull
    private final CreateUserPresenter presenter;
    @NotNull
    private final CreateUserRouter router = new CreateUserRouter();
    private final Lazy userService$delegate;
    private final Lazy walletRepository$delegate;
    private final Lazy zionRepository$delegate;

    private final CreateWalletInteractor getCreateWalletInteractor() {
        Lazy lazy = this.createWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (CreateWalletInteractor) lazy.getValue();
    }

    private final NotificationService getNotificationService() {
        Lazy lazy = this.notificationService$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (NotificationService) lazy.getValue();
    }

    private final UserRepository getUserService() {
        Lazy lazy = this.userService$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (UserRepository) lazy.getValue();
    }

    private final WalletRepository getWalletRepository() {
        Lazy lazy = this.walletRepository$delegate;
        KProperty kProperty = $$delegatedProperties[4];
        return (WalletRepository) lazy.getValue();
    }

    private final ZionRepository getZionRepository() {
        Lazy lazy = this.zionRepository$delegate;
        KProperty kProperty = $$delegatedProperties[3];
        return (ZionRepository) lazy.getValue();
    }

    public CreateUserBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.userService$delegate = LazyKt.lazy(new CreateUserBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.notificationService$delegate = LazyKt.lazy(new CreateUserBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.createWalletInteractor$delegate = LazyKt.lazy(new CreateUserBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        this.zionRepository$delegate = LazyKt.lazy(new CreateUserBuilder$$special$$inlined$inject$4(getKoin().getRootScope(), qualifier, function0));
        this.walletRepository$delegate = LazyKt.lazy(new CreateUserBuilder$$special$$inlined$inject$5(getKoin().getRootScope(), qualifier, function0));
        UserRepository userService = getUserService();
        ZionRepository zionRepository = getZionRepository();
        CreateWalletInteractor createWalletInteractor = getCreateWalletInteractor();
        Context applicationContext = application.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "application.applicationContext");
        CreateUserInteractor createUserInteractor = new CreateUserInteractor(userService, zionRepository, createWalletInteractor, new OldWalletsFileSystemSource(applicationContext), getWalletRepository(), getNotificationService());
        this.interactor = createUserInteractor;
        CreateUserPresenter createUserPresenter = new CreateUserPresenter(application, ViewModelKt.getViewModelScope(this), getUserService(), this.interactor, getRouter());
        this.presenter = createUserPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public CreateUserRouter getRouter() {
        return this.router;
    }

    @NotNull
    public CreateUserPresenter getPresenter() {
        return this.presenter;
    }
}
