package org.spongycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.MessageSigner;
import org.spongycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.spongycastle.pqc.crypto.rainbow.util.GF2Field;

public class RainbowSigner implements MessageSigner {

    /* renamed from: cf */
    private ComputeInField f1541cf = new ComputeInField();
    RainbowKeyParameters key;
    private SecureRandom random;
    int signableDocumentLength;

    /* renamed from: x */
    private short[] f1542x;

    public void init(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.key = (RainbowPublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.random = parametersWithRandom.getRandom();
            this.key = (RainbowPrivateKeyParameters) parametersWithRandom.getParameters();
        } else {
            this.random = new SecureRandom();
            this.key = (RainbowPrivateKeyParameters) cipherParameters;
        }
        this.signableDocumentLength = this.key.getDocLength();
    }

    private short[] initSign(Layer[] layerArr, short[] sArr) {
        short[] sArr2 = new short[sArr.length];
        short[] multiplyMatrix = this.f1541cf.multiplyMatrix(((RainbowPrivateKeyParameters) this.key).getInvA1(), this.f1541cf.addVect(((RainbowPrivateKeyParameters) this.key).getB1(), sArr));
        for (int i = 0; i < layerArr[0].getVi(); i++) {
            this.f1542x[i] = (short) this.random.nextInt();
            short[] sArr3 = this.f1542x;
            sArr3[i] = (short) (sArr3[i] & 255);
        }
        return multiplyMatrix;
    }

    public byte[] generateSignature(byte[] bArr) {
        boolean z;
        Layer[] layers = ((RainbowPrivateKeyParameters) this.key).getLayers();
        int length = layers.length;
        this.f1542x = new short[((RainbowPrivateKeyParameters) this.key).getInvA2().length];
        byte[] bArr2 = new byte[layers[length - 1].getViNext()];
        short[] makeMessageRepresentative = makeMessageRepresentative(bArr);
        do {
            z = false;
            try {
                short[] initSign = initSign(layers, makeMessageRepresentative);
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    short[] sArr = new short[layers[i].getOi()];
                    short[] sArr2 = new short[layers[i].getOi()];
                    int i3 = i2;
                    for (int i4 = 0; i4 < layers[i].getOi(); i4++) {
                        sArr[i4] = initSign[i3];
                        i3++;
                    }
                    short[] solveEquation = this.f1541cf.solveEquation(layers[i].plugInVinegars(this.f1542x), sArr);
                    if (solveEquation != null) {
                        for (int i5 = 0; i5 < solveEquation.length; i5++) {
                            this.f1542x[layers[i].getVi() + i5] = solveEquation[i5];
                        }
                        i++;
                        i2 = i3;
                    } else {
                        throw new Exception("LES is not solveable!");
                    }
                }
                short[] multiplyMatrix = this.f1541cf.multiplyMatrix(((RainbowPrivateKeyParameters) this.key).getInvA2(), this.f1541cf.addVect(((RainbowPrivateKeyParameters) this.key).getB2(), this.f1542x));
                for (int i6 = 0; i6 < bArr2.length; i6++) {
                    bArr2[i6] = (byte) multiplyMatrix[i6];
                }
                z = true;
                continue;
            } catch (Exception unused) {
            }
        } while (!z);
        return bArr2;
    }

    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        short[] sArr = new short[bArr2.length];
        for (int i = 0; i < bArr2.length; i++) {
            sArr[i] = (short) (((short) bArr2[i]) & 255);
        }
        short[] makeMessageRepresentative = makeMessageRepresentative(bArr);
        short[] verifySignatureIntern = verifySignatureIntern(sArr);
        if (makeMessageRepresentative.length != verifySignatureIntern.length) {
            return false;
        }
        boolean z = true;
        for (int i2 = 0; i2 < makeMessageRepresentative.length; i2++) {
            z = z && makeMessageRepresentative[i2] == verifySignatureIntern[i2];
        }
        return z;
    }

    private short[] verifySignatureIntern(short[] sArr) {
        short[][] coeffQuadratic = ((RainbowPublicKeyParameters) this.key).getCoeffQuadratic();
        short[][] coeffSingular = ((RainbowPublicKeyParameters) this.key).getCoeffSingular();
        short[] coeffScalar = ((RainbowPublicKeyParameters) this.key).getCoeffScalar();
        short[] sArr2 = new short[coeffQuadratic.length];
        int length = coeffSingular[0].length;
        for (int i = 0; i < coeffQuadratic.length; i++) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                int i4 = i3;
                for (int i5 = i2; i5 < length; i5++) {
                    sArr2[i] = GF2Field.addElem(sArr2[i], GF2Field.multElem(coeffQuadratic[i][i4], GF2Field.multElem(sArr[i2], sArr[i5])));
                    i4++;
                }
                sArr2[i] = GF2Field.addElem(sArr2[i], GF2Field.multElem(coeffSingular[i][i2], sArr[i2]));
                i2++;
                i3 = i4;
            }
            sArr2[i] = GF2Field.addElem(sArr2[i], coeffScalar[i]);
        }
        return sArr2;
    }

    private short[] makeMessageRepresentative(byte[] bArr) {
        short[] sArr = new short[this.signableDocumentLength];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            sArr[i] = (short) bArr[i2];
            sArr[i] = (short) (sArr[i] & 255);
            i2++;
            i++;
            if (i >= sArr.length) {
                break;
            }
        }
        return sArr;
    }
}
