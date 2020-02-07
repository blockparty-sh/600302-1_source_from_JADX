package org.spongycastle.crypto.tls;

class DTLSEpoch {
    private final TlsCipher cipher;
    private final int epoch;
    private final DTLSReplayWindow replayWindow = new DTLSReplayWindow();
    private long sequence_number = 0;

    DTLSEpoch(int i, TlsCipher tlsCipher) {
        if (i < 0) {
            throw new IllegalArgumentException("'epoch' must be >= 0");
        } else if (tlsCipher != null) {
            this.epoch = i;
            this.cipher = tlsCipher;
        } else {
            throw new IllegalArgumentException("'cipher' cannot be null");
        }
    }

    /* access modifiers changed from: 0000 */
    public long allocateSequenceNumber() {
        long j = this.sequence_number;
        this.sequence_number = 1 + j;
        return j;
    }

    /* access modifiers changed from: 0000 */
    public TlsCipher getCipher() {
        return this.cipher;
    }

    /* access modifiers changed from: 0000 */
    public int getEpoch() {
        return this.epoch;
    }

    /* access modifiers changed from: 0000 */
    public DTLSReplayWindow getReplayWindow() {
        return this.replayWindow;
    }

    /* access modifiers changed from: 0000 */
    public long getSequence_number() {
        return this.sequence_number;
    }
}
