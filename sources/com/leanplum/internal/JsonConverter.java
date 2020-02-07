package com.leanplum.internal;

import android.text.Editable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonConverter {
    public static String toJson(Map<String, ?> map) {
        if (map == null) {
            return null;
        }
        try {
            return mapToJsonObject(map).toString();
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error converting ");
            sb.append(map);
            sb.append(" to JSON");
            Log.m280e(sb.toString(), e);
            return null;
        }
    }

    public static Map<String, Object> fromJson(String str) {
        if (str == null) {
            return null;
        }
        try {
            return mapFromJson(new JSONObject(str));
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error converting ");
            sb.append(str);
            sb.append(" from JSON");
            Log.m280e(sb.toString(), e);
            return null;
        }
    }

    public static JSONObject mapToJsonObject(Map<String, ?> map) throws JSONException {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                value = mapToJsonObject((Map) CollectionUtil.uncheckedCast(value));
            } else if (value instanceof Iterable) {
                value = listToJsonArray((Iterable) value);
            } else if (value instanceof Editable) {
                value = value.toString();
            } else if (value == null) {
                value = JSONObject.NULL;
            }
            jSONObject.put(str, value);
        }
        return jSONObject;
    }

    public static JSONArray listToJsonArray(Iterable<?> iterable) throws JSONException {
        if (iterable == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object next : iterable) {
            if (next instanceof Map) {
                next = mapToJsonObject((Map) CollectionUtil.uncheckedCast(next));
            } else if (next instanceof Iterable) {
                next = listToJsonArray((Iterable) next);
            } else if (next == null) {
                next = JSONObject.NULL;
            }
            jSONArray.put(next);
        }
        return jSONArray;
    }

    public static <T> Map<String, T> mapFromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object opt = jSONObject.opt(str);
            if (!(opt == null || opt == JSONObject.NULL)) {
                if (opt instanceof JSONObject) {
                    opt = mapFromJson((JSONObject) opt);
                } else if (opt instanceof JSONArray) {
                    opt = listFromJson((JSONArray) opt);
                } else if (!JSONObject.NULL.equals(opt)) {
                }
                hashMap.put(str, CollectionUtil.uncheckedCast(opt));
            }
            opt = null;
            hashMap.put(str, CollectionUtil.uncheckedCast(opt));
        }
        return hashMap;
    }

    public static <T> Map<String, T> mapFromJsonOrDefault(JSONObject jSONObject) {
        if (jSONObject == null) {
            return new HashMap();
        }
        return mapFromJson(jSONObject);
    }

    public static <T> List<T> listFromJson(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            Object opt = jSONArray.opt(i);
            if (!(opt == null || opt == JSONObject.NULL)) {
                if (opt instanceof JSONObject) {
                    opt = mapFromJson((JSONObject) opt);
                } else if (opt instanceof JSONArray) {
                    opt = listFromJson((JSONArray) opt);
                } else if (!JSONObject.NULL.equals(opt)) {
                }
                arrayList.add(opt);
            }
            opt = null;
            arrayList.add(opt);
        }
        return (List) CollectionUtil.uncheckedCast(arrayList);
    }

    public static <T> List<T> listFromJsonOrDefault(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new ArrayList();
        }
        return listFromJson(jSONArray);
    }
}
