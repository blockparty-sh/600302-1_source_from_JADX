package com.bitcoin.mwallet.core.services.exchangerate;

import com.bitcoin.mwallet.ExchangeRates;
import com.bitcoin.mwallet.GetBtcExchangeRatesRequest;
import com.bitcoin.mwallet.GetBtcExhchangeRatesResponse;
import com.bitcoin.mwallet.core.services.grpc.ProtoConverterKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/services/exchangerate/ExchangeRateResponse;", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateServiceGrpc$getBtcExchangeRates$2", mo38000f = "ExchangeRateServiceGrpc.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: ExchangeRateServiceGrpc.kt */
final class ExchangeRateServiceGrpc$getBtcExchangeRates$2 extends SuspendLambda implements Function1<Continuation<? super ExchangeRateResponse>, Object> {
    int label;
    final /* synthetic */ ExchangeRateServiceGrpc this$0;

    ExchangeRateServiceGrpc$getBtcExchangeRates$2(ExchangeRateServiceGrpc exchangeRateServiceGrpc, Continuation continuation) {
        this.this$0 = exchangeRateServiceGrpc;
        super(1, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        return new ExchangeRateServiceGrpc$getBtcExchangeRates$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj) {
        return ((ExchangeRateServiceGrpc$getBtcExchangeRates$2) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            GetBtcExhchangeRatesResponse btcExchangeRates = this.this$0.blockingStub.getBtcExchangeRates(GetBtcExchangeRatesRequest.getDefaultInstance());
            Intrinsics.checkExpressionValueIsNotNull(btcExchangeRates, "blockingStub.getBtcExcha…est.getDefaultInstance())");
            ExchangeRates rates = btcExchangeRates.getRates();
            Intrinsics.checkExpressionValueIsNotNull(rates, "response.rates");
            return ProtoConverterKt.toDomain(rates);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
