package com.leanplum.internal;

import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.leanplum.Leanplum;
import com.leanplum.internal.RequestOld.ErrorCallback;
import com.leanplum.internal.RequestOld.ResponseCallback;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

class LeanplumEventCallbackManager {
    private final Map<RequestOld, LeanplumEventCallbacks> eventCallbacks = new HashMap();

    private static class LeanplumEventCallbacks {
        /* access modifiers changed from: private */
        public ErrorCallback errorCallback;
        /* access modifiers changed from: private */
        public ResponseCallback responseCallback;

        LeanplumEventCallbacks(ResponseCallback responseCallback2, ErrorCallback errorCallback2) {
            this.responseCallback = responseCallback2;
            this.errorCallback = errorCallback2;
        }
    }

    LeanplumEventCallbackManager() {
    }

    /* access modifiers changed from: 0000 */
    public void addCallbacks(RequestOld requestOld, ResponseCallback responseCallback, ErrorCallback errorCallback) {
        if (requestOld != null) {
            if (responseCallback != null || errorCallback != null) {
                this.eventCallbacks.put(requestOld, new LeanplumEventCallbacks(responseCallback, errorCallback));
                Leanplum.countAggregator().incrementCount("add_event_callback_at");
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void invokeAllCallbacksWithError(@NonNull final Exception exc, int i) {
        if (this.eventCallbacks.size() != 0) {
            Iterator it = this.eventCallbacks.entrySet().iterator();
            while (it.hasNext()) {
                final Entry entry = (Entry) it.next();
                if (entry.getKey() != null) {
                    long j = (long) i;
                    if (((RequestOld) entry.getKey()).getDataBaseIndex() >= j) {
                        ((RequestOld) entry.getKey()).setDataBaseIndex(((RequestOld) entry.getKey()).getDataBaseIndex() - j);
                    } else {
                        if (!(entry.getValue() == null || ((LeanplumEventCallbacks) entry.getValue()).errorCallback == null)) {
                            Util.executeAsyncTask(false, new AsyncTask<Void, Void, Void>() {
                                /* access modifiers changed from: protected */
                                public Void doInBackground(Void... voidArr) {
                                    ((LeanplumEventCallbacks) entry.getValue()).errorCallback.error(exc);
                                    return null;
                                }
                            }, new Void[0]);
                        }
                        it.remove();
                    }
                }
            }
            Leanplum.countAggregator().incrementCount("invoke_error_callbacks_on_responses");
        }
    }

    /* access modifiers changed from: 0000 */
    public void invokeAllCallbacksForResponse(@NonNull final JSONObject jSONObject, int i) {
        if (this.eventCallbacks.size() != 0) {
            Iterator it = this.eventCallbacks.entrySet().iterator();
            while (it.hasNext()) {
                final Entry entry = (Entry) it.next();
                if (entry.getKey() != null) {
                    long j = (long) i;
                    if (((RequestOld) entry.getKey()).getDataBaseIndex() >= j) {
                        ((RequestOld) entry.getKey()).setDataBaseIndex(((RequestOld) entry.getKey()).getDataBaseIndex() - j);
                    } else {
                        if (!(entry.getValue() == null || ((LeanplumEventCallbacks) entry.getValue()).responseCallback == null)) {
                            Util.executeAsyncTask(false, new AsyncTask<Void, Void, Void>() {
                                /* access modifiers changed from: protected */
                                public Void doInBackground(Void... voidArr) {
                                    ((LeanplumEventCallbacks) entry.getValue()).responseCallback.response(RequestOld.getResponseAt(jSONObject, (int) ((RequestOld) entry.getKey()).getDataBaseIndex()));
                                    return null;
                                }
                            }, new Void[0]);
                        }
                        it.remove();
                    }
                }
            }
            Leanplum.countAggregator().incrementCount("invoke_success_callbacks_on_responses");
        }
    }
}
