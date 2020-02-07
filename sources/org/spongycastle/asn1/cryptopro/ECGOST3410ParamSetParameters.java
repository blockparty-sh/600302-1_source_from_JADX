package org.spongycastle.asn1.cryptopro;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

public class ECGOST3410ParamSetParameters extends ASN1Object {

    /* renamed from: a */
    ASN1Integer f841a;

    /* renamed from: b */
    ASN1Integer f842b;

    /* renamed from: p */
    ASN1Integer f843p;

    /* renamed from: q */
    ASN1Integer f844q;

    /* renamed from: x */
    ASN1Integer f845x;

    /* renamed from: y */
    ASN1Integer f846y;

    public static ECGOST3410ParamSetParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static ECGOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof ECGOST3410ParamSetParameters)) {
            return (ECGOST3410ParamSetParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new ECGOST3410ParamSetParameters((ASN1Sequence) obj);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid GOST3410Parameter: ");
        sb.append(obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public ECGOST3410ParamSetParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int i, BigInteger bigInteger5) {
        this.f841a = new ASN1Integer(bigInteger);
        this.f842b = new ASN1Integer(bigInteger2);
        this.f843p = new ASN1Integer(bigInteger3);
        this.f844q = new ASN1Integer(bigInteger4);
        this.f845x = new ASN1Integer((long) i);
        this.f846y = new ASN1Integer(bigInteger5);
    }

    public ECGOST3410ParamSetParameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f841a = (ASN1Integer) objects.nextElement();
        this.f842b = (ASN1Integer) objects.nextElement();
        this.f843p = (ASN1Integer) objects.nextElement();
        this.f844q = (ASN1Integer) objects.nextElement();
        this.f845x = (ASN1Integer) objects.nextElement();
        this.f846y = (ASN1Integer) objects.nextElement();
    }

    public BigInteger getP() {
        return this.f843p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f844q.getPositiveValue();
    }

    public BigInteger getA() {
        return this.f841a.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f841a);
        aSN1EncodableVector.add(this.f842b);
        aSN1EncodableVector.add(this.f843p);
        aSN1EncodableVector.add(this.f844q);
        aSN1EncodableVector.add(this.f845x);
        aSN1EncodableVector.add(this.f846y);
        return new DERSequence(aSN1EncodableVector);
    }
}
