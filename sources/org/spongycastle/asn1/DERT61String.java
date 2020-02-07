package org.spongycastle.asn1;

import java.io.IOException;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class DERT61String extends ASN1Primitive implements ASN1String {
    private byte[] string;

    /* access modifiers changed from: 0000 */
    public boolean isConstructed() {
        return false;
    }

    public static DERT61String getInstance(Object obj) {
        if (obj == null || (obj instanceof DERT61String)) {
            return (DERT61String) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERT61String) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("encoding error in getInstance: ");
                sb.append(e.toString());
                throw new IllegalArgumentException(sb.toString());
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("illegal object in getInstance: ");
            sb2.append(obj.getClass().getName());
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public static DERT61String getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof DERT61String)) {
            return getInstance(object);
        }
        return new DERT61String(ASN1OctetString.getInstance(object).getOctets());
    }

    public DERT61String(byte[] bArr) {
        this.string = bArr;
    }

    public DERT61String(String str) {
        this(Strings.toByteArray(str));
    }

    public String getString() {
        return Strings.fromByteArray(this.string);
    }

    public String toString() {
        return getString();
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.string.length) + 1 + this.string.length;
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(20, this.string);
    }

    public byte[] getOctets() {
        return Arrays.clone(this.string);
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERT61String)) {
            return false;
        }
        return Arrays.areEqual(this.string, ((DERT61String) aSN1Primitive).string);
    }

    public int hashCode() {
        return Arrays.hashCode(this.string);
    }
}
