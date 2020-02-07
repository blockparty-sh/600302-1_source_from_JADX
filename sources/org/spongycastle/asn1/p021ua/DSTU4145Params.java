package org.spongycastle.asn1.p021ua;

import com.google.common.base.Ascii;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.asn1.ua.DSTU4145Params */
public class DSTU4145Params extends ASN1Object {
    private static final byte[] DEFAULT_DKE = {-87, -42, -21, 69, -15, 60, 112, -126, Byte.MIN_VALUE, -60, -106, 123, 35, Ascii.f531US, 94, -83, -10, 88, -21, -92, -64, 55, 41, Ascii.f523GS, 56, -39, 107, -16, 37, -54, 78, Ascii.ETB, -8, -23, 114, Ascii.f519CR, -58, Ascii.NAK, -76, 58, 40, -105, Framer.STDIN_REQUEST_FRAME_PREFIX, Ascii.f532VT, -63, -34, -93, 100, 56, -75, 100, -22, 44, Ascii.ETB, -97, -48, Ascii.DC2, 62, 109, -72, -6, -59, 121, 4};
    private byte[] dke = DEFAULT_DKE;
    private DSTU4145ECBinary ecbinary;
    private ASN1ObjectIdentifier namedCurve;

    public DSTU4145Params(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.namedCurve = aSN1ObjectIdentifier;
    }

    public DSTU4145Params(DSTU4145ECBinary dSTU4145ECBinary) {
        this.ecbinary = dSTU4145ECBinary;
    }

    public boolean isNamedCurve() {
        return this.namedCurve != null;
    }

    public DSTU4145ECBinary getECBinary() {
        return this.ecbinary;
    }

    public byte[] getDKE() {
        return this.dke;
    }

    public static byte[] getDefaultDKE() {
        return DEFAULT_DKE;
    }

    public ASN1ObjectIdentifier getNamedCurve() {
        return this.namedCurve;
    }

    public static DSTU4145Params getInstance(Object obj) {
        DSTU4145Params dSTU4145Params;
        if (obj instanceof DSTU4145Params) {
            return (DSTU4145Params) obj;
        }
        String str = "object parse error";
        if (obj != null) {
            ASN1Sequence instance = ASN1Sequence.getInstance(obj);
            if (instance.getObjectAt(0) instanceof ASN1ObjectIdentifier) {
                dSTU4145Params = new DSTU4145Params(ASN1ObjectIdentifier.getInstance(instance.getObjectAt(0)));
            } else {
                dSTU4145Params = new DSTU4145Params(DSTU4145ECBinary.getInstance(instance.getObjectAt(0)));
            }
            if (instance.size() == 2) {
                dSTU4145Params.dke = ASN1OctetString.getInstance(instance.getObjectAt(1)).getOctets();
                if (dSTU4145Params.dke.length != DEFAULT_DKE.length) {
                    throw new IllegalArgumentException(str);
                }
            }
            return dSTU4145Params;
        }
        throw new IllegalArgumentException(str);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.namedCurve;
        if (aSN1ObjectIdentifier != null) {
            aSN1EncodableVector.add(aSN1ObjectIdentifier);
        } else {
            aSN1EncodableVector.add(this.ecbinary);
        }
        if (!Arrays.areEqual(this.dke, DEFAULT_DKE)) {
            aSN1EncodableVector.add(new DEROctetString(this.dke));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
