package com.bitcoin.mwallet.app.flows.sendv2.success;

import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.success.successPresenter$setInitialArgs$exchangeRate$1", mo38000f = "successPresenter.kt", mo38001i = {}, mo38002l = {68}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: successPresenter.kt */
final class successPresenter$setInitialArgs$exchangeRate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super FiatExchangeRate>, Object> {
    final /* synthetic */ Currency $currency;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f304p$;
    final /* synthetic */ successPresenter this$0;

    successPresenter$setInitialArgs$exchangeRate$1(successPresenter successpresenter, Currency currency, Continuation continuation) {
        this.this$0 = successpresenter;
        this.$currency = currency;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        successPresenter$setInitialArgs$exchangeRate$1 successpresenter_setinitialargs_exchangerate_1 = new successPresenter$setInitialArgs$exchangeRate$1(this.this$0, this.$currency, continuation);
        successpresenter_setinitialargs_exchangerate_1.f304p$ = (CoroutineScope) obj;
        return successpresenter_setinitialargs_exchangerate_1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((successPresenter$setInitialArgs$exchangeRate$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f304p$;
            GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor = this.this$0.getGetCurrentExchangeRateInteractor();
            Coin coin = this.this$0.getSendWhatData().getCoin();
            Currency currency = this.$currency;
            Intrinsics.checkExpressionValueIsNotNull(currency, Param.CURRENCY);
            this.label = 1;
            obj = getCurrentExchangeRateInteractor.getCurrentExchangeRate(coin, currency, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}