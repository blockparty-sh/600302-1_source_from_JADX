package org.bitcoinj.core.listeners;

import org.bitcoinj.core.Peer;

@Deprecated
public interface PeerConnectionEventListener extends PeerConnectedEventListener, PeerDiscoveredEventListener, PeerDisconnectedEventListener {
    void onPeerDisconnected(Peer peer, int i);
}
