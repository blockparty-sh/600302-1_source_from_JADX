package com.bitcoin.bitcoink.p008tx;

import java.io.Serializable;
import java.math.BigDecimal;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0003H\u0002J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u000eHÖ\u0001J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0000J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0000J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001c"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/tx/Satoshis;", "Ljava/io/Serializable;", "satoshis", "", "(J)V", "coins", "Ljava/math/BigDecimal;", "getCoins", "()Ljava/math/BigDecimal;", "coins$delegate", "Lkotlin/Lazy;", "getSatoshis", "()J", "compareTo", "", "other", "component1", "copy", "equals", "", "", "hashCode", "minus", "value", "plus", "toString", "", "Companion", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.bitcoink.tx.Satoshis */
/* compiled from: Satoshis.kt */
public final class Satoshis implements Serializable {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Satoshis.class), "coins", "getCoins()Ljava/math/BigDecimal;"))};
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final Satoshis ZERO = new Satoshis(0);
    @NotNull
    private final Lazy coins$delegate = LazyKt.lazy(new Satoshis$coins$2(this));
    private final long satoshis;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/tx/Satoshis$Companion;", "", "()V", "ZERO", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "getZERO", "()Lcom/bitcoin/bitcoink/tx/Satoshis;", "fromCoins", "coins", "Ljava/math/BigDecimal;", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.bitcoink.tx.Satoshis$Companion */
    /* compiled from: Satoshis.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Satoshis fromCoins(@NotNull BigDecimal bigDecimal) {
            Intrinsics.checkParameterIsNotNull(bigDecimal, "coins");
            return new Satoshis(bigDecimal.movePointRight(8).longValue());
        }

        @NotNull
        public final Satoshis getZERO() {
            return Satoshis.ZERO;
        }
    }

    @NotNull
    public static /* synthetic */ Satoshis copy$default(Satoshis satoshis2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = satoshis2.satoshis;
        }
        return satoshis2.copy(j);
    }

    public final long component1() {
        return this.satoshis;
    }

    @NotNull
    public final Satoshis copy(long j) {
        return new Satoshis(j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Satoshis) {
                if (this.satoshis == ((Satoshis) obj).satoshis) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final BigDecimal getCoins() {
        Lazy lazy = this.coins$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (BigDecimal) lazy.getValue();
    }

    public int hashCode() {
        long j = this.satoshis;
        return (int) (j ^ (j >>> 32));
    }

    public Satoshis(long j) {
        this.satoshis = j;
    }

    public final long getSatoshis() {
        return this.satoshis;
    }

    @NotNull
    public final Satoshis plus(@NotNull Satoshis satoshis2) {
        Intrinsics.checkParameterIsNotNull(satoshis2, "value");
        return new Satoshis(this.satoshis + satoshis2.satoshis);
    }

    @NotNull
    public final Satoshis minus(@NotNull Satoshis satoshis2) {
        Intrinsics.checkParameterIsNotNull(satoshis2, "value");
        return new Satoshis(this.satoshis - satoshis2.satoshis);
    }

    @NotNull
    public String toString() {
        return String.valueOf(this.satoshis);
    }

    public final int compareTo(long j) {
        long j2 = this.satoshis;
        if (j2 == j) {
            return 0;
        }
        return j2 < j ? -1 : 1;
    }
}
