package org.spongycastle.asn1.pkcs;

import androidx.exifinterface.media.ExifInterface;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import p007at.favre.lib.slf4j_timber.BuildConfig;

public interface PKCSObjectIdentifiers {
    public static final ASN1ObjectIdentifier RC2_CBC;
    public static final ASN1ObjectIdentifier bagtypes = pkcs_12.branch("10.1");
    public static final ASN1ObjectIdentifier canNotDecryptAny = pkcs_9.branch("15.2");
    public static final ASN1ObjectIdentifier certBag;
    public static final ASN1ObjectIdentifier certTypes = pkcs_9.branch("22");
    public static final ASN1ObjectIdentifier crlBag;
    public static final ASN1ObjectIdentifier crlTypes;
    public static final ASN1ObjectIdentifier data = new ASN1ObjectIdentifier("1.2.840.113549.1.7.1");
    public static final ASN1ObjectIdentifier des_EDE3_CBC;
    public static final ASN1ObjectIdentifier dhKeyAgreement;
    public static final ASN1ObjectIdentifier digestAlgorithm = new ASN1ObjectIdentifier("1.2.840.113549.2");
    public static final ASN1ObjectIdentifier digestedData = new ASN1ObjectIdentifier("1.2.840.113549.1.7.5");
    public static final ASN1ObjectIdentifier encryptedData = new ASN1ObjectIdentifier("1.2.840.113549.1.7.6");
    public static final ASN1ObjectIdentifier encryptionAlgorithm = new ASN1ObjectIdentifier("1.2.840.113549.3");
    public static final ASN1ObjectIdentifier envelopedData = new ASN1ObjectIdentifier("1.2.840.113549.1.7.3");
    public static final ASN1ObjectIdentifier id_PBES2;
    public static final ASN1ObjectIdentifier id_PBKDF2;
    public static final ASN1ObjectIdentifier id_RSAES_OAEP;
    public static final ASN1ObjectIdentifier id_RSASSA_PSS;
    public static final ASN1ObjectIdentifier id_aa = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.2");
    public static final ASN1ObjectIdentifier id_aa_commitmentType = id_aa_ets_commitmentType;
    public static final ASN1ObjectIdentifier id_aa_contentHint;
    public static final ASN1ObjectIdentifier id_aa_contentIdentifier;
    public static final ASN1ObjectIdentifier id_aa_contentReference;
    public static final ASN1ObjectIdentifier id_aa_encrypKeyPref;
    public static final ASN1ObjectIdentifier id_aa_ets_archiveTimestamp = id_aa.branch("27");
    public static final ASN1ObjectIdentifier id_aa_ets_certCRLTimestamp = id_aa.branch("26");
    public static final ASN1ObjectIdentifier id_aa_ets_certValues;
    public static final ASN1ObjectIdentifier id_aa_ets_certificateRefs = id_aa.branch(BuildConfig.BUILD_NUMBER);
    public static final ASN1ObjectIdentifier id_aa_ets_commitmentType = id_aa.branch("16");
    public static final ASN1ObjectIdentifier id_aa_ets_contentTimestamp = id_aa.branch("20");
    public static final ASN1ObjectIdentifier id_aa_ets_escTimeStamp = id_aa.branch("25");
    public static final ASN1ObjectIdentifier id_aa_ets_otherSigCert = id_aa.branch("19");
    public static final ASN1ObjectIdentifier id_aa_ets_revocationRefs = id_aa.branch("22");
    public static final ASN1ObjectIdentifier id_aa_ets_revocationValues = id_aa.branch("24");
    public static final ASN1ObjectIdentifier id_aa_ets_sigPolicyId = id_aa.branch("15");
    public static final ASN1ObjectIdentifier id_aa_ets_signerAttr = id_aa.branch("18");
    public static final ASN1ObjectIdentifier id_aa_ets_signerLocation = id_aa.branch("17");
    public static final ASN1ObjectIdentifier id_aa_msgSigDigest;
    public static final ASN1ObjectIdentifier id_aa_otherSigCert = id_aa_ets_otherSigCert;
    public static final ASN1ObjectIdentifier id_aa_receiptRequest;
    public static final ASN1ObjectIdentifier id_aa_sigPolicyId = id_aa_ets_sigPolicyId;
    public static final ASN1ObjectIdentifier id_aa_signatureTimeStampToken;
    public static final ASN1ObjectIdentifier id_aa_signerLocation = id_aa_ets_signerLocation;
    public static final ASN1ObjectIdentifier id_aa_signingCertificate;
    public static final ASN1ObjectIdentifier id_aa_signingCertificateV2 = id_aa.branch("47");
    public static final ASN1ObjectIdentifier id_alg;
    public static final ASN1ObjectIdentifier id_alg_CMS3DESwrap = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.3.6");
    public static final ASN1ObjectIdentifier id_alg_CMSRC2wrap = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.3.7");
    public static final ASN1ObjectIdentifier id_alg_PWRI_KEK;
    public static final ASN1ObjectIdentifier id_ct = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.1");
    public static final ASN1ObjectIdentifier id_ct_TSTInfo;
    public static final ASN1ObjectIdentifier id_ct_authData;
    public static final ASN1ObjectIdentifier id_ct_authEnvelopedData;
    public static final ASN1ObjectIdentifier id_ct_compressedData;
    public static final ASN1ObjectIdentifier id_ct_timestampedData = id_ct.branch("31");
    public static final ASN1ObjectIdentifier id_cti = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.6");
    public static final ASN1ObjectIdentifier id_cti_ets_proofOfApproval;
    public static final ASN1ObjectIdentifier id_cti_ets_proofOfCreation;
    public static final ASN1ObjectIdentifier id_cti_ets_proofOfDelivery;
    public static final ASN1ObjectIdentifier id_cti_ets_proofOfOrigin;
    public static final ASN1ObjectIdentifier id_cti_ets_proofOfReceipt;
    public static final ASN1ObjectIdentifier id_cti_ets_proofOfSender;
    public static final ASN1ObjectIdentifier id_hmacWithSHA1;
    public static final ASN1ObjectIdentifier id_hmacWithSHA224;
    public static final ASN1ObjectIdentifier id_hmacWithSHA256;
    public static final ASN1ObjectIdentifier id_hmacWithSHA384;
    public static final ASN1ObjectIdentifier id_hmacWithSHA512;
    public static final ASN1ObjectIdentifier id_mgf1;
    public static final ASN1ObjectIdentifier id_pSpecified;
    public static final ASN1ObjectIdentifier id_smime = pkcs_9.branch("16");
    public static final String id_spq = "1.2.840.113549.1.9.16.5";
    public static final ASN1ObjectIdentifier id_spq_ets_unotice = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.5.2");
    public static final ASN1ObjectIdentifier id_spq_ets_uri = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.5.1");
    public static final ASN1ObjectIdentifier keyBag;
    public static final ASN1ObjectIdentifier md2;
    public static final ASN1ObjectIdentifier md2WithRSAEncryption;
    public static final ASN1ObjectIdentifier md4;
    public static final ASN1ObjectIdentifier md4WithRSAEncryption;
    public static final ASN1ObjectIdentifier md5;
    public static final ASN1ObjectIdentifier md5WithRSAEncryption;
    public static final ASN1ObjectIdentifier pbeWithMD2AndDES_CBC;
    public static final ASN1ObjectIdentifier pbeWithMD2AndRC2_CBC;
    public static final ASN1ObjectIdentifier pbeWithMD5AndDES_CBC;
    public static final ASN1ObjectIdentifier pbeWithMD5AndRC2_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHA1AndDES_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHA1AndRC2_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHAAnd128BitRC2_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHAAnd128BitRC4;
    public static final ASN1ObjectIdentifier pbeWithSHAAnd2_KeyTripleDES_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHAAnd3_KeyTripleDES_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHAAnd40BitRC2_CBC;
    public static final ASN1ObjectIdentifier pbeWithSHAAnd40BitRC4;
    public static final ASN1ObjectIdentifier pbewithSHAAnd40BitRC2_CBC;
    public static final ASN1ObjectIdentifier pkcs8ShroudedKeyBag;
    public static final ASN1ObjectIdentifier pkcs_1 = new ASN1ObjectIdentifier("1.2.840.113549.1.1");
    public static final ASN1ObjectIdentifier pkcs_12 = new ASN1ObjectIdentifier("1.2.840.113549.1.12");
    public static final ASN1ObjectIdentifier pkcs_12PbeIds;
    public static final ASN1ObjectIdentifier pkcs_3 = new ASN1ObjectIdentifier("1.2.840.113549.1.3");
    public static final ASN1ObjectIdentifier pkcs_5 = new ASN1ObjectIdentifier("1.2.840.113549.1.5");
    public static final ASN1ObjectIdentifier pkcs_7 = new ASN1ObjectIdentifier("1.2.840.113549.1.7");
    public static final ASN1ObjectIdentifier pkcs_9 = new ASN1ObjectIdentifier("1.2.840.113549.1.9");
    public static final ASN1ObjectIdentifier pkcs_9_at_challengePassword;
    public static final ASN1ObjectIdentifier pkcs_9_at_contentType;
    public static final ASN1ObjectIdentifier pkcs_9_at_counterSignature;
    public static final ASN1ObjectIdentifier pkcs_9_at_emailAddress;
    public static final ASN1ObjectIdentifier pkcs_9_at_extendedCertificateAttributes;
    public static final ASN1ObjectIdentifier pkcs_9_at_extensionRequest;
    public static final ASN1ObjectIdentifier pkcs_9_at_friendlyName = pkcs_9.branch("20");
    public static final ASN1ObjectIdentifier pkcs_9_at_localKeyId = pkcs_9.branch(BuildConfig.BUILD_NUMBER);
    public static final ASN1ObjectIdentifier pkcs_9_at_messageDigest;
    public static final ASN1ObjectIdentifier pkcs_9_at_signingDescription;
    public static final ASN1ObjectIdentifier pkcs_9_at_signingTime;
    public static final ASN1ObjectIdentifier pkcs_9_at_smimeCapabilities = pkcs_9.branch("15");
    public static final ASN1ObjectIdentifier pkcs_9_at_unstructuredAddress;
    public static final ASN1ObjectIdentifier pkcs_9_at_unstructuredName;
    public static final ASN1ObjectIdentifier preferSignedData = pkcs_9.branch("15.1");
    public static final ASN1ObjectIdentifier rc4;
    public static final ASN1ObjectIdentifier rsaEncryption;
    public static final ASN1ObjectIdentifier sMIMECapabilitiesVersions = pkcs_9.branch("15.3");
    public static final ASN1ObjectIdentifier safeContentsBag;
    public static final ASN1ObjectIdentifier sdsiCertificate;
    public static final ASN1ObjectIdentifier secretBag;
    public static final ASN1ObjectIdentifier sha1WithRSAEncryption;
    public static final ASN1ObjectIdentifier sha224WithRSAEncryption;
    public static final ASN1ObjectIdentifier sha256WithRSAEncryption;
    public static final ASN1ObjectIdentifier sha384WithRSAEncryption;
    public static final ASN1ObjectIdentifier sha512WithRSAEncryption;
    public static final ASN1ObjectIdentifier signedAndEnvelopedData = new ASN1ObjectIdentifier("1.2.840.113549.1.7.4");
    public static final ASN1ObjectIdentifier signedData = new ASN1ObjectIdentifier("1.2.840.113549.1.7.2");
    public static final ASN1ObjectIdentifier srsaOAEPEncryptionSET;
    public static final ASN1ObjectIdentifier x509Certificate;
    public static final ASN1ObjectIdentifier x509Crl;
    public static final ASN1ObjectIdentifier x509certType = pkcs_9.branch("22.1");

