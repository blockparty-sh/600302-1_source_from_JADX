package org.spongycastle.crypto;

public abstract class StreamBlockCipher implements BlockCipher, StreamCipher {
    private final BlockCipher cipher;

    /* access modifiers changed from: protected */
    public abstract byte calculateByte(byte b);

    protected StreamBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public final byte returnByte(byte b) {
        return calculateByte(b);
    }

    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (i3 + i2 <= bArr2.length) {
            int i4 = i + i2;
            if (i4 <= bArr.length) {
                while (i < i4) {
                    int i5 = i3 + 1;
                    int i6 = i + 1;
                    bArr2[i3] = calculateByte(bArr[i]);
                    i3 = i5;
                    i = i6;
                }
                return i2;
            }
            throw new DataLengthException("input buffer too small");
        }
        throw new DataLengthException("output buffer too short");
    }
}
