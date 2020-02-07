package org.spongycastle.asn1.p022x9;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.math.field.PolynomialExtensionField;
import org.spongycastle.math.p025ec.ECAlgorithms;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECPoint;

/* renamed from: org.spongycastle.asn1.x9.X9ECParameters */
public class X9ECParameters extends ASN1Object implements X9ObjectIdentifiers {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private ECCurve curve;
    private X9FieldID fieldID;

    /* renamed from: g */
    private ECPoint f921g;

    /* renamed from: h */
    private BigInteger f922h;

    /* renamed from: n */
    private BigInteger f923n;
    private byte[] seed;

    private X9ECParameters(ASN1Sequence aSN1Sequence) {
        if (!(aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) || !((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue().equals(ONE)) {
            throw new IllegalArgumentException("bad version in X9ECParameters");
        }
        X9Curve x9Curve = new X9Curve(X9FieldID.getInstance(aSN1Sequence.getObjectAt(1)), ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(2)));
        this.curve = x9Curve.getCurve();
        ASN1Encodable objectAt = aSN1Sequence.getObjectAt(3);
        if (objectAt instanceof X9ECPoint) {
            this.f921g = ((X9ECPoint) objectAt).getPoint();
        } else {
            this.f921g = new X9ECPoint(this.curve, (ASN1OctetString) objectAt).getPoint();
        }
        this.f923n = ((ASN1Integer) aSN1Sequence.getObjectAt(4)).getValue();
        this.seed = x9Curve.getSeed();
        if (aSN1Sequence.size() == 6) {
            this.f922h = ((ASN1Integer) aSN1Sequence.getObjectAt(5)).getValue();
        }
    }

    public static X9ECParameters getInstance(Object obj) {
        if (obj instanceof X9ECParameters) {
            return (X9ECParameters) obj;
        }
        if (obj != null) {
            return new X9ECParameters(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public X9ECParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this(eCCurve, eCPoint, bigInteger, ONE, null);
    }

    public X9ECParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this(eCCurve, eCPoint, bigInteger, bigInteger2, null);
    }

    public X9ECParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.curve = eCCurve;
        this.f921g = eCPoint.normalize();
        this.f923n = bigInteger;
        this.f922h = bigInteger2;
        this.seed = bArr;
        if (ECAlgorithms.isFpCurve(eCCurve)) {
            this.fieldID = new X9FieldID(eCCurve.getField().getCharacteristic());
        } else if (ECAlgorithms.isF2mCurve(eCCurve)) {
            int[] exponentsPresent = ((PolynomialExtensionField) eCCurve.getField()).getMinimalPolynomial().getExponentsPresent();
            if (exponentsPresent.length == 3) {
                this.fieldID = new X9FieldID(exponentsPresent[2], exponentsPresent[1]);
            } else if (exponentsPresent.length == 5) {
                this.fieldID = new X9FieldID(exponentsPresent[4], exponentsPresent[1], exponentsPresent[2], exponentsPresent[3]);
            } else {
                throw new IllegalArgumentException("Only trinomial and pentomial curves are supported");
            }
        } else {
            throw new IllegalArgumentException("'curve' is of an unsupported type");
        }
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.f921g;
    }

    public BigInteger getN() {
        return this.f923n;
    }

    public BigInteger getH() {
        BigInteger bigInteger = this.f922h;
        return bigInteger == null ? ONE : bigInteger;
    }

    public byte[] getSeed() {
        return this.seed;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(1));
        aSN1EncodableVector.add(this.fieldID);
        aSN1EncodableVector.add(new X9Curve(this.curve, this.seed));
        aSN1EncodableVector.add(new X9ECPoint(this.f921g));
        aSN1EncodableVector.add(new ASN1Integer(this.f923n));
        BigInteger bigInteger = this.f922h;
        if (bigInteger != null) {
            aSN1EncodableVector.add(new ASN1Integer(bigInteger));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
