package com.google.zxing.client.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.zxing.DecodeHintType;
import com.leanplum.core.BuildConfig;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class DecodeHintManager {
    private static final Pattern COMMA = Pattern.compile(",");
    private static final String TAG = "DecodeHintManager";

    private DecodeHintManager() {
    }

    private static Map<String, String> splitQuery(String str) {
        String str2;
        HashMap hashMap = new HashMap();
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                break;
            } else if (str.charAt(i) == '&') {
                i++;
            } else {
                int indexOf = str.indexOf(38, i);
                int indexOf2 = str.indexOf(61, i);
                String str3 = "";
                if (indexOf < 0) {
                    if (indexOf2 < 0) {
                        str2 = Uri.decode(str.substring(i).replace('+', ' '));
                    } else {
                        String decode = Uri.decode(str.substring(i, indexOf2).replace('+', ' '));
                        str3 = Uri.decode(str.substring(indexOf2 + 1).replace('+', ' '));
                        str2 = decode;
                    }
                    if (!hashMap.containsKey(str2)) {
                        hashMap.put(str2, str3);
                    }
                } else {
                    if (indexOf2 < 0 || indexOf2 > indexOf) {
                        String decode2 = Uri.decode(str.substring(i, indexOf).replace('+', ' '));
                        if (!hashMap.containsKey(decode2)) {
                            hashMap.put(decode2, str3);
                        }
                    } else {
                        String decode3 = Uri.decode(str.substring(i, indexOf2).replace('+', ' '));
                        String decode4 = Uri.decode(str.substring(indexOf2 + 1, indexOf).replace('+', ' '));
                        if (!hashMap.containsKey(decode3)) {
                            hashMap.put(decode3, decode4);
                        }
                    }
                    i = indexOf + 1;
                }
            }
        }
        return hashMap;
    }

    static Map<DecodeHintType, ?> parseDecodeHints(Uri uri) {
        DecodeHintType[] values;
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null || encodedQuery.isEmpty()) {
            return null;
        }
        Map splitQuery = splitQuery(encodedQuery);
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        for (DecodeHintType decodeHintType : DecodeHintType.values()) {
            if (!(decodeHintType == DecodeHintType.CHARACTER_SET || decodeHintType == DecodeHintType.NEED_RESULT_POINT_CALLBACK || decodeHintType == DecodeHintType.POSSIBLE_FORMATS)) {
                String str = (String) splitQuery.get(decodeHintType.name());
                if (str != null) {
                    if (decodeHintType.getValueType().equals(Object.class)) {
                        enumMap.put(decodeHintType, str);
                    } else if (decodeHintType.getValueType().equals(Void.class)) {
                        enumMap.put(decodeHintType, Boolean.TRUE);
                    } else if (decodeHintType.getValueType().equals(String.class)) {
                        enumMap.put(decodeHintType, str);
                    } else if (decodeHintType.getValueType().equals(Boolean.class)) {
                        if (str.isEmpty()) {
                            enumMap.put(decodeHintType, Boolean.TRUE);
                        } else if (BuildConfig.BUILD_NUMBER.equals(str) || "false".equalsIgnoreCase(str) || "no".equalsIgnoreCase(str)) {
                            enumMap.put(decodeHintType, Boolean.FALSE);
                        } else {
                            enumMap.put(decodeHintType, Boolean.TRUE);
                        }
                    } else if (decodeHintType.getValueType().equals(int[].class)) {
                        if (!str.isEmpty() && str.charAt(str.length() - 1) == ',') {
                            str = str.substring(0, str.length() - 1);
                        }
                        String[] split = COMMA.split(str);
                        int[] iArr = new int[split.length];
                        int i = 0;
                        while (i < split.length) {
                            try {
                                iArr[i] = Integer.parseInt(split[i]);
                                i++;
                            } catch (NumberFormatException unused) {
                                String str2 = TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("Skipping array of integers hint ");
                                sb.append(decodeHintType);
                                sb.append(" due to invalid numeric value: '");
                                sb.append(split[i]);
                                sb.append('\'');
                                Log.w(str2, sb.toString());
                                iArr = null;
                            }
                        }
                        if (iArr != null) {
                            enumMap.put(decodeHintType, iArr);
                        }
                    } else {
                        String str3 = TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Unsupported hint type '");
                        sb2.append(decodeHintType);
                        sb2.append("' of type ");
                        sb2.append(decodeHintType.getValueType());
                        Log.w(str3, sb2.toString());
                    }
                }
            }
        }
        String str4 = TAG;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Hints from the URI: ");
        sb3.append(enumMap);
        Log.i(str4, sb3.toString());
        return enumMap;
    }

    public static Map<DecodeHintType, Object> parseDecodeHints(Intent intent) {
        DecodeHintType[] values;
        Bundle extras = intent.getExtras();
        if (extras == null || extras.isEmpty()) {
            return null;
        }
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        for (DecodeHintType decodeHintType : DecodeHintType.values()) {
            if (!(decodeHintType == DecodeHintType.CHARACTER_SET || decodeHintType == DecodeHintType.NEED_RESULT_POINT_CALLBACK || decodeHintType == DecodeHintType.POSSIBLE_FORMATS)) {
                String name = decodeHintType.name();
                if (extras.containsKey(name)) {
                    if (decodeHintType.getValueType().equals(Void.class)) {
                        enumMap.put(decodeHintType, Boolean.TRUE);
                    } else {
                        Object obj = extras.get(name);
                        if (decodeHintType.getValueType().isInstance(obj)) {
                            enumMap.put(decodeHintType, obj);
                        } else {
                            String str = TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Ignoring hint ");
                            sb.append(decodeHintType);
                            sb.append(" because it is not assignable from ");
                            sb.append(obj);
                            Log.w(str, sb.toString());
                        }
                    }
                }
            }
        }
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Hints from the Intent: ");
        sb2.append(enumMap);
        Log.i(str2, sb2.toString());
        return enumMap;
    }
}
