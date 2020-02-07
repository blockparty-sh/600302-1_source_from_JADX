package org.spongycastle.asn1;

import com.google.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.spongycastle.util.Arrays;

public class DERUniversalString extends ASN1Primitive implements ASN1String {
    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private byte[] string;

    /* access modifiers changed from: 0000 */
    public boolean isConstructed() {
        return false;
    }

    public static DERUniversalString getInstance(Object obj) {
        if (obj == null || (obj instanceof DERUniversalString)) {
            return (DERUniversalString) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERUniversalString) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("encoding error getInstance: ");
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

    public static DERUniversalString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof DERUniversalString)) {
            return getInstance(object);
        }
        return new DERUniversalString(((ASN1OctetString) object).getOctets());
    }

    public DERUniversalString(byte[] bArr) {
        this.string = bArr;
    }

    public String getString() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ASN1OutputStream(byteArrayOutputStream).writeObject(this);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i = 0; i != byteArray.length; i++) {
                stringBuffer.append(table[(byteArray[i] >>> 4) & 15]);
                stringBuffer.append(table[byteArray[i] & Ascii.f528SI]);
            }
            return stringBuffer.toString();
        } catch (IOException unused) {
            throw new RuntimeException("internal error encoding BitString");
        }
    }

    public String toString() {
        return getString();
    }

    public byte[] getOctets() {
        return this.string;
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.string.length) + 1 + this.string.length;
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(28, getOctets());
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERUniversalString)) {
            return false;
        }
        return Arrays.areEqual(this.string, ((DERUniversalString) aSN1Primitive).string);
    }

    public int hashCode() {
        return Arrays.hashCode(this.string);
    }
}
