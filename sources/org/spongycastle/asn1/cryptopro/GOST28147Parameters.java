package org.spongycastle.asn1.cryptopro;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

public class GOST28147Parameters extends ASN1Object {

    /* renamed from: iv */
    private ASN1OctetString f847iv;
    private ASN1ObjectIdentifier paramSet;

    public static GOST28147Parameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static GOST28147Parameters getInstance(Object obj) {
        if (obj instanceof GOST28147Parameters) {
            return (GOST28147Parameters) obj;
        }
        if (obj != null) {
            return new GOST28147Parameters(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GOST28147Parameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f847iv = (ASN1OctetString) objects.nextElement();
        this.paramSet = (ASN1ObjectIdentifier) objects.nextElement();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f847iv);
        aSN1EncodableVector.add(this.paramSet);
        return new DERSequence(aSN1EncodableVector);
    }

    public ASN1ObjectIdentifier getEncryptionParamSet() {
        return this.paramSet;
    }

    public byte[] getIV() {
        return this.f847iv.getOctets();
    }
}
