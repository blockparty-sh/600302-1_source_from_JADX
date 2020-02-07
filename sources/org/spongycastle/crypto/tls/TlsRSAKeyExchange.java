package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.p026io.Streams;

public class TlsRSAKeyExchange extends AbstractTlsKeyExchange {
    protected byte[] premasterSecret;
    protected RSAKeyParameters rsaServerPublicKey = null;
    protected TlsEncryptionCredentials serverCredentials = null;
    protected AsymmetricKeyParameter serverPublicKey = null;

    public TlsRSAKeyExchange(Vector vector) {
        super(1, vector);
    }

    public void skipServerCredentials() throws IOException {
        throw new TlsFatalAlert(10);
    }

    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (tlsCredentials instanceof TlsEncryptionCredentials) {
            processServerCertificate(tlsCredentials.getCertificate());
            this.serverCredentials = (TlsEncryptionCredentials) tlsCredentials;
            return;
        }
        throw new TlsFatalAlert(80);
    }

    public void processServerCertificate(Certificate certificate) throws IOException {
        if (!certificate.isEmpty()) {
            Certificate certificateAt = certificate.getCertificateAt(0);
            try {
                this.serverPublicKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
                if (!this.serverPublicKey.isPrivate()) {
                    this.rsaServerPublicKey = validateRSAPublicKey((RSAKeyParameters) this.serverPublicKey);
                    TlsUtils.validateKeyUsage(certificateAt, 32);
                    super.processServerCertificate(certificate);
                    return;
                }
                throw new TlsFatalAlert(80);
            } catch (RuntimeException unused) {
                throw new TlsFatalAlert(43);
            }
        } else {
            throw new TlsFatalAlert(42);
        }
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

    public void generateClientKeyExchange(OutputStream outputStream) throws IOException {
        this.premasterSecret = TlsRSAUtils.generateEncryptedPreMasterSecret(this.context, this.rsaServerPublicKey, outputStream);
    }

    public void processClientKeyExchange(InputStream inputStream) throws IOException {
        byte[] bArr;
        if (TlsUtils.isSSL(this.context)) {
            bArr = Streams.readAll(inputStream);
        } else {
            bArr = TlsUtils.readOpaque16(inputStream);
        }
        this.premasterSecret = this.serverCredentials.decryptPreMasterSecret(bArr);
    }

    public byte[] generatePremasterSecret() throws IOException {
        byte[] bArr = this.premasterSecret;
        if (bArr != null) {
            this.premasterSecret = null;
            return bArr;
        }
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public RSAKeyParameters validateRSAPublicKey(RSAKeyParameters rSAKeyParameters) throws IOException {
        if (rSAKeyParameters.getExponent().isProbablePrime(2)) {
            return rSAKeyParameters;
        }
        throw new TlsFatalAlert(47);
    }
}
