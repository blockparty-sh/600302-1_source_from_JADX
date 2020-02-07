package org.spongycastle.pqc.crypto.ntru;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.TernaryPolynomial;
import org.spongycastle.util.Arrays;

public class NTRUEngine implements AsymmetricBlockCipher {
    private boolean forEncryption;
    private NTRUEncryptionParameters params;
    private NTRUEncryptionPrivateKeyParameters privKey;
    private NTRUEncryptionPublicKeyParameters pubKey;
    private SecureRandom random;

    public void init(boolean z, CipherParameters cipherParameters) {
        this.forEncryption = z;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.random = parametersWithRandom.getRandom();
                this.pubKey = (NTRUEncryptionPublicKeyParameters) parametersWithRandom.getParameters();
            } else {
                this.random = new SecureRandom();
                this.pubKey = (NTRUEncryptionPublicKeyParameters) cipherParameters;
            }
            this.params = this.pubKey.getParameters();
            return;
        }
        this.privKey = (NTRUEncryptionPrivateKeyParameters) cipherParameters;
        this.params = this.privKey.getParameters();
    }

    public int getInputBlockSize() {
        return this.params.maxMsgLenBytes;
    }

    public int getOutputBlockSize() {
        return ((this.params.f1499N * log2(this.params.f1505q)) + 7) / 8;
    }

    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        if (this.forEncryption) {
            return encrypt(bArr2, this.pubKey);
        }
        return decrypt(bArr2, this.privKey);
    }

    private byte[] encrypt(byte[] bArr, NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters) {
        byte[] bArr2 = bArr;
        IntegerPolynomial integerPolynomial = nTRUEncryptionPublicKeyParameters.f1509h;
        int i = this.params.f1499N;
        int i2 = this.params.f1505q;
        int i3 = this.params.maxMsgLenBytes;
        int i4 = this.params.f1501db;
        int i5 = this.params.bufferLenBits;
        int i6 = this.params.dm0;
        int i7 = this.params.pkLen;
        int i8 = this.params.minCallsMask;
        boolean z = this.params.hashSeed;
        byte[] bArr3 = this.params.oid;
        int length = bArr2.length;
        if (i3 > 255) {
            throw new IllegalArgumentException("llen values bigger than 1 are not supported");
        } else if (length <= i3) {
            while (true) {
                byte[] bArr4 = new byte[(i4 / 8)];
                this.random.nextBytes(bArr4);
                byte[] bArr5 = new byte[((i3 + 1) - length)];
                boolean z2 = z;
                byte[] bArr6 = new byte[(i5 / 8)];
                int i9 = i8;
                int i10 = i4;
                System.arraycopy(bArr4, 0, bArr6, 0, bArr4.length);
                bArr6[bArr4.length] = (byte) length;
                int i11 = length;
                System.arraycopy(bArr2, 0, bArr6, bArr4.length + 1, bArr2.length);
                System.arraycopy(bArr5, 0, bArr6, bArr4.length + 1 + bArr2.length, bArr5.length);
                IntegerPolynomial fromBinary3Sves = IntegerPolynomial.fromBinary3Sves(bArr6, i);
                byte[] copyOf = copyOf(integerPolynomial.toBinary(i2), i7 / 8);
                int i12 = i11;
                byte[] bArr7 = bArr3;
                boolean z3 = z2;
                int i13 = i5;
                byte[] bArr8 = bArr6;
                byte[] bArr9 = bArr4;
                int i14 = i7;
                int i15 = i9;
                IntegerPolynomial mult = generateBlindingPoly(buildSData(bArr3, bArr, i12, bArr9, copyOf), bArr8).mult(integerPolynomial, i2);
                IntegerPolynomial integerPolynomial2 = (IntegerPolynomial) mult.clone();
                integerPolynomial2.modPositive(4);
                fromBinary3Sves.add(MGF(integerPolynomial2.toBinary(4), i, i15, z3));
                fromBinary3Sves.mod3();
                if (fromBinary3Sves.count(-1) >= i6 && fromBinary3Sves.count(0) >= i6 && fromBinary3Sves.count(1) >= i6) {
                    mult.add(fromBinary3Sves, i2);
                    mult.ensurePositive(i2);
                    return mult.toBinary(i2);
                }
                length = i12;
                z = z3;
                i8 = i15;
                i5 = i13;
                i4 = i10;
                bArr3 = bArr7;
                i7 = i14;
                bArr2 = bArr;
            }
        } else {
            int i16 = length;
            StringBuilder sb = new StringBuilder();
            sb.append("Message too long: ");
            sb.append(i16);
            sb.append(">");
            sb.append(i3);
            throw new DataLengthException(sb.toString());
        }
    }

    private byte[] buildSData(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = new byte[(bArr.length + i + bArr3.length + bArr4.length)];
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr5, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr5, bArr.length + bArr2.length, bArr3.length);
        System.arraycopy(bArr4, 0, bArr5, bArr.length + bArr2.length + bArr3.length, bArr4.length);
        return bArr5;
    }

    /* access modifiers changed from: protected */
    public IntegerPolynomial encrypt(IntegerPolynomial integerPolynomial, TernaryPolynomial ternaryPolynomial, IntegerPolynomial integerPolynomial2) {
        IntegerPolynomial mult = ternaryPolynomial.mult(integerPolynomial2, this.params.f1505q);
        mult.add(integerPolynomial, this.params.f1505q);
        mult.ensurePositive(this.params.f1505q);
        return mult;
    }

    private Polynomial generateBlindingPoly(byte[] bArr, byte[] bArr2) {
        IndexGenerator indexGenerator = new IndexGenerator(bArr, this.params);
        if (this.params.polyType == 1) {
            return new ProductFormPolynomial(new SparseTernaryPolynomial(generateBlindingCoeffs(indexGenerator, this.params.dr1)), new SparseTernaryPolynomial(generateBlindingCoeffs(indexGenerator, this.params.dr2)), new SparseTernaryPolynomial(generateBlindingCoeffs(indexGenerator, this.params.dr3)));
        }
        int i = this.params.f1504dr;
        boolean z = this.params.sparse;
        int[] generateBlindingCoeffs = generateBlindingCoeffs(indexGenerator, i);
        if (z) {
            return new SparseTernaryPolynomial(generateBlindingCoeffs);
        }
        return new DenseTernaryPolynomial(generateBlindingCoeffs);
    }

    private int[] generateBlindingCoeffs(IndexGenerator indexGenerator, int i) {
        int[] iArr = new int[this.params.f1499N];
        for (int i2 = -1; i2 <= 1; i2 += 2) {
            int i3 = 0;
            while (i3 < i) {
                int nextIndex = indexGenerator.nextIndex();
                if (iArr[nextIndex] == 0) {
                    iArr[nextIndex] = i2;
                    i3++;
                }
            }
        }
        return iArr;
    }

    private IntegerPolynomial MGF(byte[] bArr, int i, int i2, boolean z) {
        Digest digest = this.params.hashAlg;
        int digestSize = digest.getDigestSize();
        byte[] bArr2 = new byte[(i2 * digestSize)];
        if (z) {
            bArr = calcHash(digest, bArr);
        }
        int i3 = 0;
        while (i3 < i2) {
            digest.update(bArr, 0, bArr.length);
            putInt(digest, i3);
            System.arraycopy(calcHash(digest), 0, bArr2, i3 * digestSize, digestSize);
            i3++;
        }
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(i);
        while (true) {
            int i4 = 0;
            for (int i5 = 0; i5 != bArr2.length; i5++) {
                byte b = bArr2[i5] & 255;
                if (b < 243) {
                    int i6 = b;
                    int i7 = i4;
                    for (int i8 = 0; i8 < 4; i8++) {
                        int i9 = i6 % 3;
                        integerPolynomial.coeffs[i7] = i9 - 1;
                        i7++;
                        if (i7 == i) {
                            return integerPolynomial;
                        }
                        i6 = (i6 - i9) / 3;
                    }
                    integerPolynomial.coeffs[i7] = i6 - 1;
                    int i10 = i7 + 1;
                    if (i10 == i) {
                        return integerPolynomial;
                    }
                    i4 = i10;
                }
            }
            if (i4 >= i) {
                return integerPolynomial;
            }
            digest.update(bArr, 0, bArr.length);
            putInt(digest, i3);
            bArr2 = calcHash(digest);
            i3++;
        }
    }

    private void putInt(Digest digest, int i) {
        digest.update((byte) (i >> 24));
        digest.update((byte) (i >> 16));
        digest.update((byte) (i >> 8));
        digest.update((byte) i);
    }

    private byte[] calcHash(Digest digest) {
        byte[] bArr = new byte[digest.getDigestSize()];
        digest.doFinal(bArr, 0);
        return bArr;
    }

    private byte[] calcHash(Digest digest, byte[] bArr) {
        byte[] bArr2 = new byte[digest.getDigestSize()];
        digest.update(bArr, 0, bArr.length);
        digest.doFinal(bArr2, 0);
        return bArr2;
    }

    private byte[] decrypt(byte[] bArr, NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters) throws InvalidCipherTextException {
        NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters2 = nTRUEncryptionPrivateKeyParameters;
        Polynomial polynomial = nTRUEncryptionPrivateKeyParameters2.f1508t;
        IntegerPolynomial integerPolynomial = nTRUEncryptionPrivateKeyParameters2.f1506fp;
        IntegerPolynomial integerPolynomial2 = nTRUEncryptionPrivateKeyParameters2.f1507h;
        int i = this.params.f1499N;
        int i2 = this.params.f1505q;
        int i3 = this.params.f1501db;
        int i4 = this.params.maxMsgLenBytes;
        int i5 = this.params.dm0;
        int i6 = this.params.pkLen;
        int i7 = this.params.minCallsMask;
        boolean z = this.params.hashSeed;
        byte[] bArr2 = this.params.oid;
        if (i4 <= 255) {
            int i8 = i3 / 8;
            IntegerPolynomial fromBinary = IntegerPolynomial.fromBinary(bArr, i, i2);
            IntegerPolynomial decrypt = decrypt(fromBinary, polynomial, integerPolynomial);
            if (decrypt.count(-1) < i5) {
                throw new InvalidCipherTextException("Less than dm0 coefficients equal -1");
            } else if (decrypt.count(0) < i5) {
                throw new InvalidCipherTextException("Less than dm0 coefficients equal 0");
            } else if (decrypt.count(1) >= i5) {
                IntegerPolynomial integerPolynomial3 = (IntegerPolynomial) fromBinary.clone();
                integerPolynomial3.sub(decrypt);
                integerPolynomial3.modPositive(i2);
                IntegerPolynomial integerPolynomial4 = (IntegerPolynomial) integerPolynomial3.clone();
                integerPolynomial4.modPositive(4);
                decrypt.sub(MGF(integerPolynomial4.toBinary(4), i, i7, z));
                decrypt.mod3();
                byte[] binary3Sves = decrypt.toBinary3Sves();
                byte[] bArr3 = new byte[i8];
                System.arraycopy(binary3Sves, 0, bArr3, 0, i8);
                int i9 = 255 & binary3Sves[i8];
                if (i9 <= i4) {
                    byte[] bArr4 = new byte[i9];
                    int i10 = i8 + 1;
                    System.arraycopy(binary3Sves, i10, bArr4, 0, i9);
                    int i11 = i10 + i9;
                    byte[] bArr5 = new byte[(binary3Sves.length - i11)];
                    System.arraycopy(binary3Sves, i11, bArr5, 0, bArr5.length);
                    if (Arrays.areEqual(bArr5, new byte[bArr5.length])) {
                        IntegerPolynomial mult = generateBlindingPoly(buildSData(bArr2, bArr4, i9, bArr3, copyOf(integerPolynomial2.toBinary(i2), i6 / 8)), bArr4).mult(integerPolynomial2);
                        mult.modPositive(i2);
                        if (mult.equals(integerPolynomial3)) {
                            return bArr4;
                        }
                        throw new InvalidCipherTextException("Invalid message encoding");
                    }
                    throw new InvalidCipherTextException("The message is not followed by zeroes");
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Message too long: ");
                sb.append(i9);
                sb.append(">");
                sb.append(i4);
                throw new InvalidCipherTextException(sb.toString());
            } else {
                throw new InvalidCipherTextException("Less than dm0 coefficients equal 1");
            }
        } else {
            throw new DataLengthException("maxMsgLenBytes values bigger than 255 are not supported");
        }
    }

    /* access modifiers changed from: protected */
    public IntegerPolynomial decrypt(IntegerPolynomial integerPolynomial, Polynomial polynomial, IntegerPolynomial integerPolynomial2) {
        IntegerPolynomial integerPolynomial3;
        if (this.params.fastFp) {
            integerPolynomial3 = polynomial.mult(integerPolynomial, this.params.f1505q);
            integerPolynomial3.mult(3);
            integerPolynomial3.add(integerPolynomial);
        } else {
            integerPolynomial3 = polynomial.mult(integerPolynomial, this.params.f1505q);
        }
        integerPolynomial3.center0(this.params.f1505q);
        integerPolynomial3.mod3();
        if (!this.params.fastFp) {
            integerPolynomial3 = new DenseTernaryPolynomial(integerPolynomial3).mult(integerPolynomial2, 3);
        }
        integerPolynomial3.center0(3);
        return integerPolynomial3;
    }

    private byte[] copyOf(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        if (i >= bArr.length) {
            i = bArr.length;
        }
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    private int log2(int i) {
        if (i == 2048) {
            return 11;
        }
        throw new IllegalStateException("log2 not fully implemented");
    }
}
