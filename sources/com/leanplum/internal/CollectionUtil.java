package com.leanplum.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CollectionUtil {
    public static <T> T uncheckedCast(Object obj) {
        return obj;
    }

    @SafeVarargs
    public static <T> ArrayList<T> newArrayList(T... tArr) {
        ArrayList<T> arrayList = new ArrayList<>(tArr != null ? tArr.length : 0);
        if (tArr != null) {
            Collections.addAll(arrayList, tArr);
        }
        return arrayList;
    }

    @SafeVarargs
    static <T> HashSet<T> newHashSet(T... tArr) {
        HashSet<T> hashSet = new HashSet<>(tArr != null ? tArr.length : 0);
        if (tArr != null) {
            Collections.addAll(hashSet, tArr);
        }
        return hashSet;
    }

    public static <T, U> HashMap<T, U> newHashMap(Object... objArr) {
        HashMap hashMap = new HashMap(objArr != null ? objArr.length : 0);
        if (objArr == null) {
            objArr = null;
        }
        return (HashMap) newMap(hashMap, objArr);
    }

    static <T, U> LinkedHashMap<T, U> newLinkedHashMap(Object... objArr) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(objArr != null ? objArr.length : 0);
        if (objArr == null) {
            objArr = null;
        }
        return (LinkedHashMap) newMap(linkedHashMap, objArr);
    }

    private static <T, U> Map<T, U> newMap(Map<T, U> map, T[] tArr) {
        if (tArr == null || tArr.length == 0) {
            return map;
        }
        if (tArr.length % 2 == 0) {
            for (int i = 0; i < tArr.length; i += 2) {
                map.put(tArr[i], tArr[i + 1]);
            }
            return map;
        }
        throw new IllegalArgumentException("newMap requires an even number of items.");
    }

    static <T> String concatenateArray(T[] tArr, String str) {
        if (tArr == null) {
            return null;
        }
        return concatenateList(Arrays.asList(tArr), str);
    }

    static String concatenateList(List<?> list, String str) {
        if (list == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object next : list) {
            if (next != null) {
                sb.append(next.toString());
                sb.append(str);
            }
        }
        String sb2 = sb.toString();
        if (sb2.length() > 0) {
            sb2 = sb2.substring(0, sb2.length() - str.length());
        }
        return sb2;
    }

    public static <K, V> V getOrDefault(Map<K, V> map, K k, V v) {
        if (map == null) {
            return v;
        }
        if (map.containsKey(k)) {
            v = map.get(k);
        }
        return v;
    }

    public static long[] toPrimitive(Long[] lArr) {
        if (lArr == null) {
            return null;
        }
        if (lArr.length == 0) {
            return new long[0];
        }
        long[] jArr = new long[lArr.length];
        for (int i = 0; i < lArr.length; i++) {
            jArr[i] = lArr[i].longValue();
        }
        return jArr;
    }
}
