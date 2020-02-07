package com.bitcoin.mwallet.app.flows.sendv2.entity;

import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.mwallet.core.models.address.AddressAndPath;
import com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentOutput;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\u0010\u0013J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\t\u0010(\u001a\u00020\u000bHÆ\u0003J\t\u0010)\u001a\u00020\rHÆ\u0003J\t\u0010*\u001a\u00020\u000fHÆ\u0003J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011HÆ\u0003J_\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011HÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u000fHÖ\u0001R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u00063"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendBip70TxRequest;", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "utxoSelection", "Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "walletNetwork", "Lcom/bitcoin/bitcoink/Network;", "walletCredentialId", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "walletPath", "Lcom/bitcoin/bitcoink/DerivationPath;", "changeAddress", "Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;", "bip70Url", "", "bip70PaymentOutputs", "", "Lcom/bitcoin/mwallet/core/models/bip70payment/Bip70PaymentOutput;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;Lcom/bitcoin/bitcoink/Network;Lcom/bitcoin/mwallet/core/models/credential/CredentialId;Lcom/bitcoin/bitcoink/DerivationPath;Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;Ljava/lang/String;Ljava/util/List;)V", "getBip70PaymentOutputs", "()Ljava/util/List;", "getBip70Url", "()Ljava/lang/String;", "getChangeAddress", "()Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;", "getUtxoSelection", "()Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "getWalletCredentialId", "()Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletNetwork", "()Lcom/bitcoin/bitcoink/Network;", "getWalletPath", "()Lcom/bitcoin/bitcoink/DerivationPath;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SendBip70TxRequest.kt */
public final class SendBip70TxRequest {
    @NotNull
    private final List<Bip70PaymentOutput> bip70PaymentOutputs;
    @NotNull
    private final String bip70Url;
    @NotNull
    private final AddressAndPath changeAddress;
    @NotNull
    private final UtxoSelection utxoSelection;
    @NotNull
    private final CredentialId walletCredentialId;
    @NotNull
    private final WalletId walletId;
    @NotNull
    private final Network walletNetwork;
    @NotNull
    private final DerivationPath walletPath;

    @NotNull
    public static /* synthetic */ SendBip70TxRequest copy$default(SendBip70TxRequest sendBip70TxRequest, WalletId walletId2, UtxoSelection utxoSelection2, Network network, CredentialId credentialId, DerivationPath derivationPath, AddressAndPath addressAndPath, String str, List list, int i, Object obj) {
        SendBip70TxRequest sendBip70TxRequest2 = sendBip70TxRequest;
        int i2 = i;
        return sendBip70TxRequest.copy((i2 & 1) != 0 ? sendBip70TxRequest2.walletId : walletId2, (i2 & 2) != 0 ? sendBip70TxRequest2.utxoSelection : utxoSelection2, (i2 & 4) != 0 ? sendBip70TxRequest2.walletNetwork : network, (i2 & 8) != 0 ? sendBip70TxRequest2.walletCredentialId : credentialId, (i2 & 16) != 0 ? sendBip70TxRequest2.walletPath : derivationPath, (i2 & 32) != 0 ? sendBip70TxRequest2.changeAddress : addressAndPath, (i2 & 64) != 0 ? sendBip70TxRequest2.bip70Url : str, (i2 & 128) != 0 ? sendBip70TxRequest2.bip70PaymentOutputs : list);
    }

    @NotNull
    public final WalletId component1() {
        return this.walletId;
    }

    @NotNull
    public final UtxoSelection component2() {
        return this.utxoSelection;
    }

    @NotNull
    public final Network component3() {
        return this.walletNetwork;
    }

    @NotNull
    public final CredentialId component4() {
        return this.walletCredentialId;
    }

    @NotNull
    public final DerivationPath component5() {
        return this.walletPath;
    }

    @NotNull
    public final AddressAndPath component6() {
        return this.changeAddress;
    }

    @NotNull
    public final String component7() {
        return this.bip70Url;
    }

    @NotNull
    public final List<Bip70PaymentOutput> component8() {
        return this.bip70PaymentOutputs;
    }

