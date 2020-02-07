package org.spongycastle.math.p025ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat192;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP192K1FieldElement */
public class SecP192K1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f1375Q = SecP192K1Curve.f1372q;

    /* renamed from: x */
    protected int[] f1376x;

    public String getFieldName() {
        return "SecP192K1Field";
    }

    public SecP192K1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f1375Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192K1FieldElement");
        }
        this.f1376x = SecP192K1Field.fromBigInteger(bigInteger);
    }

    public SecP192K1FieldElement() {
        this.f1376x = Nat192.create();
    }

    protected SecP192K1FieldElement(int[] iArr) {
        this.f1376x = iArr;
    }

    public boolean isZero() {
        return Nat192.isZero(this.f1376x);
    }

    public boolean isOne() {
        return Nat192.isOne(this.f1376x);
    }

    public boolean testBitZero() {
        return Nat192.getBit(this.f1376x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat192.toBigInteger(this.f1376x);
    }

    public int getFieldSize() {
        return f1375Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192K1Field.add(this.f1376x, ((SecP192K1FieldElement) eCFieldElement).f1376x, create);
        return new SecP192K1FieldElement(create);
    }

    public ECFieldElement addOne() {
        int[] create = Nat192.create();
        SecP192K1Field.addOne(this.f1376x, create);
        return new SecP192K1FieldElement(create);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192K1Field.subtract(this.f1376x, ((SecP192K1FieldElement) eCFieldElement).f1376x, create);
        return new SecP192K1FieldElement(create);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192K1Field.multiply(this.f1376x, ((SecP192K1FieldElement) eCFieldElement).f1376x, create);
        return new SecP192K1FieldElement(create);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        Mod.invert(SecP192K1Field.f1373P, ((SecP192K1FieldElement) eCFieldElement).f1376x, create);
        SecP192K1Field.multiply(create, this.f1376x, create);
        return new SecP192K1FieldElement(create);
    }

    public ECFieldElement negate() {
        int[] create = Nat192.create();
        SecP192K1Field.negate(this.f1376x, create);
        return new SecP192K1FieldElement(create);
    }

    public ECFieldElement square() {
        int[] create = Nat192.create();
        SecP192K1Field.square(this.f1376x, create);
        return new SecP192K1FieldElement(create);
    }

    public ECFieldElement invert() {
        int[] create = Nat192.create();
        Mod.invert(SecP192K1Field.f1373P, this.f1376x, create);
        return new SecP192K1FieldElement(create);
    }

    public ECFieldElement sqrt() {
        int[] iArr = this.f1376x;
        if (Nat192.isZero(iArr) || Nat192.isOne(iArr)) {
            return this;
        }
        int[] create = Nat192.create();
        SecP192K1Field.square(iArr, create);
        SecP192K1Field.multiply(create, iArr, create);
        int[] create2 = Nat192.create();
        SecP192K1Field.square(create, create2);
        SecP192K1Field.multiply(create2, iArr, create2);
        int[] create3 = Nat192.create();
        SecP192K1Field.squareN(create2, 3, create3);
        SecP192K1Field.multiply(create3, create2, create3);
        SecP192K1Field.squareN(create3, 2, create3);
        SecP192K1Field.multiply(create3, create, create3);
        SecP192K1Field.squareN(create3, 8, create);
        SecP192K1Field.multiply(create, create3, create);
        SecP192K1Field.squareN(create, 3, create3);
        SecP192K1Field.multiply(create3, create2, create3);
        int[] create4 = Nat192.create();
        SecP192K1Field.squareN(create3, 16, create4);
        SecP192K1Field.multiply(create4, create, create4);
        SecP192K1Field.squareN(create4, 35, create);
        SecP192K1Field.multiply(create, create4, create);
        SecP192K1Field.squareN(create, 70, create4);
        SecP192K1Field.multiply(create4, create, create4);
        SecP192K1Field.squareN(create4, 19, create);
        SecP192K1Field.multiply(create, create3, create);
        SecP192K1Field.squareN(create, 20, create);
        SecP192K1Field.multiply(create, create3, create);
        SecP192K1Field.squareN(create, 4, create);
        SecP192K1Field.multiply(create, create2, create);
        SecP192K1Field.squareN(create, 6, create);
        SecP192K1Field.multiply(create, create2, create);
        SecP192K1Field.square(create, create);
        SecP192K1Field.square(create, create2);
        return Nat192.m422eq(iArr, create2) ? new SecP192K1FieldElement(create) : null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SecP192K1FieldElement)) {
            return false;
        }
        return Nat192.m422eq(this.f1376x, ((SecP192K1FieldElement) obj).f1376x);
    }

    public int hashCode() {
        return f1375Q.hashCode() ^ Arrays.hashCode(this.f1376x, 0, 6);
    }
}
