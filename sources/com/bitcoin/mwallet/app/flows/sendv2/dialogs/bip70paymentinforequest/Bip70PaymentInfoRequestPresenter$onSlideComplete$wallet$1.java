package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter$onSlideComplete$wallet$1", mo38000f = "Bip70PaymentInfoRequestPresenter.kt", mo38001i = {}, mo38002l = {90}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: Bip70PaymentInfoRequestPresenter.kt */
final class Bip70PaymentInfoRequestPresenter$onSlideComplete$wallet$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super C1261Wallet>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f274p$;
    final /* synthetic */ Bip70PaymentInfoRequestPresenter this$0;

    Bip70PaymentInfoRequestPresenter$onSlideComplete$wallet$1(Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter, Continuation continuation) {
        this.this$0 = bip70PaymentInfoRequestPresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Bip70PaymentInfoRequestPresenter$onSlideComplete$wallet$1 bip70PaymentInfoRequestPresenter$onSlideComplete$wallet$1 = new Bip70PaymentInfoRequestPresenter$onSlideComplete$wallet$1(this.this$0, continuation);
        bip70PaymentInfoRequestPresenter$onSlideComplete$wallet$1.f274p$ = (CoroutineScope) obj;
        return bip70PaymentInfoRequestPresenter$onSlideComplete$wallet$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((Bip70PaymentInfoRequestPresenter$onSlideComplete$wallet$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f274p$;
            GetWalletInteractor access$getWalletInteractor$p = this.this$0.walletInteractor;
            Object value = this.this$0.getSelectedWalletId().getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(value, "selectedWalletId.value!!");
            WalletId walletId = (WalletId) value;
            this.label = 1;
            obj = access$getWalletInteractor$p.getWallet(walletId, this);
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
