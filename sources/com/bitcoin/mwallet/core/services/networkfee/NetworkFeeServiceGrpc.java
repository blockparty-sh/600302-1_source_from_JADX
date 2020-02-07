package com.bitcoin.mwallet.core.services.networkfee;

import com.bitcoin.mwallet.Coin;
import com.bitcoin.mwallet.EstimateFeeResponse;
import com.bitcoin.mwallet.EstimateNetworkFeeResponse;
import com.bitcoin.mwallet.EstimateNetworkFeeResponse.ResponseCase;
import com.bitcoin.mwallet.FeeEstimate;
import com.bitcoin.mwallet.FeeLevel;
import com.bitcoin.mwallet.NetworkFeeServiceGrpc.NetworkFeeServiceBlockingStub;
import com.bitcoin.mwallet.ResponseError;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFee;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase;
import com.bitcoin.mwallet.core.services.grpc.ProtoConverterKt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import p015io.grpc.ManagedChannel;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u00012\u00020\u0002:\u0001\u0012B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001f\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\f\u0010\u0010\u001a\u00020\u000b*\u00020\u0011H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/networkfee/NetworkFeeServiceGrpc;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcServiceBase;", "Lcom/bitcoin/mwallet/core/services/networkfee/NetworkFeeService;", "grpcDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "channel", "Lio/grpc/ManagedChannel;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lio/grpc/ManagedChannel;)V", "blockingStub", "Lcom/bitcoin/mwallet/NetworkFeeServiceGrpc$NetworkFeeServiceBlockingStub;", "getNetworkFee", "Lcom/bitcoin/mwallet/core/services/networkfee/NetworkFeeResponse;", "coin", "", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/bitcoin/mwallet/EstimateNetworkFeeResponse;", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: NetworkFeeServiceGrpc.kt */
public final class NetworkFeeServiceGrpc extends GrpcServiceBase implements NetworkFeeService {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public final NetworkFeeServiceBlockingStub blockingStub;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0005H\u0002J\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0006*\u00020\u0007H\u0002¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/networkfee/NetworkFeeServiceGrpc$Companion;", "", "()V", "toDomain", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFee;", "Lcom/bitcoin/mwallet/FeeEstimate;", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "Lcom/bitcoin/mwallet/FeeLevel;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: NetworkFeeServiceGrpc.kt */
    public static final class Companion {

        @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[FeeLevel.values().length];

            static {
                $EnumSwitchMapping$0[FeeLevel.FASTEST_FEE.ordinal()] = 1;
                $EnumSwitchMapping$0[FeeLevel.HALF_HOUR_FEE.ordinal()] = 2;
                $EnumSwitchMapping$0[FeeLevel.ONE_HOUR_FEE.ordinal()] = 3;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final NetworkFee toDomain(@NotNull FeeEstimate feeEstimate) {
            Coin coin = feeEstimate.getCoin();
            Intrinsics.checkExpressionValueIsNotNull(coin, "this.coin");
            com.bitcoin.mwallet.core.models.Coin domain = ProtoConverterKt.toDomain(coin);
            Companion companion = this;
            FeeLevel level = feeEstimate.getLevel();
            Intrinsics.checkExpressionValueIsNotNull(level, "this.level");
            NetworkFeeLevel domain2 = companion.toDomain(level);
            if (domain != null && domain2 != null) {
                return new NetworkFee(domain, domain2, new BigDecimal(feeEstimate.getSatoshiPerByte()));
            }
            StringBuilder sb = new StringBuilder();
            sb.append("proto to domain failed ");
            sb.append(feeEstimate);
            Timber.m426d(sb.toString(), new Object[0]);
            return null;
        }

        private final NetworkFeeLevel toDomain(@NotNull FeeLevel feeLevel) {
            int i = WhenMappings.$EnumSwitchMapping$0[feeLevel.ordinal()];
            if (i == 1) {
                return NetworkFeeLevel.PRIORITY;
            }
            if (i == 2) {
                return NetworkFeeLevel.NORMAL;
            }
            if (i != 3) {
                return null;
            }
            return NetworkFeeLevel.ECONOMY;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ResponseCase.values().length];

        static {
            $EnumSwitchMapping$0[ResponseCase.ESTIMATE.ordinal()] = 1;
            $EnumSwitchMapping$0[ResponseCase.ERROR.ordinal()] = 2;
        }
    }

