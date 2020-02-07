package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;

public class TlsECDHKeyExchange extends AbstractTlsKeyExchange {
    protected TlsAgreementCredentials agreementCredentials;
    protected short[] clientECPointFormats;
    protected ECPrivateKeyParameters ecAgreePrivateKey;
    protected ECPublicKeyParameters ecAgreePublicKey;
    protected int[] namedCurves;
    protected short[] serverECPointFormats;
    protected AsymmetricKeyParameter serverPublicKey;
    protected TlsSigner tlsSigner;

    public void processClientCertificate(Certificate certificate) throws IOException {
    }

    public TlsECDHKeyExchange(int i, Vector vector, int[] iArr, short[] sArr, short[] sArr2) {
        super(i, vector);
        switch (i) {
            case 16:
            case 18:
                this.tlsSigner = null;
                break;
            case 17:
                this.tlsSigner = new TlsECDSASigner();
                break;
            case 19:
                this.tlsSigner = new TlsRSASigner();
                break;
            default:
                throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
        this.keyExchange = i;
        this.namedCurves = iArr;
        this.clientECPointFormats = sArr;
        this.serverECPointFormats = sArr2;
    }

    public void init(TlsContext tlsContext) {
        super.init(tlsContext);
        TlsSigner tlsSigner2 = this.tlsSigner;
        if (tlsSigner2 != null) {
            tlsSigner2.init(tlsContext);
        }
    }

    public void skipServerCredentials() throws IOException {
        throw new TlsFatalAlert(10);
    }

    public void processServerCertificate(Certificate certificate) throws IOException {
        if (!certificate.isEmpty()) {
            Certificate certificateAt = certificate.getCertificateAt(0);
            try {
                this.serverPublicKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
                TlsSigner tlsSigner2 = this.tlsSigner;
                if (tlsSigner2 == null) {
                    try {
                        this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey((ECPublicKeyParameters) this.serverPublicKey);
                        TlsUtils.validateKeyUsage(certificateAt, 8);
                    } catch (ClassCastException unused) {
                        throw new TlsFatalAlert(46);
                    }
                } else if (tlsSigner2.isValidPublicKey(this.serverPublicKey)) {
                    TlsUtils.validateKeyUsage(certificateAt, 128);
                } else {
                    throw new TlsFatalAlert(46);
                }
                super.processServerCertificate(certificate);
            } catch (RuntimeException unused2) {
                throw new TlsFatalAlert(43);
            }
        } else {
            throw new TlsFatalAlert(42);
        }
    }

    public boolean requiresServerKeyExchange() {
        int i = this.keyExchange;
        return i == 17 || i == 19 || i == 20;
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        short[] certificateTypes = certificateRequest.getCertificateTypes();
        for (short s : certificateTypes) {
            if (!(s == 1 || s == 2)) {
                switch (s) {
                    case 64:
                    case 65:
                    case 66:
                        break;
                    default:
                        throw new TlsFatalAlert(47);
                }
            }
        }
    }

    public void processClientCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (tlsCredentials instanceof TlsAgreementCredentials) {
            this.agreementCredentials = (TlsAgreementCredentials) tlsCredentials;
        } else if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
    }

    public void generateClientKeyExchange(OutputStream outputStream) throws IOException {
        if (this.agreementCredentials == null) {
            this.ecAgreePrivateKey = TlsECCUtils.generateEphemeralClientKeyExchange(this.context.getSecureRandom(), this.serverECPointFormats, this.ecAgreePublicKey.getParameters(), outputStream);
        }
    }

    public void processClientKeyExchange(InputStream inputStream) throws IOException {
        if (this.ecAgreePublicKey == null) {
            byte[] readOpaque8 = TlsUtils.readOpaque8(inputStream);
            this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(this.serverECPointFormats, this.ecAgreePrivateKey.getParameters(), readOpaque8));
        }
    }

    public byte[] generatePremasterSecret() throws IOException {
        TlsAgreementCredentials tlsAgreementCredentials = this.agreementCredentials;
        if (tlsAgreementCredentials != null) {
            return tlsAgreementCredentials.generateAgreement(this.ecAgreePublicKey);
        }
        ECPrivateKeyParameters eCPrivateKeyParameters = this.ecAgreePrivateKey;
        if (eCPrivateKeyParameters != null) {
            return TlsECCUtils.calculateECDHBasicAgreement(this.ecAgreePublicKey, eCPrivateKeyParameters);
        }
        throw new TlsFatalAlert(80);
    }
}
