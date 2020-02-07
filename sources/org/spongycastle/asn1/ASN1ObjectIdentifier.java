package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import org.spongycastle.util.Arrays;

public class ASN1ObjectIdentifier extends ASN1Primitive {
    private static final long LONG_LIMIT = 72057594037927808L;
    private static ASN1ObjectIdentifier[][] cache = new ASN1ObjectIdentifier[256][];
    private byte[] body;
    String identifier;

    /* access modifiers changed from: 0000 */
    public boolean isConstructed() {
        return false;
    }

    public static ASN1ObjectIdentifier getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1ObjectIdentifier)) {
            return (ASN1ObjectIdentifier) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Encodable aSN1Encodable = (ASN1Encodable) obj;
            if (aSN1Encodable.toASN1Primitive() instanceof ASN1ObjectIdentifier) {
                return (ASN1ObjectIdentifier) aSN1Encodable.toASN1Primitive();
            }
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1ObjectIdentifier) fromByteArray((byte[]) obj);
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("failed to construct object identifier from byte[]: ");
                sb.append(e.getMessage());
                throw new IllegalArgumentException(sb.toString());
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("illegal object in getInstance: ");
            sb2.append(obj.getClass().getName());
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public static ASN1ObjectIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof ASN1ObjectIdentifier)) {
            return getInstance(object);
        }
        return fromOctetString(ASN1OctetString.getInstance(aSN1TaggedObject.getObject()).getOctets());
    }

    ASN1ObjectIdentifier(byte[] bArr) {
        byte[] bArr2 = bArr;
        StringBuffer stringBuffer = new StringBuffer();
        long j = 0;
        BigInteger bigInteger = null;
        boolean z = true;
        for (int i = 0; i != bArr2.length; i++) {
            byte b = bArr2[i] & 255;
            if (j <= LONG_LIMIT) {
                long j2 = j + ((long) (b & Byte.MAX_VALUE));
                if ((b & 128) == 0) {
                    if (z) {
                        if (j2 < 40) {
                            stringBuffer.append('0');
                        } else if (j2 < 80) {
                            stringBuffer.append('1');
                            j2 -= 40;
                        } else {
                            stringBuffer.append('2');
                            j2 -= 80;
                        }
                        z = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(j2);
                    j = 0;
                } else {
                    j = j2 << 7;
                }
            } else {
                if (bigInteger == null) {
                    bigInteger = BigInteger.valueOf(j);
                }
                BigInteger or = bigInteger.or(BigInteger.valueOf((long) (b & Byte.MAX_VALUE)));
                if ((b & 128) == 0) {
                    if (z) {
                        stringBuffer.append('2');
                        or = or.subtract(BigInteger.valueOf(80));
                        z = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(or);
                    j = 0;
                    bigInteger = null;
                } else {
                    bigInteger = or.shiftLeft(7);
                }
            }
        }
        this.identifier = stringBuffer.toString();
        this.body = Arrays.clone(bArr);
    }

    public ASN1ObjectIdentifier(String str) {
        if (str == null) {
            throw new IllegalArgumentException("'identifier' cannot be null");
        } else if (isValidIdentifier(str)) {
            this.identifier = str;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("string ");
            sb.append(str);
            sb.append(" not an OID");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    ASN1ObjectIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (isValidBranchID(str, 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append(aSN1ObjectIdentifier.getId());
            sb.append(".");
            sb.append(str);
            this.identifier = sb.toString();
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("string ");
        sb2.append(str);
        sb2.append(" not a valid OID branch");
        throw new IllegalArgumentException(sb2.toString());
    }

    public String getId() {
        return this.identifier;
    }

    public ASN1ObjectIdentifier branch(String str) {
        return new ASN1ObjectIdentifier(this, str);
    }

    /* renamed from: on */
    public boolean mo48413on(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String id = getId();
        String id2 = aSN1ObjectIdentifier.getId();
        return id.length() > id2.length() && id.charAt(id2.length()) == '.' && id.startsWith(id2);
    }

    private void writeField(ByteArrayOutputStream byteArrayOutputStream, long j) {
        byte[] bArr = new byte[9];
        int i = 8;
        bArr[8] = (byte) (((int) j) & 127);
        while (j >= 128) {
            j >>= 7;
            i--;
            bArr[i] = (byte) ((((int) j) & 127) | 128);
        }
        byteArrayOutputStream.write(bArr, i, 9 - i);
    }

    private void writeField(ByteArrayOutputStream byteArrayOutputStream, BigInteger bigInteger) {
        int bitLength = (bigInteger.bitLength() + 6) / 7;
        if (bitLength == 0) {
            byteArrayOutputStream.write(0);
            return;
        }
        byte[] bArr = new byte[bitLength];
        int i = bitLength - 1;
        BigInteger bigInteger2 = bigInteger;
        for (int i2 = i; i2 >= 0; i2--) {
            bArr[i2] = (byte) ((bigInteger2.intValue() & 127) | 128);
            bigInteger2 = bigInteger2.shiftRight(7);
        }
        bArr[i] = (byte) (bArr[i] & Byte.MAX_VALUE);
        byteArrayOutputStream.write(bArr, 0, bArr.length);
    }

    private void doOutput(ByteArrayOutputStream byteArrayOutputStream) {
        OIDTokenizer oIDTokenizer = new OIDTokenizer(this.identifier);
        int parseInt = Integer.parseInt(oIDTokenizer.nextToken()) * 40;
        String nextToken = oIDTokenizer.nextToken();
        if (nextToken.length() <= 18) {
            writeField(byteArrayOutputStream, ((long) parseInt) + Long.parseLong(nextToken));
        } else {
            writeField(byteArrayOutputStream, new BigInteger(nextToken).add(BigInteger.valueOf((long) parseInt)));
        }
        while (oIDTokenizer.hasMoreTokens()) {
            String nextToken2 = oIDTokenizer.nextToken();
            if (nextToken2.length() <= 18) {
                writeField(byteArrayOutputStream, Long.parseLong(nextToken2));
            } else {
                writeField(byteArrayOutputStream, new BigInteger(nextToken2));
            }
        }
    }

    /* access modifiers changed from: protected */
    public synchronized byte[] getBody() {
        if (this.body == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            doOutput(byteArrayOutputStream);
            this.body = byteArrayOutputStream.toByteArray();
        }
        return this.body;
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() throws IOException {
        int length = getBody().length;
        return StreamUtil.calculateBodyLength(length) + 1 + length;
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        byte[] body2 = getBody();
        aSN1OutputStream.write(6);
        aSN1OutputStream.writeLength(body2.length);
        aSN1OutputStream.write(body2);
    }

    public int hashCode() {
        return this.identifier.hashCode();
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1ObjectIdentifier)) {
            return false;
        }
        return this.identifier.equals(((ASN1ObjectIdentifier) aSN1Primitive).identifier);
    }

    public String toString() {
        return getId();
    }

    private static boolean isValidBranchID(String str, int i) {
        boolean z;
        char charAt;
        int length = str.length();
        do {
            z = false;
            while (true) {
                length--;
                if (length < i) {
                    return z;
                }
                charAt = str.charAt(length);
                if ('0' <= charAt && charAt <= '9') {
                    z = true;
                }
            }
            if (charAt != '.') {
                break;
            }
        } while (z);
        return false;
    }

    private static boolean isValidIdentifier(String str) {
        if (str.length() >= 3 && str.charAt(1) == '.') {
            char charAt = str.charAt(0);
            if (charAt >= '0' && charAt <= '2') {
                return isValidBranchID(str, 2);
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0087, code lost:
        if (org.spongycastle.util.Arrays.areEqual(r6, r1.getBody()) == false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0089, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x008f, code lost:
        return new org.spongycastle.asn1.ASN1ObjectIdentifier(r6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static org.spongycastle.asn1.ASN1ObjectIdentifier fromOctetString(byte[] r6) {
        /*
            int r0 = r6.length
            r1 = 3
            if (r0 >= r1) goto L_0x000a
            org.spongycastle.asn1.ASN1ObjectIdentifier r0 = new org.spongycastle.asn1.ASN1ObjectIdentifier
            r0.<init>(r6)
            return r0
        L_0x000a:
            int r0 = r6.length
            int r0 = r0 + -2
            byte r0 = r6[r0]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r1 = r6.length
            int r1 = r1 + -1
            byte r1 = r6[r1]
            r1 = r1 & 127(0x7f, float:1.78E-43)
            org.spongycastle.asn1.ASN1ObjectIdentifier[][] r2 = cache
            monitor-enter(r2)
            org.spongycastle.asn1.ASN1ObjectIdentifier[][] r3 = cache     // Catch:{ all -> 0x0090 }
            r3 = r3[r0]     // Catch:{ all -> 0x0090 }
            r4 = 128(0x80, float:1.794E-43)
            if (r3 != 0) goto L_0x002a
            org.spongycastle.asn1.ASN1ObjectIdentifier[][] r3 = cache     // Catch:{ all -> 0x0090 }
            org.spongycastle.asn1.ASN1ObjectIdentifier[] r5 = new org.spongycastle.asn1.ASN1ObjectIdentifier[r4]     // Catch:{ all -> 0x0090 }
            r3[r0] = r5     // Catch:{ all -> 0x0090 }
            r3 = r5
        L_0x002a:
            r5 = r3[r1]     // Catch:{ all -> 0x0090 }
            if (r5 != 0) goto L_0x0037
            org.spongycastle.asn1.ASN1ObjectIdentifier r0 = new org.spongycastle.asn1.ASN1ObjectIdentifier     // Catch:{ all -> 0x0090 }
            r0.<init>(r6)     // Catch:{ all -> 0x0090 }
            r3[r1] = r0     // Catch:{ all -> 0x0090 }
            monitor-exit(r2)     // Catch:{ all -> 0x0090 }
            return r0
        L_0x0037:
            byte[] r3 = r5.getBody()     // Catch:{ all -> 0x0090 }
            boolean r3 = org.spongycastle.util.Arrays.areEqual(r6, r3)     // Catch:{ all -> 0x0090 }
            if (r3 == 0) goto L_0x0043
            monitor-exit(r2)     // Catch:{ all -> 0x0090 }
            return r5
        L_0x0043:
            int r0 = r0 + 1
            r0 = r0 & 255(0xff, float:3.57E-43)
            org.spongycastle.asn1.ASN1ObjectIdentifier[][] r3 = cache     // Catch:{ all -> 0x0090 }
            r3 = r3[r0]     // Catch:{ all -> 0x0090 }
            if (r3 != 0) goto L_0x0054
            org.spongycastle.asn1.ASN1ObjectIdentifier[][] r3 = cache     // Catch:{ all -> 0x0090 }
            org.spongycastle.asn1.ASN1ObjectIdentifier[] r4 = new org.spongycastle.asn1.ASN1ObjectIdentifier[r4]     // Catch:{ all -> 0x0090 }
            r3[r0] = r4     // Catch:{ all -> 0x0090 }
            r3 = r4
        L_0x0054:
            r0 = r3[r1]     // Catch:{ all -> 0x0090 }
            if (r0 != 0) goto L_0x0061
            org.spongycastle.asn1.ASN1ObjectIdentifier r0 = new org.spongycastle.asn1.ASN1ObjectIdentifier     // Catch:{ all -> 0x0090 }
            r0.<init>(r6)     // Catch:{ all -> 0x0090 }
            r3[r1] = r0     // Catch:{ all -> 0x0090 }
            monitor-exit(r2)     // Catch:{ all -> 0x0090 }
            return r0
        L_0x0061:
            byte[] r4 = r0.getBody()     // Catch:{ all -> 0x0090 }
            boolean r4 = org.spongycastle.util.Arrays.areEqual(r6, r4)     // Catch:{ all -> 0x0090 }
            if (r4 == 0) goto L_0x006d
            monitor-exit(r2)     // Catch:{ all -> 0x0090 }
            return r0
        L_0x006d:
            int r1 = r1 + 1
            r0 = r1 & 127(0x7f, float:1.78E-43)
            r1 = r3[r0]     // Catch:{ all -> 0x0090 }
            if (r1 != 0) goto L_0x007e
            org.spongycastle.asn1.ASN1ObjectIdentifier r1 = new org.spongycastle.asn1.ASN1ObjectIdentifier     // Catch:{ all -> 0x0090 }
            r1.<init>(r6)     // Catch:{ all -> 0x0090 }
            r3[r0] = r1     // Catch:{ all -> 0x0090 }
            monitor-exit(r2)     // Catch:{ all -> 0x0090 }
            return r1
        L_0x007e:
            monitor-exit(r2)     // Catch:{ all -> 0x0090 }
            byte[] r0 = r1.getBody()
            boolean r0 = org.spongycastle.util.Arrays.areEqual(r6, r0)
            if (r0 == 0) goto L_0x008a
            return r1
        L_0x008a:
            org.spongycastle.asn1.ASN1ObjectIdentifier r0 = new org.spongycastle.asn1.ASN1ObjectIdentifier
            r0.<init>(r6)
            return r0
        L_0x0090:
            r6 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0090 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.ASN1ObjectIdentifier.fromOctetString(byte[]):org.spongycastle.asn1.ASN1ObjectIdentifier");
    }
}
