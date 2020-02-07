package org.bitcoinj.jni;

import java.util.List;
import org.bitcoinj.core.AbstractBlockChain.NewBlockType;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.core.listeners.NewBestBlockListener;
import org.bitcoinj.core.listeners.ReorganizeListener;
import org.bitcoinj.core.listeners.TransactionReceivedInBlockListener;

public class NativeBlockChainListener implements NewBestBlockListener, ReorganizeListener, TransactionReceivedInBlockListener {
    public long ptr;

    public native void notifyNewBestBlock(StoredBlock storedBlock) throws VerificationException;

    public native boolean notifyTransactionIsInBlock(Sha256Hash sha256Hash, StoredBlock storedBlock, NewBlockType newBlockType, int i) throws VerificationException;

    public native void receiveFromBlock(Transaction transaction, StoredBlock storedBlock, NewBlockType newBlockType, int i) throws VerificationException;

    public native void reorganize(StoredBlock storedBlock, List<StoredBlock> list, List<StoredBlock> list2) throws VerificationException;
}
