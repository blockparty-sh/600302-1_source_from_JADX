package org.bitcoinj.core.listeners;

import org.bitcoinj.core.Peer;

public interface PeerDisconnectedEventListener {
    void onPeerDisconnected(Peer peer, int i);
}
