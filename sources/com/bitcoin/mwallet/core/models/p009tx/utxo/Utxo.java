package com.bitcoin.mwallet.core.models.p009tx.utxo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.address.Address;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 22\u00020\u0001:\u00012BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\t\u0010'\u001a\u00020\u000bHÆ\u0003J\t\u0010(\u001a\u00020\rHÆ\u0003J\t\u0010)\u001a\u00020\u000fHÆ\u0003J\t\u0010*\u001a\u00020\u0011HÆ\u0003JY\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011HÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010/\u001a\u00020\u0007H\u0016J\b\u00100\u001a\u000201H\u0016R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0010\u001a\u00020\u00118\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u000e\u001a\u00020\u000f8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\f\u001a\u00020\r8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00063"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "outputIndex", "", "address", "Lcom/bitcoin/bitcoink/address/Address;", "addressPath", "Lcom/bitcoin/bitcoink/DerivationPath;", "script", "", "satoshis", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/bitcoink/tx/TxId;ILcom/bitcoin/bitcoink/address/Address;Lcom/bitcoin/bitcoink/DerivationPath;[BLcom/bitcoin/bitcoink/tx/Satoshis;Lcom/bitcoin/mwallet/core/models/Coin;)V", "getAddress", "()Lcom/bitcoin/bitcoink/address/Address;", "getAddressPath", "()Lcom/bitcoin/bitcoink/DerivationPath;", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getOutputIndex", "()I", "getSatoshis", "()Lcom/bitcoin/bitcoink/tx/Satoshis;", "getScript", "()[B", "getTxId", "()Lcom/bitcoin/bitcoink/tx/TxId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(primaryKeys = {"wallet_id", "tx_id", "output_index"}, tableName = "utxo")
/* renamed from: com.bitcoin.mwallet.core.models.tx.utxo.Utxo */
/* compiled from: Utxo.kt */
public final class Utxo {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final Comparator<Utxo> smallestFirst = Utxo$Companion$smallestFirst$1.INSTANCE;
    @ColumnInfo(name = "address")
    @NotNull
    private final Address address;
    @ColumnInfo(name = "path")
    @NotNull
    private final DerivationPath addressPath;
    @ColumnInfo(name = "coin")
    @NotNull
    private final Coin coin;
    @ColumnInfo(name = "output_index")
    private final int outputIndex;
    @ColumnInfo(name = "satoshis")
    @NotNull
    private final Satoshis satoshis;
    @ColumnInfo(name = "script")
    @NotNull
    private final byte[] script;
    @ColumnInfo(name = "tx_id")
    @NotNull
    private final TxId txId;
    @ColumnInfo(index = true, name = "wallet_id")
    @NotNull
    private final WalletId walletId;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo$Companion;", "", "()V", "smallestFirst", "Ljava/util/Comparator;", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "Lkotlin/Comparator;", "getSmallestFirst", "()Ljava/util/Comparator;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.mwallet.core.models.tx.utxo.Utxo$Companion */
    /* compiled from: Utxo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Comparator<Utxo> getSmallestFirst() {
            return Utxo.smallestFirst;
        }
    }

    @NotNull
    public static /* synthetic */ Utxo copy$default(Utxo utxo, WalletId walletId2, TxId txId2, int i, Address address2, DerivationPath derivationPath, byte[] bArr, Satoshis satoshis2, Coin coin2, int i2, Object obj) {
        Utxo utxo2 = utxo;
        int i3 = i2;
        return utxo.copy((i3 & 1) != 0 ? utxo2.walletId : walletId2, (i3 & 2) != 0 ? utxo2.txId : txId2, (i3 & 4) != 0 ? utxo2.outputIndex : i, (i3 & 8) != 0 ? utxo2.address : address2, (i3 & 16) != 0 ? utxo2.addressPath : derivationPath, (i3 & 32) != 0 ? utxo2.script : bArr, (i3 & 64) != 0 ? utxo2.satoshis : satoshis2, (i3 & 128) != 0 ? utxo2.coin : coin2);
    }

    @NotNull
    public final WalletId component1() {
        return this.walletId;
    }

