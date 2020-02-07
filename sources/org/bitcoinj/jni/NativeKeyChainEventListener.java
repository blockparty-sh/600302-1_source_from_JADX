package org.bitcoinj.jni;

import java.util.List;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.wallet.listeners.KeyChainEventListener;

public class NativeKeyChainEventListener implements KeyChainEventListener {
    public long ptr;

    public native void onKeysAdded(List<ECKey> list);
}
