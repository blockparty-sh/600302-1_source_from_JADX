package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.paddings.ISO7816d4Padding;
import org.spongycastle.crypto.params.KeyParameter;

public class CMac implements Mac {
    private static final byte CONSTANT_128 = -121;
    private static final byte CONSTANT_64 = 27;

    /* renamed from: L */
    private byte[] f1198L;

    /* renamed from: Lu */
    private byte[] f1199Lu;
    private byte[] Lu2;
    private byte[] ZEROES;
    private byte[] buf;
    private int bufOff;
    private BlockCipher cipher;
    private byte[] mac;
    private int macSize;

    public CMac(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8);
    }

    public CMac(BlockCipher blockCipher, int i) {
        if (i % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        } else if (i > blockCipher.getBlockSize() * 8) {
            StringBuilder sb = new StringBuilder();
            sb.append("MAC size must be less or equal to ");
            sb.append(blockCipher.getBlockSize() * 8);
            throw new IllegalArgumentException(sb.toString());
        } else if (blockCipher.getBlockSize() == 8 || blockCipher.getBlockSize() == 16) {
            this.cipher = new CBCBlockCipher(blockCipher);
            this.macSize = i / 8;
            this.mac = new byte[blockCipher.getBlockSize()];
            this.buf = new byte[blockCipher.getBlockSize()];
            this.ZEROES = new byte[blockCipher.getBlockSize()];
            this.bufOff = 0;
        } else {
            throw new IllegalArgumentException("Block size must be either 64 or 128 bits");
        }
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName();
    }

    private static int shiftLeft(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int i = 0;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            byte b = bArr[length] & 255;
            bArr2[length] = (byte) (i | (b << 1));
            i = (b >>> 7) & 1;
        }
    }

    private static byte[] doubleLu(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int shiftLeft = shiftLeft(bArr, bArr2);
        int length = bArr.length - 1;
        bArr2[length] = (byte) ((((bArr.length == 16 ? -121 : 27) & 255) >>> ((1 - shiftLeft) << 3)) ^ bArr2[length]);
        return bArr2;
    }

    public void init(CipherParameters cipherParameters) {
        validate(cipherParameters);
        this.cipher.init(true, cipherParameters);
        byte[] bArr = this.ZEROES;
        this.f1198L = new byte[bArr.length];
        this.cipher.processBlock(bArr, 0, this.f1198L, 0);
        this.f1199Lu = doubleLu(this.f1198L);
        this.Lu2 = doubleLu(this.f1199Lu);
        reset();
    }

    /* access modifiers changed from: 0000 */
    public void validate(CipherParameters cipherParameters) {
        if (cipherParameters != null && !(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("CMac mode only permits key to be set.");
        }
    }

    public int getMacSize() {
        return this.macSize;
    }

    public void update(byte b) {
        int i = this.bufOff;
        byte[] bArr = this.buf;
        if (i == bArr.length) {
            this.cipher.processBlock(bArr, 0, this.mac, 0);
            this.bufOff = 0;
        }
        byte[] bArr2 = this.buf;
        int i2 = this.bufOff;
        this.bufOff = i2 + 1;
        bArr2[i2] = b;
    }

    public void update(byte[] bArr, int i, int i2) {
        if (i2 >= 0) {
            int blockSize = this.cipher.getBlockSize();
            int i3 = this.bufOff;
            int i4 = blockSize - i3;
            if (i2 > i4) {
                System.arraycopy(bArr, i, this.buf, i3, i4);
                this.cipher.processBlock(this.buf, 0, this.mac, 0);
                this.bufOff = 0;
                i2 -= i4;
                i += i4;
                while (i2 > blockSize) {
                    this.cipher.processBlock(bArr, i, this.mac, 0);
                    i2 -= blockSize;
                    i += blockSize;
                }
            }
            System.arraycopy(bArr, i, this.buf, this.bufOff, i2);
            this.bufOff += i2;
            return;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    public int doFinal(byte[] bArr, int i) {
        byte[] bArr2;
        if (this.bufOff == this.cipher.getBlockSize()) {
            bArr2 = this.f1199Lu;
        } else {
            new ISO7816d4Padding().addPadding(this.buf, this.bufOff);
            bArr2 = this.Lu2;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr3 = this.mac;
            if (i2 < bArr3.length) {
                byte[] bArr4 = this.buf;
                bArr4[i2] = (byte) (bArr4[i2] ^ bArr2[i2]);
                i2++;
            } else {
                this.cipher.processBlock(this.buf, 0, bArr3, 0);
                System.arraycopy(this.mac, 0, bArr, i, this.macSize);
                reset();
                return this.macSize;
            }
        }
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
                this.cipher.reset();
                return;
            }
        }
    }
}
