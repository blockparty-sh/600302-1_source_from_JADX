package com.bitcoin.mwallet.core.repositories;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H@ø\u0001\u0000"}, mo37405d2 = {"getAvailableCurrencies", "", "continuation", "Lkotlin/coroutines/Continuation;", "", "Ljava/util/Currency;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.ExchangeRateRepository", mo38000f = "ExchangeRateRepository.kt", mo38001i = {0}, mo38002l = {66}, mo38003m = "getAvailableCurrencies", mo38004n = {"this"}, mo38005s = {"L$0"})
/* compiled from: ExchangeRateRepository.kt */
final class ExchangeRateRepository$getAvailableCurrencies$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ExchangeRateRepository this$0;

    ExchangeRateRepository$getAvailableCurrencies$1(ExchangeRateRepository exchangeRateRepository, Continuation continuation) {
        this.this$0 = exchangeRateRepository;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getAvailableCurrencies(this);
    }
}