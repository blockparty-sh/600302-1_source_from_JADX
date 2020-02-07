package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.MessageEncryptor;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2Vector;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.spongycastle.pqc.math.linearalgebra.Vector;

public class McEliecePKCSCipher implements MessageEncryptor {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.1";
    public int cipherTextSize;

    /* renamed from: k */
    private int f1471k;
    McElieceKeyParameters key;
    public int maxPlainTextSize;

    /* renamed from: n */
    private int f1472n;

    /* renamed from: sr */
    private SecureRandom f1473sr;

    /* renamed from: t */
    private int f1474t;

    public void init(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.key = (McEliecePrivateKeyParameters) cipherParameters;
            initCipherDecrypt((McEliecePrivateKeyParameters) this.key);
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.f1473sr = parametersWithRandom.getRandom();
            this.key = (McEliecePublicKeyParameters) parametersWithRandom.getParameters();
            initCipherEncrypt((McEliecePublicKeyParameters) this.key);
        } else {
            this.f1473sr = new SecureRandom();
            this.key = (McEliecePublicKeyParameters) cipherParameters;
            initCipherEncrypt((McEliecePublicKeyParameters) this.key);
        }
    }

    public int getKeySize(McElieceKeyParameters mcElieceKeyParameters) {
        if (mcElieceKeyParameters instanceof McEliecePublicKeyParameters) {
            return ((McEliecePublicKeyParameters) mcElieceKeyParameters).getN();
        }
        if (mcElieceKeyParameters instanceof McEliecePrivateKeyParameters) {
            return ((McEliecePrivateKeyParameters) mcElieceKeyParameters).getN();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    public void initCipherEncrypt(McEliecePublicKeyParameters mcEliecePublicKeyParameters) {
        SecureRandom secureRandom = this.f1473sr;
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }
        this.f1473sr = secureRandom;
        this.f1472n = mcEliecePublicKeyParameters.getN();
        this.f1471k = mcEliecePublicKeyParameters.getK();
        this.f1474t = mcEliecePublicKeyParameters.getT();
        this.cipherTextSize = this.f1472n >> 3;
        this.maxPlainTextSize = this.f1471k >> 3;
    }

    public void initCipherDecrypt(McEliecePrivateKeyParameters mcEliecePrivateKeyParameters) {
        this.f1472n = mcEliecePrivateKeyParameters.getN();
        this.f1471k = mcEliecePrivateKeyParameters.getK();
        this.maxPlainTextSize = this.f1471k >> 3;
        this.cipherTextSize = this.f1472n >> 3;
    }

    public byte[] messageEncrypt(byte[] bArr) {
        GF2Vector computeMessageRepresentative = computeMessageRepresentative(bArr);
        return ((GF2Vector) ((McEliecePublicKeyParameters) this.key).getG().leftMultiply((Vector) computeMessageRepresentative).add(new GF2Vector(this.f1472n, this.f1474t, this.f1473sr))).getEncoded();
    }

    private GF2Vector computeMessageRepresentative(byte[] bArr) {
        byte[] bArr2 = new byte[(this.maxPlainTextSize + ((this.f1471k & 7) != 0 ? 1 : 0))];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        bArr2[bArr.length] = 1;
        return GF2Vector.OS2VP(this.f1471k, bArr2);
    }

    public byte[] messageDecrypt(byte[] bArr) throws Exception {
        GF2Vector OS2VP = GF2Vector.OS2VP(this.f1472n, bArr);
        McEliecePrivateKeyParameters mcEliecePrivateKeyParameters = (McEliecePrivateKeyParameters) this.key;
        GF2mField field = mcEliecePrivateKeyParameters.getField();
        PolynomialGF2mSmallM goppaPoly = mcEliecePrivateKeyParameters.getGoppaPoly();
        GF2Matrix sInv = mcEliecePrivateKeyParameters.getSInv();
        Permutation p1 = mcEliecePrivateKeyParameters.getP1();
        Permutation p2 = mcEliecePrivateKeyParameters.getP2();
        GF2Matrix h = mcEliecePrivateKeyParameters.getH();
        PolynomialGF2mSmallM[] qInv = mcEliecePrivateKeyParameters.getQInv();
        Permutation rightMultiply = p1.rightMultiply(p2);
        GF2Vector gF2Vector = (GF2Vector) OS2VP.multiply(rightMultiply.computeInverse());
        GF2Vector syndromeDecode = GoppaCode.syndromeDecode((GF2Vector) h.rightMultiply((Vector) gF2Vector), field, goppaPoly, qInv);
        GF2Vector gF2Vector2 = (GF2Vector) ((GF2Vector) gF2Vector.add(syndromeDecode)).multiply(p1);
        GF2Vector gF2Vector3 = (GF2Vector) syndromeDecode.multiply(rightMultiply);
        return computeMessage((GF2Vector) sInv.leftMultiply((Vector) gF2Vector2.extractRightVector(this.f1471k)));
    }

    private byte[] computeMessage(GF2Vector gF2Vector) throws Exception {
        byte[] encoded = gF2Vector.getEncoded();
        int length = encoded.length - 1;
        while (length >= 0 && encoded[length] == 0) {
            length--;
        }
        if (encoded[length] == 1) {
            byte[] bArr = new byte[length];
            System.arraycopy(encoded, 0, bArr, 0, length);
            return bArr;
        }
        throw new Exception("Bad Padding: invalid ciphertext");
    }
}
