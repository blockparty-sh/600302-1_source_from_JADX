package org.spongycastle.crypto.prng;

import java.security.SecureRandom;

public class BasicEntropySourceProvider implements EntropySourceProvider {
    /* access modifiers changed from: private */
    public final boolean _predictionResistant;
    /* access modifiers changed from: private */
    public final SecureRandom _sr;

    public BasicEntropySourceProvider(SecureRandom secureRandom, boolean z) {
        this._sr = secureRandom;
        this._predictionResistant = z;
    }

    public EntropySource get(final int i) {
        return new EntropySource() {
            public boolean isPredictionResistant() {
                return BasicEntropySourceProvider.this._predictionResistant;
            }

            public byte[] getEntropy() {
                return BasicEntropySourceProvider.this._sr.generateSeed((i + 7) / 8);
            }

            public int entropySize() {
                return i;
            }
        };
    }
}
