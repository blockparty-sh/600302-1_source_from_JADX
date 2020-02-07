package org.bitcoinj.store;

import com.google.common.base.Ascii;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.StoredUndoableBlock;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionOutputChanges;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.core.UTXOProviderException;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.script.Script;
import org.fusesource.leveldbjni.JniDBFactory;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBException;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.ReadOptions;
import org.iq80.leveldb.Snapshot;
import org.iq80.leveldb.WriteBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LevelDBFullPrunedBlockStore implements FullPrunedBlockStore {
    static final long LEVELDB_READ_CACHE_DEFAULT = 104857600;
    static final int LEVELDB_WRITE_CACHE_DEFAULT = 10485760;
    static final int OPENOUT_CACHE_DEFAULT = 100000;
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(LevelDBFullPrunedBlockStore.class);
    protected boolean autoCommit;
    WriteBatch batch;
    protected BloomFilter bloom;
    protected StoredBlock chainHeadBlock;
    protected Sha256Hash chainHeadHash;

    /* renamed from: db */
    DB f819db;
    int exitBlock;
    protected String filename;
    protected int fullStoreDepth;
    long hasCall;
    long hasFalse;
    long hasTrue;
    protected long hit;
    protected boolean instrument;
    protected long leveldbReadCache;
    protected int leveldbWriteCache;
    Map<String, Long> methodCalls;
    Map<String, Stopwatch> methodStartTime;
    Map<String, Long> methodTotalTime;
    protected long miss;
    protected int openOutCache;
    NetworkParameters params;
    Stopwatch totalStopwatch;
    Map<ByteBuffer, byte[]> uncommited;
    Set<ByteBuffer> uncommitedDeletes;
    protected Map<ByteBuffer, UTXO> utxoCache;
    protected Map<ByteBuffer, UTXO> utxoUncommittedCache;
    protected Set<ByteBuffer> utxoUncommittedDeletedCache;
    protected StoredBlock verifiedChainHeadBlock;
    protected Sha256Hash verifiedChainHeadHash;

    private class BloomFilter {
        public long added;
        private byte[] cache = new byte[134217728];
        public long returnedFalse;
        public long returnedTrue;

        public BloomFilter() {
        }

        public void reloadCache(DB db) {
            LevelDBFullPrunedBlockStore.log.info("Loading Bloom Filter");
            DBIterator it = db.iterator();
            byte[] access$100 = LevelDBFullPrunedBlockStore.this.getKey(KeyType.OPENOUT_ALL);
            it.seek(access$100);
            while (it.hasNext()) {
                ByteBuffer wrap = ByteBuffer.wrap((byte[]) it.peekNext().getKey());
                if (access$100[0] != wrap.get()) {
                    printStat();
                    return;
                }
                byte[] bArr = new byte[32];
                wrap.get(bArr);
                add(bArr);
                it.next();
            }
            try {
                it.close();
            } catch (IOException e) {
                LevelDBFullPrunedBlockStore.log.error("Error closing iterator", (Throwable) e);
            }
            printStat();
        }

        public void printStat() {
            Logger access$000 = LevelDBFullPrunedBlockStore.log;
            StringBuilder sb = new StringBuilder();
            sb.append("Bloom Added: ");
            sb.append(this.added);
            sb.append(" T: ");
            sb.append(this.returnedTrue);
            sb.append(" F: ");
            sb.append(this.returnedFalse);
            access$000.info(sb.toString());
        }

        public void add(byte[] bArr) {
            byte[] bArr2 = new byte[4];
            this.added++;
            for (int i = 0; i < 3; i++) {
                System.arraycopy(bArr, i * 4, bArr2, 0, 4);
                setBit(bArr2);
            }
        }

        public void add(Sha256Hash sha256Hash) {
            add(sha256Hash.getBytes());
        }

        public boolean wasAdded(Sha256Hash sha256Hash) {
            byte[] bArr = new byte[4];
            for (int i = 0; i < 3; i++) {
                System.arraycopy(sha256Hash.getBytes(), i * 4, bArr, 0, 4);
                if (!getBit(bArr)) {
                    this.returnedFalse++;
                    return false;
                }
            }
            this.returnedTrue++;
            return true;
        }

        private void setBit(byte[] bArr) {
            int i = ((bArr[0] & 63) << Ascii.NAK) | ((bArr[1] & 255) << Ascii.f519CR) | ((bArr[2] & 255) << 5) | ((bArr[3] & 255) >> 3);
            int i2 = 1 << (bArr[3] & 7);
            byte[] bArr2 = this.cache;
            bArr2[i] = (byte) (i2 | bArr2[i]);
        }

        private boolean getBit(byte[] bArr) {
            return ((1 << (bArr[3] & 7)) & this.cache[((((bArr[0] & 63) << Ascii.NAK) | ((bArr[1] & 255) << Ascii.f519CR)) | ((bArr[2] & 255) << 5)) | ((bArr[3] & 255) >> 3)]) != 0;
        }
    }

    enum KeyType {
        CREATED,
        CHAIN_HEAD_SETTING,
        VERIFIED_CHAIN_HEAD_SETTING,
        VERSION_SETTING,
        HEADERS_ALL,
        UNDOABLEBLOCKS_ALL,
        HEIGHT_UNDOABLEBLOCKS,
        OPENOUT_ALL,
        ADDRESS_HASHINDEX
    }

    public class LRUCache extends LinkedHashMap<ByteBuffer, UTXO> {
        private static final long serialVersionUID = 1;
        private int capacity;

        public LRUCache(int i, float f) {
            super(i, f, true);
            this.capacity = i;
        }

        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Entry<ByteBuffer, UTXO> entry) {
            return size() > this.capacity;
        }
    }

    public LevelDBFullPrunedBlockStore(NetworkParameters networkParameters, String str, int i) {
        this(networkParameters, str, i, LEVELDB_READ_CACHE_DEFAULT, LEVELDB_WRITE_CACHE_DEFAULT, 100000, false, Integer.MAX_VALUE);
    }

    public LevelDBFullPrunedBlockStore(NetworkParameters networkParameters, String str, int i, long j, int i2, int i3, boolean z, int i4) {
        this.f819db = null;
        this.instrument = false;
        this.autoCommit = true;
        this.params = networkParameters;
        this.fullStoreDepth = i;
        this.instrument = z;
        this.exitBlock = i4;
        this.methodStartTime = new HashMap();
        this.methodCalls = new HashMap();
        this.methodTotalTime = new HashMap();
        this.filename = str;
        this.leveldbReadCache = j;
        this.leveldbWriteCache = i2;
        this.openOutCache = i3;
        this.bloom = new BloomFilter();
        this.totalStopwatch = Stopwatch.createStarted();
        openDB();
        this.bloom.reloadCache(this.f819db);
        this.totalStopwatch = Stopwatch.createStarted();
    }

    private void openDB() {
        Options options = new Options();
        options.createIfMissing(true);
        options.cacheSize(this.leveldbReadCache);
        options.writeBufferSize(this.leveldbWriteCache);
        options.maxOpenFiles(10000);
        try {
            this.f819db = JniDBFactory.factory.open(new File(this.filename), options);
            this.utxoCache = new LRUCache(this.openOutCache, 0.75f);
            try {
                if (batchGet(getKey(KeyType.CREATED)) == null) {
                    createNewStore(this.params);
                } else {
                    initFromDb();
                }
            } catch (BlockStoreException e) {
                throw new RuntimeException("Can not init/load db", e);
            }
        } catch (IOException e2) {
            throw new RuntimeException("Can not open DB", e2);
        }
    }

    private void initFromDb() throws BlockStoreException {
        Sha256Hash wrap = Sha256Hash.wrap(batchGet(getKey(KeyType.CHAIN_HEAD_SETTING)));
        this.chainHeadBlock = get(wrap);
        this.chainHeadHash = wrap;
        if (this.chainHeadBlock != null) {
            Sha256Hash wrap2 = Sha256Hash.wrap(batchGet(getKey(KeyType.VERIFIED_CHAIN_HEAD_SETTING)));
            this.verifiedChainHeadBlock = get(wrap2);
            this.verifiedChainHeadHash = wrap2;
            if (this.verifiedChainHeadBlock == null) {
                throw new BlockStoreException("corrupt databse block store - verified head block not found");
            }
            return;
        }
        throw new BlockStoreException("corrupt database block store - head block not found");
    }

    private void createNewStore(NetworkParameters networkParameters) throws BlockStoreException {
        try {
            StoredBlock storedBlock = new StoredBlock(networkParameters.getGenesisBlock().cloneAsHeader(), networkParameters.getGenesisBlock().getWork(), 0);
            StoredUndoableBlock storedUndoableBlock = new StoredUndoableBlock(networkParameters.getGenesisBlock().getHash(), (List<Transaction>) Lists.newLinkedList());
            beginDatabaseBatchWrite();
            put(storedBlock, storedUndoableBlock);
            setChainHead(storedBlock);
            setVerifiedChainHead(storedBlock);
            batchPut(getKey(KeyType.CREATED), JniDBFactory.bytes("done"));
            commitDatabaseBatchWrite();
        } catch (VerificationException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: 0000 */
    public void beginMethod(String str) {
        this.methodStartTime.put(str, Stopwatch.createStarted());
    }

    /* access modifiers changed from: 0000 */
    public void endMethod(String str) {
        if (this.methodCalls.containsKey(str)) {
            Map<String, Long> map = this.methodCalls;
            map.put(str, Long.valueOf(((Long) map.get(str)).longValue() + 1));
            Map<String, Long> map2 = this.methodTotalTime;
            map2.put(str, Long.valueOf(((Long) map2.get(str)).longValue() + ((Stopwatch) this.methodStartTime.get(str)).elapsed(TimeUnit.NANOSECONDS)));
            return;
        }
        this.methodCalls.put(str, Long.valueOf(1));
        this.methodTotalTime.put(str, Long.valueOf(((Stopwatch) this.methodStartTime.get(str)).elapsed(TimeUnit.NANOSECONDS)));
    }

    /* access modifiers changed from: 0000 */
    public void dumpStats() {
        long elapsed = this.totalStopwatch.elapsed(TimeUnit.NANOSECONDS);
        Iterator it = this.methodCalls.keySet().iterator();
        long j = 0;
        while (true) {
            String str = "%.2f";
            if (it.hasNext()) {
                String str2 = (String) it.next();
                long longValue = ((Long) this.methodCalls.get(str2)).longValue();
                long longValue2 = ((Long) this.methodTotalTime.get(str2)).longValue();
                Iterator it2 = it;
                long j2 = j + longValue2;
                long j3 = longValue2 / longValue;
                double d = (((double) longValue2) + 0.0d) / (((double) elapsed) + 0.0d);
                Logger logger = log;
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(" c:");
                sb.append(longValue);
                sb.append(" r:");
                sb.append(longValue2);
                sb.append(" a:");
                sb.append(j3);
                sb.append(" p:");
                sb.append(String.format(str, new Object[]{Double.valueOf(d)}));
                logger.info(sb.toString());
                it = it2;
                j = j2;
            } else {
                double d2 = (((double) j) + 0.0d) / (((double) elapsed) + 0.0d);
                long j4 = this.hit;
                double d3 = (((double) j4) + 0.0d) / (((double) (j4 + this.miss)) + 0.0d);
                Logger logger2 = log;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Cache size:");
                sb2.append(this.utxoCache.size());
                sb2.append(" hit:");
                sb2.append(this.hit);
                sb2.append(" miss:");
                sb2.append(this.miss);
                sb2.append(" rate:");
                sb2.append(String.format(str, new Object[]{Double.valueOf(d3)}));
                logger2.info(sb2.toString());
                this.bloom.printStat();
                Logger logger3 = log;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("hasTxOut call:");
                sb3.append(this.hasCall);
                sb3.append(" True:");
                sb3.append(this.hasTrue);
                sb3.append(" False:");
                sb3.append(this.hasFalse);
                logger3.info(sb3.toString());
                Logger logger4 = log;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Wall:");
                sb4.append(this.totalStopwatch);
                sb4.append(" percent:");
                sb4.append(String.format(str, new Object[]{Double.valueOf(d2)}));
                logger4.info(sb4.toString());
                System.out.println(this.f819db.getProperty("leveldb.stats"));
                return;
            }
        }
    }

    public void put(StoredBlock storedBlock) throws BlockStoreException {
        putUpdateStoredBlock(storedBlock, false);
    }

    public StoredBlock getChainHead() throws BlockStoreException {
        return this.chainHeadBlock;
    }

    public void setChainHead(StoredBlock storedBlock) throws BlockStoreException {
        String str = "setChainHead";
        if (this.instrument) {
            beginMethod(str);
        }
        Sha256Hash hash = storedBlock.getHeader().getHash();
        this.chainHeadHash = hash;
        this.chainHeadBlock = storedBlock;
        batchPut(getKey(KeyType.CHAIN_HEAD_SETTING), hash.getBytes());
        if (this.instrument) {
            endMethod(str);
        }
    }

    public void close() throws BlockStoreException {
        try {
            this.f819db.close();
        } catch (IOException e) {
            throw new BlockStoreException("Could not close db", e);
        }
    }

    public NetworkParameters getParams() {
        return this.params;
    }

    public List<UTXO> getOpenTransactionOutputs(List<Address> list) throws UTXOProviderException {
        LinkedList linkedList = new LinkedList();
        for (Address address : list) {
            ByteBuffer allocate = ByteBuffer.allocate(21);
            allocate.put((byte) KeyType.ADDRESS_HASHINDEX.ordinal());
            allocate.put(address.getHash160());
            ReadOptions readOptions = new ReadOptions();
            Snapshot snapshot = this.f819db.getSnapshot();
            readOptions.snapshot(snapshot);
            DBIterator it = this.f819db.iterator(readOptions);
            it.seek(allocate.array());
            while (it.hasNext()) {
                ByteBuffer wrap = ByteBuffer.wrap((byte[]) it.peekNext().getKey());
                wrap.get();
                byte[] bArr = new byte[20];
                wrap.get(bArr);
                if (Arrays.equals(bArr, address.getHash160())) {
                    byte[] bArr2 = new byte[32];
                    wrap.get(bArr2);
                    try {
                        UTXO transactionOutput = getTransactionOutput(Sha256Hash.wrap(bArr2), (long) wrap.getInt());
                        if (transactionOutput != null) {
                            Address toAddress = transactionOutput.getScript().getToAddress(this.params, true);
                            Sha256Hash hash = transactionOutput.getHash();
                            long index = transactionOutput.getIndex();
                            Coin value = transactionOutput.getValue();
                            int height = transactionOutput.getHeight();
                            boolean isCoinbase = transactionOutput.isCoinbase();
                            Script script = transactionOutput.getScript();
                            String address2 = toAddress.toString();
                            UTXO utxo = r8;
                            UTXO utxo2 = new UTXO(hash, index, value, height, isCoinbase, script, address2);
                            linkedList.add(utxo);
                        }
                        it.next();
                    } catch (BlockStoreException e) {
                        throw new UTXOProviderException("block store execption", e);
                    }
                }
            }
            try {
                it.close();
                snapshot.close();
            } catch (IOException e2) {
                log.error("Error closing snapshot/iterator?", (Throwable) e2);
            }
        }
        return linkedList;
    }

    public int getChainHeadHeight() throws UTXOProviderException {
        try {
            return getVerifiedChainHead().getHeight();
        } catch (BlockStoreException e) {
            throw new UTXOProviderException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public void putUpdateStoredBlock(StoredBlock storedBlock, boolean z) {
        String str = "putUpdateStoredBlock";
        if (this.instrument) {
            beginMethod(str);
        }
        Sha256Hash hash = storedBlock.getHeader().getHash();
        ByteBuffer allocate = ByteBuffer.allocate(97);
        storedBlock.serializeCompact(allocate);
        allocate.put(z ? (byte) 1 : 0);
        batchPut(getKey(KeyType.HEADERS_ALL, hash), allocate.array());
        if (this.instrument) {
            endMethod(str);
        }
    }

    public void put(StoredBlock storedBlock, StoredUndoableBlock storedUndoableBlock) throws BlockStoreException {
        byte[] bArr;
        String str = "put";
        if (this.instrument) {
            beginMethod(str);
        }
        int height = storedBlock.getHeight();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = null;
            if (storedUndoableBlock.getTxOutChanges() != null) {
                storedUndoableBlock.getTxOutChanges().serializeToStream(byteArrayOutputStream);
                bArr2 = byteArrayOutputStream.toByteArray();
                bArr = null;
            } else {
                int size = storedUndoableBlock.getTransactions().size();
                byteArrayOutputStream.write((size >> 0) & 255);
                byteArrayOutputStream.write((size >> 8) & 255);
                byteArrayOutputStream.write((size >> 16) & 255);
                byteArrayOutputStream.write((size >> 24) & 255);
                for (Transaction bitcoinSerialize : storedUndoableBlock.getTransactions()) {
                    bitcoinSerialize.bitcoinSerialize(byteArrayOutputStream);
                }
                bArr = byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.close();
            Sha256Hash hash = storedBlock.getHeader().getHash();
            ByteBuffer allocate = ByteBuffer.allocate(33);
            allocate.put((byte) KeyType.HEIGHT_UNDOABLEBLOCKS.ordinal());
            allocate.putInt(height);
            allocate.put(hash.getBytes(), 4, 28);
            batchPut(allocate.array(), new byte[1]);
            if (bArr == null) {
                ByteBuffer allocate2 = ByteBuffer.allocate(bArr2.length + 8 + 4 + 0);
                allocate2.putInt(height);
                allocate2.putInt(bArr2.length);
                allocate2.put(bArr2);
                allocate2.putInt(0);
                batchPut(getKey(KeyType.UNDOABLEBLOCKS_ALL, hash), allocate2.array());
            } else {
                ByteBuffer allocate3 = ByteBuffer.allocate(bArr.length + 12);
                allocate3.putInt(height);
                allocate3.putInt(0);
                allocate3.putInt(bArr.length);
                allocate3.put(bArr);
                batchPut(getKey(KeyType.UNDOABLEBLOCKS_ALL, hash), allocate3.array());
            }
            if (this.instrument) {
                endMethod(str);
            }
            putUpdateStoredBlock(storedBlock, true);
        } catch (IOException e) {
            throw new BlockStoreException((Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public byte[] getKey(KeyType keyType) {
        return new byte[]{(byte) keyType.ordinal()};
    }

    private byte[] getTxKey(KeyType keyType, Sha256Hash sha256Hash) {
        byte[] bArr = new byte[33];
        bArr[0] = (byte) keyType.ordinal();
        System.arraycopy(sha256Hash.getBytes(), 0, bArr, 1, 32);
        return bArr;
    }

    private byte[] getTxKey(KeyType keyType, Sha256Hash sha256Hash, int i) {
        byte[] bArr = new byte[37];
        bArr[0] = (byte) keyType.ordinal();
        System.arraycopy(sha256Hash.getBytes(), 0, bArr, 1, 32);
        System.arraycopy(ByteBuffer.allocate(4).putInt(i).array(), 0, bArr, 33, 4);
        return bArr;
    }

    private byte[] getKey(KeyType keyType, Sha256Hash sha256Hash) {
        byte[] bArr = new byte[29];
        bArr[0] = (byte) keyType.ordinal();
        System.arraycopy(sha256Hash.getBytes(), 4, bArr, 1, 28);
        return bArr;
    }

    private byte[] getKey(KeyType keyType, byte[] bArr) {
        byte[] bArr2 = new byte[29];
        bArr2[0] = (byte) keyType.ordinal();
        System.arraycopy(bArr, 4, bArr2, 1, 28);
        return bArr2;
    }

    public StoredBlock getOnceUndoableStoredBlock(Sha256Hash sha256Hash) throws BlockStoreException {
        return get(sha256Hash, true);
    }

    public StoredBlock get(Sha256Hash sha256Hash) throws BlockStoreException {
        return get(sha256Hash, false);
    }

    public StoredBlock get(Sha256Hash sha256Hash, boolean z) throws BlockStoreException {
        Sha256Hash sha256Hash2 = this.chainHeadHash;
        if (sha256Hash2 != null && sha256Hash2.equals(sha256Hash)) {
            return this.chainHeadBlock;
        }
        Sha256Hash sha256Hash3 = this.verifiedChainHeadHash;
        if (sha256Hash3 != null && sha256Hash3.equals(sha256Hash)) {
            return this.verifiedChainHeadBlock;
        }
        String str = "get";
        if (this.instrument) {
            beginMethod(str);
        }
        byte[] batchGet = batchGet(getKey(KeyType.HEADERS_ALL, sha256Hash));
        if (batchGet == null) {
            if (this.instrument) {
                endMethod(str);
            }
            return null;
        }
        boolean z2 = true;
        if (batchGet[96] != 1) {
            z2 = false;
        }
        if (!z || z2) {
            StoredBlock deserializeCompact = StoredBlock.deserializeCompact(this.params, ByteBuffer.wrap(batchGet));
            deserializeCompact.getHeader().verifyHeader();
            if (this.instrument) {
                endMethod(str);
            }
            return deserializeCompact;
        }
        if (this.instrument) {
            endMethod(str);
        }
        return null;
    }

    public StoredUndoableBlock getUndoBlock(Sha256Hash sha256Hash) throws BlockStoreException {
        StoredUndoableBlock storedUndoableBlock;
        String str = "getUndoBlock";
        try {
            if (this.instrument) {
                beginMethod(str);
            }
            byte[] batchGet = batchGet(getKey(KeyType.UNDOABLEBLOCKS_ALL, sha256Hash));
            if (batchGet == null) {
                if (this.instrument) {
                    endMethod(str);
                }
                return null;
            }
            ByteBuffer wrap = ByteBuffer.wrap(batchGet);
            wrap.getInt();
            int i = wrap.getInt();
            if (i == 0) {
                byte[] bArr = new byte[wrap.getInt()];
                wrap.get(bArr);
                int i2 = 4;
                int i3 = ((bArr[0] & 255) << 0) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16) | ((bArr[3] & 255) << Ascii.CAN);
                LinkedList linkedList = new LinkedList();
                for (int i4 = 0; i4 < i3; i4++) {
                    Transaction transaction = new Transaction(this.params, bArr, i2);
                    linkedList.add(transaction);
                    i2 += transaction.getMessageSize();
                }
                storedUndoableBlock = new StoredUndoableBlock(sha256Hash, (List<Transaction>) linkedList);
            } else {
                byte[] bArr2 = new byte[i];
                wrap.get(bArr2);
                storedUndoableBlock = new StoredUndoableBlock(sha256Hash, new TransactionOutputChanges(new ByteArrayInputStream(bArr2)));
            }
            if (this.instrument) {
                endMethod(str);
            }
            return storedUndoableBlock;
        } catch (IOException e) {
            if (this.instrument) {
                endMethod(str);
            }
            throw new BlockStoreException((Throwable) e);
        }
    }

    public UTXO getTransactionOutput(Sha256Hash sha256Hash, long j) throws BlockStoreException {
        UTXO utxo;
        String str = "Exception in getTransactionOutput.";
        String str2 = "getTransactionOutput";
        if (this.instrument) {
            beginMethod(str2);
        }
        try {
            byte[] txKey = getTxKey(KeyType.OPENOUT_ALL, sha256Hash, (int) j);
            if (this.autoCommit) {
                utxo = (UTXO) this.utxoCache.get(ByteBuffer.wrap(txKey));
            } else if (this.utxoUncommittedDeletedCache.contains(ByteBuffer.wrap(txKey))) {
                this.hit++;
                if (this.instrument) {
                    endMethod(str2);
                }
                return null;
            } else {
                utxo = (UTXO) this.utxoUncommittedCache.get(ByteBuffer.wrap(txKey));
                if (utxo == null) {
                    utxo = (UTXO) this.utxoCache.get(ByteBuffer.wrap(txKey));
                }
            }
            if (utxo != null) {
                this.hit++;
                if (this.instrument) {
                    endMethod(str2);
                }
                return utxo;
            }
            this.miss++;
            byte[] batchGet = batchGet(txKey);
            if (batchGet == null) {
                if (this.instrument) {
                    endMethod(str2);
                }
                return null;
            }
            UTXO utxo2 = new UTXO(new ByteArrayInputStream(batchGet));
            if (this.instrument) {
                endMethod(str2);
            }
            return utxo2;
        } catch (DBException e) {
            log.error(str, (Throwable) e);
            if (this.instrument) {
                endMethod(str2);
            }
            throw new BlockStoreException("problem");
        } catch (IOException e2) {
            log.error(str, (Throwable) e2);
            if (this.instrument) {
                endMethod(str2);
            }
            throw new BlockStoreException("problem");
        }
    }

    public void addUnspentTransactionOutput(UTXO utxo) throws BlockStoreException {
        String str = "addUnspentTransactionOutput";
        if (this.instrument) {
            beginMethod(str);
        }
        this.bloom.add(utxo.getHash());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            utxo.serializeToStream(byteArrayOutputStream);
            byte[] txKey = getTxKey(KeyType.OPENOUT_ALL, utxo.getHash(), (int) utxo.getIndex());
            batchPut(txKey, byteArrayOutputStream.toByteArray());
            if (this.autoCommit) {
                this.utxoCache.put(ByteBuffer.wrap(txKey), utxo);
            } else {
                this.utxoUncommittedCache.put(ByteBuffer.wrap(txKey), utxo);
                this.utxoUncommittedDeletedCache.remove(ByteBuffer.wrap(txKey));
            }
            if (utxo.getAddress() == null || utxo.getAddress().equals("")) {
                if (this.instrument) {
                    endMethod(str);
                }
                return;
            }
            try {
                Address fromBase58 = Address.fromBase58(this.params, utxo.getAddress());
                ByteBuffer allocate = ByteBuffer.allocate(57);
                allocate.put((byte) KeyType.ADDRESS_HASHINDEX.ordinal());
                allocate.put(fromBase58.getHash160());
                allocate.put(utxo.getHash().getBytes());
                allocate.putInt((int) utxo.getIndex());
                batchPut(allocate.array(), new byte[0]);
                if (this.instrument) {
                    endMethod(str);
                }
            } catch (AddressFormatException unused) {
                if (this.instrument) {
                    endMethod(str);
                }
            }
        } catch (IOException e) {
            throw new BlockStoreException("problem serialising utxo", e);
        }
    }

    private void batchPut(byte[] bArr, byte[] bArr2) {
        if (this.autoCommit) {
            this.f819db.put(bArr, bArr2);
            return;
        }
        this.uncommited.put(ByteBuffer.wrap(bArr), bArr2);
        this.batch.put(bArr, bArr2);
    }

    private byte[] batchGet(byte[] bArr) {
        byte[] bArr2;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (!this.autoCommit) {
            Set<ByteBuffer> set = this.uncommitedDeletes;
            if (set != null && set.contains(wrap)) {
                return null;
            }
        }
        if (!this.autoCommit) {
            Map<ByteBuffer, byte[]> map = this.uncommited;
            if (map != null) {
                byte[] bArr3 = (byte[]) map.get(wrap);
                if (bArr3 != null) {
                    return bArr3;
                }
            }
        }
        try {
            bArr2 = this.f819db.get(bArr);
        } catch (DBException e) {
            log.error("Caught error opening file", (Throwable) e);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException unused) {
            }
            bArr2 = this.f819db.get(bArr);
        }
        return bArr2;
    }

    private void batchDelete(byte[] bArr) {
        if (!this.autoCommit) {
            this.batch.delete(bArr);
            this.uncommited.remove(ByteBuffer.wrap(bArr));
            this.uncommitedDeletes.add(ByteBuffer.wrap(bArr));
            return;
        }
        this.f819db.delete(bArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeUnspentTransactionOutput(org.bitcoinj.core.UTXO r6) throws org.bitcoinj.store.BlockStoreException {
        /*
            r5 = this;
            boolean r0 = r5.instrument
            java.lang.String r1 = "removeUnspentTransactionOutput"
            if (r0 == 0) goto L_0x0009
            r5.beginMethod(r1)
        L_0x0009:
            org.bitcoinj.store.LevelDBFullPrunedBlockStore$KeyType r0 = org.bitcoinj.store.LevelDBFullPrunedBlockStore.KeyType.OPENOUT_ALL
            org.bitcoinj.core.Sha256Hash r2 = r6.getHash()
            long r3 = r6.getIndex()
            int r4 = (int) r3
            byte[] r0 = r5.getTxKey(r0, r2, r4)
            boolean r2 = r5.autoCommit
            if (r2 == 0) goto L_0x0026
            java.util.Map<java.nio.ByteBuffer, org.bitcoinj.core.UTXO> r2 = r5.utxoCache
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r0)
            r2.remove(r3)
            goto L_0x0038
        L_0x0026:
            java.util.Set<java.nio.ByteBuffer> r2 = r5.utxoUncommittedDeletedCache
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r0)
            r2.add(r3)
            java.util.Map<java.nio.ByteBuffer, org.bitcoinj.core.UTXO> r2 = r5.utxoUncommittedCache
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r0)
            r2.remove(r3)
        L_0x0038:
            r5.batchDelete(r0)
            r0 = 57
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r0)
            java.lang.String r2 = r6.getAddress()     // Catch:{ AddressFormatException -> 0x00a5, ScriptException -> 0x009c }
            if (r2 == 0) goto L_0x005f
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)     // Catch:{ AddressFormatException -> 0x00a5, ScriptException -> 0x009c }
            if (r2 == 0) goto L_0x0050
            goto L_0x005f
        L_0x0050:
            org.bitcoinj.core.NetworkParameters r2 = r5.params     // Catch:{ AddressFormatException -> 0x00a5, ScriptException -> 0x009c }
            java.lang.String r3 = r6.getAddress()     // Catch:{ AddressFormatException -> 0x00a5, ScriptException -> 0x009c }
            org.bitcoinj.core.Address r2 = org.bitcoinj.core.Address.fromBase58(r2, r3)     // Catch:{ AddressFormatException -> 0x00a5, ScriptException -> 0x009c }
            byte[] r2 = r2.getHash160()     // Catch:{ AddressFormatException -> 0x00a5, ScriptException -> 0x009c }
            goto L_0x006d
        L_0x005f:
            org.bitcoinj.script.Script r2 = r6.getScript()     // Catch:{ AddressFormatException -> 0x00a5, ScriptException -> 0x009c }
            org.bitcoinj.core.NetworkParameters r3 = r5.params     // Catch:{ AddressFormatException -> 0x00a5, ScriptException -> 0x009c }
            org.bitcoinj.core.Address r2 = r2.getToAddress(r3)     // Catch:{ AddressFormatException -> 0x00a5, ScriptException -> 0x009c }
            byte[] r2 = r2.getHash160()     // Catch:{ AddressFormatException -> 0x00a5, ScriptException -> 0x009c }
        L_0x006d:
            org.bitcoinj.store.LevelDBFullPrunedBlockStore$KeyType r3 = org.bitcoinj.store.LevelDBFullPrunedBlockStore.KeyType.ADDRESS_HASHINDEX
            int r3 = r3.ordinal()
            byte r3 = (byte) r3
            r0.put(r3)
            r0.put(r2)
            org.bitcoinj.core.Sha256Hash r2 = r6.getHash()
            byte[] r2 = r2.getBytes()
            r0.put(r2)
            long r2 = r6.getIndex()
            int r6 = (int) r2
            r0.putInt(r6)
            byte[] r6 = r0.array()
            r5.batchDelete(r6)
            boolean r6 = r5.instrument
            if (r6 == 0) goto L_0x009b
            r5.endMethod(r1)
        L_0x009b:
            return
        L_0x009c:
            boolean r6 = r5.instrument
            if (r6 == 0) goto L_0x00a4
            r5.endMethod(r1)
        L_0x00a4:
            return
        L_0x00a5:
            boolean r6 = r5.instrument
            if (r6 == 0) goto L_0x00ad
            r5.endMethod(r1)
        L_0x00ad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.store.LevelDBFullPrunedBlockStore.removeUnspentTransactionOutput(org.bitcoinj.core.UTXO):void");
    }

    public boolean hasUnspentOutputs(Sha256Hash sha256Hash, int i) throws BlockStoreException {
        String str = "hasUnspentOutputs";
        if (this.instrument) {
            beginMethod(str);
        }
        this.hasCall++;
        if (!this.bloom.wasAdded(sha256Hash)) {
            if (this.instrument) {
                endMethod(str);
            }
            this.hasFalse++;
            return false;
        }
        byte[] txKey = getTxKey(KeyType.OPENOUT_ALL, sha256Hash);
        byte[] bArr = new byte[txKey.length];
        DBIterator it = this.f819db.iterator();
        it.seek(txKey);
        String str2 = "Error closing iterator";
        if (it.hasNext()) {
            System.arraycopy((byte[]) it.peekNext().getKey(), 0, bArr, 0, bArr.length);
            if (Arrays.equals(txKey, bArr)) {
                this.hasTrue++;
                try {
                    it.close();
                } catch (IOException e) {
                    log.error(str2, (Throwable) e);
                }
                if (this.instrument) {
                    endMethod(str);
                }
                return true;
            }
            this.hasFalse++;
            try {
                it.close();
            } catch (IOException e2) {
                log.error(str2, (Throwable) e2);
            }
            if (this.instrument) {
                endMethod(str);
            }
            return false;
        }
        try {
            it.close();
        } catch (IOException e3) {
            log.error(str2, (Throwable) e3);
        }
        this.hasFalse++;
        if (this.instrument) {
            endMethod(str);
        }
        return false;
    }

    public StoredBlock getVerifiedChainHead() throws BlockStoreException {
        return this.verifiedChainHeadBlock;
    }

    public void setVerifiedChainHead(StoredBlock storedBlock) throws BlockStoreException {
        String str = "setVerifiedChainHead";
        if (this.instrument) {
            beginMethod(str);
        }
        Sha256Hash hash = storedBlock.getHeader().getHash();
        this.verifiedChainHeadHash = hash;
        this.verifiedChainHeadBlock = storedBlock;
        batchPut(getKey(KeyType.VERIFIED_CHAIN_HEAD_SETTING), hash.getBytes());
        if (this.chainHeadBlock.getHeight() < storedBlock.getHeight()) {
            setChainHead(storedBlock);
        }
        removeUndoableBlocksWhereHeightIsLessThan(storedBlock.getHeight() - this.fullStoreDepth);
        if (this.instrument) {
            endMethod(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public void removeUndoableBlocksWhereHeightIsLessThan(int i) {
        if (i >= 0) {
            DBIterator it = this.f819db.iterator();
            ByteBuffer allocate = ByteBuffer.allocate(5);
            allocate.put((byte) KeyType.HEIGHT_UNDOABLEBLOCKS.ordinal());
            allocate.putInt(i);
            it.seek(allocate.array());
            while (it.hasNext()) {
                byte[] bArr = (byte[]) it.peekNext().getKey();
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                wrap.get();
                int i2 = wrap.getInt();
                byte[] bArr2 = new byte[32];
                wrap.get(bArr2, 4, 28);
                if (i2 <= i) {
                    batchDelete(getKey(KeyType.UNDOABLEBLOCKS_ALL, bArr2));
                    batchDelete(bArr);
                    it.next();
                }
            }
            try {
                it.close();
            } catch (IOException e) {
                log.error("Error closing iterator", (Throwable) e);
            }
        }
    }

    public void beginDatabaseBatchWrite() throws BlockStoreException {
        if (this.autoCommit) {
            String str = "beginDatabaseBatchWrite";
            if (this.instrument) {
                beginMethod(str);
            }
            this.batch = this.f819db.createWriteBatch();
            this.uncommited = new HashMap();
            this.uncommitedDeletes = new HashSet();
            this.utxoUncommittedCache = new HashMap();
            this.utxoUncommittedDeletedCache = new HashSet();
            this.autoCommit = false;
            if (this.instrument) {
                endMethod(str);
            }
        }
    }

    public void commitDatabaseBatchWrite() throws BlockStoreException {
        this.uncommited = null;
        this.uncommitedDeletes = null;
        String str = "commitDatabaseBatchWrite";
        if (this.instrument) {
            beginMethod(str);
        }
        this.f819db.write(this.batch);
        for (Entry entry : this.utxoUncommittedCache.entrySet()) {
            this.utxoCache.put(entry.getKey(), entry.getValue());
        }
        this.utxoUncommittedCache = null;
        for (ByteBuffer remove : this.utxoUncommittedDeletedCache) {
            this.utxoCache.remove(remove);
        }
        this.utxoUncommittedDeletedCache = null;
        this.autoCommit = true;
        try {
            this.batch.close();
            this.batch = null;
            if (this.instrument) {
                endMethod(str);
            }
            if (this.instrument && this.verifiedChainHeadBlock.getHeight() % 1000 == 0) {
                Logger logger = log;
                StringBuilder sb = new StringBuilder();
                sb.append("Height: ");
                sb.append(this.verifiedChainHeadBlock.getHeight());
                logger.info(sb.toString());
                dumpStats();
                if (this.verifiedChainHeadBlock.getHeight() == this.exitBlock) {
                    System.err.println("Exit due to exitBlock set");
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            log.error("Error in db commit.", (Throwable) e);
            throw new BlockStoreException("could not close batch.");
        }
    }

    public void abortDatabaseBatchWrite() throws BlockStoreException {
        try {
            this.uncommited = null;
            this.uncommitedDeletes = null;
            this.utxoUncommittedCache = null;
            this.utxoUncommittedDeletedCache = null;
            this.autoCommit = true;
            if (this.batch != null) {
                this.batch.close();
                this.batch = null;
            }
        } catch (IOException e) {
            throw new BlockStoreException("could not close batch in abort.", e);
        }
    }

    public void resetStore() {
        try {
            this.f819db.close();
            this.uncommited = null;
            this.uncommitedDeletes = null;
            this.autoCommit = true;
            this.bloom = new BloomFilter();
            this.utxoCache = new LRUCache(this.openOutCache, 0.75f);
        } catch (IOException e) {
            log.error("Exception in resetStore.", (Throwable) e);
        }
        File file = new File(this.filename);
        if (file.isDirectory()) {
            for (File delete : file.listFiles()) {
                delete.delete();
            }
        }
        openDB();
    }
}
