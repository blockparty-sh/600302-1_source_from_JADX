package org.bitcoinj.core;

import java.io.IOException;
import java.io.OutputStream;

public abstract class EmptyMessage extends Message {
    /* access modifiers changed from: protected */
    public final void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
    }

    public EmptyMessage() {
        this.length = 0;
    }

    public EmptyMessage(NetworkParameters networkParameters) {
        super(networkParameters);
        this.length = 0;
    }

    public EmptyMessage(NetworkParameters networkParameters, byte[] bArr, int i) throws ProtocolException {
        super(networkParameters, bArr, i);
        this.length = 0;
    }

    public byte[] bitcoinSerialize() {
        return new byte[0];
    }
}
