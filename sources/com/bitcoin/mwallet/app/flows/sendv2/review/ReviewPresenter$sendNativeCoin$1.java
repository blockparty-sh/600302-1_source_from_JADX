package com.bitcoin.mwallet.app.flows.sendv2.review;

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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$sendNativeCoin$1", mo38000f = "ReviewPresenter.kt", mo38001i = {1}, mo38002l = {191, 205}, mo38003m = "invokeSuspend", mo38004n = {"utxoSelection"}, mo38005s = {"L$0"})
/* compiled from: ReviewPresenter.kt */
final class ReviewPresenter$sendNativeCoin$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f286p$;
    final /* synthetic */ ReviewPresenter this$0;

    ReviewPresenter$sendNativeCoin$1(ReviewPresenter reviewPresenter, Continuation continuation) {
        this.this$0 = reviewPresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ReviewPresenter$sendNativeCoin$1 reviewPresenter$sendNativeCoin$1 = new ReviewPresenter$sendNativeCoin$1(this.this$0, continuation);
        reviewPresenter$sendNativeCoin$1.f286p$ = (CoroutineScope) obj;
        return reviewPresenter$sendNativeCoin$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ReviewPresenter$sendNativeCoin$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f4  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0024
            if (r1 == r4) goto L_0x0020
            if (r1 != r2) goto L_0x0018
            java.lang.Object r0 = r14.L$0
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r0 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r0
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00dd
        L_0x0018:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x0020:
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0034
        L_0x0024:
            kotlin.ResultKt.throwOnFailure(r15)
            kotlinx.coroutines.CoroutineScope r15 = r14.f286p$
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r15 = r14.this$0
            r14.label = r4
            java.lang.Object r15 = r15.getSatoshiPerByte(r14)
            if (r15 != r0) goto L_0x0034
            return r0
        L_0x0034:
            r8 = r15
            java.math.BigDecimal r8 = (java.math.BigDecimal) r8
            r15 = 0
            if (r8 == 0) goto L_0x0058
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r1 = r14.this$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r5 = r1.createTxinteractor
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r1 = r14.this$0
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r6 = r1.getWalletData()
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r1 = r14.this$0
            com.bitcoin.bitcoink.tx.Satoshis r7 = r1.sendAmountSatoshis
            r9 = 0
            r10 = 0
            r12 = 24
            r13 = 0
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r1 = com.bitcoin.mwallet.core.interactors.CreateTxInteractor.selectUtxos$default(r5, r6, r7, r8, r9, r10, r12, r13)
            r7 = r1
            goto L_0x0059
        L_0x0058:
            r7 = r15
        L_0x0059:
            if (r7 != 0) goto L_0x0065
            java.lang.Object[] r15 = new java.lang.Object[r3]
            java.lang.String r0 = "Illegal state"
            timber.log.Timber.m426d(r0, r15)
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        L_0x0065:
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection$Error r1 = r7.getError()
            if (r1 == 0) goto L_0x00a5
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection$Error r15 = r7.getError()
            if (r15 != 0) goto L_0x0073
            goto L_0x015c
        L_0x0073:
            int[] r0 = com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter.WhenMappings.$EnumSwitchMapping$0
            int r15 = r15.ordinal()
            r15 = r0[r15]
            if (r15 == r4) goto L_0x007f
            goto L_0x015c
        L_0x007f:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r15 = r14.this$0
            androidx.lifecycle.MutableLiveData r15 = r15.getError()
            com.bitcoin.mwallet.app.android.Event r0 = new com.bitcoin.mwallet.app.android.Event
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r1 = r14.this$0
            android.content.Context r1 = r1.getContext()
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131951954(0x7f130152, float:1.9540337E38)
            java.lang.String r1 = r1.getString(r2)
            java.lang.String r2 = "context.resources.getStr…error_insufficient_funds)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            r0.<init>(r1)
            r15.setValue(r0)
            goto L_0x015c
        L_0x00a5:
            java.lang.String r1 = r7.toString()
            java.lang.Object[] r4 = new java.lang.Object[r3]
            timber.log.Timber.m426d(r1, r4)
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r1 = r14.this$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r5 = r1.createTxinteractor
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r1 = r14.this$0
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r6 = r1.getWalletData()
            r8 = 0
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r1 = r14.this$0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r1 = r1.getDestinationAddress()
            com.bitcoin.mwallet.core.models.address.AddressAndOriginalText r1 = r1.getAddress()
            if (r1 == 0) goto L_0x00cb
            com.bitcoin.bitcoink.address.Address r15 = r1.getParsed()
        L_0x00cb:
            r9 = r15
            if (r9 != 0) goto L_0x00d1
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00d1:
            r14.L$0 = r7
            r14.label = r2
            r10 = r14
            java.lang.Object r15 = r5.send(r6, r7, r8, r9, r10)
            if (r15 != r0) goto L_0x00dd
            return r0
        L_0x00dd:
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse r15 = (com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse) r15
            java.lang.String r0 = r15.toString()
            java.lang.Object[] r1 = new java.lang.Object[r3]
            timber.log.Timber.m426d(r0, r1)
            com.bitcoin.bitcoink.tx.TxId r0 = r15.getTxId()
            if (r0 == 0) goto L_0x00f4
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r15 = r14.this$0
            r15.toSuccess(r0)
            goto L_0x015c
        L_0x00f4:
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse$ErrorType r0 = r15.getErrorType()
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse$ErrorType r1 = com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse.ErrorType.ZION_CANCELLED
            if (r0 != r1) goto L_0x0121
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r15 = r14.this$0
            androidx.lifecycle.MutableLiveData r15 = r15.getError()
            com.bitcoin.mwallet.app.android.Event r0 = new com.bitcoin.mwallet.app.android.Event
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r1 = r14.this$0
            android.content.Context r1 = r1.getContext()
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131951955(0x7f130153, float:1.954034E38)
            java.lang.String r1 = r1.getString(r2)
            java.lang.String r2 = "context.resources.getStr…rror_zion_sign_cancelled)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            r0.<init>(r1)
            r15.setValue(r0)
            goto L_0x015c
        L_0x0121:
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse$ErrorType r15 = r15.getErrorType()
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse$ErrorType r0 = com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse.ErrorType.TOO_LONG_MEMCHAIN
            if (r15 != r0) goto L_0x0141
            com.bitcoin.mwallet.app.android.Event r15 = new com.bitcoin.mwallet.app.android.Event
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r0 = r14.this$0
            android.content.Context r0 = r0.getContext()
            r1 = 2131951706(0x7f13005a, float:1.9539834E38)
            java.lang.String r0 = r0.getString(r1)
            java.lang.String r1 = "context.getString(R.stri…t_error_mempool_too_long)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r15.<init>(r0)
            goto L_0x015c
        L_0x0141:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r15 = r14.this$0
            androidx.lifecycle.MutableLiveData r15 = r15.getError()
            com.bitcoin.mwallet.app.android.Event r0 = new com.bitcoin.mwallet.app.android.Event
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r1 = r14.this$0
            android.content.Context r1 = r1.getContext()
            r2 = 2131951707(0x7f13005b, float:1.9539836E38)
            java.lang.String r1 = r1.getString(r2)
            r0.<init>(r1)
            r15.setValue(r0)
        L_0x015c:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$sendNativeCoin$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
