package com.bitcoin.mwallet.core.services.grpc;

import androidx.core.p003os.EnvironmentCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\b\u0018\u0000 \u00142\u00020\u0001:\u0002\u0014\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "", "message", "", "type", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse$ErrorType;", "(Ljava/lang/String;Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse$ErrorType;)V", "getMessage", "()Ljava/lang/String;", "getType", "()Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse$ErrorType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "ErrorType", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GrpcErrorResponse.kt */
public final class GrpcErrorResponse {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String message;
    @NotNull
    private final ErrorType type;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse$Companion;", "", "()V", "unknown", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: GrpcErrorResponse.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final GrpcErrorResponse unknown() {
            return new GrpcErrorResponse(EnvironmentCompat.MEDIA_UNKNOWN, ErrorType.UNKNOWN);
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse$ErrorType;", "", "(Ljava/lang/String;I)V", "UNKNOWN", "BAD_SIGNATURE", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: GrpcErrorResponse.kt */
    public enum ErrorType {
        UNKNOWN,
        BAD_SIGNATURE
    }

    @NotNull
    public static /* synthetic */ GrpcErrorResponse copy$default(GrpcErrorResponse grpcErrorResponse, String str, ErrorType errorType, int i, Object obj) {
        if ((i & 1) != 0) {
            str = grpcErrorResponse.message;
        }
        if ((i & 2) != 0) {
            errorType = grpcErrorResponse.type;
        }
        return grpcErrorResponse.copy(str, errorType);
    }

    @NotNull
    public final String component1() {
        return this.message;
    }

    @NotNull
    public final ErrorType component2() {
        return this.type;
    }

    @NotNull
    public final GrpcErrorResponse copy(@NotNull String str, @NotNull ErrorType errorType) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(errorType, "type");
        return new GrpcErrorResponse(str, errorType);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.type, (java.lang.Object) r3.type) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse r3 = (com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse) r3
            java.lang.String r0 = r2.message
            java.lang.String r1 = r3.message
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse$ErrorType r0 = r2.type
            com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse$ErrorType r3 = r3.type
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.message;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        ErrorType errorType = this.type;
        if (errorType != null) {
            i = errorType.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GrpcErrorResponse(message=");
        sb.append(this.message);
        sb.append(", type=");
        sb.append(this.type);
        sb.append(")");
        return sb.toString();
    }

    public GrpcErrorResponse(@NotNull String str, @NotNull ErrorType errorType) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(errorType, "type");
        this.message = str;
        this.type = errorType;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    public final ErrorType getType() {
        return this.type;
    }
}
