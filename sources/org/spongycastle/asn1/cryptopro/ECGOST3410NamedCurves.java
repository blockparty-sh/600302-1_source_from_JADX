package org.spongycastle.asn1.cryptopro;

import com.leanplum.core.BuildConfig;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.math.p025ec.ECConstants;
import org.spongycastle.math.p025ec.ECCurve.C3639Fp;

public class ECGOST3410NamedCurves {
    static final Hashtable names = new Hashtable();
    static final Hashtable objIds = new Hashtable();
    static final Hashtable params = new Hashtable();

    static {
        String str = "115792089237316195423570985008687907853269984665640564039457584007913129639319";
        BigInteger bigInteger = new BigInteger(str);
        String str2 = "115792089237316195423570985008687907853073762908499243225378155805079068850323";
        BigInteger bigInteger2 = new BigInteger(str2);
        String str3 = "115792089237316195423570985008687907853269984665640564039457584007913129639316";
        String str4 = "166";
        C3639Fp fp = new C3639Fp(bigInteger, new BigInteger(str3), new BigInteger(str4), bigInteger2, ECConstants.ONE);
        String str5 = "1";
        String str6 = "64033881142927202683649881450433473985931760268884941288852745803908878638612";
        params.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_A, new ECDomainParameters(fp, fp.createPoint(new BigInteger(str5), new BigInteger(str6)), bigInteger2));
        BigInteger bigInteger3 = new BigInteger(str);
        BigInteger bigInteger4 = new BigInteger(str2);
        C3639Fp fp2 = new C3639Fp(bigInteger3, new BigInteger(str3), new BigInteger(str4), bigInteger4, ECConstants.ONE);
        params.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchA, new ECDomainParameters(fp2, fp2.createPoint(new BigInteger(str5), new BigInteger(str6)), bigInteger4));
        BigInteger bigInteger5 = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564823193");
        BigInteger bigInteger6 = new BigInteger("57896044618658097711785492504343953927102133160255826820068844496087732066703");
        C3639Fp fp3 = new C3639Fp(bigInteger5, new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564823190"), new BigInteger("28091019353058090096996979000309560759124368558014865957655842872397301267595"), bigInteger6, ECConstants.ONE);
        params.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_B, new ECDomainParameters(fp3, fp3.createPoint(new BigInteger(str5), new BigInteger("28792665814854611296992347458380284135028636778229113005756334730996303888124")), bigInteger6));
        String str7 = "70390085352083305199547718019018437841079516630045180471284346843705633502619";
        BigInteger bigInteger7 = new BigInteger(str7);
        String str8 = "70390085352083305199547718019018437840920882647164081035322601458352298396601";
        BigInteger bigInteger8 = new BigInteger(str8);
        String str9 = "70390085352083305199547718019018437841079516630045180471284346843705633502616";
        String str10 = "32858";
        C3639Fp fp4 = new C3639Fp(bigInteger7, new BigInteger(str9), new BigInteger(str10), bigInteger8, ECConstants.ONE);
        String str11 = BuildConfig.BUILD_NUMBER;
        String str12 = "29818893917731240733471273240314769927240550812383695689146495261604565990247";
        params.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchB, new ECDomainParameters(fp4, fp4.createPoint(new BigInteger(str11), new BigInteger(str12)), bigInteger8));
        BigInteger bigInteger9 = new BigInteger(str7);
        BigInteger bigInteger10 = new BigInteger(str8);
        C3639Fp fp5 = new C3639Fp(bigInteger9, new BigInteger(str9), new BigInteger(str10), bigInteger10, ECConstants.ONE);
        params.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_C, new ECDomainParameters(fp5, fp5.createPoint(new BigInteger(str11), new BigInteger(str12)), bigInteger10));
        String str13 = "GostR3410-2001-CryptoPro-A";
        objIds.put(str13, CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_A);
        String str14 = "GostR3410-2001-CryptoPro-B";
        objIds.put(str14, CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_B);
        String str15 = "GostR3410-2001-CryptoPro-C";
        objIds.put(str15, CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_C);
        objIds.put("GostR3410-2001-CryptoPro-XchA", CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchA);
        objIds.put("GostR3410-2001-CryptoPro-XchB", CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchB);
        names.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_A, str13);
        names.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_B, str14);
        names.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_C, str15);
        names.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchA, "GostR3410-2001-CryptoPro-XchA");
        names.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchB, "GostR3410-2001-CryptoPro-XchB");
    }

    public static ECDomainParameters getByOID(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (ECDomainParameters) params.get(aSN1ObjectIdentifier);
    }

    public static Enumeration getNames() {
        return objIds.keys();
    }

    public static ECDomainParameters getByName(String str) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) objIds.get(str);
        if (aSN1ObjectIdentifier != null) {
            return (ECDomainParameters) params.get(aSN1ObjectIdentifier);
        }
        return null;
    }

    public static String getName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) names.get(aSN1ObjectIdentifier);
    }

    public static ASN1ObjectIdentifier getOID(String str) {
        return (ASN1ObjectIdentifier) objIds.get(str);
    }
}
