package org.spongycastle.crypto.tls;

public abstract class DefaultTlsClient extends AbstractTlsClient {
    public DefaultTlsClient() {
    }

    public DefaultTlsClient(TlsCipherFactory tlsCipherFactory) {
        super(tlsCipherFactory);
    }

    public int[] getCipherSuites() {
        return new int[]{CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, 156, 60, 47};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004e, code lost:
        return createDHEKeyExchange(5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0054, code lost:
        return createDHEKeyExchange(3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005b, code lost:
        return createDHKeyExchange(9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0061, code lost:
        return createDHKeyExchange(7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.spongycastle.crypto.tls.TlsKeyExchange getKeyExchange() throws java.io.IOException {
        /*
            r2 = this;
            int r0 = r2.selectedCipherSuite
            r1 = 1
            if (r0 == r1) goto L_0x0062
            r1 = 2
            if (r0 == r1) goto L_0x0062
            r1 = 4
            if (r0 == r1) goto L_0x0062
            r1 = 5
            if (r0 == r1) goto L_0x0062
            switch(r0) {
                case 10: goto L_0x0062;
                case 13: goto L_0x005c;
                case 16: goto L_0x0055;
                case 19: goto L_0x004f;
                case 22: goto L_0x004a;
                case 59: goto L_0x0062;
                case 60: goto L_0x0062;
                case 61: goto L_0x0062;
                case 62: goto L_0x005c;
                case 63: goto L_0x0055;
                case 64: goto L_0x004f;
                case 65: goto L_0x0062;
                case 66: goto L_0x005c;
                case 67: goto L_0x0055;
                case 68: goto L_0x004f;
                case 69: goto L_0x004a;
                case 192: goto L_0x0062;
                case 193: goto L_0x005c;
                case 194: goto L_0x0055;
                case 195: goto L_0x004f;
                case 196: goto L_0x004a;
                case 49153: goto L_0x0043;
                case 49154: goto L_0x0043;
                case 49155: goto L_0x0043;
                case 49156: goto L_0x0043;
                case 49157: goto L_0x0043;
                case 49158: goto L_0x003c;
                case 49159: goto L_0x003c;
                case 49160: goto L_0x003c;
                case 49161: goto L_0x003c;
                case 49162: goto L_0x003c;
                case 49163: goto L_0x0035;
                case 49164: goto L_0x0035;
                case 49165: goto L_0x0035;
                case 49166: goto L_0x0035;
                case 49167: goto L_0x0035;
                case 49168: goto L_0x002e;
                case 49169: goto L_0x002e;
                case 49170: goto L_0x002e;
                case 49171: goto L_0x002e;
                case 49172: goto L_0x002e;
                case 49187: goto L_0x003c;
                case 49188: goto L_0x003c;
                case 49189: goto L_0x0043;
                case 49190: goto L_0x0043;
                case 49191: goto L_0x002e;
                case 49192: goto L_0x002e;
                case 49193: goto L_0x0035;
                case 49194: goto L_0x0035;
                case 49195: goto L_0x003c;
                case 49196: goto L_0x003c;
                case 49197: goto L_0x0043;
                case 49198: goto L_0x0043;
                case 49199: goto L_0x002e;
                case 49200: goto L_0x002e;
                case 49201: goto L_0x0035;
                case 49202: goto L_0x0035;
                case 49266: goto L_0x003c;
                case 49267: goto L_0x003c;
                case 49268: goto L_0x0043;
                case 49269: goto L_0x0043;
                case 49270: goto L_0x002e;
                case 49271: goto L_0x002e;
                case 49272: goto L_0x0035;
                case 49273: goto L_0x0035;
                case 49274: goto L_0x0062;
                case 49275: goto L_0x0062;
                case 49276: goto L_0x004a;
                case 49277: goto L_0x004a;
                case 49278: goto L_0x0055;
                case 49279: goto L_0x0055;
                case 49280: goto L_0x004f;
                case 49281: goto L_0x004f;
                case 49282: goto L_0x005c;
                case 49283: goto L_0x005c;
                case 49286: goto L_0x003c;
                case 49287: goto L_0x003c;
                case 49288: goto L_0x0043;
                case 49289: goto L_0x0043;
                case 49290: goto L_0x002e;
                case 49291: goto L_0x002e;
                case 49292: goto L_0x0035;
                case 49293: goto L_0x0035;
                case 49308: goto L_0x0062;
                case 49309: goto L_0x0062;
                case 49310: goto L_0x004a;
                case 49311: goto L_0x004a;
                case 49312: goto L_0x0062;
                case 49313: goto L_0x0062;
                case 49314: goto L_0x004a;
                case 49315: goto L_0x004a;
                case 52243: goto L_0x002e;
                case 52244: goto L_0x003c;
                case 52245: goto L_0x004a;
                case 58384: goto L_0x0062;
                case 58385: goto L_0x0062;
                case 58386: goto L_0x002e;
                case 58387: goto L_0x002e;
                case 58388: goto L_0x003c;
                case 58389: goto L_0x003c;
                case 58398: goto L_0x004a;
                case 58399: goto L_0x004a;
                default: goto L_0x0011;
            }
        L_0x0011:
            switch(r0) {
                case 47: goto L_0x0062;
                case 48: goto L_0x005c;
                case 49: goto L_0x0055;
                case 50: goto L_0x004f;
                case 51: goto L_0x004a;
                default: goto L_0x0014;
            }
        L_0x0014:
            switch(r0) {
                case 53: goto L_0x0062;
                case 54: goto L_0x005c;
                case 55: goto L_0x0055;
                case 56: goto L_0x004f;
                case 57: goto L_0x004a;
                default: goto L_0x0017;
            }
        L_0x0017:
            switch(r0) {
                case 103: goto L_0x004a;
                case 104: goto L_0x005c;
                case 105: goto L_0x0055;
                case 106: goto L_0x004f;
                case 107: goto L_0x004a;
                default: goto L_0x001a;
            }
        L_0x001a:
            switch(r0) {
                case 132: goto L_0x0062;
                case 133: goto L_0x005c;
                case 134: goto L_0x0055;
                case 135: goto L_0x004f;
                case 136: goto L_0x004a;
                default: goto L_0x001d;
            }
        L_0x001d:
            switch(r0) {
                case 150: goto L_0x0062;
                case 151: goto L_0x005c;
                case 152: goto L_0x0055;
                case 153: goto L_0x004f;
                case 154: goto L_0x004a;
                default: goto L_0x0020;
            }
        L_0x0020:
            switch(r0) {
                case 156: goto L_0x0062;
                case 157: goto L_0x0062;
                case 158: goto L_0x004a;
                case 159: goto L_0x004a;
                case 160: goto L_0x0055;
                case 161: goto L_0x0055;
                case 162: goto L_0x004f;
                case 163: goto L_0x004f;
                case 164: goto L_0x005c;
                case 165: goto L_0x005c;
                default: goto L_0x0023;
            }
        L_0x0023:
            switch(r0) {
                case 186: goto L_0x0062;
                case 187: goto L_0x005c;
                case 188: goto L_0x0055;
                case 189: goto L_0x004f;
                case 190: goto L_0x004a;
                default: goto L_0x0026;
            }
        L_0x0026:
            org.spongycastle.crypto.tls.TlsFatalAlert r0 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r1 = 80
            r0.<init>(r1)
            throw r0
        L_0x002e:
            r0 = 19
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createECDHEKeyExchange(r0)
            return r0
        L_0x0035:
            r0 = 18
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createECDHKeyExchange(r0)
            return r0
        L_0x003c:
            r0 = 17
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createECDHEKeyExchange(r0)
            return r0
        L_0x0043:
            r0 = 16
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createECDHKeyExchange(r0)
            return r0
        L_0x004a:
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createDHEKeyExchange(r1)
            return r0
        L_0x004f:
            r0 = 3
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createDHEKeyExchange(r0)
            return r0
        L_0x0055:
            r0 = 9
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createDHKeyExchange(r0)
            return r0
        L_0x005c:
            r0 = 7
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createDHKeyExchange(r0)
            return r0
        L_0x0062:
            org.spongycastle.crypto.tls.TlsKeyExchange r0 = r2.createRSAKeyExchange()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.DefaultTlsClient.getKeyExchange():org.spongycastle.crypto.tls.TlsKeyExchange");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c0, code lost:
        return r9.cipherFactory.createCipher(r9.context, 12, 3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00cb, code lost:
        return r9.cipherFactory.createCipher(r9.context, 11, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d6, code lost:
        return r9.cipherFactory.createCipher(r9.context, 10, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e8, code lost:
        return r9.cipherFactory.createCipher(r9.context, 9, 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f1, code lost:
        return r9.cipherFactory.createCipher(r9.context, 8, 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x010c, code lost:
        return r9.cipherFactory.createCipher(r9.context, 9, 3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0115, code lost:
        return r9.cipherFactory.createCipher(r9.context, 8, 3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.spongycastle.crypto.tls.TlsCipher getCipher() throws java.io.IOException {
        /*
            r9 = this;
            int r0 = r9.selectedCipherSuite
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L_0x0144
            r3 = 2
            if (r0 == r3) goto L_0x013b
            r4 = 4
            if (r0 == r4) goto L_0x0132
            r1 = 5
            if (r0 == r1) goto L_0x0129
            r1 = 12
            r5 = 8
            r6 = 13
            r7 = 9
            r8 = 3
            switch(r0) {
                case 10: goto L_0x011f;
                case 13: goto L_0x011f;
                case 16: goto L_0x011f;
                case 19: goto L_0x011f;
                case 22: goto L_0x011f;
                case 59: goto L_0x0116;
                case 60: goto L_0x010d;
                case 61: goto L_0x0104;
                case 62: goto L_0x010d;
                case 63: goto L_0x010d;
                case 64: goto L_0x010d;
                case 65: goto L_0x00fb;
                case 66: goto L_0x00fb;
                case 67: goto L_0x00fb;
                case 68: goto L_0x00fb;
                case 69: goto L_0x00fb;
                case 192: goto L_0x00f2;
                case 193: goto L_0x00f2;
                case 194: goto L_0x00f2;
                case 195: goto L_0x00f2;
                case 196: goto L_0x00f2;
                case 49153: goto L_0x013b;
                case 49154: goto L_0x0129;
                case 49155: goto L_0x011f;
                case 49156: goto L_0x00e9;
                case 49157: goto L_0x00e0;
                case 49158: goto L_0x013b;
                case 49159: goto L_0x0129;
                case 49160: goto L_0x011f;
                case 49161: goto L_0x00e9;
                case 49162: goto L_0x00e0;
                case 49163: goto L_0x013b;
                case 49164: goto L_0x0129;
                case 49165: goto L_0x011f;
                case 49166: goto L_0x00e9;
                case 49167: goto L_0x00e0;
                case 49168: goto L_0x013b;
                case 49169: goto L_0x0129;
                case 49170: goto L_0x011f;
                case 49171: goto L_0x00e9;
                case 49172: goto L_0x00e0;
                case 49187: goto L_0x010d;
                case 49188: goto L_0x00d7;
                case 49189: goto L_0x010d;
                case 49190: goto L_0x00d7;
                case 49191: goto L_0x010d;
                case 49192: goto L_0x00d7;
                case 49193: goto L_0x010d;
                case 49194: goto L_0x00d7;
                case 49195: goto L_0x00cc;
                case 49196: goto L_0x00c1;
                case 49197: goto L_0x00cc;
                case 49198: goto L_0x00c1;
                case 49199: goto L_0x00cc;
                case 49200: goto L_0x00c1;
                case 49201: goto L_0x00cc;
                case 49202: goto L_0x00c1;
                case 49266: goto L_0x00b8;
                case 49267: goto L_0x00af;
                case 49268: goto L_0x00b8;
                case 49269: goto L_0x00af;
                case 49270: goto L_0x00b8;
                case 49271: goto L_0x00af;
                case 49272: goto L_0x00b8;
                case 49273: goto L_0x00af;
                case 49274: goto L_0x00a4;
                case 49275: goto L_0x0099;
                case 49276: goto L_0x00a4;
                case 49277: goto L_0x0099;
                case 49278: goto L_0x00a4;
                case 49279: goto L_0x0099;
                case 49280: goto L_0x00a4;
                case 49281: goto L_0x0099;
                case 49282: goto L_0x00a4;
                case 49283: goto L_0x0099;
                case 49286: goto L_0x00a4;
                case 49287: goto L_0x0099;
                case 49288: goto L_0x00a4;
                case 49289: goto L_0x0099;
                case 49290: goto L_0x00a4;
                case 49291: goto L_0x0099;
                case 49292: goto L_0x00a4;
                case 49293: goto L_0x0099;
                case 49308: goto L_0x008e;
                case 49309: goto L_0x0083;
                case 49310: goto L_0x008e;
                case 49311: goto L_0x0083;
                case 49312: goto L_0x0078;
                case 49313: goto L_0x006d;
                case 49314: goto L_0x0078;
                case 49315: goto L_0x006d;
                case 52243: goto L_0x0062;
                case 52244: goto L_0x0062;
                case 52245: goto L_0x0062;
                case 58384: goto L_0x0057;
                case 58385: goto L_0x004c;
                case 58386: goto L_0x0057;
                case 58387: goto L_0x004c;
                case 58388: goto L_0x0057;
                case 58389: goto L_0x004c;
                case 58398: goto L_0x0057;
                case 58399: goto L_0x004c;
                default: goto L_0x001b;
            }
        L_0x001b:
            switch(r0) {
                case 47: goto L_0x00e9;
                case 48: goto L_0x00e9;
                case 49: goto L_0x00e9;
                case 50: goto L_0x00e9;
                case 51: goto L_0x00e9;
                default: goto L_0x001e;
            }
        L_0x001e:
            switch(r0) {
                case 53: goto L_0x00e0;
                case 54: goto L_0x00e0;
                case 55: goto L_0x00e0;
                case 56: goto L_0x00e0;
                case 57: goto L_0x00e0;
                default: goto L_0x0021;
            }
        L_0x0021:
            switch(r0) {
                case 103: goto L_0x010d;
                case 104: goto L_0x0104;
                case 105: goto L_0x0104;
                case 106: goto L_0x0104;
                case 107: goto L_0x0104;
                default: goto L_0x0024;
            }
        L_0x0024:
            switch(r0) {
                case 132: goto L_0x0043;
                case 133: goto L_0x0043;
                case 134: goto L_0x0043;
                case 135: goto L_0x0043;
                case 136: goto L_0x0043;
                default: goto L_0x0027;
            }
        L_0x0027:
            switch(r0) {
                case 150: goto L_0x0038;
                case 151: goto L_0x0038;
                case 152: goto L_0x0038;
                case 153: goto L_0x0038;
                case 154: goto L_0x0038;
                default: goto L_0x002a;
            }
        L_0x002a:
            switch(r0) {
                case 156: goto L_0x00cc;
                case 157: goto L_0x00c1;
                case 158: goto L_0x00cc;
                case 159: goto L_0x00c1;
                case 160: goto L_0x00cc;
                case 161: goto L_0x00c1;
                case 162: goto L_0x00cc;
                case 163: goto L_0x00c1;
                case 164: goto L_0x00cc;
                case 165: goto L_0x00c1;
                default: goto L_0x002d;
            }
        L_0x002d:
            switch(r0) {
                case 186: goto L_0x00b8;
                case 187: goto L_0x00b8;
                case 188: goto L_0x00b8;
                case 189: goto L_0x00b8;
                case 190: goto L_0x00b8;
                default: goto L_0x0030;
            }
        L_0x0030:
            org.spongycastle.crypto.tls.TlsFatalAlert r0 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r1 = 80
            r0.<init>(r1)
            throw r0
        L_0x0038:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r2 = 14
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r2, r3)
            return r0
        L_0x0043:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r6, r3)
            return r0
        L_0x004c:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r2 = 101(0x65, float:1.42E-43)
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r2, r3)
            return r0
        L_0x0057:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r2 = 100
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r2, r3)
            return r0
        L_0x0062:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r3 = 102(0x66, float:1.43E-43)
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x006d:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r3 = 18
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x0078:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r3 = 16
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x0083:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r3 = 17
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x008e:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r3 = 15
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x0099:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r3 = 20
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x00a4:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r3 = 19
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x00af:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r6, r4)
            return r0
        L_0x00b8:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r2 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r2, r1, r8)
            return r0
        L_0x00c1:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r3 = 11
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x00cc:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r3 = 10
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r2)
            return r0
        L_0x00d7:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r7, r4)
            return r0
        L_0x00e0:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r7, r3)
            return r0
        L_0x00e9:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r5, r3)
            return r0
        L_0x00f2:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r6, r8)
            return r0
        L_0x00fb:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r2 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r2, r1, r3)
            return r0
        L_0x0104:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r7, r8)
            return r0
        L_0x010d:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r5, r8)
            return r0
        L_0x0116:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r2, r8)
            return r0
        L_0x011f:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            r2 = 7
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r2, r3)
            return r0
        L_0x0129:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r3, r3)
            return r0
        L_0x0132:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r2 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r2, r3, r1)
            return r0
        L_0x013b:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r1 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r1, r2, r3)
            return r0
        L_0x0144:
            org.spongycastle.crypto.tls.TlsCipherFactory r0 = r9.cipherFactory
            org.spongycastle.crypto.tls.TlsClientContext r3 = r9.context
            org.spongycastle.crypto.tls.TlsCipher r0 = r0.createCipher(r3, r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.DefaultTlsClient.getCipher():org.spongycastle.crypto.tls.TlsCipher");
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createDHKeyExchange(int i) {
        return new TlsDHKeyExchange(i, this.supportedSignatureAlgorithms, null);
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createDHEKeyExchange(int i) {
        return new TlsDHEKeyExchange(i, this.supportedSignatureAlgorithms, null);
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createECDHKeyExchange(int i) {
        TlsECDHKeyExchange tlsECDHKeyExchange = new TlsECDHKeyExchange(i, this.supportedSignatureAlgorithms, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
        return tlsECDHKeyExchange;
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createECDHEKeyExchange(int i) {
        TlsECDHEKeyExchange tlsECDHEKeyExchange = new TlsECDHEKeyExchange(i, this.supportedSignatureAlgorithms, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
        return tlsECDHEKeyExchange;
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createRSAKeyExchange() {
        return new TlsRSAKeyExchange(this.supportedSignatureAlgorithms);
    }
}
