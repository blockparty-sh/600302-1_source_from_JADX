package org.spongycastle.asn1.crmf;

import androidx.exifinterface.media.ExifInterface;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import p007at.favre.lib.slf4j_timber.BuildConfig;

public interface CRMFObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_ct_encKeyWithID = PKCSObjectIdentifiers.id_ct.branch(BuildConfig.BUILD_NUMBER);
    public static final ASN1ObjectIdentifier id_pkip = id_pkix.branch("5");
    public static final ASN1ObjectIdentifier id_pkix = new ASN1ObjectIdentifier("1.3.6.1.5.5.7");
    public static final ASN1ObjectIdentifier id_regCtrl;
    public static final ASN1ObjectIdentifier id_regCtrl_authenticator = id_regCtrl.branch(ExifInterface.GPS_MEASUREMENT_2D);
    public static final ASN1ObjectIdentifier id_regCtrl_pkiArchiveOptions = id_regCtrl.branch("4");
    public static final ASN1ObjectIdentifier id_regCtrl_pkiPublicationInfo = id_regCtrl.branch(ExifInterface.GPS_MEASUREMENT_3D);
    public static final ASN1ObjectIdentifier id_regCtrl_regToken;

    static {
        String str = "1";
        id_regCtrl = id_pkip.branch(str);
        id_regCtrl_regToken = id_regCtrl.branch(str);
    }
}
