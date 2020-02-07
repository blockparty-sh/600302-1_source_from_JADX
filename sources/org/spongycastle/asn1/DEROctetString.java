package org.spongycastle.asn1;

import java.io.IOException;

public class DEROctetString extends ASN1OctetString {
    /* access modifiers changed from: 0000 */
    public boolean isConstructed() {
        return false;
    }

    public DEROctetString(byte[] bArr) {
        super(bArr);
    }

    public DEROctetString(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER));
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.string.length) + 1 + this.string.length;
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(4, this.string);
    }

    static void encode(DEROutputStream dEROutputStream, byte[] bArr) throws IOException {
        dEROutputStream.writeEncoded(4, bArr);
    }
}
