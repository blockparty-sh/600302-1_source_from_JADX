package org.spongycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.spongycastle.crypto.Digest;

public class NullDigest implements Digest {
    private ByteArrayOutputStream bOut = new ByteArrayOutputStream();

    public String getAlgorithmName() {
        return "NULL";
    }

    public int getDigestSize() {
        return this.bOut.size();
    }

    public void update(byte b) {
        this.bOut.write(b);
    }

    public void update(byte[] bArr, int i, int i2) {
        this.bOut.write(bArr, i, i2);
    }

    public int doFinal(byte[] bArr, int i) {
        byte[] byteArray = this.bOut.toByteArray();
        System.arraycopy(byteArray, 0, bArr, i, byteArray.length);
        reset();
        return byteArray.length;
    }

    public void reset() {
        this.bOut.reset();
    }
}
