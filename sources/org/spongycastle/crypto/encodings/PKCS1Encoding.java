package org.spongycastle.crypto.encodings;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class PKCS1Encoding implements AsymmetricBlockCipher {
    private static final int HEADER_LENGTH = 10;
    public static final String STRICT_LENGTH_ENABLED_PROPERTY = "org.spongycastle.pkcs1.strict";
    private AsymmetricBlockCipher engine;
    private byte[] fallback = null;
    private boolean forEncryption;
    private boolean forPrivateKey;
    private int pLen = -1;
    private SecureRandom random;
    private boolean useStrictLength;

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = useStrict();
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher, int i) {
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = useStrict();
        this.pLen = i;
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher, byte[] bArr) {
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = useStrict();
        this.fallback = bArr;
        this.pLen = bArr.length;
    }

    private boolean useStrict() {
        String str = (String) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                return System.getProperty(PKCS1Encoding.STRICT_LENGTH_ENABLED_PROPERTY);
            }
        });
        return str == null || str.equals("true");
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.random = parametersWithRandom.getRandom();
            asymmetricKeyParameter = (AsymmetricKeyParameter) parametersWithRandom.getParameters();
        } else {
            this.random = new SecureRandom();
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
        }
        this.engine.init(z, cipherParameters);
        this.forPrivateKey = asymmetricKeyParameter.isPrivate();
        this.forEncryption = z;
    }

    public int getInputBlockSize() {
        int inputBlockSize = this.engine.getInputBlockSize();
        return this.forEncryption ? inputBlockSize - 10 : inputBlockSize;
    }

    public int getOutputBlockSize() {
        int outputBlockSize = this.engine.getOutputBlockSize();
        return this.forEncryption ? outputBlockSize : outputBlockSize - 10;
    }

    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.forEncryption) {
            return encodeBlock(bArr, i, i2);
        }
        return decodeBlock(bArr, i, i2);
    }

    private byte[] encodeBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (i2 <= getInputBlockSize()) {
            byte[] bArr2 = new byte[this.engine.getInputBlockSize()];
            if (this.forPrivateKey) {
                bArr2[0] = 1;
                for (int i3 = 1; i3 != (bArr2.length - i2) - 1; i3++) {
                    bArr2[i3] = -1;
                }
            } else {
                this.random.nextBytes(bArr2);
                bArr2[0] = 2;
                for (int i4 = 1; i4 != (bArr2.length - i2) - 1; i4++) {
                    while (bArr2[i4] == 0) {
                        bArr2[i4] = (byte) this.random.nextInt();
                    }
                }
            }
            bArr2[(bArr2.length - i2) - 1] = 0;
            System.arraycopy(bArr, i, bArr2, bArr2.length - i2, i2);
            return this.engine.processBlock(bArr2, 0, bArr2.length);
        }
        throw new IllegalArgumentException("input data too large");
    }

    private static int checkPkcs1Encoding(byte[] bArr, int i) {
        int i2 = i + 1;
        int length = bArr.length - i2;
        byte b = 0 | (bArr[0] ^ 2);
        for (int i3 = 1; i3 < length; i3++) {
            byte b2 = bArr[i3];
            byte b3 = b2 | (b2 >> 1);
            byte b4 = b3 | (b3 >> 2);
            b |= ((b4 | (b4 >> 4)) & 1) - 1;
        }
        byte b5 = bArr[bArr.length - i2] | b;
        byte b6 = b5 | (b5 >> 1);
        byte b7 = b6 | (b6 >> 2);
        return ~(((b7 | (b7 >> 4)) & 1) - 1);
    }

    private byte[] decodeBlockOrRandom(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.forPrivateKey) {
            byte[] processBlock = this.engine.processBlock(bArr, i, i2);
            byte[] bArr2 = this.fallback;
            if (bArr2 == null) {
                bArr2 = new byte[this.pLen];
                this.random.nextBytes(bArr2);
            }
            if (processBlock.length < getOutputBlockSize()) {
                throw new InvalidCipherTextException("block truncated");
            } else if (!this.useStrictLength || processBlock.length == this.engine.getOutputBlockSize()) {
                int checkPkcs1Encoding = checkPkcs1Encoding(processBlock, this.pLen);
                byte[] bArr3 = new byte[this.pLen];
                int i3 = 0;
                while (true) {
                    int i4 = this.pLen;
                    if (i3 >= i4) {
                        return bArr3;
                    }
                    bArr3[i3] = (byte) ((processBlock[(processBlock.length - i4) + i3] & (~checkPkcs1Encoding)) | (bArr2[i3] & checkPkcs1Encoding));
                    i3++;
                }
            } else {
                throw new InvalidCipherTextException("block incorrect size");
            }
        } else {
            throw new InvalidCipherTextException("sorry, this method is only for decryption, not for signing");
        }
    }

    private byte[] decodeBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.pLen != -1) {
            return decodeBlockOrRandom(bArr, i, i2);
        }
        byte[] processBlock = this.engine.processBlock(bArr, i, i2);
        if (processBlock.length >= getOutputBlockSize()) {
            byte b = processBlock[0];
            String str = "unknown block type";
            if (this.forPrivateKey) {
                if (b != 2) {
                    throw new InvalidCipherTextException(str);
                }
            } else if (b != 1) {
                throw new InvalidCipherTextException(str);
            }
            if (!this.useStrictLength || processBlock.length == this.engine.getOutputBlockSize()) {
                int i3 = 1;
                while (i3 != processBlock.length) {
                    byte b2 = processBlock[i3];
                    if (b2 == 0) {
                        break;
                    } else if (b != 1 || b2 == -1) {
                        i3++;
                    } else {
                        throw new InvalidCipherTextException("block padding incorrect");
                    }
                }
                int i4 = i3 + 1;
                if (i4 > processBlock.length || i4 < 10) {
                    throw new InvalidCipherTextException("no data in block");
                }
                byte[] bArr2 = new byte[(processBlock.length - i4)];
                System.arraycopy(processBlock, i4, bArr2, 0, bArr2.length);
                return bArr2;
            }
            throw new InvalidCipherTextException("block incorrect size");
        }
        throw new InvalidCipherTextException("block truncated");
    }
}
