package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

public abstract class LongDigest implements ExtendedDigest, Memoable, EncodableDigest {
    private static final int BYTE_LENGTH = 128;

    /* renamed from: K */
    static final long[] f974K = {4794697086780616226L, 8158064640168781261L, -5349999486874862801L, -1606136188198331460L, 4131703408338449720L, 6480981068601479193L, -7908458776815382629L, -6116909921290321640L, -2880145864133508542L, 1334009975649890238L, 2608012711638119052L, 6128411473006802146L, 8268148722764581231L, -9160688886553864527L, -7215885187991268811L, -4495734319001033068L, -1973867731355612462L, -1171420211273849373L, 1135362057144423861L, 2597628984639134821L, 3308224258029322869L, 5365058923640841347L, 6679025012923562964L, 8573033837759648693L, -7476448914759557205L, -6327057829258317296L, -5763719355590565569L, -4658551843659510044L, -4116276920077217854L, -3051310485924567259L, 489312712824947311L, 1452737877330783856L, 2861767655752347644L, 3322285676063803686L, 5560940570517711597L, 5996557281743188959L, 7280758554555802590L, 8532644243296465576L, -9096487096722542874L, -7894198246740708037L, -6719396339535248540L, -6333637450476146687L, -4446306890439682159L, -4076793802049405392L, -3345356375505022440L, -2983346525034927856L, -860691631967231958L, 1182934255886127544L, 1847814050463011016L, 2177327727835720531L, 2830643537854262169L, 3796741975233480872L, 4115178125766777443L, 5681478168544905931L, 6601373596472566643L, 7507060721942968483L, 8399075790359081724L, 8693463985226723168L, -8878714635349349518L, -8302665154208450068L, -8016688836872298968L, -6606660893046293015L, -4685533653050689259L, -4147400797238176981L, -3880063495543823972L, -3348786107499101689L, -1523767162380948706L, -757361751448694408L, 500013540394364858L, 748580250866718886L, 1242879168328830382L, 1977374033974150939L, 2944078676154940804L, 3659926193048069267L, 4368137639120453308L, 4836135668995329356L, 5532061633213252278L, 6448918945643986474L, 6902733635092675308L, 7801388544844847127L};

    /* renamed from: H1 */
    protected long f975H1;

    /* renamed from: H2 */
    protected long f976H2;

    /* renamed from: H3 */
    protected long f977H3;

    /* renamed from: H4 */
    protected long f978H4;

    /* renamed from: H5 */
    protected long f979H5;

    /* renamed from: H6 */
    protected long f980H6;

    /* renamed from: H7 */
    protected long f981H7;

    /* renamed from: H8 */
    protected long f982H8;

    /* renamed from: W */
    private long[] f983W;
    private long byteCount1;
    private long byteCount2;
    private int wOff;
    private byte[] xBuf;
    private int xBufOff;

    /* renamed from: Ch */
    private long m352Ch(long j, long j2, long j3) {
        return ((~j) & j3) ^ (j2 & j);
    }

    private long Maj(long j, long j2, long j3) {
        return ((j & j3) ^ (j & j2)) ^ (j2 & j3);
    }

    private long Sigma0(long j) {
        return (j >>> 7) ^ (((j << 63) | (j >>> 1)) ^ ((j << 56) | (j >>> 8)));
    }

    private long Sigma1(long j) {
        return (j >>> 6) ^ (((j << 45) | (j >>> 19)) ^ ((j << 3) | (j >>> 61)));
    }

    private long Sum0(long j) {
        return ((j >>> 39) | (j << 25)) ^ (((j << 36) | (j >>> 28)) ^ ((j << 30) | (j >>> 34)));
    }

    private long Sum1(long j) {
        return ((j >>> 41) | (j << 23)) ^ (((j << 50) | (j >>> 14)) ^ ((j << 46) | (j >>> 18)));
    }

    public int getByteLength() {
        return 128;
    }

    protected LongDigest() {
        this.xBuf = new byte[8];
        this.f983W = new long[80];
        this.xBufOff = 0;
        reset();
    }

    protected LongDigest(LongDigest longDigest) {
        this.xBuf = new byte[8];
        this.f983W = new long[80];
        copyIn(longDigest);
    }

    /* access modifiers changed from: protected */
    public void copyIn(LongDigest longDigest) {
        byte[] bArr = longDigest.xBuf;
        System.arraycopy(bArr, 0, this.xBuf, 0, bArr.length);
        this.xBufOff = longDigest.xBufOff;
        this.byteCount1 = longDigest.byteCount1;
        this.byteCount2 = longDigest.byteCount2;
        this.f975H1 = longDigest.f975H1;
        this.f976H2 = longDigest.f976H2;
        this.f977H3 = longDigest.f977H3;
        this.f978H4 = longDigest.f978H4;
        this.f979H5 = longDigest.f979H5;
        this.f980H6 = longDigest.f980H6;
        this.f981H7 = longDigest.f981H7;
        this.f982H8 = longDigest.f982H8;
        long[] jArr = longDigest.f983W;
        System.arraycopy(jArr, 0, this.f983W, 0, jArr.length);
        this.wOff = longDigest.wOff;
    }

