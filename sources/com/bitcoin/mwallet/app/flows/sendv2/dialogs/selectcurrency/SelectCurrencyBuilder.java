package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcurrency;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.repositories.ExchangeRateRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "exchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "exchangeRateInteractor$delegate", "Lkotlin/Lazy;", "exchangeRateRepository", "Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;", "getExchangeRateRepository", "()Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;", "exchangeRateRepository$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyRouter;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectCurrencyBuilder.kt */
public final class SelectCurrencyBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectCurrencyBuilder.class), "exchangeRateInteractor", "getExchangeRateInteractor()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectCurrencyBuilder.class), "exchangeRateRepository", "getExchangeRateRepository()Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;"))};
    private final Lazy exchangeRateInteractor$delegate;
    private final Lazy exchangeRateRepository$delegate;
    @NotNull
    private final SelectCurrencyPresenter presenter;
    @NotNull
    private final SelectCurrencyRouter router = new SelectCurrencyRouter();

    private final GetCurrentExchangeRateInteractor getExchangeRateInteractor() {
        Lazy lazy = this.exchangeRateInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetCurrentExchangeRateInteractor) lazy.getValue();
    }

    private final ExchangeRateRepository getExchangeRateRepository() {
        Lazy lazy = this.exchangeRateRepository$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (ExchangeRateRepository) lazy.getValue();
    }

    public SelectCurrencyBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.exchangeRateInteractor$delegate = LazyKt.lazy(new SelectCurrencyBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.exchangeRateRepository$delegate = LazyKt.lazy(new SelectCurrencyBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        SelectCurrencyPresenter selectCurrencyPresenter = new SelectCurrencyPresenter(application, ViewModelKt.getViewModelScope(this), getExchangeRateInteractor(), getExchangeRateRepository(), getRouter());
        this.presenter = selectCurrencyPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public SelectCurrencyRouter getRouter() {
        return this.router;
    }

    @NotNull
    public SelectCurrencyPresenter getPresenter() {
        return this.presenter;
    }
}
