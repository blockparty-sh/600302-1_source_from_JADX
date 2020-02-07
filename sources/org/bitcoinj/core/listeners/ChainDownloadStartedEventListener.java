package org.bitcoinj.core.listeners;

import org.bitcoinj.core.Peer;

public interface ChainDownloadStartedEventListener {
    void onChainDownloadStarted(Peer peer, int i);
}
