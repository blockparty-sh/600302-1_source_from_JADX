package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.StreamBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;

public class OFBBlockCipher extends StreamBlockCipher {

    /* renamed from: IV */
    private byte[] f1250IV;
    private final int blockSize;
    private int byteCount;
    private final BlockCipher cipher;
    private byte[] ofbOutV;
    private byte[] ofbV;

    public OFBBlockCipher(BlockCipher blockCipher, int i) {
        super(blockCipher);
        this.cipher = blockCipher;
        this.blockSize = i / 8;
        this.f1250IV = new byte[blockCipher.getBlockSize()];
        this.ofbV = new byte[blockCipher.getBlockSize()];
        this.ofbOutV = new byte[blockCipher.getBlockSize()];
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            int length = iv.length;
            byte[] bArr = this.f1250IV;
            if (length < bArr.length) {
                System.arraycopy(iv, 0, bArr, bArr.length - iv.length, iv.length);
                int i = 0;
                while (true) {
                    byte[] bArr2 = this.f1250IV;
                    if (i >= bArr2.length - iv.length) {
                        break;
                    }
                    bArr2[i] = 0;
                    i++;
                }
            } else {
                System.arraycopy(iv, 0, bArr, 0, bArr.length);
            }
            reset();
            if (parametersWithIV.getParameters() != null) {
                this.cipher.init(true, parametersWithIV.getParameters());
                return;
            }
            return;
        }
        reset();
        if (cipherParameters != null) {
            this.cipher.init(true, cipherParameters);
        }
    }

    public String getAlgorithmName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.cipher.getAlgorithmName());
        sb.append("/OFB");
        sb.append(this.blockSize * 8);
        return sb.toString();
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.blockSize, bArr2, i2);
        return this.blockSize;
    }

    public void reset() {
        byte[] bArr = this.f1250IV;
        System.arraycopy(bArr, 0, this.ofbV, 0, bArr.length);
        this.byteCount = 0;
        this.cipher.reset();
    }

    /* access modifiers changed from: protected */
    public byte calculateByte(byte b) throws DataLengthException, IllegalStateException {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.ofbV, 0, this.ofbOutV, 0);
        }
        byte[] bArr = this.ofbOutV;
        int i = this.byteCount;
        this.byteCount = i + 1;
        byte b2 = (byte) (b ^ bArr[i]);
        int i2 = this.byteCount;
        int i3 = this.blockSize;
        if (i2 == i3) {
            this.byteCount = 0;
            byte[] bArr2 = this.ofbV;
            System.arraycopy(bArr2, i3, bArr2, 0, bArr2.length - i3);
            byte[] bArr3 = this.ofbOutV;
            byte[] bArr4 = this.ofbV;
            int length = bArr4.length;
            int i4 = this.blockSize;
            System.arraycopy(bArr3, 0, bArr4, length - i4, i4);
        }
        return b2;
    }
}
