package com.bitcoin.mwallet.app.components.amountselection;

import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$updateSecondaryAmount$2", mo38000f = "AmountSelectionPresenterBase.kt", mo38001i = {0}, mo38002l = {217}, mo38003m = "invokeSuspend", mo38004n = {"satoshis"}, mo38005s = {"L$0"})
/* compiled from: AmountSelectionPresenterBase.kt */
final class AmountSelectionPresenterBase$updateSecondaryAmount$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BigDecimal $currentAmount;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f221p$;
    final /* synthetic */ AmountSelectionPresenterBase this$0;

    AmountSelectionPresenterBase$updateSecondaryAmount$2(AmountSelectionPresenterBase amountSelectionPresenterBase, BigDecimal bigDecimal, Continuation continuation) {
        this.this$0 = amountSelectionPresenterBase;
        this.$currentAmount = bigDecimal;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        AmountSelectionPresenterBase$updateSecondaryAmount$2 amountSelectionPresenterBase$updateSecondaryAmount$2 = new AmountSelectionPresenterBase$updateSecondaryAmount$2(this.this$0, this.$currentAmount, continuation);
        amountSelectionPresenterBase$updateSecondaryAmount$2.f221p$ = (CoroutineScope) obj;
        return amountSelectionPresenterBase$updateSecondaryAmount$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AmountSelectionPresenterBase$updateSecondaryAmount$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0061, code lost:
        if (r2 != null) goto L_0x0066;
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L_0x001b
            if (r1 != r2) goto L_0x0013
            java.lang.Object r0 = r6.L$0
            com.bitcoin.bitcoink.tx.Satoshis r0 = (com.bitcoin.bitcoink.p008tx.Satoshis) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0057
        L_0x0013:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x001b:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.CoroutineScope r7 = r6.f221p$
            com.bitcoin.bitcoink.tx.Satoshis$Companion r7 = com.bitcoin.bitcoink.p008tx.Satoshis.Companion
            java.math.BigDecimal r1 = r6.$currentAmount
            com.bitcoin.bitcoink.tx.Satoshis r7 = r7.fromCoins(r1)
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase r1 = r6.this$0
            com.bitcoin.mwallet.core.interactors.GetCurrentExchangeRateInteractor r1 = r1.getGetCurrentExchangeRateInteractor()
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase r3 = r6.this$0
            com.bitcoin.mwallet.core.models.Coin r3 = r3.getCoin()
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase r4 = r6.this$0
            androidx.lifecycle.MutableLiveData r4 = r4.getDisplayCurrency()
            java.lang.Object r4 = r4.getValue()
            if (r4 != 0) goto L_0x0043
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0043:
            java.lang.String r5 = "displayCurrency.value!!"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5)
            java.util.Currency r4 = (java.util.Currency) r4
            r6.L$0 = r7
            r6.label = r2
            java.lang.Object r1 = r1.getCurrentExchangeRate(r3, r4, r6)
            if (r1 != r0) goto L_0x0055
            return r0
        L_0x0055:
            r0 = r7
            r7 = r1
        L_0x0057:
            com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate r7 = (com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate) r7
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase r1 = r6.this$0
            if (r7 == 0) goto L_0x0064
            java.math.BigDecimal r2 = r7.toFiat(r0)
            if (r2 == 0) goto L_0x0064
            goto L_0x0066
        L_0x0064:
            java.math.BigDecimal r2 = java.math.BigDecimal.ZERO
        L_0x0066:
            r1.setFiatAmount(r2)
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase r1 = r6.this$0
            androidx.lifecycle.MutableLiveData r1 = r1.getSecondaryDisplayAmount()
            java.lang.String r7 = com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRateKt.toFiatString(r7, r0)
            r1.setValue(r7)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$updateSecondaryAmount$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
