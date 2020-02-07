package org.spongycastle.util.p026io.pem;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import org.spongycastle.util.encoders.Base64;

/* renamed from: org.spongycastle.util.io.pem.PemWriter */
public class PemWriter extends BufferedWriter {
    private static final int LINE_LENGTH = 64;
    private char[] buf = new char[64];
    private final int nlLength;

    public PemWriter(Writer writer) {
        super(writer);
        String property = System.getProperty("line.separator");
        if (property != null) {
            this.nlLength = property.length();
        } else {
            this.nlLength = 2;
        }
    }

    public int getOutputSize(PemObject pemObject) {
        int length = ((pemObject.getType().length() + 10 + this.nlLength) * 2) + 6 + 4;
        if (!pemObject.getHeaders().isEmpty()) {
            for (PemHeader pemHeader : pemObject.getHeaders()) {
                length += pemHeader.getName().length() + 2 + pemHeader.getValue().length() + this.nlLength;
            }
            length += this.nlLength;
        }
        int length2 = ((pemObject.getContent().length + 2) / 3) * 4;
        return length + length2 + ((((length2 + 64) - 1) / 64) * this.nlLength);
    }

    public void writeObject(PemObjectGenerator pemObjectGenerator) throws IOException {
        PemObject generate = pemObjectGenerator.generate();
        writePreEncapsulationBoundary(generate.getType());
        if (!generate.getHeaders().isEmpty()) {
            for (PemHeader pemHeader : generate.getHeaders()) {
                write(pemHeader.getName());
                write(": ");
                write(pemHeader.getValue());
                newLine();
            }
            newLine();
        }
        writeEncoded(generate.getContent());
        writePostEncapsulationBoundary(generate.getType());
    }

    private void writeEncoded(byte[] bArr) throws IOException {
        byte[] encode = Base64.encode(bArr);
        int i = 0;
        while (i < encode.length) {
            int i2 = 0;
            while (true) {
                char[] cArr = this.buf;
                if (i2 == cArr.length) {
                    break;
                }
                int i3 = i + i2;
                if (i3 >= encode.length) {
                    break;
                }
                cArr[i2] = (char) encode[i3];
                i2++;
            }
            write(this.buf, 0, i2);
            newLine();
            i += this.buf.length;
        }
    }

    private void writePreEncapsulationBoundary(String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("-----BEGIN ");
        sb.append(str);
        sb.append("-----");
        write(sb.toString());
        newLine();
    }

    private void writePostEncapsulationBoundary(String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("-----END ");
        sb.append(str);
        sb.append("-----");
        write(sb.toString());
        newLine();
    }
}
