package org.spongycastle.math.p025ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECConstants;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECCurve.AbstractFp;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.util.encoders.Hex;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP192K1Curve */
public class SecP192K1Curve extends AbstractFp {
    private static final int SecP192K1_DEFAULT_COORDS = 2;

    /* renamed from: q */
    public static final BigInteger f1372q = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFEE37"));
    protected SecP192K1Point infinity;

    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }

    public SecP192K1Curve() {
        super(f1372q);
        this.infinity = new SecP192K1Point(this, null, null);
        this.f1345a = fromBigInteger(ECConstants.ZERO);
        this.f1346b = fromBigInteger(BigInteger.valueOf(3));
        this.order = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFE26F2FC170F69466A74DEFD8D"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new SecP192K1Curve();
    }

    public BigInteger getQ() {
        return f1372q;
    }

    public int getFieldSize() {
        return f1372q.bitLength();
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP192K1FieldElement(bigInteger);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        return new SecP192K1Point(this, eCFieldElement, eCFieldElement2, z);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        SecP192K1Point secP192K1Point = new SecP192K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
        return secP192K1Point;
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }
}
