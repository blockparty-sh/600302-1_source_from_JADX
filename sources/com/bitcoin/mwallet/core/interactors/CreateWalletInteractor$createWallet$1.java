package com.bitcoin.mwallet.core.interactors;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H@ø\u0001\u0000"}, mo37405d2 = {"createWallet", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "walletName", "", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor$CreateWalletResult;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.interactors.CreateWalletInteractor", mo38000f = "CreateWalletInteractor.kt", mo38001i = {0, 0, 0, 1, 1, 1, 2, 2, 2}, mo38002l = {39, 40, 42}, mo38003m = "createWallet", mo38004n = {"this", "coin", "walletName", "this", "coin", "walletName", "this", "coin", "walletName"}, mo38005s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* compiled from: CreateWalletInteractor.kt */
final class CreateWalletInteractor$createWallet$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CreateWalletInteractor this$0;

    CreateWalletInteractor$createWallet$1(CreateWalletInteractor createWalletInteractor, Continuation continuation) {
        this.this$0 = createWalletInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.createWallet(null, null, this);
    }
}
