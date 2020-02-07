package org.bitcoinj.core;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.wallet.C3530Wallet;

public class BlockChain extends AbstractBlockChain {
    protected final BlockStore blockStore;

    /* access modifiers changed from: protected */
    public void notSettingChainHead() throws BlockStoreException {
    }

    /* access modifiers changed from: protected */
    public boolean shouldVerifyTransactions() {
        return false;
    }

    public BlockChain(Context context, C3530Wallet wallet, BlockStore blockStore2) throws BlockStoreException {
        this(context, (List<? extends C3530Wallet>) new ArrayList<Object>(), blockStore2);
        addWallet(wallet);
    }

    public BlockChain(NetworkParameters networkParameters, C3530Wallet wallet, BlockStore blockStore2) throws BlockStoreException {
        this(Context.getOrCreate(networkParameters), wallet, blockStore2);
    }

    public BlockChain(Context context, BlockStore blockStore2) throws BlockStoreException {
        this(context, (List<? extends C3530Wallet>) new ArrayList<Object>(), blockStore2);
    }

    public BlockChain(NetworkParameters networkParameters, BlockStore blockStore2) throws BlockStoreException {
        this(networkParameters, (List<? extends C3530Wallet>) new ArrayList<Object>(), blockStore2);
    }

    public BlockChain(Context context, List<? extends C3530Wallet> list, BlockStore blockStore2) throws BlockStoreException {
        super(context, list, blockStore2);
        this.blockStore = blockStore2;
    }

    public BlockChain(NetworkParameters networkParameters, List<? extends C3530Wallet> list, BlockStore blockStore2) throws BlockStoreException {
        this(Context.getOrCreate(networkParameters), list, blockStore2);
    }

    /* access modifiers changed from: protected */
    public StoredBlock addToBlockStore(StoredBlock storedBlock, Block block, TransactionOutputChanges transactionOutputChanges) throws BlockStoreException, VerificationException {
        StoredBlock build = storedBlock.build(block);
        this.blockStore.put(build);
        return build;
    }

    /* access modifiers changed from: protected */
    public StoredBlock addToBlockStore(StoredBlock storedBlock, Block block) throws BlockStoreException, VerificationException {
        StoredBlock build = storedBlock.build(block);
        this.blockStore.put(build);
        return build;
    }

    /* access modifiers changed from: protected */
    public void rollbackBlockStore(int i) throws BlockStoreException {
        this.lock.lock();
        try {
            int bestChainHeight = getBestChainHeight();
            Preconditions.checkArgument(i >= 0 && i <= bestChainHeight, "Bad height: %s", Integer.valueOf(i));
            if (i != bestChainHeight) {
                StoredBlock chainHead = this.blockStore.getChainHead();
                while (chainHead.getHeight() > i) {
                    chainHead = chainHead.getPrev(this.blockStore);
                    if (chainHead == null) {
                        throw new BlockStoreException("Unreachable height");
                    }
                }
                this.blockStore.put(chainHead);
                setChainHead(chainHead);
                this.lock.unlock();
            }
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public TransactionOutputChanges connectTransactions(int i, Block block) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public TransactionOutputChanges connectTransactions(StoredBlock storedBlock) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public void disconnectTransactions(StoredBlock storedBlock) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public void doSetChainHead(StoredBlock storedBlock) throws BlockStoreException {
        this.blockStore.setChainHead(storedBlock);
    }

    /* access modifiers changed from: protected */
    public StoredBlock getStoredBlockInCurrentScope(Sha256Hash sha256Hash) throws BlockStoreException {
        return this.blockStore.get(sha256Hash);
    }

    public boolean add(FilteredBlock filteredBlock) throws VerificationException, PrunedException {
        boolean add = super.add(filteredBlock);
        if (add) {
            trackFilteredTransactions(filteredBlock.getTransactionCount());
        }
        return add;
    }
}
