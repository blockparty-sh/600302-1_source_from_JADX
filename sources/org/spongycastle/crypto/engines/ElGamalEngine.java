package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.params.ElGamalKeyParameters;
import org.spongycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.spongycastle.crypto.params.ElGamalPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.BigIntegers;

public class ElGamalEngine implements AsymmetricBlockCipher {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private int bitSize;
    private boolean forEncryption;
    private ElGamalKeyParameters key;
    private SecureRandom random;

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.key = (ElGamalKeyParameters) parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
        } else {
            this.key = (ElGamalKeyParameters) cipherParameters;
            this.random = new SecureRandom();
        }
        this.forEncryption = z;
        this.bitSize = this.key.getParameters().getP().bitLength();
        if (z) {
            if (!(this.key instanceof ElGamalPublicKeyParameters)) {
                throw new IllegalArgumentException("ElGamalPublicKeyParameters are required for encryption.");
            }
        } else if (!(this.key instanceof ElGamalPrivateKeyParameters)) {
            throw new IllegalArgumentException("ElGamalPrivateKeyParameters are required for decryption.");
        }
    }

    public int getInputBlockSize() {
        if (this.forEncryption) {
            return (this.bitSize - 1) / 8;
        }
        return ((this.bitSize + 7) / 8) * 2;
    }

    public int getOutputBlockSize() {
        if (this.forEncryption) {
            return ((this.bitSize + 7) / 8) * 2;
        }
        return (this.bitSize - 1) / 8;
    }

    public byte[] processBlock(byte[] bArr, int i, int i2) {
        if (this.key != null) {
            String str = "input too large for ElGamal cipher.\n";
            if (i2 <= (this.forEncryption ? ((this.bitSize - 1) + 7) / 8 : getInputBlockSize())) {
                BigInteger p = this.key.getParameters().getP();
                if (this.key instanceof ElGamalPrivateKeyParameters) {
                    int i3 = i2 / 2;
                    byte[] bArr2 = new byte[i3];
                    byte[] bArr3 = new byte[i3];
                    System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
                    System.arraycopy(bArr, i + bArr2.length, bArr3, 0, bArr3.length);
                    return BigIntegers.asUnsignedByteArray(new BigInteger(1, bArr2).modPow(p.subtract(ONE).subtract(((ElGamalPrivateKeyParameters) this.key).getX()), p).multiply(new BigInteger(1, bArr3)).mod(p));
                }
                if (!(i == 0 && i2 == bArr.length)) {
                    byte[] bArr4 = new byte[i2];
                    System.arraycopy(bArr, i, bArr4, 0, i2);
                    bArr = bArr4;
                }
                BigInteger bigInteger = new BigInteger(1, bArr);
                if (bigInteger.compareTo(p) < 0) {
                    ElGamalPublicKeyParameters elGamalPublicKeyParameters = (ElGamalPublicKeyParameters) this.key;
                    int bitLength = p.bitLength();
                    BigInteger bigInteger2 = new BigInteger(bitLength, this.random);
                    while (true) {
                        if (!bigInteger2.equals(ZERO) && bigInteger2.compareTo(p.subtract(TWO)) <= 0) {
                            break;
                        }
                        bigInteger2 = new BigInteger(bitLength, this.random);
                    }
                    BigInteger modPow = this.key.getParameters().getG().modPow(bigInteger2, p);
                    BigInteger mod = bigInteger.multiply(elGamalPublicKeyParameters.getY().modPow(bigInteger2, p)).mod(p);
                    byte[] byteArray = modPow.toByteArray();
                    byte[] byteArray2 = mod.toByteArray();
                    byte[] bArr5 = new byte[getOutputBlockSize()];
                    if (byteArray.length > bArr5.length / 2) {
                        System.arraycopy(byteArray, 1, bArr5, (bArr5.length / 2) - (byteArray.length - 1), byteArray.length - 1);
                    } else {
                        System.arraycopy(byteArray, 0, bArr5, (bArr5.length / 2) - byteArray.length, byteArray.length);
                    }
                    if (byteArray2.length > bArr5.length / 2) {
                        System.arraycopy(byteArray2, 1, bArr5, bArr5.length - (byteArray2.length - 1), byteArray2.length - 1);
                    } else {
                        System.arraycopy(byteArray2, 0, bArr5, bArr5.length - byteArray2.length, byteArray2.length);
                    }
                    return bArr5;
                }
                throw new DataLengthException(str);
            }
            throw new DataLengthException(str);
        }
        throw new IllegalStateException("ElGamal engine not initialised");
    }
}
