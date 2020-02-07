package org.bitcoinj.core;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.bitcoinj.core.NetworkParameters.ProtocolVersion;

public class GetBlocksMessage extends Message {
    protected List<Sha256Hash> locator;
    protected Sha256Hash stopHash;
    protected long version;

    public GetBlocksMessage(NetworkParameters networkParameters, List<Sha256Hash> list, Sha256Hash sha256Hash) {
        super(networkParameters);
        this.version = (long) this.protocolVersion;
        this.locator = list;
        this.stopHash = sha256Hash;
    }

    public GetBlocksMessage(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0);
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        this.cursor = this.offset;
        this.version = readUint32();
        int readVarInt = (int) readVarInt();
        if (readVarInt <= 500) {
            this.length = (this.cursor - this.offset) + ((readVarInt + 1) * 32);
            this.locator = new ArrayList(readVarInt);
            for (int i = 0; i < readVarInt; i++) {
                this.locator.add(readHash());
            }
            this.stopHash = readHash();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Number of locators cannot be > 500, received: ");
        sb.append(readVarInt);
        throw new ProtocolException(sb.toString());
    }

    public List<Sha256Hash> getLocator() {
        return this.locator;
    }

    public Sha256Hash getStopHash() {
        return this.stopHash;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("getblocks: ");
        sb.append(C3447Utils.join(this.locator));
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        C3447Utils.uint32ToByteStreamLE((long) this.params.getProtocolVersionNum(ProtocolVersion.CURRENT), outputStream);
        outputStream.write(new VarInt((long) this.locator.size()).encode());
        for (Sha256Hash reversedBytes : this.locator) {
            outputStream.write(reversedBytes.getReversedBytes());
        }
        outputStream.write(this.stopHash.getReversedBytes());
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetBlocksMessage getBlocksMessage = (GetBlocksMessage) obj;
        if (this.version != getBlocksMessage.version || !this.stopHash.equals(getBlocksMessage.stopHash) || this.locator.size() != getBlocksMessage.locator.size() || !this.locator.containsAll(getBlocksMessage.locator)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = ("getblocks".hashCode() ^ ((int) this.version)) ^ this.stopHash.hashCode();
        for (Sha256Hash hashCode2 : this.locator) {
            hashCode ^= hashCode2.hashCode();
        }
        return hashCode;
    }
}
