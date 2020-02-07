package com.htc.htcwalletsdk.Utils;

import com.facebook.stetho.common.Utf8Charset;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Map;
import org.json.JSONException;

public class TableParser {
    public static final int TABLE_ERC20_ERC721 = 20721;
    static final String TAG = "TableParser";
    Map<String, String> mJsonMap;
    Map<String, String> mJsonMap2;
    String mTableArray;
    String mTableSignature;
    String mTableVersion;
    String mValue;

    /* renamed from: com.htc.htcwalletsdk.Utils.TableParser$Key */
    public class C2278Key {
        public static final String table_data = "data";
        public static final String table_data_array = "array";
        public static final String table_data_version = "version";
        public static final String table_signature = "signature";

        public C2278Key() {
        }
    }

    public Map<String, String> ParserJsonString(String str) {
        try {
            return JsonParser.JsonStrToMap(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object parser(int i, String str, String str2) {
        byte[] bArr;
        String str3 = "key = ";
        String str4 = TAG;
        if (i == 20721) {
            this.mJsonMap = ParserJsonString(str2);
            this.mValue = (String) this.mJsonMap.get(str);
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            sb.append(str);
            ZKMALog.m275i(str4, sb.toString());
            String str5 = this.mValue;
            if (!(str5 == null || str5.length() == 0)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("mValue.length() = ");
                sb2.append(this.mValue.length());
                ZKMALog.m275i(str4, sb2.toString());
            }
            boolean equals = str.equals(C2278Key.table_signature);
            String str6 = Utf8Charset.NAME;
            if (equals) {
                bArr = Base64.getDecoder().decode(this.mValue);
                StringBuilder sb3 = new StringBuilder();
                sb3.append("BASE64 decoded Signature = ");
                sb3.append(new String(bArr, Charset.forName(str6)));
                ZKMALog.m272d(str4, sb3.toString());
            } else {
                String str7 = "data";
                if (str.equals(str7)) {
                    bArr = Base64.getDecoder().decode(this.mValue);
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("BASE64 decoded data = ");
                    sb4.append(new String(bArr, Charset.forName(str6)));
                    ZKMALog.m272d(str4, sb4.toString());
                } else if (str.equals("version")) {
                    this.mValue = (String) this.mJsonMap.get(str7);
                    this.mJsonMap2 = ParserJsonString(new String(Base64.getDecoder().decode(this.mValue), Charset.forName(str6)));
                    this.mTableVersion = (String) this.mJsonMap2.get(str);
                    return this.mTableVersion;
                } else if (str.equals(C2278Key.table_data_array)) {
                    this.mValue = (String) this.mJsonMap.get(str7);
                    this.mJsonMap2 = ParserJsonString(new String(Base64.getDecoder().decode(this.mValue), Charset.forName(str6)));
                    this.mTableArray = (String) this.mJsonMap2.get(str);
                    bArr = this.mTableArray.getBytes(Charset.forName(str6));
                } else {
                    ZKMALog.m273e(str4, "TableParser can't parse it!");
                }
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str3);
            sb5.append(str);
            sb5.append(" \nmValue=");
            sb5.append(this.mValue);
            sb5.append("  \nbyteData=");
            sb5.append(bArr);
            ZKMALog.m272d(str4, sb5.toString());
            return bArr;
        }
        bArr = null;
        StringBuilder sb52 = new StringBuilder();
        sb52.append(str3);
        sb52.append(str);
        sb52.append(" \nmValue=");
        sb52.append(this.mValue);
        sb52.append("  \nbyteData=");
        sb52.append(bArr);
        ZKMALog.m272d(str4, sb52.toString());
        return bArr;
    }
}
