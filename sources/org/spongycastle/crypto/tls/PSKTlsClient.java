package org.spongycastle.crypto.tls;

public abstract class PSKTlsClient extends AbstractTlsClient {
    protected TlsPSKIdentity pskIdentity;

    public PSKTlsClient(TlsPSKIdentity tlsPSKIdentity) {
        this.pskIdentity = tlsPSKIdentity;
    }

    public PSKTlsClient(TlsCipherFactory tlsCipherFactory, TlsPSKIdentity tlsPSKIdentity) {
        super(tlsCipherFactory);
        this.pskIdentity = tlsPSKIdentity;
    }

    public int[] getCipherSuites() {
        return new int[]{CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA, 182, 148};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return createPSKKeyExchange(24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        return createPSKKeyExchange(15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        return createPSKKeyExchange(14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        return createPSKKeyExchange(13);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.spongycastle.crypto.tls.TlsKeyExchange getKeyExchange() throws java.io.IOException {
        /*
            r2 = this;
            int r0 = r2.selectedCipherSuite
            switch(r0) {
                case 44: goto L_0x0034;
                case 45: goto L_0x002d;
                case 46: goto L_0x0026;
                default: goto L_0x0005;
            }
        L_0x0005:
            switch(r0) {
                case 138: goto L_0x0034;
                case 139: goto L_0x0034;
                case 140: goto L_0x0034;
                case 141: goto L_0x0034;
                case 142: goto L_0x002d;
                case 143: goto L_0x002d;
                case 144: goto L_0x002d;
                case 145: goto L_0x002d;
                case 146: goto L_0x0026;
                case 147: goto L_0x0026;
                case 148: goto L_0x0026;
                case 149: goto L_0x0026;
                default: goto L_0x0008;
            }
        L_0x0008:
            switch(r0) {
                case 168: goto L_0x0034;
                case 169: goto L_0x0034;
                case 170: goto L_0x002d;
                case 171: goto L_0x002d;
                case 172: goto L_0x0026;
                case 173: goto L_0x0026;
                case 174: goto L_0x0034;
                case 175: goto L_0x0034;
                case 176: goto L_0x0034;
                case 177: goto L_0x0034;
                case 178: goto L_0x002d;
                case 179: goto L_0x002d;
                case 180: goto L_0x002d;
                case 181: goto L_0x002d;
                case 182: goto L_0x0026;
                case 183: goto L_0x0026;
                case 184: goto L_0x0026;
                case 185: goto L_0x0026;
                default: goto L_0x000b;
            }
        L_0x000b:
            switch(r0) {
                case 49203: goto L_0x001f;
                case 49204: goto L_0x001f;
                case 49205: goto L_0x001f;
                case 49206: goto L_0x001f;
                case 49207: goto L_0x001f;
                case 49208: goto L_0x001f;
                case 49209: goto L_0x001f;
                case 49210: goto L_0x001f;
                case 49211: goto L_0x001f;
                default: goto L_0x000e;
            }
        L_0x000e:
            switch(r0) {
                case 49294: goto L_0x0034;
                case 49295: goto L_0x0034;
                case 49296: goto L_0x002d;
                case 49297: goto L_0x002d;
                case 49298: goto L_0x0026;
                case 49299: goto L_0x0026;
                case 49300: goto L_0x0034;
                case 49301: goto L_0x0034;
                case 49302: goto L_0x002d;
                case 49303: goto L_0x002d;
                case 49304: goto L_0x0026;
                case 49305: goto L_0x0026;
                case 49306: goto L_0x001f;
                case 49307: goto L_0x001f;
                default: goto L_0x0011;
            }
        L_0x0011:
            switch(r0) {
                case 49316: goto L_0x0034;
                case 49317: goto L_0x0034;
                case 49318: goto L_0x002d;
                case 49319: goto L_0x002d;
                case 49320: goto L_0x0034;
                case 49321: goto L_0x0034;
                case 49322: goto L_0x002d;
                case 49323: goto L_0x002d;
                default: goto L_0x0014;
            }
        L_0x0014:
            switch(r0) {
                case 58390: goto L_0x0034;
                case 58391: goto L_0x0034;
                case 58392: goto L_0x001f;
                case 58393: goto L_0x001f;
                case 58394: goto L_0x0026;
                case 58395: goto L_0x0026;
                case 58396: goto L_0x002d;
                case 58397: goto L_0x002d;
                default: goto L_0x0017;
            }
        L_0x0017:
            org.spongycastle.crypto.tls.TlsFatalAlert r0 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r1 = 80
            r0.<init>(r1)
            throw r0
        L_0x001f:
            r0 = 24
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createPSKKeyExchange(r0)
            return r0
        L_0x0026:
            r0 = 15
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createPSKKeyExchange(r0)
            return r0
        L_0x002d:
            r0 = 14
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createPSKKeyExchange(r0)
            return r0
        L_0x0034:
            r0 = 13
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createPSKKeyExchange(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.PSKTlsClient.getKeyExchange():org.spongycastle.crypto.tls.TlsKeyExchange");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009d, code lost:
        return r7.cipherFactory.createCipher(r7.context, 0, 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a6, code lost:
        return r7.cipherFactory.createCipher(r7.context, 0, 3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00af, code lost:
        return r7.cipherFactory.createCipher(r7.context, 9, 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b8, code lost:
        return r7.cipherFactory.createCipher(r7.context, 8, 3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d7, code lost:
        return r7.cipherFactory.createCipher(r7.context, 9, 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e0, code lost:
        return r7.cipherFactory.createCipher(r7.context, 8, 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ea, code lost:
        return r7.cipherFactory.createCipher(r7.context, 7, 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f3, code lost:
        return r7.cipherFactory.createCipher(r7.context, 2, 2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.spongycastle.crypto.tls.TlsCipher getCipher() throws java.io.IOException {
        /*
            r7 = this;
            int r0 = r7.selectedCipherSuite
            r1 = 2
            r2 = 0
            switch(r0) {
                case 44: goto L_0x00f4;
                case 45: goto L_0x00f4;
                case 46: goto L_0x00f4;
                default: goto L_0x0007;
            }
        L_0x0007:
            r3 = 9
            r4 = 8
            switch(r0) {
                case 138: goto L_0x00eb;
                case 139: goto L_0x00e1;
                case 140: goto L_0x00d8;
                case 141: goto L_0x00cf;
                case 142: goto L_0x00eb;
                case 143: goto L_0x00e1;
                case 144: goto L_0x00d8;
                case 145: goto L_0x00cf;
                case 146: goto L_0x00eb;
                case 147: goto L_0x00e1;
                case 148: goto L_0x00d8;
                case 149: goto L_0x00cf;
                default: goto L_0x000e;
            }
        L_0x000e:
            r5 = 4
            r6 = 3
            switch(r0) {
                case 168: goto L_0x00c4;
                case 169: goto L_0x00b9;
                case 170: goto L_0x00c4;
                case 171: goto L_0x00b9;
                case 172: goto L_0x00c4;
                case 173: goto L_0x00b9;
                case 174: goto L_0x00b0;
                case 175: goto L_0x00a7;
                case 176: goto L_0x009e;
                case 177: goto L_0x0095;
                case 178: goto L_0x00b0;
                case 179: goto L_0x00a7;
                case 180: goto L_0x009e;
                case 181: goto L_0x0095;
                case 182: goto L_0x00b0;
                case 183: goto L_0x00a7;
                case 184: goto L_0x009e;
                case 185: goto L_0x0095;
                default: goto L_0x0013;
            }
        L_0x0013:
            switch(r0) {
                case 49203: goto L_0x00eb;
                case 49204: goto L_0x00e1;
                case 49205: goto L_0x00d8;
                case 49206: goto L_0x00cf;
                case 49207: goto L_0x00b0;
                case 49208: goto L_0x00a7;
                case 49209: goto L_0x00f4;
                case 49210: goto L_0x009e;
                case 49211: goto L_0x0095;
                default: goto L_0x0016;
            }
        L_0x0016:
            switch(r0) {
                case 49294: goto L_0x008a;
                case 49295: goto L_0x007f;
                case 49296: goto L_0x008a;
                case 49297: goto L_0x007f;
                case 49298: goto L_0x008a;
                case 49299: goto L_0x007f;
                case 49300: goto L_0x0074;
                case 49301: goto L_0x0069;
                case 49302: goto L_0x0074;
                case 49303: goto L_0x0069;
                case 49304: goto L_0x0074;
                case 49305: goto L_0x0069;
                case 49306: goto L_0x0074;
                case 49307: goto L_0x0069;
                default: goto L_0x0019;
            }
        L_0x0019:
            switch(r0) {
                case 49316: goto L_0x005e;
                case 49317: goto L_0x0053;
                case 49318: goto L_0x005e;
                case 49319: goto L_0x0053;
                case 49320: goto L_0x0048;
                case 49321: goto L_0x003d;
                case 49322: goto L_0x0048;
                case 49323: goto L_0x003d;
                default: goto L_0x001c;
            }
        L_0x001c:
            switch(r0) {
                case 58390: goto L_0x0032;
                case 58391: goto L_0x0027;
                case 58392: goto L_0x0032;
                case 58393: goto L_0x0027;
                case 58394: goto L_0x0032;
                case 58395: goto L_0x0027;
                case 58396: goto L_0x0032;
                case 58397: goto L_0x0027;
                default: goto L_0x001f;
            }
        L_0x001f:
            org.spongycastle.crypto.tls.TlsFatalAlert r0 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r1 = 80
            r0.<init>(r1)
            throw r0
        L_0x0027:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r2 = r7.context
            r3 = 101(0x65, float:1.42E-43)
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r2, r3, r1)
            return r0
        L_0x0032:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r2 = r7.context
            r3 = 100
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r2, r3, r1)
            return r0
        L_0x003d:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            r3 = 18
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x0048:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            r3 = 16
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x0053:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            r3 = 17
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x005e:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            r3 = 15
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x0069:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            r2 = 13
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r2, r5)
            return r0
        L_0x0074:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            r2 = 12
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r2, r6)
            return r0
        L_0x007f:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            r3 = 20
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x008a:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            r3 = 19
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x0095:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r2, r5)
            return r0
        L_0x009e:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r2, r6)
            return r0
        L_0x00a7:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r5)
            return r0
        L_0x00b0:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r4, r6)
            return r0
        L_0x00b9:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            r3 = 11
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x00c4:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r7.context
            r3 = 10
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x00cf:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r2 = r7.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r2, r3, r1)
            return r0
        L_0x00d8:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r2 = r7.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r2, r4, r1)
            return r0
        L_0x00e1:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r2 = r7.context
            r3 = 7
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r2, r3, r1)
            return r0
        L_0x00eb:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r2 = r7.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r2, r1, r1)
            return r0
        L_0x00f4:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r7.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r3 = r7.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r3, r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.PSKTlsClient.getCipher():org.spongycastle.crypto.tls.TlsCipher");
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createPSKKeyExchange(int i) {
        TlsPSKKeyExchange tlsPSKKeyExchange = new TlsPSKKeyExchange(i, this.supportedSignatureAlgorithms, this.pskIdentity, null, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
        return tlsPSKKeyExchange;
    }
}
