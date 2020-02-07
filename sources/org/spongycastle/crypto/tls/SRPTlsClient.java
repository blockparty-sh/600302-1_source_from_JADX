package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import org.spongycastle.util.Arrays;

public abstract class SRPTlsClient extends AbstractTlsClient {
    public static final Integer EXT_SRP = TlsSRPUtils.EXT_SRP;
    protected byte[] identity;
    protected byte[] password;

    public SRPTlsClient(byte[] bArr, byte[] bArr2) {
        this.identity = Arrays.clone(bArr);
        this.password = Arrays.clone(bArr2);
    }

    public SRPTlsClient(TlsCipherFactory tlsCipherFactory, byte[] bArr, byte[] bArr2) {
        super(tlsCipherFactory);
        this.identity = Arrays.clone(bArr);
        this.password = Arrays.clone(bArr2);
    }

    public int[] getCipherSuites() {
        return new int[]{49182};
    }

    public Hashtable getClientExtensions() throws IOException {
        Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(super.getClientExtensions());
        TlsSRPUtils.addSRPExtension(ensureExtensionsInitialised, this.identity);
        return ensureExtensionsInitialised;
    }

    public void processServerExtensions(Hashtable hashtable) throws IOException {
        TlsUtils.hasExpectedEmptyExtensionData(hashtable, TlsSRPUtils.EXT_SRP, 47);
    }

    public TlsKeyExchange getKeyExchange() throws IOException {
        switch (this.selectedCipherSuite) {
            case CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA /*49178*/:
            case CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA /*49181*/:
            case CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA /*49184*/:
                return createSRPKeyExchange(21);
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA /*49179*/:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA /*49182*/:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA /*49185*/:
                return createSRPKeyExchange(23);
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA /*49180*/:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA /*49183*/:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA /*49186*/:
                return createSRPKeyExchange(22);
            default:
                throw new TlsFatalAlert(80);
        }
    }

    public TlsCipher getCipher() throws IOException {
        switch (this.selectedCipherSuite) {
            case CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA /*49178*/:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA /*49179*/:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA /*49180*/:
                return this.cipherFactory.createCipher(this.context, 7, 2);
            case CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA /*49181*/:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA /*49182*/:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA /*49183*/:
                return this.cipherFactory.createCipher(this.context, 8, 2);
            case CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA /*49184*/:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA /*49185*/:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA /*49186*/:
                return this.cipherFactory.createCipher(this.context, 9, 2);
            default:
                throw new TlsFatalAlert(80);
        }
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createSRPKeyExchange(int i) {
        return new TlsSRPKeyExchange(i, this.supportedSignatureAlgorithms, this.identity, this.password);
    }
}
