package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import com.bitcoin.mwallet.core.models.slp.SlpId;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter$init$1", mo38000f = "SendAmountSelectionPresenter.kt", mo38001i = {1, 2}, mo38002l = {200, 202, 205}, mo38003m = "invokeSuspend", mo38004n = {"walletId", "walletId"}, mo38005s = {"L$0", "L$0"})
/* compiled from: SendAmountSelectionPresenter.kt */
final class SendAmountSelectionPresenter$init$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SlpId $tokenId;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f298p$;
    final /* synthetic */ SendAmountSelectionPresenter this$0;

    SendAmountSelectionPresenter$init$1(SendAmountSelectionPresenter sendAmountSelectionPresenter, SlpId slpId, Continuation continuation) {
        this.this$0 = sendAmountSelectionPresenter;
        this.$tokenId = slpId;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SendAmountSelectionPresenter$init$1 sendAmountSelectionPresenter$init$1 = new SendAmountSelectionPresenter$init$1(this.this$0, this.$tokenId, continuation);
        sendAmountSelectionPresenter$init$1.f298p$ = (CoroutineScope) obj;
        return sendAmountSelectionPresenter$init$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SendAmountSelectionPresenter$init$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c1 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c2  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0036
            if (r1 == r4) goto L_0x0032
            if (r1 == r3) goto L_0x0026
            if (r1 != r2) goto L_0x001e
            java.lang.Object r0 = r7.L$1
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r0 = (com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter) r0
            java.lang.Object r1 = r7.L$0
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x00c4
        L_0x001e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0026:
            java.lang.Object r1 = r7.L$1
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r1 = (com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter) r1
            java.lang.Object r3 = r7.L$0
            com.bitcoin.mwallet.core.models.wallet.WalletId r3 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r3
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0075
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x004c
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.CoroutineScope r8 = r7.f298p$
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r8 = r7.this$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r8 = r8.slpRepository
            com.bitcoin.mwallet.core.models.slp.SlpId r1 = r7.$tokenId
            r7.label = r4
            java.lang.Object r8 = r8.getAllWalletIdsforToken(r1, r7)
            if (r8 != r0) goto L_0x004c
            return r0
        L_0x004c:
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r8 = kotlin.collections.CollectionsKt.first(r8)
            com.bitcoin.mwallet.core.models.wallet.WalletId r8 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r8
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r1 = r7.this$0
            androidx.lifecycle.MutableLiveData r1 = r1.getSelectedWalletId()
            r1.setValue(r8)
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r1 = r7.this$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r4 = r1.slpRepository
            com.bitcoin.mwallet.core.models.slp.SlpId r5 = r7.$tokenId
            r7.L$0 = r8
            r7.L$1 = r1
            r7.label = r3
            java.lang.Object r3 = r4.getToken(r8, r5, r7)
            if (r3 != r0) goto L_0x0072
            return r0
        L_0x0072:
            r6 = r3
            r3 = r8
            r8 = r6
        L_0x0075:
            com.bitcoin.mwallet.core.models.slp.Slp r8 = (com.bitcoin.mwallet.core.models.slp.Slp) r8
            r1.setTokenInfo(r8)
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r8 = r7.this$0
            com.bitcoin.mwallet.core.models.slp.Slp r1 = r8.getTokenInfo()
            r4 = 0
            if (r1 == 0) goto L_0x008c
            int r1 = r1.getDecimals()
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            goto L_0x008d
        L_0x008c:
            r1 = r4
        L_0x008d:
            if (r1 != 0) goto L_0x0092
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0092:
            int r1 = r1.intValue()
            r8.setMAX_TOKEN_DECIMALS(r1)
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r8 = r7.this$0
            com.bitcoin.mwallet.core.models.slp.Slp r1 = r8.getTokenInfo()
            if (r1 == 0) goto L_0x00a5
            java.lang.String r4 = r1.getTicker()
        L_0x00a5:
            if (r4 != 0) goto L_0x00aa
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00aa:
            r8.setTokenTicker(r4)
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r8 = r7.this$0
            com.bitcoin.mwallet.core.repositories.SlpRepository r1 = r8.slpRepository
            com.bitcoin.mwallet.core.models.slp.SlpId r4 = r7.$tokenId
            r7.L$0 = r3
            r7.L$1 = r8
            r7.label = r2
            java.lang.Object r1 = r1.getTokenAmount(r3, r4, r7)
            if (r1 != r0) goto L_0x00c2
            return r0
        L_0x00c2:
            r0 = r8
            r8 = r1
        L_0x00c4:
            java.math.BigDecimal r8 = (java.math.BigDecimal) r8
            r0.setTargetWalletTokenBalance(r8)
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r8 = r7.this$0
            androidx.lifecycle.MutableLiveData r8 = r8.getPrimaryEntryType()
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$EntryType r0 = com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType.TOKEN
            r8.setValue(r0)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter$init$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
