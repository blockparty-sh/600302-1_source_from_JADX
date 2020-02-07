package com.microsoft.appcenter.http;

import java.util.Map;

public interface ServiceCallback {
    void onCallFailed(Exception exc);

    void onCallSucceeded(String str, Map<String, String> map);
}
