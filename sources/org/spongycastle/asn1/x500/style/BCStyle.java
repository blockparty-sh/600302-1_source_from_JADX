package org.spongycastle.asn1.x500.style;

import androidx.exifinterface.media.ExifInterface;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.X500NameStyle;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;

public class BCStyle extends AbstractX500NameStyle {
    public static final ASN1ObjectIdentifier BUSINESS_CATEGORY = new ASN1ObjectIdentifier("2.5.4.15");

    /* renamed from: C */
    public static final ASN1ObjectIdentifier f881C = new ASN1ObjectIdentifier("2.5.4.6");

    /* renamed from: CN */
    public static final ASN1ObjectIdentifier f882CN = new ASN1ObjectIdentifier("2.5.4.3");
    public static final ASN1ObjectIdentifier COUNTRY_OF_CITIZENSHIP = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.4");
    public static final ASN1ObjectIdentifier COUNTRY_OF_RESIDENCE = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.5");
    public static final ASN1ObjectIdentifier DATE_OF_BIRTH = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.1");

    /* renamed from: DC */
    public static final ASN1ObjectIdentifier f883DC = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25");
    public static final ASN1ObjectIdentifier DMD_NAME = new ASN1ObjectIdentifier("2.5.4.54");
    public static final ASN1ObjectIdentifier DN_QUALIFIER = new ASN1ObjectIdentifier("2.5.4.46");
    private static final Hashtable DefaultLookUp = new Hashtable();
    private static final Hashtable DefaultSymbols = new Hashtable();

    /* renamed from: E */
    public static final ASN1ObjectIdentifier f884E = EmailAddress;
    public static final ASN1ObjectIdentifier EmailAddress = PKCSObjectIdentifiers.pkcs_9_at_emailAddress;
    public static final ASN1ObjectIdentifier GENDER = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.3");
    public static final ASN1ObjectIdentifier GENERATION = new ASN1ObjectIdentifier("2.5.4.44");
    public static final ASN1ObjectIdentifier GIVENNAME = new ASN1ObjectIdentifier("2.5.4.42");
    public static final ASN1ObjectIdentifier INITIALS = new ASN1ObjectIdentifier("2.5.4.43");
    public static final X500NameStyle INSTANCE = new BCStyle();

    /* renamed from: L */
    public static final ASN1ObjectIdentifier f885L = new ASN1ObjectIdentifier("2.5.4.7");
    public static final ASN1ObjectIdentifier NAME = X509ObjectIdentifiers.id_at_name;
    public static final ASN1ObjectIdentifier NAME_AT_BIRTH = new ASN1ObjectIdentifier("1.3.36.8.3.14");

    /* renamed from: O */
    public static final ASN1ObjectIdentifier f886O = new ASN1ObjectIdentifier("2.5.4.10");

    /* renamed from: OU */
    public static final ASN1ObjectIdentifier f887OU = new ASN1ObjectIdentifier("2.5.4.11");
    public static final ASN1ObjectIdentifier PLACE_OF_BIRTH = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.2");
    public static final ASN1ObjectIdentifier POSTAL_ADDRESS = new ASN1ObjectIdentifier("2.5.4.16");
    public static final ASN1ObjectIdentifier POSTAL_CODE = new ASN1ObjectIdentifier("2.5.4.17");
    public static final ASN1ObjectIdentifier PSEUDONYM = new ASN1ObjectIdentifier("2.5.4.65");
    public static final ASN1ObjectIdentifier SERIALNUMBER = f888SN;

    /* renamed from: SN */
    public static final ASN1ObjectIdentifier f888SN = new ASN1ObjectIdentifier("2.5.4.5");

    /* renamed from: ST */
    public static final ASN1ObjectIdentifier f889ST = new ASN1ObjectIdentifier("2.5.4.8");
    public static final ASN1ObjectIdentifier STREET = new ASN1ObjectIdentifier("2.5.4.9");
    public static final ASN1ObjectIdentifier SURNAME = new ASN1ObjectIdentifier("2.5.4.4");

    /* renamed from: T */
    public static final ASN1ObjectIdentifier f890T = new ASN1ObjectIdentifier("2.5.4.12");
    public static final ASN1ObjectIdentifier TELEPHONE_NUMBER = X509ObjectIdentifiers.id_at_telephoneNumber;
    public static final ASN1ObjectIdentifier UID = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1");
    public static final ASN1ObjectIdentifier UNIQUE_IDENTIFIER = new ASN1ObjectIdentifier("2.5.4.45");
    public static final ASN1ObjectIdentifier UnstructuredAddress = PKCSObjectIdentifiers.pkcs_9_at_unstructuredAddress;
    public static final ASN1ObjectIdentifier UnstructuredName = PKCSObjectIdentifiers.pkcs_9_at_unstructuredName;
    protected final Hashtable defaultLookUp = copyHashTable(DefaultLookUp);
    protected final Hashtable defaultSymbols = copyHashTable(DefaultSymbols);

