package com.bitcoin.mwallet.core.services.p010tx;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH@ø\u0001\u0000"}, mo37405d2 = {"getTransactionHistory", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "observer", "Lio/grpc/stub/StreamObserver;", "Lcom/bitcoin/mwallet/TxHistory;", "startTime", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.tx.TxServiceGrpc", mo38000f = "TxServiceGrpc.kt", mo38001i = {0, 0, 0, 0, 0, 0}, mo38002l = {135}, mo38003m = "getTransactionHistory", mo38004n = {"this", "walletId", "copayerId", "signingKey", "observer", "startTime"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4", "J$0"})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getTransactionHistory$1 */
/* compiled from: TxServiceGrpc.kt */
final class TxServiceGrpc$getTransactionHistory$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TxServiceGrpc this$0;

    TxServiceGrpc$getTransactionHistory$1(TxServiceGrpc txServiceGrpc, Continuation continuation) {
        this.this$0 = txServiceGrpc;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getTransactionHistory(null, null, null, null, 0, this);
    }
}
