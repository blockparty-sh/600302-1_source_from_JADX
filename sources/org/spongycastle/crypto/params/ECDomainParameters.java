package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.ECConstants;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.util.Arrays;

public class ECDomainParameters implements ECConstants {

    /* renamed from: G */
    private ECPoint f1291G;
    private ECCurve curve;

    /* renamed from: h */
    private BigInteger f1292h;

    /* renamed from: n */
    private BigInteger f1293n;
    private byte[] seed;

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this(eCCurve, eCPoint, bigInteger, ONE, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this(eCCurve, eCPoint, bigInteger, bigInteger2, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.curve = eCCurve;
        this.f1291G = eCPoint.normalize();
        this.f1293n = bigInteger;
        this.f1292h = bigInteger2;
        this.seed = bArr;
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.f1291G;
    }

    public BigInteger getN() {
        return this.f1293n;
    }

    public BigInteger getH() {
        return this.f1292h;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }
}
