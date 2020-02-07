package org.spongycastle.crypto.engines;

import com.google.common.base.Ascii;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.RC5Parameters;

public class RC532Engine implements BlockCipher {
    private static final int P32 = -1209970333;
    private static final int Q32 = -1640531527;

    /* renamed from: _S */
    private int[] f1153_S = null;
    private int _noRounds = 12;
    private boolean forEncryption;

    private int rotateLeft(int i, int i2) {
        int i3 = i2 & 31;
        return (i >>> (32 - i3)) | (i << i3);
    }

    private int rotateRight(int i, int i2) {
        int i3 = i2 & 31;
        return (i << (32 - i3)) | (i >>> i3);
    }

    public String getAlgorithmName() {
        return "RC5-32";
    }

    public int getBlockSize() {
        return 8;
    }

    public void reset() {
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof RC5Parameters) {
            RC5Parameters rC5Parameters = (RC5Parameters) cipherParameters;
            this._noRounds = rC5Parameters.getRounds();
            setKey(rC5Parameters.getKey());
        } else if (cipherParameters instanceof KeyParameter) {
            setKey(((KeyParameter) cipherParameters).getKey());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("invalid parameter passed to RC532 init - ");
            sb.append(cipherParameters.getClass().getName());
            throw new IllegalArgumentException(sb.toString());
        }
        this.forEncryption = z;
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        return this.forEncryption ? encryptBlock(bArr, i, bArr2, i2) : decryptBlock(bArr, i, bArr2, i2);
    }

    private void setKey(byte[] bArr) {
        int[] iArr;
        int i;
        int[] iArr2 = new int[((bArr.length + 3) / 4)];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            int i3 = i2 / 4;
            iArr2[i3] = iArr2[i3] + ((bArr[i2] & 255) << ((i2 % 4) * 8));
        }
        this.f1153_S = new int[((this._noRounds + 1) * 2)];
        this.f1153_S[0] = P32;
        int i4 = 1;
        while (true) {
            iArr = this.f1153_S;
            if (i4 >= iArr.length) {
                break;
            }
            iArr[i4] = iArr[i4 - 1] + Q32;
            i4++;
        }
        if (iArr2.length > iArr.length) {
            i = iArr2.length;
        } else {
            i = iArr.length;
        }
        int i5 = i * 3;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < i5; i10++) {
            int[] iArr3 = this.f1153_S;
            i7 = rotateLeft(iArr3[i6] + i7 + i8, 3);
            iArr3[i6] = i7;
            i8 = rotateLeft(iArr2[i9] + i7 + i8, i8 + i7);
            iArr2[i9] = i8;
            i6 = (i6 + 1) % this.f1153_S.length;
            i9 = (i9 + 1) % iArr2.length;
        }
    }

    private int encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int bytesToWord = bytesToWord(bArr, i) + this.f1153_S[0];
        int bytesToWord2 = bytesToWord(bArr, i + 4) + this.f1153_S[1];
        for (int i3 = 1; i3 <= this._noRounds; i3++) {
            int i4 = i3 * 2;
            bytesToWord = rotateLeft(bytesToWord ^ bytesToWord2, bytesToWord2) + this.f1153_S[i4];
            bytesToWord2 = rotateLeft(bytesToWord2 ^ bytesToWord, bytesToWord) + this.f1153_S[i4 + 1];
        }
        wordToBytes(bytesToWord, bArr2, i2);
        wordToBytes(bytesToWord2, bArr2, i2 + 4);
        return 8;
    }

    private int decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int bytesToWord = bytesToWord(bArr, i);
        int bytesToWord2 = bytesToWord(bArr, i + 4);
        for (int i3 = this._noRounds; i3 >= 1; i3--) {
            int i4 = i3 * 2;
            bytesToWord2 = rotateRight(bytesToWord2 - this.f1153_S[i4 + 1], bytesToWord) ^ bytesToWord;
            bytesToWord = rotateRight(bytesToWord - this.f1153_S[i4], bytesToWord2) ^ bytesToWord2;
        }
        wordToBytes(bytesToWord - this.f1153_S[0], bArr2, i2);
        wordToBytes(bytesToWord2 - this.f1153_S[1], bArr2, i2 + 4);
        return 8;
    }

    private int bytesToWord(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private void wordToBytes(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 3] = (byte) (i >> 24);
    }
}
