package org.spongycastle.crypto.macs;

import org.bitcoinj.core.TransactionInput;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.generators.Poly1305KeyGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Pack;

public class Poly1305 implements Mac {
    private static final int BLOCK_SIZE = 16;
    private final BlockCipher cipher;
    private final byte[] currentBlock;
    private int currentBlockOffset;

    /* renamed from: h0 */
    private int f1202h0;

    /* renamed from: h1 */
    private int f1203h1;

    /* renamed from: h2 */
    private int f1204h2;

    /* renamed from: h3 */
    private int f1205h3;

    /* renamed from: h4 */
    private int f1206h4;

    /* renamed from: k0 */
    private int f1207k0;

    /* renamed from: k1 */
    private int f1208k1;

    /* renamed from: k2 */
    private int f1209k2;

    /* renamed from: k3 */
    private int f1210k3;

    /* renamed from: r0 */
    private int f1211r0;

    /* renamed from: r1 */
    private int f1212r1;

    /* renamed from: r2 */
    private int f1213r2;

    /* renamed from: r3 */
    private int f1214r3;

    /* renamed from: r4 */
    private int f1215r4;

    /* renamed from: s1 */
    private int f1216s1;

    /* renamed from: s2 */
    private int f1217s2;

    /* renamed from: s3 */
    private int f1218s3;

    /* renamed from: s4 */
    private int f1219s4;
    private final byte[] singleByte;

    private static final long mul32x32_64(int i, int i2) {
        return ((long) i) * ((long) i2);
    }

    public int getMacSize() {
        return 16;
    }

    public Poly1305() {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        this.cipher = null;
    }

