package org.spongycastle.math.p025ec;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECPoint.F2m;

/* renamed from: org.spongycastle.math.ec.Tnaf */
class Tnaf {
    private static final BigInteger MINUS_ONE = ECConstants.ONE.negate();
    private static final BigInteger MINUS_THREE = ECConstants.THREE.negate();
    private static final BigInteger MINUS_TWO = ECConstants.TWO.negate();
    public static final byte POW_2_WIDTH = 16;
    public static final byte WIDTH = 4;
    public static final ZTauElement[] alpha0;
    public static final byte[][] alpha0Tnaf = {null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, 1}};
    public static final ZTauElement[] alpha1 = {null, new ZTauElement(ECConstants.ONE, ECConstants.ZERO), null, new ZTauElement(MINUS_THREE, ECConstants.ONE), null, new ZTauElement(MINUS_ONE, ECConstants.ONE), null, new ZTauElement(ECConstants.ONE, ECConstants.ONE), null};
    public static final byte[][] alpha1Tnaf = {null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, -1}};

    Tnaf() {
    }

    static {
        BigInteger bigInteger = MINUS_ONE;
        alpha0 = new ZTauElement[]{null, new ZTauElement(ECConstants.ONE, ECConstants.ZERO), null, new ZTauElement(MINUS_THREE, MINUS_ONE), null, new ZTauElement(bigInteger, bigInteger), null, new ZTauElement(ECConstants.ONE, MINUS_ONE), null};
    }

    public static BigInteger norm(byte b, ZTauElement zTauElement) {
        BigInteger multiply = zTauElement.f1364u.multiply(zTauElement.f1364u);
        BigInteger multiply2 = zTauElement.f1364u.multiply(zTauElement.f1365v);
        BigInteger shiftLeft = zTauElement.f1365v.multiply(zTauElement.f1365v).shiftLeft(1);
        if (b == 1) {
            return multiply.add(multiply2).add(shiftLeft);
        }
        if (b == -1) {
            return multiply.subtract(multiply2).add(shiftLeft);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static SimpleBigDecimal norm(byte b, SimpleBigDecimal simpleBigDecimal, SimpleBigDecimal simpleBigDecimal2) {
        SimpleBigDecimal multiply = simpleBigDecimal.multiply(simpleBigDecimal);
        SimpleBigDecimal multiply2 = simpleBigDecimal.multiply(simpleBigDecimal2);
        SimpleBigDecimal shiftLeft = simpleBigDecimal2.multiply(simpleBigDecimal2).shiftLeft(1);
        if (b == 1) {
            return multiply.add(multiply2).add(shiftLeft);
        }
        if (b == -1) {
            return multiply.subtract(multiply2).add(shiftLeft);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    /* JADX INFO: used method not loaded: org.spongycastle.math.ec.SimpleBigDecimal.compareTo(java.math.BigInteger):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0090, code lost:
        if (r7.compareTo(MINUS_TWO) < 0) goto L_0x0092;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.spongycastle.math.p025ec.ZTauElement round(org.spongycastle.math.p025ec.SimpleBigDecimal r7, org.spongycastle.math.p025ec.SimpleBigDecimal r8, byte r9) {
        /*
            int r0 = r7.getScale()
            int r1 = r8.getScale()
            if (r1 != r0) goto L_0x00ac
            r0 = -1
            r1 = 1
            if (r9 == r1) goto L_0x0019
            if (r9 != r0) goto L_0x0011
            goto L_0x0019
        L_0x0011:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "mu must be 1 or -1"
            r7.<init>(r8)
            throw r7
        L_0x0019:
            java.math.BigInteger r2 = r7.round()
            java.math.BigInteger r3 = r8.round()
            org.spongycastle.math.ec.SimpleBigDecimal r7 = r7.subtract(r2)
            org.spongycastle.math.ec.SimpleBigDecimal r8 = r8.subtract(r3)
            org.spongycastle.math.ec.SimpleBigDecimal r4 = r7.add(r7)
            if (r9 != r1) goto L_0x0034
            org.spongycastle.math.ec.SimpleBigDecimal r4 = r4.add(r8)
            goto L_0x0038
        L_0x0034:
            org.spongycastle.math.ec.SimpleBigDecimal r4 = r4.subtract(r8)
        L_0x0038:
            org.spongycastle.math.ec.SimpleBigDecimal r5 = r8.add(r8)
            org.spongycastle.math.ec.SimpleBigDecimal r5 = r5.add(r8)
            org.spongycastle.math.ec.SimpleBigDecimal r8 = r5.add(r8)
            if (r9 != r1) goto L_0x004f
            org.spongycastle.math.ec.SimpleBigDecimal r5 = r7.subtract(r5)
            org.spongycastle.math.ec.SimpleBigDecimal r7 = r7.add(r8)
            goto L_0x0057
        L_0x004f:
            org.spongycastle.math.ec.SimpleBigDecimal r5 = r7.add(r5)
            org.spongycastle.math.ec.SimpleBigDecimal r7 = r7.subtract(r8)
        L_0x0057:
            java.math.BigInteger r8 = org.spongycastle.math.p025ec.ECConstants.ONE
            int r8 = r4.compareTo(r8)
            r6 = 0
            if (r8 < 0) goto L_0x006c
            java.math.BigInteger r8 = MINUS_ONE
            int r8 = r5.compareTo(r8)
            if (r8 >= 0) goto L_0x0069
            goto L_0x0074
        L_0x0069:
            r8 = 0
            r6 = 1
            goto L_0x0077
        L_0x006c:
            java.math.BigInteger r8 = org.spongycastle.math.p025ec.ECConstants.TWO
            int r8 = r7.compareTo(r8)
            if (r8 < 0) goto L_0x0076
        L_0x0074:
            r8 = r9
            goto L_0x0077
        L_0x0076:
            r8 = 0
        L_0x0077:
            java.math.BigInteger r1 = MINUS_ONE
            int r1 = r4.compareTo(r1)
            if (r1 >= 0) goto L_0x008a
            java.math.BigInteger r7 = org.spongycastle.math.p025ec.ECConstants.ONE
            int r7 = r5.compareTo(r7)
            if (r7 < 0) goto L_0x0088
            goto L_0x0092
        L_0x0088:
            r6 = -1
            goto L_0x0094
        L_0x008a:
            java.math.BigInteger r0 = MINUS_TWO
            int r7 = r7.compareTo(r0)
            if (r7 >= 0) goto L_0x0094
        L_0x0092:
            int r7 = -r9
            byte r8 = (byte) r7
        L_0x0094:
            long r0 = (long) r6
            java.math.BigInteger r7 = java.math.BigInteger.valueOf(r0)
            java.math.BigInteger r7 = r2.add(r7)
            long r8 = (long) r8
            java.math.BigInteger r8 = java.math.BigInteger.valueOf(r8)
            java.math.BigInteger r8 = r3.add(r8)
            org.spongycastle.math.ec.ZTauElement r9 = new org.spongycastle.math.ec.ZTauElement
            r9.<init>(r7, r8)
            return r9
        L_0x00ac:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "lambda0 and lambda1 do not have same scale"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.math.p025ec.Tnaf.round(org.spongycastle.math.ec.SimpleBigDecimal, org.spongycastle.math.ec.SimpleBigDecimal, byte):org.spongycastle.math.ec.ZTauElement");
    }

    public static SimpleBigDecimal approximateDivisionByN(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, byte b, int i, int i2) {
        int i3 = ((i + 5) / 2) + i2;
        BigInteger multiply = bigInteger2.multiply(bigInteger.shiftRight(((i - i3) - 2) + b));
        BigInteger add = multiply.add(bigInteger3.multiply(multiply.shiftRight(i)));
        int i4 = i3 - i2;
        BigInteger shiftRight = add.shiftRight(i4);
        if (add.testBit(i4 - 1)) {
            shiftRight = shiftRight.add(ECConstants.ONE);
        }
        return new SimpleBigDecimal(shiftRight, i2);
    }

    public static byte[] tauAdicNaf(byte b, ZTauElement zTauElement) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        if (b == 1 || b == -1) {
            int bitLength = norm(b, zTauElement).bitLength();
            byte[] bArr = new byte[(bitLength > 30 ? bitLength + 4 : 34)];
            BigInteger bigInteger3 = zTauElement.f1364u;
            BigInteger bigInteger4 = zTauElement.f1365v;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (!bigInteger.equals(ECConstants.ZERO) || !bigInteger4.equals(ECConstants.ZERO)) {
                    if (bigInteger.testBit(0)) {
                        bArr[i2] = (byte) ECConstants.TWO.subtract(bigInteger.subtract(bigInteger4.shiftLeft(1)).mod(ECConstants.FOUR)).intValue();
                        if (bArr[i2] == 1) {
                            bigInteger = bigInteger.clearBit(0);
                        } else {
                            bigInteger = bigInteger.add(ECConstants.ONE);
                        }
                        i = i2;
                    } else {
                        bArr[i2] = 0;
                    }
                    BigInteger shiftRight = bigInteger.shiftRight(1);
                    if (b == 1) {
                        bigInteger2 = bigInteger4.add(shiftRight);
                    } else {
                        bigInteger2 = bigInteger4.subtract(shiftRight);
                    }
                    i2++;
                    BigInteger negate = bigInteger.shiftRight(1).negate();
                    bigInteger3 = bigInteger2;
                    bigInteger4 = negate;
                } else {
                    int i3 = i + 1;
                    byte[] bArr2 = new byte[i3];
                    System.arraycopy(bArr, 0, bArr2, 0, i3);
                    return bArr2;
                }
            }
        } else {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
    }

    public static F2m tau(F2m f2m) {
        return f2m.tau();
    }

    public static byte getMu(ECCurve.F2m f2m) {
        if (f2m.isKoblitz()) {
            return f2m.getA().isZero() ? (byte) -1 : 1;
        }
        throw new IllegalArgumentException("No Koblitz curve (ABC), TNAF multiplication not possible");
    }

    public static BigInteger[] getLucas(byte b, int i, boolean z) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        if (b == 1 || b == -1) {
            if (z) {
                bigInteger = ECConstants.TWO;
                bigInteger2 = BigInteger.valueOf((long) b);
            } else {
                bigInteger = ECConstants.ZERO;
                bigInteger2 = ECConstants.ONE;
            }
            BigInteger bigInteger3 = bigInteger2;
            BigInteger bigInteger4 = bigInteger;
            int i2 = 1;
            while (i2 < i) {
                i2++;
                BigInteger bigInteger5 = bigInteger3;
                bigInteger3 = (b == 1 ? bigInteger3 : bigInteger3.negate()).subtract(bigInteger4.shiftLeft(1));
                bigInteger4 = bigInteger5;
            }
            return new BigInteger[]{bigInteger4, bigInteger3};
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static BigInteger getTw(byte b, int i) {
        if (i != 4) {
            BigInteger[] lucas = getLucas(b, i, false);
            BigInteger bit = ECConstants.ZERO.setBit(i);
            return ECConstants.TWO.multiply(lucas[0]).multiply(lucas[1].modInverse(bit)).mod(bit);
        } else if (b == 1) {
            return BigInteger.valueOf(6);
        } else {
            return BigInteger.valueOf(10);
        }
    }

    public static BigInteger[] getSi(ECCurve.F2m f2m) {
        if (f2m.isKoblitz()) {
            int m = f2m.getM();
            int intValue = f2m.getA().toBigInteger().intValue();
            byte mu = f2m.getMu();
            int shiftsForCofactor = getShiftsForCofactor(f2m.getCofactor());
            BigInteger[] lucas = getLucas(mu, (m + 3) - intValue, false);
            if (mu == 1) {
                lucas[0] = lucas[0].negate();
                lucas[1] = lucas[1].negate();
            }
            return new BigInteger[]{ECConstants.ONE.add(lucas[1]).shiftRight(shiftsForCofactor), ECConstants.ONE.add(lucas[0]).shiftRight(shiftsForCofactor).negate()};
        }
        throw new IllegalArgumentException("si is defined for Koblitz curves only");
    }

    protected static int getShiftsForCofactor(BigInteger bigInteger) {
        if (bigInteger != null) {
            if (bigInteger.equals(ECConstants.TWO)) {
                return 1;
            }
            if (bigInteger.equals(ECConstants.FOUR)) {
                return 2;
            }
        }
        throw new IllegalArgumentException("h (Cofactor) must be 2 or 4");
    }

    public static ZTauElement partModReduction(BigInteger bigInteger, int i, byte b, BigInteger[] bigIntegerArr, byte b2, byte b3) {
        BigInteger bigInteger2;
        byte b4 = b2;
        if (b4 == 1) {
            bigInteger2 = bigIntegerArr[0].add(bigIntegerArr[1]);
        } else {
            bigInteger2 = bigIntegerArr[0].subtract(bigIntegerArr[1]);
        }
        BigInteger bigInteger3 = bigInteger2;
        int i2 = i;
        BigInteger bigInteger4 = bigInteger;
        BigInteger bigInteger5 = getLucas(b4, i, true)[1];
        byte b5 = b;
        int i3 = i;
        byte b6 = b3;
        ZTauElement round = round(approximateDivisionByN(bigInteger4, bigIntegerArr[0], bigInteger5, b5, i3, b6), approximateDivisionByN(bigInteger4, bigIntegerArr[1], bigInteger5, b5, i3, b6), b4);
        return new ZTauElement(bigInteger.subtract(bigInteger3.multiply(round.f1364u)).subtract(BigInteger.valueOf(2).multiply(bigIntegerArr[1]).multiply(round.f1365v)), bigIntegerArr[1].multiply(round.f1364u).subtract(bigIntegerArr[0].multiply(round.f1365v)));
    }

    public static F2m multiplyRTnaf(F2m f2m, BigInteger bigInteger) {
        ECCurve.F2m f2m2 = (ECCurve.F2m) f2m.getCurve();
        return multiplyTnaf(f2m, partModReduction(bigInteger, f2m2.getM(), (byte) f2m2.getA().toBigInteger().intValue(), f2m2.getSi(), f2m2.getMu(), 10));
    }

    public static F2m multiplyTnaf(F2m f2m, ZTauElement zTauElement) {
        return multiplyFromTnaf(f2m, tauAdicNaf(((ECCurve.F2m) f2m.getCurve()).getMu(), zTauElement));
    }

    public static F2m multiplyFromTnaf(F2m f2m, byte[] bArr) {
        F2m f2m2 = (F2m) ((ECCurve.F2m) f2m.getCurve()).getInfinity();
        for (int length = bArr.length - 1; length >= 0; length--) {
            f2m2 = tau(f2m2);
            if (bArr[length] == 1) {
                f2m2 = f2m2.addSimple(f2m);
            } else if (bArr[length] == -1) {
                f2m2 = f2m2.subtractSimple(f2m);
            }
        }
        return f2m2;
    }

    public static byte[] tauAdicWNaf(byte b, ZTauElement zTauElement, byte b2, BigInteger bigInteger, BigInteger bigInteger2, ZTauElement[] zTauElementArr) {
        BigInteger bigInteger3;
        int i;
        byte b3;
        boolean z;
        if (b == 1 || b == -1) {
            int bitLength = norm(b, zTauElement).bitLength();
            byte[] bArr = new byte[(bitLength > 30 ? bitLength + 4 + b2 : b2 + 34)];
            BigInteger shiftRight = bigInteger.shiftRight(1);
            BigInteger bigInteger4 = zTauElement.f1364u;
            BigInteger bigInteger5 = zTauElement.f1365v;
            int i2 = 0;
            while (true) {
                if (bigInteger4.equals(ECConstants.ZERO) && bigInteger5.equals(ECConstants.ZERO)) {
                    return bArr;
                }
                if (bigInteger4.testBit(0)) {
                    BigInteger mod = bigInteger4.add(bigInteger5.multiply(bigInteger2)).mod(bigInteger);
                    if (mod.compareTo(shiftRight) >= 0) {
                        i = mod.subtract(bigInteger).intValue();
                    } else {
                        i = mod.intValue();
                    }
                    byte b4 = (byte) i;
                    bArr[i2] = b4;
                    if (b4 < 0) {
                        b3 = (byte) (-b4);
                        z = false;
                    } else {
                        b3 = b4;
                        z = true;
                    }
                    if (z) {
                        bigInteger4 = bigInteger4.subtract(zTauElementArr[b3].f1364u);
                        bigInteger5 = bigInteger5.subtract(zTauElementArr[b3].f1365v);
                    } else {
                        bigInteger4 = bigInteger4.add(zTauElementArr[b3].f1364u);
                        bigInteger5 = bigInteger5.add(zTauElementArr[b3].f1365v);
                    }
                } else {
                    bArr[i2] = 0;
                }
                if (b == 1) {
                    bigInteger3 = bigInteger5.add(bigInteger4.shiftRight(1));
                } else {
                    bigInteger3 = bigInteger5.subtract(bigInteger4.shiftRight(1));
                }
                i2++;
                BigInteger negate = bigInteger4.shiftRight(1).negate();
                bigInteger4 = bigInteger3;
                bigInteger5 = negate;
            }
        } else {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
    }

    public static F2m[] getPreComp(F2m f2m, byte b) {
        byte[][] bArr;
        F2m[] f2mArr = new F2m[16];
        f2mArr[1] = f2m;
        if (b == 0) {
            bArr = alpha0Tnaf;
        } else {
            bArr = alpha1Tnaf;
        }
        int length = bArr.length;
        for (int i = 3; i < length; i += 2) {
            f2mArr[i] = multiplyFromTnaf(f2m, bArr[i]);
        }
        f2m.getCurve().normalizeAll(f2mArr);
        return f2mArr;
    }
}
