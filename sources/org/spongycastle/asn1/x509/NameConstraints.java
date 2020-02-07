package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class NameConstraints extends ASN1Object {
    private GeneralSubtree[] excluded;
    private GeneralSubtree[] permitted;

    public static NameConstraints getInstance(Object obj) {
        if (obj instanceof NameConstraints) {
            return (NameConstraints) obj;
        }
        if (obj != null) {
            return new NameConstraints(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private NameConstraints(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject instance = ASN1TaggedObject.getInstance(objects.nextElement());
            int tagNo = instance.getTagNo();
            if (tagNo == 0) {
                this.permitted = createArray(ASN1Sequence.getInstance(instance, false));
            } else if (tagNo == 1) {
                this.excluded = createArray(ASN1Sequence.getInstance(instance, false));
            }
        }
    }

    public NameConstraints(GeneralSubtree[] generalSubtreeArr, GeneralSubtree[] generalSubtreeArr2) {
        if (generalSubtreeArr != null) {
            this.permitted = generalSubtreeArr;
        }
        if (generalSubtreeArr2 != null) {
            this.excluded = generalSubtreeArr2;
        }
    }

    private GeneralSubtree[] createArray(ASN1Sequence aSN1Sequence) {
        GeneralSubtree[] generalSubtreeArr = new GeneralSubtree[aSN1Sequence.size()];
        for (int i = 0; i != generalSubtreeArr.length; i++) {
            generalSubtreeArr[i] = GeneralSubtree.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return generalSubtreeArr;
    }

    public GeneralSubtree[] getPermittedSubtrees() {
        return this.permitted;
    }

    public GeneralSubtree[] getExcludedSubtrees() {
        return this.excluded;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        GeneralSubtree[] generalSubtreeArr = this.permitted;
        if (generalSubtreeArr != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, new DERSequence((ASN1Encodable[]) generalSubtreeArr)));
        }
        GeneralSubtree[] generalSubtreeArr2 = this.excluded;
        if (generalSubtreeArr2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, new DERSequence((ASN1Encodable[]) generalSubtreeArr2)));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
