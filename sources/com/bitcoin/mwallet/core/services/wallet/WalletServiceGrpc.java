package com.bitcoin.mwallet.core.services.wallet;

import com.bitcoin.bitcoink.ExtendedPublicKey;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.mwallet.CreateSingleSigWalletRequest;
import com.bitcoin.mwallet.CreateSingleSigWalletResponse;
import com.bitcoin.mwallet.MigrateResponse;
import com.bitcoin.mwallet.MigrateSingleSigWalletRequest;
import com.bitcoin.mwallet.MigrateSingleSigWalletResponse;
import com.bitcoin.mwallet.RecoverResponse;
import com.bitcoin.mwallet.RecoverSingleSigWalletRequest;
import com.bitcoin.mwallet.RecoverSingleSigWalletResponse;
import com.bitcoin.mwallet.RecoverSingleSigWalletResponse.ResponseCase;
import com.bitcoin.mwallet.ResponseError;
import com.bitcoin.mwallet.WalletServiceGrpc.WalletServiceBlockingStub;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase;
import com.bitcoin.mwallet.core.services.grpc.ProtoConverterKt;
import com.google.protobuf.GeneratedMessageLite;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p015io.grpc.ManagedChannel;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J9\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J9\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J9\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\f\u0010\u001b\u001a\u00020\u000b*\u00020\u001cH\u0002J\f\u0010\u001b\u001a\u00020\u000b*\u00020\u001dH\u0002J\f\u0010\u001b\u001a\u00020\u000b*\u00020\u001eH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/wallet/WalletServiceGrpc;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcServiceBase;", "Lcom/bitcoin/mwallet/core/services/wallet/WalletService;", "grpcDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "channel", "Lio/grpc/ManagedChannel;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lio/grpc/ManagedChannel;)V", "stub", "Lcom/bitcoin/mwallet/WalletServiceGrpc$WalletServiceBlockingStub;", "createSingleSigWallet", "Lcom/bitcoin/mwallet/core/services/wallet/CreateWalletResponse;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "network", "Lcom/bitcoin/bitcoink/Network;", "publicKey", "Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "name", "", "signingPubKey", "(Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/bitcoink/Network;Lcom/bitcoin/bitcoink/ExtendedPublicKey;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "migrateExisitingWallet", "oldWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/bitcoink/Network;Lcom/bitcoin/bitcoink/ExtendedPublicKey;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recoverSingleSigWallet", "toDomain", "Lcom/bitcoin/mwallet/CreateSingleSigWalletResponse;", "Lcom/bitcoin/mwallet/MigrateSingleSigWalletResponse;", "Lcom/bitcoin/mwallet/RecoverSingleSigWalletResponse;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletServiceGrpc.kt */
public final class WalletServiceGrpc extends GrpcServiceBase implements WalletService {
    /* access modifiers changed from: private */
    public final WalletServiceBlockingStub stub;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ResponseCase.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[CreateSingleSigWalletResponse.ResponseCase.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[MigrateSingleSigWalletResponse.ResponseCase.values().length];

        static {
            $EnumSwitchMapping$0[ResponseCase.IMPORT.ordinal()] = 1;
            $EnumSwitchMapping$0[ResponseCase.ERROR.ordinal()] = 2;
            $EnumSwitchMapping$1[CreateSingleSigWalletResponse.ResponseCase.WALLET_ID.ordinal()] = 1;
            $EnumSwitchMapping$1[CreateSingleSigWalletResponse.ResponseCase.ERROR.ordinal()] = 2;
            $EnumSwitchMapping$2[MigrateSingleSigWalletResponse.ResponseCase.MIGRATE_RESPONSE.ordinal()] = 1;
            $EnumSwitchMapping$2[MigrateSingleSigWalletResponse.ResponseCase.ERROR.ordinal()] = 2;
        }
    }

