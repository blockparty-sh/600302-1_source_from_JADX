package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

public class BasicConstraints extends ASN1Object {

    /* renamed from: cA */
    ASN1Boolean f899cA;
    ASN1Integer pathLenConstraint;

    public static BasicConstraints getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static BasicConstraints getInstance(Object obj) {
        if (obj instanceof BasicConstraints) {
            return (BasicConstraints) obj;
        }
        if (obj instanceof X509Extension) {
            return getInstance(X509Extension.convertValueToObject((X509Extension) obj));
        }
        if (obj != null) {
            return new BasicConstraints(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static BasicConstraints fromExtensions(Extensions extensions) {
        return getInstance(extensions.getExtensionParsedValue(Extension.basicConstraints));
    }

    private BasicConstraints(ASN1Sequence aSN1Sequence) {
        this.f899cA = ASN1Boolean.getInstance(false);
        this.pathLenConstraint = null;
        if (aSN1Sequence.size() == 0) {
            this.f899cA = null;
            this.pathLenConstraint = null;
            return;
        }
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Boolean) {
            this.f899cA = ASN1Boolean.getInstance((Object) aSN1Sequence.getObjectAt(0));
        } else {
            this.f899cA = null;
            this.pathLenConstraint = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        }
        if (aSN1Sequence.size() <= 1) {
            return;
        }
        if (this.f899cA != null) {
            this.pathLenConstraint = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("wrong sequence in constructor");
    }

    public BasicConstraints(boolean z) {
        this.f899cA = ASN1Boolean.getInstance(false);
        this.pathLenConstraint = null;
        if (z) {
            this.f899cA = ASN1Boolean.getInstance(true);
        } else {
            this.f899cA = null;
        }
        this.pathLenConstraint = null;
    }

    public BasicConstraints(int i) {
        this.f899cA = ASN1Boolean.getInstance(false);
        this.pathLenConstraint = null;
        this.f899cA = ASN1Boolean.getInstance(true);
        this.pathLenConstraint = new ASN1Integer((long) i);
    }

    public boolean isCA() {
        ASN1Boolean aSN1Boolean = this.f899cA;
        return aSN1Boolean != null && aSN1Boolean.isTrue();
    }

    public BigInteger getPathLenConstraint() {
        ASN1Integer aSN1Integer = this.pathLenConstraint;
        if (aSN1Integer != null) {
            return aSN1Integer.getValue();
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Boolean aSN1Boolean = this.f899cA;
        if (aSN1Boolean != null) {
            aSN1EncodableVector.add(aSN1Boolean);
        }
        ASN1Integer aSN1Integer = this.pathLenConstraint;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        String str = "BasicConstraints: isCa(";
        if (this.pathLenConstraint != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(isCA());
            sb.append("), pathLenConstraint = ");
            sb.append(this.pathLenConstraint.getValue());
            return sb.toString();
        } else if (this.f899cA == null) {
            return "BasicConstraints: isCa(false)";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(isCA());
            sb2.append(")");
            return sb2.toString();
        }
    }
}
