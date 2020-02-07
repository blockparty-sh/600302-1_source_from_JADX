package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ScreenBuilderBase;
import com.bitcoin.mwallet.core.interactors.DiscoverContentInteractor;
import com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0002¢\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0002¢\u0006\f\n\u0004\b\u001c\u0010\n\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetBuilder;", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "discoverContentInteractor", "Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;", "getDiscoverContentInteractor", "()Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;", "discoverContentInteractor$delegate", "Lkotlin/Lazy;", "presenter", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetPresenter;", "router", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetRouter;", "getRouter", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SelectSendingAssetRouter;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "getSlpRepository", "()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "slpRepository$delegate", "verifiedTokenInteractor", "Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "getVerifiedTokenInteractor", "()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "verifiedTokenInteractor$delegate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectSendingAssetBuilder.kt */
public final class SelectSendingAssetBuilder extends ScreenBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectSendingAssetBuilder.class), "slpRepository", "getSlpRepository()Lcom/bitcoin/mwallet/core/repositories/SlpRepository;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectSendingAssetBuilder.class), "discoverContentInteractor", "getDiscoverContentInteractor()Lcom/bitcoin/mwallet/core/interactors/DiscoverContentInteractor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SelectSendingAssetBuilder.class), "verifiedTokenInteractor", "getVerifiedTokenInteractor()Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;"))};
    private final Lazy discoverContentInteractor$delegate;
    @NotNull
    private final SelectSendingAssetPresenter presenter;
    @NotNull
    private final SelectSendingAssetRouter router = new SelectSendingAssetRouter();
    private final Lazy slpRepository$delegate;
    private final Lazy verifiedTokenInteractor$delegate;

    private final DiscoverContentInteractor getDiscoverContentInteractor() {
        Lazy lazy = this.discoverContentInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (DiscoverContentInteractor) lazy.getValue();
    }

    private final SlpRepository getSlpRepository() {
        Lazy lazy = this.slpRepository$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (SlpRepository) lazy.getValue();
    }

    private final VerifiedTokenInteractor getVerifiedTokenInteractor() {
        Lazy lazy = this.verifiedTokenInteractor$delegate;
        KProperty kProperty = $$delegatedProperties[2];
        return (VerifiedTokenInteractor) lazy.getValue();
    }

    public SelectSendingAssetBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        Qualifier qualifier = null;
        Function0 function0 = null;
        this.slpRepository$delegate = LazyKt.lazy(new SelectSendingAssetBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), qualifier, function0));
        this.discoverContentInteractor$delegate = LazyKt.lazy(new SelectSendingAssetBuilder$$special$$inlined$inject$2(getKoin().getRootScope(), qualifier, function0));
        this.verifiedTokenInteractor$delegate = LazyKt.lazy(new SelectSendingAssetBuilder$$special$$inlined$inject$3(getKoin().getRootScope(), qualifier, function0));
        SelectSendingAssetPresenter selectSendingAssetPresenter = new SelectSendingAssetPresenter(application, ViewModelKt.getViewModelScope(this), getSlpRepository(), getDiscoverContentInteractor(), getVerifiedTokenInteractor(), getRouter());
        this.presenter = selectSendingAssetPresenter;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public SelectSendingAssetRouter getRouter() {
        return this.router;
    }

    @NotNull
    public SelectSendingAssetPresenter getPresenter() {
        return this.presenter;
    }
}
