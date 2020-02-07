package org.bitcoinj.jni;

import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.listeners.TransactionConfidenceEventListener;
import org.bitcoinj.wallet.C3530Wallet;

public class NativeTransactionConfidenceEventListener implements TransactionConfidenceEventListener {
    public long ptr;

    public native void onTransactionConfidenceChanged(C3530Wallet wallet, Transaction transaction);
}
