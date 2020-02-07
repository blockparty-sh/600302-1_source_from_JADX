package org.bitcoinj.crypto;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.Nullable;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ECKey.ECDSASignature;
import org.bitcoinj.core.ECKey.MissingPrivateKeyException;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.math.p025ec.ECPoint;

public class DeterministicKey extends ECKey {
    public static final Comparator<ECKey> CHILDNUM_ORDER = new Comparator<ECKey>() {
        public int compare(ECKey eCKey, ECKey eCKey2) {
            return ((DeterministicKey) eCKey).getChildNumber().compareTo(((DeterministicKey) eCKey2).getChildNumber());
        }
    };
    private final byte[] chainCode;
    private final ImmutableList<ChildNumber> childNumberPath;
    private final int depth;
    private final DeterministicKey parent;
    private int parentFingerprint;

    public DeterministicKey(ImmutableList<ChildNumber> immutableList, byte[] bArr, LazyECPoint lazyECPoint, @Nullable BigInteger bigInteger, @Nullable DeterministicKey deterministicKey) {
        int i;
        super(bigInteger, compressPoint((LazyECPoint) Preconditions.checkNotNull(lazyECPoint)));
        int i2 = 0;
        Preconditions.checkArgument(bArr.length == 32);
        this.parent = deterministicKey;
        this.childNumberPath = (ImmutableList) Preconditions.checkNotNull(immutableList);
        this.chainCode = Arrays.copyOf(bArr, bArr.length);
        if (deterministicKey == null) {
            i = 0;
        } else {
            i = deterministicKey.depth + 1;
        }
        this.depth = i;
        if (deterministicKey != null) {
            i2 = deterministicKey.getFingerprint();
        }
        this.parentFingerprint = i2;
    }

    public DeterministicKey(ImmutableList<ChildNumber> immutableList, byte[] bArr, ECPoint eCPoint, @Nullable BigInteger bigInteger, @Nullable DeterministicKey deterministicKey) {
        this(immutableList, bArr, new LazyECPoint(eCPoint), bigInteger, deterministicKey);
    }

    public DeterministicKey(ImmutableList<ChildNumber> immutableList, byte[] bArr, BigInteger bigInteger, @Nullable DeterministicKey deterministicKey) {
        int i;
        super(bigInteger, compressPoint(ECKey.publicPointFromPrivate(bigInteger)));
        int i2 = 0;
        Preconditions.checkArgument(bArr.length == 32);
        this.parent = deterministicKey;
        this.childNumberPath = (ImmutableList) Preconditions.checkNotNull(immutableList);
        this.chainCode = Arrays.copyOf(bArr, bArr.length);
        if (deterministicKey == null) {
            i = 0;
        } else {
            i = deterministicKey.depth + 1;
        }
        this.depth = i;
        if (deterministicKey != null) {
            i2 = deterministicKey.getFingerprint();
        }
        this.parentFingerprint = i2;
    }

    public DeterministicKey(ImmutableList<ChildNumber> immutableList, byte[] bArr, KeyCrypter keyCrypter, LazyECPoint lazyECPoint, EncryptedData encryptedData, @Nullable DeterministicKey deterministicKey) {
        this(immutableList, bArr, lazyECPoint, (BigInteger) null, deterministicKey);
        this.encryptedPrivateKey = (EncryptedData) Preconditions.checkNotNull(encryptedData);
        this.keyCrypter = (KeyCrypter) Preconditions.checkNotNull(keyCrypter);
    }

    private int ascertainParentFingerprint(DeterministicKey deterministicKey, int i) throws IllegalArgumentException {
        if (i == 0) {
            return 0;
        }
        DeterministicKey deterministicKey2 = this.parent;
        if (deterministicKey2 != null) {
            Preconditions.checkArgument(deterministicKey2.getFingerprint() == i, "parent fingerprint mismatch", Integer.toHexString(this.parent.getFingerprint()), Integer.toHexString(i));
        }
        return i;
    }

