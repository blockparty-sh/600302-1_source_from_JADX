package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

public class DSAParameter extends ASN1Object {

    /* renamed from: g */
    ASN1Integer f901g;

    /* renamed from: p */
    ASN1Integer f902p;

    /* renamed from: q */
    ASN1Integer f903q;

    public static DSAParameter getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static DSAParameter getInstance(Object obj) {
        if (obj instanceof DSAParameter) {
            return (DSAParameter) obj;
        }
        if (obj != null) {
            return new DSAParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DSAParameter(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f902p = new ASN1Integer(bigInteger);
        this.f903q = new ASN1Integer(bigInteger2);
        this.f901g = new ASN1Integer(bigInteger3);
    }

    private DSAParameter(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 3) {
            Enumeration objects = aSN1Sequence.getObjects();
            this.f902p = ASN1Integer.getInstance(objects.nextElement());
            this.f903q = ASN1Integer.getInstance(objects.nextElement());
            this.f901g = ASN1Integer.getInstance(objects.nextElement());
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Bad sequence size: ");
        sb.append(aSN1Sequence.size());
        throw new IllegalArgumentException(sb.toString());
    }

    public BigInteger getP() {
        return this.f902p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f903q.getPositiveValue();
    }

    public BigInteger getG() {
        return this.f901g.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f902p);
        aSN1EncodableVector.add(this.f903q);
        aSN1EncodableVector.add(this.f901g);
        return new DERSequence(aSN1EncodableVector);
    }
}
