package org.spongycastle.math.p025ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECConstants;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECCurve.AbstractFp;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.util.encoders.Hex;

/* renamed from: org.spongycastle.math.ec.custom.sec.SecP224K1Curve */
public class SecP224K1Curve extends AbstractFp {
    private static final int SECP224K1_DEFAULT_COORDS = 2;

    /* renamed from: q */
    public static final BigInteger f1383q = new BigInteger(1, Hex.decode("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFE56D"));
    protected SecP224K1Point infinity;

    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }

    public SecP224K1Curve() {
        super(f1383q);
        this.infinity = new SecP224K1Point(this, null, null);
        this.f1345a = fromBigInteger(ECConstants.ZERO);
        this.f1346b = fromBigInteger(BigInteger.valueOf(5));
        this.order = new BigInteger(1, Hex.decode("010000000000000000000000000001DCE8D2EC6184CAF0A971769FB1F7"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new SecP224K1Curve();
    }

    public BigInteger getQ() {
        return f1383q;
    }

    public int getFieldSize() {
        return f1383q.bitLength();
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP224K1FieldElement(bigInteger);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        return new SecP224K1Point(this, eCFieldElement, eCFieldElement2, z);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        SecP224K1Point secP224K1Point = new SecP224K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
        return secP224K1Point;
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }
}
