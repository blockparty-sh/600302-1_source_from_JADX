package org.bitcoinj.core;

import java.io.IOException;
import java.io.OutputStream;

public class Pong extends Message {
    private long nonce;

    public Pong(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0);
    }

    public Pong(long j) {
        this.nonce = j;
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        this.nonce = readInt64();
        this.length = 8;
    }

    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        C3447Utils.int64ToByteStreamLE(this.nonce, outputStream);
    }

    public long getNonce() {
        return this.nonce;
    }
}
