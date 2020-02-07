package org.spongycastle.asn1.p022x9;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

/* renamed from: org.spongycastle.asn1.x9.OtherInfo */
public class OtherInfo extends ASN1Object {
    private KeySpecificInfo keyInfo;
    private ASN1OctetString partyAInfo;
    private ASN1OctetString suppPubInfo;

    public OtherInfo(KeySpecificInfo keySpecificInfo, ASN1OctetString aSN1OctetString, ASN1OctetString aSN1OctetString2) {
        this.keyInfo = keySpecificInfo;
        this.partyAInfo = aSN1OctetString;
        this.suppPubInfo = aSN1OctetString2;
    }

    public OtherInfo(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.keyInfo = new KeySpecificInfo((ASN1Sequence) objects.nextElement());
        while (objects.hasMoreElements()) {
            DERTaggedObject dERTaggedObject = (DERTaggedObject) objects.nextElement();
            if (dERTaggedObject.getTagNo() == 0) {
                this.partyAInfo = (ASN1OctetString) dERTaggedObject.getObject();
            } else if (dERTaggedObject.getTagNo() == 2) {
                this.suppPubInfo = (ASN1OctetString) dERTaggedObject.getObject();
            }
        }
    }

    public KeySpecificInfo getKeyInfo() {
        return this.keyInfo;
    }

    public ASN1OctetString getPartyAInfo() {
        return this.partyAInfo;
    }

    public ASN1OctetString getSuppPubInfo() {
        return this.suppPubInfo;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.keyInfo);
        ASN1OctetString aSN1OctetString = this.partyAInfo;
        if (aSN1OctetString != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, aSN1OctetString));
        }
        aSN1EncodableVector.add(new DERTaggedObject(2, this.suppPubInfo));
        return new DERSequence(aSN1EncodableVector);
    }
}
