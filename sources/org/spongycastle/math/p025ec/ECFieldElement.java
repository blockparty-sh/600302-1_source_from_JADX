package org.spongycastle.math.p025ec;

import java.math.BigInteger;
import java.util.Random;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

/* renamed from: org.spongycastle.math.ec.ECFieldElement */
public abstract class ECFieldElement implements ECConstants {

    /* renamed from: org.spongycastle.math.ec.ECFieldElement$F2m */
    public static class F2m extends ECFieldElement {
        public static final int GNB = 1;
        public static final int PPB = 3;
        public static final int TPB = 2;

        /* renamed from: ks */
        private int[] f1355ks;

        /* renamed from: m */
        private int f1356m;
        private int representation;

        /* renamed from: x */
        private LongArray f1357x;

        public String getFieldName() {
            return "F2m";
        }

        public ECFieldElement negate() {
            return this;
        }

        public F2m(int i, int i2, int i3, int i4, BigInteger bigInteger) {
            if (i3 == 0 && i4 == 0) {
                this.representation = 2;
                this.f1355ks = new int[]{i2};
            } else if (i3 >= i4) {
                throw new IllegalArgumentException("k2 must be smaller than k3");
            } else if (i3 > 0) {
                this.representation = 3;
                this.f1355ks = new int[]{i2, i3, i4};
            } else {
                throw new IllegalArgumentException("k2 must be larger than 0");
            }
            this.f1356m = i;
            this.f1357x = new LongArray(bigInteger);
        }

        public F2m(int i, int i2, BigInteger bigInteger) {
            this(i, i2, 0, 0, bigInteger);
        }

        private F2m(int i, int[] iArr, LongArray longArray) {
            this.f1356m = i;
            this.representation = iArr.length == 1 ? 2 : 3;
            this.f1355ks = iArr;
            this.f1357x = longArray;
        }

        public int bitLength() {
            return this.f1357x.degree();
        }

        public boolean isOne() {
            return this.f1357x.isOne();
        }

        public boolean isZero() {
            return this.f1357x.isZero();
        }

        public boolean testBitZero() {
            return this.f1357x.testBitZero();
        }

        public BigInteger toBigInteger() {
            return this.f1357x.toBigInteger();
        }

        public int getFieldSize() {
            return this.f1356m;
        }

        public static void checkFieldElements(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            if (!(eCFieldElement instanceof F2m) || !(eCFieldElement2 instanceof F2m)) {
                throw new IllegalArgumentException("Field elements are not both instances of ECFieldElement.F2m");
            }
            F2m f2m = (F2m) eCFieldElement;
            F2m f2m2 = (F2m) eCFieldElement2;
            if (f2m.representation != f2m2.representation) {
                throw new IllegalArgumentException("One of the F2m field elements has incorrect representation");
            } else if (f2m.f1356m != f2m2.f1356m || !Arrays.areEqual(f2m.f1355ks, f2m2.f1355ks)) {
                throw new IllegalArgumentException("Field elements are not elements of the same field F2m");
            }
        }

        public ECFieldElement add(ECFieldElement eCFieldElement) {
            LongArray longArray = (LongArray) this.f1357x.clone();
            longArray.addShiftedByWords(((F2m) eCFieldElement).f1357x, 0);
            return new F2m(this.f1356m, this.f1355ks, longArray);
        }

