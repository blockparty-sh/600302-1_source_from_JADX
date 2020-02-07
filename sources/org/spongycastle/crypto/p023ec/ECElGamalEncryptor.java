package org.spongycastle.crypto.p023ec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.math.p025ec.ECMultiplier;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.math.p025ec.FixedPointCombMultiplier;

/* renamed from: org.spongycastle.crypto.ec.ECElGamalEncryptor */
public class ECElGamalEncryptor implements ECEncryptor {
    private ECPublicKeyParameters key;
    private SecureRandom random;

    public void init(CipherParameters cipherParameters) {
        String str = "ECPublicKeyParameters are required for encryption.";
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            if (parametersWithRandom.getParameters() instanceof ECPublicKeyParameters) {
                this.key = (ECPublicKeyParameters) parametersWithRandom.getParameters();
                this.random = parametersWithRandom.getRandom();
                return;
            }
            throw new IllegalArgumentException(str);
        } else if (cipherParameters instanceof ECPublicKeyParameters) {
            this.key = (ECPublicKeyParameters) cipherParameters;
            this.random = new SecureRandom();
        } else {
            throw new IllegalArgumentException(str);
        }
    }

    public ECPair encrypt(ECPoint eCPoint) {
        ECPublicKeyParameters eCPublicKeyParameters = this.key;
        if (eCPublicKeyParameters != null) {
            ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
            BigInteger generateK = ECUtil.generateK(parameters.getN(), this.random);
            ECPoint[] eCPointArr = {createBasePointMultiplier().multiply(parameters.getG(), generateK), this.key.getQ().multiply(generateK).add(eCPoint)};
            parameters.getCurve().normalizeAll(eCPointArr);
            return new ECPair(eCPointArr[0], eCPointArr[1]);
        }
        throw new IllegalStateException("ECElGamalEncryptor not initialised");
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}
