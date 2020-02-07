package org.bitcoinj.core;

import com.google.common.base.Objects;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UTXOsMessage extends Message {
    public static long MEMPOOL_HEIGHT = 2147483647L;
    private Sha256Hash chainHead;
    private long height;
    private long[] heights;
    private byte[] hits;
    private List<TransactionOutput> outputs;

    public UTXOsMessage(NetworkParameters networkParameters, byte[] bArr) {
        super(networkParameters, bArr, 0);
    }

    public UTXOsMessage(NetworkParameters networkParameters, List<TransactionOutput> list, long[] jArr, Sha256Hash sha256Hash, long j) {
        super(networkParameters);
        this.hits = new byte[((int) Math.ceil(((double) list.size()) / 8.0d))];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                C3447Utils.setBitLE(this.hits, i);
            }
        }
        this.outputs = new ArrayList(list.size());
        for (TransactionOutput transactionOutput : list) {
            if (transactionOutput != null) {
                this.outputs.add(transactionOutput);
            }
        }
        this.chainHead = sha256Hash;
        this.height = j;
        this.heights = Arrays.copyOf(jArr, jArr.length);
    }

    /* access modifiers changed from: protected */
    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        C3447Utils.uint32ToByteStreamLE(this.height, outputStream);
        outputStream.write(this.chainHead.getBytes());
        outputStream.write(new VarInt((long) this.hits.length).encode());
        outputStream.write(this.hits);
        outputStream.write(new VarInt((long) this.outputs.size()).encode());
        for (int i = 0; i < this.outputs.size(); i++) {
            TransactionOutput transactionOutput = (TransactionOutput) this.outputs.get(i);
            Transaction parentTransaction = transactionOutput.getParentTransaction();
            C3447Utils.uint32ToByteStreamLE(parentTransaction != null ? parentTransaction.getVersion() : 0, outputStream);
            C3447Utils.uint32ToByteStreamLE(this.heights[i], outputStream);
            transactionOutput.bitcoinSerializeToStream(outputStream);
        }
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        this.height = readUint32();
        this.chainHead = readHash();
        int readVarInt = (int) readVarInt();
        if (readVarInt < 0 || ((long) readVarInt) > 6250) {
            StringBuilder sb = new StringBuilder();
            sb.append("hitsBitmap out of range: ");
            sb.append(readVarInt);
            throw new ProtocolException(sb.toString());
        }
        this.hits = readBytes(readVarInt);
        int readVarInt2 = (int) readVarInt();
        if (readVarInt2 < 0 || ((long) readVarInt2) > ListMessage.MAX_INVENTORY_ITEMS) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("numOuts out of range: ");
            sb2.append(readVarInt2);
            throw new ProtocolException(sb2.toString());
        }
        this.outputs = new ArrayList(readVarInt2);
        this.heights = new long[readVarInt2];
        int i = 0;
        while (i < readVarInt2) {
            long readUint32 = readUint32();
            long readUint322 = readUint32();
            if (readUint32 <= 1) {
                TransactionOutput transactionOutput = new TransactionOutput(this.params, (Transaction) null, this.payload, this.cursor);
                this.outputs.add(transactionOutput);
                this.heights[i] = readUint322;
                this.cursor += transactionOutput.length;
                i++;
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Unknown tx version in getutxo output: ");
                sb3.append(readUint32);
                throw new ProtocolException(sb3.toString());
            }
        }
        this.length = this.cursor;
    }

    public byte[] getHitMap() {
        byte[] bArr = this.hits;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public List<TransactionOutput> getOutputs() {
        return new ArrayList(this.outputs);
    }

    public long[] getHeights() {
        long[] jArr = this.heights;
        return Arrays.copyOf(jArr, jArr.length);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UTXOsMessage{height=");
        sb.append(this.height);
        sb.append(", chainHead=");
        sb.append(this.chainHead);
        sb.append(", hitMap=");
        sb.append(Arrays.toString(this.hits));
        sb.append(", outputs=");
        sb.append(this.outputs);
        sb.append(", heights=");
        sb.append(Arrays.toString(this.heights));
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UTXOsMessage uTXOsMessage = (UTXOsMessage) obj;
        if (this.height != uTXOsMessage.height || !this.chainHead.equals(uTXOsMessage.chainHead) || !Arrays.equals(this.heights, uTXOsMessage.heights) || !Arrays.equals(this.hits, uTXOsMessage.hits) || !this.outputs.equals(uTXOsMessage.outputs)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.height), this.chainHead, Integer.valueOf(Arrays.hashCode(this.heights)), Integer.valueOf(Arrays.hashCode(this.hits)), this.outputs);
    }
}
