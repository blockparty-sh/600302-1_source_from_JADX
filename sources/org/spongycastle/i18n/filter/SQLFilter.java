package org.spongycastle.i18n.filter;

public class SQLFilter implements Filter {
    public String doFilter(String str) {
        int i;
        StringBuffer stringBuffer = new StringBuffer(str);
        int i2 = 0;
        while (i2 < stringBuffer.length()) {
            char charAt = stringBuffer.charAt(i2);
            if (charAt == 10) {
                i = i2 + 1;
                stringBuffer.replace(i2, i, "\\n");
            } else if (charAt == 13) {
                i = i2 + 1;
                stringBuffer.replace(i2, i, "\\r");
            } else if (charAt == '\"') {
                i = i2 + 1;
                stringBuffer.replace(i2, i, "\\\"");
            } else if (charAt == '\'') {
                i = i2 + 1;
                stringBuffer.replace(i2, i, "\\'");
            } else if (charAt == '-') {
                i = i2 + 1;
                stringBuffer.replace(i2, i, "\\-");
            } else if (charAt == '/') {
                i = i2 + 1;
                stringBuffer.replace(i2, i, "\\/");
            } else if (charAt == ';') {
                i = i2 + 1;
                stringBuffer.replace(i2, i, "\\;");
            } else if (charAt == '=') {
                i = i2 + 1;
                stringBuffer.replace(i2, i, "\\=");
            } else if (charAt != '\\') {
                i = i2;
            } else {
                i = i2 + 1;
                stringBuffer.replace(i2, i, "\\\\");
            }
            i2 = i + 1;
        }
        return stringBuffer.toString();
    }

    public String doFilterUrl(String str) {
        return doFilter(str);
    }
}
