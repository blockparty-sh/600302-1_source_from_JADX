package org.spongycastle.crypto.digests;

import com.google.common.base.Ascii;
import org.spongycastle.util.Memoable;

public class RIPEMD160Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 20;

    /* renamed from: H0 */
    private int f1003H0;

    /* renamed from: H1 */
    private int f1004H1;

    /* renamed from: H2 */
    private int f1005H2;

    /* renamed from: H3 */
    private int f1006H3;

    /* renamed from: H4 */
    private int f1007H4;

    /* renamed from: X */
    private int[] f1008X = new int[16];
    private int xOff;

    /* renamed from: RL */
    private int m369RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    /* renamed from: f1 */
    private int m370f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m371f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m372f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m373f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    /* renamed from: f5 */
    private int m374f5(int i, int i2, int i3) {
        return i ^ (i2 | (~i3));
    }

    public String getAlgorithmName() {
        return "RIPEMD160";
    }

    public int getDigestSize() {
        return 20;
    }

    public RIPEMD160Digest() {
        reset();
    }

    public RIPEMD160Digest(RIPEMD160Digest rIPEMD160Digest) {
        super((GeneralDigest) rIPEMD160Digest);
        copyIn(rIPEMD160Digest);
    }

    private void copyIn(RIPEMD160Digest rIPEMD160Digest) {
        super.copyIn(rIPEMD160Digest);
        this.f1003H0 = rIPEMD160Digest.f1003H0;
        this.f1004H1 = rIPEMD160Digest.f1004H1;
        this.f1005H2 = rIPEMD160Digest.f1005H2;
        this.f1006H3 = rIPEMD160Digest.f1006H3;
        this.f1007H4 = rIPEMD160Digest.f1007H4;
        int[] iArr = rIPEMD160Digest.f1008X;
        System.arraycopy(iArr, 0, this.f1008X, 0, iArr.length);
        this.xOff = rIPEMD160Digest.xOff;
    }

    /* access modifiers changed from: protected */
    public void processWord(byte[] bArr, int i) {
        int[] iArr = this.f1008X;
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
        int[] iArr = this.f1008X;
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
        unpackWord(this.f1003H0, bArr, i);
        unpackWord(this.f1004H1, bArr, i + 4);
        unpackWord(this.f1005H2, bArr, i + 8);
        unpackWord(this.f1006H3, bArr, i + 12);
        unpackWord(this.f1007H4, bArr, i + 16);
        reset();
        return 20;
    }

    public void reset() {
        super.reset();
        this.f1003H0 = 1732584193;
        this.f1004H1 = -271733879;
        this.f1005H2 = -1732584194;
        this.f1006H3 = 271733878;
        this.f1007H4 = -1009589776;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f1008X;
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
        int i = this.f1003H0;
        int i2 = this.f1004H1;
        int i3 = this.f1005H2;
        int i4 = this.f1006H3;
        int i5 = this.f1007H4;
        int RL = m369RL(m370f1(i2, i3, i4) + i + this.f1008X[0], 11) + i5;
        int RL2 = m369RL(i3, 10);
        int RL3 = m369RL(m370f1(RL, i2, RL2) + i5 + this.f1008X[1], 14) + i4;
        int RL4 = m369RL(i2, 10);
        int RL5 = m369RL(m370f1(RL3, RL, RL4) + i4 + this.f1008X[2], 15) + RL2;
        int RL6 = m369RL(RL, 10);
        int RL7 = m369RL(RL2 + m370f1(RL5, RL3, RL6) + this.f1008X[3], 12) + RL4;
        int RL8 = m369RL(RL3, 10);
        int RL9 = m369RL(RL4 + m370f1(RL7, RL5, RL8) + this.f1008X[4], 5) + RL6;
        int RL10 = m369RL(RL5, 10);
        int RL11 = m369RL(RL6 + m370f1(RL9, RL7, RL10) + this.f1008X[5], 8) + RL8;
        int RL12 = m369RL(RL7, 10);
        int RL13 = m369RL(RL8 + m370f1(RL11, RL9, RL12) + this.f1008X[6], 7) + RL10;
        int RL14 = m369RL(RL9, 10);
        int RL15 = m369RL(RL10 + m370f1(RL13, RL11, RL14) + this.f1008X[7], 9) + RL12;
        int RL16 = m369RL(RL11, 10);
        int RL17 = m369RL(RL12 + m370f1(RL15, RL13, RL16) + this.f1008X[8], 11) + RL14;
        int RL18 = m369RL(RL13, 10);
        int RL19 = m369RL(RL14 + m370f1(RL17, RL15, RL18) + this.f1008X[9], 13) + RL16;
        int RL20 = m369RL(RL15, 10);
        int RL21 = m369RL(RL16 + m370f1(RL19, RL17, RL20) + this.f1008X[10], 14) + RL18;
        int RL22 = m369RL(RL17, 10);
        int RL23 = m369RL(RL18 + m370f1(RL21, RL19, RL22) + this.f1008X[11], 15) + RL20;
        int RL24 = m369RL(RL19, 10);
        int RL25 = m369RL(RL20 + m370f1(RL23, RL21, RL24) + this.f1008X[12], 6) + RL22;
        int RL26 = m369RL(RL21, 10);
        int RL27 = m369RL(RL22 + m370f1(RL25, RL23, RL26) + this.f1008X[13], 7) + RL24;
        int RL28 = m369RL(RL23, 10);
        int RL29 = m369RL(RL24 + m370f1(RL27, RL25, RL28) + this.f1008X[14], 9) + RL26;
        int RL30 = m369RL(RL25, 10);
        int RL31 = m369RL(RL26 + m370f1(RL29, RL27, RL30) + this.f1008X[15], 8) + RL28;
        int RL32 = m369RL(RL27, 10);
        int RL33 = m369RL(i + m374f5(i2, i3, i4) + this.f1008X[5] + 1352829926, 8) + i5;
        int RL34 = m369RL(i3, 10);
        int RL35 = m369RL(i5 + m374f5(RL33, i2, RL34) + this.f1008X[14] + 1352829926, 9) + i4;
        int RL36 = m369RL(i2, 10);
        int RL37 = m369RL(i4 + m374f5(RL35, RL33, RL36) + this.f1008X[7] + 1352829926, 9) + RL34;
        int RL38 = m369RL(RL33, 10);
        int RL39 = m369RL(RL34 + m374f5(RL37, RL35, RL38) + this.f1008X[0] + 1352829926, 11) + RL36;
        int RL40 = m369RL(RL35, 10);
        int RL41 = m369RL(RL36 + m374f5(RL39, RL37, RL40) + this.f1008X[9] + 1352829926, 13) + RL38;
        int RL42 = m369RL(RL37, 10);
        int RL43 = m369RL(RL38 + m374f5(RL41, RL39, RL42) + this.f1008X[2] + 1352829926, 15) + RL40;
        int RL44 = m369RL(RL39, 10);
        int RL45 = m369RL(RL40 + m374f5(RL43, RL41, RL44) + this.f1008X[11] + 1352829926, 15) + RL42;
        int RL46 = m369RL(RL41, 10);
        int RL47 = m369RL(RL42 + m374f5(RL45, RL43, RL46) + this.f1008X[4] + 1352829926, 5) + RL44;
        int RL48 = m369RL(RL43, 10);
        int RL49 = m369RL(RL44 + m374f5(RL47, RL45, RL48) + this.f1008X[13] + 1352829926, 7) + RL46;
        int RL50 = m369RL(RL45, 10);
        int RL51 = m369RL(RL46 + m374f5(RL49, RL47, RL50) + this.f1008X[6] + 1352829926, 7) + RL48;
        int RL52 = m369RL(RL47, 10);
        int RL53 = m369RL(RL48 + m374f5(RL51, RL49, RL52) + this.f1008X[15] + 1352829926, 8) + RL50;
        int RL54 = m369RL(RL49, 10);
        int RL55 = m369RL(RL50 + m374f5(RL53, RL51, RL54) + this.f1008X[8] + 1352829926, 11) + RL52;
        int RL56 = m369RL(RL51, 10);
        int RL57 = m369RL(RL52 + m374f5(RL55, RL53, RL56) + this.f1008X[1] + 1352829926, 14) + RL54;
        int RL58 = m369RL(RL53, 10);
        int RL59 = m369RL(RL54 + m374f5(RL57, RL55, RL58) + this.f1008X[10] + 1352829926, 14) + RL56;
        int RL60 = m369RL(RL55, 10);
        int RL61 = m369RL(RL56 + m374f5(RL59, RL57, RL60) + this.f1008X[3] + 1352829926, 12) + RL58;
        int RL62 = m369RL(RL57, 10);
        int RL63 = m369RL(RL58 + m374f5(RL61, RL59, RL62) + this.f1008X[12] + 1352829926, 6) + RL60;
        int RL64 = m369RL(RL59, 10);
        int RL65 = m369RL(RL28 + m371f2(RL31, RL29, RL32) + this.f1008X[7] + 1518500249, 7) + RL30;
        int RL66 = m369RL(RL29, 10);
        int RL67 = m369RL(RL30 + m371f2(RL65, RL31, RL66) + this.f1008X[4] + 1518500249, 6) + RL32;
        int RL68 = m369RL(RL31, 10);
        int RL69 = m369RL(RL32 + m371f2(RL67, RL65, RL68) + this.f1008X[13] + 1518500249, 8) + RL66;
        int RL70 = m369RL(RL65, 10);
        int RL71 = m369RL(RL66 + m371f2(RL69, RL67, RL70) + this.f1008X[1] + 1518500249, 13) + RL68;
        int RL72 = m369RL(RL67, 10);
        int RL73 = m369RL(RL68 + m371f2(RL71, RL69, RL72) + this.f1008X[10] + 1518500249, 11) + RL70;
        int RL74 = m369RL(RL69, 10);
        int RL75 = m369RL(RL70 + m371f2(RL73, RL71, RL74) + this.f1008X[6] + 1518500249, 9) + RL72;
        int RL76 = m369RL(RL71, 10);
        int RL77 = m369RL(RL72 + m371f2(RL75, RL73, RL76) + this.f1008X[15] + 1518500249, 7) + RL74;
        int RL78 = m369RL(RL73, 10);
        int RL79 = m369RL(RL74 + m371f2(RL77, RL75, RL78) + this.f1008X[3] + 1518500249, 15) + RL76;
        int RL80 = m369RL(RL75, 10);
        int RL81 = m369RL(RL76 + m371f2(RL79, RL77, RL80) + this.f1008X[12] + 1518500249, 7) + RL78;
        int RL82 = m369RL(RL77, 10);
        int RL83 = m369RL(RL78 + m371f2(RL81, RL79, RL82) + this.f1008X[0] + 1518500249, 12) + RL80;
        int RL84 = m369RL(RL79, 10);
        int RL85 = m369RL(RL80 + m371f2(RL83, RL81, RL84) + this.f1008X[9] + 1518500249, 15) + RL82;
        int RL86 = m369RL(RL81, 10);
        int RL87 = m369RL(RL82 + m371f2(RL85, RL83, RL86) + this.f1008X[5] + 1518500249, 9) + RL84;
        int RL88 = m369RL(RL83, 10);
        int RL89 = m369RL(RL84 + m371f2(RL87, RL85, RL88) + this.f1008X[2] + 1518500249, 11) + RL86;
        int RL90 = m369RL(RL85, 10);
        int RL91 = m369RL(RL86 + m371f2(RL89, RL87, RL90) + this.f1008X[14] + 1518500249, 7) + RL88;
        int RL92 = m369RL(RL87, 10);
        int RL93 = m369RL(RL88 + m371f2(RL91, RL89, RL92) + this.f1008X[11] + 1518500249, 13) + RL90;
        int RL94 = m369RL(RL89, 10);
        int RL95 = m369RL(RL90 + m371f2(RL93, RL91, RL94) + this.f1008X[8] + 1518500249, 12) + RL92;
        int RL96 = m369RL(RL91, 10);
        int RL97 = m369RL(RL60 + m373f4(RL63, RL61, RL64) + this.f1008X[6] + 1548603684, 9) + RL62;
        int RL98 = m369RL(RL61, 10);
        int RL99 = m369RL(RL62 + m373f4(RL97, RL63, RL98) + this.f1008X[11] + 1548603684, 13) + RL64;
        int RL100 = m369RL(RL63, 10);
        int RL101 = m369RL(RL64 + m373f4(RL99, RL97, RL100) + this.f1008X[3] + 1548603684, 15) + RL98;
        int RL102 = m369RL(RL97, 10);
        int RL103 = m369RL(RL98 + m373f4(RL101, RL99, RL102) + this.f1008X[7] + 1548603684, 7) + RL100;
        int RL104 = m369RL(RL99, 10);
        int RL105 = m369RL(RL100 + m373f4(RL103, RL101, RL104) + this.f1008X[0] + 1548603684, 12) + RL102;
        int RL106 = m369RL(RL101, 10);
        int RL107 = m369RL(RL102 + m373f4(RL105, RL103, RL106) + this.f1008X[13] + 1548603684, 8) + RL104;
        int RL108 = m369RL(RL103, 10);
        int RL109 = m369RL(RL104 + m373f4(RL107, RL105, RL108) + this.f1008X[5] + 1548603684, 9) + RL106;
        int RL110 = m369RL(RL105, 10);
        int RL111 = m369RL(RL106 + m373f4(RL109, RL107, RL110) + this.f1008X[10] + 1548603684, 11) + RL108;
        int RL112 = m369RL(RL107, 10);
        int RL113 = m369RL(RL108 + m373f4(RL111, RL109, RL112) + this.f1008X[14] + 1548603684, 7) + RL110;
        int RL114 = m369RL(RL109, 10);
        int RL115 = m369RL(RL110 + m373f4(RL113, RL111, RL114) + this.f1008X[15] + 1548603684, 7) + RL112;
        int RL116 = m369RL(RL111, 10);
        int RL117 = m369RL(RL112 + m373f4(RL115, RL113, RL116) + this.f1008X[8] + 1548603684, 12) + RL114;
        int RL118 = m369RL(RL113, 10);
        int RL119 = m369RL(RL114 + m373f4(RL117, RL115, RL118) + this.f1008X[12] + 1548603684, 7) + RL116;
        int RL120 = m369RL(RL115, 10);
        int RL121 = m369RL(RL116 + m373f4(RL119, RL117, RL120) + this.f1008X[4] + 1548603684, 6) + RL118;
        int RL122 = m369RL(RL117, 10);
        int RL123 = m369RL(RL118 + m373f4(RL121, RL119, RL122) + this.f1008X[9] + 1548603684, 15) + RL120;
        int RL124 = m369RL(RL119, 10);
        int RL125 = m369RL(RL120 + m373f4(RL123, RL121, RL124) + this.f1008X[1] + 1548603684, 13) + RL122;
        int RL126 = m369RL(RL121, 10);
        int RL127 = m369RL(RL122 + m373f4(RL125, RL123, RL126) + this.f1008X[2] + 1548603684, 11) + RL124;
        int RL128 = m369RL(RL123, 10);
        int RL129 = m369RL(RL92 + m372f3(RL95, RL93, RL96) + this.f1008X[3] + 1859775393, 11) + RL94;
        int RL130 = m369RL(RL93, 10);
        int RL131 = m369RL(RL94 + m372f3(RL129, RL95, RL130) + this.f1008X[10] + 1859775393, 13) + RL96;
        int RL132 = m369RL(RL95, 10);
        int RL133 = m369RL(RL96 + m372f3(RL131, RL129, RL132) + this.f1008X[14] + 1859775393, 6) + RL130;
        int RL134 = m369RL(RL129, 10);
        int RL135 = m369RL(RL130 + m372f3(RL133, RL131, RL134) + this.f1008X[4] + 1859775393, 7) + RL132;
        int RL136 = m369RL(RL131, 10);
        int RL137 = m369RL(RL132 + m372f3(RL135, RL133, RL136) + this.f1008X[9] + 1859775393, 14) + RL134;
        int RL138 = m369RL(RL133, 10);
        int RL139 = m369RL(RL134 + m372f3(RL137, RL135, RL138) + this.f1008X[15] + 1859775393, 9) + RL136;
        int RL140 = m369RL(RL135, 10);
        int RL141 = m369RL(RL136 + m372f3(RL139, RL137, RL140) + this.f1008X[8] + 1859775393, 13) + RL138;
        int RL142 = m369RL(RL137, 10);
        int RL143 = m369RL(RL138 + m372f3(RL141, RL139, RL142) + this.f1008X[1] + 1859775393, 15) + RL140;
        int RL144 = m369RL(RL139, 10);
        int RL145 = m369RL(RL140 + m372f3(RL143, RL141, RL144) + this.f1008X[2] + 1859775393, 14) + RL142;
        int RL146 = m369RL(RL141, 10);
        int RL147 = m369RL(RL142 + m372f3(RL145, RL143, RL146) + this.f1008X[7] + 1859775393, 8) + RL144;
        int RL148 = m369RL(RL143, 10);
        int RL149 = m369RL(RL144 + m372f3(RL147, RL145, RL148) + this.f1008X[0] + 1859775393, 13) + RL146;
        int RL150 = m369RL(RL145, 10);
        int RL151 = m369RL(RL146 + m372f3(RL149, RL147, RL150) + this.f1008X[6] + 1859775393, 6) + RL148;
        int RL152 = m369RL(RL147, 10);
        int RL153 = m369RL(RL148 + m372f3(RL151, RL149, RL152) + this.f1008X[13] + 1859775393, 5) + RL150;
        int RL154 = m369RL(RL149, 10);
        int RL155 = m369RL(RL150 + m372f3(RL153, RL151, RL154) + this.f1008X[11] + 1859775393, 12) + RL152;
        int RL156 = m369RL(RL151, 10);
        int RL157 = m369RL(RL152 + m372f3(RL155, RL153, RL156) + this.f1008X[5] + 1859775393, 7) + RL154;
        int RL158 = m369RL(RL153, 10);
        int RL159 = m369RL(RL154 + m372f3(RL157, RL155, RL158) + this.f1008X[12] + 1859775393, 5) + RL156;
        int RL160 = m369RL(RL155, 10);
        int RL161 = m369RL(RL124 + m372f3(RL127, RL125, RL128) + this.f1008X[15] + 1836072691, 9) + RL126;
        int RL162 = m369RL(RL125, 10);
        int RL163 = m369RL(RL126 + m372f3(RL161, RL127, RL162) + this.f1008X[5] + 1836072691, 7) + RL128;
        int RL164 = m369RL(RL127, 10);
        int RL165 = m369RL(RL128 + m372f3(RL163, RL161, RL164) + this.f1008X[1] + 1836072691, 15) + RL162;
        int RL166 = m369RL(RL161, 10);
        int RL167 = m369RL(RL162 + m372f3(RL165, RL163, RL166) + this.f1008X[3] + 1836072691, 11) + RL164;
        int RL168 = m369RL(RL163, 10);
        int RL169 = m369RL(RL164 + m372f3(RL167, RL165, RL168) + this.f1008X[7] + 1836072691, 8) + RL166;
        int RL170 = m369RL(RL165, 10);
        int RL171 = m369RL(RL166 + m372f3(RL169, RL167, RL170) + this.f1008X[14] + 1836072691, 6) + RL168;
        int RL172 = m369RL(RL167, 10);
        int RL173 = m369RL(RL168 + m372f3(RL171, RL169, RL172) + this.f1008X[6] + 1836072691, 6) + RL170;
        int RL174 = m369RL(RL169, 10);
        int RL175 = m369RL(RL170 + m372f3(RL173, RL171, RL174) + this.f1008X[9] + 1836072691, 14) + RL172;
        int RL176 = m369RL(RL171, 10);
        int RL177 = m369RL(RL172 + m372f3(RL175, RL173, RL176) + this.f1008X[11] + 1836072691, 12) + RL174;
        int RL178 = m369RL(RL173, 10);
        int RL179 = m369RL(RL174 + m372f3(RL177, RL175, RL178) + this.f1008X[8] + 1836072691, 13) + RL176;
        int RL180 = m369RL(RL175, 10);
        int RL181 = m369RL(RL176 + m372f3(RL179, RL177, RL180) + this.f1008X[12] + 1836072691, 5) + RL178;
        int RL182 = m369RL(RL177, 10);
        int RL183 = m369RL(RL178 + m372f3(RL181, RL179, RL182) + this.f1008X[2] + 1836072691, 14) + RL180;
        int RL184 = m369RL(RL179, 10);
        int RL185 = m369RL(RL180 + m372f3(RL183, RL181, RL184) + this.f1008X[10] + 1836072691, 13) + RL182;
        int RL186 = m369RL(RL181, 10);
        int RL187 = m369RL(RL182 + m372f3(RL185, RL183, RL186) + this.f1008X[0] + 1836072691, 13) + RL184;
        int RL188 = m369RL(RL183, 10);
        int RL189 = m369RL(RL184 + m372f3(RL187, RL185, RL188) + this.f1008X[4] + 1836072691, 7) + RL186;
        int RL190 = m369RL(RL185, 10);
        int RL191 = m369RL(RL186 + m372f3(RL189, RL187, RL190) + this.f1008X[13] + 1836072691, 5) + RL188;
        int RL192 = m369RL(RL187, 10);
        int RL193 = m369RL(((RL156 + m373f4(RL159, RL157, RL160)) + this.f1008X[1]) - 1894007588, 11) + RL158;
        int RL194 = m369RL(RL157, 10);
        int RL195 = m369RL(((RL158 + m373f4(RL193, RL159, RL194)) + this.f1008X[9]) - 1894007588, 12) + RL160;
        int RL196 = m369RL(RL159, 10);
        int RL197 = m369RL(((RL160 + m373f4(RL195, RL193, RL196)) + this.f1008X[11]) - 1894007588, 14) + RL194;
        int RL198 = m369RL(RL193, 10);
        int RL199 = m369RL(((RL194 + m373f4(RL197, RL195, RL198)) + this.f1008X[10]) - 1894007588, 15) + RL196;
        int RL200 = m369RL(RL195, 10);
        int RL201 = m369RL(((RL196 + m373f4(RL199, RL197, RL200)) + this.f1008X[0]) - 1894007588, 14) + RL198;
        int RL202 = m369RL(RL197, 10);
        int RL203 = m369RL(((RL198 + m373f4(RL201, RL199, RL202)) + this.f1008X[8]) - 1894007588, 15) + RL200;
        int RL204 = m369RL(RL199, 10);
        int RL205 = m369RL(((RL200 + m373f4(RL203, RL201, RL204)) + this.f1008X[12]) - 1894007588, 9) + RL202;
        int RL206 = m369RL(RL201, 10);
        int RL207 = m369RL(((RL202 + m373f4(RL205, RL203, RL206)) + this.f1008X[4]) - 1894007588, 8) + RL204;
        int RL208 = m369RL(RL203, 10);
        int RL209 = m369RL(((RL204 + m373f4(RL207, RL205, RL208)) + this.f1008X[13]) - 1894007588, 9) + RL206;
        int RL210 = m369RL(RL205, 10);
        int RL211 = m369RL(((RL206 + m373f4(RL209, RL207, RL210)) + this.f1008X[3]) - 1894007588, 14) + RL208;
        int RL212 = m369RL(RL207, 10);
        int RL213 = m369RL(((RL208 + m373f4(RL211, RL209, RL212)) + this.f1008X[7]) - 1894007588, 5) + RL210;
        int RL214 = m369RL(RL209, 10);
        int RL215 = m369RL(((RL210 + m373f4(RL213, RL211, RL214)) + this.f1008X[15]) - 1894007588, 6) + RL212;
        int RL216 = m369RL(RL211, 10);
        int RL217 = m369RL(((RL212 + m373f4(RL215, RL213, RL216)) + this.f1008X[14]) - 1894007588, 8) + RL214;
        int RL218 = m369RL(RL213, 10);
        int RL219 = m369RL(((RL214 + m373f4(RL217, RL215, RL218)) + this.f1008X[5]) - 1894007588, 6) + RL216;
        int RL220 = m369RL(RL215, 10);
        int RL221 = m369RL(((RL216 + m373f4(RL219, RL217, RL220)) + this.f1008X[6]) - 1894007588, 5) + RL218;
        int RL222 = m369RL(RL217, 10);
        int RL223 = m369RL(((RL218 + m373f4(RL221, RL219, RL222)) + this.f1008X[2]) - 1894007588, 12) + RL220;
        int RL224 = m369RL(RL219, 10);
        int RL225 = m369RL(RL188 + m371f2(RL191, RL189, RL192) + this.f1008X[8] + 2053994217, 15) + RL190;
        int RL226 = m369RL(RL189, 10);
        int RL227 = m369RL(RL190 + m371f2(RL225, RL191, RL226) + this.f1008X[6] + 2053994217, 5) + RL192;
        int RL228 = m369RL(RL191, 10);
        int RL229 = m369RL(RL192 + m371f2(RL227, RL225, RL228) + this.f1008X[4] + 2053994217, 8) + RL226;
        int RL230 = m369RL(RL225, 10);
        int RL231 = m369RL(RL226 + m371f2(RL229, RL227, RL230) + this.f1008X[1] + 2053994217, 11) + RL228;
        int RL232 = m369RL(RL227, 10);
        int RL233 = m369RL(RL228 + m371f2(RL231, RL229, RL232) + this.f1008X[3] + 2053994217, 14) + RL230;
        int RL234 = m369RL(RL229, 10);
        int RL235 = m369RL(RL230 + m371f2(RL233, RL231, RL234) + this.f1008X[11] + 2053994217, 14) + RL232;
        int RL236 = m369RL(RL231, 10);
        int RL237 = m369RL(RL232 + m371f2(RL235, RL233, RL236) + this.f1008X[15] + 2053994217, 6) + RL234;
        int RL238 = m369RL(RL233, 10);
        int RL239 = m369RL(RL234 + m371f2(RL237, RL235, RL238) + this.f1008X[0] + 2053994217, 14) + RL236;
        int RL240 = m369RL(RL235, 10);
        int RL241 = m369RL(RL236 + m371f2(RL239, RL237, RL240) + this.f1008X[5] + 2053994217, 6) + RL238;
        int RL242 = m369RL(RL237, 10);
        int RL243 = m369RL(RL238 + m371f2(RL241, RL239, RL242) + this.f1008X[12] + 2053994217, 9) + RL240;
        int RL244 = m369RL(RL239, 10);
        int RL245 = m369RL(RL240 + m371f2(RL243, RL241, RL244) + this.f1008X[2] + 2053994217, 12) + RL242;
        int RL246 = m369RL(RL241, 10);
        int RL247 = m369RL(RL242 + m371f2(RL245, RL243, RL246) + this.f1008X[13] + 2053994217, 9) + RL244;
        int RL248 = m369RL(RL243, 10);
        int RL249 = m369RL(RL244 + m371f2(RL247, RL245, RL248) + this.f1008X[9] + 2053994217, 12) + RL246;
        int RL250 = m369RL(RL245, 10);
        int RL251 = m369RL(RL246 + m371f2(RL249, RL247, RL250) + this.f1008X[7] + 2053994217, 5) + RL248;
        int RL252 = m369RL(RL247, 10);
        int RL253 = m369RL(RL248 + m371f2(RL251, RL249, RL252) + this.f1008X[10] + 2053994217, 15) + RL250;
        int RL254 = m369RL(RL249, 10);
        int RL255 = m369RL(RL250 + m371f2(RL253, RL251, RL254) + this.f1008X[14] + 2053994217, 8) + RL252;
        int RL256 = m369RL(RL251, 10);
        int RL257 = m369RL(((RL220 + m374f5(RL223, RL221, RL224)) + this.f1008X[4]) - 1454113458, 9) + RL222;
        int RL258 = m369RL(RL221, 10);
        int RL259 = m369RL(((RL222 + m374f5(RL257, RL223, RL258)) + this.f1008X[0]) - 1454113458, 15) + RL224;
        int RL260 = m369RL(RL223, 10);
        int RL261 = m369RL(((RL224 + m374f5(RL259, RL257, RL260)) + this.f1008X[5]) - 1454113458, 5) + RL258;
        int RL262 = m369RL(RL257, 10);
        int RL263 = m369RL(((RL258 + m374f5(RL261, RL259, RL262)) + this.f1008X[9]) - 1454113458, 11) + RL260;
        int RL264 = m369RL(RL259, 10);
        int RL265 = m369RL(((RL260 + m374f5(RL263, RL261, RL264)) + this.f1008X[7]) - 1454113458, 6) + RL262;
        int RL266 = m369RL(RL261, 10);
        int RL267 = m369RL(((RL262 + m374f5(RL265, RL263, RL266)) + this.f1008X[12]) - 1454113458, 8) + RL264;
        int RL268 = m369RL(RL263, 10);
        int RL269 = m369RL(((RL264 + m374f5(RL267, RL265, RL268)) + this.f1008X[2]) - 1454113458, 13) + RL266;
        int RL270 = m369RL(RL265, 10);
        int RL271 = m369RL(((RL266 + m374f5(RL269, RL267, RL270)) + this.f1008X[10]) - 1454113458, 12) + RL268;
        int RL272 = m369RL(RL267, 10);
        int RL273 = m369RL(((RL268 + m374f5(RL271, RL269, RL272)) + this.f1008X[14]) - 1454113458, 5) + RL270;
        int RL274 = m369RL(RL269, 10);
        int RL275 = m369RL(((RL270 + m374f5(RL273, RL271, RL274)) + this.f1008X[1]) - 1454113458, 12) + RL272;
        int RL276 = m369RL(RL271, 10);
        int RL277 = m369RL(((RL272 + m374f5(RL275, RL273, RL276)) + this.f1008X[3]) - 1454113458, 13) + RL274;
        int RL278 = m369RL(RL273, 10);
        int RL279 = m369RL(((RL274 + m374f5(RL277, RL275, RL278)) + this.f1008X[8]) - 1454113458, 14) + RL276;
        int RL280 = m369RL(RL275, 10);
        int RL281 = m369RL(((RL276 + m374f5(RL279, RL277, RL280)) + this.f1008X[11]) - 1454113458, 11) + RL278;
        int RL282 = m369RL(RL277, 10);
        int RL283 = m369RL(((RL278 + m374f5(RL281, RL279, RL282)) + this.f1008X[6]) - 1454113458, 8) + RL280;
        int RL284 = m369RL(RL279, 10);
        int RL285 = m369RL(((RL280 + m374f5(RL283, RL281, RL284)) + this.f1008X[15]) - 1454113458, 5) + RL282;
        int RL286 = m369RL(RL281, 10);
        int RL287 = m369RL(((RL282 + m374f5(RL285, RL283, RL286)) + this.f1008X[13]) - 1454113458, 6) + RL284;
        int RL288 = m369RL(RL283, 10);
        int RL289 = m369RL(RL252 + m370f1(RL255, RL253, RL256) + this.f1008X[12], 8) + RL254;
        int RL290 = m369RL(RL253, 10);
        int RL291 = m369RL(RL254 + m370f1(RL289, RL255, RL290) + this.f1008X[15], 5) + RL256;
        int RL292 = m369RL(RL255, 10);
        int RL293 = m369RL(RL256 + m370f1(RL291, RL289, RL292) + this.f1008X[10], 12) + RL290;
        int RL294 = m369RL(RL289, 10);
        int RL295 = m369RL(RL290 + m370f1(RL293, RL291, RL294) + this.f1008X[4], 9) + RL292;
        int RL296 = m369RL(RL291, 10);
        int RL297 = m369RL(RL292 + m370f1(RL295, RL293, RL296) + this.f1008X[1], 12) + RL294;
        int RL298 = m369RL(RL293, 10);
        int RL299 = m369RL(RL294 + m370f1(RL297, RL295, RL298) + this.f1008X[5], 5) + RL296;
        int RL300 = m369RL(RL295, 10);
        int RL301 = m369RL(RL296 + m370f1(RL299, RL297, RL300) + this.f1008X[8], 14) + RL298;
        int RL302 = m369RL(RL297, 10);
        int RL303 = m369RL(RL298 + m370f1(RL301, RL299, RL302) + this.f1008X[7], 6) + RL300;
        int RL304 = m369RL(RL299, 10);
        int RL305 = m369RL(RL300 + m370f1(RL303, RL301, RL304) + this.f1008X[6], 8) + RL302;
        int RL306 = m369RL(RL301, 10);
        int RL307 = m369RL(RL302 + m370f1(RL305, RL303, RL306) + this.f1008X[2], 13) + RL304;
        int RL308 = m369RL(RL303, 10);
        int RL309 = m369RL(RL304 + m370f1(RL307, RL305, RL308) + this.f1008X[13], 6) + RL306;
        int RL310 = m369RL(RL305, 10);
        int RL311 = m369RL(RL306 + m370f1(RL309, RL307, RL310) + this.f1008X[14], 5) + RL308;
        int RL312 = m369RL(RL307, 10);
        int RL313 = m369RL(RL308 + m370f1(RL311, RL309, RL312) + this.f1008X[0], 15) + RL310;
        int RL314 = m369RL(RL309, 10);
        int RL315 = m369RL(RL310 + m370f1(RL313, RL311, RL314) + this.f1008X[3], 13) + RL312;
        int RL316 = m369RL(RL311, 10);
        int RL317 = m369RL(RL312 + m370f1(RL315, RL313, RL316) + this.f1008X[9], 11) + RL314;
        int RL318 = m369RL(RL313, 10);
        int RL319 = m369RL(RL314 + m370f1(RL317, RL315, RL318) + this.f1008X[11], 11) + RL316;
        int RL320 = m369RL(RL315, 10) + RL285 + this.f1004H1;
        this.f1004H1 = this.f1005H2 + RL288 + RL318;
        this.f1005H2 = this.f1006H3 + RL286 + RL316;
        this.f1006H3 = this.f1007H4 + RL284 + RL319;
        this.f1007H4 = this.f1003H0 + RL287 + RL317;
        this.f1003H0 = RL320;
        int i6 = 0;
        this.xOff = 0;
        while (true) {
            int[] iArr = this.f1008X;
            if (i6 != iArr.length) {
                iArr[i6] = 0;
                i6++;
            } else {
                return;
            }
        }
    }

    public Memoable copy() {
        return new RIPEMD160Digest(this);
    }

    public void reset(Memoable memoable) {
        copyIn((RIPEMD160Digest) memoable);
    }
}
