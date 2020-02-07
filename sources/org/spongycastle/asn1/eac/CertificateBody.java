package org.spongycastle.asn1.eac;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.asn1.DEROctetString;

public class CertificateBody extends ASN1Object {
    private static final int CAR = 2;
    private static final int CEfD = 32;
    private static final int CExD = 64;
    private static final int CHA = 16;
    private static final int CHR = 8;
    private static final int CPI = 1;

    /* renamed from: PK */
    private static final int f851PK = 4;
    public static final int profileType = 127;
    public static final int requestType = 13;
    private DERApplicationSpecific certificateEffectiveDate;
    private DERApplicationSpecific certificateExpirationDate;
    private CertificateHolderAuthorization certificateHolderAuthorization;
    private DERApplicationSpecific certificateHolderReference;
    private DERApplicationSpecific certificateProfileIdentifier;
    private int certificateType = 0;
    private DERApplicationSpecific certificationAuthorityReference;
    private PublicKeyDataObject publicKey;
    ASN1InputStream seq;

    private void setIso7816CertificateBody(DERApplicationSpecific dERApplicationSpecific) throws IOException {
        if (dERApplicationSpecific.getApplicationTag() == 78) {
            ASN1InputStream aSN1InputStream = new ASN1InputStream(dERApplicationSpecific.getContents());
            while (true) {
                ASN1Primitive readObject = aSN1InputStream.readObject();
                if (readObject == null) {
                    return;
                }
                if (readObject instanceof DERApplicationSpecific) {
                    DERApplicationSpecific dERApplicationSpecific2 = (DERApplicationSpecific) readObject;
                    int applicationTag = dERApplicationSpecific2.getApplicationTag();
                    if (applicationTag == 2) {
                        setCertificationAuthorityReference(dERApplicationSpecific2);
                    } else if (applicationTag == 32) {
                        setCertificateHolderReference(dERApplicationSpecific2);
                    } else if (applicationTag == 41) {
                        setCertificateProfileIdentifier(dERApplicationSpecific2);
                    } else if (applicationTag == 73) {
                        setPublicKey(PublicKeyDataObject.getInstance(dERApplicationSpecific2.getObject(16)));
                    } else if (applicationTag == 76) {
                        setCertificateHolderAuthorization(new CertificateHolderAuthorization(dERApplicationSpecific2));
                    } else if (applicationTag == 36) {
                        setCertificateExpirationDate(dERApplicationSpecific2);
                    } else if (applicationTag == 37) {
                        setCertificateEffectiveDate(dERApplicationSpecific2);
                    } else {
                        this.certificateType = 0;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Not a valid iso7816 DERApplicationSpecific tag ");
                        sb.append(dERApplicationSpecific2.getApplicationTag());
                        throw new IOException(sb.toString());
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Not a valid iso7816 content : not a DERApplicationSpecific Object :");
                    sb2.append(EACTags.encodeTag(dERApplicationSpecific));
                    sb2.append(readObject.getClass());
                    throw new IOException(sb2.toString());
                }
            }
        } else {
            throw new IOException("Bad tag : not an iso7816 CERTIFICATE_CONTENT_TEMPLATE");
        }
    }

    public CertificateBody(DERApplicationSpecific dERApplicationSpecific, CertificationAuthorityReference certificationAuthorityReference2, PublicKeyDataObject publicKeyDataObject, CertificateHolderReference certificateHolderReference2, CertificateHolderAuthorization certificateHolderAuthorization2, PackedDate packedDate, PackedDate packedDate2) {
        setCertificateProfileIdentifier(dERApplicationSpecific);
        setCertificationAuthorityReference(new DERApplicationSpecific(2, certificationAuthorityReference2.getEncoded()));
        setPublicKey(publicKeyDataObject);
        setCertificateHolderReference(new DERApplicationSpecific(32, certificateHolderReference2.getEncoded()));
        setCertificateHolderAuthorization(certificateHolderAuthorization2);
        try {
            setCertificateEffectiveDate(new DERApplicationSpecific(false, 37, (ASN1Encodable) new DEROctetString(packedDate.getEncoding())));
            setCertificateExpirationDate(new DERApplicationSpecific(false, 36, (ASN1Encodable) new DEROctetString(packedDate2.getEncoding())));
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("unable to encode dates: ");
            sb.append(e.getMessage());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private CertificateBody(DERApplicationSpecific dERApplicationSpecific) throws IOException {
        setIso7816CertificateBody(dERApplicationSpecific);
    }

    private ASN1Primitive profileToASN1Object() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certificateProfileIdentifier);
        aSN1EncodableVector.add(this.certificationAuthorityReference);
        aSN1EncodableVector.add(new DERApplicationSpecific(false, 73, (ASN1Encodable) this.publicKey));
        aSN1EncodableVector.add(this.certificateHolderReference);
        aSN1EncodableVector.add(this.certificateHolderAuthorization);
        aSN1EncodableVector.add(this.certificateEffectiveDate);
        aSN1EncodableVector.add(this.certificateExpirationDate);
        return new DERApplicationSpecific(78, aSN1EncodableVector);
    }

    private void setCertificateProfileIdentifier(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() == 41) {
            this.certificateProfileIdentifier = dERApplicationSpecific;
            this.certificateType |= 1;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Not an Iso7816Tags.INTERCHANGE_PROFILE tag :");
        sb.append(EACTags.encodeTag(dERApplicationSpecific));
        throw new IllegalArgumentException(sb.toString());
    }

    private void setCertificateHolderReference(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() == 32) {
            this.certificateHolderReference = dERApplicationSpecific;
            this.certificateType |= 8;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.CARDHOLDER_NAME tag");
    }

    private void setCertificationAuthorityReference(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() == 2) {
            this.certificationAuthorityReference = dERApplicationSpecific;
            this.certificateType |= 2;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.ISSUER_IDENTIFICATION_NUMBER tag");
    }

    private void setPublicKey(PublicKeyDataObject publicKeyDataObject) {
        this.publicKey = PublicKeyDataObject.getInstance(publicKeyDataObject);
        this.certificateType |= 4;
    }

    private ASN1Primitive requestToASN1Object() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certificateProfileIdentifier);
        aSN1EncodableVector.add(new DERApplicationSpecific(false, 73, (ASN1Encodable) this.publicKey));
        aSN1EncodableVector.add(this.certificateHolderReference);
        return new DERApplicationSpecific(78, aSN1EncodableVector);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1Primitive aSN1Primitive = null;
        try {
            if (this.certificateType == 127) {
                return profileToASN1Object();
            }
            if (this.certificateType == 13) {
                aSN1Primitive = requestToASN1Object();
            }
            return aSN1Primitive;
        } catch (IOException unused) {
        }
    }

    public int getCertificateType() {
        return this.certificateType;
    }

    public static CertificateBody getInstance(Object obj) throws IOException {
        if (obj instanceof CertificateBody) {
            return (CertificateBody) obj;
        }
        if (obj != null) {
            return new CertificateBody(DERApplicationSpecific.getInstance(obj));
        }
        return null;
    }

    public PackedDate getCertificateEffectiveDate() {
        if ((this.certificateType & 32) == 32) {
            return new PackedDate(this.certificateEffectiveDate.getContents());
        }
        return null;
    }

    private void setCertificateEffectiveDate(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() == 37) {
            this.certificateEffectiveDate = dERApplicationSpecific;
            this.certificateType |= 32;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Not an Iso7816Tags.APPLICATION_EFFECTIVE_DATE tag :");
        sb.append(EACTags.encodeTag(dERApplicationSpecific));
        throw new IllegalArgumentException(sb.toString());
    }

    public PackedDate getCertificateExpirationDate() throws IOException {
        if ((this.certificateType & 64) == 64) {
            return new PackedDate(this.certificateExpirationDate.getContents());
        }
        throw new IOException("certificate Expiration Date not set");
    }

    private void setCertificateExpirationDate(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() == 36) {
            this.certificateExpirationDate = dERApplicationSpecific;
            this.certificateType |= 64;
            return;
        }
        throw new IllegalArgumentException("Not an Iso7816Tags.APPLICATION_EXPIRATION_DATE tag");
    }

    public CertificateHolderAuthorization getCertificateHolderAuthorization() throws IOException {
        if ((this.certificateType & 16) == 16) {
            return this.certificateHolderAuthorization;
        }
        throw new IOException("Certificate Holder Authorisation not set");
    }

    private void setCertificateHolderAuthorization(CertificateHolderAuthorization certificateHolderAuthorization2) {
        this.certificateHolderAuthorization = certificateHolderAuthorization2;
        this.certificateType |= 16;
    }

    public CertificateHolderReference getCertificateHolderReference() {
        return new CertificateHolderReference(this.certificateHolderReference.getContents());
    }

    public DERApplicationSpecific getCertificateProfileIdentifier() {
        return this.certificateProfileIdentifier;
    }

    public CertificationAuthorityReference getCertificationAuthorityReference() throws IOException {
        if ((this.certificateType & 2) == 2) {
            return new CertificationAuthorityReference(this.certificationAuthorityReference.getContents());
        }
        throw new IOException("Certification authority reference not set");
    }

    public PublicKeyDataObject getPublicKey() {
        return this.publicKey;
    }
}
