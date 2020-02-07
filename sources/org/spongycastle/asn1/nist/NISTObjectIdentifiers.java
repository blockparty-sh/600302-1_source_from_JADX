package org.spongycastle.asn1.nist;

import androidx.exifinterface.media.ExifInterface;
import androidx.room.RoomMasterTable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import p007at.favre.lib.slf4j_timber.BuildConfig;

public interface NISTObjectIdentifiers {
    public static final ASN1ObjectIdentifier aes;
    public static final ASN1ObjectIdentifier dsa_with_sha224;
    public static final ASN1ObjectIdentifier dsa_with_sha256;
    public static final ASN1ObjectIdentifier dsa_with_sha384;
    public static final ASN1ObjectIdentifier dsa_with_sha512;
    public static final ASN1ObjectIdentifier hashAlgs;
    public static final ASN1ObjectIdentifier id_aes128_CBC;
    public static final ASN1ObjectIdentifier id_aes128_CCM = aes.branch("7");
    public static final ASN1ObjectIdentifier id_aes128_CFB;
    public static final ASN1ObjectIdentifier id_aes128_ECB;
    public static final ASN1ObjectIdentifier id_aes128_GCM;
    public static final ASN1ObjectIdentifier id_aes128_OFB;
    public static final ASN1ObjectIdentifier id_aes128_wrap;
    public static final ASN1ObjectIdentifier id_aes192_CBC = aes.branch("22");
    public static final ASN1ObjectIdentifier id_aes192_CCM = aes.branch("27");
    public static final ASN1ObjectIdentifier id_aes192_CFB = aes.branch("24");
    public static final ASN1ObjectIdentifier id_aes192_ECB = aes.branch(BuildConfig.BUILD_NUMBER);
    public static final ASN1ObjectIdentifier id_aes192_GCM = aes.branch("26");
    public static final ASN1ObjectIdentifier id_aes192_OFB = aes.branch("23");
    public static final ASN1ObjectIdentifier id_aes192_wrap = aes.branch("25");
    public static final ASN1ObjectIdentifier id_aes256_CBC = aes.branch(RoomMasterTable.DEFAULT_ID);
    public static final ASN1ObjectIdentifier id_aes256_CCM = aes.branch("47");
    public static final ASN1ObjectIdentifier id_aes256_CFB = aes.branch("44");
    public static final ASN1ObjectIdentifier id_aes256_ECB = aes.branch("41");
    public static final ASN1ObjectIdentifier id_aes256_GCM = aes.branch("46");
    public static final ASN1ObjectIdentifier id_aes256_OFB = aes.branch("43");
    public static final ASN1ObjectIdentifier id_aes256_wrap = aes.branch("45");
    public static final ASN1ObjectIdentifier id_dsa_with_sha2;
    public static final ASN1ObjectIdentifier id_sha224;
    public static final ASN1ObjectIdentifier id_sha256;
    public static final ASN1ObjectIdentifier id_sha384;
    public static final ASN1ObjectIdentifier id_sha512;
    public static final ASN1ObjectIdentifier id_sha512_224;
    public static final ASN1ObjectIdentifier id_sha512_256;
    public static final ASN1ObjectIdentifier nistAlgorithm = new ASN1ObjectIdentifier("2.16.840.1.101.3.4");

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = nistAlgorithm;
        String str = ExifInterface.GPS_MEASUREMENT_2D;
        hashAlgs = aSN1ObjectIdentifier.branch(str);
        String str2 = "1";
        id_sha256 = hashAlgs.branch(str2);
        id_sha384 = hashAlgs.branch(str);
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = hashAlgs;
        String str3 = ExifInterface.GPS_MEASUREMENT_3D;
        id_sha512 = aSN1ObjectIdentifier2.branch(str3);
        String str4 = "4";
        id_sha224 = hashAlgs.branch(str4);
        String str5 = "5";
        id_sha512_224 = hashAlgs.branch(str5);
        String str6 = "6";
        id_sha512_256 = hashAlgs.branch(str6);
        aes = nistAlgorithm.branch(str2);
        id_aes128_ECB = aes.branch(str2);
        id_aes128_CBC = aes.branch(str);
        id_aes128_OFB = aes.branch(str3);
        id_aes128_CFB = aes.branch(str4);
        id_aes128_wrap = aes.branch(str5);
        id_aes128_GCM = aes.branch(str6);
        id_dsa_with_sha2 = nistAlgorithm.branch(str3);
        dsa_with_sha224 = id_dsa_with_sha2.branch(str2);
        dsa_with_sha256 = id_dsa_with_sha2.branch(str);
        dsa_with_sha384 = id_dsa_with_sha2.branch(str3);
        dsa_with_sha512 = id_dsa_with_sha2.branch(str4);
    }
}
