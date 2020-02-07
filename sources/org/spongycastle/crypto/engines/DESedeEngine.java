package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class DESedeEngine extends DESEngine {
    protected static final int BLOCK_SIZE = 8;
    private boolean forEncryption;
    private int[] workingKey1 = null;
    private int[] workingKey2 = null;
    private int[] workingKey3 = null;

    public String getAlgorithmName() {
        return "DESede";
    }

    public int getBlockSize() {
        return 8;
    }

    public void reset() {
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            if (key.length == 24 || key.length == 16) {
                this.forEncryption = z;
                byte[] bArr = new byte[8];
                System.arraycopy(key, 0, bArr, 0, bArr.length);
                this.workingKey1 = generateWorkingKey(z, bArr);
                byte[] bArr2 = new byte[8];
                System.arraycopy(key, 8, bArr2, 0, bArr2.length);
                this.workingKey2 = generateWorkingKey(!z, bArr2);
                if (key.length == 24) {
                    byte[] bArr3 = new byte[8];
                    System.arraycopy(key, 16, bArr3, 0, bArr3.length);
                    this.workingKey3 = generateWorkingKey(z, bArr3);
                    return;
                }
                this.workingKey3 = this.workingKey1;
                return;
            }
            throw new IllegalArgumentException("key size must be 16 or 24 bytes.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("invalid parameter passed to DESede init - ");
        sb.append(cipherParameters.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = this.workingKey1;
        if (iArr == null) {
            throw new IllegalStateException("DESede engine not initialised");
        } else if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 8 <= bArr2.length) {
            byte[] bArr3 = new byte[8];
            if (this.forEncryption) {
                byte[] bArr4 = bArr3;
                desFunc(iArr, bArr, i, bArr4, 0);
                byte[] bArr5 = bArr3;
                desFunc(this.workingKey2, bArr5, 0, bArr4, 0);
                desFunc(this.workingKey3, bArr5, 0, bArr2, i2);
            } else {
                byte[] bArr6 = bArr3;
                desFunc(this.workingKey3, bArr, i, bArr6, 0);
                byte[] bArr7 = bArr3;
                desFunc(this.workingKey2, bArr7, 0, bArr6, 0);
                desFunc(this.workingKey1, bArr7, 0, bArr2, i2);
            }
            return 8;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }
}
