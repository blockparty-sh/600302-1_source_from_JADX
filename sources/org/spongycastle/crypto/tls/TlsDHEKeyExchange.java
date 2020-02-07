package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.util.p026io.TeeInputStream;

public class TlsDHEKeyExchange extends TlsDHKeyExchange {
    protected TlsSignerCredentials serverCredentials = null;

    public TlsDHEKeyExchange(int i, Vector vector, DHParameters dHParameters) {
        super(i, vector, dHParameters);
    }

    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (tlsCredentials instanceof TlsSignerCredentials) {
            processServerCertificate(tlsCredentials.getCertificate());
            this.serverCredentials = (TlsSignerCredentials) tlsCredentials;
            return;
        }
        throw new TlsFatalAlert(80);
    }

    public byte[] generateServerKeyExchange() throws IOException {
        SignatureAndHashAlgorithm signatureAndHashAlgorithm;
        Digest digest;
        if (this.dhParameters != null) {
            DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
            this.dhAgreeServerPrivateKey = TlsDHUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.dhParameters, digestInputBuffer);
            if (TlsUtils.isTLSv12(this.context)) {
                signatureAndHashAlgorithm = this.serverCredentials.getSignatureAndHashAlgorithm();
                if (signatureAndHashAlgorithm != null) {
                    digest = TlsUtils.createHash(signatureAndHashAlgorithm.getHash());
                } else {
                    throw new TlsFatalAlert(80);
                }
            } else {
                signatureAndHashAlgorithm = null;
                digest = new CombinedHash();
            }
            SecurityParameters securityParameters = this.context.getSecurityParameters();
            digest.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
            digest.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
            digestInputBuffer.updateDigest(digest);
            byte[] bArr = new byte[digest.getDigestSize()];
            digest.doFinal(bArr, 0);
            new DigitallySigned(signatureAndHashAlgorithm, this.serverCredentials.generateCertificateSignature(bArr)).encode(digestInputBuffer);
            return digestInputBuffer.toByteArray();
        }
        throw new TlsFatalAlert(80);
    }

    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        SignerInputBuffer signerInputBuffer = new SignerInputBuffer();
        ServerDHParams parse = ServerDHParams.parse(new TeeInputStream(inputStream, signerInputBuffer));
        DigitallySigned parse2 = DigitallySigned.parse(this.context, inputStream);
        Signer initVerifyer = initVerifyer(this.tlsSigner, parse2.getAlgorithm(), securityParameters);
        signerInputBuffer.updateSigner(initVerifyer);
        if (initVerifyer.verifySignature(parse2.getSignature())) {
            this.dhAgreeServerPublicKey = TlsDHUtils.validateDHPublicKey(parse.getPublicKey());
            return;
        }
        throw new TlsFatalAlert(51);
    }

    /* access modifiers changed from: protected */
    public Signer initVerifyer(TlsSigner tlsSigner, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer createVerifyer = tlsSigner.createVerifyer(signatureAndHashAlgorithm, this.serverPublicKey);
        createVerifyer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        createVerifyer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return createVerifyer;
    }
}
