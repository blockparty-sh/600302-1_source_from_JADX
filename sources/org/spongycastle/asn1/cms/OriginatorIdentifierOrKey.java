package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.SubjectKeyIdentifier;

public class OriginatorIdentifierOrKey extends ASN1Object implements ASN1Choice {

    /* renamed from: id */
    private ASN1Encodable f838id;

    public OriginatorIdentifierOrKey(IssuerAndSerialNumber issuerAndSerialNumber) {
        this.f838id = issuerAndSerialNumber;
    }

    public OriginatorIdentifierOrKey(ASN1OctetString aSN1OctetString) {
        this(new SubjectKeyIdentifier(aSN1OctetString.getOctets()));
    }

    public OriginatorIdentifierOrKey(SubjectKeyIdentifier subjectKeyIdentifier) {
        this.f838id = new DERTaggedObject(false, 0, subjectKeyIdentifier);
    }

    public OriginatorIdentifierOrKey(OriginatorPublicKey originatorPublicKey) {
        this.f838id = new DERTaggedObject(false, 1, originatorPublicKey);
    }

    public OriginatorIdentifierOrKey(ASN1Primitive aSN1Primitive) {
        this.f838id = aSN1Primitive;
    }

    public static OriginatorIdentifierOrKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            return getInstance(aSN1TaggedObject.getObject());
        }
        throw new IllegalArgumentException("Can't implicitly tag OriginatorIdentifierOrKey");
    }

    public static OriginatorIdentifierOrKey getInstance(Object obj) {
        if (obj == null || (obj instanceof OriginatorIdentifierOrKey)) {
            return (OriginatorIdentifierOrKey) obj;
        }
        if (obj instanceof IssuerAndSerialNumber) {
            return new OriginatorIdentifierOrKey((IssuerAndSerialNumber) obj);
        }
        if (obj instanceof SubjectKeyIdentifier) {
            return new OriginatorIdentifierOrKey((SubjectKeyIdentifier) obj);
        }
        if (obj instanceof OriginatorPublicKey) {
            return new OriginatorIdentifierOrKey((OriginatorPublicKey) obj);
        }
        if (obj instanceof ASN1TaggedObject) {
            return new OriginatorIdentifierOrKey((ASN1Primitive) (ASN1TaggedObject) obj);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid OriginatorIdentifierOrKey: ");
        sb.append(obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public ASN1Encodable getId() {
        return this.f838id;
    }

    public IssuerAndSerialNumber getIssuerAndSerialNumber() {
        ASN1Encodable aSN1Encodable = this.f838id;
        if (aSN1Encodable instanceof IssuerAndSerialNumber) {
            return (IssuerAndSerialNumber) aSN1Encodable;
        }
        return null;
    }

    public SubjectKeyIdentifier getSubjectKeyIdentifier() {
        ASN1Encodable aSN1Encodable = this.f838id;
        if (!(aSN1Encodable instanceof ASN1TaggedObject) || ((ASN1TaggedObject) aSN1Encodable).getTagNo() != 0) {
            return null;
        }
        return SubjectKeyIdentifier.getInstance((ASN1TaggedObject) this.f838id, false);
    }

    public OriginatorPublicKey getOriginatorKey() {
        ASN1Encodable aSN1Encodable = this.f838id;
        if (!(aSN1Encodable instanceof ASN1TaggedObject) || ((ASN1TaggedObject) aSN1Encodable).getTagNo() != 1) {
            return null;
        }
        return OriginatorPublicKey.getInstance((ASN1TaggedObject) this.f838id, false);
    }

    public ASN1Primitive toASN1Primitive() {
        return this.f838id.toASN1Primitive();
    }
}
