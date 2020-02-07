package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcResponseBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0013\b\u0016\u0012\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007¢\u0006\u0002\u0010\bB\u0007\b\u0016¢\u0006\u0002\u0010\tB!\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007¢\u0006\u0002\u0010\n¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/tx/UpdateNotesResponse;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcResponseBase;", "grpcError", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "(Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;)V", "error", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "(Ljava/lang/RuntimeException;)V", "()V", "(Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;Ljava/lang/RuntimeException;)V", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.services.tx.UpdateNotesResponse */
/* compiled from: UpdateNotesResponse.kt */
public final class UpdateNotesResponse extends GrpcResponseBase {
    private UpdateNotesResponse(GrpcErrorResponse grpcErrorResponse, RuntimeException runtimeException) {
        super(grpcErrorResponse, runtimeException);
    }

    public UpdateNotesResponse(@NotNull GrpcErrorResponse grpcErrorResponse) {
        Intrinsics.checkParameterIsNotNull(grpcErrorResponse, "grpcError");
        this(grpcErrorResponse, null);
    }

    public UpdateNotesResponse(@NotNull RuntimeException runtimeException) {
        Intrinsics.checkParameterIsNotNull(runtimeException, "error");
        this(null, runtimeException);
    }

    public UpdateNotesResponse() {
        this(null, null);
    }
}
