package com.bitcoin.mwallet.app.flows.receive.receiveamountselection;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.interactors.GetDefaultCurrencyInteractor;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "getDefaultCurrencyInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getGetDefaultCurrencyInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;", "getDefaultCurrencyInteractor$delegate", "Lkotlin/Lazy;", "getExchangeRateInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getGetExchangeRateInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;", "getExchangeRateInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/receive/receiveamountselection/ReceiveAmountSelectionRouter;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveAmountSelectionBuilder.kt */
public final class ReceiveAmountSelectionBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReceiveAmountSelectionBuilder.class), "getDefaultCurrencyInteractor", "getGetDefaultCurrencyInteractor()Lcom/bitcoin/mwallet/core/interactors/GetDefaultCurrencyInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReceiveAmountSelectionBuilder.class), "getExchangeRateInteractor", "getGetExchangeRateInteractor()Lcom/bitcoin/mwallet/core/interactors/GetCurrentExchangeRateInteractor;"))};
    private final Lazy getDefaultCurrencyInteractor$delegate;
    private final Lazy getExchangeRateInteractor$delegate;
    @NotNull
    private final ReceiveAmountSelectionPresenter presenter;
    @NotNull
    private final ReceiveAmountSelectionRouter router = new ReceiveAmountSelectionRouter();

    private final GetDefaultCurrencyInteractor getGetDefaultCurrencyInteractor() {
        Lazy lazy = this.getDefaultCurrencyInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetDefaultCurrencyInteractor) lazy.getValue();
    }

    private final GetCurrentExchangeRateInteractor getGetExchangeRateInteractor() {
        Lazy lazy = this.getExchangeRateInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetCurrentExchangeRateInteractor) lazy.getValue();
    }

    public ReceiveAmountSelectionBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.getDefaultCurrencyInteractor$delegate = LazyKt.lazy(new ReceiveAmountSelectionBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.getExchangeRateInteractor$delegate = LazyKt.lazy(new ReceiveAmountSelectionBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        ReceiveAmountSelectionPresenter receiveAmountSelectionPresenter = new ReceiveAmountSelectionPresenter(application, ViewModelKt.getViewModelScope(this), getGetDefaultCurrencyInteractor(), getGetExchangeRateInteractor(), getRouter());
        this.presenter = receiveAmountSelectionPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public ReceiveAmountSelectionRouter getRouter() {
        return this.router;
    }

    @NotNull
    public ReceiveAmountSelectionPresenter getPresenter() {
        return this.presenter;
    }
}
