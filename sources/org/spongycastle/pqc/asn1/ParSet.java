package org.spongycastle.pqc.asn1;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.util.Arrays;

public class ParSet extends ASN1Object {
    private static final BigInteger ZERO = BigInteger.valueOf(0);

    /* renamed from: h */
    private int[] f1431h;

    /* renamed from: k */
    private int[] f1432k;

    /* renamed from: t */
    private int f1433t;

    /* renamed from: w */
    private int[] f1434w;

    private static int checkBigIntegerInIntRangeAndPositive(BigInteger bigInteger) {
        if (bigInteger.compareTo(BigInteger.valueOf(2147483647L)) <= 0 && bigInteger.compareTo(ZERO) > 0) {
            return bigInteger.intValue();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("BigInteger not in Range: ");
        sb.append(bigInteger.toString());
        throw new IllegalArgumentException(sb.toString());
    }

    private ParSet(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 4) {
            this.f1433t = checkBigIntegerInIntRangeAndPositive(((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue());
            ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
            ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(2);
            ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
            if (aSN1Sequence2.size() == this.f1433t && aSN1Sequence3.size() == this.f1433t && aSN1Sequence4.size() == this.f1433t) {
                this.f1431h = new int[aSN1Sequence2.size()];
                this.f1434w = new int[aSN1Sequence3.size()];
                this.f1432k = new int[aSN1Sequence4.size()];
                for (int i = 0; i < this.f1433t; i++) {
                    this.f1431h[i] = checkBigIntegerInIntRangeAndPositive(((ASN1Integer) aSN1Sequence2.getObjectAt(i)).getValue());
                    this.f1434w[i] = checkBigIntegerInIntRangeAndPositive(((ASN1Integer) aSN1Sequence3.getObjectAt(i)).getValue());
                    this.f1432k[i] = checkBigIntegerInIntRangeAndPositive(((ASN1Integer) aSN1Sequence4.getObjectAt(i)).getValue());
                }
                return;
            }
            throw new IllegalArgumentException("invalid size of sequences");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("sie of seqOfParams = ");
        sb.append(aSN1Sequence.size());
        throw new IllegalArgumentException(sb.toString());
    }

    public ParSet(int i, int[] iArr, int[] iArr2, int[] iArr3) {
        this.f1433t = i;
        this.f1431h = iArr;
        this.f1434w = iArr2;
        this.f1432k = iArr3;
    }

    public static ParSet getInstance(Object obj) {
        if (obj instanceof ParSet) {
            return (ParSet) obj;
        }
        if (obj != null) {
            return new ParSet(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public int getT() {
        return this.f1433t;
    }

    public int[] getH() {
        return Arrays.clone(this.f1431h);
    }

    public int[] getW() {
        return Arrays.clone(this.f1434w);
    }

    public int[] getK() {
        return Arrays.clone(this.f1432k);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        int i = 0;
        while (true) {
            int[] iArr = this.f1431h;
            if (i < iArr.length) {
                aSN1EncodableVector.add(new ASN1Integer((long) iArr[i]));
                aSN1EncodableVector2.add(new ASN1Integer((long) this.f1434w[i]));
                aSN1EncodableVector3.add(new ASN1Integer((long) this.f1432k[i]));
                i++;
            } else {
                ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
                aSN1EncodableVector4.add(new ASN1Integer((long) this.f1433t));
                aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector));
                aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector2));
                aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector3));
                return new DERSequence(aSN1EncodableVector4);
            }
        }
    }
}
