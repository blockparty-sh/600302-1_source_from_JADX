package com.bitcoin.mwallet.core.services.p010tx;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@ø\u0001\u0000"}, mo37405d2 = {"getBip70PaymentInfo", "", "url", "", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/bitcoin/mwallet/core/services/tx/GetBip70PaymentResponse;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.tx.TxServiceGrpc", mo38000f = "TxServiceGrpc.kt", mo38001i = {0, 0}, mo38002l = {70}, mo38003m = "getBip70PaymentInfo", mo38004n = {"this", "url"}, mo38005s = {"L$0", "L$1"})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpc$getBip70PaymentInfo$1 */
/* compiled from: TxServiceGrpc.kt */
final class TxServiceGrpc$getBip70PaymentInfo$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TxServiceGrpc this$0;

    TxServiceGrpc$getBip70PaymentInfo$1(TxServiceGrpc txServiceGrpc, Continuation continuation) {
        this.this$0 = txServiceGrpc;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getBip70PaymentInfo(null, this);
    }
}
