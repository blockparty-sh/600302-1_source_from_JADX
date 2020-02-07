package org.bitcoinj.core;

import com.google.common.base.Ascii;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptChunk;

public class BloomFilter extends Message {
    private static final long MAX_FILTER_SIZE = 36000;
    private static final int MAX_HASH_FUNCS = 50;
    private byte[] data;
    private long hashFuncs;
    private byte nFlags;
    private long nTweak;

    public enum BloomUpdate {
        UPDATE_NONE,
        UPDATE_ALL,
        UPDATE_P2PUBKEY_ONLY
    }

    private static int rotateLeft32(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public BloomFilter(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0);
    }

    public BloomFilter(int i, double d, long j) {
        this(i, d, j, BloomUpdate.UPDATE_P2PUBKEY_ONLY);
    }

    public BloomFilter(int i, double d, long j, BloomUpdate bloomUpdate) {
        double d2 = (double) i;
        this.data = new byte[Math.max(1, Math.min((int) (((-1.0d / Math.pow(Math.log(2.0d), 2.0d)) * d2) * Math.log(d)), 288000) / 8)];
        this.hashFuncs = (long) ((int) ((((double) (this.data.length * 8)) / d2) * Math.log(2.0d)));
        this.hashFuncs = Math.max(1, Math.min(this.hashFuncs, 50));
        this.nTweak = j;
        this.nFlags = (byte) (bloomUpdate.ordinal() & 255);
    }

    public double getFalsePositiveRate(int i) {
        return Math.pow(1.0d - Math.pow(2.718281828459045d, (((double) (this.hashFuncs * ((long) i))) * -1.0d) / ((double) (this.data.length * 8))), (double) this.hashFuncs);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bloom Filter of size ");
        sb.append(this.data.length);
        sb.append(" with ");
        sb.append(this.hashFuncs);
        sb.append(" hash functions.");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        this.data = readByteArray();
        if (((long) this.data.length) <= MAX_FILTER_SIZE) {
            this.hashFuncs = readUint32();
            if (this.hashFuncs <= 50) {
                this.nTweak = readUint32();
                this.nFlags = readBytes(1)[0];
                this.length = this.cursor - this.offset;
                return;
            }
            throw new ProtocolException("Bloom filter hash function count out of range");
        }
        throw new ProtocolException("Bloom filter out of size range.");
    }

