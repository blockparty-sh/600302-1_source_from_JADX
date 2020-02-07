package com.bitcoin.mwallet.core.services.networkfee;

import com.bitcoin.mwallet.core.models.networkfee.NetworkFee;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcResponseBase;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005B\u000f\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u0013\b\u0016\u0012\n\u0010\t\u001a\u00060\nj\u0002`\u000b¢\u0006\u0002\u0010\fB/\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\t\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000b¢\u0006\u0002\u0010\rR\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/networkfee/NetworkFeeResponse;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcResponseBase;", "fees", "", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFee;", "(Ljava/util/List;)V", "grpcError", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "(Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;)V", "error", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "(Ljava/lang/RuntimeException;)V", "(Ljava/util/List;Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;Ljava/lang/RuntimeException;)V", "getFees", "()Ljava/util/List;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: NetworkFeeResponse.kt */
public final class NetworkFeeResponse extends GrpcResponseBase {
    @Nullable
    private final List<NetworkFee> fees;

    @Nullable
    public final List<NetworkFee> getFees() {
        return this.fees;
    }

    public NetworkFeeResponse(@Nullable List<NetworkFee> list, @Nullable GrpcErrorResponse grpcErrorResponse, @Nullable RuntimeException runtimeException) {
        super(grpcErrorResponse, runtimeException);
        this.fees = list;
    }

    public NetworkFeeResponse(@NotNull List<NetworkFee> list) {
        Intrinsics.checkParameterIsNotNull(list, "fees");
        this(list, null, null);
    }

    public NetworkFeeResponse(@NotNull GrpcErrorResponse grpcErrorResponse) {
        Intrinsics.checkParameterIsNotNull(grpcErrorResponse, "grpcError");
        this(null, grpcErrorResponse, null);
    }

    public NetworkFeeResponse(@NotNull RuntimeException runtimeException) {
        Intrinsics.checkParameterIsNotNull(runtimeException, "error");
        this(null, null, runtimeException);
    }
}