    static {
        String str = "1";
        rsaEncryption = pkcs_1.branch(str);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = pkcs_1;
        String str2 = ExifInterface.GPS_MEASUREMENT_2D;
        md2WithRSAEncryption = aSN1ObjectIdentifier.branch(str2);
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = pkcs_1;
        String str3 = ExifInterface.GPS_MEASUREMENT_3D;
        md4WithRSAEncryption = aSN1ObjectIdentifier2.branch(str3);
        String str4 = "4";
        md5WithRSAEncryption = pkcs_1.branch(str4);
        String str5 = "5";
        sha1WithRSAEncryption = pkcs_1.branch(str5);
        String str6 = "6";
        srsaOAEPEncryptionSET = pkcs_1.branch(str6);
        String str7 = "7";
        id_RSAES_OAEP = pkcs_1.branch(str7);
        String str8 = "8";
        id_mgf1 = pkcs_1.branch(str8);
        String str9 = "9";
        id_pSpecified = pkcs_1.branch(str9);
        String str10 = "10";
        id_RSASSA_PSS = pkcs_1.branch(str10);
        String str11 = "11";
        sha256WithRSAEncryption = pkcs_1.branch(str11);
        String str12 = "12";
        sha384WithRSAEncryption = pkcs_1.branch(str12);
        String str13 = "13";
        sha512WithRSAEncryption = pkcs_1.branch(str13);
        String str14 = "14";
        sha224WithRSAEncryption = pkcs_1.branch(str14);
        dhKeyAgreement = pkcs_3.branch(str);
        pbeWithMD2AndDES_CBC = pkcs_5.branch(str);
        pbeWithMD2AndRC2_CBC = pkcs_5.branch(str4);
        pbeWithMD5AndDES_CBC = pkcs_5.branch(str3);
        pbeWithMD5AndRC2_CBC = pkcs_5.branch(str6);
        pbeWithSHA1AndDES_CBC = pkcs_5.branch(str10);
        pbeWithSHA1AndRC2_CBC = pkcs_5.branch(str11);
        id_PBES2 = pkcs_5.branch(str13);
        id_PBKDF2 = pkcs_5.branch(str12);
        des_EDE3_CBC = encryptionAlgorithm.branch(str7);
        RC2_CBC = encryptionAlgorithm.branch(str2);
        rc4 = encryptionAlgorithm.branch(str4);
        md2 = digestAlgorithm.branch(str2);
        md4 = digestAlgorithm.branch(str4);
        md5 = digestAlgorithm.branch(str5);
        id_hmacWithSHA1 = digestAlgorithm.branch(str7);
        id_hmacWithSHA224 = digestAlgorithm.branch(str8);
        id_hmacWithSHA256 = digestAlgorithm.branch(str9);
        id_hmacWithSHA384 = digestAlgorithm.branch(str10);
        id_hmacWithSHA512 = digestAlgorithm.branch(str11);
        pkcs_9_at_emailAddress = pkcs_9.branch(str);
        pkcs_9_at_unstructuredName = pkcs_9.branch(str2);
        pkcs_9_at_contentType = pkcs_9.branch(str3);
        pkcs_9_at_messageDigest = pkcs_9.branch(str4);
        pkcs_9_at_signingTime = pkcs_9.branch(str5);
        pkcs_9_at_counterSignature = pkcs_9.branch(str6);
        pkcs_9_at_challengePassword = pkcs_9.branch(str7);
        pkcs_9_at_unstructuredAddress = pkcs_9.branch(str8);
        pkcs_9_at_extendedCertificateAttributes = pkcs_9.branch(str9);
        pkcs_9_at_signingDescription = pkcs_9.branch(str13);
        pkcs_9_at_extensionRequest = pkcs_9.branch(str14);
        x509Certificate = certTypes.branch(str);
        sdsiCertificate = certTypes.branch(str2);
        String str15 = "23";
        crlTypes = pkcs_9.branch(str15);
        x509Crl = crlTypes.branch(str);
        id_ct_authData = id_ct.branch(str2);
        id_ct_TSTInfo = id_ct.branch(str4);
        id_ct_compressedData = id_ct.branch(str9);
        id_ct_authEnvelopedData = id_ct.branch(str15);
        id_alg = id_smime.branch(str3);
        id_alg_PWRI_KEK = id_alg.branch(str9);
        id_cti_ets_proofOfOrigin = id_cti.branch(str);
        id_cti_ets_proofOfReceipt = id_cti.branch(str2);
        id_cti_ets_proofOfDelivery = id_cti.branch(str3);
        id_cti_ets_proofOfSender = id_cti.branch(str4);
        id_cti_ets_proofOfApproval = id_cti.branch(str5);
        id_cti_ets_proofOfCreation = id_cti.branch(str6);
        id_aa_receiptRequest = id_aa.branch(str);
        id_aa_contentHint = id_aa.branch(str4);
        id_aa_msgSigDigest = id_aa.branch(str5);
        id_aa_contentReference = id_aa.branch(str10);
        id_aa_encrypKeyPref = id_aa.branch(str11);
        id_aa_signingCertificate = id_aa.branch(str12);
        id_aa_contentIdentifier = id_aa.branch(str7);
        id_aa_signatureTimeStampToken = id_aa.branch(str14);
        id_aa_ets_certValues = id_aa.branch(str15);
        keyBag = bagtypes.branch(str);
        pkcs8ShroudedKeyBag = bagtypes.branch(str2);
        certBag = bagtypes.branch(str3);
        crlBag = bagtypes.branch(str4);
        secretBag = bagtypes.branch(str5);
        safeContentsBag = bagtypes.branch(str6);
        pkcs_12PbeIds = pkcs_12.branch(str);
        pbeWithSHAAnd128BitRC4 = pkcs_12PbeIds.branch(str);
        pbeWithSHAAnd40BitRC4 = pkcs_12PbeIds.branch(str2);
        pbeWithSHAAnd3_KeyTripleDES_CBC = pkcs_12PbeIds.branch(str3);
        pbeWithSHAAnd2_KeyTripleDES_CBC = pkcs_12PbeIds.branch(str4);
        pbeWithSHAAnd128BitRC2_CBC = pkcs_12PbeIds.branch(str5);
        pbeWithSHAAnd40BitRC2_CBC = pkcs_12PbeIds.branch(str6);
        pbewithSHAAnd40BitRC2_CBC = pkcs_12PbeIds.branch(str6);
    }
}