    private DeterministicKey(ImmutableList<ChildNumber> immutableList, byte[] bArr, LazyECPoint lazyECPoint, @Nullable DeterministicKey deterministicKey, int i, int i2) {
        super((BigInteger) null, compressPoint((LazyECPoint) Preconditions.checkNotNull(lazyECPoint)));
        Preconditions.checkArgument(bArr.length == 32);
        this.parent = deterministicKey;
        this.childNumberPath = (ImmutableList) Preconditions.checkNotNull(immutableList);
        this.chainCode = Arrays.copyOf(bArr, bArr.length);
        this.depth = i;
        this.parentFingerprint = ascertainParentFingerprint(deterministicKey, i2);
    }

    private DeterministicKey(ImmutableList<ChildNumber> immutableList, byte[] bArr, BigInteger bigInteger, @Nullable DeterministicKey deterministicKey, int i, int i2) {
        super(bigInteger, compressPoint(ECKey.publicPointFromPrivate(bigInteger)));
        Preconditions.checkArgument(bArr.length == 32);
        this.parent = deterministicKey;
        this.childNumberPath = (ImmutableList) Preconditions.checkNotNull(immutableList);
        this.chainCode = Arrays.copyOf(bArr, bArr.length);
        this.depth = i;
        this.parentFingerprint = ascertainParentFingerprint(deterministicKey, i2);
    }

    public DeterministicKey(DeterministicKey deterministicKey, DeterministicKey deterministicKey2) {
        super(deterministicKey.priv, deterministicKey.pub.get());
        this.parent = deterministicKey2;
        this.childNumberPath = deterministicKey.childNumberPath;
        this.chainCode = deterministicKey.chainCode;
        this.encryptedPrivateKey = deterministicKey.encryptedPrivateKey;
        this.depth = this.childNumberPath.size();
        this.parentFingerprint = this.parent.getFingerprint();
    }

    public ImmutableList<ChildNumber> getPath() {
        return this.childNumberPath;
    }

    public String getPathAsString() {
        return HDUtils.formatPath(getPath());
    }

    public int getDepth() {
        return this.depth;
    }

    public ChildNumber getChildNumber() {
        if (this.childNumberPath.size() == 0) {
            return ChildNumber.ZERO;
        }
        ImmutableList<ChildNumber> immutableList = this.childNumberPath;
        return (ChildNumber) immutableList.get(immutableList.size() - 1);
    }

    public byte[] getChainCode() {
        return this.chainCode;
    }

    public byte[] getIdentifier() {
        return C3447Utils.sha256hash160(getPubKey());
    }

    public int getFingerprint() {
        return ByteBuffer.wrap(Arrays.copyOfRange(getIdentifier(), 0, 4)).getInt();
    }

    @Nullable
    public DeterministicKey getParent() {
        return this.parent;
    }

    public int getParentFingerprint() {
        return this.parentFingerprint;
    }

    public byte[] getPrivKeyBytes33() {
        byte[] bArr = new byte[33];
        byte[] privKeyBytes = getPrivKeyBytes();
        System.arraycopy(privKeyBytes, 0, bArr, 33 - privKeyBytes.length, privKeyBytes.length);
        return bArr;
    }

    public DeterministicKey dropPrivateBytes() {
        if (isPubKeyOnly()) {
            return this;
        }
        DeterministicKey deterministicKey = new DeterministicKey(getPath(), getChainCode(), this.pub, (BigInteger) null, this.parent);
        return deterministicKey;
    }

    public DeterministicKey dropParent() {
        DeterministicKey deterministicKey = new DeterministicKey(getPath(), getChainCode(), this.pub, this.priv, (DeterministicKey) null);
        deterministicKey.parentFingerprint = this.parentFingerprint;
        return deterministicKey;
    }

    static byte[] addChecksum(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[(length + 4)];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        System.arraycopy(Sha256Hash.hashTwice(bArr), 0, bArr2, length, 4);
        return bArr2;
    }

    public DeterministicKey encrypt(KeyCrypter keyCrypter, KeyParameter keyParameter) throws KeyCrypterException {
        throw new UnsupportedOperationException("Must supply a new parent for encryption");
    }

