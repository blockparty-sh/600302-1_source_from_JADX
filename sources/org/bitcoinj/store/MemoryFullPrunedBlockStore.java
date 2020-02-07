package org.bitcoinj.store;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.StoredUndoableBlock;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.core.UTXOProviderException;
import org.bitcoinj.core.VerificationException;

public class MemoryFullPrunedBlockStore implements FullPrunedBlockStore {
    private TransactionalHashMap<Sha256Hash, StoredBlockAndWasUndoableFlag> blockMap = new TransactionalHashMap<>();
    private StoredBlock chainHead;
    private TransactionalMultiKeyHashMap<Sha256Hash, Integer, StoredUndoableBlock> fullBlockMap = new TransactionalMultiKeyHashMap<>();
    private int fullStoreDepth;
    private NetworkParameters params;
    private TransactionalHashMap<StoredTransactionOutPoint, UTXO> transactionOutputMap = new TransactionalHashMap<>();
    private StoredBlock verifiedChainHead;

    protected static class StoredBlockAndWasUndoableFlag {
        public StoredBlock block;
        public boolean wasUndoable;

        public StoredBlockAndWasUndoableFlag(StoredBlock storedBlock, boolean z) {
            this.block = storedBlock;
            this.wasUndoable = z;
        }
    }

    public MemoryFullPrunedBlockStore(NetworkParameters networkParameters, int i) {
        if (i <= 0) {
            i = 1;
        }
        this.fullStoreDepth = i;
        try {
            StoredBlock storedBlock = new StoredBlock(networkParameters.getGenesisBlock().cloneAsHeader(), networkParameters.getGenesisBlock().getWork(), 0);
            put(storedBlock, new StoredUndoableBlock(networkParameters.getGenesisBlock().getHash(), (List<Transaction>) Lists.newLinkedList()));
            setChainHead(storedBlock);
            setVerifiedChainHead(storedBlock);
            this.params = networkParameters;
        } catch (BlockStoreException e) {
            throw new RuntimeException(e);
        } catch (VerificationException e2) {
            throw new RuntimeException(e2);
        }
    }

    public synchronized void put(StoredBlock storedBlock) throws BlockStoreException {
        Preconditions.checkNotNull(this.blockMap, "MemoryFullPrunedBlockStore is closed");
        this.blockMap.put(storedBlock.getHeader().getHash(), new StoredBlockAndWasUndoableFlag(storedBlock, false));
    }

    public final synchronized void put(StoredBlock storedBlock, StoredUndoableBlock storedUndoableBlock) throws BlockStoreException {
        Preconditions.checkNotNull(this.blockMap, "MemoryFullPrunedBlockStore is closed");
        Sha256Hash hash = storedBlock.getHeader().getHash();
        this.fullBlockMap.put(hash, Integer.valueOf(storedBlock.getHeight()), storedUndoableBlock);
        this.blockMap.put(hash, new StoredBlockAndWasUndoableFlag(storedBlock, true));
    }

    @Nullable
    public synchronized StoredBlock get(Sha256Hash sha256Hash) throws BlockStoreException {
        StoredBlock storedBlock;
        Preconditions.checkNotNull(this.blockMap, "MemoryFullPrunedBlockStore is closed");
        StoredBlockAndWasUndoableFlag storedBlockAndWasUndoableFlag = (StoredBlockAndWasUndoableFlag) this.blockMap.get(sha256Hash);
        if (storedBlockAndWasUndoableFlag == null) {
            storedBlock = null;
        } else {
            storedBlock = storedBlockAndWasUndoableFlag.block;
        }
        return storedBlock;
    }

    @Nullable
    public synchronized StoredBlock getOnceUndoableStoredBlock(Sha256Hash sha256Hash) throws BlockStoreException {
        StoredBlockAndWasUndoableFlag storedBlockAndWasUndoableFlag;
        Preconditions.checkNotNull(this.blockMap, "MemoryFullPrunedBlockStore is closed");
        storedBlockAndWasUndoableFlag = (StoredBlockAndWasUndoableFlag) this.blockMap.get(sha256Hash);
        return (storedBlockAndWasUndoableFlag == null || !storedBlockAndWasUndoableFlag.wasUndoable) ? null : storedBlockAndWasUndoableFlag.block;
    }

