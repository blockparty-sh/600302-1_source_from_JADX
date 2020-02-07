package org.bitcoinj.core;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import java.util.Collection;
import java.util.List;

public class GetUTXOsMessage extends Message {
    public static final int MIN_PROTOCOL_VERSION = 70002;
    public static final long SERVICE_FLAGS_REQUIRED = 3;
    private boolean includeMempool;
    private ImmutableList<TransactionOutPoint> outPoints;

    public GetUTXOsMessage(NetworkParameters networkParameters, List<TransactionOutPoint> list, boolean z) {
        super(networkParameters);
        this.outPoints = ImmutableList.copyOf((Collection<? extends E>) list);
        this.includeMempool = z;
    }

    public GetUTXOsMessage(NetworkParameters networkParameters, byte[] bArr) {
        super(networkParameters, bArr, 0);
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        boolean z = true;
        if (readBytes(1)[0] != 1) {
            z = false;
        }
        this.includeMempool = z;
        long readVarInt = readVarInt();
        Builder builder = ImmutableList.builder();
        for (int i = 0; ((long) i) < readVarInt; i++) {
            TransactionOutPoint transactionOutPoint = new TransactionOutPoint(this.params, this.payload, this.cursor);
            builder.add((Object) transactionOutPoint);
            this.cursor += transactionOutPoint.getMessageSize();
        }
        this.outPoints = builder.build();
        this.length = this.cursor;
    }

    public boolean getIncludeMempool() {
        return this.includeMempool;
    }

    public ImmutableList<TransactionOutPoint> getOutPoints() {
        return this.outPoints;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Incorrect type for immutable var: ssa=boolean, code=byte, for r1v0, types: [boolean, byte] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void bitcoinSerializeToStream(java.io.OutputStream r4) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 1
            byte[] r0 = new byte[r0]
            boolean r1 = r3.includeMempool
            r2 = 0
            r0[r2] = r1
            r4.write(r0)
            org.bitcoinj.core.VarInt r0 = new org.bitcoinj.core.VarInt
            com.google.common.collect.ImmutableList<org.bitcoinj.core.TransactionOutPoint> r1 = r3.outPoints
            int r1 = r1.size()
            long r1 = (long) r1
            r0.<init>(r1)
            byte[] r0 = r0.encode()
            r4.write(r0)
            com.google.common.collect.ImmutableList<org.bitcoinj.core.TransactionOutPoint> r0 = r3.outPoints
            com.google.common.collect.UnmodifiableIterator r0 = r0.iterator()
        L_0x0024:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0034
            java.lang.Object r1 = r0.next()
            org.bitcoinj.core.TransactionOutPoint r1 = (org.bitcoinj.core.TransactionOutPoint) r1
            r1.bitcoinSerializeToStream(r4)
            goto L_0x0024
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.GetUTXOsMessage.bitcoinSerializeToStream(java.io.OutputStream):void");
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetUTXOsMessage getUTXOsMessage = (GetUTXOsMessage) obj;
        if (this.includeMempool != getUTXOsMessage.includeMempool || !this.outPoints.equals(getUTXOsMessage.outPoints)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.includeMempool), this.outPoints);
    }
}
