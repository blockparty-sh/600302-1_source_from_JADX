package org.spongycastle.crypto.agreement.kdf;

import java.io.IOException;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.DigestDerivationFunction;
import org.spongycastle.crypto.generators.KDF2BytesGenerator;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.util.Pack;

public class ECDHKEKGenerator implements DigestDerivationFunction {
    private ASN1ObjectIdentifier algorithm;
    private DigestDerivationFunction kdf;
    private int keySize;

    /* renamed from: z */
    private byte[] f939z;

    public ECDHKEKGenerator(Digest digest) {
        this.kdf = new KDF2BytesGenerator(digest);
    }

    public void init(DerivationParameters derivationParameters) {
        DHKDFParameters dHKDFParameters = (DHKDFParameters) derivationParameters;
        this.algorithm = dHKDFParameters.getAlgorithm();
        this.keySize = dHKDFParameters.getKeySize();
        this.f939z = dHKDFParameters.getZ();
    }

    public Digest getDigest() {
        return this.kdf.getDigest();
    }

    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new AlgorithmIdentifier(this.algorithm, DERNull.INSTANCE));
        aSN1EncodableVector.add(new DERTaggedObject(true, 2, new DEROctetString(Pack.intToBigEndian(this.keySize))));
        try {
            this.kdf.init(new KDFParameters(this.f939z, new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER)));
            return this.kdf.generateBytes(bArr, i, i2);
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("unable to initialise kdf: ");
            sb.append(e.getMessage());
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
