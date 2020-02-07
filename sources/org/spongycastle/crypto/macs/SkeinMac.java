package org.spongycastle.crypto.macs;

import org.apache.commons.cli.HelpFormatter;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.digests.SkeinEngine;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.SkeinParameters;
import org.spongycastle.crypto.params.SkeinParameters.Builder;

public class SkeinMac implements Mac {
    public static final int SKEIN_1024 = 1024;
    public static final int SKEIN_256 = 256;
    public static final int SKEIN_512 = 512;
    private SkeinEngine engine;

    public SkeinMac(int i, int i2) {
        this.engine = new SkeinEngine(i, i2);
    }

    public SkeinMac(SkeinMac skeinMac) {
        this.engine = new SkeinEngine(skeinMac.engine);
    }

    public String getAlgorithmName() {
        StringBuilder sb = new StringBuilder();
        sb.append("Skein-MAC-");
        sb.append(this.engine.getBlockSize() * 8);
        sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
        sb.append(this.engine.getOutputSize() * 8);
        return sb.toString();
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        SkeinParameters skeinParameters;
        if (cipherParameters instanceof SkeinParameters) {
            skeinParameters = (SkeinParameters) cipherParameters;
        } else if (cipherParameters instanceof KeyParameter) {
            skeinParameters = new Builder().setKey(((KeyParameter) cipherParameters).getKey()).build();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid parameter passed to Skein MAC init - ");
            sb.append(cipherParameters.getClass().getName());
            throw new IllegalArgumentException(sb.toString());
        }
        if (skeinParameters.getKey() != null) {
            this.engine.init(skeinParameters);
            return;
        }
        throw new IllegalArgumentException("Skein MAC requires a key parameter.");
    }

    public int getMacSize() {
        return this.engine.getOutputSize();
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
