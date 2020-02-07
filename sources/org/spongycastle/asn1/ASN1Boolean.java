package org.spongycastle.asn1;

import java.io.IOException;
import org.spongycastle.util.Arrays;

public class ASN1Boolean extends ASN1Primitive {
    public static final ASN1Boolean FALSE = new ASN1Boolean(false);
    private static final byte[] FALSE_VALUE = {0};
    public static final ASN1Boolean TRUE = new ASN1Boolean(true);
    private static final byte[] TRUE_VALUE = {-1};
    private byte[] value;

    /* access modifiers changed from: 0000 */
    public int encodedLength() {
        return 3;
    }

    /* access modifiers changed from: 0000 */
    public boolean isConstructed() {
        return false;
    }

    public static ASN1Boolean getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Boolean)) {
            return (ASN1Boolean) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1Boolean) fromByteArray((byte[]) obj);
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("failed to construct boolean from byte[]: ");
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

    public static ASN1Boolean getInstance(boolean z) {
        return z ? TRUE : FALSE;
    }

    public static ASN1Boolean getInstance(int i) {
        return i != 0 ? TRUE : FALSE;
    }

    public static ASN1Boolean getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof ASN1Boolean)) {
            return getInstance((Object) object);
        }
        return fromOctetString(((ASN1OctetString) object).getOctets());
    }

    ASN1Boolean(byte[] bArr) {
        if (bArr.length != 1) {
            throw new IllegalArgumentException("byte value should have 1 byte in it");
        } else if (bArr[0] == 0) {
            this.value = FALSE_VALUE;
        } else if ((bArr[0] & 255) == 255) {
            this.value = TRUE_VALUE;
        } else {
            this.value = Arrays.clone(bArr);
        }
    }

    public ASN1Boolean(boolean z) {
        this.value = z ? TRUE_VALUE : FALSE_VALUE;
    }

    public boolean isTrue() {
        return this.value[0] != 0;
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(1, this.value);
    }

    /* access modifiers changed from: protected */
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Boolean) || this.value[0] != ((ASN1Boolean) aSN1Primitive).value[0]) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.value[0];
    }

    public String toString() {
        return this.value[0] != 0 ? "TRUE" : "FALSE";
    }

    static ASN1Boolean fromOctetString(byte[] bArr) {
        if (bArr.length != 1) {
            throw new IllegalArgumentException("BOOLEAN value should have 1 byte in it");
        } else if (bArr[0] == 0) {
            return FALSE;
        } else {
            if ((bArr[0] & 255) == 255) {
                return TRUE;
            }
            return new ASN1Boolean(bArr);
        }
    }
}
