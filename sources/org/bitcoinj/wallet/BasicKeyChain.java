package org.bitcoinj.wallet;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import org.bitcoinj.core.BloomFilter;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.EncryptableItem;
import org.bitcoinj.crypto.EncryptedData;
import org.bitcoinj.crypto.KeyCrypter;
import org.bitcoinj.crypto.KeyCrypterException;
import org.bitcoinj.crypto.KeyCrypterScrypt;
import org.bitcoinj.utils.ListenerRegistration;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.KeyChain.KeyPurpose;
import org.bitcoinj.wallet.Protos.C3509Key;
import org.bitcoinj.wallet.Protos.C3509Key.Builder;
import org.bitcoinj.wallet.Protos.C3509Key.C3511Type;
import org.bitcoinj.wallet.Protos.C3527Wallet.EncryptionType;
import org.bitcoinj.wallet.listeners.KeyChainEventListener;
import org.spongycastle.crypto.params.KeyParameter;

public class BasicKeyChain implements EncryptableKeyChain {
    private final LinkedHashMap<ByteString, ECKey> hashToKeys;
    private boolean isWatching;
    @Nullable
    private final KeyCrypter keyCrypter;
    private final CopyOnWriteArrayList<ListenerRegistration<KeyChainEventListener>> listeners;
    private final ReentrantLock lock;
    private final LinkedHashMap<ByteString, ECKey> pubkeyToKeys;

    public enum State {
        EMPTY,
        WATCHING,
        REGULAR
    }

    public BasicKeyChain() {
        this(null);
    }

    public BasicKeyChain(@Nullable KeyCrypter keyCrypter2) {
        this.lock = Threading.lock("BasicKeyChain");
        this.keyCrypter = keyCrypter2;
        this.hashToKeys = new LinkedHashMap<>();
        this.pubkeyToKeys = new LinkedHashMap<>();
        this.listeners = new CopyOnWriteArrayList<>();
    }

    @Nullable
    public KeyCrypter getKeyCrypter() {
        this.lock.lock();
        try {
            return this.keyCrypter;
        } finally {
            this.lock.unlock();
        }
    }

    public ECKey getKey(@Nullable KeyPurpose keyPurpose) {
        this.lock.lock();
        try {
            if (this.hashToKeys.isEmpty()) {
                Preconditions.checkState(this.keyCrypter == null);
                ECKey eCKey = new ECKey();
                importKeyLocked(eCKey);
                queueOnKeysAdded(ImmutableList.m127of(eCKey));
            }
            return (ECKey) this.hashToKeys.values().iterator().next();
        } finally {
            this.lock.unlock();
        }
    }

