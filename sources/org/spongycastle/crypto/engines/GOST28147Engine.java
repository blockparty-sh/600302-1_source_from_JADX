package org.spongycastle.crypto.engines;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import java.util.Hashtable;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class GOST28147Engine implements BlockCipher {
    protected static final int BLOCK_SIZE = 8;
    private static byte[] DSbox_A = {10, 4, 5, 6, 8, 1, 3, 7, Ascii.f519CR, Ascii.f521FF, Ascii.f529SO, 0, 9, 2, Ascii.f532VT, Ascii.f528SI, 5, Ascii.f528SI, 4, 0, 2, Ascii.f519CR, Ascii.f532VT, 9, 1, 7, 6, 3, Ascii.f521FF, Ascii.f529SO, 10, 8, 7, Ascii.f528SI, Ascii.f521FF, Ascii.f529SO, 9, 4, 1, 0, 3, Ascii.f532VT, 5, 2, 6, 10, 8, Ascii.f519CR, 4, 10, 7, Ascii.f521FF, 0, Ascii.f528SI, 2, 8, Ascii.f529SO, 1, 6, 5, Ascii.f519CR, Ascii.f532VT, 9, 3, 7, 6, 4, Ascii.f532VT, 9, Ascii.f521FF, 2, 10, 1, 8, 0, Ascii.f529SO, Ascii.f528SI, Ascii.f519CR, 3, 5, 7, 6, 2, 4, Ascii.f519CR, 9, Ascii.f528SI, 0, 10, 1, 5, Ascii.f532VT, 8, Ascii.f529SO, Ascii.f521FF, 3, Ascii.f519CR, Ascii.f529SO, 4, 1, 7, 0, 5, 10, 3, Ascii.f521FF, 8, Ascii.f528SI, 6, 2, 9, Ascii.f532VT, 1, 3, 10, 9, 5, Ascii.f532VT, 4, Ascii.f528SI, 8, 6, 7, Ascii.f529SO, Ascii.f519CR, 0, 2, Ascii.f521FF};
    private static byte[] DSbox_Test = {4, 10, 9, 2, Ascii.f519CR, 8, 0, Ascii.f529SO, 6, Ascii.f532VT, 1, Ascii.f521FF, 7, Ascii.f528SI, 5, 3, Ascii.f529SO, Ascii.f532VT, 4, Ascii.f521FF, 6, Ascii.f519CR, Ascii.f528SI, 10, 2, 3, 8, 1, 0, 7, 5, 9, 5, 8, 1, Ascii.f519CR, 10, 3, 4, 2, Ascii.f529SO, Ascii.f528SI, Ascii.f521FF, 7, 6, 0, 9, Ascii.f532VT, 7, Ascii.f519CR, 10, 1, 0, 8, 9, Ascii.f528SI, Ascii.f529SO, 4, 6, Ascii.f521FF, Ascii.f532VT, 2, 5, 3, 6, Ascii.f521FF, 7, 1, 5, Ascii.f528SI, Ascii.f519CR, 8, 4, 10, 9, Ascii.f529SO, 0, 3, Ascii.f532VT, 2, 4, Ascii.f532VT, 10, 0, 7, 2, 1, Ascii.f519CR, 3, 6, 8, 5, 9, Ascii.f521FF, Ascii.f528SI, Ascii.f529SO, Ascii.f519CR, Ascii.f532VT, 4, 1, 3, Ascii.f528SI, 5, 9, 0, 10, Ascii.f529SO, 7, 6, 8, 2, Ascii.f521FF, 1, Ascii.f528SI, Ascii.f519CR, 0, 5, 7, 10, 4, 9, 2, 3, Ascii.f529SO, 6, Ascii.f532VT, 8, Ascii.f521FF};
    private static byte[] ESbox_A = {9, 6, 3, 2, 8, Ascii.f532VT, 1, 7, 10, 4, Ascii.f529SO, Ascii.f528SI, Ascii.f521FF, 0, Ascii.f519CR, 5, 3, 7, Ascii.f529SO, 9, 8, 10, Ascii.f528SI, 0, 5, 2, 6, Ascii.f521FF, Ascii.f532VT, 4, Ascii.f519CR, 1, Ascii.f529SO, 4, 6, 2, Ascii.f532VT, 3, Ascii.f519CR, 8, Ascii.f521FF, Ascii.f528SI, 5, 10, 0, 7, 1, 9, Ascii.f529SO, 7, 10, Ascii.f521FF, Ascii.f519CR, 1, 3, 9, 0, 2, Ascii.f532VT, 4, Ascii.f528SI, 8, 5, 6, Ascii.f532VT, 5, 1, 9, 8, Ascii.f519CR, Ascii.f528SI, 0, Ascii.f529SO, 4, 2, 3, Ascii.f521FF, 7, 10, 6, 3, 10, Ascii.f519CR, Ascii.f521FF, 1, 2, 0, Ascii.f532VT, 7, 5, 9, 4, 8, Ascii.f528SI, Ascii.f529SO, 6, 1, Ascii.f519CR, 2, 9, 7, 10, 6, 0, 8, Ascii.f521FF, 4, 5, Ascii.f528SI, 3, Ascii.f532VT, Ascii.f529SO, Ascii.f532VT, 10, Ascii.f528SI, 5, 0, Ascii.f521FF, Ascii.f529SO, 8, 6, 2, 3, 9, 1, 7, Ascii.f519CR, 4};
    private static byte[] ESbox_B = {8, 4, Ascii.f532VT, 1, 3, 5, 0, 9, 2, Ascii.f529SO, 10, Ascii.f521FF, Ascii.f519CR, 6, 7, Ascii.f528SI, 0, 1, 2, 10, 4, Ascii.f519CR, 5, Ascii.f521FF, 9, 7, 3, Ascii.f528SI, Ascii.f532VT, 8, 6, Ascii.f529SO, Ascii.f529SO, Ascii.f521FF, 0, 10, 9, 2, Ascii.f519CR, Ascii.f532VT, 7, 5, 8, Ascii.f528SI, 3, 6, 1, 4, 7, 5, 0, Ascii.f519CR, Ascii.f532VT, 6, 1, 2, 3, 10, Ascii.f521FF, Ascii.f528SI, 4, Ascii.f529SO, 9, 8, 2, 7, Ascii.f521FF, Ascii.f528SI, 9, 5, 10, Ascii.f532VT, 1, 4, 0, Ascii.f519CR, 6, 8, Ascii.f529SO, 3, 8, 3, 2, 6, 4, Ascii.f519CR, Ascii.f529SO, Ascii.f532VT, Ascii.f521FF, 1, 7, Ascii.f528SI, 10, 0, 9, 5, 5, 2, 10, Ascii.f532VT, 9, 1, Ascii.f521FF, 3, 7, 4, Ascii.f519CR, 0, 6, Ascii.f528SI, 8, Ascii.f529SO, 0, 4, Ascii.f532VT, Ascii.f529SO, 8, 3, 7, 1, 10, 2, 9, 6, Ascii.f528SI, Ascii.f519CR, 5, Ascii.f521FF};
    private static byte[] ESbox_C = {1, Ascii.f532VT, Ascii.f521FF, 2, 9, Ascii.f519CR, 0, Ascii.f528SI, 4, 5, 8, Ascii.f529SO, 10, 7, 6, 3, 0, 1, 7, Ascii.f519CR, Ascii.f532VT, 4, 5, 2, 8, Ascii.f529SO, Ascii.f528SI, Ascii.f521FF, 9, 10, 6, 3, 8, 2, 5, 0, 4, 9, Ascii.f528SI, 10, 3, 7, Ascii.f521FF, Ascii.f519CR, 6, Ascii.f529SO, 1, Ascii.f532VT, 3, 6, 0, 1, 5, Ascii.f519CR, 10, 8, Ascii.f532VT, 2, 9, 7, Ascii.f529SO, Ascii.f528SI, Ascii.f521FF, 4, 8, Ascii.f519CR, Ascii.f532VT, 0, 4, 5, 1, 2, 9, 3, Ascii.f521FF, Ascii.f529SO, 6, Ascii.f528SI, 10, 7, Ascii.f521FF, 9, Ascii.f532VT, 1, 8, Ascii.f529SO, 2, 4, 7, 3, 6, 5, 10, 0, Ascii.f528SI, Ascii.f519CR, 10, 9, 6, 8, Ascii.f519CR, Ascii.f529SO, 2, 0, Ascii.f528SI, 3, 5, Ascii.f532VT, 4, 1, Ascii.f521FF, 7, 7, 4, 0, 5, 10, 2, Ascii.f528SI, Ascii.f529SO, Ascii.f521FF, 6, 1, Ascii.f532VT, Ascii.f519CR, 9, 3, 8};
    private static byte[] ESbox_D = {Ascii.f528SI, Ascii.f521FF, 2, 10, 6, 4, 5, 0, 7, 9, Ascii.f529SO, Ascii.f519CR, 1, Ascii.f532VT, 8, 3, Ascii.f532VT, 6, 3, 4, Ascii.f521FF, Ascii.f528SI, Ascii.f529SO, 2, 7, Ascii.f519CR, 8, 0, 5, 10, 9, 1, 1, Ascii.f521FF, Ascii.f532VT, 0, Ascii.f528SI, Ascii.f529SO, 6, 5, 10, Ascii.f519CR, 4, 8, 9, 3, 7, 2, 1, 5, Ascii.f529SO, Ascii.f521FF, 10, 7, 0, Ascii.f519CR, 6, 2, Ascii.f532VT, 4, 9, 3, Ascii.f528SI, 8, 0, Ascii.f521FF, 8, 9, Ascii.f519CR, 2, 10, Ascii.f532VT, 7, 3, 6, 5, 4, Ascii.f529SO, Ascii.f528SI, 1, 8, 0, Ascii.f528SI, 3, 2, 5, Ascii.f529SO, Ascii.f532VT, 1, 10, 4, 7, Ascii.f521FF, 9, Ascii.f519CR, 6, 3, 0, 6, Ascii.f528SI, 1, Ascii.f529SO, 9, 2, Ascii.f519CR, 8, Ascii.f521FF, 4, Ascii.f532VT, 10, 5, 7, 1, 10, 6, 8, Ascii.f528SI, Ascii.f532VT, 0, 4, Ascii.f521FF, 3, 5, 9, 7, Ascii.f519CR, 2, Ascii.f529SO};
    private static byte[] ESbox_Test = {4, 2, Ascii.f528SI, 5, 9, 1, 0, 8, Ascii.f529SO, 3, Ascii.f532VT, Ascii.f521FF, Ascii.f519CR, 7, 10, 6, Ascii.f521FF, 9, Ascii.f528SI, Ascii.f529SO, 8, 1, 3, 10, 2, 7, 4, Ascii.f519CR, 6, 0, Ascii.f532VT, 5, Ascii.f519CR, 8, Ascii.f529SO, Ascii.f521FF, 7, 3, 9, 10, 1, 5, 2, 4, 6, Ascii.f528SI, 0, Ascii.f532VT, Ascii.f529SO, 9, Ascii.f532VT, 2, 5, Ascii.f528SI, 7, 1, 0, Ascii.f519CR, Ascii.f521FF, 6, 10, 4, 3, 8, 3, Ascii.f529SO, 5, 9, 6, 8, 0, Ascii.f519CR, 10, Ascii.f532VT, 7, Ascii.f521FF, 2, 1, Ascii.f528SI, 4, 8, Ascii.f528SI, 6, Ascii.f532VT, 1, 9, Ascii.f521FF, 5, Ascii.f519CR, 3, 7, 10, 0, Ascii.f529SO, 2, 4, 9, Ascii.f532VT, Ascii.f521FF, 0, 3, 6, 7, 5, 4, 8, Ascii.f529SO, Ascii.f528SI, 1, 10, 2, Ascii.f519CR, Ascii.f521FF, 6, 5, 2, Ascii.f532VT, 0, 9, Ascii.f519CR, 3, Ascii.f529SO, 7, 10, Ascii.f528SI, 4, 1, 8};
    private static byte[] Sbox_Default = {4, 10, 9, 2, Ascii.f519CR, 8, 0, Ascii.f529SO, 6, Ascii.f532VT, 1, Ascii.f521FF, 7, Ascii.f528SI, 5, 3, Ascii.f529SO, Ascii.f532VT, 4, Ascii.f521FF, 6, Ascii.f519CR, Ascii.f528SI, 10, 2, 3, 8, 1, 0, 7, 5, 9, 5, 8, 1, Ascii.f519CR, 10, 3, 4, 2, Ascii.f529SO, Ascii.f528SI, Ascii.f521FF, 7, 6, 0, 9, Ascii.f532VT, 7, Ascii.f519CR, 10, 1, 0, 8, 9, Ascii.f528SI, Ascii.f529SO, 4, 6, Ascii.f521FF, Ascii.f532VT, 2, 5, 3, 6, Ascii.f521FF, 7, 1, 5, Ascii.f528SI, Ascii.f519CR, 8, 4, 10, 9, Ascii.f529SO, 0, 3, Ascii.f532VT, 2, 4, Ascii.f532VT, 10, 0, 7, 2, 1, Ascii.f519CR, 3, 6, 8, 5, 9, Ascii.f521FF, Ascii.f528SI, Ascii.f529SO, Ascii.f519CR, Ascii.f532VT, 4, 1, 3, Ascii.f528SI, 5, 9, 0, 10, Ascii.f529SO, 7, 6, 8, 2, Ascii.f521FF, 1, Ascii.f528SI, Ascii.f519CR, 0, 5, 7, 10, 4, 9, 2, 3, Ascii.f529SO, 6, Ascii.f532VT, 8, Ascii.f521FF};
    private static Hashtable sBoxes = new Hashtable();

    /* renamed from: S */
    private byte[] f1137S = Sbox_Default;
    private boolean forEncryption;
    private int[] workingKey = null;

    public String getAlgorithmName() {
        return "GOST28147";
    }

    public int getBlockSize() {
        return 8;
    }

    public void reset() {
    }

    static {
        addSBox("Default", Sbox_Default);
        addSBox("E-TEST", ESbox_Test);
        addSBox("E-A", ESbox_A);
        addSBox("E-B", ESbox_B);
        addSBox("E-C", ESbox_C);
        addSBox("E-D", ESbox_D);
        addSBox("D-TEST", DSbox_Test);
        addSBox("D-A", DSbox_A);
    }

    private static void addSBox(String str, byte[] bArr) {
        sBoxes.put(Strings.toUpperCase(str), bArr);
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithSBox) {
            ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
            byte[] sBox = parametersWithSBox.getSBox();
            if (sBox.length == Sbox_Default.length) {
                this.f1137S = Arrays.clone(sBox);
                if (parametersWithSBox.getParameters() != null) {
                    this.workingKey = generateWorkingKey(z, ((KeyParameter) parametersWithSBox.getParameters()).getKey());
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("invalid S-box passed to GOST28147 init");
        } else if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(z, ((KeyParameter) cipherParameters).getKey());
        } else if (cipherParameters != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("invalid parameter passed to GOST28147 init - ");
            sb.append(cipherParameters.getClass().getName());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = this.workingKey;
        if (iArr == null) {
            throw new IllegalStateException("GOST28147 engine not initialised");
        } else if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 8 <= bArr2.length) {
            GOST28147Func(iArr, bArr, i, bArr2, i2);
            return 8;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    private int[] generateWorkingKey(boolean z, byte[] bArr) {
        this.forEncryption = z;
        if (bArr.length == 32) {
            int[] iArr = new int[8];
            for (int i = 0; i != 8; i++) {
                iArr[i] = bytesToint(bArr, i * 4);
            }
            return iArr;
        }
        throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
    }

    private int GOST28147_mainStep(int i, int i2) {
        int i3 = i2 + i;
        byte[] bArr = this.f1137S;
        int i4 = (bArr[((i3 >> 0) & 15) + 0] << 0) + (bArr[((i3 >> 4) & 15) + 16] << 4) + (bArr[((i3 >> 8) & 15) + 32] << 8) + (bArr[((i3 >> 12) & 15) + 48] << Ascii.f521FF) + (bArr[((i3 >> 16) & 15) + 64] << 16) + (bArr[((i3 >> 20) & 15) + 80] << Ascii.DC4) + (bArr[((i3 >> 24) & 15) + 96] << Ascii.CAN) + (bArr[((i3 >> 28) & 15) + 112] << Ascii.f522FS);
        return (i4 << 11) | (i4 >>> 21);
    }

    private void GOST28147Func(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3;
        int i4;
        int bytesToint = bytesToint(bArr, i);
        int bytesToint2 = bytesToint(bArr, i + 4);
        int i5 = 7;
        if (this.forEncryption) {
            int i6 = bytesToint2;
            int i7 = 0;
            while (i7 < 3) {
                int i8 = i6;
                int i9 = 0;
                while (i9 < 8) {
                    i9++;
                    int GOST28147_mainStep = i8 ^ GOST28147_mainStep(bytesToint, iArr[i9]);
                    i8 = bytesToint;
                    bytesToint = GOST28147_mainStep;
                }
                i7++;
                i6 = i8;
            }
            i4 = i6;
            i3 = bytesToint;
            while (i5 > 0) {
                i5--;
                int i10 = i3;
                i3 = i4 ^ GOST28147_mainStep(i3, iArr[i5]);
                i4 = i10;
            }
        } else {
            int i11 = bytesToint2;
            int i12 = 0;
            while (i12 < 8) {
                i12++;
                int i13 = bytesToint;
                bytesToint = i11 ^ GOST28147_mainStep(bytesToint, iArr[i12]);
                i11 = i13;
            }
            int i14 = 0;
            int i15 = bytesToint;
            int i16 = i11;
            int i17 = i15;
            while (i14 < 3) {
                int i18 = i16;
                int i19 = i3;
                int i20 = 7;
                while (i20 >= 0 && (i14 != 2 || i20 != 0)) {
                    i20--;
                    int GOST28147_mainStep2 = i18 ^ GOST28147_mainStep(i19, iArr[i20]);
                    i18 = i19;
                    i19 = GOST28147_mainStep2;
                }
                i14++;
                i17 = i19;
                i16 = i18;
            }
            i4 = i16;
        }
        int GOST28147_mainStep3 = GOST28147_mainStep(i3, iArr[0]) ^ i4;
        intTobytes(i3, bArr2, i2);
        intTobytes(GOST28147_mainStep3, bArr2, i2 + 4);
    }

    private int bytesToint(byte[] bArr, int i) {
        return ((bArr[i + 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) + ((bArr[i + 2] << 16) & 16711680) + ((bArr[i + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[i] & 255);
    }

    private void intTobytes(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >>> 24);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2] = (byte) i;
    }

    public static byte[] getSBox(String str) {
        byte[] bArr = (byte[]) sBoxes.get(Strings.toUpperCase(str));
        if (bArr != null) {
            return Arrays.clone(bArr);
        }
        throw new IllegalArgumentException("Unknown S-Box - possible types: \"Default\", \"E-Test\", \"E-A\", \"E-B\", \"E-C\", \"E-D\", \"D-Test\", \"D-A\".");
    }
}