    public NetworkFeeServiceGrpc(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull ManagedChannel managedChannel) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "grpcDispatcher");
        Intrinsics.checkParameterIsNotNull(managedChannel, "channel");
        super(coroutineDispatcher);
        NetworkFeeServiceBlockingStub newBlockingStub = com.bitcoin.mwallet.NetworkFeeServiceGrpc.newBlockingStub(managedChannel);
        Intrinsics.checkExpressionValueIsNotNull(newBlockingStub, "NetworkFeeServiceGrpc.newBlockingStub(channel)");
        this.blockingStub = newBlockingStub;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getNetworkFee(@org.jetbrains.annotations.NotNull java.util.Set<? extends com.bitcoin.mwallet.core.models.Coin> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.services.networkfee.NetworkFeeResponse> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.core.services.networkfee.NetworkFeeServiceGrpc$getNetworkFee$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.core.services.networkfee.NetworkFeeServiceGrpc$getNetworkFee$1 r0 = (com.bitcoin.mwallet.core.services.networkfee.NetworkFeeServiceGrpc$getNetworkFee$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.services.networkfee.NetworkFeeServiceGrpc$getNetworkFee$1 r0 = new com.bitcoin.mwallet.core.services.networkfee.NetworkFeeServiceGrpc$getNetworkFee$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r6 = r0.L$1
            java.util.Set r6 = (java.util.Set) r6
            java.lang.Object r6 = r0.L$0
            com.bitcoin.mwallet.core.services.networkfee.NetworkFeeServiceGrpc r6 = (com.bitcoin.mwallet.core.services.networkfee.NetworkFeeServiceGrpc) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ RuntimeException -> 0x0057 }
            goto L_0x0054
        L_0x0032:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.String r7 = "getNetworkFee"
            com.bitcoin.mwallet.core.services.networkfee.NetworkFeeServiceGrpc$getNetworkFee$2 r2 = new com.bitcoin.mwallet.core.services.networkfee.NetworkFeeServiceGrpc$getNetworkFee$2     // Catch:{ RuntimeException -> 0x0057 }
            r4 = 0
            r2.<init>(r5, r6, r4)     // Catch:{ RuntimeException -> 0x0057 }
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2     // Catch:{ RuntimeException -> 0x0057 }
            r0.L$0 = r5     // Catch:{ RuntimeException -> 0x0057 }
            r0.L$1 = r6     // Catch:{ RuntimeException -> 0x0057 }
            r0.label = r3     // Catch:{ RuntimeException -> 0x0057 }
            java.lang.Object r7 = r5.logDuration(r7, r2, r0)     // Catch:{ RuntimeException -> 0x0057 }
            if (r7 != r1) goto L_0x0054
            return r1
        L_0x0054:
            com.bitcoin.mwallet.core.services.networkfee.NetworkFeeResponse r7 = (com.bitcoin.mwallet.core.services.networkfee.NetworkFeeResponse) r7     // Catch:{ RuntimeException -> 0x0057 }
            goto L_0x005d
        L_0x0057:
            r6 = move-exception
            com.bitcoin.mwallet.core.services.networkfee.NetworkFeeResponse r7 = new com.bitcoin.mwallet.core.services.networkfee.NetworkFeeResponse
            r7.<init>(r6)
        L_0x005d:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.networkfee.NetworkFeeServiceGrpc.getNetworkFee(java.util.Set, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final NetworkFeeResponse toDomain(@NotNull EstimateNetworkFeeResponse estimateNetworkFeeResponse) {
        ResponseCase responseCase = estimateNetworkFeeResponse.getResponseCase();
        if (responseCase != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[responseCase.ordinal()];
            if (i == 1) {
                EstimateFeeResponse estimate = estimateNetworkFeeResponse.getEstimate();
                Intrinsics.checkExpressionValueIsNotNull(estimate, "this.estimate");
                List estimateList = estimate.getEstimateList();
                Intrinsics.checkExpressionValueIsNotNull(estimateList, "this.estimate.estimateList");
                Iterable<FeeEstimate> iterable = estimateList;
                Collection arrayList = new ArrayList();
                for (FeeEstimate feeEstimate : iterable) {
                    Companion companion = Companion;
                    Intrinsics.checkExpressionValueIsNotNull(feeEstimate, "it");
                    NetworkFee access$toDomain = companion.toDomain(feeEstimate);
                    if (access$toDomain != null) {
                        arrayList.add(access$toDomain);
                    }
                }
                return new NetworkFeeResponse((List) arrayList);
            } else if (i == 2) {
                ResponseError error = estimateNetworkFeeResponse.getError();
                Intrinsics.checkExpressionValueIsNotNull(error, "this.error");
                return new NetworkFeeResponse(ProtoConverterKt.toDomain(error));
            }
        }
        return new NetworkFeeResponse(GrpcErrorResponse.Companion.unknown());
    }
}
