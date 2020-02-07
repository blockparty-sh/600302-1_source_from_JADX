package org.spongycastle.asn1;

import com.google.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.p026io.Streams;

public class DERBitString extends ASN1Primitive implements ASN1String {
    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    protected byte[] data;
    protected int padBits;

    protected static int getPadBits(int i) {
        int i2;
        int i3 = 3;
        while (true) {
            if (i3 < 0) {
                i2 = 0;
                break;
            }
            if (i3 != 0) {
                int i4 = i >> (i3 * 8);
                if (i4 != 0) {
                    i2 = i4 & 255;
                    break;
                }
            } else if (i != 0) {
                i2 = i & 255;
                break;
            }
            i3--;
        }
        if (i2 == 0) {
            return 7;
        }
        int i5 = 1;
        while (true) {
            i2 <<= 1;
            if ((i2 & 255) == 0) {
                return 8 - i5;
            }
            i5++;
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isConstructed() {
        return false;
    }

    protected static byte[] getBytes(int i) {
        int i2 = 4;
        int i3 = 3;
        while (i3 >= 1 && ((255 << (i3 * 8)) & i) == 0) {
            i2--;
            i3--;
        }
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = (byte) ((i >> (i4 * 8)) & 255);
        }
        return bArr;
    }

    public static DERBitString getInstance(Object obj) {
        if (obj == null || (obj instanceof DERBitString)) {
            return (DERBitString) obj;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("illegal object in getInstance: ");
        sb.append(obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public static DERBitString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof DERBitString)) {
            return getInstance(object);
        }
        return fromOctetString(((ASN1OctetString) object).getOctets());
    }

    protected DERBitString(byte b, int i) {
        this.data = new byte[1];
        this.data[0] = b;
        this.padBits = i;
    }

    public DERBitString(byte[] bArr, int i) {
        this.data = bArr;
        this.padBits = i;
    }

    public DERBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public DERBitString(int i) {
        this.data = getBytes(i);
        this.padBits = getPadBits(i);
    }

    public DERBitString(ASN1Encodable aSN1Encodable) throws IOException {
        this.data = aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER);
        this.padBits = 0;
    }

    public byte[] getBytes() {
        return this.data;
    }

    public int getPadBits() {
        return this.padBits;
    }

    public int intValue() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i == bArr.length || i == 4) {
                return i2;
            }
            i2 |= (bArr[i] & 255) << (i * 8);
            i++;
        }
        return i2;
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.data.length + 1) + 1 + this.data.length + 1;
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        byte[] bArr = new byte[(getBytes().length + 1)];
        bArr[0] = (byte) getPadBits();
        System.arraycopy(getBytes(), 0, bArr, 1, bArr.length - 1);
        aSN1OutputStream.writeEncoded(3, bArr);
    }

    public int hashCode() {
        return this.padBits ^ Arrays.hashCode(this.data);
    }

    /* access modifiers changed from: protected */
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        boolean z = false;
        if (!(aSN1Primitive instanceof DERBitString)) {
            return false;
        }
        DERBitString dERBitString = (DERBitString) aSN1Primitive;
        if (this.padBits == dERBitString.padBits && Arrays.areEqual(this.data, dERBitString.data)) {
            z = true;
        }
        return z;
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

    static DERBitString fromOctetString(byte[] bArr) {
        if (bArr.length >= 1) {
            byte b = bArr[0];
            byte[] bArr2 = new byte[(bArr.length - 1)];
            if (bArr2.length != 0) {
                System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
            }
            return new DERBitString(bArr2, (int) b);
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }

    static DERBitString fromInputStream(int i, InputStream inputStream) throws IOException {
        if (i >= 1) {
            int read = inputStream.read();
            byte[] bArr = new byte[(i - 1)];
            if (bArr.length == 0 || Streams.readFully(inputStream, bArr) == bArr.length) {
                return new DERBitString(bArr, read);
            }
            throw new EOFException("EOF encountered in middle of BIT STRING");
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }
}
