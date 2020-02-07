package com.bitcoin.mwallet.app.flows.sendv2.entity;

import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.address.Address;
import com.bitcoin.mwallet.core.models.address.AddressAndPath;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection;
import com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0013J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0007HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010)\u001a\u00020\u000bHÆ\u0003J\t\u0010*\u001a\u00020\rHÆ\u0003J\t\u0010+\u001a\u00020\u000fHÆ\u0003J\t\u0010,\u001a\u00020\u0011HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0011HÆ\u0003Jg\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\t\u00104\u001a\u000205HÖ\u0001R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00066"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendTxRequest;", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "toAddress", "Lcom/bitcoin/bitcoink/address/Address;", "utxoSelection", "Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "slpUtxoSelection", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;", "walletNetwork", "Lcom/bitcoin/bitcoink/Network;", "walletCredentialId", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "walletPath", "Lcom/bitcoin/bitcoink/DerivationPath;", "changeAddress", "Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;", "slpChangeAddress", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/bitcoink/address/Address;Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;Lcom/bitcoin/bitcoink/Network;Lcom/bitcoin/mwallet/core/models/credential/CredentialId;Lcom/bitcoin/bitcoink/DerivationPath;Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;)V", "getChangeAddress", "()Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;", "getSlpChangeAddress", "getSlpUtxoSelection", "()Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;", "getToAddress", "()Lcom/bitcoin/bitcoink/address/Address;", "getUtxoSelection", "()Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "getWalletCredentialId", "()Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletNetwork", "()Lcom/bitcoin/bitcoink/Network;", "getWalletPath", "()Lcom/bitcoin/bitcoink/DerivationPath;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SendTxRequest.kt */
public final class SendTxRequest {
    @NotNull
    private final AddressAndPath changeAddress;
    @Nullable
    private final AddressAndPath slpChangeAddress;
    @Nullable
    private final SlpUtxoSelection slpUtxoSelection;
    @NotNull
    private final Address toAddress;
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
    public static /* synthetic */ SendTxRequest copy$default(SendTxRequest sendTxRequest, WalletId walletId2, Address address, UtxoSelection utxoSelection2, SlpUtxoSelection slpUtxoSelection2, Network network, CredentialId credentialId, DerivationPath derivationPath, AddressAndPath addressAndPath, AddressAndPath addressAndPath2, int i, Object obj) {
        SendTxRequest sendTxRequest2 = sendTxRequest;
        int i2 = i;
        return sendTxRequest.copy((i2 & 1) != 0 ? sendTxRequest2.walletId : walletId2, (i2 & 2) != 0 ? sendTxRequest2.toAddress : address, (i2 & 4) != 0 ? sendTxRequest2.utxoSelection : utxoSelection2, (i2 & 8) != 0 ? sendTxRequest2.slpUtxoSelection : slpUtxoSelection2, (i2 & 16) != 0 ? sendTxRequest2.walletNetwork : network, (i2 & 32) != 0 ? sendTxRequest2.walletCredentialId : credentialId, (i2 & 64) != 0 ? sendTxRequest2.walletPath : derivationPath, (i2 & 128) != 0 ? sendTxRequest2.changeAddress : addressAndPath, (i2 & 256) != 0 ? sendTxRequest2.slpChangeAddress : addressAndPath2);
    }

    @NotNull
    public final WalletId component1() {
        return this.walletId;
    }

    @NotNull
    public final Address component2() {
        return this.toAddress;
    }

    @NotNull
    public final UtxoSelection component3() {
        return this.utxoSelection;
    }

    @Nullable
    public final SlpUtxoSelection component4() {
        return this.slpUtxoSelection;
    }

    @NotNull
    public final Network component5() {
        return this.walletNetwork;
    }

    @NotNull
    public final CredentialId component6() {
        return this.walletCredentialId;
    }

    @NotNull
    public final DerivationPath component7() {
        return this.walletPath;
    }

    @NotNull
    public final AddressAndPath component8() {
        return this.changeAddress;
    }

    @Nullable
    public final AddressAndPath component9() {
        return this.slpChangeAddress;
    }

