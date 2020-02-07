package org.spongycastle.crypto.digests;

import com.google.common.base.Ascii;
import org.spongycastle.util.Memoable;

public class RIPEMD320Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 40;

    /* renamed from: H0 */
    private int f1018H0;

    /* renamed from: H1 */
    private int f1019H1;

    /* renamed from: H2 */
    private int f1020H2;

    /* renamed from: H3 */
    private int f1021H3;

    /* renamed from: H4 */
    private int f1022H4;

    /* renamed from: H5 */
    private int f1023H5;

    /* renamed from: H6 */
    private int f1024H6;

    /* renamed from: H7 */
    private int f1025H7;

    /* renamed from: H8 */
    private int f1026H8;

    /* renamed from: H9 */
    private int f1027H9;

    /* renamed from: X */
    private int[] f1028X = new int[16];
    private int xOff;

    /* renamed from: RL */
    private int m384RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    /* renamed from: f1 */
    private int m385f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m386f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m387f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m388f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    /* renamed from: f5 */
    private int m389f5(int i, int i2, int i3) {
        return i ^ (i2 | (~i3));
    }

    public String getAlgorithmName() {
        return "RIPEMD320";
    }

    public int getDigestSize() {
        return 40;
    }

    public RIPEMD320Digest() {
        reset();
    }

    public RIPEMD320Digest(RIPEMD320Digest rIPEMD320Digest) {
        super((GeneralDigest) rIPEMD320Digest);
        doCopy(rIPEMD320Digest);
    }

    private void doCopy(RIPEMD320Digest rIPEMD320Digest) {
        super.copyIn(rIPEMD320Digest);
        this.f1018H0 = rIPEMD320Digest.f1018H0;
        this.f1019H1 = rIPEMD320Digest.f1019H1;
        this.f1020H2 = rIPEMD320Digest.f1020H2;
        this.f1021H3 = rIPEMD320Digest.f1021H3;
        this.f1022H4 = rIPEMD320Digest.f1022H4;
        this.f1023H5 = rIPEMD320Digest.f1023H5;
        this.f1024H6 = rIPEMD320Digest.f1024H6;
        this.f1025H7 = rIPEMD320Digest.f1025H7;
        this.f1026H8 = rIPEMD320Digest.f1026H8;
        this.f1027H9 = rIPEMD320Digest.f1027H9;
        int[] iArr = rIPEMD320Digest.f1028X;
        System.arraycopy(iArr, 0, this.f1028X, 0, iArr.length);
        this.xOff = rIPEMD320Digest.xOff;
    }

    /* access modifiers changed from: protected */
    public void processWord(byte[] bArr, int i) {
        int[] iArr = this.f1028X;
        int i2 = this.xOff;
        this.xOff = i2 + 1;
        iArr[i2] = ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        if (this.xOff == 16) {
            processBlock();
        }
    }

    /* access modifiers changed from: protected */
    public void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f1028X;
        iArr[14] = (int) (-1 & j);
        iArr[15] = (int) (j >>> 32);
    }

    private void unpackWord(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    public int doFinal(byte[] bArr, int i) {
        finish();
        unpackWord(this.f1018H0, bArr, i);
        unpackWord(this.f1019H1, bArr, i + 4);
        unpackWord(this.f1020H2, bArr, i + 8);
        unpackWord(this.f1021H3, bArr, i + 12);
        unpackWord(this.f1022H4, bArr, i + 16);
        unpackWord(this.f1023H5, bArr, i + 20);
        unpackWord(this.f1024H6, bArr, i + 24);
        unpackWord(this.f1025H7, bArr, i + 28);
        unpackWord(this.f1026H8, bArr, i + 32);
        unpackWord(this.f1027H9, bArr, i + 36);
        reset();
        return 40;
    }

    public void reset() {
        super.reset();
        this.f1018H0 = 1732584193;
        this.f1019H1 = -271733879;
        this.f1020H2 = -1732584194;
        this.f1021H3 = 271733878;
        this.f1022H4 = -1009589776;
        this.f1023H5 = 1985229328;
        this.f1024H6 = -19088744;
        this.f1025H7 = -1985229329;
        this.f1026H8 = 19088743;
        this.f1027H9 = 1009589775;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f1028X;
            if (i != iArr.length) {
                iArr[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void processBlock() {
        int i = this.f1018H0;
        int i2 = this.f1019H1;
        int i3 = this.f1020H2;
        int i4 = this.f1021H3;
        int i5 = this.f1022H4;
        int i6 = this.f1023H5;
        int i7 = this.f1024H6;
        int i8 = this.f1025H7;
        int i9 = this.f1026H8;
        int i10 = this.f1027H9;
        int RL = m384RL(i + m385f1(i2, i3, i4) + this.f1028X[0], 11) + i5;
        int RL2 = m384RL(i3, 10);
        int RL3 = m384RL(i5 + m385f1(RL, i2, RL2) + this.f1028X[1], 14) + i4;
        int RL4 = m384RL(i2, 10);
        int RL5 = m384RL(i4 + m385f1(RL3, RL, RL4) + this.f1028X[2], 15) + RL2;
        int RL6 = m384RL(RL, 10);
        int RL7 = m384RL(RL2 + m385f1(RL5, RL3, RL6) + this.f1028X[3], 12) + RL4;
        int RL8 = m384RL(RL3, 10);
        int RL9 = m384RL(RL4 + m385f1(RL7, RL5, RL8) + this.f1028X[4], 5) + RL6;
        int RL10 = m384RL(RL5, 10);
        int RL11 = m384RL(RL6 + m385f1(RL9, RL7, RL10) + this.f1028X[5], 8) + RL8;
        int RL12 = m384RL(RL7, 10);
        int RL13 = m384RL(RL8 + m385f1(RL11, RL9, RL12) + this.f1028X[6], 7) + RL10;
        int RL14 = m384RL(RL9, 10);
        int RL15 = m384RL(RL10 + m385f1(RL13, RL11, RL14) + this.f1028X[7], 9) + RL12;
        int RL16 = m384RL(RL11, 10);
        int RL17 = m384RL(RL12 + m385f1(RL15, RL13, RL16) + this.f1028X[8], 11) + RL14;
        int RL18 = m384RL(RL13, 10);
        int RL19 = m384RL(RL14 + m385f1(RL17, RL15, RL18) + this.f1028X[9], 13) + RL16;
        int RL20 = m384RL(RL15, 10);
        int RL21 = m384RL(RL16 + m385f1(RL19, RL17, RL20) + this.f1028X[10], 14) + RL18;
        int RL22 = m384RL(RL17, 10);
        int RL23 = m384RL(RL18 + m385f1(RL21, RL19, RL22) + this.f1028X[11], 15) + RL20;
        int RL24 = m384RL(RL19, 10);
        int RL25 = m384RL(RL20 + m385f1(RL23, RL21, RL24) + this.f1028X[12], 6) + RL22;
        int RL26 = m384RL(RL21, 10);
        int RL27 = m384RL(RL22 + m385f1(RL25, RL23, RL26) + this.f1028X[13], 7) + RL24;
        int RL28 = m384RL(RL23, 10);
        int RL29 = m384RL(RL24 + m385f1(RL27, RL25, RL28) + this.f1028X[14], 9) + RL26;
        int RL30 = m384RL(RL25, 10);
        int RL31 = m384RL(RL26 + m385f1(RL29, RL27, RL30) + this.f1028X[15], 8) + RL28;
        int RL32 = m384RL(RL27, 10);
        int RL33 = m384RL(i6 + m389f5(i7, i8, i9) + this.f1028X[5] + 1352829926, 8) + i10;
        int RL34 = m384RL(i8, 10);
        int RL35 = m384RL(i10 + m389f5(RL33, i7, RL34) + this.f1028X[14] + 1352829926, 9) + i9;
        int RL36 = m384RL(i7, 10);
        int RL37 = m384RL(i9 + m389f5(RL35, RL33, RL36) + this.f1028X[7] + 1352829926, 9) + RL34;
        int RL38 = m384RL(RL33, 10);
        int RL39 = m384RL(RL34 + m389f5(RL37, RL35, RL38) + this.f1028X[0] + 1352829926, 11) + RL36;
        int RL40 = m384RL(RL35, 10);
        int RL41 = m384RL(RL36 + m389f5(RL39, RL37, RL40) + this.f1028X[9] + 1352829926, 13) + RL38;
        int RL42 = m384RL(RL37, 10);
        int RL43 = m384RL(RL38 + m389f5(RL41, RL39, RL42) + this.f1028X[2] + 1352829926, 15) + RL40;
        int RL44 = m384RL(RL39, 10);
        int RL45 = m384RL(RL40 + m389f5(RL43, RL41, RL44) + this.f1028X[11] + 1352829926, 15) + RL42;
        int RL46 = m384RL(RL41, 10);
        int RL47 = m384RL(RL42 + m389f5(RL45, RL43, RL46) + this.f1028X[4] + 1352829926, 5) + RL44;
        int RL48 = m384RL(RL43, 10);
        int RL49 = m384RL(RL44 + m389f5(RL47, RL45, RL48) + this.f1028X[13] + 1352829926, 7) + RL46;
        int RL50 = m384RL(RL45, 10);
        int RL51 = m384RL(RL46 + m389f5(RL49, RL47, RL50) + this.f1028X[6] + 1352829926, 7) + RL48;
        int RL52 = m384RL(RL47, 10);
        int RL53 = m384RL(RL48 + m389f5(RL51, RL49, RL52) + this.f1028X[15] + 1352829926, 8) + RL50;
        int RL54 = m384RL(RL49, 10);
        int RL55 = m384RL(RL50 + m389f5(RL53, RL51, RL54) + this.f1028X[8] + 1352829926, 11) + RL52;
        int RL56 = m384RL(RL51, 10);
        int RL57 = m384RL(RL52 + m389f5(RL55, RL53, RL56) + this.f1028X[1] + 1352829926, 14) + RL54;
        int RL58 = m384RL(RL53, 10);
        int RL59 = m384RL(RL54 + m389f5(RL57, RL55, RL58) + this.f1028X[10] + 1352829926, 14) + RL56;
        int RL60 = m384RL(RL55, 10);
        int RL61 = m384RL(RL56 + m389f5(RL59, RL57, RL60) + this.f1028X[3] + 1352829926, 12) + RL58;
        int RL62 = m384RL(RL57, 10);
        int RL63 = m384RL(RL58 + m389f5(RL61, RL59, RL62) + this.f1028X[12] + 1352829926, 6) + RL60;
        int RL64 = m384RL(RL59, 10);
        int RL65 = m384RL(RL28 + m386f2(RL63, RL29, RL32) + this.f1028X[7] + 1518500249, 7) + RL30;
        int RL66 = m384RL(RL29, 10);
        int RL67 = m384RL(RL30 + m386f2(RL65, RL63, RL66) + this.f1028X[4] + 1518500249, 6) + RL32;
        int RL68 = m384RL(RL63, 10);
        int RL69 = m384RL(RL32 + m386f2(RL67, RL65, RL68) + this.f1028X[13] + 1518500249, 8) + RL66;
        int RL70 = m384RL(RL65, 10);
        int RL71 = m384RL(RL66 + m386f2(RL69, RL67, RL70) + this.f1028X[1] + 1518500249, 13) + RL68;
        int RL72 = m384RL(RL67, 10);
        int RL73 = m384RL(RL68 + m386f2(RL71, RL69, RL72) + this.f1028X[10] + 1518500249, 11) + RL70;
        int RL74 = m384RL(RL69, 10);
        int RL75 = m384RL(RL70 + m386f2(RL73, RL71, RL74) + this.f1028X[6] + 1518500249, 9) + RL72;
        int RL76 = m384RL(RL71, 10);
        int RL77 = m384RL(RL72 + m386f2(RL75, RL73, RL76) + this.f1028X[15] + 1518500249, 7) + RL74;
        int RL78 = m384RL(RL73, 10);
        int RL79 = m384RL(RL74 + m386f2(RL77, RL75, RL78) + this.f1028X[3] + 1518500249, 15) + RL76;
        int RL80 = m384RL(RL75, 10);
        int RL81 = m384RL(RL76 + m386f2(RL79, RL77, RL80) + this.f1028X[12] + 1518500249, 7) + RL78;
        int RL82 = m384RL(RL77, 10);
        int RL83 = m384RL(RL78 + m386f2(RL81, RL79, RL82) + this.f1028X[0] + 1518500249, 12) + RL80;
        int RL84 = m384RL(RL79, 10);
        int RL85 = m384RL(RL80 + m386f2(RL83, RL81, RL84) + this.f1028X[9] + 1518500249, 15) + RL82;
        int RL86 = m384RL(RL81, 10);
        int RL87 = m384RL(RL82 + m386f2(RL85, RL83, RL86) + this.f1028X[5] + 1518500249, 9) + RL84;
        int RL88 = m384RL(RL83, 10);
        int RL89 = m384RL(RL84 + m386f2(RL87, RL85, RL88) + this.f1028X[2] + 1518500249, 11) + RL86;
        int RL90 = m384RL(RL85, 10);
        int RL91 = m384RL(RL86 + m386f2(RL89, RL87, RL90) + this.f1028X[14] + 1518500249, 7) + RL88;
        int RL92 = m384RL(RL87, 10);
        int RL93 = m384RL(RL88 + m386f2(RL91, RL89, RL92) + this.f1028X[11] + 1518500249, 13) + RL90;
        int RL94 = m384RL(RL89, 10);
        int RL95 = m384RL(RL90 + m386f2(RL93, RL91, RL94) + this.f1028X[8] + 1518500249, 12) + RL92;
        int RL96 = m384RL(RL91, 10);
        int RL97 = m384RL(RL60 + m388f4(RL31, RL61, RL64) + this.f1028X[6] + 1548603684, 9) + RL62;
        int RL98 = m384RL(RL61, 10);
        int RL99 = m384RL(RL62 + m388f4(RL97, RL31, RL98) + this.f1028X[11] + 1548603684, 13) + RL64;
        int RL100 = m384RL(RL31, 10);
        int RL101 = m384RL(RL64 + m388f4(RL99, RL97, RL100) + this.f1028X[3] + 1548603684, 15) + RL98;
        int RL102 = m384RL(RL97, 10);
        int RL103 = m384RL(RL98 + m388f4(RL101, RL99, RL102) + this.f1028X[7] + 1548603684, 7) + RL100;
        int RL104 = m384RL(RL99, 10);
        int RL105 = m384RL(RL100 + m388f4(RL103, RL101, RL104) + this.f1028X[0] + 1548603684, 12) + RL102;
        int RL106 = m384RL(RL101, 10);
        int RL107 = m384RL(RL102 + m388f4(RL105, RL103, RL106) + this.f1028X[13] + 1548603684, 8) + RL104;
        int RL108 = m384RL(RL103, 10);
        int RL109 = m384RL(RL104 + m388f4(RL107, RL105, RL108) + this.f1028X[5] + 1548603684, 9) + RL106;
        int RL110 = m384RL(RL105, 10);
        int RL111 = m384RL(RL106 + m388f4(RL109, RL107, RL110) + this.f1028X[10] + 1548603684, 11) + RL108;
        int RL112 = m384RL(RL107, 10);
        int RL113 = m384RL(RL108 + m388f4(RL111, RL109, RL112) + this.f1028X[14] + 1548603684, 7) + RL110;
        int RL114 = m384RL(RL109, 10);
        int RL115 = m384RL(RL110 + m388f4(RL113, RL111, RL114) + this.f1028X[15] + 1548603684, 7) + RL112;
        int RL116 = m384RL(RL111, 10);
        int RL117 = m384RL(RL112 + m388f4(RL115, RL113, RL116) + this.f1028X[8] + 1548603684, 12) + RL114;
        int RL118 = m384RL(RL113, 10);
        int RL119 = m384RL(RL114 + m388f4(RL117, RL115, RL118) + this.f1028X[12] + 1548603684, 7) + RL116;
        int RL120 = m384RL(RL115, 10);
        int RL121 = m384RL(RL116 + m388f4(RL119, RL117, RL120) + this.f1028X[4] + 1548603684, 6) + RL118;
        int RL122 = m384RL(RL117, 10);
        int RL123 = m384RL(RL118 + m388f4(RL121, RL119, RL122) + this.f1028X[9] + 1548603684, 15) + RL120;
        int RL124 = m384RL(RL119, 10);
        int RL125 = m384RL(RL120 + m388f4(RL123, RL121, RL124) + this.f1028X[1] + 1548603684, 13) + RL122;
        int RL126 = m384RL(RL121, 10);
        int RL127 = m384RL(RL122 + m388f4(RL125, RL123, RL126) + this.f1028X[2] + 1548603684, 11) + RL124;
        int RL128 = m384RL(RL123, 10);
        int RL129 = m384RL(RL92 + m387f3(RL95, RL93, RL128) + this.f1028X[3] + 1859775393, 11) + RL94;
        int RL130 = m384RL(RL93, 10);
        int RL131 = m384RL(RL94 + m387f3(RL129, RL95, RL130) + this.f1028X[10] + 1859775393, 13) + RL128;
        int RL132 = m384RL(RL95, 10);
        int RL133 = m384RL(RL128 + m387f3(RL131, RL129, RL132) + this.f1028X[14] + 1859775393, 6) + RL130;
        int RL134 = m384RL(RL129, 10);
        int RL135 = m384RL(RL130 + m387f3(RL133, RL131, RL134) + this.f1028X[4] + 1859775393, 7) + RL132;
        int RL136 = m384RL(RL131, 10);
        int RL137 = m384RL(RL132 + m387f3(RL135, RL133, RL136) + this.f1028X[9] + 1859775393, 14) + RL134;
        int RL138 = m384RL(RL133, 10);
        int RL139 = m384RL(RL134 + m387f3(RL137, RL135, RL138) + this.f1028X[15] + 1859775393, 9) + RL136;
        int RL140 = m384RL(RL135, 10);
        int RL141 = m384RL(RL136 + m387f3(RL139, RL137, RL140) + this.f1028X[8] + 1859775393, 13) + RL138;
        int RL142 = m384RL(RL137, 10);
        int RL143 = m384RL(RL138 + m387f3(RL141, RL139, RL142) + this.f1028X[1] + 1859775393, 15) + RL140;
        int RL144 = m384RL(RL139, 10);
        int RL145 = m384RL(RL140 + m387f3(RL143, RL141, RL144) + this.f1028X[2] + 1859775393, 14) + RL142;
        int RL146 = m384RL(RL141, 10);
        int RL147 = m384RL(RL142 + m387f3(RL145, RL143, RL146) + this.f1028X[7] + 1859775393, 8) + RL144;
        int RL148 = m384RL(RL143, 10);
        int RL149 = m384RL(RL144 + m387f3(RL147, RL145, RL148) + this.f1028X[0] + 1859775393, 13) + RL146;
        int RL150 = m384RL(RL145, 10);
        int RL151 = m384RL(RL146 + m387f3(RL149, RL147, RL150) + this.f1028X[6] + 1859775393, 6) + RL148;
        int RL152 = m384RL(RL147, 10);
        int RL153 = m384RL(RL148 + m387f3(RL151, RL149, RL152) + this.f1028X[13] + 1859775393, 5) + RL150;
        int RL154 = m384RL(RL149, 10);
        int RL155 = m384RL(RL150 + m387f3(RL153, RL151, RL154) + this.f1028X[11] + 1859775393, 12) + RL152;
        int RL156 = m384RL(RL151, 10);
        int RL157 = m384RL(RL152 + m387f3(RL155, RL153, RL156) + this.f1028X[5] + 1859775393, 7) + RL154;
        int RL158 = m384RL(RL153, 10);
        int RL159 = m384RL(RL154 + m387f3(RL157, RL155, RL158) + this.f1028X[12] + 1859775393, 5) + RL156;
        int RL160 = m384RL(RL155, 10);
        int RL161 = m384RL(RL124 + m387f3(RL127, RL125, RL96) + this.f1028X[15] + 1836072691, 9) + RL126;
        int RL162 = m384RL(RL125, 10);
        int RL163 = m384RL(RL126 + m387f3(RL161, RL127, RL162) + this.f1028X[5] + 1836072691, 7) + RL96;
        int RL164 = m384RL(RL127, 10);
        int RL165 = m384RL(RL96 + m387f3(RL163, RL161, RL164) + this.f1028X[1] + 1836072691, 15) + RL162;
        int RL166 = m384RL(RL161, 10);
        int RL167 = m384RL(RL162 + m387f3(RL165, RL163, RL166) + this.f1028X[3] + 1836072691, 11) + RL164;
        int RL168 = m384RL(RL163, 10);
        int RL169 = m384RL(RL164 + m387f3(RL167, RL165, RL168) + this.f1028X[7] + 1836072691, 8) + RL166;
        int RL170 = m384RL(RL165, 10);
        int RL171 = m384RL(RL166 + m387f3(RL169, RL167, RL170) + this.f1028X[14] + 1836072691, 6) + RL168;
        int RL172 = m384RL(RL167, 10);
        int RL173 = m384RL(RL168 + m387f3(RL171, RL169, RL172) + this.f1028X[6] + 1836072691, 6) + RL170;
        int RL174 = m384RL(RL169, 10);
        int RL175 = m384RL(RL170 + m387f3(RL173, RL171, RL174) + this.f1028X[9] + 1836072691, 14) + RL172;
        int RL176 = m384RL(RL171, 10);
        int RL177 = m384RL(RL172 + m387f3(RL175, RL173, RL176) + this.f1028X[11] + 1836072691, 12) + RL174;
        int RL178 = m384RL(RL173, 10);
        int RL179 = m384RL(RL174 + m387f3(RL177, RL175, RL178) + this.f1028X[8] + 1836072691, 13) + RL176;
        int RL180 = m384RL(RL175, 10);
        int RL181 = m384RL(RL176 + m387f3(RL179, RL177, RL180) + this.f1028X[12] + 1836072691, 5) + RL178;
        int RL182 = m384RL(RL177, 10);
        int RL183 = m384RL(RL178 + m387f3(RL181, RL179, RL182) + this.f1028X[2] + 1836072691, 14) + RL180;
        int RL184 = m384RL(RL179, 10);
        int RL185 = m384RL(RL180 + m387f3(RL183, RL181, RL184) + this.f1028X[10] + 1836072691, 13) + RL182;
        int RL186 = m384RL(RL181, 10);
        int RL187 = m384RL(RL182 + m387f3(RL185, RL183, RL186) + this.f1028X[0] + 1836072691, 13) + RL184;
        int RL188 = m384RL(RL183, 10);
        int RL189 = m384RL(RL184 + m387f3(RL187, RL185, RL188) + this.f1028X[4] + 1836072691, 7) + RL186;
        int RL190 = m384RL(RL185, 10);
        int RL191 = m384RL(RL186 + m387f3(RL189, RL187, RL190) + this.f1028X[13] + 1836072691, 5) + RL188;
        int RL192 = m384RL(RL187, 10);
        int RL193 = m384RL(((RL188 + m388f4(RL159, RL157, RL160)) + this.f1028X[1]) - 1894007588, 11) + RL158;
        int RL194 = m384RL(RL157, 10);
        int RL195 = m384RL(((RL158 + m388f4(RL193, RL159, RL194)) + this.f1028X[9]) - 1894007588, 12) + RL160;
        int RL196 = m384RL(RL159, 10);
        int RL197 = m384RL(((RL160 + m388f4(RL195, RL193, RL196)) + this.f1028X[11]) - 1894007588, 14) + RL194;
        int RL198 = m384RL(RL193, 10);
        int RL199 = m384RL(((RL194 + m388f4(RL197, RL195, RL198)) + this.f1028X[10]) - 1894007588, 15) + RL196;
        int RL200 = m384RL(RL195, 10);
        int RL201 = m384RL(((RL196 + m388f4(RL199, RL197, RL200)) + this.f1028X[0]) - 1894007588, 14) + RL198;
        int RL202 = m384RL(RL197, 10);
        int RL203 = m384RL(((RL198 + m388f4(RL201, RL199, RL202)) + this.f1028X[8]) - 1894007588, 15) + RL200;
        int RL204 = m384RL(RL199, 10);
        int RL205 = m384RL(((RL200 + m388f4(RL203, RL201, RL204)) + this.f1028X[12]) - 1894007588, 9) + RL202;
        int RL206 = m384RL(RL201, 10);
        int RL207 = m384RL(((RL202 + m388f4(RL205, RL203, RL206)) + this.f1028X[4]) - 1894007588, 8) + RL204;
        int RL208 = m384RL(RL203, 10);
        int RL209 = m384RL(((RL204 + m388f4(RL207, RL205, RL208)) + this.f1028X[13]) - 1894007588, 9) + RL206;
        int RL210 = m384RL(RL205, 10);
        int RL211 = m384RL(((RL206 + m388f4(RL209, RL207, RL210)) + this.f1028X[3]) - 1894007588, 14) + RL208;
        int RL212 = m384RL(RL207, 10);
        int RL213 = m384RL(((RL208 + m388f4(RL211, RL209, RL212)) + this.f1028X[7]) - 1894007588, 5) + RL210;
        int RL214 = m384RL(RL209, 10);
        int RL215 = m384RL(((RL210 + m388f4(RL213, RL211, RL214)) + this.f1028X[15]) - 1894007588, 6) + RL212;
        int RL216 = m384RL(RL211, 10);
        int RL217 = m384RL(((RL212 + m388f4(RL215, RL213, RL216)) + this.f1028X[14]) - 1894007588, 8) + RL214;
        int RL218 = m384RL(RL213, 10);
        int RL219 = m384RL(((RL214 + m388f4(RL217, RL215, RL218)) + this.f1028X[5]) - 1894007588, 6) + RL216;
        int RL220 = m384RL(RL215, 10);
        int RL221 = m384RL(((RL216 + m388f4(RL219, RL217, RL220)) + this.f1028X[6]) - 1894007588, 5) + RL218;
        int RL222 = m384RL(RL217, 10);
        int RL223 = m384RL(((RL218 + m388f4(RL221, RL219, RL222)) + this.f1028X[2]) - 1894007588, 12) + RL220;
        int RL224 = m384RL(RL219, 10);
        int RL225 = m384RL(RL156 + m386f2(RL191, RL189, RL192) + this.f1028X[8] + 2053994217, 15) + RL190;
        int RL226 = m384RL(RL189, 10);
        int RL227 = m384RL(RL190 + m386f2(RL225, RL191, RL226) + this.f1028X[6] + 2053994217, 5) + RL192;
        int RL228 = m384RL(RL191, 10);
        int RL229 = m384RL(RL192 + m386f2(RL227, RL225, RL228) + this.f1028X[4] + 2053994217, 8) + RL226;
        int RL230 = m384RL(RL225, 10);
        int RL231 = m384RL(RL226 + m386f2(RL229, RL227, RL230) + this.f1028X[1] + 2053994217, 11) + RL228;
        int RL232 = m384RL(RL227, 10);
        int RL233 = m384RL(RL228 + m386f2(RL231, RL229, RL232) + this.f1028X[3] + 2053994217, 14) + RL230;
        int RL234 = m384RL(RL229, 10);
        int RL235 = m384RL(RL230 + m386f2(RL233, RL231, RL234) + this.f1028X[11] + 2053994217, 14) + RL232;
        int RL236 = m384RL(RL231, 10);
        int RL237 = m384RL(RL232 + m386f2(RL235, RL233, RL236) + this.f1028X[15] + 2053994217, 6) + RL234;
        int RL238 = m384RL(RL233, 10);
        int RL239 = m384RL(RL234 + m386f2(RL237, RL235, RL238) + this.f1028X[0] + 2053994217, 14) + RL236;
        int RL240 = m384RL(RL235, 10);
        int RL241 = m384RL(RL236 + m386f2(RL239, RL237, RL240) + this.f1028X[5] + 2053994217, 6) + RL238;
        int RL242 = m384RL(RL237, 10);
        int RL243 = m384RL(RL238 + m386f2(RL241, RL239, RL242) + this.f1028X[12] + 2053994217, 9) + RL240;
        int RL244 = m384RL(RL239, 10);
        int RL245 = m384RL(RL240 + m386f2(RL243, RL241, RL244) + this.f1028X[2] + 2053994217, 12) + RL242;
        int RL246 = m384RL(RL241, 10);
        int RL247 = m384RL(RL242 + m386f2(RL245, RL243, RL246) + this.f1028X[13] + 2053994217, 9) + RL244;
        int RL248 = m384RL(RL243, 10);
        int RL249 = m384RL(RL244 + m386f2(RL247, RL245, RL248) + this.f1028X[9] + 2053994217, 12) + RL246;
        int RL250 = m384RL(RL245, 10);
        int RL251 = m384RL(RL246 + m386f2(RL249, RL247, RL250) + this.f1028X[7] + 2053994217, 5) + RL248;
        int RL252 = m384RL(RL247, 10);
        int RL253 = m384RL(RL248 + m386f2(RL251, RL249, RL252) + this.f1028X[10] + 2053994217, 15) + RL250;
        int RL254 = m384RL(RL249, 10);
        int RL255 = m384RL(RL250 + m386f2(RL253, RL251, RL254) + this.f1028X[14] + 2053994217, 8) + RL252;
        int RL256 = m384RL(RL251, 10);
        int RL257 = m384RL(((RL220 + m389f5(RL223, RL253, RL224)) + this.f1028X[4]) - 1454113458, 9) + RL222;
        int RL258 = m384RL(RL253, 10);
        int RL259 = m384RL(((RL222 + m389f5(RL257, RL223, RL258)) + this.f1028X[0]) - 1454113458, 15) + RL224;
        int RL260 = m384RL(RL223, 10);
        int RL261 = m384RL(((RL224 + m389f5(RL259, RL257, RL260)) + this.f1028X[5]) - 1454113458, 5) + RL258;
        int RL262 = m384RL(RL257, 10);
        int RL263 = m384RL(((RL258 + m389f5(RL261, RL259, RL262)) + this.f1028X[9]) - 1454113458, 11) + RL260;
        int RL264 = m384RL(RL259, 10);
        int RL265 = m384RL(((RL260 + m389f5(RL263, RL261, RL264)) + this.f1028X[7]) - 1454113458, 6) + RL262;
        int RL266 = m384RL(RL261, 10);
        int RL267 = m384RL(((RL262 + m389f5(RL265, RL263, RL266)) + this.f1028X[12]) - 1454113458, 8) + RL264;
        int RL268 = m384RL(RL263, 10);
        int RL269 = m384RL(((RL264 + m389f5(RL267, RL265, RL268)) + this.f1028X[2]) - 1454113458, 13) + RL266;
        int RL270 = m384RL(RL265, 10);
        int RL271 = m384RL(((RL266 + m389f5(RL269, RL267, RL270)) + this.f1028X[10]) - 1454113458, 12) + RL268;
        int RL272 = m384RL(RL267, 10);
        int RL273 = m384RL(((RL268 + m389f5(RL271, RL269, RL272)) + this.f1028X[14]) - 1454113458, 5) + RL270;
        int RL274 = m384RL(RL269, 10);
        int RL275 = m384RL(((RL270 + m389f5(RL273, RL271, RL274)) + this.f1028X[1]) - 1454113458, 12) + RL272;
        int RL276 = m384RL(RL271, 10);
        int RL277 = m384RL(((RL272 + m389f5(RL275, RL273, RL276)) + this.f1028X[3]) - 1454113458, 13) + RL274;
        int RL278 = m384RL(RL273, 10);
        int RL279 = m384RL(((RL274 + m389f5(RL277, RL275, RL278)) + this.f1028X[8]) - 1454113458, 14) + RL276;
        int RL280 = m384RL(RL275, 10);
        int RL281 = m384RL(((RL276 + m389f5(RL279, RL277, RL280)) + this.f1028X[11]) - 1454113458, 11) + RL278;
        int RL282 = m384RL(RL277, 10);
        int RL283 = m384RL(((RL278 + m389f5(RL281, RL279, RL282)) + this.f1028X[6]) - 1454113458, 8) + RL280;
        int RL284 = m384RL(RL279, 10);
        int RL285 = m384RL(((RL280 + m389f5(RL283, RL281, RL284)) + this.f1028X[15]) - 1454113458, 5) + RL282;
        int RL286 = m384RL(RL281, 10);
        int RL287 = m384RL(((RL282 + m389f5(RL285, RL283, RL286)) + this.f1028X[13]) - 1454113458, 6) + RL284;
        int RL288 = m384RL(RL283, 10);
        int RL289 = m384RL(RL252 + m385f1(RL255, RL221, RL256) + this.f1028X[12], 8) + RL254;
        int RL290 = m384RL(RL221, 10);
        int RL291 = m384RL(RL254 + m385f1(RL289, RL255, RL290) + this.f1028X[15], 5) + RL256;
        int RL292 = m384RL(RL255, 10);
        int RL293 = m384RL(RL256 + m385f1(RL291, RL289, RL292) + this.f1028X[10], 12) + RL290;
        int RL294 = m384RL(RL289, 10);
        int RL295 = m384RL(RL290 + m385f1(RL293, RL291, RL294) + this.f1028X[4], 9) + RL292;
        int RL296 = m384RL(RL291, 10);
        int RL297 = m384RL(RL292 + m385f1(RL295, RL293, RL296) + this.f1028X[1], 12) + RL294;
        int RL298 = m384RL(RL293, 10);
        int RL299 = m384RL(RL294 + m385f1(RL297, RL295, RL298) + this.f1028X[5], 5) + RL296;
        int RL300 = m384RL(RL295, 10);
        int RL301 = m384RL(RL296 + m385f1(RL299, RL297, RL300) + this.f1028X[8], 14) + RL298;
        int RL302 = m384RL(RL297, 10);
        int RL303 = m384RL(RL298 + m385f1(RL301, RL299, RL302) + this.f1028X[7], 6) + RL300;
        int RL304 = m384RL(RL299, 10);
        int RL305 = m384RL(RL300 + m385f1(RL303, RL301, RL304) + this.f1028X[6], 8) + RL302;
        int RL306 = m384RL(RL301, 10);
        int RL307 = m384RL(RL302 + m385f1(RL305, RL303, RL306) + this.f1028X[2], 13) + RL304;
        int RL308 = m384RL(RL303, 10);
        int RL309 = m384RL(RL304 + m385f1(RL307, RL305, RL308) + this.f1028X[13], 6) + RL306;
        int RL310 = m384RL(RL305, 10);
        int RL311 = m384RL(RL306 + m385f1(RL309, RL307, RL310) + this.f1028X[14], 5) + RL308;
        int RL312 = m384RL(RL307, 10);
        int RL313 = m384RL(RL308 + m385f1(RL311, RL309, RL312) + this.f1028X[0], 15) + RL310;
        int RL314 = m384RL(RL309, 10);
        int RL315 = m384RL(RL310 + m385f1(RL313, RL311, RL314) + this.f1028X[3], 13) + RL312;
        int RL316 = m384RL(RL311, 10);
        int RL317 = m384RL(RL312 + m385f1(RL315, RL313, RL316) + this.f1028X[9], 11) + RL314;
        int RL318 = m384RL(RL313, 10);
        int RL319 = m384RL(RL314 + m385f1(RL317, RL315, RL318) + this.f1028X[11], 11) + RL316;
        int RL320 = m384RL(RL315, 10);
        this.f1018H0 += RL284;
        this.f1019H1 += RL287;
        this.f1020H2 += RL285;
        this.f1021H3 += RL288;
        this.f1022H4 += RL318;
        this.f1023H5 += RL316;
        this.f1024H6 += RL319;
        this.f1025H7 += RL317;
        this.f1026H8 += RL320;
        this.f1027H9 += RL286;
        int i11 = 0;
        this.xOff = 0;
        while (true) {
            int[] iArr = this.f1028X;
            if (i11 != iArr.length) {
                iArr[i11] = 0;
                i11++;
            } else {
                return;
            }
        }
    }

    public Memoable copy() {
        return new RIPEMD320Digest(this);
    }

    public void reset(Memoable memoable) {
        doCopy((RIPEMD320Digest) memoable);
    }
}
