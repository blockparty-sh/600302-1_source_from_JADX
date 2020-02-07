package com.bitcoin.mwallet.core.interactors;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00004\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u001a\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH@ø\u0001\u0000"}, mo37405d2 = {"updateSlpChangeUtxos", "", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "slpUtxoSelection", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;", "change", "Lkotlin/Triple;", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "Lcom/bitcoin/bitcoink/tx/TxOutput;", "Lcom/bitcoin/bitcoink/DerivationPath;", "changeOutputIndex", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.interactors.SendTxInteractor", mo38000f = "SendTxInteractor.kt", mo38001i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, mo38002l = {374}, mo38003m = "updateSlpChangeUtxos", mo38004n = {"this", "txId", "slpUtxoSelection", "change", "changeOutputIndex", "$dstr$walletId$changeOutput$changeAddressPath", "walletId", "changeOutput", "changeAddressPath", "changeUtxo", "slpUtxo"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10"})
/* compiled from: SendTxInteractor.kt */
final class SendTxInteractor$updateSlpChangeUtxos$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SendTxInteractor this$0;

    SendTxInteractor$updateSlpChangeUtxos$1(SendTxInteractor sendTxInteractor, Continuation continuation) {
        this.this$0 = sendTxInteractor;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateSlpChangeUtxos(null, null, null, null, this);
    }
}
