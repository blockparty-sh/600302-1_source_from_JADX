package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;

public class ShortenedDigest implements ExtendedDigest {
    private ExtendedDigest baseDigest;
    private int length;

    public ShortenedDigest(ExtendedDigest extendedDigest, int i) {
        if (extendedDigest == null) {
            throw new IllegalArgumentException("baseDigest must not be null");
        } else if (i <= extendedDigest.getDigestSize()) {
            this.baseDigest = extendedDigest;
            this.length = i;
        } else {
            throw new IllegalArgumentException("baseDigest output not large enough to support length");
        }
    }

    public String getAlgorithmName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.baseDigest.getAlgorithmName());
        sb.append("(");
        sb.append(this.length * 8);
        sb.append(")");
        return sb.toString();
    }

    public int getDigestSize() {
        return this.length;
    }

    public void update(byte b) {
        this.baseDigest.update(b);
    }

    public void update(byte[] bArr, int i, int i2) {
        this.baseDigest.update(bArr, i, i2);
    }

    public int doFinal(byte[] bArr, int i) {
        byte[] bArr2 = new byte[this.baseDigest.getDigestSize()];
        this.baseDigest.doFinal(bArr2, 0);
        System.arraycopy(bArr2, 0, bArr, i, this.length);
        return this.length;
    }

    public void reset() {
        this.baseDigest.reset();
    }

    public int getByteLength() {
        return this.baseDigest.getByteLength();
    }
}
