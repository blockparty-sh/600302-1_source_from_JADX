package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;

public class TlsDHKeyExchange extends AbstractTlsKeyExchange {
    protected static final BigInteger ONE = BigInteger.valueOf(1);
    protected static final BigInteger TWO = BigInteger.valueOf(2);
    protected TlsAgreementCredentials agreementCredentials;
    protected DHPrivateKeyParameters dhAgreeClientPrivateKey;
    protected DHPublicKeyParameters dhAgreeClientPublicKey;
    protected DHPrivateKeyParameters dhAgreeServerPrivateKey;
    protected DHPublicKeyParameters dhAgreeServerPublicKey;
    protected DHParameters dhParameters;
    protected AsymmetricKeyParameter serverPublicKey;
    protected TlsSigner tlsSigner;

    public TlsDHKeyExchange(int i, Vector vector, DHParameters dHParameters) {
        super(i, vector);
        if (i == 3) {
            this.tlsSigner = new TlsDSSSigner();
        } else if (i == 5) {
            this.tlsSigner = new TlsRSASigner();
        } else if (i == 7 || i == 9) {
            this.tlsSigner = null;
        } else {
            throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
        this.dhParameters = dHParameters;
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
                        this.dhAgreeServerPublicKey = TlsDHUtils.validateDHPublicKey((DHPublicKeyParameters) this.serverPublicKey);
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
        return i == 3 || i == 5 || i == 11;
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        short[] certificateTypes = certificateRequest.getCertificateTypes();
        int i = 0;
        while (i < certificateTypes.length) {
            short s = certificateTypes[i];
            if (s == 1 || s == 2 || s == 3 || s == 4 || s == 64) {
                i++;
            } else {
                throw new TlsFatalAlert(47);
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
            this.dhAgreeClientPrivateKey = TlsDHUtils.generateEphemeralClientKeyExchange(this.context.getSecureRandom(), this.dhAgreeServerPublicKey.getParameters(), outputStream);
        }
    }

    public byte[] generatePremasterSecret() throws IOException {
        TlsAgreementCredentials tlsAgreementCredentials = this.agreementCredentials;
        if (tlsAgreementCredentials != null) {
            return tlsAgreementCredentials.generateAgreement(this.dhAgreeServerPublicKey);
        }
        DHPrivateKeyParameters dHPrivateKeyParameters = this.dhAgreeServerPrivateKey;
        if (dHPrivateKeyParameters != null) {
            return TlsDHUtils.calculateDHBasicAgreement(this.dhAgreeClientPublicKey, dHPrivateKeyParameters);
        }
        DHPrivateKeyParameters dHPrivateKeyParameters2 = this.dhAgreeClientPrivateKey;
        if (dHPrivateKeyParameters2 != null) {
            return TlsDHUtils.calculateDHBasicAgreement(this.dhAgreeServerPublicKey, dHPrivateKeyParameters2);
        }
        throw new TlsFatalAlert(80);
    }
}
