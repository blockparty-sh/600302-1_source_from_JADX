package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import com.bitcoin.mwallet.Bip70PaymentErrorStatus;
import com.bitcoin.mwallet.core.interactors.Bip70PaymentInteractor;
import com.bitcoin.mwallet.core.models.BitcoinUriContent;
import com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentInfo;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import com.bitcoin.mwallet.core.services.p010tx.GetBip70PaymentResponse;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
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
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter$init$1", mo38000f = "Bip70PaymentInfoRequestPresenter.kt", mo38001i = {}, mo38002l = {256}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: Bip70PaymentInfoRequestPresenter.kt */
final class Bip70PaymentInfoRequestPresenter$init$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BitcoinUriContent $bitcoinUriContent;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f271p$;
    final /* synthetic */ Bip70PaymentInfoRequestPresenter this$0;

    Bip70PaymentInfoRequestPresenter$init$1(Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter, BitcoinUriContent bitcoinUriContent, Continuation continuation) {
        this.this$0 = bip70PaymentInfoRequestPresenter;
        this.$bitcoinUriContent = bitcoinUriContent;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Bip70PaymentInfoRequestPresenter$init$1 bip70PaymentInfoRequestPresenter$init$1 = new Bip70PaymentInfoRequestPresenter$init$1(this.this$0, this.$bitcoinUriContent, continuation);
        bip70PaymentInfoRequestPresenter$init$1.f271p$ = (CoroutineScope) obj;
        return bip70PaymentInfoRequestPresenter$init$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((Bip70PaymentInfoRequestPresenter$init$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f271p$;
            Bip70PaymentInteractor access$getBip70PaymentInteractor$p = this.this$0.bip70PaymentInteractor;
            String url = this.$bitcoinUriContent.getUrl();
            if (url == null) {
                Intrinsics.throwNpe();
            }
            this.label = 1;
            obj = access$getBip70PaymentInteractor$p.getBip70PaymentInfo(url, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        GetBip70PaymentResponse getBip70PaymentResponse = (GetBip70PaymentResponse) obj;
        StringBuilder sb = new StringBuilder();
        sb.append("Bitpay Error: ");
        sb.append(getBip70PaymentResponse.getBip70PaymentError());
        Timber.m426d(sb.toString(), new Object[0]);
        this.this$0.get_dataLoaded().postValue(Boxing.boxBoolean(true));
        this.this$0.get_errorState().postValue(getBip70PaymentResponse.getBip70PaymentError());
        this.this$0.setPaymentInfo(getBip70PaymentResponse.getInfo());
        this.this$0.setPaymentTransactionInfo(getBip70PaymentResponse.getOutputs());
        this.this$0.getSelectedWalletId().postValue(this.this$0.getSelectedWalletId().getValue());
        String str = "domain";
        if (this.this$0.getPaymentInfo() != null) {
            Bip70PaymentInfo paymentInfo = this.this$0.getPaymentInfo();
            Long l = null;
            Long boxLong = paymentInfo != null ? Boxing.boxLong(paymentInfo.getCreatedTime()) : null;
            if (boxLong == null) {
                Intrinsics.throwNpe();
            }
            if (boxLong.longValue() <= 0) {
                Bip70PaymentInfo paymentInfo2 = this.this$0.getPaymentInfo();
                if (paymentInfo2 != null) {
                    paymentInfo2.setCreatedTime(System.currentTimeMillis());
                }
            }
            Bip70PaymentInfo paymentInfo3 = this.this$0.getPaymentInfo();
            if ((paymentInfo3 != null ? Boxing.boxLong(paymentInfo3.getCreatedTime()) : null) != null) {
                Bip70PaymentInfo paymentInfo4 = this.this$0.getPaymentInfo();
                if ((paymentInfo4 != null ? Boxing.boxLong(paymentInfo4.getExpiryTime()) : null) != null) {
                    Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter = this.this$0;
                    Bip70PaymentInfo paymentInfo5 = bip70PaymentInfoRequestPresenter.getPaymentInfo();
                    Long boxLong2 = paymentInfo5 != null ? Boxing.boxLong(paymentInfo5.getCreatedTime()) : null;
                    if (boxLong2 == null) {
                        Intrinsics.throwNpe();
                    }
                    long longValue = boxLong2.longValue();
                    Bip70PaymentInfo paymentInfo6 = this.this$0.getPaymentInfo();
                    if (paymentInfo6 != null) {
                        l = Boxing.boxLong(paymentInfo6.getExpiryTime());
                    }
                    if (l == null) {
                        Intrinsics.throwNpe();
                    }
                    bip70PaymentInfoRequestPresenter.startTimer(longValue, l.longValue());
                }
            }
            AnalyticsService analyticsService = this.this$0.getAnalyticsService();
            String domain = this.$bitcoinUriContent.getDomain();
            if (domain == null) {
                Intrinsics.throwNpe();
            }
            analyticsService.track("payment_protocol_fetch_succeeded", MapsKt.mapOf(TuplesKt.m309to(str, domain)));
        } else {
            this.this$0.get_errorState().postValue(Bip70PaymentErrorStatus.INVALID_PAYMENT);
            if (this.$bitcoinUriContent.getDomain() != null) {
                AnalyticsService analyticsService2 = this.this$0.getAnalyticsService();
                String domain2 = this.$bitcoinUriContent.getDomain();
                if (domain2 == null) {
                    Intrinsics.throwNpe();
                }
                analyticsService2.track("payment_protocol_fetch_failed", MapsKt.mapOf(TuplesKt.m309to(str, domain2)));
            }
        }
        return Unit.INSTANCE;
    }
}
