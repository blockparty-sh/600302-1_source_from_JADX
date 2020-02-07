package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.GeneralName;

public class DVCSRequest extends ASN1Object {
    private Data data;
    private DVCSRequestInformation requestInformation;
    private GeneralName transactionIdentifier;

    public DVCSRequest(DVCSRequestInformation dVCSRequestInformation, Data data2) {
        this(dVCSRequestInformation, data2, null);
    }

    public DVCSRequest(DVCSRequestInformation dVCSRequestInformation, Data data2, GeneralName generalName) {
        this.requestInformation = dVCSRequestInformation;
        this.data = data2;
        this.transactionIdentifier = generalName;
    }

    private DVCSRequest(ASN1Sequence aSN1Sequence) {
        this.requestInformation = DVCSRequestInformation.getInstance(aSN1Sequence.getObjectAt(0));
        this.data = Data.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() > 2) {
            this.transactionIdentifier = GeneralName.getInstance(aSN1Sequence.getObjectAt(2));
        }
    }

    public static DVCSRequest getInstance(Object obj) {
        if (obj instanceof DVCSRequest) {
            return (DVCSRequest) obj;
        }
        if (obj != null) {
            return new DVCSRequest(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static DVCSRequest getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.requestInformation);
        aSN1EncodableVector.add(this.data);
        GeneralName generalName = this.transactionIdentifier;
        if (generalName != null) {
            aSN1EncodableVector.add(generalName);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("DVCSRequest {\nrequestInformation: ");
        sb.append(this.requestInformation);
        String str2 = "\n";
        sb.append(str2);
        sb.append("data: ");
        sb.append(this.data);
        sb.append(str2);
        if (this.transactionIdentifier != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("transactionIdentifier: ");
            sb2.append(this.transactionIdentifier);
            sb2.append(str2);
            str = sb2.toString();
        } else {
            str = "";
        }
        sb.append(str);
        sb.append("}\n");
        return sb.toString();
    }

    public Data getData() {
        return this.data;
    }

    public DVCSRequestInformation getRequestInformation() {
        return this.requestInformation;
    }

    public GeneralName getTransactionIdentifier() {
        return this.transactionIdentifier;
    }
}
