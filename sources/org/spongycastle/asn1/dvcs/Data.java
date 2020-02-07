package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.DigestInfo;

public class Data extends ASN1Object implements ASN1Choice {
    private ASN1Sequence certs;
    private ASN1OctetString message;
    private DigestInfo messageImprint;

    public Data(byte[] bArr) {
        this.message = new DEROctetString(bArr);
    }

    public Data(ASN1OctetString aSN1OctetString) {
        this.message = aSN1OctetString;
    }

    public Data(DigestInfo digestInfo) {
        this.messageImprint = digestInfo;
    }

    public Data(TargetEtcChain targetEtcChain) {
        this.certs = new DERSequence((ASN1Encodable) targetEtcChain);
    }

    public Data(TargetEtcChain[] targetEtcChainArr) {
        this.certs = new DERSequence((ASN1Encodable[]) targetEtcChainArr);
    }

    private Data(ASN1Sequence aSN1Sequence) {
        this.certs = aSN1Sequence;
    }

    public static Data getInstance(Object obj) {
        if (obj instanceof Data) {
            return (Data) obj;
        }
        if (obj instanceof ASN1OctetString) {
            return new Data((ASN1OctetString) obj);
        }
        if (obj instanceof ASN1Sequence) {
            return new Data(DigestInfo.getInstance(obj));
        }
        if (obj instanceof ASN1TaggedObject) {
            return new Data(ASN1Sequence.getInstance((ASN1TaggedObject) obj, false));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown object submitted to getInstance: ");
        sb.append(obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public static Data getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1OctetString aSN1OctetString = this.message;
        if (aSN1OctetString != null) {
            return aSN1OctetString.toASN1Primitive();
        }
        DigestInfo digestInfo = this.messageImprint;
        if (digestInfo != null) {
            return digestInfo.toASN1Primitive();
        }
        return new DERTaggedObject(false, 0, this.certs);
    }

    public String toString() {
        String str = "}\n";
        String str2 = "Data {\n";
        if (this.message != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(this.message);
            sb.append(str);
            return sb.toString();
        } else if (this.messageImprint != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append(this.messageImprint);
            sb2.append(str);
            return sb2.toString();
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str2);
            sb3.append(this.certs);
            sb3.append(str);
            return sb3.toString();
        }
    }

    public ASN1OctetString getMessage() {
        return this.message;
    }

    public DigestInfo getMessageImprint() {
        return this.messageImprint;
    }

    public TargetEtcChain[] getCerts() {
        ASN1Sequence aSN1Sequence = this.certs;
        if (aSN1Sequence == null) {
            return null;
        }
        TargetEtcChain[] targetEtcChainArr = new TargetEtcChain[aSN1Sequence.size()];
        for (int i = 0; i != targetEtcChainArr.length; i++) {
            targetEtcChainArr[i] = TargetEtcChain.getInstance(this.certs.getObjectAt(i));
        }
        return targetEtcChainArr;
    }
}
