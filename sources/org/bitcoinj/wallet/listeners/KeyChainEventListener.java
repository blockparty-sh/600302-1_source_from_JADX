package org.bitcoinj.wallet.listeners;

import java.util.List;
import org.bitcoinj.core.ECKey;

public interface KeyChainEventListener {
    void onKeysAdded(List<ECKey> list);
}
