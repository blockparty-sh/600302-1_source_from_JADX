package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AttributeCertificate;
import org.spongycastle.asn1.x509.Certificate;

public class CMPCertificate extends ASN1Object implements ASN1Choice {
    private AttributeCertificate x509v2AttrCert;
    private Certificate x509v3PKCert;

    public CMPCertificate(AttributeCertificate attributeCertificate) {
        this.x509v2AttrCert = attributeCertificate;
    }

    public CMPCertificate(Certificate certificate) {
        if (certificate.getVersionNumber() == 3) {
            this.x509v3PKCert = certificate;
            return;
        }
        throw new IllegalArgumentException("only version 3 certificates allowed");
    }

    public static CMPCertificate getInstance(Object obj) {
        if (obj == null || (obj instanceof CMPCertificate)) {
            return (CMPCertificate) obj;
        }
        if ((obj instanceof ASN1Sequence) || (obj instanceof byte[])) {
            return new CMPCertificate(Certificate.getInstance(obj));
        }
        if (obj instanceof ASN1TaggedObject) {
            return new CMPCertificate(AttributeCertificate.getInstance(((ASN1TaggedObject) obj).getObject()));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid object: ");
        sb.append(obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean isX509v3PKCert() {
        return this.x509v3PKCert != null;
    }

    public Certificate getX509v3PKCert() {
        return this.x509v3PKCert;
    }

    public AttributeCertificate getX509v2AttrCert() {
        return this.x509v2AttrCert;
    }

    public ASN1Primitive toASN1Primitive() {
        AttributeCertificate attributeCertificate = this.x509v2AttrCert;
        if (attributeCertificate != null) {
            return new DERTaggedObject(true, 1, attributeCertificate);
        }
        return this.x509v3PKCert.toASN1Primitive();
    }
}
