package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;

public class SubjectKeyIdentifier extends ASN1Object {
    private byte[] keyidentifier;

    public static SubjectKeyIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1OctetString.getInstance(aSN1TaggedObject, z));
    }

    public static SubjectKeyIdentifier getInstance(Object obj) {
        if (obj instanceof SubjectKeyIdentifier) {
            return (SubjectKeyIdentifier) obj;
        }
        if (obj != null) {
            return new SubjectKeyIdentifier(ASN1OctetString.getInstance(obj));
        }
        return null;
    }

    public static SubjectKeyIdentifier fromExtensions(Extensions extensions) {
        return getInstance(extensions.getExtensionParsedValue(Extension.subjectKeyIdentifier));
    }

    public SubjectKeyIdentifier(byte[] bArr) {
        this.keyidentifier = bArr;
    }

    protected SubjectKeyIdentifier(ASN1OctetString aSN1OctetString) {
        this.keyidentifier = aSN1OctetString.getOctets();
    }

    public byte[] getKeyIdentifier() {
        return this.keyidentifier;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DEROctetString(this.keyidentifier);
    }
}
