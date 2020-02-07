package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;

public class SSL3Mac implements Mac {
    static final byte[] IPAD = genPad(IPAD_BYTE, 48);
    private static final byte IPAD_BYTE = 54;
    static final byte[] OPAD = genPad(OPAD_BYTE, 48);
    private static final byte OPAD_BYTE = 92;
    private Digest digest;
    private int padLength;
    private byte[] secret;

    public SSL3Mac(Digest digest2) {
        this.digest = digest2;
        if (digest2.getDigestSize() == 20) {
            this.padLength = 40;
        } else {
            this.padLength = 48;
        }
    }

    public String getAlgorithmName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.digest.getAlgorithmName());
        sb.append("/SSL3MAC");
        return sb.toString();
    }

    public Digest getUnderlyingDigest() {
        return this.digest;
    }

    public void init(CipherParameters cipherParameters) {
        this.secret = Arrays.clone(((KeyParameter) cipherParameters).getKey());
        reset();
    }

    public int getMacSize() {
        return this.digest.getDigestSize();
    }

    public void update(byte b) {
        this.digest.update(b);
    }

    public void update(byte[] bArr, int i, int i2) {
        this.digest.update(bArr, i, i2);
    }

    public int doFinal(byte[] bArr, int i) {
        byte[] bArr2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr2, 0);
        Digest digest2 = this.digest;
        byte[] bArr3 = this.secret;
        digest2.update(bArr3, 0, bArr3.length);
        this.digest.update(OPAD, 0, this.padLength);
        this.digest.update(bArr2, 0, bArr2.length);
        int doFinal = this.digest.doFinal(bArr, i);
        reset();
        return doFinal;
    }

    public void reset() {
        this.digest.reset();
        Digest digest2 = this.digest;
        byte[] bArr = this.secret;
        digest2.update(bArr, 0, bArr.length);
        this.digest.update(IPAD, 0, this.padLength);
    }

    private static byte[] genPad(byte b, int i) {
        byte[] bArr = new byte[i];
        Arrays.fill(bArr, b);
        return bArr;
    }
}
