package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.PolicyInformation;

public class PathProcInput extends ASN1Object {
    private PolicyInformation[] acceptablePolicySet;
    private boolean explicitPolicyReqd = false;
    private boolean inhibitAnyPolicy = false;
    private boolean inhibitPolicyMapping = false;

    public PathProcInput(PolicyInformation[] policyInformationArr) {
        this.acceptablePolicySet = policyInformationArr;
    }

    public PathProcInput(PolicyInformation[] policyInformationArr, boolean z, boolean z2, boolean z3) {
        this.acceptablePolicySet = policyInformationArr;
        this.inhibitPolicyMapping = z;
        this.explicitPolicyReqd = z2;
        this.inhibitAnyPolicy = z3;
    }

    private static PolicyInformation[] fromSequence(ASN1Sequence aSN1Sequence) {
        PolicyInformation[] policyInformationArr = new PolicyInformation[aSN1Sequence.size()];
        for (int i = 0; i != policyInformationArr.length; i++) {
            policyInformationArr[i] = PolicyInformation.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return policyInformationArr;
    }

    public static PathProcInput getInstance(Object obj) {
        if (obj instanceof PathProcInput) {
            return (PathProcInput) obj;
        }
        if (obj == null) {
            return null;
        }
        ASN1Sequence instance = ASN1Sequence.getInstance(obj);
        PathProcInput pathProcInput = new PathProcInput(fromSequence(ASN1Sequence.getInstance(instance.getObjectAt(0))));
        for (int i = 1; i < instance.size(); i++) {
            ASN1Encodable objectAt = instance.getObjectAt(i);
            if (objectAt instanceof ASN1Boolean) {
                pathProcInput.setInhibitPolicyMapping(ASN1Boolean.getInstance((Object) objectAt).isTrue());
            } else if (objectAt instanceof ASN1TaggedObject) {
                ASN1TaggedObject instance2 = ASN1TaggedObject.getInstance(objectAt);
                int tagNo = instance2.getTagNo();
                if (tagNo == 0) {
                    pathProcInput.setExplicitPolicyReqd(ASN1Boolean.getInstance(instance2, false).isTrue());
                } else if (tagNo == 1) {
                    pathProcInput.setInhibitAnyPolicy(ASN1Boolean.getInstance(instance2, false).isTrue());
                }
            }
        }
        return pathProcInput;
    }

    public static PathProcInput getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        int i = 0;
        while (true) {
            PolicyInformation[] policyInformationArr = this.acceptablePolicySet;
            if (i == policyInformationArr.length) {
                break;
            }
            aSN1EncodableVector2.add(policyInformationArr[i]);
            i++;
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        boolean z = this.inhibitPolicyMapping;
        if (z) {
            aSN1EncodableVector.add(new ASN1Boolean(z));
        }
        boolean z2 = this.explicitPolicyReqd;
        if (z2) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, new ASN1Boolean(z2)));
        }
        boolean z3 = this.inhibitAnyPolicy;
        if (z3) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, new ASN1Boolean(z3)));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PathProcInput: {\nacceptablePolicySet: ");
        sb.append(this.acceptablePolicySet);
        String str = "\n";
        sb.append(str);
        sb.append("inhibitPolicyMapping: ");
        sb.append(this.inhibitPolicyMapping);
        sb.append(str);
        sb.append("explicitPolicyReqd: ");
        sb.append(this.explicitPolicyReqd);
        sb.append(str);
        sb.append("inhibitAnyPolicy: ");
        sb.append(this.inhibitAnyPolicy);
        sb.append(str);
        sb.append("}\n");
        return sb.toString();
    }

    public PolicyInformation[] getAcceptablePolicySet() {
        return this.acceptablePolicySet;
    }

    public boolean isInhibitPolicyMapping() {
        return this.inhibitPolicyMapping;
    }

    private void setInhibitPolicyMapping(boolean z) {
        this.inhibitPolicyMapping = z;
    }

    public boolean isExplicitPolicyReqd() {
        return this.explicitPolicyReqd;
    }

    private void setExplicitPolicyReqd(boolean z) {
        this.explicitPolicyReqd = z;
    }

    public boolean isInhibitAnyPolicy() {
        return this.inhibitAnyPolicy;
    }

    private void setInhibitAnyPolicy(boolean z) {
        this.inhibitAnyPolicy = z;
    }
}
