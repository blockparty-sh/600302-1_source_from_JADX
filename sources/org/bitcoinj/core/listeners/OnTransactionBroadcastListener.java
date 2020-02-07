package org.bitcoinj.core.listeners;

import org.bitcoinj.core.Peer;
import org.bitcoinj.core.Transaction;

public interface OnTransactionBroadcastListener {
    void onTransaction(Peer peer, Transaction transaction);
}
