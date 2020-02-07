package org.bitcoinj.wallet;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import org.bitcoinj.core.BloomFilter;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.KeyCrypter;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.wallet.KeyChain.KeyPurpose;
import org.bitcoinj.wallet.Protos.C3509Key;

public class MarriedKeyChain extends DeterministicKeyChain {
    private List<DeterministicKeyChain> followingKeyChains;
    private LinkedHashMap<ByteString, RedeemData> marriedKeysRedeemData;

    public static class Builder<T extends Builder<T>> extends org.bitcoinj.wallet.DeterministicKeyChain.Builder<T> {
        private List<DeterministicKey> followingKeys;
        private int threshold;

        protected Builder() {
        }

        public T followingKeys(List<DeterministicKey> list) {
            this.followingKeys = list;
            return (Builder) self();
        }

        public T followingKeys(DeterministicKey deterministicKey, DeterministicKey... deterministicKeyArr) {
            this.followingKeys = Lists.asList(deterministicKey, deterministicKeyArr);
            return (Builder) self();
        }

        public T threshold(int i) {
            this.threshold = i;
            return (Builder) self();
        }

        public MarriedKeyChain build() {
            MarriedKeyChain marriedKeyChain;
            Preconditions.checkState((this.random == null && this.entropy == null && this.seed == null && this.watchingKey == null) ? false : true, "Must provide either entropy or random or seed or watchingKey");
            Preconditions.checkNotNull(this.followingKeys, "followingKeys must be provided");
            if (this.threshold == 0) {
                this.threshold = ((this.followingKeys.size() + 1) / 2) + 1;
            }
            if (this.random != null) {
                marriedKeyChain = new MarriedKeyChain(this.random, this.bits, getPassphrase(), this.seedCreationTimeSecs);
            } else if (this.entropy != null) {
                marriedKeyChain = new MarriedKeyChain(this.entropy, getPassphrase(), this.seedCreationTimeSecs);
            } else if (this.seed != null) {
                this.seed.setCreationTimeSeconds(this.seedCreationTimeSecs);
                marriedKeyChain = new MarriedKeyChain(this.seed);
            } else {
                this.watchingKey.setCreationTimeSeconds(this.seedCreationTimeSecs);
                marriedKeyChain = new MarriedKeyChain(this.watchingKey);
            }
            marriedKeyChain.addFollowingAccountKeys(this.followingKeys, this.threshold);
            return marriedKeyChain;
        }
    }

