package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;

public class NonMemoableDigest implements ExtendedDigest {
    private ExtendedDigest baseDigest;

    public NonMemoableDigest(ExtendedDigest extendedDigest) {
        if (extendedDigest != null) {
            this.baseDigest = extendedDigest;
            return;
        }
        throw new IllegalArgumentException("baseDigest must not be null");
    }

    public String getAlgorithmName() {
        return this.baseDigest.getAlgorithmName();
    }

    public int getDigestSize() {
        return this.baseDigest.getDigestSize();
    }

    public void update(byte b) {
        this.baseDigest.update(b);
    }

    public void update(byte[] bArr, int i, int i2) {
        this.baseDigest.update(bArr, i, i2);
    }

    public int doFinal(byte[] bArr, int i) {
        return this.baseDigest.doFinal(bArr, i);
    }

    public void reset() {
        this.baseDigest.reset();
    }

    public int getByteLength() {
        return this.baseDigest.getByteLength();
    }
}
