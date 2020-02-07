package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;
import org.spongycastle.crypto.signers.DSASigner;
import org.spongycastle.crypto.signers.HMacDSAKCalculator;

public class TlsDSSSigner extends TlsDSASigner {
    /* access modifiers changed from: protected */
    public short getSignatureAlgorithm() {
        return 2;
    }

    public boolean isValidPublicKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        return asymmetricKeyParameter instanceof DSAPublicKeyParameters;
    }

    /* access modifiers changed from: protected */
    public DSA createDSAImpl(short s) {
        return new DSASigner(new HMacDSAKCalculator(TlsUtils.createHash(s)));
    }
}
