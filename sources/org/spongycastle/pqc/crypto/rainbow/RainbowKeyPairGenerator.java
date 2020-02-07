package org.spongycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.spongycastle.pqc.crypto.rainbow.util.GF2Field;

public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {

    /* renamed from: A1 */
    private short[][] f1531A1;
    private short[][] A1inv;

    /* renamed from: A2 */
    private short[][] f1532A2;
    private short[][] A2inv;

    /* renamed from: b1 */
    private short[] f1533b1;

    /* renamed from: b2 */
    private short[] f1534b2;
    private boolean initialized = false;
    private Layer[] layers;
    private int numOfLayers;
    private short[][] pub_quadratic;
    private short[] pub_scalar;
    private short[][] pub_singular;
    private RainbowKeyGenerationParameters rainbowParams;

    /* renamed from: sr */
    private SecureRandom f1535sr;

    /* renamed from: vi */
    private int[] f1536vi;

    public AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        keygen();
        RainbowPrivateKeyParameters rainbowPrivateKeyParameters = new RainbowPrivateKeyParameters(this.A1inv, this.f1533b1, this.A2inv, this.f1534b2, this.f1536vi, this.layers);
        int[] iArr = this.f1536vi;
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new RainbowPublicKeyParameters(iArr[iArr.length - 1] - iArr[0], this.pub_quadratic, this.pub_singular, this.pub_scalar), (AsymmetricKeyParameter) rainbowPrivateKeyParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.rainbowParams = (RainbowKeyGenerationParameters) keyGenerationParameters;
        this.f1535sr = new SecureRandom();
        this.f1536vi = this.rainbowParams.getParameters().getVi();
        this.numOfLayers = this.rainbowParams.getParameters().getNumOfLayers();
        this.initialized = true;
    }

    private void initializeDefault() {
        initialize(new RainbowKeyGenerationParameters(new SecureRandom(), new RainbowParameters()));
    }

    private void keygen() {
        generateL1();
        generateL2();
        generateF();
        computePublicKey();
    }

    private void generateL1() {
        int[] iArr = this.f1536vi;
        int i = iArr[iArr.length - 1] - iArr[0];
        this.f1531A1 = (short[][]) Array.newInstance(short.class, new int[]{i, i});
        this.A1inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A1inv == null) {
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    this.f1531A1[i2][i3] = (short) (this.f1535sr.nextInt() & 255);
                }
            }
            this.A1inv = computeInField.inverse(this.f1531A1);
        }
        this.f1533b1 = new short[i];
        for (int i4 = 0; i4 < i; i4++) {
            this.f1533b1[i4] = (short) (this.f1535sr.nextInt() & 255);
        }
    }

    private void generateL2() {
        int i;
        int[] iArr = this.f1536vi;
        int i2 = iArr[iArr.length - 1];
        this.f1532A2 = (short[][]) Array.newInstance(short.class, new int[]{i2, i2});
        this.A2inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (true) {
            if (this.A2inv != null) {
                break;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    this.f1532A2[i3][i4] = (short) (this.f1535sr.nextInt() & 255);
                }
            }
            this.A2inv = computeInField.inverse(this.f1532A2);
        }
        this.f1534b2 = new short[i2];
        for (i = 0; i < i2; i++) {
            this.f1534b2[i] = (short) (this.f1535sr.nextInt() & 255);
        }
    }

    private void generateF() {
        this.layers = new Layer[this.numOfLayers];
        int i = 0;
        while (i < this.numOfLayers) {
            Layer[] layerArr = this.layers;
            int[] iArr = this.f1536vi;
            int i2 = i + 1;
            layerArr[i] = new Layer(iArr[i], iArr[i2], this.f1535sr);
            i = i2;
        }
    }

    private void computePublicKey() {
        ComputeInField computeInField = new ComputeInField();
        int[] iArr = this.f1536vi;
        int i = 0;
        int i2 = iArr[iArr.length - 1] - iArr[0];
        int i3 = iArr[iArr.length - 1];
        short[][][] sArr = (short[][][]) Array.newInstance(short.class, new int[]{i2, i3, i3});
        this.pub_singular = (short[][]) Array.newInstance(short.class, new int[]{i2, i3});
        this.pub_scalar = new short[i2];
        short[] sArr2 = new short[i3];
        int i4 = 0;
        int i5 = 0;
        while (true) {
            Layer[] layerArr = this.layers;
            if (i4 >= layerArr.length) {
                break;
            }
            short[][][] coeffAlpha = layerArr[i4].getCoeffAlpha();
            short[][][] coeffBeta = this.layers[i4].getCoeffBeta();
            short[][] coeffGamma = this.layers[i4].getCoeffGamma();
            short[] coeffEta = this.layers[i4].getCoeffEta();
            int length = coeffAlpha[i].length;
            int length2 = coeffBeta[i].length;
            int i6 = 0;
            while (i6 < length) {
                int i7 = 0;
                while (i7 < length) {
                    while (i < length2) {
                        int i8 = i3;
                        int i9 = i2;
                        int i10 = i7 + length2;
                        short[] multVect = computeInField.multVect(coeffAlpha[i6][i7][i], this.f1532A2[i10]);
                        int i11 = i5 + i6;
                        int i12 = i4;
                        short[] sArr3 = coeffEta;
                        sArr[i11] = computeInField.addSquareMatrix(sArr[i11], computeInField.multVects(multVect, this.f1532A2[i]));
                        short[] multVect2 = computeInField.multVect(this.f1534b2[i], multVect);
                        short[][] sArr4 = this.pub_singular;
                        sArr4[i11] = computeInField.addVect(multVect2, sArr4[i11]);
                        short[] multVect3 = computeInField.multVect(this.f1534b2[i10], computeInField.multVect(coeffAlpha[i6][i7][i], this.f1532A2[i]));
                        short[][] sArr5 = this.pub_singular;
                        sArr5[i11] = computeInField.addVect(multVect3, sArr5[i11]);
                        short multElem = GF2Field.multElem(coeffAlpha[i6][i7][i], this.f1534b2[i10]);
                        short[] sArr6 = this.pub_scalar;
                        short[][][] sArr7 = coeffAlpha;
                        sArr6[i11] = GF2Field.addElem(sArr6[i11], GF2Field.multElem(multElem, this.f1534b2[i]));
                        i++;
                        i2 = i9;
                        i3 = i8;
                        coeffAlpha = sArr7;
                        i4 = i12;
                        coeffEta = sArr3;
                    }
                    int i13 = i3;
                    int i14 = i2;
                    int i15 = i4;
                    short[][][] sArr8 = coeffAlpha;
                    short[] sArr9 = coeffEta;
                    i7++;
                    i = 0;
                }
                int i16 = i3;
                int i17 = i2;
                int i18 = i4;
                short[][][] sArr10 = coeffAlpha;
                short[] sArr11 = coeffEta;
                for (int i19 = 0; i19 < length2; i19++) {
                    for (int i20 = 0; i20 < length2; i20++) {
                        short[] multVect4 = computeInField.multVect(coeffBeta[i6][i19][i20], this.f1532A2[i19]);
                        int i21 = i5 + i6;
                        sArr[i21] = computeInField.addSquareMatrix(sArr[i21], computeInField.multVects(multVect4, this.f1532A2[i20]));
                        short[] multVect5 = computeInField.multVect(this.f1534b2[i20], multVect4);
                        short[][] sArr12 = this.pub_singular;
                        sArr12[i21] = computeInField.addVect(multVect5, sArr12[i21]);
                        short[] multVect6 = computeInField.multVect(this.f1534b2[i19], computeInField.multVect(coeffBeta[i6][i19][i20], this.f1532A2[i20]));
                        short[][] sArr13 = this.pub_singular;
                        sArr13[i21] = computeInField.addVect(multVect6, sArr13[i21]);
                        short multElem2 = GF2Field.multElem(coeffBeta[i6][i19][i20], this.f1534b2[i19]);
                        short[] sArr14 = this.pub_scalar;
                        sArr14[i21] = GF2Field.addElem(sArr14[i21], GF2Field.multElem(multElem2, this.f1534b2[i20]));
                    }
                }
                for (int i22 = 0; i22 < length2 + length; i22++) {
                    short[] multVect7 = computeInField.multVect(coeffGamma[i6][i22], this.f1532A2[i22]);
                    short[][] sArr15 = this.pub_singular;
                    int i23 = i5 + i6;
                    sArr15[i23] = computeInField.addVect(multVect7, sArr15[i23]);
                    short[] sArr16 = this.pub_scalar;
                    sArr16[i23] = GF2Field.addElem(sArr16[i23], GF2Field.multElem(coeffGamma[i6][i22], this.f1534b2[i22]));
                }
                short[] sArr17 = this.pub_scalar;
                int i24 = i5 + i6;
                sArr17[i24] = GF2Field.addElem(sArr17[i24], sArr11[i6]);
                i6++;
                i2 = i17;
                i3 = i16;
                coeffAlpha = sArr10;
                i4 = i18;
                coeffEta = sArr11;
                i = 0;
            }
            int i25 = i3;
            int i26 = i2;
            i5 += length;
            i4++;
            i = 0;
        }
        short[][][] sArr18 = (short[][][]) Array.newInstance(short.class, new int[]{i2, i3, i3});
        short[][] sArr19 = (short[][]) Array.newInstance(short.class, new int[]{i2, i3});
        short[] sArr20 = new short[i2];
        for (int i27 = 0; i27 < i2; i27++) {
            int i28 = 0;
            while (true) {
                short[][] sArr21 = this.f1531A1;
                if (i28 >= sArr21.length) {
                    break;
                }
                sArr18[i27] = computeInField.addSquareMatrix(sArr18[i27], computeInField.multMatrix(sArr21[i27][i28], sArr[i28]));
                sArr19[i27] = computeInField.addVect(sArr19[i27], computeInField.multVect(this.f1531A1[i27][i28], this.pub_singular[i28]));
                sArr20[i27] = GF2Field.addElem(sArr20[i27], GF2Field.multElem(this.f1531A1[i27][i28], this.pub_scalar[i28]));
                i28++;
            }
            sArr20[i27] = GF2Field.addElem(sArr20[i27], this.f1533b1[i27]);
        }
        this.pub_singular = sArr19;
        this.pub_scalar = sArr20;
        compactPublicKey(sArr18);
    }

    private void compactPublicKey(short[][][] sArr) {
        int length = sArr.length;
        int length2 = sArr[0].length;
        this.pub_quadratic = (short[][]) Array.newInstance(short.class, new int[]{length, ((length2 + 1) * length2) / 2});
        for (int i = 0; i < length; i++) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < length2) {
                int i4 = i3;
                for (int i5 = i2; i5 < length2; i5++) {
                    if (i5 == i2) {
                        this.pub_quadratic[i][i4] = sArr[i][i2][i5];
                    } else {
                        this.pub_quadratic[i][i4] = GF2Field.addElem(sArr[i][i2][i5], sArr[i][i5][i2]);
                    }
                    i4++;
                }
                i2++;
                i3 = i4;
            }
        }
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }
}
