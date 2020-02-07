package org.spongycastle.math.p025ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECCurve.AbstractFp;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.util.encoders.Hex;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP224R1Curve */
public class SecP224R1Curve extends AbstractFp {
    private static final int SecP224R1_DEFAULT_COORDS = 2;

    /* renamed from: q */
    public static final BigInteger f1388q = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000001"));
    protected SecP224R1Point infinity;

    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }

    public SecP224R1Curve() {
        super(f1388q);
        this.infinity = new SecP224R1Point(this, null, null);
        this.f1345a = fromBigInteger(new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFE")));
        this.f1346b = fromBigInteger(new BigInteger(1, Hex.decode("B4050A850C04B3ABF54132565044B0B7D7BFD8BA270B39432355FFB4")));
        this.order = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFF16A2E0B8F03E13DD29455C5C2A3D"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new SecP224R1Curve();
    }

    public BigInteger getQ() {
        return f1388q;
    }

    public int getFieldSize() {
        return f1388q.bitLength();
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP224R1FieldElement(bigInteger);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        return new SecP224R1Point(this, eCFieldElement, eCFieldElement2, z);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        SecP224R1Point secP224R1Point = new SecP224R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
        return secP224R1Point;
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }
}
