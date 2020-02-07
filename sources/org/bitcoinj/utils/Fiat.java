package org.bitcoinj.utils;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;
import com.google.common.primitives.Longs;
import java.io.Serializable;
import java.math.BigDecimal;
import org.bitcoinj.core.Monetary;

public final class Fiat implements Monetary, Comparable<Fiat>, Serializable {
    private static final MonetaryFormat FRIENDLY_FORMAT = MonetaryFormat.FIAT.postfixCode();
    private static final MonetaryFormat PLAIN_FORMAT = MonetaryFormat.FIAT.minDecimals(0).repeatOptionalDecimals(1, 4).noCode();
    public static final int SMALLEST_UNIT_EXPONENT = 4;
    public final String currencyCode;
    public final long value;

    public int smallestUnitExponent() {
        return 4;
    }

    private Fiat(String str, long j) {
        this.value = j;
        this.currencyCode = str;
    }

    public static Fiat valueOf(String str, long j) {
        return new Fiat(str, j);
    }

    public long getValue() {
        return this.value;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public static Fiat parseFiat(String str, String str2) {
        try {
            return valueOf(str, new BigDecimal(str2).movePointRight(4).toBigIntegerExact().longValue());
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Fiat add(Fiat fiat) {
        Preconditions.checkArgument(fiat.currencyCode.equals(this.currencyCode));
        return new Fiat(this.currencyCode, LongMath.checkedAdd(this.value, fiat.value));
    }

    public Fiat subtract(Fiat fiat) {
        Preconditions.checkArgument(fiat.currencyCode.equals(this.currencyCode));
        return new Fiat(this.currencyCode, LongMath.checkedSubtract(this.value, fiat.value));
    }

    public Fiat multiply(long j) {
        return new Fiat(this.currencyCode, LongMath.checkedMultiply(this.value, j));
    }

    public Fiat divide(long j) {
        return new Fiat(this.currencyCode, this.value / j);
    }

    public Fiat[] divideAndRemainder(long j) {
        return new Fiat[]{new Fiat(this.currencyCode, this.value / j), new Fiat(this.currencyCode, this.value % j)};
    }

    public long divide(Fiat fiat) {
        Preconditions.checkArgument(fiat.currencyCode.equals(this.currencyCode));
        return this.value / fiat.value;
    }

    public boolean isPositive() {
        return signum() == 1;
    }

    public boolean isNegative() {
        return signum() == -1;
    }

    public boolean isZero() {
        return signum() == 0;
    }

    public boolean isGreaterThan(Fiat fiat) {
        return compareTo(fiat) > 0;
    }

    public boolean isLessThan(Fiat fiat) {
        return compareTo(fiat) < 0;
    }

    public int signum() {
        long j = this.value;
        if (j == 0) {
            return 0;
        }
        return j < 0 ? -1 : 1;
    }

    public Fiat negate() {
        return new Fiat(this.currencyCode, -this.value);
    }

    public long longValue() {
        return this.value;
    }

    public String toFriendlyString() {
        return FRIENDLY_FORMAT.code(0, this.currencyCode).format(this).toString();
    }

    public String toPlainString() {
        return PLAIN_FORMAT.format(this).toString();
    }

    public String toString() {
        return Long.toString(this.value);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Fiat fiat = (Fiat) obj;
        if (this.value != fiat.value || !this.currencyCode.equals(fiat.currencyCode)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.value), this.currencyCode);
    }

    public int compareTo(Fiat fiat) {
        if (!this.currencyCode.equals(fiat.currencyCode)) {
            return this.currencyCode.compareTo(fiat.currencyCode);
        }
        return Longs.compare(this.value, fiat.value);
    }
}