    @NotNull
    public final SendTxRequest copy(@NotNull WalletId walletId2, @NotNull Address address, @NotNull UtxoSelection utxoSelection2, @Nullable SlpUtxoSelection slpUtxoSelection2, @NotNull Network network, @NotNull CredentialId credentialId, @NotNull DerivationPath derivationPath, @NotNull AddressAndPath addressAndPath, @Nullable AddressAndPath addressAndPath2) {
        WalletId walletId3 = walletId2;
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Address address2 = address;
        Intrinsics.checkParameterIsNotNull(address, "toAddress");
        UtxoSelection utxoSelection3 = utxoSelection2;
        Intrinsics.checkParameterIsNotNull(utxoSelection2, "utxoSelection");
        Network network2 = network;
        Intrinsics.checkParameterIsNotNull(network2, "walletNetwork");
        CredentialId credentialId2 = credentialId;
        Intrinsics.checkParameterIsNotNull(credentialId2, "walletCredentialId");
        DerivationPath derivationPath2 = derivationPath;
        Intrinsics.checkParameterIsNotNull(derivationPath2, "walletPath");
        AddressAndPath addressAndPath3 = addressAndPath;
        Intrinsics.checkParameterIsNotNull(addressAndPath3, "changeAddress");
        SendTxRequest sendTxRequest = new SendTxRequest(walletId3, address2, utxoSelection3, slpUtxoSelection2, network2, credentialId2, derivationPath2, addressAndPath3, addressAndPath2);
        return sendTxRequest;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0060, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.slpChangeAddress, (java.lang.Object) r3.slpChangeAddress) != false) goto L_0x0065;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0065
            boolean r0 = r3 instanceof com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest
            if (r0 == 0) goto L_0x0063
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r3 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest) r3
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r2.walletId
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r3.walletId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0063
            com.bitcoin.bitcoink.address.Address r0 = r2.toAddress
            com.bitcoin.bitcoink.address.Address r1 = r3.toAddress
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0063
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r0 = r2.utxoSelection
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r1 = r3.utxoSelection
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0063
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r0 = r2.slpUtxoSelection
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r1 = r3.slpUtxoSelection
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0063
            com.bitcoin.bitcoink.Network r0 = r2.walletNetwork
            com.bitcoin.bitcoink.Network r1 = r3.walletNetwork
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0063
            com.bitcoin.mwallet.core.models.credential.CredentialId r0 = r2.walletCredentialId
            com.bitcoin.mwallet.core.models.credential.CredentialId r1 = r3.walletCredentialId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0063
            com.bitcoin.bitcoink.DerivationPath r0 = r2.walletPath
            com.bitcoin.bitcoink.DerivationPath r1 = r3.walletPath
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0063
            com.bitcoin.mwallet.core.models.address.AddressAndPath r0 = r2.changeAddress
            com.bitcoin.mwallet.core.models.address.AddressAndPath r1 = r3.changeAddress
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0063
            com.bitcoin.mwallet.core.models.address.AddressAndPath r0 = r2.slpChangeAddress
            com.bitcoin.mwallet.core.models.address.AddressAndPath r3 = r3.slpChangeAddress
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0063
            goto L_0x0065
        L_0x0063:
            r3 = 0
            return r3
        L_0x0065:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        WalletId walletId2 = this.walletId;
        int i = 0;
        int hashCode = (walletId2 != null ? walletId2.hashCode() : 0) * 31;
        Address address = this.toAddress;
        int hashCode2 = (hashCode + (address != null ? address.hashCode() : 0)) * 31;
        UtxoSelection utxoSelection2 = this.utxoSelection;
        int hashCode3 = (hashCode2 + (utxoSelection2 != null ? utxoSelection2.hashCode() : 0)) * 31;
        SlpUtxoSelection slpUtxoSelection2 = this.slpUtxoSelection;
        int hashCode4 = (hashCode3 + (slpUtxoSelection2 != null ? slpUtxoSelection2.hashCode() : 0)) * 31;
        Network network = this.walletNetwork;
        int hashCode5 = (hashCode4 + (network != null ? network.hashCode() : 0)) * 31;
        CredentialId credentialId = this.walletCredentialId;
        int hashCode6 = (hashCode5 + (credentialId != null ? credentialId.hashCode() : 0)) * 31;
        DerivationPath derivationPath = this.walletPath;
        int hashCode7 = (hashCode6 + (derivationPath != null ? derivationPath.hashCode() : 0)) * 31;
        AddressAndPath addressAndPath = this.changeAddress;
        int hashCode8 = (hashCode7 + (addressAndPath != null ? addressAndPath.hashCode() : 0)) * 31;
        AddressAndPath addressAndPath2 = this.slpChangeAddress;
        if (addressAndPath2 != null) {
            i = addressAndPath2.hashCode();
        }
        return hashCode8 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SendTxRequest(walletId=");
        sb.append(this.walletId);
        sb.append(", toAddress=");
        sb.append(this.toAddress);
        sb.append(", utxoSelection=");
        sb.append(this.utxoSelection);
        sb.append(", slpUtxoSelection=");
        sb.append(this.slpUtxoSelection);
        sb.append(", walletNetwork=");
        sb.append(this.walletNetwork);
        sb.append(", walletCredentialId=");
        sb.append(this.walletCredentialId);
        sb.append(", walletPath=");
        sb.append(this.walletPath);
        sb.append(", changeAddress=");
        sb.append(this.changeAddress);
        sb.append(", slpChangeAddress=");
        sb.append(this.slpChangeAddress);
        sb.append(")");
        return sb.toString();
    }

    public SendTxRequest(@NotNull WalletId walletId2, @NotNull Address address, @NotNull UtxoSelection utxoSelection2, @Nullable SlpUtxoSelection slpUtxoSelection2, @NotNull Network network, @NotNull CredentialId credentialId, @NotNull DerivationPath derivationPath, @NotNull AddressAndPath addressAndPath, @Nullable AddressAndPath addressAndPath2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(address, "toAddress");
        Intrinsics.checkParameterIsNotNull(utxoSelection2, "utxoSelection");
        Intrinsics.checkParameterIsNotNull(network, "walletNetwork");
        Intrinsics.checkParameterIsNotNull(credentialId, "walletCredentialId");
        Intrinsics.checkParameterIsNotNull(derivationPath, "walletPath");
        Intrinsics.checkParameterIsNotNull(addressAndPath, "changeAddress");
        this.walletId = walletId2;
        this.toAddress = address;
        this.utxoSelection = utxoSelection2;
        this.slpUtxoSelection = slpUtxoSelection2;
        this.walletNetwork = network;
        this.walletCredentialId = credentialId;
        this.walletPath = derivationPath;
        this.changeAddress = addressAndPath;
        this.slpChangeAddress = addressAndPath2;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @NotNull
    public final Address getToAddress() {
        return this.toAddress;
    }

    @NotNull
    public final UtxoSelection getUtxoSelection() {
        return this.utxoSelection;
    }

    @Nullable
    public final SlpUtxoSelection getSlpUtxoSelection() {
        return this.slpUtxoSelection;
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

    @Nullable
    public final AddressAndPath getSlpChangeAddress() {
        return this.slpChangeAddress;
    }
}
