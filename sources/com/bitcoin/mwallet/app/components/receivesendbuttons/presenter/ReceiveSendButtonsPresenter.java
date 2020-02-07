package com.bitcoin.mwallet.app.components.receivesendbuttons.presenter;

import com.bitcoin.mwallet.app.components.receivesendbuttons.view.ReceiveSendButtonsView.OnReceiveSendButtonsListener;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0006R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/receivesendbuttons/presenter/ReceiveSendButtonsPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "(Lcom/bitcoin/mwallet/core/repositories/WalletRepository;)V", "listener", "Lcom/bitcoin/mwallet/app/components/receivesendbuttons/view/ReceiveSendButtonsView$OnReceiveSendButtonsListener;", "oneOrMoreWallets", "", "getOneOrMoreWallets", "()Z", "getWalletRepository", "()Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "onReceive", "", "onSend", "setOnClickListener", "newListener", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveSendButtonsPresenter.kt */
public final class ReceiveSendButtonsPresenter extends PresenterBase {
    private OnReceiveSendButtonsListener listener;
    private final boolean oneOrMoreWallets = ((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new ReceiveSendButtonsPresenter$oneOrMoreWallets$1(this, null), 1, null)).booleanValue();
    @NotNull
    private final WalletRepository walletRepository;

    @NotNull
    public final WalletRepository getWalletRepository() {
        return this.walletRepository;
    }

    public ReceiveSendButtonsPresenter(@NotNull WalletRepository walletRepository2) {
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        this.walletRepository = walletRepository2;
    }

    public final boolean getOneOrMoreWallets() {
        return this.oneOrMoreWallets;
    }

    public final void onReceive() {
        OnReceiveSendButtonsListener onReceiveSendButtonsListener = this.listener;
        if (onReceiveSendButtonsListener != null) {
            onReceiveSendButtonsListener.onReceive();
        }
    }

    public final void onSend() {
        OnReceiveSendButtonsListener onReceiveSendButtonsListener = this.listener;
        if (onReceiveSendButtonsListener != null) {
            onReceiveSendButtonsListener.onSend();
        }
    }

    public final void setOnClickListener(@NotNull OnReceiveSendButtonsListener onReceiveSendButtonsListener) {
        Intrinsics.checkParameterIsNotNull(onReceiveSendButtonsListener, "newListener");
        this.listener = onReceiveSendButtonsListener;
    }
}
