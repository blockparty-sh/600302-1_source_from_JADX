package org.bitcoinj.core;

import java.util.List;

public class GetHeadersMessage extends GetBlocksMessage {
    public GetHeadersMessage(NetworkParameters networkParameters, List<Sha256Hash> list, Sha256Hash sha256Hash) {
        super(networkParameters, list, sha256Hash);
    }

    public GetHeadersMessage(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("getheaders: ");
        sb.append(C3447Utils.join(this.locator));
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
        GetHeadersMessage getHeadersMessage = (GetHeadersMessage) obj;
        if (this.version != getHeadersMessage.version || !this.stopHash.equals(getHeadersMessage.stopHash) || this.locator.size() != getHeadersMessage.locator.size() || !this.locator.containsAll(getHeadersMessage.locator)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = ("getheaders".hashCode() ^ ((int) this.version)) ^ this.stopHash.hashCode();
        for (Sha256Hash hashCode2 : this.locator) {
            hashCode ^= hashCode2.hashCode();
        }
        return hashCode;
    }
}
