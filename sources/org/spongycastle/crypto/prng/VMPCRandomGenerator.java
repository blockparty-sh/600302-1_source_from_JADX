package org.spongycastle.crypto.prng;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.util.Pack;

public class VMPCRandomGenerator implements RandomGenerator {

    /* renamed from: P */
    private byte[] f1325P = {-69, 44, 98, Byte.MAX_VALUE, -75, -86, -44, Ascii.f519CR, -127, -2, -78, -126, -53, -96, -95, 8, Ascii.CAN, 113, 86, -24, 73, 2, 16, -60, -34, 53, -91, -20, Byte.MIN_VALUE, Ascii.DC2, -72, 105, -38, 47, 117, -52, -94, 9, 54, 3, 97, Framer.STDIN_FRAME_PREFIX, -3, -32, -35, 5, 67, -112, -83, -56, -31, -81, 87, -101, 76, -40, 81, -82, 80, -123, 60, 10, -28, -13, -100, 38, 35, 83, -55, -125, -105, 70, -79, -103, 100, Framer.STDOUT_FRAME_PREFIX, 119, -43, Ascii.f523GS, -42, Framer.EXIT_FRAME_PREFIX, -67, 94, -80, -118, 34, 56, -8, 104, 43, 42, -59, -45, -9, PSSSigner.TRAILER_IMPLICIT, 111, -33, 4, -27, -107, 62, 37, -122, -90, Ascii.f532VT, -113, -15, 36, Ascii.f529SO, -41, SignedBytes.MAX_POWER_OF_TWO, -77, -49, 126, 6, Ascii.NAK, -102, 77, Ascii.f522FS, -93, -37, Framer.STDERR_FRAME_PREFIX, -110, 88, 17, 39, -12, 89, -48, 78, 106, Ascii.ETB, 91, -84, -1, 7, -64, 101, 121, -4, -57, -51, 118, 66, 93, -25, 58, 52, 122, 48, 40, Ascii.f528SI, 115, 1, -7, -47, -46, Ascii.f520EM, -23, -111, -71, 90, -19, 65, 109, -76, -61, -98, -65, 99, -6, Ascii.f531US, 51, 96, 71, -119, -16, -106, Ascii.SUB, Framer.STDIN_REQUEST_FRAME_PREFIX, -109, 61, 55, 75, -39, -88, -63, Ascii.ESC, -10, 57, -117, -73, Ascii.f521FF, 32, -50, -120, 110, -74, 116, -114, -115, Ascii.SYN, 41, -14, -121, -11, -21, 112, -29, -5, 85, -97, -58, 68, 74, 69, 125, -30, 107, 92, 108, 102, -87, -116, -18, -124, 19, -89, Ascii.f527RS, -99, -36, 103, 72, -70, 46, -26, -92, -85, 124, -108, 0, Framer.ENTER_FRAME_PREFIX, -17, -22, -66, -54, 114, 79, 82, -104, 63, -62, Ascii.DC4, 123, 59, 84};

    /* renamed from: n */
    private byte f1326n = 0;

    /* renamed from: s */
    private byte f1327s = -66;

    public void addSeedMaterial(byte[] bArr) {
        for (byte b : bArr) {
            byte[] bArr2 = this.f1325P;
            byte b2 = this.f1327s;
            byte b3 = this.f1326n;
            this.f1327s = bArr2[(b2 + bArr2[b3 & 255] + b) & 255];
            byte b4 = bArr2[b3 & 255];
            byte b5 = b3 & 255;
            byte b6 = this.f1327s;
            bArr2[b5] = bArr2[b6 & 255];
            bArr2[b6 & 255] = b4;
            this.f1326n = (byte) ((b3 + 1) & 255);
        }
    }

    public void addSeedMaterial(long j) {
        addSeedMaterial(Pack.longToBigEndian(j));
    }

    public void nextBytes(byte[] bArr) {
        nextBytes(bArr, 0, bArr.length);
    }

    public void nextBytes(byte[] bArr, int i, int i2) {
        synchronized (this.f1325P) {
            int i3 = i2 + i;
            while (i != i3) {
                this.f1327s = this.f1325P[(this.f1327s + this.f1325P[this.f1326n & 255]) & 255];
                bArr[i] = this.f1325P[(this.f1325P[this.f1325P[this.f1327s & 255] & 255] + 1) & 255];
                byte b = this.f1325P[this.f1326n & 255];
                this.f1325P[this.f1326n & 255] = this.f1325P[this.f1327s & 255];
                this.f1325P[this.f1327s & 255] = b;
                this.f1326n = (byte) ((this.f1326n + 1) & 255);
                i++;
            }
        }
    }
}
