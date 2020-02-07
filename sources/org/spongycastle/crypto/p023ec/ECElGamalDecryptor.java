package org.spongycastle.crypto.p023ec;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.math.p025ec.ECPoint;

/* renamed from: org.spongycastle.crypto.ec.ECElGamalDecryptor */
public class ECElGamalDecryptor implements ECDecryptor {
    private ECPrivateKeyParameters key;

    public void init(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ECPrivateKeyParameters) {
            this.key = (ECPrivateKeyParameters) cipherParameters;
            return;
        }
        throw new IllegalArgumentException("ECPrivateKeyParameters are required for decryption.");
    }

    public ECPoint decrypt(ECPair eCPair) {
        if (this.key != null) {
            return eCPair.getY().subtract(eCPair.getX().multiply(this.key.getD())).normalize();
        }
        throw new IllegalStateException("ECElGamalDecryptor not initialised");
    }
}
