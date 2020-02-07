package org.spongycastle.crypto.tls;

import org.spongycastle.asn1.x509.Certificate;

public interface CertificateVerifyer {
    boolean isValid(Certificate[] certificateArr);
}
