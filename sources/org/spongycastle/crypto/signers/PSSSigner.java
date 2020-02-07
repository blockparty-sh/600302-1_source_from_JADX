package org.spongycastle.crypto.signers;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSABlindingParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class PSSSigner implements Signer {
    public static final byte TRAILER_IMPLICIT = -68;
    private byte[] block;
    private AsymmetricBlockCipher cipher;
    private Digest contentDigest;
    private int emBits;
    private int hLen;
    private byte[] mDash;
    private Digest mgfDigest;
    private int mgfhLen;
    private SecureRandom random;
    private int sLen;
    private byte[] salt;
    private byte trailer;

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int i) {
        this(asymmetricBlockCipher, digest, i, (byte) TRAILER_IMPLICIT);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, int i) {
        this(asymmetricBlockCipher, digest, digest2, i, TRAILER_IMPLICIT);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int i, byte b) {
        this(asymmetricBlockCipher, digest, digest, i, b);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, int i, byte b) {
        this.cipher = asymmetricBlockCipher;
        this.contentDigest = digest;
        this.mgfDigest = digest2;
        this.hLen = digest.getDigestSize();
        this.mgfhLen = digest2.getDigestSize();
        this.sLen = i;
        this.salt = new byte[i];
        this.mDash = new byte[(i + 8 + this.hLen)];
        this.trailer = b;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            CipherParameters parameters = parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
            cipherParameters = parameters;
        } else if (z) {
            this.random = new SecureRandom();
        }
        this.cipher.init(z, cipherParameters);
        if (cipherParameters instanceof RSABlindingParameters) {
            rSAKeyParameters = ((RSABlindingParameters) cipherParameters).getPublicKey();
        } else {
            rSAKeyParameters = (RSAKeyParameters) cipherParameters;
        }
        this.emBits = rSAKeyParameters.getModulus().bitLength() - 1;
        int i = this.emBits;
        if (i >= (this.hLen * 8) + (this.sLen * 8) + 9) {
            this.block = new byte[((i + 7) / 8)];
            reset();
            return;
        }
        throw new IllegalArgumentException("key too small for specified hash and salt lengths");
    }

    private void clearBlock(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    public void update(byte b) {
        this.contentDigest.update(b);
    }

    public void update(byte[] bArr, int i, int i2) {
        this.contentDigest.update(bArr, i, i2);
    }

    public void reset() {
        this.contentDigest.reset();
    }

    public byte[] generateSignature() throws CryptoException, DataLengthException {
        Digest digest = this.contentDigest;
        byte[] bArr = this.mDash;
        digest.doFinal(bArr, (bArr.length - this.hLen) - this.sLen);
        if (this.sLen != 0) {
            this.random.nextBytes(this.salt);
            byte[] bArr2 = this.salt;
            byte[] bArr3 = this.mDash;
            int length = bArr3.length;
            int i = this.sLen;
            System.arraycopy(bArr2, 0, bArr3, length - i, i);
        }
        byte[] bArr4 = new byte[this.hLen];
        Digest digest2 = this.contentDigest;
        byte[] bArr5 = this.mDash;
        digest2.update(bArr5, 0, bArr5.length);
        this.contentDigest.doFinal(bArr4, 0);
        byte[] bArr6 = this.block;
        int length2 = bArr6.length;
        int i2 = this.sLen;
        int i3 = (length2 - i2) - 1;
        int i4 = this.hLen;
        bArr6[(i3 - i4) - 1] = 1;
        System.arraycopy(this.salt, 0, bArr6, ((bArr6.length - i2) - i4) - 1, i2);
        byte[] maskGeneratorFunction1 = maskGeneratorFunction1(bArr4, 0, bArr4.length, (this.block.length - this.hLen) - 1);
        for (int i5 = 0; i5 != maskGeneratorFunction1.length; i5++) {
            byte[] bArr7 = this.block;
            bArr7[i5] = (byte) (bArr7[i5] ^ maskGeneratorFunction1[i5]);
        }
        byte[] bArr8 = this.block;
        bArr8[0] = (byte) (bArr8[0] & (255 >> ((bArr8.length * 8) - this.emBits)));
        int length3 = bArr8.length;
        int i6 = this.hLen;
        System.arraycopy(bArr4, 0, bArr8, (length3 - i6) - 1, i6);
        byte[] bArr9 = this.block;
        bArr9[bArr9.length - 1] = this.trailer;
        byte[] processBlock = this.cipher.processBlock(bArr9, 0, bArr9.length);
        clearBlock(this.block);
        return processBlock;
    }

    public boolean verifySignature(byte[] bArr) {
        Digest digest = this.contentDigest;
        byte[] bArr2 = this.mDash;
        digest.doFinal(bArr2, (bArr2.length - this.hLen) - this.sLen);
        try {
            byte[] processBlock = this.cipher.processBlock(bArr, 0, bArr.length);
            System.arraycopy(processBlock, 0, this.block, this.block.length - processBlock.length, processBlock.length);
            byte[] bArr3 = this.block;
            if (bArr3[bArr3.length - 1] != this.trailer) {
                clearBlock(bArr3);
                return false;
            }
            int length = bArr3.length;
            int i = this.hLen;
            byte[] maskGeneratorFunction1 = maskGeneratorFunction1(bArr3, (length - i) - 1, i, (bArr3.length - i) - 1);
            for (int i2 = 0; i2 != maskGeneratorFunction1.length; i2++) {
                byte[] bArr4 = this.block;
                bArr4[i2] = (byte) (bArr4[i2] ^ maskGeneratorFunction1[i2]);
            }
            byte[] bArr5 = this.block;
            bArr5[0] = (byte) (bArr5[0] & (255 >> ((bArr5.length * 8) - this.emBits)));
            int i3 = 0;
            while (true) {
                byte[] bArr6 = this.block;
                int length2 = bArr6.length;
                int i4 = this.hLen;
                int i5 = length2 - i4;
                int i6 = this.sLen;
                if (i3 != (i5 - i6) - 2) {
                    if (bArr6[i3] != 0) {
                        clearBlock(bArr6);
                        return false;
                    }
                    i3++;
                } else if (bArr6[((bArr6.length - i4) - i6) - 2] != 1) {
                    clearBlock(bArr6);
                    return false;
                } else {
                    int length3 = ((bArr6.length - i6) - i4) - 1;
                    byte[] bArr7 = this.mDash;
                    System.arraycopy(bArr6, length3, bArr7, bArr7.length - i6, i6);
                    Digest digest2 = this.contentDigest;
                    byte[] bArr8 = this.mDash;
                    digest2.update(bArr8, 0, bArr8.length);
                    Digest digest3 = this.contentDigest;
                    byte[] bArr9 = this.mDash;
                    digest3.doFinal(bArr9, bArr9.length - this.hLen);
                    int length4 = this.block.length;
                    int i7 = this.hLen;
                    int i8 = (length4 - i7) - 1;
                    int length5 = this.mDash.length - i7;
                    while (true) {
                        byte[] bArr10 = this.mDash;
                        if (length5 == bArr10.length) {
                            clearBlock(bArr10);
                            clearBlock(this.block);
                            return true;
                        } else if ((this.block[i8] ^ bArr10[length5]) != 0) {
                            clearBlock(bArr10);
                            clearBlock(this.block);
                            return false;
                        } else {
                            i8++;
                            length5++;
                        }
                    }
                }
            }
        } catch (Exception unused) {
            return false;
        }
    }

    private void ItoOSP(int i, byte[] bArr) {
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 0);
    }

    private byte[] maskGeneratorFunction1(byte[] bArr, int i, int i2, int i3) {
        int i4;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[this.mgfhLen];
        byte[] bArr4 = new byte[4];
        this.mgfDigest.reset();
        int i5 = 0;
        while (true) {
            i4 = this.mgfhLen;
            if (i5 >= i3 / i4) {
                break;
            }
            ItoOSP(i5, bArr4);
            this.mgfDigest.update(bArr, i, i2);
            this.mgfDigest.update(bArr4, 0, bArr4.length);
            this.mgfDigest.doFinal(bArr3, 0);
            int i6 = this.mgfhLen;
            System.arraycopy(bArr3, 0, bArr2, i5 * i6, i6);
            i5++;
        }
        if (i4 * i5 < i3) {
            ItoOSP(i5, bArr4);
            this.mgfDigest.update(bArr, i, i2);
            this.mgfDigest.update(bArr4, 0, bArr4.length);
            this.mgfDigest.doFinal(bArr3, 0);
            int i7 = this.mgfhLen;
            System.arraycopy(bArr3, 0, bArr2, i5 * i7, bArr2.length - (i5 * i7));
        }
        return bArr2;
    }
}
