package org.spongycastle.crypto.engines;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.EphemeralKeyPair;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.KeyParser;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.IESParameters;
import org.spongycastle.crypto.params.IESWithCipherParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.Pack;

public class IESEngine {

    /* renamed from: IV */
    private byte[] f1144IV;

    /* renamed from: V */
    byte[] f1145V;
    BasicAgreement agree;
    BufferedBlockCipher cipher;
    boolean forEncryption;
    DerivationFunction kdf;
    private EphemeralKeyPairGenerator keyPairGenerator;
    private KeyParser keyParser;
    Mac mac;
    byte[] macBuf;
    IESParameters param;
    CipherParameters privParam;
    CipherParameters pubParam;

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac2) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac2;
        this.macBuf = new byte[mac2.getMacSize()];
        this.cipher = null;
    }

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac2, BufferedBlockCipher bufferedBlockCipher) {
        this.agree = basicAgreement;
        this.kdf = derivationFunction;
        this.mac = mac2;
        this.macBuf = new byte[mac2.getMacSize()];
        this.cipher = bufferedBlockCipher;
    }

    public void init(boolean z, CipherParameters cipherParameters, CipherParameters cipherParameters2, CipherParameters cipherParameters3) {
        this.forEncryption = z;
        this.privParam = cipherParameters;
        this.pubParam = cipherParameters2;
        this.f1145V = new byte[0];
        extractParams(cipherParameters3);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.forEncryption = true;
        this.pubParam = asymmetricKeyParameter;
        this.keyPairGenerator = ephemeralKeyPairGenerator;
        extractParams(cipherParameters);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, KeyParser keyParser2) {
        this.forEncryption = false;
        this.privParam = asymmetricKeyParameter;
        this.keyParser = keyParser2;
        extractParams(cipherParameters);
    }

    private void extractParams(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.f1144IV = parametersWithIV.getIV();
            this.param = (IESParameters) parametersWithIV.getParameters();
            return;
        }
        this.f1144IV = null;
        this.param = (IESParameters) cipherParameters;
    }

    public BufferedBlockCipher getCipher() {
        return this.cipher;
    }

    public Mac getMac() {
        return this.mac;
    }

    private byte[] encryptBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2;
        byte[] bArr3;
        if (this.cipher == null) {
            byte[] bArr4 = new byte[i2];
            bArr2 = new byte[(this.param.getMacKeySize() / 8)];
            byte[] bArr5 = new byte[(bArr4.length + bArr2.length)];
            this.kdf.generateBytes(bArr5, 0, bArr5.length);
            if (this.f1145V.length != 0) {
                System.arraycopy(bArr5, 0, bArr2, 0, bArr2.length);
                System.arraycopy(bArr5, bArr2.length, bArr4, 0, bArr4.length);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, bArr4.length);
                System.arraycopy(bArr5, i2, bArr2, 0, bArr2.length);
            }
            byte[] bArr6 = new byte[i2];
            for (int i3 = 0; i3 != i2; i3++) {
                bArr6[i3] = (byte) (bArr[i + i3] ^ bArr4[i3]);
            }
            bArr3 = bArr6;
        } else {
            byte[] bArr7 = new byte[(((IESWithCipherParameters) this.param).getCipherKeySize() / 8)];
            bArr2 = new byte[(this.param.getMacKeySize() / 8)];
            byte[] bArr8 = new byte[(bArr7.length + bArr2.length)];
            this.kdf.generateBytes(bArr8, 0, bArr8.length);
            System.arraycopy(bArr8, 0, bArr7, 0, bArr7.length);
            System.arraycopy(bArr8, bArr7.length, bArr2, 0, bArr2.length);
            if (this.f1144IV != null) {
                this.cipher.init(true, new ParametersWithIV(new KeyParameter(bArr7), this.f1144IV));
            } else {
                this.cipher.init(true, new KeyParameter(bArr7));
            }
            bArr3 = new byte[this.cipher.getOutputSize(i2)];
            int processBytes = this.cipher.processBytes(bArr, i, i2, bArr3, 0);
            i2 = processBytes + this.cipher.doFinal(bArr3, processBytes);
        }
        byte[] encodingV = this.param.getEncodingV();
        byte[] bArr9 = new byte[4];
        if (!(this.f1145V.length == 0 || encodingV == null)) {
            Pack.intToBigEndian(encodingV.length * 8, bArr9, 0);
        }
        byte[] bArr10 = new byte[this.mac.getMacSize()];
        this.mac.init(new KeyParameter(bArr2));
        this.mac.update(bArr3, 0, bArr3.length);
        if (encodingV != null) {
            this.mac.update(encodingV, 0, encodingV.length);
        }
        if (this.f1145V.length != 0) {
            this.mac.update(bArr9, 0, bArr9.length);
        }
        this.mac.doFinal(bArr10, 0);
        byte[] bArr11 = this.f1145V;
        byte[] bArr12 = new byte[(bArr11.length + i2 + bArr10.length)];
        System.arraycopy(bArr11, 0, bArr12, 0, bArr11.length);
        System.arraycopy(bArr3, 0, bArr12, this.f1145V.length, i2);
        System.arraycopy(bArr10, 0, bArr12, this.f1145V.length + i2, bArr10.length);
        return bArr12;
    }

    private byte[] decryptBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        int i3;
        byte[] bArr2;
        byte[] bArr3;
        if (i2 > this.param.getMacKeySize() / 8) {
            if (this.cipher == null) {
                byte[] bArr4 = new byte[((i2 - this.f1145V.length) - this.mac.getMacSize())];
                bArr2 = new byte[(this.param.getMacKeySize() / 8)];
                byte[] bArr5 = new byte[(bArr4.length + bArr2.length)];
                this.kdf.generateBytes(bArr5, 0, bArr5.length);
                if (this.f1145V.length != 0) {
                    System.arraycopy(bArr5, 0, bArr2, 0, bArr2.length);
                    System.arraycopy(bArr5, bArr2.length, bArr4, 0, bArr4.length);
                } else {
                    System.arraycopy(bArr5, 0, bArr4, 0, bArr4.length);
                    System.arraycopy(bArr5, bArr4.length, bArr2, 0, bArr2.length);
                }
                byte[] bArr6 = new byte[bArr4.length];
                for (int i4 = 0; i4 != bArr4.length; i4++) {
                    bArr6[i4] = (byte) (bArr[(this.f1145V.length + i) + i4] ^ bArr4[i4]);
                }
                byte[] bArr7 = bArr6;
                i3 = bArr4.length;
                bArr3 = bArr7;
            } else {
                byte[] bArr8 = new byte[(((IESWithCipherParameters) this.param).getCipherKeySize() / 8)];
                bArr2 = new byte[(this.param.getMacKeySize() / 8)];
                byte[] bArr9 = new byte[(bArr8.length + bArr2.length)];
                this.kdf.generateBytes(bArr9, 0, bArr9.length);
                System.arraycopy(bArr9, 0, bArr8, 0, bArr8.length);
                System.arraycopy(bArr9, bArr8.length, bArr2, 0, bArr2.length);
                if (this.f1144IV != null) {
                    this.cipher.init(false, new ParametersWithIV(new KeyParameter(bArr8), this.f1144IV));
                } else {
                    this.cipher.init(false, new KeyParameter(bArr8));
                }
                bArr3 = new byte[this.cipher.getOutputSize((i2 - this.f1145V.length) - this.mac.getMacSize())];
                BufferedBlockCipher bufferedBlockCipher = this.cipher;
                byte[] bArr10 = this.f1145V;
                int processBytes = bufferedBlockCipher.processBytes(bArr, bArr10.length + i, (i2 - bArr10.length) - this.mac.getMacSize(), bArr3, 0);
                i3 = processBytes + this.cipher.doFinal(bArr3, processBytes);
            }
            byte[] encodingV = this.param.getEncodingV();
            byte[] bArr11 = new byte[4];
            if (!(this.f1145V.length == 0 || encodingV == null)) {
                Pack.intToBigEndian(encodingV.length * 8, bArr11, 0);
            }
            int i5 = i + i2;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i5 - this.mac.getMacSize(), i5);
            byte[] bArr12 = new byte[copyOfRange.length];
            this.mac.init(new KeyParameter(bArr2));
            Mac mac2 = this.mac;
            byte[] bArr13 = this.f1145V;
            mac2.update(bArr, i + bArr13.length, (i2 - bArr13.length) - bArr12.length);
            if (encodingV != null) {
                this.mac.update(encodingV, 0, encodingV.length);
            }
            if (this.f1145V.length != 0) {
                this.mac.update(bArr11, 0, bArr11.length);
            }
            this.mac.doFinal(bArr12, 0);
            if (Arrays.constantTimeAreEqual(copyOfRange, bArr12)) {
                return Arrays.copyOfRange(bArr3, 0, i3);
            }
            throw new InvalidCipherTextException("Invalid MAC.");
        }
        throw new InvalidCipherTextException("Length of input must be greater than the MAC");
    }

    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.forEncryption) {
            EphemeralKeyPairGenerator ephemeralKeyPairGenerator = this.keyPairGenerator;
            if (ephemeralKeyPairGenerator != null) {
                EphemeralKeyPair generate = ephemeralKeyPairGenerator.generate();
                this.privParam = generate.getKeyPair().getPrivate();
                this.f1145V = generate.getEncodedPublicKey();
            }
        } else if (this.keyParser != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
            try {
                this.pubParam = this.keyParser.readKey(byteArrayInputStream);
                this.f1145V = Arrays.copyOfRange(bArr, i, (i2 - byteArrayInputStream.available()) + i);
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("unable to recover ephemeral public key: ");
                sb.append(e.getMessage());
                throw new InvalidCipherTextException(sb.toString(), e);
            }
        }
        this.agree.init(this.privParam);
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.agree.getFieldSize(), this.agree.calculateAgreement(this.pubParam));
        byte[] bArr2 = this.f1145V;
        if (bArr2.length != 0) {
            byte[] bArr3 = new byte[(bArr2.length + asUnsignedByteArray.length)];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(asUnsignedByteArray, 0, bArr3, this.f1145V.length, asUnsignedByteArray.length);
            asUnsignedByteArray = bArr3;
        }
        this.kdf.init(new KDFParameters(asUnsignedByteArray, this.param.getDerivationV()));
        return this.forEncryption ? encryptBlock(bArr, i, i2) : decryptBlock(bArr, i, i2);
    }
}
