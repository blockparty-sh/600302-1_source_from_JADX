package com.bitcoin.mwallet.core.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import com.bitcoin.mwallet.core.dao.ExchangeRateDao;
import com.bitcoin.mwallet.core.extensions.StringKt;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateService;
import java.util.Currency;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J#\u0010\u0011\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J \u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\t0\u00130\b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository;", "", "exchangeRateDao", "Lcom/bitcoin/mwallet/core/dao/ExchangeRateDao;", "exchangeRateService", "Lcom/bitcoin/mwallet/core/services/exchangerate/ExchangeRateService;", "(Lcom/bitcoin/mwallet/core/dao/ExchangeRateDao;Lcom/bitcoin/mwallet/core/services/exchangerate/ExchangeRateService;)V", "currentExchangeRate", "Landroidx/lifecycle/LiveData;", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "fiatCurrency", "Ljava/util/Currency;", "getAvailableCurrencies", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentExchangeRate", "(Lcom/bitcoin/mwallet/core/models/Coin;Ljava/util/Currency;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ExchangeRateRepository.kt */
public final class ExchangeRateRepository {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public final ExchangeRateDao exchangeRateDao;
    /* access modifiers changed from: private */
    public final ExchangeRateService exchangeRateService;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/ExchangeRateRepository$Companion;", "", "()V", "toFiatExchangeRate", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "rate", "Lcom/bitcoin/mwallet/core/models/exchangerate/ExchangeRate;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ExchangeRateRepository.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final FiatExchangeRate toFiatExchangeRate(ExchangeRate exchangeRate) {
            Coin fromTicker = Coin.CREATOR.fromTicker(exchangeRate.getFromTicker());
            Currency tryParseCurrency = StringKt.tryParseCurrency(exchangeRate.getToTicker());
            if (fromTicker == null || tryParseCurrency == null) {
                return null;
            }
            return new FiatExchangeRate(fromTicker, tryParseCurrency, exchangeRate.getRate());
        }
    }

    public ExchangeRateRepository(@NotNull ExchangeRateDao exchangeRateDao2, @NotNull ExchangeRateService exchangeRateService2) {
        Intrinsics.checkParameterIsNotNull(exchangeRateDao2, "exchangeRateDao");
        Intrinsics.checkParameterIsNotNull(exchangeRateService2, "exchangeRateService");
        this.exchangeRateDao = exchangeRateDao2;
        this.exchangeRateService = exchangeRateService2;
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        Intrinsics.checkExpressionValueIsNotNull(newSingleThreadScheduledExecutor, "Executors.newSingleThreadScheduledExecutor()");
        newSingleThreadScheduledExecutor.scheduleWithFixedDelay(new Runnable(this) {
            final /* synthetic */ ExchangeRateRepository this$0;

            @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
            @DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$1$1", mo38000f = "ExchangeRateRepository.kt", mo38001i = {0, 1, 1, 1, 2, 2, 2, 2}, mo38002l = {34, 143, 160}, mo38003m = "invokeSuspend", mo38004n = {"btcBchRate", "btcBchRate", "responseRates", "btcRates", "btcBchRate", "responseRates", "btcRates", "bchRates"}, mo38005s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
            /* renamed from: com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$1$1 */
            /* compiled from: ExchangeRateRepository.kt */
            static final class C12651 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f382p$;
                final /* synthetic */ C12641 this$0;

                {
                    this.this$0 = r1;
                }

                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    Intrinsics.checkParameterIsNotNull(continuation, "completion");
                    C12651 r0 = new C12651(this.this$0, continuation);
                    r0.f382p$ = (CoroutineScope) obj;
                    return r0;
                }

                public final Object invoke(Object obj, Object obj2) {
                    return ((C12651) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARNING: Removed duplicated region for block: B:28:0x00f8  */
                @org.jetbrains.annotations.Nullable
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r17) {
                    /*
                        r16 = this;
                        r1 = r16
                        java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r0 = r1.label
                        java.lang.String r3 = "null cannot be cast to non-null type kotlin.Array<T>"
                        r4 = 0
                        r5 = 3
                        r6 = 2
                        java.lang.String r7 = "BCH"
                        r8 = 0
                        r9 = 1
                        if (r0 == 0) goto L_0x0052
                        if (r0 == r9) goto L_0x0048
                        if (r0 == r6) goto L_0x0036
                        if (r0 != r5) goto L_0x002e
                        java.lang.Object r0 = r1.L$3
                        java.util.List r0 = (java.util.List) r0
                        java.lang.Object r0 = r1.L$2
                        java.util.List r0 = (java.util.List) r0
                        java.lang.Object r0 = r1.L$1
                        com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse r0 = (com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse) r0
                        java.lang.Object r0 = r1.L$0
                        kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
                        kotlin.ResultKt.throwOnFailure(r17)
                        goto L_0x01b9
                    L_0x002e:
                        java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                        java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                        r0.<init>(r2)
                        throw r0
                    L_0x0036:
                        java.lang.Object r0 = r1.L$2
                        java.util.List r0 = (java.util.List) r0
                        java.lang.Object r6 = r1.L$1
                        com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse r6 = (com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse) r6
                        java.lang.Object r10 = r1.L$0
                        kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
                        kotlin.ResultKt.throwOnFailure(r17)
                        r4 = r0
                        goto L_0x00f2
                    L_0x0048:
                        java.lang.Object r0 = r1.L$0
                        kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
                        kotlin.ResultKt.throwOnFailure(r17)
                        r10 = r17
                        goto L_0x0074
                    L_0x0052:
                        kotlin.ResultKt.throwOnFailure(r17)
                        kotlinx.coroutines.CoroutineScope r0 = r1.f382p$
                        kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
                        r0.<init>()
                        r10 = r4
                        java.math.BigDecimal r10 = (java.math.BigDecimal) r10
                        r0.element = r10
                        com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$1 r10 = r1.this$0
                        com.bitcoin.mwallet.core.repositories.ExchangeRateRepository r10 = r10.this$0
                        com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateService r10 = r10.exchangeRateService
                        r1.L$0 = r0
                        r1.label = r9
                        java.lang.Object r10 = r10.getBtcExchangeRates(r1)
                        if (r10 != r2) goto L_0x0074
                        return r2
                    L_0x0074:
                        com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse r10 = (com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse) r10
                        java.util.Set r11 = r10.getRates()
                        java.lang.Iterable r11 = (java.lang.Iterable) r11
                        java.util.ArrayList r12 = new java.util.ArrayList
                        r13 = 10
                        int r13 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r11, r13)
                        r12.<init>(r13)
                        java.util.Collection r12 = (java.util.Collection) r12
                        java.util.Iterator r11 = r11.iterator()
                    L_0x008d:
                        boolean r13 = r11.hasNext()
                        if (r13 == 0) goto L_0x00c2
                        java.lang.Object r13 = r11.next()
                        com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse$ExchangeRate r13 = (com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse.ExchangeRate) r13
                        java.lang.String r14 = r13.getTicker()
                        boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual(r14, r7)
                        if (r14 == 0) goto L_0x00a9
                        java.math.BigDecimal r14 = r13.getRate()
                        r0.element = r14
                    L_0x00a9:
                        com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate r14 = new com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate
                        java.lang.String r15 = r13.getTicker()
                        java.math.BigDecimal r13 = r13.getRate()
                        java.util.Date r4 = r10.getTimestamp()
                        java.lang.String r5 = "BTC"
                        r14.<init>(r5, r15, r13, r4)
                        r12.add(r14)
                        r4 = 0
                        r5 = 3
                        goto L_0x008d
                    L_0x00c2:
                        r4 = r12
                        java.util.List r4 = (java.util.List) r4
                        com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$1 r5 = r1.this$0
                        com.bitcoin.mwallet.core.repositories.ExchangeRateRepository r5 = r5.this$0
                        com.bitcoin.mwallet.core.dao.ExchangeRateDao r5 = r5.exchangeRateDao
                        r11 = r4
                        java.util.Collection r11 = (java.util.Collection) r11
                        com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate[] r12 = new com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate[r8]
                        java.lang.Object[] r11 = r11.toArray(r12)
                        if (r11 == 0) goto L_0x01bc
                        com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate[] r11 = (com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate[]) r11
                        int r12 = r11.length
                        java.lang.Object[] r11 = java.util.Arrays.copyOf(r11, r12)
                        com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate[] r11 = (com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate[]) r11
                        r1.L$0 = r0
                        r1.L$1 = r10
                        r1.L$2 = r4
                        r1.label = r6
                        java.lang.Object r5 = r5.upsert(r11, r1)
                        if (r5 != r2) goto L_0x00f0
                        return r2
                    L_0x00f0:
                        r6 = r10
                        r10 = r0
                    L_0x00f2:
                        T r0 = r10.element
                        java.math.BigDecimal r0 = (java.math.BigDecimal) r0
                        if (r0 == 0) goto L_0x01b9
                        java.util.Set r0 = r6.getRates()
                        java.lang.Iterable r0 = (java.lang.Iterable) r0
                        java.util.ArrayList r5 = new java.util.ArrayList
                        r5.<init>()
                        java.util.Collection r5 = (java.util.Collection) r5
                        java.util.Iterator r0 = r0.iterator()
                    L_0x0109:
                        boolean r11 = r0.hasNext()
                        if (r11 == 0) goto L_0x012d
                        java.lang.Object r11 = r0.next()
                        r12 = r11
                        com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse$ExchangeRate r12 = (com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse.ExchangeRate) r12
                        java.lang.String r12 = r12.getTicker()
                        boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual(r12, r7)
                        r12 = r12 ^ r9
                        java.lang.Boolean r12 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r12)
                        boolean r12 = r12.booleanValue()
                        if (r12 == 0) goto L_0x0109
                        r5.add(r11)
                        goto L_0x0109
                    L_0x012d:
                        java.util.List r5 = (java.util.List) r5
                        java.lang.Iterable r5 = (java.lang.Iterable) r5
                        java.util.ArrayList r0 = new java.util.ArrayList
                        r0.<init>()
                        r9 = r0
                        java.util.Collection r9 = (java.util.Collection) r9
                        java.util.Iterator r5 = r5.iterator()
                    L_0x013d:
                        boolean r0 = r5.hasNext()
                        if (r0 == 0) goto L_0x0183
                        java.lang.Object r0 = r5.next()
                        com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse$ExchangeRate r0 = (com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse.ExchangeRate) r0
                        java.math.BigDecimal r11 = r0.getRate()     // Catch:{ RuntimeException -> 0x0172 }
                        T r12 = r10.element     // Catch:{ RuntimeException -> 0x0172 }
                        java.math.BigDecimal r12 = (java.math.BigDecimal) r12     // Catch:{ RuntimeException -> 0x0172 }
                        java.math.BigDecimal r13 = r0.getRate()     // Catch:{ RuntimeException -> 0x0172 }
                        int r13 = r13.precision()     // Catch:{ RuntimeException -> 0x0172 }
                        java.math.RoundingMode r14 = java.math.RoundingMode.HALF_UP     // Catch:{ RuntimeException -> 0x0172 }
                        java.math.BigDecimal r11 = r11.divide(r12, r13, r14)     // Catch:{ RuntimeException -> 0x0172 }
                        java.lang.String r12 = "responseRate.rate.divide…                        )"
                        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r12)     // Catch:{ RuntimeException -> 0x0172 }
                        com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate r12 = new com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate     // Catch:{ RuntimeException -> 0x0172 }
                        java.lang.String r0 = r0.getTicker()     // Catch:{ RuntimeException -> 0x0172 }
                        java.util.Date r13 = r6.getTimestamp()     // Catch:{ RuntimeException -> 0x0172 }
                        r12.<init>(r7, r0, r11, r13)     // Catch:{ RuntimeException -> 0x0172 }
                        goto L_0x017d
                    L_0x0172:
                        r0 = move-exception
                        java.lang.Throwable r0 = (java.lang.Throwable) r0
                        java.lang.Object[] r11 = new java.lang.Object[r8]
                        java.lang.String r12 = "Rate conversion error"
                        timber.log.Timber.m431e(r0, r12, r11)
                        r12 = 0
                    L_0x017d:
                        if (r12 == 0) goto L_0x013d
                        r9.add(r12)
                        goto L_0x013d
                    L_0x0183:
                        java.util.List r9 = (java.util.List) r9
                        com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$1 r0 = r1.this$0
                        com.bitcoin.mwallet.core.repositories.ExchangeRateRepository r0 = r0.this$0
                        com.bitcoin.mwallet.core.dao.ExchangeRateDao r0 = r0.exchangeRateDao
                        r5 = r9
                        java.util.Collection r5 = (java.util.Collection) r5
                        com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate[] r7 = new com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate[r8]
                        java.lang.Object[] r5 = r5.toArray(r7)
                        if (r5 == 0) goto L_0x01b3
                        com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate[] r5 = (com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate[]) r5
                        int r3 = r5.length
                        java.lang.Object[] r3 = java.util.Arrays.copyOf(r5, r3)
                        com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate[] r3 = (com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate[]) r3
                        r1.L$0 = r10
                        r1.L$1 = r6
                        r1.L$2 = r4
                        r1.L$3 = r9
                        r4 = 3
                        r1.label = r4
                        java.lang.Object r0 = r0.upsert(r3, r1)
                        if (r0 != r2) goto L_0x01b9
                        return r2
                    L_0x01b3:
                        kotlin.TypeCastException r0 = new kotlin.TypeCastException
                        r0.<init>(r3)
                        throw r0
                    L_0x01b9:
                        kotlin.Unit r0 = kotlin.Unit.INSTANCE
                        return r0
                    L_0x01bc:
                        kotlin.TypeCastException r0 = new kotlin.TypeCastException
                        r0.<init>(r3)
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.ExchangeRateRepository.C12641.C12651.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            {
                this.this$0 = r1;
            }

            public final void run() {
                BuildersKt__BuildersKt.runBlocking$default(null, new C12651(this, null), 1, null);
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getAvailableCurrencies(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<java.util.Currency>> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$getAvailableCurrencies$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$getAvailableCurrencies$1 r0 = (com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$getAvailableCurrencies$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$getAvailableCurrencies$1 r0 = new com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$getAvailableCurrencies$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r4) goto L_0x002f
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.repositories.ExchangeRateRepository r0 = (com.bitcoin.mwallet.core.repositories.ExchangeRateRepository) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0052
        L_0x002f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$getAvailableCurrencies$toTickers$1 r2 = new com.bitcoin.mwallet.core.repositories.ExchangeRateRepository$getAvailableCurrencies$toTickers$1
            r2.<init>(r5, r3)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)
            if (r6 != r1) goto L_0x0052
            return r1
        L_0x0052:
            java.util.List r6 = (java.util.List) r6
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r6 = r6.iterator()
        L_0x0061:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x0090
            java.lang.Object r1 = r6.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.Currency r1 = java.util.Currency.getInstance(r1)     // Catch:{ RuntimeException -> 0x0072 }
            goto L_0x008a
        L_0x0072:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Failed to parse Currency from toTicker="
            r2.append(r4)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]
            timber.log.Timber.m429e(r1, r2)
            r1 = r3
        L_0x008a:
            if (r1 == 0) goto L_0x0061
            r0.add(r1)
            goto L_0x0061
        L_0x0090:
            java.util.List r0 = (java.util.List) r0
            boolean r6 = r0.isEmpty()
            if (r6 == 0) goto L_0x00a3
            java.lang.String r6 = "USD"
            java.util.Currency r6 = java.util.Currency.getInstance(r6)
            java.util.List r6 = kotlin.collections.CollectionsKt.listOf(r6)
            return r6
        L_0x00a3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.ExchangeRateRepository.getAvailableCurrencies(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final LiveData<Map<Coin, FiatExchangeRate>> getCurrentExchangeRate(@NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(currency, "fiatCurrency");
        ExchangeRateDao exchangeRateDao2 = this.exchangeRateDao;
        String currencyCode = currency.getCurrencyCode();
        Intrinsics.checkExpressionValueIsNotNull(currencyCode, "fiatCurrency.currencyCode");
        LiveData<Map<Coin, FiatExchangeRate>> map = Transformations.map(exchangeRateDao2.selectByToTicker(currencyCode), ExchangeRateRepository$getCurrentExchangeRate$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "Transformations.map(exch…rn@map ratesMap\n        }");
        return map;
    }

    @NotNull
    public final LiveData<FiatExchangeRate> currentExchangeRate(@NotNull Coin coin, @NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        Intrinsics.checkParameterIsNotNull(currency, "fiatCurrency");
        ExchangeRateDao exchangeRateDao2 = this.exchangeRateDao;
        String currencyCode = currency.getCurrencyCode();
        Intrinsics.checkExpressionValueIsNotNull(currencyCode, "fiatCurrency.currencyCode");
        LiveData<FiatExchangeRate> map = Transformations.map(exchangeRateDao2.select(currencyCode, coin.getTicker()), ExchangeRateRepository$currentExchangeRate$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "Transformations.map(\n   …hangeRate(it) }\n        }");
        return map;
    }

    @Nullable
    public final Object getCurrentExchangeRate(@NotNull Coin coin, @NotNull Currency currency, @NotNull Continuation<? super FiatExchangeRate> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ExchangeRateRepository$getCurrentExchangeRate$3(this, currency, coin, null), continuation);
    }
}
