package org.bitcoinj.wallet.listeners;

import java.util.List;
import org.bitcoinj.core.ECKey;

public class AbstractKeyChainEventListener implements KeyChainEventListener {
    public void onKeysAdded(List<ECKey> list) {
    }
}
