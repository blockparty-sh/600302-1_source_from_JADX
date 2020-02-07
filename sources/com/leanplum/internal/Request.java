package com.leanplum.internal;

import com.leanplum.Leanplum;
import com.leanplum.internal.Constants.Methods;
import com.leanplum.internal.Log.LeanplumLogType;
import com.leanplum.internal.Requesting.ErrorCallback;
import com.leanplum.internal.Requesting.ResponseCallback;
import com.microsoft.appcenter.http.DefaultHttpClient;
import java.util.Map;

public class Request implements Requesting {
    private final String apiMethod;
    private final CountAggregator countAggregator;
    private ErrorCallback error = null;
    private final String httpMethod;
    private final Map<String, Object> params;
    private ResponseCallback response = null;

    public Request(String str, String str2, Map<String, Object> map) {
        this.httpMethod = str;
        this.apiMethod = str2;
        this.params = map;
        this.countAggregator = Leanplum.countAggregator();
    }

    public static Request get(String str, Map<String, Object> map) {
        LeanplumLogType leanplumLogType = Methods.LOG.equals(str) ? LeanplumLogType.DEBUG : LeanplumLogType.VERBOSE;
        StringBuilder sb = new StringBuilder();
        sb.append("Will call API method ");
        sb.append(str);
        sb.append(" with arguments ");
        sb.append(map);
        Log.log(leanplumLogType, sb.toString());
        Leanplum.countAggregator().incrementCount("get_lprequest");
        return new Request(DefaultHttpClient.METHOD_GET, str, map);
    }

    public static Request post(String str, Map<String, Object> map) {
        LeanplumLogType leanplumLogType = Methods.LOG.equals(str) ? LeanplumLogType.DEBUG : LeanplumLogType.VERBOSE;
        StringBuilder sb = new StringBuilder();
        sb.append("Will call API method ");
        sb.append(str);
        sb.append(" with arguments ");
        sb.append(map);
        Log.log(leanplumLogType, sb.toString());
        Leanplum.countAggregator().incrementCount("post_lprequest");
        return new Request("POST", str, map);
    }

    public void onResponse(ResponseCallback responseCallback) {
        this.response = responseCallback;
        this.countAggregator.incrementCount("on_response_lprequest");
    }

    public void onError(ErrorCallback errorCallback) {
        this.error = errorCallback;
        this.countAggregator.incrementCount("on_error_lprequest");
    }
}
