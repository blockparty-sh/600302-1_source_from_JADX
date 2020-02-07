package org.spongycastle.asn1.p021ua;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

/* renamed from: org.spongycastle.asn1.ua.DSTU4145BinaryField */
public class DSTU4145BinaryField extends ASN1Object {

    /* renamed from: j */
    private int f872j;

    /* renamed from: k */
    private int f873k;

    /* renamed from: l */
    private int f874l;

    /* renamed from: m */
    private int f875m;

    private DSTU4145BinaryField(ASN1Sequence aSN1Sequence) {
        this.f875m = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getPositiveValue().intValue();
        if (aSN1Sequence.getObjectAt(1) instanceof ASN1Integer) {
            this.f873k = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getPositiveValue().intValue();
        } else if (aSN1Sequence.getObjectAt(1) instanceof ASN1Sequence) {
            ASN1Sequence instance = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
            this.f873k = ASN1Integer.getInstance(instance.getObjectAt(0)).getPositiveValue().intValue();
            this.f872j = ASN1Integer.getInstance(instance.getObjectAt(1)).getPositiveValue().intValue();
            this.f874l = ASN1Integer.getInstance(instance.getObjectAt(2)).getPositiveValue().intValue();
        } else {
            throw new IllegalArgumentException("object parse error");
        }
    }

    public static DSTU4145BinaryField getInstance(Object obj) {
        if (obj instanceof DSTU4145BinaryField) {
            return (DSTU4145BinaryField) obj;
        }
        if (obj != null) {
            return new DSTU4145BinaryField(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DSTU4145BinaryField(int i, int i2, int i3, int i4) {
        this.f875m = i;
        this.f873k = i2;
        this.f872j = i3;
        this.f874l = i4;
    }

    public int getM() {
        return this.f875m;
    }

    public int getK1() {
        return this.f873k;
    }

    public int getK2() {
        return this.f872j;
    }

    public int getK3() {
        return this.f874l;
    }

    public DSTU4145BinaryField(int i, int i2) {
        this(i, i2, 0, 0);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer((long) this.f875m));
        if (this.f872j == 0) {
            aSN1EncodableVector.add(new ASN1Integer((long) this.f873k));
        } else {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.add(new ASN1Integer((long) this.f873k));
            aSN1EncodableVector2.add(new ASN1Integer((long) this.f872j));
            aSN1EncodableVector2.add(new ASN1Integer((long) this.f874l));
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
