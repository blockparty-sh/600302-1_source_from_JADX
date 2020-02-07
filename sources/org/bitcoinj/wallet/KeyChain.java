package org.bitcoinj.wallet;

import java.util.List;
import java.util.concurrent.Executor;
import org.bitcoinj.core.BloomFilter;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.wallet.Protos.C3509Key;
import org.bitcoinj.wallet.listeners.KeyChainEventListener;

public interface KeyChain {

    public enum KeyPurpose {
        RECEIVE_FUNDS,
        CHANGE,
        REFUND,
        AUTHENTICATION
    }

    void addEventListener(KeyChainEventListener keyChainEventListener);

    void addEventListener(KeyChainEventListener keyChainEventListener, Executor executor);

    long getEarliestKeyCreationTime();

    BloomFilter getFilter(int i, double d, long j);

    ECKey getKey(KeyPurpose keyPurpose);

    List<? extends ECKey> getKeys(KeyPurpose keyPurpose, int i);

    boolean hasKey(ECKey eCKey);

    int numBloomFilterEntries();

    int numKeys();

    boolean removeEventListener(KeyChainEventListener keyChainEventListener);

    List<C3509Key> serializeToProtobuf();
}
