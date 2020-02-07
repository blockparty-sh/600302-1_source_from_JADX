package org.spongycastle.math.p025ec.custom.sec;

import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.math.p025ec.ECPoint.AbstractFp;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat256;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP256R1Point */
public class SecP256R1Point extends AbstractFp {
    public SecP256R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP256R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
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

    SecP256R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z;
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP256R1Point(null, getAffineXCoord(), getAffineYCoord());
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
        SecP256R1FieldElement secP256R1FieldElement = (SecP256R1FieldElement) this.f1361x;
        SecP256R1FieldElement secP256R1FieldElement2 = (SecP256R1FieldElement) this.f1362y;
        SecP256R1FieldElement secP256R1FieldElement3 = (SecP256R1FieldElement) eCPoint.getXCoord();
        SecP256R1FieldElement secP256R1FieldElement4 = (SecP256R1FieldElement) eCPoint.getYCoord();
        SecP256R1FieldElement secP256R1FieldElement5 = (SecP256R1FieldElement) this.f1363zs[0];
        SecP256R1FieldElement secP256R1FieldElement6 = (SecP256R1FieldElement) eCPoint.getZCoord(0);
        int[] createExt = Nat256.createExt();
        int[] create = Nat256.create();
        int[] create2 = Nat256.create();
        int[] create3 = Nat256.create();
        boolean isOne = secP256R1FieldElement5.isOne();
        if (isOne) {
            iArr2 = secP256R1FieldElement3.f1404x;
            iArr = secP256R1FieldElement4.f1404x;
        } else {
            SecP256R1Field.square(secP256R1FieldElement5.f1404x, create2);
            SecP256R1Field.multiply(create2, secP256R1FieldElement3.f1404x, create);
            SecP256R1Field.multiply(create2, secP256R1FieldElement5.f1404x, create2);
            SecP256R1Field.multiply(create2, secP256R1FieldElement4.f1404x, create2);
            iArr2 = create;
            iArr = create2;
        }
        boolean isOne2 = secP256R1FieldElement6.isOne();
        if (isOne2) {
            iArr4 = secP256R1FieldElement.f1404x;
            iArr3 = secP256R1FieldElement2.f1404x;
        } else {
            SecP256R1Field.square(secP256R1FieldElement6.f1404x, create3);
            SecP256R1Field.multiply(create3, secP256R1FieldElement.f1404x, createExt);
            SecP256R1Field.multiply(create3, secP256R1FieldElement6.f1404x, create3);
            SecP256R1Field.multiply(create3, secP256R1FieldElement2.f1404x, create3);
            iArr4 = createExt;
            iArr3 = create3;
        }
        int[] create4 = Nat256.create();
        SecP256R1Field.subtract(iArr4, iArr2, create4);
        SecP256R1Field.subtract(iArr3, iArr, create);
        if (!Nat256.isZero(create4)) {
            SecP256R1Field.square(create4, create2);
            int[] create5 = Nat256.create();
            SecP256R1Field.multiply(create2, create4, create5);
            SecP256R1Field.multiply(create2, iArr4, create2);
            SecP256R1Field.negate(create5, create5);
            Nat256.mul(iArr3, create5, createExt);
            SecP256R1Field.reduce32(Nat256.addBothTo(create2, create2, create5), create5);
            SecP256R1FieldElement secP256R1FieldElement7 = new SecP256R1FieldElement(create3);
            SecP256R1Field.square(create, secP256R1FieldElement7.f1404x);
            SecP256R1Field.subtract(secP256R1FieldElement7.f1404x, create5, secP256R1FieldElement7.f1404x);
            SecP256R1FieldElement secP256R1FieldElement8 = new SecP256R1FieldElement(create5);
            SecP256R1Field.subtract(create2, secP256R1FieldElement7.f1404x, secP256R1FieldElement8.f1404x);
            SecP256R1Field.multiplyAddToExt(secP256R1FieldElement8.f1404x, create, createExt);
            SecP256R1Field.reduce(createExt, secP256R1FieldElement8.f1404x);
            SecP256R1FieldElement secP256R1FieldElement9 = new SecP256R1FieldElement(create4);
            if (!isOne) {
                SecP256R1Field.multiply(secP256R1FieldElement9.f1404x, secP256R1FieldElement5.f1404x, secP256R1FieldElement9.f1404x);
            }
            if (!isOne2) {
                SecP256R1Field.multiply(secP256R1FieldElement9.f1404x, secP256R1FieldElement6.f1404x, secP256R1FieldElement9.f1404x);
            }
            SecP256R1Point secP256R1Point = new SecP256R1Point(curve, secP256R1FieldElement7, secP256R1FieldElement8, new ECFieldElement[]{secP256R1FieldElement9}, this.withCompression);
            return secP256R1Point;
        } else if (Nat256.isZero(create)) {
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
        SecP256R1FieldElement secP256R1FieldElement = (SecP256R1FieldElement) this.f1362y;
        if (secP256R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP256R1FieldElement secP256R1FieldElement2 = (SecP256R1FieldElement) this.f1361x;
        SecP256R1FieldElement secP256R1FieldElement3 = (SecP256R1FieldElement) this.f1363zs[0];
        int[] create = Nat256.create();
        int[] create2 = Nat256.create();
        int[] create3 = Nat256.create();
        SecP256R1Field.square(secP256R1FieldElement.f1404x, create3);
        int[] create4 = Nat256.create();
        SecP256R1Field.square(create3, create4);
        boolean isOne = secP256R1FieldElement3.isOne();
        int[] iArr = secP256R1FieldElement3.f1404x;
        if (!isOne) {
            SecP256R1Field.square(secP256R1FieldElement3.f1404x, create2);
            iArr = create2;
        }
        SecP256R1Field.subtract(secP256R1FieldElement2.f1404x, iArr, create);
        SecP256R1Field.add(secP256R1FieldElement2.f1404x, iArr, create2);
        SecP256R1Field.multiply(create2, create, create2);
        SecP256R1Field.reduce32(Nat256.addBothTo(create2, create2, create2), create2);
        SecP256R1Field.multiply(create3, secP256R1FieldElement2.f1404x, create3);
        SecP256R1Field.reduce32(Nat.shiftUpBits(8, create3, 2, 0), create3);
        SecP256R1Field.reduce32(Nat.shiftUpBits(8, create4, 3, 0, create), create);
        SecP256R1FieldElement secP256R1FieldElement4 = new SecP256R1FieldElement(create4);
        SecP256R1Field.square(create2, secP256R1FieldElement4.f1404x);
        SecP256R1Field.subtract(secP256R1FieldElement4.f1404x, create3, secP256R1FieldElement4.f1404x);
        SecP256R1Field.subtract(secP256R1FieldElement4.f1404x, create3, secP256R1FieldElement4.f1404x);
        SecP256R1FieldElement secP256R1FieldElement5 = new SecP256R1FieldElement(create3);
        SecP256R1Field.subtract(create3, secP256R1FieldElement4.f1404x, secP256R1FieldElement5.f1404x);
        SecP256R1Field.multiply(secP256R1FieldElement5.f1404x, create2, secP256R1FieldElement5.f1404x);
        SecP256R1Field.subtract(secP256R1FieldElement5.f1404x, create, secP256R1FieldElement5.f1404x);
        SecP256R1FieldElement secP256R1FieldElement6 = new SecP256R1FieldElement(create2);
        SecP256R1Field.twice(secP256R1FieldElement.f1404x, secP256R1FieldElement6.f1404x);
        if (!isOne) {
            SecP256R1Field.multiply(secP256R1FieldElement6.f1404x, secP256R1FieldElement3.f1404x, secP256R1FieldElement6.f1404x);
        }
        SecP256R1FieldElement secP256R1FieldElement7 = secP256R1FieldElement4;
        SecP256R1FieldElement secP256R1FieldElement8 = secP256R1FieldElement5;
        SecP256R1Point secP256R1Point = new SecP256R1Point(curve, secP256R1FieldElement7, secP256R1FieldElement8, new ECFieldElement[]{secP256R1FieldElement6}, this.withCompression);
        return secP256R1Point;
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
        SecP256R1Point secP256R1Point = new SecP256R1Point(this.curve, this.f1361x, this.f1362y.negate(), this.f1363zs, this.withCompression);
        return secP256R1Point;
    }
}
