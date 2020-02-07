package org.spongycastle.asn1.p022x9;

import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;

/* renamed from: org.spongycastle.asn1.x9.DHPublicKey */
public class DHPublicKey extends ASN1Object {

    /* renamed from: y */
    private ASN1Integer f920y;

    public static DHPublicKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Integer.getInstance(aSN1TaggedObject, z));
    }

    public static DHPublicKey getInstance(Object obj) {
        if (obj == null || (obj instanceof DHPublicKey)) {
            return (DHPublicKey) obj;
        }
        if (obj instanceof ASN1Integer) {
            return new DHPublicKey((ASN1Integer) obj);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid DHPublicKey: ");
        sb.append(obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public DHPublicKey(ASN1Integer aSN1Integer) {
        if (aSN1Integer != null) {
            this.f920y = aSN1Integer;
            return;
        }
        throw new IllegalArgumentException("'y' cannot be null");
    }

    public ASN1Integer getY() {
        return this.f920y;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.f920y;
    }
}