    /* access modifiers changed from: protected */
    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        outputStream.write(new VarInt((long) this.data.length).encode());
        outputStream.write(this.data);
        C3447Utils.uint32ToByteStreamLE(this.hashFuncs, outputStream);
        C3447Utils.uint32ToByteStreamLE(this.nTweak, outputStream);
        outputStream.write(this.nFlags);
    }

    public static int murmurHash3(byte[] bArr, long j, int i, byte[] bArr2) {
        int i2 = (int) ((((long) i) * 4221880213L) + j);
        int length = (bArr2.length / 4) * 4;
        byte b = 0;
        int i3 = i2;
        for (int i4 = 0; i4 < length; i4 += 4) {
            i3 = (rotateLeft32(i3 ^ (rotateLeft32(((((bArr2[i4] & 255) | ((bArr2[i4 + 1] & 255) << 8)) | ((bArr2[i4 + 2] & 255) << 16)) | ((bArr2[i4 + 3] & 255) << Ascii.CAN)) * -862048943, 15) * 461845907), 13) * 5) - 430675100;
        }
        int length2 = bArr2.length & 3;
        if (length2 != 1) {
            if (length2 != 2) {
                if (length2 == 3) {
                    b = 0 ^ ((bArr2[length + 2] & 255) << 16);
                }
                int length3 = bArr2.length ^ i3;
                int i5 = (length3 ^ (length3 >>> 16)) * -2048144789;
                int i6 = (i5 ^ (i5 >>> 13)) * -1028477387;
                return (int) ((((long) (i6 ^ (i6 >>> 16))) & TransactionInput.NO_SEQUENCE) % ((long) (bArr.length * 8)));
            }
            b ^= (bArr2[length + 1] & 255) << 8;
        }
        i3 ^= rotateLeft32(((bArr2[length] & 255) ^ b) * -862048943, 15) * 461845907;
        int length32 = bArr2.length ^ i3;
        int i52 = (length32 ^ (length32 >>> 16)) * -2048144789;
        int i62 = (i52 ^ (i52 >>> 13)) * -1028477387;
        return (int) ((((long) (i62 ^ (i62 >>> 16))) & TransactionInput.NO_SEQUENCE) % ((long) (bArr.length * 8)));
    }

    public synchronized boolean contains(byte[] bArr) {
        int i = 0;
        while (true) {
            if (((long) i) >= this.hashFuncs) {
                return true;
            }
            if (!C3447Utils.checkBitLE(this.data, murmurHash3(this.data, this.nTweak, i, bArr))) {
                return false;
            }
            i++;
        }
    }

    public synchronized void insert(byte[] bArr) {
        int i = 0;
        while (true) {
            if (((long) i) < this.hashFuncs) {
                C3447Utils.setBitLE(this.data, murmurHash3(this.data, this.nTweak, i, bArr));
                i++;
            }
        }
    }

    public synchronized void insert(ECKey eCKey) {
        insert(eCKey.getPubKey());
        insert(eCKey.getPubKeyHash());
    }

    public synchronized void setMatchAll() {
        this.data = new byte[]{-1};
    }

    public synchronized void merge(BloomFilter bloomFilter) {
        boolean z = true;
        if (matchesAll() || bloomFilter.matchesAll()) {
            this.data = new byte[]{-1};
        } else {
            if (bloomFilter.data.length != this.data.length || bloomFilter.hashFuncs != this.hashFuncs || bloomFilter.nTweak != this.nTweak) {
                z = false;
            }
            Preconditions.checkArgument(z);
            for (int i = 0; i < this.data.length; i++) {
                byte[] bArr = this.data;
                bArr[i] = (byte) (bArr[i] | bloomFilter.data[i]);
            }
        }
    }

    public synchronized boolean matchesAll() {
        for (byte b : this.data) {
            if (b != -1) {
                return false;
            }
        }
        return true;
    }

    public synchronized BloomUpdate getUpdateFlag() {
        if (this.nFlags == 0) {
            return BloomUpdate.UPDATE_NONE;
        } else if (this.nFlags == 1) {
            return BloomUpdate.UPDATE_ALL;
        } else if (this.nFlags == 2) {
            return BloomUpdate.UPDATE_P2PUBKEY_ONLY;
        } else {
            throw new IllegalStateException("Unknown flag combination");
        }
    }

    public synchronized FilteredBlock applyAndUpdate(Block block) {
        FilteredBlock filteredBlock;
        List transactions = block.getTransactions();
        ArrayList arrayList = new ArrayList(transactions.size());
        ArrayList<Transaction> newArrayList = Lists.newArrayList();
        byte[] bArr = new byte[((int) Math.ceil(((double) transactions.size()) / 8.0d))];
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = (Transaction) transactions.get(i);
            arrayList.add(transaction.getHash());
            if (applyAndUpdate(transaction)) {
                C3447Utils.setBitLE(bArr, i);
                newArrayList.add(transaction);
            }
        }
        filteredBlock = new FilteredBlock(block.getParams(), block.cloneAsHeader(), PartialMerkleTree.buildFromLeaves(block.getParams(), bArr, arrayList));
        for (Transaction provideTransaction : newArrayList) {
            filteredBlock.provideTransaction(provideTransaction);
        }
        return filteredBlock;
    }

    public synchronized boolean applyAndUpdate(Transaction transaction) {
        boolean z;
        if (contains(transaction.getHash().getBytes())) {
            return true;
        }
        BloomUpdate updateFlag = getUpdateFlag();
        boolean z2 = false;
        for (TransactionOutput transactionOutput : transaction.getOutputs()) {
            Script scriptPubKey = transactionOutput.getScriptPubKey();
            for (ScriptChunk scriptChunk : scriptPubKey.getChunks()) {
                if (scriptChunk.isPushData()) {
                    if (contains(scriptChunk.data)) {
                        if (!scriptPubKey.isSentToRawPubKey()) {
                            if (!scriptPubKey.isSentToMultiSig()) {
                                z = false;
                                if (updateFlag == BloomUpdate.UPDATE_ALL || (updateFlag == BloomUpdate.UPDATE_P2PUBKEY_ONLY && z)) {
                                    insert(transactionOutput.getOutPointFor().unsafeBitcoinSerialize());
                                }
                                z2 = true;
                            }
                        }
                        z = true;
                        insert(transactionOutput.getOutPointFor().unsafeBitcoinSerialize());
                        z2 = true;
                    }
                }
            }
        }
        if (z2) {
            return true;
        }
        for (TransactionInput transactionInput : transaction.getInputs()) {
            if (contains(transactionInput.getOutpoint().unsafeBitcoinSerialize())) {
                return true;
            }
            Iterator it = transactionInput.getScriptSig().getChunks().iterator();
            while (true) {
                if (it.hasNext()) {
                    ScriptChunk scriptChunk2 = (ScriptChunk) it.next();
                    if (scriptChunk2.isPushData() && contains(scriptChunk2.data)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0033, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0038, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 1
            if (r7 != r8) goto L_0x0006
            monitor-exit(r7)
            return r0
        L_0x0006:
            r1 = 0
            if (r8 == 0) goto L_0x0037
            java.lang.Class r2 = r7.getClass()     // Catch:{ all -> 0x0034 }
            java.lang.Class r3 = r8.getClass()     // Catch:{ all -> 0x0034 }
            if (r2 == r3) goto L_0x0014
            goto L_0x0037
        L_0x0014:
            org.bitcoinj.core.BloomFilter r8 = (org.bitcoinj.core.BloomFilter) r8     // Catch:{ all -> 0x0034 }
            long r2 = r7.hashFuncs     // Catch:{ all -> 0x0034 }
            long r4 = r8.hashFuncs     // Catch:{ all -> 0x0034 }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0031
            long r2 = r7.nTweak     // Catch:{ all -> 0x0034 }
            long r4 = r8.nTweak     // Catch:{ all -> 0x0034 }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0031
            byte[] r2 = r7.data     // Catch:{ all -> 0x0034 }
            byte[] r8 = r8.data     // Catch:{ all -> 0x0034 }
            boolean r8 = java.util.Arrays.equals(r2, r8)     // Catch:{ all -> 0x0034 }
            if (r8 == 0) goto L_0x0031
            goto L_0x0032
        L_0x0031:
            r0 = 0
        L_0x0032:
            monitor-exit(r7)
            return r0
        L_0x0034:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x0037:
            monitor-exit(r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.BloomFilter.equals(java.lang.Object):boolean");
    }

    public synchronized int hashCode() {
        return Objects.hashCode(Long.valueOf(this.hashFuncs), Long.valueOf(this.nTweak), Integer.valueOf(Arrays.hashCode(this.data)));
    }
}
