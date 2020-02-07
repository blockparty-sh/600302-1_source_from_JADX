package com.bitcoin.mwallet.core.models.networkfee;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.bitcoin.mwallet.core.models.Coin;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFee;", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "feeLevel", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "satoshisPerByte", "Ljava/math/BigDecimal;", "(Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;Ljava/math/BigDecimal;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getFeeLevel", "()Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "getSatoshisPerByte", "()Ljava/math/BigDecimal;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(primaryKeys = {"coin", "fee_level"}, tableName = "network_fee")
/* compiled from: NetworkFee.kt */
public final class NetworkFee {
    @ColumnInfo(index = true, name = "coin")
    @NotNull
    private final Coin coin;
    @ColumnInfo(name = "fee_level")
    @NotNull
    private final NetworkFeeLevel feeLevel;
    @ColumnInfo(name = "satoshis_per_byte")
    @NotNull
    private final BigDecimal satoshisPerByte;

    @NotNull
    public static /* synthetic */ NetworkFee copy$default(NetworkFee networkFee, Coin coin2, NetworkFeeLevel networkFeeLevel, BigDecimal bigDecimal, int i, Object obj) {
        if ((i & 1) != 0) {
            coin2 = networkFee.coin;
        }
        if ((i & 2) != 0) {
            networkFeeLevel = networkFee.feeLevel;
        }
        if ((i & 4) != 0) {
            bigDecimal = networkFee.satoshisPerByte;
        }
        return networkFee.copy(coin2, networkFeeLevel, bigDecimal);
    }

    @NotNull
    public final Coin component1() {
        return this.coin;
    }

    @NotNull
    public final NetworkFeeLevel component2() {
        return this.feeLevel;
    }

    @NotNull
    public final BigDecimal component3() {
        return this.satoshisPerByte;
    }

    @NotNull
    public final NetworkFee copy(@NotNull Coin coin2, @NotNull NetworkFeeLevel networkFeeLevel, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(networkFeeLevel, "feeLevel");
        Intrinsics.checkParameterIsNotNull(bigDecimal, "satoshisPerByte");
        return new NetworkFee(coin2, networkFeeLevel, bigDecimal);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.satoshisPerByte, (java.lang.Object) r3.satoshisPerByte) != false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0029
            boolean r0 = r3 instanceof com.bitcoin.mwallet.core.models.networkfee.NetworkFee
            if (r0 == 0) goto L_0x0027
            com.bitcoin.mwallet.core.models.networkfee.NetworkFee r3 = (com.bitcoin.mwallet.core.models.networkfee.NetworkFee) r3
            com.bitcoin.mwallet.core.models.Coin r0 = r2.coin
            com.bitcoin.mwallet.core.models.Coin r1 = r3.coin
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel r0 = r2.feeLevel
            com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel r1 = r3.feeLevel
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            java.math.BigDecimal r0 = r2.satoshisPerByte
            java.math.BigDecimal r3 = r3.satoshisPerByte
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r3 = 0
            return r3
        L_0x0029:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.networkfee.NetworkFee.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Coin coin2 = this.coin;
        int i = 0;
        int hashCode = (coin2 != null ? coin2.hashCode() : 0) * 31;
        NetworkFeeLevel networkFeeLevel = this.feeLevel;
        int hashCode2 = (hashCode + (networkFeeLevel != null ? networkFeeLevel.hashCode() : 0)) * 31;
        BigDecimal bigDecimal = this.satoshisPerByte;
        if (bigDecimal != null) {
            i = bigDecimal.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NetworkFee(coin=");
        sb.append(this.coin);
        sb.append(", feeLevel=");
        sb.append(this.feeLevel);
        sb.append(", satoshisPerByte=");
        sb.append(this.satoshisPerByte);
        sb.append(")");
        return sb.toString();
    }

    public NetworkFee(@NotNull Coin coin2, @NotNull NetworkFeeLevel networkFeeLevel, @NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(networkFeeLevel, "feeLevel");
        Intrinsics.checkParameterIsNotNull(bigDecimal, "satoshisPerByte");
        this.coin = coin2;
        this.feeLevel = networkFeeLevel;
        this.satoshisPerByte = bigDecimal;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @NotNull
    public final NetworkFeeLevel getFeeLevel() {
        return this.feeLevel;
    }

    @NotNull
    public final BigDecimal getSatoshisPerByte() {
        return this.satoshisPerByte;
    }
}