    public List<ECKey> getKeys(@Nullable KeyPurpose keyPurpose, int i) {
        boolean z = true;
        int i2 = 0;
        Preconditions.checkArgument(i > 0);
        this.lock.lock();
        try {
            if (this.hashToKeys.size() < i) {
                if (this.keyCrypter != null) {
                    z = false;
                }
                Preconditions.checkState(z);
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < i - this.hashToKeys.size(); i3++) {
                    arrayList.add(new ECKey());
                }
                ImmutableList copyOf = ImmutableList.copyOf((Collection<? extends E>) arrayList);
                importKeysLocked(copyOf);
                queueOnKeysAdded(copyOf);
            }
            ArrayList arrayList2 = new ArrayList();
            while (this.hashToKeys.values().iterator().hasNext() && i != i2) {
                arrayList2.add(this.hashToKeys.values().iterator().next());
                i2++;
            }
            return arrayList2;
        } finally {
            this.lock.unlock();
        }
    }

    public List<ECKey> getKeys() {
        this.lock.lock();
        try {
            return new ArrayList(this.hashToKeys.values());
        } finally {
            this.lock.unlock();
        }
    }

    public int importKeys(ECKey... eCKeyArr) {
        return importKeys((List<? extends ECKey>) ImmutableList.copyOf((E[]) eCKeyArr));
    }

    public int importKeys(List<? extends ECKey> list) {
        this.lock.lock();
        try {
            for (ECKey checkKeyEncryptionStateMatches : list) {
                checkKeyEncryptionStateMatches(checkKeyEncryptionStateMatches);
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (ECKey eCKey : list) {
                if (!hasKey(eCKey)) {
                    arrayList.add(eCKey);
                    importKeyLocked(eCKey);
                }
            }
            if (arrayList.size() > 0) {
                queueOnKeysAdded(arrayList);
            }
            int size = arrayList.size();
            return size;
        } finally {
            this.lock.unlock();
        }
    }

    private void checkKeyEncryptionStateMatches(ECKey eCKey) {
        if (this.keyCrypter == null && eCKey.isEncrypted()) {
            throw new KeyCrypterException("Key is encrypted but chain is not");
        } else if (this.keyCrypter != null && !eCKey.isEncrypted()) {
            throw new KeyCrypterException("Key is not encrypted but chain is");
        } else if (this.keyCrypter != null && eCKey.getKeyCrypter() != null && !eCKey.getKeyCrypter().equals(this.keyCrypter)) {
            throw new KeyCrypterException("Key encrypted under different parameters to chain");
        }
    }

    private void importKeyLocked(ECKey eCKey) {
        if (this.hashToKeys.isEmpty()) {
            this.isWatching = eCKey.isWatching();
        } else if (eCKey.isWatching() && !this.isWatching) {
            throw new IllegalArgumentException("Key is watching but chain is not");
        } else if (!eCKey.isWatching() && this.isWatching) {
            throw new IllegalArgumentException("Key is not watching but chain is");
        }
        ECKey eCKey2 = (ECKey) this.pubkeyToKeys.put(ByteString.copyFrom(eCKey.getPubKey()), eCKey);
        this.hashToKeys.put(ByteString.copyFrom(eCKey.getPubKeyHash()), eCKey);
        Preconditions.checkState(eCKey2 == null);
    }

    private void importKeysLocked(List<ECKey> list) {
        for (ECKey importKeyLocked : list) {
            importKeyLocked(importKeyLocked);
        }
    }

    public void importKey(ECKey eCKey) {
        this.lock.lock();
        try {
            checkKeyEncryptionStateMatches(eCKey);
            if (!hasKey(eCKey)) {
                importKeyLocked(eCKey);
                queueOnKeysAdded(ImmutableList.m127of(eCKey));
                this.lock.unlock();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public ECKey findKeyFromPubHash(byte[] bArr) {
        this.lock.lock();
        try {
            return (ECKey) this.hashToKeys.get(ByteString.copyFrom(bArr));
        } finally {
            this.lock.unlock();
        }
    }

    public ECKey findKeyFromPubKey(byte[] bArr) {
        this.lock.lock();
        try {
            return (ECKey) this.pubkeyToKeys.get(ByteString.copyFrom(bArr));
        } finally {
            this.lock.unlock();
        }
    }

    public boolean hasKey(ECKey eCKey) {
        return findKeyFromPubKey(eCKey.getPubKey()) != null;
    }

    public int numKeys() {
        return this.pubkeyToKeys.size();
    }

    public State isWatching() {
        this.lock.lock();
        try {
            State state = this.hashToKeys.isEmpty() ? State.EMPTY : this.isWatching ? State.WATCHING : State.REGULAR;
            return state;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean removeKey(ECKey eCKey) {
        this.lock.lock();
        try {
            boolean z = true;
            boolean z2 = this.hashToKeys.remove(ByteString.copyFrom(eCKey.getPubKeyHash())) != null;
            if (z2 != (this.pubkeyToKeys.remove(ByteString.copyFrom(eCKey.getPubKey())) != null)) {
                z = false;
            }
            Preconditions.checkState(z);
            return z2;
        } finally {
            this.lock.unlock();
        }
    }

    public long getEarliestKeyCreationTime() {
        this.lock.lock();
        long j = Long.MAX_VALUE;
        try {
            for (ECKey creationTimeSeconds : this.hashToKeys.values()) {
                j = Math.min(creationTimeSeconds.getCreationTimeSeconds(), j);
            }
            return j;
        } finally {
            this.lock.unlock();
        }
    }

    public List<ListenerRegistration<KeyChainEventListener>> getListeners() {
        return new ArrayList(this.listeners);
    }

    /* access modifiers changed from: 0000 */
    public Map<ECKey, Builder> serializeToEditableProtobufs() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ECKey eCKey : this.hashToKeys.values()) {
            Builder serializeEncryptableItem = serializeEncryptableItem(eCKey);
            serializeEncryptableItem.setPublicKey(ByteString.copyFrom(eCKey.getPubKey()));
            linkedHashMap.put(eCKey, serializeEncryptableItem);
        }
        return linkedHashMap;
    }

    public List<C3509Key> serializeToProtobuf() {
        Collection<Builder> values = serializeToEditableProtobufs().values();
        ArrayList arrayList = new ArrayList(values.size());
        for (Builder build : values) {
            arrayList.add(build.build());
        }
        return arrayList;
    }

    static Builder serializeEncryptableItem(EncryptableItem encryptableItem) {
        Builder newBuilder = C3509Key.newBuilder();
        newBuilder.setCreationTimestamp(encryptableItem.getCreationTimeSeconds() * 1000);
        if (!encryptableItem.isEncrypted() || encryptableItem.getEncryptedData() == null) {
            byte[] secretBytes = encryptableItem.getSecretBytes();
            if (secretBytes != null) {
                newBuilder.setSecretBytes(ByteString.copyFrom(secretBytes));
            }
            newBuilder.setType(C3511Type.ORIGINAL);
        } else {
            EncryptedData encryptedData = encryptableItem.getEncryptedData();
            newBuilder.getEncryptedDataBuilder().setEncryptedPrivateKey(ByteString.copyFrom(encryptedData.encryptedBytes)).setInitialisationVector(ByteString.copyFrom(encryptedData.initialisationVector));
            Preconditions.checkState(encryptableItem.getEncryptionType() == EncryptionType.ENCRYPTED_SCRYPT_AES);
            newBuilder.setType(C3511Type.ENCRYPTED_SCRYPT_AES);
        }
        return newBuilder;
    }

    public static BasicKeyChain fromProtobufUnencrypted(List<C3509Key> list) throws UnreadableWalletException {
        BasicKeyChain basicKeyChain = new BasicKeyChain();
        basicKeyChain.deserializeFromProtobuf(list);
        return basicKeyChain;
    }

    public static BasicKeyChain fromProtobufEncrypted(List<C3509Key> list, KeyCrypter keyCrypter2) throws UnreadableWalletException {
        BasicKeyChain basicKeyChain = new BasicKeyChain((KeyCrypter) Preconditions.checkNotNull(keyCrypter2));
        basicKeyChain.deserializeFromProtobuf(list);
        return basicKeyChain;
    }

    private void deserializeFromProtobuf(List<C3509Key> list) throws UnreadableWalletException {
        ECKey eCKey;
        this.lock.lock();
        try {
            Preconditions.checkState(this.hashToKeys.isEmpty(), "Tried to deserialize into a non-empty chain");
            for (C3509Key key : list) {
                if (key.getType() == C3511Type.ORIGINAL || key.getType() == C3511Type.ENCRYPTED_SCRYPT_AES) {
                    boolean z = true;
                    boolean z2 = key.getType() == C3511Type.ENCRYPTED_SCRYPT_AES;
                    byte[] byteArray = key.hasSecretBytes() ? key.getSecretBytes().toByteArray() : null;
                    if (key.hasPublicKey()) {
                        byte[] byteArray2 = key.getPublicKey().toByteArray();
                        if (z2) {
                            if (this.keyCrypter == null) {
                                z = false;
                            }
                            Preconditions.checkState(z, "This wallet is encrypted but encrypt() was not called prior to deserialization");
                            if (key.hasEncryptedData()) {
                                Protos.EncryptedData encryptedData = key.getEncryptedData();
                                eCKey = ECKey.fromEncrypted(new EncryptedData(encryptedData.getInitialisationVector().toByteArray(), encryptedData.getEncryptedPrivateKey().toByteArray()), this.keyCrypter, byteArray2);
                            } else {
                                throw new UnreadableWalletException("Encrypted private key data missing");
                            }
                        } else if (byteArray != null) {
                            eCKey = ECKey.fromPrivateAndPrecalculatedPublic(byteArray, byteArray2);
                        } else {
                            eCKey = ECKey.fromPublicOnly(byteArray2);
                        }
                        eCKey.setCreationTimeSeconds(key.getCreationTimestamp() / 1000);
                        importKeyLocked(eCKey);
                    } else {
                        throw new UnreadableWalletException("Public key missing");
                    }
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void addEventListener(KeyChainEventListener keyChainEventListener) {
        addEventListener(keyChainEventListener, Threading.USER_THREAD);
    }

    public void addEventListener(KeyChainEventListener keyChainEventListener, Executor executor) {
        this.listeners.add(new ListenerRegistration(keyChainEventListener, executor));
    }

    public boolean removeEventListener(KeyChainEventListener keyChainEventListener) {
        return ListenerRegistration.removeFromList(keyChainEventListener, this.listeners);
    }

    private void queueOnKeysAdded(final List<ECKey> list) {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            listenerRegistration.executor.execute(new Runnable() {
                public void run() {
                    ((KeyChainEventListener) listenerRegistration.listener).onKeysAdded(list);
                }
            });
        }
    }

    public BasicKeyChain toEncrypted(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkArgument(charSequence.length() > 0);
        KeyCrypterScrypt keyCrypterScrypt = new KeyCrypterScrypt();
        return toEncrypted((KeyCrypter) keyCrypterScrypt, keyCrypterScrypt.deriveKey(charSequence));
    }

    public BasicKeyChain toEncrypted(KeyCrypter keyCrypter2, KeyParameter keyParameter) {
        this.lock.lock();
        try {
            Preconditions.checkNotNull(keyCrypter2);
            Preconditions.checkState(this.keyCrypter == null, "Key chain is already encrypted");
            BasicKeyChain basicKeyChain = new BasicKeyChain(keyCrypter2);
            for (ECKey eCKey : this.hashToKeys.values()) {
                ECKey encrypt = eCKey.encrypt(keyCrypter2, keyParameter);
                if (ECKey.encryptionIsReversible(eCKey, encrypt, keyCrypter2, keyParameter)) {
                    basicKeyChain.importKeyLocked(encrypt);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("The key ");
                    sb.append(eCKey.toString());
                    sb.append(" cannot be successfully decrypted after encryption so aborting wallet encryption.");
                    throw new KeyCrypterException(sb.toString());
                }
            }
            return basicKeyChain;
        } finally {
            this.lock.unlock();
        }
    }

    public BasicKeyChain toDecrypted(CharSequence charSequence) {
        Preconditions.checkNotNull(this.keyCrypter, "Wallet is already decrypted");
        return toDecrypted(this.keyCrypter.deriveKey(charSequence));
    }

    public BasicKeyChain toDecrypted(KeyParameter keyParameter) {
        this.lock.lock();
        try {
            Preconditions.checkState(this.keyCrypter != null, "Wallet is already decrypted");
            if (numKeys() > 0) {
                if (!checkAESKey(keyParameter)) {
                    throw new KeyCrypterException("Password/key was incorrect.");
                }
            }
            BasicKeyChain basicKeyChain = new BasicKeyChain();
            for (ECKey decrypt : this.hashToKeys.values()) {
                basicKeyChain.importKeyLocked(decrypt.decrypt(keyParameter));
            }
            return basicKeyChain;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean checkPassword(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkState(this.keyCrypter != null, "Key chain not encrypted");
        return checkAESKey(this.keyCrypter.deriveKey(charSequence));
    }

    public boolean checkAESKey(KeyParameter keyParameter) {
        this.lock.lock();
        try {
            if (!this.hashToKeys.isEmpty()) {
                boolean z = true;
                Preconditions.checkState(this.keyCrypter != null, "Key chain is not encrypted");
                ECKey eCKey = null;
                Iterator it = this.hashToKeys.values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ECKey eCKey2 = (ECKey) it.next();
                    if (eCKey2.isEncrypted()) {
                        eCKey = eCKey2;
                        break;
                    }
                }
                if (eCKey == null) {
                    z = false;
                }
                Preconditions.checkState(z, "No encrypted keys in the wallet");
                try {
                    boolean equals = Arrays.equals(eCKey.getPubKey(), eCKey.decrypt(keyParameter).getPubKey());
                    this.lock.unlock();
                    return equals;
                } catch (KeyCrypterException unused) {
                }
            }
            return false;
        } finally {
            this.lock.unlock();
        }
    }

    public BloomFilter getFilter(int i, double d, long j) {
        this.lock.lock();
        try {
            BloomFilter bloomFilter = new BloomFilter(i, d, j);
            for (ECKey insert : this.hashToKeys.values()) {
                bloomFilter.insert(insert);
            }
            return bloomFilter;
        } finally {
            this.lock.unlock();
        }
    }

    public int numBloomFilterEntries() {
        return numKeys() * 2;
    }

    @Nullable
    public ECKey findOldestKeyAfter(long j) {
        this.lock.lock();
        ECKey eCKey = null;
        try {
            for (ECKey eCKey2 : this.hashToKeys.values()) {
                long creationTimeSeconds = eCKey2.getCreationTimeSeconds();
                if (creationTimeSeconds > j && (eCKey == null || eCKey.getCreationTimeSeconds() > creationTimeSeconds)) {
                    eCKey = eCKey2;
                }
            }
            return eCKey;
        } finally {
            this.lock.unlock();
        }
    }

    public List<ECKey> findKeysBefore(long j) {
        this.lock.lock();
        try {
            LinkedList newLinkedList = Lists.newLinkedList();
            for (ECKey eCKey : this.hashToKeys.values()) {
                if (eCKey.getCreationTimeSeconds() < j) {
                    newLinkedList.add(eCKey);
                }
            }
            return newLinkedList;
        } finally {
            this.lock.unlock();
        }
    }
}
