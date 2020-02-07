package org.bitcoinj.store;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.VerificationException;

public class MemoryBlockStore implements BlockStore {
    /* access modifiers changed from: private */
    public LinkedHashMap<Sha256Hash, StoredBlock> blockMap = new LinkedHashMap<Sha256Hash, StoredBlock>() {
        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Entry<Sha256Hash, StoredBlock> entry) {
            return MemoryBlockStore.this.blockMap.size() > 5000;
        }
    };
    private StoredBlock chainHead;
    private NetworkParameters params;

    public MemoryBlockStore(NetworkParameters networkParameters) {
        try {
            Block cloneAsHeader = networkParameters.getGenesisBlock().cloneAsHeader();
            StoredBlock storedBlock = new StoredBlock(cloneAsHeader, cloneAsHeader.getWork(), 0);
            put(storedBlock);
            setChainHead(storedBlock);
            this.params = networkParameters;
        } catch (BlockStoreException e) {
            throw new RuntimeException(e);
        } catch (VerificationException e2) {
            throw new RuntimeException(e2);
        }
    }

    public final synchronized void put(StoredBlock storedBlock) throws BlockStoreException {
        if (this.blockMap != null) {
            this.blockMap.put(storedBlock.getHeader().getHash(), storedBlock);
        } else {
            throw new BlockStoreException("MemoryBlockStore is closed");
        }
    }

    public synchronized StoredBlock get(Sha256Hash sha256Hash) throws BlockStoreException {
        if (this.blockMap != null) {
        } else {
            throw new BlockStoreException("MemoryBlockStore is closed");
        }
        return (StoredBlock) this.blockMap.get(sha256Hash);
    }

    public StoredBlock getChainHead() throws BlockStoreException {
        if (this.blockMap != null) {
            return this.chainHead;
        }
        throw new BlockStoreException("MemoryBlockStore is closed");
    }

    public final void setChainHead(StoredBlock storedBlock) throws BlockStoreException {
        if (this.blockMap != null) {
            this.chainHead = storedBlock;
            return;
        }
        throw new BlockStoreException("MemoryBlockStore is closed");
    }

    public void close() {
        this.blockMap = null;
    }

    public NetworkParameters getParams() {
        return this.params;
    }
}
