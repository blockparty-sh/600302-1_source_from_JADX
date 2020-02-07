package com.bitcoin.mwallet.core.interactors;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H@ø\u0001\u0000"}, mo37405d2 = {"sendBip70Tx", "", "request", "Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendBip70TxRequest;", "depositAddress", "Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/core/services/tx/Bip70BroadcastTxResponse;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.interactors.SendTxInteractor", mo38000f = "SendTxInteractor.kt", mo38001i = {0, 0, 0, 1, 1, 1, 1, 1, 1}, mo38002l = {62, 75}, mo38003m = "sendBip70Tx", mo38004n = {"this", "request", "depositAddress", "this", "request", "depositAddress", "rawTx", "changeUtxo", "createdRawTx"}, mo38005s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* compiled from: SendTxInteractor.kt */
final class SendTxInteractor$sendBip70Tx$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SendTxInteractor this$0;

    SendTxInteractor$sendBip70Tx$1(SendTxInteractor sendTxInteractor, Continuation continuation) {
        this.this$0 = sendTxInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.sendBip70Tx(null, null, this);
    }
}
