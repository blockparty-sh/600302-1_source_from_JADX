package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;

public class NullEngine implements BlockCipher {
    protected static final int DEFAULT_BLOCK_SIZE = 1;
    private final int blockSize;
    private boolean initialised;

    public String getAlgorithmName() {
        return "Null";
    }

    public void reset() {
    }

    public NullEngine() {
        this(1);
    }

    public NullEngine(int i) {
        this.blockSize = i;
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.initialised = true;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.initialised) {
            int i3 = this.blockSize;
            if (i + i3 > bArr.length) {
                throw new DataLengthException("input buffer too short");
            } else if (i3 + i2 <= bArr2.length) {
                int i4 = 0;
                while (true) {
                    int i5 = this.blockSize;
                    if (i4 >= i5) {
                        return i5;
                    }
                    bArr2[i2 + i4] = bArr[i + i4];
                    i4++;
                }
            } else {
                throw new OutputLengthException("output buffer too short");
            }
        } else {
            throw new IllegalStateException("Null engine not initialised");
        }
    }
}
