package org.spongycastle.crypto.macs;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithSBox;

public class GOST28147Mac implements Mac {

    /* renamed from: S */
    private byte[] f1200S = {9, 6, 3, 2, 8, Ascii.f532VT, 1, 7, 10, 4, Ascii.f529SO, Ascii.f528SI, Ascii.f521FF, 0, Ascii.f519CR, 5, 3, 7, Ascii.f529SO, 9, 8, 10, Ascii.f528SI, 0, 5, 2, 6, Ascii.f521FF, Ascii.f532VT, 4, Ascii.f519CR, 1, Ascii.f529SO, 4, 6, 2, Ascii.f532VT, 3, Ascii.f519CR, 8, Ascii.f521FF, Ascii.f528SI, 5, 10, 0, 7, 1, 9, Ascii.f529SO, 7, 10, Ascii.f521FF, Ascii.f519CR, 1, 3, 9, 0, 2, Ascii.f532VT, 4, Ascii.f528SI, 8, 5, 6, Ascii.f532VT, 5, 1, 9, 8, Ascii.f519CR, Ascii.f528SI, 0, Ascii.f529SO, 4, 2, 3, Ascii.f521FF, 7, 10, 6, 3, 10, Ascii.f519CR, Ascii.f521FF, 1, 2, 0, Ascii.f532VT, 7, 5, 9, 4, 8, Ascii.f528SI, Ascii.f529SO, 6, 1, Ascii.f519CR, 2, 9, 7, 10, 6, 0, 8, Ascii.f521FF, 4, 5, Ascii.f528SI, 3, Ascii.f532VT, Ascii.f529SO, Ascii.f532VT, 10, Ascii.f528SI, 5, 0, Ascii.f521FF, Ascii.f529SO, 8, 6, 2, 3, 9, 1, 7, Ascii.f519CR, 4};
    private int blockSize = 8;
    private byte[] buf;
    private int bufOff;
    private boolean firstStep = true;
    private byte[] mac;
    private int macSize = 4;
    private int[] workingKey = null;

    public String getAlgorithmName() {
        return "GOST28147Mac";
    }

    public GOST28147Mac() {
        int i = this.blockSize;
        this.mac = new byte[i];
        this.buf = new byte[i];
        this.bufOff = 0;
    }

