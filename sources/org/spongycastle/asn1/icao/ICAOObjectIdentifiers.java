package org.spongycastle.asn1.icao;

import androidx.exifinterface.media.ExifInterface;
import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface ICAOObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_icao = new ASN1ObjectIdentifier("2.23.136");
    public static final ASN1ObjectIdentifier id_icao_aaProtocolObject = id_icao_mrtd_security.branch("5");
    public static final ASN1ObjectIdentifier id_icao_cscaMasterList = id_icao_mrtd_security.branch(ExifInterface.GPS_MEASUREMENT_2D);
    public static final ASN1ObjectIdentifier id_icao_cscaMasterListSigningKey = id_icao_mrtd_security.branch(ExifInterface.GPS_MEASUREMENT_3D);
    public static final ASN1ObjectIdentifier id_icao_documentTypeList = id_icao_mrtd_security.branch("4");
    public static final ASN1ObjectIdentifier id_icao_extensions = id_icao_mrtd_security.branch("6");
    public static final ASN1ObjectIdentifier id_icao_extensions_namechangekeyrollover;
    public static final ASN1ObjectIdentifier id_icao_ldsSecurityObject;
    public static final ASN1ObjectIdentifier id_icao_mrtd;
    public static final ASN1ObjectIdentifier id_icao_mrtd_security;

    static {
        String str = "1";
        id_icao_mrtd = id_icao.branch(str);
        id_icao_mrtd_security = id_icao_mrtd.branch(str);
        id_icao_ldsSecurityObject = id_icao_mrtd_security.branch(str);
        id_icao_extensions_namechangekeyrollover = id_icao_extensions.branch(str);
    }
}