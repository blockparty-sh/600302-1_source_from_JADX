package com.microsoft.appcenter.http;

import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.http.HttpClient.CallTemplate;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAppCallTemplate implements CallTemplate {
    public void onBeforeCalling(URL url, Map<String, String> map) {
        if (AppCenterLog.getLogLevel() <= 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calling ");
            sb.append(url);
            sb.append("...");
            String sb2 = sb.toString();
            String str = "AppCenter";
            AppCenterLog.verbose(str, sb2);
            HashMap hashMap = new HashMap(map);
            String str2 = Constants.APP_SECRET;
            String str3 = (String) hashMap.get(str2);
            if (str3 != null) {
                hashMap.put(str2, HttpUtils.hideSecret(str3));
            }
            String str4 = "Authorization";
            String str5 = (String) hashMap.get(str4);
            if (str5 != null) {
                hashMap.put(str4, HttpUtils.hideAuthToken(str5));
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Headers: ");
            sb3.append(hashMap);
            AppCenterLog.verbose(str, sb3.toString());
        }
    }
}
