package org.spongycastle.math.p025ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat256;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP256K1FieldElement */
public class SecP256K1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f1397Q = SecP256K1Curve.f1394q;

    /* renamed from: x */
    protected int[] f1398x;

    public String getFieldName() {
        return "SecP256K1Field";
    }

    public SecP256K1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f1397Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP256K1FieldElement");
        }
        this.f1398x = SecP256K1Field.fromBigInteger(bigInteger);
    }

    public SecP256K1FieldElement() {
        this.f1398x = Nat256.create();
    }

    protected SecP256K1FieldElement(int[] iArr) {
        this.f1398x = iArr;
    }

    public boolean isZero() {
        return Nat256.isZero(this.f1398x);
    }

    public boolean isOne() {
        return Nat256.isOne(this.f1398x);
    }

    public boolean testBitZero() {
        return Nat256.getBit(this.f1398x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat256.toBigInteger(this.f1398x);
    }

    public int getFieldSize() {
        return f1397Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        SecP256K1Field.add(this.f1398x, ((SecP256K1FieldElement) eCFieldElement).f1398x, create);
        return new SecP256K1FieldElement(create);
    }

    public ECFieldElement addOne() {
        int[] create = Nat256.create();
        SecP256K1Field.addOne(this.f1398x, create);
        return new SecP256K1FieldElement(create);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        SecP256K1Field.subtract(this.f1398x, ((SecP256K1FieldElement) eCFieldElement).f1398x, create);
        return new SecP256K1FieldElement(create);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        SecP256K1Field.multiply(this.f1398x, ((SecP256K1FieldElement) eCFieldElement).f1398x, create);
        return new SecP256K1FieldElement(create);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        Mod.invert(SecP256K1Field.f1395P, ((SecP256K1FieldElement) eCFieldElement).f1398x, create);
        SecP256K1Field.multiply(create, this.f1398x, create);
        return new SecP256K1FieldElement(create);
    }

    public ECFieldElement negate() {
        int[] create = Nat256.create();
        SecP256K1Field.negate(this.f1398x, create);
        return new SecP256K1FieldElement(create);
    }

    public ECFieldElement square() {
        int[] create = Nat256.create();
        SecP256K1Field.square(this.f1398x, create);
        return new SecP256K1FieldElement(create);
    }

    public ECFieldElement invert() {
        int[] create = Nat256.create();
        Mod.invert(SecP256K1Field.f1395P, this.f1398x, create);
        return new SecP256K1FieldElement(create);
    }

    public ECFieldElement sqrt() {
        int[] iArr = this.f1398x;
        if (Nat256.isZero(iArr) || Nat256.isOne(iArr)) {
            return this;
        }
        int[] create = Nat256.create();
        SecP256K1Field.square(iArr, create);
        SecP256K1Field.multiply(create, iArr, create);
        int[] create2 = Nat256.create();
        SecP256K1Field.square(create, create2);
        SecP256K1Field.multiply(create2, iArr, create2);
        int[] create3 = Nat256.create();
        SecP256K1Field.squareN(create2, 3, create3);
        SecP256K1Field.multiply(create3, create2, create3);
        SecP256K1Field.squareN(create3, 3, create3);
        SecP256K1Field.multiply(create3, create2, create3);
        SecP256K1Field.squareN(create3, 2, create3);
        SecP256K1Field.multiply(create3, create, create3);
        int[] create4 = Nat256.create();
        SecP256K1Field.squareN(create3, 11, create4);
        SecP256K1Field.multiply(create4, create3, create4);
        SecP256K1Field.squareN(create4, 22, create3);
        SecP256K1Field.multiply(create3, create4, create3);
        int[] create5 = Nat256.create();
        SecP256K1Field.squareN(create3, 44, create5);
        SecP256K1Field.multiply(create5, create3, create5);
        int[] create6 = Nat256.create();
        SecP256K1Field.squareN(create5, 88, create6);
        SecP256K1Field.multiply(create6, create5, create6);
        SecP256K1Field.squareN(create6, 44, create5);
        SecP256K1Field.multiply(create5, create3, create5);
        SecP256K1Field.squareN(create5, 3, create3);
        SecP256K1Field.multiply(create3, create2, create3);
        SecP256K1Field.squareN(create3, 23, create3);
        SecP256K1Field.multiply(create3, create4, create3);
        SecP256K1Field.squareN(create3, 6, create3);
        SecP256K1Field.multiply(create3, create, create3);
        SecP256K1Field.squareN(create3, 2, create3);
        SecP256K1Field.square(create3, create);
        return Nat256.m424eq(iArr, create) ? new SecP256K1FieldElement(create3) : null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SecP256K1FieldElement)) {
            return false;
        }
        return Nat256.m424eq(this.f1398x, ((SecP256K1FieldElement) obj).f1398x);
    }

    public int hashCode() {
        return f1397Q.hashCode() ^ Arrays.hashCode(this.f1398x, 0, 8);
    }
}
