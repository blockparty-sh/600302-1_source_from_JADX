package org.spongycastle.math.p025ec.custom.sec;

import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.math.p025ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat192;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP192K1Point */
public class SecP192K1Point extends AbstractFp {
    public SecP192K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP192K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
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

    SecP192K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP192K1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    public ECPoint add(ECPoint eCPoint) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        if (this == eCPoint) {
            return twice();
        }
        ECCurve curve = getCurve();
        SecP192K1FieldElement secP192K1FieldElement = (SecP192K1FieldElement) this.f1361x;
        SecP192K1FieldElement secP192K1FieldElement2 = (SecP192K1FieldElement) this.f1362y;
        SecP192K1FieldElement secP192K1FieldElement3 = (SecP192K1FieldElement) eCPoint.getXCoord();
        SecP192K1FieldElement secP192K1FieldElement4 = (SecP192K1FieldElement) eCPoint.getYCoord();
        SecP192K1FieldElement secP192K1FieldElement5 = (SecP192K1FieldElement) this.f1363zs[0];
        SecP192K1FieldElement secP192K1FieldElement6 = (SecP192K1FieldElement) eCPoint.getZCoord(0);
        int[] createExt = Nat192.createExt();
        int[] create = Nat192.create();
        int[] create2 = Nat192.create();
        int[] create3 = Nat192.create();
        boolean isOne = secP192K1FieldElement5.isOne();
        if (isOne) {
            iArr2 = secP192K1FieldElement3.f1376x;
            iArr = secP192K1FieldElement4.f1376x;
        } else {
            SecP192K1Field.square(secP192K1FieldElement5.f1376x, create2);
            SecP192K1Field.multiply(create2, secP192K1FieldElement3.f1376x, create);
            SecP192K1Field.multiply(create2, secP192K1FieldElement5.f1376x, create2);
            SecP192K1Field.multiply(create2, secP192K1FieldElement4.f1376x, create2);
            iArr2 = create;
            iArr = create2;
        }
        boolean isOne2 = secP192K1FieldElement6.isOne();
        if (isOne2) {
            iArr4 = secP192K1FieldElement.f1376x;
            iArr3 = secP192K1FieldElement2.f1376x;
        } else {
            SecP192K1Field.square(secP192K1FieldElement6.f1376x, create3);
            SecP192K1Field.multiply(create3, secP192K1FieldElement.f1376x, createExt);
            SecP192K1Field.multiply(create3, secP192K1FieldElement6.f1376x, create3);
            SecP192K1Field.multiply(create3, secP192K1FieldElement2.f1376x, create3);
            iArr4 = createExt;
            iArr3 = create3;
        }
        int[] create4 = Nat192.create();
        SecP192K1Field.subtract(iArr4, iArr2, create4);
        SecP192K1Field.subtract(iArr3, iArr, create);
        if (!Nat192.isZero(create4)) {
            SecP192K1Field.square(create4, create2);
            int[] create5 = Nat192.create();
            SecP192K1Field.multiply(create2, create4, create5);
            SecP192K1Field.multiply(create2, iArr4, create2);
            SecP192K1Field.negate(create5, create5);
            Nat192.mul(iArr3, create5, createExt);
            SecP192K1Field.reduce32(Nat192.addBothTo(create2, create2, create5), create5);
            SecP192K1FieldElement secP192K1FieldElement7 = new SecP192K1FieldElement(create3);
            SecP192K1Field.square(create, secP192K1FieldElement7.f1376x);
            SecP192K1Field.subtract(secP192K1FieldElement7.f1376x, create5, secP192K1FieldElement7.f1376x);
            SecP192K1FieldElement secP192K1FieldElement8 = new SecP192K1FieldElement(create5);
            SecP192K1Field.subtract(create2, secP192K1FieldElement7.f1376x, secP192K1FieldElement8.f1376x);
            SecP192K1Field.multiplyAddToExt(secP192K1FieldElement8.f1376x, create, createExt);
            SecP192K1Field.reduce(createExt, secP192K1FieldElement8.f1376x);
            SecP192K1FieldElement secP192K1FieldElement9 = new SecP192K1FieldElement(create4);
            if (!isOne) {
                SecP192K1Field.multiply(secP192K1FieldElement9.f1376x, secP192K1FieldElement5.f1376x, secP192K1FieldElement9.f1376x);
            }
            if (!isOne2) {
                SecP192K1Field.multiply(secP192K1FieldElement9.f1376x, secP192K1FieldElement6.f1376x, secP192K1FieldElement9.f1376x);
            }
            SecP192K1Point secP192K1Point = new SecP192K1Point(curve, secP192K1FieldElement7, secP192K1FieldElement8, new ECFieldElement[]{secP192K1FieldElement9}, this.withCompression);
            return secP192K1Point;
        } else if (Nat192.isZero(create)) {
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
        SecP192K1FieldElement secP192K1FieldElement = (SecP192K1FieldElement) this.f1362y;
        if (secP192K1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP192K1FieldElement secP192K1FieldElement2 = (SecP192K1FieldElement) this.f1361x;
        SecP192K1FieldElement secP192K1FieldElement3 = (SecP192K1FieldElement) this.f1363zs[0];
        int[] create = Nat192.create();
        SecP192K1Field.square(secP192K1FieldElement.f1376x, create);
        int[] create2 = Nat192.create();
        SecP192K1Field.square(create, create2);
        int[] create3 = Nat192.create();
        SecP192K1Field.square(secP192K1FieldElement2.f1376x, create3);
        SecP192K1Field.reduce32(Nat192.addBothTo(create3, create3, create3), create3);
        SecP192K1Field.multiply(create, secP192K1FieldElement2.f1376x, create);
        SecP192K1Field.reduce32(Nat.shiftUpBits(6, create, 2, 0), create);
        int[] create4 = Nat192.create();
        SecP192K1Field.reduce32(Nat.shiftUpBits(6, create2, 3, 0, create4), create4);
        SecP192K1FieldElement secP192K1FieldElement4 = new SecP192K1FieldElement(create2);
        SecP192K1Field.square(create3, secP192K1FieldElement4.f1376x);
        SecP192K1Field.subtract(secP192K1FieldElement4.f1376x, create, secP192K1FieldElement4.f1376x);
        SecP192K1Field.subtract(secP192K1FieldElement4.f1376x, create, secP192K1FieldElement4.f1376x);
        SecP192K1FieldElement secP192K1FieldElement5 = new SecP192K1FieldElement(create);
        SecP192K1Field.subtract(create, secP192K1FieldElement4.f1376x, secP192K1FieldElement5.f1376x);
        SecP192K1Field.multiply(secP192K1FieldElement5.f1376x, create3, secP192K1FieldElement5.f1376x);
        SecP192K1Field.subtract(secP192K1FieldElement5.f1376x, create4, secP192K1FieldElement5.f1376x);
        SecP192K1FieldElement secP192K1FieldElement6 = new SecP192K1FieldElement(create3);
        SecP192K1Field.twice(secP192K1FieldElement.f1376x, secP192K1FieldElement6.f1376x);
        if (!secP192K1FieldElement3.isOne()) {
            SecP192K1Field.multiply(secP192K1FieldElement6.f1376x, secP192K1FieldElement3.f1376x, secP192K1FieldElement6.f1376x);
        }
        SecP192K1Point secP192K1Point = new SecP192K1Point(curve, secP192K1FieldElement4, secP192K1FieldElement5, new ECFieldElement[]{secP192K1FieldElement6}, this.withCompression);
        return secP192K1Point;
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
        SecP192K1Point secP192K1Point = new SecP192K1Point(this.curve, this.f1361x, this.f1362y.negate(), this.f1363zs, this.withCompression);
        return secP192K1Point;
    }
}