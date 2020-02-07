package org.spongycastle.crypto.engines;

import androidx.core.app.FrameMetricsAggregator;
import androidx.recyclerview.widget.ItemTouchHelper.Callback;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.lang.reflect.Array;
import org.bitcoinj.core.TransactionInput;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.crypto.tls.CipherSuite;

public class RijndaelEngine implements BlockCipher {
    private static final int MAXKC = 64;
    private static final int MAXROUNDS = 14;

    /* renamed from: S */
    private static final byte[] f1157S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, Framer.STDOUT_FRAME_PREFIX, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, Framer.ENTER_FRAME_PREFIX, 16, -1, -13, -46, -51, Ascii.f521FF, 19, -20, Framer.STDIN_REQUEST_FRAME_PREFIX, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.f520EM, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.f532VT, -37, -32, Framer.STDERR_FRAME_PREFIX, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, Framer.EXIT_FRAME_PREFIX, 37, 46, Ascii.f522FS, -90, -76, -58, -24, -35, 116, Ascii.f531US, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, Ascii.f529SO, 97, 53, 87, -71, -122, -63, Ascii.f523GS, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.f527RS, -121, -23, -50, 85, 40, -33, -116, -95, -119, Ascii.f519CR, -65, -26, 66, 104, 65, -103, Framer.STDIN_FRAME_PREFIX, Ascii.f528SI, -80, 84, -69, Ascii.SYN};

    /* renamed from: Si */
    private static final byte[] f1158Si = {82, 9, 106, -43, 48, 54, -91, 56, -65, SignedBytes.MAX_POWER_OF_TWO, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, Framer.STDERR_FRAME_PREFIX, -90, -62, 35, 61, -18, 76, -107, Ascii.f532VT, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, Ascii.NAK, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, Ascii.f527RS, -113, -54, 63, Ascii.f528SI, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, Ascii.f522FS, 117, -33, 110, 71, -15, Ascii.SUB, 113, Ascii.f523GS, 41, -59, -119, 111, -73, 98, Ascii.f529SO, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, Framer.EXIT_FRAME_PREFIX, -51, 90, -12, Ascii.f531US, -35, -88, 51, -120, 7, -57, Framer.STDOUT_FRAME_PREFIX, -79, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, Framer.STDIN_REQUEST_FRAME_PREFIX, 96, 81, Byte.MAX_VALUE, -87, Ascii.f520EM, -75, 74, Ascii.f519CR, Framer.STDIN_FRAME_PREFIX, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, -42, 38, -31, 105, Ascii.DC4, 99, 85, Framer.ENTER_FRAME_PREFIX, Ascii.f521FF, 125};
    private static final byte[] aLogtable;
    private static final byte[] logtable = {0, 0, Ascii.f520EM, 1, Framer.STDERR_FRAME_PREFIX, 2, Ascii.SUB, -58, 75, -57, Ascii.ESC, 104, 51, -18, -33, 3, 100, 4, -32, Ascii.f529SO, 52, -115, -127, -17, 76, 113, 8, -56, -8, 105, Ascii.f522FS, -63, 125, -62, Ascii.f523GS, -75, -7, -71, 39, 106, 77, -28, -90, 114, -102, -55, 9, Framer.EXIT_FRAME_PREFIX, 101, 47, -118, 5, Framer.ENTER_FRAME_PREFIX, Ascii.f528SI, -31, 36, Ascii.DC2, -16, -126, 69, 53, -109, -38, -114, -106, -113, -37, -67, 54, -48, -50, -108, 19, 92, -46, -15, SignedBytes.MAX_POWER_OF_TWO, 70, -125, 56, 102, -35, -3, 48, -65, 6, -117, 98, -77, 37, -30, -104, 34, -120, -111, 16, 126, 110, 72, -61, -93, -74, Ascii.f527RS, 66, 58, 107, 40, 84, -6, -123, 61, -70, 43, 121, 10, Ascii.NAK, -101, -97, 94, -54, 78, -44, -84, -27, -13, 115, -89, 87, -81, 88, -88, 80, -12, -22, -42, 116, 79, -82, -23, -43, -25, -26, -83, -24, 44, -41, 117, 122, -21, Ascii.SYN, Ascii.f532VT, -11, 89, -53, Framer.STDIN_REQUEST_FRAME_PREFIX, -80, -100, -87, 81, -96, Byte.MAX_VALUE, Ascii.f521FF, -10, 111, Ascii.ETB, -60, 73, -20, -40, 67, Ascii.f531US, Framer.STDIN_FRAME_PREFIX, -92, 118, 123, -73, -52, -69, 62, 90, -5, 96, -79, -122, 59, 82, -95, 108, -86, 85, 41, -99, -105, -78, -121, -112, 97, -66, -36, -4, PSSSigner.TRAILER_IMPLICIT, -107, -49, -51, 55, 63, 91, -47, 83, 57, -124, 60, 65, -94, 109, 71, Ascii.DC4, 42, -98, 93, 86, -14, -45, -85, 68, 17, -110, -39, 35, 32, 46, -119, -76, 124, -72, 38, 119, -103, -29, -91, 103, 74, -19, -34, -59, Framer.STDOUT_FRAME_PREFIX, -2, Ascii.CAN, Ascii.f519CR, 99, -116, Byte.MIN_VALUE, -64, -9, 112, 7};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 145};
    static byte[][] shifts0 = {new byte[]{0, 8, 16, Ascii.CAN}, new byte[]{0, 8, 16, Ascii.CAN}, new byte[]{0, 8, 16, Ascii.CAN}, new byte[]{0, 8, 16, 32}, new byte[]{0, 8, Ascii.CAN, 32}};
    static byte[][] shifts1 = {new byte[]{0, Ascii.CAN, 16, 8}, new byte[]{0, 32, Ascii.CAN, 16}, new byte[]{0, 40, 32, Ascii.CAN}, new byte[]{0, 48, 40, Ascii.CAN}, new byte[]{0, 56, 40, 32}};

    /* renamed from: A0 */
    private long f1159A0;

    /* renamed from: A1 */
    private long f1160A1;

    /* renamed from: A2 */
    private long f1161A2;

    /* renamed from: A3 */
    private long f1162A3;

    /* renamed from: BC */
    private int f1163BC;
    private long BC_MASK;
    private int ROUNDS;
    private int blockBits;
    private boolean forEncryption;
    private byte[] shifts0SC;
    private byte[] shifts1SC;
    private long[][] workingKey;

    public String getAlgorithmName() {
        return "Rijndael";
    }

    public void reset() {
    }

    static {
        byte[] bArr = new byte[FrameMetricsAggregator.EVERY_DURATION];
        // fill-array-data instruction
        bArr[0] = 0;
        bArr[1] = 3;
        bArr[2] = 5;
        bArr[3] = 15;
        bArr[4] = 17;
        bArr[5] = 51;
        bArr[6] = 85;
        bArr[7] = -1;
        bArr[8] = 26;
        bArr[9] = 46;
        bArr[10] = 114;
        bArr[11] = -106;
        bArr[12] = -95;
        bArr[13] = -8;
        bArr[14] = 19;
        bArr[15] = 53;
        bArr[16] = 95;
        bArr[17] = -31;
        bArr[18] = 56;
        bArr[19] = 72;
        bArr[20] = -40;
        bArr[21] = 115;
        bArr[22] = -107;
        bArr[23] = -92;
        bArr[24] = -9;
        bArr[25] = 2;
        bArr[26] = 6;
        bArr[27] = 10;
        bArr[28] = 30;
        bArr[29] = 34;
        bArr[30] = 102;
        bArr[31] = -86;
        bArr[32] = -27;
        bArr[33] = 52;
        bArr[34] = 92;
        bArr[35] = -28;
        bArr[36] = 55;
        bArr[37] = 89;
        bArr[38] = -21;
        bArr[39] = 38;
        bArr[40] = 106;
        bArr[41] = -66;
        bArr[42] = -39;
        bArr[43] = 112;
        bArr[44] = -112;
        bArr[45] = -85;
        bArr[46] = -26;
        bArr[47] = 49;
        bArr[48] = 83;
        bArr[49] = -11;
        bArr[50] = 4;
        bArr[51] = 12;
        bArr[52] = 20;
        bArr[53] = 60;
        bArr[54] = 68;
        bArr[55] = -52;
        bArr[56] = 79;
        bArr[57] = -47;
        bArr[58] = 104;
        bArr[59] = -72;
        bArr[60] = -45;
        bArr[61] = 110;
        bArr[62] = -78;
        bArr[63] = -51;
        bArr[64] = 76;
        bArr[65] = -44;
        bArr[66] = 103;
        bArr[67] = -87;
        bArr[68] = -32;
        bArr[69] = 59;
        bArr[70] = 77;
        bArr[71] = -41;
        bArr[72] = 98;
        bArr[73] = -90;
        bArr[74] = -15;
        bArr[75] = 8;
        bArr[76] = 24;
        bArr[77] = 40;
        bArr[78] = 120;
        bArr[79] = -120;
        bArr[80] = -125;
        bArr[81] = -98;
        bArr[82] = -71;
        bArr[83] = -48;
        bArr[84] = 107;
        bArr[85] = -67;
        bArr[86] = -36;
        bArr[87] = 127;
        bArr[88] = -127;
        bArr[89] = -104;
        bArr[90] = -77;
        bArr[91] = -50;
        bArr[92] = 73;
        bArr[93] = -37;
        bArr[94] = 118;
        bArr[95] = -102;
        bArr[96] = -75;
        bArr[97] = -60;
        bArr[98] = 87;
        bArr[99] = -7;
        bArr[100] = 16;
        bArr[101] = 48;
        bArr[102] = 80;
        bArr[103] = -16;
        bArr[104] = 11;
        bArr[105] = 29;
        bArr[106] = 39;
        bArr[107] = 105;
        bArr[108] = -69;
        bArr[109] = -42;
        bArr[110] = 97;
        bArr[111] = -93;
        bArr[112] = -2;
        bArr[113] = 25;
        bArr[114] = 43;
        bArr[115] = 125;
        bArr[116] = -121;
        bArr[117] = -110;
        bArr[118] = -83;
        bArr[119] = -20;
        bArr[120] = 47;
        bArr[121] = 113;
        bArr[122] = -109;
        bArr[123] = -82;
        bArr[124] = -23;
        bArr[125] = 32;
        bArr[126] = 96;
        bArr[127] = -96;
        bArr[128] = -5;
        bArr[129] = 22;
        bArr[130] = 58;
        bArr[131] = 78;
        bArr[132] = -46;
        bArr[133] = 109;
        bArr[134] = -73;
        bArr[135] = -62;
        bArr[136] = 93;
        bArr[137] = -25;
        bArr[138] = 50;
        bArr[139] = 86;
        bArr[140] = -6;
        bArr[141] = 21;
        bArr[142] = 63;
        bArr[143] = 65;
        bArr[144] = -61;
        bArr[145] = 94;
        bArr[146] = -30;
        bArr[147] = 61;
        bArr[148] = 71;
        bArr[149] = -55;
        bArr[150] = 64;
        bArr[151] = -64;
        bArr[152] = 91;
        bArr[153] = -19;
        bArr[154] = 44;
        bArr[155] = 116;
        bArr[156] = -100;
        bArr[157] = -65;
        bArr[158] = -38;
        bArr[159] = 117;
        bArr[160] = -97;
        bArr[161] = -70;
        bArr[162] = -43;
        bArr[163] = 100;
        bArr[164] = -84;
        bArr[165] = -17;
        bArr[166] = 42;
        bArr[167] = 126;
        bArr[168] = -126;
        bArr[169] = -99;
        bArr[170] = -68;
        bArr[171] = -33;
        bArr[172] = 122;
        bArr[173] = -114;
        bArr[174] = -119;
        bArr[175] = -128;
        bArr[176] = -101;
        bArr[177] = -74;
        bArr[178] = -63;
        bArr[179] = 88;
        bArr[180] = -24;
        bArr[181] = 35;
        bArr[182] = 101;
        bArr[183] = -81;
        bArr[184] = -22;
        bArr[185] = 37;
        bArr[186] = 111;
        bArr[187] = -79;
        bArr[188] = -56;
        bArr[189] = 67;
        bArr[190] = -59;
        bArr[191] = 84;
        bArr[192] = -4;
        bArr[193] = 31;
        bArr[194] = 33;
        bArr[195] = 99;
        bArr[196] = -91;
        bArr[197] = -12;
        bArr[198] = 7;
        bArr[199] = 9;
        bArr[200] = 27;
        bArr[201] = 45;
        bArr[202] = 119;
        bArr[203] = -103;
        bArr[204] = -80;
        bArr[205] = -53;
        bArr[206] = 70;
        bArr[207] = -54;
        bArr[208] = 69;
        bArr[209] = -49;
        bArr[210] = 74;
        bArr[211] = -34;
        bArr[212] = 121;
        bArr[213] = -117;
        bArr[214] = -122;
        bArr[215] = -111;
        bArr[216] = -88;
        bArr[217] = -29;
        bArr[218] = 62;
        bArr[219] = 66;
        bArr[220] = -58;
        bArr[221] = 81;
        bArr[222] = -13;
        bArr[223] = 14;
        bArr[224] = 18;
        bArr[225] = 54;
        bArr[226] = 90;
        bArr[227] = -18;
        bArr[228] = 41;
        bArr[229] = 123;
        bArr[230] = -115;
        bArr[231] = -116;
        bArr[232] = -113;
        bArr[233] = -118;
        bArr[234] = -123;
        bArr[235] = -108;
        bArr[236] = -89;
        bArr[237] = -14;
        bArr[238] = 13;
        bArr[239] = 23;
        bArr[240] = 57;
        bArr[241] = 75;
        bArr[242] = -35;
        bArr[243] = 124;
        bArr[244] = -124;
        bArr[245] = -105;
        bArr[246] = -94;
        bArr[247] = -3;
        bArr[248] = 28;
        bArr[249] = 36;
        bArr[250] = 108;
        bArr[251] = -76;
        bArr[252] = -57;
        bArr[253] = 82;
        bArr[254] = -10;
        bArr[255] = 1;
        bArr[256] = 3;
        bArr[257] = 5;
        bArr[258] = 15;
        bArr[259] = 17;
        bArr[260] = 51;
        bArr[261] = 85;
        bArr[262] = -1;
        bArr[263] = 26;
        bArr[264] = 46;
        bArr[265] = 114;
        bArr[266] = -106;
        bArr[267] = -95;
        bArr[268] = -8;
        bArr[269] = 19;
        bArr[270] = 53;
        bArr[271] = 95;
        bArr[272] = -31;
        bArr[273] = 56;
        bArr[274] = 72;
        bArr[275] = -40;
        bArr[276] = 115;
        bArr[277] = -107;
        bArr[278] = -92;
        bArr[279] = -9;
        bArr[280] = 2;
        bArr[281] = 6;
        bArr[282] = 10;
        bArr[283] = 30;
        bArr[284] = 34;
        bArr[285] = 102;
        bArr[286] = -86;
        bArr[287] = -27;
        bArr[288] = 52;
        bArr[289] = 92;
        bArr[290] = -28;
        bArr[291] = 55;
        bArr[292] = 89;
        bArr[293] = -21;
        bArr[294] = 38;
        bArr[295] = 106;
        bArr[296] = -66;
        bArr[297] = -39;
        bArr[298] = 112;
        bArr[299] = -112;
        bArr[300] = -85;
        bArr[301] = -26;
        bArr[302] = 49;
        bArr[303] = 83;
        bArr[304] = -11;
        bArr[305] = 4;
        bArr[306] = 12;
        bArr[307] = 20;
        bArr[308] = 60;
        bArr[309] = 68;
        bArr[310] = -52;
        bArr[311] = 79;
        bArr[312] = -47;
        bArr[313] = 104;
        bArr[314] = -72;
        bArr[315] = -45;
        bArr[316] = 110;
        bArr[317] = -78;
        bArr[318] = -51;
        bArr[319] = 76;
        bArr[320] = -44;
        bArr[321] = 103;
        bArr[322] = -87;
        bArr[323] = -32;
        bArr[324] = 59;
        bArr[325] = 77;
        bArr[326] = -41;
        bArr[327] = 98;
        bArr[328] = -90;
        bArr[329] = -15;
        bArr[330] = 8;
        bArr[331] = 24;
        bArr[332] = 40;
        bArr[333] = 120;
        bArr[334] = -120;
        bArr[335] = -125;
        bArr[336] = -98;
        bArr[337] = -71;
        bArr[338] = -48;
        bArr[339] = 107;
        bArr[340] = -67;
        bArr[341] = -36;
        bArr[342] = 127;
        bArr[343] = -127;
        bArr[344] = -104;
        bArr[345] = -77;
        bArr[346] = -50;
        bArr[347] = 73;
        bArr[348] = -37;
        bArr[349] = 118;
        bArr[350] = -102;
        bArr[351] = -75;
        bArr[352] = -60;
        bArr[353] = 87;
        bArr[354] = -7;
        bArr[355] = 16;
        bArr[356] = 48;
        bArr[357] = 80;
        bArr[358] = -16;
        bArr[359] = 11;
        bArr[360] = 29;
        bArr[361] = 39;
        bArr[362] = 105;
        bArr[363] = -69;
        bArr[364] = -42;
        bArr[365] = 97;
        bArr[366] = -93;
        bArr[367] = -2;
        bArr[368] = 25;
        bArr[369] = 43;
        bArr[370] = 125;
        bArr[371] = -121;
        bArr[372] = -110;
        bArr[373] = -83;
        bArr[374] = -20;
        bArr[375] = 47;
        bArr[376] = 113;
        bArr[377] = -109;
        bArr[378] = -82;
        bArr[379] = -23;
        bArr[380] = 32;
        bArr[381] = 96;
        bArr[382] = -96;
        bArr[383] = -5;
        bArr[384] = 22;
        bArr[385] = 58;
        bArr[386] = 78;
        bArr[387] = -46;
        bArr[388] = 109;
        bArr[389] = -73;
        bArr[390] = -62;
        bArr[391] = 93;
        bArr[392] = -25;
        bArr[393] = 50;
        bArr[394] = 86;
        bArr[395] = -6;
        bArr[396] = 21;
        bArr[397] = 63;
        bArr[398] = 65;
        bArr[399] = -61;
        bArr[400] = 94;
        bArr[401] = -30;
        bArr[402] = 61;
        bArr[403] = 71;
        bArr[404] = -55;
        bArr[405] = 64;
        bArr[406] = -64;
        bArr[407] = 91;
        bArr[408] = -19;
        bArr[409] = 44;
        bArr[410] = 116;
        bArr[411] = -100;
        bArr[412] = -65;
        bArr[413] = -38;
        bArr[414] = 117;
        bArr[415] = -97;
        bArr[416] = -70;
        bArr[417] = -43;
        bArr[418] = 100;
        bArr[419] = -84;
        bArr[420] = -17;
        bArr[421] = 42;
        bArr[422] = 126;
        bArr[423] = -126;
        bArr[424] = -99;
        bArr[425] = -68;
        bArr[426] = -33;
        bArr[427] = 122;
        bArr[428] = -114;
        bArr[429] = -119;
        bArr[430] = -128;
        bArr[431] = -101;
        bArr[432] = -74;
        bArr[433] = -63;
        bArr[434] = 88;
        bArr[435] = -24;
        bArr[436] = 35;
        bArr[437] = 101;
        bArr[438] = -81;
        bArr[439] = -22;
        bArr[440] = 37;
        bArr[441] = 111;
        bArr[442] = -79;
        bArr[443] = -56;
        bArr[444] = 67;
        bArr[445] = -59;
        bArr[446] = 84;
        bArr[447] = -4;
        bArr[448] = 31;
        bArr[449] = 33;
        bArr[450] = 99;
        bArr[451] = -91;
        bArr[452] = -12;
        bArr[453] = 7;
        bArr[454] = 9;
        bArr[455] = 27;
        bArr[456] = 45;
        bArr[457] = 119;
        bArr[458] = -103;
        bArr[459] = -80;
        bArr[460] = -53;
        bArr[461] = 70;
        bArr[462] = -54;
        bArr[463] = 69;
        bArr[464] = -49;
        bArr[465] = 74;
        bArr[466] = -34;
        bArr[467] = 121;
        bArr[468] = -117;
        bArr[469] = -122;
        bArr[470] = -111;
        bArr[471] = -88;
        bArr[472] = -29;
        bArr[473] = 62;
        bArr[474] = 66;
        bArr[475] = -58;
        bArr[476] = 81;
        bArr[477] = -13;
        bArr[478] = 14;
        bArr[479] = 18;
        bArr[480] = 54;
        bArr[481] = 90;
        bArr[482] = -18;
        bArr[483] = 41;
        bArr[484] = 123;
        bArr[485] = -115;
        bArr[486] = -116;
        bArr[487] = -113;
        bArr[488] = -118;
        bArr[489] = -123;
        bArr[490] = -108;
        bArr[491] = -89;
        bArr[492] = -14;
        bArr[493] = 13;
        bArr[494] = 23;
        bArr[495] = 57;
        bArr[496] = 75;
        bArr[497] = -35;
        bArr[498] = 124;
        bArr[499] = -124;
        bArr[500] = -105;
        bArr[501] = -94;
        bArr[502] = -3;
        bArr[503] = 28;
        bArr[504] = 36;
        bArr[505] = 108;
        bArr[506] = -76;
        bArr[507] = -57;
        bArr[508] = 82;
        bArr[509] = -10;
        bArr[510] = 1;
        aLogtable = bArr;
    }

    private byte mul0x2(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & 255) + Ascii.f520EM];
        }
        return 0;
    }

    private byte mul0x3(int i) {
        if (i != 0) {
            return aLogtable[(logtable[i] & 255) + 1];
        }
        return 0;
    }

    private byte mul0x9(int i) {
        if (i >= 0) {
            return aLogtable[i + 199];
        }
        return 0;
    }

    private byte mul0xb(int i) {
        if (i >= 0) {
            return aLogtable[i + 104];
        }
        return 0;
    }

    private byte mul0xd(int i) {
        if (i >= 0) {
            return aLogtable[i + 238];
        }
        return 0;
    }

    private byte mul0xe(int i) {
        if (i >= 0) {
            return aLogtable[i + 223];
        }
        return 0;
    }

    private void KeyAddition(long[] jArr) {
        this.f1159A0 ^= jArr[0];
        this.f1160A1 ^= jArr[1];
        this.f1161A2 ^= jArr[2];
        this.f1162A3 ^= jArr[3];
    }

    private long shift(long j, int i) {
        return ((j << (this.f1163BC - i)) | (j >>> i)) & this.BC_MASK;
    }

    private void ShiftRow(byte[] bArr) {
        this.f1160A1 = shift(this.f1160A1, bArr[1]);
        this.f1161A2 = shift(this.f1161A2, bArr[2]);
        this.f1162A3 = shift(this.f1162A3, bArr[3]);
    }

    private long applyS(long j, byte[] bArr) {
        long j2 = 0;
        for (int i = 0; i < this.f1163BC; i += 8) {
            j2 |= ((long) (bArr[(int) ((j >> i) & 255)] & 255)) << i;
        }
        return j2;
    }

    private void Substitution(byte[] bArr) {
        this.f1159A0 = applyS(this.f1159A0, bArr);
        this.f1160A1 = applyS(this.f1160A1, bArr);
        this.f1161A2 = applyS(this.f1161A2, bArr);
        this.f1162A3 = applyS(this.f1162A3, bArr);
    }

    private void MixColumn() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.f1163BC; i += 8) {
            int i2 = (int) ((this.f1159A0 >> i) & 255);
            int i3 = (int) ((this.f1160A1 >> i) & 255);
            int i4 = (int) ((this.f1161A2 >> i) & 255);
            int i5 = (int) ((this.f1162A3 >> i) & 255);
            j |= ((long) ((((mul0x2(i2) ^ mul0x3(i3)) ^ i4) ^ i5) & 255)) << i;
            j2 |= ((long) ((((mul0x2(i3) ^ mul0x3(i4)) ^ i5) ^ i2) & 255)) << i;
            j3 |= ((long) ((((mul0x2(i4) ^ mul0x3(i5)) ^ i2) ^ i3) & 255)) << i;
            j4 |= ((long) ((((mul0x2(i5) ^ mul0x3(i2)) ^ i3) ^ i4) & 255)) << i;
        }
        long j5 = j4;
        this.f1159A0 = j;
        this.f1160A1 = j2;
        this.f1161A2 = j3;
        this.f1162A3 = j5;
    }

    private void InvMixColumn() {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        for (int i = 0; i < this.f1163BC; i += 8) {
            int i2 = (int) ((this.f1159A0 >> i) & 255);
            int i3 = (int) ((this.f1160A1 >> i) & 255);
            int i4 = (int) ((this.f1161A2 >> i) & 255);
            long j5 = j4;
            int i5 = (int) ((this.f1162A3 >> i) & 255);
            byte b = -1;
            byte b2 = i2 != 0 ? logtable[i2 & 255] & 255 : -1;
            byte b3 = i3 != 0 ? logtable[i3 & 255] & 255 : -1;
            byte b4 = i4 != 0 ? logtable[i4 & 255] & 255 : -1;
            if (i5 != 0) {
                b = logtable[i5 & 255] & 255;
            }
            j |= ((long) ((((mul0xe(b2) ^ mul0xb(b3)) ^ mul0xd(b4)) ^ mul0x9(b)) & 255)) << i;
            j2 |= ((long) ((((mul0xe(b3) ^ mul0xb(b4)) ^ mul0xd(b)) ^ mul0x9(b2)) & 255)) << i;
            j3 |= ((long) ((((mul0xe(b4) ^ mul0xb(b)) ^ mul0xd(b2)) ^ mul0x9(b3)) & 255)) << i;
            j4 = j5 | (((long) ((((mul0xe(b) ^ mul0xb(b2)) ^ mul0xd(b3)) ^ mul0x9(b4)) & 255)) << i);
        }
        long j6 = j4;
        this.f1159A0 = j;
        this.f1160A1 = j2;
        this.f1161A2 = j3;
        this.f1162A3 = j6;
    }

    private long[][] generateWorkingKey(byte[] bArr) {
        int i;
        int i2;
        int i3;
        byte[] bArr2 = bArr;
        int length = bArr2.length * 8;
        int i4 = 4;
        byte[][] bArr3 = (byte[][]) Array.newInstance(byte.class, new int[]{4, 64});
        long[][] jArr = (long[][]) Array.newInstance(long.class, new int[]{15, 4});
        if (length == 128) {
            i = 4;
        } else if (length == 160) {
            i = 5;
        } else if (length == 192) {
            i = 6;
        } else if (length == 224) {
            i = 7;
        } else if (length == 256) {
            i = 8;
        } else {
            throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
        }
        if (length >= this.blockBits) {
            this.ROUNDS = i + 6;
        } else {
            this.ROUNDS = (this.f1163BC / 8) + 6;
        }
        char c = 0;
        int i5 = 0;
        int i6 = 0;
        while (i5 < bArr2.length) {
            int i7 = i6 + 1;
            bArr3[i5 % 4][i5 / 4] = bArr2[i6];
            i5++;
            i6 = i7;
        }
        int i8 = 0;
        int i9 = 0;
        while (true) {
            i2 = 1;
            if (i8 >= i || i9 >= (this.ROUNDS + 1) * (this.f1163BC / 8)) {
                byte[][] bArr4 = bArr3;
                int i10 = 0;
            } else {
                int i11 = 0;
                while (i11 < i4) {
                    int i12 = this.f1163BC;
                    long[] jArr2 = jArr[i9 / (i12 / 8)];
                    byte[][] bArr5 = bArr3;
                    jArr2[i11] = (((long) (bArr3[i11][i8] & 255)) << ((i9 * 8) % i12)) | jArr2[i11];
                    i11++;
                    bArr3 = bArr5;
                    i4 = 4;
                }
                byte[][] bArr6 = bArr3;
                i8++;
                i9++;
                i4 = 4;
            }
        }
        byte[][] bArr42 = bArr3;
        int i102 = 0;
        while (i9 < (this.ROUNDS + i2) * (this.f1163BC / 8)) {
            int i13 = 0;
            while (i13 < 4) {
                byte[] bArr7 = bArr42[i13];
                i13++;
                bArr7[c] = (byte) (bArr7[c] ^ f1157S[bArr42[i13 % 4][i - 1] & 255]);
            }
            byte[] bArr8 = bArr42[c];
            int i14 = i102 + 1;
            bArr8[c] = (byte) (rcon[i102] ^ bArr8[c]);
            if (i <= 6) {
                for (int i15 = 1; i15 < i; i15++) {
                    for (int i16 = 0; i16 < 4; i16++) {
                        byte[] bArr9 = bArr42[i16];
                        bArr9[i15] = (byte) (bArr9[i15] ^ bArr42[i16][i15 - 1]);
                    }
                }
            } else {
                int i17 = 1;
                while (true) {
                    i3 = 4;
                    if (i17 >= 4) {
                        break;
                    }
                    int i18 = 0;
                    while (i18 < i3) {
                        byte[] bArr10 = bArr42[i18];
                        bArr10[i17] = (byte) (bArr10[i17] ^ bArr42[i18][i17 - 1]);
                        i18++;
                        i3 = 4;
                    }
                    i17++;
                }
                for (int i19 = 0; i19 < 4; i19++) {
                    byte[] bArr11 = bArr42[i19];
                    bArr11[4] = (byte) (bArr11[4] ^ f1157S[bArr42[i19][3] & 255]);
                }
                int i20 = 5;
                while (i20 < i) {
                    int i21 = 0;
                    while (i21 < i3) {
                        byte[] bArr12 = bArr42[i21];
                        bArr12[i20] = (byte) (bArr12[i20] ^ bArr42[i21][i20 - 1]);
                        i21++;
                        i3 = 4;
                    }
                    i20++;
                    i3 = 4;
                }
            }
            int i22 = 0;
            while (i22 < i && i9 < (this.ROUNDS + i2) * (this.f1163BC / 8)) {
                int i23 = 0;
                while (i23 < 4) {
                    int i24 = this.f1163BC;
                    long[] jArr3 = jArr[i9 / (i24 / 8)];
                    int i25 = i14;
                    jArr3[i23] = (((long) (bArr42[i23][i22] & 255)) << ((i9 * 8) % i24)) | jArr3[i23];
                    i23++;
                    i14 = i25;
                }
                int i26 = i14;
                i22++;
                i9++;
                i2 = 1;
            }
            i102 = i14;
            c = 0;
            i2 = 1;
        }
        return jArr;
    }

    public RijndaelEngine() {
        this(128);
    }

    public RijndaelEngine(int i) {
        if (i == 128) {
            this.f1163BC = 32;
            this.BC_MASK = TransactionInput.NO_SEQUENCE;
            this.shifts0SC = shifts0[0];
            this.shifts1SC = shifts1[0];
        } else if (i == 160) {
            this.f1163BC = 40;
            this.BC_MASK = 1099511627775L;
            this.shifts0SC = shifts0[1];
            this.shifts1SC = shifts1[1];
        } else if (i == 192) {
            this.f1163BC = 48;
            this.BC_MASK = 281474976710655L;
            this.shifts0SC = shifts0[2];
            this.shifts1SC = shifts1[2];
        } else if (i == 224) {
            this.f1163BC = 56;
            this.BC_MASK = 72057594037927935L;
            this.shifts0SC = shifts0[3];
            this.shifts1SC = shifts1[3];
        } else if (i == 256) {
            this.f1163BC = 64;
            this.BC_MASK = -1;
            this.shifts0SC = shifts0[4];
            this.shifts1SC = shifts1[4];
        } else {
            throw new IllegalArgumentException("unknown blocksize to Rijndael");
        }
        this.blockBits = i;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
            this.forEncryption = z;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("invalid parameter passed to Rijndael init - ");
        sb.append(cipherParameters.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public int getBlockSize() {
        return this.f1163BC / 2;
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.workingKey != null) {
            int i3 = this.f1163BC;
            if ((i3 / 2) + i > bArr.length) {
                throw new DataLengthException("input buffer too short");
            } else if ((i3 / 2) + i2 <= bArr2.length) {
                if (this.forEncryption) {
                    unpackBlock(bArr, i);
                    encryptBlock(this.workingKey);
                    packBlock(bArr2, i2);
                } else {
                    unpackBlock(bArr, i);
                    decryptBlock(this.workingKey);
                    packBlock(bArr2, i2);
                }
                return this.f1163BC / 2;
            } else {
                throw new OutputLengthException("output buffer too short");
            }
        } else {
            throw new IllegalStateException("Rijndael engine not initialised");
        }
    }

    private void unpackBlock(byte[] bArr, int i) {
        int i2 = i + 1;
        this.f1159A0 = (long) (bArr[i] & 255);
        int i3 = i2 + 1;
        this.f1160A1 = (long) (bArr[i2] & 255);
        int i4 = i3 + 1;
        this.f1161A2 = (long) (bArr[i3] & 255);
        int i5 = i4 + 1;
        this.f1162A3 = (long) (bArr[i4] & 255);
        for (int i6 = 8; i6 != this.f1163BC; i6 += 8) {
            int i7 = i5 + 1;
            this.f1159A0 |= ((long) (bArr[i5] & 255)) << i6;
            int i8 = i7 + 1;
            this.f1160A1 |= ((long) (bArr[i7] & 255)) << i6;
            int i9 = i8 + 1;
            this.f1161A2 |= ((long) (bArr[i8] & 255)) << i6;
            i5 = i9 + 1;
            this.f1162A3 |= ((long) (bArr[i9] & 255)) << i6;
        }
    }

    private void packBlock(byte[] bArr, int i) {
        for (int i2 = 0; i2 != this.f1163BC; i2 += 8) {
            int i3 = i + 1;
            bArr[i] = (byte) ((int) (this.f1159A0 >> i2));
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((int) (this.f1160A1 >> i2));
            int i5 = i4 + 1;
            bArr[i4] = (byte) ((int) (this.f1161A2 >> i2));
            i = i5 + 1;
            bArr[i5] = (byte) ((int) (this.f1162A3 >> i2));
        }
    }

    private void encryptBlock(long[][] jArr) {
        KeyAddition(jArr[0]);
        for (int i = 1; i < this.ROUNDS; i++) {
            Substitution(f1157S);
            ShiftRow(this.shifts0SC);
            MixColumn();
            KeyAddition(jArr[i]);
        }
        Substitution(f1157S);
        ShiftRow(this.shifts0SC);
        KeyAddition(jArr[this.ROUNDS]);
    }

    private void decryptBlock(long[][] jArr) {
        KeyAddition(jArr[this.ROUNDS]);
        Substitution(f1158Si);
        ShiftRow(this.shifts1SC);
        for (int i = this.ROUNDS - 1; i > 0; i--) {
            KeyAddition(jArr[i]);
            InvMixColumn();
            Substitution(f1158Si);
            ShiftRow(this.shifts1SC);
        }
        KeyAddition(jArr[0]);
    }
}
