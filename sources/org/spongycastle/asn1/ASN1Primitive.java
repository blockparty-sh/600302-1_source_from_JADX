package org.spongycastle.asn1;

import java.io.IOException;

public abstract class ASN1Primitive extends ASN1Object {
    /* access modifiers changed from: 0000 */
    public abstract boolean asn1Equals(ASN1Primitive aSN1Primitive);

    /* access modifiers changed from: 0000 */
    public abstract void encode(ASN1OutputStream aSN1OutputStream) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract int encodedLength() throws IOException;

    public abstract int hashCode();

    /* access modifiers changed from: 0000 */
    public abstract boolean isConstructed();

    public ASN1Primitive toASN1Primitive() {
        return this;
    }

    /* access modifiers changed from: 0000 */
    public ASN1Primitive toDERObject() {
        return this;
    }

    /* access modifiers changed from: 0000 */
    public ASN1Primitive toDLObject() {
        return this;
    }

    ASN1Primitive() {
    }

    public static ASN1Primitive fromByteArray(byte[] bArr) throws IOException {
        try {
            return new ASN1InputStream(bArr).readObject();
        } catch (ClassCastException unused) {
            throw new IOException("cannot recognise object in stream");
        }
    }

    public final boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ASN1Encodable) || !asn1Equals(((ASN1Encodable) obj).toASN1Primitive())) {
            z = false;
        }
        return z;
    }
}
