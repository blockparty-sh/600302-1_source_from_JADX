package org.bitcoinj.utils;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.math.BigInteger;
import org.bitcoinj.core.Coin;

public class ExchangeRate implements Serializable {
    public final Coin coin;
    public final Fiat fiat;

    public ExchangeRate(Coin coin2, Fiat fiat2) {
        Preconditions.checkArgument(coin2.isPositive());
        Preconditions.checkArgument(fiat2.isPositive());
        Preconditions.checkArgument(fiat2.currencyCode != null, "currency code required");
        this.coin = coin2;
        this.fiat = fiat2;
    }

    public ExchangeRate(Fiat fiat2) {
        this(Coin.COIN, fiat2);
    }

    public Fiat coinToFiat(Coin coin2) {
        BigInteger divide = BigInteger.valueOf(coin2.value).multiply(BigInteger.valueOf(this.fiat.value)).divide(BigInteger.valueOf(this.coin.value));
        if (divide.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) <= 0 && divide.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) >= 0) {
            return Fiat.valueOf(this.fiat.currencyCode, divide.longValue());
        }
        throw new ArithmeticException("Overflow");
    }

    public Coin fiatToCoin(Fiat fiat2) {
        Preconditions.checkArgument(fiat2.currencyCode.equals(this.fiat.currencyCode), "Currency mismatch: %s vs %s", fiat2.currencyCode, this.fiat.currencyCode);
        BigInteger divide = BigInteger.valueOf(fiat2.value).multiply(BigInteger.valueOf(this.coin.value)).divide(BigInteger.valueOf(this.fiat.value));
        if (divide.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0 || divide.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) < 0) {
            throw new ArithmeticException("Overflow");
        }
        try {
            return Coin.valueOf(divide.longValue());
        } catch (IllegalArgumentException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Overflow: ");
            sb.append(e.getMessage());
            throw new ArithmeticException(sb.toString());
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExchangeRate exchangeRate = (ExchangeRate) obj;
        if (!Objects.equal(this.coin, exchangeRate.coin) || !Objects.equal(this.fiat, exchangeRate.fiat)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(this.coin, this.fiat);
    }
}
