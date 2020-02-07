package org.spongycastle.util.p026io.pem;

import com.microsoft.appcenter.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import org.spongycastle.util.encoders.Base64;

/* renamed from: org.spongycastle.util.io.pem.PemReader */
public class PemReader extends BufferedReader {
    private static final String BEGIN = "-----BEGIN ";
    private static final String END = "-----END ";

    public PemReader(Reader reader) {
        super(reader);
    }

    public PemObject readPemObject() throws IOException {
        String readLine = readLine();
        while (readLine != null && !readLine.startsWith(BEGIN)) {
            readLine = readLine();
        }
        if (readLine != null) {
            String substring = readLine.substring(11);
            int indexOf = substring.indexOf(45);
            String substring2 = substring.substring(0, indexOf);
            if (indexOf > 0) {
                return loadObject(substring2);
            }
        }
        return null;
    }

    private PemObject loadObject(String str) throws IOException {
        String readLine;
        StringBuilder sb = new StringBuilder();
        sb.append(END);
        sb.append(str);
        String sb2 = sb.toString();
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList();
        while (true) {
            readLine = readLine();
            if (readLine == null) {
                break;
            } else if (readLine.indexOf(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR) >= 0) {
                int indexOf = readLine.indexOf(58);
                arrayList.add(new PemHeader(readLine.substring(0, indexOf), readLine.substring(indexOf + 1).trim()));
            } else if (readLine.indexOf(sb2) != -1) {
                break;
            } else {
                stringBuffer.append(readLine.trim());
            }
        }
        if (readLine != null) {
            return new PemObject(str, arrayList, Base64.decode(stringBuffer.toString()));
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append(" not found");
        throw new IOException(sb3.toString());
    }
}
