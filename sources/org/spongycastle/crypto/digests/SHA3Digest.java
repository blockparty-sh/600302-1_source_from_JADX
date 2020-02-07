package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Arrays;

public class SHA3Digest implements ExtendedDigest {
    private static int[] KeccakRhoOffsets = keccakInitializeRhoOffsets();
    private static long[] KeccakRoundConstants = keccakInitializeRoundConstants();

    /* renamed from: C */
    long[] f1059C = new long[5];
    private int bitsAvailableForSqueezing;
    private int bitsInQueue;
    long[] chiC = new long[5];
    private byte[] chunk;
    private byte[] dataQueue = new byte[192];
    private int fixedOutputLength;
    private byte[] oneByte;
    private int rate;
    private boolean squeezing;
    private byte[] state = new byte[200];
    long[] tempA = new long[25];

    private static long[] keccakInitializeRoundConstants() {
        long[] jArr = new long[24];
        byte[] bArr = {1};
        for (int i = 0; i < 24; i++) {
            jArr[i] = 0;
            for (int i2 = 0; i2 < 7; i2++) {
                int i3 = (1 << i2) - 1;
                if (LFSR86540(bArr)) {
                    jArr[i] = jArr[i] ^ (1 << i3);
                }
            }
        }
        return jArr;
    }

    private static boolean LFSR86540(byte[] bArr) {
        boolean z = (bArr[0] & 1) != 0;
        if ((bArr[0] & 128) != 0) {
            bArr[0] = (byte) ((bArr[0] << 1) ^ 113);
        } else {
            bArr[0] = (byte) (bArr[0] << 1);
        }
        return z;
    }

    private static int[] keccakInitializeRhoOffsets() {
        int[] iArr = new int[25];
        int i = 0;
        iArr[0] = 0;
        int i2 = 1;
        int i3 = 0;
        while (i < 24) {
            int i4 = i + 1;
            iArr[(i2 % 5) + ((i3 % 5) * 5)] = (((i + 2) * i4) / 2) % 64;
            i3 = ((i2 * 2) + (i3 * 3)) % 5;
            i2 = ((i2 * 0) + (i3 * 1)) % 5;
            i = i4;
        }
        return iArr;
    }

    private void clearDataQueueSection(int i, int i2) {
        for (int i3 = i; i3 != i + i2; i3++) {
            this.dataQueue[i3] = 0;
        }
    }

    public SHA3Digest() {
        init(0);
    }

    public SHA3Digest(int i) {
        init(i);
    }

    public SHA3Digest(SHA3Digest sHA3Digest) {
        byte[] bArr = sHA3Digest.state;
        System.arraycopy(bArr, 0, this.state, 0, bArr.length);
        byte[] bArr2 = sHA3Digest.dataQueue;
        System.arraycopy(bArr2, 0, this.dataQueue, 0, bArr2.length);
        this.rate = sHA3Digest.rate;
        this.bitsInQueue = sHA3Digest.bitsInQueue;
        this.fixedOutputLength = sHA3Digest.fixedOutputLength;
        this.squeezing = sHA3Digest.squeezing;
        this.bitsAvailableForSqueezing = sHA3Digest.bitsAvailableForSqueezing;
        this.chunk = Arrays.clone(sHA3Digest.chunk);
        this.oneByte = Arrays.clone(sHA3Digest.oneByte);
    }

    public String getAlgorithmName() {
        StringBuilder sb = new StringBuilder();
        sb.append("SHA3-");
        sb.append(this.fixedOutputLength);
        return sb.toString();
    }

    public int getDigestSize() {
        return this.fixedOutputLength / 8;
    }

    public void update(byte b) {
        byte[] bArr = this.oneByte;
        bArr[0] = b;
        doUpdate(bArr, 0, 8);
    }

    public void update(byte[] bArr, int i, int i2) {
        doUpdate(bArr, i, ((long) i2) * 8);
    }

    public int doFinal(byte[] bArr, int i) {
        squeeze(bArr, i, (long) this.fixedOutputLength);
        reset();
        return getDigestSize();
    }

    public void reset() {
        init(this.fixedOutputLength);
    }

    public int getByteLength() {
        return this.rate / 8;
    }

    private void init(int i) {
        if (i != 0) {
            if (i == 224) {
                initSponge(1152, 448);
                return;
            } else if (i == 256) {
                initSponge(1088, 512);
                return;
            } else if (i != 288) {
                if (i == 384) {
                    initSponge(832, 768);
                    return;
                } else if (i == 512) {
                    initSponge(576, 1024);
                    return;
                } else {
                    throw new IllegalArgumentException("bitLength must be one of 224, 256, 384, or 512.");
                }
            }
        }
        initSponge(1024, 576);
    }

    private void doUpdate(byte[] bArr, int i, long j) {
        long j2 = j % 8;
        if (j2 == 0) {
            absorb(bArr, i, j);
            return;
        }
        absorb(bArr, i, j - j2);
        absorb(new byte[]{(byte) (bArr[((int) (j / 8)) + i] >> ((int) (8 - j2)))}, i, j2);
    }

