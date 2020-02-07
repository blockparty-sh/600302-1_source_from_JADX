package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.signers.ECDSASigner;
import org.spongycastle.crypto.signers.HMacDSAKCalculator;

public class TlsECDSASigner extends TlsDSASigner {
    /* access modifiers changed from: protected */
    public short getSignatureAlgorithm() {
        return 3;
    }

    public boolean isValidPublicKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        return asymmetricKeyParameter instanceof ECPublicKeyParameters;
    }

    /* access modifiers changed from: protected */
    public DSA createDSAImpl(short s) {
        return new ECDSASigner(new HMacDSAKCalculator(TlsUtils.createHash(s)));
    }
}
