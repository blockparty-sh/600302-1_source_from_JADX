package com.bitcoin.mwallet.core.services.verifiedtoken;

import com.bitcoin.bitcoink.Network;
import com.bitcoin.mwallet.AssetServiceGrpc.AssetServiceBlockingStub;
import com.bitcoin.mwallet.AssetServiceGrpc.AssetServiceStub;
import com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import p015io.grpc.Channel;
import p015io.grpc.ManagedChannel;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u000e\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/verifiedtoken/AssetServiceGrpc;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcServiceBase;", "Lcom/bitcoin/mwallet/core/services/verifiedtoken/VerifiedTokenService;", "grpcDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "channel", "Lio/grpc/ManagedChannel;", "network", "Lcom/bitcoin/bitcoink/Network;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lio/grpc/ManagedChannel;Lcom/bitcoin/bitcoink/Network;)V", "blockingStub", "Lcom/bitcoin/mwallet/AssetServiceGrpc$AssetServiceBlockingStub;", "stub", "Lcom/bitcoin/mwallet/AssetServiceGrpc$AssetServiceStub;", "getVerifiedTokens", "Lcom/bitcoin/mwallet/core/services/verifiedtoken/VerifiedTokensResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AssetServiceGrpc.kt */
public final class AssetServiceGrpc extends GrpcServiceBase implements VerifiedTokenService {
    /* access modifiers changed from: private */
    public final AssetServiceBlockingStub blockingStub;
    private final Network network;
    private final AssetServiceStub stub;

    public /* synthetic */ AssetServiceGrpc(CoroutineDispatcher coroutineDispatcher, ManagedChannel managedChannel, Network network2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 4) != 0) {
            network2 = Network.MAIN;
        }
        this(coroutineDispatcher, managedChannel, network2);
    }

    public AssetServiceGrpc(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull ManagedChannel managedChannel, @NotNull Network network2) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "grpcDispatcher");
        Intrinsics.checkParameterIsNotNull(managedChannel, "channel");
        Intrinsics.checkParameterIsNotNull(network2, "network");
        super(coroutineDispatcher);
        this.network = network2;
        Channel channel = managedChannel;
        AssetServiceBlockingStub newBlockingStub = com.bitcoin.mwallet.AssetServiceGrpc.newBlockingStub(channel);
        Intrinsics.checkExpressionValueIsNotNull(newBlockingStub, "AssetServiceGrpc.newBlockingStub(channel)");
        this.blockingStub = newBlockingStub;
        AssetServiceStub newStub = com.bitcoin.mwallet.AssetServiceGrpc.newStub(channel);
        Intrinsics.checkExpressionValueIsNotNull(newStub, "AssetServiceGrpc.newStub(channel)");
        this.stub = newStub;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getVerifiedTokens(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokensResponse> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc$getVerifiedTokens$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc$getVerifiedTokens$1 r0 = (com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc$getVerifiedTokens$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc$getVerifiedTokens$1 r0 = new com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc$getVerifiedTokens$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc r0 = (com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ RuntimeException -> 0x0051 }
            goto L_0x004e
        L_0x002e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.String r6 = "getVerifiedTokens"
            com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc$getVerifiedTokens$2 r2 = new com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc$getVerifiedTokens$2     // Catch:{ RuntimeException -> 0x0051 }
            r4 = 0
            r2.<init>(r5, r4)     // Catch:{ RuntimeException -> 0x0051 }
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2     // Catch:{ RuntimeException -> 0x0051 }
            r0.L$0 = r5     // Catch:{ RuntimeException -> 0x0051 }
            r0.label = r3     // Catch:{ RuntimeException -> 0x0051 }
            java.lang.Object r6 = r5.logDuration(r6, r2, r0)     // Catch:{ RuntimeException -> 0x0051 }
            if (r6 != r1) goto L_0x004e
            return r1
        L_0x004e:
            com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokensResponse r6 = (com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokensResponse) r6     // Catch:{ RuntimeException -> 0x0051 }
            goto L_0x0058
        L_0x0051:
            r6 = move-exception
            com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokensResponse r0 = new com.bitcoin.mwallet.core.services.verifiedtoken.VerifiedTokensResponse
            r0.<init>(r6)
            r6 = r0
        L_0x0058:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.verifiedtoken.AssetServiceGrpc.getVerifiedTokens(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
