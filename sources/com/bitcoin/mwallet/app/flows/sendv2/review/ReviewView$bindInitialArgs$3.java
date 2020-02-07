package com.bitcoin.mwallet.app.flows.sendv2.review;

import com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel;
import java.math.BigDecimal;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.review.ReviewView$bindInitialArgs$3", mo38000f = "ReviewView.kt", mo38001i = {}, mo38002l = {107}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: ReviewView.kt */
final class ReviewView$bindInitialArgs$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SendWhatModel $sendData;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f290p$;
    final /* synthetic */ ReviewView this$0;

    ReviewView$bindInitialArgs$3(ReviewView reviewView, SendWhatModel sendWhatModel, Continuation continuation) {
        this.this$0 = reviewView;
        this.$sendData = sendWhatModel;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ReviewView$bindInitialArgs$3 reviewView$bindInitialArgs$3 = new ReviewView$bindInitialArgs$3(this.this$0, this.$sendData, continuation);
        reviewView$bindInitialArgs$3.f290p$ = (CoroutineScope) obj;
        return reviewView$bindInitialArgs$3;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ReviewView$bindInitialArgs$3) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f290p$;
            ReviewPresenter reviewPresenter = (ReviewPresenter) this.this$0.getPresenter();
            BigDecimal cryptoAmount = this.$sendData.getCryptoAmount();
            if (cryptoAmount == null) {
                cryptoAmount = BigDecimal.ZERO;
                Intrinsics.checkExpressionValueIsNotNull(cryptoAmount, "BigDecimal.ZERO");
            }
            this.label = 1;
            if (reviewPresenter.setCryptoSendAmount(cryptoAmount, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
