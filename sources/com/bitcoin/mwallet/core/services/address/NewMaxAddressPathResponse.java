package com.bitcoin.mwallet.core.services.address;

import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcResponseBase;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0013\b\u0016\u0012\n\u0010\b\u001a\u00060\tj\u0002`\n¢\u0006\u0002\u0010\u000bB+\b\u0002\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n¢\u0006\u0002\u0010\rJ\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/address/NewMaxAddressPathResponse;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcResponseBase;", "path", "Lcom/bitcoin/mwallet/core/services/address/AddressPath;", "(Lcom/bitcoin/mwallet/core/services/address/AddressPath;)V", "grpcError", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "(Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;)V", "error", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "(Ljava/lang/RuntimeException;)V", "newMaxPath", "(Lcom/bitcoin/mwallet/core/services/address/AddressPath;Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;Ljava/lang/RuntimeException;)V", "getNewMaxPath", "()Lcom/bitcoin/mwallet/core/services/address/AddressPath;", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: NewMaxAddressPathResponse.kt */
public final class NewMaxAddressPathResponse extends GrpcResponseBase {
    @Nullable
    private final AddressPath newMaxPath;

    @Nullable
    public final AddressPath getNewMaxPath() {
        return this.newMaxPath;
    }

    private NewMaxAddressPathResponse(AddressPath addressPath, GrpcErrorResponse grpcErrorResponse, RuntimeException runtimeException) {
        super(grpcErrorResponse, runtimeException);
        this.newMaxPath = addressPath;
    }

    public NewMaxAddressPathResponse(@NotNull AddressPath addressPath) {
        Intrinsics.checkParameterIsNotNull(addressPath, JsonDataKey_signMessage.path);
        this(addressPath, null, null);
    }

    public NewMaxAddressPathResponse(@NotNull GrpcErrorResponse grpcErrorResponse) {
        Intrinsics.checkParameterIsNotNull(grpcErrorResponse, "grpcError");
        this(null, grpcErrorResponse, null);
    }

    public NewMaxAddressPathResponse(@NotNull RuntimeException runtimeException) {
        Intrinsics.checkParameterIsNotNull(runtimeException, "error");
        this(null, null, runtimeException);
    }

    @NotNull
    public String toString() {
        AddressPath addressPath = this.newMaxPath;
        if (addressPath != null) {
            String addressPath2 = addressPath.toString();
            if (addressPath2 != null) {
                return addressPath2;
            }
        }
        return super.baseResponseToString();
    }
}