    /* access modifiers changed from: protected */
    public void populateState(byte[] bArr) {
        System.arraycopy(this.xBuf, 0, bArr, 0, this.xBufOff);
        Pack.intToBigEndian(this.xBufOff, bArr, 8);
        Pack.longToBigEndian(this.byteCount1, bArr, 12);
        Pack.longToBigEndian(this.byteCount2, bArr, 20);
        Pack.longToBigEndian(this.f975H1, bArr, 28);
        Pack.longToBigEndian(this.f976H2, bArr, 36);
        Pack.longToBigEndian(this.f977H3, bArr, 44);
        Pack.longToBigEndian(this.f978H4, bArr, 52);
        Pack.longToBigEndian(this.f979H5, bArr, 60);
        Pack.longToBigEndian(this.f980H6, bArr, 68);
        Pack.longToBigEndian(this.f981H7, bArr, 76);
        Pack.longToBigEndian(this.f982H8, bArr, 84);
        Pack.intToBigEndian(this.wOff, bArr, 92);
        for (int i = 0; i < this.wOff; i++) {
            Pack.longToBigEndian(this.f983W[i], bArr, (i * 8) + 96);
        }
    }

    /* access modifiers changed from: protected */
    public void restoreState(byte[] bArr) {
        this.xBufOff = Pack.bigEndianToInt(bArr, 8);
        System.arraycopy(bArr, 0, this.xBuf, 0, this.xBufOff);
        this.byteCount1 = Pack.bigEndianToLong(bArr, 12);
        this.byteCount2 = Pack.bigEndianToLong(bArr, 20);
        this.f975H1 = Pack.bigEndianToLong(bArr, 28);
        this.f976H2 = Pack.bigEndianToLong(bArr, 36);
        this.f977H3 = Pack.bigEndianToLong(bArr, 44);
        this.f978H4 = Pack.bigEndianToLong(bArr, 52);
        this.f979H5 = Pack.bigEndianToLong(bArr, 60);
        this.f980H6 = Pack.bigEndianToLong(bArr, 68);
        this.f981H7 = Pack.bigEndianToLong(bArr, 76);
        this.f982H8 = Pack.bigEndianToLong(bArr, 84);
        this.wOff = Pack.bigEndianToInt(bArr, 92);
        for (int i = 0; i < this.wOff; i++) {
            this.f983W[i] = Pack.bigEndianToLong(bArr, (i * 8) + 96);
        }
    }

    /* access modifiers changed from: protected */
    public int getEncodedStateSize() {
        return (this.wOff * 8) + 96;
    }

    public void update(byte b) {
        byte[] bArr = this.xBuf;
        int i = this.xBufOff;
        this.xBufOff = i + 1;
        bArr[i] = b;
        if (this.xBufOff == bArr.length) {
            processWord(bArr, 0);
            this.xBufOff = 0;
        }
        this.byteCount1++;
    }

