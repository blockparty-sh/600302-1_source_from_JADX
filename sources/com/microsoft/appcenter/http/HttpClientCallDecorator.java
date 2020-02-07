package com.microsoft.appcenter.http;

import com.microsoft.appcenter.http.HttpClient.CallTemplate;
import java.util.Map;

abstract class HttpClientCallDecorator implements Runnable, ServiceCall, ServiceCallback {
    private final CallTemplate mCallTemplate;
    private final HttpClient mDecoratedApi;
    private final Map<String, String> mHeaders;
    private final String mMethod;
    ServiceCall mServiceCall;
    final ServiceCallback mServiceCallback;
    private final String mUrl;

    HttpClientCallDecorator(HttpClient httpClient, String str, String str2, Map<String, String> map, CallTemplate callTemplate, ServiceCallback serviceCallback) {
        this.mDecoratedApi = httpClient;
        this.mUrl = str;
        this.mMethod = str2;
        this.mHeaders = map;
        this.mCallTemplate = callTemplate;
        this.mServiceCallback = serviceCallback;
    }

    public synchronized void cancel() {
        this.mServiceCall.cancel();
    }

    public synchronized void run() {
        this.mServiceCall = this.mDecoratedApi.callAsync(this.mUrl, this.mMethod, this.mHeaders, this.mCallTemplate, this);
    }

    public void onCallSucceeded(String str, Map<String, String> map) {
        this.mServiceCallback.onCallSucceeded(str, map);
    }

    public void onCallFailed(Exception exc) {
        this.mServiceCallback.onCallFailed(exc);
    }
}
