package com.bitcoin.mwallet.app.flows.discover.discover;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "getAnalyticsService", "()Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "analyticsService$delegate", "Lkotlin/Lazy;", "discoverInteractor", "Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;", "getDiscoverInteractor", "()Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;", "discoverInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverRouter;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DiscoverBuilder.kt */
public final class DiscoverBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DiscoverBuilder.class), "discoverInteractor", "getDiscoverInteractor()Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DiscoverBuilder.class), "analyticsService", "getAnalyticsService()Lcom/bitcoin/mwallet/core/services/AnalyticsService;"))};
    @NotNull
    private final Lazy analyticsService$delegate;
    @NotNull
    private final Lazy discoverInteractor$delegate;
    @NotNull
    private final DiscoverPresenter presenter;
    @NotNull
    private final DiscoverRouter router = new DiscoverRouter();

    @NotNull
    public final AnalyticsService getAnalyticsService() {
        Lazy lazy = this.analyticsService$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (AnalyticsService) lazy.getValue();
    }

    @NotNull
    public final DiscoverContentInteractor getDiscoverInteractor() {
        Lazy lazy = this.discoverInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (DiscoverContentInteractor) lazy.getValue();
    }

    public DiscoverBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.discoverInteractor$delegate = LazyKt.lazy(new DiscoverBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.analyticsService$delegate = LazyKt.lazy(new DiscoverBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        DiscoverPresenter discoverPresenter = new DiscoverPresenter(application, getDiscoverInteractor(), getAnalyticsService(), ViewModelKt.getViewModelScope(this), getRouter());
        this.presenter = discoverPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public DiscoverRouter getRouter() {
        return this.router;
    }

    @NotNull
    public DiscoverPresenter getPresenter() {
        return this.presenter;
    }
}
