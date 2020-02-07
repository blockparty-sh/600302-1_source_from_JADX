package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;

public class TlsPSKKeyExchange extends AbstractTlsKeyExchange {
    protected short[] clientECPointFormats;
    protected DHPrivateKeyParameters dhAgreePrivateKey = null;
    protected DHPublicKeyParameters dhAgreePublicKey = null;
    protected DHParameters dhParameters;
    protected int[] namedCurves;
    protected byte[] premasterSecret;
    protected TlsPSKIdentity pskIdentity;
    protected byte[] psk_identity_hint = null;
    protected RSAKeyParameters rsaServerPublicKey = null;
    protected TlsEncryptionCredentials serverCredentials = null;
    protected short[] serverECPointFormats;
    protected AsymmetricKeyParameter serverPublicKey = null;

    public TlsPSKKeyExchange(int i, Vector vector, TlsPSKIdentity tlsPSKIdentity, DHParameters dHParameters, int[] iArr, short[] sArr, short[] sArr2) {
        super(i, vector);
        if (i != 24) {
            switch (i) {
                case 13:
                case 14:
                case 15:
                    break;
                default:
                    throw new IllegalArgumentException("unsupported key exchange algorithm");
            }
        }
        this.pskIdentity = tlsPSKIdentity;
        this.dhParameters = dHParameters;
        this.namedCurves = iArr;
        this.clientECPointFormats = sArr;
        this.serverECPointFormats = sArr2;
    }

    public void skipServerCredentials() throws IOException {
        if (this.keyExchange == 15) {
            throw new TlsFatalAlert(10);
        }
    }

    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (tlsCredentials instanceof TlsEncryptionCredentials) {
            processServerCertificate(tlsCredentials.getCertificate());
            this.serverCredentials = (TlsEncryptionCredentials) tlsCredentials;
            return;
        }
        throw new TlsFatalAlert(80);
    }

    public byte[] generateServerKeyExchange() throws IOException {
        this.psk_identity_hint = null;
        if (this.psk_identity_hint == null && !requiresServerKeyExchange()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = this.psk_identity_hint;
        if (bArr == null) {
            TlsUtils.writeOpaque16(TlsUtils.EMPTY_BYTES, byteArrayOutputStream);
        } else {
            TlsUtils.writeOpaque16(bArr, byteArrayOutputStream);
        }
        if (this.keyExchange != 14) {
            int i = this.keyExchange;
        } else if (this.dhParameters != null) {
            this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.dhParameters, byteArrayOutputStream);
        } else {
            throw new TlsFatalAlert(80);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public void processServerCertificate(Certificate certificate) throws IOException {
        if (this.keyExchange != 15) {
            throw new TlsFatalAlert(10);
        } else if (!certificate.isEmpty()) {
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

    public boolean requiresServerKeyExchange() {
        int i = this.keyExchange;
        return i == 14 || i == 24;
    }

    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        this.psk_identity_hint = TlsUtils.readOpaque16(inputStream);
        if (this.keyExchange == 14) {
            this.dhAgreePublicKey = TlsDHUtils.validateDHPublicKey(ServerDHParams.parse(inputStream).getPublicKey());
        } else {
            int i = this.keyExchange;
        }
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        throw new TlsFatalAlert(10);
    }

    public void processClientCredentials(TlsCredentials tlsCredentials) throws IOException {
        throw new TlsFatalAlert(80);
    }

    public void generateClientKeyExchange(OutputStream outputStream) throws IOException {
        byte[] bArr = this.psk_identity_hint;
        if (bArr == null) {
            this.pskIdentity.skipIdentityHint();
        } else {
            this.pskIdentity.notifyIdentityHint(bArr);
        }
        TlsUtils.writeOpaque16(this.pskIdentity.getPSKIdentity(), outputStream);
        if (this.keyExchange == 14) {
            this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralClientKeyExchange(this.context.getSecureRandom(), this.dhAgreePublicKey.getParameters(), outputStream);
        } else if (this.keyExchange == 24) {
            throw new TlsFatalAlert(80);
        } else if (this.keyExchange == 15) {
            this.premasterSecret = TlsRSAUtils.generateEncryptedPreMasterSecret(this.context, this.rsaServerPublicKey, outputStream);
        }
    }

    public byte[] generatePremasterSecret() throws IOException {
        byte[] psk = this.pskIdentity.getPSK();
        byte[] generateOtherSecret = generateOtherSecret(psk.length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(generateOtherSecret.length + 4 + psk.length);
        TlsUtils.writeOpaque16(generateOtherSecret, byteArrayOutputStream);
        TlsUtils.writeOpaque16(psk, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: protected */
    public byte[] generateOtherSecret(int i) throws IOException {
        if (this.keyExchange == 14) {
            DHPrivateKeyParameters dHPrivateKeyParameters = this.dhAgreePrivateKey;
            if (dHPrivateKeyParameters != null) {
                return TlsDHUtils.calculateDHBasicAgreement(this.dhAgreePublicKey, dHPrivateKeyParameters);
            }
            throw new TlsFatalAlert(80);
        } else if (this.keyExchange == 24) {
            throw new TlsFatalAlert(80);
        } else if (this.keyExchange == 15) {
            return this.premasterSecret;
        } else {
            return new byte[i];
        }
    }

    /* access modifiers changed from: protected */
    public RSAKeyParameters validateRSAPublicKey(RSAKeyParameters rSAKeyParameters) throws IOException {
        if (rSAKeyParameters.getExponent().isProbablePrime(2)) {
            return rSAKeyParameters;
        }
        throw new TlsFatalAlert(47);
    }
}
