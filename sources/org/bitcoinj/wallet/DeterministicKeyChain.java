package org.bitcoinj.wallet;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import com.google.protobuf.ByteString;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import org.bitcoinj.core.BloomFilter;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.EncryptedData;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.HDKeyDerivation.PublicDeriveMode;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.crypto.KeyCrypter;
import org.bitcoinj.crypto.KeyCrypterException;
import org.bitcoinj.crypto.KeyCrypterScrypt;
import org.bitcoinj.script.Script;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.KeyChain.KeyPurpose;
import org.bitcoinj.wallet.Protos.C3509Key;
import org.bitcoinj.wallet.Protos.C3509Key.C3511Type;
import org.bitcoinj.wallet.Protos.C3527Wallet.EncryptionType;
import org.bitcoinj.wallet.listeners.KeyChainEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.crypto.params.KeyParameter;

public class DeterministicKeyChain implements EncryptableKeyChain {
    public static final ImmutableList<ChildNumber> ACCOUNT_ZERO_PATH = ImmutableList.m127of(ChildNumber.ZERO_HARDENED);
    public static final ImmutableList<ChildNumber> BIP44_ACCOUNT_ZERO_PATH = ImmutableList.m129of(new ChildNumber(44, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO_HARDENED);
    public static final String DEFAULT_PASSPHRASE_FOR_MNEMONIC = "";
    public static final ImmutableList<ChildNumber> EXTERNAL_PATH = HDUtils.concat(ACCOUNT_ZERO_PATH, EXTERNAL_SUBPATH);
    public static final ImmutableList<ChildNumber> EXTERNAL_SUBPATH = ImmutableList.m127of(ChildNumber.ZERO);
    public static final ImmutableList<ChildNumber> INTERNAL_PATH = HDUtils.concat(ACCOUNT_ZERO_PATH, INTERNAL_SUBPATH);
    public static final ImmutableList<ChildNumber> INTERNAL_SUBPATH = ImmutableList.m127of(ChildNumber.ONE);
    private static final int LAZY_CALCULATE_LOOKAHEAD = -1;
    private static final Logger log = LoggerFactory.getLogger(DeterministicKeyChain.class);
    private final BasicKeyChain basicKeyChain;
    private DeterministicKey externalParentKey;
    private DeterministicHierarchy hierarchy;
    private DeterministicKey internalParentKey;
    private boolean isFollowing;
    private int issuedExternalKeys;
    private int issuedInternalKeys;
    private int keyLookaheadEpoch;
    protected final ReentrantLock lock;
    protected int lookaheadSize;
    protected int lookaheadThreshold;
    @Nullable
    private DeterministicKey rootKey;
    @Nullable
    private DeterministicSeed seed;
    protected int sigsRequiredToSpend;

    /* renamed from: org.bitcoinj.wallet.DeterministicKeyChain$1 */
    static /* synthetic */ class C35021 {
        static final /* synthetic */ int[] $SwitchMap$org$bitcoinj$wallet$KeyChain$KeyPurpose = new int[KeyPurpose.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                org.bitcoinj.wallet.KeyChain$KeyPurpose[] r0 = org.bitcoinj.wallet.KeyChain.KeyPurpose.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bitcoinj$wallet$KeyChain$KeyPurpose = r0
                int[] r0 = $SwitchMap$org$bitcoinj$wallet$KeyChain$KeyPurpose     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.bitcoinj.wallet.KeyChain$KeyPurpose r1 = org.bitcoinj.wallet.KeyChain.KeyPurpose.RECEIVE_FUNDS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$bitcoinj$wallet$KeyChain$KeyPurpose     // Catch:{ NoSuchFieldError -> 0x001f }
                org.bitcoinj.wallet.KeyChain$KeyPurpose r1 = org.bitcoinj.wallet.KeyChain.KeyPurpose.REFUND     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$org$bitcoinj$wallet$KeyChain$KeyPurpose     // Catch:{ NoSuchFieldError -> 0x002a }
                org.bitcoinj.wallet.KeyChain$KeyPurpose r1 = org.bitcoinj.wallet.KeyChain.KeyPurpose.AUTHENTICATION     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$org$bitcoinj$wallet$KeyChain$KeyPurpose     // Catch:{ NoSuchFieldError -> 0x0035 }
                org.bitcoinj.wallet.KeyChain$KeyPurpose r1 = org.bitcoinj.wallet.KeyChain.KeyPurpose.CHANGE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.DeterministicKeyChain.C35021.<clinit>():void");
        }
    }

    public static class Builder<T extends Builder<T>> {
        protected int bits = 128;
        protected byte[] entropy;
        protected String passphrase;
        protected SecureRandom random;
        protected DeterministicSeed seed;
        protected long seedCreationTimeSecs;
        protected DeterministicKey watchingKey;

        /* access modifiers changed from: protected */
        public T self() {
            return this;
        }

        protected Builder() {
        }

        public T entropy(byte[] bArr) {
            this.entropy = bArr;
            return self();
        }

        public T seed(DeterministicSeed deterministicSeed) {
            this.seed = deterministicSeed;
            return self();
        }

        public T random(SecureRandom secureRandom, int i) {
            this.random = secureRandom;
            this.bits = i;
            return self();
        }

        public T random(SecureRandom secureRandom) {
            this.random = secureRandom;
            return self();
        }

        public T watchingKey(DeterministicKey deterministicKey) {
            this.watchingKey = deterministicKey;
            return self();
        }

        public T seedCreationTimeSecs(long j) {
            this.seedCreationTimeSecs = j;
            return self();
        }

        public T passphrase(String str) {
            this.passphrase = str;
            return self();
        }

        public DeterministicKeyChain build() {
            boolean z = false;
            Preconditions.checkState((this.random == null && this.entropy == null && this.seed == null && this.watchingKey == null) ? false : true, "Must provide either entropy or random or seed or watchingKey");
            if (this.passphrase == null || this.seed == null) {
                z = true;
            }
            Preconditions.checkState(z, "Passphrase must not be specified with seed");
            SecureRandom secureRandom = this.random;
            if (secureRandom != null) {
                DeterministicKeyChain deterministicKeyChain = new DeterministicKeyChain(secureRandom, this.bits, getPassphrase(), this.seedCreationTimeSecs);
                return deterministicKeyChain;
            }
            byte[] bArr = this.entropy;
            if (bArr != null) {
                return new DeterministicKeyChain(bArr, getPassphrase(), this.seedCreationTimeSecs);
            }
            DeterministicSeed deterministicSeed = this.seed;
            if (deterministicSeed != null) {
                deterministicSeed.setCreationTimeSeconds(this.seedCreationTimeSecs);
                return new DeterministicKeyChain(this.seed);
            }
            this.watchingKey.setCreationTimeSeconds(this.seedCreationTimeSecs);
            return new DeterministicKeyChain(this.watchingKey);
        }

        /* access modifiers changed from: protected */
        public String getPassphrase() {
            String str = this.passphrase;
            return str != null ? str : "";
        }
    }

    @Nullable
    public RedeemData findRedeemDataByScriptHash(ByteString byteString) {
        return null;
    }

    public boolean isMarried() {
        return false;
    }

    public void maybeLookAheadScripts() {
    }

    private int calcDefaultLookaheadThreshold() {
        return this.lookaheadSize / 3;
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public DeterministicKeyChain(SecureRandom secureRandom) {
        SecureRandom secureRandom2 = secureRandom;
        this(secureRandom2, 128, "", C3447Utils.currentTimeSeconds());
    }

    public DeterministicKeyChain(SecureRandom secureRandom, int i) {
        SecureRandom secureRandom2 = secureRandom;
        int i2 = i;
        this(secureRandom2, i2, "", C3447Utils.currentTimeSeconds());
    }

    public DeterministicKeyChain(SecureRandom secureRandom, int i, String str, long j) {
        DeterministicSeed deterministicSeed = new DeterministicSeed(secureRandom, i, str, j);
        this(deterministicSeed);
    }

    public DeterministicKeyChain(byte[] bArr, String str, long j) {
        this(new DeterministicSeed(bArr, str, j));
    }

    protected DeterministicKeyChain(DeterministicSeed deterministicSeed) {
        this(deterministicSeed, (KeyCrypter) null);
    }

    public DeterministicKeyChain(DeterministicKey deterministicKey) {
        this.lock = Threading.lock("DeterministicKeyChain");
        this.lookaheadSize = 100;
        this.lookaheadThreshold = calcDefaultLookaheadThreshold();
        boolean z = true;
        this.sigsRequiredToSpend = 1;
        Preconditions.checkArgument(deterministicKey.isPubKeyOnly(), "Private subtrees not currently supported: if you got this key from DKC.getWatchingKey() then use .dropPrivate().dropParent() on it first.");
        if (deterministicKey.getPath().size() != getAccountPath().size()) {
            z = false;
        }
        Preconditions.checkArgument(z, "You can only watch an account key currently");
        this.basicKeyChain = new BasicKeyChain();
        this.seed = null;
        this.rootKey = null;
        addToBasicChain(deterministicKey);
        this.hierarchy = new DeterministicHierarchy(deterministicKey);
        initializeHierarchyUnencrypted(deterministicKey);
    }

    protected DeterministicKeyChain(DeterministicKey deterministicKey, boolean z) {
        this(deterministicKey);
        this.isFollowing = z;
    }

    public static DeterministicKeyChain watchAndFollow(DeterministicKey deterministicKey) {
        return new DeterministicKeyChain(deterministicKey, true);
    }

    public static DeterministicKeyChain watch(DeterministicKey deterministicKey) {
        return new DeterministicKeyChain(deterministicKey);
    }

    protected DeterministicKeyChain(DeterministicSeed deterministicSeed, @Nullable KeyCrypter keyCrypter) {
        this.lock = Threading.lock("DeterministicKeyChain");
        this.lookaheadSize = 100;
        this.lookaheadThreshold = calcDefaultLookaheadThreshold();
        this.sigsRequiredToSpend = 1;
        this.seed = deterministicSeed;
        this.basicKeyChain = new BasicKeyChain(keyCrypter);
        if (!deterministicSeed.isEncrypted()) {
            this.rootKey = HDKeyDerivation.createMasterPrivateKey((byte[]) Preconditions.checkNotNull(deterministicSeed.getSeedBytes()));
            this.rootKey.setCreationTimeSeconds(deterministicSeed.getCreationTimeSeconds());
            addToBasicChain(this.rootKey);
            this.hierarchy = new DeterministicHierarchy(this.rootKey);
            for (int i = 1; i <= getAccountPath().size(); i++) {
                addToBasicChain(this.hierarchy.get(getAccountPath().subList(0, i), false, true));
            }
            initializeHierarchyUnencrypted(this.rootKey);
        }
    }

    protected DeterministicKeyChain(KeyCrypter keyCrypter, KeyParameter keyParameter, DeterministicKeyChain deterministicKeyChain) {
        this.lock = Threading.lock("DeterministicKeyChain");
        this.lookaheadSize = 100;
        this.lookaheadThreshold = calcDefaultLookaheadThreshold();
        this.sigsRequiredToSpend = 1;
        Preconditions.checkNotNull(deterministicKeyChain.rootKey);
        Preconditions.checkNotNull(deterministicKeyChain.seed);
        Preconditions.checkArgument(!deterministicKeyChain.rootKey.isEncrypted(), "Chain already encrypted");
        this.issuedExternalKeys = deterministicKeyChain.issuedExternalKeys;
        this.issuedInternalKeys = deterministicKeyChain.issuedInternalKeys;
        this.lookaheadSize = deterministicKeyChain.lookaheadSize;
        this.lookaheadThreshold = deterministicKeyChain.lookaheadThreshold;
        this.seed = deterministicKeyChain.seed.encrypt(keyCrypter, keyParameter);
        this.basicKeyChain = new BasicKeyChain(keyCrypter);
        this.rootKey = deterministicKeyChain.rootKey.encrypt(keyCrypter, keyParameter, null);
        this.hierarchy = new DeterministicHierarchy(this.rootKey);
        this.basicKeyChain.importKey(this.rootKey);
        for (int i = 1; i < getAccountPath().size(); i++) {
            encryptNonLeaf(keyParameter, deterministicKeyChain, this.rootKey, getAccountPath().subList(0, i));
        }
        DeterministicKey encryptNonLeaf = encryptNonLeaf(keyParameter, deterministicKeyChain, this.rootKey, getAccountPath());
        this.externalParentKey = encryptNonLeaf(keyParameter, deterministicKeyChain, encryptNonLeaf, HDUtils.concat(getAccountPath(), EXTERNAL_SUBPATH));
        this.internalParentKey = encryptNonLeaf(keyParameter, deterministicKeyChain, encryptNonLeaf, HDUtils.concat(getAccountPath(), INTERNAL_SUBPATH));
        for (ECKey eCKey : deterministicKeyChain.basicKeyChain.getKeys()) {
            DeterministicKey deterministicKey = (DeterministicKey) eCKey;
            if (deterministicKey.getPath().size() == getAccountPath().size() + 2) {
                DeterministicKey deterministicKey2 = new DeterministicKey(deterministicKey.dropPrivateBytes(), this.hierarchy.get(((DeterministicKey) Preconditions.checkNotNull(deterministicKey.getParent())).getPath(), false, false));
                this.hierarchy.putKey(deterministicKey2);
                this.basicKeyChain.importKey(deterministicKey2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public ImmutableList<ChildNumber> getAccountPath() {
        return ACCOUNT_ZERO_PATH;
    }

    private DeterministicKey encryptNonLeaf(KeyParameter keyParameter, DeterministicKeyChain deterministicKeyChain, DeterministicKey deterministicKey, ImmutableList<ChildNumber> immutableList) {
        DeterministicKey encrypt = deterministicKeyChain.hierarchy.get(immutableList, false, false).encrypt((KeyCrypter) Preconditions.checkNotNull(this.basicKeyChain.getKeyCrypter()), keyParameter, deterministicKey);
        this.hierarchy.putKey(encrypt);
        this.basicKeyChain.importKey(encrypt);
        return encrypt;
    }

    private void initializeHierarchyUnencrypted(DeterministicKey deterministicKey) {
        this.externalParentKey = this.hierarchy.deriveChild(getAccountPath(), false, false, ChildNumber.ZERO);
        this.internalParentKey = this.hierarchy.deriveChild(getAccountPath(), false, false, ChildNumber.ONE);
        addToBasicChain(this.externalParentKey);
        addToBasicChain(this.internalParentKey);
    }

    public DeterministicKey getKey(KeyPurpose keyPurpose) {
        return (DeterministicKey) getKeys(keyPurpose, 1).get(0);
    }

    public List<DeterministicKey> getKeys(KeyPurpose keyPurpose, int i) {
        int i2;
        DeterministicKey deterministicKey;
        Preconditions.checkArgument(i > 0);
        this.lock.lock();
        try {
            int i3 = C35021.$SwitchMap$org$bitcoinj$wallet$KeyChain$KeyPurpose[keyPurpose.ordinal()];
            if (i3 == 1 || i3 == 2) {
                this.issuedExternalKeys += i;
                i2 = this.issuedExternalKeys;
                deterministicKey = this.externalParentKey;
            } else {
                if (i3 != 3) {
                    if (i3 != 4) {
                        throw new UnsupportedOperationException();
                    }
                }
                this.issuedInternalKeys += i;
                i2 = this.issuedInternalKeys;
                deterministicKey = this.internalParentKey;
            }
            this.basicKeyChain.importKeys(maybeLookAhead(deterministicKey, i2, 0, 0));
            ArrayList arrayList = new ArrayList(i);
            for (int i4 = 0; i4 < i; i4++) {
                DeterministicKey deterministicKey2 = this.hierarchy.get(HDUtils.append(deterministicKey.getPath(), new ChildNumber((i2 - i) + i4, false)), false, false);
                checkForBitFlip(deterministicKey2);
                arrayList.add(deterministicKey2);
            }
            return arrayList;
        } finally {
            this.lock.unlock();
        }
    }

    private void checkForBitFlip(DeterministicKey deterministicKey) {
        byte[] bArr = HDKeyDerivation.deriveChildKeyBytesFromPublic((DeterministicKey) Preconditions.checkNotNull(deterministicKey.getParent()), deterministicKey.getChildNumber(), PublicDeriveMode.WITH_INVERSION).keyBytes;
        byte[] pubKey = deterministicKey.getPubKey();
        if (!Arrays.equals(bArr, pubKey)) {
            throw new IllegalStateException(String.format(Locale.US, "Bit-flip check failed: %s vs %s", new Object[]{Arrays.toString(bArr), Arrays.toString(pubKey)}));
        }
    }

    private void addToBasicChain(DeterministicKey deterministicKey) {
        this.basicKeyChain.importKeys((List<? extends ECKey>) ImmutableList.m127of(deterministicKey));
    }

    public DeterministicKey markKeyAsUsed(DeterministicKey deterministicKey) {
        int i = deterministicKey.getChildNumber().mo46017i() + 1;
        if (deterministicKey.getParent() == this.internalParentKey) {
            if (this.issuedInternalKeys < i) {
                this.issuedInternalKeys = i;
                maybeLookAhead();
            }
        } else if (deterministicKey.getParent() == this.externalParentKey && this.issuedExternalKeys < i) {
            this.issuedExternalKeys = i;
            maybeLookAhead();
        }
        return deterministicKey;
    }

    public DeterministicKey findKeyFromPubHash(byte[] bArr) {
        this.lock.lock();
        try {
            return (DeterministicKey) this.basicKeyChain.findKeyFromPubHash(bArr);
        } finally {
            this.lock.unlock();
        }
    }

    public DeterministicKey findKeyFromPubKey(byte[] bArr) {
        this.lock.lock();
        try {
            return (DeterministicKey) this.basicKeyChain.findKeyFromPubKey(bArr);
        } finally {
            this.lock.unlock();
        }
    }

    @Nullable
    public DeterministicKey markPubHashAsUsed(byte[] bArr) {
        this.lock.lock();
        try {
            DeterministicKey deterministicKey = (DeterministicKey) this.basicKeyChain.findKeyFromPubHash(bArr);
            if (deterministicKey != null) {
                markKeyAsUsed(deterministicKey);
            }
            return deterministicKey;
        } finally {
            this.lock.unlock();
        }
    }

    @Nullable
    public DeterministicKey markPubKeyAsUsed(byte[] bArr) {
        this.lock.lock();
        try {
            DeterministicKey deterministicKey = (DeterministicKey) this.basicKeyChain.findKeyFromPubKey(bArr);
            if (deterministicKey != null) {
                markKeyAsUsed(deterministicKey);
            }
            return deterministicKey;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean hasKey(ECKey eCKey) {
        this.lock.lock();
        try {
            return this.basicKeyChain.hasKey(eCKey);
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public DeterministicKey getKeyByPath(ChildNumber... childNumberArr) {
        return getKeyByPath((List<ChildNumber>) ImmutableList.copyOf((E[]) childNumberArr));
    }

    /* access modifiers changed from: protected */
    public DeterministicKey getKeyByPath(List<ChildNumber> list) {
        return getKeyByPath(list, false);
    }

    public DeterministicKey getKeyByPath(List<ChildNumber> list, boolean z) {
        return this.hierarchy.get(list, false, z);
    }

    public DeterministicKey getWatchingKey() {
        return getKeyByPath((List<ChildNumber>) getAccountPath());
    }

    public boolean isWatching() {
        return getWatchingKey().isWatching();
    }

    public int numKeys() {
        this.lock.lock();
        try {
            maybeLookAhead();
            return this.basicKeyChain.numKeys();
        } finally {
            this.lock.unlock();
        }
    }

    public int numLeafKeysIssued() {
        this.lock.lock();
        try {
            return this.issuedExternalKeys + this.issuedInternalKeys;
        } finally {
            this.lock.unlock();
        }
    }

    public long getEarliestKeyCreationTime() {
        DeterministicSeed deterministicSeed = this.seed;
        if (deterministicSeed != null) {
            return deterministicSeed.getCreationTimeSeconds();
        }
        return getWatchingKey().getCreationTimeSeconds();
    }

    public void addEventListener(KeyChainEventListener keyChainEventListener) {
        this.basicKeyChain.addEventListener(keyChainEventListener);
    }

    public void addEventListener(KeyChainEventListener keyChainEventListener, Executor executor) {
        this.basicKeyChain.addEventListener(keyChainEventListener, executor);
    }

    public boolean removeEventListener(KeyChainEventListener keyChainEventListener) {
        return this.basicKeyChain.removeEventListener(keyChainEventListener);
    }

    @Nullable
    public List<String> getMnemonicCode() {
        if (this.seed == null) {
            return null;
        }
        this.lock.lock();
        try {
            return this.seed.getMnemonicCode();
        } finally {
            this.lock.unlock();
        }
    }

    public boolean isFollowing() {
        return this.isFollowing;
    }

    public List<C3509Key> serializeToProtobuf() {
        ArrayList newArrayList = Lists.newArrayList();
        this.lock.lock();
        try {
            newArrayList.addAll(serializeMyselfToProtobuf());
            return newArrayList;
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public List<C3509Key> serializeMyselfToProtobuf() {
        LinkedList newLinkedList = Lists.newLinkedList();
        DeterministicSeed deterministicSeed = this.seed;
        if (deterministicSeed != null) {
            org.bitcoinj.wallet.Protos.C3509Key.Builder serializeEncryptableItem = BasicKeyChain.serializeEncryptableItem(deterministicSeed);
            serializeEncryptableItem.setType(C3511Type.DETERMINISTIC_MNEMONIC);
            serializeSeedEncryptableItem(this.seed, serializeEncryptableItem);
            newLinkedList.add(serializeEncryptableItem.build());
        }
        for (Entry entry : this.basicKeyChain.serializeToEditableProtobufs().entrySet()) {
            DeterministicKey deterministicKey = (DeterministicKey) entry.getKey();
            org.bitcoinj.wallet.Protos.C3509Key.Builder builder = (org.bitcoinj.wallet.Protos.C3509Key.Builder) entry.getValue();
            builder.setType(C3511Type.DETERMINISTIC_KEY);
            org.bitcoinj.wallet.Protos.DeterministicKey.Builder deterministicKeyBuilder = builder.getDeterministicKeyBuilder();
            deterministicKeyBuilder.setChainCode(ByteString.copyFrom(deterministicKey.getChainCode()));
            UnmodifiableIterator it = deterministicKey.getPath().iterator();
            while (it.hasNext()) {
                deterministicKeyBuilder.addPath(((ChildNumber) it.next()).mo46017i());
            }
            if (deterministicKey.equals(this.externalParentKey)) {
                deterministicKeyBuilder.setIssuedSubkeys(this.issuedExternalKeys);
                deterministicKeyBuilder.setLookaheadSize(this.lookaheadSize);
                deterministicKeyBuilder.setSigsRequiredToSpend(getSigsRequiredToSpend());
            } else if (deterministicKey.equals(this.internalParentKey)) {
                deterministicKeyBuilder.setIssuedSubkeys(this.issuedInternalKeys);
                deterministicKeyBuilder.setLookaheadSize(this.lookaheadSize);
                deterministicKeyBuilder.setSigsRequiredToSpend(getSigsRequiredToSpend());
            }
            if (newLinkedList.isEmpty() && isFollowing()) {
                deterministicKeyBuilder.setIsFollowing(true);
            }
            if (deterministicKey.getParent() != null) {
                builder.clearCreationTimestamp();
            }
            newLinkedList.add(builder.build());
        }
        return newLinkedList;
    }

    static List<DeterministicKeyChain> fromProtobuf(List<C3509Key> list, @Nullable KeyCrypter keyCrypter) throws UnreadableWalletException {
        return fromProtobuf(list, keyCrypter, new DefaultKeyChainFactory());
    }

    /* JADX WARNING: Removed duplicated region for block: B:73:0x0235  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0257  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x02a9  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x02ba  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02c3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<org.bitcoinj.wallet.DeterministicKeyChain> fromProtobuf(java.util.List<org.bitcoinj.wallet.Protos.C3509Key> r28, @javax.annotation.Nullable org.bitcoinj.crypto.KeyCrypter r29, org.bitcoinj.wallet.KeyChainFactory r30) throws org.bitcoinj.wallet.UnreadableWalletException {
        /*
            java.util.LinkedList r0 = com.google.common.collect.Lists.newLinkedList()
            java.util.Iterator r1 = r28.iterator()
            com.google.common.collect.PeekingIterator r1 = com.google.common.collect.Iterators.peekingIterator(r1)
            r2 = -1
            r4 = 1
            r5 = 0
            r6 = -1
            r7 = 1
            r8 = 0
        L_0x0012:
            boolean r9 = r1.hasNext()
            r10 = 0
            if (r9 == 0) goto L_0x0355
            java.lang.Object r9 = r1.next()
            org.bitcoinj.wallet.Protos$Key r9 = (org.bitcoinj.wallet.Protos.C3509Key) r9
            org.bitcoinj.wallet.Protos$Key$Type r11 = r9.getType()
            org.bitcoinj.wallet.Protos$Key$Type r12 = org.bitcoinj.wallet.Protos.C3509Key.C3511Type.DETERMINISTIC_MNEMONIC
            r17 = 1000(0x3e8, double:4.94E-321)
            if (r11 != r12) goto L_0x0125
            if (r5 == 0) goto L_0x003e
            if (r6 < 0) goto L_0x002e
            r10 = 1
        L_0x002e:
            com.google.common.base.Preconditions.checkState(r10)
            r5.setLookaheadSize(r6)
            r5.setSigsRequiredToSpend(r7)
            r5.maybeLookAhead()
            r0.add(r5)
            r5 = 0
        L_0x003e:
            long r10 = r9.getCreationTimestamp()
            long r10 = r10 / r17
            boolean r8 = r9.hasSecretBytes()
            java.lang.String r12 = "Malformed key proto: "
            if (r8 == 0) goto L_0x0092
            boolean r8 = r9.hasEncryptedDeterministicSeed()
            if (r8 != 0) goto L_0x0079
            boolean r8 = r9.hasDeterministicSeed()
            if (r8 == 0) goto L_0x0063
            com.google.protobuf.ByteString r8 = r9.getDeterministicSeed()
            byte[] r8 = r8.toByteArray()
            r21 = r8
            goto L_0x0065
        L_0x0063:
            r21 = 0
        L_0x0065:
            org.bitcoinj.wallet.DeterministicSeed r8 = new org.bitcoinj.wallet.DeterministicSeed
            com.google.protobuf.ByteString r9 = r9.getSecretBytes()
            java.lang.String r20 = r9.toStringUtf8()
            java.lang.String r22 = ""
            r19 = r8
            r23 = r10
            r19.<init>(r20, r21, r22, r23)
            goto L_0x00e2
        L_0x0079:
            org.bitcoinj.wallet.UnreadableWalletException r0 = new org.bitcoinj.wallet.UnreadableWalletException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r12)
            java.lang.String r2 = r9.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0092:
            boolean r8 = r9.hasEncryptedData()
            if (r8 == 0) goto L_0x010c
            boolean r8 = r9.hasDeterministicSeed()
            if (r8 != 0) goto L_0x00f3
            org.bitcoinj.crypto.EncryptedData r8 = new org.bitcoinj.crypto.EncryptedData
            org.bitcoinj.wallet.Protos$EncryptedData r12 = r9.getEncryptedData()
            com.google.protobuf.ByteString r12 = r12.getInitialisationVector()
            byte[] r12 = r12.toByteArray()
            org.bitcoinj.wallet.Protos$EncryptedData r13 = r9.getEncryptedData()
            com.google.protobuf.ByteString r13 = r13.getEncryptedPrivateKey()
            byte[] r13 = r13.toByteArray()
            r8.<init>(r12, r13)
            boolean r12 = r9.hasEncryptedDeterministicSeed()
            if (r12 == 0) goto L_0x00db
            org.bitcoinj.wallet.Protos$EncryptedData r9 = r9.getEncryptedDeterministicSeed()
            org.bitcoinj.crypto.EncryptedData r12 = new org.bitcoinj.crypto.EncryptedData
            com.google.protobuf.ByteString r13 = r9.getInitialisationVector()
            byte[] r13 = r13.toByteArray()
            com.google.protobuf.ByteString r9 = r9.getEncryptedPrivateKey()
            byte[] r9 = r9.toByteArray()
            r12.<init>(r13, r9)
            goto L_0x00dc
        L_0x00db:
            r12 = 0
        L_0x00dc:
            org.bitcoinj.wallet.DeterministicSeed r9 = new org.bitcoinj.wallet.DeterministicSeed
            r9.<init>(r8, r12, r10)
            r8 = r9
        L_0x00e2:
            org.slf4j.Logger r9 = log
            boolean r9 = r9.isDebugEnabled()
            if (r9 == 0) goto L_0x0350
            org.slf4j.Logger r9 = log
            java.lang.String r10 = "Deserializing: DETERMINISTIC_MNEMONIC: {}"
            r9.debug(r10, r8)
            goto L_0x0350
        L_0x00f3:
            org.bitcoinj.wallet.UnreadableWalletException r0 = new org.bitcoinj.wallet.UnreadableWalletException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r12)
            java.lang.String r2 = r9.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x010c:
            org.bitcoinj.wallet.UnreadableWalletException r0 = new org.bitcoinj.wallet.UnreadableWalletException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r12)
            java.lang.String r2 = r9.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0125:
            org.bitcoinj.wallet.Protos$Key$Type r12 = org.bitcoinj.wallet.Protos.C3509Key.C3511Type.DETERMINISTIC_KEY
            if (r11 != r12) goto L_0x0350
            boolean r11 = r9.hasDeterministicKey()
            if (r11 == 0) goto L_0x0335
            org.bitcoinj.wallet.Protos$DeterministicKey r11 = r9.getDeterministicKey()
            com.google.protobuf.ByteString r11 = r11.getChainCode()
            byte[] r25 = r11.toByteArray()
            java.util.LinkedList r15 = com.google.common.collect.Lists.newLinkedList()
            org.bitcoinj.wallet.Protos$DeterministicKey r11 = r9.getDeterministicKey()
            java.util.List r11 = r11.getPathList()
            java.util.Iterator r11 = r11.iterator()
        L_0x014b:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x0164
            java.lang.Object r12 = r11.next()
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            org.bitcoinj.crypto.ChildNumber r13 = new org.bitcoinj.crypto.ChildNumber
            r13.<init>(r12)
            r15.add(r13)
            goto L_0x014b
        L_0x0164:
            org.bitcoinj.crypto.LazyECPoint r14 = new org.bitcoinj.crypto.LazyECPoint
            org.spongycastle.crypto.params.ECDomainParameters r11 = org.bitcoinj.core.ECKey.CURVE
            org.spongycastle.math.ec.ECCurve r11 = r11.getCurve()
            com.google.protobuf.ByteString r12 = r9.getPublicKey()
            byte[] r12 = r12.toByteArray()
            r14.<init>(r11, r12)
            com.google.common.collect.ImmutableList r26 = com.google.common.collect.ImmutableList.copyOf(r15)
            org.bitcoinj.wallet.Protos$DeterministicKey r11 = r9.getDeterministicKey()
            boolean r11 = r11.getIsFollowing()
            if (r11 == 0) goto L_0x01a1
            if (r5 == 0) goto L_0x019d
            if (r6 < 0) goto L_0x018b
            r8 = 1
            goto L_0x018c
        L_0x018b:
            r8 = 0
        L_0x018c:
            com.google.common.base.Preconditions.checkState(r8)
            r5.setLookaheadSize(r6)
            r5.setSigsRequiredToSpend(r7)
            r5.maybeLookAhead()
            r0.add(r5)
            r5 = 0
            r8 = 0
        L_0x019d:
            r27 = r8
            r8 = 1
            goto L_0x01a4
        L_0x01a1:
            r27 = r8
            r8 = 0
        L_0x01a4:
            if (r5 != 0) goto L_0x0211
            if (r8 != 0) goto L_0x01c2
            boolean r5 = r0.isEmpty()
            if (r5 != 0) goto L_0x01c2
            int r5 = r0.size()
            int r5 = r5 - r4
            java.lang.Object r5 = r0.get(r5)
            org.bitcoinj.wallet.DeterministicKeyChain r5 = (org.bitcoinj.wallet.DeterministicKeyChain) r5
            boolean r5 = r5.isFollowing()
            if (r5 == 0) goto L_0x01c2
            r16 = 1
            goto L_0x01c4
        L_0x01c2:
            r16 = 0
        L_0x01c4:
            if (r27 != 0) goto L_0x01f7
            org.bitcoinj.crypto.DeterministicKey r5 = new org.bitcoinj.crypto.DeterministicKey
            r23 = 0
            r24 = 0
            r19 = r5
            r20 = r26
            r21 = r25
            r22 = r14
            r19.<init>(r20, r21, r22, r23, r24)
            long r11 = r9.getCreationTimestamp()
            long r11 = r11 / r17
            r5.setCreationTimeSeconds(r11)
            java.lang.Object r11 = r1.peek()
            r13 = r11
            org.bitcoinj.wallet.Protos$Key r13 = (org.bitcoinj.wallet.Protos.C3509Key) r13
            r11 = r30
            r12 = r9
            r23 = r14
            r14 = r5
            r5 = r15
            r15 = r8
            org.bitcoinj.wallet.DeterministicKeyChain r8 = r11.makeWatchingKeyChain(r12, r13, r14, r15, r16)
            r11 = r8
            r8 = r5
            r5 = 1
            goto L_0x0216
        L_0x01f7:
            r23 = r14
            r5 = r15
            java.lang.Object r8 = r1.peek()
            r13 = r8
            org.bitcoinj.wallet.Protos$Key r13 = (org.bitcoinj.wallet.Protos.C3509Key) r13
            r11 = r30
            r12 = r9
            r14 = r27
            r15 = r29
            org.bitcoinj.wallet.DeterministicKeyChain r8 = r11.makeKeyChain(r12, r13, r14, r15, r16)
            r8.lookaheadSize = r2
            r11 = r8
            r8 = r5
            goto L_0x0215
        L_0x0211:
            r23 = r14
            r8 = r15
            r11 = r5
        L_0x0215:
            r5 = 0
        L_0x0216:
            boolean r12 = r8.isEmpty()
            if (r12 != 0) goto L_0x022e
            if (r5 != 0) goto L_0x022e
            java.lang.Object r12 = r8.removeLast()
            org.bitcoinj.crypto.ChildNumber r12 = (org.bitcoinj.crypto.ChildNumber) r12
            org.bitcoinj.crypto.DeterministicHierarchy r13 = r11.hierarchy
            org.bitcoinj.crypto.DeterministicKey r10 = r13.get(r8, r10, r10)
            r8.add(r12)
            goto L_0x022f
        L_0x022e:
            r10 = 0
        L_0x022f:
            boolean r12 = r9.hasSecretBytes()
            if (r12 == 0) goto L_0x0257
            java.math.BigInteger r12 = new java.math.BigInteger
            com.google.protobuf.ByteString r13 = r9.getSecretBytes()
            byte[] r13 = r13.toByteArray()
            r12.<init>(r4, r13)
            org.bitcoinj.crypto.DeterministicKey r13 = new org.bitcoinj.crypto.DeterministicKey
            r19 = r13
            r20 = r26
            r21 = r25
            r22 = r23
            r23 = r12
            r24 = r10
            r19.<init>(r20, r21, r22, r23, r24)
            r14 = r29
            r12 = r13
            goto L_0x02a3
        L_0x0257:
            boolean r12 = r9.hasEncryptedData()
            if (r12 == 0) goto L_0x028f
            org.bitcoinj.wallet.Protos$EncryptedData r12 = r9.getEncryptedData()
            org.bitcoinj.crypto.EncryptedData r13 = new org.bitcoinj.crypto.EncryptedData
            com.google.protobuf.ByteString r14 = r12.getInitialisationVector()
            byte[] r14 = r14.toByteArray()
            com.google.protobuf.ByteString r12 = r12.getEncryptedPrivateKey()
            byte[] r12 = r12.toByteArray()
            r13.<init>(r14, r12)
            java.lang.String r12 = "Encountered an encrypted key but no key crypter provided"
            r14 = r29
            com.google.common.base.Preconditions.checkNotNull(r14, r12)
            org.bitcoinj.crypto.DeterministicKey r12 = new org.bitcoinj.crypto.DeterministicKey
            r19 = r12
            r20 = r26
            r21 = r25
            r22 = r29
            r24 = r13
            r25 = r10
            r19.<init>(r20, r21, r22, r23, r24, r25)
            goto L_0x02a3
        L_0x028f:
            r14 = r29
            org.bitcoinj.crypto.DeterministicKey r12 = new org.bitcoinj.crypto.DeterministicKey
            r13 = 0
            r19 = r12
            r20 = r26
            r21 = r25
            r22 = r23
            r23 = r13
            r24 = r10
            r19.<init>(r20, r21, r22, r23, r24)
        L_0x02a3:
            boolean r10 = r9.hasCreationTimestamp()
            if (r10 == 0) goto L_0x02b2
            long r15 = r9.getCreationTimestamp()
            long r2 = r15 / r17
            r12.setCreationTimeSeconds(r2)
        L_0x02b2:
            org.slf4j.Logger r2 = log
            boolean r2 = r2.isDebugEnabled()
            if (r2 == 0) goto L_0x02c1
            org.slf4j.Logger r2 = log
            java.lang.String r3 = "Deserializing: DETERMINISTIC_KEY: {}"
            r2.debug(r3, r12)
        L_0x02c1:
            if (r5 != 0) goto L_0x0327
            int r2 = r8.size()
            if (r2 != 0) goto L_0x02d7
            org.bitcoinj.crypto.DeterministicKey r2 = r11.rootKey
            if (r2 != 0) goto L_0x0327
            r11.rootKey = r12
            org.bitcoinj.crypto.DeterministicHierarchy r2 = new org.bitcoinj.crypto.DeterministicHierarchy
            r2.<init>(r12)
            r11.hierarchy = r2
            goto L_0x0327
        L_0x02d7:
            int r2 = r8.size()
            com.google.common.collect.ImmutableList r3 = r11.getAccountPath()
            int r3 = r3.size()
            int r3 = r3 + r4
            if (r2 != r3) goto L_0x0327
            org.bitcoinj.crypto.ChildNumber r2 = r12.getChildNumber()
            int r2 = r2.num()
            if (r2 != 0) goto L_0x0311
            r11.externalParentKey = r12
            org.bitcoinj.wallet.Protos$DeterministicKey r2 = r9.getDeterministicKey()
            int r2 = r2.getIssuedSubkeys()
            r11.issuedExternalKeys = r2
            org.bitcoinj.wallet.Protos$DeterministicKey r2 = r9.getDeterministicKey()
            int r2 = r2.getLookaheadSize()
            int r6 = java.lang.Math.max(r6, r2)
            org.bitcoinj.wallet.Protos$DeterministicKey r2 = r9.getDeterministicKey()
            int r7 = r2.getSigsRequiredToSpend()
            goto L_0x0327
        L_0x0311:
            org.bitcoinj.crypto.ChildNumber r2 = r12.getChildNumber()
            int r2 = r2.num()
            if (r2 != r4) goto L_0x0327
            r11.internalParentKey = r12
            org.bitcoinj.wallet.Protos$DeterministicKey r2 = r9.getDeterministicKey()
            int r2 = r2.getIssuedSubkeys()
            r11.issuedInternalKeys = r2
        L_0x0327:
            org.bitcoinj.crypto.DeterministicHierarchy r2 = r11.hierarchy
            r2.putKey(r12)
            org.bitcoinj.wallet.BasicKeyChain r2 = r11.basicKeyChain
            r2.importKey(r12)
            r5 = r11
            r8 = r27
            goto L_0x0352
        L_0x0335:
            org.bitcoinj.wallet.UnreadableWalletException r0 = new org.bitcoinj.wallet.UnreadableWalletException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Deterministic key missing extra data: "
            r1.append(r2)
            java.lang.String r2 = r9.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0350:
            r14 = r29
        L_0x0352:
            r2 = -1
            goto L_0x0012
        L_0x0355:
            if (r5 == 0) goto L_0x036a
            if (r6 < 0) goto L_0x035a
            goto L_0x035b
        L_0x035a:
            r4 = 0
        L_0x035b:
            com.google.common.base.Preconditions.checkState(r4)
            r5.setLookaheadSize(r6)
            r5.setSigsRequiredToSpend(r7)
            r5.maybeLookAhead()
            r0.add(r5)
        L_0x036a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.wallet.DeterministicKeyChain.fromProtobuf(java.util.List, org.bitcoinj.crypto.KeyCrypter, org.bitcoinj.wallet.KeyChainFactory):java.util.List");
    }

    public DeterministicKeyChain toEncrypted(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        boolean z = false;
        Preconditions.checkArgument(charSequence.length() > 0);
        if (this.seed != null) {
            z = true;
        }
        Preconditions.checkState(z, "Attempt to encrypt a watching chain.");
        Preconditions.checkState(!this.seed.isEncrypted());
        KeyCrypterScrypt keyCrypterScrypt = new KeyCrypterScrypt();
        return toEncrypted((KeyCrypter) keyCrypterScrypt, keyCrypterScrypt.deriveKey(charSequence));
    }

    public DeterministicKeyChain toEncrypted(KeyCrypter keyCrypter, KeyParameter keyParameter) {
        return new DeterministicKeyChain(keyCrypter, keyParameter, this);
    }

    public DeterministicKeyChain toDecrypted(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        boolean z = true;
        Preconditions.checkArgument(charSequence.length() > 0);
        KeyCrypter keyCrypter = getKeyCrypter();
        if (keyCrypter == null) {
            z = false;
        }
        Preconditions.checkState(z, "Chain not encrypted");
        return toDecrypted(keyCrypter.deriveKey(charSequence));
    }

    public DeterministicKeyChain toDecrypted(KeyParameter keyParameter) {
        boolean z = true;
        Preconditions.checkState(getKeyCrypter() != null, "Key chain not encrypted");
        if (this.seed == null) {
            z = false;
        }
        Preconditions.checkState(z, "Can't decrypt a watching chain");
        Preconditions.checkState(this.seed.isEncrypted());
        DeterministicKeyChain makeKeyChainFromSeed = makeKeyChainFromSeed(this.seed.decrypt(getKeyCrypter(), "", keyParameter));
        if (makeKeyChainFromSeed.getWatchingKey().getPubKeyPoint().equals(getWatchingKey().getPubKeyPoint())) {
            makeKeyChainFromSeed.lookaheadSize = this.lookaheadSize;
            for (ECKey eCKey : this.basicKeyChain.getKeys()) {
                DeterministicKey deterministicKey = (DeterministicKey) eCKey;
                if (deterministicKey.getPath().size() == getAccountPath().size() + 2) {
                    Preconditions.checkState(deterministicKey.isEncrypted());
                    DeterministicKey deterministicKey2 = new DeterministicKey(deterministicKey.dropPrivateBytes(), makeKeyChainFromSeed.hierarchy.get(((DeterministicKey) Preconditions.checkNotNull(deterministicKey.getParent())).getPath(), false, false));
                    makeKeyChainFromSeed.hierarchy.putKey(deterministicKey2);
                    makeKeyChainFromSeed.basicKeyChain.importKey(deterministicKey2);
                }
            }
            makeKeyChainFromSeed.issuedExternalKeys = this.issuedExternalKeys;
            makeKeyChainFromSeed.issuedInternalKeys = this.issuedInternalKeys;
            return makeKeyChainFromSeed;
        }
        throw new KeyCrypterException("Provided AES key is wrong");
    }

    /* access modifiers changed from: protected */
    public DeterministicKeyChain makeKeyChainFromSeed(DeterministicSeed deterministicSeed) {
        return new DeterministicKeyChain(deterministicSeed);
    }

    public boolean checkPassword(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkState(getKeyCrypter() != null, "Key chain not encrypted");
        return checkAESKey(getKeyCrypter().deriveKey(charSequence));
    }

    public boolean checkAESKey(KeyParameter keyParameter) {
        boolean z = true;
        Preconditions.checkState(this.rootKey != null, "Can't check password for a watching chain");
        Preconditions.checkNotNull(keyParameter);
        if (getKeyCrypter() == null) {
            z = false;
        }
        Preconditions.checkState(z, "Key chain not encrypted");
        try {
            return this.rootKey.decrypt(keyParameter).getPubKeyPoint().equals(this.rootKey.getPubKeyPoint());
        } catch (KeyCrypterException unused) {
            return false;
        }
    }

    @Nullable
    public KeyCrypter getKeyCrypter() {
        return this.basicKeyChain.getKeyCrypter();
    }

    public int numBloomFilterEntries() {
        return numKeys() * 2;
    }

    public BloomFilter getFilter(int i, double d, long j) {
        this.lock.lock();
        try {
            Preconditions.checkArgument(i >= numBloomFilterEntries());
            maybeLookAhead();
            BloomFilter filter = this.basicKeyChain.getFilter(i, d, j);
            return filter;
        } finally {
            this.lock.unlock();
        }
    }

    public int getLookaheadSize() {
        this.lock.lock();
        try {
            return this.lookaheadSize;
        } finally {
            this.lock.unlock();
        }
    }

    public void setLookaheadSize(int i) {
        this.lock.lock();
        try {
            boolean z = this.lookaheadThreshold == calcDefaultLookaheadThreshold();
            this.lookaheadSize = i;
            if (z) {
                this.lookaheadThreshold = calcDefaultLookaheadThreshold();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void setLookaheadThreshold(int i) {
        this.lock.lock();
        try {
            if (i < this.lookaheadSize) {
                this.lookaheadThreshold = i;
                return;
            }
            throw new IllegalArgumentException("Threshold larger or equal to the lookaheadSize");
        } finally {
            this.lock.unlock();
        }
    }

    public int getLookaheadThreshold() {
        int i;
        this.lock.lock();
        try {
            if (this.lookaheadThreshold >= this.lookaheadSize) {
                i = 0;
            } else {
                i = this.lookaheadThreshold;
            }
            return i;
        } finally {
            this.lock.unlock();
        }
    }

    public void maybeLookAhead() {
        this.lock.lock();
        try {
            List maybeLookAhead = maybeLookAhead(this.externalParentKey, this.issuedExternalKeys);
            maybeLookAhead.addAll(maybeLookAhead(this.internalParentKey, this.issuedInternalKeys));
            if (!maybeLookAhead.isEmpty()) {
                this.keyLookaheadEpoch++;
                this.basicKeyChain.importKeys(maybeLookAhead);
                this.lock.unlock();
            }
        } finally {
            this.lock.unlock();
        }
    }

    private List<DeterministicKey> maybeLookAhead(DeterministicKey deterministicKey, int i) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        return maybeLookAhead(deterministicKey, i, getLookaheadSize(), getLookaheadThreshold());
    }

    private List<DeterministicKey> maybeLookAhead(DeterministicKey deterministicKey, int i, int i2, int i3) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        int numChildren = this.hierarchy.getNumChildren(deterministicKey.getPath());
        int i4 = ((i + i2) + i3) - numChildren;
        if (i4 <= i3) {
            return new ArrayList();
        }
        log.info("{} keys needed for {} = {} issued + {} lookahead size + {} lookahead threshold - {} num children", Integer.valueOf(i4), deterministicKey.getPathAsString(), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(numChildren));
        ArrayList arrayList = new ArrayList(i4);
        Stopwatch createStarted = Stopwatch.createStarted();
        for (int i5 = 0; i5 < i4; i5++) {
            DeterministicKey dropPrivateBytes = HDKeyDerivation.deriveThisOrNextChildKey(deterministicKey, numChildren).dropPrivateBytes();
            this.hierarchy.putKey(dropPrivateBytes);
            arrayList.add(dropPrivateBytes);
            numChildren = dropPrivateBytes.getChildNumber().num() + 1;
        }
        createStarted.stop();
        log.info("Took {}", (Object) createStarted);
        return arrayList;
    }

    public int getIssuedExternalKeys() {
        this.lock.lock();
        try {
            return this.issuedExternalKeys;
        } finally {
            this.lock.unlock();
        }
    }

    public int getIssuedInternalKeys() {
        this.lock.lock();
        try {
            return this.issuedInternalKeys;
        } finally {
            this.lock.unlock();
        }
    }

    @Nullable
    public DeterministicSeed getSeed() {
        this.lock.lock();
        try {
            return this.seed;
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public List<ECKey> getKeys(boolean z) {
        List<ECKey> keys = this.basicKeyChain.getKeys();
        if (z) {
            return keys;
        }
        int size = this.internalParentKey.getPath().size();
        LinkedList linkedList = new LinkedList();
        for (ECKey eCKey : keys) {
            DeterministicKey deterministicKey = (DeterministicKey) eCKey;
            DeterministicKey parent = deterministicKey.getParent();
            if (parent != null && deterministicKey.getPath().size() > size) {
                if ((!parent.equals(this.internalParentKey) || deterministicKey.getChildNumber().mo46017i() < this.issuedInternalKeys) && (!parent.equals(this.externalParentKey) || deterministicKey.getChildNumber().mo46017i() < this.issuedExternalKeys)) {
                    linkedList.add(deterministicKey);
                }
            }
        }
        return linkedList;
    }

    public List<ECKey> getIssuedReceiveKeys() {
        ArrayList arrayList = new ArrayList(getKeys(false));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            DeterministicKey parent = ((DeterministicKey) it.next()).getParent();
            if (parent == null || !this.externalParentKey.equals(parent)) {
                it.remove();
            }
        }
        return arrayList;
    }

    public List<DeterministicKey> getLeafKeys() {
        com.google.common.collect.ImmutableList.Builder builder = ImmutableList.builder();
        for (ECKey eCKey : getKeys(true)) {
            DeterministicKey deterministicKey = (DeterministicKey) eCKey;
            if (deterministicKey.getPath().size() == getAccountPath().size() + 2) {
                builder.add((Object) deterministicKey);
            }
        }
        return builder.build();
    }

    static void serializeSeedEncryptableItem(DeterministicSeed deterministicSeed, org.bitcoinj.wallet.Protos.C3509Key.Builder builder) {
        if (!deterministicSeed.isEncrypted() || deterministicSeed.getEncryptedSeedData() == null) {
            byte[] seedBytes = deterministicSeed.getSeedBytes();
            if (seedBytes != null) {
                builder.setDeterministicSeed(ByteString.copyFrom(seedBytes));
                return;
            }
            return;
        }
        EncryptedData encryptedSeedData = deterministicSeed.getEncryptedSeedData();
        builder.getEncryptedDeterministicSeedBuilder().setEncryptedPrivateKey(ByteString.copyFrom(encryptedSeedData.encryptedBytes)).setInitialisationVector(ByteString.copyFrom(encryptedSeedData.initialisationVector));
        Preconditions.checkState(deterministicSeed.getEncryptionType() == EncryptionType.ENCRYPTED_SCRYPT_AES);
    }

    public int getKeyLookaheadEpoch() {
        this.lock.lock();
        try {
            return this.keyLookaheadEpoch;
        } finally {
            this.lock.unlock();
        }
    }

    public RedeemData getRedeemData(DeterministicKey deterministicKey) {
        throw new UnsupportedOperationException();
    }

    public Script freshOutputScript(KeyPurpose keyPurpose) {
        throw new UnsupportedOperationException();
    }

    public String toString(boolean z, NetworkParameters networkParameters) {
        DeterministicKey watchingKey = getWatchingKey();
        StringBuilder sb = new StringBuilder();
        DeterministicSeed deterministicSeed = this.seed;
        String str = "]\n";
        String str2 = "  [";
        if (deterministicSeed != null) {
            if (deterministicSeed.isEncrypted()) {
                sb.append("Seed is encrypted\n");
            } else if (z) {
                List mnemonicCode = this.seed.getMnemonicCode();
                sb.append("Seed as words: ");
                sb.append(C3447Utils.join(mnemonicCode));
                sb.append(10);
                sb.append("Seed as hex:   ");
                sb.append(this.seed.toHexString());
                sb.append(10);
            }
            sb.append("Seed birthday: ");
            sb.append(this.seed.getCreationTimeSeconds());
            sb.append(str2);
            sb.append(C3447Utils.dateTimeFormat(this.seed.getCreationTimeSeconds() * 1000));
            sb.append(str);
        } else {
            sb.append("Key birthday:  ");
            sb.append(watchingKey.getCreationTimeSeconds());
            sb.append(str2);
            sb.append(C3447Utils.dateTimeFormat(watchingKey.getCreationTimeSeconds() * 1000));
            sb.append(str);
        }
        sb.append("Key to watch:  ");
        sb.append(watchingKey.serializePubB58(networkParameters));
        sb.append(10);
        formatAddresses(z, networkParameters, sb);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void formatAddresses(boolean z, NetworkParameters networkParameters, StringBuilder sb) {
        for (ECKey formatKeyWithAddress : getKeys(false)) {
            formatKeyWithAddress.formatKeyWithAddress(z, sb, networkParameters);
        }
    }

    public void setSigsRequiredToSpend(int i) {
        this.sigsRequiredToSpend = i;
    }

    public int getSigsRequiredToSpend() {
        return this.sigsRequiredToSpend;
    }
}