    public WalletServiceGrpc(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull ManagedChannel managedChannel) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "grpcDispatcher");
        Intrinsics.checkParameterIsNotNull(managedChannel, "channel");
        super(coroutineDispatcher);
        WalletServiceBlockingStub newBlockingStub = com.bitcoin.mwallet.WalletServiceGrpc.newBlockingStub(managedChannel);
        Intrinsics.checkExpressionValueIsNotNull(newBlockingStub, "WalletServiceGrpc.newBlockingStub(channel)");
        this.stub = newBlockingStub;
    }

    @Nullable
    public Object createSingleSigWallet(@NotNull Coin coin, @NotNull Network network, @NotNull ExtendedPublicKey extendedPublicKey, @NotNull String str, @NotNull String str2, @NotNull Continuation<? super CreateWalletResponse> continuation) {
        GeneratedMessageLite build = CreateSingleSigWalletRequest.newBuilder().setXPubKey(extendedPublicKey.base58(network)).setNetwork(ProtoConverterKt.toProto(network)).setCoin(ProtoConverterKt.toProto(coin)).setName(str).setSigningPubKey(str2).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "CreateSingleSigWalletReq…Key)\n            .build()");
        return logDuration("createSingleSigWallet", new WalletServiceGrpc$createSingleSigWallet$2(this, (CreateSingleSigWalletRequest) build, null), continuation);
    }

    @Nullable
    public Object recoverSingleSigWallet(@NotNull Coin coin, @NotNull Network network, @NotNull ExtendedPublicKey extendedPublicKey, @NotNull String str, @NotNull String str2, @NotNull Continuation<? super CreateWalletResponse> continuation) {
        GeneratedMessageLite build = RecoverSingleSigWalletRequest.newBuilder().setXPubKey(extendedPublicKey.base58(network)).setNetwork(ProtoConverterKt.toProto(network)).setCoin(ProtoConverterKt.toProto(coin)).setName(str).setSigningPubKey(str2).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "RecoverSingleSigWalletRe…Key)\n            .build()");
        return logDuration("recoverSingleSigWallet", new WalletServiceGrpc$recoverSingleSigWallet$2(this, (RecoverSingleSigWalletRequest) build, null), continuation);
    }

    @Nullable
    public Object migrateExisitingWallet(@NotNull WalletId walletId, @NotNull Coin coin, @NotNull Network network, @NotNull ExtendedPublicKey extendedPublicKey, @NotNull String str, @NotNull Continuation<? super CreateWalletResponse> continuation) {
        GeneratedMessageLite build = MigrateSingleSigWalletRequest.newBuilder().setOldWalletId(walletId.getId()).setXPubKey(extendedPublicKey.base58(network)).setNetwork(ProtoConverterKt.toProto(network)).setCoin(ProtoConverterKt.toProto(coin)).setSigningPubKey(str).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "MigrateSingleSigWalletRe…Key)\n            .build()");
        return logDuration("migrateExisitingWallet", new WalletServiceGrpc$migrateExisitingWallet$2(this, (MigrateSingleSigWalletRequest) build, null), continuation);
    }

    /* access modifiers changed from: private */
    public final CreateWalletResponse toDomain(@NotNull RecoverSingleSigWalletResponse recoverSingleSigWalletResponse) {
        ResponseCase responseCase = recoverSingleSigWalletResponse.getResponseCase();
        if (responseCase != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[responseCase.ordinal()];
            if (i == 1) {
                RecoverResponse recoverResponse = recoverSingleSigWalletResponse.getImport();
                String str = "import";
                Intrinsics.checkExpressionValueIsNotNull(recoverResponse, str);
                String walletId = recoverResponse.getWalletId();
                Intrinsics.checkExpressionValueIsNotNull(walletId, "import.walletId");
                WalletId walletId2 = new WalletId(walletId);
                RecoverResponse recoverResponse2 = recoverSingleSigWalletResponse.getImport();
                Intrinsics.checkExpressionValueIsNotNull(recoverResponse2, str);
                return new CreateWalletResponse(walletId2, recoverResponse2.getName());
            } else if (i == 2) {
                ResponseError error = recoverSingleSigWalletResponse.getError();
                Intrinsics.checkExpressionValueIsNotNull(error, "error");
                return new CreateWalletResponse(ProtoConverterKt.toDomain(error));
            }
        }
        return new CreateWalletResponse(GrpcErrorResponse.Companion.unknown());
    }

    /* access modifiers changed from: private */
    public final CreateWalletResponse toDomain(@NotNull CreateSingleSigWalletResponse createSingleSigWalletResponse) {
        CreateSingleSigWalletResponse.ResponseCase responseCase = createSingleSigWalletResponse.getResponseCase();
        if (responseCase != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[responseCase.ordinal()];
            if (i == 1) {
                String walletId = createSingleSigWalletResponse.getWalletId();
                Intrinsics.checkExpressionValueIsNotNull(walletId, "walletId");
                return new CreateWalletResponse(new WalletId(walletId), null);
            } else if (i == 2) {
                ResponseError error = createSingleSigWalletResponse.getError();
                Intrinsics.checkExpressionValueIsNotNull(error, "error");
                return new CreateWalletResponse(ProtoConverterKt.toDomain(error));
            }
        }
        return new CreateWalletResponse(GrpcErrorResponse.Companion.unknown());
    }

    /* access modifiers changed from: private */
    public final CreateWalletResponse toDomain(@NotNull MigrateSingleSigWalletResponse migrateSingleSigWalletResponse) {
        MigrateSingleSigWalletResponse.ResponseCase responseCase = migrateSingleSigWalletResponse.getResponseCase();
        if (responseCase != null) {
            int i = WhenMappings.$EnumSwitchMapping$2[responseCase.ordinal()];
            if (i == 1) {
                MigrateResponse migrateResponse = migrateSingleSigWalletResponse.getMigrateResponse();
                Intrinsics.checkExpressionValueIsNotNull(migrateResponse, "migrateResponse");
                String currentWalletId = migrateResponse.getCurrentWalletId();
                Intrinsics.checkExpressionValueIsNotNull(currentWalletId, "migrateResponse.currentWalletId");
                return new CreateWalletResponse(new WalletId(currentWalletId), null);
            } else if (i == 2) {
                ResponseError error = migrateSingleSigWalletResponse.getError();
                Intrinsics.checkExpressionValueIsNotNull(error, "error");
                return new CreateWalletResponse(ProtoConverterKt.toDomain(error));
            }
        }
        return new CreateWalletResponse(GrpcErrorResponse.Companion.unknown());
    }
}
