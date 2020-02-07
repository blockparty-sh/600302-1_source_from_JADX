package com.leanplum.internal;

import com.facebook.stetho.common.Utf8Charset;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

class HybiParser {
    private static final int BYTE = 255;
    private static final int FIN = 128;
    private static final List<Integer> FRAGMENTED_OPCODES;
    private static final int LENGTH = 127;
    private static final int MASK = 128;
    private static final int MODE_BINARY = 2;
    private static final int MODE_TEXT = 1;
    private static final int OPCODE = 15;
    private static final List<Integer> OPCODES;
    private static final int OP_BINARY = 2;
    private static final int OP_CLOSE = 8;
    private static final int OP_CONTINUATION = 0;
    private static final int OP_PING = 9;
    private static final int OP_PONG = 10;
    private static final int OP_TEXT = 1;
    private static final int RSV1 = 64;
    private static final int RSV2 = 32;
    private static final int RSV3 = 16;
    private ByteArrayOutputStream mBuffer = new ByteArrayOutputStream();
    private WebSocketClient mClient;
    private boolean mClosed = false;
    private boolean mFinal;
    private int mLength;
    private int mLengthSize;
    private byte[] mMask = new byte[0];
    private boolean mMasked;
    private boolean mMasking = true;
    private int mMode;
    private int mOpcode;
    private byte[] mPayload = new byte[0];
    private int mStage;

