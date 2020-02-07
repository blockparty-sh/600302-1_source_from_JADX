package org.spongycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

class LazyEncodedSequence extends ASN1Sequence {
    private byte[] encoded;

    LazyEncodedSequence(byte[] bArr) throws IOException {
        this.encoded = bArr;
    }

    private void parse() {
        LazyConstructionEnumeration lazyConstructionEnumeration = new LazyConstructionEnumeration(this.encoded);
        while (lazyConstructionEnumeration.hasMoreElements()) {
            this.seq.addElement(lazyConstructionEnumeration.nextElement());
        }
        this.encoded = null;
    }

    public synchronized ASN1Encodable getObjectAt(int i) {
        if (this.encoded != null) {
            parse();
        }
        return super.getObjectAt(i);
    }

    public synchronized Enumeration getObjects() {
        if (this.encoded == null) {
            return super.getObjects();
        }
        return new LazyConstructionEnumeration(this.encoded);
    }

    public synchronized int size() {
        if (this.encoded != null) {
            parse();
        }
        return super.size();
    }

    /* access modifiers changed from: 0000 */
    public ASN1Primitive toDERObject() {
        if (this.encoded != null) {
            parse();
        }
        return super.toDERObject();
    }

    /* access modifiers changed from: 0000 */
    public ASN1Primitive toDLObject() {
        if (this.encoded != null) {
            parse();
        }
        return super.toDLObject();
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() throws IOException {
        byte[] bArr = this.encoded;
        if (bArr != null) {
            return StreamUtil.calculateBodyLength(bArr.length) + 1 + this.encoded.length;
        }
        return super.toDLObject().encodedLength();
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        byte[] bArr = this.encoded;
        if (bArr != null) {
            aSN1OutputStream.writeEncoded(48, bArr);
        } else {
            super.toDLObject().encode(aSN1OutputStream);
        }
    }
}
