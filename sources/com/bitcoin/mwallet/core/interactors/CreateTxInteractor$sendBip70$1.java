package com.bitcoin.mwallet.core.interactors;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH@ø\u0001\u0000"}, mo37405d2 = {"sendBip70", "", "walletData", "Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;", "utxoSelection", "Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "bip70Url", "", "bip70Outputs", "", "Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentOutput;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/core/services/tx/Bip70BroadcastTxResponse;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.interactors.CreateTxInteractor", mo38000f = "CreateTxInteractor.kt", mo38001i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4}, mo38002l = {71, 72, 85, 86, 88}, mo38003m = "sendBip70", mo38004n = {"this", "walletData", "utxoSelection", "bip70Url", "bip70Outputs", "this", "walletData", "utxoSelection", "bip70Url", "bip70Outputs", "this", "walletData", "utxoSelection", "bip70Url", "bip70Outputs", "changeAddress", "sendRequest", "this", "walletData", "utxoSelection", "bip70Url", "bip70Outputs", "changeAddress", "sendRequest", "this", "walletData", "utxoSelection", "bip70Url", "bip70Outputs", "changeAddress", "sendRequest", "depositAddress"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"})
/* compiled from: CreateTxInteractor.kt */
final class CreateTxInteractor$sendBip70$1 extends ContinuationImpl {
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

    CreateTxInteractor$sendBip70$1(CreateTxInteractor createTxInteractor, Continuation continuation) {
        this.this$0 = createTxInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.sendBip70(null, null, null, null, this);
    }
}
