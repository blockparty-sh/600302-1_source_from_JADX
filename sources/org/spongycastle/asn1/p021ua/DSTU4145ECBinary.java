package org.spongycastle.asn1.p021ua;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.math.field.PolynomialExtensionField;
import org.spongycastle.math.p025ec.ECAlgorithms;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.asn1.ua.DSTU4145ECBinary */
public class DSTU4145ECBinary extends ASN1Object {

    /* renamed from: a */
    ASN1Integer f876a;

    /* renamed from: b */
    ASN1OctetString f877b;

    /* renamed from: bp */
    ASN1OctetString f878bp;

    /* renamed from: f */
    DSTU4145BinaryField f879f;

    /* renamed from: n */
    ASN1Integer f880n;
    BigInteger version = BigInteger.valueOf(0);

    public DSTU4145ECBinary(ECDomainParameters eCDomainParameters) {
        ECCurve curve = eCDomainParameters.getCurve();
        if (ECAlgorithms.isF2mCurve(curve)) {
            int[] exponentsPresent = ((PolynomialExtensionField) curve.getField()).getMinimalPolynomial().getExponentsPresent();
            if (exponentsPresent.length == 3) {
                this.f879f = new DSTU4145BinaryField(exponentsPresent[2], exponentsPresent[1]);
            } else if (exponentsPresent.length == 5) {
                this.f879f = new DSTU4145BinaryField(exponentsPresent[4], exponentsPresent[1], exponentsPresent[2], exponentsPresent[3]);
            }
            this.f876a = new ASN1Integer(curve.getA().toBigInteger());
            this.f877b = new DEROctetString(curve.getB().getEncoded());
            this.f880n = new ASN1Integer(eCDomainParameters.getN());
            this.f878bp = new DEROctetString(DSTU4145PointEncoder.encodePoint(eCDomainParameters.getG()));
            return;
        }
        throw new IllegalArgumentException("only binary domain is possible");
    }

    private DSTU4145ECBinary(ASN1Sequence aSN1Sequence) {
        int i = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(0);
            if (!aSN1TaggedObject.isExplicit() || aSN1TaggedObject.getTagNo() != 0) {
                throw new IllegalArgumentException("object parse error");
            }
            this.version = ASN1Integer.getInstance(aSN1TaggedObject.getLoadedObject()).getValue();
            i = 1;
        }
        this.f879f = DSTU4145BinaryField.getInstance(aSN1Sequence.getObjectAt(i));
        int i2 = i + 1;
        this.f876a = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i2));
        int i3 = i2 + 1;
        this.f877b = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i3));
        int i4 = i3 + 1;
        this.f880n = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i4));
        this.f878bp = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i4 + 1));
    }

    public static DSTU4145ECBinary getInstance(Object obj) {
        if (obj instanceof DSTU4145ECBinary) {
            return (DSTU4145ECBinary) obj;
        }
        if (obj != null) {
            return new DSTU4145ECBinary(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DSTU4145BinaryField getField() {
        return this.f879f;
    }

    public BigInteger getA() {
        return this.f876a.getValue();
    }

    public byte[] getB() {
        return Arrays.clone(this.f877b.getOctets());
    }

    public BigInteger getN() {
        return this.f880n.getValue();
    }

    public byte[] getG() {
        return Arrays.clone(this.f878bp.getOctets());
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.version.compareTo(BigInteger.valueOf(0)) != 0) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, new ASN1Integer(this.version)));
        }
        aSN1EncodableVector.add(this.f879f);
        aSN1EncodableVector.add(this.f876a);
        aSN1EncodableVector.add(this.f877b);
        aSN1EncodableVector.add(this.f880n);
        aSN1EncodableVector.add(this.f878bp);
        return new DERSequence(aSN1EncodableVector);
    }
}