    public DeterministicKey encrypt(KeyCrypter keyCrypter, KeyParameter keyParameter, @Nullable DeterministicKey deterministicKey) throws KeyCrypterException {
        Preconditions.checkNotNull(keyCrypter);
        if (deterministicKey != null) {
            Preconditions.checkArgument(deterministicKey.isEncrypted());
        }
        byte[] privKeyBytes = getPrivKeyBytes();
        Preconditions.checkState(privKeyBytes != null, "Private key is not available");
        DeterministicKey deterministicKey2 = new DeterministicKey(this.childNumberPath, this.chainCode, keyCrypter, this.pub, keyCrypter.encrypt(privKeyBytes, keyParameter), deterministicKey);
        if (deterministicKey == null) {
            deterministicKey2.setCreationTimeSeconds(getCreationTimeSeconds());
        }
        return deterministicKey2;
    }

    public boolean isPubKeyOnly() {
        if (super.isPubKeyOnly()) {
            DeterministicKey deterministicKey = this.parent;
            if (deterministicKey == null || deterministicKey.isPubKeyOnly()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPrivKey() {
        return findParentWithPrivKey() != null;
    }

    @Nullable
    public byte[] getSecretBytes() {
        if (this.priv != null) {
            return getPrivKeyBytes();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0012, code lost:
        if (r0.isEncrypted() != false) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isEncrypted() {
        /*
            r1 = this;
            java.math.BigInteger r0 = r1.priv
            if (r0 != 0) goto L_0x0016
            boolean r0 = super.isEncrypted()
            if (r0 != 0) goto L_0x0014
            org.bitcoinj.crypto.DeterministicKey r0 = r1.parent
            if (r0 == 0) goto L_0x0016
            boolean r0 = r0.isEncrypted()
            if (r0 == 0) goto L_0x0016
        L_0x0014:
            r0 = 1
            goto L_0x0017
        L_0x0016:
            r0 = 0
        L_0x0017:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.crypto.DeterministicKey.isEncrypted():boolean");
    }

    @Nullable
    public KeyCrypter getKeyCrypter() {
        if (this.keyCrypter != null) {
            return this.keyCrypter;
        }
        DeterministicKey deterministicKey = this.parent;
        if (deterministicKey != null) {
            return deterministicKey.getKeyCrypter();
        }
        return null;
    }

    public ECDSASignature sign(Sha256Hash sha256Hash, @Nullable KeyParameter keyParameter) throws KeyCrypterException {
        if (isEncrypted()) {
            return super.sign(sha256Hash, keyParameter);
        }
        BigInteger findOrDerivePrivateKey = findOrDerivePrivateKey();
        if (findOrDerivePrivateKey != null) {
            return super.doSign(sha256Hash, findOrDerivePrivateKey);
        }
        throw new MissingPrivateKeyException();
    }

    public DeterministicKey decrypt(KeyCrypter keyCrypter, KeyParameter keyParameter) throws KeyCrypterException {
        Preconditions.checkNotNull(keyCrypter);
        if (this.keyCrypter == null || this.keyCrypter.equals(keyCrypter)) {
            DeterministicKey deterministicKey = new DeterministicKey(this.childNumberPath, this.chainCode, findOrDeriveEncryptedPrivateKey(keyCrypter, keyParameter), this.parent);
            if (Arrays.equals(deterministicKey.getPubKey(), getPubKey())) {
                if (this.parent == null) {
                    deterministicKey.setCreationTimeSeconds(getCreationTimeSeconds());
                }
                return deterministicKey;
            }
            throw new KeyCrypterException("Provided AES key is wrong");
        }
        throw new KeyCrypterException("The keyCrypter being used to decrypt the key is different to the one that was used to encrypt it");
    }

    public DeterministicKey decrypt(KeyParameter keyParameter) throws KeyCrypterException {
        return (DeterministicKey) super.decrypt(keyParameter);
    }

    private BigInteger findOrDeriveEncryptedPrivateKey(KeyCrypter keyCrypter, KeyParameter keyParameter) {
        if (this.encryptedPrivateKey != null) {
            return new BigInteger(1, keyCrypter.decrypt(this.encryptedPrivateKey, keyParameter));
        }
        DeterministicKey deterministicKey = this.parent;
        while (deterministicKey != null && deterministicKey.encryptedPrivateKey == null) {
            deterministicKey = deterministicKey.parent;
        }
        if (deterministicKey != null) {
            return derivePrivateKeyDownwards(deterministicKey, keyCrypter.decrypt(deterministicKey.encryptedPrivateKey, keyParameter));
        }
        throw new KeyCrypterException("Neither this key nor its parents have an encrypted private key");
    }

    private DeterministicKey findParentWithPrivKey() {
        DeterministicKey deterministicKey = this;
        while (deterministicKey != null && deterministicKey.priv == null) {
            deterministicKey = deterministicKey.parent;
        }
        return deterministicKey;
    }

    @Nullable
    private BigInteger findOrDerivePrivateKey() {
        DeterministicKey findParentWithPrivKey = findParentWithPrivKey();
        if (findParentWithPrivKey == null) {
            return null;
        }
        return derivePrivateKeyDownwards(findParentWithPrivKey, findParentWithPrivKey.priv.toByteArray());
    }

    private BigInteger derivePrivateKeyDownwards(DeterministicKey deterministicKey, byte[] bArr) {
        DeterministicKey deterministicKey2 = new DeterministicKey(deterministicKey.childNumberPath, deterministicKey.chainCode, deterministicKey.pub, new BigInteger(1, bArr), deterministicKey.parent);
        UnmodifiableIterator it = this.childNumberPath.subList(deterministicKey.getPath().size(), this.childNumberPath.size()).iterator();
        while (it.hasNext()) {
            deterministicKey2 = HDKeyDerivation.deriveChildKey(deterministicKey2, (ChildNumber) it.next());
        }
        if (deterministicKey2.pub.equals((Object) this.pub)) {
            return (BigInteger) Preconditions.checkNotNull(deterministicKey2.priv);
        }
        throw new KeyCrypterException("Could not decrypt bytes");
    }

    public DeterministicKey derive(int i) {
        return HDKeyDerivation.deriveChildKey(this, new ChildNumber(i, true));
    }

    public BigInteger getPrivKey() {
        BigInteger findOrDerivePrivateKey = findOrDerivePrivateKey();
        Preconditions.checkState(findOrDerivePrivateKey != null, "Private key bytes not available");
        return findOrDerivePrivateKey;
    }

    public byte[] serializePublic(NetworkParameters networkParameters) {
        return serialize(networkParameters, true);
    }

    public byte[] serializePrivate(NetworkParameters networkParameters) {
        return serialize(networkParameters, false);
    }

    private byte[] serialize(NetworkParameters networkParameters, boolean z) {
        ByteBuffer allocate = ByteBuffer.allocate(78);
        allocate.putInt(z ? networkParameters.getBip32HeaderPub() : networkParameters.getBip32HeaderPriv());
        allocate.put((byte) getDepth());
        allocate.putInt(getParentFingerprint());
        allocate.putInt(getChildNumber().mo46017i());
        allocate.put(getChainCode());
        allocate.put(z ? getPubKey() : getPrivKeyBytes33());
        Preconditions.checkState(allocate.position() == 78);
        return allocate.array();
    }

    public String serializePubB58(NetworkParameters networkParameters) {
        return toBase58(serialize(networkParameters, true));
    }

    public String serializePrivB58(NetworkParameters networkParameters) {
        return toBase58(serialize(networkParameters, false));
    }

    static String toBase58(byte[] bArr) {
        return Base58.encode(addChecksum(bArr));
    }

    public static DeterministicKey deserializeB58(String str, NetworkParameters networkParameters) {
        return deserializeB58(null, str, networkParameters);
    }

    public static DeterministicKey deserializeB58(@Nullable DeterministicKey deterministicKey, String str, NetworkParameters networkParameters) {
        return deserialize(networkParameters, Base58.decodeChecked(str), deterministicKey);
    }

    public static DeterministicKey deserialize(NetworkParameters networkParameters, byte[] bArr) {
        return deserialize(networkParameters, bArr, null);
    }

    public static DeterministicKey deserialize(NetworkParameters networkParameters, byte[] bArr, @Nullable DeterministicKey deterministicKey) {
        ImmutableList immutableList;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        int i = wrap.getInt();
        boolean z = false;
        if (i == networkParameters.getBip32HeaderPriv() || i == networkParameters.getBip32HeaderPub()) {
            if (i == networkParameters.getBip32HeaderPub()) {
                z = true;
            }
            byte b = wrap.get() & 255;
            int i2 = wrap.getInt();
            ChildNumber childNumber = new ChildNumber(wrap.getInt());
            if (deterministicKey != null) {
                if (i2 == 0) {
                    throw new IllegalArgumentException("Parent was provided but this key doesn't have one");
                } else if (deterministicKey.getFingerprint() == i2) {
                    immutableList = HDUtils.append(deterministicKey.getPath(), childNumber);
                    if (immutableList.size() != b) {
                        throw new IllegalArgumentException("Depth does not match");
                    }
                } else {
                    throw new IllegalArgumentException("Parent fingerprints don't match");
                }
            } else if (b >= 1) {
                immutableList = ImmutableList.m127of(childNumber);
            } else {
                immutableList = ImmutableList.m126of();
            }
            ImmutableList immutableList2 = immutableList;
            byte[] bArr2 = new byte[32];
            wrap.get(bArr2);
            byte[] bArr3 = new byte[33];
            wrap.get(bArr3);
            Preconditions.checkArgument(!wrap.hasRemaining(), "Found unexpected data in key");
            if (z) {
                DeterministicKey deterministicKey2 = new DeterministicKey(immutableList2, bArr2, new LazyECPoint(ECKey.CURVE.getCurve(), bArr3), deterministicKey, (int) b, i2);
                return deterministicKey2;
            }
            DeterministicKey deterministicKey3 = new DeterministicKey(immutableList2, bArr2, new BigInteger(1, bArr3), deterministicKey, (int) b, i2);
            return deterministicKey3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown header bytes: ");
        sb.append(toBase58(bArr).substring(0, 4));
        throw new IllegalArgumentException(sb.toString());
    }

    public long getCreationTimeSeconds() {
        DeterministicKey deterministicKey = this.parent;
        if (deterministicKey != null) {
            return deterministicKey.getCreationTimeSeconds();
        }
        return super.getCreationTimeSeconds();
    }

    public void setCreationTimeSeconds(long j) {
        if (this.parent == null) {
            super.setCreationTimeSeconds(j);
            return;
        }
        throw new IllegalStateException("Creation time can only be set on root keys.");
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DeterministicKey deterministicKey = (DeterministicKey) obj;
        if (!super.equals(deterministicKey) || !Arrays.equals(this.chainCode, deterministicKey.chainCode) || !Objects.equal(this.childNumberPath, deterministicKey.childNumberPath)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), Integer.valueOf(Arrays.hashCode(this.chainCode)), this.childNumberPath);
    }

    public String toString() {
        ToStringHelper omitNullValues = MoreObjects.toStringHelper((Object) this).omitNullValues();
        omitNullValues.add("pub", (Object) C3447Utils.HEX.encode(this.pub.getEncoded()));
        omitNullValues.add("chainCode", (Object) C3447Utils.HEX.encode(this.chainCode));
        omitNullValues.add(JsonDataKey_signMessage.path, (Object) getPathAsString());
        if (this.creationTimeSeconds > 0) {
            omitNullValues.add("creationTimeSeconds", this.creationTimeSeconds);
        }
        omitNullValues.add("isEncrypted", isEncrypted());
        omitNullValues.add("isPubKeyOnly", isPubKeyOnly());
        return omitNullValues.toString();
    }

    public void formatKeyWithAddress(boolean z, StringBuilder sb, NetworkParameters networkParameters) {
        Address address = toAddress(networkParameters);
        sb.append("  addr:");
        sb.append(address);
        sb.append("  hash160:");
        sb.append(C3447Utils.HEX.encode(getPubKeyHash()));
        sb.append("  (");
        sb.append(getPathAsString());
        sb.append(")\n");
        if (z) {
            sb.append("  ");
            sb.append(toStringWithPrivate(networkParameters));
            sb.append("\n");
        }
    }
}