    public void update(byte[] bArr, int i, int i2) {
        while (this.xBufOff != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (i2 > this.xBuf.length) {
            processWord(bArr, i);
            byte[] bArr2 = this.xBuf;
            i += bArr2.length;
            i2 -= bArr2.length;
            this.byteCount1 += (long) bArr2.length;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }

    public void finish() {
        adjustByteCounts();
        long j = this.byteCount1 << 3;
        long j2 = this.byteCount2;
        update(Byte.MIN_VALUE);
        while (this.xBufOff != 0) {
            update(0);
        }
        processLength(j, j2);
        processBlock();
    }

    public void reset() {
        this.byteCount1 = 0;
        this.byteCount2 = 0;
        int i = 0;
        this.xBufOff = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.xBuf;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = 0;
            i2++;
        }
        this.wOff = 0;
        while (true) {
            long[] jArr = this.f983W;
            if (i != jArr.length) {
                jArr[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void processWord(byte[] bArr, int i) {
        this.f983W[this.wOff] = Pack.bigEndianToLong(bArr, i);
        int i2 = this.wOff + 1;
        this.wOff = i2;
        if (i2 == 16) {
            processBlock();
        }
    }

    private void adjustByteCounts() {
        long j = this.byteCount1;
        if (j > 2305843009213693951L) {
            this.byteCount2 += j >>> 61;
            this.byteCount1 = j & 2305843009213693951L;
        }
    }

    /* access modifiers changed from: protected */
    public void processLength(long j, long j2) {
        if (this.wOff > 14) {
            processBlock();
        }
        long[] jArr = this.f983W;
        jArr[14] = j2;
        jArr[15] = j;
    }

    /* access modifiers changed from: protected */
    public void processBlock() {
        adjustByteCounts();
        for (int i = 16; i <= 79; i++) {
            long[] jArr = this.f983W;
            long Sigma1 = Sigma1(jArr[i - 2]);
            long[] jArr2 = this.f983W;
            jArr[i] = Sigma1 + jArr2[i - 7] + Sigma0(jArr2[i - 15]) + this.f983W[i - 16];
        }
        long j = this.f975H1;
        long j2 = this.f976H2;
        long j3 = this.f977H3;
        long j4 = this.f978H4;
        long j5 = this.f979H5;
        long j6 = this.f980H6;
        long j7 = j4;
        long j8 = this.f981H7;
        long j9 = j;
        long j10 = this.f982H8;
        long j11 = j8;
        long j12 = j6;
        int i2 = 0;
        int i3 = 0;
        long j13 = j2;
        long j14 = j5;
        long j15 = j9;
        long j16 = j7;
        long j17 = j3;
        long j18 = j14;
        while (i2 < 10) {
            long j19 = j18;
            int i4 = i3 + 1;
            long Sum1 = j10 + Sum1(j18) + m352Ch(j18, j12, j11) + f974K[i3] + this.f983W[i3];
            long j20 = j15;
            long j21 = j16 + Sum1;
            long Sum0 = Sum1 + Sum0(j15) + Maj(j15, j13, j17);
            long j22 = j21;
            long j23 = Sum0;
            int i5 = i4 + 1;
            long Sum12 = j11 + Sum1(j21) + m352Ch(j21, j19, j12) + f974K[i4] + this.f983W[i4];
            int i6 = i2;
            long j24 = j23;
            long j25 = j17 + Sum12;
            int i7 = i5 + 1;
            long Sum13 = j12 + Sum1(j25) + m352Ch(j25, j22, j19) + f974K[i5] + this.f983W[i5];
            long j26 = j13 + Sum13;
            long Sum02 = Sum12 + Sum0(j23) + Maj(j23, j20, j13);
            long j27 = Sum02;
            long Sum03 = Sum13 + Sum0(Sum02) + Maj(Sum02, j24, j20);
            long j28 = j26;
            long j29 = Sum03;
            int i8 = i7 + 1;
            long Sum14 = j19 + Sum1(j26) + m352Ch(j26, j25, j22) + f974K[i7] + this.f983W[i7];
            long j30 = j29;
            long j31 = j20 + Sum14;
            long j32 = j31;
            long Sum04 = Sum14 + Sum0(j29) + Maj(j29, j27, j24);
            int i9 = i8 + 1;
            long Sum15 = j22 + Sum1(j31) + m352Ch(j31, j28, j25) + f974K[i8] + this.f983W[i8];
            long j33 = j24 + Sum15;
            long Sum05 = Sum0(Sum04);
            long j34 = Sum04;
            long j35 = j33;
            long Maj = Sum15 + Sum05 + Maj(Sum04, j30, j27);
            int i10 = i9 + 1;
            long Sum16 = j25 + Sum1(j35) + m352Ch(j35, j32, j28) + f974K[i9] + this.f983W[i9];
            long j36 = j27 + Sum16;
            long j37 = Maj;
            long j38 = j36;
            long Sum06 = Sum16 + Sum0(Maj) + Maj(Maj, j34, j30);
            int i11 = i10 + 1;
            long Sum17 = j28 + Sum1(j38) + m352Ch(j38, j35, j32) + f974K[i10] + this.f983W[i10];
            long j39 = j30 + Sum17;
            long Sum07 = Sum0(Sum06);
            long j40 = Sum06;
            long j41 = j39;
            long Maj2 = Sum17 + Sum07 + Maj(Sum06, j37, j34);
            long Sum18 = Sum1(j41);
            j12 = j41;
            long j42 = Maj2;
            long Ch = j32 + Sum18 + m352Ch(j41, j38, j35) + f974K[i11] + this.f983W[i11];
            long Sum08 = Ch + Sum0(j42) + Maj(j42, j40, j37);
            j18 = j34 + Ch;
            j16 = j37;
            j17 = j40;
            j10 = j35;
            j11 = j38;
            j15 = Sum08;
            j13 = j42;
            i3 = i11 + 1;
            i2 = i6 + 1;
        }
        long j43 = j18;
        this.f975H1 += j15;
        this.f976H2 += j13;
        this.f977H3 += j17;
        this.f978H4 += j16;
        this.f979H5 += j43;
        this.f980H6 += j12;
        this.f981H7 += j11;
        this.f982H8 += j10;
        this.wOff = 0;
        for (int i12 = 0; i12 < 16; i12++) {
            this.f983W[i12] = 0;
        }
    }
}
