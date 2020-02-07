package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.mwallet.core.models.p009tx.slputxo.WalletSlpUtxos;
import com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcResponseBase;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0013\b\u0016\u0012\n\u0010\b\u001a\u00060\tj\u0002`\n¢\u0006\u0002\u0010\u000bB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eB%\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0002\u0010\u0012B\u000f\b\u0016\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u0013BE\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n¢\u0006\u0002\u0010\u0014R\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/tx/GetUtxosResponse;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcResponseBase;", "walletUtxos", "Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;", "(Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;)V", "grpcError", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;", "(Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;)V", "error", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "(Ljava/lang/RuntimeException;)V", "walletSlpUtxos", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/WalletSlpUtxos;", "(Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;Lcom/bitcoin/mwallet/core/models/tx/slputxo/WalletSlpUtxos;)V", "slpTokenInfo", "", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "(Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;Lcom/bitcoin/mwallet/core/models/tx/slputxo/WalletSlpUtxos;Ljava/util/List;)V", "(Lcom/bitcoin/mwallet/core/models/tx/slputxo/WalletSlpUtxos;)V", "(Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;Lcom/bitcoin/mwallet/core/models/tx/slputxo/WalletSlpUtxos;Ljava/util/List;Lcom/bitcoin/mwallet/core/services/grpc/GrpcErrorResponse;Ljava/lang/RuntimeException;)V", "getSlpTokenInfo", "()Ljava/util/List;", "getWalletSlpUtxos", "()Lcom/bitcoin/mwallet/core/models/tx/slputxo/WalletSlpUtxos;", "getWalletUtxos", "()Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.services.tx.GetUtxosResponse */
/* compiled from: GetUtxosResponse.kt */
public final class GetUtxosResponse extends GrpcResponseBase {
    @Nullable
    private final List<Slp> slpTokenInfo;
    @Nullable
    private final WalletSlpUtxos walletSlpUtxos;
    @Nullable
    private final WalletUtxos walletUtxos;

    @Nullable
    public final WalletUtxos getWalletUtxos() {
        return this.walletUtxos;
    }

    @Nullable
    public final WalletSlpUtxos getWalletSlpUtxos() {
        return this.walletSlpUtxos;
    }

    @Nullable
    public final List<Slp> getSlpTokenInfo() {
        return this.slpTokenInfo;
    }

    private GetUtxosResponse(WalletUtxos walletUtxos2, WalletSlpUtxos walletSlpUtxos2, List<Slp> list, GrpcErrorResponse grpcErrorResponse, RuntimeException runtimeException) {
        super(grpcErrorResponse, runtimeException);
        this.walletUtxos = walletUtxos2;
        this.walletSlpUtxos = walletSlpUtxos2;
        this.slpTokenInfo = list;
    }

    public GetUtxosResponse(@NotNull WalletUtxos walletUtxos2) {
        Intrinsics.checkParameterIsNotNull(walletUtxos2, "walletUtxos");
        this(walletUtxos2, null, null, null, null);
    }

    public GetUtxosResponse(@NotNull GrpcErrorResponse grpcErrorResponse) {
        Intrinsics.checkParameterIsNotNull(grpcErrorResponse, "grpcError");
        this(null, null, null, grpcErrorResponse, null);
    }

    public GetUtxosResponse(@NotNull RuntimeException runtimeException) {
        Intrinsics.checkParameterIsNotNull(runtimeException, "error");
        this(null, null, null, null, runtimeException);
    }

    public GetUtxosResponse(@NotNull WalletUtxos walletUtxos2, @NotNull WalletSlpUtxos walletSlpUtxos2) {
        Intrinsics.checkParameterIsNotNull(walletUtxos2, "walletUtxos");
        Intrinsics.checkParameterIsNotNull(walletSlpUtxos2, "walletSlpUtxos");
        this(walletUtxos2, walletSlpUtxos2, null, null, null);
    }

    public GetUtxosResponse(@NotNull WalletUtxos walletUtxos2, @NotNull WalletSlpUtxos walletSlpUtxos2, @NotNull List<Slp> list) {
        Intrinsics.checkParameterIsNotNull(walletUtxos2, "walletUtxos");
        Intrinsics.checkParameterIsNotNull(walletSlpUtxos2, "walletSlpUtxos");
        Intrinsics.checkParameterIsNotNull(list, "slpTokenInfo");
        this(walletUtxos2, walletSlpUtxos2, list, null, null);
    }

    public GetUtxosResponse(@NotNull WalletSlpUtxos walletSlpUtxos2) {
        Intrinsics.checkParameterIsNotNull(walletSlpUtxos2, "walletSlpUtxos");
        this(null, walletSlpUtxos2, null, null, null);
    }
}