    @Nullable
    public synchronized StoredUndoableBlock getUndoBlock(Sha256Hash sha256Hash) throws BlockStoreException {
        Preconditions.checkNotNull(this.fullBlockMap, "MemoryFullPrunedBlockStore is closed");
        return (StoredUndoableBlock) this.fullBlockMap.get(sha256Hash);
    }

    public synchronized StoredBlock getChainHead() throws BlockStoreException {
        Preconditions.checkNotNull(this.blockMap, "MemoryFullPrunedBlockStore is closed");
        return this.chainHead;
    }

    public final synchronized void setChainHead(StoredBlock storedBlock) throws BlockStoreException {
        Preconditions.checkNotNull(this.blockMap, "MemoryFullPrunedBlockStore is closed");
        this.chainHead = storedBlock;
    }

    public synchronized StoredBlock getVerifiedChainHead() throws BlockStoreException {
        Preconditions.checkNotNull(this.blockMap, "MemoryFullPrunedBlockStore is closed");
        return this.verifiedChainHead;
    }

    public final synchronized void setVerifiedChainHead(StoredBlock storedBlock) throws BlockStoreException {
        Preconditions.checkNotNull(this.blockMap, "MemoryFullPrunedBlockStore is closed");
        this.verifiedChainHead = storedBlock;
        if (this.chainHead.getHeight() < storedBlock.getHeight()) {
            setChainHead(storedBlock);
        }
        this.fullBlockMap.removeByMultiKey(Integer.valueOf(storedBlock.getHeight() - this.fullStoreDepth));
    }

    public void close() {
        this.blockMap = null;
        this.fullBlockMap = null;
        this.transactionOutputMap = null;
    }

    @Nullable
    public synchronized UTXO getTransactionOutput(Sha256Hash sha256Hash, long j) throws BlockStoreException {
        Preconditions.checkNotNull(this.transactionOutputMap, "MemoryFullPrunedBlockStore is closed");
        return (UTXO) this.transactionOutputMap.get(new StoredTransactionOutPoint(sha256Hash, j));
    }

    public synchronized void addUnspentTransactionOutput(UTXO utxo) throws BlockStoreException {
        Preconditions.checkNotNull(this.transactionOutputMap, "MemoryFullPrunedBlockStore is closed");
        this.transactionOutputMap.put(new StoredTransactionOutPoint(utxo), utxo);
    }

    public synchronized void removeUnspentTransactionOutput(UTXO utxo) throws BlockStoreException {
        Preconditions.checkNotNull(this.transactionOutputMap, "MemoryFullPrunedBlockStore is closed");
        if (this.transactionOutputMap.remove(new StoredTransactionOutPoint(utxo)) == null) {
            throw new BlockStoreException("Tried to remove a UTXO from MemoryFullPrunedBlockStore that it didn't have!");
        }
    }

    public synchronized void beginDatabaseBatchWrite() throws BlockStoreException {
        this.blockMap.beginDatabaseBatchWrite();
        this.fullBlockMap.BeginTransaction();
        this.transactionOutputMap.beginDatabaseBatchWrite();
    }

    public synchronized void commitDatabaseBatchWrite() throws BlockStoreException {
        this.blockMap.commitDatabaseBatchWrite();
        this.fullBlockMap.CommitTransaction();
        this.transactionOutputMap.commitDatabaseBatchWrite();
    }

    public synchronized void abortDatabaseBatchWrite() throws BlockStoreException {
        this.blockMap.abortDatabaseBatchWrite();
        this.fullBlockMap.AbortTransaction();
        this.transactionOutputMap.abortDatabaseBatchWrite();
    }

    public synchronized boolean hasUnspentOutputs(Sha256Hash sha256Hash, int i) throws BlockStoreException {
        for (int i2 = 0; i2 < i; i2++) {
            if (getTransactionOutput(sha256Hash, (long) i2) != null) {
                return true;
            }
        }
        return false;
    }

    public NetworkParameters getParams() {
        return this.params;
    }

    public int getChainHeadHeight() throws UTXOProviderException {
        try {
            return getVerifiedChainHead().getHeight();
        } catch (BlockStoreException e) {
            throw new UTXOProviderException((Throwable) e);
        }
    }

    public List<UTXO> getOpenTransactionOutputs(List<Address> list) throws UTXOProviderException {
        ArrayList arrayList = new ArrayList();
        for (UTXO utxo : this.transactionOutputMap.values()) {
            for (Address address : list) {
                if (utxo.getAddress().equals(address.toString())) {
                    arrayList.add(utxo);
                }
            }
        }
        return arrayList;
    }
}
