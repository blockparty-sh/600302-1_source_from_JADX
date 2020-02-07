package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.modes.gcm.GCMExponentiator;
import org.spongycastle.crypto.modes.gcm.GCMMultiplier;
import org.spongycastle.crypto.modes.gcm.Tables1kGCMExponentiator;
import org.spongycastle.crypto.modes.gcm.Tables8kGCMMultiplier;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

public class GCMBlockCipher implements AEADBlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: H */
    private byte[] f1241H;

    /* renamed from: J0 */
    private byte[] f1242J0;

    /* renamed from: S */
    private byte[] f1243S;
    private byte[] S_at;
    private byte[] S_atPre;
    private byte[] atBlock;
    private int atBlockPos;
    private long atLength;
    private long atLengthPre;
    private byte[] bufBlock;
    private int bufOff;
    private BlockCipher cipher;
    private byte[] counter;
    private GCMExponentiator exp;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private byte[] macBlock;
    private int macSize;
    private GCMMultiplier multiplier;
    private byte[] nonce;
    private long totalLength;

    public GCMBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, null);
    }

    public GCMBlockCipher(BlockCipher blockCipher, GCMMultiplier gCMMultiplier) {
        if (blockCipher.getBlockSize() == 16) {
            if (gCMMultiplier == null) {
                gCMMultiplier = new Tables8kGCMMultiplier();
            }
            this.cipher = blockCipher;
            this.multiplier = gCMMultiplier;
            return;
        }
        throw new IllegalArgumentException("cipher required with a block size of 16.");
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public String getAlgorithmName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.cipher.getAlgorithmName());
        sb.append("/GCM");
        return sb.toString();
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        KeyParameter keyParameter;
        int i;
        this.forEncryption = z;
        this.macBlock = null;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            this.nonce = aEADParameters.getNonce();
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            int macSize2 = aEADParameters.getMacSize();
            if (macSize2 < 32 || macSize2 > 128 || macSize2 % 8 != 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid value for MAC size: ");
                sb.append(macSize2);
                throw new IllegalArgumentException(sb.toString());
            }
            this.macSize = macSize2 / 8;
            keyParameter = aEADParameters.getKey();
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.nonce = parametersWithIV.getIV();
            this.initialAssociatedText = null;
            this.macSize = 16;
            keyParameter = (KeyParameter) parametersWithIV.getParameters();
        } else {
            throw new IllegalArgumentException("invalid parameters passed to GCM");
        }
        if (z) {
            i = 16;
        } else {
            i = this.macSize + 16;
        }
        this.bufBlock = new byte[i];
        byte[] bArr = this.nonce;
        if (bArr == null || bArr.length < 1) {
            throw new IllegalArgumentException("IV must be at least 1 byte");
        }
        if (keyParameter != null) {
            this.cipher.init(true, keyParameter);
            this.f1241H = new byte[16];
            BlockCipher blockCipher = this.cipher;
            byte[] bArr2 = this.f1241H;
            blockCipher.processBlock(bArr2, 0, bArr2, 0);
            this.multiplier.init(this.f1241H);
            this.exp = null;
        } else if (this.f1241H == null) {
            throw new IllegalArgumentException("Key must be specified in initial init");
        }
        this.f1242J0 = new byte[16];
        byte[] bArr3 = this.nonce;
        if (bArr3.length == 12) {
            System.arraycopy(bArr3, 0, this.f1242J0, 0, bArr3.length);
            this.f1242J0[15] = 1;
        } else {
            gHASH(this.f1242J0, bArr3, bArr3.length);
            byte[] bArr4 = new byte[16];
            Pack.longToBigEndian(((long) this.nonce.length) * 8, bArr4, 8);
            gHASHBlock(this.f1242J0, bArr4);
        }
        this.f1243S = new byte[16];
        this.S_at = new byte[16];
        this.S_atPre = new byte[16];
        this.atBlock = new byte[16];
        this.atBlockPos = 0;
        this.atLength = 0;
        this.atLengthPre = 0;
        this.counter = Arrays.clone(this.f1242J0);
        this.bufOff = 0;
        this.totalLength = 0;
        byte[] bArr5 = this.initialAssociatedText;
        if (bArr5 != null) {
            processAADBytes(bArr5, 0, bArr5.length);
        }
    }

    public byte[] getMac() {
        return Arrays.clone(this.macBlock);
    }

    public int getOutputSize(int i) {
        int i2 = i + this.bufOff;
        if (this.forEncryption) {
            return i2 + this.macSize;
        }
        int i3 = this.macSize;
        return i2 < i3 ? 0 : i2 - i3;
    }

    public int getUpdateOutputSize(int i) {
        int i2 = i + this.bufOff;
        if (!this.forEncryption) {
            int i3 = this.macSize;
            if (i2 < i3) {
                return 0;
            }
            i2 -= i3;
        }
        return i2 - (i2 % 16);
    }

    public void processAADByte(byte b) {
        byte[] bArr = this.atBlock;
        int i = this.atBlockPos;
        bArr[i] = b;
        int i2 = i + 1;
        this.atBlockPos = i2;
        if (i2 == 16) {
            gHASHBlock(this.S_at, bArr);
            this.atBlockPos = 0;
            this.atLength += 16;
        }
    }

    public void processAADBytes(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            byte[] bArr2 = this.atBlock;
            int i4 = this.atBlockPos;
            bArr2[i4] = bArr[i + i3];
            int i5 = i4 + 1;
            this.atBlockPos = i5;
            if (i5 == 16) {
                gHASHBlock(this.S_at, bArr2);
                this.atBlockPos = 0;
                this.atLength += 16;
            }
        }
    }

    private void initCipher() {
        if (this.atLength > 0) {
            System.arraycopy(this.S_at, 0, this.S_atPre, 0, 16);
            this.atLengthPre = this.atLength;
        }
        int i = this.atBlockPos;
        if (i > 0) {
            gHASHPartial(this.S_atPre, this.atBlock, 0, i);
            this.atLengthPre += (long) this.atBlockPos;
        }
        if (this.atLengthPre > 0) {
            System.arraycopy(this.S_atPre, 0, this.f1243S, 0, 16);
        }
    }

    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        byte[] bArr2 = this.bufBlock;
        int i2 = this.bufOff;
        bArr2[i2] = b;
        int i3 = i2 + 1;
        this.bufOff = i3;
        if (i3 != bArr2.length) {
            return 0;
        }
        outputBlock(bArr, i);
        return 16;
    }

    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (bArr.length >= i + i2) {
            int i4 = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                byte[] bArr3 = this.bufBlock;
                int i6 = this.bufOff;
                bArr3[i6] = bArr[i + i5];
                int i7 = i6 + 1;
                this.bufOff = i7;
                if (i7 == bArr3.length) {
                    outputBlock(bArr2, i3 + i4);
                    i4 += 16;
                }
            }
            return i4;
        }
        throw new DataLengthException("Input buffer too short");
    }

    private void outputBlock(byte[] bArr, int i) {
        if (bArr.length >= i + 16) {
            if (this.totalLength == 0) {
                initCipher();
            }
            gCTRBlock(this.bufBlock, bArr, i);
            if (this.forEncryption) {
                this.bufOff = 0;
                return;
            }
            byte[] bArr2 = this.bufBlock;
            System.arraycopy(bArr2, 16, bArr2, 0, this.macSize);
            this.bufOff = this.macSize;
            return;
        }
        throw new OutputLengthException("Output buffer too short");
    }

    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        byte[] bArr2 = bArr;
        if (this.totalLength == 0) {
            initCipher();
        }
        int i2 = this.bufOff;
        if (!this.forEncryption) {
            int i3 = this.macSize;
            if (i2 >= i3) {
                i2 -= i3;
            } else {
                throw new InvalidCipherTextException("data too short");
            }
        }
        int i4 = i2;
        String str = "Output buffer too short";
        if (i4 > 0) {
            if (bArr2.length >= i + i4) {
                gCTRPartial(this.bufBlock, 0, i4, bArr, i);
            } else {
                throw new OutputLengthException(str);
            }
        }
        long j = this.atLength;
        int i5 = this.atBlockPos;
        this.atLength = j + ((long) i5);
        if (this.atLength > this.atLengthPre) {
            if (i5 > 0) {
                gHASHPartial(this.S_at, this.atBlock, 0, i5);
            }
            if (this.atLengthPre > 0) {
                xor(this.S_at, this.S_atPre);
            }
            long j2 = ((this.totalLength * 8) + 127) >>> 7;
            byte[] bArr3 = new byte[16];
            if (this.exp == null) {
                this.exp = new Tables1kGCMExponentiator();
                this.exp.init(this.f1241H);
            }
            this.exp.exponentiateX(j2, bArr3);
            multiply(this.S_at, bArr3);
            xor(this.f1243S, this.S_at);
        }
        byte[] bArr4 = new byte[16];
        Pack.longToBigEndian(this.atLength * 8, bArr4, 0);
        Pack.longToBigEndian(this.totalLength * 8, bArr4, 8);
        gHASHBlock(this.f1243S, bArr4);
        byte[] bArr5 = new byte[16];
        this.cipher.processBlock(this.f1242J0, 0, bArr5, 0);
        xor(bArr5, this.f1243S);
        int i6 = this.macSize;
        this.macBlock = new byte[i6];
        System.arraycopy(bArr5, 0, this.macBlock, 0, i6);
        if (this.forEncryption) {
            int length = bArr2.length;
            int i7 = i + i4;
            int i8 = this.macSize;
            if (length >= i7 + i8) {
                System.arraycopy(this.macBlock, 0, bArr2, i + this.bufOff, i8);
                i4 += this.macSize;
            } else {
                throw new OutputLengthException(str);
            }
        } else {
            int i9 = this.macSize;
            byte[] bArr6 = new byte[i9];
            System.arraycopy(this.bufBlock, i4, bArr6, 0, i9);
            if (!Arrays.constantTimeAreEqual(this.macBlock, bArr6)) {
                throw new InvalidCipherTextException("mac check in GCM failed");
            }
        }
        reset(false);
        return i4;
    }

    public void reset() {
        reset(true);
    }

    private void reset(boolean z) {
        this.cipher.reset();
        this.f1243S = new byte[16];
        this.S_at = new byte[16];
        this.S_atPre = new byte[16];
        this.atBlock = new byte[16];
        this.atBlockPos = 0;
        this.atLength = 0;
        this.atLengthPre = 0;
        this.counter = Arrays.clone(this.f1242J0);
        this.bufOff = 0;
        this.totalLength = 0;
        byte[] bArr = this.bufBlock;
        if (bArr != null) {
            Arrays.fill(bArr, 0);
        }
        if (z) {
            this.macBlock = null;
        }
        byte[] bArr2 = this.initialAssociatedText;
        if (bArr2 != null) {
            processAADBytes(bArr2, 0, bArr2.length);
        }
    }

    private void gCTRBlock(byte[] bArr, byte[] bArr2, int i) {
        byte[] nextCounterBlock = getNextCounterBlock();
        xor(nextCounterBlock, bArr);
        System.arraycopy(nextCounterBlock, 0, bArr2, i, 16);
        byte[] bArr3 = this.f1243S;
        if (this.forEncryption) {
            bArr = nextCounterBlock;
        }
        gHASHBlock(bArr3, bArr);
        this.totalLength += 16;
    }

    private void gCTRPartial(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        byte[] nextCounterBlock = getNextCounterBlock();
        xor(nextCounterBlock, bArr, i, i2);
        System.arraycopy(nextCounterBlock, 0, bArr2, i3, i2);
        byte[] bArr3 = this.f1243S;
        if (this.forEncryption) {
            bArr = nextCounterBlock;
        }
        gHASHPartial(bArr3, bArr, 0, i2);
        this.totalLength += (long) i2;
    }

    private void gHASH(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2 += 16) {
            gHASHPartial(bArr, bArr2, i2, Math.min(i - i2, 16));
        }
    }

    private void gHASHBlock(byte[] bArr, byte[] bArr2) {
        xor(bArr, bArr2);
        this.multiplier.multiplyH(bArr);
    }

    private void gHASHPartial(byte[] bArr, byte[] bArr2, int i, int i2) {
        xor(bArr, bArr2, i, i2);
        this.multiplier.multiplyH(bArr);
    }

    private byte[] getNextCounterBlock() {
        for (int i = 15; i >= 12; i--) {
            byte[] bArr = this.counter;
            byte b = (byte) ((bArr[i] + 1) & 255);
            bArr[i] = b;
            if (b != 0) {
                break;
            }
        }
        byte[] bArr2 = new byte[16];
        this.cipher.processBlock(this.counter, 0, bArr2, 0);
        return bArr2;
    }

    private static void multiply(byte[] bArr, byte[] bArr2) {
        byte[] clone = Arrays.clone(bArr);
        byte[] bArr3 = new byte[16];
        for (int i = 0; i < 16; i++) {
            byte b = bArr2[i];
            for (int i2 = 7; i2 >= 0; i2--) {
                boolean z = true;
                if (((1 << i2) & b) != 0) {
                    xor(bArr3, clone);
                }
                if ((clone[15] & 1) == 0) {
                    z = false;
                }
                shiftRight(clone);
                if (z) {
                    clone[0] = (byte) (clone[0] ^ -31);
                }
            }
        }
        System.arraycopy(bArr3, 0, bArr, 0, 16);
    }

    private static void shiftRight(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte b = bArr[i] & 255;
            bArr[i] = (byte) (i2 | (b >>> 1));
            i++;
            if (i != 16) {
                i2 = (b & 1) << 7;
            } else {
                return;
            }
        }
    }

    private static void xor(byte[] bArr, byte[] bArr2) {
        for (int i = 15; i >= 0; i--) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    private static void xor(byte[] bArr, byte[] bArr2, int i, int i2) {
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                bArr[i3] = (byte) (bArr[i3] ^ bArr2[i + i3]);
                i2 = i3;
            } else {
                return;
            }
        }
    }
}