    private void initSponge(int i, int i2) {
        if (i + i2 != 1600) {
            throw new IllegalStateException("rate + capacity != 1600");
        } else if (i <= 0 || i >= 1600 || i % 64 != 0) {
            throw new IllegalStateException("invalid rate value");
        } else {
            this.rate = i;
            this.fixedOutputLength = 0;
            Arrays.fill(this.state, 0);
            Arrays.fill(this.dataQueue, 0);
            this.bitsInQueue = 0;
            this.squeezing = false;
            this.bitsAvailableForSqueezing = 0;
            this.fixedOutputLength = i2 / 2;
            this.chunk = new byte[(i / 8)];
            this.oneByte = new byte[1];
        }
    }

    private void absorbQueue() {
        KeccakAbsorb(this.state, this.dataQueue, this.rate / 8);
        this.bitsInQueue = 0;
    }

    private void absorb(byte[] bArr, int i, long j) {
        byte[] bArr2 = bArr;
        int i2 = i;
        if (this.bitsInQueue % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue.");
        } else if (!this.squeezing) {
            long j2 = 0;
            while (j2 < j) {
                if (this.bitsInQueue == 0) {
                    int i3 = this.rate;
                    if (j >= ((long) i3) && j2 <= j - ((long) i3)) {
                        long j3 = (j - j2) / ((long) i3);
                        for (long j4 = 0; j4 < j3; j4++) {
                            long j5 = ((long) i2) + (j2 / 8);
                            byte[] bArr3 = this.chunk;
                            System.arraycopy(bArr2, (int) (j5 + (((long) bArr3.length) * j4)), bArr3, 0, bArr3.length);
                            byte[] bArr4 = this.state;
                            byte[] bArr5 = this.chunk;
                            KeccakAbsorb(bArr4, bArr5, bArr5.length);
                        }
                        j2 += j3 * ((long) this.rate);
                    }
                }
                int i4 = (int) (j - j2);
                int i5 = this.bitsInQueue;
                int i6 = i4 + i5;
                int i7 = this.rate;
                if (i6 > i7) {
                    i4 = i7 - i5;
                }
                int i8 = i4 % 8;
                int i9 = i4 - i8;
                System.arraycopy(bArr2, ((int) (j2 / 8)) + i2, this.dataQueue, this.bitsInQueue / 8, i9 / 8);
                this.bitsInQueue += i9;
                j2 += (long) i9;
                if (this.bitsInQueue == this.rate) {
                    absorbQueue();
                }
                if (i8 > 0) {
                    int i10 = (1 << i8) - 1;
                    byte[] bArr6 = this.dataQueue;
                    int i11 = this.bitsInQueue;
                    bArr6[i11 / 8] = (byte) (i10 & bArr2[i2 + ((int) (j2 / 8))]);
                    this.bitsInQueue = i11 + i8;
                    j2 += (long) i8;
                }
            }
        } else {
            throw new IllegalStateException("attempt to absorb while squeezing.");
        }
    }

    private void padAndSwitchToSqueezingPhase() {
        int i = this.bitsInQueue;
        int i2 = i + 1;
        int i3 = this.rate;
        if (i2 == i3) {
            byte[] bArr = this.dataQueue;
            int i4 = i / 8;
            bArr[i4] = (byte) ((1 << (i % 8)) | bArr[i4]);
            absorbQueue();
            clearDataQueueSection(0, this.rate / 8);
        } else {
            clearDataQueueSection((i + 7) / 8, (i3 / 8) - ((i + 7) / 8));
            byte[] bArr2 = this.dataQueue;
            int i5 = this.bitsInQueue;
            int i6 = i5 / 8;
            bArr2[i6] = (byte) ((1 << (i5 % 8)) | bArr2[i6]);
        }
        byte[] bArr3 = this.dataQueue;
        int i7 = this.rate;
        int i8 = (i7 - 1) / 8;
        bArr3[i8] = (byte) ((1 << ((i7 - 1) % 8)) | bArr3[i8]);
        absorbQueue();
        int i9 = this.rate;
        if (i9 == 1024) {
            KeccakExtract1024bits(this.state, this.dataQueue);
            this.bitsAvailableForSqueezing = 1024;
        } else {
            KeccakExtract(this.state, this.dataQueue, i9 / 64);
            this.bitsAvailableForSqueezing = this.rate;
        }
        this.squeezing = true;
    }

