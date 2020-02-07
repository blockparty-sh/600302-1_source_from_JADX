package org.spongycastle.util.p026io;

import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.util.io.BufferingOutputStream */
public class BufferingOutputStream extends OutputStream {
    private final byte[] buf;
    private int bufOff;
    private final OutputStream other;

    public BufferingOutputStream(OutputStream outputStream) {
        this.other = outputStream;
        this.buf = new byte[4096];
    }

    public BufferingOutputStream(OutputStream outputStream, int i) {
        this.other = outputStream;
        this.buf = new byte[i];
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2;
        byte[] bArr3 = this.buf;
        int length = bArr3.length;
        int i3 = this.bufOff;
        if (i2 < length - i3) {
            System.arraycopy(bArr, i, bArr3, i3, i2);
            this.bufOff += i2;
            return;
        }
        int length2 = bArr3.length - i3;
        System.arraycopy(bArr, i, bArr3, i3, length2);
        this.bufOff += length2;
        flush();
        int i4 = i + length2;
        int i5 = i2 - length2;
        while (true) {
            bArr2 = this.buf;
            if (i5 < bArr2.length) {
                break;
            }
            this.other.write(bArr, i4, bArr2.length);
            byte[] bArr4 = this.buf;
            i4 += bArr4.length;
            i5 -= bArr4.length;
        }
        if (i5 > 0) {
            System.arraycopy(bArr, i4, bArr2, this.bufOff, i5);
            this.bufOff += i5;
        }
    }

    public void write(int i) throws IOException {
        byte[] bArr = this.buf;
        int i2 = this.bufOff;
        this.bufOff = i2 + 1;
        bArr[i2] = (byte) i;
        if (this.bufOff == bArr.length) {
            flush();
        }
    }

    public void flush() throws IOException {
        this.other.write(this.buf, 0, this.bufOff);
        this.bufOff = 0;
        Arrays.fill(this.buf, 0);
    }

    public void close() throws IOException {
        flush();
        this.other.close();
    }
}
