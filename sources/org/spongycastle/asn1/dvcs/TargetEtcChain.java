package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class TargetEtcChain extends ASN1Object {
    private ASN1Sequence chain;
    private PathProcInput pathProcInput;
    private CertEtcToken target;

    public TargetEtcChain(CertEtcToken certEtcToken) {
        this(certEtcToken, null, null);
    }

    public TargetEtcChain(CertEtcToken certEtcToken, CertEtcToken[] certEtcTokenArr) {
        this(certEtcToken, certEtcTokenArr, null);
    }

    public TargetEtcChain(CertEtcToken certEtcToken, PathProcInput pathProcInput2) {
        this(certEtcToken, null, pathProcInput2);
    }

    public TargetEtcChain(CertEtcToken certEtcToken, CertEtcToken[] certEtcTokenArr, PathProcInput pathProcInput2) {
        this.target = certEtcToken;
        if (certEtcTokenArr != null) {
            this.chain = new DERSequence((ASN1Encodable[]) certEtcTokenArr);
        }
        this.pathProcInput = pathProcInput2;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001c */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0031 }] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002b A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0031 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private TargetEtcChain(org.spongycastle.asn1.ASN1Sequence r4) {
        /*
            r3 = this;
            r3.<init>()
            r0 = 0
            org.spongycastle.asn1.ASN1Encodable r1 = r4.getObjectAt(r0)
            org.spongycastle.asn1.dvcs.CertEtcToken r1 = org.spongycastle.asn1.dvcs.CertEtcToken.getInstance(r1)
            r3.target = r1
            r1 = 2
            r2 = 1
            org.spongycastle.asn1.ASN1Encodable r2 = r4.getObjectAt(r2)     // Catch:{ IllegalArgumentException -> 0x001c, IndexOutOfBoundsException -> 0x001b }
            org.spongycastle.asn1.ASN1Sequence r2 = org.spongycastle.asn1.ASN1Sequence.getInstance(r2)     // Catch:{ IllegalArgumentException -> 0x001c, IndexOutOfBoundsException -> 0x001b }
            r3.chain = r2     // Catch:{ IllegalArgumentException -> 0x001c, IndexOutOfBoundsException -> 0x001b }
            goto L_0x001c
        L_0x001b:
            return
        L_0x001c:
            org.spongycastle.asn1.ASN1Encodable r4 = r4.getObjectAt(r1)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0031 }
            org.spongycastle.asn1.ASN1TaggedObject r4 = org.spongycastle.asn1.ASN1TaggedObject.getInstance(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0031 }
            int r1 = r4.getTagNo()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0031 }
            if (r1 == 0) goto L_0x002b
            goto L_0x0031
        L_0x002b:
            org.spongycastle.asn1.dvcs.PathProcInput r4 = org.spongycastle.asn1.dvcs.PathProcInput.getInstance(r4, r0)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0031 }
            r3.pathProcInput = r4     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0031 }
        L_0x0031:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.dvcs.TargetEtcChain.<init>(org.spongycastle.asn1.ASN1Sequence):void");
    }

    public static TargetEtcChain getInstance(Object obj) {
        if (obj instanceof TargetEtcChain) {
            return (TargetEtcChain) obj;
        }
        if (obj != null) {
            return new TargetEtcChain(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TargetEtcChain getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.target);
        ASN1Sequence aSN1Sequence = this.chain;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        PathProcInput pathProcInput2 = this.pathProcInput;
        if (pathProcInput2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, pathProcInput2));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("TargetEtcChain {\n");
        StringBuilder sb = new StringBuilder();
        sb.append("target: ");
        sb.append(this.target);
        String str = "\n";
        sb.append(str);
        stringBuffer.append(sb.toString());
        if (this.chain != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("chain: ");
            sb2.append(this.chain);
            sb2.append(str);
            stringBuffer.append(sb2.toString());
        }
        if (this.pathProcInput != null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("pathProcInput: ");
            sb3.append(this.pathProcInput);
            sb3.append(str);
            stringBuffer.append(sb3.toString());
        }
        stringBuffer.append("}\n");
        return stringBuffer.toString();
    }

    public CertEtcToken getTarget() {
        return this.target;
    }

    public CertEtcToken[] getChain() {
        ASN1Sequence aSN1Sequence = this.chain;
        if (aSN1Sequence != null) {
            return CertEtcToken.arrayFromSequence(aSN1Sequence);
        }
        return null;
    }

    private void setChain(ASN1Sequence aSN1Sequence) {
        this.chain = aSN1Sequence;
    }

    public PathProcInput getPathProcInput() {
        return this.pathProcInput;
    }

    private void setPathProcInput(PathProcInput pathProcInput2) {
        this.pathProcInput = pathProcInput2;
    }

    public static TargetEtcChain[] arrayFromSequence(ASN1Sequence aSN1Sequence) {
        TargetEtcChain[] targetEtcChainArr = new TargetEtcChain[aSN1Sequence.size()];
        for (int i = 0; i != targetEtcChainArr.length; i++) {
            targetEtcChainArr[i] = getInstance(aSN1Sequence.getObjectAt(i));
        }
        return targetEtcChainArr;
    }
}
