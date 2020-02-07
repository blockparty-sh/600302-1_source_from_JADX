package org.spongycastle.math.p025ec.custom.sec;

import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.math.p025ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat384;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP384R1Point */
public class SecP384R1Point extends AbstractFp {
    public SecP384R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP384R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        boolean z2 = true;
        boolean z3 = eCFieldElement == null;
        if (eCFieldElement2 != null) {
            z2 = false;
        }
        if (z3 == z2) {
            this.withCompression = z;
            return;
        }
        throw new IllegalArgumentException("Exactly one of the field elements is null");
    }

    SecP384R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP384R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    public ECPoint add(ECPoint eCPoint) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        ECPoint eCPoint2 = eCPoint;
        if (isInfinity()) {
            return eCPoint2;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        if (this == eCPoint2) {
            return twice();
        }
        ECCurve curve = getCurve();
        SecP384R1FieldElement secP384R1FieldElement = (SecP384R1FieldElement) this.f1361x;
        SecP384R1FieldElement secP384R1FieldElement2 = (SecP384R1FieldElement) this.f1362y;
        SecP384R1FieldElement secP384R1FieldElement3 = (SecP384R1FieldElement) eCPoint.getXCoord();
        SecP384R1FieldElement secP384R1FieldElement4 = (SecP384R1FieldElement) eCPoint.getYCoord();
        SecP384R1FieldElement secP384R1FieldElement5 = (SecP384R1FieldElement) this.f1363zs[0];
        SecP384R1FieldElement secP384R1FieldElement6 = (SecP384R1FieldElement) eCPoint2.getZCoord(0);
        int[] create = Nat.create(24);
        int[] create2 = Nat.create(24);
        int[] create3 = Nat.create(12);
        int[] create4 = Nat.create(12);
        boolean isOne = secP384R1FieldElement5.isOne();
        if (isOne) {
            iArr2 = secP384R1FieldElement3.f1409x;
            iArr = secP384R1FieldElement4.f1409x;
        } else {
            SecP384R1Field.square(secP384R1FieldElement5.f1409x, create3);
            SecP384R1Field.multiply(create3, secP384R1FieldElement3.f1409x, create2);
            SecP384R1Field.multiply(create3, secP384R1FieldElement5.f1409x, create3);
            SecP384R1Field.multiply(create3, secP384R1FieldElement4.f1409x, create3);
            iArr2 = create2;
            iArr = create3;
        }
        boolean isOne2 = secP384R1FieldElement6.isOne();
        if (isOne2) {
            iArr4 = secP384R1FieldElement.f1409x;
            iArr3 = secP384R1FieldElement2.f1409x;
        } else {
            SecP384R1Field.square(secP384R1FieldElement6.f1409x, create4);
            SecP384R1Field.multiply(create4, secP384R1FieldElement.f1409x, create);
            SecP384R1Field.multiply(create4, secP384R1FieldElement6.f1409x, create4);
            SecP384R1Field.multiply(create4, secP384R1FieldElement2.f1409x, create4);
            iArr4 = create;
            iArr3 = create4;
        }
        int[] create5 = Nat.create(12);
        SecP384R1Field.subtract(iArr4, iArr2, create5);
        int[] create6 = Nat.create(12);
        SecP384R1Field.subtract(iArr3, iArr, create6);
        if (!Nat.isZero(12, create5)) {
            SecP384R1Field.square(create5, create3);
            int[] create7 = Nat.create(12);
            SecP384R1Field.multiply(create3, create5, create7);
            SecP384R1Field.multiply(create3, iArr4, create3);
            SecP384R1Field.negate(create7, create7);
            Nat384.mul(iArr3, create7, create);
            SecP384R1Field.reduce32(Nat.addBothTo(12, create3, create3, create7), create7);
            SecP384R1FieldElement secP384R1FieldElement7 = new SecP384R1FieldElement(create4);
            SecP384R1Field.square(create6, secP384R1FieldElement7.f1409x);
            SecP384R1Field.subtract(secP384R1FieldElement7.f1409x, create7, secP384R1FieldElement7.f1409x);
            SecP384R1FieldElement secP384R1FieldElement8 = new SecP384R1FieldElement(create7);
            SecP384R1Field.subtract(create3, secP384R1FieldElement7.f1409x, secP384R1FieldElement8.f1409x);
            Nat384.mul(secP384R1FieldElement8.f1409x, create6, create2);
            SecP384R1Field.addExt(create, create2, create);
            SecP384R1Field.reduce(create, secP384R1FieldElement8.f1409x);
            SecP384R1FieldElement secP384R1FieldElement9 = new SecP384R1FieldElement(create5);
            if (!isOne) {
                SecP384R1Field.multiply(secP384R1FieldElement9.f1409x, secP384R1FieldElement5.f1409x, secP384R1FieldElement9.f1409x);
            }
            if (!isOne2) {
                SecP384R1Field.multiply(secP384R1FieldElement9.f1409x, secP384R1FieldElement6.f1409x, secP384R1FieldElement9.f1409x);
            }
            SecP384R1FieldElement secP384R1FieldElement10 = secP384R1FieldElement8;
            SecP384R1Point secP384R1Point = new SecP384R1Point(curve, secP384R1FieldElement7, secP384R1FieldElement10, new ECFieldElement[]{secP384R1FieldElement9}, this.withCompression);
            return secP384R1Point;
        } else if (Nat.isZero(12, create6)) {
            return twice();
        } else {
            return curve.getInfinity();
        }
    }

    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP384R1FieldElement secP384R1FieldElement = (SecP384R1FieldElement) this.f1362y;
        if (secP384R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP384R1FieldElement secP384R1FieldElement2 = (SecP384R1FieldElement) this.f1361x;
        SecP384R1FieldElement secP384R1FieldElement3 = (SecP384R1FieldElement) this.f1363zs[0];
        int[] create = Nat.create(12);
        int[] create2 = Nat.create(12);
        int[] create3 = Nat.create(12);
        SecP384R1Field.square(secP384R1FieldElement.f1409x, create3);
        int[] create4 = Nat.create(12);
        SecP384R1Field.square(create3, create4);
        boolean isOne = secP384R1FieldElement3.isOne();
        int[] iArr = secP384R1FieldElement3.f1409x;
        if (!isOne) {
            SecP384R1Field.square(secP384R1FieldElement3.f1409x, create2);
            iArr = create2;
        }
        SecP384R1Field.subtract(secP384R1FieldElement2.f1409x, iArr, create);
        SecP384R1Field.add(secP384R1FieldElement2.f1409x, iArr, create2);
        SecP384R1Field.multiply(create2, create, create2);
        SecP384R1Field.reduce32(Nat.addBothTo(12, create2, create2, create2), create2);
        SecP384R1Field.multiply(create3, secP384R1FieldElement2.f1409x, create3);
        SecP384R1Field.reduce32(Nat.shiftUpBits(12, create3, 2, 0), create3);
        SecP384R1Field.reduce32(Nat.shiftUpBits(12, create4, 3, 0, create), create);
        SecP384R1FieldElement secP384R1FieldElement4 = new SecP384R1FieldElement(create4);
        SecP384R1Field.square(create2, secP384R1FieldElement4.f1409x);
        SecP384R1Field.subtract(secP384R1FieldElement4.f1409x, create3, secP384R1FieldElement4.f1409x);
        SecP384R1Field.subtract(secP384R1FieldElement4.f1409x, create3, secP384R1FieldElement4.f1409x);
        SecP384R1FieldElement secP384R1FieldElement5 = new SecP384R1FieldElement(create3);
        SecP384R1Field.subtract(create3, secP384R1FieldElement4.f1409x, secP384R1FieldElement5.f1409x);
        SecP384R1Field.multiply(secP384R1FieldElement5.f1409x, create2, secP384R1FieldElement5.f1409x);
        SecP384R1Field.subtract(secP384R1FieldElement5.f1409x, create, secP384R1FieldElement5.f1409x);
        SecP384R1FieldElement secP384R1FieldElement6 = new SecP384R1FieldElement(create2);
        SecP384R1Field.twice(secP384R1FieldElement.f1409x, secP384R1FieldElement6.f1409x);
        if (!isOne) {
            SecP384R1Field.multiply(secP384R1FieldElement6.f1409x, secP384R1FieldElement3.f1409x, secP384R1FieldElement6.f1409x);
        }
        SecP384R1Point secP384R1Point = new SecP384R1Point(curve, secP384R1FieldElement4, secP384R1FieldElement5, new ECFieldElement[]{secP384R1FieldElement6}, this.withCompression);
        return secP384R1Point;
    }

    public ECPoint twicePlus(ECPoint eCPoint) {
        if (this == eCPoint) {
            return threeTimes();
        }
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return twice();
        }
        if (this.f1362y.isZero()) {
            return eCPoint;
        }
        return twice().add(eCPoint);
    }

    public ECPoint threeTimes() {
        return (isInfinity() || this.f1362y.isZero()) ? this : twice().add(this);
    }

    public ECPoint negate() {
        if (isInfinity()) {
            return this;
        }
        SecP384R1Point secP384R1Point = new SecP384R1Point(this.curve, this.f1361x, this.f1362y.negate(), this.f1363zs, this.withCompression);
        return secP384R1Point;
    }
}
