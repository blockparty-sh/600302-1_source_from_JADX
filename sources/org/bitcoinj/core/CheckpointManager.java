package org.bitcoinj.core;

import com.google.common.base.Preconditions;
import com.google.common.p011io.BaseEncoding;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.annotation.Nullable;
import org.bitcoinj.protocols.channels.PaymentChannelServer;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.FullPrunedBlockStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckpointManager {
    public static final BaseEncoding BASE64 = BaseEncoding.base64().omitPadding();
    private static final String BINARY_MAGIC = "CHECKPOINTS 1";
    private static final int MAX_SIGNATURES = 256;
    private static final String TEXTUAL_MAGIC = "TXT CHECKPOINTS 1";
    private static final Logger log = LoggerFactory.getLogger(CheckpointManager.class);
    protected final TreeMap<Long, StoredBlock> checkpoints;
    protected final Sha256Hash dataHash;
    protected final NetworkParameters params;

    public CheckpointManager(Context context) throws IOException {
        this(context.getParams(), null);
    }

    public CheckpointManager(NetworkParameters networkParameters, @Nullable InputStream inputStream) throws IOException {
        this.checkpoints = new TreeMap<>();
        this.params = (NetworkParameters) Preconditions.checkNotNull(networkParameters);
        if (inputStream == null) {
            inputStream = openStream(networkParameters);
        }
        Preconditions.checkNotNull(inputStream);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.mark(1);
        int read = bufferedInputStream.read();
        bufferedInputStream.reset();
        if (read == BINARY_MAGIC.charAt(0)) {
            this.dataHash = readBinary(bufferedInputStream);
        } else if (read == TEXTUAL_MAGIC.charAt(0)) {
            this.dataHash = readTextual(bufferedInputStream);
        } else {
            throw new IOException("Unsupported format.");
        }
    }

    public static InputStream openStream(NetworkParameters networkParameters) {
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        sb.append(networkParameters.getId());
        sb.append(".checkpoints.txt");
        return CheckpointManager.class.getResourceAsStream(sb.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.bitcoinj.core.Sha256Hash readBinary(java.io.InputStream r12) throws java.io.IOException {
        /*
            r11 = this;
            r0 = 0
            java.security.MessageDigest r1 = org.bitcoinj.core.Sha256Hash.newDigest()     // Catch:{ ProtocolException -> 0x00b6, all -> 0x00b2 }
            java.security.DigestInputStream r2 = new java.security.DigestInputStream     // Catch:{ ProtocolException -> 0x00b6, all -> 0x00b2 }
            r2.<init>(r12, r1)     // Catch:{ ProtocolException -> 0x00b6, all -> 0x00b2 }
            java.io.DataInputStream r3 = new java.io.DataInputStream     // Catch:{ ProtocolException -> 0x00b6, all -> 0x00b2 }
            r3.<init>(r2)     // Catch:{ ProtocolException -> 0x00b6, all -> 0x00b2 }
            r0 = 0
            r2.on(r0)     // Catch:{ ProtocolException -> 0x00b0 }
            r4 = 13
            byte[] r4 = new byte[r4]     // Catch:{ ProtocolException -> 0x00b0 }
            r3.readFully(r4)     // Catch:{ ProtocolException -> 0x00b0 }
            java.lang.String r5 = "CHECKPOINTS 1"
            java.lang.String r6 = "US-ASCII"
            byte[] r5 = r5.getBytes(r6)     // Catch:{ ProtocolException -> 0x00b0 }
            boolean r4 = java.util.Arrays.equals(r4, r5)     // Catch:{ ProtocolException -> 0x00b0 }
            if (r4 == 0) goto L_0x00a8
            int r4 = r3.readInt()     // Catch:{ ProtocolException -> 0x00b0 }
            r5 = 256(0x100, float:3.59E-43)
            java.lang.String r6 = "Num signatures out of range"
            int r4 = com.google.common.base.Preconditions.checkPositionIndex(r4, r5, r6)     // Catch:{ ProtocolException -> 0x00b0 }
            r5 = 0
        L_0x0035:
            if (r5 >= r4) goto L_0x0041
            r6 = 65
            byte[] r6 = new byte[r6]     // Catch:{ ProtocolException -> 0x00b0 }
            r3.readFully(r6)     // Catch:{ ProtocolException -> 0x00b0 }
            int r5 = r5 + 1
            goto L_0x0035
        L_0x0041:
            r4 = 1
            r2.on(r4)     // Catch:{ ProtocolException -> 0x00b0 }
            int r2 = r3.readInt()     // Catch:{ ProtocolException -> 0x00b0 }
            if (r2 <= 0) goto L_0x004c
            goto L_0x004d
        L_0x004c:
            r4 = 0
        L_0x004d:
            com.google.common.base.Preconditions.checkState(r4)     // Catch:{ ProtocolException -> 0x00b0 }
            r4 = 96
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocate(r4)     // Catch:{ ProtocolException -> 0x00b0 }
            r6 = 0
        L_0x0057:
            if (r6 >= r2) goto L_0x0088
            byte[] r7 = r5.array()     // Catch:{ ProtocolException -> 0x00b0 }
            int r7 = r3.read(r7, r0, r4)     // Catch:{ ProtocolException -> 0x00b0 }
            if (r7 < r4) goto L_0x0080
            org.bitcoinj.core.NetworkParameters r7 = r11.params     // Catch:{ ProtocolException -> 0x00b0 }
            org.bitcoinj.core.StoredBlock r7 = org.bitcoinj.core.StoredBlock.deserializeCompact(r7, r5)     // Catch:{ ProtocolException -> 0x00b0 }
            r5.position(r0)     // Catch:{ ProtocolException -> 0x00b0 }
            java.util.TreeMap<java.lang.Long, org.bitcoinj.core.StoredBlock> r8 = r11.checkpoints     // Catch:{ ProtocolException -> 0x00b0 }
            org.bitcoinj.core.Block r9 = r7.getHeader()     // Catch:{ ProtocolException -> 0x00b0 }
            long r9 = r9.getTimeSeconds()     // Catch:{ ProtocolException -> 0x00b0 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ ProtocolException -> 0x00b0 }
            r8.put(r9, r7)     // Catch:{ ProtocolException -> 0x00b0 }
            int r6 = r6 + 1
            goto L_0x0057
        L_0x0080:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ ProtocolException -> 0x00b0 }
            java.lang.String r1 = "Incomplete read whilst loading checkpoints."
            r0.<init>(r1)     // Catch:{ ProtocolException -> 0x00b0 }
            throw r0     // Catch:{ ProtocolException -> 0x00b0 }
        L_0x0088:
            byte[] r0 = r1.digest()     // Catch:{ ProtocolException -> 0x00b0 }
            org.bitcoinj.core.Sha256Hash r0 = org.bitcoinj.core.Sha256Hash.wrap(r0)     // Catch:{ ProtocolException -> 0x00b0 }
            org.slf4j.Logger r1 = log     // Catch:{ ProtocolException -> 0x00b0 }
            java.lang.String r2 = "Read {} checkpoints, hash is {}"
            java.util.TreeMap<java.lang.Long, org.bitcoinj.core.StoredBlock> r4 = r11.checkpoints     // Catch:{ ProtocolException -> 0x00b0 }
            int r4 = r4.size()     // Catch:{ ProtocolException -> 0x00b0 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ ProtocolException -> 0x00b0 }
            r1.info(r2, r4, r0)     // Catch:{ ProtocolException -> 0x00b0 }
            r3.close()
            r12.close()
            return r0
        L_0x00a8:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ ProtocolException -> 0x00b0 }
            java.lang.String r1 = "Header bytes did not match expected version"
            r0.<init>(r1)     // Catch:{ ProtocolException -> 0x00b0 }
            throw r0     // Catch:{ ProtocolException -> 0x00b0 }
        L_0x00b0:
            r0 = move-exception
            goto L_0x00b9
        L_0x00b2:
            r1 = move-exception
            r3 = r0
            r0 = r1
            goto L_0x00c0
        L_0x00b6:
            r1 = move-exception
            r3 = r0
            r0 = r1
        L_0x00b9:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x00bf }
            r1.<init>(r0)     // Catch:{ all -> 0x00bf }
            throw r1     // Catch:{ all -> 0x00bf }
        L_0x00bf:
            r0 = move-exception
        L_0x00c0:
            if (r3 == 0) goto L_0x00c5
            r3.close()
        L_0x00c5:
            r12.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.CheckpointManager.readBinary(java.io.InputStream):org.bitcoinj.core.Sha256Hash");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00d0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.bitcoinj.core.Sha256Hash readTextual(java.io.InputStream r10) throws java.io.IOException {
        /*
            r9 = this;
            com.google.common.hash.HashFunction r0 = com.google.common.hash.Hashing.sha256()
            com.google.common.hash.Hasher r0 = r0.newHasher()
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x00cc }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x00cc }
            java.nio.charset.Charset r4 = com.google.common.base.Charsets.US_ASCII     // Catch:{ all -> 0x00cc }
            r3.<init>(r10, r4)     // Catch:{ all -> 0x00cc }
            r2.<init>(r3)     // Catch:{ all -> 0x00cc }
            java.lang.String r10 = r2.readLine()     // Catch:{ all -> 0x00ca }
            java.lang.String r1 = "TXT CHECKPOINTS 1"
            boolean r1 = r1.equals(r10)     // Catch:{ all -> 0x00ca }
            if (r1 == 0) goto L_0x00b3
            java.lang.String r10 = r2.readLine()     // Catch:{ all -> 0x00ca }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ all -> 0x00ca }
            r1 = 0
            r3 = 0
        L_0x002b:
            if (r3 >= r10) goto L_0x0033
            r2.readLine()     // Catch:{ all -> 0x00ca }
            int r3 = r3 + 1
            goto L_0x002b
        L_0x0033:
            java.lang.String r10 = r2.readLine()     // Catch:{ all -> 0x00ca }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ all -> 0x00ca }
            if (r10 <= 0) goto L_0x003f
            r3 = 1
            goto L_0x0040
        L_0x003f:
            r3 = 0
        L_0x0040:
            com.google.common.base.Preconditions.checkState(r3)     // Catch:{ all -> 0x00ca }
            r3 = 4
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r3)     // Catch:{ all -> 0x00ca }
            java.nio.ByteOrder r4 = java.nio.ByteOrder.BIG_ENDIAN     // Catch:{ all -> 0x00ca }
            java.nio.ByteBuffer r3 = r3.order(r4)     // Catch:{ all -> 0x00ca }
            java.nio.ByteBuffer r3 = r3.putInt(r10)     // Catch:{ all -> 0x00ca }
            byte[] r3 = r3.array()     // Catch:{ all -> 0x00ca }
            r0.putBytes(r3)     // Catch:{ all -> 0x00ca }
            r3 = 96
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r3)     // Catch:{ all -> 0x00ca }
            r4 = 0
        L_0x0060:
            if (r4 >= r10) goto L_0x0092
            com.google.common.io.BaseEncoding r5 = BASE64     // Catch:{ all -> 0x00ca }
            java.lang.String r6 = r2.readLine()     // Catch:{ all -> 0x00ca }
            byte[] r5 = r5.decode(r6)     // Catch:{ all -> 0x00ca }
            r0.putBytes(r5)     // Catch:{ all -> 0x00ca }
            r3.position(r1)     // Catch:{ all -> 0x00ca }
            r3.put(r5)     // Catch:{ all -> 0x00ca }
            r3.position(r1)     // Catch:{ all -> 0x00ca }
            org.bitcoinj.core.NetworkParameters r5 = r9.params     // Catch:{ all -> 0x00ca }
            org.bitcoinj.core.StoredBlock r5 = org.bitcoinj.core.StoredBlock.deserializeCompact(r5, r3)     // Catch:{ all -> 0x00ca }
            java.util.TreeMap<java.lang.Long, org.bitcoinj.core.StoredBlock> r6 = r9.checkpoints     // Catch:{ all -> 0x00ca }
            org.bitcoinj.core.Block r7 = r5.getHeader()     // Catch:{ all -> 0x00ca }
            long r7 = r7.getTimeSeconds()     // Catch:{ all -> 0x00ca }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x00ca }
            r6.put(r7, r5)     // Catch:{ all -> 0x00ca }
            int r4 = r4 + 1
            goto L_0x0060
        L_0x0092:
            com.google.common.hash.HashCode r10 = r0.hash()     // Catch:{ all -> 0x00ca }
            org.slf4j.Logger r0 = log     // Catch:{ all -> 0x00ca }
            java.lang.String r1 = "Read {} checkpoints, hash is {}"
            java.util.TreeMap<java.lang.Long, org.bitcoinj.core.StoredBlock> r3 = r9.checkpoints     // Catch:{ all -> 0x00ca }
            int r3 = r3.size()     // Catch:{ all -> 0x00ca }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00ca }
            r0.info(r1, r3, r10)     // Catch:{ all -> 0x00ca }
            byte[] r10 = r10.asBytes()     // Catch:{ all -> 0x00ca }
            org.bitcoinj.core.Sha256Hash r10 = org.bitcoinj.core.Sha256Hash.wrap(r10)     // Catch:{ all -> 0x00ca }
            r2.close()
            return r10
        L_0x00b3:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x00ca }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ca }
            r1.<init>()     // Catch:{ all -> 0x00ca }
            java.lang.String r3 = "unexpected magic: "
            r1.append(r3)     // Catch:{ all -> 0x00ca }
            r1.append(r10)     // Catch:{ all -> 0x00ca }
            java.lang.String r10 = r1.toString()     // Catch:{ all -> 0x00ca }
            r0.<init>(r10)     // Catch:{ all -> 0x00ca }
            throw r0     // Catch:{ all -> 0x00ca }
        L_0x00ca:
            r10 = move-exception
            goto L_0x00ce
        L_0x00cc:
            r10 = move-exception
            r2 = r1
        L_0x00ce:
            if (r2 == 0) goto L_0x00d3
            r2.close()
        L_0x00d3:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.CheckpointManager.readTextual(java.io.InputStream):org.bitcoinj.core.Sha256Hash");
    }

    public StoredBlock getCheckpointBefore(long j) {
        try {
            Preconditions.checkArgument(j > this.params.getGenesisBlock().getTimeSeconds());
            Entry floorEntry = this.checkpoints.floorEntry(Long.valueOf(j));
            if (floorEntry != null) {
                return (StoredBlock) floorEntry.getValue();
            }
            Block cloneAsHeader = this.params.getGenesisBlock().cloneAsHeader();
            return new StoredBlock(cloneAsHeader, cloneAsHeader.getWork(), 0);
        } catch (VerificationException e) {
            throw new RuntimeException(e);
        }
    }

    public int numCheckpoints() {
        return this.checkpoints.size();
    }

    public Sha256Hash getDataHash() {
        return this.dataHash;
    }

    public static void checkpoint(NetworkParameters networkParameters, InputStream inputStream, BlockStore blockStore, long j) throws IOException, BlockStoreException {
        Preconditions.checkNotNull(networkParameters);
        Preconditions.checkNotNull(blockStore);
        boolean z = true;
        Preconditions.checkArgument(!(blockStore instanceof FullPrunedBlockStore), "You cannot use checkpointing with a full store.");
        long j2 = j - PaymentChannelServer.DEFAULT_MAX_TIME_WINDOW;
        if (j2 <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        log.info("Attempting to initialize a new block store with a checkpoint for time {} ({})", (Object) Long.valueOf(j2), (Object) C3447Utils.dateTimeFormat(1000 * j2));
        StoredBlock checkpointBefore = new CheckpointManager(networkParameters, new BufferedInputStream(inputStream)).getCheckpointBefore(j2);
        blockStore.put(checkpointBefore);
        blockStore.setChainHead(checkpointBefore);
    }
}
