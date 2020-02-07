package org.spongycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class PBKDF2Params extends ASN1Object {
    private static final AlgorithmIdentifier algid_hmacWithSHA1 = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA1, DERNull.INSTANCE);
    private ASN1Integer iterationCount;
    private ASN1Integer keyLength;
    private ASN1OctetString octStr;
    private AlgorithmIdentifier prf;

    public static PBKDF2Params getInstance(Object obj) {
        if (obj instanceof PBKDF2Params) {
            return (PBKDF2Params) obj;
        }
        if (obj != null) {
            return new PBKDF2Params(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public PBKDF2Params(byte[] bArr, int i) {
        this.octStr = new DEROctetString(bArr);
        this.iterationCount = new ASN1Integer((long) i);
    }

    public PBKDF2Params(byte[] bArr, int i, int i2) {
        this(bArr, i);
        this.keyLength = new ASN1Integer((long) i2);
    }

    public PBKDF2Params(byte[] bArr, int i, int i2, AlgorithmIdentifier algorithmIdentifier) {
        this(bArr, i);
        this.keyLength = new ASN1Integer((long) i2);
        this.prf = algorithmIdentifier;
    }

    public PBKDF2Params(byte[] bArr, int i, AlgorithmIdentifier algorithmIdentifier) {
        this(bArr, i);
        this.prf = algorithmIdentifier;
    }

    private PBKDF2Params(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.octStr = (ASN1OctetString) objects.nextElement();
        this.iterationCount = (ASN1Integer) objects.nextElement();
        if (objects.hasMoreElements()) {
            Object nextElement = objects.nextElement();
            if (nextElement instanceof ASN1Integer) {
                this.keyLength = ASN1Integer.getInstance(nextElement);
                nextElement = objects.hasMoreElements() ? objects.nextElement() : null;
            } else {
                this.keyLength = null;
            }
            if (nextElement != null) {
                this.prf = AlgorithmIdentifier.getInstance(nextElement);
            }
        }
    }

    public byte[] getSalt() {
        return this.octStr.getOctets();
    }

    public BigInteger getIterationCount() {
        return this.iterationCount.getValue();
    }

    public BigInteger getKeyLength() {
        ASN1Integer aSN1Integer = this.keyLength;
        if (aSN1Integer != null) {
            return aSN1Integer.getValue();
        }
        return null;
    }

    public boolean isDefaultPrf() {
        AlgorithmIdentifier algorithmIdentifier = this.prf;
        return algorithmIdentifier == null || algorithmIdentifier.equals(algid_hmacWithSHA1);
    }

    public AlgorithmIdentifier getPrf() {
        AlgorithmIdentifier algorithmIdentifier = this.prf;
        if (algorithmIdentifier != null) {
            return algorithmIdentifier;
        }
        return algid_hmacWithSHA1;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.octStr);
        aSN1EncodableVector.add(this.iterationCount);
        ASN1Integer aSN1Integer = this.keyLength;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        AlgorithmIdentifier algorithmIdentifier = this.prf;
        if (algorithmIdentifier != null && !algorithmIdentifier.equals(algid_hmacWithSHA1)) {
            aSN1EncodableVector.add(this.prf);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
