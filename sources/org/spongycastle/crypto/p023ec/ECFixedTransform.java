package org.spongycastle.crypto.p023ec;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.math.p025ec.ECMultiplier;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.math.p025ec.FixedPointCombMultiplier;

/* renamed from: org.spongycastle.crypto.ec.ECFixedTransform */
public class ECFixedTransform implements ECPairFactorTransform {

    /* renamed from: k */
    private BigInteger f1082k;
    private ECPublicKeyParameters key;

    public ECFixedTransform(BigInteger bigInteger) {
        this.f1082k = bigInteger;
    }

    public void init(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ECPublicKeyParameters) {
            this.key = (ECPublicKeyParameters) cipherParameters;
            return;
        }
        throw new IllegalArgumentException("ECPublicKeyParameters are required for fixed transform.");
    }

    public ECPair transform(ECPair eCPair) {
        ECPublicKeyParameters eCPublicKeyParameters = this.key;
        if (eCPublicKeyParameters != null) {
            ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
            BigInteger n = parameters.getN();
            ECMultiplier createBasePointMultiplier = createBasePointMultiplier();
            BigInteger mod = this.f1082k.mod(n);
            ECPoint[] eCPointArr = {createBasePointMultiplier.multiply(parameters.getG(), mod).add(eCPair.getX()), this.key.getQ().multiply(mod).add(eCPair.getY())};
            parameters.getCurve().normalizeAll(eCPointArr);
            return new ECPair(eCPointArr[0], eCPointArr[1]);
        }
        throw new IllegalStateException("ECFixedTransform not initialised");
    }

    public BigInteger getTransformValue() {
        return this.f1082k;
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}
