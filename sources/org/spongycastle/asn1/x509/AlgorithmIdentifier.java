package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERSequence;

public class AlgorithmIdentifier extends ASN1Object {
    private ASN1ObjectIdentifier objectId;
    private ASN1Encodable parameters;
    private boolean parametersDefined;

    public static AlgorithmIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static AlgorithmIdentifier getInstance(Object obj) {
        if (obj == null || (obj instanceof AlgorithmIdentifier)) {
            return (AlgorithmIdentifier) obj;
        }
        if (obj instanceof ASN1ObjectIdentifier) {
            return new AlgorithmIdentifier((ASN1ObjectIdentifier) obj);
        }
        if (obj instanceof String) {
            return new AlgorithmIdentifier((String) obj);
        }
        return new AlgorithmIdentifier(ASN1Sequence.getInstance(obj));
    }

    public AlgorithmIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.parametersDefined = false;
        this.objectId = aSN1ObjectIdentifier;
    }

    public AlgorithmIdentifier(String str) {
        this.parametersDefined = false;
        this.objectId = new ASN1ObjectIdentifier(str);
    }

    public AlgorithmIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.parametersDefined = false;
        this.parametersDefined = true;
        this.objectId = aSN1ObjectIdentifier;
        this.parameters = aSN1Encodable;
    }

    public AlgorithmIdentifier(ASN1Sequence aSN1Sequence) {
        this.parametersDefined = false;
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Bad sequence size: ");
            sb.append(aSN1Sequence.size());
            throw new IllegalArgumentException(sb.toString());
        }
        this.objectId = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() == 2) {
            this.parametersDefined = true;
            this.parameters = aSN1Sequence.getObjectAt(1);
            return;
        }
        this.parameters = null;
    }

    public ASN1ObjectIdentifier getAlgorithm() {
        return new ASN1ObjectIdentifier(this.objectId.getId());
    }

    public ASN1ObjectIdentifier getObjectId() {
        return this.objectId;
    }

    public ASN1Encodable getParameters() {
        return this.parameters;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.objectId);
        if (this.parametersDefined) {
            ASN1Encodable aSN1Encodable = this.parameters;
            if (aSN1Encodable != null) {
                aSN1EncodableVector.add(aSN1Encodable);
            } else {
                aSN1EncodableVector.add(DERNull.INSTANCE);
            }
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