    private void squeeze(byte[] bArr, int i, long j) {
        if (!this.squeezing) {
            padAndSwitchToSqueezingPhase();
        }
        long j2 = 0;
        if (j % 8 == 0) {
            while (j2 < j) {
                if (this.bitsAvailableForSqueezing == 0) {
                    keccakPermutation(this.state);
                    int i2 = this.rate;
                    if (i2 == 1024) {
                        KeccakExtract1024bits(this.state, this.dataQueue);
                        this.bitsAvailableForSqueezing = 1024;
                    } else {
                        KeccakExtract(this.state, this.dataQueue, i2 / 64);
                        this.bitsAvailableForSqueezing = this.rate;
                    }
                }
                int i3 = this.bitsAvailableForSqueezing;
                long j3 = j - j2;
                if (((long) i3) > j3) {
                    i3 = (int) j3;
                }
                System.arraycopy(this.dataQueue, (this.rate - this.bitsAvailableForSqueezing) / 8, bArr, ((int) (j2 / 8)) + i, i3 / 8);
                this.bitsAvailableForSqueezing -= i3;
                j2 += (long) i3;
            }
            return;
        }
        throw new IllegalStateException("outputLength not a multiple of 8");
    }

    private void fromBytesToWords(long[] jArr, byte[] bArr) {
        for (int i = 0; i < 25; i++) {
            jArr[i] = 0;
            int i2 = i * 8;
            for (int i3 = 0; i3 < 8; i3++) {
                jArr[i] = jArr[i] | ((((long) bArr[i2 + i3]) & 255) << (i3 * 8));
            }
        }
    }

    private void fromWordsToBytes(byte[] bArr, long[] jArr) {
        for (int i = 0; i < 25; i++) {
            int i2 = i * 8;
            for (int i3 = 0; i3 < 8; i3++) {
                bArr[i2 + i3] = (byte) ((int) ((jArr[i] >>> (i3 * 8)) & 255));
            }
        }
    }

    private void keccakPermutation(byte[] bArr) {
        long[] jArr = new long[(bArr.length / 8)];
        fromBytesToWords(jArr, bArr);
        keccakPermutationOnWords(jArr);
        fromWordsToBytes(bArr, jArr);
    }

    private void keccakPermutationAfterXor(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
        keccakPermutation(bArr);
    }

    private void keccakPermutationOnWords(long[] jArr) {
        for (int i = 0; i < 24; i++) {
            theta(jArr);
            rho(jArr);
            m395pi(jArr);
            chi(jArr);
            iota(jArr, i);
        }
    }

    private void theta(long[] jArr) {
        for (int i = 0; i < 5; i++) {
            this.f1059C[i] = 0;
            for (int i2 = 0; i2 < 5; i2++) {
                long[] jArr2 = this.f1059C;
                jArr2[i] = jArr2[i] ^ jArr[(i2 * 5) + i];
            }
        }
        int i3 = 0;
        while (i3 < 5) {
            long[] jArr3 = this.f1059C;
            int i4 = i3 + 1;
            int i5 = i4 % 5;
            long j = ((jArr3[i5] << 1) ^ (jArr3[i5] >>> 63)) ^ jArr3[(i3 + 4) % 5];
            for (int i6 = 0; i6 < 5; i6++) {
                int i7 = (i6 * 5) + i3;
                jArr[i7] = jArr[i7] ^ j;
            }
            i3 = i4;
        }
    }

    private void rho(long[] jArr) {
        for (int i = 0; i < 5; i++) {
            for (int i2 = 0; i2 < 5; i2++) {
                int i3 = (i2 * 5) + i;
                int[] iArr = KeccakRhoOffsets;
                jArr[i3] = iArr[i3] != 0 ? (jArr[i3] << iArr[i3]) ^ (jArr[i3] >>> (64 - iArr[i3])) : jArr[i3];
            }
        }
    }

    /* renamed from: pi */
    private void m395pi(long[] jArr) {
        long[] jArr2 = this.tempA;
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        for (int i = 0; i < 5; i++) {
            for (int i2 = 0; i2 < 5; i2++) {
                jArr[((((i * 2) + (i2 * 3)) % 5) * 5) + i2] = this.tempA[(i2 * 5) + i];
            }
        }
    }

    private void chi(long[] jArr) {
        for (int i = 0; i < 5; i++) {
            int i2 = 0;
            while (i2 < 5) {
                int i3 = i * 5;
                int i4 = i2 + 1;
                this.chiC[i2] = jArr[i2 + i3] ^ ((~jArr[(i4 % 5) + i3]) & jArr[((i2 + 2) % 5) + i3]);
                i2 = i4;
            }
            for (int i5 = 0; i5 < 5; i5++) {
                jArr[(i * 5) + i5] = this.chiC[i5];
            }
        }
    }

    private void iota(long[] jArr, int i) {
        jArr[0] = jArr[0] ^ KeccakRoundConstants[i];
    }

    private void KeccakAbsorb(byte[] bArr, byte[] bArr2, int i) {
        keccakPermutationAfterXor(bArr, bArr2, i);
    }

    private void KeccakExtract1024bits(byte[] bArr, byte[] bArr2) {
        System.arraycopy(bArr, 0, bArr2, 0, 128);
    }

    private void KeccakExtract(byte[] bArr, byte[] bArr2, int i) {
        System.arraycopy(bArr, 0, bArr2, 0, i * 8);
    }
}
