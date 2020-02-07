package com.bitcoin.mwallet.core.services.p010tx;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH@ø\u0001\u0000"}, mo37405d2 = {"broadcastBip70Tx", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "url", "", "depositAddress", "txRaw", "", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/core/services/tx/Bip70BroadcastTxResponse;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.tx.TxServiceGrpc", mo38000f = "TxServiceGrpc.kt", mo38001i = {0, 0, 0, 0, 0}, mo38002l = {52}, mo38003m = "broadcastBip70Tx", mo38004n = {"this", "walletId", "url", "depositAddress", "txRaw"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$broadcastBip70Tx$1 */
/* compiled from: TxServiceGrpc.kt */
final class TxServiceGrpc$broadcastBip70Tx$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TxServiceGrpc this$0;

    TxServiceGrpc$broadcastBip70Tx$1(TxServiceGrpc txServiceGrpc, Continuation continuation) {
        this.this$0 = txServiceGrpc;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.broadcastBip70Tx(null, null, null, null, this);
    }
}
