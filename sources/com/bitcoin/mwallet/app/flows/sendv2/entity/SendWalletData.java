package com.bitcoin.mwallet.app.flows.sendv2.entity;

import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.WalletSlpUtxos;
import com.bitcoin.mwallet.core.models.p009tx.utxo.WalletUtxos;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0006HÆ\u0003J\t\u0010,\u001a\u00020\bHÆ\u0003J\t\u0010-\u001a\u00020\nHÆ\u0003J\t\u0010.\u001a\u00020\fHÆ\u0003J\t\u0010/\u001a\u00020\u000eHÆ\u0003J\t\u00100\u001a\u00020\u0010HÆ\u0003J\t\u00101\u001a\u00020\u0012HÆ\u0003Jo\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÆ\u0001J\u0013\u00103\u001a\u00020\u00102\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u000206HÖ\u0001J\t\u00107\u001a\u00020\fHÖ\u0001R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010$¨\u00068"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendWalletData;", "", "walletPk", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "walletId", "network", "Lcom/bitcoin/bitcoink/Network;", "walletPath", "Lcom/bitcoin/bitcoink/DerivationPath;", "credentialId", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "name", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "isMultiSig", "", "utxos", "Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;", "slpUtxos", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/WalletSlpUtxos;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/bitcoink/Network;Lcom/bitcoin/bitcoink/DerivationPath;Lcom/bitcoin/mwallet/core/models/credential/CredentialId;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/Coin;ZLcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;Lcom/bitcoin/mwallet/core/models/tx/slputxo/WalletSlpUtxos;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getCredentialId", "()Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "()Z", "getName", "()Ljava/lang/String;", "getNetwork", "()Lcom/bitcoin/bitcoink/Network;", "getSlpUtxos", "()Lcom/bitcoin/mwallet/core/models/tx/slputxo/WalletSlpUtxos;", "getUtxos", "()Lcom/bitcoin/mwallet/core/models/tx/utxo/WalletUtxos;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletPath", "()Lcom/bitcoin/bitcoink/DerivationPath;", "getWalletPk", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SendWalletData.kt */
public final class SendWalletData {
    @NotNull
    private final Coin coin;
    @NotNull
    private final CredentialId credentialId;
    private final boolean isMultiSig;
    @NotNull
    private final String name;
    @NotNull
    private final Network network;
    @Nullable
    private final WalletSlpUtxos slpUtxos;
    @NotNull
    private final WalletUtxos utxos;
    @NotNull
    private final WalletId walletId;
    @NotNull
    private final DerivationPath walletPath;
    @NotNull
    private final WalletId walletPk;

    @NotNull
    public static /* synthetic */ SendWalletData copy$default(SendWalletData sendWalletData, WalletId walletId2, WalletId walletId3, Network network2, DerivationPath derivationPath, CredentialId credentialId2, String str, Coin coin2, boolean z, WalletUtxos walletUtxos, WalletSlpUtxos walletSlpUtxos, int i, Object obj) {
        SendWalletData sendWalletData2 = sendWalletData;
        int i2 = i;
        return sendWalletData.copy((i2 & 1) != 0 ? sendWalletData2.walletPk : walletId2, (i2 & 2) != 0 ? sendWalletData2.walletId : walletId3, (i2 & 4) != 0 ? sendWalletData2.network : network2, (i2 & 8) != 0 ? sendWalletData2.walletPath : derivationPath, (i2 & 16) != 0 ? sendWalletData2.credentialId : credentialId2, (i2 & 32) != 0 ? sendWalletData2.name : str, (i2 & 64) != 0 ? sendWalletData2.coin : coin2, (i2 & 128) != 0 ? sendWalletData2.isMultiSig : z, (i2 & 256) != 0 ? sendWalletData2.utxos : walletUtxos, (i2 & 512) != 0 ? sendWalletData2.slpUtxos : walletSlpUtxos);
    }

    @NotNull
    public final WalletId component1() {
        return this.walletPk;
    }

    @Nullable
    public final WalletSlpUtxos component10() {
        return this.slpUtxos;
    }

    @NotNull
    public final WalletId component2() {
        return this.walletId;
    }

    @NotNull
    public final Network component3() {
        return this.network;
    }

    @NotNull
    public final DerivationPath component4() {
        return this.walletPath;
    }

    @NotNull
    public final CredentialId component5() {
        return this.credentialId;
    }

    @NotNull
    public final String component6() {
        return this.name;
    }

    @NotNull
    public final Coin component7() {
        return this.coin;
    }

    public final boolean component8() {
        return this.isMultiSig;
    }

    @NotNull
    public final WalletUtxos component9() {
        return this.utxos;
    }

