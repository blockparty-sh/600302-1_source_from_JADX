package com.bitcoin.mwallet.core.services.location;

import com.bitcoin.mwallet.GeolocationServiceGrpc.GeolocationServiceBlockingStub;
import com.bitcoin.mwallet.GetGeolocationInfoRequest;
import com.bitcoin.mwallet.GetGeolocationInfoRequest.Builder;
import com.bitcoin.mwallet.GetGeolocationInfoResponse;
import com.bitcoin.mwallet.GetGeolocationInfoResponse.ResponseCase;
import com.bitcoin.mwallet.LocationInfo;
import com.bitcoin.mwallet.ResponseError;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001b\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\f\u0010\u000f\u001a\u00020\u000b*\u00020\u0010H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/location/GeolocationServiceGrpc;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcServiceBase;", "Lcom/bitcoin/mwallet/core/services/location/GeolocationService;", "grpcDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "channel", "Lio/grpc/ManagedChannel;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lio/grpc/ManagedChannel;)V", "blockingStub", "Lcom/bitcoin/mwallet/GeolocationServiceGrpc$GeolocationServiceBlockingStub;", "getBuyBitcoinProvider", "Lcom/bitcoin/mwallet/core/services/location/GeolocationResponse;", "ipAddress", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/bitcoin/mwallet/GetGeolocationInfoResponse;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GeolocationServiceGrpc.kt */
public final class GeolocationServiceGrpc extends GrpcServiceBase implements GeolocationService {
    private final GeolocationServiceBlockingStub blockingStub;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ResponseCase.values().length];

        static {
            $EnumSwitchMapping$0[ResponseCase.INFO.ordinal()] = 1;
            $EnumSwitchMapping$0[ResponseCase.LOCATIONERROR.ordinal()] = 2;
        }
    }

    public GeolocationServiceGrpc(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull ManagedChannel managedChannel) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "grpcDispatcher");
        Intrinsics.checkParameterIsNotNull(managedChannel, "channel");
        super(coroutineDispatcher);
        GeolocationServiceBlockingStub newBlockingStub = com.bitcoin.mwallet.GeolocationServiceGrpc.newBlockingStub(managedChannel);
        Intrinsics.checkExpressionValueIsNotNull(newBlockingStub, "GeolocationServiceGrpc.newBlockingStub(channel)");
        this.blockingStub = newBlockingStub;
    }

    @Nullable
    public Object getBuyBitcoinProvider(@Nullable String str, @NotNull Continuation<? super GeolocationResponse> continuation) {
        try {
            Builder newBuilder = GetGeolocationInfoRequest.newBuilder();
            Intrinsics.checkExpressionValueIsNotNull(newBuilder, "GetGeolocationInfoRequest.newBuilder()");
            if (str != null) {
                newBuilder.setIp(str);
            }
            GeneratedMessageLite build = newBuilder.build();
            Intrinsics.checkExpressionValueIsNotNull(build, "requestBuilder.build()");
            GetGeolocationInfoResponse geolocationInfo = this.blockingStub.getGeolocationInfo((GetGeolocationInfoRequest) build);
            Intrinsics.checkExpressionValueIsNotNull(geolocationInfo, "blockingStub.getGeolocationInfo(request)");
            return toDomain(geolocationInfo);
        } catch (RuntimeException e) {
            return new GeolocationResponse(e);
        }
    }

    private final GeolocationResponse toDomain(@NotNull GetGeolocationInfoResponse getGeolocationInfoResponse) {
        ResponseCase responseCase = getGeolocationInfoResponse.getResponseCase();
        if (responseCase != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[responseCase.ordinal()];
            if (i == 1) {
                LocationInfo info = getGeolocationInfoResponse.getInfo();
                Intrinsics.checkExpressionValueIsNotNull(info, "this.info");
                return new GeolocationResponse(info.getBuyBitcoinAllowed());
            } else if (i == 2) {
                ResponseError error = getGeolocationInfoResponse.getError();
                Intrinsics.checkExpressionValueIsNotNull(error, "this.error");
                return new GeolocationResponse(ProtoConverterKt.toDomain(error));
            }
        }
        return new GeolocationResponse(GrpcErrorResponse.Companion.unknown());
    }
}
