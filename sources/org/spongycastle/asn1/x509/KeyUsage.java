package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERBitString;

public class KeyUsage extends ASN1Object {
    public static final int cRLSign = 2;
    public static final int dataEncipherment = 16;
    public static final int decipherOnly = 32768;
    public static final int digitalSignature = 128;
    public static final int encipherOnly = 1;
    public static final int keyAgreement = 8;
    public static final int keyCertSign = 4;
    public static final int keyEncipherment = 32;
    public static final int nonRepudiation = 64;
    private DERBitString bitString;

    public static KeyUsage getInstance(Object obj) {
        if (obj instanceof KeyUsage) {
            return (KeyUsage) obj;
        }
        if (obj != null) {
            return new KeyUsage(DERBitString.getInstance(obj));
        }
        return null;
    }

    public static KeyUsage fromExtensions(Extensions extensions) {
        return getInstance(extensions.getExtensionParsedValue(Extension.keyUsage));
    }

    public KeyUsage(int i) {
        this.bitString = new DERBitString(i);
    }

    private KeyUsage(DERBitString dERBitString) {
        this.bitString = dERBitString;
    }

    public boolean hasUsages(int i) {
        return (this.bitString.intValue() & i) == i;
    }

    public byte[] getBytes() {
        return this.bitString.getBytes();
    }

    public int getPadBits() {
        return this.bitString.getPadBits();
    }

    public String toString() {
        byte[] bytes = this.bitString.getBytes();
        String str = "KeyUsage: 0x";
        if (bytes.length == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(Integer.toHexString(bytes[0] & 255));
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(Integer.toHexString((bytes[0] & 255) | ((bytes[1] & 255) << 8)));
        return sb2.toString();
    }

    public ASN1Primitive toASN1Primitive() {
        return this.bitString;
    }
}
