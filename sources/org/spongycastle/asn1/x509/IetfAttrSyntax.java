package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.DERUTF8String;

public class IetfAttrSyntax extends ASN1Object {
    public static final int VALUE_OCTETS = 1;
    public static final int VALUE_OID = 2;
    public static final int VALUE_UTF8 = 3;
    GeneralNames policyAuthority = null;
    int valueChoice = -1;
    Vector values = new Vector();

    public static IetfAttrSyntax getInstance(Object obj) {
        if (obj instanceof IetfAttrSyntax) {
            return (IetfAttrSyntax) obj;
        }
        if (obj != null) {
            return new IetfAttrSyntax(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private IetfAttrSyntax(org.spongycastle.asn1.ASN1Sequence r6) {
        /*
            r5 = this;
            r5.<init>()
            r0 = 0
            r5.policyAuthority = r0
            java.util.Vector r0 = new java.util.Vector
            r0.<init>()
            r5.values = r0
            r0 = -1
            r5.valueChoice = r0
            r0 = 0
            org.spongycastle.asn1.ASN1Encodable r1 = r6.getObjectAt(r0)
            boolean r1 = r1 instanceof org.spongycastle.asn1.ASN1TaggedObject
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0029
            org.spongycastle.asn1.ASN1Encodable r1 = r6.getObjectAt(r0)
            org.spongycastle.asn1.ASN1TaggedObject r1 = (org.spongycastle.asn1.ASN1TaggedObject) r1
            org.spongycastle.asn1.x509.GeneralNames r0 = org.spongycastle.asn1.x509.GeneralNames.getInstance(r1, r0)
            r5.policyAuthority = r0
        L_0x0027:
            r0 = 1
            goto L_0x003a
        L_0x0029:
            int r1 = r6.size()
            if (r1 != r2) goto L_0x003a
            org.spongycastle.asn1.ASN1Encodable r0 = r6.getObjectAt(r0)
            org.spongycastle.asn1.x509.GeneralNames r0 = org.spongycastle.asn1.x509.GeneralNames.getInstance(r0)
            r5.policyAuthority = r0
            goto L_0x0027
        L_0x003a:
            org.spongycastle.asn1.ASN1Encodable r1 = r6.getObjectAt(r0)
            boolean r1 = r1 instanceof org.spongycastle.asn1.ASN1Sequence
            if (r1 == 0) goto L_0x008a
            org.spongycastle.asn1.ASN1Encodable r6 = r6.getObjectAt(r0)
            org.spongycastle.asn1.ASN1Sequence r6 = (org.spongycastle.asn1.ASN1Sequence) r6
            java.util.Enumeration r6 = r6.getObjects()
        L_0x004c:
            boolean r0 = r6.hasMoreElements()
            if (r0 == 0) goto L_0x0089
            java.lang.Object r0 = r6.nextElement()
            org.spongycastle.asn1.ASN1Primitive r0 = (org.spongycastle.asn1.ASN1Primitive) r0
            boolean r1 = r0 instanceof org.spongycastle.asn1.ASN1ObjectIdentifier
            if (r1 == 0) goto L_0x005e
            r1 = 2
            goto L_0x0069
        L_0x005e:
            boolean r1 = r0 instanceof org.spongycastle.asn1.DERUTF8String
            if (r1 == 0) goto L_0x0064
            r1 = 3
            goto L_0x0069
        L_0x0064:
            boolean r1 = r0 instanceof org.spongycastle.asn1.DEROctetString
            if (r1 == 0) goto L_0x0081
            r1 = 1
        L_0x0069:
            int r4 = r5.valueChoice
            if (r4 >= 0) goto L_0x006f
            r5.valueChoice = r1
        L_0x006f:
            int r4 = r5.valueChoice
            if (r1 != r4) goto L_0x0079
            java.util.Vector r1 = r5.values
            r1.addElement(r0)
            goto L_0x004c
        L_0x0079:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Mix of value types in IetfAttrSyntax"
            r6.<init>(r0)
            throw r6
        L_0x0081:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Bad value type encoding IetfAttrSyntax"
            r6.<init>(r0)
            throw r6
        L_0x0089:
            return
        L_0x008a:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Non-IetfAttrSyntax encoding"
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.IetfAttrSyntax.<init>(org.spongycastle.asn1.ASN1Sequence):void");
    }

    public GeneralNames getPolicyAuthority() {
        return this.policyAuthority;
    }

    public int getValueType() {
        return this.valueChoice;
    }

    public Object[] getValues() {
        int i = 0;
        if (getValueType() == 1) {
            ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[this.values.size()];
            while (i != aSN1OctetStringArr.length) {
                aSN1OctetStringArr[i] = (ASN1OctetString) this.values.elementAt(i);
                i++;
            }
            return aSN1OctetStringArr;
        } else if (getValueType() == 2) {
            ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr = new ASN1ObjectIdentifier[this.values.size()];
            while (i != aSN1ObjectIdentifierArr.length) {
                aSN1ObjectIdentifierArr[i] = (ASN1ObjectIdentifier) this.values.elementAt(i);
                i++;
            }
            return aSN1ObjectIdentifierArr;
        } else {
            DERUTF8String[] dERUTF8StringArr = new DERUTF8String[this.values.size()];
            while (i != dERUTF8StringArr.length) {
                dERUTF8StringArr[i] = (DERUTF8String) this.values.elementAt(i);
                i++;
            }
            return dERUTF8StringArr;
        }
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        GeneralNames generalNames = this.policyAuthority;
        if (generalNames != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, generalNames));
        }
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        Enumeration elements = this.values.elements();
        while (elements.hasMoreElements()) {
            aSN1EncodableVector2.add((ASN1Encodable) elements.nextElement());
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        return new DERSequence(aSN1EncodableVector);
    }
}
