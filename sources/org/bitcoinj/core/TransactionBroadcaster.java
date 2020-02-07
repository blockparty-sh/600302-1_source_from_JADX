package org.bitcoinj.core;

public interface TransactionBroadcaster {
    TransactionBroadcast broadcastTransaction(Transaction transaction);
}
