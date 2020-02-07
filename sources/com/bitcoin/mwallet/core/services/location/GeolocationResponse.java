package com.bitcoin.mwallet.core.services.location;

import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcResponseBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0013\b\u0016\u0012\n\u0010\b\u001a\u00060\tj\u0002`\n¢\u0006\u0002\u0010\u000bB)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n¢\u0006\u0002\u0010\fR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/location/GeolocationResponse;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcResponseBase;", "buyBitcoinIsIAllowed", "", "(Z)V", "grpcError", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "(Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;)V", "error", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "(Ljava/lang/RuntimeException;)V", "(Ljava/lang/Boolean;Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;Ljava/lang/RuntimeException;)V", "getBuyBitcoinIsIAllowed", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GeolocationResponse.kt */
public final class GeolocationResponse extends GrpcResponseBase {
    @Nullable
    private final Boolean buyBitcoinIsIAllowed;

    @Nullable
    public final Boolean getBuyBitcoinIsIAllowed() {
        return this.buyBitcoinIsIAllowed;
    }

    public GeolocationResponse(@Nullable Boolean bool, @Nullable GrpcErrorResponse grpcErrorResponse, @Nullable RuntimeException runtimeException) {
        super(grpcErrorResponse, runtimeException);
        this.buyBitcoinIsIAllowed = bool;
    }

    public GeolocationResponse(boolean z) {
        this(Boolean.valueOf(z), null, null);
    }

    public GeolocationResponse(@NotNull GrpcErrorResponse grpcErrorResponse) {
        Intrinsics.checkParameterIsNotNull(grpcErrorResponse, "grpcError");
        this(null, grpcErrorResponse, null);
    }

    public GeolocationResponse(@NotNull RuntimeException runtimeException) {
        Intrinsics.checkParameterIsNotNull(runtimeException, "error");
        this(null, null, runtimeException);
    }
}
