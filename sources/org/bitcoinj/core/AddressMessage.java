package org.bitcoinj.core;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressMessage extends Message {
    private static final long MAX_ADDRESSES = 1024;
    private List<PeerAddress> addresses;

    AddressMessage(NetworkParameters networkParameters, byte[] bArr, int i, MessageSerializer messageSerializer, int i2) throws ProtocolException {
        super(networkParameters, bArr, i, messageSerializer, i2);
    }

    AddressMessage(NetworkParameters networkParameters, byte[] bArr, MessageSerializer messageSerializer, int i) throws ProtocolException {
        super(networkParameters, bArr, 0, messageSerializer, i);
    }

    AddressMessage(NetworkParameters networkParameters, byte[] bArr, int i) throws ProtocolException {
        super(networkParameters, bArr, i, networkParameters.getDefaultSerializer(), Integer.MIN_VALUE);
    }

    AddressMessage(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0, networkParameters.getDefaultSerializer(), Integer.MIN_VALUE);
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        long readVarInt = readVarInt();
        if (readVarInt <= 1024) {
            this.addresses = new ArrayList((int) readVarInt);
            for (int i = 0; ((long) i) < readVarInt; i++) {
                PeerAddress peerAddress = new PeerAddress(this.params, this.payload, this.cursor, this.protocolVersion, this, this.serializer);
                this.addresses.add(peerAddress);
                this.cursor += peerAddress.getMessageSize();
            }
            this.length = new VarInt((long) this.addresses.size()).getSizeInBytes();
            this.length += this.addresses.size() * (this.protocolVersion > 31402 ? 30 : 26);
            return;
        }
        throw new ProtocolException("Address message too large.");
    }

    /* access modifiers changed from: protected */
    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        List<PeerAddress> list = this.addresses;
        if (list != null) {
            outputStream.write(new VarInt((long) list.size()).encode());
            for (PeerAddress bitcoinSerialize : this.addresses) {
                bitcoinSerialize.bitcoinSerialize(outputStream);
            }
        }
    }

    public List<PeerAddress> getAddresses() {
        return Collections.unmodifiableList(this.addresses);
    }

    public void addAddress(PeerAddress peerAddress) {
        unCache();
        peerAddress.setParent(this);
        this.addresses.add(peerAddress);
        if (this.length == Integer.MIN_VALUE) {
            getMessageSize();
        } else {
            this.length += peerAddress.getMessageSize();
        }
    }

    public void removeAddress(int i) {
        unCache();
        PeerAddress peerAddress = (PeerAddress) this.addresses.remove(i);
        peerAddress.setParent(null);
        if (this.length == Integer.MIN_VALUE) {
            getMessageSize();
        } else {
            this.length -= peerAddress.getMessageSize();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("addr: ");
        sb.append(C3447Utils.join(this.addresses));
        return sb.toString();
    }
}
