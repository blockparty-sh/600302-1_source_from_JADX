package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public abstract class AbstractTlsSigner implements TlsSigner {
    protected TlsContext context;

    public void init(TlsContext tlsContext) {
        this.context = tlsContext;
    }

    public byte[] generateRawSignature(AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr) throws CryptoException {
        return generateRawSignature(null, asymmetricKeyParameter, bArr);
    }

    public boolean verifyRawSignature(byte[] bArr, AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr2) throws CryptoException {
        return verifyRawSignature(null, bArr, asymmetricKeyParameter, bArr2);
    }

    public Signer createSigner(AsymmetricKeyParameter asymmetricKeyParameter) {
        return createSigner(null, asymmetricKeyParameter);
    }

    public Signer createVerifyer(AsymmetricKeyParameter asymmetricKeyParameter) {
        return createVerifyer(null, asymmetricKeyParameter);
    }
}
