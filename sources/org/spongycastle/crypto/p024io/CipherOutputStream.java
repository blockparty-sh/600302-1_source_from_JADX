package org.spongycastle.crypto.p024io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.modes.AEADBlockCipher;

/* renamed from: org.spongycastle.crypto.io.CipherOutputStream */
public class CipherOutputStream extends FilterOutputStream {
    private AEADBlockCipher aeadBlockCipher;
    private byte[] buf;
    private BufferedBlockCipher bufferedBlockCipher;
    private final byte[] oneByte = new byte[1];
    private StreamCipher streamCipher;

    public CipherOutputStream(OutputStream outputStream, BufferedBlockCipher bufferedBlockCipher2) {
        super(outputStream);
        this.bufferedBlockCipher = bufferedBlockCipher2;
    }

    public CipherOutputStream(OutputStream outputStream, StreamCipher streamCipher2) {
        super(outputStream);
        this.streamCipher = streamCipher2;
    }

    public CipherOutputStream(OutputStream outputStream, AEADBlockCipher aEADBlockCipher) {
        super(outputStream);
        this.aeadBlockCipher = aEADBlockCipher;
    }

    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        byte b = (byte) i;
        bArr[0] = b;
        if (this.streamCipher != null) {
            this.out.write(this.streamCipher.returnByte(b));
        } else {
            write(bArr, 0, 1);
        }
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        ensureCapacity(i2, false);
        BufferedBlockCipher bufferedBlockCipher2 = this.bufferedBlockCipher;
        if (bufferedBlockCipher2 != null) {
            int processBytes = bufferedBlockCipher2.processBytes(bArr, i, i2, this.buf, 0);
            if (processBytes != 0) {
                this.out.write(this.buf, 0, processBytes);
                return;
            }
            return;
        }
        AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
        if (aEADBlockCipher != null) {
            int processBytes2 = aEADBlockCipher.processBytes(bArr, i, i2, this.buf, 0);
            if (processBytes2 != 0) {
                this.out.write(this.buf, 0, processBytes2);
                return;
            }
            return;
        }
        this.streamCipher.processBytes(bArr, i, i2, this.buf, 0);
        this.out.write(this.buf, 0, i2);
    }

    private void ensureCapacity(int i, boolean z) {
        if (z) {
            BufferedBlockCipher bufferedBlockCipher2 = this.bufferedBlockCipher;
            if (bufferedBlockCipher2 != null) {
                i = bufferedBlockCipher2.getOutputSize(i);
            } else {
                AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
                if (aEADBlockCipher != null) {
                    i = aEADBlockCipher.getOutputSize(i);
                }
            }
        } else {
            BufferedBlockCipher bufferedBlockCipher3 = this.bufferedBlockCipher;
            if (bufferedBlockCipher3 != null) {
                i = bufferedBlockCipher3.getUpdateOutputSize(i);
            } else {
                AEADBlockCipher aEADBlockCipher2 = this.aeadBlockCipher;
                if (aEADBlockCipher2 != null) {
                    i = aEADBlockCipher2.getUpdateOutputSize(i);
                }
            }
        }
        byte[] bArr = this.buf;
        if (bArr == null || bArr.length < i) {
            this.buf = new byte[i];
        }
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004e, code lost:
        if (r1 != null) goto L_0x0051;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            r4.ensureCapacity(r0, r1)
            org.spongycastle.crypto.BufferedBlockCipher r1 = r4.bufferedBlockCipher     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            if (r1 == 0) goto L_0x001b
            org.spongycastle.crypto.BufferedBlockCipher r1 = r4.bufferedBlockCipher     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            byte[] r2 = r4.buf     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            int r1 = r1.doFinal(r2, r0)     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            if (r1 == 0) goto L_0x0030
            java.io.OutputStream r2 = r4.out     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            byte[] r3 = r4.buf     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            r2.write(r3, r0, r1)     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            goto L_0x0030
        L_0x001b:
            org.spongycastle.crypto.modes.AEADBlockCipher r1 = r4.aeadBlockCipher     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            if (r1 == 0) goto L_0x0030
            org.spongycastle.crypto.modes.AEADBlockCipher r1 = r4.aeadBlockCipher     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            byte[] r2 = r4.buf     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            int r1 = r1.doFinal(r2, r0)     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            if (r1 == 0) goto L_0x0030
            java.io.OutputStream r2 = r4.out     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            byte[] r3 = r4.buf     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
            r2.write(r3, r0, r1)     // Catch:{ InvalidCipherTextException -> 0x003c, Exception -> 0x0033 }
        L_0x0030:
            r0 = 0
            r1 = r0
            goto L_0x0044
        L_0x0033:
            r0 = move-exception
            org.spongycastle.crypto.io.CipherIOException r1 = new org.spongycastle.crypto.io.CipherIOException
            java.lang.String r2 = "Error closing stream: "
            r1.<init>(r2, r0)
            goto L_0x0044
        L_0x003c:
            r0 = move-exception
            org.spongycastle.crypto.io.InvalidCipherTextIOException r1 = new org.spongycastle.crypto.io.InvalidCipherTextIOException
            java.lang.String r2 = "Error finalising cipher data"
            r1.<init>(r2, r0)
        L_0x0044:
            r4.flush()     // Catch:{ IOException -> 0x004d }
            java.io.OutputStream r0 = r4.out     // Catch:{ IOException -> 0x004d }
            r0.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x0051
        L_0x004d:
            r0 = move-exception
            if (r1 != 0) goto L_0x0051
            goto L_0x0052
        L_0x0051:
            r0 = r1
        L_0x0052:
            if (r0 != 0) goto L_0x0055
            return
        L_0x0055:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.p024io.CipherOutputStream.close():void");
    }
}