    private int[] generateWorkingKey(byte[] bArr) {
        if (bArr.length == 32) {
            int[] iArr = new int[8];
            for (int i = 0; i != 8; i++) {
                iArr[i] = bytesToint(bArr, i * 4);
            }
            return iArr;
        }
        throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        reset();
        this.buf = new byte[this.blockSize];
        if (cipherParameters instanceof ParametersWithSBox) {
            ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
            System.arraycopy(parametersWithSBox.getSBox(), 0, this.f1200S, 0, parametersWithSBox.getSBox().length);
            if (parametersWithSBox.getParameters() != null) {
                this.workingKey = generateWorkingKey(((KeyParameter) parametersWithSBox.getParameters()).getKey());
            }
        } else if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("invalid parameter passed to GOST28147 init - ");
            sb.append(cipherParameters.getClass().getName());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public int getMacSize() {
        return this.macSize;
    }

    private int gost28147_mainStep(int i, int i2) {
        int i3 = i2 + i;
        byte[] bArr = this.f1200S;
        int i4 = (bArr[((i3 >> 0) & 15) + 0] << 0) + (bArr[((i3 >> 4) & 15) + 16] << 4) + (bArr[((i3 >> 8) & 15) + 32] << 8) + (bArr[((i3 >> 12) & 15) + 48] << Ascii.f521FF) + (bArr[((i3 >> 16) & 15) + 64] << 16) + (bArr[((i3 >> 20) & 15) + 80] << Ascii.DC4) + (bArr[((i3 >> 24) & 15) + 96] << Ascii.CAN) + (bArr[((i3 >> 28) & 15) + 112] << Ascii.f522FS);
        return (i4 << 11) | (i4 >>> 21);
    }

    private void gost28147MacFunc(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int bytesToint = bytesToint(bArr, i);
        int bytesToint2 = bytesToint(bArr, i + 4);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = bytesToint2;
            int i5 = bytesToint;
            int i6 = 0;
            while (i6 < 8) {
                i6++;
                int gost28147_mainStep = i4 ^ gost28147_mainStep(i5, iArr[i6]);
                i4 = i5;
                i5 = gost28147_mainStep;
            }
            i3++;
            bytesToint = i5;
            bytesToint2 = i4;
        }
        intTobytes(bytesToint, bArr2, i2);
        intTobytes(bytesToint2, bArr2, i2 + 4);
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

    private byte[] CM5func(byte[] bArr, int i, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length - i)];
        System.arraycopy(bArr, i, bArr3, 0, bArr2.length);
        for (int i2 = 0; i2 != bArr2.length; i2++) {
            bArr3[i2] = (byte) (bArr3[i2] ^ bArr2[i2]);
        }
        return bArr3;
    }

    public void update(byte b) throws IllegalStateException {
        int i = this.bufOff;
        byte[] bArr = this.buf;
        if (i == bArr.length) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, this.mac.length);
            if (this.firstStep) {
                this.firstStep = false;
            } else {
                bArr2 = CM5func(this.buf, 0, this.mac);
            }
            gost28147MacFunc(this.workingKey, bArr2, 0, this.mac, 0);
            this.bufOff = 0;
        }
        byte[] bArr3 = this.buf;
        int i2 = this.bufOff;
        this.bufOff = i2 + 1;
        bArr3[i2] = b;
    }

    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        if (i2 >= 0) {
            int i3 = this.blockSize;
            int i4 = this.bufOff;
            int i5 = i3 - i4;
            if (i2 > i5) {
                System.arraycopy(bArr, i, this.buf, i4, i5);
                byte[] bArr2 = this.buf;
                byte[] bArr3 = new byte[bArr2.length];
                System.arraycopy(bArr2, 0, bArr3, 0, this.mac.length);
                if (this.firstStep) {
                    this.firstStep = false;
                } else {
                    bArr3 = CM5func(this.buf, 0, this.mac);
                }
                gost28147MacFunc(this.workingKey, bArr3, 0, this.mac, 0);
                this.bufOff = 0;
                while (true) {
                    i2 -= i5;
                    i += i5;
                    if (i2 <= this.blockSize) {
                        break;
                    }
                    gost28147MacFunc(this.workingKey, CM5func(bArr, i, this.mac), 0, this.mac, 0);
                    i5 = this.blockSize;
                }
            }
            System.arraycopy(bArr, i, this.buf, this.bufOff, i2);
            this.bufOff += i2;
            return;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        while (true) {
            int i2 = this.bufOff;
            if (i2 >= this.blockSize) {
                break;
            }
            this.buf[i2] = 0;
            this.bufOff = i2 + 1;
        }
        byte[] bArr2 = this.buf;
        byte[] bArr3 = new byte[bArr2.length];
        System.arraycopy(bArr2, 0, bArr3, 0, this.mac.length);
        if (this.firstStep) {
            this.firstStep = false;
        } else {
            bArr3 = CM5func(this.buf, 0, this.mac);
        }
        gost28147MacFunc(this.workingKey, bArr3, 0, this.mac, 0);
        byte[] bArr4 = this.mac;
        int length = bArr4.length / 2;
        int i3 = this.macSize;
        System.arraycopy(bArr4, length - i3, bArr, i, i3);
        reset();
        return this.macSize;
    }

    public void reset() {
        int i = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (i < bArr.length) {
                bArr[i] = 0;
                i++;
            } else {
                this.bufOff = 0;
                this.firstStep = true;
                return;
            }
        }
    }
}
