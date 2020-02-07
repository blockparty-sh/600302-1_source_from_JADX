package com.bitcoin.mwallet.app.flows.receive.receive;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.GetAddressInteractor;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "getWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getGetWalletInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "getWalletInteractor$delegate", "Lkotlin/Lazy;", "nextAddressInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;", "getNextAddressInteractor", "()Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;", "nextAddressInteractor$delegate", "presenter", "Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceivePresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceivePresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/receive/receive/ReceiveRouter;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveBuilder.kt */
public final class ReceiveBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReceiveBuilder.class), "nextAddressInteractor", "getNextAddressInteractor()Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReceiveBuilder.class), "getWalletInteractor", "getGetWalletInteractor()Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;"))};
    private final Lazy getWalletInteractor$delegate;
    private final Lazy nextAddressInteractor$delegate;
    @NotNull
    private final ReceivePresenter presenter;
    @NotNull
    private final ReceiveRouter router = new ReceiveRouter();

    private final GetWalletInteractor getGetWalletInteractor() {
        Lazy lazy = this.getWalletInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (GetWalletInteractor) lazy.getValue();
    }

    private final GetAddressInteractor getNextAddressInteractor() {
        Lazy lazy = this.nextAddressInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (GetAddressInteractor) lazy.getValue();
    }

    public ReceiveBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.nextAddressInteractor$delegate = LazyKt.lazy(new ReceiveBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.getWalletInteractor$delegate = LazyKt.lazy(new ReceiveBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        ReceivePresenter receivePresenter = new ReceivePresenter(application, ViewModelKt.getViewModelScope(this), getNextAddressInteractor(), getGetWalletInteractor(), getRouter());
        this.presenter = receivePresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public ReceiveRouter getRouter() {
        return this.router;
    }

    @NotNull
    public ReceivePresenter getPresenter() {
        return this.presenter;
    }
}
