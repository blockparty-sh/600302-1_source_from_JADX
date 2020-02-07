package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class TargetInformation extends ASN1Object {
    private ASN1Sequence targets;

    public static TargetInformation getInstance(Object obj) {
        if (obj instanceof TargetInformation) {
            return (TargetInformation) obj;
        }
        if (obj != null) {
            return new TargetInformation(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private TargetInformation(ASN1Sequence aSN1Sequence) {
        this.targets = aSN1Sequence;
    }

    public Targets[] getTargetsObjects() {
        Targets[] targetsArr = new Targets[this.targets.size()];
        Enumeration objects = this.targets.getObjects();
        int i = 0;
        while (objects.hasMoreElements()) {
            int i2 = i + 1;
            targetsArr[i] = Targets.getInstance(objects.nextElement());
            i = i2;
        }
        return targetsArr;
    }

    public TargetInformation(Targets targets2) {
        this.targets = new DERSequence((ASN1Encodable) targets2);
    }

    public TargetInformation(Target[] targetArr) {
        this(new Targets(targetArr));
    }

    public ASN1Primitive toASN1Primitive() {
        return this.targets;
    }
}