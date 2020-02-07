package com.bitcoin.mwallet.app.flows.sendv2.selectaddress;

import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
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
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$sendNativeCoin$1", mo38000f = "SelectAddressPresenter.kt", mo38001i = {1, 2, 2}, mo38002l = {196, 201, 221}, mo38003m = "invokeSuspend", mo38004n = {"walletData", "walletData", "utxoSelection"}, mo38005s = {"L$0", "L$0", "L$1"})
/* compiled from: SelectAddressPresenter.kt */
final class SelectAddressPresenter$sendNativeCoin$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ C1261Wallet $walletCandidate;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    boolean Z$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f296p$;
    final /* synthetic */ SelectAddressPresenter this$0;

    SelectAddressPresenter$sendNativeCoin$1(SelectAddressPresenter selectAddressPresenter, C1261Wallet wallet, Continuation continuation) {
        this.this$0 = selectAddressPresenter;
        this.$walletCandidate = wallet;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SelectAddressPresenter$sendNativeCoin$1 selectAddressPresenter$sendNativeCoin$1 = new SelectAddressPresenter$sendNativeCoin$1(this.this$0, this.$walletCandidate, continuation);
        selectAddressPresenter$sendNativeCoin$1.f296p$ = (CoroutineScope) obj;
        return selectAddressPresenter$sendNativeCoin$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SelectAddressPresenter$sendNativeCoin$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00eb A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01ad  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0209  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r28) {
        /*
            r27 = this;
            r6 = r27
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r6.label
            r1 = 3
            r2 = 2
            r9 = 0
            r3 = 1
            if (r0 == 0) goto L_0x005b
            if (r0 == r3) goto L_0x0037
            if (r0 == r2) goto L_0x002b
            if (r0 != r1) goto L_0x0023
            java.lang.Object r0 = r6.L$1
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r0 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r0
            java.lang.Object r0 = r6.L$0
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r0 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r0
            kotlin.ResultKt.throwOnFailure(r28)
            r0 = r28
            goto L_0x019c
        L_0x0023:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x002b:
            java.lang.Object r0 = r6.L$0
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r0 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r0
            kotlin.ResultKt.throwOnFailure(r28)
            r2 = r28
        L_0x0034:
            r4 = r0
            goto L_0x00ec
        L_0x0037:
            boolean r0 = r6.Z$0
            java.lang.Object r4 = r6.L$6
            com.bitcoin.mwallet.core.models.Coin r4 = (com.bitcoin.mwallet.core.models.Coin) r4
            java.lang.Object r5 = r6.L$5
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r10 = r6.L$4
            com.bitcoin.mwallet.core.models.credential.CredentialId r10 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r10
            java.lang.Object r11 = r6.L$3
            com.bitcoin.bitcoink.DerivationPath r11 = (com.bitcoin.bitcoink.DerivationPath) r11
            java.lang.Object r12 = r6.L$2
            com.bitcoin.bitcoink.Network r12 = (com.bitcoin.bitcoink.Network) r12
            java.lang.Object r13 = r6.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r13 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r13
            java.lang.Object r14 = r6.L$0
            com.bitcoin.mwallet.core.models.wallet.WalletId r14 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r14
            kotlin.ResultKt.throwOnFailure(r28)
            r8 = r28
            goto L_0x00b5
        L_0x005b:
            kotlin.ResultKt.throwOnFailure(r28)
            kotlinx.coroutines.CoroutineScope r0 = r6.f296p$
            com.bitcoin.mwallet.core.models.wallet.Wallet r0 = r6.$walletCandidate
            com.bitcoin.mwallet.core.models.wallet.WalletId r14 = r0.getPri_key()
            com.bitcoin.mwallet.core.models.wallet.Wallet r0 = r6.$walletCandidate
            com.bitcoin.mwallet.core.models.wallet.WalletId r13 = r0.getId()
            com.bitcoin.mwallet.core.models.wallet.Wallet r0 = r6.$walletCandidate
            com.bitcoin.bitcoink.Network r12 = r0.getNetwork()
            com.bitcoin.mwallet.core.models.wallet.Wallet r0 = r6.$walletCandidate
            com.bitcoin.bitcoink.DerivationPath r11 = r0.getPath()
            com.bitcoin.mwallet.core.models.wallet.Wallet r0 = r6.$walletCandidate
            com.bitcoin.mwallet.core.models.credential.CredentialId r10 = r0.getCredentialId()
            com.bitcoin.mwallet.core.models.wallet.Wallet r0 = r6.$walletCandidate
            java.lang.String r5 = r0.getName()
            com.bitcoin.mwallet.core.models.wallet.Wallet r0 = r6.$walletCandidate
            com.bitcoin.mwallet.core.models.Coin r4 = r0.getCoin()
            com.bitcoin.mwallet.core.models.wallet.Wallet r0 = r6.$walletCandidate
            boolean r0 = r0.isMultiSig()
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r15 = r6.this$0
            com.bitcoin.mwallet.core.repositories.UtxoRepository r15 = r15.getUtxoRepository()
            com.bitcoin.mwallet.core.models.wallet.Wallet r8 = r6.$walletCandidate
            com.bitcoin.mwallet.core.models.wallet.WalletId r8 = r8.getId()
            r6.L$0 = r14
            r6.L$1 = r13
            r6.L$2 = r12
            r6.L$3 = r11
            r6.L$4 = r10
            r6.L$5 = r5
            r6.L$6 = r4
            r6.Z$0 = r0
            r6.label = r3
            java.lang.Object r8 = r15.getWalletUtxos(r8, r6)
            if (r8 != r7) goto L_0x00b5
            return r7
        L_0x00b5:
            r24 = r0
            r23 = r4
            r22 = r5
            r21 = r10
            r20 = r11
            r19 = r12
            r18 = r13
            r17 = r14
            r25 = r8
            com.bitcoin.mwallet.core.models.tx.utxo.WalletUtxos r25 = (com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos) r25
            r26 = 0
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r0 = new com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData
            r16 = r0
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r4 = r6.this$0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r5 = r4.getBitcoinUriContent()
            com.bitcoin.mwallet.core.models.Coin r5 = r5.getCoin()
            if (r5 != 0) goto L_0x00e1
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00e1:
            r6.L$0 = r0
            r6.label = r2
            java.lang.Object r2 = r4.getSatoshiPerByte(r5, r6)
            if (r2 != r7) goto L_0x0034
            return r7
        L_0x00ec:
            r19 = r2
            java.math.BigDecimal r19 = (java.math.BigDecimal) r19
            if (r19 == 0) goto L_0x0117
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r0 = r6.this$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r16 = r0.createTxInteractor
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r0 = r6.this$0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r0 = r0.getBitcoinUriContent()
            com.bitcoin.bitcoink.tx.Satoshis r18 = r0.getAmount()
            if (r18 != 0) goto L_0x0107
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0107:
            r20 = 0
            r21 = 0
            r23 = 24
            r24 = 0
            r17 = r4
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r0 = com.bitcoin.mwallet.core.interactors.CreateTxInteractor.selectUtxos$default(r16, r17, r18, r19, r20, r21, r23, r24)
            r2 = r0
            goto L_0x0118
        L_0x0117:
            r2 = 0
        L_0x0118:
            if (r2 != 0) goto L_0x0124
            java.lang.Object[] r0 = new java.lang.Object[r9]
            java.lang.String r1 = "Illegal state"
            timber.log.Timber.m426d(r1, r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0124:
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection$Error r0 = r2.getError()
            if (r0 == 0) goto L_0x0164
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection$Error r0 = r2.getError()
            if (r0 != 0) goto L_0x0132
            goto L_0x0248
        L_0x0132:
            int[] r1 = com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter.WhenMappings.$EnumSwitchMapping$2
            int r0 = r0.ordinal()
            r0 = r1[r0]
            if (r0 == r3) goto L_0x013e
            goto L_0x0248
        L_0x013e:
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r0 = r6.this$0
            androidx.lifecycle.MutableLiveData r0 = r0.getError()
            com.bitcoin.mwallet.app.android.Event r1 = new com.bitcoin.mwallet.app.android.Event
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r2 = r6.this$0
            android.content.Context r2 = r2.getContext()
            android.content.res.Resources r2 = r2.getResources()
            r3 = 2131951954(0x7f130152, float:1.9540337E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.String r3 = "context.resources.getStr…error_insufficient_funds)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            r1.<init>(r2)
            r0.setValue(r1)
            goto L_0x0248
        L_0x0164:
            java.lang.String r0 = r2.toString()
            java.lang.Object[] r3 = new java.lang.Object[r9]
            timber.log.Timber.m426d(r0, r3)
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r0 = r6.this$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r0 = r0.createTxInteractor
            r3 = 0
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r5 = r6.this$0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r5 = r5.getBitcoinUriContent()
            com.bitcoin.mwallet.core.models.address.AddressAndOriginalText r5 = r5.getAddress()
            if (r5 == 0) goto L_0x0185
            com.bitcoin.bitcoink.address.Address r5 = r5.getParsed()
            goto L_0x0186
        L_0x0185:
            r5 = 0
        L_0x0186:
            if (r5 != 0) goto L_0x018b
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x018b:
            r6.L$0 = r4
            r6.L$1 = r2
            r6.label = r1
            r1 = r4
            r4 = r5
            r5 = r27
            java.lang.Object r0 = r0.send(r1, r2, r3, r4, r5)
            if (r0 != r7) goto L_0x019c
            return r7
        L_0x019c:
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse r0 = (com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse) r0
            java.lang.String r1 = r0.toString()
            java.lang.Object[] r2 = new java.lang.Object[r9]
            timber.log.Timber.m426d(r1, r2)
            com.bitcoin.bitcoink.tx.TxId r1 = r0.getTxId()
            if (r1 == 0) goto L_0x0209
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r0 = r6.this$0
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressRouter r0 = com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter.access$getRouter$p(r0)
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel r2 = new com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendWhatModel
            r17 = 0
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r3 = r6.this$0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r3 = r3.getBitcoinUriContent()
            com.bitcoin.bitcoink.tx.Satoshis r3 = r3.getAmount()
            if (r3 == 0) goto L_0x01ca
            java.math.BigDecimal r8 = r3.getCoins()
            r18 = r8
            goto L_0x01cc
        L_0x01ca:
            r18 = 0
        L_0x01cc:
            r19 = 0
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r3 = r6.this$0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r3 = r3.getBitcoinUriContent()
            com.bitcoin.mwallet.core.models.Coin r20 = r3.getCoin()
            if (r20 != 0) goto L_0x01dd
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x01dd:
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r3 = r6.this$0
            java.util.Currency r3 = r3.getCurrency()
            java.lang.String r3 = r3.getCurrencyCode()
            java.lang.String r4 = "currency.currencyCode"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            com.bitcoin.mwallet.core.models.wallet.Wallet r4 = r6.$walletCandidate
            com.bitcoin.mwallet.core.models.wallet.WalletId r22 = r4.getId()
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r4 = r6.this$0
            com.bitcoin.mwallet.core.models.BitcoinUriContent r23 = r4.getBitcoinUriContent()
            r24 = 0
            r25 = 128(0x80, float:1.794E-43)
            r26 = 0
            r16 = r2
            r21 = r3
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            r0.toSendSuccess(r1, r2)
            goto L_0x0248
        L_0x0209:
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse$ErrorType r1 = r0.getErrorType()
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse$ErrorType r2 = com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse.ErrorType.ZION_CANCELLED
            if (r1 != r2) goto L_0x0236
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r0 = r6.this$0
            androidx.lifecycle.MutableLiveData r0 = r0.getError()
            com.bitcoin.mwallet.app.android.Event r1 = new com.bitcoin.mwallet.app.android.Event
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r2 = r6.this$0
            android.content.Context r2 = r2.getContext()
            android.content.res.Resources r2 = r2.getResources()
            r3 = 2131951955(0x7f130153, float:1.954034E38)
            java.lang.String r2 = r2.getString(r3)
            java.lang.String r3 = "context.resources.getStr…rror_zion_sign_cancelled)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            r1.<init>(r2)
            r0.setValue(r1)
            goto L_0x0248
        L_0x0236:
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r1 = r6.this$0
            androidx.lifecycle.MutableLiveData r1 = r1.getError()
            com.bitcoin.mwallet.app.android.Event r2 = new com.bitcoin.mwallet.app.android.Event
            java.lang.String r0 = r0.baseResponseToString()
            r2.<init>(r0)
            r1.setValue(r2)
        L_0x0248:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$sendNativeCoin$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
