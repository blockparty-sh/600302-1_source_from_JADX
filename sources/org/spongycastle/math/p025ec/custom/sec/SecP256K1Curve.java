package org.spongycastle.math.p025ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECConstants;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECCurve.AbstractFp;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.util.encoders.Hex;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP256K1Curve */
public class SecP256K1Curve extends AbstractFp {
    private static final int SECP256K1_DEFAULT_COORDS = 2;

    /* renamed from: q */
    public static final BigInteger f1394q = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F"));
    protected SecP256K1Point infinity;

    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }

    public SecP256K1Curve() {
        super(f1394q);
        this.infinity = new SecP256K1Point(this, null, null);
        this.f1345a = fromBigInteger(ECConstants.ZERO);
        this.f1346b = fromBigInteger(BigInteger.valueOf(7));
        this.order = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new SecP256K1Curve();
    }

    public BigInteger getQ() {
        return f1394q;
    }

    public int getFieldSize() {
        return f1394q.bitLength();
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP256K1FieldElement(bigInteger);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        return new SecP256K1Point(this, eCFieldElement, eCFieldElement2, z);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        SecP256K1Point secP256K1Point = new SecP256K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
        return secP256K1Point;
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }
}
