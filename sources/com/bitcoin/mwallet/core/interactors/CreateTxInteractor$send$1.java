package com.bitcoin.mwallet.core.interactors;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH@ø\u0001\u0000"}, mo37405d2 = {"send", "", "walletData", "Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;", "utxoSelection", "Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "slpUtxoSelection", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;", "toAddress", "Lcom/bitcoin/bitcoink/address/Address;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.interactors.CreateTxInteractor", mo38000f = "CreateTxInteractor.kt", mo38001i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4}, mo38002l = {99, 100, 104, 105, 122}, mo38003m = "send", mo38004n = {"this", "walletData", "utxoSelection", "slpUtxoSelection", "toAddress", "this", "walletData", "utxoSelection", "slpUtxoSelection", "toAddress", "this", "walletData", "utxoSelection", "slpUtxoSelection", "toAddress", "changeAddress", "slpChangeAddress", "this", "walletData", "utxoSelection", "slpUtxoSelection", "toAddress", "changeAddress", "slpChangeAddress", "this", "walletData", "utxoSelection", "slpUtxoSelection", "toAddress", "changeAddress", "slpChangeAddress", "sendRequest"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"})
/* compiled from: CreateTxInteractor.kt */
final class CreateTxInteractor$send$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CreateTxInteractor this$0;

    CreateTxInteractor$send$1(CreateTxInteractor createTxInteractor, Continuation continuation) {
        this.this$0 = createTxInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.send(null, null, null, null, this);
    }
}
