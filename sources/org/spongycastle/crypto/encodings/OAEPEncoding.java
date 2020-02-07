package org.spongycastle.crypto.encodings;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class OAEPEncoding implements AsymmetricBlockCipher {
    private byte[] defHash;
    private AsymmetricBlockCipher engine;
    private boolean forEncryption;
    private Digest mgf1Hash;
    private SecureRandom random;

    public OAEPEncoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this(asymmetricBlockCipher, new SHA1Digest(), null);
    }

    public OAEPEncoding(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest) {
        this(asymmetricBlockCipher, digest, null);
    }

    public OAEPEncoding(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, byte[] bArr) {
        this(asymmetricBlockCipher, digest, digest, bArr);
    }

    public OAEPEncoding(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, byte[] bArr) {
        this.engine = asymmetricBlockCipher;
        this.mgf1Hash = digest2;
        this.defHash = new byte[digest.getDigestSize()];
        digest.reset();
        if (bArr != null) {
            digest.update(bArr, 0, bArr.length);
        }
        digest.doFinal(this.defHash, 0);
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            this.random = ((ParametersWithRandom) cipherParameters).getRandom();
        } else {
            this.random = new SecureRandom();
        }
        this.engine.init(z, cipherParameters);
        this.forEncryption = z;
    }

    public int getInputBlockSize() {
        int inputBlockSize = this.engine.getInputBlockSize();
        return this.forEncryption ? (inputBlockSize - 1) - (this.defHash.length * 2) : inputBlockSize;
    }

    public int getOutputBlockSize() {
        int outputBlockSize = this.engine.getOutputBlockSize();
        if (this.forEncryption) {
            return outputBlockSize;
        }
        return (outputBlockSize - 1) - (this.defHash.length * 2);
    }

    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.forEncryption) {
            return encodeBlock(bArr, i, i2);
        }
        return decodeBlock(bArr, i, i2);
    }

    public byte[] encodeBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2 = new byte[(getInputBlockSize() + 1 + (this.defHash.length * 2))];
        System.arraycopy(bArr, i, bArr2, bArr2.length - i2, i2);
        bArr2[(bArr2.length - i2) - 1] = 1;
        byte[] bArr3 = this.defHash;
        System.arraycopy(bArr3, 0, bArr2, bArr3.length, bArr3.length);
        byte[] bArr4 = new byte[this.defHash.length];
        this.random.nextBytes(bArr4);
        byte[] maskGeneratorFunction1 = maskGeneratorFunction1(bArr4, 0, bArr4.length, bArr2.length - this.defHash.length);
        for (int length = this.defHash.length; length != bArr2.length; length++) {
            bArr2[length] = (byte) (bArr2[length] ^ maskGeneratorFunction1[length - this.defHash.length]);
        }
        System.arraycopy(bArr4, 0, bArr2, 0, this.defHash.length);
        byte[] bArr5 = this.defHash;
        byte[] maskGeneratorFunction12 = maskGeneratorFunction1(bArr2, bArr5.length, bArr2.length - bArr5.length, bArr5.length);
        for (int i3 = 0; i3 != this.defHash.length; i3++) {
            bArr2[i3] = (byte) (bArr2[i3] ^ maskGeneratorFunction12[i3]);
        }
        return this.engine.processBlock(bArr2, 0, bArr2.length);
    }

    public byte[] decodeBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2;
        byte[] bArr3;
        byte[] processBlock = this.engine.processBlock(bArr, i, i2);
        if (processBlock.length < this.engine.getOutputBlockSize()) {
            byte[] bArr4 = new byte[this.engine.getOutputBlockSize()];
            System.arraycopy(processBlock, 0, bArr4, bArr4.length - processBlock.length, processBlock.length);
            processBlock = bArr4;
        }
        int length = processBlock.length;
        byte[] bArr5 = this.defHash;
        if (length >= (bArr5.length * 2) + 1) {
            byte[] maskGeneratorFunction1 = maskGeneratorFunction1(processBlock, bArr5.length, processBlock.length - bArr5.length, bArr5.length);
            int i3 = 0;
            while (true) {
                bArr2 = this.defHash;
                if (i3 == bArr2.length) {
                    break;
                }
                processBlock[i3] = (byte) (processBlock[i3] ^ maskGeneratorFunction1[i3]);
                i3++;
            }
            byte[] maskGeneratorFunction12 = maskGeneratorFunction1(processBlock, 0, bArr2.length, processBlock.length - bArr2.length);
            for (int length2 = this.defHash.length; length2 != processBlock.length; length2++) {
                processBlock[length2] = (byte) (processBlock[length2] ^ maskGeneratorFunction12[length2 - this.defHash.length]);
            }
            int i4 = 0;
            boolean z = false;
            while (true) {
                bArr3 = this.defHash;
                if (i4 == bArr3.length) {
                    break;
                }
                if (bArr3[i4] != processBlock[bArr3.length + i4]) {
                    z = true;
                }
                i4++;
            }
            if (!z) {
                int length3 = bArr3.length * 2;
                while (length3 != processBlock.length && processBlock[length3] == 0) {
                    length3++;
                }
                if (length3 >= processBlock.length - 1 || processBlock[length3] != 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("data start wrong ");
                    sb.append(length3);
                    throw new InvalidCipherTextException(sb.toString());
                }
                int i5 = length3 + 1;
                byte[] bArr6 = new byte[(processBlock.length - i5)];
                System.arraycopy(processBlock, i5, bArr6, 0, bArr6.length);
                return bArr6;
            }
            throw new InvalidCipherTextException("data hash wrong");
        }
        throw new InvalidCipherTextException("data too short");
    }

    private void ItoOSP(int i, byte[] bArr) {
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 0);
    }

    private byte[] maskGeneratorFunction1(byte[] bArr, int i, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[this.mgf1Hash.getDigestSize()];
        byte[] bArr4 = new byte[4];
        this.mgf1Hash.reset();
        int i4 = 0;
        while (i4 < i3 / bArr3.length) {
            ItoOSP(i4, bArr4);
            this.mgf1Hash.update(bArr, i, i2);
            this.mgf1Hash.update(bArr4, 0, bArr4.length);
            this.mgf1Hash.doFinal(bArr3, 0);
            System.arraycopy(bArr3, 0, bArr2, bArr3.length * i4, bArr3.length);
            i4++;
        }
        if (bArr3.length * i4 < i3) {
            ItoOSP(i4, bArr4);
            this.mgf1Hash.update(bArr, i, i2);
            this.mgf1Hash.update(bArr4, 0, bArr4.length);
            this.mgf1Hash.doFinal(bArr3, 0);
            System.arraycopy(bArr3, 0, bArr2, bArr3.length * i4, bArr2.length - (i4 * bArr3.length));
        }
        return bArr2;
    }
}
