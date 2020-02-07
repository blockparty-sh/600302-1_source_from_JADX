package com.bitcoin.mwallet.core.models;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/CoinBalance;", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "satoshis", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "fiat", "Ljava/math/BigDecimal;", "(Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/bitcoink/tx/Satoshis;Ljava/math/BigDecimal;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getFiat", "()Ljava/math/BigDecimal;", "getSatoshis", "()Lcom/bitcoin/bitcoink/tx/Satoshis;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CoinBalance.kt */
public final class CoinBalance {
    @NotNull
    private final Coin coin;
    @Nullable
    private final BigDecimal fiat;
    @NotNull
    private final Satoshis satoshis;

    @NotNull
    public static /* synthetic */ CoinBalance copy$default(CoinBalance coinBalance, Coin coin2, Satoshis satoshis2, BigDecimal bigDecimal, int i, Object obj) {
        if ((i & 1) != 0) {
            coin2 = coinBalance.coin;
        }
        if ((i & 2) != 0) {
            satoshis2 = coinBalance.satoshis;
        }
        if ((i & 4) != 0) {
            bigDecimal = coinBalance.fiat;
        }
        return coinBalance.copy(coin2, satoshis2, bigDecimal);
    }

    @NotNull
    public final Coin component1() {
        return this.coin;
    }

    @NotNull
    public final Satoshis component2() {
        return this.satoshis;
    }

    @Nullable
    public final BigDecimal component3() {
        return this.fiat;
    }

    @NotNull
    public final CoinBalance copy(@NotNull Coin coin2, @NotNull Satoshis satoshis2, @Nullable BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(satoshis2, "satoshis");
        return new CoinBalance(coin2, satoshis2, bigDecimal);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.fiat, (java.lang.Object) r3.fiat) != false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0029
            boolean r0 = r3 instanceof com.bitcoin.mwallet.core.models.CoinBalance
            if (r0 == 0) goto L_0x0027
            com.bitcoin.mwallet.core.models.CoinBalance r3 = (com.bitcoin.mwallet.core.models.CoinBalance) r3
            com.bitcoin.mwallet.core.models.Coin r0 = r2.coin
            com.bitcoin.mwallet.core.models.Coin r1 = r3.coin
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            com.bitcoin.bitcoink.tx.Satoshis r0 = r2.satoshis
            com.bitcoin.bitcoink.tx.Satoshis r1 = r3.satoshis
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            java.math.BigDecimal r0 = r2.fiat
            java.math.BigDecimal r3 = r3.fiat
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.CoinBalance.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Coin coin2 = this.coin;
        int i = 0;
        int hashCode = (coin2 != null ? coin2.hashCode() : 0) * 31;
        Satoshis satoshis2 = this.satoshis;
        int hashCode2 = (hashCode + (satoshis2 != null ? satoshis2.hashCode() : 0)) * 31;
        BigDecimal bigDecimal = this.fiat;
        if (bigDecimal != null) {
            i = bigDecimal.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CoinBalance(coin=");
        sb.append(this.coin);
        sb.append(", satoshis=");
        sb.append(this.satoshis);
        sb.append(", fiat=");
        sb.append(this.fiat);
        sb.append(")");
        return sb.toString();
    }

    public CoinBalance(@NotNull Coin coin2, @NotNull Satoshis satoshis2, @Nullable BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(satoshis2, "satoshis");
        this.coin = coin2;
        this.satoshis = satoshis2;
        this.fiat = bigDecimal;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @NotNull
    public final Satoshis getSatoshis() {
        return this.satoshis;
    }

    @Nullable
    public final BigDecimal getFiat() {
        return this.fiat;
    }
}
