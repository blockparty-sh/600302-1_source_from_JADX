package org.spongycastle.asn1.esf;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.ocsp.BasicOCSPResponse;
import org.spongycastle.asn1.x509.CertificateList;

public class RevocationValues extends ASN1Object {
    private ASN1Sequence crlVals;
    private ASN1Sequence ocspVals;
    private OtherRevVals otherRevVals;

    public static RevocationValues getInstance(Object obj) {
        if (obj instanceof RevocationValues) {
            return (RevocationValues) obj;
        }
        if (obj != null) {
            return new RevocationValues(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private RevocationValues(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() <= 3) {
            Enumeration objects = aSN1Sequence.getObjects();
            while (objects.hasMoreElements()) {
                DERTaggedObject dERTaggedObject = (DERTaggedObject) objects.nextElement();
                int tagNo = dERTaggedObject.getTagNo();
                if (tagNo == 0) {
                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) dERTaggedObject.getObject();
                    Enumeration objects2 = aSN1Sequence2.getObjects();
                    while (objects2.hasMoreElements()) {
                        CertificateList.getInstance(objects2.nextElement());
                    }
                    this.crlVals = aSN1Sequence2;
                } else if (tagNo == 1) {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) dERTaggedObject.getObject();
                    Enumeration objects3 = aSN1Sequence3.getObjects();
                    while (objects3.hasMoreElements()) {
                        BasicOCSPResponse.getInstance(objects3.nextElement());
                    }
                    this.ocspVals = aSN1Sequence3;
                } else if (tagNo == 2) {
                    this.otherRevVals = OtherRevVals.getInstance(dERTaggedObject.getObject());
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("invalid tag: ");
                    sb.append(dERTaggedObject.getTagNo());
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Bad sequence size: ");
        sb2.append(aSN1Sequence.size());
        throw new IllegalArgumentException(sb2.toString());
    }

    public RevocationValues(CertificateList[] certificateListArr, BasicOCSPResponse[] basicOCSPResponseArr, OtherRevVals otherRevVals2) {
        if (certificateListArr != null) {
            this.crlVals = new DERSequence((ASN1Encodable[]) certificateListArr);
        }
        if (basicOCSPResponseArr != null) {
            this.ocspVals = new DERSequence((ASN1Encodable[]) basicOCSPResponseArr);
        }
        this.otherRevVals = otherRevVals2;
    }

    public CertificateList[] getCrlVals() {
        ASN1Sequence aSN1Sequence = this.crlVals;
        if (aSN1Sequence == null) {
            return new CertificateList[0];
        }
        CertificateList[] certificateListArr = new CertificateList[aSN1Sequence.size()];
        for (int i = 0; i < certificateListArr.length; i++) {
            certificateListArr[i] = CertificateList.getInstance(this.crlVals.getObjectAt(i));
        }
        return certificateListArr;
    }

    public BasicOCSPResponse[] getOcspVals() {
        ASN1Sequence aSN1Sequence = this.ocspVals;
        if (aSN1Sequence == null) {
            return new BasicOCSPResponse[0];
        }
        BasicOCSPResponse[] basicOCSPResponseArr = new BasicOCSPResponse[aSN1Sequence.size()];
        for (int i = 0; i < basicOCSPResponseArr.length; i++) {
            basicOCSPResponseArr[i] = BasicOCSPResponse.getInstance(this.ocspVals.getObjectAt(i));
        }
        return basicOCSPResponseArr;
    }

    public OtherRevVals getOtherRevVals() {
        return this.otherRevVals;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Sequence aSN1Sequence = this.crlVals;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, aSN1Sequence));
        }
        ASN1Sequence aSN1Sequence2 = this.ocspVals;
        if (aSN1Sequence2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, aSN1Sequence2));
        }
        OtherRevVals otherRevVals2 = this.otherRevVals;
        if (otherRevVals2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, otherRevVals2.toASN1Primitive()));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
