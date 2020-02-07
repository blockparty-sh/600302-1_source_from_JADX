package org.spongycastle.crypto.engines;

import com.google.common.base.Ascii;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class SerpentEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    static final int PHI = -1640531527;
    static final int ROUNDS = 32;

    /* renamed from: X0 */
    private int f1166X0;

    /* renamed from: X1 */
    private int f1167X1;

    /* renamed from: X2 */
    private int f1168X2;

    /* renamed from: X3 */
    private int f1169X3;
    private boolean encrypting;
    private int[] wKey;

    private int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    private int rotateRight(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    public String getAlgorithmName() {
        return "Serpent";
    }

    public int getBlockSize() {
        return 16;
    }

    public void reset() {
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.encrypting = z;
            this.wKey = makeWorkingKey(((KeyParameter) cipherParameters).getKey());
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("invalid parameter passed to Serpent init - ");
        sb.append(cipherParameters.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public final int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.wKey == null) {
            throw new IllegalStateException("Serpent not initialised");
        } else if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 16 <= bArr2.length) {
            if (this.encrypting) {
                encryptBlock(bArr, i, bArr2, i2);
            } else {
                decryptBlock(bArr, i, bArr2, i2);
            }
            return 16;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    private int[] makeWorkingKey(byte[] bArr) throws IllegalArgumentException {
        int[] iArr = new int[16];
        int length = bArr.length - 4;
        int i = 0;
        while (length > 0) {
            int i2 = i + 1;
            iArr[i] = bytesToWord(bArr, length);
            length -= 4;
            i = i2;
        }
        if (length == 0) {
            int i3 = i + 1;
            iArr[i] = bytesToWord(bArr, 0);
            if (i3 < 8) {
                iArr[i3] = 1;
            }
            int[] iArr2 = new int[132];
            for (int i4 = 8; i4 < 16; i4++) {
                int i5 = i4 - 8;
                iArr[i4] = rotateLeft((PHI ^ (((iArr[i5] ^ iArr[i4 - 5]) ^ iArr[i4 - 3]) ^ iArr[i4 - 1])) ^ i5, 11);
            }
            System.arraycopy(iArr, 8, iArr2, 0, 8);
            for (int i6 = 8; i6 < 132; i6++) {
                iArr2[i6] = rotateLeft(((((iArr2[i6 - 8] ^ iArr2[i6 - 5]) ^ iArr2[i6 - 3]) ^ iArr2[i6 - 1]) ^ PHI) ^ i6, 11);
            }
            sb3(iArr2[0], iArr2[1], iArr2[2], iArr2[3]);
            iArr2[0] = this.f1166X0;
            iArr2[1] = this.f1167X1;
            iArr2[2] = this.f1168X2;
            iArr2[3] = this.f1169X3;
            sb2(iArr2[4], iArr2[5], iArr2[6], iArr2[7]);
            iArr2[4] = this.f1166X0;
            iArr2[5] = this.f1167X1;
            iArr2[6] = this.f1168X2;
            iArr2[7] = this.f1169X3;
            sb1(iArr2[8], iArr2[9], iArr2[10], iArr2[11]);
            iArr2[8] = this.f1166X0;
            iArr2[9] = this.f1167X1;
            iArr2[10] = this.f1168X2;
            iArr2[11] = this.f1169X3;
            sb0(iArr2[12], iArr2[13], iArr2[14], iArr2[15]);
            iArr2[12] = this.f1166X0;
            iArr2[13] = this.f1167X1;
            iArr2[14] = this.f1168X2;
            iArr2[15] = this.f1169X3;
            sb7(iArr2[16], iArr2[17], iArr2[18], iArr2[19]);
            iArr2[16] = this.f1166X0;
            iArr2[17] = this.f1167X1;
            iArr2[18] = this.f1168X2;
            iArr2[19] = this.f1169X3;
            sb6(iArr2[20], iArr2[21], iArr2[22], iArr2[23]);
            iArr2[20] = this.f1166X0;
            iArr2[21] = this.f1167X1;
            iArr2[22] = this.f1168X2;
            iArr2[23] = this.f1169X3;
            sb5(iArr2[24], iArr2[25], iArr2[26], iArr2[27]);
            iArr2[24] = this.f1166X0;
            iArr2[25] = this.f1167X1;
            iArr2[26] = this.f1168X2;
            iArr2[27] = this.f1169X3;
            sb4(iArr2[28], iArr2[29], iArr2[30], iArr2[31]);
            iArr2[28] = this.f1166X0;
            iArr2[29] = this.f1167X1;
            iArr2[30] = this.f1168X2;
            iArr2[31] = this.f1169X3;
            sb3(iArr2[32], iArr2[33], iArr2[34], iArr2[35]);
            iArr2[32] = this.f1166X0;
            iArr2[33] = this.f1167X1;
            iArr2[34] = this.f1168X2;
            iArr2[35] = this.f1169X3;
            sb2(iArr2[36], iArr2[37], iArr2[38], iArr2[39]);
            iArr2[36] = this.f1166X0;
            iArr2[37] = this.f1167X1;
            iArr2[38] = this.f1168X2;
            iArr2[39] = this.f1169X3;
            sb1(iArr2[40], iArr2[41], iArr2[42], iArr2[43]);
            iArr2[40] = this.f1166X0;
            iArr2[41] = this.f1167X1;
            iArr2[42] = this.f1168X2;
            iArr2[43] = this.f1169X3;
            sb0(iArr2[44], iArr2[45], iArr2[46], iArr2[47]);
            iArr2[44] = this.f1166X0;
            iArr2[45] = this.f1167X1;
            iArr2[46] = this.f1168X2;
            iArr2[47] = this.f1169X3;
            sb7(iArr2[48], iArr2[49], iArr2[50], iArr2[51]);
            iArr2[48] = this.f1166X0;
            iArr2[49] = this.f1167X1;
            iArr2[50] = this.f1168X2;
            iArr2[51] = this.f1169X3;
            sb6(iArr2[52], iArr2[53], iArr2[54], iArr2[55]);
            iArr2[52] = this.f1166X0;
            iArr2[53] = this.f1167X1;
            iArr2[54] = this.f1168X2;
            iArr2[55] = this.f1169X3;
            sb5(iArr2[56], iArr2[57], iArr2[58], iArr2[59]);
            iArr2[56] = this.f1166X0;
            iArr2[57] = this.f1167X1;
            iArr2[58] = this.f1168X2;
            iArr2[59] = this.f1169X3;
            sb4(iArr2[60], iArr2[61], iArr2[62], iArr2[63]);
            iArr2[60] = this.f1166X0;
            iArr2[61] = this.f1167X1;
            iArr2[62] = this.f1168X2;
            iArr2[63] = this.f1169X3;
            sb3(iArr2[64], iArr2[65], iArr2[66], iArr2[67]);
            iArr2[64] = this.f1166X0;
            iArr2[65] = this.f1167X1;
            iArr2[66] = this.f1168X2;
            iArr2[67] = this.f1169X3;
            sb2(iArr2[68], iArr2[69], iArr2[70], iArr2[71]);
            iArr2[68] = this.f1166X0;
            iArr2[69] = this.f1167X1;
            iArr2[70] = this.f1168X2;
            iArr2[71] = this.f1169X3;
            sb1(iArr2[72], iArr2[73], iArr2[74], iArr2[75]);
            iArr2[72] = this.f1166X0;
            iArr2[73] = this.f1167X1;
            iArr2[74] = this.f1168X2;
            iArr2[75] = this.f1169X3;
            sb0(iArr2[76], iArr2[77], iArr2[78], iArr2[79]);
            iArr2[76] = this.f1166X0;
            iArr2[77] = this.f1167X1;
            iArr2[78] = this.f1168X2;
            iArr2[79] = this.f1169X3;
            sb7(iArr2[80], iArr2[81], iArr2[82], iArr2[83]);
            iArr2[80] = this.f1166X0;
            iArr2[81] = this.f1167X1;
            iArr2[82] = this.f1168X2;
            iArr2[83] = this.f1169X3;
            sb6(iArr2[84], iArr2[85], iArr2[86], iArr2[87]);
            iArr2[84] = this.f1166X0;
            iArr2[85] = this.f1167X1;
            iArr2[86] = this.f1168X2;
            iArr2[87] = this.f1169X3;
            sb5(iArr2[88], iArr2[89], iArr2[90], iArr2[91]);
            iArr2[88] = this.f1166X0;
            iArr2[89] = this.f1167X1;
            iArr2[90] = this.f1168X2;
            iArr2[91] = this.f1169X3;
            sb4(iArr2[92], iArr2[93], iArr2[94], iArr2[95]);
            iArr2[92] = this.f1166X0;
            iArr2[93] = this.f1167X1;
            iArr2[94] = this.f1168X2;
            iArr2[95] = this.f1169X3;
            sb3(iArr2[96], iArr2[97], iArr2[98], iArr2[99]);
            iArr2[96] = this.f1166X0;
            iArr2[97] = this.f1167X1;
            iArr2[98] = this.f1168X2;
            iArr2[99] = this.f1169X3;
            sb2(iArr2[100], iArr2[101], iArr2[102], iArr2[103]);
            iArr2[100] = this.f1166X0;
            iArr2[101] = this.f1167X1;
            iArr2[102] = this.f1168X2;
            iArr2[103] = this.f1169X3;
            sb1(iArr2[104], iArr2[105], iArr2[106], iArr2[107]);
            iArr2[104] = this.f1166X0;
            iArr2[105] = this.f1167X1;
            iArr2[106] = this.f1168X2;
            iArr2[107] = this.f1169X3;
            sb0(iArr2[108], iArr2[109], iArr2[110], iArr2[111]);
            iArr2[108] = this.f1166X0;
            iArr2[109] = this.f1167X1;
            iArr2[110] = this.f1168X2;
            iArr2[111] = this.f1169X3;
            sb7(iArr2[112], iArr2[113], iArr2[114], iArr2[115]);
            iArr2[112] = this.f1166X0;
            iArr2[113] = this.f1167X1;
            iArr2[114] = this.f1168X2;
            iArr2[115] = this.f1169X3;
            sb6(iArr2[116], iArr2[117], iArr2[118], iArr2[119]);
            iArr2[116] = this.f1166X0;
            iArr2[117] = this.f1167X1;
            iArr2[118] = this.f1168X2;
            iArr2[119] = this.f1169X3;
            sb5(iArr2[120], iArr2[121], iArr2[122], iArr2[123]);
            iArr2[120] = this.f1166X0;
            iArr2[121] = this.f1167X1;
            iArr2[122] = this.f1168X2;
            iArr2[123] = this.f1169X3;
            sb4(iArr2[124], iArr2[125], iArr2[126], iArr2[127]);
            iArr2[124] = this.f1166X0;
            iArr2[125] = this.f1167X1;
            iArr2[126] = this.f1168X2;
            iArr2[127] = this.f1169X3;
            sb3(iArr2[128], iArr2[129], iArr2[130], iArr2[131]);
            iArr2[128] = this.f1166X0;
            iArr2[129] = this.f1167X1;
            iArr2[130] = this.f1168X2;
            iArr2[131] = this.f1169X3;
            return iArr2;
        }
        throw new IllegalArgumentException("key must be a multiple of 4 bytes");
    }

    private int bytesToWord(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << Ascii.CAN) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    private void wordToBytes(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) i;
        bArr[i2 + 2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) (i >>> 16);
        bArr[i2] = (byte) (i >>> 24);
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.f1169X3 = bytesToWord(bArr, i);
        this.f1168X2 = bytesToWord(bArr, i + 4);
        this.f1167X1 = bytesToWord(bArr, i + 8);
        this.f1166X0 = bytesToWord(bArr, i + 12);
        int[] iArr = this.wKey;
        sb0(iArr[0] ^ this.f1166X0, iArr[1] ^ this.f1167X1, iArr[2] ^ this.f1168X2, iArr[3] ^ this.f1169X3);
        m410LT();
        int[] iArr2 = this.wKey;
        sb1(iArr2[4] ^ this.f1166X0, iArr2[5] ^ this.f1167X1, iArr2[6] ^ this.f1168X2, iArr2[7] ^ this.f1169X3);
        m410LT();
        int[] iArr3 = this.wKey;
        sb2(iArr3[8] ^ this.f1166X0, iArr3[9] ^ this.f1167X1, iArr3[10] ^ this.f1168X2, iArr3[11] ^ this.f1169X3);
        m410LT();
        int[] iArr4 = this.wKey;
        sb3(iArr4[12] ^ this.f1166X0, iArr4[13] ^ this.f1167X1, iArr4[14] ^ this.f1168X2, iArr4[15] ^ this.f1169X3);
        m410LT();
        int[] iArr5 = this.wKey;
        sb4(iArr5[16] ^ this.f1166X0, iArr5[17] ^ this.f1167X1, iArr5[18] ^ this.f1168X2, iArr5[19] ^ this.f1169X3);
        m410LT();
        int[] iArr6 = this.wKey;
        sb5(iArr6[20] ^ this.f1166X0, iArr6[21] ^ this.f1167X1, iArr6[22] ^ this.f1168X2, iArr6[23] ^ this.f1169X3);
        m410LT();
        int[] iArr7 = this.wKey;
        sb6(iArr7[24] ^ this.f1166X0, iArr7[25] ^ this.f1167X1, iArr7[26] ^ this.f1168X2, iArr7[27] ^ this.f1169X3);
        m410LT();
        int[] iArr8 = this.wKey;
        sb7(iArr8[28] ^ this.f1166X0, iArr8[29] ^ this.f1167X1, iArr8[30] ^ this.f1168X2, iArr8[31] ^ this.f1169X3);
        m410LT();
        int[] iArr9 = this.wKey;
        sb0(iArr9[32] ^ this.f1166X0, iArr9[33] ^ this.f1167X1, iArr9[34] ^ this.f1168X2, iArr9[35] ^ this.f1169X3);
        m410LT();
        int[] iArr10 = this.wKey;
        sb1(iArr10[36] ^ this.f1166X0, iArr10[37] ^ this.f1167X1, iArr10[38] ^ this.f1168X2, iArr10[39] ^ this.f1169X3);
        m410LT();
        int[] iArr11 = this.wKey;
        sb2(iArr11[40] ^ this.f1166X0, iArr11[41] ^ this.f1167X1, iArr11[42] ^ this.f1168X2, iArr11[43] ^ this.f1169X3);
        m410LT();
        int[] iArr12 = this.wKey;
        sb3(iArr12[44] ^ this.f1166X0, iArr12[45] ^ this.f1167X1, iArr12[46] ^ this.f1168X2, iArr12[47] ^ this.f1169X3);
        m410LT();
        int[] iArr13 = this.wKey;
        sb4(iArr13[48] ^ this.f1166X0, iArr13[49] ^ this.f1167X1, iArr13[50] ^ this.f1168X2, iArr13[51] ^ this.f1169X3);
        m410LT();
        int[] iArr14 = this.wKey;
        sb5(iArr14[52] ^ this.f1166X0, iArr14[53] ^ this.f1167X1, iArr14[54] ^ this.f1168X2, iArr14[55] ^ this.f1169X3);
        m410LT();
        int[] iArr15 = this.wKey;
        sb6(iArr15[56] ^ this.f1166X0, iArr15[57] ^ this.f1167X1, iArr15[58] ^ this.f1168X2, iArr15[59] ^ this.f1169X3);
        m410LT();
        int[] iArr16 = this.wKey;
        sb7(iArr16[60] ^ this.f1166X0, iArr16[61] ^ this.f1167X1, iArr16[62] ^ this.f1168X2, iArr16[63] ^ this.f1169X3);
        m410LT();
        int[] iArr17 = this.wKey;
        sb0(iArr17[64] ^ this.f1166X0, iArr17[65] ^ this.f1167X1, iArr17[66] ^ this.f1168X2, iArr17[67] ^ this.f1169X3);
        m410LT();
        int[] iArr18 = this.wKey;
        sb1(iArr18[68] ^ this.f1166X0, iArr18[69] ^ this.f1167X1, iArr18[70] ^ this.f1168X2, iArr18[71] ^ this.f1169X3);
        m410LT();
        int[] iArr19 = this.wKey;
        sb2(iArr19[72] ^ this.f1166X0, iArr19[73] ^ this.f1167X1, iArr19[74] ^ this.f1168X2, iArr19[75] ^ this.f1169X3);
        m410LT();
        int[] iArr20 = this.wKey;
        sb3(iArr20[76] ^ this.f1166X0, iArr20[77] ^ this.f1167X1, iArr20[78] ^ this.f1168X2, iArr20[79] ^ this.f1169X3);
        m410LT();
        int[] iArr21 = this.wKey;
        sb4(iArr21[80] ^ this.f1166X0, iArr21[81] ^ this.f1167X1, iArr21[82] ^ this.f1168X2, iArr21[83] ^ this.f1169X3);
        m410LT();
        int[] iArr22 = this.wKey;
        sb5(iArr22[84] ^ this.f1166X0, iArr22[85] ^ this.f1167X1, iArr22[86] ^ this.f1168X2, iArr22[87] ^ this.f1169X3);
        m410LT();
        int[] iArr23 = this.wKey;
        sb6(iArr23[88] ^ this.f1166X0, iArr23[89] ^ this.f1167X1, iArr23[90] ^ this.f1168X2, iArr23[91] ^ this.f1169X3);
        m410LT();
        int[] iArr24 = this.wKey;
        sb7(iArr24[92] ^ this.f1166X0, iArr24[93] ^ this.f1167X1, iArr24[94] ^ this.f1168X2, iArr24[95] ^ this.f1169X3);
        m410LT();
        int[] iArr25 = this.wKey;
        sb0(iArr25[96] ^ this.f1166X0, iArr25[97] ^ this.f1167X1, iArr25[98] ^ this.f1168X2, iArr25[99] ^ this.f1169X3);
        m410LT();
        int[] iArr26 = this.wKey;
        sb1(iArr26[100] ^ this.f1166X0, iArr26[101] ^ this.f1167X1, iArr26[102] ^ this.f1168X2, iArr26[103] ^ this.f1169X3);
        m410LT();
        int[] iArr27 = this.wKey;
        sb2(iArr27[104] ^ this.f1166X0, iArr27[105] ^ this.f1167X1, iArr27[106] ^ this.f1168X2, iArr27[107] ^ this.f1169X3);
        m410LT();
        int[] iArr28 = this.wKey;
        sb3(iArr28[108] ^ this.f1166X0, iArr28[109] ^ this.f1167X1, iArr28[110] ^ this.f1168X2, iArr28[111] ^ this.f1169X3);
        m410LT();
        int[] iArr29 = this.wKey;
        sb4(iArr29[112] ^ this.f1166X0, iArr29[113] ^ this.f1167X1, iArr29[114] ^ this.f1168X2, iArr29[115] ^ this.f1169X3);
        m410LT();
        int[] iArr30 = this.wKey;
        sb5(iArr30[116] ^ this.f1166X0, iArr30[117] ^ this.f1167X1, iArr30[118] ^ this.f1168X2, iArr30[119] ^ this.f1169X3);
        m410LT();
        int[] iArr31 = this.wKey;
        sb6(iArr31[120] ^ this.f1166X0, iArr31[121] ^ this.f1167X1, iArr31[122] ^ this.f1168X2, iArr31[123] ^ this.f1169X3);
        m410LT();
        int[] iArr32 = this.wKey;
        sb7(iArr32[124] ^ this.f1166X0, iArr32[125] ^ this.f1167X1, iArr32[126] ^ this.f1168X2, iArr32[127] ^ this.f1169X3);
        wordToBytes(this.wKey[131] ^ this.f1169X3, bArr2, i2);
        wordToBytes(this.wKey[130] ^ this.f1168X2, bArr2, i2 + 4);
        wordToBytes(this.wKey[129] ^ this.f1167X1, bArr2, i2 + 8);
        wordToBytes(this.wKey[128] ^ this.f1166X0, bArr2, i2 + 12);
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.f1169X3 = this.wKey[131] ^ bytesToWord(bArr, i);
        this.f1168X2 = this.wKey[130] ^ bytesToWord(bArr, i + 4);
        this.f1167X1 = this.wKey[129] ^ bytesToWord(bArr, i + 8);
        this.f1166X0 = bytesToWord(bArr, i + 12) ^ this.wKey[128];
        ib7(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i3 = this.f1166X0;
        int[] iArr = this.wKey;
        this.f1166X0 = i3 ^ iArr[124];
        this.f1167X1 ^= iArr[125];
        this.f1168X2 ^= iArr[126];
        this.f1169X3 ^= iArr[127];
        inverseLT();
        ib6(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i4 = this.f1166X0;
        int[] iArr2 = this.wKey;
        this.f1166X0 = i4 ^ iArr2[120];
        this.f1167X1 ^= iArr2[121];
        this.f1168X2 ^= iArr2[122];
        this.f1169X3 ^= iArr2[123];
        inverseLT();
        ib5(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i5 = this.f1166X0;
        int[] iArr3 = this.wKey;
        this.f1166X0 = i5 ^ iArr3[116];
        this.f1167X1 ^= iArr3[117];
        this.f1168X2 ^= iArr3[118];
        this.f1169X3 ^= iArr3[119];
        inverseLT();
        ib4(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i6 = this.f1166X0;
        int[] iArr4 = this.wKey;
        this.f1166X0 = i6 ^ iArr4[112];
        this.f1167X1 ^= iArr4[113];
        this.f1168X2 ^= iArr4[114];
        this.f1169X3 ^= iArr4[115];
        inverseLT();
        ib3(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i7 = this.f1166X0;
        int[] iArr5 = this.wKey;
        this.f1166X0 = i7 ^ iArr5[108];
        this.f1167X1 ^= iArr5[109];
        this.f1168X2 ^= iArr5[110];
        this.f1169X3 ^= iArr5[111];
        inverseLT();
        ib2(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i8 = this.f1166X0;
        int[] iArr6 = this.wKey;
        this.f1166X0 = i8 ^ iArr6[104];
        this.f1167X1 ^= iArr6[105];
        this.f1168X2 ^= iArr6[106];
        this.f1169X3 ^= iArr6[107];
        inverseLT();
        ib1(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i9 = this.f1166X0;
        int[] iArr7 = this.wKey;
        this.f1166X0 = i9 ^ iArr7[100];
        this.f1167X1 ^= iArr7[101];
        this.f1168X2 ^= iArr7[102];
        this.f1169X3 ^= iArr7[103];
        inverseLT();
        ib0(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i10 = this.f1166X0;
        int[] iArr8 = this.wKey;
        this.f1166X0 = i10 ^ iArr8[96];
        this.f1167X1 ^= iArr8[97];
        this.f1168X2 ^= iArr8[98];
        this.f1169X3 ^= iArr8[99];
        inverseLT();
        ib7(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i11 = this.f1166X0;
        int[] iArr9 = this.wKey;
        this.f1166X0 = i11 ^ iArr9[92];
        this.f1167X1 ^= iArr9[93];
        this.f1168X2 ^= iArr9[94];
        this.f1169X3 ^= iArr9[95];
        inverseLT();
        ib6(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i12 = this.f1166X0;
        int[] iArr10 = this.wKey;
        this.f1166X0 = i12 ^ iArr10[88];
        this.f1167X1 ^= iArr10[89];
        this.f1168X2 ^= iArr10[90];
        this.f1169X3 ^= iArr10[91];
        inverseLT();
        ib5(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i13 = this.f1166X0;
        int[] iArr11 = this.wKey;
        this.f1166X0 = i13 ^ iArr11[84];
        this.f1167X1 ^= iArr11[85];
        this.f1168X2 ^= iArr11[86];
        this.f1169X3 ^= iArr11[87];
        inverseLT();
        ib4(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i14 = this.f1166X0;
        int[] iArr12 = this.wKey;
        this.f1166X0 = i14 ^ iArr12[80];
        this.f1167X1 ^= iArr12[81];
        this.f1168X2 ^= iArr12[82];
        this.f1169X3 ^= iArr12[83];
        inverseLT();
        ib3(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i15 = this.f1166X0;
        int[] iArr13 = this.wKey;
        this.f1166X0 = i15 ^ iArr13[76];
        this.f1167X1 ^= iArr13[77];
        this.f1168X2 ^= iArr13[78];
        this.f1169X3 ^= iArr13[79];
        inverseLT();
        ib2(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i16 = this.f1166X0;
        int[] iArr14 = this.wKey;
        this.f1166X0 = i16 ^ iArr14[72];
        this.f1167X1 ^= iArr14[73];
        this.f1168X2 ^= iArr14[74];
        this.f1169X3 ^= iArr14[75];
        inverseLT();
        ib1(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i17 = this.f1166X0;
        int[] iArr15 = this.wKey;
        this.f1166X0 = i17 ^ iArr15[68];
        this.f1167X1 ^= iArr15[69];
        this.f1168X2 ^= iArr15[70];
        this.f1169X3 ^= iArr15[71];
        inverseLT();
        ib0(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i18 = this.f1166X0;
        int[] iArr16 = this.wKey;
        this.f1166X0 = i18 ^ iArr16[64];
        this.f1167X1 ^= iArr16[65];
        this.f1168X2 ^= iArr16[66];
        this.f1169X3 ^= iArr16[67];
        inverseLT();
        ib7(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i19 = this.f1166X0;
        int[] iArr17 = this.wKey;
        this.f1166X0 = i19 ^ iArr17[60];
        this.f1167X1 ^= iArr17[61];
        this.f1168X2 ^= iArr17[62];
        this.f1169X3 ^= iArr17[63];
        inverseLT();
        ib6(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i20 = this.f1166X0;
        int[] iArr18 = this.wKey;
        this.f1166X0 = i20 ^ iArr18[56];
        this.f1167X1 ^= iArr18[57];
        this.f1168X2 ^= iArr18[58];
        this.f1169X3 ^= iArr18[59];
        inverseLT();
        ib5(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i21 = this.f1166X0;
        int[] iArr19 = this.wKey;
        this.f1166X0 = i21 ^ iArr19[52];
        this.f1167X1 ^= iArr19[53];
        this.f1168X2 ^= iArr19[54];
        this.f1169X3 ^= iArr19[55];
        inverseLT();
        ib4(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i22 = this.f1166X0;
        int[] iArr20 = this.wKey;
        this.f1166X0 = i22 ^ iArr20[48];
        this.f1167X1 ^= iArr20[49];
        this.f1168X2 ^= iArr20[50];
        this.f1169X3 ^= iArr20[51];
        inverseLT();
        ib3(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i23 = this.f1166X0;
        int[] iArr21 = this.wKey;
        this.f1166X0 = i23 ^ iArr21[44];
        this.f1167X1 ^= iArr21[45];
        this.f1168X2 ^= iArr21[46];
        this.f1169X3 ^= iArr21[47];
        inverseLT();
        ib2(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i24 = this.f1166X0;
        int[] iArr22 = this.wKey;
        this.f1166X0 = i24 ^ iArr22[40];
        this.f1167X1 ^= iArr22[41];
        this.f1168X2 ^= iArr22[42];
        this.f1169X3 ^= iArr22[43];
        inverseLT();
        ib1(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i25 = this.f1166X0;
        int[] iArr23 = this.wKey;
        this.f1166X0 = i25 ^ iArr23[36];
        this.f1167X1 ^= iArr23[37];
        this.f1168X2 ^= iArr23[38];
        this.f1169X3 ^= iArr23[39];
        inverseLT();
        ib0(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i26 = this.f1166X0;
        int[] iArr24 = this.wKey;
        this.f1166X0 = i26 ^ iArr24[32];
        this.f1167X1 ^= iArr24[33];
        this.f1168X2 ^= iArr24[34];
        this.f1169X3 ^= iArr24[35];
        inverseLT();
        ib7(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i27 = this.f1166X0;
        int[] iArr25 = this.wKey;
        this.f1166X0 = i27 ^ iArr25[28];
        this.f1167X1 ^= iArr25[29];
        this.f1168X2 ^= iArr25[30];
        this.f1169X3 ^= iArr25[31];
        inverseLT();
        ib6(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i28 = this.f1166X0;
        int[] iArr26 = this.wKey;
        this.f1166X0 = i28 ^ iArr26[24];
        this.f1167X1 ^= iArr26[25];
        this.f1168X2 ^= iArr26[26];
        this.f1169X3 ^= iArr26[27];
        inverseLT();
        ib5(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i29 = this.f1166X0;
        int[] iArr27 = this.wKey;
        this.f1166X0 = i29 ^ iArr27[20];
        this.f1167X1 ^= iArr27[21];
        this.f1168X2 ^= iArr27[22];
        this.f1169X3 ^= iArr27[23];
        inverseLT();
        ib4(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i30 = this.f1166X0;
        int[] iArr28 = this.wKey;
        this.f1166X0 = i30 ^ iArr28[16];
        this.f1167X1 ^= iArr28[17];
        this.f1168X2 ^= iArr28[18];
        this.f1169X3 ^= iArr28[19];
        inverseLT();
        ib3(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i31 = this.f1166X0;
        int[] iArr29 = this.wKey;
        this.f1166X0 = i31 ^ iArr29[12];
        this.f1167X1 ^= iArr29[13];
        this.f1168X2 ^= iArr29[14];
        this.f1169X3 ^= iArr29[15];
        inverseLT();
        ib2(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i32 = this.f1166X0;
        int[] iArr30 = this.wKey;
        this.f1166X0 = i32 ^ iArr30[8];
        this.f1167X1 ^= iArr30[9];
        this.f1168X2 ^= iArr30[10];
        this.f1169X3 ^= iArr30[11];
        inverseLT();
        ib1(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        int i33 = this.f1166X0;
        int[] iArr31 = this.wKey;
        this.f1166X0 = i33 ^ iArr31[4];
        this.f1167X1 ^= iArr31[5];
        this.f1168X2 ^= iArr31[6];
        this.f1169X3 ^= iArr31[7];
        inverseLT();
        ib0(this.f1166X0, this.f1167X1, this.f1168X2, this.f1169X3);
        wordToBytes(this.f1169X3 ^ this.wKey[3], bArr2, i2);
        wordToBytes(this.f1168X2 ^ this.wKey[2], bArr2, i2 + 4);
        wordToBytes(this.f1167X1 ^ this.wKey[1], bArr2, i2 + 8);
        wordToBytes(this.f1166X0 ^ this.wKey[0], bArr2, i2 + 12);
    }

    private void sb0(int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = i3 ^ i5;
        int i7 = i2 ^ i6;
        this.f1169X3 = (i4 & i) ^ i7;
        int i8 = i ^ (i2 & i5);
        this.f1168X2 = (i3 | i8) ^ i7;
        int i9 = this.f1169X3 & (i6 ^ i8);
        this.f1167X1 = (~i6) ^ i9;
        this.f1166X0 = (~i8) ^ i9;
    }

    private void ib0(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i2 ^ i;
        int i7 = (i5 | i6) ^ i4;
        int i8 = i3 ^ i7;
        this.f1168X2 = i6 ^ i8;
        int i9 = (i6 & i4) ^ i5;
        this.f1167X1 = (this.f1168X2 & i9) ^ i7;
        this.f1169X3 = (i & i7) ^ (this.f1167X1 | i8);
        this.f1166X0 = this.f1169X3 ^ (i9 ^ i8);
    }

    private void sb1(int i, int i2, int i3, int i4) {
        int i5 = (~i) ^ i2;
        int i6 = (i | i5) ^ i3;
        this.f1168X2 = i4 ^ i6;
        int i7 = i2 ^ (i4 | i5);
        int i8 = this.f1168X2 ^ i5;
        this.f1169X3 = (i6 & i7) ^ i8;
        int i9 = i7 ^ i6;
        this.f1167X1 = this.f1169X3 ^ i9;
        this.f1166X0 = i6 ^ (i9 & i8);
    }

    private void ib1(int i, int i2, int i3, int i4) {
        int i5 = i4 ^ i2;
        int i6 = i ^ (i2 & i5);
        int i7 = i5 ^ i6;
        this.f1169X3 = i3 ^ i7;
        int i8 = i2 ^ (i5 & i6);
        int i9 = this.f1169X3;
        this.f1167X1 = i6 ^ (i9 | i8);
        int i10 = ~this.f1167X1;
        int i11 = i8 ^ i9;
        this.f1166X0 = i10 ^ i11;
        this.f1168X2 = (i10 | i11) ^ i7;
    }

    private void sb2(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i2 ^ i4;
        this.f1166X0 = (i3 & i5) ^ i6;
        int i7 = i3 ^ i5;
        int i8 = this.f1166X0;
        int i9 = i2 & (i3 ^ i8);
        this.f1169X3 = i7 ^ i9;
        this.f1168X2 = i ^ ((i9 | i4) & (i8 | i7));
        this.f1167X1 = (this.f1169X3 ^ i6) ^ (this.f1168X2 ^ (i4 | i5));
    }

    private void ib2(int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i4;
        int i6 = ~i5;
        int i7 = i ^ i3;
        int i8 = i3 ^ i5;
        this.f1166X0 = (i2 & i8) ^ i7;
        this.f1169X3 = (((i | i6) ^ i4) | i7) ^ i5;
        int i9 = ~i8;
        int i10 = this.f1166X0 | this.f1169X3;
        this.f1167X1 = i9 ^ i10;
        this.f1168X2 = (i9 & i4) ^ (i10 ^ i7);
    }

    private void sb3(int i, int i2, int i3, int i4) {
        int i5 = i ^ i2;
        int i6 = i & i3;
        int i7 = i | i4;
        int i8 = i3 ^ i4;
        int i9 = i6 | (i5 & i7);
        this.f1168X2 = i8 ^ i9;
        int i10 = (i7 ^ i2) ^ i9;
        this.f1166X0 = i5 ^ (i8 & i10);
        int i11 = this.f1168X2 & this.f1166X0;
        this.f1167X1 = i10 ^ i11;
        this.f1169X3 = (i2 | i4) ^ (i8 ^ i11);
    }

    private void ib3(int i, int i2, int i3, int i4) {
        int i5 = i | i2;
        int i6 = i2 ^ i3;
        int i7 = i ^ (i2 & i6);
        int i8 = i3 ^ i7;
        int i9 = i4 | i7;
        this.f1166X0 = i6 ^ i9;
        int i10 = (i9 | i6) ^ i4;
        this.f1168X2 = i8 ^ i10;
        int i11 = i5 ^ i10;
        int i12 = this.f1166X0;
        this.f1169X3 = i7 ^ (i12 & i11);
        this.f1167X1 = this.f1169X3 ^ (i11 ^ i12);
    }

    private void sb4(int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = i3 ^ (i4 & i5);
        int i7 = i2 | i6;
        this.f1169X3 = i5 ^ i7;
        int i8 = ~i2;
        this.f1166X0 = (i5 | i8) ^ i6;
        int i9 = i8 ^ i5;
        this.f1168X2 = (i7 & i9) ^ (this.f1166X0 & i);
        this.f1167X1 = (i ^ i6) ^ (i9 & this.f1168X2);
    }

    private void ib4(int i, int i2, int i3, int i4) {
        int i5 = i2 ^ ((i3 | i4) & i);
        int i6 = i3 ^ (i & i5);
        this.f1167X1 = i4 ^ i6;
        int i7 = ~i;
        int i8 = this.f1167X1;
        this.f1169X3 = (i6 & i8) ^ i5;
        int i9 = (i8 | i7) ^ i4;
        this.f1166X0 = this.f1169X3 ^ i9;
        this.f1168X2 = (i7 ^ i8) ^ (i5 & i9);
    }

    private void sb5(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i2;
        int i7 = i ^ i4;
        this.f1166X0 = (i3 ^ i5) ^ (i6 | i7);
        int i8 = this.f1166X0;
        int i9 = i4 & i8;
        this.f1167X1 = (i6 ^ i8) ^ i9;
        int i10 = i7 ^ (i8 | i5);
        this.f1168X2 = (i6 | i9) ^ i10;
        this.f1169X3 = (i10 & this.f1167X1) ^ (i2 ^ i9);
    }

    private void ib5(int i, int i2, int i3, int i4) {
        int i5 = ~i3;
        int i6 = (i2 & i5) ^ i4;
        int i7 = i & i6;
        this.f1169X3 = (i2 ^ i5) ^ i7;
        int i8 = this.f1169X3 | i2;
        this.f1167X1 = i6 ^ (i & i8);
        int i9 = i4 | i;
        this.f1166X0 = (i5 ^ i8) ^ i9;
        this.f1168X2 = ((i ^ i3) | i7) ^ (i2 & i9);
    }

    private void sb6(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i4;
        int i7 = i2 ^ i6;
        int i8 = i3 ^ (i5 | i6);
        this.f1167X1 = i2 ^ i8;
        int i9 = (i6 | this.f1167X1) ^ i4;
        this.f1168X2 = (i8 & i9) ^ i7;
        int i10 = i9 ^ i8;
        this.f1166X0 = this.f1168X2 ^ i10;
        this.f1169X3 = (i10 & i7) ^ (~i8);
    }

    private void ib6(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i2;
        int i7 = i3 ^ i6;
        int i8 = (i3 | i5) ^ i4;
        this.f1167X1 = i7 ^ i8;
        int i9 = i6 ^ (i7 & i8);
        this.f1169X3 = i8 ^ (i2 | i9);
        int i10 = i2 | this.f1169X3;
        this.f1166X0 = i9 ^ i10;
        this.f1168X2 = (i4 & i5) ^ (i10 ^ i7);
    }

    private void sb7(int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i3;
        int i6 = (i3 & i5) ^ i4;
        int i7 = i ^ i6;
        this.f1167X1 = i2 ^ ((i4 | i5) & i7);
        int i8 = this.f1167X1 | i6;
        this.f1169X3 = (i & i7) ^ i5;
        int i9 = i7 ^ i8;
        int i10 = this.f1169X3;
        this.f1168X2 = i6 ^ (i10 & i9);
        this.f1166X0 = (~i9) ^ (i10 & this.f1168X2);
    }

    private void ib7(int i, int i2, int i3, int i4) {
        int i5 = (i & i2) | i3;
        int i6 = (i | i2) & i4;
        this.f1169X3 = i5 ^ i6;
        int i7 = ~i4;
        int i8 = i2 ^ i6;
        int i9 = this.f1169X3;
        this.f1167X1 = ((i7 ^ i9) | i8) ^ i;
        int i10 = i8 ^ i3;
        int i11 = this.f1167X1;
        this.f1166X0 = i10 ^ (i4 | i11);
        this.f1168X2 = ((i & i9) ^ this.f1166X0) ^ (i5 ^ i11);
    }

    /* renamed from: LT */
    private void m410LT() {
        int rotateLeft = rotateLeft(this.f1166X0, 13);
        int rotateLeft2 = rotateLeft(this.f1168X2, 3);
        int i = (this.f1169X3 ^ rotateLeft2) ^ (rotateLeft << 3);
        this.f1167X1 = rotateLeft((this.f1167X1 ^ rotateLeft) ^ rotateLeft2, 1);
        this.f1169X3 = rotateLeft(i, 7);
        this.f1166X0 = rotateLeft((rotateLeft ^ this.f1167X1) ^ this.f1169X3, 5);
        this.f1168X2 = rotateLeft((this.f1169X3 ^ rotateLeft2) ^ (this.f1167X1 << 7), 22);
    }

    private void inverseLT() {
        int rotateRight = (rotateRight(this.f1168X2, 22) ^ this.f1169X3) ^ (this.f1167X1 << 7);
        int rotateRight2 = rotateRight(this.f1166X0, 5) ^ this.f1167X1;
        int i = this.f1169X3;
        int i2 = rotateRight2 ^ i;
        int rotateRight3 = rotateRight(i, 7);
        int rotateRight4 = rotateRight(this.f1167X1, 1);
        this.f1169X3 = (rotateRight3 ^ rotateRight) ^ (i2 << 3);
        this.f1167X1 = (rotateRight4 ^ i2) ^ rotateRight;
        this.f1168X2 = rotateRight(rotateRight, 3);
        this.f1166X0 = rotateRight(i2, 13);
    }
}
