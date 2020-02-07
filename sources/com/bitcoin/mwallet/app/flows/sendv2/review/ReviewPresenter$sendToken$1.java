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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$sendToken$1", mo38000f = "ReviewPresenter.kt", mo38001i = {0, 1, 2, 2}, mo38002l = {233, 244, 263}, mo38003m = "invokeSuspend", mo38004n = {"slpSelection", "slpSelection", "slpSelection", "utxoSelection"}, mo38005s = {"L$0", "L$0", "L$0", "L$1"})
/* compiled from: ReviewPresenter.kt */
final class ReviewPresenter$sendToken$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f287p$;
    final /* synthetic */ ReviewPresenter this$0;

    ReviewPresenter$sendToken$1(ReviewPresenter reviewPresenter, Continuation continuation) {
        this.this$0 = reviewPresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        ReviewPresenter$sendToken$1 reviewPresenter$sendToken$1 = new ReviewPresenter$sendToken$1(this.this$0, continuation);
        reviewPresenter$sendToken$1.f287p$ = (CoroutineScope) obj;
        return reviewPresenter$sendToken$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ReviewPresenter$sendToken$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0169  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r19) {
        /*
            r18 = this;
            r6 = r18
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r6.label
            r8 = 0
            r1 = 3
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L_0x0041
            if (r0 == r3) goto L_0x0037
            if (r0 == r2) goto L_0x002c
            if (r0 != r1) goto L_0x0024
            java.lang.Object r0 = r6.L$1
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r0 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r0
            java.lang.Object r0 = r6.L$0
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r0 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection) r0
            kotlin.ResultKt.throwOnFailure(r19)
            r0 = r19
            goto L_0x0152
        L_0x0024:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x002c:
            java.lang.Object r0 = r6.L$0
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r0 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection) r0
            kotlin.ResultKt.throwOnFailure(r19)
            r2 = r19
        L_0x0035:
            r5 = r0
            goto L_0x009f
        L_0x0037:
            java.lang.Object r0 = r6.L$0
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r0 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection) r0
            kotlin.ResultKt.throwOnFailure(r19)
            r0 = r19
            goto L_0x0066
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r19)
            kotlinx.coroutines.CoroutineScope r0 = r6.f287p$
            r0 = r4
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r0 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection) r0
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r5 = r6.this$0
            java.math.BigDecimal r5 = r5.getTokenAmount()
            if (r5 == 0) goto L_0x0092
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r5 = r6.this$0
            com.bitcoin.mwallet.core.models.slp.Slp r5 = r5.getToken()
            if (r5 == 0) goto L_0x0092
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r5 = r6.this$0
            r6.L$0 = r0
            r6.label = r3
            java.lang.Object r0 = r5.getSatoshiPerByte(r6)
            if (r0 != r7) goto L_0x0066
            return r7
        L_0x0066:
            java.math.BigDecimal r0 = (java.math.BigDecimal) r0
            if (r0 == 0) goto L_0x0091
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r5 = r6.this$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r5 = r5.createTxinteractor
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r9 = r6.this$0
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r9 = r9.getWalletData()
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r10 = r6.this$0
            java.math.BigDecimal r10 = r10.getTokenAmount()
            if (r10 != 0) goto L_0x0081
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0081:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r11 = r6.this$0
            com.bitcoin.mwallet.core.models.slp.Slp r11 = r11.getToken()
            if (r11 != 0) goto L_0x008c
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x008c:
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r0 = r5.selectSlpUtxos(r9, r10, r0, r11)
            goto L_0x0092
        L_0x0091:
            r0 = r4
        L_0x0092:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r5 = r6.this$0
            r6.L$0 = r0
            r6.label = r2
            java.lang.Object r2 = r5.getSatoshiPerByte(r6)
            if (r2 != r7) goto L_0x0035
            return r7
        L_0x009f:
            r12 = r2
            java.math.BigDecimal r12 = (java.math.BigDecimal) r12
            if (r12 == 0) goto L_0x00c3
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r0 = r6.this$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r9 = r0.createTxinteractor
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r0 = r6.this$0
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r10 = r0.getWalletData()
            com.bitcoin.bitcoink.tx.Satoshis$Companion r0 = com.bitcoin.bitcoink.p008tx.Satoshis.Companion
            com.bitcoin.bitcoink.tx.Satoshis r11 = r0.getZERO()
            r13 = 0
            r14 = 0
            r16 = 24
            r17 = 0
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r0 = com.bitcoin.mwallet.core.interactors.CreateTxInteractor.selectUtxos$default(r9, r10, r11, r12, r13, r14, r16, r17)
            r2 = r0
            goto L_0x00c4
        L_0x00c3:
            r2 = r4
        L_0x00c4:
            if (r2 == 0) goto L_0x01d4
            if (r5 != 0) goto L_0x00ca
            goto L_0x01d4
        L_0x00ca:
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection$Error r0 = r5.getError()
            if (r0 == 0) goto L_0x010a
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection$Error r0 = r5.getError()
            if (r0 != 0) goto L_0x00d8
            goto L_0x01d1
        L_0x00d8:
            int[] r1 = com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter.WhenMappings.$EnumSwitchMapping$1
            int r0 = r0.ordinal()
            r0 = r1[r0]
            if (r0 == r3) goto L_0x00e4
            goto L_0x01d1
        L_0x00e4:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r0 = r6.this$0
            androidx.lifecycle.MutableLiveData r0 = r0.getError()
            com.bitcoin.mwallet.app.android.Event r1 = new com.bitcoin.mwallet.app.android.Event
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r2 = r6.this$0
            android.content.Context r2 = r2.getContext()
            android.content.res.Resources r2 = r2.getResources()
            r3 = 2131951954(0x7f130152, float:1.9540337E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.String r3 = "context.resources.getStr…error_insufficient_funds)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            r1.<init>(r2)
            r0.setValue(r1)
            goto L_0x01d1
        L_0x010a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "SLPSelection: "
            r0.append(r3)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r3 = new java.lang.Object[r8]
            timber.log.Timber.m426d(r0, r3)
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r0 = r6.this$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r0 = r0.createTxinteractor
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r3 = r6.this$0
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r3 = r3.getWalletData()
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r9 = r6.this$0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r9 = r9.getDestinationAddress()
            com.bitcoin.mwallet.core.models.address.AddressAndOriginalText r9 = r9.getAddress()
            if (r9 == 0) goto L_0x013c
            com.bitcoin.bitcoink.address.Address r4 = r9.getParsed()
        L_0x013c:
            if (r4 != 0) goto L_0x0141
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0141:
            r6.L$0 = r5
            r6.L$1 = r2
            r6.label = r1
            r1 = r3
            r3 = r5
            r5 = r18
            java.lang.Object r0 = r0.send(r1, r2, r3, r4, r5)
            if (r0 != r7) goto L_0x0152
            return r7
        L_0x0152:
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse r0 = (com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse) r0
            java.lang.String r1 = r0.toString()
            java.lang.Object[] r2 = new java.lang.Object[r8]
            timber.log.Timber.m426d(r1, r2)
            com.bitcoin.bitcoink.tx.TxId r1 = r0.getTxId()
            if (r1 == 0) goto L_0x0169
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r0 = r6.this$0
            r0.toSuccess(r1)
            goto L_0x01d1
        L_0x0169:
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse$ErrorType r1 = r0.getErrorType()
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse$ErrorType r2 = com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse.ErrorType.ZION_CANCELLED
            if (r1 != r2) goto L_0x0196
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r0 = r6.this$0
            androidx.lifecycle.MutableLiveData r0 = r0.getError()
            com.bitcoin.mwallet.app.android.Event r1 = new com.bitcoin.mwallet.app.android.Event
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r2 = r6.this$0
            android.content.Context r2 = r2.getContext()
            android.content.res.Resources r2 = r2.getResources()
            r3 = 2131951955(0x7f130153, float:1.954034E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.String r3 = "context.resources.getStr…rror_zion_sign_cancelled)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            r1.<init>(r2)
            r0.setValue(r1)
            goto L_0x01d1
        L_0x0196:
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse$ErrorType r0 = r0.getErrorType()
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse$ErrorType r1 = com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse.ErrorType.TOO_LONG_MEMCHAIN
            if (r0 != r1) goto L_0x01b6
            com.bitcoin.mwallet.app.android.Event r0 = new com.bitcoin.mwallet.app.android.Event
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r1 = r6.this$0
            android.content.Context r1 = r1.getContext()
            r2 = 2131951706(0x7f13005a, float:1.9539834E38)
            java.lang.String r1 = r1.getString(r2)
            java.lang.String r2 = "context.getString(R.stri…t_error_mempool_too_long)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            r0.<init>(r1)
            goto L_0x01d1
        L_0x01b6:
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r0 = r6.this$0
            androidx.lifecycle.MutableLiveData r0 = r0.getError()
            com.bitcoin.mwallet.app.android.Event r1 = new com.bitcoin.mwallet.app.android.Event
            com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter r2 = r6.this$0
            android.content.Context r2 = r2.getContext()
            r3 = 2131951707(0x7f13005b, float:1.9539836E38)
            java.lang.String r2 = r2.getString(r3)
            r1.<init>(r2)
            r0.setValue(r1)
        L_0x01d1:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x01d4:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.review.ReviewPresenter$sendToken$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
