package com.bitcoin.mwallet.core.services.link;

import com.bitcoin.mwallet.core.models.discover.Link;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcResponseBase;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0013\b\u0016\u0012\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007¢\u0006\u0002\u0010\bB\u0015\b\u0016\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fB1\b\u0002\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007¢\u0006\u0002\u0010\rR\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/link/GetLinksResponse;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcResponseBase;", "grpcError", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "(Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;)V", "error", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "(Ljava/lang/RuntimeException;)V", "links", "", "Lcom/bitcoin/mwallet/core/models/discover/Link;", "(Ljava/util/List;)V", "(Ljava/util/List;Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;Ljava/lang/RuntimeException;)V", "getLinks", "()Ljava/util/List;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GetLinksResponse.kt */
public final class GetLinksResponse extends GrpcResponseBase {
    @Nullable
    private final List<Link> links;

    @Nullable
    public final List<Link> getLinks() {
        return this.links;
    }

    private GetLinksResponse(List<Link> list, GrpcErrorResponse grpcErrorResponse, RuntimeException runtimeException) {
        super(grpcErrorResponse, runtimeException);
        this.links = list;
    }

    public GetLinksResponse(@NotNull GrpcErrorResponse grpcErrorResponse) {
        Intrinsics.checkParameterIsNotNull(grpcErrorResponse, "grpcError");
        this(null, grpcErrorResponse, null);
    }

    public GetLinksResponse(@NotNull RuntimeException runtimeException) {
        Intrinsics.checkParameterIsNotNull(runtimeException, "error");
        this(null, null, runtimeException);
    }

    public GetLinksResponse(@NotNull List<Link> list) {
        Intrinsics.checkParameterIsNotNull(list, "links");
        this(list, null, null);
    }
}
