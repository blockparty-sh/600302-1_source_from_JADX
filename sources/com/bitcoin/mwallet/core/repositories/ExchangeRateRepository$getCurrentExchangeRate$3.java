package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.dao.ExchangeRateDao;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$getCurrentExchangeRate$3", mo38000f = "ExchangeRateRepository.kt", mo38001i = {}, mo38002l = {116}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: ExchangeRateRepository.kt */
final class ExchangeRateRepository$getCurrentExchangeRate$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super FiatExchangeRate>, Object> {
    final /* synthetic */ Coin $coin;
    final /* synthetic */ Currency $fiatCurrency;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f384p$;
    final /* synthetic */ ExchangeRateRepository this$0;

    ExchangeRateRepository$getCurrentExchangeRate$3(ExchangeRateRepository exchangeRateRepository, Currency currency, Coin coin, Continuation continuation) {
        this.this$0 = exchangeRateRepository;
        this.$fiatCurrency = currency;
        this.$coin = coin;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ExchangeRateRepository$getCurrentExchangeRate$3 exchangeRateRepository$getCurrentExchangeRate$3 = new ExchangeRateRepository$getCurrentExchangeRate$3(this.this$0, this.$fiatCurrency, this.$coin, continuation);
        exchangeRateRepository$getCurrentExchangeRate$3.f384p$ = (CoroutineScope) obj;
        return exchangeRateRepository$getCurrentExchangeRate$3;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ExchangeRateRepository$getCurrentExchangeRate$3) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f384p$;
            ExchangeRateDao access$getExchangeRateDao$p = this.this$0.exchangeRateDao;
            String currencyCode = this.$fiatCurrency.getCurrencyCode();
            Intrinsics.checkExpressionValueIsNotNull(currencyCode, "fiatCurrency.currencyCode");
            String ticker = this.$coin.getTicker();
            this.label = 1;
            obj = access$getExchangeRateDao$p.get(currencyCode, ticker, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ExchangeRate exchangeRate = (ExchangeRate) obj;
        return exchangeRate != null ? ExchangeRateRepository.Companion.toFiatExchangeRate(exchangeRate) : null;
    }
}
