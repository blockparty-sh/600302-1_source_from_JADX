package com.bitcoin.mwallet.core.interactors;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@ø\u0001\u0000"}, mo37405d2 = {"deleteWallet", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor", mo38000f = "ModifyWalletInteractor.kt", mo38001i = {0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8}, mo38002l = {91, 93, 95, 100, 104, 108, 111, 112, 113}, mo38003m = "deleteWallet", mo38004n = {"this", "walletId", "this", "walletId", "credential", "this", "walletId", "credential", "walletIdsWithZionId", "this", "walletId", "credential", "walletIdsWithZionId", "zionError", "this", "walletId", "credential", "this", "walletId", "credential", "wallets", "this", "walletId", "credential", "wallets", "this", "walletId", "credential", "wallets", "this", "walletId", "credential", "wallets"}, mo38005s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
/* compiled from: ModifyWalletInteractor.kt */
final class ModifyWalletInteractor$deleteWallet$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ModifyWalletInteractor this$0;

    ModifyWalletInteractor$deleteWallet$1(ModifyWalletInteractor modifyWalletInteractor, Continuation continuation) {
        this.this$0 = modifyWalletInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.deleteWallet(null, this);
    }
}
