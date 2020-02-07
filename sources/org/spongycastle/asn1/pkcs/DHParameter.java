package org.spongycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class DHParameter extends ASN1Object {

    /* renamed from: g */
    ASN1Integer f867g;

    /* renamed from: l */
    ASN1Integer f868l;

    /* renamed from: p */
    ASN1Integer f869p;

    public DHParameter(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f869p = new ASN1Integer(bigInteger);
        this.f867g = new ASN1Integer(bigInteger2);
        if (i != 0) {
            this.f868l = new ASN1Integer((long) i);
        } else {
            this.f868l = null;
        }
    }

    public static DHParameter getInstance(Object obj) {
        if (obj instanceof DHParameter) {
            return (DHParameter) obj;
        }
        if (obj != null) {
            return new DHParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private DHParameter(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f869p = ASN1Integer.getInstance(objects.nextElement());
        this.f867g = ASN1Integer.getInstance(objects.nextElement());
        if (objects.hasMoreElements()) {
            this.f868l = (ASN1Integer) objects.nextElement();
        } else {
            this.f868l = null;
        }
    }

    public BigInteger getP() {
        return this.f869p.getPositiveValue();
    }

    public BigInteger getG() {
        return this.f867g.getPositiveValue();
    }

    public BigInteger getL() {
        ASN1Integer aSN1Integer = this.f868l;
        if (aSN1Integer == null) {
            return null;
        }
        return aSN1Integer.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f869p);
        aSN1EncodableVector.add(this.f867g);
        if (getL() != null) {
            aSN1EncodableVector.add(this.f868l);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