    public Poly1305(BlockCipher blockCipher) {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        if (blockCipher.getBlockSize() == 16) {
            this.cipher = blockCipher;
            return;
        }
        throw new IllegalArgumentException("Poly1305 requires a 128 bit block cipher.");
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] bArr;
        if (this.cipher == null) {
            bArr = null;
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            throw new IllegalArgumentException("Poly1305 requires an IV when used with a block cipher.");
        }
        if (cipherParameters instanceof KeyParameter) {
            setKey(((KeyParameter) cipherParameters).getKey(), bArr);
            reset();
            return;
        }
        throw new IllegalArgumentException("Poly1305 requires a key.");
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        if (this.cipher == null || (bArr2 != null && bArr2.length == 16)) {
            Poly1305KeyGenerator.checkKey(bArr);
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 16);
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 20);
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 24);
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 28);
            this.f1211r0 = 67108863 & littleEndianToInt;
            this.f1212r1 = ((littleEndianToInt >>> 26) | (littleEndianToInt2 << 6)) & 67108611;
            this.f1213r2 = ((littleEndianToInt2 >>> 20) | (littleEndianToInt3 << 12)) & 67092735;
            this.f1214r3 = ((littleEndianToInt3 >>> 14) | (littleEndianToInt4 << 18)) & 66076671;
            this.f1215r4 = (littleEndianToInt4 >>> 8) & 1048575;
            this.f1216s1 = this.f1212r1 * 5;
            this.f1217s2 = this.f1213r2 * 5;
            this.f1218s3 = this.f1214r3 * 5;
            this.f1219s4 = this.f1215r4 * 5;
            BlockCipher blockCipher = this.cipher;
            if (blockCipher != null) {
                byte[] bArr3 = new byte[16];
                blockCipher.init(true, new KeyParameter(bArr, 0, 16));
                this.cipher.processBlock(bArr2, 0, bArr3, 0);
                bArr = bArr3;
            }
            this.f1207k0 = Pack.littleEndianToInt(bArr, 0);
            this.f1208k1 = Pack.littleEndianToInt(bArr, 4);
            this.f1209k2 = Pack.littleEndianToInt(bArr, 8);
            this.f1210k3 = Pack.littleEndianToInt(bArr, 12);
            return;
        }
        throw new IllegalArgumentException("Poly1305 requires a 128 bit IV.");
    }

    public String getAlgorithmName() {
        if (this.cipher == null) {
            return "Poly1305";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Poly1305-");
        sb.append(this.cipher.getAlgorithmName());
        return sb.toString();
    }

    public void update(byte b) throws IllegalStateException {
        byte[] bArr = this.singleByte;
        bArr[0] = b;
        update(bArr, 0, 1);
    }

    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        int i3 = 0;
        while (i2 > i3) {
            if (this.currentBlockOffset == 16) {
                processBlock();
                this.currentBlockOffset = 0;
            }
            int min = Math.min(i2 - i3, 16 - this.currentBlockOffset);
            System.arraycopy(bArr, i3 + i, this.currentBlock, this.currentBlockOffset, min);
            i3 += min;
            this.currentBlockOffset += min;
        }
    }

    private void processBlock() {
        int i = this.currentBlockOffset;
        if (i < 16) {
            this.currentBlock[i] = 1;
            for (int i2 = i + 1; i2 < 16; i2++) {
                this.currentBlock[i2] = 0;
            }
        }
        long littleEndianToInt = ((long) Pack.littleEndianToInt(this.currentBlock, 0)) & TransactionInput.NO_SEQUENCE;
        long littleEndianToInt2 = ((long) Pack.littleEndianToInt(this.currentBlock, 4)) & TransactionInput.NO_SEQUENCE;
        long littleEndianToInt3 = ((long) Pack.littleEndianToInt(this.currentBlock, 8)) & TransactionInput.NO_SEQUENCE;
        long littleEndianToInt4 = TransactionInput.NO_SEQUENCE & ((long) Pack.littleEndianToInt(this.currentBlock, 12));
        this.f1202h0 = (int) (((long) this.f1202h0) + (littleEndianToInt & 67108863));
        this.f1203h1 = (int) (((long) this.f1203h1) + ((((littleEndianToInt2 << 32) | littleEndianToInt) >>> 26) & 67108863));
        this.f1204h2 = (int) (((long) this.f1204h2) + (((littleEndianToInt2 | (littleEndianToInt3 << 32)) >>> 20) & 67108863));
        this.f1205h3 = (int) (((long) this.f1205h3) + ((((littleEndianToInt4 << 32) | littleEndianToInt3) >>> 14) & 67108863));
        this.f1206h4 = (int) (((long) this.f1206h4) + (littleEndianToInt4 >>> 8));
        if (this.currentBlockOffset == 16) {
            this.f1206h4 += 16777216;
        }
        long mul32x32_64 = mul32x32_64(this.f1202h0, this.f1211r0) + mul32x32_64(this.f1203h1, this.f1219s4) + mul32x32_64(this.f1204h2, this.f1218s3) + mul32x32_64(this.f1205h3, this.f1217s2) + mul32x32_64(this.f1206h4, this.f1216s1);
        long mul32x32_642 = mul32x32_64(this.f1202h0, this.f1212r1) + mul32x32_64(this.f1203h1, this.f1211r0) + mul32x32_64(this.f1204h2, this.f1219s4) + mul32x32_64(this.f1205h3, this.f1218s3) + mul32x32_64(this.f1206h4, this.f1217s2);
        long mul32x32_643 = mul32x32_64(this.f1202h0, this.f1213r2) + mul32x32_64(this.f1203h1, this.f1212r1) + mul32x32_64(this.f1204h2, this.f1211r0) + mul32x32_64(this.f1205h3, this.f1219s4) + mul32x32_64(this.f1206h4, this.f1218s3);
        long mul32x32_644 = mul32x32_64(this.f1202h0, this.f1214r3) + mul32x32_64(this.f1203h1, this.f1213r2) + mul32x32_64(this.f1204h2, this.f1212r1) + mul32x32_64(this.f1205h3, this.f1211r0) + mul32x32_64(this.f1206h4, this.f1219s4);
        long mul32x32_645 = mul32x32_64(this.f1202h0, this.f1215r4) + mul32x32_64(this.f1203h1, this.f1214r3) + mul32x32_64(this.f1204h2, this.f1213r2) + mul32x32_64(this.f1205h3, this.f1212r1) + mul32x32_64(this.f1206h4, this.f1211r0);
        this.f1202h0 = ((int) mul32x32_64) & 67108863;
        long j = mul32x32_642 + (mul32x32_64 >>> 26);
        this.f1203h1 = ((int) j) & 67108863;
        long j2 = mul32x32_643 + ((j >>> 26) & -1);
        this.f1204h2 = ((int) j2) & 67108863;
        long j3 = mul32x32_644 + ((j2 >>> 26) & -1);
        this.f1205h3 = ((int) j3) & 67108863;
        long j4 = mul32x32_645 + (j3 >>> 26);
        this.f1206h4 = ((int) j4) & 67108863;
        this.f1202h0 = (int) (((long) this.f1202h0) + ((j4 >>> 26) * 5));
    }

    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        if (i + 16 <= bArr.length) {
            if (this.currentBlockOffset > 0) {
                processBlock();
            }
            int i2 = this.f1202h0;
            int i3 = i2 >>> 26;
            this.f1202h0 = i2 & 67108863;
            this.f1203h1 += i3;
            int i4 = this.f1203h1;
            int i5 = i4 >>> 26;
            this.f1203h1 = i4 & 67108863;
            this.f1204h2 += i5;
            int i6 = this.f1204h2;
            int i7 = i6 >>> 26;
            this.f1204h2 = i6 & 67108863;
            this.f1205h3 += i7;
            int i8 = this.f1205h3;
            int i9 = i8 >>> 26;
            this.f1205h3 = i8 & 67108863;
            this.f1206h4 += i9;
            int i10 = this.f1206h4;
            int i11 = i10 >>> 26;
            this.f1206h4 = i10 & 67108863;
            this.f1202h0 += i11 * 5;
            int i12 = this.f1202h0;
            int i13 = i12 + 5;
            int i14 = i13 >>> 26;
            int i15 = i13 & 67108863;
            int i16 = this.f1203h1;
            int i17 = i14 + i16;
            int i18 = i17 >>> 26;
            int i19 = i17 & 67108863;
            int i20 = this.f1204h2;
            int i21 = i18 + i20;
            int i22 = i21 >>> 26;
            int i23 = i21 & 67108863;
            int i24 = this.f1205h3;
            int i25 = i22 + i24;
            int i26 = i25 >>> 26;
            int i27 = 67108863 & i25;
            int i28 = this.f1206h4;
            int i29 = (i26 + i28) - 67108864;
            int i30 = (i29 >>> 31) - 1;
            int i31 = ~i30;
            this.f1202h0 = (i12 & i31) | (i15 & i30);
            this.f1203h1 = (i16 & i31) | (i19 & i30);
            this.f1204h2 = (i20 & i31) | (i23 & i30);
            this.f1205h3 = (i24 & i31) | (i27 & i30);
            this.f1206h4 = (i28 & i31) | (i29 & i30);
            int i32 = this.f1202h0;
            int i33 = this.f1203h1;
            long j = (((long) (i32 | (i33 << 26))) & TransactionInput.NO_SEQUENCE) + (((long) this.f1207k0) & TransactionInput.NO_SEQUENCE);
            int i34 = i33 >>> 6;
            int i35 = this.f1204h2;
            long j2 = (((long) (i34 | (i35 << 20))) & TransactionInput.NO_SEQUENCE) + (((long) this.f1208k1) & TransactionInput.NO_SEQUENCE);
            int i36 = i35 >>> 12;
            int i37 = this.f1205h3;
            long j3 = (((long) (i36 | (i37 << 14))) & TransactionInput.NO_SEQUENCE) + (((long) this.f1209k2) & TransactionInput.NO_SEQUENCE);
            long j4 = (((long) ((i37 >>> 18) | (this.f1206h4 << 8))) & TransactionInput.NO_SEQUENCE) + (TransactionInput.NO_SEQUENCE & ((long) this.f1210k3));
            Pack.intToLittleEndian((int) j, bArr, i);
            long j5 = j2 + (j >>> 32);
            Pack.intToLittleEndian((int) j5, bArr, i + 4);
            long j6 = j3 + (j5 >>> 32);
            Pack.intToLittleEndian((int) j6, bArr, i + 8);
            Pack.intToLittleEndian((int) (j4 + (j6 >>> 32)), bArr, i + 12);
            reset();
            return 16;
        }
        throw new DataLengthException("Output buffer is too short.");
    }

    public void reset() {
        this.currentBlockOffset = 0;
        this.f1206h4 = 0;
        this.f1205h3 = 0;
        this.f1204h2 = 0;
        this.f1203h1 = 0;
        this.f1202h0 = 0;
    }
}
