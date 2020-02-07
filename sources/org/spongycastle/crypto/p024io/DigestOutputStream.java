package org.spongycastle.crypto.p024io;

import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.crypto.Digest;

/* renamed from: org.spongycastle.crypto.io.DigestOutputStream */
public class DigestOutputStream extends OutputStream {
    protected Digest digest;

    public DigestOutputStream(Digest digest2) {
        this.digest = digest2;
    }

    public void write(int i) throws IOException {
        this.digest.update((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.digest.update(bArr, i, i2);
    }

    public byte[] getDigest() {
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        return bArr;
    }
}
