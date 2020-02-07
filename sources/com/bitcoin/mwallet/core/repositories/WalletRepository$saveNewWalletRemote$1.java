package com.bitcoin.mwallet.core.repositories;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H@ø\u0001\u0000"}, mo37405d2 = {"saveNewWalletRemote", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "saveType", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository$WalletSaveType;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository", mo38000f = "WalletRepository.kt", mo38001i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, mo38002l = {178, 183}, mo38003m = "saveNewWalletRemote", mo38004n = {"this", "walletId", "saveType", "wallet", "this", "walletId", "saveType", "wallet", "credential"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: WalletRepository.kt */
final class WalletRepository$saveNewWalletRemote$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WalletRepository this$0;

    WalletRepository$saveNewWalletRemote$1(WalletRepository walletRepository, Continuation continuation) {
        this.this$0 = walletRepository;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.saveNewWalletRemote(null, null, this);
    }
}