    @NotNull
    public final SendBip70TxRequest copy(@NotNull WalletId walletId2, @NotNull UtxoSelection utxoSelection2, @NotNull Network network, @NotNull CredentialId credentialId, @NotNull DerivationPath derivationPath, @NotNull AddressAndPath addressAndPath, @NotNull String str, @NotNull List<Bip70PaymentOutput> list) {
        WalletId walletId3 = walletId2;
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        UtxoSelection utxoSelection3 = utxoSelection2;
        Intrinsics.checkParameterIsNotNull(utxoSelection2, "utxoSelection");
        Network network2 = network;
        Intrinsics.checkParameterIsNotNull(network, "walletNetwork");
        CredentialId credentialId2 = credentialId;
        Intrinsics.checkParameterIsNotNull(credentialId, "walletCredentialId");
        DerivationPath derivationPath2 = derivationPath;
        Intrinsics.checkParameterIsNotNull(derivationPath, "walletPath");
        AddressAndPath addressAndPath2 = addressAndPath;
        Intrinsics.checkParameterIsNotNull(addressAndPath2, "changeAddress");
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str2, "bip70Url");
        List<Bip70PaymentOutput> list2 = list;
        Intrinsics.checkParameterIsNotNull(list2, "bip70PaymentOutputs");
        SendBip70TxRequest sendBip70TxRequest = new SendBip70TxRequest(walletId3, utxoSelection3, network2, credentialId2, derivationPath2, addressAndPath2, str2, list2);
        return sendBip70TxRequest;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.bip70PaymentOutputs, (java.lang.Object) r3.bip70PaymentOutputs) != false) goto L_0x005b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x005b
            boolean r0 = r3 instanceof com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest
            if (r0 == 0) goto L_0x0059
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest r3 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest) r3
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r2.walletId
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r3.walletId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r0 = r2.utxoSelection
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r1 = r3.utxoSelection
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            com.bitcoin.bitcoink.Network r0 = r2.walletNetwork
            com.bitcoin.bitcoink.Network r1 = r3.walletNetwork
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            com.bitcoin.mwallet.core.models.credential.CredentialId r0 = r2.walletCredentialId
            com.bitcoin.mwallet.core.models.credential.CredentialId r1 = r3.walletCredentialId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            com.bitcoin.bitcoink.DerivationPath r0 = r2.walletPath
            com.bitcoin.bitcoink.DerivationPath r1 = r3.walletPath
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            com.bitcoin.mwallet.core.models.address.AddressAndPath r0 = r2.changeAddress
            com.bitcoin.mwallet.core.models.address.AddressAndPath r1 = r3.changeAddress
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r2.bip70Url
            java.lang.String r1 = r3.bip70Url
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0059
            java.util.List<com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentOutput> r0 = r2.bip70PaymentOutputs
            java.util.List<com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentOutput> r3 = r3.bip70PaymentOutputs
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r3 = 0
            return r3
        L_0x005b:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        WalletId walletId2 = this.walletId;
        int i = 0;
        int hashCode = (walletId2 != null ? walletId2.hashCode() : 0) * 31;
        UtxoSelection utxoSelection2 = this.utxoSelection;
        int hashCode2 = (hashCode + (utxoSelection2 != null ? utxoSelection2.hashCode() : 0)) * 31;
        Network network = this.walletNetwork;
        int hashCode3 = (hashCode2 + (network != null ? network.hashCode() : 0)) * 31;
        CredentialId credentialId = this.walletCredentialId;
        int hashCode4 = (hashCode3 + (credentialId != null ? credentialId.hashCode() : 0)) * 31;
        DerivationPath derivationPath = this.walletPath;
        int hashCode5 = (hashCode4 + (derivationPath != null ? derivationPath.hashCode() : 0)) * 31;
        AddressAndPath addressAndPath = this.changeAddress;
        int hashCode6 = (hashCode5 + (addressAndPath != null ? addressAndPath.hashCode() : 0)) * 31;
        String str = this.bip70Url;
        int hashCode7 = (hashCode6 + (str != null ? str.hashCode() : 0)) * 31;
        List<Bip70PaymentOutput> list = this.bip70PaymentOutputs;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode7 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SendBip70TxRequest(walletId=");
        sb.append(this.walletId);
        sb.append(", utxoSelection=");
        sb.append(this.utxoSelection);
        sb.append(", walletNetwork=");
        sb.append(this.walletNetwork);
        sb.append(", walletCredentialId=");
        sb.append(this.walletCredentialId);
        sb.append(", walletPath=");
        sb.append(this.walletPath);
        sb.append(", changeAddress=");
        sb.append(this.changeAddress);
        sb.append(", bip70Url=");
        sb.append(this.bip70Url);
        sb.append(", bip70PaymentOutputs=");
        sb.append(this.bip70PaymentOutputs);
        sb.append(")");
        return sb.toString();
    }

    public SendBip70TxRequest(@NotNull WalletId walletId2, @NotNull UtxoSelection utxoSelection2, @NotNull Network network, @NotNull CredentialId credentialId, @NotNull DerivationPath derivationPath, @NotNull AddressAndPath addressAndPath, @NotNull String str, @NotNull List<Bip70PaymentOutput> list) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(utxoSelection2, "utxoSelection");
        Intrinsics.checkParameterIsNotNull(network, "walletNetwork");
        Intrinsics.checkParameterIsNotNull(credentialId, "walletCredentialId");
        Intrinsics.checkParameterIsNotNull(derivationPath, "walletPath");
        Intrinsics.checkParameterIsNotNull(addressAndPath, "changeAddress");
        Intrinsics.checkParameterIsNotNull(str, "bip70Url");
        Intrinsics.checkParameterIsNotNull(list, "bip70PaymentOutputs");
        this.walletId = walletId2;
        this.utxoSelection = utxoSelection2;
        this.walletNetwork = network;
        this.walletCredentialId = credentialId;
        this.walletPath = derivationPath;
        this.changeAddress = addressAndPath;
        this.bip70Url = str;
        this.bip70PaymentOutputs = list;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @NotNull
    public final UtxoSelection getUtxoSelection() {
        return this.utxoSelection;
    }

    @NotNull
    public final Network getWalletNetwork() {
        return this.walletNetwork;
    }

    @NotNull
    public final CredentialId getWalletCredentialId() {
        return this.walletCredentialId;
    }

    @NotNull
    public final DerivationPath getWalletPath() {
        return this.walletPath;
    }

    @NotNull
    public final AddressAndPath getChangeAddress() {
        return this.changeAddress;
    }

    @NotNull
    public final String getBip70Url() {
        return this.bip70Url;
    }

    @NotNull
    public final List<Bip70PaymentOutput> getBip70PaymentOutputs() {
        return this.bip70PaymentOutputs;
    }
}
