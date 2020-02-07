package org.spongycastle.crypto.p024io;

import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.crypto.Signer;

/* renamed from: org.spongycastle.crypto.io.SignerOutputStream */
public class SignerOutputStream extends OutputStream {
    protected Signer signer;

    public SignerOutputStream(Signer signer2) {
        this.signer = signer2;
    }

    public void write(int i) throws IOException {
        this.signer.update((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.signer.update(bArr, i, i2);
    }

    public Signer getSigner() {
        return this.signer;
    }
}
