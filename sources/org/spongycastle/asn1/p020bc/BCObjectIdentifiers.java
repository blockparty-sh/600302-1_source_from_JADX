package org.spongycastle.asn1.p020bc;

import androidx.exifinterface.media.ExifInterface;
import org.spongycastle.asn1.ASN1ObjectIdentifier;

/* renamed from: org.spongycastle.asn1.bc.BCObjectIdentifiers */
public interface BCObjectIdentifiers {

    /* renamed from: bc */
    public static final ASN1ObjectIdentifier f837bc = new ASN1ObjectIdentifier("1.3.6.1.4.1.22554");
    public static final ASN1ObjectIdentifier bc_pbe;
    public static final ASN1ObjectIdentifier bc_pbe_sha1;
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs12;
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs12_aes128_cbc;
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs12_aes192_cbc;
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs12_aes256_cbc;
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs5;
    public static final ASN1ObjectIdentifier bc_pbe_sha224 = bc_pbe.branch("2.4");
    public static final ASN1ObjectIdentifier bc_pbe_sha256 = bc_pbe.branch("2.1");
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs12;
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs12_aes128_cbc;
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs12_aes192_cbc;
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs12_aes256_cbc;
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs5;
    public static final ASN1ObjectIdentifier bc_pbe_sha384 = bc_pbe.branch("2.2");
    public static final ASN1ObjectIdentifier bc_pbe_sha512 = bc_pbe.branch("2.3");

    static {
        String str = "1";
        bc_pbe = f837bc.branch(str);
        bc_pbe_sha1 = bc_pbe.branch(str);
        bc_pbe_sha1_pkcs5 = bc_pbe_sha1.branch(str);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = bc_pbe_sha1;
        String str2 = ExifInterface.GPS_MEASUREMENT_2D;
        bc_pbe_sha1_pkcs12 = aSN1ObjectIdentifier.branch(str2);
        bc_pbe_sha256_pkcs5 = bc_pbe_sha256.branch(str);
        bc_pbe_sha256_pkcs12 = bc_pbe_sha256.branch(str2);
        String str3 = "1.2";
        bc_pbe_sha1_pkcs12_aes128_cbc = bc_pbe_sha1_pkcs12.branch(str3);
        String str4 = "1.22";
        bc_pbe_sha1_pkcs12_aes192_cbc = bc_pbe_sha1_pkcs12.branch(str4);
        String str5 = "1.42";
        bc_pbe_sha1_pkcs12_aes256_cbc = bc_pbe_sha1_pkcs12.branch(str5);
        bc_pbe_sha256_pkcs12_aes128_cbc = bc_pbe_sha256_pkcs12.branch(str3);
        bc_pbe_sha256_pkcs12_aes192_cbc = bc_pbe_sha256_pkcs12.branch(str4);
        bc_pbe_sha256_pkcs12_aes256_cbc = bc_pbe_sha256_pkcs12.branch(str5);
    }
}
