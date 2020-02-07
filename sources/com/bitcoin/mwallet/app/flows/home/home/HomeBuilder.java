package com.bitcoin.mwallet.app.flows.home.home;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWalletsFileSystemSource;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.repositories.UserRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\n\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/home/home/HomeBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "interactor", "Lcom/bitcoin/mwallet/app/flows/home/home/HomeInteractor;", "getInteractor", "()Lcom/bitcoin/mwallet/app/flows/home/home/HomeInteractor;", "presenter", "Lcom/bitcoin/mwallet/app/flows/home/home/HomePresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/home/home/HomePresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/home/home/HomeRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/home/home/HomeRouter;", "userService", "Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "getUserService", "()Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "userService$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: HomeBuilder.kt */
public final class HomeBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(HomeBuilder.class), "userService", "getUserService()Lcom/bitcoin/mwallet/core/repositories/UserRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(HomeBuilder.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;"))};
    private final Lazy analyticsService$delegate;
    @NotNull
    private final HomeInteractor interactor;
    @NotNull
    private final HomePresenter presenter;
    @NotNull
    private final HomeRouter router = new HomeRouter();
    private final Lazy userService$delegate;

    private final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (AnalyticsService) lazy.getValue();
    }

    private final UserRepository getUserService() {
        Lazy lazy = this.userService$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (UserRepository) lazy.getValue();
    }

    public HomeBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.userService$delegate = LazyKt.lazy(new HomeBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new HomeBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        UserRepository userService = getUserService();
        Context applicationContext = application.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "application.applicationContext");
        this.interactor = new HomeInteractor(userService, new OldWalletsFileSystemSource(applicationContext));
        HomePresenter homePresenter = new HomePresenter(application, ViewModelKt.getViewModelScope(this), getAnalyticsService(), getRouter(), this.interactor);
        this.presenter = homePresenter;
    }

    @NotNull
    public final HomeInteractor getInteractor() {
        return this.interactor;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HomeRouter getRouter() {
        return this.router;
    }

    @NotNull
    public HomePresenter getPresenter() {
        return this.presenter;
    }
}
