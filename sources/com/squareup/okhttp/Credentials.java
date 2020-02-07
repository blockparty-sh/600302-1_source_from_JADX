package com.squareup.okhttp;

import com.microsoft.appcenter.Constants;
import java.io.UnsupportedEncodingException;
import okio.ByteString;
import org.spongycastle.i18n.LocalizedMessage;

public final class Credentials {
    private Credentials() {
    }

    public static String basic(String str, String str2) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            sb.append(str2);
            String base64 = ByteString.m329of(sb.toString().getBytes(LocalizedMessage.DEFAULT_ENCODING)).base64();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Basic ");
            sb2.append(base64);
            return sb2.toString();
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError();
        }
    }
}
