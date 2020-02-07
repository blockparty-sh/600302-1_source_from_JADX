package org.bitcoinj.store;

import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ProtocolException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.utils.Threading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SPVBlockStore implements BlockStore {
    public static final int DEFAULT_NUM_HEADERS = 5000;
    protected static final int FILE_PROLOGUE_BYTES = 1024;
    public static final String HEADER_MAGIC = "SPVB";
    protected static final int RECORD_SIZE = 128;
    private static final Logger log = LoggerFactory.getLogger(SPVBlockStore.class);
    protected static final Object notFoundMarker = new Object();
    protected LinkedHashMap<Sha256Hash, StoredBlock> blockCache = new LinkedHashMap<Sha256Hash, StoredBlock>() {
        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Entry<Sha256Hash, StoredBlock> entry) {
            return size() > 2050;
        }
    };
    protected volatile MappedByteBuffer buffer;
    protected FileLock fileLock = null;
    protected StoredBlock lastChainHead = null;
    protected ReentrantLock lock = Threading.lock("SPVBlockStore");
    protected LinkedHashMap<Sha256Hash, Object> notFoundCache = new LinkedHashMap<Sha256Hash, Object>() {
        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Entry<Sha256Hash, Object> entry) {
            return size() > 100;
        }
    };
    protected int numHeaders;
    protected NetworkParameters params;
    protected RandomAccessFile randomAccessFile = null;

    public SPVBlockStore(NetworkParameters networkParameters, File file) throws BlockStoreException {
        Preconditions.checkNotNull(file);
        this.params = (NetworkParameters) Preconditions.checkNotNull(networkParameters);
        try {
            this.numHeaders = 5000;
            boolean exists = file.exists();
            this.randomAccessFile = new RandomAccessFile(file, "rw");
            long fileSize = (long) getFileSize();
            if (!exists) {
                Logger logger = log;
                StringBuilder sb = new StringBuilder();
                sb.append("Creating new SPV block chain file ");
                sb.append(file);
                logger.info(sb.toString());
                this.randomAccessFile.setLength(fileSize);
            } else if (this.randomAccessFile.length() != fileSize) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("File size on disk does not match expected size: ");
                sb2.append(this.randomAccessFile.length());
                sb2.append(" vs ");
                sb2.append(fileSize);
                throw new BlockStoreException(sb2.toString());
            }
            FileChannel channel = this.randomAccessFile.getChannel();
            this.fileLock = channel.tryLock();
            if (this.fileLock != null) {
                this.buffer = channel.map(MapMode.READ_WRITE, 0, fileSize);
                if (exists) {
                    byte[] bArr = new byte[4];
                    this.buffer.get(bArr);
                    if (!new String(bArr, "US-ASCII").equals(HEADER_MAGIC)) {
                        throw new BlockStoreException("Header bytes do not equal SPVB");
                    }
                    return;
                }
                initNewStore(networkParameters);
                return;
            }
            throw new ChainFileLockedException("Store file is already locked by another process");
        } catch (Exception e) {
            try {
                if (this.randomAccessFile != null) {
                    this.randomAccessFile.close();
                }
                throw new BlockStoreException((Throwable) e);
            } catch (IOException e2) {
                throw new BlockStoreException((Throwable) e2);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private void initNewStore(NetworkParameters networkParameters) throws Exception {
        this.buffer.put(HEADER_MAGIC.getBytes("US-ASCII"));
        this.lock.lock();
        try {
            setRingCursor(this.buffer, 1024);
            this.lock.unlock();
            Block cloneAsHeader = networkParameters.getGenesisBlock().cloneAsHeader();
            StoredBlock storedBlock = new StoredBlock(cloneAsHeader, cloneAsHeader.getWork(), 0);
            put(storedBlock);
            setChainHead(storedBlock);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public final int getFileSize() {
        return (this.numHeaders * 128) + 1024;
    }

    public void put(StoredBlock storedBlock) throws BlockStoreException {
        MappedByteBuffer mappedByteBuffer = this.buffer;
        if (mappedByteBuffer != null) {
            this.lock.lock();
            try {
                int ringCursor = getRingCursor(mappedByteBuffer);
                if (ringCursor == getFileSize()) {
                    ringCursor = 1024;
                }
                mappedByteBuffer.position(ringCursor);
                Sha256Hash hash = storedBlock.getHeader().getHash();
                this.notFoundCache.remove(hash);
                mappedByteBuffer.put(hash.getBytes());
                storedBlock.serializeCompact(mappedByteBuffer);
                setRingCursor(mappedByteBuffer, mappedByteBuffer.position());
                this.blockCache.put(hash, storedBlock);
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new BlockStoreException("Store closed");
        }
    }

    @Nullable
    public StoredBlock get(Sha256Hash sha256Hash) throws BlockStoreException {
        MappedByteBuffer mappedByteBuffer = this.buffer;
        if (mappedByteBuffer != null) {
            this.lock.lock();
            try {
                StoredBlock storedBlock = (StoredBlock) this.blockCache.get(sha256Hash);
                if (storedBlock != null) {
                    this.lock.unlock();
                    return storedBlock;
                }
                if (this.notFoundCache.get(sha256Hash) == null) {
                    int ringCursor = getRingCursor(mappedByteBuffer);
                    int fileSize = getFileSize();
                    byte[] bytes = sha256Hash.getBytes();
                    byte[] bArr = new byte[32];
                    int i = ringCursor;
                    do {
                        i -= 128;
                        if (i < 1024) {
                            i = fileSize - 128;
                        }
                        mappedByteBuffer.position(i);
                        mappedByteBuffer.get(bArr);
                        if (Arrays.equals(bArr, bytes)) {
                            StoredBlock deserializeCompact = StoredBlock.deserializeCompact(this.params, mappedByteBuffer);
                            this.blockCache.put(sha256Hash, deserializeCompact);
                            this.lock.unlock();
                            return deserializeCompact;
                        }
                    } while (i != ringCursor);
                    this.notFoundCache.put(sha256Hash, notFoundMarker);
                }
                this.lock.unlock();
                return null;
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        } else {
            throw new BlockStoreException("Store closed");
        }
    }

    public StoredBlock getChainHead() throws BlockStoreException {
        MappedByteBuffer mappedByteBuffer = this.buffer;
        if (mappedByteBuffer != null) {
            this.lock.lock();
            try {
                if (this.lastChainHead == null) {
                    byte[] bArr = new byte[32];
                    mappedByteBuffer.position(8);
                    mappedByteBuffer.get(bArr);
                    Sha256Hash wrap = Sha256Hash.wrap(bArr);
                    StoredBlock storedBlock = get(wrap);
                    if (storedBlock != null) {
                        this.lastChainHead = storedBlock;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Corrupted block store: could not find chain head: ");
                        sb.append(wrap);
                        throw new BlockStoreException(sb.toString());
                    }
                }
                return this.lastChainHead;
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new BlockStoreException("Store closed");
        }
    }

    public void setChainHead(StoredBlock storedBlock) throws BlockStoreException {
        MappedByteBuffer mappedByteBuffer = this.buffer;
        if (mappedByteBuffer != null) {
            this.lock.lock();
            try {
                this.lastChainHead = storedBlock;
                byte[] bytes = storedBlock.getHeader().getHash().getBytes();
                mappedByteBuffer.position(8);
                mappedByteBuffer.put(bytes);
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new BlockStoreException("Store closed");
        }
    }

    public void close() throws BlockStoreException {
        try {
            this.buffer.force();
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                log.info("Windows mmap hack: Forcing buffer cleaning");
                WindowsMMapHack.forceRelease(this.buffer);
            }
            this.buffer = null;
            this.randomAccessFile.close();
        } catch (IOException e) {
            throw new BlockStoreException((Throwable) e);
        }
    }

    public NetworkParameters getParams() {
        return this.params;
    }

    private int getRingCursor(ByteBuffer byteBuffer) {
        int i = byteBuffer.getInt(4);
        Preconditions.checkState(i >= 1024, "Integer overflow");
        return i;
    }

    private void setRingCursor(ByteBuffer byteBuffer, int i) {
        Preconditions.checkArgument(i >= 0);
        byteBuffer.putInt(4, i);
    }
}
