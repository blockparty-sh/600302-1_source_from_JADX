package org.spongycastle.math.p025ec.custom.djb;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECCurve.AbstractFp;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.math.raw.Nat256;
import org.spongycastle.util.encoders.Hex;

/* renamed from: org.spongycastle.math.ec.custom.djb.Curve25519 */
public class Curve25519 extends AbstractFp {
    private static final int Curve25519_DEFAULT_COORDS = 4;

    /* renamed from: q */
    public static final BigInteger f1366q = Nat256.toBigInteger(Curve25519Field.f1368P);
    protected Curve25519Point infinity;

    public boolean supportsCoordinateSystem(int i) {
        return i == 4;
    }

    public Curve25519() {
        super(f1366q);
        this.infinity = new Curve25519Point(this, null, null);
        this.f1345a = fromBigInteger(new BigInteger(1, Hex.decode("2AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA984914A144")));
        this.f1346b = fromBigInteger(new BigInteger(1, Hex.decode("7B425ED097B425ED097B425ED097B425ED097B425ED097B4260B5E9C7710C864")));
        this.order = new BigInteger(1, Hex.decode("1000000000000000000000000000000014DEF9DEA2F79CD65812631A5CF5D3ED"));
        this.cofactor = BigInteger.valueOf(8);
        this.coord = 4;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new Curve25519();
    }

    public BigInteger getQ() {
        return f1366q;
    }

    public int getFieldSize() {
        return f1366q.bitLength();
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new Curve25519FieldElement(bigInteger);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        return new Curve25519Point(this, eCFieldElement, eCFieldElement2, z);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        Curve25519Point curve25519Point = new Curve25519Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
        return curve25519Point;
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }
}
