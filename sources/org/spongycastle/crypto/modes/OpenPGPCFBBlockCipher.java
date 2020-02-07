package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;

public class OpenPGPCFBBlockCipher implements BlockCipher {

    /* renamed from: FR */
    private byte[] f1251FR;
    private byte[] FRE;

    /* renamed from: IV */
    private byte[] f1252IV;
    private int blockSize;
    private BlockCipher cipher;
    private int count;
    private boolean forEncryption;

    public OpenPGPCFBBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        this.blockSize = blockCipher.getBlockSize();
        int i = this.blockSize;
        this.f1252IV = new byte[i];
        this.f1251FR = new byte[i];
        this.FRE = new byte[i];
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public String getAlgorithmName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.cipher.getAlgorithmName());
        sb.append("/OpenPGPCFB");
        return sb.toString();
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        return this.forEncryption ? encryptBlock(bArr, i, bArr2, i2) : decryptBlock(bArr, i, bArr2, i2);
    }

    public void reset() {
        this.count = 0;
        byte[] bArr = this.f1252IV;
        byte[] bArr2 = this.f1251FR;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.cipher.reset();
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.forEncryption = z;
        reset();
        this.cipher.init(true, cipherParameters);
    }

    private byte encryptByte(byte b, int i) {
        return (byte) (b ^ this.FRE[i]);
    }

    private int encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3;
        int i4;
        int i5 = this.blockSize;
        if (i + i5 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + i5 <= bArr2.length) {
            int i6 = this.count;
            int i7 = 2;
            int i8 = 0;
            if (i6 > i5) {
                byte[] bArr3 = this.f1251FR;
                int i9 = i5 - 2;
                byte encryptByte = encryptByte(bArr[i], i5 - 2);
                bArr2[i2] = encryptByte;
                bArr3[i9] = encryptByte;
                byte[] bArr4 = this.f1251FR;
                int i10 = this.blockSize;
                int i11 = i10 - 1;
                int i12 = i2 + 1;
                byte encryptByte2 = encryptByte(bArr[i + 1], i10 - 1);
                bArr2[i12] = encryptByte2;
                bArr4[i11] = encryptByte2;
                this.cipher.processBlock(this.f1251FR, 0, this.FRE, 0);
                while (i7 < this.blockSize) {
                    byte[] bArr5 = this.f1251FR;
                    int i13 = i7 - 2;
                    int i14 = i2 + i7;
                    byte encryptByte3 = encryptByte(bArr[i + i7], i13);
                    bArr2[i14] = encryptByte3;
                    bArr5[i13] = encryptByte3;
                    i7++;
                }
            } else if (i6 == 0) {
                this.cipher.processBlock(this.f1251FR, 0, this.FRE, 0);
                while (true) {
                    i4 = this.blockSize;
                    if (i8 >= i4) {
                        break;
                    }
                    byte[] bArr6 = this.f1251FR;
                    int i15 = i2 + i8;
                    byte encryptByte4 = encryptByte(bArr[i + i8], i8);
                    bArr2[i15] = encryptByte4;
                    bArr6[i8] = encryptByte4;
                    i8++;
                }
                this.count += i4;
            } else if (i6 == i5) {
                this.cipher.processBlock(this.f1251FR, 0, this.FRE, 0);
                bArr2[i2] = encryptByte(bArr[i], 0);
                bArr2[i2 + 1] = encryptByte(bArr[i + 1], 1);
                byte[] bArr7 = this.f1251FR;
                System.arraycopy(bArr7, 2, bArr7, 0, this.blockSize - 2);
                System.arraycopy(bArr2, i2, this.f1251FR, this.blockSize - 2, 2);
                this.cipher.processBlock(this.f1251FR, 0, this.FRE, 0);
                while (true) {
                    i3 = this.blockSize;
                    if (i7 >= i3) {
                        break;
                    }
                    byte[] bArr8 = this.f1251FR;
                    int i16 = i7 - 2;
                    int i17 = i2 + i7;
                    byte encryptByte5 = encryptByte(bArr[i + i7], i16);
                    bArr2[i17] = encryptByte5;
                    bArr8[i16] = encryptByte5;
                    i7++;
                }
                this.count += i3;
            }
            return this.blockSize;
        } else {
            throw new DataLengthException("output buffer too short");
        }
    }

    private int decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3;
        int i4;
        int i5 = this.blockSize;
        if (i + i5 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + i5 <= bArr2.length) {
            int i6 = this.count;
            int i7 = 2;
            int i8 = 0;
            if (i6 > i5) {
                byte b = bArr[i];
                this.f1251FR[i5 - 2] = b;
                bArr2[i2] = encryptByte(b, i5 - 2);
                byte b2 = bArr[i + 1];
                byte[] bArr3 = this.f1251FR;
                int i9 = this.blockSize;
                bArr3[i9 - 1] = b2;
                bArr2[i2 + 1] = encryptByte(b2, i9 - 1);
                this.cipher.processBlock(this.f1251FR, 0, this.FRE, 0);
                while (i7 < this.blockSize) {
                    byte b3 = bArr[i + i7];
                    int i10 = i7 - 2;
                    this.f1251FR[i10] = b3;
                    bArr2[i2 + i7] = encryptByte(b3, i10);
                    i7++;
                }
            } else if (i6 == 0) {
                this.cipher.processBlock(this.f1251FR, 0, this.FRE, 0);
                while (true) {
                    i4 = this.blockSize;
                    if (i8 >= i4) {
                        break;
                    }
                    int i11 = i + i8;
                    this.f1251FR[i8] = bArr[i11];
                    bArr2[i8] = encryptByte(bArr[i11], i8);
                    i8++;
                }
                this.count += i4;
            } else if (i6 == i5) {
                this.cipher.processBlock(this.f1251FR, 0, this.FRE, 0);
                byte b4 = bArr[i];
                byte b5 = bArr[i + 1];
                bArr2[i2] = encryptByte(b4, 0);
                bArr2[i2 + 1] = encryptByte(b5, 1);
                byte[] bArr4 = this.f1251FR;
                System.arraycopy(bArr4, 2, bArr4, 0, this.blockSize - 2);
                byte[] bArr5 = this.f1251FR;
                int i12 = this.blockSize;
                bArr5[i12 - 2] = b4;
                bArr5[i12 - 1] = b5;
                this.cipher.processBlock(bArr5, 0, this.FRE, 0);
                while (true) {
                    i3 = this.blockSize;
                    if (i7 >= i3) {
                        break;
                    }
                    byte b6 = bArr[i + i7];
                    int i13 = i7 - 2;
                    this.f1251FR[i13] = b6;
                    bArr2[i2 + i7] = encryptByte(b6, i13);
                    i7++;
                }
                this.count += i3;
            }
            return this.blockSize;
        } else {
            throw new DataLengthException("output buffer too short");
        }
    }
}
