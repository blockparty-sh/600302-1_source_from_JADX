package com.bitcoin.mwallet.core.services.networkfee;

import com.bitcoin.mwallet.EstimateNetworkFeeRequest;
import com.bitcoin.mwallet.EstimateNetworkFeeRequest.Builder;
import com.bitcoin.mwallet.EstimateNetworkFeeResponse;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.services.grpc.ProtoConverterKt;
import com.google.protobuf.GeneratedMessageLite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/services/networkfee/NetworkFeeResponse;", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.networkfee.NetworkFeeServiceGrpc$getNetworkFee$2", mo38000f = "NetworkFeeServiceGrpc.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: NetworkFeeServiceGrpc.kt */
final class NetworkFeeServiceGrpc$getNetworkFee$2 extends SuspendLambda implements Function1<Continuation<? super NetworkFeeResponse>, Object> {
    final /* synthetic */ Set $coin;
    int label;
    final /* synthetic */ NetworkFeeServiceGrpc this$0;

    NetworkFeeServiceGrpc$getNetworkFee$2(NetworkFeeServiceGrpc networkFeeServiceGrpc, Set set, Continuation continuation) {
        this.this$0 = networkFeeServiceGrpc;
        this.$coin = set;
        super(1, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        return new NetworkFeeServiceGrpc$getNetworkFee$2(this.this$0, this.$coin, continuation);
    }

    public final Object invoke(Object obj) {
        return ((NetworkFeeServiceGrpc$getNetworkFee$2) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Builder newBuilder = EstimateNetworkFeeRequest.newBuilder();
            Iterable<Coin> iterable = this.$coin;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Coin proto : iterable) {
                arrayList.add(ProtoConverterKt.toProto(proto));
            }
            GeneratedMessageLite build = newBuilder.addAllCoin((List) arrayList).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "EstimateNetworkFeeReques…                 .build()");
            EstimateNetworkFeeResponse estimateNetworkFee = this.this$0.blockingStub.estimateNetworkFee((EstimateNetworkFeeRequest) build);
            Intrinsics.checkExpressionValueIsNotNull(estimateNetworkFee, "blockingStub.estimateNetworkFee(request)");
            return this.this$0.toDomain(estimateNetworkFee);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
