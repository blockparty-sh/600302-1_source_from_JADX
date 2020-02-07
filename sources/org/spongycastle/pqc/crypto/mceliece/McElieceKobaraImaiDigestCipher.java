package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.MessageEncryptor;

public class McElieceKobaraImaiDigestCipher {
    private boolean forEncrypting;
    private final MessageEncryptor mcElieceCCA2Cipher;
    private final Digest messDigest;

    public McElieceKobaraImaiDigestCipher(MessageEncryptor messageEncryptor, Digest digest) {
        this.mcElieceCCA2Cipher = messageEncryptor;
        this.messDigest = digest;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        this.forEncrypting = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            asymmetricKeyParameter = (AsymmetricKeyParameter) ((ParametersWithRandom) cipherParameters).getParameters();
        } else {
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
        }
        if (z && asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("Encrypting Requires Public Key.");
        } else if (z || asymmetricKeyParameter.isPrivate()) {
            reset();
            this.mcElieceCCA2Cipher.init(z, cipherParameters);
        } else {
            throw new IllegalArgumentException("Decrypting Requires Private Key.");
        }
    }

    public byte[] messageEncrypt() {
        if (this.forEncrypting) {
            byte[] bArr = new byte[this.messDigest.getDigestSize()];
            this.messDigest.doFinal(bArr, 0);
            try {
                return this.mcElieceCCA2Cipher.messageEncrypt(bArr);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new IllegalStateException("McElieceKobaraImaiDigestCipher not initialised for encrypting.");
        }
    }

    public byte[] messageDecrypt(byte[] bArr) {
        if (!this.forEncrypting) {
            try {
                return this.mcElieceCCA2Cipher.messageDecrypt(bArr);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new IllegalStateException("McElieceKobaraImaiDigestCipher not initialised for decrypting.");
        }
    }

    public void update(byte b) {
        this.messDigest.update(b);
    }

    public void update(byte[] bArr, int i, int i2) {
        this.messDigest.update(bArr, i, i2);
    }

    public void reset() {
        this.messDigest.reset();
    }
}
