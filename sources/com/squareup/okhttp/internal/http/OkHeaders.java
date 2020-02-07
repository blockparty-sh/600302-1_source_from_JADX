package com.squareup.okhttp.internal.http;

import com.google.common.net.HttpHeaders;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Challenge;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.slf4j.Marker;

public final class OkHeaders {
    private static final Comparator<String> FIELD_NAME_COMPARATOR = new Comparator<String>() {
        public int compare(String str, String str2) {
            if (str == str2) {
                return 0;
            }
            if (str == null) {
                return -1;
            }
            if (str2 == null) {
                return 1;
            }
            return String.CASE_INSENSITIVE_ORDER.compare(str, str2);
        }
    };
    static final String PREFIX = Platform.get().getPrefix();
    public static final String RECEIVED_MILLIS;
    public static final String SELECTED_PROTOCOL;
    public static final String SENT_MILLIS;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX);
        sb.append("-Sent-Millis");
        SENT_MILLIS = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(PREFIX);
        sb2.append("-Received-Millis");
        RECEIVED_MILLIS = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(PREFIX);
        sb3.append("-Selected-Protocol");
        SELECTED_PROTOCOL = sb3.toString();
    }

    private OkHeaders() {
    }

    public static long contentLength(Request request) {
        return contentLength(request.headers());
    }

    public static long contentLength(Response response) {
        return contentLength(response.headers());
    }

    public static long contentLength(Headers headers) {
        return stringToLong(headers.get("Content-Length"));
    }

    private static long stringToLong(String str) {
        long j = -1;
        if (str == null) {
            return -1;
        }
        try {
            j = Long.parseLong(str);
        } catch (NumberFormatException unused) {
        }
        return j;
    }

    public static Map<String, List<String>> toMultimap(Headers headers, String str) {
        TreeMap treeMap = new TreeMap(FIELD_NAME_COMPARATOR);
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            ArrayList arrayList = new ArrayList();
            List list = (List) treeMap.get(name);
            if (list != null) {
                arrayList.addAll(list);
            }
            arrayList.add(value);
            treeMap.put(name, Collections.unmodifiableList(arrayList));
        }
        if (str != null) {
            treeMap.put(null, Collections.unmodifiableList(Collections.singletonList(str)));
        }
        return Collections.unmodifiableMap(treeMap);
    }

    public static void addCookies(Builder builder, Map<String, List<String>> map) {
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if ((HttpHeaders.COOKIE.equalsIgnoreCase(str) || "Cookie2".equalsIgnoreCase(str)) && !((List) entry.getValue()).isEmpty()) {
                builder.addHeader(str, buildCookieHeader((List) entry.getValue()));
            }
        }
    }

    private static String buildCookieHeader(List<String> list) {
        if (list.size() == 1) {
            return (String) list.get(0);
        }
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            sb.append((String) list.get(i));
        }
        return sb.toString();
    }

    public static boolean varyMatches(Response response, Headers headers, Request request) {
        for (String str : varyFields(response)) {
            if (!Util.equal(headers.values(str), request.headers(str))) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasVaryAll(Response response) {
        return hasVaryAll(response.headers());
    }

    public static boolean hasVaryAll(Headers headers) {
        return varyFields(headers).contains(Marker.ANY_MARKER);
    }

    private static Set<String> varyFields(Response response) {
        return varyFields(response.headers());
    }

    public static Set<String> varyFields(Headers headers) {
        Set emptySet = Collections.emptySet();
        int size = headers.size();
        Set set = emptySet;
        for (int i = 0; i < size; i++) {
            if (HttpHeaders.VARY.equalsIgnoreCase(headers.name(i))) {
                String value = headers.value(i);
                if (set.isEmpty()) {
                    set = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                }
                for (String trim : value.split(",")) {
                    set.add(trim.trim());
                }
            }
        }
        return set;
    }

    public static Headers varyHeaders(Response response) {
        return varyHeaders(response.networkResponse().request().headers(), response.headers());
    }

    public static Headers varyHeaders(Headers headers, Headers headers2) {
        Set varyFields = varyFields(headers2);
        if (varyFields.isEmpty()) {
            return new Headers.Builder().build();
        }
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            if (varyFields.contains(name)) {
                builder.add(name, headers.value(i));
            }
        }
        return builder.build();
    }

    static boolean isEndToEnd(String str) {
        return !HttpHeaders.CONNECTION.equalsIgnoreCase(str) && !"Keep-Alive".equalsIgnoreCase(str) && !HttpHeaders.PROXY_AUTHENTICATE.equalsIgnoreCase(str) && !HttpHeaders.PROXY_AUTHORIZATION.equalsIgnoreCase(str) && !HttpHeaders.f602TE.equalsIgnoreCase(str) && !"Trailers".equalsIgnoreCase(str) && !HttpHeaders.TRANSFER_ENCODING.equalsIgnoreCase(str) && !HttpHeaders.UPGRADE.equalsIgnoreCase(str);
    }

    public static List<Challenge> parseChallenges(Headers headers, String str) {
        ArrayList arrayList = new ArrayList();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase(headers.name(i))) {
                String value = headers.value(i);
                int i2 = 0;
                while (i2 < value.length()) {
                    int skipUntil = HeaderParser.skipUntil(value, i2, " ");
                    String trim = value.substring(i2, skipUntil).trim();
                    int skipWhitespace = HeaderParser.skipWhitespace(value, skipUntil);
                    if (!value.regionMatches(true, skipWhitespace, "realm=\"", 0, 7)) {
                        break;
                    }
                    int i3 = skipWhitespace + 7;
                    int skipUntil2 = HeaderParser.skipUntil(value, i3, "\"");
                    String substring = value.substring(i3, skipUntil2);
                    i2 = HeaderParser.skipWhitespace(value, HeaderParser.skipUntil(value, skipUntil2 + 1, ",") + 1);
                    arrayList.add(new Challenge(trim, substring));
                }
            }
        }
        return arrayList;
    }

    public static Request processAuthHeader(Authenticator authenticator, Response response, Proxy proxy) throws IOException {
        if (response.code() == 407) {
            return authenticator.authenticateProxy(proxy, response);
        }
        return authenticator.authenticate(proxy, response);
    }
}
