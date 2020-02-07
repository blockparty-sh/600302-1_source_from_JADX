package org.bitcoinj.core;

import java.io.IOException;
import java.io.OutputStream;

public class Ping extends Message {
    private boolean hasNonce;
    private long nonce;

    public Ping(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0);
    }

    public Ping(long j) {
        this.nonce = j;
        this.hasNonce = true;
    }

    public Ping() {
        this.hasNonce = false;
    }

    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        if (this.hasNonce) {
            C3447Utils.int64ToByteStreamLE(this.nonce, outputStream);
        }
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        int i = 0;
        try {
            this.nonce = readInt64();
            this.hasNonce = true;
        } catch (ProtocolException unused) {
            this.hasNonce = false;
        }
        if (this.hasNonce) {
            i = 8;
        }
        this.length = i;
    }

    public boolean hasNonce() {
        return this.hasNonce;
    }

    public long getNonce() {
        return this.nonce;
    }
}
