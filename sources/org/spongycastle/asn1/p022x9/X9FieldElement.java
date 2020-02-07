package org.spongycastle.asn1.p022x9;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECFieldElement.C3640Fp;
import org.spongycastle.math.p025ec.ECFieldElement.F2m;

/* renamed from: org.spongycastle.asn1.x9.X9FieldElement */
public class X9FieldElement extends ASN1Object {
    private static X9IntegerConverter converter = new X9IntegerConverter();

    /* renamed from: f */
    protected ECFieldElement f925f;

    public X9FieldElement(ECFieldElement eCFieldElement) {
        this.f925f = eCFieldElement;
    }

    public X9FieldElement(BigInteger bigInteger, ASN1OctetString aSN1OctetString) {
        this(new C3640Fp(bigInteger, new BigInteger(1, aSN1OctetString.getOctets())));
    }

    public X9FieldElement(int i, int i2, int i3, int i4, ASN1OctetString aSN1OctetString) {
        F2m f2m = new F2m(i, i2, i3, i4, new BigInteger(1, aSN1OctetString.getOctets()));
        this(f2m);
    }

    public ECFieldElement getValue() {
        return this.f925f;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DEROctetString(converter.integerToBytes(this.f925f.toBigInteger(), converter.getByteLength(this.f925f)));
    }
}
