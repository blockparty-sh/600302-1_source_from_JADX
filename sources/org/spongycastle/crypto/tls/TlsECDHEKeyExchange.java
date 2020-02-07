package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.p026io.TeeInputStream;

public class TlsECDHEKeyExchange extends TlsECDHKeyExchange {
    protected TlsSignerCredentials serverCredentials = null;

    public TlsECDHEKeyExchange(int i, Vector vector, int[] iArr, short[] sArr, short[] sArr2) {
        super(i, vector, iArr, sArr, sArr2);
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
        int i;
        ECDomainParameters eCDomainParameters;
        Digest digest;
        if (this.namedCurves != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.namedCurves.length) {
                    i = -1;
                    break;
                }
                int i3 = this.namedCurves[i2];
                if (NamedCurve.isValid(i3) && TlsECCUtils.isSupportedNamedCurve(i3)) {
                    i = i3;
                    break;
                }
                i2++;
            }
        } else {
            i = 23;
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = null;
        if (i >= 0) {
            eCDomainParameters = TlsECCUtils.getParametersForNamedCurve(i);
        } else if (Arrays.contains(this.namedCurves, 65281)) {
            eCDomainParameters = TlsECCUtils.getParametersForNamedCurve(23);
        } else if (Arrays.contains(this.namedCurves, (int) NamedCurve.arbitrary_explicit_char2_curves)) {
            eCDomainParameters = TlsECCUtils.getParametersForNamedCurve(10);
        } else {
            eCDomainParameters = null;
        }
        if (eCDomainParameters != null) {
            AsymmetricCipherKeyPair generateECKeyPair = TlsECCUtils.generateECKeyPair(this.context.getSecureRandom(), eCDomainParameters);
            this.ecAgreePrivateKey = (ECPrivateKeyParameters) generateECKeyPair.getPrivate();
            DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
            if (i < 0) {
                TlsECCUtils.writeExplicitECParameters(this.clientECPointFormats, eCDomainParameters, digestInputBuffer);
            } else {
                TlsECCUtils.writeNamedECParameters(i, digestInputBuffer);
            }
            TlsECCUtils.writeECPoint(this.clientECPointFormats, ((ECPublicKeyParameters) generateECKeyPair.getPublic()).getQ(), digestInputBuffer);
            if (TlsUtils.isTLSv12(this.context)) {
                signatureAndHashAlgorithm = this.serverCredentials.getSignatureAndHashAlgorithm();
                if (signatureAndHashAlgorithm != null) {
                    digest = TlsUtils.createHash(signatureAndHashAlgorithm.getHash());
                } else {
                    throw new TlsFatalAlert(80);
                }
            } else {
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
        TeeInputStream teeInputStream = new TeeInputStream(inputStream, signerInputBuffer);
        ECDomainParameters readECParameters = TlsECCUtils.readECParameters(this.namedCurves, this.clientECPointFormats, teeInputStream);
        byte[] readOpaque8 = TlsUtils.readOpaque8(teeInputStream);
        DigitallySigned parse = DigitallySigned.parse(this.context, inputStream);
        Signer initVerifyer = initVerifyer(this.tlsSigner, parse.getAlgorithm(), securityParameters);
        signerInputBuffer.updateSigner(initVerifyer);
        if (initVerifyer.verifySignature(parse.getSignature())) {
            this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(this.clientECPointFormats, readECParameters, readOpaque8));
            return;
        }
        throw new TlsFatalAlert(51);
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        short[] certificateTypes = certificateRequest.getCertificateTypes();
        int i = 0;
        while (i < certificateTypes.length) {
            short s = certificateTypes[i];
            if (s == 1 || s == 2 || s == 64) {
                i++;
            } else {
                throw new TlsFatalAlert(47);
            }
        }
    }

    public void processClientCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
    }

    /* access modifiers changed from: protected */
    public Signer initVerifyer(TlsSigner tlsSigner, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer createVerifyer = tlsSigner.createVerifyer(signatureAndHashAlgorithm, this.serverPublicKey);
        createVerifyer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        createVerifyer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return createVerifyer;
    }
}
