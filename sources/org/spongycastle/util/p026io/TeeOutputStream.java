package org.spongycastle.util.p026io;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: org.spongycastle.util.io.TeeOutputStream */
public class TeeOutputStream extends OutputStream {
    private OutputStream output1;
    private OutputStream output2;

    public TeeOutputStream(OutputStream outputStream, OutputStream outputStream2) {
        this.output1 = outputStream;
        this.output2 = outputStream2;
    }

    public void write(byte[] bArr) throws IOException {
        this.output1.write(bArr);
        this.output2.write(bArr);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.output1.write(bArr, i, i2);
        this.output2.write(bArr, i, i2);
    }

    public void write(int i) throws IOException {
        this.output1.write(i);
        this.output2.write(i);
    }

    public void flush() throws IOException {
        this.output1.flush();
        this.output2.flush();
    }

    public void close() throws IOException {
        this.output1.close();
        this.output2.close();
    }
}
