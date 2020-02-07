package org.bitcoinj.jni;

import org.bitcoinj.core.TransactionConfidence;
import org.bitcoinj.core.TransactionConfidence.Listener;
import org.bitcoinj.core.TransactionConfidence.Listener.ChangeReason;

public class NativeTransactionConfidenceListener implements Listener {
    public long ptr;

    public native void onConfidenceChanged(TransactionConfidence transactionConfidence, ChangeReason changeReason);
}
