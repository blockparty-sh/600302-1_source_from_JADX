package com.bitcoin.mwallet.app.flows.settings.currencies;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "currenciesInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getCurrenciesInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "currenciesInteractor$delegate", "Lkotlin/Lazy;", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getGetWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesRouter;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CurrenciesBuilder.kt */
public final class CurrenciesBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CurrenciesBuilder.class), "currenciesInteractor", "getCurrenciesInteractor()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CurrenciesBuilder.class), "getWalletInteractor", "getGetWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;"))};
    @NotNull
    private final Lazy currenciesInteractor$delegate;
    @NotNull
    private final Lazy getWalletInteractor$delegate;
    @NotNull
    private final CurrenciesPresenter presenter;
    @NotNull
    private final CurrenciesRouter router = new CurrenciesRouter();

    @NotNull
    public final GetCurrentExchangeRateInteractor getCurrenciesInteractor() {
        Lazy lazy = this.currenciesInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetCurrentExchangeRateInteractor) lazy.getValue();
    }

    @NotNull
    public final GetWalletInteractor getGetWalletInteractor() {
        Lazy lazy = this.getWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetWalletInteractor) lazy.getValue();
    }

    public CurrenciesBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.currenciesInteractor$delegate = LazyKt.lazy(new CurrenciesBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.getWalletInteractor$delegate = LazyKt.lazy(new CurrenciesBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        CurrenciesPresenter currenciesPresenter = new CurrenciesPresenter(application, ViewModelKt.getViewModelScope(this), getCurrenciesInteractor(), getGetWalletInteractor(), getRouter());
        this.presenter = currenciesPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public CurrenciesRouter getRouter() {
        return this.router;
    }

    @NotNull
    public CurrenciesPresenter getPresenter() {
        return this.presenter;
    }
}