    @NotNull
    public final SendWalletData copy(@NotNull WalletId walletId2, @NotNull WalletId walletId3, @NotNull Network network2, @NotNull DerivationPath derivationPath, @NotNull CredentialId credentialId2, @NotNull String str, @NotNull Coin coin2, boolean z, @NotNull WalletUtxos walletUtxos, @Nullable WalletSlpUtxos walletSlpUtxos) {
        WalletId walletId4 = walletId2;
        Intrinsics.checkParameterIsNotNull(walletId2, "walletPk");
        WalletId walletId5 = walletId3;
        Intrinsics.checkParameterIsNotNull(walletId3, "walletId");
        Network network3 = network2;
        Intrinsics.checkParameterIsNotNull(network2, "network");
        DerivationPath derivationPath2 = derivationPath;
        Intrinsics.checkParameterIsNotNull(derivationPath2, "walletPath");
        CredentialId credentialId3 = credentialId2;
        Intrinsics.checkParameterIsNotNull(credentialId3, "credentialId");
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str2, "name");
        Coin coin3 = coin2;
        Intrinsics.checkParameterIsNotNull(coin3, "coin");
        WalletUtxos walletUtxos2 = walletUtxos;
        Intrinsics.checkParameterIsNotNull(walletUtxos2, "utxos");
        SendWalletData sendWalletData = new SendWalletData(walletId4, walletId5, network3, derivationPath2, credentialId3, str2, coin3, z, walletUtxos2, walletSlpUtxos);
        return sendWalletData;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof SendWalletData) {
                SendWalletData sendWalletData = (SendWalletData) obj;
                if (Intrinsics.areEqual((Object) this.walletPk, (Object) sendWalletData.walletPk) && Intrinsics.areEqual((Object) this.walletId, (Object) sendWalletData.walletId) && Intrinsics.areEqual((Object) this.network, (Object) sendWalletData.network) && Intrinsics.areEqual((Object) this.walletPath, (Object) sendWalletData.walletPath) && Intrinsics.areEqual((Object) this.credentialId, (Object) sendWalletData.credentialId) && Intrinsics.areEqual((Object) this.name, (Object) sendWalletData.name) && Intrinsics.areEqual((Object) this.coin, (Object) sendWalletData.coin)) {
                    if (!(this.isMultiSig == sendWalletData.isMultiSig) || !Intrinsics.areEqual((Object) this.utxos, (Object) sendWalletData.utxos) || !Intrinsics.areEqual((Object) this.slpUtxos, (Object) sendWalletData.slpUtxos)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        WalletId walletId2 = this.walletPk;
        int i = 0;
        int hashCode = (walletId2 != null ? walletId2.hashCode() : 0) * 31;
        WalletId walletId3 = this.walletId;
        int hashCode2 = (hashCode + (walletId3 != null ? walletId3.hashCode() : 0)) * 31;
        Network network2 = this.network;
        int hashCode3 = (hashCode2 + (network2 != null ? network2.hashCode() : 0)) * 31;
        DerivationPath derivationPath = this.walletPath;
        int hashCode4 = (hashCode3 + (derivationPath != null ? derivationPath.hashCode() : 0)) * 31;
        CredentialId credentialId2 = this.credentialId;
        int hashCode5 = (hashCode4 + (credentialId2 != null ? credentialId2.hashCode() : 0)) * 31;
        String str = this.name;
        int hashCode6 = (hashCode5 + (str != null ? str.hashCode() : 0)) * 31;
        Coin coin2 = this.coin;
        int hashCode7 = (hashCode6 + (coin2 != null ? coin2.hashCode() : 0)) * 31;
        boolean z = this.isMultiSig;
        if (z) {
            z = true;
        }
        int i2 = (hashCode7 + (z ? 1 : 0)) * 31;
        WalletUtxos walletUtxos = this.utxos;
        int hashCode8 = (i2 + (walletUtxos != null ? walletUtxos.hashCode() : 0)) * 31;
        WalletSlpUtxos walletSlpUtxos = this.slpUtxos;
        if (walletSlpUtxos != null) {
            i = walletSlpUtxos.hashCode();
        }
        return hashCode8 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SendWalletData(walletPk=");
        sb.append(this.walletPk);
        sb.append(", walletId=");
        sb.append(this.walletId);
        sb.append(", network=");
        sb.append(this.network);
        sb.append(", walletPath=");
        sb.append(this.walletPath);
        sb.append(", credentialId=");
        sb.append(this.credentialId);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", isMultiSig=");
        sb.append(this.isMultiSig);
        sb.append(", utxos=");
        sb.append(this.utxos);
        sb.append(", slpUtxos=");
        sb.append(this.slpUtxos);
        sb.append(")");
        return sb.toString();
    }

    public SendWalletData(@NotNull WalletId walletId2, @NotNull WalletId walletId3, @NotNull Network network2, @NotNull DerivationPath derivationPath, @NotNull CredentialId credentialId2, @NotNull String str, @NotNull Coin coin2, boolean z, @NotNull WalletUtxos walletUtxos, @Nullable WalletSlpUtxos walletSlpUtxos) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletPk");
        Intrinsics.checkParameterIsNotNull(walletId3, "walletId");
        Intrinsics.checkParameterIsNotNull(network2, "network");
        Intrinsics.checkParameterIsNotNull(derivationPath, "walletPath");
        Intrinsics.checkParameterIsNotNull(credentialId2, "credentialId");
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(walletUtxos, "utxos");
        this.walletPk = walletId2;
        this.walletId = walletId3;
        this.network = network2;
        this.walletPath = derivationPath;
        this.credentialId = credentialId2;
        this.name = str;
        this.coin = coin2;
        this.isMultiSig = z;
        this.utxos = walletUtxos;
        this.slpUtxos = walletSlpUtxos;
    }

    @NotNull
    public final WalletId getWalletPk() {
        return this.walletPk;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @NotNull
    public final Network getNetwork() {
        return this.network;
    }

    @NotNull
    public final DerivationPath getWalletPath() {
        return this.walletPath;
    }

    @NotNull
    public final CredentialId getCredentialId() {
        return this.credentialId;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    public final boolean isMultiSig() {
        return this.isMultiSig;
    }

    @NotNull
    public final WalletUtxos getUtxos() {
        return this.utxos;
    }

    @Nullable
    public final WalletSlpUtxos getSlpUtxos() {
        return this.slpUtxos;
    }
}