    static class HappyDataInputStream extends DataInputStream {
        public HappyDataInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public byte[] readBytes(int i) throws IOException {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = read(bArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            }
            if (i2 == i) {
                return bArr;
            }
            throw new IOException(String.format("Read wrong number of bytes. Got: %s, Expected: %s.", new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}));
        }
    }

    static class ProtocolError extends IOException {
        public ProtocolError(String str) {
            super(str);
        }
    }

    static {
        Integer valueOf = Integer.valueOf(0);
        Integer valueOf2 = Integer.valueOf(1);
        Integer valueOf3 = Integer.valueOf(2);
        OPCODES = Arrays.asList(new Integer[]{valueOf, valueOf2, valueOf3, Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10)});
        FRAGMENTED_OPCODES = Arrays.asList(new Integer[]{valueOf, valueOf2, valueOf3});
    }

    public HybiParser(WebSocketClient webSocketClient) {
        this.mClient = webSocketClient;
    }

    private static byte[] mask(byte[] bArr, byte[] bArr2, int i) {
        if (bArr2.length == 0) {
            return bArr;
        }
        for (int i2 = 0; i2 < bArr.length - i; i2++) {
            int i3 = i + i2;
            bArr[i3] = (byte) (bArr[i3] ^ bArr2[i2 % 4]);
        }
        return bArr;
    }

    public void start(HappyDataInputStream happyDataInputStream) throws IOException {
        while (happyDataInputStream.available() != -1) {
            int i = this.mStage;
            if (i == 0) {
                parseOpcode(happyDataInputStream.readByte());
            } else if (i == 1) {
                parseLength(happyDataInputStream.readByte());
            } else if (i == 2) {
                parseExtendedLength(happyDataInputStream.readBytes(this.mLengthSize));
            } else if (i == 3) {
                this.mMask = happyDataInputStream.readBytes(4);
                this.mStage = 4;
            } else if (i == 4) {
                this.mPayload = happyDataInputStream.readBytes(this.mLength);
                emitFrame();
                this.mStage = 0;
            }
        }
        this.mClient.getListener().onDisconnect(0, "EOF");
    }

    private void parseOpcode(byte b) throws ProtocolError {
        boolean z = (b & SignedBytes.MAX_POWER_OF_TWO) == 64;
        boolean z2 = (b & 32) == 32;
        boolean z3 = (b & 16) == 16;
        if (z || z2 || z3) {
            throw new ProtocolError("RSV not zero");
        }
        this.mFinal = (b & 128) == 128;
        this.mOpcode = b & Ascii.f528SI;
        this.mMask = new byte[0];
        this.mPayload = new byte[0];
        if (!OPCODES.contains(Integer.valueOf(this.mOpcode))) {
            throw new ProtocolError("Bad opcode");
        } else if (FRAGMENTED_OPCODES.contains(Integer.valueOf(this.mOpcode)) || this.mFinal) {
            this.mStage = 1;
        } else {
            throw new ProtocolError("Expected non-final packet");
        }
    }

    private void parseLength(byte b) {
        this.mMasked = (b & 128) == 128;
        this.mLength = b & Byte.MAX_VALUE;
        int i = this.mLength;
        if (i < 0 || i > 125) {
            this.mLengthSize = this.mLength == 126 ? 2 : 8;
            this.mStage = 2;
            return;
        }
        this.mStage = this.mMasked ? 3 : 4;
    }

    private void parseExtendedLength(byte[] bArr) throws ProtocolError {
        this.mLength = getInteger(bArr);
        this.mStage = this.mMasked ? 3 : 4;
    }

    public byte[] frame(String str) {
        return frame(str, 1, -1);
    }

    public byte[] frame(byte[] bArr) {
        return frame(bArr, 2, -1);
    }

    private byte[] frame(byte[] bArr, int i, int i2) {
        return frame((Object) bArr, i, i2);
    }

    private byte[] frame(String str, int i, int i2) {
        return frame((Object) str, i, i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x012f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] frame(java.lang.Object r19, int r20, int r21) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r21
            boolean r3 = r0.mClosed
            if (r3 == 0) goto L_0x000c
            r1 = 0
            return r1
        L_0x000c:
            boolean r3 = r1 instanceof java.lang.String
            if (r3 == 0) goto L_0x0017
            java.lang.String r1 = (java.lang.String) r1
            byte[] r1 = r0.decode(r1)
            goto L_0x001b
        L_0x0017:
            byte[] r1 = (byte[]) r1
            byte[] r1 = (byte[]) r1
        L_0x001b:
            r3 = 2
            r4 = 0
            if (r2 <= 0) goto L_0x0021
            r5 = 2
            goto L_0x0022
        L_0x0021:
            r5 = 0
        L_0x0022:
            int r6 = r1.length
            int r6 = r6 + r5
            r7 = 65535(0xffff, float:9.1834E-41)
            r8 = 125(0x7d, float:1.75E-43)
            if (r6 > r8) goto L_0x002d
            r10 = 2
            goto L_0x0033
        L_0x002d:
            if (r6 > r7) goto L_0x0031
            r10 = 4
            goto L_0x0033
        L_0x0031:
            r10 = 10
        L_0x0033:
            boolean r11 = r0.mMasking
            if (r11 == 0) goto L_0x0039
            r11 = 4
            goto L_0x003a
        L_0x0039:
            r11 = 0
        L_0x003a:
            int r11 = r11 + r10
            boolean r12 = r0.mMasking
            if (r12 == 0) goto L_0x0042
            r12 = 128(0x80, float:1.794E-43)
            goto L_0x0043
        L_0x0042:
            r12 = 0
        L_0x0043:
            int r13 = r6 + r11
            byte[] r13 = new byte[r13]
            r14 = r20
            byte r14 = (byte) r14
            r14 = r14 | -128(0xffffffffffffff80, float:NaN)
            byte r14 = (byte) r14
            r13[r4] = r14
            r14 = 1132462080(0x43800000, float:256.0)
            r15 = 3
            r16 = 1
            if (r6 > r8) goto L_0x005f
            r6 = r6 | r12
            byte r6 = (byte) r6
            r13[r16] = r6
        L_0x005a:
            r12 = r5
            r17 = r10
            goto L_0x010c
        L_0x005f:
            if (r6 > r7) goto L_0x0077
            r7 = r12 | 126(0x7e, float:1.77E-43)
            byte r7 = (byte) r7
            r13[r16] = r7
            float r7 = (float) r6
            float r7 = r7 / r14
            double r7 = (double) r7
            double r7 = java.lang.Math.floor(r7)
            int r7 = (int) r7
            byte r7 = (byte) r7
            r13[r3] = r7
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = (byte) r6
            r13[r15] = r6
            goto L_0x005a
        L_0x0077:
            r7 = r12 | 127(0x7f, float:1.78E-43)
            byte r7 = (byte) r7
            r13[r16] = r7
            double r7 = (double) r6
            r12 = r5
            r4 = 4633078116657397760(0x404c000000000000, double:56.0)
            r17 = r10
            r9 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r4 = java.lang.Math.pow(r9, r4)
            double r4 = r7 / r4
            double r4 = java.lang.Math.floor(r4)
            int r4 = (int) r4
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = (byte) r4
            r13[r3] = r4
            r4 = 4631952216750555136(0x4048000000000000, double:48.0)
            double r4 = java.lang.Math.pow(r9, r4)
            double r4 = r7 / r4
            double r4 = java.lang.Math.floor(r4)
            int r4 = (int) r4
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = (byte) r4
            r13[r15] = r4
            r4 = 4630826316843712512(0x4044000000000000, double:40.0)
            double r4 = java.lang.Math.pow(r9, r4)
            double r4 = r7 / r4
            double r4 = java.lang.Math.floor(r4)
            int r4 = (int) r4
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = (byte) r4
            r5 = 4
            r13[r5] = r4
            r4 = 5
            r14 = 4629700416936869888(0x4040000000000000, double:32.0)
            double r14 = java.lang.Math.pow(r9, r14)
            double r14 = r7 / r14
            double r14 = java.lang.Math.floor(r14)
            int r14 = (int) r14
            r14 = r14 & 255(0xff, float:3.57E-43)
            byte r14 = (byte) r14
            r13[r4] = r14
            r4 = 6
            r14 = 4627448617123184640(0x4038000000000000, double:24.0)
            double r14 = java.lang.Math.pow(r9, r14)
            double r14 = r7 / r14
            double r14 = java.lang.Math.floor(r14)
            int r14 = (int) r14
            r14 = r14 & 255(0xff, float:3.57E-43)
            byte r14 = (byte) r14
            r13[r4] = r14
            r4 = 7
            r14 = 4625196817309499392(0x4030000000000000, double:16.0)
            double r14 = java.lang.Math.pow(r9, r14)
            double r14 = r7 / r14
            double r14 = java.lang.Math.floor(r14)
            int r14 = (int) r14
            r14 = r14 & 255(0xff, float:3.57E-43)
            byte r14 = (byte) r14
            r13[r4] = r14
            r4 = 8
            r14 = 4620693217682128896(0x4020000000000000, double:8.0)
            double r9 = java.lang.Math.pow(r9, r14)
            double r7 = r7 / r9
            double r7 = java.lang.Math.floor(r7)
            int r7 = (int) r7
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r7 = (byte) r7
            r13[r4] = r7
            r4 = 9
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r6 = (byte) r6
            r13[r4] = r6
        L_0x010c:
            if (r2 <= 0) goto L_0x0124
            float r4 = (float) r2
            r6 = 1132462080(0x43800000, float:256.0)
            float r4 = r4 / r6
            double r6 = (double) r4
            double r6 = java.lang.Math.floor(r6)
            int r4 = (int) r6
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = (byte) r4
            r13[r11] = r4
            int r4 = r11 + 1
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r13[r4] = r2
        L_0x0124:
            int r2 = r11 + r12
            int r4 = r1.length
            r6 = 0
            java.lang.System.arraycopy(r1, r6, r13, r2, r4)
            boolean r1 = r0.mMasking
            if (r1 == 0) goto L_0x0177
            r1 = 4
            byte[] r1 = new byte[r1]
            double r7 = java.lang.Math.random()
            r9 = 4643211215818981376(0x4070000000000000, double:256.0)
            double r7 = r7 * r9
            double r7 = java.lang.Math.floor(r7)
            int r2 = (int) r7
            byte r2 = (byte) r2
            r1[r6] = r2
            double r6 = java.lang.Math.random()
            double r6 = r6 * r9
            double r6 = java.lang.Math.floor(r6)
            int r2 = (int) r6
            byte r2 = (byte) r2
            r1[r16] = r2
            double r6 = java.lang.Math.random()
            double r6 = r6 * r9
            double r6 = java.lang.Math.floor(r6)
            int r2 = (int) r6
            byte r2 = (byte) r2
            r1[r3] = r2
            double r2 = java.lang.Math.random()
            double r2 = r2 * r9
            double r2 = java.lang.Math.floor(r2)
            int r2 = (int) r2
            byte r2 = (byte) r2
            r3 = 3
            r1[r3] = r2
            int r2 = r1.length
            r3 = r17
            r4 = 0
            java.lang.System.arraycopy(r1, r4, r13, r3, r2)
            mask(r13, r1, r11)
        L_0x0177:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.HybiParser.frame(java.lang.Object, int, int):byte[]");
    }

    public void ping(String str) {
        this.mClient.send(frame(str, 9, -1));
    }

    public void close(int i, String str) {
        if (!this.mClosed) {
            this.mClient.send(frame(str, 8, i));
            this.mClosed = true;
        }
    }

    private void emitFrame() throws IOException {
        int i = 0;
        byte[] mask = mask(this.mPayload, this.mMask, 0);
        int i2 = this.mOpcode;
        if (i2 == 0) {
            if (this.mMode != 0) {
                this.mBuffer.write(mask);
                if (this.mFinal) {
                    byte[] byteArray = this.mBuffer.toByteArray();
                    if (this.mMode == 1) {
                        this.mClient.getListener().onMessage(encode(byteArray));
                    } else {
                        this.mClient.getListener().onMessage(byteArray);
                    }
                    reset();
                    return;
                }
                return;
            }
            throw new ProtocolError("Mode was not set.");
        } else if (i2 == 1) {
            if (this.mFinal) {
                this.mClient.getListener().onMessage(encode(mask));
                return;
            }
            this.mMode = 1;
            this.mBuffer.write(mask);
        } else if (i2 == 2) {
            if (this.mFinal) {
                this.mClient.getListener().onMessage(mask);
                return;
            }
            this.mMode = 2;
            this.mBuffer.write(mask);
        } else if (i2 == 8) {
            if (mask.length >= 2) {
                i = mask[1] + (mask[0] * 256);
            }
            this.mClient.getListener().onDisconnect(i, mask.length > 2 ? encode(slice(mask, 2)) : null);
        } else if (i2 != 9) {
        } else {
            if (mask.length <= 125) {
                this.mClient.sendFrame(frame(mask, 10, -1));
                return;
            }
            throw new ProtocolError("Ping payload too large");
        }
    }

    private void reset() {
        this.mMode = 0;
        this.mBuffer.reset();
    }

    private String encode(byte[] bArr) {
        try {
            return new String(bArr, Utf8Charset.NAME);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] decode(String str) {
        try {
            return str.getBytes(Utf8Charset.NAME);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private int getInteger(byte[] bArr) throws ProtocolError {
        long byteArrayToLong = byteArrayToLong(bArr, 0, bArr.length);
        if (byteArrayToLong >= 0 && byteArrayToLong <= 2147483647L) {
            return (int) byteArrayToLong;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Bad integer: ");
        sb.append(byteArrayToLong);
        throw new ProtocolError(sb.toString());
    }

    private static byte[] copyOfRange(byte[] bArr, int i, int i2) {
        if (i <= i2) {
            int length = bArr.length;
            if (i < 0 || i > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, i, bArr2, 0, min);
            return bArr2;
        }
        throw new IllegalArgumentException();
    }

    private byte[] slice(byte[] bArr, int i) {
        return copyOfRange(bArr, i, bArr.length);
    }

    private static long byteArrayToLong(byte[] bArr, int i, int i2) {
        if (bArr.length >= i2) {
            long j = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                j += (long) ((bArr[i3 + i] & 255) << (((i2 - 1) - i3) * 8));
            }
            return j;
        }
        throw new IllegalArgumentException("length must be less than or equal to b.length");
    }
}