    static {
        DefaultSymbols.put(f881C, "C");
        DefaultSymbols.put(f886O, "O");
        DefaultSymbols.put(f890T, ExifInterface.GPS_DIRECTION_TRUE);
        DefaultSymbols.put(f887OU, "OU");
        DefaultSymbols.put(f882CN, "CN");
        DefaultSymbols.put(f885L, "L");
        DefaultSymbols.put(f889ST, "ST");
        DefaultSymbols.put(f888SN, "SERIALNUMBER");
        DefaultSymbols.put(EmailAddress, ExifInterface.LONGITUDE_EAST);
        DefaultSymbols.put(f883DC, "DC");
        DefaultSymbols.put(UID, "UID");
        DefaultSymbols.put(STREET, "STREET");
        DefaultSymbols.put(SURNAME, "SURNAME");
        DefaultSymbols.put(GIVENNAME, "GIVENNAME");
        DefaultSymbols.put(INITIALS, "INITIALS");
        DefaultSymbols.put(GENERATION, "GENERATION");
        DefaultSymbols.put(UnstructuredAddress, "unstructuredAddress");
        DefaultSymbols.put(UnstructuredName, "unstructuredName");
        DefaultSymbols.put(UNIQUE_IDENTIFIER, "UniqueIdentifier");
        DefaultSymbols.put(DN_QUALIFIER, "DN");
        DefaultSymbols.put(PSEUDONYM, "Pseudonym");
        DefaultSymbols.put(POSTAL_ADDRESS, "PostalAddress");
        DefaultSymbols.put(NAME_AT_BIRTH, "NameAtBirth");
        DefaultSymbols.put(COUNTRY_OF_CITIZENSHIP, "CountryOfCitizenship");
        DefaultSymbols.put(COUNTRY_OF_RESIDENCE, "CountryOfResidence");
        DefaultSymbols.put(GENDER, "Gender");
        DefaultSymbols.put(PLACE_OF_BIRTH, "PlaceOfBirth");
        DefaultSymbols.put(DATE_OF_BIRTH, "DateOfBirth");
        DefaultSymbols.put(POSTAL_CODE, "PostalCode");
        DefaultSymbols.put(BUSINESS_CATEGORY, "BusinessCategory");
        DefaultSymbols.put(TELEPHONE_NUMBER, "TelephoneNumber");
        DefaultSymbols.put(NAME, "Name");
        DefaultLookUp.put("c", f881C);
        DefaultLookUp.put("o", f886O);
        DefaultLookUp.put("t", f890T);
        DefaultLookUp.put("ou", f887OU);
        DefaultLookUp.put("cn", f882CN);
        DefaultLookUp.put("l", f885L);
        DefaultLookUp.put("st", f889ST);
        DefaultLookUp.put("sn", f888SN);
        DefaultLookUp.put("serialnumber", f888SN);
        DefaultLookUp.put("street", STREET);
        DefaultLookUp.put("emailaddress", f884E);
        DefaultLookUp.put("dc", f883DC);
        DefaultLookUp.put("e", f884E);
        DefaultLookUp.put("uid", UID);
        DefaultLookUp.put("surname", SURNAME);
        DefaultLookUp.put("givenname", GIVENNAME);
        DefaultLookUp.put("initials", INITIALS);
        DefaultLookUp.put("generation", GENERATION);
        DefaultLookUp.put("unstructuredaddress", UnstructuredAddress);
        DefaultLookUp.put("unstructuredname", UnstructuredName);
        DefaultLookUp.put("uniqueidentifier", UNIQUE_IDENTIFIER);
        DefaultLookUp.put("dn", DN_QUALIFIER);
        DefaultLookUp.put("pseudonym", PSEUDONYM);
        DefaultLookUp.put("postaladdress", POSTAL_ADDRESS);
        DefaultLookUp.put("nameofbirth", NAME_AT_BIRTH);
        DefaultLookUp.put("countryofcitizenship", COUNTRY_OF_CITIZENSHIP);
        DefaultLookUp.put("countryofresidence", COUNTRY_OF_RESIDENCE);
        DefaultLookUp.put("gender", GENDER);
        DefaultLookUp.put("placeofbirth", PLACE_OF_BIRTH);
        DefaultLookUp.put("dateofbirth", DATE_OF_BIRTH);
        DefaultLookUp.put("postalcode", POSTAL_CODE);
        DefaultLookUp.put("businesscategory", BUSINESS_CATEGORY);
        DefaultLookUp.put("telephonenumber", TELEPHONE_NUMBER);
        DefaultLookUp.put("name", NAME);
    }

    protected BCStyle() {
    }

    /* access modifiers changed from: protected */
    public ASN1Encodable encodeStringValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (aSN1ObjectIdentifier.equals(EmailAddress) || aSN1ObjectIdentifier.equals(f883DC)) {
            return new DERIA5String(str);
        }
        if (aSN1ObjectIdentifier.equals(DATE_OF_BIRTH)) {
            return new ASN1GeneralizedTime(str);
        }
        if (aSN1ObjectIdentifier.equals(f881C) || aSN1ObjectIdentifier.equals(f888SN) || aSN1ObjectIdentifier.equals(DN_QUALIFIER) || aSN1ObjectIdentifier.equals(TELEPHONE_NUMBER)) {
            return new DERPrintableString(str);
        }
        return super.encodeStringValue(aSN1ObjectIdentifier, str);
    }

    public String oidToDisplayName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) DefaultSymbols.get(aSN1ObjectIdentifier);
    }

    public String[] oidToAttrNames(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return IETFUtils.findAttrNamesForOID(aSN1ObjectIdentifier, this.defaultLookUp);
    }

    public ASN1ObjectIdentifier attrNameToOID(String str) {
        return IETFUtils.decodeAttrName(str, this.defaultLookUp);
    }

    public RDN[] fromString(String str) {
        return IETFUtils.rDNsFromString(str, this);
    }

    public String toString(X500Name x500Name) {
        StringBuffer stringBuffer = new StringBuffer();
        RDN[] rDNs = x500Name.getRDNs();
        boolean z = true;
        for (RDN appendRDN : rDNs) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append(',');
            }
            IETFUtils.appendRDN(stringBuffer, appendRDN, this.defaultSymbols);
        }
        return stringBuffer.toString();
    }
}
