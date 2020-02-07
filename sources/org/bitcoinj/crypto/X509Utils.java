package org.bitcoinj.crypto;

import com.google.common.base.Joiner;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.x500.AttributeTypeAndValue;
import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.style.RFC4519Style;

public class X509Utils {
    @Nullable
    public static String getDisplayNameFromCertificate(@Nonnull X509Certificate x509Certificate, boolean z) throws CertificateParsingException {
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        for (RDN first : new X500Name(x509Certificate.getSubjectX500Principal().getName()).getRDNs()) {
            AttributeTypeAndValue first2 = first.getFirst();
            String string = ((ASN1String) first2.getValue()).getString();
            ASN1ObjectIdentifier type = first2.getType();
            if (type.equals(RFC4519Style.f892cn)) {
                str2 = string;
            } else if (type.equals(RFC4519Style.f895o)) {
                str3 = string;
            } else if (type.equals(RFC4519Style.f894l)) {
                str4 = string;
            } else if (type.equals(RFC4519Style.f891c)) {
                str5 = string;
            }
        }
        Collection<List> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
        if (subjectAlternativeNames != null) {
            for (List list : subjectAlternativeNames) {
                if (((Integer) list.get(0)).intValue() == 1) {
                    str = (String) list.get(1);
                }
            }
        }
        if (str3 == null) {
            return str2 != null ? str2 : str;
        }
        if (z) {
            str3 = Joiner.m96on(", ").skipNulls().join(str3, str4, str5);
        }
        return str3;
    }

    public static KeyStore loadKeyStore(String str, @Nullable String str2, InputStream inputStream) throws KeyStoreException {
        try {
            KeyStore instance = KeyStore.getInstance(str);
            instance.load(inputStream, str2 != null ? str2.toCharArray() : null);
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
            return instance;
        } catch (IOException e) {
            throw new KeyStoreException(e);
        } catch (GeneralSecurityException e2) {
            throw new KeyStoreException(e2);
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
            throw th;
        }
    }
}
