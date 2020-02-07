package com.htc.htcwalletsdk.Utils;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonParser {
    static final String TAG = "JsonParser";
    String mData;
    Map<String, String> mJsonMap;
    Map<String, String> mJsonMap2;
    String mMessage;
    String[] mMessageArrays;
    String mVersion;

    public class JsonDataKey_signMessage {
        public static final String message = "message";
        public static final String message_data = "data";
        public static final String message_version = "version";
        public static final String path = "path";

        public JsonDataKey_signMessage() {
        }
    }

    public static String[] JsonStrToStrArray(String str) {
        String str2 = TAG;
        ZKMALog.m272d(str2, "JsonStrToStrArray +++");
        StringBuilder sb = new StringBuilder();
        sb.append("{key:");
        sb.append(str);
        sb.append("}");
        try {
            JSONArray jSONArray = ((JSONObject) new JSONTokener(sb.toString()).nextValue()).getJSONArray("key");
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jSONArray.getString(i);
            }
            ZKMALog.m272d(str2, "JsonStrToStrArray ---");
            return strArr;
        } catch (JSONException unused) {
            return new String[0];
        }
    }

    public static Map<String, String> JsonStrToMap(String str) throws JSONException {
        String str2 = TAG;
        ZKMALog.m272d(str2, "JsonStrToMap +++");
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str)) {
            return hashMap;
        }
        JSONObject jSONObject = (JSONObject) new JSONTokener(str).nextValue();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str3 = (String) keys.next();
            hashMap.put(str3, jSONObject.getString(str3));
        }
        ZKMALog.m272d(str2, "JsonStrToMap ---");
        return hashMap;
    }

    public Map<String, String> ParserJsonString(String str) {
        try {
            return JsonStrToMap(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int ParserJsonEthSignMessage(String str) {
        this.mJsonMap = ParserJsonString(str);
        this.mMessage = (String) this.mJsonMap.get("message");
        this.mJsonMap2 = ParserJsonString(this.mMessage);
        this.mVersion = (String) this.mJsonMap2.get("version");
        this.mData = (String) this.mJsonMap2.get("data");
        return hexToString(this.mData).length();
    }

    public static String hexToString(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str.length() - 1) {
            int i2 = i + 2;
            sb.append((char) Integer.parseInt(str.substring(i, i2), 16));
            i = i2;
        }
        return sb.toString();
    }
}
