package com.bitcoin.mwallet.core.services.p010tx;

import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.Bip70BroadcastTxResponse;
import com.bitcoin.mwallet.Bip70BroadcastTxResponse.ResponseCase;
import com.bitcoin.mwallet.ResponseError;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.ProtoConverterKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, mo37405d2 = {"toDomain", "Lcom/bitcoin/mwallet/core/services/tx/Bip70BroadcastTxResponse;", "Lcom/bitcoin/mwallet/Bip70BroadcastTxResponse;", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpcKt */
/* compiled from: TxServiceGrpc.kt */
public final class TxServiceGrpcKt {

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.mwallet.core.services.tx.TxServiceGrpcKt$WhenMappings */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ResponseCase.values().length];

        static {
            $EnumSwitchMapping$0[ResponseCase.MEMO.ordinal()] = 1;
            $EnumSwitchMapping$0[ResponseCase.ERROR.ordinal()] = 2;
        }
    }

    /* access modifiers changed from: private */
    public static final Bip70BroadcastTxResponse toDomain(@NotNull Bip70BroadcastTxResponse bip70BroadcastTxResponse) {
        ResponseCase responseCase = bip70BroadcastTxResponse.getResponseCase();
        if (responseCase != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[responseCase.ordinal()];
            if (i == 1) {
                String txId = bip70BroadcastTxResponse.getTxId();
                Intrinsics.checkExpressionValueIsNotNull(txId, "this.txId");
                return new Bip70BroadcastTxResponse(new TxId(txId), bip70BroadcastTxResponse.getMemo());
            } else if (i == 2) {
                ResponseError error = bip70BroadcastTxResponse.getError();
                Intrinsics.checkExpressionValueIsNotNull(error, "this.error");
                return new Bip70BroadcastTxResponse(ProtoConverterKt.toDomain(error));
            }
        }
        return new Bip70BroadcastTxResponse(GrpcErrorResponse.Companion.unknown());
    }
}
