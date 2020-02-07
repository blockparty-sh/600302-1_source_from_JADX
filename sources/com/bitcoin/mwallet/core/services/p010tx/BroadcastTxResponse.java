package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcResponseBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB\u0013\b\u0016\u0012\n\u0010\u000b\u001a\u00060\fj\u0002`\r¢\u0006\u0002\u0010\u000eB5\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u000e\u0010\u000b\u001a\n\u0018\u00010\fj\u0004\u0018\u0001`\r¢\u0006\u0002\u0010\u000fJ\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcResponseBase;", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "(Lcom/bitcoin/bitcoink/tx/TxId;)V", "errorType", "Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse$ErrorType;", "(Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse$ErrorType;)V", "grpcError", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "(Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;)V", "error", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "(Ljava/lang/RuntimeException;)V", "(Lcom/bitcoin/bitcoink/tx/TxId;Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse$ErrorType;Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;Ljava/lang/RuntimeException;)V", "getErrorType", "()Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse$ErrorType;", "getTxId", "()Lcom/bitcoin/bitcoink/tx/TxId;", "isSuccess", "", "toString", "", "ErrorType", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse */
/* compiled from: BroadcastTxResponse.kt */
public final class BroadcastTxResponse extends GrpcResponseBase {
    @Nullable
    private final ErrorType errorType;
    @Nullable
    private final TxId txId;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse$ErrorType;", "", "(Ljava/lang/String;I)V", "ZION_CANCELLED", "TOO_LONG_MEMCHAIN", "UNKNOWN", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse$ErrorType */
    /* compiled from: BroadcastTxResponse.kt */
    public enum ErrorType {
        ZION_CANCELLED,
        TOO_LONG_MEMCHAIN,
        UNKNOWN
    }

    @Nullable
    public final TxId getTxId() {
        return this.txId;
    }

    @Nullable
    public final ErrorType getErrorType() {
        return this.errorType;
    }

    private BroadcastTxResponse(TxId txId2, ErrorType errorType2, GrpcErrorResponse grpcErrorResponse, RuntimeException runtimeException) {
        super(grpcErrorResponse, runtimeException);
        this.txId = txId2;
        this.errorType = errorType2;
    }

    public BroadcastTxResponse(@NotNull TxId txId2) {
        Intrinsics.checkParameterIsNotNull(txId2, "txId");
        this(txId2, null, null, null);
    }

    public BroadcastTxResponse(@NotNull ErrorType errorType2) {
        Intrinsics.checkParameterIsNotNull(errorType2, "errorType");
        this(null, errorType2, null, null);
    }

    public BroadcastTxResponse(@NotNull GrpcErrorResponse grpcErrorResponse) {
        Intrinsics.checkParameterIsNotNull(grpcErrorResponse, "grpcError");
        this(null, null, grpcErrorResponse, null);
    }

    public BroadcastTxResponse(@NotNull RuntimeException runtimeException) {
        Intrinsics.checkParameterIsNotNull(runtimeException, "error");
        this(null, null, null, runtimeException);
    }

    public final boolean isSuccess() {
        return this.txId != null;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BroadcastTxResponse(txId=");
        sb.append(this.txId);
        sb.append(", errorType=");
        sb.append(this.errorType);
        sb.append(')');
        return sb.toString();
    }
}
