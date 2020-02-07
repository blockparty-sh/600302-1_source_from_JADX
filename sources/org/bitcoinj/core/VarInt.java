package org.bitcoinj.core;

public class VarInt {
    private final int originallyEncodedSize;
    public final long value;

    public static int sizeOf(long j) {
        if (j < 0) {
            return 9;
        }
        if (j < 253) {
            return 1;
        }
        if (j <= 65535) {
            return 3;
        }
        return j <= TransactionInput.NO_SEQUENCE ? 5 : 9;
    }

    public VarInt(long j) {
        this.value = j;
        this.originallyEncodedSize = getSizeInBytes();
    }

    public VarInt(byte[] bArr, int i) {
        byte b = bArr[i] & 255;
        if (b < 253) {
            this.value = (long) b;
            this.originallyEncodedSize = 1;
        } else if (b == 253) {
            this.value = (long) (((bArr[i + 2] & 255) << 8) | (bArr[i + 1] & 255));
            this.originallyEncodedSize = 3;
        } else if (b == 254) {
            this.value = C3447Utils.readUint32(bArr, i + 1);
            this.originallyEncodedSize = 5;
        } else {
            this.value = C3447Utils.readInt64(bArr, i + 1);
            this.originallyEncodedSize = 9;
        }
    }

    public int getOriginalSizeInBytes() {
        return this.originallyEncodedSize;
    }

    public final int getSizeInBytes() {
        return sizeOf(this.value);
    }

    public byte[] encode() {
        int sizeOf = sizeOf(this.value);
        if (sizeOf == 1) {
            return new byte[]{(byte) ((int) this.value)};
        } else if (sizeOf == 3) {
            long j = this.value;
            return new byte[]{-3, (byte) ((int) j), (byte) ((int) (j >> 8))};
        } else if (sizeOf != 5) {
            byte[] bArr = new byte[9];
            bArr[0] = -1;
            C3447Utils.uint64ToByteArrayLE(this.value, bArr, 1);
            return bArr;
        } else {
            byte[] bArr2 = new byte[5];
            bArr2[0] = -2;
            C3447Utils.uint32ToByteArrayLE(this.value, bArr2, 1);
            return bArr2;
        }
    }
}
