package com.bitcoin.mwallet.core.services.wallet;

import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u0013\b\u0016\u0012\n\u0010\n\u001a\u00060\u000bj\u0002`\f¢\u0006\u0002\u0010\rB5\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u000e\u0010\n\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\f¢\u0006\u0002\u0010\u000eR\u0019\u0010\n\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/wallet/CreateWalletResponse;", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "name", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;)V", "grpcError", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "(Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;)V", "error", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "(Ljava/lang/RuntimeException;)V", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;Ljava/lang/RuntimeException;)V", "getError", "()Ljava/lang/RuntimeException;", "getGrpcError", "()Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "getName", "()Ljava/lang/String;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CreateWalletResponse.kt */
public final class CreateWalletResponse {
    @Nullable
    private final RuntimeException error;
    @Nullable
    private final GrpcErrorResponse grpcError;
    @Nullable
    private final String name;
    @Nullable
    private final WalletId walletId;

    private CreateWalletResponse(WalletId walletId2, String str, GrpcErrorResponse grpcErrorResponse, RuntimeException runtimeException) {
        this.walletId = walletId2;
        this.name = str;
        this.grpcError = grpcErrorResponse;
        this.error = runtimeException;
    }

    @Nullable
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final GrpcErrorResponse getGrpcError() {
        return this.grpcError;
    }

    @Nullable
    public final RuntimeException getError() {
        return this.error;
    }

    public CreateWalletResponse(@NotNull WalletId walletId2, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        this(walletId2, str, null, null);
    }

    public CreateWalletResponse(@NotNull GrpcErrorResponse grpcErrorResponse) {
        Intrinsics.checkParameterIsNotNull(grpcErrorResponse, "grpcError");
        this(null, null, grpcErrorResponse, null);
    }

    public CreateWalletResponse(@NotNull RuntimeException runtimeException) {
        Intrinsics.checkParameterIsNotNull(runtimeException, "error");
        this(null, null, null, runtimeException);
    }
}
