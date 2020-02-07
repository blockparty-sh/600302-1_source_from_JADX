package com.bitcoin.mwallet.core.services.exchangerate;

import com.bitcoin.mwallet.ExchangeRateServiceGrpc.ExchangeRateServiceBlockingStub;
import com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p015io.grpc.ManagedChannel;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\n\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\fR\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/exchangerate/ExchangeRateServiceGrpc;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcServiceBase;", "Lcom/bitcoin/mwallet/core/services/exchangerate/ExchangeRateService;", "grpcDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "channel", "Lio/grpc/ManagedChannel;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lio/grpc/ManagedChannel;)V", "blockingStub", "Lcom/bitcoin/mwallet/ExchangeRateServiceGrpc$ExchangeRateServiceBlockingStub;", "getBtcExchangeRates", "Lcom/bitcoin/mwallet/core/services/exchangerate/ExchangeRateResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ExchangeRateServiceGrpc.kt */
public final class ExchangeRateServiceGrpc extends GrpcServiceBase implements ExchangeRateService {
    /* access modifiers changed from: private */
    public final ExchangeRateServiceBlockingStub blockingStub;

    public ExchangeRateServiceGrpc(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull ManagedChannel managedChannel) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "grpcDispatcher");
        Intrinsics.checkParameterIsNotNull(managedChannel, "channel");
        super(coroutineDispatcher);
        ExchangeRateServiceBlockingStub newBlockingStub = com.bitcoin.mwallet.ExchangeRateServiceGrpc.newBlockingStub(managedChannel);
        Intrinsics.checkExpressionValueIsNotNull(newBlockingStub, "ExchangeRateServiceGrpc.newBlockingStub(channel)");
        this.blockingStub = newBlockingStub;
    }

    @Nullable
    public Object getBtcExchangeRates(@NotNull Continuation<? super ExchangeRateResponse> continuation) {
        return logDuration("getBtcExchangeRates", new ExchangeRateServiceGrpc$getBtcExchangeRates$2(this, null), continuation);
    }
}
