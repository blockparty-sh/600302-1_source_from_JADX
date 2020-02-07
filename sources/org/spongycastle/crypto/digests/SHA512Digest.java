package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

public class SHA512Digest extends LongDigest {
    private static final int DIGEST_LENGTH = 64;

    public String getAlgorithmName() {
        return "SHA-512";
    }

    public int getDigestSize() {
        return 64;
    }

    public SHA512Digest() {
    }

    public SHA512Digest(SHA512Digest sHA512Digest) {
        super(sHA512Digest);
    }

    public SHA512Digest(byte[] bArr) {
        restoreState(bArr);
    }

    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.longToBigEndian(this.f975H1, bArr, i);
        Pack.longToBigEndian(this.f976H2, bArr, i + 8);
        Pack.longToBigEndian(this.f977H3, bArr, i + 16);
        Pack.longToBigEndian(this.f978H4, bArr, i + 24);
        Pack.longToBigEndian(this.f979H5, bArr, i + 32);
        Pack.longToBigEndian(this.f980H6, bArr, i + 40);
        Pack.longToBigEndian(this.f981H7, bArr, i + 48);
        Pack.longToBigEndian(this.f982H8, bArr, i + 56);
        reset();
        return 64;
    }

    public void reset() {
        super.reset();
        this.f975H1 = 7640891576956012808L;
        this.f976H2 = -4942790177534073029L;
        this.f977H3 = 4354685564936845355L;
        this.f978H4 = -6534734903238641935L;
        this.f979H5 = 5840696475078001361L;
        this.f980H6 = -7276294671716946913L;
        this.f981H7 = 2270897969802886507L;
        this.f982H8 = 6620516959819538809L;
    }

    public Memoable copy() {
        return new SHA512Digest(this);
    }

    public void reset(Memoable memoable) {
        copyIn((SHA512Digest) memoable);
    }

    public byte[] getEncodedState() {
        byte[] bArr = new byte[getEncodedStateSize()];
        super.populateState(bArr);
        return bArr;
    }
}
