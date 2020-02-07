package org.bitcoinj.core;

import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;
import com.google.common.primitives.Longs;
import java.io.Serializable;
import java.math.BigDecimal;
import org.bitcoinj.utils.MonetaryFormat;

public final class Coin implements Monetary, Comparable<Coin>, Serializable {
    public static final Coin CENT = COIN.divide(100);
    public static final Coin COIN = valueOf(COIN_VALUE);
    private static final long COIN_VALUE = LongMath.pow(10, 8);
    public static final Coin FIFTY_COINS = COIN.multiply(50);
    private static final MonetaryFormat FRIENDLY_FORMAT = MonetaryFormat.BTC.minDecimals(2).repeatOptionalDecimals(1, 6).postfixCode();
    public static final Coin MICROCOIN = MILLICOIN.divide(1000);
    public static final Coin MILLICOIN = COIN.divide(1000);
    public static final Coin NEGATIVE_SATOSHI = valueOf(-1);
    private static final MonetaryFormat PLAIN_FORMAT = MonetaryFormat.BTC.minDecimals(0).repeatOptionalDecimals(1, 8).noCode();
    public static final Coin SATOSHI = valueOf(1);
    public static final int SMALLEST_UNIT_EXPONENT = 8;
    public static final Coin ZERO = valueOf(0);
    public final long value;

    public int smallestUnitExponent() {
        return 8;
    }

    private Coin(long j) {
        this.value = j;
    }

    public static Coin valueOf(long j) {
        return new Coin(j);
    }

    public long getValue() {
        return this.value;
    }

    public static Coin valueOf(int i, int i2) {
        boolean z = true;
        Preconditions.checkArgument(i2 < 100);
        Preconditions.checkArgument(i2 >= 0);
        if (i < 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        return COIN.multiply((long) i).add(CENT.multiply((long) i2));
    }

    public static Coin parseCoin(String str) {
        try {
            return valueOf(new BigDecimal(str).movePointRight(8).toBigIntegerExact().longValue());
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Coin add(Coin coin) {
        return new Coin(LongMath.checkedAdd(this.value, coin.value));
    }

    public Coin plus(Coin coin) {
        return add(coin);
    }

    public Coin subtract(Coin coin) {
        return new Coin(LongMath.checkedSubtract(this.value, coin.value));
    }

    public Coin minus(Coin coin) {
        return subtract(coin);
    }

    public Coin multiply(long j) {
        return new Coin(LongMath.checkedMultiply(this.value, j));
    }

    public Coin times(long j) {
        return multiply(j);
    }

    public Coin times(int i) {
        return multiply((long) i);
    }

    public Coin divide(long j) {
        return new Coin(this.value / j);
    }

    public Coin div(long j) {
        return divide(j);
    }

    public Coin div(int i) {
        return divide((long) i);
    }

    public Coin[] divideAndRemainder(long j) {
        return new Coin[]{new Coin(this.value / j), new Coin(this.value % j)};
    }

    public long divide(Coin coin) {
        return this.value / coin.value;
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

    public boolean isGreaterThan(Coin coin) {
        return compareTo(coin) > 0;
    }

    public boolean isLessThan(Coin coin) {
        return compareTo(coin) < 0;
    }

    public Coin shiftLeft(int i) {
        return new Coin(this.value << i);
    }

    public Coin shiftRight(int i) {
        return new Coin(this.value >> i);
    }

    public int signum() {
        long j = this.value;
        if (j == 0) {
            return 0;
        }
        return j < 0 ? -1 : 1;
    }

    public Coin negate() {
        return new Coin(-this.value);
    }

    public long longValue() {
        return this.value;
    }

    public String toFriendlyString() {
        return FRIENDLY_FORMAT.format(this).toString();
    }

    public String toPlainString() {
        return PLAIN_FORMAT.format(this).toString();
    }

    public String toString() {
        return Long.toString(this.value);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.value != ((Coin) obj).value) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (int) this.value;
    }

    public int compareTo(Coin coin) {
        return Longs.compare(this.value, coin.value);
    }
}
