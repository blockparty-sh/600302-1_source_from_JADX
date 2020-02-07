package org.spongycastle.crypto.agreement;

import java.math.BigInteger;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.math.p025ec.ECPoint;

public class ECDHCBasicAgreement implements BasicAgreement {
    ECPrivateKeyParameters key;

    public void init(CipherParameters cipherParameters) {
        this.key = (ECPrivateKeyParameters) cipherParameters;
    }

    public int getFieldSize() {
        return (this.key.getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    public BigInteger calculateAgreement(CipherParameters cipherParameters) {
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) cipherParameters;
        ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
        ECPoint normalize = eCPublicKeyParameters.getQ().multiply(parameters.getH().multiply(this.key.getD()).mod(parameters.getN())).normalize();
        if (!normalize.isInfinity()) {
            return normalize.getAffineXCoord().toBigInteger();
        }
        throw new IllegalStateException("Infinity is not a valid agreement value for ECDHC");
    }
}
