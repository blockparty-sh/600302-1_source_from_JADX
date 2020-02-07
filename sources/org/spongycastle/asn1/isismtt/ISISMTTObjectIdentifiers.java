package org.spongycastle.asn1.isismtt;

import androidx.exifinterface.media.ExifInterface;
import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface ISISMTTObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_isismtt = new ASN1ObjectIdentifier("1.3.36.8");
    public static final ASN1ObjectIdentifier id_isismtt_at;
    public static final ASN1ObjectIdentifier id_isismtt_at_PKReference = id_isismtt_at.branch("7");
    public static final ASN1ObjectIdentifier id_isismtt_at_additionalInformation = id_isismtt_at.branch("15");
    public static final ASN1ObjectIdentifier id_isismtt_at_admission;
    public static final ASN1ObjectIdentifier id_isismtt_at_certHash = id_isismtt_at.branch("13");
    public static final ASN1ObjectIdentifier id_isismtt_at_certInDirSince = id_isismtt_at.branch("12");
    public static final ASN1ObjectIdentifier id_isismtt_at_dateOfCertGen;
    public static final ASN1ObjectIdentifier id_isismtt_at_declarationOfMajority = id_isismtt_at.branch("5");
    public static final ASN1ObjectIdentifier id_isismtt_at_iCCSN = id_isismtt_at.branch("6");
    public static final ASN1ObjectIdentifier id_isismtt_at_liabilityLimitationFlag = new ASN1ObjectIdentifier("0.2.262.1.10.12.0");
    public static final ASN1ObjectIdentifier id_isismtt_at_monetaryLimit = id_isismtt_at.branch("4");
    public static final ASN1ObjectIdentifier id_isismtt_at_nameAtBirth = id_isismtt_at.branch("14");
    public static final ASN1ObjectIdentifier id_isismtt_at_namingAuthorities = id_isismtt_at.branch("11");
    public static final ASN1ObjectIdentifier id_isismtt_at_procuration = id_isismtt_at.branch(ExifInterface.GPS_MEASUREMENT_2D);
    public static final ASN1ObjectIdentifier id_isismtt_at_requestedCertificate = id_isismtt_at.branch("10");
    public static final ASN1ObjectIdentifier id_isismtt_at_restriction = id_isismtt_at.branch("8");
    public static final ASN1ObjectIdentifier id_isismtt_at_retrieveIfAllowed = id_isismtt_at.branch("9");
    public static final ASN1ObjectIdentifier id_isismtt_cp;
    public static final ASN1ObjectIdentifier id_isismtt_cp_accredited;

    static {
        String str = "1";
        id_isismtt_cp = id_isismtt.branch(str);
        id_isismtt_cp_accredited = id_isismtt_cp.branch(str);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = id_isismtt;
        String str2 = ExifInterface.GPS_MEASUREMENT_3D;
        id_isismtt_at = aSN1ObjectIdentifier.branch(str2);
        id_isismtt_at_dateOfCertGen = id_isismtt_at.branch(str);
        id_isismtt_at_admission = id_isismtt_at.branch(str2);
    }
}
