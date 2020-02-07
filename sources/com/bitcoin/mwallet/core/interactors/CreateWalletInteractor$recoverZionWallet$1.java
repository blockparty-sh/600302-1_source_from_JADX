package com.bitcoin.mwallet.core.interactors;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH@ø\u0001\u0000"}, mo37405d2 = {"recoverZionWallet", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "walletName", "", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "zionWalletName", "Lcom/bitcoin/mwallet/zion/ZionWalletName;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor$CreateWalletResult;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.interactors.CreateWalletInteractor", mo38000f = "CreateWalletInteractor.kt", mo38001i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6}, mo38002l = {97, 100, 102, 105, 110, 115, 116}, mo38003m = "recoverZionWallet", mo38004n = {"this", "coin", "walletName", "zionId", "zionWalletName", "this", "coin", "walletName", "zionId", "zionWalletName", "error", "this", "coin", "walletName", "zionId", "zionWalletName", "error", "this", "coin", "walletName", "zionId", "zionWalletName", "error", "this", "coin", "walletName", "zionId", "zionWalletName", "error", "this", "coin", "walletName", "zionId", "zionWalletName", "error", "zionResponse", "xPub", "wallet", "this", "coin", "walletName", "zionId", "zionWalletName", "error", "zionResponse", "xPub", "wallet"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"})
/* compiled from: CreateWalletInteractor.kt */
final class CreateWalletInteractor$recoverZionWallet$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CreateWalletInteractor this$0;

    CreateWalletInteractor$recoverZionWallet$1(CreateWalletInteractor createWalletInteractor, Continuation continuation) {
        this.this$0 = createWalletInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.recoverZionWallet(null, null, null, null, this);
    }
}
