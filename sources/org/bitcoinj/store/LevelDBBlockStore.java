package org.bitcoinj.store;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.Context;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;
import org.fusesource.leveldbjni.JniDBFactory;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteBatch;

public class LevelDBBlockStore implements BlockStore {
    private static final byte[] CHAIN_HEAD_KEY = "chainhead".getBytes();
    private final ByteBuffer buffer;
    private final Context context;

    /* renamed from: db */
    private DB f818db;
    private final File path;

    public LevelDBBlockStore(Context context2, File file) throws BlockStoreException {
        this(context2, file, JniDBFactory.factory);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x001b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LevelDBBlockStore(org.bitcoinj.core.Context r2, java.io.File r3, org.iq80.leveldb.DBFactory r4) throws org.bitcoinj.store.BlockStoreException {
        /*
            r1 = this;
            r1.<init>()
            r0 = 96
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r0)
            r1.buffer = r0
            r1.context = r2
            r1.path = r3
            org.iq80.leveldb.Options r2 = new org.iq80.leveldb.Options
            r2.<init>()
            r2.createIfMissing()
            r1.tryOpen(r3, r4, r2)     // Catch:{ IOException -> 0x001b }
            goto L_0x0021
        L_0x001b:
            r4.repair(r3, r2)     // Catch:{ IOException -> 0x0022 }
            r1.tryOpen(r3, r4, r2)     // Catch:{ IOException -> 0x0022 }
        L_0x0021:
            return
        L_0x0022:
            r2 = move-exception
            org.bitcoinj.store.BlockStoreException r3 = new org.bitcoinj.store.BlockStoreException
            r3.<init>(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.store.LevelDBBlockStore.<init>(org.bitcoinj.core.Context, java.io.File, org.iq80.leveldb.DBFactory):void");
    }

    private synchronized void tryOpen(File file, DBFactory dBFactory, Options options) throws IOException, BlockStoreException {
        this.f818db = dBFactory.open(file, options);
        initStoreIfNeeded();
    }

    private synchronized void initStoreIfNeeded() throws BlockStoreException {
        if (this.f818db.get(CHAIN_HEAD_KEY) == null) {
            Block cloneAsHeader = this.context.getParams().getGenesisBlock().cloneAsHeader();
            StoredBlock storedBlock = new StoredBlock(cloneAsHeader, cloneAsHeader.getWork(), 0);
            put(storedBlock);
            setChainHead(storedBlock);
        }
    }

    public synchronized void put(StoredBlock storedBlock) throws BlockStoreException {
        this.buffer.clear();
        storedBlock.serializeCompact(this.buffer);
        this.f818db.put(storedBlock.getHeader().getHash().getBytes(), this.buffer.array());
    }

    @Nullable
    public synchronized StoredBlock get(Sha256Hash sha256Hash) throws BlockStoreException {
        byte[] bArr = this.f818db.get(sha256Hash.getBytes());
        if (bArr == null) {
            return null;
        }
        return StoredBlock.deserializeCompact(this.context.getParams(), ByteBuffer.wrap(bArr));
    }

    public synchronized StoredBlock getChainHead() throws BlockStoreException {
        return get(Sha256Hash.wrap(this.f818db.get(CHAIN_HEAD_KEY)));
    }

    public synchronized void setChainHead(StoredBlock storedBlock) throws BlockStoreException {
        this.f818db.put(CHAIN_HEAD_KEY, storedBlock.getHeader().getHash().getBytes());
    }

    public synchronized void close() throws BlockStoreException {
        try {
            this.f818db.close();
        } catch (IOException e) {
            throw new BlockStoreException((Throwable) e);
        }
    }

    public synchronized void reset() throws BlockStoreException {
        DBIterator it;
        try {
            WriteBatch createWriteBatch = this.f818db.createWriteBatch();
            try {
                it = this.f818db.iterator();
                it.seekToFirst();
                while (it.hasNext()) {
                    createWriteBatch.delete((byte[]) ((Entry) it.next()).getKey());
                }
                this.f818db.write(createWriteBatch);
                it.close();
                createWriteBatch.close();
                initStoreIfNeeded();
            } catch (Throwable th) {
                createWriteBatch.close();
                throw th;
            }
        } catch (IOException e) {
            throw new BlockStoreException((Throwable) e);
        }
    }

    public synchronized void destroy() throws IOException {
        JniDBFactory.factory.destroy(this.path, new Options());
    }

    public NetworkParameters getParams() {
        return this.context.getParams();
    }
}