    public boolean isMarried() {
        return true;
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    MarriedKeyChain(DeterministicKey deterministicKey) {
        super(deterministicKey, false);
        this.marriedKeysRedeemData = new LinkedHashMap<>();
    }

    MarriedKeyChain(DeterministicSeed deterministicSeed, KeyCrypter keyCrypter) {
        super(deterministicSeed, keyCrypter);
        this.marriedKeysRedeemData = new LinkedHashMap<>();
    }

    private MarriedKeyChain(SecureRandom secureRandom, int i, String str, long j) {
        super(secureRandom, i, str, j);
        this.marriedKeysRedeemData = new LinkedHashMap<>();
    }

    private MarriedKeyChain(byte[] bArr, String str, long j) {
        super(bArr, str, j);
        this.marriedKeysRedeemData = new LinkedHashMap<>();
    }

    private MarriedKeyChain(DeterministicSeed deterministicSeed) {
        super(deterministicSeed);
        this.marriedKeysRedeemData = new LinkedHashMap<>();
    }

    /* access modifiers changed from: 0000 */
    public void setFollowingKeyChains(List<DeterministicKeyChain> list) {
        Preconditions.checkArgument(!list.isEmpty());
        this.followingKeyChains = list;
    }

    public Script freshOutputScript(KeyPurpose keyPurpose) {
        DeterministicKey key = getKey(keyPurpose);
        com.google.common.collect.ImmutableList.Builder add = ImmutableList.builder().add((Object) key);
        for (DeterministicKeyChain key2 : this.followingKeyChains) {
            DeterministicKey key3 = key2.getKey(keyPurpose);
            Preconditions.checkState(key.getChildNumber().equals(key3.getChildNumber()), "Following keychains should be in sync");
            add.add((Object) key3);
        }
        return ScriptBuilder.createP2SHOutputScript(ScriptBuilder.createRedeemScript(this.sigsRequiredToSpend, add.build()));
    }

    private List<ECKey> getMarriedKeysWithFollowed(DeterministicKey deterministicKey) {
        com.google.common.collect.ImmutableList.Builder builder = ImmutableList.builder();
        for (DeterministicKeyChain deterministicKeyChain : this.followingKeyChains) {
            deterministicKeyChain.maybeLookAhead();
            builder.add((Object) deterministicKeyChain.getKeyByPath((List<ChildNumber>) deterministicKey.getPath()));
        }
        builder.add((Object) deterministicKey);
        return builder.build();
    }

    public RedeemData getRedeemData(DeterministicKey deterministicKey) {
        List marriedKeysWithFollowed = getMarriedKeysWithFollowed(deterministicKey);
        return RedeemData.m343of(marriedKeysWithFollowed, ScriptBuilder.createRedeemScript(this.sigsRequiredToSpend, marriedKeysWithFollowed));
    }

    /* access modifiers changed from: private */
    public void addFollowingAccountKeys(List<DeterministicKey> list, int i) {
        Preconditions.checkArgument(i <= list.size() + 1, "Multisig threshold can't exceed total number of keys");
        Preconditions.checkState(numLeafKeysIssued() == 0, "Active keychain already has keys in use");
        Preconditions.checkState(this.followingKeyChains == null);
        ArrayList newArrayList = Lists.newArrayList();
        for (DeterministicKey deterministicKey : list) {
            Preconditions.checkArgument(deterministicKey.getPath().size() == getAccountPath().size(), "Following keys have to be account keys");
            DeterministicKeyChain watchAndFollow = DeterministicKeyChain.watchAndFollow(deterministicKey);
            if (this.lookaheadSize >= 0) {
                watchAndFollow.setLookaheadSize(this.lookaheadSize);
            }
            if (this.lookaheadThreshold >= 0) {
                watchAndFollow.setLookaheadThreshold(this.lookaheadThreshold);
            }
            newArrayList.add(watchAndFollow);
        }
        this.sigsRequiredToSpend = i;
        this.followingKeyChains = newArrayList;
    }

    public void setLookaheadSize(int i) {
        this.lock.lock();
        try {
            super.setLookaheadSize(i);
            if (this.followingKeyChains != null) {
                for (DeterministicKeyChain lookaheadSize : this.followingKeyChains) {
                    lookaheadSize.setLookaheadSize(i);
                }
            }
        } finally {
            this.lock.unlock();
        }
    }

    public List<C3509Key> serializeToProtobuf() {
        ArrayList newArrayList = Lists.newArrayList();
        this.lock.lock();
        try {
            for (DeterministicKeyChain serializeMyselfToProtobuf : this.followingKeyChains) {
                newArrayList.addAll(serializeMyselfToProtobuf.serializeMyselfToProtobuf());
            }
            newArrayList.addAll(serializeMyselfToProtobuf());
            return newArrayList;
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void formatAddresses(boolean z, NetworkParameters networkParameters, StringBuilder sb) {
        for (DeterministicKeyChain deterministicKeyChain : this.followingKeyChains) {
            sb.append("Following chain:  ");
            sb.append(deterministicKeyChain.getWatchingKey().serializePubB58(networkParameters));
            sb.append(10);
        }
        sb.append(10);
        for (RedeemData redeemData : this.marriedKeysRedeemData.values()) {
            formatScript(ScriptBuilder.createP2SHOutputScript(redeemData.redeemScript), sb, networkParameters);
        }
    }

    private void formatScript(Script script, StringBuilder sb, NetworkParameters networkParameters) {
        sb.append("  addr:");
        sb.append(script.getToAddress(networkParameters));
        sb.append("  hash160:");
        sb.append(C3447Utils.HEX.encode(script.getPubKeyHash()));
        if (script.getCreationTimeSeconds() > 0) {
            sb.append("  creationTimeSeconds:");
            sb.append(script.getCreationTimeSeconds());
        }
        sb.append(10);
    }

    public void maybeLookAheadScripts() {
        super.maybeLookAheadScripts();
        int size = getLeafKeys().size();
        Preconditions.checkState(this.marriedKeysRedeemData.size() <= size, "Number of scripts is greater than number of leaf keys");
        if (this.marriedKeysRedeemData.size() != size) {
            maybeLookAhead();
            for (DeterministicKey redeemData : getLeafKeys()) {
                RedeemData redeemData2 = getRedeemData(redeemData);
                this.marriedKeysRedeemData.put(ByteString.copyFrom(ScriptBuilder.createP2SHOutputScript(redeemData2.redeemScript).getPubKeyHash()), redeemData2);
            }
        }
    }

    @Nullable
    public RedeemData findRedeemDataByScriptHash(ByteString byteString) {
        return (RedeemData) this.marriedKeysRedeemData.get(byteString);
    }

    public BloomFilter getFilter(int i, double d, long j) {
        this.lock.lock();
        try {
            BloomFilter bloomFilter = new BloomFilter(i, d, j);
            for (Entry entry : this.marriedKeysRedeemData.entrySet()) {
                bloomFilter.insert(((ByteString) entry.getKey()).toByteArray());
                bloomFilter.insert(((RedeemData) entry.getValue()).redeemScript.getProgram());
            }
            return bloomFilter;
        } finally {
            this.lock.unlock();
        }
    }

    public int numBloomFilterEntries() {
        maybeLookAhead();
        return getLeafKeys().size() * 2;
    }
}
