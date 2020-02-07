package com.bitcoin.mwallet.app.flows.sendv2.review;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H@ø\u0001\u0000"}, mo37405d2 = {"getSatoshiPerByte", "", "continuation", "Lkotlin/coroutines/Continuation;", "Ljava/math/BigDecimal;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter", mo38000f = "ReviewPresenter.kt", mo38001i = {0}, mo38002l = {183}, mo38003m = "getSatoshiPerByte", mo38004n = {"this"}, mo38005s = {"L$0"})
/* compiled from: ReviewPresenter.kt */
final class ReviewPresenter$getSatoshiPerByte$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ReviewPresenter this$0;

    ReviewPresenter$getSatoshiPerByte$1(ReviewPresenter reviewPresenter, Continuation continuation) {
        this.this$0 = reviewPresenter;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getSatoshiPerByte(this);
    }
}
