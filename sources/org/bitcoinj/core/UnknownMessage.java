package org.bitcoinj.core;

public class UnknownMessage extends EmptyMessage {
    private String name;

    public UnknownMessage(NetworkParameters networkParameters, String str, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0);
        this.name = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown message [");
        sb.append(this.name);
        sb.append("]: ");
        sb.append(this.payload == null ? "" : C3447Utils.HEX.encode(this.payload));
        return sb.toString();
    }
}
