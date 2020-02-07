package org.spongycastle.math.p025ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECCurve.AbstractFp;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.util.encoders.Hex;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP256R1Curve */
public class SecP256R1Curve extends AbstractFp {
    private static final int SecP256R1_DEFAULT_COORDS = 2;

    /* renamed from: q */
    public static final BigInteger f1399q = new BigInteger(1, Hex.decode("FFFFFFFF00000001000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFF"));
    protected SecP256R1Point infinity;

    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }

    public SecP256R1Curve() {
        super(f1399q);
        this.infinity = new SecP256R1Point(this, null, null);
        this.f1345a = fromBigInteger(new BigInteger(1, Hex.decode("FFFFFFFF00000001000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFC")));
        this.f1346b = fromBigInteger(new BigInteger(1, Hex.decode("5AC635D8AA3A93E7B3EBBD55769886BC651D06B0CC53B0F63BCE3C3E27D2604B")));
        this.order = new BigInteger(1, Hex.decode("FFFFFFFF00000000FFFFFFFFFFFFFFFFBCE6FAADA7179E84F3B9CAC2FC632551"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new SecP256R1Curve();
    }

    public BigInteger getQ() {
        return f1399q;
    }

    public int getFieldSize() {
        return f1399q.bitLength();
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP256R1FieldElement(bigInteger);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        return new SecP256R1Point(this, eCFieldElement, eCFieldElement2, z);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        SecP256R1Point secP256R1Point = new SecP256R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
        return secP256R1Point;
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }
}
