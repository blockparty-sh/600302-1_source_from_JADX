package org.spongycastle.crypto.digests;

import org.apache.commons.cli.HelpFormatter;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.params.SkeinParameters;
import org.spongycastle.util.Memoable;

public class SkeinDigest implements ExtendedDigest, Memoable {
    public static final int SKEIN_1024 = 1024;
    public static final int SKEIN_256 = 256;
    public static final int SKEIN_512 = 512;
    private SkeinEngine engine;

    public SkeinDigest(int i, int i2) {
        this.engine = new SkeinEngine(i, i2);
        init(null);
    }

    public SkeinDigest(SkeinDigest skeinDigest) {
        this.engine = new SkeinEngine(skeinDigest.engine);
    }

    public void reset(Memoable memoable) {
        this.engine.reset(((SkeinDigest) memoable).engine);
    }

    public Memoable copy() {
        return new SkeinDigest(this);
    }

    public String getAlgorithmName() {
        StringBuilder sb = new StringBuilder();
        sb.append("Skein-");
        sb.append(this.engine.getBlockSize() * 8);
        sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
        sb.append(this.engine.getOutputSize() * 8);
        return sb.toString();
    }

    public int getDigestSize() {
        return this.engine.getOutputSize();
    }

    public int getByteLength() {
        return this.engine.getBlockSize();
    }

    public void init(SkeinParameters skeinParameters) {
        this.engine.init(skeinParameters);
    }

    public void reset() {
        this.engine.reset();
    }

    public void update(byte b) {
        this.engine.update(b);
    }

    public void update(byte[] bArr, int i, int i2) {
        this.engine.update(bArr, i, i2);
    }

    public int doFinal(byte[] bArr, int i) {
        return this.engine.doFinal(bArr, i);
    }
}
