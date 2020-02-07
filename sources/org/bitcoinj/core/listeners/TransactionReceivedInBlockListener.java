package org.bitcoinj.core.listeners;

import org.bitcoinj.core.AbstractBlockChain.NewBlockType;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.VerificationException;

public interface TransactionReceivedInBlockListener {
    boolean notifyTransactionIsInBlock(Sha256Hash sha256Hash, StoredBlock storedBlock, NewBlockType newBlockType, int i) throws VerificationException;

    void receiveFromBlock(Transaction transaction, StoredBlock storedBlock, NewBlockType newBlockType, int i) throws VerificationException;
}
