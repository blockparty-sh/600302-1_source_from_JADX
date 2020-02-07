package org.spongycastle.asn1.eac;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class RSAPublicKey extends PublicKeyDataObject {
    private static int exponentValid = 2;
    private static int modulusValid = 1;
    private BigInteger exponent;
    private BigInteger modulus;
    private ASN1ObjectIdentifier usage;
    private int valid = 0;

    RSAPublicKey(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.usage = ASN1ObjectIdentifier.getInstance(objects.nextElement());
        while (objects.hasMoreElements()) {
            UnsignedInteger instance = UnsignedInteger.getInstance(objects.nextElement());
            int tagNo = instance.getTagNo();
            if (tagNo == 1) {
                setModulus(instance);
            } else if (tagNo == 2) {
                setExponent(instance);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown DERTaggedObject :");
                sb.append(instance.getTagNo());
                sb.append("-> not an Iso7816RSAPublicKeyStructure");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        if (this.valid != 3) {
            throw new IllegalArgumentException("missing argument -> not an Iso7816RSAPublicKeyStructure");
        }
    }

    public RSAPublicKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, BigInteger bigInteger, BigInteger bigInteger2) {
        this.usage = aSN1ObjectIdentifier;
        this.modulus = bigInteger;
        this.exponent = bigInteger2;
    }

    public ASN1ObjectIdentifier getUsage() {
        return this.usage;
    }

    public BigInteger getModulus() {
        return this.modulus;
    }

    public BigInteger getPublicExponent() {
        return this.exponent;
    }

    private void setModulus(UnsignedInteger unsignedInteger) {
        int i = this.valid;
        int i2 = modulusValid;
        if ((i & i2) == 0) {
            this.valid = i | i2;
            this.modulus = unsignedInteger.getValue();
            return;
        }
        throw new IllegalArgumentException("Modulus already set");
    }

    private void setExponent(UnsignedInteger unsignedInteger) {
        int i = this.valid;
        int i2 = exponentValid;
        if ((i & i2) == 0) {
            this.valid = i | i2;
            this.exponent = unsignedInteger.getValue();
            return;
        }
        throw new IllegalArgumentException("Exponent already set");
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.usage);
        aSN1EncodableVector.add(new UnsignedInteger(1, getModulus()));
        aSN1EncodableVector.add(new UnsignedInteger(2, getPublicExponent()));
        return new DERSequence(aSN1EncodableVector);
    }
}
