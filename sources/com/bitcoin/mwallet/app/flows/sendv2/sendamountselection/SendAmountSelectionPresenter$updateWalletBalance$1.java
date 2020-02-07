package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Ljava/math/BigDecimal;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter$updateWalletBalance$1", mo38000f = "SendAmountSelectionPresenter.kt", mo38001i = {}, mo38002l = {68}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: SendAmountSelectionPresenter.kt */
final class SendAmountSelectionPresenter$updateWalletBalance$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BigDecimal>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f302p$;
    final /* synthetic */ SendAmountSelectionPresenter this$0;

    SendAmountSelectionPresenter$updateWalletBalance$1(SendAmountSelectionPresenter sendAmountSelectionPresenter, Continuation continuation) {
        this.this$0 = sendAmountSelectionPresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SendAmountSelectionPresenter$updateWalletBalance$1 sendAmountSelectionPresenter$updateWalletBalance$1 = new SendAmountSelectionPresenter$updateWalletBalance$1(this.this$0, continuation);
        sendAmountSelectionPresenter$updateWalletBalance$1.f302p$ = (CoroutineScope) obj;
        return sendAmountSelectionPresenter$updateWalletBalance$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SendAmountSelectionPresenter$updateWalletBalance$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f302p$;
            SlpRepository access$getSlpRepository$p = this.this$0.slpRepository;
            Object value = this.this$0.getSelectedWalletId().getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(value, "selectedWalletId.value!!");
            WalletId walletId = (WalletId) value;
            Slp tokenInfo = this.this$0.getTokenInfo();
            SlpId tokenId = tokenInfo != null ? tokenInfo.getTokenId() : null;
            if (tokenId == null) {
                Intrinsics.throwNpe();
            }
            this.label = 1;
            obj = access$getSlpRepository$p.getTokenAmount(walletId, tokenId, this);
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
