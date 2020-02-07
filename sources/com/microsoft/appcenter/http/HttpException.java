package com.microsoft.appcenter.http;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpException extends IOException {
    private final Map<String, String> headers;
    private final String payload;
    private final int statusCode;

    public HttpException(int i) {
        this(i, "");
    }

    public HttpException(int i, @NonNull String str) {
        this(i, str, new HashMap());
    }

    public HttpException(int i, @NonNull String str, @NonNull Map<String, String> map) {
        super(getDetailMessage(i, str));
        this.payload = str;
        this.statusCode = i;
        this.headers = map;
    }

    @NonNull
    private static String getDetailMessage(int i, @NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return String.valueOf(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(" - ");
        sb.append(str);
        return sb.toString();
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    @NonNull
    public String getPayload() {
        return this.payload;
    }

    @NonNull
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HttpException httpException = (HttpException) obj;
        if (this.statusCode != httpException.statusCode || !this.payload.equals(httpException.payload) || !this.headers.equals(httpException.headers)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((this.statusCode * 31) + this.payload.hashCode()) * 31) + this.headers.hashCode();
    }
}
