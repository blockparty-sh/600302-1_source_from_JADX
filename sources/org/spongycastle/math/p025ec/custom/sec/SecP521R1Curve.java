package org.spongycastle.math.p025ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECCurve.AbstractFp;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.util.encoders.Hex;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP521R1Curve */
public class SecP521R1Curve extends AbstractFp {
    private static final int SecP521R1_DEFAULT_COORDS = 2;

    /* renamed from: q */
    public static final BigInteger f1410q = new BigInteger(1, Hex.decode("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"));
    protected SecP521R1Point infinity;

    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }

    public SecP521R1Curve() {
        super(f1410q);
        this.infinity = new SecP521R1Point(this, null, null);
        this.f1345a = fromBigInteger(new BigInteger(1, Hex.decode("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC")));
        this.f1346b = fromBigInteger(new BigInteger(1, Hex.decode("0051953EB9618E1C9A1F929A21A0B68540EEA2DA725B99B315F3B8B489918EF109E156193951EC7E937B1652C0BD3BB1BF073573DF883D2C34F1EF451FD46B503F00")));
        this.order = new BigInteger(1, Hex.decode("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA51868783BF2F966B7FCC0148F709A5D03BB5C9B8899C47AEBB6FB71E91386409"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new SecP521R1Curve();
    }

    public BigInteger getQ() {
        return f1410q;
    }

    public int getFieldSize() {
        return f1410q.bitLength();
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP521R1FieldElement(bigInteger);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        return new SecP521R1Point(this, eCFieldElement, eCFieldElement2, z);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        SecP521R1Point secP521R1Point = new SecP521R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
        return secP521R1Point;
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }
}
