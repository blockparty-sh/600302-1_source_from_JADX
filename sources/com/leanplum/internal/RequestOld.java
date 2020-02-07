package com.leanplum.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.leanplum.Leanplum;
import com.leanplum.internal.Constants.Defaults;
import com.leanplum.internal.Constants.Keys;
import com.leanplum.internal.Constants.Methods;
import com.leanplum.internal.Constants.Params;
import com.leanplum.internal.Log.LeanplumLogType;
import com.leanplum.utils.SharedPreferencesUtil;
import com.microsoft.appcenter.http.DefaultHttpClient;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestOld implements Requesting {
    private static final long DEVELOPMENT_MAX_DELAY_MS = 5000;
    private static final long DEVELOPMENT_MIN_DELAY_MS = 100;
    static final String LEANPLUM = "__leanplum__";
    static final int MAX_EVENTS_PER_API_CALL;
    private static final long PRODUCTION_DELAY = 60000;
    public static final String REQUEST_ID_KEY = "reqId";
    static final String UUID_KEY = "uuid";
    private static String accessKey;
    private static ApiResponseCallback apiResponse;
    private static String appId;
    private static String deviceId;
    private static final LeanplumEventCallbackManager eventCallbackManager = new LeanplumEventCallbackManager();
    private static final Map<String, Boolean> fileTransferStatus = new HashMap();
    /* access modifiers changed from: private */
    public static final Map<File, Double> fileUploadProgress = new HashMap();
    private static String fileUploadProgressString = "";
    private static final Map<File, Long> fileUploadSize = new HashMap();
    private static long lastSendTimeMs;
    private static List<Map<String, Object>> localErrors = new ArrayList();
    private static NoPendingDownloadsCallback noPendingDownloadsBlock;
    private static int pendingDownloads;
    private static String token = null;
    /* access modifiers changed from: private */
    public static final Object uploadFileLock = new Object();
    private static String userId;
    private final String apiMethod;
    private long dataBaseIndex;
    /* access modifiers changed from: private */
    public ErrorCallback error;
    /* access modifiers changed from: private */
    public final String httpMethod;
    private final Map<String, Object> params;
    private String requestId;
    private RequestSequenceRecorder requestSequenceRecorder;
    /* access modifiers changed from: private */
    public ResponseCallback response;
    private boolean sent;

    public interface ApiResponseCallback {
        void response(List<Map<String, Object>> list, JSONObject jSONObject, int i);
    }

    public interface ErrorCallback {
        void error(Exception exc);
    }

    public interface NoPendingDownloadsCallback {
        void noPendingDownloads();
    }

    private static class NoRequestSequenceRecorder implements RequestSequenceRecorder {
        public void afterRead() {
        }

        public void afterWrite() {
        }

        public void beforeRead() {
        }

        public void beforeWrite() {
        }

        private NoRequestSequenceRecorder() {
        }
    }

    static class RequestsWithEncoding {
        String jsonEncodedString;
        List<Map<String, Object>> requestsToSend;
        List<Map<String, Object>> unsentRequests;

        RequestsWithEncoding() {
        }
    }

    public interface ResponseCallback {
        void response(JSONObject jSONObject);
    }

    static {
        if (VERSION.SDK_INT <= 17) {
            MAX_EVENTS_PER_API_CALL = 5000;
        } else {
            MAX_EVENTS_PER_API_CALL = 10000;
        }
    }

    public static void setAppId(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            appId = str.trim();
        }
        if (!TextUtils.isEmpty(str2)) {
            accessKey = str2.trim();
        }
        Leanplum.countAggregator().incrementCount("set_app_id");
    }

    public static void setDeviceId(String str) {
        deviceId = str;
    }

    public static void setUserId(String str) {
        userId = str;
    }

    public static void setToken(String str) {
        token = str;
        Leanplum.countAggregator().incrementCount("set_token");
    }

    public static String token() {
        return token;
    }

    public long getDataBaseIndex() {
        return this.dataBaseIndex;
    }

    public void setDataBaseIndex(long j) {
        this.dataBaseIndex = j;
    }

    public static void loadToken() {
        String string = Leanplum.getContext().getSharedPreferences("__leanplum__", 0).getString(Defaults.TOKEN_KEY, null);
        if (string != null) {
            setToken(string);
            Leanplum.countAggregator().incrementCount("load_token");
        }
    }

    public static void saveToken() {
        Editor edit = Leanplum.getContext().getSharedPreferences("__leanplum__", 0).edit();
        edit.putString(Defaults.TOKEN_KEY, token());
        SharedPreferencesUtil.commitChanges(edit);
    }

    public static String appId() {
        return appId;
    }

    public static String deviceId() {
        return deviceId;
    }

    public static String userId() {
        return userId;
    }

    public RequestOld(String str, String str2, Map<String, Object> map) {
        this(str, str2, map, new NoRequestSequenceRecorder());
    }

    RequestOld(String str, String str2, Map<String, Object> map, RequestSequenceRecorder requestSequenceRecorder2) {
        this.httpMethod = str;
        this.apiMethod = str2;
        if (map == null) {
            map = new HashMap<>();
        }
        this.params = map;
        if (Methods.LOG.equals(str2) && LeanplumEventDataManager.willSendErrorLog) {
            localErrors.add(createArgsDictionary());
        }
        OsHandler.getInstance();
        this.dataBaseIndex = -1;
        this.requestSequenceRecorder = requestSequenceRecorder2;
        this.requestId = UUID.randomUUID().toString();
    }

    public static RequestOld get(String str, Map<String, Object> map) {
        LeanplumLogType leanplumLogType = Methods.LOG.equals(str) ? LeanplumLogType.DEBUG : LeanplumLogType.VERBOSE;
        StringBuilder sb = new StringBuilder();
        sb.append("Will call API method ");
        sb.append(str);
        sb.append(" with arguments ");
        sb.append(map);
        Log.log(leanplumLogType, sb.toString());
        Leanplum.countAggregator().incrementCount("get_request");
        return RequestFactory.getInstance().createRequest(DefaultHttpClient.METHOD_GET, str, map);
    }

    public static RequestOld post(String str, Map<String, Object> map) {
        LeanplumLogType leanplumLogType = Methods.LOG.equals(str) ? LeanplumLogType.DEBUG : LeanplumLogType.VERBOSE;
        StringBuilder sb = new StringBuilder();
        sb.append("Will call API method ");
        sb.append(str);
        sb.append(" with arguments ");
        sb.append(map);
        Log.log(leanplumLogType, sb.toString());
        Leanplum.countAggregator().incrementCount("post_request");
        return RequestFactory.getInstance().createRequest("POST", str, map);
    }

    public void onResponse(ResponseCallback responseCallback) {
        this.response = responseCallback;
        Leanplum.countAggregator().incrementCount("on_response");
    }

    public void onError(ErrorCallback errorCallback) {
        this.error = errorCallback;
        Leanplum.countAggregator().incrementCount("on_error");
    }

    public void onApiResponse(ApiResponseCallback apiResponseCallback) {
        apiResponse = apiResponseCallback;
    }

    @VisibleForTesting
    public Map<String, Object> createArgsDictionary() {
        HashMap hashMap = new HashMap();
        hashMap.put(Params.DEVICE_ID, deviceId);
        hashMap.put(Params.USER_ID, userId);
        hashMap.put("action", this.apiMethod);
        hashMap.put(Params.SDK_VERSION, Constants.LEANPLUM_VERSION);
        hashMap.put(Params.DEV_MODE, Boolean.toString(Constants.isDevelopmentModeEnabled));
        hashMap.put(Params.TIME, Double.toString(((double) new Date().getTime()) / 1000.0d));
        hashMap.put(REQUEST_ID_KEY, this.requestId);
        String str = token;
        if (str != null) {
            hashMap.put("token", str);
        }
        hashMap.putAll(this.params);
        return hashMap;
    }

    /* access modifiers changed from: private */
    public void saveRequestForLater(Map<String, Object> map) {
        try {
            this.requestSequenceRecorder.beforeWrite();
            synchronized (RequestOld.class) {
                SharedPreferences sharedPreferences = Leanplum.getContext().getSharedPreferences("__leanplum__", 0);
                Editor edit = sharedPreferences.edit();
                long eventsCount = LeanplumEventDataManager.getEventsCount();
                String string = sharedPreferences.getString(Defaults.UUID_KEY, null);
                if (string == null || eventsCount % ((long) MAX_EVENTS_PER_API_CALL) == 0) {
                    string = UUID.randomUUID().toString();
                    edit.putString(Defaults.UUID_KEY, string);
                    SharedPreferencesUtil.commitChanges(edit);
                }
                map.put(UUID_KEY, string);
                LeanplumEventDataManager.insertEvent(JsonConverter.toJson(map));
                this.dataBaseIndex = eventsCount;
                if (this.response != null || (this.error != null && !Util.isConnected())) {
                    eventCallbackManager.addCallbacks(this, this.response, this.error);
                }
            }
            this.requestSequenceRecorder.afterWrite();
        } catch (Throwable th) {
            Util.handleException(th);
        }
    }

    public void send() {
        sendEventually();
        if (Constants.isDevelopmentModeEnabled) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = lastSendTimeMs;
            OsHandler.getInstance().postDelayed(new Runnable() {
                public void run() {
                    try {
                        RequestOld.this.sendIfConnected();
                    } catch (Throwable th) {
                        Util.handleException(th);
                    }
                }
            }, (j == 0 || currentTimeMillis - j > DEVELOPMENT_MAX_DELAY_MS) ? DEVELOPMENT_MIN_DELAY_MS : (j + DEVELOPMENT_MAX_DELAY_MS) - currentTimeMillis);
        }
        Leanplum.countAggregator().incrementCount("send_request");
    }

    public void sendIfDelayed() {
        sendEventually();
        OsHandler.getInstance().postDelayed(new Runnable() {
            public void run() {
                try {
                    RequestOld.this.sendIfDelayedHelper();
                } catch (Throwable th) {
                    Util.handleException(th);
                }
            }
        }, 1000);
        Leanplum.countAggregator().incrementCount("send_if_delayed");
    }

    /* access modifiers changed from: private */
    public void sendIfDelayedHelper() {
        if (Constants.isDevelopmentModeEnabled) {
            send();
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = lastSendTimeMs;
        if (j == 0 || currentTimeMillis - j > PRODUCTION_DELAY) {
            sendIfConnected();
        }
    }

    public void sendIfConnected() {
        if (Util.isConnected()) {
            sendNow();
        } else {
            sendEventually();
            Log.m281i("Device is offline, will send later");
            triggerErrorCallback(new Exception("Not connected to the Internet"));
        }
        Leanplum.countAggregator().incrementCount("send_if_connected");
    }

    private void triggerErrorCallback(Exception exc) {
        ErrorCallback errorCallback = this.error;
        if (errorCallback != null) {
            errorCallback.error(exc);
        }
        if (apiResponse != null) {
            List unsentRequests = getUnsentRequests(1.0d);
            apiResponse.response(removeIrrelevantBackgroundStartRequests(unsentRequests), null, unsentRequests.size());
        }
    }

    private static boolean attachApiKeys(Map<String, Object> map) {
        String str = appId;
        if (str == null || accessKey == null) {
            Log.m280e("API keys are not set. Please use Leanplum.setAppIdForDevelopmentMode or Leanplum.setAppIdForProductionMode.");
            return false;
        }
        map.put(Params.APP_ID, str);
        map.put(Params.CLIENT_KEY, accessKey);
        map.put(Params.CLIENT, Constants.CLIENT);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0059, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseResponseBody(org.json.JSONObject r6, java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r7, java.lang.Exception r8, int r9) {
        /*
            r5 = this;
            java.lang.Class<com.leanplum.internal.RequestOld> r0 = com.leanplum.internal.RequestOld.class
            monitor-enter(r0)
            if (r6 != 0) goto L_0x000e
            if (r8 == 0) goto L_0x000e
            com.leanplum.internal.LeanplumEventCallbackManager r6 = eventCallbackManager     // Catch:{ all -> 0x005a }
            r6.invokeAllCallbacksWithError(r8, r9)     // Catch:{ all -> 0x005a }
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            return
        L_0x000e:
            if (r6 != 0) goto L_0x0012
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            return
        L_0x0012:
            com.leanplum.internal.RequestOld$ApiResponseCallback r1 = apiResponse     // Catch:{ all -> 0x005a }
            if (r1 == 0) goto L_0x001b
            com.leanplum.internal.RequestOld$ApiResponseCallback r1 = apiResponse     // Catch:{ all -> 0x005a }
            r1.response(r7, r6, r9)     // Catch:{ all -> 0x005a }
        L_0x001b:
            int r7 = numResponses(r6)     // Catch:{ all -> 0x005a }
            r1 = 0
            r2 = r8
            r8 = 0
        L_0x0022:
            if (r8 >= r7) goto L_0x004b
            org.json.JSONObject r3 = getResponseAt(r6, r8)     // Catch:{ all -> 0x005a }
            boolean r4 = isResponseSuccess(r3)     // Catch:{ all -> 0x005a }
            if (r4 == 0) goto L_0x002f
            goto L_0x0048
        L_0x002f:
            java.lang.String r3 = getResponseError(r3)     // Catch:{ all -> 0x005a }
            java.lang.String r3 = r5.getReadableErrorMessage(r3)     // Catch:{ all -> 0x005a }
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x005a }
            r4[r1] = r3     // Catch:{ all -> 0x005a }
            com.leanplum.internal.Log.m280e(r4)     // Catch:{ all -> 0x005a }
            int r4 = r7 + -1
            if (r8 != r4) goto L_0x0048
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ all -> 0x005a }
            r2.<init>(r3)     // Catch:{ all -> 0x005a }
        L_0x0048:
            int r8 = r8 + 1
            goto L_0x0022
        L_0x004b:
            if (r2 == 0) goto L_0x0053
            com.leanplum.internal.LeanplumEventCallbackManager r6 = eventCallbackManager     // Catch:{ all -> 0x005a }
            r6.invokeAllCallbacksWithError(r2, r9)     // Catch:{ all -> 0x005a }
            goto L_0x0058
        L_0x0053:
            com.leanplum.internal.LeanplumEventCallbackManager r7 = eventCallbackManager     // Catch:{ all -> 0x005a }
            r7.invokeAllCallbacksForResponse(r6, r9)     // Catch:{ all -> 0x005a }
        L_0x0058:
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            return
        L_0x005a:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.RequestOld.parseResponseBody(org.json.JSONObject, java.util.List, java.lang.Exception, int):void");
    }

    @NonNull
    private String getReadableErrorMessage(String str) {
        if (str == null || str.length() == 0) {
            return "API error";
        }
        if (str.startsWith("App not found")) {
            Constants.isInPermanentFailureState = true;
            return "No app matching the provided app ID was found.";
        } else if (str.startsWith("Invalid access key")) {
            Constants.isInPermanentFailureState = true;
            return "The access key you provided is not valid for this app.";
        } else if (str.startsWith("Development mode requested but not permitted")) {
            Constants.isInPermanentFailureState = true;
            return "A call to Leanplum.setAppIdForDevelopmentMode with your production key was made, which is not permitted.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("API error: ");
            sb.append(str);
            return sb.toString();
        }
    }

    private void sendNow() {
        if (!Constants.isTestMode) {
            if (appId == null) {
                Log.m280e("Cannot send request. appId is not set.");
            } else if (accessKey == null) {
                Log.m280e("Cannot send request. accessKey is not set.");
            } else {
                sendEventually();
                Leanplum.countAggregator().incrementCount("send_now");
                Util.executeAsyncTask(true, new AsyncTask<Void, Void, Void>() {
                    /* access modifiers changed from: protected */
                    public Void doInBackground(Void... voidArr) {
                        try {
                            RequestOld.this.sendRequests();
                        } catch (Throwable th) {
                            Util.handleException(th);
                        }
                        return null;
                    }
                }, new Void[0]);
            }
        }
    }

    private RequestsWithEncoding getRequestsWithEncodedStringForErrors() {
        ArrayList arrayList = new ArrayList();
        String uuid = UUID.randomUUID().toString();
        for (Map map : localErrors) {
            map.put(UUID_KEY, uuid);
            arrayList.add(map);
        }
        String jsonEncodeRequests = jsonEncodeRequests(arrayList);
        RequestsWithEncoding requestsWithEncoding = new RequestsWithEncoding();
        requestsWithEncoding.unsentRequests = arrayList;
        requestsWithEncoding.requestsToSend = arrayList;
        requestsWithEncoding.jsonEncodedString = jsonEncodeRequests;
        return requestsWithEncoding;
    }

    /* access modifiers changed from: protected */
    public RequestsWithEncoding getRequestsWithEncodedStringStoredRequests(double d) {
        List<Map<String, Object>> list;
        List<Map<String, Object>> list2;
        try {
            RequestsWithEncoding requestsWithEncoding = new RequestsWithEncoding();
            if (d < 0.01d) {
                list2 = new ArrayList<>(0);
                list = new ArrayList<>(0);
            } else {
                list2 = getUnsentRequests(d);
                list = removeIrrelevantBackgroundStartRequests(list2);
            }
            String jsonEncodeRequests = jsonEncodeRequests(list);
            requestsWithEncoding.unsentRequests = list2;
            requestsWithEncoding.requestsToSend = list;
            requestsWithEncoding.jsonEncodedString = jsonEncodeRequests;
            return requestsWithEncoding;
        } catch (OutOfMemoryError unused) {
            return getRequestsWithEncodedStringStoredRequests(d * 0.5d);
        }
    }

    private RequestsWithEncoding getRequestsWithEncodedString() {
        if (localErrors.size() != 0) {
            return getRequestsWithEncodedStringForErrors();
        }
        return getRequestsWithEncodedStringStoredRequests(1.0d);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x011a, code lost:
        if (r5 != null) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r5.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x018c, code lost:
        if (r5 != null) goto L_0x011c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0152 A[SYNTHETIC, Splitter:B:48:0x0152] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0192 A[SYNTHETIC, Splitter:B:57:0x0192] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendRequests() {
        /*
            r12 = this;
            java.lang.String r0 = "\n"
            com.leanplum.internal.CountAggregator r1 = com.leanplum.Leanplum.countAggregator()
            r1.sendAllCounts()
            com.leanplum.internal.RequestSequenceRecorder r1 = r12.requestSequenceRecorder
            r1.beforeRead()
            com.leanplum.internal.RequestOld$RequestsWithEncoding r1 = r12.getRequestsWithEncodedString()
            com.leanplum.internal.RequestSequenceRecorder r2 = r12.requestSequenceRecorder
            r2.afterRead()
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r2 = r1.unsentRequests
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r3 = r1.requestsToSend
            java.lang.String r1 = r1.jsonEncodedString
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L_0x0024
            return
        L_0x0024:
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            boolean r4 = attachApiKeys(r7)
            if (r4 != 0) goto L_0x0030
            return
        L_0x0030:
            java.lang.String r4 = "data"
            r7.put(r4, r1)
            java.lang.String r1 = com.leanplum.internal.Constants.LEANPLUM_VERSION
            java.lang.String r4 = "sdkVersion"
            r7.put(r4, r1)
            java.lang.String r1 = "action"
            java.lang.String r4 = "multi"
            r7.put(r1, r4)
            java.util.Date r1 = new java.util.Date
            r1.<init>()
            long r4 = r1.getTime()
            double r4 = (double) r4
            r8 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r4 = r4 / r8
            java.lang.String r1 = java.lang.Double.toString(r4)
            java.lang.String r4 = "time"
            r7.put(r4, r1)
            r1 = 0
            r4 = 1
            r11 = 0
            java.lang.String r5 = com.leanplum.internal.Constants.API_HOST_NAME     // Catch:{ JSONException -> 0x0156, Exception -> 0x0129 }
            java.lang.String r6 = com.leanplum.internal.Constants.API_SERVLET     // Catch:{ JSONException -> 0x0156, Exception -> 0x0129 }
            java.lang.String r8 = r12.httpMethod     // Catch:{ JSONException -> 0x0156, Exception -> 0x0129 }
            boolean r9 = com.leanplum.internal.Constants.API_SSL     // Catch:{ JSONException -> 0x0156, Exception -> 0x0129 }
            int r10 = com.leanplum.internal.Constants.NETWORK_TIMEOUT_SECONDS     // Catch:{ JSONException -> 0x0156, Exception -> 0x0129 }
            java.net.HttpURLConnection r5 = com.leanplum.internal.Util.operation(r5, r6, r7, r8, r9, r10)     // Catch:{ JSONException -> 0x0156, Exception -> 0x0129 }
            org.json.JSONObject r6 = com.leanplum.internal.Util.getJsonResponse(r5)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            int r7 = r5.getResponseCode()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r8 = 200(0xc8, float:2.8E-43)
            if (r7 < r8) goto L_0x00e7
            r8 = 299(0x12b, float:4.19E-43)
            if (r7 > r8) goto L_0x00e7
            if (r6 != 0) goto L_0x009a
            java.lang.Exception r6 = new java.lang.Exception     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            java.lang.String r7 = "Response JSON is null."
            r6.<init>(r7)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            int r7 = r2.size()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            deleteSentRequests(r7)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            int r7 = r2.size()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r12.parseResponseBody(r11, r3, r6, r7)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            if (r5 == 0) goto L_0x0099
            r5.disconnect()     // Catch:{ Throwable -> 0x0196 }
        L_0x0099:
            return
        L_0x009a:
            int r7 = numResponses(r6)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            int r8 = r3.size()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            if (r7 == r8) goto L_0x00c8
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r9.<init>()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            java.lang.String r10 = "Sent "
            r9.append(r10)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            int r10 = r3.size()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r9.append(r10)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            java.lang.String r10 = " requests but only received "
            r9.append(r10)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r9.append(r7)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            java.lang.String r7 = r9.toString()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r8[r1] = r7     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            com.leanplum.internal.Log.m284w(r8)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
        L_0x00c8:
            int r7 = r2.size()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r12.parseResponseBody(r6, r3, r11, r7)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            java.util.List<java.util.Map<java.lang.String, java.lang.Object>> r6 = localErrors     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r6.clear()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            int r6 = r2.size()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            deleteSentRequests(r6)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            int r6 = r2.size()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            int r7 = MAX_EVENTS_PER_API_CALL     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            if (r6 != r7) goto L_0x011a
            r12.sendRequests()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            goto L_0x011a
        L_0x00e7:
            java.lang.Exception r8 = new java.lang.Exception     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r9.<init>()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            java.lang.String r10 = "HTTP error "
            r9.append(r10)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r9.append(r7)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            java.lang.String r9 = r9.toString()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r8.<init>(r9)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r9 = -1
            if (r7 == r9) goto L_0x011a
            r9 = 408(0x198, float:5.72E-43)
            if (r7 == r9) goto L_0x011a
            r9 = 500(0x1f4, float:7.0E-43)
            if (r7 < r9) goto L_0x010c
            r9 = 599(0x257, float:8.4E-43)
            if (r7 <= r9) goto L_0x011a
        L_0x010c:
            int r7 = r2.size()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            deleteSentRequests(r7)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            int r7 = r2.size()     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
            r12.parseResponseBody(r6, r3, r8, r7)     // Catch:{ JSONException -> 0x0124, Exception -> 0x0121 }
        L_0x011a:
            if (r5 == 0) goto L_0x019a
        L_0x011c:
            r5.disconnect()     // Catch:{ Throwable -> 0x0196 }
            goto L_0x019a
        L_0x0121:
            r2 = move-exception
            r11 = r5
            goto L_0x012a
        L_0x0124:
            r6 = move-exception
            goto L_0x0158
        L_0x0126:
            r0 = move-exception
            r5 = r11
            goto L_0x0190
        L_0x0129:
            r2 = move-exception
        L_0x012a:
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ all -> 0x0126 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0126 }
            r4.<init>()     // Catch:{ all -> 0x0126 }
            java.lang.String r5 = "Unable to send request: "
            r4.append(r5)     // Catch:{ all -> 0x0126 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0126 }
            r4.append(r5)     // Catch:{ all -> 0x0126 }
            r4.append(r0)     // Catch:{ all -> 0x0126 }
            java.lang.String r0 = com.leanplum.internal.Log.getStackTraceString(r2)     // Catch:{ all -> 0x0126 }
            r4.append(r0)     // Catch:{ all -> 0x0126 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0126 }
            r3[r1] = r0     // Catch:{ all -> 0x0126 }
            com.leanplum.internal.Log.m280e(r3)     // Catch:{ all -> 0x0126 }
            if (r11 == 0) goto L_0x019a
            r11.disconnect()     // Catch:{ Throwable -> 0x0196 }
            goto L_0x019a
        L_0x0156:
            r6 = move-exception
            r5 = r11
        L_0x0158:
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x018f }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x018f }
            r7.<init>()     // Catch:{ all -> 0x018f }
            java.lang.String r8 = "Error parsing JSON response: "
            r7.append(r8)     // Catch:{ all -> 0x018f }
            java.lang.String r8 = r6.toString()     // Catch:{ all -> 0x018f }
            r7.append(r8)     // Catch:{ all -> 0x018f }
            r7.append(r0)     // Catch:{ all -> 0x018f }
            java.lang.String r0 = com.leanplum.internal.Log.getStackTraceString(r6)     // Catch:{ all -> 0x018f }
            r7.append(r0)     // Catch:{ all -> 0x018f }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x018f }
            r4[r1] = r0     // Catch:{ all -> 0x018f }
            com.leanplum.internal.Log.m280e(r4)     // Catch:{ all -> 0x018f }
            int r0 = r2.size()     // Catch:{ all -> 0x018f }
            deleteSentRequests(r0)     // Catch:{ all -> 0x018f }
            int r0 = r2.size()     // Catch:{ all -> 0x018f }
            r12.parseResponseBody(r11, r3, r6, r0)     // Catch:{ all -> 0x018f }
            if (r5 == 0) goto L_0x019a
            goto L_0x011c
        L_0x018f:
            r0 = move-exception
        L_0x0190:
            if (r5 == 0) goto L_0x0195
            r5.disconnect()     // Catch:{ Throwable -> 0x0196 }
        L_0x0195:
            throw r0     // Catch:{ Throwable -> 0x0196 }
        L_0x0196:
            r0 = move-exception
            com.leanplum.internal.Util.handleException(r0)
        L_0x019a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.RequestOld.sendRequests():void");
    }

    public void sendEventually() {
        if (!Constants.isTestMode && !LeanplumEventDataManager.willSendErrorLog) {
            if (!this.sent) {
                this.sent = true;
                createArgsDictionary();
                Util.executeAsyncTask(true, new AsyncTask<Void, Void, Void>() {
                    /* access modifiers changed from: protected */
                    public Void doInBackground(Void... voidArr) {
                        RequestOld.this.saveRequestForLater(RequestOld.this.createArgsDictionary());
                        return null;
                    }
                }, new Void[0]);
            }
            Leanplum.countAggregator().incrementCount("send_eventually");
        }
    }

    static void deleteSentRequests(int i) {
        if (i != 0) {
            synchronized (RequestOld.class) {
                LeanplumEventDataManager.deleteEvents(i);
            }
        }
    }

    public List<Map<String, Object>> getUnsentRequests(double d) {
        List<Map<String, Object>> events;
        synchronized (RequestOld.class) {
            lastSendTimeMs = System.currentTimeMillis();
            Editor edit = Leanplum.getContext().getSharedPreferences("__leanplum__", 0).edit();
            events = LeanplumEventDataManager.getEvents((int) (d * ((double) MAX_EVENTS_PER_API_CALL)));
            edit.remove(Defaults.UUID_KEY);
            SharedPreferencesUtil.commitChanges(edit);
        }
        return events;
    }

    private static List<Map<String, Object>> removeIrrelevantBackgroundStartRequests(List<Map<String, Object>> list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                Map map = (Map) list.get(i);
                if (i < size - 1) {
                    String str = "action";
                    Object obj = ((Map) list.get(i + 1)).get(str);
                    String str2 = Methods.START;
                    if (str2.equals(obj) && str2.equals(map.get(str)) && Boolean.TRUE.toString().equals(map.get(Params.BACKGROUND))) {
                    }
                }
                arrayList.add(map);
            }
        }
        return arrayList;
    }

    protected static String jsonEncodeRequests(List<Map<String, Object>> list) {
        HashMap hashMap = new HashMap();
        hashMap.put("data", list);
        return JsonConverter.toJson(hashMap);
    }

    private static String getSizeAsString(int i) {
        if (i < 1024) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(" B");
            return sb.toString();
        } else if (i < 1048576) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(i >> 10);
            sb2.append(" KB");
            return sb2.toString();
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(i >> 20);
            sb3.append(" MB");
            return sb3.toString();
        }
    }

    /* access modifiers changed from: private */
    public static void printUploadProgress() {
        int size = fileUploadSize.size();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (Entry entry : fileUploadSize.entrySet()) {
            File file = (File) entry.getKey();
            long longValue = ((Long) entry.getValue()).longValue();
            double doubleValue = ((Double) fileUploadProgress.get(file)).doubleValue();
            if (doubleValue == 1.0d) {
                i++;
            }
            i2 += (int) (((double) longValue) * doubleValue);
            i3 = (int) (((long) i3) + longValue);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Uploading resources. ");
        sb.append(i);
        sb.append('/');
        sb.append(size);
        sb.append(" files completed; ");
        sb.append(getSizeAsString(i2));
        sb.append('/');
        sb.append(getSizeAsString(i3));
        sb.append(" transferred.");
        String sb2 = sb.toString();
        if (!fileUploadProgressString.equals(sb2)) {
            fileUploadProgressString = sb2;
            Log.m281i(sb2);
        }
    }

    public void sendFilesNow(List<String> list, final List<InputStream> list2) {
        long j;
        if (!Constants.isTestMode) {
            final Map createArgsDictionary = createArgsDictionary();
            if (attachApiKeys(createArgsDictionary)) {
                final ArrayList arrayList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    String str = (String) list.get(i);
                    if (str != null && !Boolean.TRUE.equals(fileTransferStatus.get(str))) {
                        File file = new File(str);
                        try {
                            j = (long) ((InputStream) list2.get(i)).available();
                        } catch (IOException unused) {
                            j = file.length();
                        } catch (NullPointerException unused2) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Unable to read file ");
                            sb.append(str);
                            Log.m280e(sb.toString());
                        }
                        fileTransferStatus.put(str, Boolean.valueOf(true));
                        arrayList.add(file);
                        fileUploadSize.put(file, Long.valueOf(j));
                        fileUploadProgress.put(file, Double.valueOf(0.0d));
                    }
                }
                if (arrayList.size() != 0) {
                    Leanplum.countAggregator().incrementCount("send_files_now");
                    printUploadProgress();
                    Util.executeAsyncTask(false, new AsyncTask<Void, Void, Void>() {
                        /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.String] */
                        /* JADX WARNING: type inference failed for: r4v1, types: [java.net.HttpURLConnection] */
                        /* JADX WARNING: type inference failed for: r4v2 */
                        /* JADX WARNING: type inference failed for: r4v5 */
                        /* JADX WARNING: type inference failed for: r4v6 */
                        /* JADX WARNING: type inference failed for: r4v7 */
                        /* JADX WARNING: type inference failed for: r4v8 */
                        /* JADX WARNING: type inference failed for: r4v9 */
                        /* JADX WARNING: type inference failed for: r4v10 */
                        /* JADX WARNING: type inference failed for: r4v11 */
                        /* JADX WARNING: type inference failed for: r4v12, types: [java.net.HttpURLConnection] */
                        /* JADX WARNING: type inference failed for: r4v13, types: [java.net.HttpURLConnection] */
                        /* JADX WARNING: type inference failed for: r4v14 */
                        /* JADX WARNING: type inference failed for: r4v15 */
                        /* JADX WARNING: type inference failed for: r4v16 */
                        /* JADX WARNING: type inference failed for: r4v17 */
                        /* JADX WARNING: type inference failed for: r4v18 */
                        /* access modifiers changed from: protected */
                        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0072, code lost:
                            if (r4 != 0) goto L_0x0074;
                         */
                        /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e1, code lost:
                            if (r4 == 0) goto L_0x00e4;
                         */
                        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r4v2
                          assigns: []
                          uses: []
                          mth insns count: 113
                        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
                        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
                        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
                        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
                        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
                        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
                        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
                        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
                        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
                        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                         */
                        /* JADX WARNING: Removed duplicated region for block: B:30:0x0098 A[Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f, all -> 0x0109 }] */
                        /* JADX WARNING: Removed duplicated region for block: B:32:0x00a3 A[Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f, all -> 0x0109 }] */
                        /* JADX WARNING: Removed duplicated region for block: B:37:0x00b7 A[Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f, all -> 0x0109 }] */
                        /* JADX WARNING: Removed duplicated region for block: B:39:0x00c2 A[Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f, all -> 0x0109 }] */
                        /* JADX WARNING: Removed duplicated region for block: B:44:0x00d8 A[Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f, all -> 0x0109 }] */
                        /* JADX WARNING: Removed duplicated region for block: B:50:0x00f0 A[LOOP:0: B:48:0x00ea->B:50:0x00f0, LOOP_END] */
                        /* JADX WARNING: Removed duplicated region for block: B:56:0x010c  */
                        /* JADX WARNING: Unknown variable types count: 4 */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public java.lang.Void doInBackground(java.lang.Void... r14) {
                            /*
                                r13 = this;
                                java.lang.Object r14 = com.leanplum.internal.RequestOld.uploadFileLock
                                monitor-enter(r14)
                                r0 = 2
                                r1 = 0
                                r2 = 0
                                r3 = 1
                                java.lang.String r4 = "file"
                                java.util.List r5 = r1     // Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f }
                                java.util.List r6 = r12     // Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f }
                                java.lang.String r7 = com.leanplum.internal.Constants.API_HOST_NAME     // Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f }
                                java.lang.String r8 = com.leanplum.internal.Constants.API_SERVLET     // Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f }
                                java.util.Map r9 = r0     // Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f }
                                com.leanplum.internal.RequestOld r10 = com.leanplum.internal.RequestOld.this     // Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f }
                                java.lang.String r10 = r10.httpMethod     // Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f }
                                boolean r11 = com.leanplum.internal.Constants.API_SSL     // Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f }
                                r12 = 60
                                java.net.HttpURLConnection r4 = com.leanplum.internal.Util.uploadFilesOperation(r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ JSONException -> 0x00c3, SocketTimeoutException -> 0x00a4, Exception -> 0x0083, all -> 0x007f }
                                if (r4 == 0) goto L_0x005a
                                org.json.JSONObject r5 = com.leanplum.internal.Util.getJsonResponse(r4)     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                int r6 = r4.getResponseCode()     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                r7 = 200(0xc8, float:2.8E-43)
                                if (r6 != r7) goto L_0x0043
                                com.leanplum.internal.RequestOld r6 = com.leanplum.internal.RequestOld.this     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                com.leanplum.internal.RequestOld$ResponseCallback r6 = r6.response     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                if (r6 == 0) goto L_0x0072
                                com.leanplum.internal.RequestOld r6 = com.leanplum.internal.RequestOld.this     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                com.leanplum.internal.RequestOld$ResponseCallback r6 = r6.response     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                r6.response(r5)     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                goto L_0x0072
                            L_0x0043:
                                java.lang.Exception r5 = new java.lang.Exception     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                r7.<init>()     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                java.lang.String r8 = "Leanplum: Error sending request: "
                                r7.append(r8)     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                r7.append(r6)     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                java.lang.String r6 = r7.toString()     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                r5.<init>(r6)     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                throw r5     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                            L_0x005a:
                                com.leanplum.internal.RequestOld r5 = com.leanplum.internal.RequestOld.this     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                com.leanplum.internal.RequestOld$ErrorCallback r5 = r5.error     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                if (r5 == 0) goto L_0x0072
                                com.leanplum.internal.RequestOld r5 = com.leanplum.internal.RequestOld.this     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                com.leanplum.internal.RequestOld$ErrorCallback r5 = r5.error     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                java.lang.Exception r6 = new java.lang.Exception     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                java.lang.String r7 = "Leanplum: Unable to read file."
                                r6.<init>(r7)     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                                r5.error(r6)     // Catch:{ JSONException -> 0x007d, SocketTimeoutException -> 0x007b, Exception -> 0x0079 }
                            L_0x0072:
                                if (r4 == 0) goto L_0x00e4
                            L_0x0074:
                                r4.disconnect()     // Catch:{ all -> 0x0110 }
                                goto L_0x00e4
                            L_0x0079:
                                r5 = move-exception
                                goto L_0x0085
                            L_0x007b:
                                r0 = move-exception
                                goto L_0x00a6
                            L_0x007d:
                                r5 = move-exception
                                goto L_0x00c5
                            L_0x007f:
                                r0 = move-exception
                                r4 = r1
                                goto L_0x010a
                            L_0x0083:
                                r5 = move-exception
                                r4 = r1
                            L_0x0085:
                                java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0109 }
                                java.lang.String r6 = "Unable to send file."
                                r0[r2] = r6     // Catch:{ all -> 0x0109 }
                                r0[r3] = r5     // Catch:{ all -> 0x0109 }
                                com.leanplum.internal.Log.m280e(r0)     // Catch:{ all -> 0x0109 }
                                com.leanplum.internal.RequestOld r0 = com.leanplum.internal.RequestOld.this     // Catch:{ all -> 0x0109 }
                                com.leanplum.internal.RequestOld$ErrorCallback r0 = r0.error     // Catch:{ all -> 0x0109 }
                                if (r0 == 0) goto L_0x00a1
                                com.leanplum.internal.RequestOld r0 = com.leanplum.internal.RequestOld.this     // Catch:{ all -> 0x0109 }
                                com.leanplum.internal.RequestOld$ErrorCallback r0 = r0.error     // Catch:{ all -> 0x0109 }
                                r0.error(r5)     // Catch:{ all -> 0x0109 }
                            L_0x00a1:
                                if (r4 == 0) goto L_0x00e4
                                goto L_0x0074
                            L_0x00a4:
                                r0 = move-exception
                                r4 = r1
                            L_0x00a6:
                                java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0109 }
                                java.lang.String r5 = "Timeout uploading files. Try again or limit the number of files to upload with parameters to syncResourcesAsync."
                                r3[r2] = r5     // Catch:{ all -> 0x0109 }
                                com.leanplum.internal.Log.m280e(r3)     // Catch:{ all -> 0x0109 }
                                com.leanplum.internal.RequestOld r2 = com.leanplum.internal.RequestOld.this     // Catch:{ all -> 0x0109 }
                                com.leanplum.internal.RequestOld$ErrorCallback r2 = r2.error     // Catch:{ all -> 0x0109 }
                                if (r2 == 0) goto L_0x00c0
                                com.leanplum.internal.RequestOld r2 = com.leanplum.internal.RequestOld.this     // Catch:{ all -> 0x0109 }
                                com.leanplum.internal.RequestOld$ErrorCallback r2 = r2.error     // Catch:{ all -> 0x0109 }
                                r2.error(r0)     // Catch:{ all -> 0x0109 }
                            L_0x00c0:
                                if (r4 == 0) goto L_0x00e4
                                goto L_0x0074
                            L_0x00c3:
                                r5 = move-exception
                                r4 = r1
                            L_0x00c5:
                                java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0109 }
                                java.lang.String r6 = "Unable to convert to JSON."
                                r0[r2] = r6     // Catch:{ all -> 0x0109 }
                                r0[r3] = r5     // Catch:{ all -> 0x0109 }
                                com.leanplum.internal.Log.m280e(r0)     // Catch:{ all -> 0x0109 }
                                com.leanplum.internal.RequestOld r0 = com.leanplum.internal.RequestOld.this     // Catch:{ all -> 0x0109 }
                                com.leanplum.internal.RequestOld$ErrorCallback r0 = r0.error     // Catch:{ all -> 0x0109 }
                                if (r0 == 0) goto L_0x00e1
                                com.leanplum.internal.RequestOld r0 = com.leanplum.internal.RequestOld.this     // Catch:{ all -> 0x0109 }
                                com.leanplum.internal.RequestOld$ErrorCallback r0 = r0.error     // Catch:{ all -> 0x0109 }
                                r0.error(r5)     // Catch:{ all -> 0x0109 }
                            L_0x00e1:
                                if (r4 == 0) goto L_0x00e4
                                goto L_0x0074
                            L_0x00e4:
                                java.util.List r0 = r1     // Catch:{ all -> 0x0110 }
                                java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0110 }
                            L_0x00ea:
                                boolean r2 = r0.hasNext()     // Catch:{ all -> 0x0110 }
                                if (r2 == 0) goto L_0x0104
                                java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x0110 }
                                java.io.File r2 = (java.io.File) r2     // Catch:{ all -> 0x0110 }
                                java.util.Map r3 = com.leanplum.internal.RequestOld.fileUploadProgress     // Catch:{ all -> 0x0110 }
                                r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
                                java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ all -> 0x0110 }
                                r3.put(r2, r4)     // Catch:{ all -> 0x0110 }
                                goto L_0x00ea
                            L_0x0104:
                                com.leanplum.internal.RequestOld.printUploadProgress()     // Catch:{ all -> 0x0110 }
                                monitor-exit(r14)     // Catch:{ all -> 0x0110 }
                                return r1
                            L_0x0109:
                                r0 = move-exception
                            L_0x010a:
                                if (r4 == 0) goto L_0x010f
                                r4.disconnect()     // Catch:{ all -> 0x0110 }
                            L_0x010f:
                                throw r0     // Catch:{ all -> 0x0110 }
                            L_0x0110:
                                r0 = move-exception
                                monitor-exit(r14)     // Catch:{ all -> 0x0110 }
                                throw r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.RequestOld.C23845.doInBackground(java.lang.Void[]):java.lang.Void");
                        }
                    }, new Void[0]);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void downloadFile(final String str, final String str2) {
        if (!Constants.isTestMode && !Boolean.TRUE.equals(fileTransferStatus.get(str))) {
            pendingDownloads++;
            StringBuilder sb = new StringBuilder();
            sb.append("Downloading resource ");
            sb.append(str);
            Log.m281i(sb.toString());
            fileTransferStatus.put(str, Boolean.valueOf(true));
            final Map createArgsDictionary = createArgsDictionary();
            createArgsDictionary.put(Keys.FILENAME, str);
            if (attachApiKeys(createArgsDictionary)) {
                Leanplum.countAggregator().incrementCount("download_file");
                Util.executeAsyncTask(false, new AsyncTask<Void, Void, Void>() {
                    /* access modifiers changed from: protected */
                    public Void doInBackground(Void... voidArr) {
                        try {
                            RequestOld.this.downloadHelper(Constants.API_HOST_NAME, Constants.API_SERVLET, str, str2, createArgsDictionary);
                        } catch (Throwable th) {
                            Util.handleException(th);
                        }
                        return null;
                    }
                }, new Void[0]);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c1, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c4, code lost:
        r11 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c5, code lost:
        r15 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c1 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0022] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d1 A[ADDED_TO_REGION, Catch:{ all -> 0x00c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x011a A[Catch:{ all -> 0x00c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0134  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void downloadHelper(java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.util.Map<java.lang.String, java.lang.Object> r15) {
        /*
            r10 = this;
            r0 = 0
            r1 = 2
            r2 = 0
            r3 = 1
            if (r14 != 0) goto L_0x0014
            java.lang.String r7 = r10.httpMethod     // Catch:{ Exception -> 0x00cb }
            boolean r8 = com.leanplum.internal.Constants.API_SSL     // Catch:{ Exception -> 0x00cb }
            int r9 = com.leanplum.internal.Constants.NETWORK_TIMEOUT_SECONDS_FOR_DOWNLOADS     // Catch:{ Exception -> 0x00cb }
            r4 = r11
            r5 = r12
            r6 = r15
            java.net.HttpURLConnection r12 = com.leanplum.internal.Util.operation(r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x00cb }
            goto L_0x0022
        L_0x0014:
            java.lang.String r12 = r10.httpMethod     // Catch:{ Exception -> 0x00cb }
            java.lang.String r15 = "https://"
            boolean r15 = r14.startsWith(r15)     // Catch:{ Exception -> 0x00cb }
            int r4 = com.leanplum.internal.Constants.NETWORK_TIMEOUT_SECONDS_FOR_DOWNLOADS     // Catch:{ Exception -> 0x00cb }
            java.net.HttpURLConnection r12 = com.leanplum.internal.Util.createHttpUrlConnection(r14, r12, r15, r4)     // Catch:{ Exception -> 0x00cb }
        L_0x0022:
            java.net.URL r15 = r12.getURL()     // Catch:{ Exception -> 0x00c4, all -> 0x00c1 }
            r12.connect()     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            int r4 = r12.getResponseCode()     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r5 = 200(0xc8, float:2.8E-43)
            if (r4 != r5) goto L_0x00a0
            java.util.Stack r11 = new java.util.Stack     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r11.<init>()     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r4 = r13
        L_0x0037:
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r5.<init>(r4)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            java.lang.String r4 = r5.getParent()     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            if (r4 == 0) goto L_0x0046
            r11.push(r4)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            goto L_0x0037
        L_0x0046:
            boolean r4 = r11.isEmpty()     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            if (r4 != 0) goto L_0x006d
            java.lang.Object r4 = r11.pop()     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            java.lang.String r4 = com.leanplum.internal.FileManager.fileRelativeToDocuments(r4)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r5.<init>(r4)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            boolean r5 = r5.mkdir()     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            if (r5 != 0) goto L_0x0046
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            java.lang.String r6 = "Failed to create directory: "
            r5[r0] = r6     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r5[r3] = r4     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            com.leanplum.internal.Log.m284w(r5)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            goto L_0x0046
        L_0x006d:
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            java.lang.String r5 = com.leanplum.internal.FileManager.fileRelativeToDocuments(r13)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r11.<init>(r4)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            com.leanplum.internal.Util.saveResponse(r12, r11)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            int r11 = pendingDownloads     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            int r11 = r11 - r3
            pendingDownloads = r11     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            com.leanplum.internal.RequestOld$ResponseCallback r11 = r10.response     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            if (r11 == 0) goto L_0x008c
            com.leanplum.internal.RequestOld$ResponseCallback r11 = r10.response     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r11.response(r2)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
        L_0x008c:
            int r11 = pendingDownloads     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            if (r11 != 0) goto L_0x0099
            com.leanplum.internal.RequestOld$NoPendingDownloadsCallback r11 = noPendingDownloadsBlock     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            if (r11 == 0) goto L_0x0099
            com.leanplum.internal.RequestOld$NoPendingDownloadsCallback r11 = noPendingDownloadsBlock     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r11.noPendingDownloads()     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
        L_0x0099:
            if (r12 == 0) goto L_0x0131
            r12.disconnect()
            goto L_0x0131
        L_0x00a0:
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r5.<init>()     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            java.lang.String r6 = "Leanplum: Error sending request to: "
            r5.append(r6)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r5.append(r11)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            java.lang.String r11 = ", HTTP status code: "
            r5.append(r11)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r5.append(r4)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            java.lang.String r11 = r5.toString()     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            r2.<init>(r11)     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
            throw r2     // Catch:{ Exception -> 0x00bf, all -> 0x00c1 }
        L_0x00bf:
            r11 = move-exception
            goto L_0x00c6
        L_0x00c1:
            r11 = move-exception
            goto L_0x0132
        L_0x00c4:
            r11 = move-exception
            r15 = r2
        L_0x00c6:
            r2 = r12
            goto L_0x00cd
        L_0x00c8:
            r11 = move-exception
            r12 = r2
            goto L_0x0132
        L_0x00cb:
            r11 = move-exception
            r15 = r2
        L_0x00cd:
            boolean r12 = r11 instanceof java.io.EOFException     // Catch:{ all -> 0x00c8 }
            if (r12 == 0) goto L_0x00f7
            if (r2 == 0) goto L_0x00f7
            java.net.URL r12 = r2.getURL()     // Catch:{ all -> 0x00c8 }
            boolean r12 = r12.equals(r15)     // Catch:{ all -> 0x00c8 }
            if (r12 != 0) goto L_0x00f7
            r5 = 0
            java.net.URL r11 = r2.getURL()     // Catch:{ all -> 0x00c8 }
            java.lang.String r6 = r11.toString()     // Catch:{ all -> 0x00c8 }
            java.util.HashMap r9 = new java.util.HashMap     // Catch:{ all -> 0x00c8 }
            r9.<init>()     // Catch:{ all -> 0x00c8 }
            r4 = r10
            r7 = r13
            r8 = r14
            r4.downloadHelper(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00c8 }
            if (r2 == 0) goto L_0x00f6
            r2.disconnect()
        L_0x00f6:
            return
        L_0x00f7:
            java.lang.Object[] r12 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c8 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c8 }
            r14.<init>()     // Catch:{ all -> 0x00c8 }
            java.lang.String r15 = "Error downloading resource:"
            r14.append(r15)     // Catch:{ all -> 0x00c8 }
            r14.append(r13)     // Catch:{ all -> 0x00c8 }
            java.lang.String r13 = r14.toString()     // Catch:{ all -> 0x00c8 }
            r12[r0] = r13     // Catch:{ all -> 0x00c8 }
            r12[r3] = r11     // Catch:{ all -> 0x00c8 }
            com.leanplum.internal.Log.m280e(r12)     // Catch:{ all -> 0x00c8 }
            int r12 = pendingDownloads     // Catch:{ all -> 0x00c8 }
            int r12 = r12 - r3
            pendingDownloads = r12     // Catch:{ all -> 0x00c8 }
            com.leanplum.internal.RequestOld$ErrorCallback r12 = r10.error     // Catch:{ all -> 0x00c8 }
            if (r12 == 0) goto L_0x011f
            com.leanplum.internal.RequestOld$ErrorCallback r12 = r10.error     // Catch:{ all -> 0x00c8 }
            r12.error(r11)     // Catch:{ all -> 0x00c8 }
        L_0x011f:
            int r11 = pendingDownloads     // Catch:{ all -> 0x00c8 }
            if (r11 != 0) goto L_0x012c
            com.leanplum.internal.RequestOld$NoPendingDownloadsCallback r11 = noPendingDownloadsBlock     // Catch:{ all -> 0x00c8 }
            if (r11 == 0) goto L_0x012c
            com.leanplum.internal.RequestOld$NoPendingDownloadsCallback r11 = noPendingDownloadsBlock     // Catch:{ all -> 0x00c8 }
            r11.noPendingDownloads()     // Catch:{ all -> 0x00c8 }
        L_0x012c:
            if (r2 == 0) goto L_0x0131
            r2.disconnect()
        L_0x0131:
            return
        L_0x0132:
            if (r12 == 0) goto L_0x0137
            r12.disconnect()
        L_0x0137:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.internal.RequestOld.downloadHelper(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Map):void");
    }

    public static int numPendingDownloads() {
        return pendingDownloads;
    }

    public static void onNoPendingDownloads(NoPendingDownloadsCallback noPendingDownloadsCallback) {
        noPendingDownloadsBlock = noPendingDownloadsCallback;
    }

    public static int numResponses(JSONObject jSONObject) {
        if (jSONObject == null) {
            return 0;
        }
        try {
            return jSONObject.getJSONArray("response").length();
        } catch (JSONException e) {
            Log.m280e("Could not parse JSON response.", e);
            return 0;
        }
    }

    public static JSONObject getResponseAt(JSONObject jSONObject, int i) {
        Leanplum.countAggregator().incrementCount("get_response_at");
        try {
            return jSONObject.getJSONArray("response").getJSONObject(i);
        } catch (JSONException e) {
            Log.m280e("Could not parse JSON response.", e);
            return null;
        }
    }

    public static JSONObject getLastResponse(JSONObject jSONObject) {
        int numResponses = numResponses(jSONObject);
        Leanplum.countAggregator().incrementCount("get_last_response");
        if (numResponses > 0) {
            return getResponseAt(jSONObject, numResponses - 1);
        }
        return null;
    }

    public static boolean isResponseSuccess(JSONObject jSONObject) {
        Leanplum.countAggregator().incrementCount("is_response_success");
        if (jSONObject == null) {
            return false;
        }
        try {
            return jSONObject.getBoolean(Param.SUCCESS);
        } catch (JSONException e) {
            Log.m280e("Could not parse JSON response.", e);
            return false;
        }
    }

    public static String getResponseError(JSONObject jSONObject) {
        Leanplum.countAggregator().incrementCount("get_response_error");
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("error");
            if (optJSONObject == null) {
                return null;
            }
            return optJSONObject.getString("message");
        } catch (JSONException e) {
            Log.m280e("Could not parse JSON response.", e);
            return null;
        }
    }
}
