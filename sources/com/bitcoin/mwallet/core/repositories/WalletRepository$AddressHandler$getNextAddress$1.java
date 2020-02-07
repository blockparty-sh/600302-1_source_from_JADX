package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.repositories.WalletRepository.AddressHandler;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tH@ø\u0001\u0000"}, mo37405d2 = {"getNextAddress", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "network", "Lcom/bitcoin/bitcoink/Network;", "purpose", "Lcom/bitcoin/mwallet/core/models/address/AddressPurpose;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.WalletRepository$AddressHandler", mo38000f = "WalletRepository.kt", mo38001i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, mo38002l = {415, 424}, mo38003m = "getNextAddress", mo38004n = {"this", "walletId", "network", "purpose", "this", "walletId", "network", "purpose", "pathYNext", "nextPathY"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0"})
/* compiled from: WalletRepository.kt */
final class WalletRepository$AddressHandler$getNextAddress$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AddressHandler this$0;

    WalletRepository$AddressHandler$getNextAddress$1(AddressHandler addressHandler, Continuation continuation) {
        this.this$0 = addressHandler;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getNextAddress(null, null, null, this);
    }
}