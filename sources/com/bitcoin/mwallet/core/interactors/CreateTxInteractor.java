package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.WalletSlpUtxos;
import com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection;
import com.bitcoin.mwallet.core.models.slp.Slp;
import java.math.BigDecimal;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011J9\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001aJ3\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u00132\b\u0010\u001e\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001f\u001a\u00020 H@ø\u0001\u0000¢\u0006\u0002\u0010!J7\u0010\"\u001a\u00020#2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'H@ø\u0001\u0000¢\u0006\u0002\u0010)R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/CreateTxInteractor;", "", "sendTxInteractor", "Lcom/bitcoin/mwallet/core/interactors/SendTxInteractor;", "getAddressInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;", "utxoSelector", "Lcom/bitcoin/mwallet/core/interactors/UtxoSelector;", "(Lcom/bitcoin/mwallet/core/interactors/SendTxInteractor;Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;Lcom/bitcoin/mwallet/core/interactors/UtxoSelector;)V", "selectSlpUtxos", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;", "walletData", "Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;", "sendTokenAmount", "Ljava/math/BigDecimal;", "satoshiPerByte", "token", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "selectUtxos", "Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "sendAmount", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "outputs", "", "extraFees", "", "(Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;Lcom/bitcoin/bitcoink/tx/Satoshis;Ljava/math/BigDecimal;Ljava/lang/Integer;J)Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "send", "Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse;", "utxoSelection", "slpUtxoSelection", "toAddress", "Lcom/bitcoin/bitcoink/address/Address;", "(Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;Lcom/bitcoin/bitcoink/address/Address;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendBip70", "Lcom/bitcoin/mwallet/core/services/tx/Bip70BroadcastTxResponse;", "bip70Url", "", "bip70Outputs", "", "Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentOutput;", "(Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CreateTxInteractor.kt */
public final class CreateTxInteractor {
    private final GetAddressInteractor getAddressInteractor;
    private final SendTxInteractor sendTxInteractor;
    private final UtxoSelector utxoSelector;

    public CreateTxInteractor(@NotNull SendTxInteractor sendTxInteractor2, @NotNull GetAddressInteractor getAddressInteractor2, @NotNull UtxoSelector utxoSelector2) {
        Intrinsics.checkParameterIsNotNull(sendTxInteractor2, "sendTxInteractor");
        Intrinsics.checkParameterIsNotNull(getAddressInteractor2, "getAddressInteractor");
        Intrinsics.checkParameterIsNotNull(utxoSelector2, "utxoSelector");
        this.sendTxInteractor = sendTxInteractor2;
        this.getAddressInteractor = getAddressInteractor2;
        this.utxoSelector = utxoSelector2;
    }

    @NotNull
    public static /* synthetic */ UtxoSelection selectUtxos$default(CreateTxInteractor createTxInteractor, SendWalletData sendWalletData, Satoshis satoshis, BigDecimal bigDecimal, Integer num, long j, int i, Object obj) {
        if ((i & 8) != 0) {
            num = null;
        }
        Integer num2 = num;
        if ((i & 16) != 0) {
            j = 0;
        }
        return createTxInteractor.selectUtxos(sendWalletData, satoshis, bigDecimal, num2, j);
    }

    @NotNull
    public final UtxoSelection selectUtxos(@NotNull SendWalletData sendWalletData, @NotNull Satoshis satoshis, @NotNull BigDecimal bigDecimal, @Nullable Integer num, long j) {
        Satoshis satoshis2 = satoshis;
        SendWalletData sendWalletData2 = sendWalletData;
        Intrinsics.checkParameterIsNotNull(sendWalletData, "walletData");
        Intrinsics.checkParameterIsNotNull(satoshis, "sendAmount");
        BigDecimal bigDecimal2 = bigDecimal;
        Intrinsics.checkParameterIsNotNull(bigDecimal, "satoshiPerByte");
        boolean areEqual = Intrinsics.areEqual((Object) sendWalletData.getUtxos().getBalance(), (Object) satoshis);
        UtxoSelection select = this.utxoSelector.select(sendWalletData.getCoin(), sendWalletData.getUtxos().getUtxos(), satoshis2, areEqual, bigDecimal2, num, j);
        StringBuilder sb = new StringBuilder();
        sb.append("sendMax=");
        sb.append(areEqual);
        sb.append(' ');
        sb.append(select);
        Timber.m426d(sb.toString(), new Object[0]);
        return select;
    }

    @NotNull
    public final SlpUtxoSelection selectSlpUtxos(@NotNull SendWalletData sendWalletData, @NotNull BigDecimal bigDecimal, @NotNull BigDecimal bigDecimal2, @NotNull Slp slp) {
        Intrinsics.checkParameterIsNotNull(sendWalletData, "walletData");
        Intrinsics.checkParameterIsNotNull(bigDecimal, "sendTokenAmount");
        Intrinsics.checkParameterIsNotNull(bigDecimal2, "satoshiPerByte");
        Intrinsics.checkParameterIsNotNull(slp, "token");
        UtxoSelector utxoSelector2 = this.utxoSelector;
        List utxos = sendWalletData.getUtxos().getUtxos();
        WalletSlpUtxos slpUtxos = sendWalletData.getSlpUtxos();
        List utxos2 = slpUtxos != null ? slpUtxos.getUtxos() : null;
        if (utxos2 == null) {
            Intrinsics.throwNpe();
        }
        SlpUtxoSelection select = utxoSelector2.select(utxos, utxos2, slp, bigDecimal, bigDecimal2);
        Timber.m426d(String.valueOf(select), new Object[0]);
        return select;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x016f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x01b8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x01b9 A[PHI: r1 
      PHI: (r1v2 java.lang.Object) = (r1v4 java.lang.Object), (r1v1 java.lang.Object) binds: [B:41:0x01b6, B:13:0x0036] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object sendBip70(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r25, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection r26, @org.jetbrains.annotations.NotNull java.lang.String r27, @org.jetbrains.annotations.NotNull java.util.List<com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentOutput> r28, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.services.p010tx.Bip70BroadcastTxResponse> r29) {
        /*
            r24 = this;
            r0 = r24
            r1 = r29
            boolean r2 = r1 instanceof com.bitcoin.mwallet.core.interactors.CreateTxInteractor$sendBip70$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor$sendBip70$1 r2 = (com.bitcoin.mwallet.core.interactors.CreateTxInteractor$sendBip70$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor$sendBip70$1 r2 = new com.bitcoin.mwallet.core.interactors.CreateTxInteractor$sendBip70$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 5
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = 1
            if (r4 == 0) goto L_0x00e7
            if (r4 == r9) goto L_0x00cb
            if (r4 == r8) goto L_0x00b1
            if (r4 == r7) goto L_0x0086
            if (r4 == r6) goto L_0x0063
            if (r4 != r5) goto L_0x005b
            java.lang.Object r3 = r2.L$7
            com.bitcoin.mwallet.core.models.address.AddressAndPath r3 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r3
            java.lang.Object r3 = r2.L$6
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest r3 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest) r3
            java.lang.Object r3 = r2.L$5
            com.bitcoin.mwallet.core.models.address.AddressAndPath r3 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r3
            java.lang.Object r3 = r2.L$4
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r2.L$3
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r2.L$2
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r3 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r3
            java.lang.Object r3 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r3 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r3
            java.lang.Object r2 = r2.L$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r2 = (com.bitcoin.mwallet.core.interactors.CreateTxInteractor) r2
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x01b9
        L_0x005b:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0063:
            java.lang.Object r4 = r2.L$6
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest r4 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest) r4
            java.lang.Object r6 = r2.L$5
            com.bitcoin.mwallet.core.models.address.AddressAndPath r6 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r6
            java.lang.Object r7 = r2.L$4
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r8 = r2.L$3
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r9 = r2.L$2
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r9 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r9
            java.lang.Object r10 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r10 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r10
            java.lang.Object r11 = r2.L$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r11 = (com.bitcoin.mwallet.core.interactors.CreateTxInteractor) r11
            kotlin.ResultKt.throwOnFailure(r1)
            r12 = r4
            r4 = r6
            goto L_0x019c
        L_0x0086:
            java.lang.Object r4 = r2.L$6
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest r4 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest) r4
            java.lang.Object r7 = r2.L$5
            com.bitcoin.mwallet.core.models.address.AddressAndPath r7 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r7
            java.lang.Object r8 = r2.L$4
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r9 = r2.L$3
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r10 = r2.L$2
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r10 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r10
            java.lang.Object r11 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r11 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r11
            java.lang.Object r12 = r2.L$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r12 = (com.bitcoin.mwallet.core.interactors.CreateTxInteractor) r12
            kotlin.ResultKt.throwOnFailure(r1)
            r23 = r12
            r12 = r4
            r4 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r11
            r11 = r23
            goto L_0x0178
        L_0x00b1:
            java.lang.Object r4 = r2.L$4
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r8 = r2.L$3
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r9 = r2.L$2
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r9 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r9
            java.lang.Object r10 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r10 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r10
            java.lang.Object r11 = r2.L$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r11 = (com.bitcoin.mwallet.core.interactors.CreateTxInteractor) r11
            kotlin.ResultKt.throwOnFailure(r1)
            r13 = r8
            goto L_0x0131
        L_0x00cb:
            java.lang.Object r4 = r2.L$4
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r9 = r2.L$3
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r10 = r2.L$2
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r10 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r10
            java.lang.Object r11 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r11 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r11
            java.lang.Object r12 = r2.L$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r12 = (com.bitcoin.mwallet.core.interactors.CreateTxInteractor) r12
            kotlin.ResultKt.throwOnFailure(r1)
            r13 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            goto L_0x0111
        L_0x00e7:
            kotlin.ResultKt.throwOnFailure(r1)
            com.bitcoin.mwallet.core.interactors.GetAddressInteractor r1 = r0.getAddressInteractor
            com.bitcoin.mwallet.core.models.wallet.WalletId r4 = r25.getWalletId()
            com.bitcoin.mwallet.core.models.address.AddressPurpose r10 = com.bitcoin.mwallet.core.models.address.AddressPurpose.CHANGE
            r2.L$0 = r0
            r11 = r25
            r2.L$1 = r11
            r12 = r26
            r2.L$2 = r12
            r13 = r27
            r2.L$3 = r13
            r14 = r28
            r2.L$4 = r14
            r2.label = r9
            java.lang.Object r1 = r1.getNextAvailableAddress(r4, r10, r2)
            if (r1 != r3) goto L_0x010d
            return r3
        L_0x010d:
            r10 = r11
            r9 = r12
            r4 = r14
            r11 = r0
        L_0x0111:
            com.bitcoin.mwallet.core.models.address.AddressAndPath r1 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r1
            if (r1 == 0) goto L_0x0116
            goto L_0x0133
        L_0x0116:
            com.bitcoin.mwallet.core.interactors.GetAddressInteractor r1 = r11.getAddressInteractor
            com.bitcoin.mwallet.core.models.wallet.WalletId r12 = r10.getWalletId()
            com.bitcoin.mwallet.core.models.address.AddressPurpose r14 = com.bitcoin.mwallet.core.models.address.AddressPurpose.CHANGE
            r2.L$0 = r11
            r2.L$1 = r10
            r2.L$2 = r9
            r2.L$3 = r13
            r2.L$4 = r4
            r2.label = r8
            java.lang.Object r1 = r1.getCurrentAddress(r12, r14, r2)
            if (r1 != r3) goto L_0x0131
            return r3
        L_0x0131:
            com.bitcoin.mwallet.core.models.address.AddressAndPath r1 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r1
        L_0x0133:
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest r8 = new com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest
            com.bitcoin.mwallet.core.models.wallet.WalletId r15 = r10.getWalletId()
            com.bitcoin.bitcoink.Network r17 = r10.getNetwork()
            com.bitcoin.mwallet.core.models.credential.CredentialId r18 = r10.getCredentialId()
            com.bitcoin.bitcoink.DerivationPath r19 = r10.getWalletPath()
            r14 = r8
            r16 = r9
            r20 = r1
            r21 = r13
            r22 = r4
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22)
            com.bitcoin.mwallet.core.interactors.GetAddressInteractor r12 = r11.getAddressInteractor
            com.bitcoin.mwallet.core.models.wallet.WalletId r14 = r10.getWalletId()
            com.bitcoin.mwallet.core.models.address.AddressPurpose r15 = com.bitcoin.mwallet.core.models.address.AddressPurpose.CHANGE
            r2.L$0 = r11
            r2.L$1 = r10
            r2.L$2 = r9
            r2.L$3 = r13
            r2.L$4 = r4
            r2.L$5 = r1
            r2.L$6 = r8
            r2.label = r7
            java.lang.Object r7 = r12.getNextAvailableAddress(r14, r15, r2)
            if (r7 != r3) goto L_0x0170
            return r3
        L_0x0170:
            r12 = r8
            r8 = r13
            r23 = r4
            r4 = r1
            r1 = r7
            r7 = r23
        L_0x0178:
            com.bitcoin.mwallet.core.models.address.AddressAndPath r1 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r1
            if (r1 == 0) goto L_0x017d
            goto L_0x019e
        L_0x017d:
            com.bitcoin.mwallet.core.interactors.GetAddressInteractor r1 = r11.getAddressInteractor
            com.bitcoin.mwallet.core.models.wallet.WalletId r13 = r10.getWalletId()
            com.bitcoin.mwallet.core.models.address.AddressPurpose r14 = com.bitcoin.mwallet.core.models.address.AddressPurpose.CHANGE
            r2.L$0 = r11
            r2.L$1 = r10
            r2.L$2 = r9
            r2.L$3 = r8
            r2.L$4 = r7
            r2.L$5 = r4
            r2.L$6 = r12
            r2.label = r6
            java.lang.Object r1 = r1.getCurrentAddress(r13, r14, r2)
            if (r1 != r3) goto L_0x019c
            return r3
        L_0x019c:
            com.bitcoin.mwallet.core.models.address.AddressAndPath r1 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r1
        L_0x019e:
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r6 = r11.sendTxInteractor
            r2.L$0 = r11
            r2.L$1 = r10
            r2.L$2 = r9
            r2.L$3 = r8
            r2.L$4 = r7
            r2.L$5 = r4
            r2.L$6 = r12
            r2.L$7 = r1
            r2.label = r5
            java.lang.Object r1 = r6.sendBip70Tx(r12, r1, r2)
            if (r1 != r3) goto L_0x01b9
            return r3
        L_0x01b9:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.CreateTxInteractor.sendBip70(com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData, com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection, java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01e6 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x01e7 A[PHI: r1 
      PHI: (r1v2 java.lang.Object) = (r1v5 java.lang.Object), (r1v1 java.lang.Object) binds: [B:44:0x01e4, B:13:0x0036] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object send(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r24, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection r25, @org.jetbrains.annotations.Nullable com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection r26, @org.jetbrains.annotations.NotNull com.bitcoin.bitcoink.address.Address r27, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse> r28) {
        /*
            r23 = this;
            r0 = r23
            r1 = r28
            boolean r2 = r1 instanceof com.bitcoin.mwallet.core.interactors.CreateTxInteractor$send$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor$send$1 r2 = (com.bitcoin.mwallet.core.interactors.CreateTxInteractor$send$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor$send$1 r2 = new com.bitcoin.mwallet.core.interactors.CreateTxInteractor$send$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 5
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = 1
            if (r4 == 0) goto L_0x00ea
            if (r4 == r9) goto L_0x00ce
            if (r4 == r8) goto L_0x00b4
            if (r4 == r7) goto L_0x0086
            if (r4 == r6) goto L_0x0063
            if (r4 != r5) goto L_0x005b
            java.lang.Object r3 = r2.L$7
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r3 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest) r3
            java.lang.Object r3 = r2.L$6
            com.bitcoin.mwallet.core.models.address.AddressAndPath r3 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r3
            java.lang.Object r3 = r2.L$5
            com.bitcoin.mwallet.core.models.address.AddressAndPath r3 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r3
            java.lang.Object r3 = r2.L$4
            com.bitcoin.bitcoink.address.Address r3 = (com.bitcoin.bitcoink.address.Address) r3
            java.lang.Object r3 = r2.L$3
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r3 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection) r3
            java.lang.Object r3 = r2.L$2
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r3 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r3
            java.lang.Object r3 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r3 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r3
            java.lang.Object r2 = r2.L$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r2 = (com.bitcoin.mwallet.core.interactors.CreateTxInteractor) r2
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x01e7
        L_0x005b:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0063:
            java.lang.Object r4 = r2.L$6
            com.bitcoin.mwallet.core.models.address.AddressAndPath r4 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r4
            java.lang.Object r4 = r2.L$5
            com.bitcoin.mwallet.core.models.address.AddressAndPath r4 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r4
            java.lang.Object r6 = r2.L$4
            com.bitcoin.bitcoink.address.Address r6 = (com.bitcoin.bitcoink.address.Address) r6
            java.lang.Object r7 = r2.L$3
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r7 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection) r7
            java.lang.Object r8 = r2.L$2
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r8 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r8
            java.lang.Object r9 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r9 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r9
            java.lang.Object r10 = r2.L$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r10 = (com.bitcoin.mwallet.core.interactors.CreateTxInteractor) r10
            kotlin.ResultKt.throwOnFailure(r1)
            r11 = r4
            r4 = r6
            goto L_0x0185
        L_0x0086:
            java.lang.Object r4 = r2.L$6
            com.bitcoin.mwallet.core.models.address.AddressAndPath r4 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r4
            java.lang.Object r7 = r2.L$5
            com.bitcoin.mwallet.core.models.address.AddressAndPath r7 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r7
            java.lang.Object r8 = r2.L$4
            com.bitcoin.bitcoink.address.Address r8 = (com.bitcoin.bitcoink.address.Address) r8
            java.lang.Object r9 = r2.L$3
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r9 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection) r9
            java.lang.Object r10 = r2.L$2
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r10 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r10
            java.lang.Object r11 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r11 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r11
            java.lang.Object r12 = r2.L$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r12 = (com.bitcoin.mwallet.core.interactors.CreateTxInteractor) r12
            kotlin.ResultKt.throwOnFailure(r1)
            r21 = r12
            r12 = r4
            r4 = r8
            r8 = r10
            r10 = r21
            r22 = r11
            r11 = r7
            r7 = r9
            r9 = r22
            goto L_0x0161
        L_0x00b4:
            java.lang.Object r4 = r2.L$4
            com.bitcoin.bitcoink.address.Address r4 = (com.bitcoin.bitcoink.address.Address) r4
            java.lang.Object r8 = r2.L$3
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r8 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection) r8
            java.lang.Object r9 = r2.L$2
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r9 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r9
            java.lang.Object r10 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r10 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r10
            java.lang.Object r11 = r2.L$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r11 = (com.bitcoin.mwallet.core.interactors.CreateTxInteractor) r11
            kotlin.ResultKt.throwOnFailure(r1)
            r13 = r8
            goto L_0x0134
        L_0x00ce:
            java.lang.Object r4 = r2.L$4
            com.bitcoin.bitcoink.address.Address r4 = (com.bitcoin.bitcoink.address.Address) r4
            java.lang.Object r9 = r2.L$3
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r9 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection) r9
            java.lang.Object r10 = r2.L$2
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r10 = (com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection) r10
            java.lang.Object r11 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData r11 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData) r11
            java.lang.Object r12 = r2.L$0
            com.bitcoin.mwallet.core.interactors.CreateTxInteractor r12 = (com.bitcoin.mwallet.core.interactors.CreateTxInteractor) r12
            kotlin.ResultKt.throwOnFailure(r1)
            r13 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            goto L_0x0114
        L_0x00ea:
            kotlin.ResultKt.throwOnFailure(r1)
            com.bitcoin.mwallet.core.interactors.GetAddressInteractor r1 = r0.getAddressInteractor
            com.bitcoin.mwallet.core.models.wallet.WalletId r4 = r24.getWalletId()
            com.bitcoin.mwallet.core.models.address.AddressPurpose r10 = com.bitcoin.mwallet.core.models.address.AddressPurpose.CHANGE
            r2.L$0 = r0
            r11 = r24
            r2.L$1 = r11
            r12 = r25
            r2.L$2 = r12
            r13 = r26
            r2.L$3 = r13
            r14 = r27
            r2.L$4 = r14
            r2.label = r9
            java.lang.Object r1 = r1.getNextAvailableAddress(r4, r10, r2)
            if (r1 != r3) goto L_0x0110
            return r3
        L_0x0110:
            r10 = r11
            r9 = r12
            r4 = r14
            r11 = r0
        L_0x0114:
            com.bitcoin.mwallet.core.models.address.AddressAndPath r1 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r1
            if (r1 == 0) goto L_0x0119
            goto L_0x0136
        L_0x0119:
            com.bitcoin.mwallet.core.interactors.GetAddressInteractor r1 = r11.getAddressInteractor
            com.bitcoin.mwallet.core.models.wallet.WalletId r12 = r10.getWalletId()
            com.bitcoin.mwallet.core.models.address.AddressPurpose r14 = com.bitcoin.mwallet.core.models.address.AddressPurpose.CHANGE
            r2.L$0 = r11
            r2.L$1 = r10
            r2.L$2 = r9
            r2.L$3 = r13
            r2.L$4 = r4
            r2.label = r8
            java.lang.Object r1 = r1.getCurrentAddress(r12, r14, r2)
            if (r1 != r3) goto L_0x0134
            return r3
        L_0x0134:
            com.bitcoin.mwallet.core.models.address.AddressAndPath r1 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r1
        L_0x0136:
            r8 = 0
            com.bitcoin.mwallet.core.models.address.AddressAndPath r8 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r8
            if (r13 == 0) goto L_0x0190
            com.bitcoin.mwallet.core.interactors.GetAddressInteractor r12 = r11.getAddressInteractor
            com.bitcoin.mwallet.core.models.wallet.WalletId r14 = r10.getWalletId()
            com.bitcoin.mwallet.core.models.address.AddressPurpose r15 = com.bitcoin.mwallet.core.models.address.AddressPurpose.CHANGE
            r2.L$0 = r11
            r2.L$1 = r10
            r2.L$2 = r9
            r2.L$3 = r13
            r2.L$4 = r4
            r2.L$5 = r1
            r2.L$6 = r8
            r2.label = r7
            java.lang.Object r7 = r12.getNextAvailableAddress(r14, r15, r2)
            if (r7 != r3) goto L_0x015a
            return r3
        L_0x015a:
            r12 = r8
            r8 = r9
            r9 = r10
            r10 = r11
            r11 = r1
            r1 = r7
            r7 = r13
        L_0x0161:
            com.bitcoin.mwallet.core.models.address.AddressAndPath r1 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r1
            if (r1 == 0) goto L_0x0166
            goto L_0x0187
        L_0x0166:
            com.bitcoin.mwallet.core.interactors.GetAddressInteractor r1 = r10.getAddressInteractor
            com.bitcoin.mwallet.core.models.wallet.WalletId r13 = r9.getWalletId()
            com.bitcoin.mwallet.core.models.address.AddressPurpose r14 = com.bitcoin.mwallet.core.models.address.AddressPurpose.CHANGE
            r2.L$0 = r10
            r2.L$1 = r9
            r2.L$2 = r8
            r2.L$3 = r7
            r2.L$4 = r4
            r2.L$5 = r11
            r2.L$6 = r12
            r2.label = r6
            java.lang.Object r1 = r1.getCurrentAddress(r13, r14, r2)
            if (r1 != r3) goto L_0x0185
            return r3
        L_0x0185:
            com.bitcoin.mwallet.core.models.address.AddressAndPath r1 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r1
        L_0x0187:
            r13 = r7
            r21 = r8
            r8 = r1
            r1 = r11
            r11 = r10
            r10 = r9
            r9 = r21
        L_0x0190:
            r7 = r11
            r6 = r13
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "WalletData Credential: "
            r11.append(r12)
            com.bitcoin.mwallet.core.models.credential.CredentialId r12 = r10.getCredentialId()
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            r12 = 0
            java.lang.Object[] r12 = new java.lang.Object[r12]
            timber.log.Timber.m426d(r11, r12)
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r15 = new com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest
            com.bitcoin.mwallet.core.models.wallet.WalletId r12 = r10.getWalletId()
            com.bitcoin.bitcoink.Network r16 = r10.getNetwork()
            com.bitcoin.mwallet.core.models.credential.CredentialId r17 = r10.getCredentialId()
            com.bitcoin.bitcoink.DerivationPath r18 = r10.getWalletPath()
            r11 = r15
            r13 = r4
            r14 = r9
            r5 = r15
            r15 = r6
            r19 = r1
            r20 = r8
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19, r20)
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r11 = r7.sendTxInteractor
            r2.L$0 = r7
            r2.L$1 = r10
            r2.L$2 = r9
            r2.L$3 = r6
            r2.L$4 = r4
            r2.L$5 = r1
            r2.L$6 = r8
            r2.L$7 = r5
            r1 = 5
            r2.label = r1
            java.lang.Object r1 = r11.sendTx(r5, r2)
            if (r1 != r3) goto L_0x01e7
            return r3
        L_0x01e7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.CreateTxInteractor.send(com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData, com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection, com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection, com.bitcoin.bitcoink.address.Address, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
