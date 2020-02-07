package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.DoubleRef;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter$startTimer$1", mo38000f = "Bip70PaymentInfoRequestPresenter.kt", mo38001i = {}, mo38002l = {295}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: Bip70PaymentInfoRequestPresenter.kt */
final class Bip70PaymentInfoRequestPresenter$startTimer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $originalTime;
    final /* synthetic */ DoubleRef $remainingTime;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f277p$;
    final /* synthetic */ Bip70PaymentInfoRequestPresenter this$0;

    Bip70PaymentInfoRequestPresenter$startTimer$1(Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter, DoubleRef doubleRef, double d, Continuation continuation) {
        this.this$0 = bip70PaymentInfoRequestPresenter;
        this.$remainingTime = doubleRef;
        this.$originalTime = d;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Bip70PaymentInfoRequestPresenter$startTimer$1 bip70PaymentInfoRequestPresenter$startTimer$1 = new Bip70PaymentInfoRequestPresenter$startTimer$1(this.this$0, this.$remainingTime, this.$originalTime, continuation);
        bip70PaymentInfoRequestPresenter$startTimer$1.f277p$ = (CoroutineScope) obj;
        return bip70PaymentInfoRequestPresenter$startTimer$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((Bip70PaymentInfoRequestPresenter$startTimer$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L_0x0018
            if (r1 != r2) goto L_0x0010
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r7
            goto L_0x0033
        L_0x0010:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0018:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.CoroutineScope r8 = r7.f277p$
            r8 = r7
        L_0x001e:
            kotlin.jvm.internal.Ref$DoubleRef r1 = r8.$remainingTime
            double r3 = r1.element
            r1 = 0
            double r5 = (double) r1
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x0058
            r3 = 1000(0x3e8, double:4.94E-321)
            r8.label = r2
            java.lang.Object r1 = kotlinx.coroutines.DelayKt.delay(r3, r8)
            if (r1 != r0) goto L_0x0033
            return r0
        L_0x0033:
            kotlin.jvm.internal.Ref$DoubleRef r1 = r8.$remainingTime
            double r3 = r1.element
            r5 = 1000(0x3e8, float:1.401E-42)
            double r5 = (double) r5
            double r3 = r3 - r5
            r1.element = r3
            kotlin.jvm.internal.Ref$DoubleRef r1 = r8.$remainingTime
            double r3 = r1.element
            double r5 = r8.$originalTime
            double r3 = r3 / r5
            r1 = 100
            double r5 = (double) r1
            double r3 = r3 * r5
            int r1 = (int) r3
            com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter r3 = r8.this$0
            androidx.lifecycle.MutableLiveData r3 = r3.getExpiryTimer()
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            r3.postValue(r1)
            goto L_0x001e
        L_0x0058:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter$startTimer$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
