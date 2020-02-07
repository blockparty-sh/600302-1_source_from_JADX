package org.bitcoinj.wallet;

import com.facebook.stetho.common.Utf8Charset;
import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import java.security.SecureRandom;
import java.util.List;
import javax.annotation.Nullable;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.crypto.EncryptableItem;
import org.bitcoinj.crypto.EncryptedData;
import org.bitcoinj.crypto.KeyCrypter;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;
import org.bitcoinj.crypto.MnemonicException.MnemonicLengthException;
import org.bitcoinj.wallet.Protos.C3527Wallet.EncryptionType;
import org.spongycastle.crypto.params.KeyParameter;

public class DeterministicSeed implements EncryptableItem {
    public static final int DEFAULT_SEED_ENTROPY_BITS = 128;
    public static final int MAX_SEED_ENTROPY_BITS = 512;
    private long creationTimeSeconds;
    @Nullable
    private final EncryptedData encryptedMnemonicCode;
    @Nullable
    private EncryptedData encryptedSeed;
    @Nullable
    private final List<String> mnemonicCode;
    @Nullable
    private final byte[] seed;

    public DeterministicSeed(String str, byte[] bArr, String str2, long j) throws UnreadableWalletException {
        this(decodeMnemonicCode(str), bArr, str2, j);
    }

    public DeterministicSeed(byte[] bArr, List<String> list, long j) {
        this.seed = (byte[]) Preconditions.checkNotNull(bArr);
        this.mnemonicCode = (List) Preconditions.checkNotNull(list);
        this.encryptedMnemonicCode = null;
        this.creationTimeSeconds = j;
    }

    public DeterministicSeed(EncryptedData encryptedData, @Nullable EncryptedData encryptedData2, long j) {
        this.seed = null;
        this.mnemonicCode = null;
        this.encryptedMnemonicCode = (EncryptedData) Preconditions.checkNotNull(encryptedData);
        this.encryptedSeed = encryptedData2;
        this.creationTimeSeconds = j;
    }

    public DeterministicSeed(List<String> list, @Nullable byte[] bArr, String str, long j) {
        if (bArr == null) {
            bArr = MnemonicCode.toSeed(list, (String) Preconditions.checkNotNull(str));
        }
        this(bArr, list, j);
    }

    public DeterministicSeed(SecureRandom secureRandom, int i, String str, long j) {
        this(getEntropy(secureRandom, i), (String) Preconditions.checkNotNull(str), j);
    }

    public DeterministicSeed(byte[] bArr, String str, long j) {
        boolean z = true;
        Preconditions.checkArgument(bArr.length % 4 == 0, "entropy size in bits not divisible by 32");
        if (bArr.length * 8 < 128) {
            z = false;
        }
        Preconditions.checkArgument(z, "entropy size too small");
        Preconditions.checkNotNull(str);
        try {
            this.mnemonicCode = MnemonicCode.INSTANCE.toMnemonic(bArr);
            this.seed = MnemonicCode.toSeed(this.mnemonicCode, str);
            this.encryptedMnemonicCode = null;
            this.creationTimeSeconds = j;
        } catch (MnemonicLengthException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getEntropy(SecureRandom secureRandom, int i) {
        Preconditions.checkArgument(i <= 512, "requested entropy size too large");
        byte[] bArr = new byte[(i / 8)];
        secureRandom.nextBytes(bArr);
        return bArr;
    }

    public boolean isEncrypted() {
        Preconditions.checkState((this.mnemonicCode == null && this.encryptedMnemonicCode == null) ? false : true);
        if (this.encryptedMnemonicCode != null) {
            return true;
        }
        return false;
    }

    public String toString() {
        if (isEncrypted()) {
            return "DeterministicSeed [encrypted]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("DeterministicSeed ");
        sb.append(toHexString());
        sb.append(" ");
        sb.append(C3447Utils.join(this.mnemonicCode));
        return sb.toString();
    }

    @Nullable
    public String toHexString() {
        if (this.seed != null) {
            return C3447Utils.HEX.encode(this.seed);
        }
        return null;
    }

    @Nullable
    public byte[] getSecretBytes() {
        return getMnemonicAsBytes();
    }

    @Nullable
    public byte[] getSeedBytes() {
        return this.seed;
    }

    @Nullable
    public EncryptedData getEncryptedData() {
        return this.encryptedMnemonicCode;
    }

    public EncryptionType getEncryptionType() {
        return EncryptionType.ENCRYPTED_SCRYPT_AES;
    }

    @Nullable
    public EncryptedData getEncryptedSeedData() {
        return this.encryptedSeed;
    }

    public long getCreationTimeSeconds() {
        return this.creationTimeSeconds;
    }

    public void setCreationTimeSeconds(long j) {
        this.creationTimeSeconds = j;
    }

    public DeterministicSeed encrypt(KeyCrypter keyCrypter, KeyParameter keyParameter) {
        boolean z = true;
        Preconditions.checkState(this.encryptedMnemonicCode == null, "Trying to encrypt seed twice");
        if (this.mnemonicCode == null) {
            z = false;
        }
        Preconditions.checkState(z, "Mnemonic missing so cannot encrypt");
        return new DeterministicSeed(keyCrypter.encrypt(getMnemonicAsBytes(), keyParameter), keyCrypter.encrypt(this.seed, keyParameter), this.creationTimeSeconds);
    }

    private byte[] getMnemonicAsBytes() {
        return C3447Utils.join(this.mnemonicCode).getBytes(Charsets.UTF_8);
    }

    public DeterministicSeed decrypt(KeyCrypter keyCrypter, String str, KeyParameter keyParameter) {
        Preconditions.checkState(isEncrypted());
        Preconditions.checkNotNull(this.encryptedMnemonicCode);
        List decodeMnemonicCode = decodeMnemonicCode(keyCrypter.decrypt(this.encryptedMnemonicCode, keyParameter));
        EncryptedData encryptedData = this.encryptedSeed;
        DeterministicSeed deterministicSeed = new DeterministicSeed(decodeMnemonicCode, encryptedData == null ? null : keyCrypter.decrypt(encryptedData, keyParameter), str, this.creationTimeSeconds);
        return deterministicSeed;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DeterministicSeed deterministicSeed = (DeterministicSeed) obj;
        if (this.creationTimeSeconds != deterministicSeed.creationTimeSeconds || !Objects.equal(this.encryptedMnemonicCode, deterministicSeed.encryptedMnemonicCode) || !Objects.equal(this.mnemonicCode, deterministicSeed.mnemonicCode)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.creationTimeSeconds), this.encryptedMnemonicCode, this.mnemonicCode);
    }

    public void check() throws MnemonicException {
        if (this.mnemonicCode != null) {
            MnemonicCode.INSTANCE.check(this.mnemonicCode);
        }
    }

    /* access modifiers changed from: 0000 */
    public byte[] getEntropyBytes() throws MnemonicException {
        return MnemonicCode.INSTANCE.toEntropy(this.mnemonicCode);
    }

    @Nullable
    public List<String> getMnemonicCode() {
        return this.mnemonicCode;
    }

    private static List<String> decodeMnemonicCode(byte[] bArr) {
        return decodeMnemonicCode(C3447Utils.toString(bArr, Utf8Charset.NAME));
    }

    private static List<String> decodeMnemonicCode(String str) {
        return Splitter.m111on(" ").splitToList(str);
    }
}
