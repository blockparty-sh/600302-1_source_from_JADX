package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001c\u0010\u0004\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00060\u0005H@ø\u0001\u0000"}, mo37405d2 = {"registerZionWallet", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "continuation", "Lkotlin/coroutines/Continuation;", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/zion/ZionId;", "Lcom/bitcoin/mwallet/zion/ZionWalletName;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter", mo38000f = "ImportWalletPresenter.kt", mo38001i = {0, 0, 0, 1, 1, 1, 1}, mo38002l = {134, 136}, mo38003m = "registerZionWallet", mo38004n = {"this", "coin", "coinPrefix", "this", "coin", "coinPrefix", "zionWalletName"}, mo38005s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
/* compiled from: ImportWalletPresenter.kt */
final class ImportWalletPresenter$registerZionWallet$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ImportWalletPresenter this$0;

    ImportWalletPresenter$registerZionWallet$1(ImportWalletPresenter importWalletPresenter, Continuation continuation) {
        this.this$0 = importWalletPresenter;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.registerZionWallet(null, this);
    }
}
