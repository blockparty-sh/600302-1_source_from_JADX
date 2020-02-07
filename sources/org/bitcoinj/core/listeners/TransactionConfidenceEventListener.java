package org.bitcoinj.core.listeners;

import org.bitcoinj.core.Transaction;
import org.bitcoinj.wallet.C3530Wallet;

public interface TransactionConfidenceEventListener {
    void onTransactionConfidenceChanged(C3530Wallet wallet, Transaction transaction);
}
