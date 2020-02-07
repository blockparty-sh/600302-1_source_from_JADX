package org.spongycastle.asn1.eac;

import java.io.IOException;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1ParsingException;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.asn1.DEROctetString;

public class CVCertificateRequest extends ASN1Object {
    public static byte[] ZeroArray = {0};
    private static int bodyValid = 1;
    private static int signValid = 2;
    int ProfileId;
    byte[] certificate = null;
    private CertificateBody certificateBody;
    byte[] encoded;
    byte[] encodedAuthorityReference;
    private byte[] innerSignature = null;
    PublicKeyDataObject iso7816PubKey = null;
    ASN1ObjectIdentifier keyOid = null;
    private byte[] outerSignature = null;
    protected String overSignerReference = null;
    ASN1ObjectIdentifier signOid = null;
    String strCertificateHolderReference;
    private int valid;

    private CVCertificateRequest(DERApplicationSpecific dERApplicationSpecific) throws IOException {
        if (dERApplicationSpecific.getApplicationTag() == 103) {
            ASN1Sequence instance = ASN1Sequence.getInstance(dERApplicationSpecific.getObject(16));
            initCertBody(DERApplicationSpecific.getInstance(instance.getObjectAt(0)));
            this.outerSignature = DERApplicationSpecific.getInstance(instance.getObjectAt(instance.size() - 1)).getContents();
            return;
        }
        initCertBody(dERApplicationSpecific);
    }

    private void initCertBody(DERApplicationSpecific dERApplicationSpecific) throws IOException {
        if (dERApplicationSpecific.getApplicationTag() == 33) {
            Enumeration objects = ASN1Sequence.getInstance(dERApplicationSpecific.getObject(16)).getObjects();
            while (objects.hasMoreElements()) {
                DERApplicationSpecific instance = DERApplicationSpecific.getInstance(objects.nextElement());
                int applicationTag = instance.getApplicationTag();
                if (applicationTag == 55) {
                    this.innerSignature = instance.getContents();
                    this.valid |= signValid;
                } else if (applicationTag == 78) {
                    this.certificateBody = CertificateBody.getInstance(instance);
                    this.valid |= bodyValid;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid tag, not an CV Certificate Request element:");
                    sb.append(instance.getApplicationTag());
                    throw new IOException(sb.toString());
                }
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("not a CARDHOLDER_CERTIFICATE in request:");
        sb2.append(dERApplicationSpecific.getApplicationTag());
        throw new IOException(sb2.toString());
    }

    public static CVCertificateRequest getInstance(Object obj) {
        if (obj instanceof CVCertificateRequest) {
            return (CVCertificateRequest) obj;
        }
        if (obj == null) {
            return null;
        }
        try {
            return new CVCertificateRequest(DERApplicationSpecific.getInstance(obj));
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("unable to parse data: ");
            sb.append(e.getMessage());
            throw new ASN1ParsingException(sb.toString(), e);
        }
    }

    public CertificateBody getCertificateBody() {
        return this.certificateBody;
    }

    public PublicKeyDataObject getPublicKey() {
        return this.certificateBody.getPublicKey();
    }

    public byte[] getInnerSignature() {
        return this.innerSignature;
    }

    public byte[] getOuterSignature() {
        return this.outerSignature;
    }

    public boolean hasOuterSignature() {
        return this.outerSignature != null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certificateBody);
        try {
            aSN1EncodableVector.add(new DERApplicationSpecific(false, 55, (ASN1Encodable) new DEROctetString(this.innerSignature)));
            return new DERApplicationSpecific(33, aSN1EncodableVector);
        } catch (IOException unused) {
            throw new IllegalStateException("unable to convert signature!");
        }
    }
}
