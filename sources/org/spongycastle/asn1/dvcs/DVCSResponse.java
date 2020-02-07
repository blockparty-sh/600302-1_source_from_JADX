package org.spongycastle.asn1.dvcs;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;

public class DVCSResponse extends ASN1Object implements ASN1Choice {
    private DVCSCertInfo dvCertInfo;
    private DVCSErrorNotice dvErrorNote;

    public DVCSResponse(DVCSCertInfo dVCSCertInfo) {
        this.dvCertInfo = dVCSCertInfo;
    }

    public DVCSResponse(DVCSErrorNotice dVCSErrorNotice) {
        this.dvErrorNote = dVCSErrorNotice;
    }

    public static DVCSResponse getInstance(Object obj) {
        if (obj == null || (obj instanceof DVCSResponse)) {
            return (DVCSResponse) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("failed to construct sequence from byte[]: ");
                sb.append(e.getMessage());
                throw new IllegalArgumentException(sb.toString());
            }
        } else if (obj instanceof ASN1Sequence) {
            return new DVCSResponse(DVCSCertInfo.getInstance(obj));
        } else {
            if (obj instanceof ASN1TaggedObject) {
                return new DVCSResponse(DVCSErrorNotice.getInstance(ASN1TaggedObject.getInstance(obj), false));
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Couldn't convert from object to DVCSResponse: ");
            sb2.append(obj.getClass().getName());
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public static DVCSResponse getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public DVCSCertInfo getCertInfo() {
        return this.dvCertInfo;
    }

    public DVCSErrorNotice getErrorNotice() {
        return this.dvErrorNote;
    }

    public ASN1Primitive toASN1Primitive() {
        DVCSCertInfo dVCSCertInfo = this.dvCertInfo;
        if (dVCSCertInfo != null) {
            return dVCSCertInfo.toASN1Primitive();
        }
        return new DERTaggedObject(0, this.dvErrorNote);
    }

    public String toString() {
        String str = "}\n";
        if (this.dvCertInfo != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("DVCSResponse {\ndvCertInfo: ");
            sb.append(this.dvCertInfo.toString());
            sb.append(str);
            return sb.toString();
        } else if (this.dvErrorNote == null) {
            return null;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("DVCSResponse {\ndvErrorNote: ");
            sb2.append(this.dvErrorNote.toString());
            sb2.append(str);
            return sb2.toString();
        }
    }
}
