package org.bitcoinj.core.listeners;

import java.util.Set;
import org.bitcoinj.core.Peer;
import org.bitcoinj.core.PeerAddress;

@Deprecated
public abstract class AbstractPeerConnectionEventListener implements PeerConnectionEventListener {
    public void onPeerConnected(Peer peer, int i) {
    }

    public void onPeerDisconnected(Peer peer, int i) {
    }

    public void onPeersDiscovered(Set<PeerAddress> set) {
    }
}
