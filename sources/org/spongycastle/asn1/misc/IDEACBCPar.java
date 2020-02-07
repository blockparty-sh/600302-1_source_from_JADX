package org.spongycastle.asn1.misc;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;

public class IDEACBCPar extends ASN1Object {

    /* renamed from: iv */
    ASN1OctetString f862iv;

    public static IDEACBCPar getInstance(Object obj) {
        if (obj instanceof IDEACBCPar) {
            return (IDEACBCPar) obj;
        }
        if (obj != null) {
            return new IDEACBCPar(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public IDEACBCPar(byte[] bArr) {
        this.f862iv = new DEROctetString(bArr);
    }

    public IDEACBCPar(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 1) {
            this.f862iv = (ASN1OctetString) aSN1Sequence.getObjectAt(0);
        } else {
            this.f862iv = null;
        }
    }

    public byte[] getIV() {
        ASN1OctetString aSN1OctetString = this.f862iv;
        if (aSN1OctetString != null) {
            return aSN1OctetString.getOctets();
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1OctetString aSN1OctetString = this.f862iv;
        if (aSN1OctetString != null) {
            aSN1EncodableVector.add(aSN1OctetString);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
