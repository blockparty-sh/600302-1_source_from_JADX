package com.bitcoin.mwallet.core.services.p010tx;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH@ø\u0001\u0000"}, mo37405d2 = {"updateNotes", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "txNote", "Lcom/bitcoin/mwallet/TxNote;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/core/services/tx/UpdateNotesResponse;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.tx.TxServiceGrpc", mo38000f = "TxServiceGrpc.kt", mo38001i = {0, 0, 0, 0, 0}, mo38002l = {152}, mo38003m = "updateNotes", mo38004n = {"this", "walletId", "copayerId", "signingKey", "txNote"}, mo38005s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$updateNotes$1 */
/* compiled from: TxServiceGrpc.kt */
final class TxServiceGrpc$updateNotes$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TxServiceGrpc this$0;

    TxServiceGrpc$updateNotes$1(TxServiceGrpc txServiceGrpc, Continuation continuation) {
        this.this$0 = txServiceGrpc;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateNotes(null, null, null, null, this);
    }
}
