package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH@ø\u0001\u0000"}, mo37405d2 = {"recoverZionWallet", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "zionWalletName", "Lcom/bitcoin/mwallet/zion/ZionWalletName;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter", mo38000f = "ImportWalletPresenter.kt", mo38001i = {0, 0, 0, 0}, mo38002l = {198}, mo38003m = "recoverZionWallet", mo38004n = {"this", "coin", "zionId", "zionWalletName"}, mo38005s = {"L$0", "L$1", "L$2", "L$3"})
/* compiled from: ImportWalletPresenter.kt */
final class ImportWalletPresenter$recoverZionWallet$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ImportWalletPresenter this$0;

    ImportWalletPresenter$recoverZionWallet$1(ImportWalletPresenter importWalletPresenter, Continuation continuation) {
        this.this$0 = importWalletPresenter;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.recoverZionWallet(null, null, null, this);
    }
}
