package com.leanplum.internal;

import org.json.JSONObject;

public interface Requesting {

    public interface ErrorCallback {
        void error(Exception exc);
    }

    public interface ResponseCallback {
        void response(JSONObject jSONObject);
    }
}
