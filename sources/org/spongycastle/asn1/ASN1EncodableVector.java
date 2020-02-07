package org.spongycastle.asn1;

import java.util.Enumeration;
import java.util.Vector;

public class ASN1EncodableVector {

    /* renamed from: v */
    Vector f834v = new Vector();

    public void add(ASN1Encodable aSN1Encodable) {
        this.f834v.addElement(aSN1Encodable);
    }

    public void addAll(ASN1EncodableVector aSN1EncodableVector) {
        Enumeration elements = aSN1EncodableVector.f834v.elements();
        while (elements.hasMoreElements()) {
            this.f834v.addElement(elements.nextElement());
        }
    }

    public ASN1Encodable get(int i) {
        return (ASN1Encodable) this.f834v.elementAt(i);
    }

    public int size() {
        return this.f834v.size();
    }
}
