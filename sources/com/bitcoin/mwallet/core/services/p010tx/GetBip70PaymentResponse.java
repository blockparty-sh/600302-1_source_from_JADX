package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.mwallet.Bip70PaymentErrorStatus;
import com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentInfo;
import com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentOutput;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.leanplum.internal.Constants.Params;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001d\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007B\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB\u0011\b\u0016\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rB\u0013\b\u0016\u0012\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010¢\u0006\u0002\u0010\u0011BE\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u000e\u0010\u000e\u001a\n\u0018\u00010\u000fj\u0004\u0018\u0001`\u0010¢\u0006\u0002\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/tx/GetBip70PaymentResponse;", "", "info", "Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentInfo;", "outputs", "", "Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentOutput;", "(Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentInfo;Ljava/util/List;)V", "bip70PaymentError", "Lcom/bitcoin/mwallet/Bip70PaymentErrorStatus;", "(Lcom/bitcoin/mwallet/Bip70PaymentErrorStatus;)V", "grpcError", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "(Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;)V", "error", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "(Ljava/lang/RuntimeException;)V", "(Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentInfo;Ljava/util/List;Lcom/bitcoin/mwallet/Bip70PaymentErrorStatus;Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;Ljava/lang/RuntimeException;)V", "getBip70PaymentError", "()Lcom/bitcoin/mwallet/Bip70PaymentErrorStatus;", "getInfo", "()Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentInfo;", "getOutputs", "()Ljava/util/List;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.services.tx.GetBip70PaymentResponse */
/* compiled from: GetBip70PaymentResponse.kt */
public final class GetBip70PaymentResponse {
    @Nullable
    private final Bip70PaymentErrorStatus bip70PaymentError;
    @Nullable
    private final Bip70PaymentInfo info;
    @Nullable
    private final List<Bip70PaymentOutput> outputs;

    private GetBip70PaymentResponse(Bip70PaymentInfo bip70PaymentInfo, List<Bip70PaymentOutput> list, Bip70PaymentErrorStatus bip70PaymentErrorStatus, GrpcErrorResponse grpcErrorResponse, RuntimeException runtimeException) {
        this.info = bip70PaymentInfo;
        this.outputs = list;
        this.bip70PaymentError = bip70PaymentErrorStatus;
    }

    @Nullable
    public final Bip70PaymentInfo getInfo() {
        return this.info;
    }

    @Nullable
    public final List<Bip70PaymentOutput> getOutputs() {
        return this.outputs;
    }

    @Nullable
    public final Bip70PaymentErrorStatus getBip70PaymentError() {
        return this.bip70PaymentError;
    }

    public GetBip70PaymentResponse(@NotNull Bip70PaymentInfo bip70PaymentInfo, @NotNull List<Bip70PaymentOutput> list) {
        Intrinsics.checkParameterIsNotNull(bip70PaymentInfo, Params.INFO);
        Intrinsics.checkParameterIsNotNull(list, "outputs");
        this(bip70PaymentInfo, list, null, null, null);
    }

    public GetBip70PaymentResponse(@NotNull Bip70PaymentErrorStatus bip70PaymentErrorStatus) {
        Intrinsics.checkParameterIsNotNull(bip70PaymentErrorStatus, "bip70PaymentError");
        this(null, null, bip70PaymentErrorStatus, null, null);
    }

    public GetBip70PaymentResponse(@Nullable GrpcErrorResponse grpcErrorResponse) {
        this(null, null, null, grpcErrorResponse, null);
    }

    public GetBip70PaymentResponse(@NotNull RuntimeException runtimeException) {
        Intrinsics.checkParameterIsNotNull(runtimeException, "error");
        this(null, null, null, null, runtimeException);
    }
}
