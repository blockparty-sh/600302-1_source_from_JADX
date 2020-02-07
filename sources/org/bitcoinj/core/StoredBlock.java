package org.bitcoinj.core;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Locale;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;

public class StoredBlock {
    public static final int CHAIN_WORK_BYTES = 12;
    public static final int COMPACT_SERIALIZED_SIZE = 96;
    public static final byte[] EMPTY_BYTES = new byte[12];
    private BigInteger chainWork;
    private Block header;
    private int height;

    public StoredBlock(Block block, BigInteger bigInteger, int i) {
        this.header = block;
        this.chainWork = bigInteger;
        this.height = i;
    }

    public Block getHeader() {
        return this.header;
    }

    public BigInteger getChainWork() {
        return this.chainWork;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean moreWorkThan(StoredBlock storedBlock) {
        return this.chainWork.compareTo(storedBlock.chainWork) > 0;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StoredBlock storedBlock = (StoredBlock) obj;
        if (!this.header.equals(storedBlock.header) || !this.chainWork.equals(storedBlock.chainWork) || this.height != storedBlock.height) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(this.header, this.chainWork, Integer.valueOf(this.height));
    }

    public StoredBlock build(Block block) throws VerificationException {
        return new StoredBlock(block, this.chainWork.add(block.getWork()), this.height + 1);
    }

    public StoredBlock getPrev(BlockStore blockStore) throws BlockStoreException {
        return blockStore.get(getHeader().getPrevBlockHash());
    }

    public void serializeCompact(ByteBuffer byteBuffer) {
        byte[] byteArray = getChainWork().toByteArray();
        Preconditions.checkState(byteArray.length <= 12, "Ran out of space to store chain work!");
        if (byteArray.length < 12) {
            byteBuffer.put(EMPTY_BYTES, 0, 12 - byteArray.length);
        }
        byteBuffer.put(byteArray);
        byteBuffer.putInt(getHeight());
        byteBuffer.put(getHeader().unsafeBitcoinSerialize(), 0, 80);
    }

    public static StoredBlock deserializeCompact(NetworkParameters networkParameters, ByteBuffer byteBuffer) throws ProtocolException {
        byte[] bArr = new byte[12];
        byteBuffer.get(bArr);
        BigInteger bigInteger = new BigInteger(1, bArr);
        int i = byteBuffer.getInt();
        byte[] bArr2 = new byte[81];
        byteBuffer.get(bArr2, 0, 80);
        return new StoredBlock(networkParameters.getDefaultSerializer().makeBlock(bArr2), bigInteger, i);
    }

    public String toString() {
        return String.format(Locale.US, "Block %s at height %d: %s", new Object[]{getHeader().getHashAsString(), Integer.valueOf(getHeight()), getHeader().toString()});
    }
}
