package org.bitcoinj.core.listeners;

import java.util.List;
import org.bitcoinj.core.AbstractBlockChain.NewBlockType;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.VerificationException;

@Deprecated
public class AbstractBlockChainListener implements BlockChainListener {
    public void notifyNewBestBlock(StoredBlock storedBlock) throws VerificationException {
    }

    public boolean notifyTransactionIsInBlock(Sha256Hash sha256Hash, StoredBlock storedBlock, NewBlockType newBlockType, int i) throws VerificationException {
        return false;
    }

    public void receiveFromBlock(Transaction transaction, StoredBlock storedBlock, NewBlockType newBlockType, int i) throws VerificationException {
    }

    public void reorganize(StoredBlock storedBlock, List<StoredBlock> list, List<StoredBlock> list2) throws VerificationException {
    }
}
