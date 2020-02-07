package com.leanplum.internal;

import com.leanplum.Leanplum;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.internal.Constants.Methods;
import com.leanplum.internal.RequestOld.ErrorCallback;
import com.leanplum.internal.RequestOld.ResponseCallback;
import java.util.HashMap;
import org.json.JSONObject;

public class Registration {
    public static void registerDevice(String str, final StartCallback startCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("email", str);
        RequestOld post = RequestOld.post(Methods.REGISTER_FOR_DEVELOPMENT, hashMap);
        post.onResponse(new ResponseCallback() {
            public void response(final JSONObject jSONObject) {
                OsHandler.getInstance().post(new Runnable() {
                    public void run() {
                        try {
                            if (!RequestOld.isResponseSuccess(jSONObject)) {
                                Log.m280e(RequestOld.getResponseError(jSONObject));
                                if (startCallback != null) {
                                    startCallback.onResponse(false);
                                }
                            } else if (startCallback != null) {
                                startCallback.onResponse(true);
                            }
                        } catch (Throwable th) {
                            Util.handleException(th);
                        }
                    }
                });
            }
        });
        post.onError(new ErrorCallback() {
            public void error(Exception exc) {
                OsHandler.getInstance().post(new Runnable() {
                    public void run() {
                        if (startCallback != null) {
                            startCallback.onResponse(false);
                        }
                    }
                });
            }
        });
        post.sendIfConnected();
        Leanplum.countAggregator().incrementCount("register_device");
    }
}
