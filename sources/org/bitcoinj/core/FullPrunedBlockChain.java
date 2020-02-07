package org.bitcoinj.core;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import javax.annotation.Nullable;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.Script.VerifyFlag;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.FullPrunedBlockStore;
import org.bitcoinj.utils.ContextPropagatingThreadFactory;
import org.bitcoinj.wallet.C3530Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FullPrunedBlockChain extends AbstractBlockChain {
    private static final Logger log = LoggerFactory.getLogger(FullPrunedBlockChain.class);
    protected final FullPrunedBlockStore blockStore;
    private boolean runScripts;
    ExecutorService scriptVerificationExecutor;

    private static class Verifier implements Callable<VerificationException> {
        final List<Script> prevOutScripts;

        /* renamed from: tx */
        final Transaction f798tx;
        final Set<VerifyFlag> verifyFlags;

        public Verifier(Transaction transaction, List<Script> list, Set<VerifyFlag> set) {
            this.f798tx = transaction;
            this.prevOutScripts = list;
            this.verifyFlags = set;
        }

        @Nullable
        public VerificationException call() throws Exception {
            try {
                ListIterator listIterator = this.prevOutScripts.listIterator();
                for (int i = 0; i < this.f798tx.getInputs().size(); i++) {
                    long j = (long) i;
                    ((TransactionInput) this.f798tx.getInputs().get(i)).getScriptSig().correctlySpends(this.f798tx, j, (Script) listIterator.next(), this.f798tx.getInput(j).getConnectedOutput() != null ? this.f798tx.getInput(j).getConnectedOutput().getValue() : Coin.ZERO, this.verifyFlags);
                }
                return null;
            } catch (VerificationException e) {
                return e;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldVerifyTransactions() {
        return true;
    }

    public FullPrunedBlockChain(Context context, C3530Wallet wallet, FullPrunedBlockStore fullPrunedBlockStore) throws BlockStoreException {
        this(context, (List<C3530Wallet>) new ArrayList<C3530Wallet>(), fullPrunedBlockStore);
        addWallet(wallet);
    }

    public FullPrunedBlockChain(NetworkParameters networkParameters, C3530Wallet wallet, FullPrunedBlockStore fullPrunedBlockStore) throws BlockStoreException {
        this(Context.getOrCreate(networkParameters), wallet, fullPrunedBlockStore);
    }

    public FullPrunedBlockChain(Context context, FullPrunedBlockStore fullPrunedBlockStore) throws BlockStoreException {
        this(context, (List<C3530Wallet>) new ArrayList<C3530Wallet>(), fullPrunedBlockStore);
    }

    public FullPrunedBlockChain(NetworkParameters networkParameters, FullPrunedBlockStore fullPrunedBlockStore) throws BlockStoreException {
        this(Context.getOrCreate(networkParameters), fullPrunedBlockStore);
    }

    public FullPrunedBlockChain(Context context, List<C3530Wallet> list, FullPrunedBlockStore fullPrunedBlockStore) throws BlockStoreException {
        super(context, list, (BlockStore) fullPrunedBlockStore);
        this.runScripts = true;
        this.scriptVerificationExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), new ContextPropagatingThreadFactory("Script verification"));
        this.blockStore = fullPrunedBlockStore;
        this.chainHead = fullPrunedBlockStore.getVerifiedChainHead();
    }

    public FullPrunedBlockChain(NetworkParameters networkParameters, List<C3530Wallet> list, FullPrunedBlockStore fullPrunedBlockStore) throws BlockStoreException {
        this(Context.getOrCreate(networkParameters), list, fullPrunedBlockStore);
    }

    /* access modifiers changed from: protected */
    public StoredBlock addToBlockStore(StoredBlock storedBlock, Block block, TransactionOutputChanges transactionOutputChanges) throws BlockStoreException, VerificationException {
        StoredBlock build = storedBlock.build(block);
        this.blockStore.put(build, new StoredUndoableBlock(build.getHeader().getHash(), transactionOutputChanges));
        return build;
    }

    /* access modifiers changed from: protected */
    public StoredBlock addToBlockStore(StoredBlock storedBlock, Block block) throws BlockStoreException, VerificationException {
        StoredBlock build = storedBlock.build(block);
        this.blockStore.put(build, new StoredUndoableBlock(build.getHeader().getHash(), block.transactions));
        return build;
    }

    /* access modifiers changed from: protected */
    public void rollbackBlockStore(int i) throws BlockStoreException {
        throw new BlockStoreException("Unsupported");
    }

    public void setRunScripts(boolean z) {
        this.runScripts = z;
    }

    private Script getScript(byte[] bArr) {
        try {
            return new Script(bArr);
        } catch (Exception unused) {
            return new Script(new byte[0]);
        }
    }

    private String getScriptAddress(@Nullable Script script) {
        String str = "";
        if (script == null) {
            return str;
        }
        try {
            return script.getToAddress(this.params, true).toString();
        } catch (Exception unused) {
            return str;
        }
    }

    /* access modifiers changed from: protected */
    public TransactionOutputChanges connectTransactions(int i, Block block) throws VerificationException, BlockStoreException {
        ArrayList arrayList;
        int i2 = i;
        Block block2 = block;
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        if (block2.transactions == null) {
            throw new RuntimeException("connectTransactions called with Block that didn't have transactions!");
        } else if (this.params.passesCheckpoint(i2, block.getHash())) {
            this.blockStore.beginDatabaseBatchWrite();
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            long j = 0;
            if (this.scriptVerificationExecutor.isShutdown()) {
                this.scriptVerificationExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            }
            ArrayList arrayList2 = new ArrayList(block2.transactions.size());
            try {
                if (!this.params.isCheckpoint(i2)) {
                    for (Transaction transaction : block2.transactions) {
                        EnumSet transactionVerificationFlags = this.params.getTransactionVerificationFlags(block2, transaction, getVersionTally(), Integer.valueOf(i));
                        if (this.blockStore.hasUnspentOutputs(transaction.getHash(), transaction.getOutputs().size())) {
                            throw new VerificationException("Block failed BIP30 test!");
                        } else if (transactionVerificationFlags.contains(VerifyFlag.P2SH)) {
                            j += (long) transaction.getSigOpCount();
                        }
                    }
                }
                Coin coin = Coin.ZERO;
                Iterator it = block2.transactions.iterator();
                Coin coin2 = coin;
                Coin coin3 = null;
                while (it.hasNext()) {
                    Transaction transaction2 = (Transaction) it.next();
                    boolean isCoinBase = transaction2.isCoinBase();
                    Coin coin4 = Coin.ZERO;
                    Coin coin5 = Coin.ZERO;
                    LinkedList linkedList3 = new LinkedList();
                    long j2 = j;
                    EnumSet transactionVerificationFlags2 = this.params.getTransactionVerificationFlags(block2, transaction2, getVersionTally(), Integer.valueOf(i));
                    if (!isCoinBase) {
                        int i3 = 0;
                        while (i3 < transaction2.getInputs().size()) {
                            TransactionInput transactionInput = (TransactionInput) transaction2.getInputs().get(i3);
                            Coin coin6 = coin5;
                            Coin coin7 = coin3;
                            Coin coin8 = coin2;
                            UTXO transactionOutput = this.blockStore.getTransactionOutput(transactionInput.getOutpoint().getHash(), transactionInput.getOutpoint().getIndex());
                            if (transactionOutput != null) {
                                if (transactionOutput.isCoinbase()) {
                                    if (i2 - transactionOutput.getHeight() < this.params.getSpendableCoinbaseDepth()) {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Tried to spend coinbase at depth ");
                                        sb.append(i2 - transactionOutput.getHeight());
                                        throw new VerificationException(sb.toString());
                                    }
                                }
                                coin4 = coin4.add(transactionOutput.getValue());
                                if (transactionVerificationFlags2.contains(VerifyFlag.P2SH)) {
                                    if (transactionOutput.getScript().isPayToScriptHash()) {
                                        j2 += Script.getP2SHSigOpCount(transactionInput.getScriptBytes());
                                    }
                                    if (j2 > 160000) {
                                        throw new VerificationException("Too many P2SH SigOps in block");
                                    }
                                }
                                linkedList3.add(transactionOutput.getScript());
                                this.blockStore.removeUnspentTransactionOutput(transactionOutput);
                                linkedList.add(transactionOutput);
                                i3++;
                                Block block3 = block;
                                coin5 = coin6;
                                coin3 = coin7;
                                coin2 = coin8;
                            } else {
                                throw new VerificationException("Attempted to spend a non-existent or already spent output!");
                            }
                        }
                    }
                    Coin coin9 = coin5;
                    Coin coin10 = coin3;
                    Coin coin11 = coin2;
                    Coin coin12 = coin4;
                    Sha256Hash hash = transaction2.getHash();
                    Coin coin13 = coin9;
                    for (TransactionOutput transactionOutput2 : transaction2.getOutputs()) {
                        Coin add = coin13.add(transactionOutput2.getValue());
                        Script script = getScript(transactionOutput2.getScriptBytes());
                        long index = (long) transactionOutput2.getIndex();
                        Iterator it2 = it;
                        EnumSet enumSet = transactionVerificationFlags2;
                        Coin value = transactionOutput2.getValue();
                        LinkedList linkedList4 = linkedList;
                        LinkedList linkedList5 = linkedList3;
                        Coin coin14 = coin10;
                        ArrayList arrayList3 = arrayList2;
                        Coin coin15 = coin11;
                        Transaction transaction3 = transaction2;
                        UTXO utxo = r2;
                        UTXO utxo2 = new UTXO(hash, index, value, i, isCoinBase, script, getScriptAddress(script));
                        this.blockStore.addUnspentTransactionOutput(utxo);
                        linkedList2.add(utxo);
                        int i4 = i;
                        linkedList3 = linkedList5;
                        transactionVerificationFlags2 = enumSet;
                        coin13 = add;
                        transaction2 = transaction3;
                        linkedList = linkedList4;
                        it = it2;
                        coin11 = coin15;
                        arrayList2 = arrayList3;
                        coin10 = coin14;
                    }
                    LinkedList linkedList6 = linkedList;
                    Iterator it3 = it;
                    Coin coin16 = coin10;
                    EnumSet enumSet2 = transactionVerificationFlags2;
                    LinkedList linkedList7 = linkedList3;
                    ArrayList arrayList4 = arrayList2;
                    Coin coin17 = coin11;
                    Transaction transaction4 = transaction2;
                    if (coin13.signum() < 0 || coin13.compareTo(this.params.getMaxMoney()) > 0) {
                        throw new VerificationException("Transaction output value out of range");
                    }
                    if (isCoinBase) {
                        coin3 = coin13;
                        coin2 = coin17;
                    } else if (coin12.compareTo(coin13) < 0 || coin12.compareTo(this.params.getMaxMoney()) > 0) {
                        throw new VerificationException("Transaction input value out of range");
                    } else {
                        coin2 = coin17.add(coin12.subtract(coin13));
                        coin3 = coin16;
                    }
                    if (isCoinBase || !this.runScripts) {
                        arrayList = arrayList4;
                    } else {
                        FutureTask futureTask = new FutureTask(new Verifier(transaction4, linkedList7, enumSet2));
                        this.scriptVerificationExecutor.execute(futureTask);
                        arrayList = arrayList4;
                        arrayList.add(futureTask);
                    }
                    i2 = i;
                    block2 = block;
                    arrayList2 = arrayList;
                    j = j2;
                    linkedList = linkedList6;
                    it = it3;
                }
                Coin coin18 = coin3;
                LinkedList linkedList8 = linkedList;
                ArrayList<Future> arrayList5 = arrayList2;
                Coin coin19 = coin2;
                if (coin19.compareTo(this.params.getMaxMoney()) <= 0) {
                    if (block.getBlockInflation(i).add(coin19).compareTo(coin18) >= 0) {
                        for (Future future : arrayList5) {
                            VerificationException verificationException = (VerificationException) future.get();
                            if (verificationException != null) {
                                throw verificationException;
                            }
                        }
                        return new TransactionOutputChanges(linkedList2, linkedList8);
                    }
                }
                throw new VerificationException("Transaction fees out of range");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e2) {
                Logger logger = log;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Script.correctlySpends threw a non-normal exception: ");
                sb2.append(e2.getCause());
                logger.error(sb2.toString());
                throw new VerificationException("Bug in Script.correctlySpends, likely script malformed in some new and interesting way.", e2);
            } catch (VerificationException e3) {
                this.scriptVerificationExecutor.shutdownNow();
                this.blockStore.abortDatabaseBatchWrite();
                throw e3;
            } catch (BlockStoreException e4) {
                this.scriptVerificationExecutor.shutdownNow();
                this.blockStore.abortDatabaseBatchWrite();
                throw e4;
            }
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Block failed checkpoint lockin at ");
            sb3.append(i2);
            throw new VerificationException(sb3.toString());
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public synchronized TransactionOutputChanges connectTransactions(StoredBlock storedBlock) throws VerificationException, BlockStoreException, PrunedException {
        TransactionOutputChanges transactionOutputChanges;
        Iterator it;
        ArrayList arrayList;
        synchronized (this) {
            try {
                Preconditions.checkState(this.lock.isHeldByCurrentThread());
                if (this.params.passesCheckpoint(storedBlock.getHeight(), storedBlock.getHeader().getHash())) {
                    this.blockStore.beginDatabaseBatchWrite();
                    StoredUndoableBlock undoBlock = this.blockStore.getUndoBlock(storedBlock.getHeader().getHash());
                    if (undoBlock != null) {
                        try {
                            List<Transaction> transactions = undoBlock.getTransactions();
                            if (transactions != null) {
                                LinkedList linkedList = new LinkedList();
                                LinkedList linkedList2 = new LinkedList();
                                long j = 0;
                                if (!this.params.isCheckpoint(storedBlock.getHeight())) {
                                    for (Transaction transaction : transactions) {
                                        if (this.blockStore.hasUnspentOutputs(transaction.getHash(), transaction.getOutputs().size())) {
                                            throw new VerificationException("Block failed BIP30 test!");
                                        }
                                    }
                                }
                                Coin coin = Coin.ZERO;
                                Coin coin2 = null;
                                if (this.scriptVerificationExecutor.isShutdown()) {
                                    this.scriptVerificationExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                                }
                                ArrayList arrayList2 = new ArrayList(transactions.size());
                                Iterator it2 = transactions.iterator();
                                while (it2.hasNext()) {
                                    Transaction transaction2 = (Transaction) it2.next();
                                    EnumSet transactionVerificationFlags = this.params.getTransactionVerificationFlags(storedBlock.getHeader(), transaction2, getVersionTally(), Integer.valueOf(32));
                                    boolean isCoinBase = transaction2.isCoinBase();
                                    Coin coin3 = Coin.ZERO;
                                    Coin coin4 = Coin.ZERO;
                                    LinkedList linkedList3 = new LinkedList();
                                    if (!isCoinBase) {
                                        int i = 0;
                                        while (true) {
                                            it = it2;
                                            if (i >= transaction2.getInputs().size()) {
                                                break;
                                            }
                                            TransactionInput transactionInput = (TransactionInput) transaction2.getInputs().get(i);
                                            Coin coin5 = coin4;
                                            Coin coin6 = coin2;
                                            ArrayList arrayList3 = arrayList2;
                                            Transaction transaction3 = transaction2;
                                            UTXO transactionOutput = this.blockStore.getTransactionOutput(transactionInput.getOutpoint().getHash(), transactionInput.getOutpoint().getIndex());
                                            if (transactionOutput != null) {
                                                if (transactionOutput.isCoinbase()) {
                                                    if (storedBlock.getHeight() - transactionOutput.getHeight() < this.params.getSpendableCoinbaseDepth()) {
                                                        StringBuilder sb = new StringBuilder();
                                                        sb.append("Tried to spend coinbase at depth ");
                                                        sb.append(storedBlock.getHeight() - transactionOutput.getHeight());
                                                        throw new VerificationException(sb.toString());
                                                    }
                                                }
                                                coin3 = coin3.add(transactionOutput.getValue());
                                                if (transactionVerificationFlags.contains(VerifyFlag.P2SH)) {
                                                    if (transactionOutput.getScript().isPayToScriptHash()) {
                                                        j += Script.getP2SHSigOpCount(transactionInput.getScriptBytes());
                                                    }
                                                    if (j > 160000) {
                                                        throw new VerificationException("Too many P2SH SigOps in block");
                                                    }
                                                }
                                                linkedList3.add(transactionOutput.getScript());
                                                this.blockStore.removeUnspentTransactionOutput(transactionOutput);
                                                linkedList.add(transactionOutput);
                                                i++;
                                                coin4 = coin5;
                                                it2 = it;
                                                coin2 = coin6;
                                                arrayList2 = arrayList3;
                                                transaction2 = transaction3;
                                            } else {
                                                throw new VerificationException("Attempted spend of a non-existent or already spent output!");
                                            }
                                        }
                                    } else {
                                        it = it2;
                                    }
                                    Coin coin7 = coin2;
                                    ArrayList arrayList4 = arrayList2;
                                    Transaction transaction4 = transaction2;
                                    Coin coin8 = coin4;
                                    Coin coin9 = coin3;
                                    Sha256Hash hash = transaction4.getHash();
                                    Iterator it3 = transaction4.getOutputs().iterator();
                                    Coin coin10 = coin8;
                                    while (it3.hasNext()) {
                                        TransactionOutput transactionOutput2 = (TransactionOutput) it3.next();
                                        coin10 = coin10.add(transactionOutput2.getValue());
                                        Script script = getScript(transactionOutput2.getScriptBytes());
                                        long j2 = j;
                                        Iterator it4 = it3;
                                        UTXO utxo = r12;
                                        Sha256Hash sha256Hash = hash;
                                        Sha256Hash sha256Hash2 = hash;
                                        LinkedList linkedList4 = linkedList3;
                                        long index = (long) transactionOutput2.getIndex();
                                        boolean z = isCoinBase;
                                        UTXO utxo2 = new UTXO(sha256Hash, index, transactionOutput2.getValue(), storedBlock.getHeight(), z, script, getScriptAddress(script));
                                        this.blockStore.addUnspentTransactionOutput(utxo);
                                        linkedList2.add(utxo);
                                        linkedList3 = linkedList4;
                                        j = j2;
                                        it3 = it4;
                                        hash = sha256Hash2;
                                    }
                                    long j3 = j;
                                    LinkedList linkedList5 = linkedList3;
                                    if (coin10.signum() < 0 || coin10.compareTo(this.params.getMaxMoney()) > 0) {
                                        throw new VerificationException("Transaction output value out of range");
                                    }
                                    if (isCoinBase) {
                                        coin7 = coin10;
                                    } else if (coin9.compareTo(coin10) < 0 || coin9.compareTo(this.params.getMaxMoney()) > 0) {
                                        throw new VerificationException("Transaction input value out of range");
                                    } else {
                                        coin = coin.add(coin9.subtract(coin10));
                                    }
                                    if (!isCoinBase) {
                                        FutureTask futureTask = new FutureTask(new Verifier(transaction4, linkedList5, transactionVerificationFlags));
                                        this.scriptVerificationExecutor.execute(futureTask);
                                        arrayList = arrayList4;
                                        arrayList.add(futureTask);
                                    } else {
                                        arrayList = arrayList4;
                                    }
                                    arrayList2 = arrayList;
                                    it2 = it;
                                    coin2 = coin7;
                                    j = j3;
                                }
                                Coin coin11 = coin2;
                                ArrayList<Future> arrayList5 = arrayList2;
                                if (coin.compareTo(this.params.getMaxMoney()) > 0 || storedBlock.getHeader().getBlockInflation(storedBlock.getHeight()).add(coin).compareTo(coin11) < 0) {
                                    throw new VerificationException("Transaction fees out of range");
                                }
                                transactionOutputChanges = new TransactionOutputChanges(linkedList2, linkedList);
                                for (Future future : arrayList5) {
                                    VerificationException verificationException = (VerificationException) future.get();
                                    if (verificationException != null) {
                                        throw verificationException;
                                    }
                                }
                            } else {
                                transactionOutputChanges = undoBlock.getTxOutChanges();
                                if (!this.params.isCheckpoint(storedBlock.getHeight())) {
                                    for (UTXO utxo3 : transactionOutputChanges.txOutsCreated) {
                                        if (this.blockStore.getTransactionOutput(utxo3.getHash(), utxo3.getIndex()) != null) {
                                            throw new VerificationException("Block failed BIP30 test!");
                                        }
                                    }
                                }
                                for (UTXO addUnspentTransactionOutput : transactionOutputChanges.txOutsCreated) {
                                    this.blockStore.addUnspentTransactionOutput(addUnspentTransactionOutput);
                                }
                                for (UTXO removeUnspentTransactionOutput : transactionOutputChanges.txOutsSpent) {
                                    this.blockStore.removeUnspentTransactionOutput(removeUnspentTransactionOutput);
                                }
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } catch (ExecutionException e2) {
                            ExecutionException executionException = e2;
                            Logger logger = log;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Script.correctlySpends threw a non-normal exception: ");
                            sb2.append(executionException.getCause());
                            logger.error(sb2.toString());
                            throw new VerificationException("Bug in Script.correctlySpends, likely script malformed in some new and interesting way.", executionException);
                        } catch (VerificationException e3) {
                            VerificationException verificationException2 = e3;
                            this.scriptVerificationExecutor.shutdownNow();
                            this.blockStore.abortDatabaseBatchWrite();
                            throw verificationException2;
                        } catch (BlockStoreException e4) {
                            BlockStoreException blockStoreException = e4;
                            this.scriptVerificationExecutor.shutdownNow();
                            this.blockStore.abortDatabaseBatchWrite();
                            throw blockStoreException;
                        }
                    } else {
                        this.blockStore.abortDatabaseBatchWrite();
                        throw new PrunedException(storedBlock.getHeader().getHash());
                    }
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Block failed checkpoint lockin at ");
                    sb3.append(storedBlock.getHeight());
                    throw new VerificationException(sb3.toString());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return transactionOutputChanges;
    }

    /* access modifiers changed from: protected */
    public void disconnectTransactions(StoredBlock storedBlock) throws PrunedException, BlockStoreException {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        this.blockStore.beginDatabaseBatchWrite();
        try {
            StoredUndoableBlock undoBlock = this.blockStore.getUndoBlock(storedBlock.getHeader().getHash());
            if (undoBlock != null) {
                TransactionOutputChanges txOutChanges = undoBlock.getTxOutChanges();
                for (UTXO addUnspentTransactionOutput : txOutChanges.txOutsSpent) {
                    this.blockStore.addUnspentTransactionOutput(addUnspentTransactionOutput);
                }
                for (UTXO removeUnspentTransactionOutput : txOutChanges.txOutsCreated) {
                    this.blockStore.removeUnspentTransactionOutput(removeUnspentTransactionOutput);
                }
                return;
            }
            throw new PrunedException(storedBlock.getHeader().getHash());
        } catch (PrunedException e) {
            this.blockStore.abortDatabaseBatchWrite();
            throw e;
        } catch (BlockStoreException e2) {
            this.blockStore.abortDatabaseBatchWrite();
            throw e2;
        }
    }

    /* access modifiers changed from: protected */
    public void doSetChainHead(StoredBlock storedBlock) throws BlockStoreException {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        this.blockStore.setVerifiedChainHead(storedBlock);
        this.blockStore.commitDatabaseBatchWrite();
    }

    /* access modifiers changed from: protected */
    public void notSettingChainHead() throws BlockStoreException {
        this.blockStore.abortDatabaseBatchWrite();
    }

    /* access modifiers changed from: protected */
    public StoredBlock getStoredBlockInCurrentScope(Sha256Hash sha256Hash) throws BlockStoreException {
        Preconditions.checkState(this.lock.isHeldByCurrentThread());
        return this.blockStore.getOnceUndoableStoredBlock(sha256Hash);
    }
}