        public ECFieldElement addOne() {
            return new F2m(this.f1356m, this.f1355ks, this.f1357x.addOne());
        }

        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return add(eCFieldElement);
        }

        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            int i = this.f1356m;
            int[] iArr = this.f1355ks;
            return new F2m(i, iArr, this.f1357x.modMultiply(((F2m) eCFieldElement).f1357x, i, iArr));
        }

        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
        }

        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            LongArray longArray = this.f1357x;
            LongArray longArray2 = ((F2m) eCFieldElement).f1357x;
            LongArray longArray3 = ((F2m) eCFieldElement2).f1357x;
            LongArray longArray4 = ((F2m) eCFieldElement3).f1357x;
            LongArray multiply = longArray.multiply(longArray2, this.f1356m, this.f1355ks);
            LongArray multiply2 = longArray3.multiply(longArray4, this.f1356m, this.f1355ks);
            if (multiply == longArray || multiply == longArray2) {
                multiply = (LongArray) multiply.clone();
            }
            multiply.addShiftedByWords(multiply2, 0);
            multiply.reduce(this.f1356m, this.f1355ks);
            return new F2m(this.f1356m, this.f1355ks, multiply);
        }

        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return multiply(eCFieldElement.invert());
        }

        public ECFieldElement square() {
            int i = this.f1356m;
            int[] iArr = this.f1355ks;
            return new F2m(i, iArr, this.f1357x.modSquare(i, iArr));
        }

        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return squarePlusProduct(eCFieldElement, eCFieldElement2);
        }

        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            LongArray longArray = this.f1357x;
            LongArray longArray2 = ((F2m) eCFieldElement).f1357x;
            LongArray longArray3 = ((F2m) eCFieldElement2).f1357x;
            LongArray square = longArray.square(this.f1356m, this.f1355ks);
            LongArray multiply = longArray2.multiply(longArray3, this.f1356m, this.f1355ks);
            if (square == longArray) {
                square = (LongArray) square.clone();
            }
            square.addShiftedByWords(multiply, 0);
            square.reduce(this.f1356m, this.f1355ks);
            return new F2m(this.f1356m, this.f1355ks, square);
        }

        public ECFieldElement invert() {
            int i = this.f1356m;
            int[] iArr = this.f1355ks;
            return new F2m(i, iArr, this.f1357x.modInverse(i, iArr));
        }

        public ECFieldElement sqrt() {
            LongArray longArray = this.f1357x;
            if (longArray.isOne() || longArray.isZero()) {
                return this;
            }
            int i = this.f1356m;
            return new F2m(this.f1356m, this.f1355ks, longArray.modSquareN(i - 1, i, this.f1355ks));
        }

        public int getRepresentation() {
            return this.representation;
        }

        public int getM() {
            return this.f1356m;
        }

        public int getK1() {
            return this.f1355ks[0];
        }

        public int getK2() {
            int[] iArr = this.f1355ks;
            if (iArr.length >= 2) {
                return iArr[1];
            }
            return 0;
        }

        public int getK3() {
            int[] iArr = this.f1355ks;
            if (iArr.length >= 3) {
                return iArr[2];
            }
            return 0;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof F2m)) {
                return false;
            }
            F2m f2m = (F2m) obj;
            if (this.f1356m != f2m.f1356m || this.representation != f2m.representation || !Arrays.areEqual(this.f1355ks, f2m.f1355ks) || !this.f1357x.equals(f2m.f1357x)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (this.f1357x.hashCode() ^ this.f1356m) ^ Arrays.hashCode(this.f1355ks);
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECFieldElement$Fp */
    public static class C3640Fp extends ECFieldElement {

        /* renamed from: q */
        BigInteger f1358q;

        /* renamed from: r */
        BigInteger f1359r;

        /* renamed from: x */
        BigInteger f1360x;

        public String getFieldName() {
            return "Fp";
        }

        static BigInteger calculateResidue(BigInteger bigInteger) {
            int bitLength = bigInteger.bitLength();
            if (bitLength < 96 || bigInteger.shiftRight(bitLength - 64).longValue() != -1) {
                return null;
            }
            return ONE.shiftLeft(bitLength).subtract(bigInteger);
        }

        public C3640Fp(BigInteger bigInteger, BigInteger bigInteger2) {
            this(bigInteger, calculateResidue(bigInteger), bigInteger2);
        }

        C3640Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            if (bigInteger3 == null || bigInteger3.signum() < 0 || bigInteger3.compareTo(bigInteger) >= 0) {
                throw new IllegalArgumentException("x value invalid in Fp field element");
            }
            this.f1358q = bigInteger;
            this.f1359r = bigInteger2;
            this.f1360x = bigInteger3;
        }

        public BigInteger toBigInteger() {
            return this.f1360x;
        }

        public int getFieldSize() {
            return this.f1358q.bitLength();
        }

        public BigInteger getQ() {
            return this.f1358q;
        }

        public ECFieldElement add(ECFieldElement eCFieldElement) {
            return new C3640Fp(this.f1358q, this.f1359r, modAdd(this.f1360x, eCFieldElement.toBigInteger()));
        }

        public ECFieldElement addOne() {
            BigInteger add = this.f1360x.add(ECConstants.ONE);
            if (add.compareTo(this.f1358q) == 0) {
                add = ECConstants.ZERO;
            }
            return new C3640Fp(this.f1358q, this.f1359r, add);
        }

        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return new C3640Fp(this.f1358q, this.f1359r, modSubtract(this.f1360x, eCFieldElement.toBigInteger()));
        }

        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            return new C3640Fp(this.f1358q, this.f1359r, modMult(this.f1360x, eCFieldElement.toBigInteger()));
        }

        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f1360x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new C3640Fp(this.f1358q, this.f1359r, modReduce(bigInteger.multiply(bigInteger2).subtract(bigInteger3.multiply(bigInteger4))));
        }

        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f1360x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new C3640Fp(this.f1358q, this.f1359r, modReduce(bigInteger.multiply(bigInteger2).add(bigInteger3.multiply(bigInteger4))));
        }

        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return new C3640Fp(this.f1358q, this.f1359r, modMult(this.f1360x, modInverse(eCFieldElement.toBigInteger())));
        }

        public ECFieldElement negate() {
            if (this.f1360x.signum() == 0) {
                return this;
            }
            BigInteger bigInteger = this.f1358q;
            return new C3640Fp(bigInteger, this.f1359r, bigInteger.subtract(this.f1360x));
        }

        public ECFieldElement square() {
            BigInteger bigInteger = this.f1358q;
            BigInteger bigInteger2 = this.f1359r;
            BigInteger bigInteger3 = this.f1360x;
            return new C3640Fp(bigInteger, bigInteger2, modMult(bigInteger3, bigInteger3));
        }

        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f1360x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new C3640Fp(this.f1358q, this.f1359r, modReduce(bigInteger.multiply(bigInteger).subtract(bigInteger2.multiply(bigInteger3))));
        }

        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f1360x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new C3640Fp(this.f1358q, this.f1359r, modReduce(bigInteger.multiply(bigInteger).add(bigInteger2.multiply(bigInteger3))));
        }

        public ECFieldElement invert() {
            return new C3640Fp(this.f1358q, this.f1359r, modInverse(this.f1360x));
        }

        public ECFieldElement sqrt() {
            if (isZero() || isOne()) {
                return this;
            }
            if (!this.f1358q.testBit(0)) {
                throw new RuntimeException("not done yet");
            } else if (this.f1358q.testBit(1)) {
                BigInteger add = this.f1358q.shiftRight(2).add(ECConstants.ONE);
                BigInteger bigInteger = this.f1358q;
                return checkSqrt(new C3640Fp(bigInteger, this.f1359r, this.f1360x.modPow(add, bigInteger)));
            } else if (this.f1358q.testBit(2)) {
                BigInteger modPow = this.f1360x.modPow(this.f1358q.shiftRight(3), this.f1358q);
                BigInteger modMult = modMult(modPow, this.f1360x);
                if (modMult(modMult, modPow).equals(ECConstants.ONE)) {
                    return checkSqrt(new C3640Fp(this.f1358q, this.f1359r, modMult));
                }
                return checkSqrt(new C3640Fp(this.f1358q, this.f1359r, modMult(modMult, ECConstants.TWO.modPow(this.f1358q.shiftRight(2), this.f1358q))));
            } else {
                BigInteger shiftRight = this.f1358q.shiftRight(1);
                if (!this.f1360x.modPow(shiftRight, this.f1358q).equals(ECConstants.ONE)) {
                    return null;
                }
                BigInteger bigInteger2 = this.f1360x;
                BigInteger modDouble = modDouble(modDouble(bigInteger2));
                BigInteger add2 = shiftRight.add(ECConstants.ONE);
                BigInteger subtract = this.f1358q.subtract(ECConstants.ONE);
                Random random = new Random();
                while (true) {
                    BigInteger bigInteger3 = new BigInteger(this.f1358q.bitLength(), random);
                    if (bigInteger3.compareTo(this.f1358q) < 0 && modReduce(bigInteger3.multiply(bigInteger3).subtract(modDouble)).modPow(shiftRight, this.f1358q).equals(subtract)) {
                        BigInteger[] lucasSequence = lucasSequence(bigInteger3, bigInteger2, add2);
                        BigInteger bigInteger4 = lucasSequence[0];
                        BigInteger bigInteger5 = lucasSequence[1];
                        if (modMult(bigInteger5, bigInteger5).equals(modDouble)) {
                            return new C3640Fp(this.f1358q, this.f1359r, modHalfAbs(bigInteger5));
                        }
                        if (!bigInteger4.equals(ECConstants.ONE) && !bigInteger4.equals(subtract)) {
                            return null;
                        }
                    }
                }
            }
        }

        private ECFieldElement checkSqrt(ECFieldElement eCFieldElement) {
            if (eCFieldElement.square().equals(this)) {
                return eCFieldElement;
            }
            return null;
        }

        private BigInteger[] lucasSequence(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            int bitLength = bigInteger3.bitLength();
            int lowestSetBit = bigInteger3.getLowestSetBit();
            BigInteger bigInteger4 = ECConstants.ONE;
            BigInteger bigInteger5 = ECConstants.TWO;
            BigInteger bigInteger6 = ECConstants.ONE;
            BigInteger bigInteger7 = ECConstants.ONE;
            BigInteger bigInteger8 = bigInteger;
            for (int i = bitLength - 1; i >= lowestSetBit + 1; i--) {
                bigInteger6 = modMult(bigInteger6, bigInteger7);
                if (bigInteger3.testBit(i)) {
                    bigInteger7 = modMult(bigInteger6, bigInteger2);
                    bigInteger4 = modMult(bigInteger4, bigInteger8);
                    bigInteger5 = modReduce(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger6)));
                    bigInteger8 = modReduce(bigInteger8.multiply(bigInteger8).subtract(bigInteger7.shiftLeft(1)));
                } else {
                    bigInteger4 = modReduce(bigInteger4.multiply(bigInteger5).subtract(bigInteger6));
                    BigInteger modReduce = modReduce(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger6)));
                    bigInteger5 = modReduce(bigInteger5.multiply(bigInteger5).subtract(bigInteger6.shiftLeft(1)));
                    bigInteger8 = modReduce;
                    bigInteger7 = bigInteger6;
                }
            }
            BigInteger modMult = modMult(bigInteger6, bigInteger7);
            BigInteger modMult2 = modMult(modMult, bigInteger2);
            BigInteger modReduce2 = modReduce(bigInteger4.multiply(bigInteger5).subtract(modMult));
            BigInteger modReduce3 = modReduce(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(modMult)));
            BigInteger modMult3 = modMult(modMult, modMult2);
            BigInteger bigInteger9 = modReduce3;
            for (int i2 = 1; i2 <= lowestSetBit; i2++) {
                modReduce2 = modMult(modReduce2, bigInteger9);
                bigInteger9 = modReduce(bigInteger9.multiply(bigInteger9).subtract(modMult3.shiftLeft(1)));
                modMult3 = modMult(modMult3, modMult3);
            }
            return new BigInteger[]{modReduce2, bigInteger9};
        }

        /* access modifiers changed from: protected */
        public BigInteger modAdd(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger add = bigInteger.add(bigInteger2);
            return add.compareTo(this.f1358q) >= 0 ? add.subtract(this.f1358q) : add;
        }

        /* access modifiers changed from: protected */
        public BigInteger modDouble(BigInteger bigInteger) {
            BigInteger shiftLeft = bigInteger.shiftLeft(1);
            return shiftLeft.compareTo(this.f1358q) >= 0 ? shiftLeft.subtract(this.f1358q) : shiftLeft;
        }

        /* access modifiers changed from: protected */
        public BigInteger modHalf(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f1358q.add(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        /* access modifiers changed from: protected */
        public BigInteger modHalfAbs(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f1358q.subtract(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        /* access modifiers changed from: protected */
        public BigInteger modInverse(BigInteger bigInteger) {
            int fieldSize = getFieldSize();
            int i = (fieldSize + 31) >> 5;
            int[] fromBigInteger = Nat.fromBigInteger(fieldSize, this.f1358q);
            int[] fromBigInteger2 = Nat.fromBigInteger(fieldSize, bigInteger);
            int[] create = Nat.create(i);
            Mod.invert(fromBigInteger, fromBigInteger2, create);
            return Nat.toBigInteger(i, create);
        }

        /* access modifiers changed from: protected */
        public BigInteger modMult(BigInteger bigInteger, BigInteger bigInteger2) {
            return modReduce(bigInteger.multiply(bigInteger2));
        }

        /* access modifiers changed from: protected */
        public BigInteger modReduce(BigInteger bigInteger) {
            if (this.f1359r == null) {
                return bigInteger.mod(this.f1358q);
            }
            boolean z = bigInteger.signum() < 0;
            if (z) {
                bigInteger = bigInteger.abs();
            }
            int bitLength = this.f1358q.bitLength();
            boolean equals = this.f1359r.equals(ECConstants.ONE);
            while (bigInteger.bitLength() > bitLength + 1) {
                BigInteger shiftRight = bigInteger.shiftRight(bitLength);
                BigInteger subtract = bigInteger.subtract(shiftRight.shiftLeft(bitLength));
                if (!equals) {
                    shiftRight = shiftRight.multiply(this.f1359r);
                }
                bigInteger = shiftRight.add(subtract);
            }
            while (bigInteger.compareTo(this.f1358q) >= 0) {
                bigInteger = bigInteger.subtract(this.f1358q);
            }
            if (!z || bigInteger.signum() == 0) {
                return bigInteger;
            }
            return this.f1358q.subtract(bigInteger);
        }

        /* access modifiers changed from: protected */
        public BigInteger modSubtract(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger subtract = bigInteger.subtract(bigInteger2);
            return subtract.signum() < 0 ? subtract.add(this.f1358q) : subtract;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C3640Fp)) {
                return false;
            }
            C3640Fp fp = (C3640Fp) obj;
            if (!this.f1358q.equals(fp.f1358q) || !this.f1360x.equals(fp.f1360x)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return this.f1358q.hashCode() ^ this.f1360x.hashCode();
        }
    }

    public abstract ECFieldElement add(ECFieldElement eCFieldElement);

    public abstract ECFieldElement addOne();

    public abstract ECFieldElement divide(ECFieldElement eCFieldElement);

    public abstract String getFieldName();

    public abstract int getFieldSize();

    public abstract ECFieldElement invert();

    public abstract ECFieldElement multiply(ECFieldElement eCFieldElement);

    public abstract ECFieldElement negate();

    public abstract ECFieldElement sqrt();

    public abstract ECFieldElement square();

    public abstract ECFieldElement subtract(ECFieldElement eCFieldElement);

    public abstract BigInteger toBigInteger();

    public int bitLength() {
        return toBigInteger().bitLength();
    }

    public boolean isOne() {
        return bitLength() == 1;
    }

    public boolean isZero() {
        return toBigInteger().signum() == 0;
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).subtract(eCFieldElement2.multiply(eCFieldElement3));
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).add(eCFieldElement2.multiply(eCFieldElement3));
    }

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().subtract(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().add(eCFieldElement.multiply(eCFieldElement2));
    }

    public boolean testBitZero() {
        return toBigInteger().testBit(0);
    }

    public String toString() {
        return toBigInteger().toString(16);
    }

    public byte[] getEncoded() {
        return BigIntegers.asUnsignedByteArray((getFieldSize() + 7) / 8, toBigInteger());
    }
}
