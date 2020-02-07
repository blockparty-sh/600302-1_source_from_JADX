package org.bitcoinj.wallet;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.BloomFilter;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.crypto.KeyCrypter;
import org.bitcoinj.crypto.LinuxSecureRandom;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.utils.ListenerRegistration;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.BasicKeyChain.State;
import org.bitcoinj.wallet.KeyChain.KeyPurpose;
import org.bitcoinj.wallet.Protos.C3509Key;
import org.bitcoinj.wallet.listeners.KeyChainEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.crypto.params.KeyParameter;

public class KeyChainGroup implements KeyBag {
    private static final Logger log = LoggerFactory.getLogger(KeyChainGroup.class);
    private BasicKeyChain basic;
    protected final LinkedList<DeterministicKeyChain> chains;
    private final EnumMap<KeyPurpose, Address> currentAddresses;
    private final EnumMap<KeyPurpose, DeterministicKey> currentKeys;
    @Nullable
    private KeyCrypter keyCrypter;
    private int lookaheadSize;
    private int lookaheadThreshold;
    private NetworkParameters params;

    static {
        if (C3447Utils.isAndroidRuntime()) {
            new LinuxSecureRandom();
        }
    }

    public KeyChainGroup(NetworkParameters networkParameters) {
        this(networkParameters, null, new ArrayList(1), null, null);
    }

    public KeyChainGroup(NetworkParameters networkParameters, DeterministicSeed deterministicSeed) {
        this(networkParameters, null, ImmutableList.m127of(new DeterministicKeyChain(deterministicSeed)), null, null);
    }

    public KeyChainGroup(NetworkParameters networkParameters, DeterministicKey deterministicKey) {
        this(networkParameters, null, ImmutableList.m127of(DeterministicKeyChain.watch(deterministicKey)), null, null);
    }

    private KeyChainGroup(NetworkParameters networkParameters, @Nullable BasicKeyChain basicKeyChain, List<DeterministicKeyChain> list, @Nullable EnumMap<KeyPurpose, DeterministicKey> enumMap, @Nullable KeyCrypter keyCrypter2) {
        this.lookaheadSize = -1;
        this.lookaheadThreshold = -1;
        this.params = networkParameters;
        if (basicKeyChain == null) {
            basicKeyChain = new BasicKeyChain();
        }
        this.basic = basicKeyChain;
        this.chains = new LinkedList<>((Collection) Preconditions.checkNotNull(list));
        this.keyCrypter = keyCrypter2;
        if (enumMap == null) {
            enumMap = new EnumMap<>(KeyPurpose.class);
        }
        this.currentKeys = enumMap;
        this.currentAddresses = new EnumMap<>(KeyPurpose.class);
        maybeLookaheadScripts();
        if (isMarried()) {
            for (Entry entry : this.currentKeys.entrySet()) {
                this.currentAddresses.put((Enum) entry.getKey(), makeP2SHOutputScript((DeterministicKey) entry.getValue(), getActiveKeyChain()).getToAddress(networkParameters));
            }
        }
    }

