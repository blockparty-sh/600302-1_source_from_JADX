package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcResponseBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u0013\b\u0016\u0012\n\u0010\n\u001a\u00060\u000bj\u0002`\f¢\u0006\u0002\u0010\rB5\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u000e\u0010\n\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\f¢\u0006\u0002\u0010\u000eJ\u0006\u0010\u0013\u001a\u00020\u0014R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/tx/Bip70BroadcastTxResponse;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcResponseBase;", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "memo", "", "(Lcom/bitcoin/bitcoink/tx/TxId;Ljava/lang/String;)V", "grpcError", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "(Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;)V", "error", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "(Ljava/lang/RuntimeException;)V", "(Lcom/bitcoin/bitcoink/tx/TxId;Ljava/lang/String;Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;Ljava/lang/RuntimeException;)V", "getMemo", "()Ljava/lang/String;", "getTxId", "()Lcom/bitcoin/bitcoink/tx/TxId;", "isSuccess", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.services.tx.Bip70BroadcastTxResponse */
/* compiled from: Bip70BroadcastTxResponse.kt */
public final class Bip70BroadcastTxResponse extends GrpcResponseBase {
    @Nullable
    private final String memo;
    @Nullable
    private final TxId txId;

    @Nullable
    public final TxId getTxId() {
        return this.txId;
    }

    @Nullable
    public final String getMemo() {
        return this.memo;
    }

    private Bip70BroadcastTxResponse(TxId txId2, String str, GrpcErrorResponse grpcErrorResponse, RuntimeException runtimeException) {
        super(grpcErrorResponse, runtimeException);
        this.txId = txId2;
        this.memo = str;
    }

    public Bip70BroadcastTxResponse(@Nullable TxId txId2, @Nullable String str) {
        this(txId2, str, null, null);
    }

    public Bip70BroadcastTxResponse(@NotNull GrpcErrorResponse grpcErrorResponse) {
        Intrinsics.checkParameterIsNotNull(grpcErrorResponse, "grpcError");
        this(null, null, grpcErrorResponse, null);
    }

    public Bip70BroadcastTxResponse(@NotNull RuntimeException runtimeException) {
        Intrinsics.checkParameterIsNotNull(runtimeException, "error");
        this(null, null, null, runtimeException);
    }

    public final boolean isSuccess() {
        return !Intrinsics.areEqual((Object) this.memo, (Object) "Payment error");
    }
}
