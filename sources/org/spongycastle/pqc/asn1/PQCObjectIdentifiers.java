package org.spongycastle.pqc.asn1;

import androidx.exifinterface.media.ExifInterface;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.pqc.crypto.gmss.GMSSKeyPairGenerator;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2KeyPairGenerator;
import org.spongycastle.pqc.crypto.mceliece.McEliecePKCSCipher;

public interface PQCObjectIdentifiers {
    public static final ASN1ObjectIdentifier gmss = new ASN1ObjectIdentifier(GMSSKeyPairGenerator.OID);
    public static final ASN1ObjectIdentifier gmssWithSha1;
    public static final ASN1ObjectIdentifier gmssWithSha224;
    public static final ASN1ObjectIdentifier gmssWithSha256;
    public static final ASN1ObjectIdentifier gmssWithSha384;
    public static final ASN1ObjectIdentifier gmssWithSha512;
    public static final ASN1ObjectIdentifier mcEliece = new ASN1ObjectIdentifier(McEliecePKCSCipher.OID);
    public static final ASN1ObjectIdentifier mcElieceCca2 = new ASN1ObjectIdentifier(McElieceCCA2KeyPairGenerator.OID);
    public static final ASN1ObjectIdentifier rainbow = new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.5.3.2");
    public static final ASN1ObjectIdentifier rainbowWithSha1;
    public static final ASN1ObjectIdentifier rainbowWithSha224;
    public static final ASN1ObjectIdentifier rainbowWithSha256;
    public static final ASN1ObjectIdentifier rainbowWithSha384;
    public static final ASN1ObjectIdentifier rainbowWithSha512;

    static {
        String str = "1";
        rainbowWithSha1 = rainbow.branch(str);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = rainbow;
        String str2 = ExifInterface.GPS_MEASUREMENT_2D;
        rainbowWithSha224 = aSN1ObjectIdentifier.branch(str2);
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = rainbow;
        String str3 = ExifInterface.GPS_MEASUREMENT_3D;
        rainbowWithSha256 = aSN1ObjectIdentifier2.branch(str3);
        String str4 = "4";
        rainbowWithSha384 = rainbow.branch(str4);
        String str5 = "5";
        rainbowWithSha512 = rainbow.branch(str5);
        gmssWithSha1 = gmss.branch(str);
        gmssWithSha224 = gmss.branch(str2);
        gmssWithSha256 = gmss.branch(str3);
        gmssWithSha384 = gmss.branch(str4);
        gmssWithSha512 = gmss.branch(str5);
    }
}
