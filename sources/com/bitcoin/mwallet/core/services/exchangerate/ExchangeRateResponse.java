package com.bitcoin.mwallet.core.services.exchangerate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u0016B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/exchangerate/ExchangeRateResponse;", "", "timestamp", "Ljava/util/Date;", "rates", "", "Lcom/bitcoin/mwallet/core/services/exchangerate/ExchangeRateResponse$ExchangeRate;", "(Ljava/util/Date;Ljava/util/Set;)V", "getRates", "()Ljava/util/Set;", "getTimestamp", "()Ljava/util/Date;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "ExchangeRate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ExchangeRateResponse.kt */
public final class ExchangeRateResponse {
    @NotNull
    private final Set<ExchangeRate> rates;
    @NotNull
    private final Date timestamp;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/exchangerate/ExchangeRateResponse$ExchangeRate;", "", "ticker", "", "name", "rate", "Ljava/math/BigDecimal;", "(Ljava/lang/String;Ljava/lang/String;Ljava/math/BigDecimal;)V", "getName", "()Ljava/lang/String;", "getRate", "()Ljava/math/BigDecimal;", "getTicker", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ExchangeRateResponse.kt */
    public static final class ExchangeRate {
        @NotNull
        private final String name;
        @NotNull
        private final BigDecimal rate;
        @NotNull
        private final String ticker;

        @NotNull
        public static /* synthetic */ ExchangeRate copy$default(ExchangeRate exchangeRate, String str, String str2, BigDecimal bigDecimal, int i, Object obj) {
            if ((i & 1) != 0) {
                str = exchangeRate.ticker;
            }
            if ((i & 2) != 0) {
                str2 = exchangeRate.name;
            }
            if ((i & 4) != 0) {
                bigDecimal = exchangeRate.rate;
            }
            return exchangeRate.copy(str, str2, bigDecimal);
        }

        @NotNull
        public final String component1() {
            return this.ticker;
        }

        @NotNull
        public final String component2() {
            return this.name;
        }

        @NotNull
        public final BigDecimal component3() {
            return this.rate;
        }

        @NotNull
        public final ExchangeRate copy(@NotNull String str, @NotNull String str2, @NotNull BigDecimal bigDecimal) {
            Intrinsics.checkParameterIsNotNull(str, "ticker");
            Intrinsics.checkParameterIsNotNull(str2, "name");
            Intrinsics.checkParameterIsNotNull(bigDecimal, "rate");
            return new ExchangeRate(str, str2, bigDecimal);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.rate, (java.lang.Object) r3.rate) != false) goto L_0x0029;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x0029
                boolean r0 = r3 instanceof com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse.ExchangeRate
                if (r0 == 0) goto L_0x0027
                com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse$ExchangeRate r3 = (com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse.ExchangeRate) r3
                java.lang.String r0 = r2.ticker
                java.lang.String r1 = r3.ticker
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0027
                java.lang.String r0 = r2.name
                java.lang.String r1 = r3.name
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0027
                java.math.BigDecimal r0 = r2.rate
                java.math.BigDecimal r3 = r3.rate
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
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse.ExchangeRate.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            String str = this.ticker;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.name;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            BigDecimal bigDecimal = this.rate;
            if (bigDecimal != null) {
                i = bigDecimal.hashCode();
            }
            return hashCode2 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ExchangeRate(ticker=");
            sb.append(this.ticker);
            sb.append(", name=");
            sb.append(this.name);
            sb.append(", rate=");
            sb.append(this.rate);
            sb.append(")");
            return sb.toString();
        }

        public ExchangeRate(@NotNull String str, @NotNull String str2, @NotNull BigDecimal bigDecimal) {
            Intrinsics.checkParameterIsNotNull(str, "ticker");
            Intrinsics.checkParameterIsNotNull(str2, "name");
            Intrinsics.checkParameterIsNotNull(bigDecimal, "rate");
            this.ticker = str;
            this.name = str2;
            this.rate = bigDecimal;
        }

        @NotNull
        public final String getTicker() {
            return this.ticker;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final BigDecimal getRate() {
            return this.rate;
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse$ExchangeRate>, for r2v0, types: [java.util.Set] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse copy$default(com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse r0, java.util.Date r1, java.util.Set<com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse.ExchangeRate> r2, int r3, java.lang.Object r4) {
        /*
            r4 = r3 & 1
            if (r4 == 0) goto L_0x0006
            java.util.Date r1 = r0.timestamp
        L_0x0006:
            r3 = r3 & 2
            if (r3 == 0) goto L_0x000c
            java.util.Set<com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse$ExchangeRate> r2 = r0.rates
        L_0x000c:
            com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse r0 = r0.copy(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse.copy$default(com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse, java.util.Date, java.util.Set, int, java.lang.Object):com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse");
    }

    @NotNull
    public final Date component1() {
        return this.timestamp;
    }

    @NotNull
    public final Set<ExchangeRate> component2() {
        return this.rates;
    }

    @NotNull
    public final ExchangeRateResponse copy(@NotNull Date date, @NotNull Set<ExchangeRate> set) {
        Intrinsics.checkParameterIsNotNull(date, "timestamp");
        Intrinsics.checkParameterIsNotNull(set, "rates");
        return new ExchangeRateResponse(date, set);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.rates, (java.lang.Object) r3.rates) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse r3 = (com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse) r3
            java.util.Date r0 = r2.timestamp
            java.util.Date r1 = r3.timestamp
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            java.util.Set<com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse$ExchangeRate> r0 = r2.rates
            java.util.Set<com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse$ExchangeRate> r3 = r3.rates
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.exchangerate.ExchangeRateResponse.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Date date = this.timestamp;
        int i = 0;
        int hashCode = (date != null ? date.hashCode() : 0) * 31;
        Set<ExchangeRate> set = this.rates;
        if (set != null) {
            i = set.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExchangeRateResponse(timestamp=");
        sb.append(this.timestamp);
        sb.append(", rates=");
        sb.append(this.rates);
        sb.append(")");
        return sb.toString();
    }

    public ExchangeRateResponse(@NotNull Date date, @NotNull Set<ExchangeRate> set) {
        Intrinsics.checkParameterIsNotNull(date, "timestamp");
        Intrinsics.checkParameterIsNotNull(set, "rates");
        this.timestamp = date;
        this.rates = set;
    }

    @NotNull
    public final Date getTimestamp() {
        return this.timestamp;
    }

    @NotNull
    public final Set<ExchangeRate> getRates() {
        return this.rates;
    }
}
