package org.bitcoinj.store;

import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.StoredUndoableBlock;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.core.UTXOProvider;

public interface FullPrunedBlockStore extends BlockStore, UTXOProvider {
    void abortDatabaseBatchWrite() throws BlockStoreException;

    void addUnspentTransactionOutput(UTXO utxo) throws BlockStoreException;

    void beginDatabaseBatchWrite() throws BlockStoreException;

    void commitDatabaseBatchWrite() throws BlockStoreException;

    StoredBlock getOnceUndoableStoredBlock(Sha256Hash sha256Hash) throws BlockStoreException;

    UTXO getTransactionOutput(Sha256Hash sha256Hash, long j) throws BlockStoreException;

    StoredUndoableBlock getUndoBlock(Sha256Hash sha256Hash) throws BlockStoreException;

    StoredBlock getVerifiedChainHead() throws BlockStoreException;

    boolean hasUnspentOutputs(Sha256Hash sha256Hash, int i) throws BlockStoreException;

    void put(StoredBlock storedBlock, StoredUndoableBlock storedUndoableBlock) throws BlockStoreException;

    void removeUnspentTransactionOutput(UTXO utxo) throws BlockStoreException;

    void setVerifiedChainHead(StoredBlock storedBlock) throws BlockStoreException;
}
