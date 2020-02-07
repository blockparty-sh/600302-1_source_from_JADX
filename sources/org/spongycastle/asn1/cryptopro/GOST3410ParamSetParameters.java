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

public class GOST3410ParamSetParameters extends ASN1Object {

    /* renamed from: a */
    ASN1Integer f848a;
    int keySize;

    /* renamed from: p */
    ASN1Integer f849p;

    /* renamed from: q */
    ASN1Integer f850q;

    public static GOST3410ParamSetParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static GOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof GOST3410ParamSetParameters)) {
            return (GOST3410ParamSetParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new GOST3410ParamSetParameters((ASN1Sequence) obj);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid GOST3410Parameter: ");
        sb.append(obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public GOST3410ParamSetParameters(int i, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.keySize = i;
        this.f849p = new ASN1Integer(bigInteger);
        this.f850q = new ASN1Integer(bigInteger2);
        this.f848a = new ASN1Integer(bigInteger3);
    }

    public GOST3410ParamSetParameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.keySize = ((ASN1Integer) objects.nextElement()).getValue().intValue();
        this.f849p = (ASN1Integer) objects.nextElement();
        this.f850q = (ASN1Integer) objects.nextElement();
        this.f848a = (ASN1Integer) objects.nextElement();
    }

    public int getLKeySize() {
        return this.keySize;
    }

    public int getKeySize() {
        return this.keySize;
    }

    public BigInteger getP() {
        return this.f849p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f850q.getPositiveValue();
    }

    public BigInteger getA() {
        return this.f848a.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer((long) this.keySize));
        aSN1EncodableVector.add(this.f849p);
        aSN1EncodableVector.add(this.f850q);
        aSN1EncodableVector.add(this.f848a);
        return new DERSequence(aSN1EncodableVector);
    }
}
