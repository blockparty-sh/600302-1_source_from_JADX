package org.spongycastle.crypto.engines;

import com.google.common.base.Ascii;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class TEAEngine implements BlockCipher {
    private static final int block_size = 8;
    private static final int d_sum = -957401312;
    private static final int delta = -1640531527;
    private static final int rounds = 32;

    /* renamed from: _a */
    private int f1171_a;

    /* renamed from: _b */
    private int f1172_b;

    /* renamed from: _c */
    private int f1173_c;

    /* renamed from: _d */
    private int f1174_d;
    private boolean _forEncryption;
    private boolean _initialised = false;

    public String getAlgorithmName() {
        return "TEA";
    }

    public int getBlockSize() {
        return 8;
    }

    public void reset() {
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this._forEncryption = z;
            this._initialised = true;
            setKey(((KeyParameter) cipherParameters).getKey());
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("invalid parameter passed to TEA init - ");
        sb.append(cipherParameters.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (!this._initialised) {
            StringBuilder sb = new StringBuilder();
            sb.append(getAlgorithmName());
            sb.append(" not initialised");
            throw new IllegalStateException(sb.toString());
        } else if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 8 <= bArr2.length) {
            return this._forEncryption ? encryptBlock(bArr, i, bArr2, i2) : decryptBlock(bArr, i, bArr2, i2);
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    private void setKey(byte[] bArr) {
        if (bArr.length == 16) {
            this.f1171_a = bytesToInt(bArr, 0);
            this.f1172_b = bytesToInt(bArr, 4);
            this.f1173_c = bytesToInt(bArr, 8);
            this.f1174_d = bytesToInt(bArr, 12);
            return;
        }
        throw new IllegalArgumentException("Key size must be 128 bits.");
    }

    private int encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int bytesToInt = bytesToInt(bArr, i);
        int bytesToInt2 = bytesToInt(bArr, i + 4);
        int i3 = 0;
        for (int i4 = 0; i4 != 32; i4++) {
            i3 -= 1640531527;
            bytesToInt += (((bytesToInt2 << 4) + this.f1171_a) ^ (bytesToInt2 + i3)) ^ ((bytesToInt2 >>> 5) + this.f1172_b);
            bytesToInt2 += (((bytesToInt << 4) + this.f1173_c) ^ (bytesToInt + i3)) ^ ((bytesToInt >>> 5) + this.f1174_d);
        }
        unpackInt(bytesToInt, bArr2, i2);
        unpackInt(bytesToInt2, bArr2, i2 + 4);
        return 8;
    }

    private int decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int bytesToInt = bytesToInt(bArr, i);
        int bytesToInt2 = bytesToInt(bArr, i + 4);
        int i3 = d_sum;
        for (int i4 = 0; i4 != 32; i4++) {
            bytesToInt2 -= (((bytesToInt << 4) + this.f1173_c) ^ (bytesToInt + i3)) ^ ((bytesToInt >>> 5) + this.f1174_d);
            bytesToInt -= (((bytesToInt2 << 4) + this.f1171_a) ^ (bytesToInt2 + i3)) ^ ((bytesToInt2 >>> 5) + this.f1172_b);
            i3 += 1640531527;
        }
        unpackInt(bytesToInt, bArr2, i2);
        unpackInt(bytesToInt2, bArr2, i2 + 4);
        return 8;
    }

    private int bytesToInt(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        byte b = (bArr[i] << Ascii.CAN) | ((bArr[i2] & 255) << 16);
        return (bArr[i3 + 1] & 255) | b | ((bArr[i3] & 255) << 8);
    }

    private void unpackInt(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i5] = (byte) i;
    }
}
