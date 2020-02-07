package com.bitcoin.mwallet.core.repositories;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH@ø\u0001\u0000"}, mo37405d2 = {"saveNewWalletRemote", "", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "credential", "Lcom/bitcoin/mwallet/core/models/credential/Credential;", "saveType", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository$WalletSaveType;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository", mo38000f = "WalletRepository.kt", mo38001i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4}, mo38002l = {202, 209, 219, 255, 264}, mo38003m = "saveNewWalletRemote", mo38004n = {"this", "wallet", "credential", "saveType", "this", "wallet", "credential", "saveType", "this", "wallet", "credential", "saveType", "localPrefixLength", "oldWalletId", "this", "wallet", "credential", "saveType", "walletResponse", "oldCredentialId", "walletToSave", "credentialToSave", "notificationSwitch", "this", "wallet", "credential", "saveType", "walletResponse", "oldCredentialId", "walletToSave", "credentialToSave", "notificationSwitch"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0"})
/* compiled from: WalletRepository.kt */
final class WalletRepository$saveNewWalletRemote$2 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WalletRepository this$0;

    WalletRepository$saveNewWalletRemote$2(WalletRepository walletRepository, Continuation continuation) {
        this.this$0 = walletRepository;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.saveNewWalletRemote(null, null, null, this);
    }
}
