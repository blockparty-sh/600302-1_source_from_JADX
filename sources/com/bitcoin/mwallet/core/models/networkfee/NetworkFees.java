package com.bitcoin.mwallet.core.models.networkfee;

import com.bitcoin.mwallet.core.models.Coin;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.math.BigDecimal;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\b\u0010\u0013\u001a\u00020\u0007H\u0002J\u000e\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0006J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFees;", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "fees", "", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "Ljava/math/BigDecimal;", "(Lcom/bitcoin/mwallet/core/models/Coin;Ljava/util/Map;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getFees", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "fallbackFee", "getFee", "level", "hashCode", "", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: NetworkFees.kt */
public final class NetworkFees {
    @NotNull
    private final Coin coin;
    @NotNull
    private final Map<NetworkFeeLevel, BigDecimal> fees;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Map, code=java.util.Map<com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel, java.math.BigDecimal>, for r2v0, types: [java.util.Map] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.bitcoin.mwallet.core.models.networkfee.NetworkFees copy$default(com.bitcoin.mwallet.core.models.networkfee.NetworkFees r0, com.bitcoin.mwallet.core.models.Coin r1, java.util.Map<com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel, java.math.BigDecimal> r2, int r3, java.lang.Object r4) {
        /*
            r4 = r3 & 1
            if (r4 == 0) goto L_0x0006
            com.bitcoin.mwallet.core.models.Coin r1 = r0.coin
        L_0x0006:
            r3 = r3 & 2
            if (r3 == 0) goto L_0x000c
            java.util.Map<com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel, java.math.BigDecimal> r2 = r0.fees
        L_0x000c:
            com.bitcoin.mwallet.core.models.networkfee.NetworkFees r0 = r0.copy(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.networkfee.NetworkFees.copy$default(com.bitcoin.mwallet.core.models.networkfee.NetworkFees, com.bitcoin.mwallet.core.models.Coin, java.util.Map, int, java.lang.Object):com.bitcoin.mwallet.core.models.networkfee.NetworkFees");
    }

    @NotNull
    public final Coin component1() {
        return this.coin;
    }

    @NotNull
    public final Map<NetworkFeeLevel, BigDecimal> component2() {
        return this.fees;
    }

    @NotNull
    public final NetworkFees copy(@NotNull Coin coin2, @NotNull Map<NetworkFeeLevel, ? extends BigDecimal> map) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(map, "fees");
        return new NetworkFees(coin2, map);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.fees, (java.lang.Object) r3.fees) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.bitcoin.mwallet.core.models.networkfee.NetworkFees
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.core.models.networkfee.NetworkFees r3 = (com.bitcoin.mwallet.core.models.networkfee.NetworkFees) r3
            com.bitcoin.mwallet.core.models.Coin r0 = r2.coin
            com.bitcoin.mwallet.core.models.Coin r1 = r3.coin
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            java.util.Map<com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel, java.math.BigDecimal> r0 = r2.fees
            java.util.Map<com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel, java.math.BigDecimal> r3 = r3.fees
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.networkfee.NetworkFees.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Coin coin2 = this.coin;
        int i = 0;
        int hashCode = (coin2 != null ? coin2.hashCode() : 0) * 31;
        Map<NetworkFeeLevel, BigDecimal> map = this.fees;
        if (map != null) {
            i = map.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NetworkFees(coin=");
        sb.append(this.coin);
        sb.append(", fees=");
        sb.append(this.fees);
        sb.append(")");
        return sb.toString();
    }

    public NetworkFees(@NotNull Coin coin2, @NotNull Map<NetworkFeeLevel, ? extends BigDecimal> map) {
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(map, "fees");
        this.coin = coin2;
        this.fees = map;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @NotNull
    public final Map<NetworkFeeLevel, BigDecimal> getFees() {
        return this.fees;
    }

    @NotNull
    public final BigDecimal getFee(@NotNull NetworkFeeLevel networkFeeLevel) {
        Intrinsics.checkParameterIsNotNull(networkFeeLevel, Param.LEVEL);
        Object obj = this.fees.get(networkFeeLevel);
        if (obj == null) {
            obj = fallbackFee();
        }
        return (BigDecimal) obj;
    }

    private final BigDecimal fallbackFee() {
        StringBuilder sb = new StringBuilder();
        sb.append("Using last resort fallback fee coin=");
        sb.append(this.coin);
        Timber.m426d(sb.toString(), new Object[0]);
        int i = WhenMappings.$EnumSwitchMapping$0[this.coin.ordinal()];
        if (i == 1) {
            BigDecimal bigDecimal = BigDecimal.ONE;
            Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "BigDecimal.ONE");
            return bigDecimal;
        } else if (i == 2) {
            return new BigDecimal(5);
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }
}
