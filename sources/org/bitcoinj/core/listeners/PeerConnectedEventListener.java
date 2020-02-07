package org.bitcoinj.core.listeners;

import org.bitcoinj.core.Peer;

public interface PeerConnectedEventListener {
    void onPeerConnected(Peer peer, int i);
}
