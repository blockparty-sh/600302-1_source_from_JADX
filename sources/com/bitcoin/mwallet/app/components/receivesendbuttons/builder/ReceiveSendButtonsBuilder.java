package com.bitcoin.mwallet.app.components.receivesendbuttons.builder;

import android.app.Application;
import com.bitcoin.mwallet.app.components.receivesendbuttons.presenter.ReceiveSendButtonsPresenter;
import com.bitcoin.mwallet.app.viper.ComponentBuilderBase;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8FX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/receivesendbuttons/builder/ReceiveSendButtonsBuilder;", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "presenter", "Lcom/bitcoin/mwallet/app/components/receivesendbuttons/presenter/ReceiveSendButtonsPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/receivesendbuttons/presenter/ReceiveSendButtonsPresenter;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "getWalletRepository", "()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "walletRepository$delegate", "Lkotlin/Lazy;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveSendButtonsBuilder.kt */
public final class ReceiveSendButtonsBuilder extends ComponentBuilderBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ReceiveSendButtonsBuilder.class), "walletRepository", "getWalletRepository()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;"))};
    @NotNull
    private final ReceiveSendButtonsPresenter presenter = new ReceiveSendButtonsPresenter(getWalletRepository());
    @NotNull
    private final Lazy walletRepository$delegate = LazyKt.lazy(new ReceiveSendButtonsBuilder$$special$$inlined$inject$1(getKoin().getRootScope(), null, null));

    @NotNull
    public final WalletRepository getWalletRepository() {
        Lazy lazy = this.walletRepository$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (WalletRepository) lazy.getValue();
    }

    public ReceiveSendButtonsBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
    }

    @NotNull
    public ReceiveSendButtonsPresenter getPresenter() {
        return this.presenter;
    }
}