    private void maybeLookaheadScripts() {
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            ((DeterministicKeyChain) it.next()).maybeLookAheadScripts();
        }
    }

    public void createAndActivateNewHDChain() {
        addAndActivateHDChain(new DeterministicKeyChain(new SecureRandom()));
    }

    public void addAndActivateHDChain(DeterministicKeyChain deterministicKeyChain) {
        log.info("Creating and activating a new HD chain: {}", (Object) deterministicKeyChain);
        for (ListenerRegistration listenerRegistration : this.basic.getListeners()) {
            deterministicKeyChain.addEventListener((KeyChainEventListener) listenerRegistration.listener, listenerRegistration.executor);
        }
        int i = this.lookaheadSize;
        if (i >= 0) {
            deterministicKeyChain.setLookaheadSize(i);
        }
        int i2 = this.lookaheadThreshold;
        if (i2 >= 0) {
            deterministicKeyChain.setLookaheadThreshold(i2);
        }
        this.chains.add(deterministicKeyChain);
    }

    public DeterministicKey currentKey(KeyPurpose keyPurpose) {
        if (!getActiveKeyChain().isMarried()) {
            DeterministicKey deterministicKey = (DeterministicKey) this.currentKeys.get(keyPurpose);
            if (deterministicKey != null) {
                return deterministicKey;
            }
            DeterministicKey freshKey = freshKey(keyPurpose);
            this.currentKeys.put(keyPurpose, freshKey);
            return freshKey;
        }
        throw new UnsupportedOperationException("Key is not suitable to receive coins for married keychains. Use freshAddress to get P2SH address instead");
    }

    public Address currentAddress(KeyPurpose keyPurpose) {
        if (!getActiveKeyChain().isMarried()) {
            return currentKey(keyPurpose).toAddress(this.params);
        }
        Address address = (Address) this.currentAddresses.get(keyPurpose);
        if (address == null) {
            address = freshAddress(keyPurpose);
            this.currentAddresses.put(keyPurpose, address);
        }
        return address;
    }

    public DeterministicKey freshKey(KeyPurpose keyPurpose) {
        return (DeterministicKey) freshKeys(keyPurpose, 1).get(0);
    }

    public List<DeterministicKey> freshKeys(KeyPurpose keyPurpose, int i) {
        DeterministicKeyChain activeKeyChain = getActiveKeyChain();
        if (!activeKeyChain.isMarried()) {
            return activeKeyChain.getKeys(keyPurpose, i);
        }
        throw new UnsupportedOperationException("Key is not suitable to receive coins for married keychains. Use freshAddress to get P2SH address instead");
    }

    public Address freshAddress(KeyPurpose keyPurpose) {
        DeterministicKeyChain activeKeyChain = getActiveKeyChain();
        if (!activeKeyChain.isMarried()) {
            return freshKey(keyPurpose).toAddress(this.params);
        }
        Script freshOutputScript = activeKeyChain.freshOutputScript(keyPurpose);
        Preconditions.checkState(freshOutputScript.isPayToScriptHash());
        Address fromP2SHScript = Address.fromP2SHScript(this.params, freshOutputScript);
        maybeLookaheadScripts();
        this.currentAddresses.put(keyPurpose, fromP2SHScript);
        return fromP2SHScript;
    }

    public final DeterministicKeyChain getActiveKeyChain() {
        if (this.chains.isEmpty()) {
            if (this.basic.numKeys() <= 0) {
                createAndActivateNewHDChain();
            } else {
                log.warn("No HD chain present but random keys are: you probably deserialized an old wallet.");
                throw new DeterministicUpgradeRequiredException();
            }
        }
        LinkedList<DeterministicKeyChain> linkedList = this.chains;
        return (DeterministicKeyChain) linkedList.get(linkedList.size() - 1);
    }

    public void setLookaheadSize(int i) {
        this.lookaheadSize = i;
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            ((DeterministicKeyChain) it.next()).setLookaheadSize(i);
        }
    }

    public int getLookaheadSize() {
        int i = this.lookaheadSize;
        return i == -1 ? getActiveKeyChain().getLookaheadSize() : i;
    }

    public void setLookaheadThreshold(int i) {
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            ((DeterministicKeyChain) it.next()).setLookaheadThreshold(i);
        }
    }

    public int getLookaheadThreshold() {
        int i = this.lookaheadThreshold;
        return i == -1 ? getActiveKeyChain().getLookaheadThreshold() : i;
    }

    public int importKeys(List<ECKey> list) {
        return this.basic.importKeys(list);
    }

    public int importKeys(ECKey... eCKeyArr) {
        return importKeys((List<ECKey>) ImmutableList.copyOf((E[]) eCKeyArr));
    }

    public boolean checkPassword(CharSequence charSequence) {
        Preconditions.checkState(this.keyCrypter != null, "Not encrypted");
        return checkAESKey(this.keyCrypter.deriveKey(charSequence));
    }

    public boolean checkAESKey(KeyParameter keyParameter) {
        Preconditions.checkState(this.keyCrypter != null, "Not encrypted");
        if (this.basic.numKeys() > 0) {
            return this.basic.checkAESKey(keyParameter);
        }
        return getActiveKeyChain().checkAESKey(keyParameter);
    }

    public int importKeysAndEncrypt(List<ECKey> list, KeyParameter keyParameter) {
        Preconditions.checkState(this.keyCrypter != null, "Not encrypted");
        LinkedList newLinkedList = Lists.newLinkedList();
        for (ECKey eCKey : list) {
            if (!eCKey.isEncrypted()) {
                newLinkedList.add(eCKey.encrypt(this.keyCrypter, keyParameter));
            } else {
                throw new IllegalArgumentException("Cannot provide already encrypted keys");
            }
        }
        return importKeys((List<ECKey>) newLinkedList);
    }

    @Nullable
    public RedeemData findRedeemDataFromScriptHash(byte[] bArr) {
        Iterator descendingIterator = this.chains.descendingIterator();
        while (descendingIterator.hasNext()) {
            RedeemData findRedeemDataByScriptHash = ((DeterministicKeyChain) descendingIterator.next()).findRedeemDataByScriptHash(ByteString.copyFrom(bArr));
            if (findRedeemDataByScriptHash != null) {
                return findRedeemDataByScriptHash;
            }
        }
        return null;
    }

    public void markP2SHAddressAsUsed(Address address) {
        Preconditions.checkArgument(address.isP2SHAddress());
        RedeemData findRedeemDataFromScriptHash = findRedeemDataFromScriptHash(address.getHash160());
        if (findRedeemDataFromScriptHash != null) {
            for (ECKey eCKey : findRedeemDataFromScriptHash.keys) {
                Iterator it = this.chains.iterator();
                while (it.hasNext()) {
                    DeterministicKeyChain deterministicKeyChain = (DeterministicKeyChain) it.next();
                    DeterministicKey findKeyFromPubKey = deterministicKeyChain.findKeyFromPubKey(eCKey.getPubKey());
                    if (findKeyFromPubKey != null) {
                        deterministicKeyChain.markKeyAsUsed(findKeyFromPubKey);
                        maybeMarkCurrentAddressAsUsed(address);
                    }
                }
            }
        }
    }

    @Nullable
    public ECKey findKeyFromPubHash(byte[] bArr) {
        ECKey findKeyFromPubHash = this.basic.findKeyFromPubHash(bArr);
        if (findKeyFromPubHash != null) {
            return findKeyFromPubHash;
        }
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            DeterministicKey findKeyFromPubHash2 = ((DeterministicKeyChain) it.next()).findKeyFromPubHash(bArr);
            if (findKeyFromPubHash2 != null) {
                return findKeyFromPubHash2;
            }
        }
        return null;
    }

    public void markPubKeyHashAsUsed(byte[] bArr) {
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            DeterministicKey markPubHashAsUsed = ((DeterministicKeyChain) it.next()).markPubHashAsUsed(bArr);
            if (markPubHashAsUsed != null) {
                maybeMarkCurrentKeyAsUsed(markPubHashAsUsed);
                return;
            }
        }
    }

    private void maybeMarkCurrentAddressAsUsed(Address address) {
        Preconditions.checkArgument(address.isP2SHAddress());
        for (Entry entry : this.currentAddresses.entrySet()) {
            if (entry.getValue() != null && ((Address) entry.getValue()).equals(address)) {
                log.info("Marking P2SH address as used: {}", (Object) address);
                this.currentAddresses.put((Enum) entry.getKey(), freshAddress((KeyPurpose) entry.getKey()));
                return;
            }
        }
    }

    private void maybeMarkCurrentKeyAsUsed(DeterministicKey deterministicKey) {
        for (Entry entry : this.currentKeys.entrySet()) {
            if (entry.getValue() != null && ((DeterministicKey) entry.getValue()).equals(deterministicKey)) {
                log.info("Marking key as used: {}", (Object) deterministicKey);
                this.currentKeys.put((Enum) entry.getKey(), freshKey((KeyPurpose) entry.getKey()));
                return;
            }
        }
    }

    public boolean hasKey(ECKey eCKey) {
        if (this.basic.hasKey(eCKey)) {
            return true;
        }
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            if (((DeterministicKeyChain) it.next()).hasKey(eCKey)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public ECKey findKeyFromPubKey(byte[] bArr) {
        ECKey findKeyFromPubKey = this.basic.findKeyFromPubKey(bArr);
        if (findKeyFromPubKey != null) {
            return findKeyFromPubKey;
        }
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            DeterministicKey findKeyFromPubKey2 = ((DeterministicKeyChain) it.next()).findKeyFromPubKey(bArr);
            if (findKeyFromPubKey2 != null) {
                return findKeyFromPubKey2;
            }
        }
        return null;
    }

    public void markPubKeyAsUsed(byte[] bArr) {
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            DeterministicKey markPubKeyAsUsed = ((DeterministicKeyChain) it.next()).markPubKeyAsUsed(bArr);
            if (markPubKeyAsUsed != null) {
                maybeMarkCurrentKeyAsUsed(markPubKeyAsUsed);
                return;
            }
        }
    }

    public int numKeys() {
        int numKeys = this.basic.numKeys();
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            numKeys += ((DeterministicKeyChain) it.next()).numKeys();
        }
        return numKeys;
    }

    public boolean removeImportedKey(ECKey eCKey) {
        Preconditions.checkNotNull(eCKey);
        Preconditions.checkArgument(!(eCKey instanceof DeterministicKey));
        return this.basic.removeKey(eCKey);
    }

    public final boolean isMarried() {
        return !this.chains.isEmpty() && getActiveKeyChain().isMarried();
    }

    public void encrypt(KeyCrypter keyCrypter2, KeyParameter keyParameter) {
        Preconditions.checkNotNull(keyCrypter2);
        Preconditions.checkNotNull(keyParameter);
        BasicKeyChain encrypted = this.basic.toEncrypted(keyCrypter2, keyParameter);
        ArrayList arrayList = new ArrayList(this.chains.size());
        if (this.chains.isEmpty() && this.basic.numKeys() == 0) {
            createAndActivateNewHDChain();
        }
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            arrayList.add(((DeterministicKeyChain) it.next()).toEncrypted(keyCrypter2, keyParameter));
        }
        this.keyCrypter = keyCrypter2;
        this.basic = encrypted;
        this.chains.clear();
        this.chains.addAll(arrayList);
    }

    public void decrypt(KeyParameter keyParameter) {
        Preconditions.checkNotNull(keyParameter);
        BasicKeyChain decrypted = this.basic.toDecrypted(keyParameter);
        ArrayList arrayList = new ArrayList(this.chains.size());
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            arrayList.add(((DeterministicKeyChain) it.next()).toDecrypted(keyParameter));
        }
        this.keyCrypter = null;
        this.basic = decrypted;
        this.chains.clear();
        this.chains.addAll(arrayList);
    }

    public boolean isEncrypted() {
        return this.keyCrypter != null;
    }

    public boolean isWatching() {
        State isWatching = this.basic.isWatching();
        State state = State.EMPTY;
        if (!this.chains.isEmpty()) {
            if (getActiveKeyChain().isWatching()) {
                state = State.WATCHING;
            } else {
                state = State.REGULAR;
            }
        }
        boolean z = true;
        if (isWatching == State.EMPTY) {
            if (state != State.EMPTY) {
                if (state != State.WATCHING) {
                    z = false;
                }
                return z;
            }
            throw new IllegalStateException("Empty key chain group: cannot answer isWatching() query");
        } else if (state == State.EMPTY) {
            if (isWatching != State.WATCHING) {
                z = false;
            }
            return z;
        } else if (state == isWatching) {
            if (state != State.WATCHING) {
                z = false;
            }
            return z;
        } else {
            throw new IllegalStateException("Mix of watching and non-watching keys in wallet");
        }
    }

    @Nullable
    public KeyCrypter getKeyCrypter() {
        return this.keyCrypter;
    }

    public List<ECKey> getImportedKeys() {
        return this.basic.getKeys();
    }

    public long getEarliestKeyCreationTime() {
        long earliestKeyCreationTime = this.basic.getEarliestKeyCreationTime();
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            earliestKeyCreationTime = Math.min(earliestKeyCreationTime, ((DeterministicKeyChain) it.next()).getEarliestKeyCreationTime());
        }
        return earliestKeyCreationTime;
    }

    public int getBloomFilterElementCount() {
        int numBloomFilterEntries = this.basic.numBloomFilterEntries();
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            numBloomFilterEntries += ((DeterministicKeyChain) it.next()).numBloomFilterEntries();
        }
        return numBloomFilterEntries;
    }

    public BloomFilter getBloomFilter(int i, double d, long j) {
        BloomFilter bloomFilter = new BloomFilter(i, d, j);
        if (this.basic.numKeys() > 0) {
            bloomFilter.merge(this.basic.getFilter(i, d, j));
        }
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            bloomFilter.merge(((DeterministicKeyChain) it.next()).getFilter(i, d, j));
        }
        return bloomFilter;
    }

    public boolean isRequiringUpdateAllBloomFilter() {
        throw new UnsupportedOperationException();
    }

    private Script makeP2SHOutputScript(DeterministicKey deterministicKey, DeterministicKeyChain deterministicKeyChain) {
        return ScriptBuilder.createP2SHOutputScript(deterministicKeyChain.getRedeemData(deterministicKey).redeemScript);
    }

    public void addEventListener(KeyChainEventListener keyChainEventListener) {
        addEventListener(keyChainEventListener, Threading.USER_THREAD);
    }

    public void addEventListener(KeyChainEventListener keyChainEventListener, Executor executor) {
        Preconditions.checkNotNull(keyChainEventListener);
        Preconditions.checkNotNull(executor);
        this.basic.addEventListener(keyChainEventListener, executor);
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            ((DeterministicKeyChain) it.next()).addEventListener(keyChainEventListener, executor);
        }
    }

    public boolean removeEventListener(KeyChainEventListener keyChainEventListener) {
        Preconditions.checkNotNull(keyChainEventListener);
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            ((DeterministicKeyChain) it.next()).removeEventListener(keyChainEventListener);
        }
        return this.basic.removeEventListener(keyChainEventListener);
    }

    public List<C3509Key> serializeToProtobuf() {
        List<C3509Key> list;
        BasicKeyChain basicKeyChain = this.basic;
        if (basicKeyChain != null) {
            list = basicKeyChain.serializeToProtobuf();
        } else {
            list = Lists.newArrayList();
        }
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            list.addAll(((DeterministicKeyChain) it.next()).serializeToProtobuf());
        }
        return list;
    }

    static KeyChainGroup fromProtobufUnencrypted(NetworkParameters networkParameters, List<C3509Key> list) throws UnreadableWalletException {
        return fromProtobufUnencrypted(networkParameters, list, new DefaultKeyChainFactory());
    }

    public static KeyChainGroup fromProtobufUnencrypted(NetworkParameters networkParameters, List<C3509Key> list, KeyChainFactory keyChainFactory) throws UnreadableWalletException {
        BasicKeyChain fromProtobufUnencrypted = BasicKeyChain.fromProtobufUnencrypted(list);
        List fromProtobuf = DeterministicKeyChain.fromProtobuf(list, null, keyChainFactory);
        EnumMap createCurrentKeysMap = !fromProtobuf.isEmpty() ? createCurrentKeysMap(fromProtobuf) : null;
        extractFollowingKeychains(fromProtobuf);
        KeyChainGroup keyChainGroup = new KeyChainGroup(networkParameters, fromProtobufUnencrypted, fromProtobuf, createCurrentKeysMap, null);
        return keyChainGroup;
    }

    static KeyChainGroup fromProtobufEncrypted(NetworkParameters networkParameters, List<C3509Key> list, KeyCrypter keyCrypter2) throws UnreadableWalletException {
        return fromProtobufEncrypted(networkParameters, list, keyCrypter2, new DefaultKeyChainFactory());
    }

    public static KeyChainGroup fromProtobufEncrypted(NetworkParameters networkParameters, List<C3509Key> list, KeyCrypter keyCrypter2, KeyChainFactory keyChainFactory) throws UnreadableWalletException {
        Preconditions.checkNotNull(keyCrypter2);
        BasicKeyChain fromProtobufEncrypted = BasicKeyChain.fromProtobufEncrypted(list, keyCrypter2);
        List fromProtobuf = DeterministicKeyChain.fromProtobuf(list, keyCrypter2, keyChainFactory);
        EnumMap createCurrentKeysMap = !fromProtobuf.isEmpty() ? createCurrentKeysMap(fromProtobuf) : null;
        extractFollowingKeychains(fromProtobuf);
        KeyChainGroup keyChainGroup = new KeyChainGroup(networkParameters, fromProtobufEncrypted, fromProtobuf, createCurrentKeysMap, keyCrypter2);
        return keyChainGroup;
    }

    public DeterministicKeyChain upgradeToDeterministic(long j, @Nullable KeyParameter keyParameter) throws DeterministicUpgradeRequiresPassword, AllRandomKeysRotating {
        boolean z = true;
        Preconditions.checkState(this.basic.numKeys() > 0);
        Preconditions.checkArgument(j >= 0);
        ECKey findOldestKeyAfter = this.basic.findOldestKeyAfter(j - 1);
        if (findOldestKeyAfter != null) {
            if (findOldestKeyAfter.isEncrypted()) {
                if (keyParameter != null) {
                    findOldestKeyAfter = findOldestKeyAfter.decrypt(keyParameter);
                } else {
                    throw new DeterministicUpgradeRequiresPassword();
                }
            } else if (keyParameter != null) {
                throw new IllegalStateException("AES Key was provided but wallet is not encrypted.");
            }
            if (this.chains.isEmpty()) {
                log.info("Auto-upgrading pre-HD wallet to HD!");
            } else {
                log.info("Wallet with existing HD chain is being re-upgraded due to change in key rotation time.");
            }
            log.info("Instantiating new HD chain using oldest non-rotating private key (address: {})", (Object) findOldestKeyAfter.toAddress(this.params));
            byte[] bArr = (byte[]) Preconditions.checkNotNull(findOldestKeyAfter.getSecretBytes());
            Preconditions.checkState(bArr.length >= 16);
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 16);
            if (copyOfRange.length != 16) {
                z = false;
            }
            Preconditions.checkState(z);
            DeterministicKeyChain deterministicKeyChain = new DeterministicKeyChain(copyOfRange, "", findOldestKeyAfter.getCreationTimeSeconds());
            if (keyParameter != null) {
                deterministicKeyChain = deterministicKeyChain.toEncrypted((KeyCrypter) Preconditions.checkNotNull(this.basic.getKeyCrypter()), keyParameter);
            }
            this.chains.add(deterministicKeyChain);
            return deterministicKeyChain;
        }
        throw new AllRandomKeysRotating();
    }

    public boolean isDeterministicUpgradeRequired() {
        return this.basic.numKeys() > 0 && this.chains.isEmpty();
    }

    private static EnumMap<KeyPurpose, DeterministicKey> createCurrentKeysMap(List<DeterministicKeyChain> list) {
        DeterministicKeyChain deterministicKeyChain = (DeterministicKeyChain) list.get(list.size() - 1);
        EnumMap<KeyPurpose, DeterministicKey> enumMap = new EnumMap<>(KeyPurpose.class);
        if (deterministicKeyChain.getIssuedExternalKeys() > 0) {
            enumMap.put(KeyPurpose.RECEIVE_FUNDS, deterministicKeyChain.getKeyByPath((List<ChildNumber>) HDUtils.append(HDUtils.concat(deterministicKeyChain.getAccountPath(), DeterministicKeyChain.EXTERNAL_SUBPATH), new ChildNumber(deterministicKeyChain.getIssuedExternalKeys() - 1))));
        }
        if (deterministicKeyChain.getIssuedInternalKeys() > 0) {
            enumMap.put(KeyPurpose.CHANGE, deterministicKeyChain.getKeyByPath((List<ChildNumber>) HDUtils.append(HDUtils.concat(deterministicKeyChain.getAccountPath(), DeterministicKeyChain.INTERNAL_SUBPATH), new ChildNumber(deterministicKeyChain.getIssuedInternalKeys() - 1))));
        }
        return enumMap;
    }

    private static void extractFollowingKeychains(List<DeterministicKeyChain> list) {
        ArrayList newArrayList = Lists.newArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            DeterministicKeyChain deterministicKeyChain = (DeterministicKeyChain) it.next();
            if (deterministicKeyChain.isFollowing()) {
                newArrayList.add(deterministicKeyChain);
                it.remove();
            } else if (newArrayList.isEmpty()) {
                continue;
            } else if (deterministicKeyChain instanceof MarriedKeyChain) {
                ((MarriedKeyChain) deterministicKeyChain).setFollowingKeyChains(newArrayList);
                newArrayList = Lists.newArrayList();
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public String toString(boolean z) {
        StringBuilder sb = new StringBuilder();
        BasicKeyChain basicKeyChain = this.basic;
        if (basicKeyChain != null) {
            List<ECKey> keys = basicKeyChain.getKeys();
            Collections.sort(keys, ECKey.AGE_COMPARATOR);
            for (ECKey formatKeyWithAddress : keys) {
                formatKeyWithAddress.formatKeyWithAddress(z, sb, this.params);
            }
        }
        Iterator it = this.chains.iterator();
        while (it.hasNext()) {
            sb.append(((DeterministicKeyChain) it.next()).toString(z, this.params));
            sb.append(10);
        }
        return sb.toString();
    }

    public List<DeterministicKeyChain> getDeterministicKeyChains() {
        return new ArrayList(this.chains);
    }

    public int getCombinedKeyLookaheadEpochs() {
        Iterator it = this.chains.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((DeterministicKeyChain) it.next()).getKeyLookaheadEpoch();
        }
        return i;
    }
}
