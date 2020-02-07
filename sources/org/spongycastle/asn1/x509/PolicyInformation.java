package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class PolicyInformation extends ASN1Object {
    private ASN1ObjectIdentifier policyIdentifier;
    private ASN1Sequence policyQualifiers;

    private PolicyInformation(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Bad sequence size: ");
            sb.append(aSN1Sequence.size());
            throw new IllegalArgumentException(sb.toString());
        }
        this.policyIdentifier = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() > 1) {
            this.policyQualifiers = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public PolicyInformation(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.policyIdentifier = aSN1ObjectIdentifier;
    }

    public PolicyInformation(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Sequence aSN1Sequence) {
        this.policyIdentifier = aSN1ObjectIdentifier;
        this.policyQualifiers = aSN1Sequence;
    }

    public static PolicyInformation getInstance(Object obj) {
        if (obj == null || (obj instanceof PolicyInformation)) {
            return (PolicyInformation) obj;
        }
        return new PolicyInformation(ASN1Sequence.getInstance(obj));
    }

    public ASN1ObjectIdentifier getPolicyIdentifier() {
        return this.policyIdentifier;
    }

    public ASN1Sequence getPolicyQualifiers() {
        return this.policyQualifiers;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.policyIdentifier);
        ASN1Sequence aSN1Sequence = this.policyQualifiers;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
