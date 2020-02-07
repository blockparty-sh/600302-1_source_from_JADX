package org.bitcoinj.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import org.bitcoinj.core.VerificationException.BlockVersionOutOfDate;
import org.bitcoinj.core.listeners.BlockChainListener;
import org.bitcoinj.core.listeners.NewBestBlockListener;
import org.bitcoinj.core.listeners.ReorganizeListener;
import org.bitcoinj.core.listeners.TransactionReceivedInBlockListener;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.utils.ListenerRegistration;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.utils.VersionTally;
import org.bitcoinj.wallet.C3530Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBlockChain {
    public static final double FP_ESTIMATOR_ALPHA = 1.0E-4d;
    public static final double FP_ESTIMATOR_BETA = 0.01d;
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(AbstractBlockChain.class);
    private final BlockStore blockStore;
    protected StoredBlock chainHead;
    private final Object chainHeadLock;
    private double falsePositiveRate;
    private double falsePositiveTrend;
    protected final ReentrantLock lock;
    private final CopyOnWriteArrayList<ListenerRegistration<NewBestBlockListener>> newBestBlockListeners;
    private final LinkedHashMap<Sha256Hash, OrphanBlock> orphanBlocks;
    protected final NetworkParameters params;
    private double previousFalsePositiveRate;
    private final CopyOnWriteArrayList<ListenerRegistration<ReorganizeListener>> reorganizeListeners;
    private final CopyOnWriteArrayList<ListenerRegistration<TransactionReceivedInBlockListener>> transactionReceivedListeners;
    private final VersionTally versionTally;

    public enum NewBlockType {
        BEST_CHAIN,
        SIDE_CHAIN
    }

    class OrphanBlock {
        final Block block;
        final List<Sha256Hash> filteredTxHashes;
        final Map<Sha256Hash, Transaction> filteredTxn;

        OrphanBlock(Block block2, @Nullable List<Sha256Hash> list, @Nullable Map<Sha256Hash, Transaction> map) {
            boolean z = true;
            boolean z2 = (list == null || map == null) ? false : true;
            if ((block2.transactions != null || !z2) && (block2.transactions == null || z2)) {
                z = false;
            }
            Preconditions.checkArgument(z);
            this.block = block2;
            this.filteredTxHashes = list;
            this.filteredTxn = map;
        }
    }

    /* access modifiers changed from: protected */
    public abstract StoredBlock addToBlockStore(StoredBlock storedBlock, Block block) throws BlockStoreException, VerificationException;

    /* access modifiers changed from: protected */
    public abstract StoredBlock addToBlockStore(StoredBlock storedBlock, Block block, @Nullable TransactionOutputChanges transactionOutputChanges) throws BlockStoreException, VerificationException;

    /* access modifiers changed from: protected */
    public abstract TransactionOutputChanges connectTransactions(int i, Block block) throws VerificationException, BlockStoreException;

    /* access modifiers changed from: protected */
    public abstract TransactionOutputChanges connectTransactions(StoredBlock storedBlock) throws VerificationException, BlockStoreException, PrunedException;

    /* access modifiers changed from: protected */
    public abstract void disconnectTransactions(StoredBlock storedBlock) throws PrunedException, BlockStoreException;

    /* access modifiers changed from: protected */
    public abstract void doSetChainHead(StoredBlock storedBlock) throws BlockStoreException;

    /* access modifiers changed from: protected */
    public abstract StoredBlock getStoredBlockInCurrentScope(Sha256Hash sha256Hash) throws BlockStoreException;

    /* access modifiers changed from: protected */
    public abstract void notSettingChainHead() throws BlockStoreException;

    /* access modifiers changed from: protected */
    public abstract void rollbackBlockStore(int i) throws BlockStoreException;

    /* access modifiers changed from: protected */
    public abstract boolean shouldVerifyTransactions();

    public AbstractBlockChain(NetworkParameters networkParameters, List<? extends C3530Wallet> list, BlockStore blockStore2) throws BlockStoreException {
        this(Context.getOrCreate(networkParameters), list, blockStore2);
    }

    public AbstractBlockChain(Context context, List<? extends C3530Wallet> list, BlockStore blockStore2) throws BlockStoreException {
        this.lock = Threading.lock("blockchain");
        this.chainHeadLock = new Object();
        this.orphanBlocks = new LinkedHashMap<>();
        this.blockStore = blockStore2;
        this.chainHead = blockStore2.getChainHead();
        log.info("chain head is at height {}:\n{}", (Object) Integer.valueOf(this.chainHead.getHeight()), (Object) this.chainHead.getHeader());
        this.params = context.getParams();
        this.newBestBlockListeners = new CopyOnWriteArrayList<>();
        this.reorganizeListeners = new CopyOnWriteArrayList<>();
        this.transactionReceivedListeners = new CopyOnWriteArrayList<>();
        for (NewBestBlockListener addNewBestBlockListener : list) {
            addNewBestBlockListener(Threading.SAME_THREAD, addNewBestBlockListener);
        }
        for (ReorganizeListener addReorganizeListener : list) {
            addReorganizeListener(Threading.SAME_THREAD, addReorganizeListener);
        }
        for (TransactionReceivedInBlockListener addTransactionReceivedListener : list) {
            addTransactionReceivedListener(Threading.SAME_THREAD, addTransactionReceivedListener);
        }
        this.versionTally = new VersionTally(context.getParams());
        this.versionTally.initialize(blockStore2, this.chainHead);
    }

    public final void addWallet(C3530Wallet wallet) {
        addNewBestBlockListener(Threading.SAME_THREAD, wallet);
        addReorganizeListener(Threading.SAME_THREAD, wallet);
        addTransactionReceivedListener(Threading.SAME_THREAD, wallet);
        int lastBlockSeenHeight = wallet.getLastBlockSeenHeight();
        int bestChainHeight = getBestChainHeight();
        if (lastBlockSeenHeight != bestChainHeight) {
            log.warn("Wallet/chain height mismatch: {} vs {}", (Object) Integer.valueOf(lastBlockSeenHeight), (Object) Integer.valueOf(bestChainHeight));
            log.warn("Hashes: {} vs {}", (Object) wallet.getLastBlockSeenHash(), (Object) getChainHead().getHeader().getHash());
            if (lastBlockSeenHeight < bestChainHeight && lastBlockSeenHeight > 0) {
                try {
                    rollbackBlockStore(lastBlockSeenHeight);
                    log.info("Rolled back block store to height {}.", (Object) Integer.valueOf(lastBlockSeenHeight));
                } catch (BlockStoreException unused) {
                    log.warn("Rollback of block store failed, continuing with mismatched heights. This can happen due to a replay.");
                }
            }
        }
    }

    public void removeWallet(C3530Wallet wallet) {
        removeNewBestBlockListener(wallet);
        removeReorganizeListener(wallet);
        removeTransactionReceivedListener(wallet);
    }

    @Deprecated
    public void addListener(BlockChainListener blockChainListener) {
        addListener(blockChainListener, Threading.USER_THREAD);
    }

    @Deprecated
    public void addListener(BlockChainListener blockChainListener, Executor executor) {
        addReorganizeListener(executor, blockChainListener);
        addNewBestBlockListener(executor, blockChainListener);
        addTransactionReceivedListener(executor, blockChainListener);
    }

    @Deprecated
    public void removeListener(BlockChainListener blockChainListener) {
        removeReorganizeListener(blockChainListener);
        removeNewBestBlockListener(blockChainListener);
        removeTransactionReceivedListener(blockChainListener);
    }

    public void addNewBestBlockListener(NewBestBlockListener newBestBlockListener) {
        addNewBestBlockListener(Threading.USER_THREAD, newBestBlockListener);
    }

    public final void addNewBestBlockListener(Executor executor, NewBestBlockListener newBestBlockListener) {
        this.newBestBlockListeners.add(new ListenerRegistration(newBestBlockListener, executor));
    }

    public void addReorganizeListener(ReorganizeListener reorganizeListener) {
        addReorganizeListener(Threading.USER_THREAD, reorganizeListener);
    }

    public final void addReorganizeListener(Executor executor, ReorganizeListener reorganizeListener) {
        this.reorganizeListeners.add(new ListenerRegistration(reorganizeListener, executor));
    }

    public void addTransactionReceivedListener(TransactionReceivedInBlockListener transactionReceivedInBlockListener) {
        addTransactionReceivedListener(Threading.USER_THREAD, transactionReceivedInBlockListener);
    }

    public final void addTransactionReceivedListener(Executor executor, TransactionReceivedInBlockListener transactionReceivedInBlockListener) {
        this.transactionReceivedListeners.add(new ListenerRegistration(transactionReceivedInBlockListener, executor));
    }

    public void removeNewBestBlockListener(NewBestBlockListener newBestBlockListener) {
        ListenerRegistration.removeFromList(newBestBlockListener, this.newBestBlockListeners);
    }

    public void removeReorganizeListener(ReorganizeListener reorganizeListener) {
        ListenerRegistration.removeFromList(reorganizeListener, this.reorganizeListeners);
    }

    public void removeTransactionReceivedListener(TransactionReceivedInBlockListener transactionReceivedInBlockListener) {
        ListenerRegistration.removeFromList(transactionReceivedInBlockListener, this.transactionReceivedListeners);
    }

    public BlockStore getBlockStore() {
        return this.blockStore;
    }

    public boolean add(Block block) throws VerificationException, PrunedException {
        try {
            return add(block, true, null, null);
        } catch (BlockStoreException e) {
            throw new RuntimeException(e);
        } catch (VerificationException e2) {
            try {
                notSettingChainHead();
                StringBuilder sb = new StringBuilder();
                sb.append("Could not verify block:\n");
                sb.append(block.toString());
                throw new VerificationException(sb.toString(), e2);
            } catch (BlockStoreException e3) {
                throw new RuntimeException(e3);
            }
        }
    }

    public boolean add(FilteredBlock filteredBlock) throws VerificationException, PrunedException {
        try {
            return add(filteredBlock.getBlockHeader(), true, filteredBlock.getTransactionHashes(), filteredBlock.getAssociatedTransactions());
        } catch (BlockStoreException e) {
            throw new RuntimeException(e);
        } catch (VerificationException e2) {
            try {
                notSettingChainHead();
                StringBuilder sb = new StringBuilder();
                sb.append("Could not verify block ");
                sb.append(filteredBlock.getHash().toString());
                sb.append("\n");
                sb.append(filteredBlock.toString());
                throw new VerificationException(sb.toString(), e2);
            } catch (BlockStoreException e3) {
                throw new RuntimeException(e3);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        if (r9.orphanBlocks.containsKey(r10.getHash()) != false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean add(org.bitcoinj.core.Block r10, boolean r11, @javax.annotation.Nullable java.util.List<org.bitcoinj.core.Sha256Hash> r12, @javax.annotation.Nullable java.util.Map<org.bitcoinj.core.Sha256Hash, org.bitcoinj.core.Transaction> r13) throws org.bitcoinj.store.BlockStoreException, org.bitcoinj.core.VerificationException, org.bitcoinj.core.PrunedException {
        /*
            r9 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r9.lock
            r0.lock()
            org.bitcoinj.core.StoredBlock r0 = r9.getChainHead()     // Catch:{ all -> 0x00d7 }
            org.bitcoinj.core.Block r0 = r0.getHeader()     // Catch:{ all -> 0x00d7 }
            boolean r0 = r10.equals(r0)     // Catch:{ all -> 0x00d7 }
            r1 = 1
            if (r0 == 0) goto L_0x001a
        L_0x0014:
            java.util.concurrent.locks.ReentrantLock r10 = r9.lock
            r10.unlock()
            return r1
        L_0x001a:
            r0 = 0
            if (r11 == 0) goto L_0x002f
            java.util.LinkedHashMap<org.bitcoinj.core.Sha256Hash, org.bitcoinj.core.AbstractBlockChain$OrphanBlock> r2 = r9.orphanBlocks     // Catch:{ all -> 0x00d7 }
            org.bitcoinj.core.Sha256Hash r3 = r10.getHash()     // Catch:{ all -> 0x00d7 }
            boolean r2 = r2.containsKey(r3)     // Catch:{ all -> 0x00d7 }
            if (r2 == 0) goto L_0x002f
        L_0x0029:
            java.util.concurrent.locks.ReentrantLock r10 = r9.lock
            r10.unlock()
            return r0
        L_0x002f:
            boolean r2 = r9.shouldVerifyTransactions()     // Catch:{ all -> 0x00d7 }
            if (r2 == 0) goto L_0x0042
            java.util.List<org.bitcoinj.core.Transaction> r2 = r10.transactions     // Catch:{ all -> 0x00d7 }
            if (r2 == 0) goto L_0x003a
            goto L_0x0042
        L_0x003a:
            org.bitcoinj.core.VerificationException r10 = new org.bitcoinj.core.VerificationException     // Catch:{ all -> 0x00d7 }
            java.lang.String r11 = "Got a block header while running in full-block mode"
            r10.<init>(r11)     // Catch:{ all -> 0x00d7 }
            throw r10     // Catch:{ all -> 0x00d7 }
        L_0x0042:
            boolean r2 = r9.shouldVerifyTransactions()     // Catch:{ all -> 0x00d7 }
            if (r2 == 0) goto L_0x0055
            org.bitcoinj.store.BlockStore r2 = r9.blockStore     // Catch:{ all -> 0x00d7 }
            org.bitcoinj.core.Sha256Hash r3 = r10.getHash()     // Catch:{ all -> 0x00d7 }
            org.bitcoinj.core.StoredBlock r2 = r2.get(r3)     // Catch:{ all -> 0x00d7 }
            if (r2 == 0) goto L_0x0055
            goto L_0x0014
        L_0x0055:
            r10.verifyHeader()     // Catch:{ VerificationException -> 0x00c5 }
            org.bitcoinj.core.Sha256Hash r2 = r10.getPrevBlockHash()     // Catch:{ VerificationException -> 0x00c5 }
            org.bitcoinj.core.StoredBlock r5 = r9.getStoredBlockInCurrentScope(r2)     // Catch:{ VerificationException -> 0x00c5 }
            if (r5 == 0) goto L_0x0068
            int r2 = r5.getHeight()     // Catch:{ VerificationException -> 0x00c5 }
            int r2 = r2 + r1
            goto L_0x0069
        L_0x0068:
            r2 = -1
        L_0x0069:
            org.bitcoinj.core.NetworkParameters r3 = r9.params     // Catch:{ VerificationException -> 0x00c5 }
            org.bitcoinj.utils.VersionTally r4 = r9.versionTally     // Catch:{ VerificationException -> 0x00c5 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)     // Catch:{ VerificationException -> 0x00c5 }
            java.util.EnumSet r3 = r3.getBlockVerificationFlags(r10, r4, r6)     // Catch:{ VerificationException -> 0x00c5 }
            boolean r4 = r9.shouldVerifyTransactions()     // Catch:{ VerificationException -> 0x00c5 }
            if (r4 == 0) goto L_0x007e
            r10.verifyTransactions(r2, r3)     // Catch:{ VerificationException -> 0x00c5 }
        L_0x007e:
            if (r5 != 0) goto L_0x00a3
            java.lang.String r1 = "bug in tryConnectingOrphans"
            com.google.common.base.Preconditions.checkState(r11, r1)     // Catch:{ all -> 0x00d7 }
            org.slf4j.Logger r11 = log     // Catch:{ all -> 0x00d7 }
            java.lang.String r1 = "Block does not connect: {} prev {}"
            java.lang.String r2 = r10.getHashAsString()     // Catch:{ all -> 0x00d7 }
            org.bitcoinj.core.Sha256Hash r3 = r10.getPrevBlockHash()     // Catch:{ all -> 0x00d7 }
            r11.warn(r1, r2, r3)     // Catch:{ all -> 0x00d7 }
            java.util.LinkedHashMap<org.bitcoinj.core.Sha256Hash, org.bitcoinj.core.AbstractBlockChain$OrphanBlock> r11 = r9.orphanBlocks     // Catch:{ all -> 0x00d7 }
            org.bitcoinj.core.Sha256Hash r1 = r10.getHash()     // Catch:{ all -> 0x00d7 }
            org.bitcoinj.core.AbstractBlockChain$OrphanBlock r2 = new org.bitcoinj.core.AbstractBlockChain$OrphanBlock     // Catch:{ all -> 0x00d7 }
            r2.<init>(r10, r12, r13)     // Catch:{ all -> 0x00d7 }
            r11.put(r1, r2)     // Catch:{ all -> 0x00d7 }
            goto L_0x0029
        L_0x00a3:
            java.util.concurrent.locks.ReentrantLock r0 = r9.lock     // Catch:{ all -> 0x00d7 }
            boolean r0 = r0.isHeldByCurrentThread()     // Catch:{ all -> 0x00d7 }
            com.google.common.base.Preconditions.checkState(r0)     // Catch:{ all -> 0x00d7 }
            org.bitcoinj.core.NetworkParameters r0 = r9.params     // Catch:{ all -> 0x00d7 }
            org.bitcoinj.store.BlockStore r2 = r9.blockStore     // Catch:{ all -> 0x00d7 }
            r0.checkDifficultyTransitions(r5, r10, r2, r9)     // Catch:{ all -> 0x00d7 }
            boolean r6 = r9.shouldVerifyTransactions()     // Catch:{ all -> 0x00d7 }
            r3 = r9
            r4 = r10
            r7 = r12
            r8 = r13
            r3.connectBlock(r4, r5, r6, r7, r8)     // Catch:{ all -> 0x00d7 }
            if (r11 == 0) goto L_0x0014
            r9.tryConnectingOrphans()     // Catch:{ all -> 0x00d7 }
            goto L_0x0014
        L_0x00c5:
            r11 = move-exception
            org.slf4j.Logger r12 = log     // Catch:{ all -> 0x00d7 }
            java.lang.String r13 = "Failed to verify block: "
            r12.error(r13, r11)     // Catch:{ all -> 0x00d7 }
            org.slf4j.Logger r12 = log     // Catch:{ all -> 0x00d7 }
            java.lang.String r10 = r10.getHashAsString()     // Catch:{ all -> 0x00d7 }
            r12.error(r10)     // Catch:{ all -> 0x00d7 }
            throw r11     // Catch:{ all -> 0x00d7 }
        L_0x00d7:
            r10 = move-exception
            java.util.concurrent.locks.ReentrantLock r11 = r9.lock
            r11.unlock()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.AbstractBlockChain.add(org.bitcoinj.core.Block, boolean, java.util.List, java.util.Map):boolean");
    }

    public Set<Sha256Hash> drainOrphanBlocks() {
        this.lock.lock();
        try {
            HashSet hashSet = new HashSet(this.orphanBlocks.keySet());
            this.orphanBlocks.clear();
            return hashSet;
        } finally {
            this.lock.unlock();
        }
    }

    private void connectBlock(Block block, StoredBlock storedBlock, boolean z, @Nullable List<Sha256Hash> list, @Nullable Map<Sha256Hash, Transaction> map) throws BlockStoreException, VerificationException, PrunedException {
        Block block2;
        Block block3 = block;
        StoredBlock storedBlock2 = storedBlock;
        boolean z2 = z;
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        boolean z3 = (list == null || map == null) ? false : true;
        if (this.params.passesCheckpoint(storedBlock.getHeight() + 1, block.getHash())) {
            if (shouldVerifyTransactions()) {
                Preconditions.checkNotNull(block3.transactions);
                for (Transaction isFinal : block3.transactions) {
                    if (!isFinal.isFinal(storedBlock.getHeight() + 1, block.getTimeSeconds())) {
                        throw new VerificationException("Block contains non-final transaction");
                    }
                }
            }
            StoredBlock chainHead2 = getChainHead();
            if (storedBlock2.equals(chainHead2)) {
                if (z3 && map.size() > 0) {
                    log.debug("Block {} connects to top of best chain with {} transaction(s) of which we were sent {}", block.getHashAsString(), Integer.valueOf(list.size()), Integer.valueOf(map.size()));
                    for (Sha256Hash debug : list) {
                        log.debug("  matched tx {}", (Object) debug);
                    }
                }
                if (!z2 || block.getTimeSeconds() > getMedianTimestampOfRecentBlocks(chainHead2, this.blockStore)) {
                    if (block.getVersion() == 2 || block.getVersion() == 3) {
                        Integer countAtOrAbove = this.versionTally.getCountAtOrAbove(block.getVersion() + 1);
                        if (countAtOrAbove != null && countAtOrAbove.intValue() >= this.params.getMajorityRejectBlockOutdated()) {
                            throw new BlockVersionOutOfDate(block.getVersion());
                        }
                    }
                    TransactionOutputChanges transactionOutputChanges = null;
                    if (shouldVerifyTransactions()) {
                        transactionOutputChanges = connectTransactions(storedBlock.getHeight() + 1, block);
                    }
                    if (block3.transactions == null) {
                        block2 = block3;
                    } else {
                        block2 = block.cloneAsHeader();
                    }
                    StoredBlock addToBlockStore = addToBlockStore(storedBlock2, block2, transactionOutputChanges);
                    this.versionTally.add(block.getVersion());
                    setChainHead(addToBlockStore);
                    log.debug("Chain is now {} blocks high, running listeners", (Object) Integer.valueOf(addToBlockStore.getHeight()));
                    informListenersForNewBlock(block, NewBlockType.BEST_CHAIN, list, map, addToBlockStore);
                } else {
                    throw new VerificationException("Block's timestamp is too early");
                }
            } else {
                StoredBlock build = storedBlock2.build(block);
                boolean moreWorkThan = build.moreWorkThan(chainHead2);
                if (moreWorkThan) {
                    log.info("Block is causing a re-organize");
                } else {
                    StoredBlock findSplit = findSplit(build, chainHead2, this.blockStore);
                    if (findSplit != null && findSplit.equals(build)) {
                        log.warn("Saw duplicated block in main chain at height {}: {}", (Object) Integer.valueOf(build.getHeight()), (Object) build.getHeader().getHash());
                        return;
                    } else if (findSplit != null) {
                        addToBlockStore(storedBlock2, block);
                        int height = findSplit.getHeight();
                        log.info("Block forks the chain at height {}/block {}, but it did not cause a reorganize:\n{}", Integer.valueOf(height), findSplit.getHeader().getHashAsString(), build.getHeader().getHashAsString());
                    } else {
                        throw new VerificationException("Block forks the chain but splitPoint is null");
                    }
                }
                if (block3.transactions != null || z3) {
                    informListenersForNewBlock(block, NewBlockType.SIDE_CHAIN, list, map, build);
                }
                if (moreWorkThan) {
                    handleNewBestChain(storedBlock2, build, block, z2);
                }
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Block failed checkpoint lockin at ");
        sb.append(storedBlock.getHeight() + 1);
        throw new VerificationException(sb.toString());
    }

    private void informListenersForNewBlock(Block block, NewBlockType newBlockType, @Nullable List<Sha256Hash> list, @Nullable Map<Sha256Hash, Transaction> map, StoredBlock storedBlock) throws VerificationException {
        final NewBlockType newBlockType2 = newBlockType;
        List<Sha256Hash> list2 = list;
        final StoredBlock storedBlock2 = storedBlock;
        HashSet newHashSet = Sets.newHashSet();
        if (list2 != null) {
            newHashSet.addAll(list2);
        }
        Iterator it = this.transactionReceivedListeners.iterator();
        boolean z = true;
        while (it.hasNext()) {
            final ListenerRegistration listenerRegistration = (ListenerRegistration) it.next();
            if (listenerRegistration.executor == Threading.SAME_THREAD) {
                informListenerForNewTransactions(block, newBlockType, list, map, storedBlock, z, (TransactionReceivedInBlockListener) listenerRegistration.listener, newHashSet);
            } else {
                final boolean z2 = !z;
                Executor executor = listenerRegistration.executor;
                final Block block2 = block;
                final NewBlockType newBlockType3 = newBlockType;
                final List<Sha256Hash> list3 = list;
                final Map<Sha256Hash, Transaction> map2 = map;
                C34001 r11 = r0;
                final StoredBlock storedBlock3 = storedBlock;
                C34001 r0 = new Runnable() {
                    public void run() {
                        try {
                            AbstractBlockChain.informListenerForNewTransactions(block2, newBlockType3, list3, map2, storedBlock3, z2, (TransactionReceivedInBlockListener) listenerRegistration.listener, Sets.newHashSet());
                        } catch (VerificationException e) {
                            AbstractBlockChain.log.error("Block chain listener threw exception: ", (Throwable) e);
                        }
                    }
                };
                executor.execute(r11);
            }
            z = false;
            List<Sha256Hash> list4 = list;
        }
        Iterator it2 = this.newBestBlockListeners.iterator();
        while (it2.hasNext()) {
            final ListenerRegistration listenerRegistration2 = (ListenerRegistration) it2.next();
            if (listenerRegistration2.executor != Threading.SAME_THREAD) {
                listenerRegistration2.executor.execute(new Runnable() {
                    public void run() {
                        try {
                            if (newBlockType2 == NewBlockType.BEST_CHAIN) {
                                ((NewBestBlockListener) listenerRegistration2.listener).notifyNewBestBlock(storedBlock2);
                            }
                        } catch (VerificationException e) {
                            AbstractBlockChain.log.error("Block chain listener threw exception: ", (Throwable) e);
                        }
                    }
                });
            } else if (newBlockType2 == NewBlockType.BEST_CHAIN) {
                ((NewBestBlockListener) listenerRegistration2.listener).notifyNewBestBlock(storedBlock2);
            }
        }
        trackFalsePositives(newHashSet.size());
    }

    /* access modifiers changed from: private */
    public static void informListenerForNewTransactions(Block block, NewBlockType newBlockType, @Nullable List<Sha256Hash> list, @Nullable Map<Sha256Hash, Transaction> map, StoredBlock storedBlock, boolean z, TransactionReceivedInBlockListener transactionReceivedInBlockListener, Set<Sha256Hash> set) throws VerificationException {
        if (block.transactions != null) {
            sendTransactionsToListener(storedBlock, newBlockType, transactionReceivedInBlockListener, 0, block.transactions, !z, set);
        } else if (list != null) {
            Preconditions.checkNotNull(map);
            int i = 0;
            for (Sha256Hash sha256Hash : list) {
                Transaction transaction = (Transaction) map.get(sha256Hash);
                if (transaction != null) {
                    sendTransactionsToListener(storedBlock, newBlockType, transactionReceivedInBlockListener, i, Collections.singletonList(transaction), !z, set);
                } else if (transactionReceivedInBlockListener.notifyTransactionIsInBlock(sha256Hash, storedBlock, newBlockType, i)) {
                    set.remove(sha256Hash);
                }
                i++;
            }
        }
    }

    public static long getMedianTimestampOfRecentBlocks(StoredBlock storedBlock, BlockStore blockStore2) throws BlockStoreException {
        long[] jArr = new long[11];
        jArr[10] = storedBlock.getHeader().getTimeSeconds();
        int i = 9;
        while (i >= 0) {
            storedBlock = storedBlock.getPrev(blockStore2);
            if (storedBlock == null) {
                break;
            }
            int i2 = i - 1;
            jArr[i] = storedBlock.getHeader().getTimeSeconds();
            i = i2;
        }
        Arrays.sort(jArr, i + 1, 11);
        return jArr[i + ((11 - i) / 2)];
    }

    private void handleNewBestChain(StoredBlock storedBlock, StoredBlock storedBlock2, Block block, boolean z) throws BlockStoreException, VerificationException, PrunedException {
        StoredBlock storedBlock3;
        TransactionOutputChanges transactionOutputChanges;
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        StoredBlock chainHead2 = getChainHead();
        StoredBlock findSplit = findSplit(storedBlock2, chainHead2, this.blockStore);
        log.info("Re-organize after split at height {}", (Object) Integer.valueOf(findSplit.getHeight()));
        log.info("Old chain head: {}", (Object) chainHead2.getHeader().getHashAsString());
        log.info("New chain head: {}", (Object) storedBlock2.getHeader().getHashAsString());
        log.info("Split at block: {}", (Object) findSplit.getHeader().getHashAsString());
        LinkedList partialChain = getPartialChain(chainHead2, findSplit, this.blockStore);
        LinkedList partialChain2 = getPartialChain(storedBlock2, findSplit, this.blockStore);
        if (shouldVerifyTransactions()) {
            Iterator it = partialChain.iterator();
            while (it.hasNext()) {
                try {
                    disconnectTransactions((StoredBlock) it.next());
                } catch (PrunedException e) {
                    throw e;
                }
            }
            Iterator descendingIterator = partialChain2.descendingIterator();
            storedBlock3 = findSplit;
            while (descendingIterator.hasNext()) {
                StoredBlock storedBlock4 = (StoredBlock) descendingIterator.next();
                Block header = storedBlock4.getHeader();
                if (!z || header.getTimeSeconds() > getMedianTimestampOfRecentBlocks(storedBlock4.getPrev(this.blockStore), this.blockStore)) {
                    if (storedBlock4 != storedBlock2 || block == null) {
                        transactionOutputChanges = connectTransactions(storedBlock4);
                    } else {
                        transactionOutputChanges = connectTransactions(storedBlock2.getHeight(), block);
                    }
                    storedBlock3 = addToBlockStore(storedBlock3, header.cloneAsHeader(), transactionOutputChanges);
                } else {
                    throw new VerificationException("Block's timestamp is too early during reorg");
                }
            }
        } else {
            storedBlock3 = addToBlockStore(storedBlock, storedBlock2.getHeader());
        }
        StoredBlock storedBlock5 = storedBlock3;
        Iterator it2 = this.reorganizeListeners.iterator();
        while (it2.hasNext()) {
            final ListenerRegistration listenerRegistration = (ListenerRegistration) it2.next();
            if (listenerRegistration.executor == Threading.SAME_THREAD) {
                ((ReorganizeListener) listenerRegistration.listener).reorganize(findSplit, partialChain, partialChain2);
            } else {
                Executor executor = listenerRegistration.executor;
                final StoredBlock storedBlock6 = findSplit;
                final LinkedList linkedList = partialChain;
                final LinkedList linkedList2 = partialChain2;
                C34023 r2 = new Runnable() {
                    public void run() {
                        try {
                            ((ReorganizeListener) listenerRegistration.listener).reorganize(storedBlock6, linkedList, linkedList2);
                        } catch (VerificationException e) {
                            AbstractBlockChain.log.error("Block chain listener threw exception during reorg", (Throwable) e);
                        }
                    }
                };
                executor.execute(r2);
            }
        }
        setChainHead(storedBlock5);
    }

    private static LinkedList<StoredBlock> getPartialChain(StoredBlock storedBlock, StoredBlock storedBlock2, BlockStore blockStore2) throws BlockStoreException {
        Preconditions.checkArgument(storedBlock.getHeight() > storedBlock2.getHeight(), "higher and lower are reversed");
        LinkedList<StoredBlock> linkedList = new LinkedList<>();
        do {
            linkedList.add(storedBlock);
            storedBlock = (StoredBlock) Preconditions.checkNotNull(storedBlock.getPrev(blockStore2), "Ran off the end of the chain");
        } while (!storedBlock.equals(storedBlock2));
        return linkedList;
    }

    private static StoredBlock findSplit(StoredBlock storedBlock, StoredBlock storedBlock2, BlockStore blockStore2) throws BlockStoreException {
        while (!storedBlock2.equals(storedBlock)) {
            String str = "Attempt to follow an orphan chain";
            if (storedBlock2.getHeight() > storedBlock.getHeight()) {
                storedBlock2 = storedBlock2.getPrev(blockStore2);
                Preconditions.checkNotNull(storedBlock2, str);
            } else {
                storedBlock = storedBlock.getPrev(blockStore2);
                Preconditions.checkNotNull(storedBlock, str);
            }
        }
        return storedBlock2;
    }

    public final int getBestChainHeight() {
        return getChainHead().getHeight();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        r7 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        throw new java.lang.RuntimeException(r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0031 A[ExcHandler: ProtocolException (r4v1 'e' org.bitcoinj.core.ProtocolException A[CUSTOM_DECLARE]), Splitter:B:4:0x0010] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void sendTransactionsToListener(org.bitcoinj.core.StoredBlock r4, org.bitcoinj.core.AbstractBlockChain.NewBlockType r5, org.bitcoinj.core.listeners.TransactionReceivedInBlockListener r6, int r7, java.util.List<org.bitcoinj.core.Transaction> r8, boolean r9, java.util.Set<org.bitcoinj.core.Sha256Hash> r10) throws org.bitcoinj.core.VerificationException {
        /*
            java.util.Iterator r8 = r8.iterator()
        L_0x0004:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x0054
            java.lang.Object r0 = r8.next()
            org.bitcoinj.core.Transaction r0 = (org.bitcoinj.core.Transaction) r0
            org.bitcoinj.core.Sha256Hash r1 = r0.getHash()     // Catch:{ ScriptException -> 0x0038, ProtocolException -> 0x0031 }
            r10.remove(r1)     // Catch:{ ScriptException -> 0x0038, ProtocolException -> 0x0031 }
            if (r9 == 0) goto L_0x0027
            org.bitcoinj.core.NetworkParameters r1 = r0.params     // Catch:{ ScriptException -> 0x0038, ProtocolException -> 0x0031 }
            org.bitcoinj.core.MessageSerializer r1 = r1.getDefaultSerializer()     // Catch:{ ScriptException -> 0x0038, ProtocolException -> 0x0031 }
            byte[] r0 = r0.bitcoinSerialize()     // Catch:{ ScriptException -> 0x0038, ProtocolException -> 0x0031 }
            org.bitcoinj.core.Transaction r0 = r1.makeTransaction(r0)     // Catch:{ ScriptException -> 0x0038, ProtocolException -> 0x0031 }
        L_0x0027:
            int r1 = r7 + 1
            r6.receiveFromBlock(r0, r4, r5, r7)     // Catch:{ ScriptException -> 0x002e, ProtocolException -> 0x0031 }
            r7 = r1
            goto L_0x0004
        L_0x002e:
            r0 = move-exception
            r7 = r1
            goto L_0x0039
        L_0x0031:
            r4 = move-exception
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            r5.<init>(r4)
            throw r5
        L_0x0038:
            r0 = move-exception
        L_0x0039:
            org.slf4j.Logger r1 = log
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to parse a script: "
            r2.append(r3)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.warn(r0)
            goto L_0x0004
        L_0x0054:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.AbstractBlockChain.sendTransactionsToListener(org.bitcoinj.core.StoredBlock, org.bitcoinj.core.AbstractBlockChain$NewBlockType, org.bitcoinj.core.listeners.TransactionReceivedInBlockListener, int, java.util.List, boolean, java.util.Set):void");
    }

    /* access modifiers changed from: protected */
    public void setChainHead(StoredBlock storedBlock) throws BlockStoreException {
        doSetChainHead(storedBlock);
        synchronized (this.chainHeadLock) {
            this.chainHead = storedBlock;
        }
    }

    private void tryConnectingOrphans() throws VerificationException, BlockStoreException, PrunedException {
        int i;
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        do {
            Iterator it = this.orphanBlocks.values().iterator();
            i = 0;
            while (it.hasNext()) {
                OrphanBlock orphanBlock = (OrphanBlock) it.next();
                if (getStoredBlockInCurrentScope(orphanBlock.block.getPrevBlockHash()) == null) {
                    log.debug("Orphan block {} is not connectable right now", (Object) orphanBlock.block.getHash());
                } else {
                    log.info("Connected orphan {}", (Object) orphanBlock.block.getHash());
                    add(orphanBlock.block, false, orphanBlock.filteredTxHashes, orphanBlock.filteredTxn);
                    it.remove();
                    i++;
                }
            }
            if (i > 0) {
                log.info("Connected {} orphan blocks.", (Object) Integer.valueOf(i));
                continue;
            }
        } while (i > 0);
    }

    public StoredBlock getChainHead() {
        StoredBlock storedBlock;
        synchronized (this.chainHeadLock) {
            storedBlock = this.chainHead;
        }
        return storedBlock;
    }

    @Nullable
    public Block getOrphanRoot(Sha256Hash sha256Hash) {
        Block block;
        this.lock.lock();
        try {
            OrphanBlock orphanBlock = (OrphanBlock) this.orphanBlocks.get(sha256Hash);
            if (orphanBlock == null) {
                block = null;
            } else {
                while (true) {
                    OrphanBlock orphanBlock2 = (OrphanBlock) this.orphanBlocks.get(orphanBlock.block.getPrevBlockHash());
                    if (orphanBlock2 == null) {
                        break;
                    }
                    orphanBlock = orphanBlock2;
                }
                block = orphanBlock.block;
            }
            return block;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean isOrphan(Sha256Hash sha256Hash) {
        this.lock.lock();
        try {
            return this.orphanBlocks.containsKey(sha256Hash);
        } finally {
            this.lock.unlock();
        }
    }

    public Date estimateBlockTime(int i) {
        Date date;
        synchronized (this.chainHeadLock) {
            date = new Date((this.chainHead.getHeader().getTimeSeconds() * 1000) + (((long) (i - this.chainHead.getHeight())) * 600000));
        }
        return date;
    }

    public ListenableFuture<StoredBlock> getHeightFuture(final int i) {
        final SettableFuture create = SettableFuture.create();
        addNewBestBlockListener(Threading.SAME_THREAD, new NewBestBlockListener() {
            public void notifyNewBestBlock(StoredBlock storedBlock) throws VerificationException {
                if (storedBlock.getHeight() >= i) {
                    AbstractBlockChain.this.removeNewBestBlockListener(this);
                    create.set(storedBlock);
                }
            }
        });
        return create;
    }

    public double getFalsePositiveRate() {
        return this.falsePositiveRate;
    }

    /* access modifiers changed from: protected */
    public void trackFilteredTransactions(int i) {
        double d = (double) i;
        double pow = Math.pow(0.9999d, d);
        this.falsePositiveRate *= pow;
        double pow2 = Math.pow(0.99d, d);
        double d2 = d * 0.01d;
        double d3 = this.falsePositiveRate;
        this.falsePositiveTrend = (d2 * (d3 - this.previousFalsePositiveRate)) + (pow2 * this.falsePositiveTrend);
        this.falsePositiveRate = d3 + (pow * this.falsePositiveTrend);
        this.previousFalsePositiveRate = this.falsePositiveRate;
    }

    /* access modifiers changed from: 0000 */
    public void trackFalsePositives(int i) {
        this.falsePositiveRate += ((double) i) * 1.0E-4d;
        if (i > 0) {
            log.debug("{} false positives, current rate = {} trend = {}", Integer.valueOf(i), Double.valueOf(this.falsePositiveRate), Double.valueOf(this.falsePositiveTrend));
        }
    }

    public void resetFalsePositiveEstimate() {
        this.falsePositiveRate = 0.0d;
        this.falsePositiveTrend = 0.0d;
        this.previousFalsePositiveRate = 0.0d;
    }

    /* access modifiers changed from: protected */
    public VersionTally getVersionTally() {
        return this.versionTally;
    }
}
