package org.spongycastle.math.p025ec;

import java.math.BigInteger;
import org.spongycastle.math.p025ec.endo.GLVEndomorphism;

/* renamed from: org.spongycastle.math.ec.GLVMultiplier */
public class GLVMultiplier extends AbstractECMultiplier {
    protected final ECCurve curve;
    protected final GLVEndomorphism glvEndomorphism;

    public GLVMultiplier(ECCurve eCCurve, GLVEndomorphism gLVEndomorphism) {
        if (eCCurve == null || eCCurve.getOrder() == null) {
            throw new IllegalArgumentException("Need curve with known group order");
        }
        this.curve = eCCurve;
        this.glvEndomorphism = gLVEndomorphism;
    }

    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        if (this.curve.equals(eCPoint.getCurve())) {
            BigInteger[] decomposeScalar = this.glvEndomorphism.decomposeScalar(bigInteger.mod(eCPoint.getCurve().getOrder()));
            BigInteger bigInteger2 = decomposeScalar[0];
            BigInteger bigInteger3 = decomposeScalar[1];
            ECPointMap pointMap = this.glvEndomorphism.getPointMap();
            if (this.glvEndomorphism.hasEfficientPointMap()) {
                return ECAlgorithms.implShamirsTrickWNaf(eCPoint, bigInteger2, pointMap, bigInteger3);
            }
            return ECAlgorithms.implShamirsTrickWNaf(eCPoint, bigInteger2, pointMap.map(eCPoint), bigInteger3);
        }
        throw new IllegalStateException();
    }
}
