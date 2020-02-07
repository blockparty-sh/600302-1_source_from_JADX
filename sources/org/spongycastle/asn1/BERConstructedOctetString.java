package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public class BERConstructedOctetString extends BEROctetString {
    private static final int MAX_LENGTH = 1000;
    private Vector octs;

    private static byte[] toBytes(Vector vector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i != vector.size()) {
            try {
                byteArrayOutputStream.write(((DEROctetString) vector.elementAt(i)).getOctets());
                i++;
            } catch (ClassCastException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append(vector.elementAt(i).getClass().getName());
                sb.append(" found in input should only contain DEROctetString");
                throw new IllegalArgumentException(sb.toString());
            } catch (IOException e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("exception converting octets ");
                sb2.append(e.toString());
                throw new IllegalArgumentException(sb2.toString());
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public BERConstructedOctetString(byte[] bArr) {
        super(bArr);
    }

    public BERConstructedOctetString(Vector vector) {
        super(toBytes(vector));
        this.octs = vector;
    }

    public BERConstructedOctetString(ASN1Primitive aSN1Primitive) {
        super(toByteArray(aSN1Primitive));
    }

    private static byte[] toByteArray(ASN1Primitive aSN1Primitive) {
        try {
            return aSN1Primitive.getEncoded();
        } catch (IOException unused) {
            throw new IllegalArgumentException("Unable to encode object");
        }
    }

    public BERConstructedOctetString(ASN1Encodable aSN1Encodable) {
        this(aSN1Encodable.toASN1Primitive());
    }

    public byte[] getOctets() {
        return this.string;
    }

    public Enumeration getObjects() {
        Vector vector = this.octs;
        if (vector == null) {
            return generateOcts().elements();
        }
        return vector.elements();
    }

    private Vector generateOcts() {
        Vector vector = new Vector();
        int i = 0;
        while (i < this.string.length) {
            int i2 = i + 1000;
            byte[] bArr = new byte[((i2 > this.string.length ? this.string.length : i2) - i)];
            System.arraycopy(this.string, i, bArr, 0, bArr.length);
            vector.addElement(new DEROctetString(bArr));
            i = i2;
        }
        return vector;
    }

    public static BEROctetString fromSequence(ASN1Sequence aSN1Sequence) {
        Vector vector = new Vector();
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            vector.addElement(objects.nextElement());
        }
        return new BERConstructedOctetString(vector);
    }
}
