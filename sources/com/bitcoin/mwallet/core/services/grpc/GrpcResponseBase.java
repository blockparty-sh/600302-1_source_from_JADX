package com.bitcoin.mwallet.core.services.grpc;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\f\u001a\u00020\rR\u0019\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/grpc/GrpcResponseBase;", "", "grpcError", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "error", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "(Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;Ljava/lang/RuntimeException;)V", "getError", "()Ljava/lang/RuntimeException;", "getGrpcError", "()Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "baseResponseToString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GrpcResponseBase.kt */
public class GrpcResponseBase {
    @Nullable
    private final RuntimeException error;
    @Nullable
    private final GrpcErrorResponse grpcError;

    public GrpcResponseBase(@Nullable GrpcErrorResponse grpcErrorResponse, @Nullable RuntimeException runtimeException) {
        this.grpcError = grpcErrorResponse;
        this.error = runtimeException;
    }

    @Nullable
    public final GrpcErrorResponse getGrpcError() {
        return this.grpcError;
    }

    @Nullable
    public final RuntimeException getError() {
        return this.error;
    }

    @NotNull
    public final String baseResponseToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(grpcError=");
        sb.append(this.grpcError);
        sb.append(", error=");
        sb.append(this.error);
        sb.append(')');
        return sb.toString();
    }
}
