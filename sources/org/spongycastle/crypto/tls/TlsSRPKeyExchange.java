package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.agreement.srp.SRP6Client;
import org.spongycastle.crypto.agreement.srp.SRP6Util;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.p026io.TeeInputStream;

public class TlsSRPKeyExchange extends AbstractTlsKeyExchange {

    /* renamed from: B */
    protected BigInteger f1342B = null;
    protected byte[] identity;
    protected byte[] password;

    /* renamed from: s */
    protected byte[] f1343s = null;
    protected AsymmetricKeyParameter serverPublicKey = null;
    protected SRP6Client srpClient = new SRP6Client();
    protected TlsSigner tlsSigner;

    public boolean requiresServerKeyExchange() {
        return true;
    }

    public TlsSRPKeyExchange(int i, Vector vector, byte[] bArr, byte[] bArr2) {
        super(i, vector);
        switch (i) {
            case 21:
                this.tlsSigner = null;
                break;
            case 22:
                this.tlsSigner = new TlsDSSSigner();
                break;
            case 23:
                this.tlsSigner = new TlsRSASigner();
                break;
            default:
                throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
        this.keyExchange = i;
        this.identity = bArr;
        this.password = bArr2;
    }

    public void init(TlsContext tlsContext) {
        super.init(tlsContext);
        TlsSigner tlsSigner2 = this.tlsSigner;
        if (tlsSigner2 != null) {
            tlsSigner2.init(tlsContext);
        }
    }

    public void skipServerCredentials() throws IOException {
        if (this.tlsSigner != null) {
            throw new TlsFatalAlert(10);
        }
    }

    public void processServerCertificate(Certificate certificate) throws IOException {
        if (this.tlsSigner == null) {
            throw new TlsFatalAlert(10);
        } else if (!certificate.isEmpty()) {
            Certificate certificateAt = certificate.getCertificateAt(0);
            try {
                this.serverPublicKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
                if (this.tlsSigner.isValidPublicKey(this.serverPublicKey)) {
                    TlsUtils.validateKeyUsage(certificateAt, 128);
                    super.processServerCertificate(certificate);
                    return;
                }
                throw new TlsFatalAlert(46);
            } catch (RuntimeException unused) {
                throw new TlsFatalAlert(43);
            }
        } else {
            throw new TlsFatalAlert(42);
        }
    }

    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        InputStream inputStream2;
        SignerInputBuffer signerInputBuffer;
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        if (this.tlsSigner != null) {
            signerInputBuffer = new SignerInputBuffer();
            inputStream2 = new TeeInputStream(inputStream, signerInputBuffer);
        } else {
            signerInputBuffer = null;
            inputStream2 = inputStream;
        }
        byte[] readOpaque16 = TlsUtils.readOpaque16(inputStream2);
        byte[] readOpaque162 = TlsUtils.readOpaque16(inputStream2);
        byte[] readOpaque8 = TlsUtils.readOpaque8(inputStream2);
        byte[] readOpaque163 = TlsUtils.readOpaque16(inputStream2);
        if (signerInputBuffer != null) {
            DigitallySigned parse = DigitallySigned.parse(this.context, inputStream);
            Signer initVerifyer = initVerifyer(this.tlsSigner, parse.getAlgorithm(), securityParameters);
            signerInputBuffer.updateSigner(initVerifyer);
            if (!initVerifyer.verifySignature(parse.getSignature())) {
                throw new TlsFatalAlert(51);
            }
        }
        BigInteger bigInteger = new BigInteger(1, readOpaque16);
        BigInteger bigInteger2 = new BigInteger(1, readOpaque162);
        this.f1343s = readOpaque8;
        try {
            this.f1342B = SRP6Util.validatePublicValue(bigInteger, new BigInteger(1, readOpaque163));
            this.srpClient.init(bigInteger, bigInteger2, TlsUtils.createHash(2), this.context.getSecureRandom());
        } catch (CryptoException unused) {
            throw new TlsFatalAlert(47);
        }
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        throw new TlsFatalAlert(10);
    }

    public void processClientCredentials(TlsCredentials tlsCredentials) throws IOException {
        throw new TlsFatalAlert(80);
    }

    public void generateClientKeyExchange(OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque16(BigIntegers.asUnsignedByteArray(this.srpClient.generateClientCredentials(this.f1343s, this.identity, this.password)), outputStream);
    }

    public byte[] generatePremasterSecret() throws IOException {
        try {
            return BigIntegers.asUnsignedByteArray(this.srpClient.calculateSecret(this.f1342B));
        } catch (CryptoException unused) {
            throw new TlsFatalAlert(47);
        }
    }

    /* access modifiers changed from: protected */
    public Signer initVerifyer(TlsSigner tlsSigner2, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer createVerifyer = tlsSigner2.createVerifyer(signatureAndHashAlgorithm, this.serverPublicKey);
        createVerifyer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        createVerifyer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return createVerifyer;
    }
}
