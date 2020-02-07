package org.bitcoinj.crypto;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.protobuf.ByteString;
import java.security.SecureRandom;
import java.util.Arrays;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.wallet.Protos.C3527Wallet.EncryptionType;
import org.bitcoinj.wallet.Protos.ScryptParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.crypto.engines.AESFastEngine;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class KeyCrypterScrypt implements KeyCrypter {
    public static final int BLOCK_LENGTH = 16;
    public static final int KEY_LENGTH = 32;
    public static final int SALT_LENGTH = 8;
    private static final Logger log = LoggerFactory.getLogger(KeyCrypterScrypt.class);
    private static final SecureRandom secureRandom = new SecureRandom();
    private final ScryptParameters scryptParameters;

    static {
        if (C3447Utils.isAndroidRuntime()) {
            new LinuxSecureRandom();
        }
    }

    public static byte[] randomSalt() {
        byte[] bArr = new byte[8];
        secureRandom.nextBytes(bArr);
        return bArr;
    }

    public KeyCrypterScrypt() {
        this.scryptParameters = ScryptParameters.newBuilder().setSalt(ByteString.copyFrom(randomSalt())).build();
    }

    public KeyCrypterScrypt(int i) {
        this.scryptParameters = ScryptParameters.newBuilder().setSalt(ByteString.copyFrom(randomSalt())).setN((long) i).build();
    }

    public KeyCrypterScrypt(ScryptParameters scryptParameters2) {
        this.scryptParameters = (ScryptParameters) Preconditions.checkNotNull(scryptParameters2);
        if (scryptParameters2.getSalt() == null || scryptParameters2.getSalt().toByteArray() == null || scryptParameters2.getSalt().toByteArray().length == 0) {
            log.warn("You are using a ScryptParameters with no salt. Your encryption may be vulnerable to a dictionary attack.");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.spongycastle.crypto.params.KeyParameter deriveKey(java.lang.CharSequence r10) throws org.bitcoinj.crypto.KeyCrypterException {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            byte[] r10 = convertToByteArray(r10)     // Catch:{ Exception -> 0x0069, all -> 0x0064 }
            byte[] r1 = new byte[r0]     // Catch:{ Exception -> 0x0062 }
            org.bitcoinj.wallet.Protos$ScryptParameters r2 = r9.scryptParameters     // Catch:{ Exception -> 0x0062 }
            com.google.protobuf.ByteString r2 = r2.getSalt()     // Catch:{ Exception -> 0x0062 }
            if (r2 == 0) goto L_0x001b
            org.bitcoinj.wallet.Protos$ScryptParameters r1 = r9.scryptParameters     // Catch:{ Exception -> 0x0062 }
            com.google.protobuf.ByteString r1 = r1.getSalt()     // Catch:{ Exception -> 0x0062 }
            byte[] r1 = r1.toByteArray()     // Catch:{ Exception -> 0x0062 }
            goto L_0x0022
        L_0x001b:
            org.slf4j.Logger r2 = log     // Catch:{ Exception -> 0x0062 }
            java.lang.String r3 = "You are using a ScryptParameters with no salt. Your encryption may be vulnerable to a dictionary attack."
            r2.warn(r3)     // Catch:{ Exception -> 0x0062 }
        L_0x0022:
            r3 = r1
            com.google.common.base.Stopwatch r1 = com.google.common.base.Stopwatch.createStarted()     // Catch:{ Exception -> 0x0062 }
            org.bitcoinj.wallet.Protos$ScryptParameters r2 = r9.scryptParameters     // Catch:{ Exception -> 0x0062 }
            long r4 = r2.getN()     // Catch:{ Exception -> 0x0062 }
            int r4 = (int) r4     // Catch:{ Exception -> 0x0062 }
            org.bitcoinj.wallet.Protos$ScryptParameters r2 = r9.scryptParameters     // Catch:{ Exception -> 0x0062 }
            int r5 = r2.getR()     // Catch:{ Exception -> 0x0062 }
            org.bitcoinj.wallet.Protos$ScryptParameters r2 = r9.scryptParameters     // Catch:{ Exception -> 0x0062 }
            int r6 = r2.getP()     // Catch:{ Exception -> 0x0062 }
            r7 = 32
            r2 = r10
            byte[] r2 = com.lambdaworks.crypto.SCrypt.scrypt(r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0062 }
            r1.stop()     // Catch:{ Exception -> 0x0062 }
            org.slf4j.Logger r3 = log     // Catch:{ Exception -> 0x0062 }
            java.lang.String r4 = "Deriving key took {} for {} scrypt iterations."
            org.bitcoinj.wallet.Protos$ScryptParameters r5 = r9.scryptParameters     // Catch:{ Exception -> 0x0062 }
            long r5 = r5.getN()     // Catch:{ Exception -> 0x0062 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ Exception -> 0x0062 }
            r3.info(r4, r1, r5)     // Catch:{ Exception -> 0x0062 }
            org.spongycastle.crypto.params.KeyParameter r1 = new org.spongycastle.crypto.params.KeyParameter     // Catch:{ Exception -> 0x0062 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0062 }
            if (r10 == 0) goto L_0x005f
            java.util.Arrays.fill(r10, r0)
        L_0x005f:
            return r1
        L_0x0060:
            r1 = move-exception
            goto L_0x0075
        L_0x0062:
            r1 = move-exception
            goto L_0x006d
        L_0x0064:
            r10 = move-exception
            r8 = r1
            r1 = r10
            r10 = r8
            goto L_0x0075
        L_0x0069:
            r10 = move-exception
            r8 = r1
            r1 = r10
            r10 = r8
        L_0x006d:
            org.bitcoinj.crypto.KeyCrypterException r2 = new org.bitcoinj.crypto.KeyCrypterException     // Catch:{ all -> 0x0060 }
            java.lang.String r3 = "Could not generate key from password and salt."
            r2.<init>(r3, r1)     // Catch:{ all -> 0x0060 }
            throw r2     // Catch:{ all -> 0x0060 }
        L_0x0075:
            if (r10 == 0) goto L_0x007a
            java.util.Arrays.fill(r10, r0)
        L_0x007a:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.crypto.KeyCrypterScrypt.deriveKey(java.lang.CharSequence):org.spongycastle.crypto.params.KeyParameter");
    }

    public EncryptedData encrypt(byte[] bArr, KeyParameter keyParameter) throws KeyCrypterException {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkNotNull(keyParameter);
        try {
            byte[] bArr2 = new byte[16];
            secureRandom.nextBytes(bArr2);
            ParametersWithIV parametersWithIV = new ParametersWithIV(keyParameter, bArr2);
            PaddedBufferedBlockCipher paddedBufferedBlockCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESFastEngine()));
            paddedBufferedBlockCipher.init(true, parametersWithIV);
            byte[] bArr3 = new byte[paddedBufferedBlockCipher.getOutputSize(bArr.length)];
            int processBytes = paddedBufferedBlockCipher.processBytes(bArr, 0, bArr.length, bArr3, 0);
            return new EncryptedData(bArr2, Arrays.copyOf(bArr3, processBytes + paddedBufferedBlockCipher.doFinal(bArr3, processBytes)));
        } catch (Exception e) {
            throw new KeyCrypterException("Could not encrypt bytes.", e);
        }
    }

    public byte[] decrypt(EncryptedData encryptedData, KeyParameter keyParameter) throws KeyCrypterException {
        Preconditions.checkNotNull(encryptedData);
        Preconditions.checkNotNull(keyParameter);
        try {
            ParametersWithIV parametersWithIV = new ParametersWithIV(new KeyParameter(keyParameter.getKey()), encryptedData.initialisationVector);
            PaddedBufferedBlockCipher paddedBufferedBlockCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESFastEngine()));
            paddedBufferedBlockCipher.init(false, parametersWithIV);
            byte[] bArr = encryptedData.encryptedBytes;
            byte[] bArr2 = new byte[paddedBufferedBlockCipher.getOutputSize(bArr.length)];
            int processBytes = paddedBufferedBlockCipher.processBytes(bArr, 0, bArr.length, bArr2, 0);
            return Arrays.copyOf(bArr2, processBytes + paddedBufferedBlockCipher.doFinal(bArr2, processBytes));
        } catch (Exception e) {
            throw new KeyCrypterException("Could not decrypt bytes", e);
        }
    }

    private static byte[] convertToByteArray(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        byte[] bArr = new byte[(charSequence.length() << 1)];
        for (int i = 0; i < charSequence.length(); i++) {
            int i2 = i << 1;
            bArr[i2] = (byte) ((charSequence.charAt(i) & 65280) >> 8);
            bArr[i2 + 1] = (byte) (charSequence.charAt(i) & 255);
        }
        return bArr;
    }

    public ScryptParameters getScryptParameters() {
        return this.scryptParameters;
    }

    public EncryptionType getUnderstoodEncryptionType() {
        return EncryptionType.ENCRYPTED_SCRYPT_AES;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AES-256-CBC, Scrypt (N: ");
        sb.append(this.scryptParameters.getN());
        sb.append(")");
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.scryptParameters);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equal(this.scryptParameters, ((KeyCrypterScrypt) obj).scryptParameters);
    }
}
