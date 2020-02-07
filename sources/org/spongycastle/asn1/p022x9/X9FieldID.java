package org.spongycastle.asn1.p022x9;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

/* renamed from: org.spongycastle.asn1.x9.X9FieldID */
public class X9FieldID extends ASN1Object implements X9ObjectIdentifiers {

    /* renamed from: id */
    private ASN1ObjectIdentifier f926id;
    private ASN1Primitive parameters;

    public X9FieldID(BigInteger bigInteger) {
        this.f926id = prime_field;
        this.parameters = new ASN1Integer(bigInteger);
    }

    public X9FieldID(int i, int i2) {
        this(i, i2, 0, 0);
    }

    public X9FieldID(int i, int i2, int i3, int i4) {
        this.f926id = characteristic_two_field;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer((long) i));
        String str = "inconsistent k values";
        if (i3 == 0) {
            if (i4 == 0) {
                aSN1EncodableVector.add(tpBasis);
                aSN1EncodableVector.add(new ASN1Integer((long) i2));
            } else {
                throw new IllegalArgumentException(str);
            }
        } else if (i3 <= i2 || i4 <= i3) {
            throw new IllegalArgumentException(str);
        } else {
            aSN1EncodableVector.add(ppBasis);
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.add(new ASN1Integer((long) i2));
            aSN1EncodableVector2.add(new ASN1Integer((long) i3));
            aSN1EncodableVector2.add(new ASN1Integer((long) i4));
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        this.parameters = new DERSequence(aSN1EncodableVector);
    }

    private X9FieldID(ASN1Sequence aSN1Sequence) {
        this.f926id = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.parameters = aSN1Sequence.getObjectAt(1).toASN1Primitive();
    }

    public static X9FieldID getInstance(Object obj) {
        if (obj instanceof X9FieldID) {
            return (X9FieldID) obj;
        }
        if (obj != null) {
            return new X9FieldID(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1ObjectIdentifier getIdentifier() {
        return this.f926id;
    }

    public ASN1Primitive getParameters() {
        return this.parameters;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f926id);
        aSN1EncodableVector.add(this.parameters);
        return new DERSequence(aSN1EncodableVector);
    }
}