    @NotNull
    public final TxId component2() {
        return this.txId;
    }

    public final int component3() {
        return this.outputIndex;
    }

    @NotNull
    public final Address component4() {
        return this.address;
    }

    @NotNull
    public final DerivationPath component5() {
        return this.addressPath;
    }

    @NotNull
    public final byte[] component6() {
        return this.script;
    }

    @NotNull
    public final Satoshis component7() {
        return this.satoshis;
    }

    @NotNull
    public final Coin component8() {
        return this.coin;
    }

    @NotNull
    public final Utxo copy(@NotNull WalletId walletId2, @NotNull TxId txId2, int i, @NotNull Address address2, @NotNull DerivationPath derivationPath, @NotNull byte[] bArr, @NotNull Satoshis satoshis2, @NotNull Coin coin2) {
        WalletId walletId3 = walletId2;
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        TxId txId3 = txId2;
        Intrinsics.checkParameterIsNotNull(txId2, "txId");
        Address address3 = address2;
        Intrinsics.checkParameterIsNotNull(address2, "address");
        DerivationPath derivationPath2 = derivationPath;
        Intrinsics.checkParameterIsNotNull(derivationPath, "addressPath");
        byte[] bArr2 = bArr;
        Intrinsics.checkParameterIsNotNull(bArr2, "script");
        Satoshis satoshis3 = satoshis2;
        Intrinsics.checkParameterIsNotNull(satoshis3, "satoshis");
        Coin coin3 = coin2;
        Intrinsics.checkParameterIsNotNull(coin3, "coin");
        Utxo utxo = new Utxo(walletId3, txId3, i, address3, derivationPath2, bArr2, satoshis3, coin3);
        return utxo;
    }

    public Utxo(@NotNull WalletId walletId2, @NotNull TxId txId2, int i, @NotNull Address address2, @NotNull DerivationPath derivationPath, @NotNull byte[] bArr, @NotNull Satoshis satoshis2, @NotNull Coin coin2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(txId2, "txId");
        Intrinsics.checkParameterIsNotNull(address2, "address");
        Intrinsics.checkParameterIsNotNull(derivationPath, "addressPath");
        Intrinsics.checkParameterIsNotNull(bArr, "script");
        Intrinsics.checkParameterIsNotNull(satoshis2, "satoshis");
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        this.walletId = walletId2;
        this.txId = txId2;
        this.outputIndex = i;
        this.address = address2;
        this.addressPath = derivationPath;
        this.script = bArr;
        this.satoshis = satoshis2;
        this.coin = coin2;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @NotNull
    public final TxId getTxId() {
        return this.txId;
    }

    public final int getOutputIndex() {
        return this.outputIndex;
    }

    @NotNull
    public final Address getAddress() {
        return this.address;
    }

    @NotNull
    public final DerivationPath getAddressPath() {
        return this.addressPath;
    }

    @NotNull
    public final byte[] getScript() {
        return this.script;
    }

    @NotNull
    public final Satoshis getSatoshis() {
        return this.satoshis;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            Utxo utxo = (Utxo) obj;
            if (!(!Intrinsics.areEqual((Object) this.walletId, (Object) utxo.walletId)) && !(!Intrinsics.areEqual((Object) this.txId, (Object) utxo.txId)) && this.outputIndex == utxo.outputIndex) {
                return true;
            }
            return false;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.core.models.tx.utxo.Utxo");
    }

    public int hashCode() {
        return (((this.walletId.hashCode() * 31) + this.txId.hashCode()) * 31) + this.outputIndex;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Utxo(walletId=");
        sb.append(this.walletId);
        sb.append(", txId=");
        sb.append(this.txId);
        sb.append(", outputIndex=");
        sb.append(this.outputIndex);
        sb.append(", address=");
        sb.append(this.address);
        sb.append(", addressPath=");
        sb.append(this.addressPath);
        sb.append(", script.size=");
        sb.append(this.script.length);
        sb.append(", satoshis=");
        sb.append(this.satoshis);
        sb.append("), coin=");
        sb.append(this.coin);
        sb.append(')');
        return sb.toString();
    }
}
