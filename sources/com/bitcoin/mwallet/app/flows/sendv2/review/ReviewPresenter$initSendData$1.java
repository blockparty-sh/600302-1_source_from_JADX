package com.bitcoin.mwallet.app.flows.sendv2.review;

import com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$initSendData$1", mo38000f = "ReviewPresenter.kt", mo38001i = {}, mo38002l = {308, 310}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: ReviewPresenter.kt */
final class ReviewPresenter$initSendData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SendWhatModel $sendData;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f285p$;
    final /* synthetic */ ReviewPresenter this$0;

    ReviewPresenter$initSendData$1(ReviewPresenter reviewPresenter, SendWhatModel sendWhatModel, Continuation continuation) {
        this.this$0 = reviewPresenter;
        this.$sendData = sendWhatModel;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ReviewPresenter$initSendData$1 reviewPresenter$initSendData$1 = new ReviewPresenter$initSendData$1(this.this$0, this.$sendData, continuation);
        reviewPresenter$initSendData$1.f285p$ = (CoroutineScope) obj;
        return reviewPresenter$initSendData$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ReviewPresenter$initSendData$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        ReviewPresenter reviewPresenter;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f285p$;
            ReviewPresenter reviewPresenter2 = this.this$0;
            WalletId wallet = this.$sendData.getWallet();
            this.label = 1;
            if (reviewPresenter2.setWalletId(wallet, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            reviewPresenter = (ReviewPresenter) this.L$0;
            ResultKt.throwOnFailure(obj);
            reviewPresenter.setToken((Slp) obj);
            this.this$0.isSendingToken().postValue(Boxing.boxBoolean(true));
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (this.this$0.getTokenId() != null) {
            ReviewPresenter reviewPresenter3 = this.this$0;
            SlpRepository access$getSlpRepository$p = reviewPresenter3.slpRepository;
            WalletId walletId = this.this$0.getWalletId();
            SlpId tokenId = this.this$0.getTokenId();
            if (tokenId == null) {
                Intrinsics.throwNpe();
            }
            this.L$0 = reviewPresenter3;
            this.label = 2;
            Object token = access$getSlpRepository$p.getToken(walletId, tokenId, this);
            if (token == coroutine_suspended) {
                return coroutine_suspended;
            }
            reviewPresenter = reviewPresenter3;
            obj = token;
            reviewPresenter.setToken((Slp) obj);
            this.this$0.isSendingToken().postValue(Boxing.boxBoolean(true));
            return Unit.INSTANCE;
        }
        this.this$0.isSendingToken().postValue(Boxing.boxBoolean(false));
        return Unit.INSTANCE;
    }
}
