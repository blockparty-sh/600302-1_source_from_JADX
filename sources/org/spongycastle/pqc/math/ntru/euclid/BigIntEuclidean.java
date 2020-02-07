package org.spongycastle.pqc.math.ntru.euclid;

import java.math.BigInteger;

public class BigIntEuclidean {
    public BigInteger gcd;

    /* renamed from: x */
    public BigInteger f1554x;

    /* renamed from: y */
    public BigInteger f1555y;

    private BigIntEuclidean() {
    }

    public static BigIntEuclidean calculate(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = BigInteger.ZERO;
        BigInteger bigInteger4 = BigInteger.ONE;
        BigInteger bigInteger5 = BigInteger.ONE;
        BigInteger bigInteger6 = BigInteger.ZERO;
        BigInteger bigInteger7 = bigInteger2;
        BigInteger bigInteger8 = bigInteger;
        BigInteger bigInteger9 = bigInteger7;
        while (!bigInteger9.equals(BigInteger.ZERO)) {
            BigInteger[] divideAndRemainder = bigInteger8.divideAndRemainder(bigInteger9);
            BigInteger bigInteger10 = divideAndRemainder[0];
            BigInteger bigInteger11 = divideAndRemainder[1];
            BigInteger bigInteger12 = bigInteger11;
            bigInteger8 = bigInteger9;
            bigInteger9 = bigInteger12;
            BigInteger subtract = bigInteger4.subtract(bigInteger10.multiply(bigInteger3));
            bigInteger4 = bigInteger3;
            bigInteger3 = subtract;
            BigInteger subtract2 = bigInteger6.subtract(bigInteger10.multiply(bigInteger5));
            bigInteger6 = bigInteger5;
            bigInteger5 = subtract2;
        }
        BigIntEuclidean bigIntEuclidean = new BigIntEuclidean();
        bigIntEuclidean.f1554x = bigInteger4;
        bigIntEuclidean.f1555y = bigInteger6;
        bigIntEuclidean.gcd = bigInteger8;
        return bigIntEuclidean;
    }
}
