package com.bitcoin.mwallet.app.components.amountselection;

import androidx.lifecycle.MutableLiveData;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.core.extensions.SatoshisKt;
import com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRateKt;
import java.math.BigDecimal;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$updateSecondaryAmount$1", mo38000f = "AmountSelectionPresenterBase.kt", mo38001i = {}, mo38002l = {208}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: AmountSelectionPresenterBase.kt */
final class AmountSelectionPresenterBase$updateSecondaryAmount$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BigDecimal $currentAmount;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f220p$;
    final /* synthetic */ AmountSelectionPresenterBase this$0;

    AmountSelectionPresenterBase$updateSecondaryAmount$1(AmountSelectionPresenterBase amountSelectionPresenterBase, BigDecimal bigDecimal, Continuation continuation) {
        this.this$0 = amountSelectionPresenterBase;
        this.$currentAmount = bigDecimal;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        AmountSelectionPresenterBase$updateSecondaryAmount$1 amountSelectionPresenterBase$updateSecondaryAmount$1 = new AmountSelectionPresenterBase$updateSecondaryAmount$1(this.this$0, this.$currentAmount, continuation);
        amountSelectionPresenterBase$updateSecondaryAmount$1.f220p$ = (CoroutineScope) obj;
        return amountSelectionPresenterBase$updateSecondaryAmount$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AmountSelectionPresenterBase$updateSecondaryAmount$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f220p$;
            GetCurrentExchangeRateInteractor getCurrentExchangeRateInteractor = this.this$0.getGetCurrentExchangeRateInteractor();
            Coin coin = this.this$0.getCoin();
            Object value = this.this$0.getDisplayCurrency().getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(value, "displayCurrency.value!!");
            Currency currency = (Currency) value;
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
        Satoshis fiatToSatoshis = FiatExchangeRateKt.fiatToSatoshis((FiatExchangeRate) obj, this.$currentAmount);
        MutableLiveData secondaryDisplayAmount = this.this$0.getSecondaryDisplayAmount();
        StringBuilder sb = new StringBuilder();
        sb.append(SatoshisKt.toCoinString$default(fiatToSatoshis, null, 1, null));
        sb.append(' ');
        sb.append(this.this$0.getCoin().getTicker());
        secondaryDisplayAmount.setValue(sb.toString());
        return Unit.INSTANCE;
    }
}
