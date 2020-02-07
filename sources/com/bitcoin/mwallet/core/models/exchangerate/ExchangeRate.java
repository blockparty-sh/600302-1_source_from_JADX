package com.bitcoin.mwallet.core.models.exchangerate;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import java.math.BigDecimal;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/exchangerate/ExchangeRate;", "", "fromTicker", "", "toTicker", "rate", "Ljava/math/BigDecimal;", "timestamp", "Ljava/util/Date;", "(Ljava/lang/String;Ljava/lang/String;Ljava/math/BigDecimal;Ljava/util/Date;)V", "getFromTicker", "()Ljava/lang/String;", "getRate", "()Ljava/math/BigDecimal;", "getTimestamp", "()Ljava/util/Date;", "getToTicker", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(primaryKeys = {"from_ticker", "to_ticker"}, tableName = "exchange_rate")
/* compiled from: ExchangeRate.kt */
public final class ExchangeRate {
    @ColumnInfo(index = true, name = "from_ticker")
    @NotNull
    private final String fromTicker;
    @ColumnInfo(name = "rate")
    @NotNull
    private final BigDecimal rate;
    @ColumnInfo(name = "timestamp")
    @NotNull
    private final Date timestamp;
    @ColumnInfo(index = true, name = "to_ticker")
    @NotNull
    private final String toTicker;

    @NotNull
    public static /* synthetic */ ExchangeRate copy$default(ExchangeRate exchangeRate, String str, String str2, BigDecimal bigDecimal, Date date, int i, Object obj) {
        if ((i & 1) != 0) {
            str = exchangeRate.fromTicker;
        }
        if ((i & 2) != 0) {
            str2 = exchangeRate.toTicker;
        }
        if ((i & 4) != 0) {
            bigDecimal = exchangeRate.rate;
        }
        if ((i & 8) != 0) {
            date = exchangeRate.timestamp;
        }
        return exchangeRate.copy(str, str2, bigDecimal, date);
    }

    @NotNull
    public final String component1() {
        return this.fromTicker;
    }

    @NotNull
    public final String component2() {
        return this.toTicker;
    }

    @NotNull
    public final BigDecimal component3() {
        return this.rate;
    }

    @NotNull
    public final Date component4() {
        return this.timestamp;
    }

    @NotNull
    public final ExchangeRate copy(@NotNull String str, @NotNull String str2, @NotNull BigDecimal bigDecimal, @NotNull Date date) {
        Intrinsics.checkParameterIsNotNull(str, "fromTicker");
        Intrinsics.checkParameterIsNotNull(str2, "toTicker");
        Intrinsics.checkParameterIsNotNull(bigDecimal, "rate");
        Intrinsics.checkParameterIsNotNull(date, "timestamp");
        return new ExchangeRate(str, str2, bigDecimal, date);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.timestamp, (java.lang.Object) r3.timestamp) != false) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0033
            boolean r0 = r3 instanceof com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate
            if (r0 == 0) goto L_0x0031
            com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate r3 = (com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate) r3
            java.lang.String r0 = r2.fromTicker
            java.lang.String r1 = r3.fromTicker
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            java.lang.String r0 = r2.toTicker
            java.lang.String r1 = r3.toTicker
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            java.math.BigDecimal r0 = r2.rate
            java.math.BigDecimal r1 = r3.rate
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            java.util.Date r0 = r2.timestamp
            java.util.Date r3 = r3.timestamp
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r3 = 0
            return r3
        L_0x0033:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.exchangerate.ExchangeRate.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.fromTicker;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.toTicker;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        BigDecimal bigDecimal = this.rate;
        int hashCode3 = (hashCode2 + (bigDecimal != null ? bigDecimal.hashCode() : 0)) * 31;
        Date date = this.timestamp;
        if (date != null) {
            i = date.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExchangeRate(fromTicker=");
        sb.append(this.fromTicker);
        sb.append(", toTicker=");
        sb.append(this.toTicker);
        sb.append(", rate=");
        sb.append(this.rate);
        sb.append(", timestamp=");
        sb.append(this.timestamp);
        sb.append(")");
        return sb.toString();
    }

    public ExchangeRate(@NotNull String str, @NotNull String str2, @NotNull BigDecimal bigDecimal, @NotNull Date date) {
        Intrinsics.checkParameterIsNotNull(str, "fromTicker");
        Intrinsics.checkParameterIsNotNull(str2, "toTicker");
        Intrinsics.checkParameterIsNotNull(bigDecimal, "rate");
        Intrinsics.checkParameterIsNotNull(date, "timestamp");
        this.fromTicker = str;
        this.toTicker = str2;
        this.rate = bigDecimal;
        this.timestamp = date;
    }

    @NotNull
    public final String getFromTicker() {
        return this.fromTicker;
    }

    @NotNull
    public final String getToTicker() {
        return this.toTicker;
    }

    @NotNull
    public final BigDecimal getRate() {
        return this.rate;
    }

    @NotNull
    public final Date getTimestamp() {
        return this.timestamp;
    }
}
