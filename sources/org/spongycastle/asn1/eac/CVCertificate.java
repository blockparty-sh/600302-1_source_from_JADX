package org.spongycastle.asn1.eac;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1ParsingException;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.asn1.DEROctetString;

public class CVCertificate extends ASN1Object {
    public static String ReferenceEncoding = "ISO-8859-1";
    private static int bodyValid = 1;
    private static int signValid = 2;
    public static final byte version_1 = 0;
    private CertificateBody certificateBody;
    private byte[] signature;
    private int valid;

    private void setPrivateData(DERApplicationSpecific dERApplicationSpecific) throws IOException {
        this.valid = 0;
        if (dERApplicationSpecific.getApplicationTag() == 33) {
            ASN1InputStream aSN1InputStream = new ASN1InputStream(dERApplicationSpecific.getContents());
            while (true) {
                ASN1Primitive readObject = aSN1InputStream.readObject();
                if (readObject == null) {
                    return;
                }
                if (readObject instanceof DERApplicationSpecific) {
                    DERApplicationSpecific dERApplicationSpecific2 = (DERApplicationSpecific) readObject;
                    int applicationTag = dERApplicationSpecific2.getApplicationTag();
                    if (applicationTag == 55) {
                        this.signature = dERApplicationSpecific2.getContents();
                        this.valid |= signValid;
                    } else if (applicationTag == 78) {
                        this.certificateBody = CertificateBody.getInstance(dERApplicationSpecific2);
                        this.valid |= bodyValid;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Invalid tag, not an Iso7816CertificateStructure :");
                        sb.append(dERApplicationSpecific2.getApplicationTag());
                        throw new IOException(sb.toString());
                    }
                } else {
                    throw new IOException("Invalid Object, not an Iso7816CertificateStructure");
                }
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("not a CARDHOLDER_CERTIFICATE :");
            sb2.append(dERApplicationSpecific.getApplicationTag());
            throw new IOException(sb2.toString());
        }
    }

    public CVCertificate(ASN1InputStream aSN1InputStream) throws IOException {
        initFrom(aSN1InputStream);
    }

    private void initFrom(ASN1InputStream aSN1InputStream) throws IOException {
        while (true) {
            ASN1Primitive readObject = aSN1InputStream.readObject();
            if (readObject == null) {
                return;
            }
            if (readObject instanceof DERApplicationSpecific) {
                setPrivateData((DERApplicationSpecific) readObject);
            } else {
                throw new IOException("Invalid Input Stream for creating an Iso7816CertificateStructure");
            }
        }
    }

    private CVCertificate(DERApplicationSpecific dERApplicationSpecific) throws IOException {
        setPrivateData(dERApplicationSpecific);
    }

    public CVCertificate(CertificateBody certificateBody2, byte[] bArr) throws IOException {
        this.certificateBody = certificateBody2;
        this.signature = bArr;
        this.valid |= bodyValid;
        this.valid |= signValid;
    }

    public static CVCertificate getInstance(Object obj) {
        if (obj instanceof CVCertificate) {
            return (CVCertificate) obj;
        }
        if (obj == null) {
            return null;
        }
        try {
            return new CVCertificate(DERApplicationSpecific.getInstance(obj));
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("unable to parse data: ");
            sb.append(e.getMessage());
            throw new ASN1ParsingException(sb.toString(), e);
        }
    }

    public byte[] getSignature() {
        return this.signature;
    }

    public CertificateBody getBody() {
        return this.certificateBody;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.valid != (signValid | bodyValid)) {
            return null;
        }
        aSN1EncodableVector.add(this.certificateBody);
        try {
            aSN1EncodableVector.add(new DERApplicationSpecific(false, 55, (ASN1Encodable) new DEROctetString(this.signature)));
            return new DERApplicationSpecific(33, aSN1EncodableVector);
        } catch (IOException unused) {
            throw new IllegalStateException("unable to convert signature!");
        }
    }

    public ASN1ObjectIdentifier getHolderAuthorization() throws IOException {
        return this.certificateBody.getCertificateHolderAuthorization().getOid();
    }

    public PackedDate getEffectiveDate() throws IOException {
        return this.certificateBody.getCertificateEffectiveDate();
    }

    public int getCertificateType() {
        return this.certificateBody.getCertificateType();
    }

    public PackedDate getExpirationDate() throws IOException {
        return this.certificateBody.getCertificateExpirationDate();
    }

    public int getRole() throws IOException {
        return this.certificateBody.getCertificateHolderAuthorization().getAccessRights();
    }

    public CertificationAuthorityReference getAuthorityReference() throws IOException {
        return this.certificateBody.getCertificationAuthorityReference();
    }

    public CertificateHolderReference getHolderReference() throws IOException {
        return this.certificateBody.getCertificateHolderReference();
    }

    public int getHolderAuthorizationRole() throws IOException {
        return this.certificateBody.getCertificateHolderAuthorization().getAccessRights() & 192;
    }

    public Flags getHolderAuthorizationRights() throws IOException {
        return new Flags(this.certificateBody.getCertificateHolderAuthorization().getAccessRights() & 31);
    }
}
