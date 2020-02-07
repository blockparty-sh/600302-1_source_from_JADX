package org.bitcoinj.core.listeners;

import java.util.Set;
import org.bitcoinj.core.PeerAddress;

public interface PeerDiscoveredEventListener {
    void onPeersDiscovered(Set<PeerAddress> set);
}
