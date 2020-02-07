package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;

public class Target extends ASN1Object implements ASN1Choice {
    public static final int targetGroup = 1;
    public static final int targetName = 0;
    private GeneralName targGroup;
    private GeneralName targName;

    public static Target getInstance(Object obj) {
        if (obj == null || (obj instanceof Target)) {
            return (Target) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new Target((ASN1TaggedObject) obj);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("unknown object in factory: ");
        sb.append(obj.getClass());
        throw new IllegalArgumentException(sb.toString());
    }

    private Target(ASN1TaggedObject aSN1TaggedObject) {
        int tagNo = aSN1TaggedObject.getTagNo();
        if (tagNo == 0) {
            this.targName = GeneralName.getInstance(aSN1TaggedObject, true);
        } else if (tagNo == 1) {
            this.targGroup = GeneralName.getInstance(aSN1TaggedObject, true);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("unknown tag: ");
            sb.append(aSN1TaggedObject.getTagNo());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public Target(int i, GeneralName generalName) {
        this(new DERTaggedObject(i, generalName));
    }

    public GeneralName getTargetGroup() {
        return this.targGroup;
    }

    public GeneralName getTargetName() {
        return this.targName;
    }

    public ASN1Primitive toASN1Primitive() {
        GeneralName generalName = this.targName;
        if (generalName != null) {
            return new DERTaggedObject(true, 0, generalName);
        }
        return new DERTaggedObject(true, 1, this.targGroup);
    }
}
