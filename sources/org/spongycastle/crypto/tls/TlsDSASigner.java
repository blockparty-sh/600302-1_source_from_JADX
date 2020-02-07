package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.digests.NullDigest;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.signers.DSADigestSigner;

public abstract class TlsDSASigner extends AbstractTlsSigner {
    /* access modifiers changed from: protected */
    public abstract DSA createDSAImpl(short s);

    /* access modifiers changed from: protected */
    public abstract short getSignatureAlgorithm();

    /* access modifiers changed from: protected */
    public CipherParameters makeInitParameters(boolean z, CipherParameters cipherParameters) {
        return cipherParameters;
    }

    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr) throws CryptoException {
        Signer makeSigner = makeSigner(signatureAndHashAlgorithm, true, true, new ParametersWithRandom(asymmetricKeyParameter, this.context.getSecureRandom()));
        if (signatureAndHashAlgorithm == null) {
            makeSigner.update(bArr, 16, 20);
        } else {
            makeSigner.update(bArr, 0, bArr.length);
        }
        return makeSigner.generateSignature();
    }

    public boolean verifyRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr, AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr2) throws CryptoException {
        Signer makeSigner = makeSigner(signatureAndHashAlgorithm, true, false, asymmetricKeyParameter);
        if (signatureAndHashAlgorithm == null) {
            makeSigner.update(bArr2, 16, 20);
        } else {
            makeSigner.update(bArr2, 0, bArr2.length);
        }
        return makeSigner.verifySignature(bArr);
    }

    public Signer createSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter) {
        return makeSigner(signatureAndHashAlgorithm, false, true, asymmetricKeyParameter);
    }

    public Signer createVerifyer(SignatureAndHashAlgorithm signatureAndHashAlgorithm, AsymmetricKeyParameter asymmetricKeyParameter) {
        return makeSigner(signatureAndHashAlgorithm, false, false, asymmetricKeyParameter);
    }

    /* access modifiers changed from: protected */
    public Signer makeSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm, boolean z, boolean z2, CipherParameters cipherParameters) {
        if ((signatureAndHashAlgorithm != null) == TlsUtils.isTLSv12(this.context)) {
            short s = 2;
            if (signatureAndHashAlgorithm == null || (signatureAndHashAlgorithm.getHash() == 2 && signatureAndHashAlgorithm.getSignature() == getSignatureAlgorithm())) {
                if (signatureAndHashAlgorithm != null) {
                    s = signatureAndHashAlgorithm.getHash();
                }
                DSADigestSigner dSADigestSigner = new DSADigestSigner(createDSAImpl(s), z ? new NullDigest() : TlsUtils.createHash(s));
                dSADigestSigner.init(z2, makeInitParameters(z2, cipherParameters));
                return dSADigestSigner;
            }
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }
}
