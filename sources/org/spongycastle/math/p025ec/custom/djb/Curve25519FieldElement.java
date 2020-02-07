package org.spongycastle.math.p025ec.custom.djb;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat256;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.math.ec.custom.djb.Curve25519FieldElement */
public class Curve25519FieldElement extends ECFieldElement {
    private static final int[] PRECOMP_POW2 = {1242472624, -991028441, -1389370248, 792926214, 1039914919, 726466713, 1338105611, 730014848};

    /* renamed from: Q */
    public static final BigInteger f1370Q = Curve25519.f1366q;

    /* renamed from: x */
    protected int[] f1371x;

    public String getFieldName() {
        return "Curve25519Field";
    }

    public Curve25519FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f1370Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for Curve25519FieldElement");
        }
        this.f1371x = Curve25519Field.fromBigInteger(bigInteger);
    }

    public Curve25519FieldElement() {
        this.f1371x = Nat256.create();
    }

    protected Curve25519FieldElement(int[] iArr) {
        this.f1371x = iArr;
    }

    public boolean isZero() {
        return Nat256.isZero(this.f1371x);
    }

    public boolean isOne() {
        return Nat256.isOne(this.f1371x);
    }

    public boolean testBitZero() {
        return Nat256.getBit(this.f1371x, 0) == 1;
    }

    public BigInteger toBigInteger() {
        return Nat256.toBigInteger(this.f1371x);
    }

    public int getFieldSize() {
        return f1370Q.bitLength();
    }

    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        Curve25519Field.add(this.f1371x, ((Curve25519FieldElement) eCFieldElement).f1371x, create);
        return new Curve25519FieldElement(create);
    }

    public ECFieldElement addOne() {
        int[] create = Nat256.create();
        Curve25519Field.addOne(this.f1371x, create);
        return new Curve25519FieldElement(create);
    }

    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        Curve25519Field.subtract(this.f1371x, ((Curve25519FieldElement) eCFieldElement).f1371x, create);
        return new Curve25519FieldElement(create);
    }

    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        Curve25519Field.multiply(this.f1371x, ((Curve25519FieldElement) eCFieldElement).f1371x, create);
        return new Curve25519FieldElement(create);
    }

    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        Mod.invert(Curve25519Field.f1368P, ((Curve25519FieldElement) eCFieldElement).f1371x, create);
        Curve25519Field.multiply(create, this.f1371x, create);
        return new Curve25519FieldElement(create);
    }

    public ECFieldElement negate() {
        int[] create = Nat256.create();
        Curve25519Field.negate(this.f1371x, create);
        return new Curve25519FieldElement(create);
    }

    public ECFieldElement square() {
        int[] create = Nat256.create();
        Curve25519Field.square(this.f1371x, create);
        return new Curve25519FieldElement(create);
    }

    public ECFieldElement invert() {
        int[] create = Nat256.create();
        Mod.invert(Curve25519Field.f1368P, this.f1371x, create);
        return new Curve25519FieldElement(create);
    }

    public ECFieldElement sqrt() {
        int[] iArr = this.f1371x;
        if (Nat256.isZero(iArr) || Nat256.isOne(iArr)) {
            return this;
        }
        int[] create = Nat256.create();
        Curve25519Field.square(iArr, create);
        Curve25519Field.multiply(create, iArr, create);
        Curve25519Field.square(create, create);
        Curve25519Field.multiply(create, iArr, create);
        int[] create2 = Nat256.create();
        Curve25519Field.square(create, create2);
        Curve25519Field.multiply(create2, iArr, create2);
        int[] create3 = Nat256.create();
        Curve25519Field.squareN(create2, 3, create3);
        Curve25519Field.multiply(create3, create, create3);
        Curve25519Field.squareN(create3, 4, create);
        Curve25519Field.multiply(create, create2, create);
        Curve25519Field.squareN(create, 4, create3);
        Curve25519Field.multiply(create3, create2, create3);
        Curve25519Field.squareN(create3, 15, create2);
        Curve25519Field.multiply(create2, create3, create2);
        Curve25519Field.squareN(create2, 30, create3);
        Curve25519Field.multiply(create3, create2, create3);
        Curve25519Field.squareN(create3, 60, create2);
        Curve25519Field.multiply(create2, create3, create2);
        Curve25519Field.squareN(create2, 11, create3);
        Curve25519Field.multiply(create3, create, create3);
        Curve25519Field.squareN(create3, 120, create);
        Curve25519Field.multiply(create, create2, create);
        Curve25519Field.square(create, create);
        Curve25519Field.square(create, create2);
        if (Nat256.m424eq(iArr, create2)) {
            return new Curve25519FieldElement(create);
        }
        Curve25519Field.multiply(create, PRECOMP_POW2, create);
        Curve25519Field.square(create, create2);
        if (Nat256.m424eq(iArr, create2)) {
            return new Curve25519FieldElement(create);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Curve25519FieldElement)) {
            return false;
        }
        return Nat256.m424eq(this.f1371x, ((Curve25519FieldElement) obj).f1371x);
    }

    public int hashCode() {
        return f1370Q.hashCode() ^ Arrays.hashCode(this.f1371x, 0, 8);
    }
}
