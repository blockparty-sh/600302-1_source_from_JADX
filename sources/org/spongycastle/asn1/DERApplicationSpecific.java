package org.spongycastle.asn1;

import com.google.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.spongycastle.util.Arrays;

public class DERApplicationSpecific extends ASN1Primitive {
    private final boolean isConstructed;
    private final byte[] octets;
    private final int tag;

    DERApplicationSpecific(boolean z, int i, byte[] bArr) {
        this.isConstructed = z;
        this.tag = i;
        this.octets = bArr;
    }

    public DERApplicationSpecific(int i, byte[] bArr) {
        this(false, i, bArr);
    }

    public DERApplicationSpecific(int i, ASN1Encodable aSN1Encodable) throws IOException {
        this(true, i, aSN1Encodable);
    }

    public DERApplicationSpecific(boolean z, int i, ASN1Encodable aSN1Encodable) throws IOException {
        ASN1Primitive aSN1Primitive = aSN1Encodable.toASN1Primitive();
        byte[] encoded = aSN1Primitive.getEncoded(ASN1Encoding.DER);
        this.isConstructed = z || (aSN1Primitive instanceof ASN1Set) || (aSN1Primitive instanceof ASN1Sequence);
        this.tag = i;
        if (z) {
            this.octets = encoded;
            return;
        }
        int lengthOfHeader = getLengthOfHeader(encoded);
        byte[] bArr = new byte[(encoded.length - lengthOfHeader)];
        System.arraycopy(encoded, lengthOfHeader, bArr, 0, bArr.length);
        this.octets = bArr;
    }

    public DERApplicationSpecific(int i, ASN1EncodableVector aSN1EncodableVector) {
        this.tag = i;
        this.isConstructed = true;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        while (i2 != aSN1EncodableVector.size()) {
            try {
                byteArrayOutputStream.write(((ASN1Object) aSN1EncodableVector.get(i2)).getEncoded(ASN1Encoding.DER));
                i2++;
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("malformed object: ");
                sb.append(e);
                throw new ASN1ParsingException(sb.toString(), e);
            }
        }
        this.octets = byteArrayOutputStream.toByteArray();
    }

    public static DERApplicationSpecific getInstance(Object obj) {
        if (obj == null || (obj instanceof DERApplicationSpecific)) {
            return (DERApplicationSpecific) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("failed to construct object from byte[]: ");
                sb.append(e.getMessage());
                throw new IllegalArgumentException(sb.toString());
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("unknown object in getInstance: ");
            sb2.append(obj.getClass().getName());
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    private int getLengthOfHeader(byte[] bArr) {
        byte b = bArr[1] & 255;
        if (b == 128 || b <= Byte.MAX_VALUE) {
            return 2;
        }
        byte b2 = b & Byte.MAX_VALUE;
        if (b2 <= 4) {
            return b2 + 2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("DER length more than 4 bytes: ");
        sb.append(b2);
        throw new IllegalStateException(sb.toString());
    }

    public boolean isConstructed() {
        return this.isConstructed;
    }

    public byte[] getContents() {
        return this.octets;
    }

    public int getApplicationTag() {
        return this.tag;
    }

    public ASN1Primitive getObject() throws IOException {
        return new ASN1InputStream(getContents()).readObject();
    }

    public ASN1Primitive getObject(int i) throws IOException {
        if (i < 31) {
            byte[] encoded = getEncoded();
            byte[] replaceTagNumber = replaceTagNumber(i, encoded);
            if ((encoded[0] & 32) != 0) {
                replaceTagNumber[0] = (byte) (replaceTagNumber[0] | 32);
            }
            return new ASN1InputStream(replaceTagNumber).readObject();
        }
        throw new IOException("unsupported tag number");
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() throws IOException {
        return StreamUtil.calculateTagLength(this.tag) + StreamUtil.calculateBodyLength(this.octets.length) + this.octets.length;
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(this.isConstructed ? 96 : 64, this.tag, this.octets);
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        boolean z = false;
        if (!(aSN1Primitive instanceof DERApplicationSpecific)) {
            return false;
        }
        DERApplicationSpecific dERApplicationSpecific = (DERApplicationSpecific) aSN1Primitive;
        if (this.isConstructed == dERApplicationSpecific.isConstructed && this.tag == dERApplicationSpecific.tag && Arrays.areEqual(this.octets, dERApplicationSpecific.octets)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (this.isConstructed ^ this.tag) ^ Arrays.hashCode(this.octets) ? 1 : 0;
    }

    private byte[] replaceTagNumber(int i, byte[] bArr) throws IOException {
        int i2;
        if ((bArr[0] & Ascii.f531US) == 31) {
            i2 = 2;
            byte b = bArr[1] & 255;
            if ((b & Byte.MAX_VALUE) != 0) {
                while (b >= 0 && (b & 128) != 0) {
                    int i3 = i2 + 1;
                    b = bArr[i2] & 255;
                    i2 = i3;
                }
            } else {
                throw new ASN1ParsingException("corrupted stream - invalid high tag number found");
            }
        } else {
            i2 = 1;
        }
        byte[] bArr2 = new byte[((bArr.length - i2) + 1)];
        System.arraycopy(bArr, i2, bArr2, 1, bArr2.length - 1);
        bArr2[0] = (byte) i;
        return bArr2;
    }
}
